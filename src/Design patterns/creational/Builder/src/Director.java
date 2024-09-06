public class Director {
    StudentBuilder sb;

    Director(StudentBuilder sb) {
        this.sb = sb;
    }

    public Student createStudent() {
        if (sb instanceof EngineeringStudentBuilder) {
            return createEngineeringStudent();
        }
        return createMBAStudent();
    }

    private Student createEngineeringStudent() {
        return sb.setRollNumber(1).setAge(20).setName("Harsh").setFatherName("Parag").setMotherName("Reshma").setSubjects().build();
    }

    private Student createMBAStudent() {
        return sb.setRollNumber(2).setAge(26).setName("Gojo").setFatherName("Satorue").setMotherName("egegge").setSubjects().build();
    }
}
