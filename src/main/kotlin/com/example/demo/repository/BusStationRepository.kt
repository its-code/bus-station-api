package com.example.demo.repository

import com.example.demo.model.BusStation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BusStationRepository : JpaRepository<BusStation, Long> {
    fun findByCountryCode(countryCode: String): List<BusStation>
}
