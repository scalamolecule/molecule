package molecule.db.datomic.setup

import boopickle.Default._
import molecule.core.marshalling.WebClient

class MoleculeTestFramework extends utest.runner.Framework with WebClient {

  //  override def setup(): Unit = {
  //    //    println("Setting up JS MoleculeTestFramework")
  //  }

  //  override def teardown(): Unit = {
  //    //    println("Tearing down JS MoleculeTestFramework")
  //
  //    // Clear connection pool after each test suite run
  //    moleculeAjax("localhost", 8080).wire[MoleculeRpc].clearConnPool
  //  }
}
