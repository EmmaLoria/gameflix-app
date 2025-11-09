## GameFlix Backend - Docker Instructions

### Build the Docker image

```bash
./mvnw clean package -DskipTests
docker build -t gameflix-backend .
