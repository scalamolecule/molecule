package molecule.db.datomic.apis

import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.sync._
import utest._
import scala.language.implicitConversions


object SyncApi extends DatomicTestSuite {

  lazy val tests = Tests {

    "Sync actions" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

//      val x = Ns.int.insert.apply(1, 2).transact
//      val List(a, b) = List(1L, 5L)
      val List(a, b) = Ns.int.insert.apply(1, 2).transact.eids
      Ns.int(3).save.transact
      Ns.int.query.get ==> List(1, 2, 3)
      Ns(a).int(10).update.transact
      Ns(b).delete.transact
      Ns.int.query.get ==> List(3, 10)
      Ns.int.query.get ==> List(3, 10)


    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
