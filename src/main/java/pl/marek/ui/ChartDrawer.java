package pl.marek.ui;

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.chart.controllers.keyboard.camera.AWTCameraKeyController;
import org.jzy3d.chart.controllers.mouse.camera.AWTCameraMouseController;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import pl.marek.function.Function;
import pl.marek.model.Particle;

import java.util.ArrayList;
import java.util.List;

public class ChartDrawer extends AbstractAnalysis {

    private final Function function;
    private List<Point> points;

    public ChartDrawer(Function function) {
        this.function = function;
        this.points = new ArrayList<>();
    }

    @Override
    public void init() {
        Mapper mapper = new Mapper() {
            @Override
            public double f(double x, double y) {
                return function.fitness(x, y);
            }
        };

        Range range = new Range(
                function.getFunctionRestriction()._1.floatValue(),
                function.getFunctionRestriction()._2.floatValue()
        );
        int steps = 100;

        final Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
        surface.setColorMapper(
                new ColorMapper(
                        new ColorMapRainbow(),
                        surface.getBounds().getZmin(),
                        surface.getBounds().getZmax(),
                        new Color(1, 1, 1, .5f)
                )
        );
        surface.setFaceDisplayed(true);
        surface.setWireframeDisplayed(false);

        chart = AWTChartComponentFactory.chart(Quality.Intermediate, getCanvasType());
        chart.addController(new AWTCameraKeyController());
        chart.addController(new AWTCameraMouseController());
        chart.getScene().getGraph().add(surface);
    }

    public void addParticles(List<Particle> particles) {
        for (Particle p : particles) {
            Point point = new Point(
                    new Coord3d(
                            p.getPosition().X(),
                            p.getPosition().Y(),
                            function.fitness(p.getPosition().X(), p.getPosition().Y())
                    ),
                    Color.BLACK,
                    10.0f
            );
            points.add(point);
            chart.getScene().getGraph().add(point);
        }
    }

    public void clearParticles() {
        points.forEach(p -> chart.getScene().getGraph().remove(p));
    }
}
