public class Student {
    int id;
    String name;
    String studentCode;

    public Student(int id, String name, String studentCode) {
        this.id = id;
        this.name = name;
        this.studentCode = studentCode;
    }

    @Override
    protected Object clone() {
        Student student = new Student(this.id, this.name, this.studentCode);
        return student;
    }

    @Override
    public String toString() {
        return String.valueOf(id) + "; " + name + "; " + studentCode;
    }
}
