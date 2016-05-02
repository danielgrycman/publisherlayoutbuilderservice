package io.github.danielgrycman;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by danielgrycman
 */
public class LayoutResolver implements EntityResolver {
    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        if (systemId != null) {
            URI systemUri = null;
            try {
                systemUri = new URI(systemId);
                if (systemUri.isAbsolute()) {
                    URI baseUri = new URI(systemId);
                    systemUri = baseUri.resolve(systemUri);
                    String sysIdString = systemUri.toString();
                    String[] split = null;
                    split = sysIdString.split("service/");
                    systemId = "xml_layouts/" + split[1];
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        final InputSource inputSource = new InputSource();
        inputSource.setSystemId(systemId);
        inputSource.setByteStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(systemId));
        return inputSource;
    }
}
