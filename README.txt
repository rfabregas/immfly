Hello Immfly,

For this project I have built a Domain Driven Design Hexagonal architecture. Leaving the domain module
out of any external dependencies.

To run the project:

First we will download all the dependencies:
-> mvn clean install

In order to start the project you should run the docker-compose to build the local Redis image:
-> docker-compose up

Once the Redis is up and running, you can already start the application from your IDE or run
-> java -jar infrastructure/target/app.jar

------------------------------ Redis Database & Cache ---------------------
As requested, I have set up a Redis database and Redis cache.
The external endpoint that retrieves the mocked information has a 30 seconds cache to make it easier
to perform changes and test the code.
If you want to modify the mock information of the external service, in the resources folder of the
infrastructure module, you will find a json with the mock information (FlightInformation.json).

---------------------------------------------------------------------------

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

 ------------------------- Unit Tests issue: -----------------------
 I wanted to create Integration Tests of the Controller layer, even though they are usually performed
 by QA.
 As you can see, I have a commented class (FlightControllerTest). That's because I have been having
 some odd issues with this Unit Test class. If you uncomment the class and run the class manually,
 all the tests will pass. Also, if you run mvn test, the tests will pass. However, if you run
 the mvn clean install command, the test will fail for some reason.
 To be totally transparent, I could not invest all the time that I wanted to try to investigate this
 issue, so I apologize about that.


Thanks for your time.