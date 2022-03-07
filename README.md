## Table of contents

* [General-info](#general-info)
* [Entities](#Entities)
* [Tests](#Tests)
* [Services](#Services)
* [Technologies](#Technologies)

## General info

I present yo ou a REST API which acts as library system.

## Entities

Application contains following entities:

* user(entity) - contains userId, firstname, lastname and date of creating account
* book(entity) - contains bookId, title, author and book publication data
* copy(entity) - contains copyId, book(entity) and status of the book with options(AVAILABLE,RENTED,LOST)
* rental(entity) -contains rentalId, copyId, userId, a date when user has borrowed book and the date of returning

## Services

The application contains programmed services:

* /getUsers(GET) - returns list of all registered users,
* /getUser(GET) - returns list of specific user (query param 'userId'),
* /deleteUser(DELETE) - deletes user from the list by id (query param 'userId'),
* /updateUser(PUT) - updates user accepts input as JSON format with values 'firstname', 'lastname', 'userDateCreateAccount'(YYYY-MM-DD),
* /createUser(POST) - creates user accepts input as JSON format with values 'firstname', 'lastname', 'userDateCreateAccount'(YYYY-MM-DD),
* /createBook(POST) - creates book accepts input as JSON format with values 'title', 'author', 'releaseDate'
* /getBookById(GET) - returns list of specific book(query param 'bookId'),
* /deleteBook - deletes book from the list by id (query param 'bookId'),
* /updateBook - updates book accepts input as JSON format with (body 'book' object),
* /getBooks - returns list of all books,
* /createCopyBook - creates copy book row, accepts input as JSON format with values('status', 'book' object)
* /updateCopyBook - updates copy book accepts input as JSON format with(body 'copy' object)
* /deleteCopy - deletes copy book from the list by id (query param 'bookCopyId')
* /getCopy - returns list of specific copy book (query param 'bookCopyId)
* /getCopyByTitle - return list of book copies with status 'AVAILABLE'
* /deleteRental - deletes rental from the list by id (query param 'rentalId'),
* /getRental - returns rental object (query param 'rentalId')
* /rentedBook - creates row in table accepts input ('userId', bookCopyId, rentUntil)

## Tests

The application contains several tests that add values to specific tables.


## Technologies

Project contains technologies:
* IntelliJ IDEA Community Edition 2021.1.3 x64
* JUnit Jupiter Engine v: 5.8.2
* JUnit Jupiter API v: 5.8.2
* Gradle v: 7.4
* Spring Boot v: 2.6.3
* Hibernate v: 5.6.4
* Spring JDBC v: 5.3.15
* MySQL Connector v: 8.0.28
* Lombok v: 1.18.22





