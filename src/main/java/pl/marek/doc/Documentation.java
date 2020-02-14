package pl.marek.doc;

import io.vavr.Tuple3;

import java.util.List;

public interface Documentation {

    void write(List<Tuple3<Integer, Double, Double>> values);
}
