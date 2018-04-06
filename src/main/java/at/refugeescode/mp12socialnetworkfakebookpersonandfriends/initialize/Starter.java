package at.refugeescode.mp12socialnetworkfakebookpersonandfriends.initialize;


import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.Friend;
import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.FriendsReopsitory;
import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.Person;
import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.PersonRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class Starter {


    @Bean
    ApplicationRunner applicationRunner(PersonRepository personRepository, FriendsReopsitory friendsReopsitory){
        return args -> {
            personRepository.deleteAll();

            List<Person> persons = createPersons();
            persons.stream().map(person -> person.getFriends())
                    .forEach(friends -> friendsReopsitory.saveAll(friends));
            personRepository.saveAll(persons);

            personRepository.findAll().forEach(System.out::println);
            System.out.println("-------------------------------");
            friendsReopsitory.findAll().forEach(System.out::println);
        };
    }

    public List<Person> createPersons(){
        return Stream.of(new Person("Mohammad" ,
                                 Arrays.asList(new Friend("Diaa"),new Friend("Dahman"))),
                         new Person("Ismael" ,
                                 Arrays.asList(new Friend("Mohammad"),new Friend("Dahman"))))
                .collect(Collectors.toList());
    }

}
