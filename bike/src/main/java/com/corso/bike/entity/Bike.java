package com.corso.bike.entity;

import com.corso.bike.entity.Bike;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "bikes")
@Data
@Builder

public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String brand; // es. Ducati

    @NotBlank
    private String model; // es. Panigale V4

    @Min(50)
    @Column(name = "eng_cc", nullable = false)
    private Integer engCc; // cilindrata in cc

    @NotBlank
    private String type; // sport, naked, enduro, scooter...

    @Min(1900)
    private Integer year;

    @Min(0)
    private Double price;

    public static Bike fromDto(com.corso.bike.dtos.BikeDto dto) {
        return Bike.builder()
                .id(dto.getId())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .engCc(dto.getEng_cc())
                .type(dto.getType())
                .year(dto.getYear())
                .price(dto.getPrice())
                .build();
    }

}