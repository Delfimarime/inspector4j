package org.inspector4j.impl;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.TypeUtils;
import org.inspector4j.api.InspectionException;
import org.inspector4j.api.Inspector;
import org.inspector4j.api.Node;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class InspectorImpl implements Inspector {

    private final NullNode nullReference;

    public InspectorImpl() {
        this.nullReference = new NullNode();
    }

    @Override
    public Node inspect(Object object) {
        Map<String, Node> map = new HashMap<>();
        return Optional.ofNullable(object).map(value -> inspect(value, map)).orElse(nullReference);
    }

    private boolean isKnownType(Object object) {
        return object instanceof String || ClassUtils.isPrimitiveOrWrapper(object.getClass());
    }

    private Node asNode(Map<String, Node> map) {
        Map<Node, Node> container = map.entrySet().stream().collect(Collectors.toMap(key -> inspect(key, map), value -> inspect(value, map)));
        return ObjectNode.Builder.create(container);
    }

    private Node inspect(Object object, Map<String, Node> map) {

        if (object == null) {
            return nullReference;
        }

        Node node;
        String hash = object.getClass().getName() + "@" + object.hashCode();

        if (map.containsKey(hash)) {
            return map.get(hash);
        } else if (object instanceof Node) {
            node = (Node) object;
        } else if (isKnownType(object)) {
            return new ValueNode(object);
        } else if (object instanceof Map) {
            node = asNode(map);
        } else if (TypeUtils.isArrayType(object.getClass())) {
            node = new IterableNode(Arrays.stream((Object[]) object).map(each -> inspect(each, map)).toArray(Node[]::new));
        } else if (object instanceof Collection<?>) {
            node = new IterableNode(((Collection<?>) object).stream().map(each -> inspect(each, map)).toArray(Node[]::new));
        } else {
            ObjectNode.Builder builder = asNode(object, map);
            node = builder.build();
        }

        // CACHE
        map.put(object.getClass().getName() + "@" + object.hashCode(), node);

        return node;
    }

    private ObjectNode.Builder asNode(Object object, Map<String, Node> map) {
        try {

            ObjectNode.Builder builder = ObjectNode.Builder.get();

            for (Field field : FieldUtils.getAllFieldsList(object.getClass())) {
                builder.addNode(field.getName(), inspect(FieldUtils.readField(field, object, true), map));
            }

            return builder;
        } catch (Exception ex) {
            throw new InspectionException(ex);
        }
    }

}
