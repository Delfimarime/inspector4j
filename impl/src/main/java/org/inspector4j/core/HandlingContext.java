package org.inspector4j.core;

import org.inspector4j.api.Node;

public interface HandlingContext {

    Node proceed();

    Node get(Object obj);

    Builder.Factory getFactory();

    interface Builder {

        Builder setObject(Object obj);

        HandlingContext build();

        interface Factory {

            Builder get();

        }

    }


}
