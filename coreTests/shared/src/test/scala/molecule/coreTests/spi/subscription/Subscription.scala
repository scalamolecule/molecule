package molecule.coreTests.spi.subscription

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait Subscription extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mutations call back" - types { implicit conn =>
      var intermediaryCallbackResults = List.empty[List[Int]]
      for {
        // Initial data
        _ <- Ns.i(1).save.transact

        // Start subscription
        _ <- Ns.i.query.subscribe { freshResult =>
          intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult.sorted
        }

        // Mutations to be monitored by subscription
        id <- Ns.i(2).save.transact.map(_.id)
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 2))

        _ <- Ns.i.insert(3, 4).transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 2, 3, 4))

        _ <- Ns(id).i(20).update.transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 3, 4, 20))

        _ <- Ns(id).delete.transact
        _ <- Ns.i.a1.query.get.map(_ ==> List(1, 3, 4))

        // Mutations with no callback-involved attributes don't call back
        _ <- Ns.string("foo").save.transact

        // Callback produced all intermediary results correctly
        _ = intermediaryCallbackResults ==> List(
          List(1, 2), // query result after 2 was saved
          List(1, 2, 3, 4), // query result after 3 and 4 were inserted
          List(1, 3, 4, 20), // query result after 2 was updated to 20
          List(1, 3, 4), // query result after 20 was deleted
        )
      } yield ()
    }


    "Multiple callbacks and unsubscribe" - types { implicit conn =>
      var fooResults = List.empty[List[Int]]
      var barResults = List.empty[List[String]]
      for {
        // Initial data
        _ <- Ns.i(1).save.transact
        _ <- Ns.s("a").save.transact

        // Start subscription for fooResults
        _ <- Ns.i.query.subscribe { freshResult =>
          fooResults = fooResults :+ freshResult.sorted
        }

        // Start subscription for barResults
        barQuery = Ns.s.query
        _ <- barQuery.subscribe { freshResult =>
          barResults = barResults :+ freshResult.sorted
        }

        // Transact additional data
        _ <- Ns.i(2).save.transact
        _ <- Ns.s("b").save.transact

        _ <- Ns.i(3).save.transact
        _ <- Ns.s("c").save.transact

        // Non-matching transactions don't trigger callbacks
        _ <- Ns.string("foo").save.transact

        // Intermediary results have been pushed to correct subscriptions
        _ = fooResults ==> List(
          List(1, 2),
          List(1, 2, 3),
        )
        _ = barResults ==> List(
          List("a", "b"),
          List("a", "b", "c"),
        )

        // Cancel barResults update subscription
        _ <- barQuery.unsubscribe()

        // Mutate some more
        _ <- Ns.s("x").save.transact
        _ <- Ns.i(4).save.transact

        // After unsubscribing, barResults is no longer automatically updated (x wasn't added)
        _ = barResults ==> List(
          List("a", "b"),
          List("a", "b", "c"),
        )

        // fooResults keeps subscribing (4 was added)
        _ = fooResults ==> List(
          List(1, 2),
          List(1, 2, 3),
          List(1, 2, 3, 4),
        )
      } yield ()
    }
  }
}
