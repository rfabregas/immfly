Hello Immfly,

First we will download all the dependencies:
-> mvn clean install

In order to start the project you should run the docker-compose to build the local Redis image:
-> docker-compose up

As requested, I have set up a Redis database and Redis cache.
The external endpoint that retrieves the mocked information has a 30 seconds cache to make it easier
to perform changes and test the code.

Once the Redis is up and running, you can already start the application from your IDE or run
-> java -jar infrastructure/target/app.jar

The following curl will give a 401 Unauthorized error just as required:
curl --location --request GET 'http://localhost:8080/v1/flight-information/EC-MYT/653' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=FD96A564E04754CD4E6C64C1FDC70CD6'

In the test class FlightControllerTest there are a couple tests testing the ADMIN functionallity by
mocking a user with the necessary role.

If for testing reasons you need to clear the Redis database, you can go through the following steps:
-> docker ps
Get the id of the Redis container.
-> docker exec -it containerId sh
-> redis-cli
Once inside the Redis console, run:
FLUSHALL
