package com.corso.garage.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import com.corso.garage.entities.Vehicle;

@Data
@Builder
public class GarageModifyDataDto {

    @NotBlank
    private String ownerName;

    @NotBlank
    private String address;

    @OneToMany(mappedBy = "garage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles = new ArrayList<>();

}