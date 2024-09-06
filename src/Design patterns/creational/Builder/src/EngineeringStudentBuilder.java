import java.util.ArrayList;
import java.util.List;

public class EngineeringStudentBuilder extends StudentBuilder {

    @Override
    public StudentBuilder setSubjects() {
        List<String> subjects = new ArrayList<>();
        subjects.add("Computer Science");
        subjects.add("CT");
        subjects.add("Mathematics");

        this.subjects = subjects;
        return this;
    }
}
