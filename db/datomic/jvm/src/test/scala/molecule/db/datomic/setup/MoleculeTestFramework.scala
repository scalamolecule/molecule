package molecule.db.datomic.setup

import molecule.db.datomic.marshalling.DatomicRpcServer
import scribe.Logging

class MoleculeTestFramework extends utest.runner.Framework with Logging {

//  override def teardown(): Unit = {
//    logger.info("Tearing down JVM MoleculeTestFramework")
//
//    //    // Delete temporary dirs when using dev-local
//    //    new Directory(new File(s"$datomicHome/datomic-samples-temp")).deleteRecursively()
//
//    DatomicRpcServer.system.terminate()
//  }
}
