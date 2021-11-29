#Overview
This project is a part of the zendesk coding challenge. Zendesk is a customer service tool that 
allows the creation and management of support tickets. We aim to
build a ticket viewer that uses APIs provided by zendesk to
support required functionality.

#List of Features
1. List all tickets for an account.
2. Display individual ticket details.
3. support for pagination when ticket count is more than 25 with options to go to next and previous pages.
4. Option to reload data for current page.
5. Minimal UI to support above functionality.

#How to Run
The application can be run in 2 ways
1. Packaging as a .war file and then running the .war file.
   1. Open a terminal at the base directory (The directory containing pom.xml).
   2. run the command _mvn clean package_. Needs maven to be installed.
   3. A war file will be generated in the target folder.
   4. Open a terminal window in the target folder, and run the command _java -jar zcc-0.0.1-SNAPSHOT.war --user xxx --pass xxx --sub_domain xxx_.
   5. Access [http://localhost/](http://localhost/) on the browser to access the application.
2. Directly running without packaging.
   1. Open a terminal at the base directory (The directory containing pom.xml).
   2. run the command _mvn spring-boot:run -Dspring-boot.run.arguments="--user=xxx --pass=xxx --sub_domain=xxx"_.
   3. Access [http://localhost/](http://localhost/) on the browser to access the application.

#Limitations
1. The application is not secure, and does not authenticate before giving access to tickets.
2. The user interface is very basic and minimal.

#Citations
1. https://spring.io/guides/gs/spring-boot/
