package misc.customImmutableClass;

import java.util.ArrayList;
import java.util.List;

// final class: Prevents subclassing
public final class ImmutablePerson {
    // 1. Private final fields
    // State cannot be modified after construction
    private final String name;
    private final int age;
    private final List<String> hobbies; // Mutable object

    // 2. Constructor initializes all fields
    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // 3. Defensive copy for mutable objects
        // Prevents external modification of passed collections
        this.hobbies = new ArrayList<>(hobbies);
    }

    // 4. No setters (only getters): State cannot be changed after instantiation

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // 5. Return defensive copies for mutable objects
    // Prevents modification of internal state through returned reference
    public List<String> getHobbies() {
        return new ArrayList<>(hobbies);
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                '}';
    }
}
