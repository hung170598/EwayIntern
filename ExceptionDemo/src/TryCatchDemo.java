public class TryCatchDemo {
    public static void main(String[] args) {
        int x = 15;
        int y = 0;
        try{
            System.out.println("Truoc exception");
            System.out.println("x/y = " + x/y);
            System.out.println("Sau exception");
        } catch(ClassCastException e){
            e.printStackTrace();
        } finally {
            System.out.println("Finally");
        }
        System.out.println("Sau try-catch");
    }
}
