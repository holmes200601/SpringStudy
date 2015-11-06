package sampson.file;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlFileParser {
    private String xmlFileName = null;
    private Document parsedDom = null;
    
    public XmlFileParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    
    public static String getElementValueById(Long id) {
        String result = null;
        
        
        return result;
    }
}
