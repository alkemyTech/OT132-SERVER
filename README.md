# README

### CODE STANDARDS

We follow the rules
from [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) and:

- The interfaces should start with prefix "I". Example: IUserRepository.
- Package names are in singular.
- The names of the attributes for Java code use camel case, but the name for SQL uses underscore and
  uppercase.
- The name of the tables should be in plural, but the entity name should be in singular.
- Exceptions should be handled by an implementation of ControllerAdvice.
- All the configuration stuffs must go in the config package.
- The name of abstract classes should start with prefix "Abstract". Example: "AbstractFile".
- The integration test should go into the integration package.
- If you add a new endpoint, make sure to set the role access for it in the SecurityConfig class.

The code style for this repository is the used by [Google](https://github.com/google/styleguide).
So, make sure to set up your IDE with the right code style format file.

### KEEP IN MIND FOR PULL REQUEST AND CODE REVIEW

- The branch name should be the {ticket#}.
- The rule for pull request title is: "{ticket#}: {jiraTitle}".
- The commits should follow this pattern: "{ticket#}: {commitDescription}". Small commits are a nice
  to have.
- If you don’t add unit test or integration test as part of your code changes, you should add the
  request and response as evidence that the code is working as expected.
- Postman collection should be updated every time that you open a pull request. Be a team player!
- Once you finish to addressing all the comments, leave a comment on the pull request to the
  reviewer asking to re-review the PR.

### RUN LOCALLY

In this project run:

```
mvn spring-boot:run
```

### SWAGGER DOCUMENTATION

If you want to display the API documentation go to below page after run the project:

```
http://localhost:8080/swagger-ui/index.html
```

### CHECKSTYLE

You can generate the Checkstyle report by explicitly executing below command from the command line.

```
mvn checkstyle:checkstyle
```

### DEFAULT USERS

- Admin users:

| Email                   | Password   |
|-------------------------|------------|
| etluit12@gmail.com      | t0400e3ps  |
| epchristel15@gmail.com  | p00846e4p  |
| jfstefanie5@gmail.com   | f94475j9f  |
| gkelsenbach10@gmail.com | k15564g6k  |
| jjottomar9@gmail.com    | j654110j9j |
| erleitner17@gmail.com   | r05400e4r  |
| hamoses0@gmail.com      | a987103h7a |
| behoyt4@gmail.com       | e62685b1e  |
| adblanchette3@gmail.com | d25548a0d  |
| dkpauling10@gmail.com   | k97165d3k  |

- Standard users:

| Email                     | Password   |
|---------------------------|------------|
| bskunigunde18@gmail.com   | s30103b1s  |
| jpschell15@gmail.com      | p96776j9p  |
| airupel8@gmail.com        | i117107a0i |
| cpcarina15@gmail.com      | p09919c2p  |
| hrgastelu17@gmail.com     | r80744h7r  |
| huweinmeier-r20@gmail.com | u83385h7u  |
| esplatz18@gmail.com       | s23664e4s  |
| cysonje24@gmail.com       | y59736c2y  |
| gdgitte3@gmail.com        | d30762g6d  |
| clsarvott11@gmail.com     | l22929c2l  |