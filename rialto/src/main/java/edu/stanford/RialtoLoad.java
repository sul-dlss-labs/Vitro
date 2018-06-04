package edu.stanford;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RialtoLoad {

    public static void main(String args[]) {

        // args[0] is expected to be the tcdContentModels directory, e.g.
        // /usr/local/vitro/home/tdbContentModels       OR    /opt/app/vitro/home/tdbContentModels
        Dataset dataset = TDBFactory.createDataset(args[0]);
        String graphURI = "http://vitro.mannlib.cornell.edu/default/vitro-kb-2";
        Model model = dataset.getNamedModel(graphURI);
        dataset.begin(ReadWrite.WRITE);

        try {
            model.enterCriticalSection(Lock.WRITE);


            // args[1] is expected to be the agents directory in the sample data set, e.g.
            // /Users/tommyi/Documents/projects/rialto/github/rialto-etl/sample_data/vivo/agents         OR
            // /opt/app/vitro/data/rialto-sample-data/vivo/agents
            List<String> filesToLoad = Files.walk(Paths.get(args[1]))
                                          .filter(s -> s.toString().endsWith(".nt"))
                                          .map(Path::toString)
                                          .sorted()
                                          .collect(toList());

            TDBLoader.loadModel(model, filesToLoad, true);
            dataset.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dataset.abort();
        } finally {
            model.leaveCriticalSection();
            dataset.end();
        }
    }
}
