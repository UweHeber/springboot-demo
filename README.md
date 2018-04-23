# Spring Boot 2.0 Demo 
_This demo is an attempt to connect a database via JPA to a REST interface - Condition here to support paging, sorting and HyperMedia_

## Database
For this demo the internal H2 database is used, which generates two tables including associated data when the application is started:

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

Therefore, the [specifications pattern][anchor_1] is used to codify business rules that state something about an (JPA) object. 
These rules define the fulfillment criteria for the database query, so that Hibernate aggregates the corresponding valid data in the background.

Both tables can be accessed via its own `repository` , which extends the `JpaRepository` and `JpaSpecificationExecuter` 
interface to allow execution of Specifications based on the JPA criteria API.

## REST Controller
To ensure access to the two JPA repository, two REST controllers have been implemented which can be accessed via following URLs
1. `http://localhost:8080/api/customers`
2. `http://localhost:8080/api/companies`

Both controllers using `PagedResources` with which you can scroll elegantly between the individual result sets (pages). 
This in combination with the `PagedResourcesAssembler` enables the controller to support HyperMedia.

So, the result contains always all required information about `first`, `previous`, `current`, `next` or `last` page.

To support the filtering by certain fields of an entity, a specific `specification` is assembled in each controller, 
which is passed to the backend logic. This can differ from case to case. 

## Installation and Setup

Following steps are required to install the plugin:

1. Clone this repository to your developer workspace instance:

    `$ git clone https://github.com/UweHeber/springboot-demo`

2. Run following Maven command within your developer workspace 

    `$ mvn clean install`

3. Start application via shell command within your developer workspace 

    `$ java -Dfile.encoding=UTF-8 -jar target\springboot-demo-0.0.1-SNAPSHOT.jar`

## Usage
As already mentioned in REST Controller section, this example provides two REST URLs
1. `http://localhost:8080/api/customers`
2. `http://localhost:8080/api/companies`

which initially list 25 data records including HyperMedia links and page information:

![JSON response - Spring Boot with Spring Data][image_json-example]
    
The result set can then be sorted using URL parameters, whereby influence can be exerted on the current page to be 
displayed, including the number of data sets.

### Paging/Sorting

URL parameters for paging and sorting are simple. Spring Data supports per default

    page // which page to be display     
    size // how much results should be displayed per page
    sort // which field(s) want to sort by (per default ascending)
    
Here are a few examples of how the result sets can be configured. Interestingly, the result can be easily influenced by 
nested sorting. 

    1. http://localhost:8080/api/customers?page=0&size=5&sort=id
    2. http://localhost:8080/api/customers?page=1&size=10&sort=firstName
    3. http://localhost:8080/api/customers?page=0&size=15&sort=lastName,asc&sort=firstName,desc
    4. http://localhost:8080/api/customers?page=0&size=20&sort=company
    5. http://localhost:8080/api/customers?page=0&size=100&sort=age,desc
 
### Filtering

Filtering the result set is also easy. We had established the class `SearchOperation`, which allows the following 
operations:

    ':' // equal
    '!' // not
    '>' // grather then
    '<' // less then
    '~' // like
    
Filtering is possible using the optional URL parameter `search`, which must follow the following syntax:

    search=<FIELD_NAME><SEARCH_OPERATION><VALUE>

A special feature of the `equality` operator (colon `:`) is that you can determine whether the string you are looking for is 
arbitrary

    # all customer, where last name starts with 'Mill' AND younger than 28 years
    http://localhost:8080/api/customers?search=lastName:Mill*,age<28
    --
    # all customer, where last name ends with 'ller' AND older than 28 years
    http://localhost:8080/api/customers?search=lastName:*ller,age>28
    --
    # all customer, where last name contains 'ille' AND equals 24 years
    http://localhost:8080/api/customers?search=lastName:*ille*,age=24

It is also helpful to search for results within a certain range:
    
    # all customers older than 26 and younger than 30 years
    http://localhost:8080/api/customers?search=age>26,age<30

### Combine

In this section we will just mention once again that both options (Paging/Sorting AND Filtering) can be combined 
wonderfully:

    # all customers with last name 'Miller', descending sorted by their firstnames followed by its age
    http://localhost:8080/api/customers?search=lastName:Miller&page=0&size=10&sort=firstName,desc&sort=age
    
## License
This example is licensed under the MIT license. See LICENSE for details.


[anchor_1]: https://dzone.com/articles/java-using-specification
[image_json-example]: https://heber.it/images/github/springboot-data_json-exampl.png