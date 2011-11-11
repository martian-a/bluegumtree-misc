package uk.co.bluegumtree.code.java.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

public class Organiser {


	/**
	 * Deletes a file or directory.
	 * @param currFile
	 * a File object representing the file or directory to be deleted
	 */
	public static void delete(File currFile) {
		Organiser.delete(currFile, false);
	}
	
	/**
	 * Deletes a file or directory.
	 * @param currFile
	 * a File object representing the file or directory to be deleted
	 * @param force
	 * true if the delete should be forced, false if not.
	 */
	public static void delete(File currFile, boolean force) {
		
		// Check that the File exists
	    if (!currFile.exists()) {
	    	return;
	    }	
	    
	    // Check whether the File represents a file or a directory
	    if (currFile.isDirectory()) {
	    	
	    	// Loop through all the Files in this directory
	    	File[] directoryContents = currFile.listFiles();
	    	for (int i = 0; i < directoryContents.length; i++) {
	    		Organiser.delete(directoryContents[i], force);
	    	}	    	
	    	
	    	// Now it's empty, delete it
	    	currFile.delete();
	    	
	    } else {
	    	
	    	// It's a file (not a directory)
	    	// Delete it.
	    	currFile.delete();
	    	
	    }	    
	}
	
	
	public static HashSet<File> getDirectories(File currFile) throws FileNotFoundException {	

		// Check that the directory represented by currFile exists
	    if (!currFile.exists()) {
	    	throw new FileNotFoundException();
	    }	

	    HashSet<File> files = new HashSet<File>();

	    // Check whether the File represents a file or a directory
	    if (currFile.isDirectory()) {	    	
	    	
	    	// Retrieve a list of all the files and directories
	    	// in this directory
	    	File[] directoryContents = currFile.listFiles();

			// return this list of Files
    		// without drilling down any further
    		for (int i = 0; i < directoryContents.length; i++) {    			    		
    			
    			if (directoryContents[i].isDirectory()) {    		
    				
    				files.add(directoryContents[i]);	
    			}
    		}
	    }
	    return files;
	}
	
	public static HashSet<File> getFiles(File currFile, boolean drillDown) throws FileNotFoundException {				
		
		// Check that the File exists
	    if (!currFile.exists()) {
	    	throw new FileNotFoundException();
	    }	
	    
	    HashSet<File> files = new HashSet<File>();
	    
	    // Check whether the File represents a file or a directory
	    if (currFile.isDirectory()) {	    	
	    	
	    	// Retrieve a list of all the files and directories
	    	// in this directory
	    	File[] directoryContents = currFile.listFiles();
	    	
	    	
	    	if (!drillDown) {

	    		// drillDown is false, return this list of Files
	    		// without drilling down any further
	    		for (int i = 0; i < directoryContents.length; i++) {
	    			files.add(directoryContents[i]);
	    		}

	    	} else {
	    	
		    	// drillDown is true, loop through and process
		    	// all the Files in this directory
		    	for (int i = 0; i < directoryContents.length; i++) {
		    		files.addAll(Organiser.getFiles(directoryContents[i], drillDown));
		    	}
		    	
	    	}
	    } else {
	    	
	    	// It's a file, return it
	    	files.add(currFile);
	    	
	    }	
	    
	    return files;
	}

}
