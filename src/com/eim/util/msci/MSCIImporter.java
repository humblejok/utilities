/**
 * Title           : $Workfile: MSCIImporter.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10/08/07 9:44 $
 * By              : $Author: Als $
 * Version number  : $Revision: 12 $
 *
 * $History: MSCIImporter.java $
 * 
 * *****************  Version 12  *****************
 * User: Als          Date: 10/08/07   Time: 9:44
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 * Refactoring for Java 5
 * 
 * *****************  Version 11  *****************
 * User: Sdj          Date: 21.06.06   Time: 15:57
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 * Integrating single import into the console editor
 * 
 * *****************  Version 10  *****************
 * User: Als          Date: 24.11.04   Time: 10:07
 * Updated in $/Current/Projects/utilities/src/com/eim/util/msci
 */
package com.eim.util.msci;

import com.eim.util.msci.event.EndAnalysisMsciInformationEvent;
import com.eim.util.msci.event.EndAnalysisMsciTracksEvent;
import com.eim.util.msci.event.EndZipDecompressEvent;
import com.eim.util.msci.event.EntryZipDecompressEvent;
import com.eim.util.msci.event.MsciImporterEvent;
import com.eim.util.msci.event.MsciImporterEventListener;
import com.eim.util.msci.event.StartAnalysisMsciInformationEvent;
import com.eim.util.msci.event.StartAnalysisMsciTracksEvent;
import com.eim.util.msci.event.StartZipDecompressEvent;

import org.apache.log4j.Logger;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * JavaDoc class comment
 *
 * @author  als
 */
@SuppressWarnings("unchecked")
public class MSCIImporter {

	//~ Static fields/initializers ---------------------------------------------

	private static Logger logger = Logger.getLogger( MSCIImporter.class );

	//~ Instance fields --------------------------------------------------------

	protected int	    fundNb			 = -1;
	protected int	    indexNb			 = -1;
	protected ArrayList<MsciImporterEventListener> listeners;
	protected File[]    msciFiles;
	protected boolean   unzipDone;
	protected String    workingDirectory;
	protected File	    zipFile;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new MSCIImporter object.
	 *
	 * @param  workingDirectory  DOCUMENT ME!
	 * @param  zipFileName       DOCUMENT ME!
	 */
	public MSCIImporter(String workingDirectory, String zipFileName) {
		this( workingDirectory );
		unzipDone						 = false;
		zipFile							 = new File( zipFileName );
	}

	/**
	 * Creates a new MSCIImporter object.
	 *
	 * @param  workingDirectory  DOCUMENT ME!
	 * @param  zipFile           DOCUMENT ME!
	 */
	public MSCIImporter(String workingDirectory, File zipFile) {
		this( workingDirectory );
		unzipDone    = false;
		this.zipFile = zipFile;
	}

	/**
	 * Creates a new MSCIImporter object.
	 *
	 * @param   workingDirectory  DOCUMENT ME!
	 *
	 * @throws  IllegalArgumentException  DOCUMENT ME!
	 */
	private MSCIImporter(String workingDirectory) {
		this.workingDirectory = workingDirectory;
		if(!checkWorkingDirectory()) {
			throw new IllegalArgumentException( "The working directory parameter cannot be an existing file!" );
		}
		listeners = new ArrayList<MsciImporterEventListener>();
		unzipDone = false;
	} // end ctor MSCIImporter

	//~ Methods ----------------------------------------------------------------

	/**
	 * JavaDoc method comments
	 *
	 * @param  args  Add comments
	 */
	public static void main(String[] args) {
		try {
			ArrayList<String> desiredFunds = new ArrayList<String>();
			desiredFunds.add( "501781" );
			// desiredFunds.add( "501524" );
			MSCIImporter  importer   = new MSCIImporter( "C:\\temp", "X:\\WRKGROUP\\It_it\\Temp\\Financial Database\\MSCI\\repdel_20030930.zip" );
			XMLSerializer serializer = new XMLSerializer( new OutputStreamWriter( System.out ), new OutputFormat() );
			serializer.asDOMSerializer();
			HashMap  tracks			 = importer.getFundsTracks( desiredFunds );
			Iterator retrieveFundsIt = tracks.values().iterator();
			while(retrieveFundsIt.hasNext()) {
				MSCIImportedData data = (MSCIImportedData)retrieveFundsIt.next();
				serializer.serialize( data.getData() );
			}
		} catch(ZipException e) {
			e.printStackTrace();
		} catch(MSCIImporterException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // end method main

	/**
	 * JavaDoc method comments
	 *
	 * @param   listener  Add comments
	 *
	 * @return  Add comments
	 */
	public boolean addListener(MsciImporterEventListener listener) {
		if(listeners==null) {
			listeners = new ArrayList<MsciImporterEventListener>();
		}
		if(listeners.contains( listener )) {
			return false;
		}
		return listeners.add( listener );
	} // end method addListener

	/**
	 * JavaDoc method comments
	 *
	 * @param   listener  Add comments
	 *
	 * @return  Add comments
	 */
	public boolean removeListener(MsciImporterEventListener listener) {
		return listeners.remove( listener );
	}

	/**
	 * JavaDoc method comments
	 *
	 * @return  Add comments
	 */
	private boolean checkWorkingDirectory() {
		File working = new File( workingDirectory );
		if(!working.exists()) {
			return working.mkdirs();
		} else {
			return working.isDirectory();
		}
	} // end method checkWorkingDirectory

	/**
	 * DOCUMENT ME!
	 *
	 * @param  entry  DOCUMENT ME!
	 */
	private void extractDir(ZipEntry entry) {
		File dir = new File( workingDirectory + File.separator + entry.getName() );
		dir.mkdirs();
	}

	/**
	 * DOCUMENT ME!
	 *
	 * @return  DOCUMENT ME!
	 *
	 * @throws  IOException  DOCUMENT ME!
	 */
	private ArrayList<File> extractEverything()
								 throws IOException
	{
		ArrayList<File> xmlFiles = new ArrayList<File>();
		if((zipFile!=null) && zipFile.exists() && (workingDirectory!=null)) {
			ZipInputStream		 zis = null;
			BufferedOutputStream bos = null;
			try {
				zis = new ZipInputStream( new BufferedInputStream( new FileInputStream( zipFile.getAbsoluteFile() ) ) );
				ZipEntry entry = null;
				while((entry = zis.getNextEntry())!=null) {
					if(entry.isDirectory()) {
						// directory
						if(logger.isDebugEnabled()) {
							logger.debug( "Exploding dir:" + workingDirectory + File.separator + entry.getName() );
						}
						extractDir( entry );
						fireEvent( new EntryZipDecompressEvent( entry.getName() ) );
					} else {
						// file
						if(logger.isDebugEnabled()) {
							logger.debug( "Exploding file:" + workingDirectory + File.separator + entry.getName() );
						}
						extractFile( xmlFiles, zis, entry );
						fireEvent( new EntryZipDecompressEvent( entry.getName() ) );
					} // end if
				} // end while
				zis.close();
			} finally {
				if(bos!=null) {
					try {
						bos.close();
					} catch(IOException ioe) {
						/* ignore */
					}
				} // end if
				if(zis!=null) {
					try {
						zis.close();
					} catch(IOException ioe) {
						/* ignore */
					}
				} // end if
			} // end try-finally
		} // end if
		return xmlFiles;
	} // end method extractEverything

	/**
	 * DOCUMENT ME!
	 *
	 * @param   xmlFiles  DOCUMENT ME!
	 * @param   zis       DOCUMENT ME!
	 * @param   entry     DOCUMENT ME!
	 *
	 * @throws  IOException  DOCUMENT ME!
	 */
	private void extractFile(ArrayList<File> xmlFiles, ZipInputStream zis, ZipEntry entry)
					  throws IOException
	{
		final int			 bufferSize = 2048;
		BufferedOutputStream bos;
		bos							    = new BufferedOutputStream( new FileOutputStream( workingDirectory + File.separator + entry.getName() ), bufferSize );
		int    count;
		byte[] data					    = new byte[bufferSize];
		while((count = zis.read( data, 0, bufferSize ))!=-1) {
			bos.write( data, 0, count );
		}
		bos.flush();
		if(entry.getName().endsWith( ".xml" )) {
			xmlFiles.add( new File( workingDirectory + File.separator + entry.getName() ) );
		}
	} // end method extractFile

	/**
	 * JavaDoc method comments
	 *
	 * @param  event  Add comments
	 */
	private void fireEvent(MsciImporterEvent event) {
		if(listeners!=null) {
			for(Iterator<MsciImporterEventListener> iter = listeners.iterator(); iter.hasNext();) {
				iter.next().onMsciImporterEvent( event );
			}
		}
	} // end method fireEvent

	/**
	 * Unzip a ZIP/JAR file.
	 *
	 * @throws  MSCIImporterException
	 */
	private void unzipFile()
					throws MSCIImporterException
	{
		if(logger.isDebugEnabled()) {
			logger.debug( "Unzipping file: " + zipFile.getAbsolutePath() );
		}
		try {
			fireEvent( new StartZipDecompressEvent( new ZipFile( zipFile ).size() ) );
			this.msciFiles = extractEverything().toArray( new File[0] );
			unzipDone	   = true;
			fireEvent( new EndZipDecompressEvent() );
		} catch(IOException e) {
			throw new MSCIImporterException( new ZipException( "The MSCI zip file could not be decompress" ) );
		}
	} // end method unzipFile

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciFundIds  DOCUMENT ME!
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getFundsInformation(ArrayList<String> msciFundIds)
								throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(fundNb==-1) {
			getFundNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciInformationEvent( fundNb ) );
					MSCIFundInfoSaxHandler handler = new MSCIFundInfoSaxHandler( msciFundIds, listeners );
					SAXParserFactory	   factory = SAXParserFactory.newInstance();
					SAXParser			   parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getFunds() );
					fireEvent( new EndAnalysisMsciInformationEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getFundsInformation

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciFundIds  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getFundsInformation(String[] msciFundIds)
								throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(fundNb==-1) {
			getFundNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciInformationEvent( fundNb ) );
					MSCIFundInfoSaxHandler handler = new MSCIFundInfoSaxHandler( msciFundIds, listeners );
					SAXParserFactory	   factory = SAXParserFactory.newInstance();
					SAXParser			   parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getFunds() );
					fireEvent( new EndAnalysisMsciInformationEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getFundsInformation

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciFundId  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getFundsInformation(String msciFundId)
								throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(fundNb==-1) {
			getFundNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciInformationEvent( fundNb ) );
					MSCIFundInfoSaxHandler handler = new MSCIFundInfoSaxHandler( msciFundId, listeners );
					SAXParserFactory	   factory = SAXParserFactory.newInstance();
					SAXParser			   parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getFunds() );
					fireEvent( new EndAnalysisMsciInformationEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getFundsInformation

	/**
	 * JavaDoc method comments
	 *
	 * @param   msciFundIds  DOCUMENT ME!
	 *
	 * @return  Add comments
	 *
	 * @throws  MSCIImporterException  DOCUMENT ME!
	 */
	public HashMap<String, MSCIImportedData> getFundsTracks(ArrayList<String> msciFundIds)
						   throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(fundNb==-1) {
			getFundNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciTracksEvent( fundNb ) );
					MSCIFundTrackSaxHandler handler = new MSCIFundTrackSaxHandler( msciFundIds, listeners );
					SAXParserFactory	    factory = SAXParserFactory.newInstance();
					SAXParser			    parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getFunds() );
					fireEvent( new EndAnalysisMsciTracksEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getFundsTracks

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciFundIds  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getFundsTracks(String[] msciFundIds)
						   throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(fundNb==-1) {
			getFundNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciTracksEvent( fundNb ) );
					MSCIFundTrackSaxHandler handler = new MSCIFundTrackSaxHandler( msciFundIds, listeners );
					SAXParserFactory	    factory = SAXParserFactory.newInstance();
					SAXParser			    parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getFunds() );
					fireEvent( new EndAnalysisMsciTracksEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getFundsTracks

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciFundId  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getFundsTracks(String msciFundId)
						   throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(fundNb==-1) {
			getFundNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciTracksEvent( fundNb ) );
					MSCIFundTrackSaxHandler handler = new MSCIFundTrackSaxHandler( msciFundId, listeners );
					SAXParserFactory	    factory = SAXParserFactory.newInstance();
					SAXParser			    parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getFunds() );
					fireEvent( new EndAnalysisMsciTracksEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getFundsTracks

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciIndexIds  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getIndexesTracks(ArrayList<String> msciIndexIds)
							 throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(indexNb==-1) {
			getIndexNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Index History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciTracksEvent( fundNb ) );
					MSCIIndexTrackSaxHandler handler = new MSCIIndexTrackSaxHandler( msciIndexIds, listeners );
					SAXParserFactory		 factory = SAXParserFactory.newInstance();
					SAXParser				 parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getIndexes() );
					fireEvent( new EndAnalysisMsciTracksEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getIndexesTracks

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciIndexIds  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getIndexesTracks(String[] msciIndexIds)
							 throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(indexNb==-1) {
			getIndexNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Index History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciTracksEvent( fundNb ) );
					MSCIIndexTrackSaxHandler handler = new MSCIIndexTrackSaxHandler( msciIndexIds, listeners );
					SAXParserFactory		 factory = SAXParserFactory.newInstance();
					SAXParser				 parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getIndexes() );
					fireEvent( new EndAnalysisMsciTracksEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getIndexesTracks

	/**
	 * JavaDoc method comments
	 *
	 * @param      msciIndexId  Add comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	public HashMap<String, MSCIImportedData> getIndexesTracks(String msciIndexId)
							 throws MSCIImporterException
	{
		if(!unzipDone) {
			unzipFile();
		}
		if(indexNb==-1) {
			getIndexNb();
		}
		HashMap<String, MSCIImportedData> results = new HashMap<String, MSCIImportedData>();
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Index History" )>0) {
				try {
					if(logger.isDebugEnabled()) {
						logger.debug( "Analyzing file: " + file.getName() );
					}
					fireEvent( new StartAnalysisMsciTracksEvent( fundNb ) );
					MSCIIndexTrackSaxHandler handler = new MSCIIndexTrackSaxHandler( msciIndexId, listeners );
					SAXParserFactory		 factory = SAXParserFactory.newInstance();
					SAXParser				 parser  = factory.newSAXParser();
					parser.parse( file, handler );
					results.putAll( handler.getIndexes() );
					fireEvent( new EndAnalysisMsciTracksEvent() );
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return results;
	} // end method getIndexesTracks

	/**
	 * JavaDoc method comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	private int getFundNb()
				   throws MSCIImporterException
	{
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Fund History" )>0) {
				try {
					MSCIFundCounterSaxHandler counter = new MSCIFundCounterSaxHandler();
					SAXParserFactory		  factory = SAXParserFactory.newInstance();
					SAXParser				  parser  = factory.newSAXParser();
					parser.parse( file, counter );
					fundNb = counter.getFundNb();
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return fundNb;
	} // end method getFundNb

	/**
	 * JavaDoc method comments
	 *
	 * @return     Add comments
	 *
	 * @exception  MSCIImporterException  Add comments
	 */
	private int getIndexNb()
					throws MSCIImporterException
	{
		for(int i = 0; i<msciFiles.length; i++) {
			File file = msciFiles[i];
			if(file.getName().indexOf( "Total Index History" )>0) {
				try {
					MSCIIndexCounterSaxHandler counter = new MSCIIndexCounterSaxHandler();
					SAXParserFactory		   factory = SAXParserFactory.newInstance();
					SAXParser				   parser  = factory.newSAXParser();
					parser.parse( file, counter );
					indexNb = counter.getIndexNb();
				} catch(FactoryConfigurationError e) {
					throw new MSCIImporterException( e );
				} catch(ParserConfigurationException e) {
					throw new MSCIImporterException( e );
				} catch(SAXException e) {
					throw new MSCIImporterException( e );
				} catch(IOException e) {
					throw new MSCIImporterException( e );
				}
			} // end if
		} // end for
		return indexNb;
	} // end method getIndexNb
} // end class MSCIImporter
