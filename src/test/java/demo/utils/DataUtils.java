package demo.utils;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DataUtils {
    public static final String PATH_RESOURCE;
    public static final String PATH_RESOURCE_DATA;
    public static final EnvironmentVariables ENV;

    public DataUtils() {
    }

    public static String getValueConf(String config) {
        EnvironmentVariables environmentVariables = (EnvironmentVariables) Injectors.getInjector().getInstance(EnvironmentVariables.class);
        return EnvironmentSpecificConfiguration.from(environmentVariables).getProperty(config);
    }

    public static String getPropertiesValue(String key) {
        Properties props = new Properties();
        String lang = getValueConf("lang");
        InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("language" + File.separator + lang + ".properties");

        try {
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            props.load(isr);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return props.getProperty(key);
    }

    public static String getTestDataByCol(String colName) {
        String id = (String) Serenity.sessionVariableCalled("id");
        Map<String, String> rowData = (Map) Serenity.sessionVariableCalled(id);
        return (String) rowData.get(colName);
    }

    public static Map<String, String> getTestDataRow() {
        String id = (String) Serenity.sessionVariableCalled("id");
        return (Map) Serenity.sessionVariableCalled(id);
    }

    public static Map<String, String> getTestDataRowWithId(String id) {
        return (Map) Serenity.sessionVariableCalled(id);
    }

    public static List<Map<String, String>> getTestDataListWithId(String id) {
        return (List) Serenity.sessionVariableCalled(id);
    }

    public static Document getDocument(String document) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream stream = new ByteArrayInputStream(document.getBytes(StandardCharsets.UTF_8));
        Document doc = builder.parse(stream);
        return doc;
    }

    public static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        ArrayList values = new ArrayList();

        try {
            XPathExpression expr = xpath.compile(xpathExpression);
            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); ++i) {
                values.add(nodes.item(i).getNodeValue());
            }
        } catch (XPathExpressionException var8) {
            var8.printStackTrace();
        }

        return values;
    }

    public static String getTextOfXml(String xml, String xpath) {
        String text = "";
        Document doc = convertStringToXMLDocument(xml);
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xp = xpathFactory.newXPath();
        XPathExpression expr = null;

        try {
            expr = xp.compile(xpath);
            NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            text = nodes.item(0).getNodeValue();
        } catch (XPathExpressionException var8) {
            var8.printStackTrace();
        }

        System.out.println("Xpath Value: " + text);
        return text;
    }

    public static Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    static {
        PATH_RESOURCE = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources";
        PATH_RESOURCE_DATA = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "data";
        ENV = SystemEnvironmentVariables.createEnvironmentVariables();
    }
}