package com.toyproject.springbootsimpleboard1.Repository;

import com.toyproject.springbootsimpleboard1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
