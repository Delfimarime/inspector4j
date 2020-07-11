package org.inspector4j.api;

import java.lang.reflect.Method;

public interface InspectionResult extends Node {

    Node[] getArgs();

    Method getMethod();

}
