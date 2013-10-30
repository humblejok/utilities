/**
 * Title           : $Workfile: PimMailer.java $
 * Copyright       : EIM (c) 2004
 * Updates         : $Date: 10.11.04 17:46 $
 * By              : $Author: Als $
 * Version number  : $Revision: 2 $
 *
 * $History: PimMailer.java $
 * 
 * *****************  Version 2  *****************
 * User: Als          Date: 10.11.04   Time: 17:46
 * Updated in $/Current/Projects/utilities/src/com/eim/util/pim
 */
package com.eim.util.pim;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * 
 */
public class PimMailer {

	//~ Instance fields --------------------------------------------------------

	protected String from;
	protected String host;
	protected String to;

	//~ Constructors -----------------------------------------------------------

	/**
	 * Creates a new PimMailer object.
	 *
	 * @param  host  DOCUMENT ME!
	 * @param  from  DOCUMENT ME!
	 * @param  to    DOCUMENT ME!
	 */
	public PimMailer(String host, String from, String to) {
		super();
		this.host = host;
		this.from = from;
		this.to   = to;
	}

	//~ Methods ----------------------------------------------------------------

	/**
	 * @param  calendar
	 * @param  subject
	 */
	public void send(VCalendar calendar, String subject) {
		try {
			// Get system properties
			Properties props = new Properties();
			// Setup mail server
			props.put( "mail.smtp.host", host );
			// Get session
			Session session = Session.getInstance( props, null );
			// Define message
			javax.mail.Message mailMessage = new MimeMessage( session );
			mailMessage.setFrom( new InternetAddress( from ) );
			mailMessage.addRecipient( javax.mail.Message.RecipientType.TO, new InternetAddress( to ) );
			mailMessage.setSubject( subject );
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();
			// Fill the message
			messageBodyPart.setText( "" );
			// Create a Multipart
			Multipart multipart = new MimeMultipart();
			// Add part one
			multipart.addBodyPart( messageBodyPart );
			//
			// Part two is attachment
			//
			// Create second body part
			messageBodyPart = new MimeBodyPart();
			// Get the attachment
			// we must first build the attachment file
			File	    attachement = File.createTempFile( "Fund_visit" + System.currentTimeMillis(), "vcs" );
			PrintWriter pw		    = new PrintWriter( new FileOutputStream( attachement ) );
			pw.print( calendar.toString() );
			pw.close();
			DataSource source = new FileDataSource( attachement );
			// Set the data handler to the attachment
			messageBodyPart.setDataHandler( new DataHandler( source ) );
			// Set the filename
			messageBodyPart.setFileName( "Fund_visit.vcs" );
			// Add part two
			multipart.addBodyPart( messageBodyPart );
			// Put parts in message
			mailMessage.setContent( multipart );
			// Send the message
			Transport.send( mailMessage );
			attachement.delete();
		} catch(AddressException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(MessagingException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // end method send
} // end class PimMailer