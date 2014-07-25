package com.test.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class StaXParserTest  implements IConstant {
  
 
  String fr = null ; 
  String fileError = null ;
  private static SessionFactory factory; 
  //static Logger customLogger = Logger.getLogger("myCustomLogName");
  static Logger logger = Logger.getLogger(StaXParserTest.class);
  


  public void readConfig(String configFile) {
   logger.info("in readConfig");
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = new FileInputStream("G:\\Downloads Chrome\\Code\\Bijli\\multi xmls\\"+configFile);
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			 
			
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					
					if (startElement.getName().getLocalPart().equals(D1)) {
						logger.info(startElement.getName().getLocalPart());
						processD1(eventReader);
						if (fileError.contains(",1,") || fileError.contains(",2,") || fileError.contains(",3,") || fileError.contains(",4,") || fileError.contains(",5,"))
							break;
					}
					
					if (startElement.getName().getLocalPart() == (D2)) {
						processD2(eventReader);
					}
					
				/*	if (startElement.getName().getLocalPart() == (D3)) {
						logger.info("eeeeeeeeeeehaaawwwwww");
						processD3(eventReader);
					}
					
					if (startElement.getName().getLocalPart() == (D4)) {
						logger.info("eeeeeeeeeeehaaawwwwww");
						processD4(eventReader);
					}
					
					if (startElement.getName().getLocalPart() == (D5)) {
						logger.info("eeeeeeeeeeehaaawwwwww");
						processD5(eventReader);
					}
					
					if (startElement.getName().getLocalPart() == (D6)) {
						logger.info("eeeeeeeeeeehaaawwwwww");
						processD6(eventReader);
					}*/
				}
			}
		} /*catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (XMLStreamException e) {
      e.printStackTrace();
    }*/
		catch(Exception e){
			fileError = ",20,";
			logger.error(e);
		}
  }
  
 /* public static void main(String args[]) throws IOException{
	  StaXParserTest read = new StaXParserTest();
	  read.readConfig("260414013508S217146HPS47756    26042014001353101.xml");
	  PropertyConfigurator.configure("log4j.properties");
	  //create log file, where messages will be sent, 
	//you can also use console appender
	//FileAppender fileAppender = new FileAppender(new PatternLayout(),"some.log");

	//sometimes you can call this if you reuse this logger 
	//to avoid useless traces
	//customLogger.removeAllAppenders();

	//tell to logger where to write
	//customLogger.addAppender(fileAppender);

	// send message (of type :: info, you can use also error, warn, etc)
	logger.info("Hello! message from custom logger");

  }*/
  
  private String getValueOfEvent(XMLEvent event){
	  
	  String value = null;
	  if(event.isStartElement()){
		  value = event.asStartElement().getName().getLocalPart();
	  }
	  else if(event.isCharacters()){
		  value = event.asCharacters().getData();
	  }else if(event.isEndElement()){
		  value = event.asEndElement().getName().getLocalPart();
	  }
	  return value;
  }
  
	@SuppressWarnings("deprecation")
	private void processD1(XMLEventReader eventReader) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("in process D1");
		
		
		try{
			 factory = new AnnotationConfiguration().
	                   configure().
	                  // addPackage("com.test.parser"). //add package if used.
	                   addAnnotatedClass(BeanD1.class).
	                   buildSessionFactory();
			 Session session = factory.openSession();
		      Transaction tx = null;
		     
			XMLEvent event = eventReader.nextEvent();
		    String strg =null;
		    String strMDUpdate = "Update TBLMETERDETAIL SET METERNO=METERNO" ;
			boolean flag = true;
		
			tx = session.beginTransaction();
			BeanD1 csd  = new BeanD1();
			while (flag) {
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equals(D1)) {
						flag = false;

					}
				}
			if(flag && event.isStartElement()){
				//Date date = new Date(0);
			   // SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
			    //sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
			    
			    
				String getValue =event.asStartElement().getName().getLocalPart();
				 switch(getValue){
				 case "G1" :
					 csd.setMeterNo(getValueOfEvent(event));
					 String CurrentMeterNo = getValueOfEvent(event) ; 
					 break;
				 case "G2" :
					 try{
					 //String  strDate = sdf.format(getValueOfEvent(event));
					 //csd.setMeterTime(strDate);
					 }
					 catch(Exception e){
						 //csd.setInvalidMeterTimeFormat(true);
						 logger.error(""+e);
					 }
					 break;
				 case "G3" :
					 try{
						 //String  strDate = sdf.format(getValueOfEvent(event));
						 //csd.setModemTime(strDate);
						 }
						 catch(Exception e){
							 //csd.setInvalidMeterTimeFormat(true);
							 logger.error(""+e);
						 }
					 break;
				 case "G7" :
					 strg = getValueOfEvent(event).trim();
					 csd.setPtprimarytosec(strg);
					 break;
				 case "G8" :
					 strg = getValueOfEvent(event).trim();
					 csd.setCtprimarytosec(strg);
					 break;
				 case "G9" :
					 strg = getValueOfEvent(event).trim();
					 csd.setProgptprimary(strg);
					 break;
				 case "G10" :
					 strg = getValueOfEvent(event).trim();
					 csd.setProgctsecondary(strg);
					 break;
				 case "G11" :
					 strg = getValueOfEvent(event).trim();
					 csd.setProgptsecondary(strg);
					 break;
				 case "G12" :
					 strg = getValueOfEvent(event).trim();
					 csd.setProgctsecondary(strg);
					 break;
				 case "G13" :
					 strg = getValueOfEvent(event).trim();
					 csd.setMeterclass(strg);
					 break;
				 case "G14" :
					 strg = getValueOfEvent(event).trim();
					 csd.setMeterrating(strg);
					 break;
				 case "G15" :
					 strg = getValueOfEvent(event).trim();
					 csd.setMetertype(strg);
					 break;
				 case "G16" :
					 strg = getValueOfEvent(event).trim();
					 csd.setMeterscaling(strg);
					 break;
				 case "G17" :
					 strg = getValueOfEvent(event).trim();
					 csd.setMeterprogname(strg);
					 break;
				 case "G22" :
					 strg = getValueOfEvent(event).trim();
					 csd.setManufacturecode(strg);
					 break;
					 
					 
				 }
				 event = eventReader.nextEvent();
			
			
			}
			event = eventReader.nextEvent();
		}
			csd.setMeterNo("hjh");
			 session.save(csd); 
	         tx.commit();
			
//			if(csd.getMeterNo()== null || "".equals(csd.getMeterNo()))
//			fr = fr +1 ;
//			else 
				//Connection creation
				
		}catch (Exception e) {
		      e.printStackTrace();
	    }
	}
	
	private void processD2(XMLEventReader eventReader) {
		
		try{
			XMLEvent event = eventReader.nextEvent();
			boolean flag = true;
			while (flag) {
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equals(D2)) {
						flag = false;
					}
				}
				
				/**
				 *  Implement Logic of process D2 here
				 */
				logger.info(getValueOfEvent(event));
				event = eventReader.nextEvent();
			}
		}catch (XMLStreamException e) {
		      e.printStackTrace();
	    }
	}
	
	private void processD3(XMLEventReader eventReader) {
		
		try{
			XMLEvent event = eventReader.nextEvent();
			boolean flag = true;
			while (flag) {
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equals(D3)) {
						flag = false;
					}
				}
				
				/**
				 *  Implement Logic of process D3 here
				 */
				logger.info(getValueOfEvent(event));
				event = eventReader.nextEvent();
			}
		}catch (XMLStreamException e) {
		      e.printStackTrace();
	    }
	}

	private void processD4(XMLEventReader eventReader) {
		
		try{
			XMLEvent event = eventReader.nextEvent();
			boolean flag = true;
			while (flag) {
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equals(D4)) {
						flag = false;
					}
				}
				
				/**
				 *  Implement Logic of process D4 here
				 */
				logger.info(getValueOfEvent(event));
				event = eventReader.nextEvent();
			}
		}catch (XMLStreamException e) {
		      e.printStackTrace();
	    }
	}

	private void processD5(XMLEventReader eventReader) {
		
		try{
			XMLEvent event = eventReader.nextEvent();
			boolean flag = true;
			while (flag) {
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equals(D5)) {
						flag = false;
					}
				}
				
				/**
				 *  Implement Logic of process D5 here
				 */
				logger.info(getValueOfEvent(event));
				event = eventReader.nextEvent();
			}
		}catch (XMLStreamException e) {
		      e.printStackTrace();
	    }
	}

	private void processD6(XMLEventReader eventReader) {
		
		try{
			XMLEvent event = eventReader.nextEvent();
			boolean flag = true;
			while (flag) {
				if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equals(D6)) {
						flag = false;
					}
				}
				
				/**
				 *  Implement Logic of process D6 here
				 */
				logger.info(getValueOfEvent(event));
				event = eventReader.nextEvent();
			}
		}catch (XMLStreamException e) {
		      e.printStackTrace();
	    }
	}


  
} 
