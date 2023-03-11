#mvn clean package
#docker build -t video-library-2 .
#docker-compose up -d

./mvnw clean install -Dmaven.test.skip=true
cd service
docker run --name p3 -p 5433:5432 -e POSTGRES_PASSWORD=pass -d postgres
java -jar service/target/video-library-2.jar
