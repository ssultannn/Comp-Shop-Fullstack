package com.shop.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.demo.entity.CompEntity;
import com.shop.demo.entity.User;
@Repository
public interface CompRepo extends JpaRepository<CompEntity, Integer>{

	List<CompEntity> findAllByOwner(User user);

}
