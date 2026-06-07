package com.moments.core.security.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    public UUID id;

}
