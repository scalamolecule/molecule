package molecule.db.datomic.setup

import boopickle.Default._
import molecule.core.marshalling.Boopicklers._
import molecule.core.marshalling.{MoleculeRpc, WebClient}
import scribe.Logging

class MoleculeTestFramework extends utest.runner.Framework with WebClient with Logging {

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
