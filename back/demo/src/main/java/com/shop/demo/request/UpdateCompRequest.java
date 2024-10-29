package com.shop.demo.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class UpdateCompRequest {
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
}
