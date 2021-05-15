/**
 * Test the Body file.
 */

public class TestBody{
    /**
     * test the Body to make sure it's working correctly.
     */
     public static void main(String[] args){
         checkBody();
     }
    
    private static void checkBody(){
        System.out.println("Checking Body...");

        Body test1 = new Body(1.0, 2.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body test2 = new Body(3.0, 4.0, 5.0, 6.0, 7.0, "jupiter.gif");

        System.out.println(test1.calcForceExertedBy(test2));

    }


}