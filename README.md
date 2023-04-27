# Word Cloud Core Microservice

This repository contains the Core microservice for the Word Cloud application, which is a part of a microservices architecture designed for easy deployment using Docker.

The Core microservice is responsible for providing a REST/JSON API for the Frontend and communicating with RabbitMQ and PostgreSQL services.

## Features

* REST/JSON API for uploading text files and retrieving word occurrence statistics.
* Communication with RabbitMQ for message queue processing.
* Integration with PostgreSQL for persistent storage.

## Technologies

* Spring Boot: Java-based framework for building the microservice.
* PostgreSQL: Database service for storing word occurrence statistics.
* RabbitMQ: Message queue service for transferring messages from Core to Worker.

## Getting Started

### Prerequisites

* Java 17 or higher.
* Gradle for building the project.

### Building the project

1. Clone the repository:

    ```
    git clone https://github.com/kenbockler/word-cloud-core.git
    ```
2. Navigate to the cloned repository folder:

    ```
    cd word-cloud-core
    ```
3. Build the project:

    ```
    ./gradlew build
    ```
### Running the application

1. Run the application:

    ```
    java -jar build/libs/word-cloud-core-1.0.0.jar
    ```
The Core microservice should now be running and accessible on port 8080.

## Contributing

If you'd like to improve or extend the project, your contributions are welcome! Please create a new branch and make a pull request with your changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
