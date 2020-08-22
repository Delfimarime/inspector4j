package org.inspector4j.api.configuration;

import org.inspector4j.api.Inspect4JException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.URL;

public class XmlConfigurationProvider implements ConfigurationProvider {

    @Override
    public Configuration toProperties() {
        try {
            URL url = XmlConfigurationProvider.class.getClassLoader().getResource("inspector4j.xml");
            InputStream io = url == null ? null : url.openStream();
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Configuration) jaxbUnmarshaller.unmarshal(io);
        }catch (Inspect4JException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Inspect4JException("Couldn't initialize due to an error", ex);
        }


    }


}
