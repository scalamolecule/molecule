package molecule.db.datomic.setup

import java.io.File
import molecule.db.datomic.marshalling.{DatomicRpcJVM, DatomicRpcServer}
import moleculeBuildInfo.BuildInfo.datomicHome
import scala.reflect.io.Directory

class MoleculeTestFramework extends utest.runner.Framework {

//  override def teardown(): Unit = {
//        println("Tearing down JVM MoleculeTestFramework")
//
////    // Delete temporary dirs when using dev-local
////    new Directory(new File(s"$datomicHome/datomic-samples-temp")).deleteRecursively()
//
//    DatomicRpcServer.system.terminate()
//
//  }
}
