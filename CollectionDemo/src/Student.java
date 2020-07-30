public class Student{
    int id;
    String name;
    String studentCode;

    public Student(int id, String name, String studentCode) {
        this.id = id;
        this.name = name;
        this.studentCode = studentCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentCode() {
        return studentCode;
    }
}
