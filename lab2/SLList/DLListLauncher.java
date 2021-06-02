public class DLListLauncher{
    public static void main(String[] args) {
        DLList<String> s1 = new DLList<> ("bone");
        s1.addFirst("flesh");

        System.out.println( s1.getFirst());
    }
}