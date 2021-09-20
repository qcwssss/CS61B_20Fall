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
    private boolean isStart;
    private boolean haveKey;
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

        showTheWorld();
        drawStartMenu();
        gameOver = false;

        while (!gameOver) {
            char action = keySource.getNextKey();
            processInput(keySource, action);
            ter.renderFrame(world);
            drawHelperUI();
        }

        showEndMessage();
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
                isStart = true;
                break;
            case ':':
                if (input.possibleNextInput()){
                    if (input.getNextKey() == 'Q' && !gameOver) {
                      // save and exit
                        savedString.deleteCharAt(savedString.length() - 1);
                        //System.out.println("saving: " + savedString);
                        saveGame(savedString.toString());

                        System.exit(0);
                    }
                }
                break;
            case 'L': case 'R':
                //load
                showSavedWorld();
                break;
            case 'Q':
                if (!isStart) {
                    System.exit(0);
                }

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
            default:
                break;
        }
    }

    private void showSavedWorld() {
        savedString.deleteCharAt(savedString.length() - 1);
        String savedRecord = loadGame();
        if (savedRecord == "") {
            System.exit(0);
        } else {
            world = interactWithInputString(savedRecord);
            ter.renderFrame(world);
        }
    }

    private void showTheWorld() {
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

        String haveKey = this.haveKey ? "key" : "none";
        StdDraw.textLeft(2, uiY, "Items: " + haveKey);

        String hint = this.haveKey ? "Unlock the door and get out of here!" : "Find the key!";
        StdDraw.text(WIDTH/2, uiY, hint);

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
            os.writeObject(this.savedString);

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
                String loaded = os.readObject().toString();
                //System.out.println("loading: " + loaded);
                return loaded;

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
        StdDraw.text(midX, midY * 4 /3, "BYOW: Escape The Dungeon");
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
     * Add interactivity to the avatar.
     * @param grid world grid
     * @param xPos x coordinate of destination
     * @param yPos y coordinate of destination
     */
    private void moveAvatar(TETile[][] grid, int xPos, int yPos) {
        if (grid[xPos][yPos] == Tileset.LOCKED_DOOR) {
            if (this.haveKey) {
                grid[xPos][yPos] = Tileset.UNLOCKED_DOOR;
                StdDraw.pause(500);
            }
        } else if (grid[xPos][yPos] == Tileset.UNLOCKED_DOOR) {
            gameOver = true;
        }

        if (grid[xPos][yPos] == Tileset.FLOOR|| grid[xPos][yPos] == Tileset.KEY) {
            if (grid[xPos][yPos] == Tileset.KEY) {
                haveKey = true;
            }
            Position moveTo = new Position(xPos, yPos);
            grid[posOfAvatar.getX()][posOfAvatar.getY()] = Tileset.FLOOR;
            grid[moveTo.getX()][moveTo.getY()] = Tileset.AVATAR;
            this.posOfAvatar = moveTo;
        }
    }

    private void showEndMessage() {
        drawSentence("Congratulations, you made it!");
        drawSentence("The sequel: Back To The Dungeon");
        drawSentence("Coming Soon In 2077");
        drawFrame("Thanks For Playing!");
    }

    private void drawSentence(String s) {
        drawFrame(s);
        StdDraw.pause(1500);
    }


}
