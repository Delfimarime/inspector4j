package org.inspector4j.api;

public interface Context {

    int proceed();

    Object getBean();

    Node getResolved(Object object);

    boolean isResolved(Object object);

    void setResolvable(Object object, Node node);

    Context contextOf(Object object);

}
