package com.example.demo.service

import com.example.demo.model.BusStation
import com.example.demo.repository.BusStationRepository
import org.springframework.stereotype.Service
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


@Service
class BusStationService(private val busStationRepository: BusStationRepository) {


    fun findAll(): List<BusStation> = busStationRepository.findAll()

    fun findById(id: Long): BusStation? = busStationRepository.findById(id).orElse(null)

    fun save(busStation: BusStation): BusStation = busStationRepository.save(busStation)

    fun deleteById(id: Long) = busStationRepository.deleteById(id)

    fun findByCountryCode(countryCode: String): List<BusStation> = busStationRepository.findByCountryCode(countryCode)

    fun findWithinRadius(latitude: Double, longitude: Double, radius: Double): List<BusStation> {
        return busStationRepository.findAll().filter { busStation ->
            val distance = distance(busStation.latitude, busStation.longitude, latitude, longitude)
            distance <= radius
        }
    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371.0 // km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2) * sin(dLon / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }

}