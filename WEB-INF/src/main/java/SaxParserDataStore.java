
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
    
    SmartDoorbell smartDoorbell;
	SmartDoorLock smartDoorLock;
    Speaker speaker;
	SmartLighting smartLighting;
    SmartThermostat smartThermostat;
	Accessory accessory;
	HashMap<String,Object> products;
    
    static HashMap<String,SmartDoorbell> smartDoorbells;
	static HashMap<String,SmartDoorLock> smartDoorLocks;
	static HashMap<String,Speaker> speakers;
	static HashMap<String,SmartLighting> smartLightings;
    static HashMap<String,SmartThermostat> smartThermostats;
   	static HashMap<String,Accessory> accessories;

	String tvXmlFileName;
	int totalSize;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String tvXmlFileName) {
    this.tvXmlFileName = tvXmlFileName;
    
	smartDoorbells=new HashMap<String, SmartDoorbell>();
	smartDoorLocks= new HashMap<String, SmartDoorLock>();
	speakers= new HashMap<String, Speaker>();
	smartLightings=new  HashMap<String, SmartLighting>();
	smartThermostats=new  HashMap<String, SmartThermostat>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();
	products = new HashMap<String, Object>();
	parseDocument();


	products.put("smartDoorbells",smartDoorbells);
	products.put("smartDoorLocks",smartDoorLocks);
	products.put("speakers",speakers);
	products.put("smartLightings",smartLightings);
	products.put("smartThermostats",smartThermostats);
	products.put("accessories",accessories);

	totalSize=smartDoorbells.size()+speakers.size()+smartThermostats.size()+smartLightings.size() +smartDoorLocks.size()+accessories.size();
    
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(tvXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

	public HashMap<String,Object> getProducts()
	{
		return products;
	}
	
	public int getProductsSize()
	{
		return totalSize;
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for tv,nanoleafLightPanels etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        

        if (elementName.equalsIgnoreCase("smartDoorbell"))
		{
			currentElement="smartDoorbell";
			smartDoorbell = new SmartDoorbell();
            smartDoorbell.setId(attributes.getValue("id"));
        }
		if (elementName.equalsIgnoreCase("smartDoorLock"))
		{
			currentElement="smartDoorLock";
			smartDoorLock = new SmartDoorLock();
            smartDoorLock.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("speaker"))
		{
			currentElement="speaker";
			speaker = new Speaker();
            speaker.setId(attributes.getValue("id"));
        }
		if (elementName.equalsIgnoreCase("smartLighting")) 
		{
			currentElement="smartLighting";
			smartLighting = new SmartLighting();
            smartLighting.setId(attributes.getValue("id"));
		}
	 	if (elementName.equalsIgnoreCase("smartThermostat")) 
		{
			currentElement="smartThermostat";
			smartThermostat = new SmartThermostat();
            smartThermostat.setId(attributes.getValue("id"));
		}
        
        
        if (elementName.equals("accessory") &&  !currentElement.equals("smartDoorbell"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for tv,smartLightings etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        
 
        if (element.equals("smartDoorbell")) {	
			smartDoorbells.put(smartDoorbell.getId(),smartDoorbell);
			return;
        }
		if (element.equals("smartDoorLock")) {	
			smartDoorLocks.put(smartDoorLock.getId(),smartDoorLock);
			return;
        }
        if (element.equals("speaker")) {	
			speakers.put(speaker.getId(),speaker);
			return;
        }
		if (element.equals("smartLighting")) {
			smartLightings.put(smartLighting.getId(),smartLighting);
			return;
        }
        if (element.equals("smartThermostat")) {	  
			smartThermostats.put(smartThermostat.getId(),smartThermostat);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("accessory")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("smartDoorbell")) {
			smartDoorbell.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}
        if (element.equalsIgnoreCase("image")) {
		    
            if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setImage(elementValueRead);
			if(currentElement.equals("smartDoorLock"))
			    smartDoorLock.setImage(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setImage(elementValueRead);
			if(currentElement.equals("smartLighting"))
				smartLighting.setImage(elementValueRead);
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setImage(elementValueRead);
			
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
			
            if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartDoorLock"))
			    smartDoorLock.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("speaker"))
				speaker.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartLighting"))
				smartLighting.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setDiscount(Double.parseDouble(elementValueRead));
            
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
			
        	if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setCondition(elementValueRead);
			if(currentElement.equals("smartDoorLock"))
				smartDoorLock.setCondition(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setCondition(elementValueRead);
			if(currentElement.equals("smartLighting"))
				smartLighting.setCondition(elementValueRead);
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setCondition(elementValueRead);
            
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            
            if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setRetailer(elementValueRead);
			if(currentElement.equals("smartDoorLock"))
			    smartDoorLock.setRetailer(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setRetailer(elementValueRead);
			if(currentElement.equals("smartLighting"))
				smartLighting.setRetailer(elementValueRead);
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setRetailer(elementValueRead);
			
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            
        	if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setName(elementValueRead);
			if(currentElement.equals("smartDoorLock"))
			 	smartDoorLock.setName(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setName(elementValueRead);
			if(currentElement.equals("smartLighting"))
				smartLighting.setName(elementValueRead);
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setName(elementValueRead);
			
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			
        	if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartDoorLock"))
			 	smartDoorLock.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("speaker"))
				speaker.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartLighting"))
				smartLighting.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setPrice(Double.parseDouble(elementValueRead));
			
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
		}

		if (element.equalsIgnoreCase("description")) {
            
        	if(currentElement.equals("smartDoorbell"))
				smartDoorbell.setDescription(elementValueRead);
			if(currentElement.equals("smartDoorLock"))
			 	smartDoorLock.setDescription(elementValueRead);
			if(currentElement.equals("speaker"))
				speaker.setDescription(elementValueRead);
			if(currentElement.equals("smartLighting"))
				smartLighting.setDescription(elementValueRead);
			if(currentElement.equals("smartThermostat"))
				smartThermostat.setDescription(elementValueRead);
			
            if(currentElement.equals("accessory"))
				accessory.setDescription(elementValueRead);          
			return;
	    }
		
		

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\Assignment1SGC\\ProductCatalog.xml");
    } 
}
