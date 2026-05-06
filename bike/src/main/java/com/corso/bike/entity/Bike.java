package com.corso.bike.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bikes", schema = "bikesdb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;
    @Min(50)
    @Column(name = "eng_cc", nullable = false)
    private Integer engCc;

    @NotBlank
    private String type;

    @Min(1900)
    private Integer year;

    @Min(0)
    private Double price;

}