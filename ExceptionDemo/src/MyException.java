public class MyException extends Exception {
    public MyException() {
        super("Co exception");
    }

    public MyException(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
