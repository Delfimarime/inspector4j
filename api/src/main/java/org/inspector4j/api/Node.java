package org.inspector4j.api;

public interface Node {

    Node[] keys();

    Node get(Node key);

    boolean isNull();

    boolean isArray();

    boolean isDouble();

    boolean isBoolean();

    boolean isLong();

    boolean isText();

    boolean isInteger();

    boolean isContainer();

    int size();

    String asText();

    Long asLong();

    boolean asBoolean();

    int asInt();

}
