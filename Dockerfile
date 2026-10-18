# ---- Stage 1: Build ----
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src ./src
RUN mvn clean package -DskipTests -q

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -S devops && adduser -S devops -G devops
COPY --from=builder /build/target/devops-demo-1.0.0.jar app.jar
RUN chown devops:devops app.jar
USER devops
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
