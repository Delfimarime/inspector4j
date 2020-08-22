package org.inspector4j.api.internal;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.inspector4j.Adapter;
import org.inspector4j.api.Inspector;
import org.inspector4j.api.configuration.Configuration;

import java.lang.reflect.Proxy;

public class FactoryImpl implements Factory {

    private final Adapter adapter;

    public FactoryImpl(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public Inspector create(Configuration configuration) {

        if (configuration == null) {
            throw new IllegalArgumentException("Configuration mustn't be null");
        }

        Object object = new Object();

        return (Inspector) Proxy.newProxyInstance(configuration.getClass().getClassLoader(), new Class[]{Inspector.class}, (instance, method, args) -> {
            if (method.getName().equals("inspect")) {
                return MethodUtils.invokeMethod(this.adapter, "inspect", ArrayUtils.addFirst(args, configuration));
            } else {
                return method.invoke(object, args);
            }
        });
    }

}
