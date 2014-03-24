package com.bn.automation.staf.core;



import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;

import com.bn.automation.staf.helpers.STAFConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class STAFLogger {
	String path = System.getProperty("user.dir");

	
	
	public void closeStafLog(String filePath){
		PrintWriter out = null;
		try {
            //String filePath = path+"/STAFLog/XML/"+fileName+".xml";
			//out = new PrintWriter(new BufferedWriter(new FileWriter(path + "/target/test.xml", true)));
            out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            //out.println("\n");
			//out.println("<Event logger=\"something\" ></Event></Events>");
            out.println("</Events>");
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	public void logEvent(String filePath, String className, String methodName, String result, String time){
        //PrintWriter out = null;
        try {
            //Logger logger = null;

            String data = "<Event logger=\"something\" class_name=\"another thing\" > </Event>";

            File file =new File(filePath);

            //if file doesnt exists, then create it
           /* if(!file.exists()){
                file.createNewFile();
            }*/

            //true = append file
            FileWriter fileWritter = new FileWriter(filePath,true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            //bufferWritter.write(data);
            bufferWritter.flush();
            bufferWritter.close();

            System.out.println("Done");

            //String filePath = path+"/STAFLog/XML/"+fileName+".xml";
            //out = new PrintWriter(new BufferedWriter(new FileWriter(path + "/target/test.xml", true)));
           /* System.out.println("path = " + filePath);
            out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
            String logMessage1 = "<vent class_name=\""+className+"\" method_name=\""+methodName+"\" result=\""+result+"\" time=\""+time+"\" >";
            String logMessage2 = "</vent>";
            out.println(logMessage1);
            out.println(logMessage2);
            System.out.println("logMessage = " + logMessage1);
            System.out.println("log message inseted");*/

                    

        } catch (IOException e) {
            System.err.println(e);
        }  /*finally {
            if (out != null) {
                out.close();
            }*/

    }
	/*public void convertXMLToHTML(){
		try {
			
	        TransformerFactory tFactory=TransformerFactory.newInstance();

	        //Source xslDoc=data StreamSource(path+"/src/test/resources/stylesheet/StafStylesheet.xsl");
	        //Source xmlDoc=data StreamSource(path+"/target/STAFlog.xml");
	        Source xslDoc=new StreamSource(path+"/src/test/resources/stylesheet/DefaultStylesheet2.xsl");
	        Source xmlDoc=new StreamSource(path+"/target/test.xml");

	      

	        String outputFileName=path+"/reports/Staflog.html";

	        OutputStream htmlFile=new FileOutputStream(outputFileName);
	        Transformer trasform=tFactory.newTransformer(xslDoc);
	        trasform.transform(xmlDoc, new StreamResult(htmlFile));
	    } 
	    catch (FileNotFoundException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (TransformerConfigurationException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (TransformerFactoryConfigurationError e) 
	    {
	        e.printStackTrace();
	    }
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	}*/
	
	public void openFile(String filePath) throws IOException{
		File file = new File(filePath);
		//Desktop.getDesktop().browse(htmlFile.toURI());
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public void logResult(String fileName, String id, String className, String methodName, String result, String time){
        Document document = null;
        Element root = null;


        try{
            File xmlFile = new File(fileName);
            if(xmlFile.exists()) {
                // try to load document from xml file if it exist
                // create a file input stream
                FileInputStream fis = new FileInputStream(xmlFile);
                // create a sax builder to parse the document
                SAXBuilder sb = new SAXBuilder();
                // parse the xml content provided by the file input stream and create a Document object
                document = sb.build(fis);
                // get the root element of the document
                root = document.getRootElement();
                fis.close();
            } else {
                // if it does not exist create a new document and new root
                document = new Document();
                root = new Element("root");
            }
            //Element root = new Element("root");
            //Document doc = new Document(root);
            Element testCase = new Element("testcase");
            testCase.setAttribute(new Attribute("id", id));
            testCase.addContent(new Element("class_name").setText(className));
            testCase.addContent(new Element("method_name").setText(methodName));
            testCase.addContent(new Element("result").setText(result));
            testCase.addContent(new Element("time").setText(time));
            root.addContent(testCase);
            document.setContent(root);
            try {
                FileWriter writer = new FileWriter(fileName);
                XMLOutputter outputter = new XMLOutputter();
                outputter.setFormat(Format.getPrettyFormat());
                outputter.output(document, writer);
                outputter.output(document, System.out);
                writer.close(); // close writer
            } catch (IOException e) {
                e.printStackTrace();
            }

           /* XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter("file.xml"));*/

        }  catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }

    }

    public void convertXMLToHTML(String filePath){
        try {
            String path = STAFRunner.getInfo().get(STAFConstant.USER_DIR).toString();

            TransformerFactory tFactory=TransformerFactory.newInstance();

            //Source xslDoc=data StreamSource(path+"/src/test/resources/stylesheet/StafStylesheet.xsl");
            //Source xmlDoc=data StreamSource(path+"/target/STAFlog.xml");
            Source xslDoc=new StreamSource(path+"/src/main/resources/log/DefaultStylesheet2.xsl");
            //Source xmlDoc=new StreamSource(path+"/target/test.xml");


            //Source xmlDoc=new StreamSource(STAFRunner.getInfo().get("xml_log_path").toString());
            Source xmlDoc = new StreamSource(filePath);
            // Source xmlDoc=new StreamSource(path+"/testlog.xml");
            File theDir = new File("STAFLog\\HTML");

            // if the directory does not exist, create it
            if (!theDir.exists()) {
                System.out.println("creating directory: " + theDir);
                boolean result = theDir.mkdir();

                if(result) {
                    System.out.println("DIR created");
                }
            }


            //String outputFileName=path+"/reports/Staflog.html";
            // String outputFileName=STAFRunner.getInfo().get(STAFConstant.USER_DIR)+"\\STAFLog\\HTML\\STAFLog_"+STAFRunner.getInfo().get(STAFConstant.START_TIME)+".html";
            String outputFileName=STAFRunner.getInfo().get("html_log1").toString();


            OutputStream htmlFile=new FileOutputStream(outputFileName);
            Transformer trasform=tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e)
        {
            e.printStackTrace();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }

    public void convertXMLToHTML2(String filePath){
        try {
            String path = STAFRunner.getInfo().get(STAFConstant.USER_DIR).toString();

            TransformerFactory tFactory=TransformerFactory.newInstance();

            //Source xslDoc=data StreamSource(path+"/src/test/resources/stylesheet/StafStylesheet.xsl");
            //Source xmlDoc=data StreamSource(path+"/target/STAFlog.xml");
            Source xslDoc=new StreamSource(path+"/src/main/resources/log/OverviewStylesheet.xsl");
            //Source xmlDoc=new StreamSource(path+"/target/test.xml");


            //Source xmlDoc=new StreamSource(STAFRunner.getInfo().get("xml_log_path").toString());
            Source xmlDoc = new StreamSource(filePath);
            // Source xmlDoc=new StreamSource(path+"/testlog.xml");
            File theDir = new File("STAFLog\\HTML");

            // if the directory does not exist, create it
            if (!theDir.exists()) {
                System.out.println("creating directory: " + theDir);
                boolean result = theDir.mkdir();

                if(result) {
                    System.out.println("DIR created");
                }
            }


            //String outputFileName=path+"/reports/Staflog.html";
            // String outputFileName=STAFRunner.getInfo().get(STAFConstant.USER_DIR)+"\\STAFLog\\HTML\\STAFLog_"+STAFRunner.getInfo().get(STAFConstant.START_TIME)+".html";
            String outputFileName=STAFRunner.getInfo().get("html_log2").toString();


            OutputStream htmlFile=new FileOutputStream(outputFileName);
            Transformer trasform=tFactory.newTransformer(xslDoc);
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e)
        {
            e.printStackTrace();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }

    public void createHTMLReport(){
        try{

            String html1 = "STAFLog_"+STAFRunner.getInfo().get(STAFConstant.START_TIME)+".html";
            String html2 = "ResultLog_"+STAFRunner.getInfo().get(STAFConstant.START_TIME)+".html";


            File f = new File("STAFLog\\HTML\\report.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("<html>\n");
            bw.write("<body>\n");
            bw.write("<h1>STAFLog</h1>\n");
            bw.write("<frameset rows=\"25%,*,75%\">\n");
            bw.write("<frame src=\""+html1+"\">\n");
            bw.write("<frame src=\""+html2+"\">\n");
            bw.write("</frameset>\n");
            /*bw.write("<frameset cols=\"25%,*,25%\">\n" +
                    "  <frame src=\""+html1+"\">\n" +
                    "  <frame src=\""+html2+"\">\n" +
                    "</frameset> ");*/
            /*String line;
            while ((line=br.readLine())!=null) {
                bw.write(line);
                bw.newLine();
            }
*/
            //bw.write("</text" + "area>");
            bw.write("</body>\n");
            bw.write("</html>\n");

            bw.close();

           // Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e){
            e.printStackTrace();

        }

    }

}
