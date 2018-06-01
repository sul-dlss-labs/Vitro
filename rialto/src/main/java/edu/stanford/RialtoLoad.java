package edu.stanford;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;

import java.util.Iterator;

public class RialtoLoad {

    public static void main(String args[]) {

        Dataset dataset = TDBFactory.createDataset("/usr/local/vivo/home/tdbContentModels");
        String graphURI = "http://vitro.mannlib.cornell.edu/default/vitro-kb-2";
        Model model = dataset.getNamedModel(graphURI);
        dataset.begin(ReadWrite.WRITE);

        try {
            model.enterCriticalSection(Lock.WRITE);
            TDBLoader.loadModel(model, args[0]);
            dataset.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dataset.abort();
        } finally {
            model.leaveCriticalSection();
            dataset.end();
        }
        System.out.println("=======================================================================================");

        dataset.begin(ReadWrite.READ);
        try {
            Iterator<Quad> iter = dataset.asDatasetGraph().find();
            while ( iter.hasNext() ) {
                Quad quad = iter.next();
                System.out.println(quad);
            }
        } finally {
            dataset.end();
        }

    }
}
