package com.test.parser;

import java.io.File;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class testThread implements Runnable{
		static Logger logger = Logger.getLogger(testThread.class);
		public static final int MAX_THREADS = 9; 
	    public static int threads_counter = 0; 
	    private final int REPEATS = 1; 
        private final int DELAY = 1095700; 
        String fileName = null ;
        testThread(String fileName){
        	logger.info("in test Thread");
        	this.fileName = fileName ;
        	PropertyConfigurator.configure("log4j.properties");
        }
	 public void run()
	 {
		 logger.info("testThread.threads_counter::->"+testThread.threads_counter);
         if(testThread.threads_counter >= testThread.MAX_THREADS) { 
               try { 
                     Thread.sleep(this.DELAY); 
               } catch(Exception e) { 
            	   testThread.addResponse("Thread error"); 
            	   logger.error(""+e);
               } 
               this.run(); 
               return; 
         } 

         testThread.threads_counter++; 
         try { 
               for(int i = 0; i < this.REPEATS; ++i) { 
             	  StaXParserTest read = new StaXParserTest();
             	  read.readConfig(this.fileName);
               } 
         } catch(Exception e) { 
        	 testThread.addResponse("And error occured for Thread "); 
        	 logger.error(""+e);
         } finally { 
        	 testThread.addResponse("Thread  stopping"); 
        	 testThread.threads_counter--; 
         } 
   

	 }  
	 public static void addResponse(String response) { 
         logger.info(response); 
   } 
	}

public class MainClass  {

	static Logger logger = Logger.getLogger(MainClass.class);
    private static int totalNumberOfFiles = 0;
    private static int nextFile = 0;
    private static ArrayList<String> allFilesArrayList = new ArrayList<String>();

    public static void getAllFilesInArrayList(final File fileOrFolder) {
        String temp = "";
        logger.info("getAllFilesInArrayList fileOrFolder.getAbsolutePath()" + fileOrFolder.getAbsolutePath());
        if (fileOrFolder.isDirectory()) {
            for (final File fileEntry : fileOrFolder.listFiles()) {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getAbsolutePath();
                    allFilesArrayList.add(temp);
                }
            }
        }
        if (fileOrFolder.isFile()) {
            temp = fileOrFolder.getAbsolutePath();
            allFilesArrayList.add(temp);
        }
        totalNumberOfFiles = allFilesArrayList.size();
        for (int i = 0; i < allFilesArrayList.size(); i++) {
             logger.info("getAllFilesInArrayList path: " + allFilesArrayList.get(i));
        }
    }

    public synchronized static String getNextFile() {
        logger.info("inside getNextFile");
        
        if (nextFile < allFilesArrayList.size()) {
            return allFilesArrayList.get(nextFile);
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
    	PropertyConfigurator.configure("log4j.properties");
       File file = new File("G:\\Downloads Chrome\\Code\\Bijli\\multi xmls");
        getAllFilesInArrayList(file);
        
         logger.info("totalNumberOfFiles--> "+totalNumberOfFiles);
         logger.info("allFilesArrayList size--> "+allFilesArrayList.size());
        
        while(nextFile < totalNumberOfFiles)
        {
              logger.info("nextFile--> "+nextFile);
              String tempGetFile = getNextFile();
              File tempFile = new File(allFilesArrayList.get(nextFile));
              logger.info("filename--> "+tempFile.getName());
              testThread r = new testThread(tempFile.getName());
              Thread t1 = new Thread(r);
              t1.start();  /* Question is: can we call instead t1.run() */  
               logger.info("I am the main thread , i created thread t1 and had it execute run method, which is currently looping for i to 1000000");
              nextFile++;
            
        }
    }
}
