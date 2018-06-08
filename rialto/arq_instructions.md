1. Stop Tomcat (`sudo /sbin/service tomcat stop`)
2. Delete the contents of /usr/local/vitro/home/tdbContentModels and /usr/local/vitro/home/tdbModels
3. Compile and run the Java class as below:

```
github/Vitro/rialto(73-tdb-load)$ javac -cp "lib/*" src/main/java/RialtoArq.java
github/Vitro/rialto(73-tdb-load)$ mv src/main/java/RialtoArq.class .
github/Vitro/rialto(73-tdb-load)$ time java -cp "lib/*:." RialtoArq /usr/local/vitro/home/tdbContentModels  /Users/tommyi/Documents/projects/rialto/github/rialto-etl/sample_data/vivo/agents

on the server:
cd current/rialto
time java -cp "lib/*:." RialtoArq ~/home/tdbContentModels ~/data/rialto-sample-data/vivo/agents
```

4. Start Tomcat (`sudo /sbin/service tomcat start`)
5. Verify using e.g.
```sparql
SELECT ?s ?p ?o
WHERE
{
  GRAPH <http://vitro.mannlib.cornell.edu/default/vitro-kb-2> { ?s ?p ?o }
}
LIMIT 1000
```

