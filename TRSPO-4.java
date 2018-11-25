import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {
        String jsonSource = "{\"Name\": \"Steven\", \n\"Age\": 15, \n\"Country\": \"Sweden\"}";
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonSource);
        
        System.out.println("Name: " + jsonObject.get("Name") + "\n" +
                           "Age: " + jsonObject.get("Age") + "\n" +
                           "Country of living: " + jsonObject.get("Country"));

        String xml = "<note><to>Steven</to><from>Mom</from><title>Note</title><message>Do your room this weekend!</message></note>";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        InputSource input = new InputSource(new StringReader(xml));
        Document xmlDocument = dBuilder.parse(input);

        String to = xmlDocument.getElementsByTagName("to").item(0).getTextContent();
        String from = xmlDocument.getElementsByTagName("from").item(0).getTextContent();
        String title = xmlDocument.getElementsByTagName("title").item(0).getTextContent();
        String message = xmlDocument.getElementsByTagName("message").item(0).getTextContent();
        
        System.out.println("\n\n\t Note.
                            \nTitle: " + title + 
                           "\n From: " + from + 
                           "\n To: " + to + 
                           "\nMessage: " + message);
    }
}
