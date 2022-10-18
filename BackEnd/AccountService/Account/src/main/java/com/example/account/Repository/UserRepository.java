package com.example.account.Repository;

import com.example.account.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

    //update methods/query

    // update emails, keep in mind that the new email must be unique
    // i.e. the new email m ust not exist in doctor, user and admin table
    @Modifying
    @Query("update User u set u.email = :newEmail where u.email = :email")
    @Transactional
    void updateEmail(String email, String newEmail);

    // update gender
    @Modifying
    @Query("update User u set u.gender = :gender where u.email = :email")
    @Transactional
    void updateGender(String email, String gender);

    // update first name
    @Modifying
    @Query("update User u set u.name = :name where u.email = :email")
    @Transactional
    void updateName(String email, String name);

    // update surname
    @Modifying
    @Query("update User u set u.surname = :surname where u.email = :email")
    @Transactional
    void updateSurname(String email, String surname);

    // update password
    @Modifying
    @Query("update User u set u.password = :password where u.email = :email")
    @Transactional
    void updatePassword(String email, String password);
}
