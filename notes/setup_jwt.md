# Setting up jwt

## Adding the maven dependencies
    - groupId : io.jsonwebtoken
    - artifactId : jjwt
    - version : 0.9.1

## Define the application.properties   
    This is for the jwt secret key which will be used for the hashing algorithm.
    The secret key is combined with the header and the payload to create a unique hash.
    - Defining this property requires additional metadata added to the resources folder.

## Setup the jwtTokenUtil class which
    Follow the package "security/jwt" to see the class

## Setup the UserDetailsService implementation
    Follow the package "service/impl" to see the class

## setup jwt request and response classes

## setup jwt request filter