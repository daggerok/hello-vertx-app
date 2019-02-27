package com.github.daggerok;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class HelloVertxVerticle extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx.vertx().deployVerticle(HelloVertxVerticle.class.getName());
  }

/*
  private static final BiFunction<String, String, String> env = (key, defaultValue) ->
      System.getenv().getOrDefault(key, defaultValue);

  private static final BiFunction<String, String, String> sys = System::getProperty;

  private static final String PORT = env.apply("PORT",
                                               sys.apply("PORT",
                                                         env.apply("SERVER_PORT",
                                                                   sys.apply("serverPort", "8080"))));
*/

  private static final BiFunction<String, String, String> props = (key, defaultValue) ->
      Stream.concat(System.getProperties().entrySet().stream(),
                    System.getenv().entrySet().stream())
            .filter(entry -> key.equals(entry.getKey()))
            .map(Map.Entry::getValue)
            .filter(Objects::nonNull)
            .map(String::valueOf)
            .filter(s -> s.trim().length() > 0)
            .findFirst()
            .orElse(defaultValue);

  private static final String PORT = props.apply("PORT",
                                                 props.apply("SERVER_PORT",
                                                             props.apply("port",
                                                                         props.apply("serverPort", "8080"))));

  @Override
  public void start() {

    vertx.createHttpServer()
         .requestHandler(req -> req.response().end("Hey!"))
         .listen(Integer.valueOf(PORT));
  }

}
