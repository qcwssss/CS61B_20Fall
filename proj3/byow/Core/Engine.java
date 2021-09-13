package byow.Core;

import byow.Core.Input.KeyboardInputSource;
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
    private TETile[][] world;
    private InputSource source;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        source = new KeyboardInputSource();
        this.world = MapGenerator.buildEmptyMap(WIDTH, HEIGHT);

        while (source.possibleNextInput()) {
            char action = source.getNextKey();
            processInput(source, action);
        }

        showTheWorld(world);
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

        this.source = new StringInputDevice(input);
        this.world = MapGenerator.buildEmptyMap(WIDTH, HEIGHT);
        while (source.possibleNextInput()) {
            char action = source.getNextKey();
            processInput(source, action);
        }


        showTheWorld(world);

        return world;
    }

    /**
     * Take actions based on input.
     * @param input input key
     * @param action types of action
     */
    private void processInput(InputSource input, char action) {
        action = Character.toUpperCase(action);
        switch (action) {
            case 'N':
                getSeed(input);
                MapGenerator map = new MapGenerator(new Random(this.seed), world);
                posOfAvatar = map.getAvatarPos();
                break;
            case ':':
                if (input.possibleNextInput()){
                    if (input.getNextKey() == 'q') {
                      // save and exit
                    }
                }
                break;
            case 'L':
                //load
                break;
            // move avatar
            case 'W':
                moveAvatar(world, posOfAvatar.getX(), posOfAvatar.getY() + 1);
                break;
            case 'S':
                moveAvatar(world, posOfAvatar.getX(), posOfAvatar.getY() - 1);
                break;
            case 'A':
                moveAvatar(world, posOfAvatar.getX() - 1, posOfAvatar.getY());
                break;
            case 'D':
                moveAvatar(world, posOfAvatar.getX() + 1, posOfAvatar.getY());
                break;
        }


    }

    private void showTheWorld(TETile[][] grid) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(grid);
    }

    private void getSeed(InputSource input) {
        StringBuilder seedBuilder = new StringBuilder();
        while (input.possibleNextInput()) {
            char c = Character.toUpperCase(input.getNextKey());
            if (c == 'N') {
                continue;
            }
            if (c!= 'S') {
                seedBuilder.append(c);
            } else {
                this.seed = Long.parseLong(seedBuilder.toString());

                return;
            }
        }
    }

    // add interactivity to an avatar
    private void moveAvatar(TETile[][] grid, int xPos, int yPos) {
        if (grid[xPos][yPos] == Tileset.FLOOR) {
            Position moveTo = new Position(xPos, yPos);
            grid[posOfAvatar.getX()][posOfAvatar.getY()] = Tileset.FLOOR;
            grid[moveTo.getX()][moveTo.getY()] = Tileset.AVATAR;
            this.posOfAvatar = moveTo;
        }
    }

}
