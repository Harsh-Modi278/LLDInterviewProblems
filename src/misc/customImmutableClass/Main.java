package misc.customImmutableClass;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> originalHobbies = new ArrayList<>();
        originalHobbies.add("Reading");

        ImmutablePerson person = new ImmutablePerson("Alice", 30, originalHobbies);

        // Original list modification doesn't affect person
        originalHobbies.add("Hiking");

        // Getter returns copy, so modification doesn't affect internal state
        person.getHobbies().add("Swimming");

        System.out.println(person);
        // Output: ImmutablePerson{name='Alice', age=30, hobbies=[Reading]}
    }
}
