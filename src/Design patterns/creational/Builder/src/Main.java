public class Main {
    public static void main(String[] args) {
        Director dr1 = new Director(new EngineeringStudentBuilder());
        Director dr2 = new Director(new MBAStudentBuilder());

        Student engineeringStudent = dr1.createStudent();
        Student mbaStudent = dr2.createStudent();

        System.out.println(engineeringStudent.toString());
        System.out.println(mbaStudent.toString());
    }
}