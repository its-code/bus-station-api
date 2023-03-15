# Coding Challenge

### Task

The task consists in building a small web application that can provide access to some data via Rest API.

For our web application, we need an endpoint that would allow our users to create a new bus station and also edit,delete
and retrieve an existing one.

A bus station must have a name, coordinates, country code and unique id. You can add other fields if necessary.

Also, the user should be able to find all stations in a country, by providing a country code.

Finally, we want to give users the possibility to find all stations by their position. In particular, we want to provide
an endpoint that returns all the stations that are located inside a given circle measures (circle center coordinates
and max distance from it).

### Requirements

- The project can be done both in Java and Kotlin. You should have all the necessary libraries but feel free to add more
  if needed.
- Data should be persisted in SQL in-memory database.
- Implementation logic should be covered by unit tests.
- Consider performance improvements.
- Use git during development to track progress milestones.
- `./gradlew bootRun` should start the application successfully.
- `./gradlew test` should run tests.

### Submission

- You one week to complete the task.
- Please complete as much as you will be able to in the given timeframe. Don't worry if you don't complete all tasks,
  you can submit an incomplete project as well.
- Please zip the project directory including .git folder and send it back via email.
