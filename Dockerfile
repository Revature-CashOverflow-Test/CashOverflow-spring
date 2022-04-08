FROM openjdk:8-jdk-alpine

# copy the generated JAR into the container to run
COPY /target/CashOverflow.jar CashOverflow.jar

# Expose port 5000 of the container
EXPOSE 5000

# Run the JAR when we run the container, thus executing the app
ENTRYPOINT ["java", "-jar", "CashOverflow.jar"]
