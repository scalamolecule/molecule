package molecule.db.datomic.setup

import boopickle.Default._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.Boopicklers._

class MoleculeTestFramework extends utest.runner.Framework with MoleculeLogging {

//  override def setup(): Unit = {
//    logger.info("Setting up JS MoleculeTestFramework")
//  }
//
//  override def teardown(): Unit = {
//    logger.info("JS MoleculeTestFramework.teardown()")
//
//    // Clear connection pool after each test suite run
//    moleculeAjax("localhost", 8080).wire[MoleculeRpc].clearConnPool
//  }
}
