# ZoomInOutImageProject
READ ME

This project is a distributed microservices application designed to process images with zoom in/out functionality. It leverages a Java EE architecture, JMS messaging, RMI services, and a Node.js REST API, all orchestrated with Docker and deployed on a Google Cloud VM.

🗂️ Architecture Overview
The system consists of six Docker containers working together:

C01: Java Javalin/Apache Tomcat Microservice

Provides a REST endpoint to accept image upload requests.

Publishes processing tasks via JMS to the broker.

Notifies when processing is complete.

C02: Apache TomEE JMS Broker

Acts as a message broker with Topics & Queues.

Manages communication between microservices.

C03: Apache TomEE EJB Client & REST API

Consumes JMS messages.

Processes image zoom tasks.

Stores processed images in the database via the Node.js REST API.

Publishes a message when a job is done.

C04 & C05: Apache TomEE RMI Servers

Provide distributed RMI objects for heavy image processing.

Work together to zoom and transform images.

C06: Node.js REST API with MongoDB

Exposes two REST endpoints:

One for DB operations.

One for serving images to the frontend.

Stores and retrieves processed images.

Frontend

A simple HTML page that allows users to upload an image and download the processed result when it’s ready.

☁️ Deployment Details
All containers are orchestrated with Docker Compose and run on a Google Cloud VM Instance.

The backend microservices are implemented as Eclipse Dynamic Web Projects using Java EE 10 with Apache Tomcat/TomEE.

Communication between services uses JMS, RMI, and REST APIs.

The database uses MongoDB for storing image metadata and files.

⚙️ Tech Stack
Java JDK 11

Apache Tomcat 10 / Apache TomEE 10

Javalin framework

JMS (Java Message Service)

EJB & RMI

Node.js & Express.js

MongoDB

Docker & Docker Compose

Google Cloud VM

✅ Key Features
Distributed microservices architecture.

Asynchronous image processing with JMS.

Scalable RMI-based image transformations.

RESTful API for storage and download.

Containerized deployment for easy scaling and isolation.

