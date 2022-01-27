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

### CHECKSTYLE

You can generate the Checkstyle report by explicitly executing below command from the command line.

```
mvn checkstyle:checkstyle
```
