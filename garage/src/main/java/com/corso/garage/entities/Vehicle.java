package com.corso.garage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.Id;

@Entity
@Data
@Table(name = "vehicles", schema = "vehicles")
@Builder

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long externalId; // id della bike o della car

    @NotBlank
    private String vehicleType; // "BIKE" o "CAR"

    @ManyToOne
    @JoinColumn(name = "garage_id")
    @JsonIgnore // evita ciclo di serializzazione
    private Garage garage;

}
