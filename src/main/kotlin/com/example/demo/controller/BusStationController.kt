package com.example.demo.controller

import com.example.demo.model.BusStation
import com.example.demo.service.BusStationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bus-stations")
class BusStationController(private val busStationService: BusStationService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<BusStation>> {
        val busStations = busStationService.findAll()
        return ResponseEntity.ok(busStations)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<BusStation> {
        val busStation = busStationService.findById(id) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(busStation)
    }

    @PostMapping
    fun create(@RequestBody busStation: BusStation): ResponseEntity<BusStation> {
        val savedBusStation = busStationService.save(busStation)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBusStation)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody busStation: BusStation): ResponseEntity<BusStation> {
        if (busStationService.findById(id) == null) {
            return ResponseEntity.notFound().build()
        }
        val updatedBusStation = busStation.copy(id = id)
        val savedBusStation = busStationService.save(updatedBusStation)
        return ResponseEntity.ok(savedBusStation)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val busStation = busStationService.findById(id) ?: return ResponseEntity.notFound().build()
        busStationService.deleteById(busStation.id!!)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/by-country/{countryCode}")
    fun getByCountryCode(@PathVariable countryCode: String): ResponseEntity<List<BusStation>> {
        val busStations = busStationService.findByCountryCode(countryCode)
        return ResponseEntity.ok(busStations)
    }

    @GetMapping("/by-location")
    fun getByLocation(
        @RequestParam("latitude") latitude: Double,
        @RequestParam("longitude") longitude: Double,
        @RequestParam("radius") radius: Double
    ): ResponseEntity<List<BusStation>> {
        val busStations = busStationService.findWithinRadius(latitude, longitude, radius)
        return ResponseEntity.ok(busStations)
    }

}
