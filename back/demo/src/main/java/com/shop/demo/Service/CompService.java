package com.shop.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.shop.demo.Repository.CompRepo;
import com.shop.demo.Repository.UserRepository;
import com.shop.demo.entity.CompEntity;
import com.shop.demo.entity.User;
import com.shop.demo.request.UpdateCompRequest;

import jakarta.transaction.Transactional;
@Service
public class CompService {
	 @Autowired
	    private CompRepo computerRepository;

	    @Autowired
	    private UserService userService; // Сервис для получения текущего пользователя

	    // Добавление компьютера для текущего пользователя
	    public CompEntity addComputerForCurrentUser(CompEntity computer) {
	        User currentUser = userService.getCurrentUser();
	        computer.setOwner(currentUser);
	        return computerRepository.save(computer);
	    }

	    // Получение компьютеров текущего пользователя
	    public List<CompEntity> getCurrentUserComputers() {
	        User currentUser = userService.getCurrentUser();
	        return computerRepository.findAllByOwner(currentUser);
	    }

	    // Удаление компьютера текущего пользователя
	    public void deleteCurrentUserComputer(Integer computerId) {
	        CompEntity computer = computerRepository.findById(computerId).orElseThrow(() -> new RuntimeException("Computer not found"));
	        User currentUser = userService.getCurrentUser();

	        if (computer.getOwner().equals(currentUser)) {
	            computerRepository.delete(computer);
	        } else {
	            throw new RuntimeException("User not authorized to delete this computer");
	        }
	    }
	    @Transactional
	    public void update(UpdateCompRequest comp) {
	        Integer id = comp.getId(); // Получаем ID компьютера
	        CompEntity c = computerRepository.findById(id)
	            .orElseThrow();

	        // Обновляем поля
	        c.setCategory(comp.getCategory());
	        c.setName(comp.getName());
	        c.setPrice(comp.getPrice());
	        c.setRam(comp.getRam());
	        c.setStorage(comp.getStorage());
	        c.setStorageType(comp.getStorageType());
	        c.setOperatingSystem(comp.getOperatingSystem());
	        c.setGraphicsCard(comp.getGraphicsCard());
	        c.setDescription(comp.getDescription());
	        c.setImage(comp.getImage());

	        // Сохраняем обновленную сущность
	        computerRepository.save(c);
	    }


	    // Получение всех компьютеров в системе (для раздела Shop)
	    public List<CompEntity> getAllComputers() {
	        return computerRepository.findAll();
	    }

	    public CompEntity getComputerById(int id) {
	        return computerRepository.findById(id).orElseThrow(() -> new RuntimeException("Computer not found"));
	    }
	}