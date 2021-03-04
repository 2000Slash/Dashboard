# Server Dashboard

This is a simple web dashboard for my server. Right now it only includes a docker overview that shows all containers and provides functionality to control them.

## Building

The project contains a vuejs frontend and a java spring powered backend.

### Building the frontend

To build the frontend, just make sure ```mode``` in frontend/webpack.config.js is set to ```development```, then run ```npx webpack``` in the frontend folder.

### Building the backend

The backend is supposed to ship with the frontend, so you have to copy the output file from frontend/dist/ to src/main/resources/static. After that you can simply run ```mvn clean verify spring-boot:repackage``` to build or ```mvn clean spring-boot:run``` to run the project. It then runs at port 8080. 

### Building a docker container

Right now there is not an easy way to let this run in a docker container, since the java application needs write privileges to the docker socket. If you use ```spring-boot:build-image```, the user in the container will not be root and wont be in the docker group. Because of this, he wont be able to access the docker.socket file.
