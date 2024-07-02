package molecule.datalog.datomic

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._


object AdhocJS_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {

        id <- Ns.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).intMap.remove(string1).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).intMap.add(pint1, pint2, pint3, pint4, pint5, pint6, pint7).upsert.transact

        // Remove pair by String key
        _ <- Ns(id).intMap.remove(string7).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //
    //        a <- A.iSeq(Seq(1, 2)).save.transact.map(_.id)
    //
    //      } yield ()
    //    }
  }
}
