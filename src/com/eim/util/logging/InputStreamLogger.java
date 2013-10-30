package com.eim.util.logging;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This class is a utility that allows to redirect a stream to a LOG4J Logger.
 * 
 * @author als
 *
 */
public class InputStreamLogger implements Callable<Boolean> {

	private static final Logger	internalLogger	= Logger.getLogger(InputStreamLogger.class);

	private Logger	            logger;
	private InputStream	        inputStream;
	private Level	        	level;

	public InputStreamLogger(Logger logger, InputStream inputStream, Level level) {
		super();
		this.inputStream = inputStream;
		this.logger = logger;
		this.level = level;
	}

	public Boolean call()throws Exception {
		String line;
		BufferedReader input = null;
		boolean somethingRead = false;
		try {
			input = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = input.readLine()) != null) {
				somethingRead = true;
				logger.log(level, line);
			}
			input.close();
		} catch (IOException e) {
			internalLogger.error("Problem redirecting stream to logger!", e);
			input = null;
		}
		return somethingRead;
	}

}
