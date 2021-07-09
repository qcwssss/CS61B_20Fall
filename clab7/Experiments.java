import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST randTree = new BST();
        // chart info
        List<Integer> xVal = new ArrayList<>();
        List<Double> yAvgDepth = new ArrayList<>();
        List<Double> yOptAvgDepth = new ArrayList<>();

        int x = 0;
        while (randTree.size() < 5000) {
            int addItems = RandomGenerator.getRandomInt(10000);
            // if contains, skip
            if (!randTree.contains(addItems)) {
                randTree.add(addItems); // add items to BST

                xVal.add(x);
                yAvgDepth.add(randTree.averageDepth());
                yOptAvgDepth.add(ExperimentHelper.optimalAverageDepth(randTree.size()));
                // can't be x, why?
                x++;
            }
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x items").yAxisTitle("y average_depth").build();
        chart.addSeries("averageDepth", xVal, yAvgDepth);
        chart.addSeries("optimalAverageDepth", xVal, yOptAvgDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
