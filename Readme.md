# Server Dashboard

This is a simple web dashboard for my server. Right now it only includes a docker overview that shows all containers and provides functionality to control them.

## Building

The project contains a vuejs frontend and a java spring powered backend.

### Building for development

If you just want to develop, the frontend can easily be decoupled from the backend. 

First, build the backend with ```mvn clean verify```. This will automatically repackage the jar to contain all dependencies. When this is done, you can start the backend with ```java -jar target/dashboard-1.0.0.jar```. You can also start the project from an ide if you want. The backend now lives at port 8080 and under the path ```/api```.

To develop for the backend, run ```npx webpack serve``` in the frontend folder. Make sure that the mode set in frontend/webpack.config.js is set to ```development```. The frontend now lives at port 8000. All calls made to ```http://localhost:8000/api``` will be redirected to ```http://localhost:8080/api```. If you use your own server, make sure that the calls get redirected, or else the frontend wont work.

### Building the prod

Building for prod is really easy, thanks to maven profiles. First make sure that mode in webpack.config.js is set to ```production```. You should also remove the @CrossOrigin line in src/main/java/com/nils/main/Controller.jar. Without it, everyone can access the java api. Then simply call maven with ```mvn clean verify -Dprod```. This will download node to the node folder and then run ```npx webpack``` in the frontend. The resulting files get copied automatically to the target/classes/static folder and will be included in the final jar. You can start the production jar the same way as the development jar with ```java -jar target/dashboard-1.0.0.jar```. The frontend is included in the jar and lives in ```http://localhost:8080```, just like the backend which lives in ```http://localhost:8080/api`

### Building a docker container

Right now there is no easy way to let this run in a docker container, since the java application needs read/write privileges to the docker socket. If you use ```spring-boot:build-image```, the user in the container will not be root and wont be in the docker group. Because of this, he wont be able to access the docker.socket file.
