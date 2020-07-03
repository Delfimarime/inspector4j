package org.inspector4j.impl;

import org.inspector4j.api.Inspector;
import org.inspector4j.api.Node;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.TypeUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class InspectorImpl implements Inspector {

    private NullNode nullReference;

    public InspectorImpl() {
        this.nullReference = new NullNode();
    }

    @Override
    public Node inspect(Object object) {
        if (object == null) {
            return nullReference;
        } else if (ClassUtils.isPrimitiveOrWrapper(object.getClass())) {
            return new ValueNode(object);
        } else {
            return inspect(object, new HashMap<>());
        }
    }

    private Node inspect(final Object object, final Map<String, Node> map) {
        try {
            Node node;

            if (object instanceof Node) {
                node = (Node) object;
            } else if (object instanceof Map) {
                Map<Node, Node> container = map.entrySet().stream().collect(Collectors.toMap(key -> inspect(key, map), value -> inspect(value, map)));
                node = ObjectNode.Builder.create(container);
            } else if (TypeUtils.isArrayType(object.getClass())) {
                node = new IterableNode(Arrays.stream((Object[]) object).map(each -> inspect(each, map)).toArray(n -> new Node[n]));
            } else if (object instanceof Collection<?>) {
                node = new IterableNode(((Collection<?>)object).stream().map(each -> inspect(each, map)).toArray(n -> new Node[n]));
            } else {
                ObjectNode.Builder builder = ObjectNode.Builder.get();
                FieldUtils.getAllFieldsList(object.getClass()).forEach(field -> builder.addNode(field.getName(), inspect(object, field.getName(), map)));
                node = builder.build();
            }

            // CACHE
            map.put(object.getClass().getName() + "@" + object.hashCode(), node);

            return node;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    private Node inspect(Object object, String fieldName, Map<String, Node> map) {
        try {

            Node each;
            Object value = FieldUtils.readField(object, fieldName, true);

            if (value == null) {
                each = nullReference;
            } else if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
                each = new ValueNode(value);
            } else if (map.containsKey(value.getClass().getName() + "@" + value.hashCode())) {
                each = map.get(value.getClass().getName() + "@" + value.hashCode());
            } else {
                each = inspect(value, map);
            }

            return each;
        } catch (Exception ex) {
            throw new IllegalStateException("Unexpect behaviour", ex);
        }

    }

}
