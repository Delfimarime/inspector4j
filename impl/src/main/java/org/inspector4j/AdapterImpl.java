package org.inspector4j;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.api.configuration.Configuration;
import org.inspector4j.api.internal.Node;
import org.inspector4j.api.internal.NodeFactory;
import org.inspector4j.impl.Commons;
import org.inspector4j.impl.MaskedNode;
import org.inspector4j.impl.NodeFactoryImpl;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class AdapterImpl implements Adapter {

    private final NodeFactory nodeFactory = new NodeFactoryImpl();

    @Override
    public InspectionResult inspect(Configuration configuration, Method method, Object[] args) {
        return inspect(configuration, method, args, SecretVisibility.MASKED);
    }

    @Override
    public InspectionResult inspect(Configuration configuration, Method method, Object[] args, SecretVisibility visibility) {

        if (method == null) {
            throw new IllegalArgumentException("Method mustn't be null ");
        }

        if (args == null) {
            throw new IllegalArgumentException("Args mustn't be null ");
        }

        SecretVisibility target = configuration.isOverridable() ? visibility : configuration.getScope();

        if (target == null) {
            target = configuration.getScope();
        }

        Node node = map(method, args, target);

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

    private Node map(Method method, Object[] args, SecretVisibility visibility) {
        Node.Builder builder = nodeFactory.newBuilder();

        for (int index = 0; index < method.getParameterCount(); index++) {
            Parameter parameter = method.getParameters()[index];

            boolean isSecret = Commons.isSecret(parameter);

            if (SecretVisibility.VISIBLE.equals(visibility) || (visibility.equals(SecretVisibility.MASKED) && !isSecret)) {
                builder.setNode(parameter.getName(), nodeFactory.create(args[index], visibility));
            } else if (visibility.equals(SecretVisibility.MASKED)) {
                builder.setNode(parameter.getName(), new MaskedNode(args[index].getClass()));
            }

        }

        return builder.setType(InspectionResult.class).build();
    }

}
