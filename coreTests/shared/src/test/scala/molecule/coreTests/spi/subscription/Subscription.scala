package molecule.coreTests.spi.subscription

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*
import scala.language.implicitConversions

case class Subscription(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mutations call back" - types { implicit conn =>
    var intermediaryCallbackResults = List.empty[List[Int]]
    for {
      // Initial data
      _ <- Entity.i(1).save.transact

      // Start subscription and define a callback function
      _ <- Entity.i.query.subscribe { freshResult =>
        // Do something with fresh result
        intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult.sorted
      }

      // Mutations to be monitored by subscription
      id <- Entity.i(2).save.transact.map(_.id)
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 2))

      _ <- Entity.i.insert(3, 4).transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 2, 3, 4))

      _ <- Entity(id).i(20).update.transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 3, 4, 20))

      _ <- Entity(id).delete.transact
      _ <- Entity.i.a1.query.get.map(_ ==> List(1, 3, 4))

      // Mutations with no callback-involved attributes don't call back
      _ <- Entity.string("foo").save.transact

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
      _ <- Entity.i(1).save.transact
      _ <- Entity.s("a").save.transact

      // Start subscription for fooResults
      _ <- Entity.i.query.subscribe { freshResult =>
        fooResults = fooResults :+ freshResult.sorted
      }

      // Start subscription for barResults
      barQuery = Entity.s.query
      _ <- barQuery.subscribe { freshResult =>
        barResults = barResults :+ freshResult.sorted
      }

      // Transact additional data
      _ <- Entity.i(2).save.transact
      _ <- Entity.s("b").save.transact

      _ <- Entity.i(3).save.transact
      _ <- Entity.s("c").save.transact

      // Non-matching transactions don't trigger callbacks
      _ <- Entity.string("foo").save.transact

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
      _ <- Entity.s("x").save.transact
      _ <- Entity.i(4).save.transact

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
