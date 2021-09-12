package byow.Core;

import byow.Core.Map.MapGenerator;
import byow.Core.Map.Position;
import byow.InputDemo.InputSource;
import byow.InputDemo.StringInputDevice;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.lab12.HexWorld;

import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;

    private Position posOfAvatar;
    private long seed;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        InputSource source = new StringInputDevice(input);
        TETile[][] board = MapGenerator.buildEmptyMap(WIDTH, HEIGHT);
        Random rand = new Random(getSeed(source));
        MapGenerator map = new MapGenerator(rand, board);
        showTheWorld(board);

        return board;
    }

    private void showTheWorld(TETile[][] grid) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(grid);
    }

    private Long getSeed(InputSource input) {
        StringBuilder seedBuilder = new StringBuilder();
        while (input.possibleNextInput()) {
            char c = input.getNextKey();
            if (c!= 'S') {
                seedBuilder.append(c);
            }
        }
        this.seed = Long.parseLong(seedBuilder.toString());
        return seed;
    }

    // add interactivity to an avatar
    private void moveAvatar(TETile[][] grid, Position moveTo) {
        grid[posOfAvatar.getX()][posOfAvatar.getY()] = Tileset.FLOOR;
        grid[moveTo.getX()][moveTo.getY()] = Tileset.AVATAR;
        this.posOfAvatar = moveTo;
    }

}
