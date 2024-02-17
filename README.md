# GithubTask

The application is based on the Spring Boot framework and provides a RESTful API for retrieving user repositories along with their branches in a given project. The application utilizes the GitHub API.

## Used Technologies

List the technologies/frameworks/libraries used in your application.

- Spring Boot
- Lombok
- Spring WebFlux
- WebClient
- Mockito

## Functionality

The application allows the client to retrieve a list of projects posted on GitHub based on the provided username. Users can call the endpoint `/github/api/{username}`. The response includes the repository name, owner's login, and a list of branches in the project.

If an invalid username is provided, the application will return an appropriate HTTP status along with a message.


