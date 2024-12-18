package molecule.coreTests.spi.api

import molecule.base.error.{InsertErrors, ModelError, ValidationErrors}
import molecule.core.api.Api_zio
import molecule.core.spi.Spi_zio
import molecule.coreTests.dataModels.dsl.Types.Ns
import molecule.coreTests.setup.CoreTestSuite_zio
import zio._
import zio.test.TestAspect._
import zio.test._
import scala.annotation.nowarn


trait ZioApi extends CoreTestSuite_zio with Api_zio { spi: Spi_zio =>

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

      test("Transaction bundle") {
        // Mutation actions only
        for {
          _ <- transact(
            Ns.int(1).save, //         List(1)
            Ns.int.insert(2, 3), //    List(1, 2, 3)
            Ns(1).delete, //           List(2, 3)
            Ns(3).int.*(10).update, // List(2, 30)
          )
          result <- Ns.int.query.get
        } yield {
          assertTrue(result == List(2, 30))
          //          assertTrue(result == List())
        }
      }.provide(types.orDie),

      test("Unit of work") {
        // Use a unitOfWork when both mutations and queries are needed
        for {
          // Initial balance in two bank accounts
          _ <- Ns.s("fromAccount").int(100).save.transact
          _ <- Ns.s("toAccount").int(50).save.transact

          _ <- unitOfWork(
            for {
              _ <- Ns.s_("fromAccount").int.-(200).update.transact
              _ <- Ns.s_("toAccount").int.+(200).update.transact

              _ <- Ns.s_("fromAccount").int.query.get
                .flatMap {
                  // Check that fromAccount had sufficient funds
                  case i if i.head < 0 =>
                    // Abort all transactions in this unit of work
                    ZIO.fail(ModelError("Insufficient funds in fromAccountx..."))
                  case i               => ZIO.succeed(i)
                }
            } yield ()
          ).either // don't let failed ZIO fail test

          result <- Ns.s.a1.int.query.get
        } yield {
          // No data transacted
          assertTrue(result == List(
            ("fromAccount", 100),
            ("toAccount", 50),
          ))
        }
      }.provide(types.orDie),

      test("Savepoint") {
        // Use savepoint within unitOfWork to
        // rollback transactions within the savepoint body
        unitOfWork {
          for {
            _ <- Ns.int.insert(1 to 4).transact
            a <- Ns.int(count).query.get

            _ <- savepoint { sp =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                b <- Ns.int(count).query.get

                // Roll back delete
                _ = sp.rollback()
                c <- Ns.int(count).query.get
              } yield {
                assertTrue(b == List(0)) &&
                  assertTrue(c == List(4))
              }
            }

            // Nothing deleted
            d <- Ns.int(count).query.get
          } yield {
            assertTrue(a == List(4)) &&
              assertTrue(d == List(4))
          }
        }
      }.provide(types.orDie),


      test("Validation") {
        import molecule.coreTests.dataModels.dsl.Validation.Type

        Type.string("a").save.transact.flip.map {
          case ValidationErrors(errorMap) => assertTrue(
            errorMap.head._2.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
          )
        } && Type.string.insert("a").transact.flip.map {
          case InsertErrors(errors, _) => assertTrue(
            errors.head._2.head.errors.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
          )
        } && Type.string("c").save.transact.flatMap { txReport =>
          val id = txReport.ids.head
          Type(id).string("a").update.transact.flip.map {
            case ValidationErrors(errorMap) => assertTrue(
              errorMap.head._2.head ==
                s"""Type.string with value `a` doesn't satisfy validation:
                   |_ > "b"
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
            assertTrue(c == (List(2, 3), 3, false)) &&
            assertTrue(d == (List(2), 3, true))
      ).provide(types.orDie),

      test("Cursor query") {
        import molecule.coreTests.dataModels.dsl.Uniques._
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

          _ <- Ns.i.insert(3, 4).transact

          _ <- Ns(id).i(20).update.transact

          _ <- Ns(id).delete.transact

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

      //      // todo: Make more zio-idiomatic. There's some concurrency issue with setting the Ref...
      //
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
