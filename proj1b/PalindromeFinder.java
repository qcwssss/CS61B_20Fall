
/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-fa20/data/words.txt");
        Palindrome palindrome = new Palindrome();
        OffByOne offByOne = new OffByOne();

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }
        }
        System.out.println("Palindromes printed.\n");


        // Off by one
        In in1 = new In("../library-fa20/data/words.txt");

        System.out.println("Palindromes off by one: ");
        while (!in1.isEmpty()) {
            String word = in1.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offByOne)) {
                System.out.println(word);
                //System.out.println("There's nothing left");

            }
        }

    }
}
