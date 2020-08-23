package org.inspector4j.api.configuration;

import org.apache.commons.collections4.EnumerationUtils;
import org.inspector4j.Scope;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SystemConfigurationProvider implements ConfigurationProvider {

    private final Predicate<String> predicate;

    public SystemConfigurationProvider() {
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
    public Inspector4JConfiguration toProperties() {

        Set<String> fromEnv = keys(System.getenv().keySet());
        Set<String> fromSys = keys(EnumerationUtils.toList(System.getProperties().keys()).stream().map(Object::toString).collect(Collectors.toSet()));

        Set<String> keys = new HashSet<>();
        keys.addAll(fromEnv);
        keys.addAll(fromSys);

        Inspector4JConfiguration configuration = new Inspector4JConfiguration();

        configuration.setRoot(new InspectorConfiguration());
        configuration.getRoot().setScope(get("org.inspect4j.scope", Scope.class));
        configuration.getRoot().setOverridable(get("org.inspect4j.override", Boolean.class));
        configuration.setChildren(new HashMap<>());

        for (String key : keys) {
            InspectorConfiguration instance = new InspectorConfiguration();
            instance.setScope(get("org.inspect4j." + key + ".scope", Scope.class));
            instance.setOverridable(get("org.inspect4j." + key + ".override", Boolean.class));
            configuration.getChildren().put(key, instance);
        }

        return configuration;
    }

    @SuppressWarnings({"unchecked"})
    private <B> B get(String name, Class<B> returnType) {
        String value = Optional.ofNullable(System.getenv(name)).orElse(System.getProperty(name));

        if (value == null) {
            return null;
        }

        if (returnType.equals(Scope.class)) {
            return (B) Scope.valueOf(value);
        }

        if (returnType.equals(Boolean.class)) {
            return (B) ((Boolean) Boolean.parseBoolean(value));
        }

        throw new IllegalArgumentException("Cannot handle type :" + returnType.getName());

    }

    private Set<String> keys(Set<String> keySet) {
        return keySet.stream().filter(this.predicate).map(each -> each.substring(each.lastIndexOf(".") + 1)).collect(Collectors.toSet());
    }

}
