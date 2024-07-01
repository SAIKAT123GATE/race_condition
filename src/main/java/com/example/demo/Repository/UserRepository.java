package com.example.demo.Repository;

import com.example.demo.entity.DemoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DemoUser, String> {

}
