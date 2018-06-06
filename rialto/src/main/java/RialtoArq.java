//package edu.stanford;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.UpdateRequest;
import com.hp.hpl.jena.update.UpdateAction;
import com.hp.hpl.jena.update.UpdateFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RialtoArq {

    public static void main(String args[]) {

        // args[0] is expected to be the tcdContentModels directory, e.g.
        // /usr/local/vitro/home/tdbContentModels       OR    /opt/app/vitro/home/tdbContentModels
        Dataset dataset = TDBFactory.createDataset(args[0]);
        String graphURI = "<http://vitro.mannlib.cornell.edu/default/vitro-kb-2>";
        dataset.begin(ReadWrite.WRITE);

        UpdateRequest request = UpdateFactory.create();
        request.add("DROP ALL");
        request.add("CREATE GRAPH " + graphURI);

        System.out.println("About to start loading...");

        // <file:/Users/tommyi/Documents/projects/rialto/github/rialto-etl/sample_data/vivo/agents/zz572.nt>
        try {
            // args[1] is expected to be the agents directory in the sample data set, e.g.
            // /Users/tommyi/Documents/projects/rialto/github/rialto-etl/sample_data/vivo/agents         OR
            // /opt/app/vitro/data/rialto-sample-data/vivo/agents
            Files.walk(Paths.get(args[1]))
                    .filter(s -> s.toString().endsWith(".nt"))
                    .map(Path::toString)
                    .sorted()
                    .forEach(path -> {
                        System.out.println(path);
                        request.add("LOAD <file:" + path + "> INTO GRAPH " + graphURI); } );




            UpdateAction.execute(request, dataset);
            dataset.commit();
            dataset.end();

            System.out.println("Done!");
        }
        catch (Exception e) {
            e.printStackTrace();
            dataset.abort();
            dataset.end();
        }
    }
}
