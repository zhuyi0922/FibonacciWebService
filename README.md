# Fibonacci Calculator RESTful Web Service

This project is a simple RESTful web service designed to calculate Fibonacci numbers. It consists of three main components: a Dockerfile for containerization, Java source code with a JAR package for the backend, and a React application for the frontend.

Here is the Url for demo: http://54.172.119.14:3000/

The Api accessment url: http://54.172.119.14:8080/fibonacci

## Components

### 1. Java code

The Java source code for the backend is located in the `src` directory. And the jar package in the `target` directory. You can run the source code in the Idea, but be noticed that you need to edit the configuration with Program arguements: `server OracleTest\src\main\resources\webApp.yml`

Alternatively, you can run the jar package with command:

```bash
java -jar OracleTest-1.0-SNAPSHOT.jar server OracleTest\src\main\resources\webApp.yml
```

#### 2. **React Application**

The React application is located in the `ReactFile/my-fibonacci-app` directory. To start the React application, use the following command:

```bash
# cd into the react directory first
npm install
npm start
```

### 3. Docker

The Dockerfile in the project root directory allows you to build a Docker image for the entire application. There are two Dockerfile inside this project, one in the `Dockerfile` directory, and another one in the `ReactFile/my-fibonacci` redirectory. All the files need to be loaded into image are in the same direcotry with Dockerfile. To build and run the Docker image, use the following command:

```bash
# cd into the docker directory first
docker build -t fiboapp .
docker run -d -p 8080:8080 fiboapp
```

And also the image of React App

```bash
docker build -t react .
docker run -d -p 3000:3000 react
```

Then you can access it by host

### Usage

1. You can run the source code as above.
  
2. Alternatively, Just run the docker image.
