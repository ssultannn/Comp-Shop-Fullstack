package com.shop.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}

