package molecule.coreTests.spi.api

import molecule.base.error._
import molecule.core.api.ApiZio
import molecule.core.spi.SpiZio
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.coreTests.zio._
import zio._
import zio.test.TestAspect._
import zio.test._
import scala.annotation.nowarn


trait ZioApi extends CoreTestZioSpec with ApiZio { spi: SpiZio =>

  @nowarn override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Molecule ZIO api")(
      test("Crud actions") {
        for {
          ids <- Ns.int.insert(1, 2).transact.map(_.ids)
          _ <- Ns.int(3).save.transact
          a <- Ns.int.a1.query.get
          _ <- Ns(ids(0)).int(10).update.transact
          _ <- Ns(ids(1)).delete.transact
          b <- Ns.int.a1.query.get
        } yield {
          assertTrue(ids.size == 2) &&
            assertTrue(a == List(1, 2, 3)) &&
            assertTrue(b == List(3, 10))
        }
      }.provide(types.orDie),

      test("Error handling") {
        import molecule.coreTests.dataModels.core.dsl.Validation.Type

        Type.string("a").save.transact.flip.map {
          case ValidationErrors(errorMap) => assertTrue(
            errorMap.head._2.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
          )
        } && Type.string.insert("a").transact.flip.map {
          case InsertErrors(errors, _) => assertTrue(
            errors.head._2.head.errors.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
          )
        } && Type.string("c").save.transact.flatMap { txReport =>
          val id = txReport.ids.head
          Type(id).string("a").update.transact.flip.map {
            case ValidationErrors(errorMap) => assertTrue(
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "b"
                   |""".stripMargin
            )
          }
        }
      }.provide(validation.orDie),

      test("Inspection") {
        for {
          ids <- Ns.int.insert(1, 2).transact.map(_.ids) // Need data for update and delete
          _ <- Ns.int.insert(1, 2).inspect
          _ <- Ns.int(3).save.inspect
          _ <- Ns.int.query.inspect
          _ <- Ns.int.query.get
          _ <- Ns(ids(0)).int(10).update.inspect
          _ <- Ns(ids(1)).delete.inspect
        } yield assertTrue(true)
      }.provide(types.orDie),

      test("Offset query")(
        for {
          _ <- Ns.int.insert(1, 2, 3).transact
          a <- Ns.int.a1.query.get
          b <- Ns.int.a1.query.limit(2).get
          c <- Ns.int.a1.query.offset(1).get
          d <- Ns.int.a1.query.offset(1).limit(1).get
        } yield
          assertTrue(a == List(1, 2, 3)) &&
            assertTrue(b == List(1, 2)) &&
            assertTrue(c == (List(2, 3), 3, true)) &&
            assertTrue(d == (List(2), 3, true))
      ).provide(types.orDie),

      test("Cursor query") {
        import molecule.coreTests.dataModels.core.dsl.Uniques._
        val query = Uniques.int.a1.query
        for {
          _ <- Uniques.int.insert(1, 2, 3, 4, 5).transact
          c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
          c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
          c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
          c2 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
          res <- query.from(c2).limit(-2).get
        } yield {
          assertTrue(res._1 == List(1, 2) && !res._3)
        }
      }.provide(unique.orDie),

      test("Subscription") {
        var intermediaryCallbackResults = List.empty[List[Int]]
        for {
          // Initial data (not pushed)
          _ <- Ns.i(1).save.transact

          // Start subscription in separate thread
          _ <- Ns.i.query.subscribe { freshResult =>
            intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
          }

          // Mutations to be monitored by subscription
          id <- Ns.i(2).save.transact.map(_.id)

          // For testing purpose, allow each mutation to finish so that we can
          // catch the intermediary callback result in order
          _ <- delay(50)(())

          _ <- Ns.i.insert(3, 4).transact
          _ <- delay(50)(())

          _ <- Ns(id).i(20).update.transact
          _ <- delay(50)(())

          _ <- Ns(id).delete.transact
          _ <- delay(50)(())

          // Mutations with no callback-involved attributes don't call back
          _ <- Ns.string("foo").save.transact

          // Allow callbacks running in parallel to finish
          //          _ <- TestClock.adjust(50.milliseconds) // doesn't work on first run on JS, hmm..
        } yield {
          assertTrue(intermediaryCallbackResults.map(_.sorted).toSet == Set(
            List(1, 2), // query result after 2 was saved
            List(1, 2, 3, 4), // query result after 3 and 4 were inserted
            List(1, 3, 4, 20), // query result after 2 was updated to 20
            List(1, 3, 4), // query result after 20 was deleted
          ))
        }
      }.provide(types.orDie),

      // todo: Make more zio-idiomatic. There's some concurrency issue with setting the Ref...

      //      test("Subscription") {
      //        for {
      //          r <- zio.Ref.make(List.empty[List[Int]])
      //          // _ <- r.getAndUpdate(_ :+ List(42)) // this works fine
      //          _ <- Ns.i(1).save.transact
      //          // start subscription in separate thread
      //          _ <- Ns.i.query.subscribe { freshResult =>
      //            println("####### " + freshResult)
      //            // todo: why is the ref not updated?
      //            r.getAndUpdate(_ :+ freshResult) // in another thread/fiber?
      //          }
      //          _ <- Ns.i(2).save.transact
      //          _ <- Ns.i(3).save.transact
      //          // Allow subscription thread to catch up
      //          _ <- TestClock.adjust(200.milliseconds)
      //          intermediaryResults <- r.get
      //        } yield {
      //          assertTrue(intermediaryResults == List(
      //            List(1, 2), // query result after 2 was added
      //            List(1, 2, 3) // query result after 3 was added
      //          ))
      //        }
      //      }.provide(types.orDie),

    ) @@ sequential
}
