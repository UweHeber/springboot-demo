# Spring Boot 2.0 Demo 
_An attempt to connect a database to a REST interface via JPA , where paging, sorting and HyperMedia are taken into account_

## Database
For the example, the internal H2 database is used, which generates two tables including associated data when the application is started:

    # Table 'CUSTOMER'
    --------------------
    Long    id 
    String  firstName
    String  lastName
    Integer age
    String  company
    
    # Table 'COMPANY'
    --------------------
    Long    id 
    String  name

## JPA usage
Using Spring Data it is quite easy to perform queries on entities, as it supports filter and sort algorithms by default.

Therefore, the [specifications pattern][1] is used to codify business rules that state something about an (JPA) object. 
These rules define the fulfillment criteria for the database query, so that Hibernate aggregates the corresponding valid data in the background.

Both tables can be accessed via its own `repository` , which extends the `JpaRepository` and `JpaSpecificationExecuter` 
interface to allow execution of Specifications based on the JPA criteria API.

## REST Controller
To ensure access to the two JPA repository, two REST controllers have been implemented which can be accessed via following URLs
1. `http://localhost:8080/api/customers`
2. `http://localhost:8080/api/companies`

Both controllers use `PagedResources`, with which you can scroll elegantly between the individual result sets (pages). 
This in combination with the `PagedResourcesAssembler` enables the controller to support HyperMedia.

So, the result contains always all required information about first, previous, current, next or last page.

To support filtering by certain fields of an entity, a specific `specification` is assembled in each controller, which is transferred to the backend. This can differ from case to case. 

## Installation and Setup

Following steps are required to install the plugin:

1. Clone this repository to your developer workspace instance as mutation-test project:

    `$ git clone https://github.com/UweHeber/springboot-demo`

2. Run following Maven command within your developer workspace 

    `mvn clean install`

3. Start application via shell command within your developer workspace 

    `java -Dfile.encoding=UTF-8 -jar target\springboot-demo-0.0.1-SNAPSHOT.jar`

## Usage

## License
This example is licensed under the MIT license. See LICENSE for details.


[1]: https://dzone.com/articles/java-using-specification