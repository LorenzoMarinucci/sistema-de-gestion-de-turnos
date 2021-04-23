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
import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.InputStream;
import java.util.PriorityQueue;
import java.util.Queue;

public class FilaDeEspera {

    @Getter
    @Setter
    private Integer tamañoMaximo;

    private Queue<Atencion> fila;

    public FilaDeEspera() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse("config.xml");
            XPath xPath = XPathFactory.newInstance().newXPath();
            Node node = (Node) xPath.evaluate("//filaDeEspera/tamañoMaximo", dDoc, XPathConstants.NODE);
            System.out.println(node);
            this.tamañoMaximo = Integer.parseInt(node.getNodeValue());
            System.out.println(this.tamañoMaximo);
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
