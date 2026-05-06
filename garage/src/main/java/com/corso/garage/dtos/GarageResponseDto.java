package com.corso.garage.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import com.corso.garage.entities.Vehicle;

@Data
@Builder
public class GarageResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ownerName;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles = new ArrayList<>();

}
