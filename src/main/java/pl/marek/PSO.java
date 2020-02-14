package pl.marek;

import io.vavr.Tuple3;
import pl.marek.config.Config;
import pl.marek.doc.Documentation;
import pl.marek.doc.ExcelWriter;
import pl.marek.function.Function;
import pl.marek.model.Position;
import pl.marek.ui.ChartDrawer;

import java.util.ArrayList;
import java.util.List;

public class PSO {

    private final ChartDrawer chartDrawer;
    private double W;
    private final Config config;
    private final Function function;
    private final Boolean withDocs;
    private final List<Tuple3<Integer, Double, Double>> values;

    private Space searchSpace;

    public PSO(ChartDrawer chartDrawer, Config config, Function function, Boolean withDocs) {
        this.chartDrawer = chartDrawer;
        this.config = config;
        this.function = function;
        this.withDocs = withDocs;
        this.values = new ArrayList<>();
        this.W = config.W();
    }

    public Position run() {
        System.out.println("Initialization");
        searchSpace = new Space(config, function, config.particles());

        System.out.println("Init position particles");
        searchSpace.printParticles();

        int iteration = 1;
        while (iteration <= config.iteration()) {
            printIterationInfo(iteration);
            searchSpace.setGlobalBestPosition();
            searchSpace.printGlobalBestPosition();
            searchSpace.setParticlesBest();

            updateChartUIIfRequired();
            addMetaInfoFor(iteration);

            searchSpace.moveParticles();

            updateW(iteration);

            iteration++;
        }

        updateDocsIfRequired();

        return searchSpace.getGlobalBestPosition();
    }

    private void updateW(int iteration) {
        int part = config.iteration() / 5;
        if (iteration % part == 0) {
            this.W -= 0.1;
        }
    }

    private void printIterationInfo(int iteration) {
        System.out.println("========================= iteration : " + iteration);
    }

    private void updateChartUIIfRequired() {
        if (chartDrawer != null) {
            chartDrawer.clearParticles();
            chartDrawer.addParticles(searchSpace.getParticles());
        }
    }

    private void addMetaInfoFor(int iteration) {
        values.add(new Tuple3<>(
                iteration,
                searchSpace.getGlobalBestValue(),
                searchSpace.getGenerationAverageValue())
        );
    }

    private void updateDocsIfRequired() {
        if (withDocs) {
            Documentation excel = new ExcelWriter();
            excel.write(values);
        }
    }

    public List<Tuple3<Integer, Double, Double>> getValues() {
        return values;
    }
}
