package com.footyverse.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.footyverse.app.model.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{

    
}
