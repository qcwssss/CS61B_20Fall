package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        char[] randChars = new char[n];
        for (int i = 0; i < n; i++) {
            randChars[i] = CHARACTERS[rand.nextInt(26)];
        }
        return new String(randChars);
    }

    public void drawFrame(String s) {
        int midX = width / 2;
        int midY = height / 2;
        //TODO: Take the string and display it in the center of the screen
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(midX, midY, s);

        //TODO: If game is not over, display relevant game information at the top of the screen
        int uiY = height - 2;
        if (!gameOver) {
            Font uiFont = new Font("Monaco", Font.ITALIC, 20);
            StdDraw.setFont(uiFont);
            StdDraw.textLeft(2, uiY, "Round: " + round);
            StdDraw.text(midX, uiY,this.playerTurn ? "Type!" : "Watch!");
            StdDraw.textRight(width - 2, uiY, ENCOURAGEMENT[rand.nextInt(7)]);
            StdDraw.line(0, uiY-1, width, uiY - 1);
        }

        StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        int second = 1000;
        char[] charsOfLetter = letters.toCharArray();
        for (int i = 0 ; i< charsOfLetter.length; i++) {
            String single = String.valueOf(letters.charAt(i));
            drawFrame(single);

            StdDraw.pause(second/2);
            StdDraw.clear();
            StdDraw.pause(second / 2);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
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

    public void startGame() {
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

}
