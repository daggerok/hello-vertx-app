# hello-vertx-app
Vert.x getting started quick guide on `Windows OS` by using archetype generator from `maven-fabric8-plugin`

## create a project

```batch
mkdir hello-vertx-app
cd hello-vertx-app\

mvn -N io.takari:maven:wrapper -Dmaven=3.6.0

mvnw io.fabric8:vertx-maven-plugin:1.0.13:setup^
  -DprojectGroupId=com.github.daggerok^
  -DprojectArtifactId=hello-vertx-app^
  -Dverticle=com.github.daggerok.HelloVertxVerticle

mvnw compile vertx:run
```

or just run main method in your IDE...

## test

Lastly, update verticle business logic and test things out:

```batch
λ http :8080
HTTP/1.1 200 OK
Content-Length: 4

Hey!
```

or open in your browser http://127.0.0.1:8080 link

[read Building Reactive Microservices in Java book](./building_reactive_microservices_in_java.pdf)
