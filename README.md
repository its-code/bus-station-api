###Bus Station REST api

For our web application, we need an endpoint that would allow our users to create a new bus station and also edit,delete
and retrieve an existing one.

A bus station have a name, coordinates, country code and unique id.

the user able to find all stations in a country, by providing a country code.

Finally, we want to give users the possibility to find all stations by their position. In particular, we want to provide
an endpoint that returns all the stations that are located inside a given circle measures 

### Technologies

- The project is done in Kotlin.
- Data persisted in SQL in-memory database.
- Implementation logic covered by unit tests. (Services only)
- `./gradlew bootRun` should start the application successfully.
- `./gradlew test` should run tests.
