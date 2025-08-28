# ---- Build stage ----
FROM gradle:8.8-jdk21-alpine AS build
WORKDIR /workspace
COPY . .
# Use BuildKit caching so Gradle doesn't download deps every time
RUN --mount=type=cache,target=/root/.gradle \
    gradle --no-daemon clean bootJar

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/build/libs/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

