package bearmaps.test;

import bearmaps.proj2d.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import bearmaps.proj2d.server.handler.impl.RasterAPIHandler;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.StringJoiner;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Test of the rastering part of the assignment.*/
public class TestRasterAPIHandler {
    private static final double DOUBLE_THRESHOLD = 0.000000001;
    private static DecimalFormat df2 = new DecimalFormat(".#########");
    private static final String PARAMS_FILE = "../library-fa20/data/proj2d_test_inputs/raster_params.txt";
    private static final String RESULTS_FILE = "../library-fa20/data/proj2d_test_inputs/raster_results.txt";
    private static final int NUM_TESTS = 8;
    private static RasterAPIHandler rasterer;


    @Before
    public void setUp() throws Exception {
        rasterer = new RasterAPIHandler();
    }

    @Test
    public void testProcessRequests() throws Exception {
        List<Map<String, Double>> testParams = paramsFromFile();
        List<Map<String, Object>> expectedResults = resultsFromFile();

        for (int i = 0; i < NUM_TESTS; i++) {
            System.out.println(String.format("Running test: %d", i));
            Map<String, Double> params = testParams.get(i);
            Map<String, Object> actual = rasterer.processRequest(params, null);
            Map<String, Object> expected = expectedResults.get(i);
            String msg = "Your results did not match the expected results for input "
                         + mapToString(params) + ".\n";
            // expected = 4, when i = 0;
            assertEquals(expected.get("depth"), actual.get("depth"));
            checkParamsMap(msg, expected, actual);

        }
    }

    private List<Map<String, Double>> paramsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(PARAMS_FILE), Charset.defaultCharset());
        List<Map<String, Double>> testParams = new ArrayList<>();
        int lineIdx = 2; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            Map<String, Double> params = new HashMap<>();
            params.put("ullon", Double.parseDouble(lines.get(lineIdx)));
            params.put("ullat", Double.parseDouble(lines.get(lineIdx + 1)));
            params.put("lrlon", Double.parseDouble(lines.get(lineIdx + 2)));
            params.put("lrlat", Double.parseDouble(lines.get(lineIdx + 3)));
            params.put("w", Double.parseDouble(lines.get(lineIdx + 4)));
            params.put("h", Double.parseDouble(lines.get(lineIdx + 5)));
            testParams.add(params);
            lineIdx += 6;
        }
        return testParams;
    }

    private List<Map<String, Object>> resultsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(RESULTS_FILE), Charset.defaultCharset());
        List<Map<String, Object>> expected = new ArrayList<>();
        int lineIdx = 4; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            Map<String, Object> results = new HashMap<>();
            results.put("raster_ul_lon", Double.parseDouble(lines.get(lineIdx)));
            results.put("raster_ul_lat", Double.parseDouble(lines.get(lineIdx + 1)));
            results.put("raster_lr_lon", Double.parseDouble(lines.get(lineIdx + 2)));
            results.put("raster_lr_lat", Double.parseDouble(lines.get(lineIdx + 3)));
            results.put("depth", Integer.parseInt(lines.get(lineIdx + 4)));
            results.put("query_success", Boolean.parseBoolean(lines.get(lineIdx + 5)));
            lineIdx += 6;
            String[] dimensions = lines.get(lineIdx).split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);
            lineIdx += 1;
            String[][] grid = new String[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = lines.get(lineIdx);
                    lineIdx++;
                }
            }
            results.put("render_grid", grid);
            expected.add(results);
        }
        return expected;
    }

    private void checkParamsMap(String err, Map<String, Object> expected,
                                            Map<String, Object> actual) {
        for (String key : expected.keySet()) {
            assertTrue(err + "Your results map is missing "
                       + key, actual.containsKey(key));
            Object o1 = expected.get(key);
            Object o2 = actual.get(key);

            if (o1 instanceof Double) {
                String errMsg = genDiffErrMsg(err, expected, actual);
                assertTrue(errMsg, Math.abs((Double) o1 - (Double) o2) < DOUBLE_THRESHOLD);
            } else if (o1 instanceof String[][]) {
                String errMsg = genDiffErrMsg(err, expected, actual);
                assertArrayEquals(errMsg, (String[][]) o1, (String[][]) o2);
            } else {
                String errMsg = genDiffErrMsg(err, expected, actual);
                assertEquals(errMsg, o1, o2);
            }
        }
    }

    /** Generates an actual/expected message from a base message, an actual map,
     *  and an expected map.
     */
    private String genDiffErrMsg(String basemsg, Map<String, Object> expected,
                                 Map<String, Object> actual) {
        return basemsg + "Expected: " + mapToString(expected) + ", but got\n"
                       + "Actual  : " + mapToString(actual);
    }

    /** Converts a Rasterer input or output map to its string representation. */
    private String mapToString(Map<String, ?> m) {
        StringJoiner sj = new StringJoiner(", ", "{", "}");

        List<String> keys = new ArrayList<>();
        keys.addAll(m.keySet());
        Collections.sort(keys);

        for (String k : keys) {

            StringBuilder sb = new StringBuilder();
            sb.append(k);
            sb.append("=");
            Object v = m.get(k);

            if (v instanceof String[][]) {
                sb.append(Arrays.deepToString((String[][]) v));
            } else if (v instanceof Double) {
                sb.append(df2.format(v));
            } else {
                sb.append(v.toString());
            }
            String thisEntry = sb.toString();

            sj.add(thisEntry);
        }

        return sj.toString();
    }

    /*
    @Test
    public void testGetDepth() {
        //double SL = 288200.0;
        //double curLonDPP = SL * Math.abs(Constants.ROOT_LRLON - Constants.ROOT_ULLON)/Constants.TILE_SIZE;

        double lrlon=-122.22275132672245, ullon=-122.23995662778569, w=613.0, h=676.0;

        int actual  = (int) rasterer.getDepthAndPixelInFeet(ullon, lrlon, w)[0];
        double pixel1  =  rasterer.getDepthAndPixelInFeet(ullon, lrlon, w)[1];

        assertEquals(4, actual);
        assertEquals(6.1841, pixel1, 0.0001);

    }
     */

    @Test
    public void testProcessFourImages() {

        Map<String, Double> params1 = new HashMap<>();
        // {lrlon=-122.2104604264636, ullon=-122.30410170759153, w=1091.0, h=566.0, ullat=37.870213571328854, lrlat=37.8318576119893}
        params1.put("ullon",-122.30410170759153);
        params1.put("ullat", 37.870213571328854);
        params1.put("lrlon", -122.2104604264636);
        params1.put("lrlat", 37.8318576119893);
        params1.put("w", 1091.0);
        params1.put("h", 566.0);

        Map<String, Double> expected1 = new HashMap<>();
        // {lrlon=-122.2104604264636, ullon=-122.30410170759153, w=1091.0, h=566.0, ullat=37.870213571328854, lrlat=37.8318576119893}
        expected1.put("ullon",-122.30410170759153);
        expected1.put("ullat", 37.870213571328854);
        expected1.put("lrlon", -122.2104604264636);
        expected1.put("lrlat", 37.8318576119893);
        expected1.put("w", 1091.0);
        expected1.put("h", 566.0);

        Map<String, Object> actual = rasterer.processRequest(params1, null);
        String msg = "Your results did not match the expected results for input "
                + mapToString(params1) + ".\n";
        // expected = 4, when i = 0;
        checkParamsMap(msg, expected1, actual);
    }

}
