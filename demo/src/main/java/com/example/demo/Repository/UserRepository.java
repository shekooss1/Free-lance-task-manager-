package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.User;
import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User,Long> {    

    public boolean existsByName(String name);

  Optional<User> findByName(String username);

}
