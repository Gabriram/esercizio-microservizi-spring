package com.corso.cars.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cars", schema = "autodb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String fuelType;

    @Min(2)
    private Integer doors;

    @Min(2)
    private Integer seats;

    @Min(1900)
    private Integer year;

    @Min(0)
    private Double price;

}
