package molecule.datalog.datomic.test.subscription

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Subscription extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Single" - types { implicit conn =>
      var intermediaryResults = List.empty[List[Int]]
      for {
        // Initial data (not pushed)
        _ <- Ns.i(1).save.transact

        // Start subscription in separate thread on server
        _ = Ns.i.query.subscribe { freshResult =>
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


    "Multiple" - types { implicit conn =>
      var intermediaryResults1 = List.empty[List[Int]]
      var intermediaryResults2 = List.empty[List[String]]
      for {
        // Initial data (not pushed)
        _ <- Ns.i(1).save.transact
        _ <- Ns.s("a").save.transact

        // For some reason needed on JVM (concurrency related)
        _ <- delay(300)(())

        // start subscriptions in separate thread on server
        _ = Ns.i.query.subscribe { freshResult =>
          intermediaryResults1 = intermediaryResults1 :+ freshResult
        }
        _ <- delay(300)(())
        _ = Ns.s.query.subscribe { freshResult =>
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
  }
}
