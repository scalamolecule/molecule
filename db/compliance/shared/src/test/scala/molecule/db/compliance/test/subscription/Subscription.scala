package molecule.db.compliance.test.subscription

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Platform}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class Subscription(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils with Platform {

  import api.*
  import suite.*

  "Mutations call back" - types { implicit conn =>
    var intermediaryResults = List.empty[List[Int]]
    for {
      // Initial data
      _ <- Entity.i(1).save.transact

      // Start subscription and define a callback function
      _ <- Entity.i.query.subscribe { updatedResult =>
        // Do something with updated result
        intermediaryResults = intermediaryResults :+ updatedResult.sorted
      }

      // When calling from ScalaJS, calls are asynchronous, and we need to wait
      // a bit for the subscription websocket to be ready to serve callbacks.
      _ <- delay(1000)

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

      _ <- delay(1000)

      // Callback produced all intermediary results correctly
      _ = intermediaryResults ==> List(
        List(1, 2), //        query result after 2 was saved
        List(1, 2, 3, 4), //  query result after 3 and 4 were inserted
        List(1, 3, 4, 20), // query result after 2 was updated to 20
        List(1, 3, 4), //     query result after 20 was deleted
      )
    } yield ()
  }


  "Multiple callbacks and unsubscribe" - types { implicit conn =>
    var intResults    = List.empty[List[Int]]
    var stringResults = List.empty[List[String]]
    for {
      // Initial data
      _ <- Entity.i(1).save.transact
      _ <- Entity.s("a").save.transact

      // Start subscription for intResults
      _ <- Entity.i.query.subscribe { updatedInts =>
        intResults = intResults :+ updatedInts.sorted
      }

      // Start subscription for stringResults
      stringQuery = Entity.s.query
      _ <- stringQuery.subscribe { updatedStrings =>
        stringResults = stringResults :+ updatedStrings.sorted
      }

      // When calling from ScalaJS, calls are asynchronous, and we need to wait
      // a bit for the subscription websockets to be ready to serve callbacks.
      _ <- delay(1000)

      // Transact additional data
      _ <- Entity.i(2).save.transact
      _ <- Entity.s("b").save.transact

      _ <- Entity.i(3).save.transact
      _ <- Entity.s("c").save.transact

      // Non-matching transactions don't trigger callbacks
      _ <- Entity.string("foo").save.transact

      // Intermediary results have been pushed to correct subscriptions
      _ = intResults ==> List(
        List(1, 2),
        List(1, 2, 3),
      )
      _ = stringResults ==> List(
        List("a", "b"),
        List("a", "b", "c"),
      )

      // Cancel stringResults update subscription
      _ <- stringQuery.unsubscribe()

      // Mutate some more
      _ <- Entity.s("x").save.transact
      _ <- Entity.i(4).save.transact

      // After unsubscribing, stringResults is no longer automatically updated (x wasn't added)
      _ = stringResults ==> List(
        List("a", "b"),
        List("a", "b", "c"),
      )

      // intResults keeps subscribing (4 was added)
      _ = intResults ==> List(
        List(1, 2),
        List(1, 2, 3),
        List(1, 2, 3, 4),
      )
    } yield ()
  }
}
