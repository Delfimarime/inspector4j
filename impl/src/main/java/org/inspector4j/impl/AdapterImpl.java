package org.inspector4j.impl;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.inspector4j.Adapter;
import org.inspector4j.api.*;
import org.inspector4j.api.configuration.InspectorConfiguration;

import java.lang.reflect.*;
import java.util.*;

public class AdapterImpl implements Adapter {

    private final NodeFactory nodeFactory = new NodeFactoryImpl();

    @Override
    public InspectionResult inspect(InspectorConfiguration configuration, Method method, Object[] args) {
        return inspect(configuration, method, args, Scope.ATTRIBUTE);
    }

    @Override
    public InspectionResult inspect(InspectorConfiguration configuration, Method method, Object[] args, Scope scope) {

        if (method == null) {
            throw new IllegalArgumentException("Method mustn't be null ");
        }

        if (args == null) {
            throw new IllegalArgumentException("Args mustn't be null ");
        }

        Scope vScope = configuration.isDynamic() ? scope : configuration.getScope();

        if (vScope == null) {
            vScope = configuration.getScope();
        }

        Node node = map(method, args, vScope);

        return (InspectionResult) Proxy.newProxyInstance(InspectionResult.class.getClassLoader(), new Class[]{Node.class, InspectionResult.class}, (obj, exec, vars) -> {
            if (exec.getName().equals("getMethod")) {
                return exec;
            } else if (exec.getName().equals("getArgs")) {
                return Arrays.stream(node.keys()).map(node::get).toArray(Node[]::new);
            } else {
                return MethodUtils.invokeMethod(node, exec.getName(), vars);
            }
        });

    }

    private Node map(Method method, Object[] args, Scope scope) {
        Node.Builder builder = nodeFactory.newBuilder();

        for (int index = 0; index < method.getParameterCount(); index++) {
            Parameter parameter = method.getParameters()[index];

            if (Scope.SECRET.equals(scope) || (scope.equals(Scope.ATTRIBUTE) && Commons.isSecret(parameter))) {
                builder.setNode(parameter.getName(), nodeFactory.create(args[index], scope));
            }

        }

        return builder.setType(InspectionResult.class).build();
    }

}
