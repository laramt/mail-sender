<h1 align="center">Email Sender</h1>
<p align="center">A simple repository for a Java application built with Spring and Thymeleaf to send emails using SMTP. This README provides a brief overview of the project and its functionalities.
</p>
<p align="center">
  <a href="https://www.java.com">
    <img src="https://img.shields.io/badge/Java-17-yellow.svg">
  </a>
  <a href="https://spring.io/">
    <img src="https://img.shields.io/badge/Spring-3.0.6-green.svg">
  </a>
</p>


## Table of Contents

- [Introduction](#introduction)
- [Functionalities](#functionalities)
- [Installation](#installation)
- [Contact](#contact)

## Introduction

Email Sender is a Java application that allows you to send emails using the SMTP protocol. It utilizes Spring and Thymeleaf to create and send various types of emails.

## Functionalities

The following functionalities are available in this application:

-  Sends manually written emails.
-  Sends emails using templates, with or without attachment.
-  Retrieves all emails in a pageable format.
-  Retrieves an email by its ID.

## Installation

To run this Java Spring project locally, please follow the instructions below:

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Maven

### Steps

1. Clone the repository to your local machine:

```bash
  git clone <https://github.com/laramt/mail-sender>
  ````
2. Navigate to the project directory:

````bash
cd <email-sender>
````

3. Build the project using Maven:
 ````bash
mvn clean install
 ````

4. Configure the application properties:

  - Open the `application.properties` file located in the src/main/resources directory.
  - Update the necessary configuration properties such as the database connection details, server port, etc., based on your environment.
  - Create and configure the `emailconfig.properties` file with the required SMTP settings.


Please note that the configuration file for SMTP settings is not available in this repository for security reasons, as it contains private information.
Here's a snippet of the code you can use as a reference:


```` properties
# EMAIL
spring.mail.host=smtp.your_smtp_host
spring.mail.port=your_smtp_port
spring.mail.username= your_username
spring.mail.password= your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

````

Replace your_smtp_host, your_smtp_port, your_username, and your_password with your specific SMTP server details. Ensure that you have the appropriate credentials and SMTP server information from your email service provider.

5. Run the application


## Contact

If you have any questions or feedback regarding the Email Sender application, please feel free to reach out [here](mailto:your_email@example.com).

