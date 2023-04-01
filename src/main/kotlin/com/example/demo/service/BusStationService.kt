package com.example.demo.service

import com.example.demo.model.BusStation
import com.example.demo.repository.BusStationRepository
import org.springframework.stereotype.Service


@Service
class BusStationService(private val busStationRepository: BusStationRepository) {


    fun findAll(): List<BusStation> = busStationRepository.findAll()

    fun findById(id: Long): BusStation? = busStationRepository.findById(id).orElse(null)

    fun save(busStation: BusStation): BusStation = busStationRepository.save(busStation)

    fun deleteById(id: Long) = busStationRepository.deleteById(id)

    fun findByCountryCode(countryCode: String): List<BusStation> = busStationRepository.findByCountryCode(countryCode)

}