package comunicacion.configuracion;

import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class ConfiguracionXML implements ConfiguracionComunicacion {

    @Getter
    private Integer puerto;

    public ConfiguracionXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("totemConfig.xml"));
            Element rootElement = document.getDocumentElement();
            Node node = rootElement.getElementsByTagName("puerto").item(0); //SE PUEDE MODIFICAR CON LO QUE NECESITEMOS METER EN EL CONFIG.XML
            StringWriter buf = new StringWriter();
            Transformer xform = TransformerFactory.newInstance().newTransformer();
            xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // optional
            xform.setOutputProperty(OutputKeys.INDENT, "yes"); // optional
            xform.transform(new DOMSource(node), new StreamResult(buf));
            this.puerto = Integer.parseInt(buf.toString().replaceAll("[<>a-zA-Z/ñ\n]", "")); //SE PUEDE MODIFICAR DEPENDIENDO DEL VALUE
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer getPuerto() {
        return this.puerto;
    }
}
