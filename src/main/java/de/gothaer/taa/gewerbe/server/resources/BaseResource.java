package de.gothaer.taa.gewerbe.server.resources;

import de.gothaer.taa.gewerbe.server.Utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.net.URL;

public abstract class BaseResource<C> {

  @SuppressWarnings("unchecked")
  protected C getDefinition(Class<?> clazz,
                            String path) {

    //    String content = Utils.getXmlString(path);

    try {

      URL url = Utils.class.getResource(path);

      XMLInputFactory xif = XMLInputFactory.newFactory();
      StreamSource xml = new StreamSource(url.getPath());
      XMLStreamReader xsr = xif.createXMLStreamReader(xml);
      xsr.nextTag();
      while (!xsr.getLocalName()
                 .equals("Body")) {
        xsr.nextTag();
      }
      xsr.nextTag();

      JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
      Unmarshaller unmarschaller = jaxbContext.createUnmarshaller();
      JAXBElement<C> jaxbElement = (JAXBElement<C>) unmarschaller.unmarshal(xsr);

      return jaxbElement.getValue();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
