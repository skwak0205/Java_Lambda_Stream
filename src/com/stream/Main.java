package com.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();


        // Imperative approach (옛날 방식)
        /*
        List<Person> females = new ArrayList<>();

        for (Person person : people) {
            if(person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }

        females.forEach(System.out::println);

         */

        // Declarative approach (Stream 사용)

        // 1. Filter
        List<Person> females = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList());

        females.forEach(System.out::println);

        // 2. Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());

        List<Person> reversed = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
        reversed.forEach(System.out::println);

        // 3. All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 5);

        System.out.println(allMatch);

        // 4. Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 8);

        System.out.println(anyMatch);

        // 5. None match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("kim"));

        System.out.println(noneMatch);

        // 6. Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // 7. Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        /*
        * max()와 min()은 Optional을 리턴하기 때문에 ifPresent() 사용 가능
        * */

        // 8. Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);

            System.out.println();
        });

        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
