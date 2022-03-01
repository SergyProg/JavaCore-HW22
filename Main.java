package com.javacore.hw22;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Gender.values()[new Random().nextInt(Gender.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long minorsCount = persons.stream()
                                 .filter(person -> person.getAge() <= 18)
                                 .count();
        List<String> conscriptsList = persons.stream()
                                             .filter(person -> person.getGender() == Gender.MAN)
                                             .filter(person -> (person.getAge() >= 18 && person.getAge() <= 27))
                                             .map(person -> person.getFamily())
                                             .collect(Collectors.toList());
        List<String> workersManList = persons.stream()
        .filter(person -> (( person.getGender() == Gender.MAN) && (person.getAge() >= 18 && person.getAge() <= 65))
                        || ((person.getGender() == Gender.WOMAN) && (person.getAge() >= 18 && person.getAge() <= 60)))
                                             .map(person -> person.getFamily())
                                             .sorted(Comparator.naturalOrder())
                                             .collect(Collectors.toList());

        System.out.println("Количество несовершеннолетних: " + minorsCount);
        System.out.println("Список призывников: ");
        conscriptsList.stream().forEach(x -> System.out.print(x + " "));
        System.out.println("");
        System.out.println("Трудоспособные работники с высшим образованием: ");
        workersManList.stream().forEach(x -> System.out.print(x + " "));

    }
}
