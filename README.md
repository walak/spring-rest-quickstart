# spring-rest-quickstart
Maven archetype for quickstart of REST service

## Abstract
This artifact is intended to ease starting new REST/Spring project. It is just a collection of some
dependencies (Spring, JPA/Hibernate, Jackson, etc.). Current set is based on my personal preferences, so I may not be
perfect for you.

## Installing the artifact
The artifact has not been released to any Maven repository (and it won't be). If you really want to use it:

1. Clone the repository
2. Run `mvn install`
3. Create a new project with Maven. Example:
```
mvn archetype:generate
    -DarchetypeGroupId=com.github.walak
    -DarchetypeArtifactId=spring-rest-quickstart
    -DarchetypeVersion=1.0
    -DgroupId=org.dyndns.walak
    -DartifactId=test
    -Dversion=1.0-SNAPSHOT
