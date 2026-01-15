# ============================================================================
# Production-Grade Multi-Stage Dockerfile for ChefApp
# ============================================================================
# Stage 1: Build - Compile and package the application
# ============================================================================
FROM maven:3.9-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy only dependency files first (for better layer caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Download dependencies (cached unless pom.xml changes)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build application (skip tests in Docker build - tests run in CI)
RUN mvn clean package -DskipTests -B

# ============================================================================
# Stage 2: Runtime - Minimal production image
# ============================================================================
FROM eclipse-temurin:17-jre-jammy AS runtime

# Security: Create non-root user to run application
RUN groupadd -r chefapp && useradd -r -g chefapp chefapp

# Install security updates and required packages
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y --no-install-recommends curl && \
    rm -rf /var/lib/apt/lists/* && \
    apt-get clean

# Set working directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/chefapp-*.jar app.jar

# Change ownership to non-root user
RUN chown -R chefapp:chefapp /app

# Switch to non-root user (security best practice)
USER chefapp

# Expose application port
EXPOSE 8080

# Health check endpoint (Spring Boot Actuator recommended, but basic check for now)
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD curl -f http://localhost:8080/ || exit 1

# Use exec form to ensure proper signal handling
ENTRYPOINT ["java", \
    "-Djava.security.egd=file:/dev/./urandom", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75.0", \
    "-XX:+ExitOnOutOfMemoryError", \
    "-jar", \
    "app.jar"]
