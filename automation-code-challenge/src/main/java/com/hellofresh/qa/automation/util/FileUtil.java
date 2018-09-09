package com.hellofresh.qa.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to read and parse the CSV data file . 
 * 
 * 
 * 
 * @author bharath
 *
 */
public class FileUtil {
	private static final Logger LOGGER  = LoggerFactory.getLogger(FileUtil.class);
	private static final String FILE_NOT_FOUND_MESSAGE = "The file %s could not be located ";
	private static final String IO_ERROR_MESSAGE = "An I/O error occured while reading the file %s";
	private static final String FILE_EMPTY_MESSAGE = "The file %s is empty ";
	private static final int FILE_HEADER_INDEX = 0;
	private static final String CSV_DELIMITER = ",";
	
	
	private static InputStream getFileAsStream(final String absFilePath){
		LOGGER.debug("Attempting to read the file {}", absFilePath);
		final InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(absFilePath);
		if(is == null){
			throw new RuntimeException(String.format(FILE_NOT_FOUND_MESSAGE, absFilePath));
		}
		return is;
	}
	
	private static List<String> readFileAsList(final String absFilePath){
		final List<String> fileData = new ArrayList<String>();
		final InputStream is = getFileAsStream(absFilePath);
		String data = null;
		final BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		try {
			while((data = br.readLine()) != null){
				fileData.add(data);
			}
		} catch (IOException e) {
			throw new RuntimeException(String.format(IO_ERROR_MESSAGE, absFilePath));
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// We dont care about this 
				}
			}
		}
		// Remove the header
		fileData.remove(FILE_HEADER_INDEX);
		if(fileData.isEmpty()){
			throw new RuntimeException(String.format(FILE_EMPTY_MESSAGE, absFilePath));
		}
		return fileData;
	}
	
	/**
	 * Read the file and get the file data As string 
	 * 
	 * @param absFilePath
	 * 				The file path 
	 * @return
	 * 			String containing the file data
	 */
	public static String getFileDataAsString(final String absFilePath){
		final InputStream is = getFileAsStream(absFilePath);
		final BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String data = null;
		try {
			while((data = br.readLine()) != null){
				sb.append(data);
			}
		} catch (IOException e) {
			throw new RuntimeException(String.format(IO_ERROR_MESSAGE, absFilePath));
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// We dont care about this 
				}
			}
		}
		return sb.toString();
		
	}
	
	
	/**
	 * Fetch the data from the CSV file and return it in a TestNG friendly
	 * format. THE FIRST LINE OF THE FILE IS IGNORED AS HEADER
	 * 
	 * 
	 * @param absFilePath
	 * 			The CSV file path 
	 * @return
	 * 		A list iterator containing the file data 
	 */
	public static Iterator<Object[]> fetchCSVFileData(final String absFilePath){
		LOGGER.debug("Fetching the data from the CSV file {}", absFilePath);
		final List<Object[]> data = new ArrayList<Object[]>();
		final List<String> fileDataString = readFileAsList(absFilePath);
		for(String line: fileDataString){
			String[] lineArray = line.split(CSV_DELIMITER);
			data.add(lineArray);
		}
		LOGGER.debug("There are {} lines in the data file ", data.size());
		return data.iterator();
	}
	
	

}
 