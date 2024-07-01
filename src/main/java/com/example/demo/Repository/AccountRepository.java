package com.example.demo.Repository;

import com.example.demo.entity.DemoAccount;
import com.example.demo.entity.DemoUser;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<DemoAccount, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<DemoAccount> findByUser(DemoUser user);
}
