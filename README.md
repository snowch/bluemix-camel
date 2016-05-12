
## Set up a Spring Boot + Camel app

```
./gradlew clean build && java -jar build/libs/bluemix-docker-camel-0.1.0.jar --cloudant_username="<<your username>>" --cloudant_password="<<your password>>" --cloudant_database="<<your db>>"
```

## Containerize It

```
./gradlew build buildDocker
...

> Successfully built cab6f303cbec

export IMAGE_ID=cab6f303cbec
```

The next section is based on ref: http://heidloff.net/article/17.08.2015084655NHE9YE.htm

```
cf ic namespace get
> chstest  

export NAMESPACE=chstest
```

```
docker tag ${IMAGE_ID} registry.ng.bluemix.net/${NAMESPACE}/bluemix-docker-camel
docker push registry.ng.bluemix.net/${NAMESPACE}/bluemix-docker-camel

cf ic images

cf ic run -d -t registry.ng.bluemix.net/${NAMESPACE}/bluemix-docker-camel 

# wait some time for image to start

cf ic ps
CONTAINER ID        IMAGE                                                         COMMAND             CREATED             STATUS                  PORTS               NAMES
5ada5cfb-9f8        registry.ng.bluemix.net/chstest/bluemix-docker-camel:latest   ""                  3 minutes ago       Building a minute ago   18080/tcp           thirsty_cori

export CONTAINER_ID=5ada5cfb-9f8

cf ic ip request
> The IP address "134.168.22.62" was obtained.

export IP_ADDRESS=134.168.22.62

cf ic ip bind ${IP_ADDRESS} ${CONTAINER_ID}
```

