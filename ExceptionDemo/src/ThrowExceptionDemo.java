public class ThrowExceptionDemo {
    public static void main(String[] args) {
        double x = 15.0;
        double y = 0.0;
        try{
            System.out.println("Truoc exception");
            System.out.println("x/y = " + divide(x,y));
            System.out.println("Sau exception");
        }
//        catch(ClassCastException e){
//            e.printStackTrace();
//      }
        finally {
            System.out.println("Finally");
        }
        System.out.println("Sau try-catch");


    }

    static double divide(double a, double b){
        if(b == 0.0){
            throw new ArithmeticException("divide by zero");
        } else {
            return a/b;
        }
    }
}
