package atenciones;

import excepciones.DniRepetidoException;
import excepciones.FilaDeEsperaVaciaException;
import excepciones.SinCapacidadException;
import lombok.Getter;
import lombok.Setter;
import modelo.Atencion;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaDeEspera {

    @Getter
    @Setter
    private Integer tamañoMaximo;
    private Queue<Atencion> fila;

    public FilaDeEspera() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("config.xml"));
            Element rootElement = document.getDocumentElement();
            Node node = rootElement.getElementsByTagName("tamañoMaximo").item(0); //SE PUEDE MODIFICAR CON LO QUE NECESITEMOS METER EN EL CONFIG.XML
            StringWriter buf = new StringWriter();
            Transformer xform = TransformerFactory.newInstance().newTransformer();
            xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes"); // optional
            xform.setOutputProperty(OutputKeys.INDENT, "yes"); // optional
            xform.transform(new DOMSource(node), new StreamResult(buf));
            this.tamañoMaximo = Integer.parseInt(buf.toString().replaceAll("[<>a-zA-Z/ñ\n]", "")); //SE PUEDE MODIFICAR DEPENDIENDO DEL VALUE
        } catch (Exception e) {
            e.printStackTrace();
        }

        fila = new PriorityQueue<>(tamañoMaximo, (Atencion atencion1, Atencion atencion2) -> {
            if (atencion1.getPrioridad() > atencion2.getPrioridad())
                return 1;
            else if (atencion1.getPrioridad() < atencion2.getPrioridad())
                return -1;
            else
                return 0;
        });
    }

    public void agregarAtencion(Atencion atencion) throws SinCapacidadException, DniRepetidoException {
        if (fila.size() == tamañoMaximo)
            throw new SinCapacidadException();
        if (fila.stream().anyMatch(atencionEnEspera -> atencion.getDNI().equals(atencionEnEspera.getDNI())))
            throw new DniRepetidoException();
        fila.add(atencion);
    }

    public Atencion sacarNuevaAtencion() throws FilaDeEsperaVaciaException {
        if (fila.isEmpty()) {
            throw new FilaDeEsperaVaciaException();
        }
        return fila.poll();
    }

}
