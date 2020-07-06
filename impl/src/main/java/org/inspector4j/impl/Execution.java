package org.inspector4j.impl;

import java.io.Serializable;
import java.lang.reflect.Method;

 class Execution implements Serializable {

    private Method method;
    private Object[] args;
    private Class<?> beanClass;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

}
