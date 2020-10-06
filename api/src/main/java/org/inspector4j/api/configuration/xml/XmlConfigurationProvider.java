package org.inspector4j.api.configuration.xml;

import org.apache.commons.lang3.StringUtils;
import org.inspector4j.Inspect4JException;
import org.inspector4j.SecretVisibility;
import org.inspector4j.api.configuration.ConfigurationProvider;
import org.inspector4j.api.configuration.Inspector4JConfiguration;
import org.inspector4j.api.configuration.InspectorConfiguration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class XmlConfigurationProvider implements ConfigurationProvider {

    @Override
    public Inspector4JConfiguration toProperties() {
        try {
            URL url = XmlConfigurationProvider.class.getClassLoader().getResource("inspector4j.xml");

            if (url == null) {
                return null;
            }

            InputStream io = url.openStream();
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlConfiguration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            XmlConfiguration xmlConfiguration = (XmlConfiguration) jaxbUnmarshaller.unmarshal(io);

            if (xmlConfiguration == null) {
                return null;
            }

            Inspector4JConfiguration configuration = new Inspector4JConfiguration();

            if (xmlConfiguration.getRoot() != null) {
                configuration.setRoot(new InspectorConfiguration());
                configuration.getRoot().setVisibility(Optional.ofNullable(parse(xmlConfiguration.getRoot().getSecretsVisibility())).map(SecretVisibility::valueOf).orElse(null));
                configuration.getRoot().setAllowRuntimeConfiguration(Optional.ofNullable(parse(xmlConfiguration.getRoot().getAllowRuntimeConfiguration())).map(Boolean::parseBoolean).orElse(null));
            }

            Map<String, InspectorConfiguration> config = new HashMap<>();

            if (xmlConfiguration.getChildren() != null) {
                for (XmlNamedInspectorConfiguration each : xmlConfiguration.getChildren()) {
                    if (StringUtils.isNotBlank(each.getName()) && !config.containsKey(each.getName())) {
                        InspectorConfiguration instance = new InspectorConfiguration();
                        instance.setVisibility(Optional.ofNullable(parse(each.getSecretsVisibility())).map(SecretVisibility::valueOf).orElse(null));
                        instance.setAllowRuntimeConfiguration(Optional.ofNullable(parse(each.getAllowRuntimeConfiguration())).map(Boolean::parseBoolean).orElse(null));
                        config.put(each.getName(), instance);
                    }
                }
            }

            configuration.setChildren(config);
            return configuration;

        } catch (Inspect4JException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Inspect4JException("Couldn't initialize due to an error", ex);
        }

    }

    public static String parse(String expr) {

        if (StringUtils.isBlank(expr)) {
            return null;
        }

        if (expr.startsWith("{sys:") && expr.endsWith("}")) {
            return System.getProperty(expr.substring(5, expr.length() - 1));
        }

        if (expr.startsWith("{env:") && expr.endsWith("}")) {
            return System.getenv(expr.substring(5, expr.length() - 1));
        }

        if (expr.startsWith("{") && expr.endsWith("}")) {
            String key = expr.substring(2, expr.length() - 1);
            return Optional.ofNullable(System.getenv(key)).orElseGet(() -> System.getProperty(key));
        }

        return expr;
    }

}
