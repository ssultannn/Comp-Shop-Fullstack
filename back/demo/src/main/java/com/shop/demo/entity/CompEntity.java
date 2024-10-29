package com.shop.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comp")
public class CompEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String category;
	private String name;
	private double price;
	private int ram;
	private int storage;
	private String storageType;
	private String operatingSystem;
	private String graphicsCard;
	private String description;
	private String image;
	 @ManyToOne
	    @JoinColumn(name = "owner_id")
	    private User owner;

}
