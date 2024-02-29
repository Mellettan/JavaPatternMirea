package pract_2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Human {
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        return "Human{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                '}';
    }

    public static void main(String[] args) {
        List<Human> humans = new ArrayList<>();

        humans.add(new Human(34, "John", "Doe", LocalDate.of(1990, 1, 1), 70));
        humans.add(new Human(23, "Jane", "Doe", LocalDate.of(2000, 6, 15), 50));
        humans.add(new Human(45, "Bob", "Smith", LocalDate.of(1978, 12, 31), 80));
        humans.add(new Human(11, "Alice", "Johnson", LocalDate.of(2012, 3, 10), 60));
        humans.add(new Human(19, "Mike", "Brown", LocalDate.of(2004, 7, 22), 90));

        // Сортировка по возрасту
        System.out.println("Сортировка по возрасту: ");
        List<Human> sortedByAge = humans.stream()
                .sorted(Comparator.comparingInt(Human::getAge))
                .toList();
        sortedByAge.forEach(System.out::println);
        System.out.print('\n');

        // Фильтрация по возрасту меньше, чем 20
        System.out.println("Фильтрация по возрасту меньше, чем 20: ");
        List<Human> ageLessThan20 = humans.stream()
                .filter(h -> h.getAge() < 20)
                .toList();
        ageLessThan20.forEach(System.out::println);
        System.out.print('\n');

        // Фильтрация по имени «содержит ‘е’»
        System.out.println("Фильтрация по имени «содержит ‘е’»: ");
        List<Human> nameContainsE = humans.stream()
                .filter(h -> h.getFirstName().contains("e"))
                .toList();
        nameContainsE.forEach(System.out::println);
        System.out.print('\n');

        // Конкатенация первых букв имен
        System.out.println("Конкатенация первых букв имен: ");
        String concatenatedInitials = humans.stream()
                .map(h -> h.getFirstName().substring(0, 1))
                .collect(Collectors.joining());
        System.out.println(concatenatedInitials);
    }
}
