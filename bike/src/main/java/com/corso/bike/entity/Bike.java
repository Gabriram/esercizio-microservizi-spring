package com.corso.bike.entity;

import com.corso.bike.entity.Bike;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "bikes")
@Data
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand; // es. Ducati

    @NotBlank
    private String model; // es. Panigale V4

    @Min(50)
    private Integer eng_cc; // cilindrata in cc

    @NotBlank
    private String type; // sport, naked, enduro, scooter...

    @Min(1900)
    private Integer year;

    @Min(0)
    private Double price;

}