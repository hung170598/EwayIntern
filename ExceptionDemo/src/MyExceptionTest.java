public class MyExceptionTest {
    public static void main(String[] args) {
        double canh_1 = 3;
        double canh_2 = 2;
        double canh_3 = 5;

        try{
            int loaiTamGiac = timLoaiTamGiac(canh_1, canh_2, canh_3);
            switch (loaiTamGiac){
                case 1:{
                    System.out.println("Tam giac deu");
                    break;
                }
                case 2:{
                    System.out.println("Tam giac can");
                    break;
                }
                case 3:{
                    System.out.println("Tam giac vuong");
                    break;
                }
                case 4:{
                    System.out.println("Tam giac thuong");
                    break;
                }
            }
        }
        catch (MyException e){
            System.out.println(e.getMessage());
            System.out.println("Hay kiem tra lai 3 canh");
        }
    }

    static int timLoaiTamGiac(double canh_1, double canh_2, double canh_3) throws MyException{
        if((canh_1 + canh_2 <= canh_3) || (canh_2 + canh_3 <= canh_1) || (canh_3 + canh_1 <= canh_2)){
            throw new MyException("Day khong phai 3 canh tam giac");
        }
        else if (canh_1 == canh_2 && canh_2 == canh_3){
            return 1;
        }
        else if (canh_1 == canh_2 || canh_2 == canh_3 || canh_3 == canh_1){
            return 2;
        }
        else if(canh_1*canh_1 + canh_2*canh_2 == canh_3*canh_3 || canh_2*canh_2 + canh_3*canh_3 == canh_1*canh_1
                || canh_3*canh_3 + canh_1*canh_1 == canh_2*canh_2){
            return 3;
        }
        else {
            return 4;
        }
    }
}
