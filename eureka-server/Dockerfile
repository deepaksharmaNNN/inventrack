# ---------- Build Stage ----------
FROM amazoncorretto:25 AS builder

# Install Maven inside build stage
RUN yum install -y maven

WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:resolve dependency:resolve-plugins
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---------- Runtime Stage ----------
FROM amazoncorretto:25-alpine

WORKDIR /app
COPY --from=builder /app/target/eureka-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
