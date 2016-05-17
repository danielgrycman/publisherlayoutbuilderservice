package io.github.danielgrycman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by danielgrycman
 */
@RestController
@RequestMapping("/helloworld")
public class HelloWorldLayoutController {

    @Autowired
    private ResourceLoader resourceLoader;

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    DocumentBuilder builder = null;

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    StringWriter stringWriter = new StringWriter();

    public HelloWorldLayoutController() throws TransformerConfigurationException {

    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public String createLayout() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        factory.setNamespaceAware(true);
        factory.setXIncludeAware(true);
        factory.setIgnoringComments(true);

        builder = factory.newDocumentBuilder();
        builder.setEntityResolver(new LayoutResolver());

        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.transform(new DOMSource(builder.parse(new InputSource((resourceLoader.getResource("classpath:xml_layouts/helloworldlayout.xml")).getInputStream()))), new StreamResult(stringWriter));
        return stringWriter.getBuffer().toString();
    }

}
