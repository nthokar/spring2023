package com.nthokar.spring2023.main.infrastructure;

import com.nthokar.spring2023.main.application.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, String> {
}
