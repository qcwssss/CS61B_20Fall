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

        while (randTree.size() < 5000) {
            int addItems = RandomGenerator.getRandomInt(10000);
            // if contains, skip
            if (!randTree.contains(addItems)) {
                randTree.add(addItems); // add items to BST

                xVal.add(randTree.size());
                yAvgDepth.add(randTree.averageDepth());
                yOptAvgDepth.add(ExperimentHelper.optimalAverageDepth(randTree.size()));
                // can't be x, why? That's because x = size -1, before x++.
                //x++;
            }
        }

        XYChart chart = new XYChartBuilder().width(1000).height(600).xAxisTitle("x: items").yAxisTitle("y: average depth").build();
        chart.addSeries("averageDepth", xVal, yAvgDepth);
        chart.addSeries("optimalAverageDepth", xVal, yOptAvgDepth);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST tree = new BST();
        int M = 5000; // total of M times.
        // chart info
        List<Integer> xVal =  new ArrayList<>();
        List<Double> yAvgDepth = new ArrayList<>();

        int x = 0; // steps
        // randomly inserting N items
        while (tree.size() < M) {
            int rand = RandomGenerator.getRandomInt(2*M);
            if (!tree.contains(rand)) {
                tree.add(rand);
            }
        }
        yAvgDepth.add(tree.averageDepth());
        xVal.add(x);

        for (int i = 0; i < M; i++) {
            ExperimentHelper.randomDeleteSuccessor(tree);
            ExperimentHelper.randomInsert(2*M, tree);
            yAvgDepth.add(tree.averageDepth());
            xVal.add(i + 1);
        }

        XYChart chart =
                new XYChartBuilder().width(1000).height(600).xAxisTitle("x: operation").yAxisTitle("y: average depth").build();
        chart.addSeries("averageDepth", xVal, yAvgDepth);
        new SwingWrapper<>(chart).displayChart();
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        //experiment1();
        experiment2();
    }
}
