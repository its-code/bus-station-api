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

    /**
     * Returns a list of all BusStations.
     */
    fun findAll(): List<BusStation> = busStationRepository.findAll()

    /**
     * Returns a BusStation with the specified ID, or null if not found.
     *
     * @param id The ID of the BusStation to find.
     */
    fun findById(id: Long): BusStation? = busStationRepository.findById(id).orElse(null)

    /**
     * Saves a new BusStation or updates an existing one.
     *
     * @param busStation The BusStation to save or update.
     */
    fun save(busStation: BusStation): BusStation = busStationRepository.save(busStation)

    /**
     * Deletes a BusStation with the specified ID.
     *
     * @param id The ID of the BusStation to delete.
     */
    fun deleteById(id: Long) = busStationRepository.deleteById(id)

    /**
     * Returns a list of BusStations with the specified country code.
     *
     * @param countryCode The country code to filter BusStations by.
     */
    fun findByCountryCode(countryCode: String): List<BusStation> = busStationRepository.findByCountryCode(countryCode)

    /**
     * Updates an existing BusStation object with the provided changes.
     *
     * @param busStation The BusStation object to be updated.
     * @param updates A map containing the updated properties and their new values.
     * @return The updated BusStation object after saving.
     */
    fun update(busStation: BusStation, updates: Map<String, Any>): BusStation {
        val updatedBusStation = busStation.copy(
            name = updates["name"] as? String ?: busStation.name,
            latitude = updates["latitude"] as? Double ?: busStation.latitude,
            longitude = updates["longitude"] as? Double ?: busStation.longitude,
            countryCode = updates["countryCode"] as? String ?: busStation.countryCode
        )
        return save(updatedBusStation)
    }


    /**
     * Returns a list of BusStations within the specified radius (in Km) of the given latitude and longitude.
     *
     * @param latitude The latitude to center the search around.
     * @param longitude The longitude to center the search around.
     * @param radius The radius (in kilometers) within which to search for BusStations.
     * @return A list of BusStations within the specified radius.
     */
    fun findWithinRadius(latitude: Double, longitude: Double, radius: Double): List<BusStation> {
        return busStationRepository.findAll().filter { busStation ->
            val distance = distance(busStation.latitude, busStation.longitude, latitude, longitude)
            distance <= radius
        }
    }

    /**
     * Computes the distance between two sets of latitude and longitude using the Haversine formula.
     *
     * @param lat1 The latitude of the first point.
     * @param lon1 The longitude of the first point.
     * @param lat2 The latitude of the second point.
     * @param lon2 The longitude of the second point.
     * @return The distance (in kilometers) between the two points.
     */
    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371.0 // km
        val diffLat = Math.toRadians(lat2 - lat1)
        val diffLon = Math.toRadians(lon2 - lon1)
        val a = haversine(diffLat) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * haversine(diffLon)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return earthRadius * c
    }

    /**
     * Calculates the haversine of an angle.
     *
     * @param angle The angle (in radians) for which to calculate the haversine.
     * @return The haversine of the given angle.
     */
    private fun haversine(angle: Double): Double {
        val sinHalfAngle = sin(angle / 2)
        return sinHalfAngle * sinHalfAngle
    }

}