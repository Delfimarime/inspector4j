package org.inspector4j.api;

import java.lang.reflect.Method;

public interface Analysis extends Node {

    Node[] getArgs();

    Method getMethod();

}
