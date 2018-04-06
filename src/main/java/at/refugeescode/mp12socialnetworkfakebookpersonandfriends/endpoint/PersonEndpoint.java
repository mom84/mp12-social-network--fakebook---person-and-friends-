package at.refugeescode.mp12socialnetworkfakebookpersonandfriends.endpoint;


import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.Friend;
import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.FriendsReopsitory;
import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.Person;
import at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonEndpoint {

    private PersonRepository personRepository;
    private FriendsReopsitory friendsReopsitory;

    public PersonEndpoint(PersonRepository personRepository, FriendsReopsitory friendsReopsitory) {
        this.personRepository = personRepository;
        this.friendsReopsitory=friendsReopsitory;
    }

    @GetMapping("/persons")
    List<Person> gatAllPersons(){
      return personRepository.findAll();
    }

    @GetMapping("/friends")
    List<Friend> getAllFriends(){
        return friendsReopsitory.findAll();
    }

    @GetMapping("/persons/{id1}/friend/{id2}")
    void setFriend(@PathVariable Long id1 ,@PathVariable Long id2){
        Optional<Person>  person = personRepository.findById(id1);
        Optional<Friend> friend = friendsReopsitory.findById(id2);
        System.out.println(friend.get());


        if(person.isPresent() && friend.isPresent()){

            String nameOfNewFriend = friend.get().getName();
            Friend newFriend = friendsReopsitory.save(new Friend(nameOfNewFriend));

            List<Friend> friends = person.get().getFriends();
            friends.add(newFriend);
            person.get().setFriends(friends);
            personRepository.save(person.get());

        }

    }
}
