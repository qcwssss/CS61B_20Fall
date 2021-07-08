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
        int numberOfItems =  5000;
        Random r = new Random();
        BST randTree = new BST();
        // chart info
        List<Integer> xVal = new ArrayList<>();
        List<Double> yAvgDepth = new ArrayList<>();
        List<Double> yOptAvgDepth = new ArrayList<>();

        for (int x = 0; x < numberOfItems; x++) {
            int addItems = r.nextInt(100);
            // if contains, skip
            if (!randTree.contains(addItems)){
                randTree.add(addItems); // add items to BST
            }

            xVal.add(x);
            yAvgDepth.add(randTree.averageDepth());
            yOptAvgDepth.add(ExperimentHelper.optimalAverageDepth(x));
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x items").yAxisTitle("y average_depth").build();
        chart.addSeries("average depth", xVal, yAvgDepth);
        chart.addSeries("optimal average depth", xVal, yOptAvgDepth);

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
