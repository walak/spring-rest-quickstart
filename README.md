# spring-rest-quickstart
Maven archetype for quickstart of REST service

## Abstract
This artifact is intended to ease starting new REST/Spring projects. It is just a collection of some
dependencies (Spring, JPA/Hibernate, Jackson, etc.). The actual set is based on my personal preferences, so it may not be
perfect for you.


## What's here
The artifact will create a very basic project with following items:
* configured Jetty with Spring
* basic DAOs
* basic Services (using DAOs)
* simple Entity to test DB settings
* simple tests to check if artifact generates projects correctly

Probably the most of the stuff can be removed right after the project is created.

## Installing the artifact
The artifact has not been released to any Maven repository (and it won't be). If you really want to use it:

1. Clone the repository
2. Run `mvn install`
3. Create a new project with Maven. Example:
```
mvn archetype:generate
    -DarchetypeGroupId=com.github.walak
    -DarchetypeArtifactId=spring-rest-quickstart
    -DarchetypeVersion=1.1
    -DgroupId=com.github.walak
    -DartifactId=test
    -Dversion=1.0-SNAPSHOT

# Changelog

## Changes in version 1.2
* JettyRunner is now configured to automatically redeploying 

## Changes in version 1.1
* added DAOs
* added Services
* added some tests
* added standalone runner
* a bit of cleanup 
