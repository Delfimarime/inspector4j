package org.inspector4j.api;

import org.inspector4j.api.internal.Node;

import java.lang.reflect.Method;

public interface InspectionResult extends Node {

    Node[] getArgs();

    Method getMethod();

}
