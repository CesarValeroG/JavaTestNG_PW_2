FROM mcr.microsoft.com/playwright/java:v1.55.0-noble

WORKDIR /app

COPY pom.xml .
COPY . .

RUN apt-get update && apt-get install -y maven

CMD ["mvn", "test"]