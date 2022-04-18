# iStudyBucket API

This is the backend of the iStudyBucket project, built with Spring and contains the application business logic. 
iStudyBucket is an application which gives students the chance to share and learn together on content regarding school as they would on social media. The aim of this is to have a platform (similar to StackOverflow, Reddit, Facebook) where students (targetting Cameroon, and Africa later) can post, comment and review content from others strictly for learning purposes and education.

This backend is built with Java and targets Java 11. We use Maven to manage dependencies, Freemarker for html templates, and JUnit for testing.

<!--
**Note:** See individual language directories in this repo for technology-specific badges. 

[![CircleCI](https://img.shields.io/circleci/project/github/auth0/open-source-template.svg?style=flat-square)](https://circleci.com/gh/auth0/open-source-template/tree/master)
[![TravisCI](https://travis-ci.org/auth0/open-source-template.png)](https://travis-ci.org/auth0/open-source-template)
[![CodeCov](https://img.shields.io/codecov/c/github/auth0/open-source-template/v3.svg?style=flat-square)](https://codecov.io/github/auth0/open-source-template)
[![Coveralls](https://coveralls.io/repos/auth0/open-source-template/badge.svg?branch=master)](https://coveralls.io/r/auth0/open-source-template?branch=master)
[![Code Climate](https://img.shields.io/codeclimate/maintainability/auth0/open-source-template.svg)](https://codeclimate.com/github/auth0/open-source-template)
[![License](http://img.shields.io/:license-mit-blue.svg?style=flat)](https://opensource.org/licenses/MIT)
-->
## Table of Contents

Make sure this is updated based on the sections included:

- [Documentation](#documentation)
- [Installation](#installation)
- [Getting Started](#getting-started)
- [Contributing](#contributing)
- [Support + Feedback](#support--feedback)
- [Thank You](#thank-you)
- [License](#license)

## Documentation

NO DOCUMENTATION AVAILABLE FOR THIS PROJECT YET

## Installation

##### To get this project running on your personal computer or server, ensure you have:
- Java SE 11 or above | [Download JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- Maven | [Download Maven](https://maven.apache.org/download.cgi)
- MySQL | [Download MySQL Community Server](https://dev.mysql.com/downloads/mysql/)


##### To run this project, follow these steps

- Clone this repository on your machine using this command:
```bash
git clone https://github.com/istudybucket/istudybucket-backend
```

- Navigate to the application directory
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8000
```

If you are using a modern IDE like Eclipse STS, NetBeans or IntelliJ (Ultimate / Community + Plugins), then your IDE may pick up the configurations from analysing the project in its workspace, and set up all necessary prerequisites for you.


## Getting Started

Once you have successfully had the application running on your machine, you can carry on from here. Ensure you have an HTTP Client (preferably Postman) on your machine to communicate with the API.

You however do not need a specific HTTP Client as we have SwaggerUI in this application which can show you all the API endpoints and offers a simple interface for communicating with the application.

 - Open your browser and open this link to access the SwaggerUI build: {link}
 - Using SwaggerUI Client or your favorite HTTP Client, try out the application by making a simple request to this endpoint: {sample endpoint}
 - Explore more by trying out these various endpoints: {various endpoints}

## Contributing

We appreciate feedback and contribution to this repo! Before you get started, please see the following:

- [iStudyBucket's general contribution guidelines](https://github.com/istudybucket/istudybucket-backend/blob/master/GENERAL-CONTRIBUTING.md)
- [iStudyBucket's code of conduct guidelines](https://github.com/istudybucket/istudybucket-backend/blob/master/CODE-OF-CONDUCT.md)
- [This repo's contribution guide](CONTRIBUTING.md)

## Support + Feedback

Include information on how to get support. Consider adding:

- Use [Issues](https://github.com/istudybucket/istudybucket-backend/issues) for code-level support
- Use [Community](https://matrix.to/#/#istudybucket:matrix.org) for usage, questions, specific cases

<!-- TODO fix this
## Vulnerability Reporting

Please do not report security vulnerabilities on the public GitHub issue tracker. The [Responsible Disclosure Program](https://auth0.com/whitehat) details the procedure for disclosing security issues.


## What is Auth0?

Auth0 helps you to easily:

- implement authentication with multiple identity providers, including social (e.g., Google, Facebook, Microsoft, LinkedIn, GitHub, Twitter, etc), or enterprise (e.g., Windows Azure AD, Google Apps, Active Directory, ADFS, SAML, etc.)
- log in users with username/password databases, passwordless, or multi-factor authentication
- link multiple user accounts together
- generate signed JSON Web Tokens to authorize your API calls and flow the user identity securely
- access demographics and analytics detailing how, when, and where users are logging in
- enrich user profiles from other data sources using customizable JavaScript rules

[Why Auth0?](https://auth0.com/why-auth0)
-->

## Thank You!

{List out dependencies here}

## License

Link to [LICENSE](LICENSE) doc. Typically MIT but can be different for a specific platform.
