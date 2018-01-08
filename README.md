This is a spring-boot gradle based project. In order to run, gradle 4 is required.
In order to start the application issue 'gradle clean bootRun'.
An http server listening to http://localhost:8080 will be up and running.

In order to execute the requests, the project is packaged with swagger ui.
To access swagger ui use the http://localhost:8080/swagger-ui.html url.

Post requests for navigation using the payload specified can be issued at the http://localhost:8080/robot/navigate url.

Persisting the data in a database is required. H2 was used in order to provide a more out of the box experience.

The h2 database is in memory. The database initialization is done automatically through hibernate.
The json request and responses are stored in the corresponding column fields using the @Lob annotation.

In order to view the h2 in memory database the h2 console is enabled.
To access the h2 console you have to navigate to the the http://localhost:8080/h2-console url.
In order to connect to the database currently running in memory you have to specify 'jdbc:h2:mem:testdb' as the jdbc url.

An extra controller is provided in order to display the history of the navigation requests and the navigation responses.

