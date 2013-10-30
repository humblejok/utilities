package com.eim.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.eim.util.date.DateUtil;

public class FTPUtil {
	
	private static Logger logger = Logger.getLogger(FTPUtil.class);
	
	private final int DEFAULT_TIMEOUT = 180000;
	
	private static FTPUtil instance = null;
	
	private HashMap<String, FTPClient> connections = new HashMap<String, FTPClient>();
	
	private FTPUtil() {
	}
	
	public synchronized static FTPUtil getInstance() {
		if (instance == null) {
			instance = new FTPUtil();
		}
		return instance;
	}
	
	public String getConnection(String connectionId,String host, int port, String login, String password, int timeOut) throws IOException {
		logger.debug("Getting new connection on FTP - " + host + ":" + port);
		FTPClient client = new FTPClient();
		client.setDefaultTimeout(timeOut);
		client.setDefaultPort(port);
		try {
			client.connect(host);
			client.setSoTimeout(DEFAULT_TIMEOUT);
		} catch (SocketException e) {
			logger.error("Network error during connection",e);
			client = null;
			return null;
		} catch (IOException e) {
			logger.error("File error during connection",e);
			client = null;
			return null;
		}
		if (FTPReply.isPositiveCompletion( client.getReplyCode() )) {
			if (client.login(login, password)) {
				logger.debug("Login and login accepted by server");
			} else {
				logger.error("Login and login refused by server");
				client.disconnect();
				client = null;
				throw new SocketException("Login and login refused by server");
			}
		} else {
			client = null;
			logger.error("Connection to FTP server is refused by server");
			throw new SocketException("Connection to FTP server is refused by server");
		}
		
		logger.info("Connection on FTP - " + host + ":" + port + " established. Stored as [" + connectionId + "].");
		connections.put(connectionId, client);
		return connectionId;
	}
	
	public String getConnection(String connectionId,String host, int port, String login, String password) throws IOException {
		logger.debug("Getting new connection on FTP - " + host + ":" + port);
		FTPClient client = new FTPClient();
		client.setDefaultTimeout(DEFAULT_TIMEOUT);
		client.setDefaultPort(port);
		try {
			client.connect(host);
			client.setSoTimeout(DEFAULT_TIMEOUT);
		} catch (SocketException e) {
			logger.error("Network error during connection",e);
			client = null;
			return null;
		} catch (IOException e) {
			logger.error("File error during connection",e);
			client = null;
			return null;
		}
		if (FTPReply.isPositiveCompletion( client.getReplyCode() )) {
			if (client.login(login, password)) {
				logger.debug("Login and login accepted by server");
			} else {
				logger.error("Login and login refused by server");
				client.disconnect();
				client = null;
				throw new SocketException("Login and login refused by server");
			}
		} else {
			client = null;
			logger.error("Connection to FTP server is refused by server");
			throw new SocketException("Connection to FTP server is refused by server");
		}
		
		logger.info("Connection on FTP - " + host + ":" + port + " established. Stored as [" + connectionId + "].");
		connections.put(connectionId, client);
		return connectionId;
	}
		
	public boolean isConnected(String connection) {
		if (connections.get(connection)!=null) {
			return connections.get(connection).isConnected();
		}
		return false;
	}
	
	public String getWorkingDirectory(String connection) {
		try {
			return connections.get(connection).printWorkingDirectory();
		} catch (IOException e) {
			logger.error("Could not get remote folder.",e);
		}
		return "?";
	}
	
	public boolean changeRemoteFolder(String connection, String folderName) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			try {
				client.changeWorkingDirectory(folderName);
				return true;
			} catch (IOException e) {
				logger.error("Could not change remote folder.",e);
			}
		}
		return false;
	}
	
	public void closeConnection(String connection) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException e) {
					client = null;
					logger.error("FTP connection could not be properly closed",e);
				}
			}
			client = null;
		}
	}
	
	public boolean putFile(String connection, String localFile) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(localFile);
				client.storeFile(new File(localFile).getName(),fis);
				fis.close();
				return true;
			} catch (FileNotFoundException e) {
				logger.error("Could not find local file!",e);
			} catch (IOException e) {
				logger.error("Could not upload local file!",e);
			}

		}
		return false;
	}
	
	public ArrayList<String> getRemoteFilesName(String connection) {
		FTPClient client = connections.get(connection);
		ArrayList<String> filesNames = new ArrayList<String>();
		if (client!=null) {
			try {
				for (FTPFile fFile : connections.get(connection).listFiles()) {
					if (fFile.isFile()) {
						filesNames.add(fFile.getName());
					}
				}
				return filesNames;
			} catch (IOException e) {
				logger.error("Problem while searching remote file.",e);
			}
		} else {
			logger.error("Connection could not be retrieved.");
		}
		return null;
	}
	
	public boolean getFile(String connection, String remoteFile, String localDestination, boolean erase, String fullRemotePath) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream( localDestination );
				if (!client.retrieveFile(remoteFile, fileOutputStream)) {
					return false;
				}
				if(fileOutputStream!=null)
					fileOutputStream.close();
				boolean delete = false;
				if (erase) {
					delete = client.deleteFile(fullRemotePath + remoteFile);
				}
				if (!delete && erase) 
					logger.error("Problem deleting ftp file: " + remoteFile + " Full remote path: " + fullRemotePath);
			} catch (FileNotFoundException e) {
				logger.error("Problem while downloading remote file.",e);
				return false;
			} catch (IOException e) {
				logger.error("Problem while writing local file",e);
				return false;
			}
			finally {
				if(fileOutputStream!=null)
					try {
						fileOutputStream.close();
					} catch (IOException e) {
						logger.error("Problem while writing local file",e);
					}
			}
			return true;
		}
		logger.error("Connection could not be retrieved.");
		return false;
	}
	
	public FTPFile [] listConnectionFile(String connection) {
		try {
			return connections.get(connection).listFiles();
		} catch (IOException e) {
			logger.error("Problem while listing remote files.",e);
		}
		return null;
	}
	
	public String remoteFileDayBeforeExists(String connection, Date today, String prefix, String suffix) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			try {
				for (FTPFile fFile : connections.get(connection).listFiles()) {
					if (fFile.isFile()) {
						Date fileRoundToDay = DateUtil.roundToDay(fFile.getTimestamp().getTime());
						Date todayRoundToDay = DateUtil.roundToDay(today==null?new Date():today);
						todayRoundToDay = DateUtil.removeOneDay(todayRoundToDay);
						if (fileRoundToDay.equals(todayRoundToDay) && fFile.getName().toLowerCase().startsWith(prefix.toLowerCase()) && fFile.getName().toLowerCase().endsWith(suffix.toLowerCase())) {
							return  fFile.getName();
						}
					}
				}
			} catch (IOException e) {
				logger.error("Problem while searching remote file.",e);
			}
		} else {
			logger.error("Connection could not be retrieved.");
		}
		return null;
	}
	
	public String remoteFileDayBeforeExists(String connection, Date today) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			try {
				for (FTPFile fFile : connections.get(connection).listFiles()) {
					if (fFile.isFile()) {
						Date fileRoundToDay = DateUtil.roundToDay(fFile.getTimestamp().getTime());
						Date todayRoundToDay = DateUtil.roundToDay(today==null?new Date():today);
						todayRoundToDay = DateUtil.removeOneDay(todayRoundToDay);
						if (fileRoundToDay.equals(todayRoundToDay)) {
							return  fFile.getName();
						}
					}
				}
			} catch (IOException e) {
				logger.error("Problem while searching remote file.",e);
			}
		} else {
			logger.error("Connection could not be retrieved.");
		}
		return null;
	}
	
	public String remoteFileTodayExists(String connection, Date today) {
		FTPClient client = connections.get(connection);
		if (client!=null) {
			try {
				for (FTPFile fFile : connections.get(connection).listFiles()) {
					if (fFile.isFile()) {
						Date fileRoundToDay = DateUtil.roundToDay(fFile.getTimestamp().getTime());
						Date todayRoundToDay = DateUtil.roundToDay(today==null?new Date():today);
						if (fileRoundToDay.equals(todayRoundToDay)) {
							return  fFile.getName();
						}
					}
				}
			} catch (IOException e) {
				logger.error("Problem while searching remote file.",e);
			}
		} else {
			logger.error("Connection could not be retrieved.");
		}
		return null;
	}
	
	public boolean remoteFileExist(String connection, String remoteFile) {
		try {
			for (FTPFile fFile : connections.get(connection).listFiles()) {
				if (fFile.getName().equals(remoteFile)) {
					return true;
				}
			}
		} catch (IOException e) {
			logger.error("Problem while searching remote file.",e);
		}
		return false;
	}
	
}
