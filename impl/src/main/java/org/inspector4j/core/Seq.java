package org.inspector4j.core;

import java.util.List;

public interface Seq {

    List<Handler> toList();

    Seq remove(int index);

    Seq add(Handler instance);

    Seq set(int index, Handler instance);

    Seq setAt(int index, Handler instance);

}
