package molecule.datomic.test

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._


object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    "types" - types { implicit conn =>

      var intermediaryResults1 = List.empty[List[Int]]
      var intermediaryResults2 = List.empty[List[String]]
      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))

        // start subscription in separate thread on server
        _ = Ns.i.query.subscribe { freshResult =>
          println("====== callback 1: " + freshResult)
          intermediaryResults1 = intermediaryResults1 :+ freshResult
        }
        //        _ = Ns.string.query.subscribe { freshResult =>
        //          println("====== callback 2: " + freshResult)
        //          intermediaryResults2 = intermediaryResults2 :+ freshResult
        //        }
        // Wait for subscription thread to startup to propagate first result
        _ <- delay(500)()
        _ <- Ns.i(2).save.transact
        _ <- Ns.i(3).save.transact

        _ <- Ns.string("yeah").save.transact

        // Wait for subscription thread to propagate last result to client
        _ <- delay(50)(
          intermediaryResults1 ==> List(
            List(1, 2), // query result after 2 was added
            List(1, 2, 3), // query result after 3 was added
          )
        )
        //        _ <- delay(50)(
        //          intermediaryResults2 ==> List(
        //            List("yeah"), // query result after 2 was added
        //          )
        //        )
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
