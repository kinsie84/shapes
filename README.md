# SHAPES Spring Boot Application
Simple Rest API built with Spring-Boot.
 
Supports CRUD calls that generates and updates data for creating randomly-sized squares and circles.

# Install & Run
1. Clone this repository
1. `mvn spring-boot:run` in root directory

# Endpoints
* "/shapes/new" to generate and get a new set of 50 randomly sized circles and 50 randomly sized squares
* "/shapes/all" to get the most recently generated set of shapes in increasing order of ID
* "/shapes/sorted" to get the most recently generated set of shapes by decreasing order of area
* "/shapes/{id}" to get a single shape by its ID



