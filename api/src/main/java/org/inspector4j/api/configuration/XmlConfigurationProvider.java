package org.inspector4j.api.configuration;

import org.inspector4j.Inspect4JException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.URL;

public class XmlConfigurationProvider implements ConfigurationProvider {

    @Override
    public Inspector4JConfiguration toProperties() {
        try {
            URL url = XmlConfigurationProvider.class.getClassLoader().getResource("inspector4j.xml");

            if (url == null) {
                return null;
            }

            InputStream io = url.openStream();
            JAXBContext jaxbContext = JAXBContext.newInstance(Inspector4JConfiguration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Inspector4JConfiguration) jaxbUnmarshaller.unmarshal(io);
        } catch (Inspect4JException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Inspect4JException("Couldn't initialize due to an error", ex);
        }


    }


}
