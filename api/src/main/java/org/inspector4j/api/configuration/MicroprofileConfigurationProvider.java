package org.inspector4j.api.configuration;

import org.apache.commons.collections4.IterableUtils;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.inspector4j.api.Scope;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MicroprofileConfigurationProvider implements ConfigurationProvider {

    private final Predicate<String> predicate;

    public MicroprofileConfigurationProvider() {
        Predicate<String> scopePattern = Pattern.compile("org.inspect4j.configuration.*.scope").asPredicate();
        Predicate<String> overridePattern = Pattern.compile("org.inspect4j.configuration.*.override").asPredicate();
        this.predicate = (value) -> {
            if (value == null) {
                return Boolean.FALSE;
            }

            return scopePattern.test(value) || overridePattern.test(value);
        };
    }

    @Override
    public Configuration toProperties() {
        Config config = ConfigProvider.getConfig();

        Set<String> keys = IterableUtils.toList(config.getPropertyNames()).stream().filter(this.predicate).map(each -> each.substring(each.lastIndexOf(".") + 1)).collect(Collectors.toSet());

        Configuration configuration = new Configuration();

        configuration.setRoot(new InspectorConfiguration());
        configuration.getRoot().setScope(config.getOptionalValue("org.inspect4j.scope", Scope.class).orElse(null));
        configuration.getRoot().setOverridable(config.getOptionalValue("org.inspect4j.override", Boolean.class).orElse(null));
        configuration.setInspectors(new ArrayList<>());

        for (String key : keys) {
            NamedConfiguration instance = new NamedConfiguration();
            instance.setName(key);
            instance.setScope(config.getOptionalValue("org.inspect4j.scope", Scope.class).orElse(null));
            instance.setOverridable(config.getOptionalValue("org.inspect4j.override", Boolean.class).orElse(null));
            configuration.getInspectors().add(instance);
        }

        return configuration;
    }

    public static void main(String[] args) {
        String[] variables = {"org.inspect4j.configuration.scope", "org.inspect4j.configuration.override", "org.inspect4j.configuration.c.override", "org.inspect4j.c.d.scope"};

        String sRegex = "org.inspect4j.configuration.*.scope";
        String oRegex = "org.inspect4j.configuration.*.override";


        for (String var : variables) {
            System.out.println(var + " => " + (var.matches(sRegex) || var.matches(oRegex)));
        }

    }

}
