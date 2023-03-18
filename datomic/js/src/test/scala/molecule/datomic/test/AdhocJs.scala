package molecule.datomic.test

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._


object AdhocJs extends DatomicTestSuite {

  lazy val tests = Tests {

    "Single" - types { implicit conn =>
      var intermediaryResults = List.empty[List[Int]]
      for {
        // Initial data (not pushed)
        _ <- Ns.i(1).save.transact

        // Start subscription in separate thread on server
        _ = Ns.i.query.subscribe { freshResult =>
//          println("====== callback 0: " + freshResult)
          intermediaryResults = intermediaryResults :+ freshResult
        }
        // Wait for subscription thread to startup to propagate first result
        _ <- delay(300)(())

        // Make changes to generate new results to be pushed
        _ <- Ns.i(2).save.transact
        _ <- Ns.i(3).save.transact

        // Non-matching transactions don't trigger callbacks
        _ <- Ns.string("foo").save.transact

        // Wait for subscription thread to propagate last result
        _ <- delay(300)(
          intermediaryResults ==> List(
            List(1, 2), // query result after 2 was added
            List(1, 2, 3), // query result after 3 was added
          )
        )
      } yield ()
    }

    "types" - types { implicit conn =>
      var intermediaryResults1 = List.empty[List[Int]]
      var intermediaryResults2 = List.empty[List[String]]
      for {
        // Initial data (not pushed)
        _ <- Ns.i(1).save.transact
        _ <- Ns.s("a").save.transact

        _ <- delay(300)(())

        // start subscriptions in separate thread on server
        _ = Ns.i.query.subscribe { freshResult =>
//          println("====== callback 1: " + freshResult)
          intermediaryResults1 = intermediaryResults1 :+ freshResult
        }
        _ <- delay(300)(())
        _ = Ns.s.query.subscribe { freshResult =>
//          println("====== callback 2: " + freshResult)
          intermediaryResults2 = intermediaryResults2 :+ freshResult
        }

        // Wait for subscription thread to startup to propagate first result
        _ <- delay(300)(())

        // Transact additional data in any order
        _ <- Ns.i(2).save.transact
        _ <- Ns.s("b").save.transact
        _ <- Ns.i(3).save.transact
        _ <- Ns.s("c").save.transact

        // Non-matching transactions don't trigger callbacks
        _ <- Ns.string("foo").save.transact

        // Wait for subscription thread to propagate last result
        _ <- delay(300)(())

        // Intermediary results have been pushed to correct subscriptions
        _ = intermediaryResults1 ==> List(
          List(1, 2),
          List(1, 2, 3),
        )
        _ = intermediaryResults2 ==> List(
          List("a", "b"),
          List("a", "b", "c"),
        )
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
