package molecule.db.datomic.test

import boopickle.Default._
import molecule.base.util.exceptions.MoleculeException
import molecule.core.marshalling.{ConnProxy, DatomicPeerProxy}
import molecule.core.transaction.Save
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.schema.TypesSchema
import molecule.db.datomic.facade.DatomicConn_JS
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.transaction.Save_edn
import utest._
//import molecule.db.datomic.RpcTester._
import molecule.boilerplate.ast.Model._
import molecule.db.datomic._
import molecule.core.util.Executor._


object AdhocJs extends DatomicTestSuite {

//  val aa: Array[Int] = Array.emptyIntArray
//  val aa: Array[Int] = new Array[Int](0)
//  val aa: Array[Int] = Array.empty[Int]

  lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        _ <- Ns.i(7).save.transact //.map(_ ==> 7)
        _ <- Ns.i.query.get.map(_ ==> List(7))
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      //      Ns.i.v.Self.i(v).s
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }
}
