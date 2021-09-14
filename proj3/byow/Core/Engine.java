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
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    private static final int TILE_SIZE = 16;

    private Position posOfAvatar;
    private long seed;
    private TETile[][] world;
    private InputSource source;
    private boolean isGameOver;


    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        source = new KeyboardInputSource();
        this.world = MapGenerator.buildEmptyMap(WIDTH, HEIGHT);
        isGameOver = false;

        showTheWorld(world);
        drawStartMenu();

        while (!isGameOver) {
            char action = source.getNextKey();
            processInput(source, action);
            //showTheWorld(world);
            ter.renderFrame(world);
        }

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
        ter.renderFrame(world);

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
                    if (input.getNextKey() == 'Q') {
                      // save and exit
                        System.exit(0);
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
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        //ter.renderFrame(grid);
    }

    private void getSeed(InputSource input) {
        StringBuilder seedBuilder = new StringBuilder();
        while (input.possibleNextInput()) {
            char c = Character.toUpperCase(input.getNextKey());
            if (c != 'S') {
                seedBuilder.append(c);
                drawFrame(seedBuilder.toString());
            } else {
                this.seed = Long.parseLong(seedBuilder.toString());

                return;
            }
        }
    }

    private void drawFrame(String s) {
        int midX = WIDTH / 2;
        int midY = HEIGHT / 2;
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midX, midY, s);


        //TODO: If game is not over, display relevant game information at the top of the screen
        int uiY = HEIGHT - 1;
        if (!isGameOver) {
            Font uiFont = new Font("Monaco", Font.ITALIC, 20);
            StdDraw.setFont(uiFont);
            //StdDraw.textLeft(2, uiY, "Keys: " + numOfKey);
            //StdDraw.textRight(WIDTH - 2, uiY, tileInfo);
            StdDraw.line(0, uiY-1, WIDTH, uiY - 1);
        }

        resetFontToOriginal();
        StdDraw.show();

    }

    /**
     * Display each character in letters, blank the screen between letters
     * @param letters input seed
     */
    private void flashSequence(String letters) {
        int second = 200;
        char[] charsOfLetter = letters.toCharArray();
        for (int i = 0 ; i< charsOfLetter.length; i++) {
            String single = String.valueOf(letters.charAt(i));
            drawFrame(single);

            StdDraw.pause(second);
            StdDraw.clear();
            StdDraw.pause(second);
        }
    }

    void drawStartMenu() {
        int midX = WIDTH / 2;
        int midY = HEIGHT / 2;
        // title
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midX, midY * 4 /3, "CS61B: Zelda Game");
        // menu options
        Font menuFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(menuFont);
        String[] options = new String[] {"New Game (N)", "Load Game (L)", "Quit (Q)", "Replay (R)"};
        for (int i = 0; i < options.length; i++) {
            StdDraw.text(midX, midY - 2 * i, options[i]);
        }

        // Reset font size to original tile size.
        resetFontToOriginal();

        StdDraw.show();

    }

    private void resetFontToOriginal() {
        Font font = new Font("Monaco", Font.BOLD, TILE_SIZE - 2);
        StdDraw.setFont(font);
    }

    /**
     * Read n letters of player input
     * @param n number of chars
     * @return input as a string
     */
    private String solicitNCharsInput(int n) {
        String input = "";
        drawFrame(input);

        while(input.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                input += key;
                drawFrame(input);
            }
        }
        StdDraw.pause(500);
        return input;
    }

    /*
    private void startGame() {
        //TODO: Set any relevant variables before the game starts
        this.round = 1;
        this.gameOver = false;

        String target, input;
        //TODO: Establish Engine loop
        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round: " + round);
            StdDraw.pause(1500);

            target = generateRandomString(round);
            flashSequence(target);

            playerTurn = true;
            input = solicitNCharsInput(round);
            if (input.equals(target)) {
                drawFrame("Correct, good job!");
                StdDraw.pause(1500);
                round += 1;
            } else {
                gameOver = true;
                String gameOver = "Game Over! Final round score:" + round;
                drawFrame(gameOver);

            }
        }
    }

     */

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
