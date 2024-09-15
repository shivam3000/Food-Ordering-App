package com.foodorderBackend.foodbackend.Request;

import java.time.LocalDateTime;
import java.util.List;

import com.foodorderBackend.foodbackend.Model.Address;
import com.foodorderBackend.foodbackend.Model.ContactInformation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantRequest {
	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;
    private LocalDateTime registrationDate;
}
