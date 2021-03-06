package org.inspector4j.api.configuration;

import org.apache.commons.collections4.IterableUtils;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.inspector4j.SecretVisibility;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MicroprofileConfigurationProvider implements ConfigurationProvider {

    private final Predicate<String> predicate;

    public MicroprofileConfigurationProvider() {
        Predicate<String> scopePattern = Pattern.compile("org.inspect4j.configuration.*." + InspectorConfiguration.VISIBILITY_FIELD).asPredicate();
        Predicate<String> overridePattern = Pattern.compile("org.inspect4j.configuration.*.allow-runtime-configuration").asPredicate();
        this.predicate = (value) -> {
            if (value == null) {
                return Boolean.FALSE;
            }

            return scopePattern.test(value) || overridePattern.test(value);
        };
    }

    @Override
    public Inspector4JConfiguration toProperties() {
        Config config = ConfigProvider.getConfig();

        Set<String> keys = IterableUtils.toList(config.getPropertyNames()).stream().filter(this.predicate).map(each -> each.substring(each.lastIndexOf(".") + 1)).collect(Collectors.toSet());

        Inspector4JConfiguration configuration = new Inspector4JConfiguration();

        configuration.setRoot(new InspectorConfiguration());
        configuration.getRoot().setVisibility(config.getOptionalValue("org.inspect4j." + InspectorConfiguration.VISIBILITY_FIELD, SecretVisibility.class).orElse(null));
        configuration.getRoot().setAllowRuntimeConfiguration(config.getOptionalValue("org.inspect4j." + InspectorConfiguration.ALLOW_RUNTIME_CONFIGURATION_FIELD, Boolean.class).orElse(null));
        configuration.setChildren(new HashMap<>());

        for (String key : keys) {
            InspectorConfiguration instance = new InspectorConfiguration();

            instance.setVisibility(config.getOptionalValue("org.inspect4j." + key + "." + InspectorConfiguration.VISIBILITY_FIELD, SecretVisibility.class).orElse(null));
            instance.setAllowRuntimeConfiguration(config.getOptionalValue("org.inspect4j." + key + "." + InspectorConfiguration.ALLOW_RUNTIME_CONFIGURATION_FIELD, Boolean.class).orElse(null));

            configuration.getChildren().put(key, instance);
        }

        return configuration;
    }

}
