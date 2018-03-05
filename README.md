# Flight Controller

Master: [![Build Status](https://travis-ci.org/mvs5465/flight-controller.svg?branch=master)](https://travis-ci.org/mvs5465/flight-controller)

## Description

A sample concurrent aircraft takeoff scheduler application using Spring Boot.

## Requirements

- [Java 8](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

- [Maven](https://maven.apache.org/install.html)

- [Docker](https://docs.docker.com/install/)

- [Docker-Compose](https://docs.docker.com/compose/install/)

## Installation

1. `git clone https://github.com/mvs5465/flight-controller.git`

2. `mvn clean install`

## Usage

### 1. Run

#### Outside of Docker:

`java -jar target/flight-controller-0.1.0.jar`

#### With Docker-Compose:

`docker-compose up --build`

### 2. Use

There are two endpoints provided by this application and a sample web ui at `localhost:8080`.

## Testing

Unit tests are run during `mvn clean install` unless the `-DskipTests` flag is added. Otherwise tests can be run using `mvn test`.
