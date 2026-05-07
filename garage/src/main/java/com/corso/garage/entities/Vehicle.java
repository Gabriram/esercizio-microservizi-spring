package com.corso.garage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@Table(name = "vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "external_id")
    private Long externalId; // id della bike o della car

    @NotBlank
    @Column(name = "vehicle_type")
    private String vehicleType; // "BIKE" o "CAR"

    @ManyToOne
    @JoinColumn(name = "garage_id")
    @JsonIgnore // evita ciclo di serializzazione
    private Garage garage;

}
