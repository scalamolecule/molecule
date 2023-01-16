package molecule.db.datomic.test

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._


      val a = (1, Some(Set(bigDecimal1, bigDecimal2)))
      val b = (2, Some(Set(bigDecimal2, bigDecimal3, bigDecimal4)))
      val c = (3, None)




      for {


        _ <- Ns.i.bigDecimals_?.insert(a, b, c).transact

        // Sets with one or more values matching

        // "Has this value"
        //          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimals_?.apply(Some(bigDecimal1)).query.get.map(_ ==> List(a))
        //          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
        //          _ <- Ns.i.a1.bigDecimals_?(Some(bigDecimal3)).query.get.map(_ ==> List(b))




      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //
    //        _ <- Ns.i.Rs1.*(R1.i).insert(0, List(1)).transact
    //
    //      } yield ()
    //    }
  }
}
