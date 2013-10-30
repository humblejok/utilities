package com.eim.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtils {
	
    public static void copyFile(File in, File out) throws IOException {
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
        }
    }
    
    public static void deleteFolder(File folder) throws IOException {
    	for (File f : folder.listFiles()) {
    		if (f.isDirectory()) {
    			deleteFolder(f);
    		} else {
    			f.delete();
    		}
    	}
    	folder.delete();
    }
    
    @SuppressWarnings("unchecked")
	public static void unzipFile(File zip, File target) throws IOException {
    	ZipFile zipped = new ZipFile(zip);
    	Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>)zipped.entries();
    	int BUFFER_SIZE = 4096;

    	while (entries.hasMoreElements()) {
    		ZipEntry entry = (ZipEntry) entries.nextElement();
    		String entryName = entry.getName();
    		File targetFile = new File(target, entryName);
    		targetFile.getParentFile().mkdirs();
    		if (!entry.isDirectory()) {
    			BufferedInputStream bis = new BufferedInputStream(zipped.getInputStream(entry));
    			byte [] buffer = new byte[BUFFER_SIZE];
    			int current = 0;
    			FileOutputStream fos = new FileOutputStream(targetFile);
                BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER_SIZE);
                while ((current = bis.read(buffer, 0, BUFFER_SIZE)) != -1) {
                    bos.write(buffer, 0, current);
                }
                bos.close();
                bis.close();
    		}
    	}
    	
    }
    
    public static void zipFile(File source, File zip) throws IOException {
    	
    	FileOutputStream fos = new FileOutputStream(zip);
    	BufferedOutputStream bos = new BufferedOutputStream(fos);
    	ZipOutputStream zop = new ZipOutputStream(bos);
    	
    	addEntry(zop,source,false, source.getCanonicalPath());
    	
    	zop.close();
    	bos.close();
    	fos.close();
    }
    
    public static void addEntry(ZipOutputStream zop, File entry, boolean includeFolder, String prefix) throws IOException {
    	int BUFFER_SIZE = 4096;
    	byte [] buffer = new byte[BUFFER_SIZE];
    	FileInputStream fis = null;
    	BufferedInputStream bis = null;
    	String name = entry.getCanonicalPath().equals(prefix)?entry.getName():entry.getCanonicalPath().substring(prefix.length()+1);
    	if (entry.isFile()) {
    		fis = new FileInputStream(entry);
    		bis = new BufferedInputStream(fis);
    		ZipEntry zEntry = new ZipEntry(name);
    		zop.putNextEntry(zEntry);
    		int write = -1;
    		while ((write=bis.read(buffer,0, BUFFER_SIZE))!=-1) {
    			zop.write(buffer, 0, write);
    		}
    		zop.closeEntry();
    		bis.close();
    		fis.close();
    	} else {
    		if (includeFolder) {
	    		ZipEntry zEntry = new ZipEntry(name);
	    		zop.putNextEntry(zEntry);
    		}
    		for (File sEntry : entry.listFiles()) {
    			addEntry(zop, sEntry, false,prefix);
    		}
    		if (includeFolder) {
    			zop.closeEntry();
    		}
    	}
    }
    
    public static void main(String [] argv) {
    	try {
			zipFile(new File("c:\\temp"), new File("c:\\test.zip"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
