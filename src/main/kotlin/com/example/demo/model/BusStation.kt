package com.example.demo.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class BusStation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank
    val name: String,

    @NotNull
    val latitude: Double,

    @NotNull
    val longitude: Double,

    @NotBlank
    @Column(length = 2)
    val countryCode: String
)
