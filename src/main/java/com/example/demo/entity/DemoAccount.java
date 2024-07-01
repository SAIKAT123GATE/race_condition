package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "demo_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemoAccount extends BaseEntity{
    @Id
    @Column(name = "account_id", nullable = false)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private DemoUser user;
    private BigDecimal balance;
}
