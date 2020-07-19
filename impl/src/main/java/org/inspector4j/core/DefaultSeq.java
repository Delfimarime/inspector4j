package org.inspector4j.core;


import java.util.ArrayList;
import java.util.List;

public class DefaultSeq implements Seq {

    private final List<Handler> seq;

    public DefaultSeq() {
        this.seq = new ArrayList<>();
    }

    @Override
    public List<Handler> toList() {
        return new ArrayList<>(seq);
    }

    @Override
    public Seq remove(int index) {
        if (index > 0 && index < seq.size()) {
            seq.remove(index);
        }
        return this;
    }

    @Override
    public Seq add(Handler instance) {
        if (instance != null) {
            seq.add(instance);
        }
        return this;
    }

    @Override
    public Seq set(int index, Handler instance) {
        if (index > 0 && index < seq.size()) {
            seq.set(index, instance);
        }
        return this;
    }

    @Override
    public Seq setAt(int index, Handler instance) {
        if (index > 0 && index < seq.size()) {
            seq.add(index, instance);
        }
        return this;
    }

}
