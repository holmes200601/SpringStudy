package sampson.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import restaurant.utils.StringUtil;

public class XmlFileParser {
    private String xmlFileName = null;
    private Document parsedDom = null;

    public XmlFileParser(String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
        this.xmlFileName = xmlFileName;
        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        parsedDom = docBuilder.parse(this.xmlFileName);
    }

    public String getElementValueById(String id) {
        if (StringUtil.isNullString(id)) {
            return "";
        }

        String result = "";
        Element element = parsedDom.getElementById(id.toString());
        result = element.getNodeValue();

        return result;
    }

    public List<String> getElementsValueByTagName(String tagName) {
        if (StringUtil.isNullString(tagName)) {
            return new ArrayList<String>();
        }

        List<String> resultList = new ArrayList<String>();
        NodeList elemList = parsedDom.getElementsByTagName(tagName);
        for (int idx = 0; idx < elemList.getLength(); ++idx) {
            resultList.add(elemList.item(idx).getTextContent());
        }

        return resultList;
    }
}
