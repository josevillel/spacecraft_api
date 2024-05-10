# SpaceCraft CRUD API

Prueba técnica Spring Boot

## Getting Started

La prueba consiste en desarrollar, utilizando Maven, Spring Boot, y Java, una API que permita hacer un mantenimiento CRUD de naves espaciales de series y películas.

Este mantenimiento debe permitir:

* Consultar todas las naves utilizando paginación. [X]
	
* Consultar una única nave por id. [X]
	
* Consultar todas las naves que contienen, en su nombre, el valor de un 	parámetro 	enviado en la petición. Por ejemplo, si enviamos “wing” devolverá 	“x-wing”. [X]
	
* Crear una nueva nave. [X]
	
* Modificar una nave. [X]
	
* Eliminar una nave. [X]
	
* Test unitario de como mínimo de una clase. [X]
	
* Desarrollar un @Aspect que añada una línea de log cuando nos piden una nave 	con un 	id negativo. [X]
* Gestión centralizada de excepciones. [X]

* Utilizar cachés de algún tipo. [X]

Puntos a tener en cuenta:

* Las naves se deben guardar en una base de datos. Puede ser, por ejemplo, H2 	en memoria. [X]

* La prueba se debe presentar en un repositorio de Git. No hace falta que  	esté publicado. Se puede enviar comprimido en un único archivo. [X]

Puntos opcionales de mejora:

* Utilizar alguna librería que facilite el mantenimiento de los scripts DDL 	de base de datos. [X]

* Test de integración. [X]

* Presentar la aplicación dockerizada. [X]

* Documentación de la API. [X]

* Seguridad del API. [X]

* Implementar algún consumer/producer para algún broker (Rabbit, Kafka, etc). [X] 

### Prerequisites

 - Java 17
 - Maven

### Installation

 - mvn clean package

 - java -jar target/spacecrafts-api.jar

## Api docs

 - OpenApi 3.0 en http://localhost:8080/swagger-ui/index.html
 
## Security

 - Autenticación HTTP básica de Spring, con usuario y contraseña fijados a efectos de    pruebas.
 
## Usage

 - curl -X 'GET' --user user:password 'http://localhost:8080/api/spacecrafts?page=0&size=5'

## Docker

Con docker instalado y en ejecución:

 - docker build -t spacecrafts-api .

 - docker run -p 8080:8080 spacecrafts-api


