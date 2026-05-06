package com.corso.garage.entities;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import java.util.List;
import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;

@Entity
@Data
@Table(name = "garages", schema = "garages")
@Builder
public class Garage {

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
