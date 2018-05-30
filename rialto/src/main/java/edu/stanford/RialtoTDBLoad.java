package edu.stanford;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.sparql.core.Quad;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.tdb.TDBLoader;
import com.hp.hpl.jena.tdb.sys.TDBInternal;

import java.util.Iterator;

public class RialtoTDBLoad {

    public static void main(String args[]) {

        Dataset dataset = TDBFactory.createDataset("/usr/local/vivo/home/tdbContentModels");
        dataset.begin(ReadWrite.WRITE);

        try {
            TDBLoader.load(TDBInternal.getBaseDatasetGraphTDB(dataset.asDatasetGraph()), args[0], true);
            dataset.commit();
        } catch (Exception e) {
            dataset.abort();
        } finally {
            dataset.end();
        }

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
