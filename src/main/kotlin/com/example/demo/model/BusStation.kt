package com.example.demo.model

import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Entity
data class BusStation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank
    val name: String,

    @NotNull
    @Min(-90) @Max(90)
    val latitude: Double,

    @NotNull
    @Min(-180) @Max(180)
    val longitude: Double,

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must consist of exactly 2 capital letters")
    @Column(length = 2)
    val countryCode: String
)
