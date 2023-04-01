import com.example.demo.model.BusStation
import com.example.demo.repository.BusStationRepository
import com.example.demo.service.BusStationService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.*

class BusStationServiceTest {

    private lateinit var service: BusStationService
    private lateinit var repository: BusStationRepository

    @BeforeEach
    fun setUp() {
        repository = mock(BusStationRepository::class.java)
        service = BusStationService(repository)
    }

    @Test
    fun testFindAll() {
        println("Running should return all bus stations test...")
        val busStation1 = BusStation(1L, "Station 1", 0.0, 0.0, "DE")
        val busStation2 = BusStation(2L, "Station 2", 0.0, 0.0, "US")
        `when`(repository.findAll()).thenReturn(listOf(busStation1, busStation2))
        val result = service.findAll()
        assertEquals(2, result.size)
        assertEquals(busStation1, result[0])
        assertEquals(busStation2, result[1])
        println("should return all bus stations test completed.")
    }

    @Test
    fun testFindById() {
        println("Running testFindById()...")
        val busStation = BusStation(1L, "Station 1", 0.0, 0.0, "DE")
        `when`(repository.findById(1L)).thenReturn(Optional.of(busStation))
        val result = service.findById(1L)

        println("Bus station found: $result")
        assertEquals(busStation, result)
    }

    @Test
    fun testSave() {
        println("Running testSave()...")
        val busStation = BusStation(1L, "Station 1", 0.0, 0.0, "DE")
        `when`(repository.save(busStation)).thenReturn(busStation)
        val result = service.save(busStation)
        assertEquals(busStation, result)
    }

    @Test
    fun testDeleteById() {
        println("Running testDeleteById()...")
        val id = 1L
        service.deleteById(id)
        verify(repository).deleteById(id)
    }

    @Test
    fun testFindByCountryCode() {
        println("Running testFindByCountryCode()...")
        val countryCode = "DE"
        val busStation1 = BusStation(1L, "Station 1", 0.0, 0.0, countryCode)
        val busStation2 = BusStation(2L, "Station 2", 0.0, 0.0, countryCode)
        `when`(repository.findByCountryCode(countryCode)).thenReturn(listOf(busStation1, busStation2))
        val result = service.findByCountryCode(countryCode)
        assertEquals(2, result.size)
        assertEquals(busStation1, result[0])
        assertEquals(busStation2, result[1])
    }

    @Test
    fun testFindWithinRadius() {
        // Given
        val latitude = 12.971598
        val longitude = 77.594566
        val radius = 2.0

        val busStation1 = BusStation(1, "Station 1", 12.971598, 77.594566, "UK")
        val busStation2 = BusStation(2, "Station 2", 12.972598, 77.595566, "FR")
        val busStation3 = BusStation(3, "Station 3", 13.000000, 77.600000, "DE")

        `when`(repository.findAll()).thenReturn(listOf(busStation1, busStation2, busStation3))

        // When
        val result = service.findWithinRadius(latitude, longitude, radius)

        // Then
        assertEquals(2, result.size)
        assertTrue(result.contains(busStation1))
        assertTrue(result.contains(busStation2))
        assertFalse(result.contains(busStation3))
    }

}