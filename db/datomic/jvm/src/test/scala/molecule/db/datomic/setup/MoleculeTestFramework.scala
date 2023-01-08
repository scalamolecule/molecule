package molecule.db.datomic.setup

import molecule.boilerplate.util.MoleculeLogging

class MoleculeTestFramework extends utest.runner.Framework with MoleculeLogging {

//  override def teardown(): Unit = {
//    logger.info("Tearing down JVM MoleculeTestFramework")
//
//    //    // Delete temporary dirs when using dev-local
//    //    new Directory(new File(s"$datomicHome/datomic-samples-temp")).deleteRecursively()
//
//    DatomicRpcServer.system.terminate()
//  }
}
