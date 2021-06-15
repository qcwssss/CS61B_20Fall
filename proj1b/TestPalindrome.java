import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("racecar") );
        assertTrue(palindrome.isPalindrome("noon") );
        assertTrue(palindrome.isPalindrome("tenet") );
        // 1 or 0 character are all palindrome
        assertTrue(palindrome.isPalindrome("a") );
        assertTrue(palindrome.isPalindrome("") );

        assertFalse(palindrome.isPalindrome("dance") );
        assertFalse(palindrome.isPalindrome("dance") );
        assertFalse(palindrome.isPalindrome("horse") );

    }

    @Test
    public void testIsPalindromeOverload() {
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("abab", offByOne));

        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));

        assertFalse(palindrome.isPalindrome("noon", offByOne));
        assertFalse(palindrome.isPalindrome("tenet", offByOne));

    }
}
