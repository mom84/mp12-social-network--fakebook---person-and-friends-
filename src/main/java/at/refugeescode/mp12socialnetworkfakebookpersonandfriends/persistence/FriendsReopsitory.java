package at.refugeescode.mp12socialnetworkfakebookpersonandfriends.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendsReopsitory extends JpaRepository<Friend,Long> {

    Optional<Friend> findById(Long aLong);
}
