package com.shop.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.shop.demo.Service.CompService;
import com.shop.demo.entity.CompEntity;
import com.shop.demo.request.UpdateCompRequest;
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/computer")
public class CompController {

    @Autowired
    private CompService compService;

    // Добавление компьютера для текущего пользователя
    @PostMapping("/add")
    public CompEntity addComputer(@RequestBody CompEntity computer) {
        return compService.addComputerForCurrentUser(computer);
    }

    // Получение всех компьютеров текущего пользователя
    @GetMapping("/my-computers")
    public List<CompEntity> getUserComputers() {
        return compService.getCurrentUserComputers();
    }
    @GetMapping("/{id}")
    public CompEntity getComputerById(@PathVariable int id) {
        return compService.getComputerById(id);
    }
    @DeleteMapping("/delete/{computerId}")
    public ResponseEntity<String> deleteComputer(@PathVariable Integer computerId) {
        System.out.println("Delete request received for computer ID: " + computerId);
        compService.deleteCurrentUserComputer(computerId);
        return ResponseEntity.ok("Computer deleted successfully");
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateComputer(@PathVariable int id, @RequestBody UpdateCompRequest comp) {
        // Установим ID в переданный объект
        comp.setId(id);
        compService.update(comp);
        return ResponseEntity.noContent().build(); // Возвращаем 204 No Content
    }

    // Получение всех компьютеров в системе (для раздела Shop)
    @GetMapping("/all")
    public List<CompEntity> getAllComputers() {
        return compService.getAllComputers();
    }
}