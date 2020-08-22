package org.inspector4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.inspector4j.api.Inspector;
import org.inspector4j.api.configuration.Configuration;
import org.inspector4j.api.configuration.ConfigurationManager;
import org.inspector4j.api.internal.Factory;
import org.inspector4j.api.internal.FactoryImpl;

import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public final class Inspector4J {

    private static Inspector basic;
    private static Factory factory;
    private static boolean startedUp = Boolean.FALSE;
    private static ConfigurationManager configurationManager;
    private static final Map<String, Inspector> cache = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LogManager.getLogger(Inspector4J.class);

    private static void init() {
        try {
            configurationManager = new ConfigurationManager();

            Iterator<Adapter> iterator = ServiceLoader.load(Adapter.class).iterator();

            if (!iterator.hasNext()) {
                throw new Inspect4JException("Couldn't initialize due to " + Adapter.class.getName() + " implementation absence!");
            }

            Inspector4J.factory = new FactoryImpl(iterator.next());

            LOGGER.info(factory.getClass() + " initialized as " + Factory.class.getName() + " implementation");

        } catch (Inspect4JException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Inspect4JException("Couldn't initialize due to an error", ex);
        }
    }

    public static Inspector get() {
        return findById(null);
    }

    public static Inspector get(String name) {
        return findById(null);
    }

    public static Inspector get(Class<?> type) {
        return type == null ? get() : findById(type.getName());
    }

    private static Inspector findById(String name) {

        if (!startedUp) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Inspector4J hasn't been initialized...");
            }

            init();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Inspector4J has been initialized...");
            }

            startedUp = Boolean.TRUE;
        }

        Inspector instance = name == null || name.trim().isEmpty() ? null : cache.get(name);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(instance == null ? "No " + Inspector.class.getName() + " cached for " + name : instance.getClass() + " cached for " + name);
        }

        if (instance != null) {
            return instance;
        }

        if (name == null || name.trim().isEmpty()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(basic == null ? "Default  " + Inspector.class.getName() + " is absent and will be built " + name : "Default  " + Inspector.class.getName() + " is present as " + basic.getClass());
            }

            if (basic != null) {
                return basic;
            }

            basic = factory.create(newConfiguration(null));

            return basic;
        }

        instance = cache.get(name);

        if (instance == null) {
            instance = cache.get(name);

            if (instance != null) {
                return instance;
            }

            instance = factory.create(newConfiguration(name));

            cache.put(name, instance);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(instance.getClass() + " built for " + name);
            }
        }

        return instance;
    }

    private static Configuration newConfiguration(String name) {
        Object instance = new Object();
        return (Configuration) Proxy.newProxyInstance(Inspector4J.class.getClassLoader(), new Class[]{Configuration.class}, (obj, method, args) -> {
            if (method.getName().equals("getScope")) {
                return name == null ? configurationManager.getScope() : configurationManager.getScope(name);
            } else if (method.getName().equals("isOverridable")) {
                return name == null ? configurationManager.isOverridable() : configurationManager.isOverridable(name);
            } else {
                return method.invoke(instance, args);
            }
        });
    }


}
