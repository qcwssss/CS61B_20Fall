package byow.Core;

import byow.Core.Input.InputSource;
import byow.Core.Input.KeyboardInputSource;
import byow.Core.Input.StringInputDevice;
import byow.Core.Map.MapGenerator;
import byow.Core.Map.Position;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    private static final int TILE_SIZE = 16;

    private Position posOfAvatar;
    private boolean gameOver;
    private long seed;
    private TETile[][] world;
    private InputSource source;
    private StringBuilder savedString = new StringBuilder();


    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        InputSource keySource = new KeyboardInputSource();
        this.world = MapGenerator.buildEmptyMap(WIDTH, HEIGHT);

        showTheWorld(world);
        drawStartMenu();

        while (!gameOver) {
            char action = keySource.getNextKey();
            processInput(keySource, action);
            ter.renderFrame(world);
            drawHelperUI();

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

        //showTheWorld(world);
        //ter.renderFrame(world);
        return world;
    }

    /**
     * Take actions based on input.
     * @param input input key
     * @param action types of action
     */
    private void processInput(InputSource input, char action) {
        action = Character.toUpperCase(action);
        savedString.append(action);
        switch (action) {
            case 'N':
                getSeed(input);
                savedString.append(this.seed).append('S');

                MapGenerator map = new MapGenerator(new Random(this.seed), world);
                posOfAvatar = map.getAvatarPos();
                break;
            case ':':
                if (input.possibleNextInput()){
                    if (input.getNextKey() == 'Q') {
                      // save and exit
                        savedString.deleteCharAt(savedString.length() - 1);
                        System.out.println(savedString);
                        saveGame(savedString.toString());

                        System.exit(0);
                    }
                }
                break;
            case 'L':
                //load
                loadGame();
                TETile[][] board = MapGenerator.buildEmptyMap(WIDTH, HEIGHT);
                MapGenerator worldGen = new MapGenerator(new Random(this.seed), board);
                showTheWorld(world);
                drawStartMenu();

                while (!gameOver) {
                    char action = keySource.getNextKey();
                    processInput(keySource, action);
                    ter.renderFrame(world);
                    drawHelperUI();

                }

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
            default :
                break;
        }

    }

    private void showTheWorld(TETile[][] grid) {
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
    }

    private void getSeed(InputSource input) {
        StringBuilder seedBuilder = new StringBuilder();
        drawInputSeedGuide("");

        while (input.possibleNextInput()) {
            char c = Character.toUpperCase(input.getNextKey());

            if (Character.isDigit(c)) {
                seedBuilder.append(c);
                drawInputSeedGuide(seedBuilder.toString());

            } else if (c == 'S' && !seedBuilder.isEmpty()){
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

        resetFontToOriginal();
        StdDraw.show();
    }

    private void drawInputSeedGuide(String s) {
        int midX = WIDTH / 2;
        int midY = HEIGHT / 2;
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midX, midY, "Please input a seed, press 'S' to confirm");
        StdDraw.text(midX, midY - 2, s);

        resetFontToOriginal();
        StdDraw.show();

    }

    /**
     * If game is not over, display relevant game information at the top of the screen.
     */
    private void drawHelperUI(){
        int uiY = HEIGHT - 1;
        Position mousePos = new Position((int)StdDraw.mouseX(), (int)StdDraw.mouseY());
        Font uiFont = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(uiFont);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.textLeft(2, uiY, "Items: nothing ");
        StdDraw.textRight(WIDTH - 2, uiY, "TileInfo: " + showTileInfo(mousePos));
        StdDraw.line(0, uiY - 1, WIDTH, uiY - 1);

        resetFontToOriginal();
        StdDraw.show();

    }

    private void saveGame(String saved) {
        File f = new File("./save.txt");
        try {

            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this.posOfAvatar);
            os.writeObject(this.seed);
            os.close();
            fs.close();
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            }

    }

    private String loadGame() {
        File f = new File("./save.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                this.posOfAvatar = (Position) os.readObject();
                this.seed = (Long) os.readObject();

                //return (String) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no file has been saved yet, we return an empty string. */
        return "";

    }

    private void drawStartMenu() {
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

    private String showTileInfo(Position mouse) {
        String info = this.world[mouse.getX()][mouse.getY()].description();
        return info;


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
