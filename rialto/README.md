#Rialto

##Running the Jena API TDBLoad

```
$> java -jar lib/vitro-rialto-1.9.3-jar-with-dependencies.jar src/test/resources/edu/stanford/grid.433695.e.nt
```

###Setting up local environment

1. Edit the file `rialto-vitro-settings.xml` to configure your local path to Tomcat and path to your local TDB data directory.
1. Stop Tomcat if it's running.
1. Run `mvn clean install`.
1. `cd` into the rialto directory and run `mvn package`. This will generate the JAR file in the `rialto/lib` directory.
1. Start up Tomcat
1. Run the custom rialto TDBLoad using the JAR as shown above.