package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    /**
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return the width of row i for a size s hexagon.
     */
    public static int hexRowWidth(int s, int i) {
        return s - 2 * hexRowOffset(s, i);
    }

    /**
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return the relative x coordinate of the leftmost tile in the ith row.
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /** Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int i = 0; i < width; i += 1) {
            int x = p.x + i;
            int y = p.y;
            world[x][y] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    /**
     * Adds a hexagon to the world.
     * @param world the world to draw on
     * @param p the bottom left coordinate of the hexagon
     * @param s the size of the hexagon
     * @param t the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);
        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int num) {
        Position pp =  new Position(p.x, p.y);
        TETile t = randomTile();
        for (int i = 0; i < num; i += 1) {
            pp.y += 2 * s;
            addHexagon(world, pp, s, t);
            t = randomTile();
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.SAND;
            case 4: return Tileset.GRASS;
            default: return Tileset.NOTHING;
        }
    }

    public static Position topRightNeighbor(Position p, int s) {
        Position res = new Position(p.x, p.y);
        res.x += 2 * s - 1;
        res.y += s;
        return res;
    }


    public static Position bottomRightNeighbor(Position p, int s) {
        Position res = new Position(p.x, p.y);
        res.x += 2 * s - 1;
        res.y -= s;
        return res;
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        int s = 3;
        Position p = new Position(s, 2 * s);

        drawRandomVerticalHexes(world, p, s, 3);
        p = bottomRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p, s, 4);
        p = bottomRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p, s, 5);
        p = topRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p, s, 4);
        p = topRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p, s, 3);

        ter.renderFrame(world);
    }
}
