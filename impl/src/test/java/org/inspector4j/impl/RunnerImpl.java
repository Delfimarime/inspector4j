package org.inspector4j.impl;

import org.inspector4j.Inspector4J;
import org.inspector4j.api.InspectionResult;
import org.inspector4j.impl.model.Address;

import java.lang.reflect.Method;

public class RunnerImpl {

    public static void main(String[] args) throws Exception {
        Method method = UserServiceImpl.class.getMethod("create", String.class, String.class, Address.class);
        InspectionResult result = Inspector4J.get().inspect(method, new Object[]{"Delfim", "RaitonBL ", new Address("Av.25 de Setembro", "1871")});
        System.out.println(result.toMap()); // Line 1

        result = Inspector4J.get(RunnerImpl.class).inspect(method, new Object[]{"Delfim", "RaitonBL ", new Address("Av.25 de Setembro", "1871")});
        System.out.println(result.toMap()); // Line 2

    }

}
