
## Set up a Spring Boot + Camel app

```
./gradlew build 

java -jar build/libs/gs-spring-boot-docker-0.1.0.jar &

sleep 5

curl http://localhost:18080 
```

## Containerize It

```
./gradlew build buildDocker
```
