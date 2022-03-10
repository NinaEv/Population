package javacore.task_2_2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long YoungPeople = persons.stream()
                .filter((p) -> p.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних " + YoungPeople);

        List<String> ReadyForArmy = persons.stream()
                .filter((m) -> m.getAge() >= 18 && m.getAge() <= 27 && m.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников " + ReadyForArmy);

        List<Person> ableToWork = persons.stream()
                .filter((x) -> x.getAge() >= 18 && x.getAge() <= 65 && x.getEducation() == Education.HIGHER)
                .filter((v) -> v.getSex() == Sex.MAN || v.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Список потенциально работоспособных людей с высшим образованием  " + ableToWork);
    }
}
