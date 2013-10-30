package com.eim.util.security.encription;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Iterator;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.bouncycastle.bcpg.BCPGInputStream;
import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedDataList;
import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPLiteralData;
import org.bouncycastle.openpgp.PGPObjectFactory;
import org.bouncycastle.openpgp.PGPOnePassSignatureList;
import org.bouncycastle.openpgp.PGPPrivateKey;
import org.bouncycastle.openpgp.PGPPublicKeyEncryptedData;
import org.bouncycastle.openpgp.PGPSecretKey;
import org.bouncycastle.openpgp.PGPSecretKeyRingCollection;
import org.bouncycastle.openpgp.PGPUtil;

public class PGPDecipher {
	
	private static Logger logger = Logger.getLogger(PGPDecipher.class);
	static {
		logger.setLevel( Level.INFO );
		logger.addAppender( new ConsoleAppender( new PatternLayout( "%d{dd MMM yy  HH:mm:ss} %-48C{1} %-5p:  %m%n" ) ) );
	}
	

	public static PGPPrivateKey findSecretKey(InputStream keyIn, long keyID, char[] pass) throws IOException, PGPException, NoSuchProviderException {
		logger.debug("Getting private key from file");
		PGPSecretKeyRingCollection pgpSec = new PGPSecretKeyRingCollection(PGPUtil.getDecoderStream(keyIn));
		PGPSecretKey secretKey = pgpSec.getSecretKey(keyID);
		if (secretKey == null) {
			logger.error("Could not read private key!");
			return null;
		}
		return secretKey.extractPrivateKey(pass,"BC");
	}
	
	public static byte[] decrypt(byte[] cipheredData,File privateKeyFile, String privateKeyPassword) {
		logger.debug("Start deciphering file data (size:" + cipheredData.length + ")");

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(cipheredData);
			FileInputStream privKey = new FileInputStream(privateKeyFile);
			return performDecryption(bais, privKey, privateKeyPassword.toCharArray());
		} catch (Exception e) {
			logger.error("Error during deciphering process. Check your log file.",e);
		}
		return null;
	}
	
	public static byte[] decrypt(String file,File privateKeyFile, String privateKeyPassword) throws IOException {
		logger.debug("Start deciphering file [" + file + "]");
		File jFile = new File(file);
		FileInputStream fileInputStream = new FileInputStream(jFile);
		byte[] fileData = new byte[(int)jFile.length()];
		fileInputStream.read(fileData);
		byte[] result = decrypt(fileData, privateKeyFile, privateKeyPassword);
		fileInputStream.close();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private static byte[] performDecryption(InputStream in, InputStream keyIn, char[] passwd) throws Exception {
		logger.debug("Perform data deciphering");
		in = PGPUtil.getDecoderStream(in);
		try {
			PGPObjectFactory pgpF = new PGPObjectFactory(in);
			PGPEncryptedDataList enc;
			Object o = pgpF.nextObject();

			if (o instanceof PGPEncryptedDataList) {
				enc = (PGPEncryptedDataList) o;
			} else {
				enc = (PGPEncryptedDataList) pgpF.nextObject();
			}
			// Finding the secret key
			logger.debug("Trying to find the secret key");
			Iterator iterator = enc.getEncryptedDataObjects();
			PGPPrivateKey privateKey = null;
			PGPPublicKeyEncryptedData publicKeyEncryptedData = null;
			while (privateKey == null && iterator.hasNext()) {
				publicKeyEncryptedData = (PGPPublicKeyEncryptedData) iterator.next();
				logger.info("publicKeyEncryptedData id=" + publicKeyEncryptedData.getKeyID());
				privateKey = findSecretKey(keyIn, publicKeyEncryptedData.getKeyID(), passwd);
			}
			if (privateKey == null) {
				logger.error("Could not find the secret key for the current ciphered data!");
				throw new IllegalArgumentException(	"Could not find the secret key for the current ciphered data!");
			}
			logger.info("Used algorithm:" + privateKey.getKey().getAlgorithm());
			InputStream inputStream = publicKeyEncryptedData.getDataStream(	privateKey, "BC");
			PGPObjectFactory objectFactory = new PGPObjectFactory(inputStream);
			Object message = objectFactory.nextObject();
			if (message instanceof PGPCompressedData) {
				PGPCompressedData cData = (PGPCompressedData) message;
				PGPObjectFactory pgpFact = new PGPObjectFactory(cData.getDataStream());
				message = pgpFact.nextObject();
			}
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			if (message instanceof PGPLiteralData) {
				PGPLiteralData pgpLiteralData = (PGPLiteralData) message;
				// InputStream unc = pgpLiteralData.getInputStream();
				BCPGInputStream  unc = (BCPGInputStream)pgpLiteralData.getInputStream();
				byte [] buf = new byte[2048];
				int position = 0;
				int ch = 0;
				while ((ch = unc.read(buf)) >= 0) {
					byteArrayOutputStream.write(buf,0,ch);
					position += ch;
				}
			} else if (message instanceof PGPOnePassSignatureList) {
				logger.error("The data are signed but there is no literal content.");
				throw new PGPException("The data are signed but there is no literal content");
			} else {
				logger.error("The data are not ciphered using PGP or are corrupted.");
				throw new PGPException("The data are not ciphered using PGP or are corrupted.");
			}
			if (publicKeyEncryptedData.isIntegrityProtected()) {
				if (!publicKeyEncryptedData.verify()) {
					logger.error("The data integrity is not OK.");
				} else {
					logger.info("The data integrity is OK.");
				}
			} else {
				logger.error("The data integrity could not be checked.");
			}
			return byteArrayOutputStream.toByteArray();
		} catch (PGPException e) {
			logger.error("Unknown PGP error encountered. Please check your log file.",e);
		}
		return null;
	}
	
	public static void main(String [] args) {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider ());
		StringBuilder sb = new StringBuilder();
		for (String arg : args) {
			sb.append(arg).append(" ");
		}
		String path = sb.toString().trim();
		System.out.println("Parsing:" + path);
		File folder = new File(path);
		for (File crypt : folder.listFiles()) {
			if (crypt.getName().endsWith("pgp")) {
				try {
					String newName = crypt.getPath().substring(0,crypt.getPath().length()-4);
					System.out.print("Working on [" + crypt.getPath() + "] : ");
					FileOutputStream fos = new FileOutputStream(newName);
					fos.write(PGPDecipher.decrypt(crypt.getPath(),new File("c:\\dev\\Keys & Licences\\seckeylight.asc"),"e1m-1t-SUPPORT!"));
					fos.close();
					System.out.println(newName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
	}
	
}
