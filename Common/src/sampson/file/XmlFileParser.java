package sampson.file;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import sampson.string.StringUtils;

public class XmlFileParser {
    private String xmlFileName = null;
    private Document parsedDom = null;
    
    public XmlFileParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            parsedDom = docBuilder.parse(xmlFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getElementValueById(String id) {
        if (StringUtils.isNullString(id)) {
            return "";
        }
        
        String result = "";
        Element element = parsedDom.getElementById(id.toString());
        result = element.getNodeValue();       
        
        return result;
    }
    
    public List<String> getElementsValueByTagName(String tagName) {
        if (StringUtils.isNullString(tagName)) {
            return new ArrayList<String>();
        }
        
        List<String> resultList = new ArrayList<String>();
        NodeList elemList = parsedDom.getElementsByTagName(tagName);
        for (int idx = 0; idx < elemList.getLength(); ++idx) {
            resultList.add(elemList.item(idx).getNodeValue());
        }        
        
        return resultList;
    }
}
