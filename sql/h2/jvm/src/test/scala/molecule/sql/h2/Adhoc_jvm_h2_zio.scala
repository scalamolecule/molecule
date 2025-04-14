package molecule.sql.h2

import molecule.coreTests.domains.dsl.Types.Entity
import molecule.sql.h2.Zio._
import molecule.sql.h2.setup.DbProviders_h2_zio
import zio._
import zio.test.TestAspect._
import zio.test._
import scala.annotation.nowarn
import scala.language.implicitConversions


object Adhoc_jvm_h2_zio extends ZIOSpecDefault with DbProviders_h2_zio {

  @nowarn override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Molecule ZIO api")(

      test("Streaming") {
        for {
          _ <- Entity.i.insert(1, 2, 3).transact
          chunk <- Entity.i.query.stream.runCollect
        } yield {
          assertTrue(chunk.toList == List(1, 2, 3))
        }
      }.provide(types.orDie),


      //      test("Crud actions") {
      //        for {
      //          ids <- Entity.int.insert(1, 2).transact.map(_.ids)
      //          _ <- Entity.int(3).save.transact
      //          a <- Entity.int.a1.query.get
      //          _ <- Entity(ids(0)).int(10).update.transact
      //          _ <- Entity(ids(1)).delete.transact
      //          b <- Entity.int.a1.query.get
      //        } yield {
      //          assertTrue(
      //            ids.size == 2
      //          ) && assertTrue(
      //            a == List(1, 2, 3)
      //          ) && assertTrue(
      //            b == List(3, 10)
      //          )
      //        }
      //      }.provide(types.orDie),
      //      test("Opt ref") {
      //        import molecule.coreTests.domains.dsl.Refs._
      //        for {
      //          _ <- A.i(1).save.transact
      //
      //          // Optional card-one ref (SQL left join)
      //          a <- A.i.B.?(B.i).query.get
      //
      //          _ <- A.i(2).B.i(3).s("b").save.transact
      //
      //          // Optional card-one ref (SQL left join)
      //          b <- A.i.a1.B.?(B.i.s).query.i.get
      //        } yield {
      //          assertTrue(a == List(
      //            (1, None)
      //          )) && assertTrue(b == List(
      //            (1, None),
      //            (2, Some((3, "b"))),
      //          ))
      //        }
      //      }.provide(refs.orDie),
      //
      //
      //      test("Validation") {
      //        import molecule.coreTests.domains.dsl.Validation.Type
      //        Type.string("a").save.transact.flip.map {
      //          case ValidationErrors(errorMap) => assertTrue(
      //            errorMap.head._2.head ==
      //              s"""Type.string with value `a` doesn't satisfy validation:
      //                 |_ > "b"
      //                 |""".stripMargin
      //          )
      //        } && Type.string.insert("a").transact.flip.map {
      //          case InsertErrors(errors, _) => assertTrue(
      //            errors.head._2.head.errors.head ==
      //              s"""Type.string with value `a` doesn't satisfy validation:
      //                 |_ > "b"
      //                 |""".stripMargin
      //          )
      //        } && Type.string("c").save.transact.flatMap { txReport =>
      //          val id = txReport.ids.head
      //          Type(id).string("a").update.transact.flip.map {
      //            case ValidationErrors(errorMap) => assertTrue(
      //              errorMap.head._2.head ==
      //                s"""Type.string with value `a` doesn't satisfy validation:
      //                   |_ > "b"
      //                   |""".stripMargin
      //            )
      //          }
      //        }
      //      }.provide(validation.orDie),
      //
      //
      //      test("Inspection") {
      //        for {
      //          ids <- Entity.int.insert(1, 2).transact.map(_.ids) // Need data for update and delete
      //          _ <- Entity.int.insert(1, 2).inspect
      //          _ <- Entity.int(3).save.inspect
      //          _ <- Entity.int.query.inspect
      //          _ <- Entity.int.query.get
      //          _ <- Entity(ids(0)).int(10).update.inspect
      //          _ <- Entity(ids(1)).delete.inspect
      //        } yield assertTrue(true)
      //      }.provide(types.orDie),
      //
      //
      //      test("Offset query")(
      //        for {
      //          _ <- Entity.int.insert(1, 2, 3).transact
      //          a <- Entity.int.a1.query.get
      //          b <- Entity.int.a1.query.limit(2).get
      //          c <- Entity.int.a1.query.offset(1).get
      //          d <- Entity.int.a1.query.offset(1).limit(1).get
      //        } yield
      //          assertTrue(a == List(1, 2, 3)) &&
      //            assertTrue(b == List(1, 2)) &&
      //            assertTrue(c == (List(2, 3), 3, false)) &&
      //            assertTrue(d == (List(2), 3, true))
      //      ).provide(types.orDie),
      //
      //
      //      test("Cursor query") {
      //        import molecule.coreTests.domains.dsl.Uniques._
      //        val query = Uniques.int.a1.query
      //        for {
      //          _ <- Uniques.int.insert(1, 2, 3, 4, 5).transact
      //          c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
      //          c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
      //          c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
      //          c2 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
      //          res <- query.from(c2).limit(-2).get
      //        } yield {
      //          assertTrue(res._1 == List(1, 2) && !res._3)
      //        }
      //      }.provide(unique.orDie),
      //
      //
      //      test("Subscription") {
      //        var intermediaryCallbackResults = List.empty[List[Int]]
      //        for {
      //          // Initial data (not pushed)
      //          _ <- Entity.i(1).save.transact
      //
      //          // Start subscription in separate thread
      //          _ <- Entity.i.query.subscribe { freshResult =>
      //            intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
      //          }
      //
      //          // Mutations to be monitored by subscription
      //          id <- Entity.i(2).save.transact.map(_.id)
      //
      //          _ <- Entity.i.insert(3, 4).transact
      //
      //          _ <- Entity(id).i(20).update.transact
      //
      //          _ <- Entity(id).delete.transact
      //
      //          // Mutations with no callback-involved attributes don't call back
      //          _ <- Entity.string("foo").save.transact
      //
      //          // Allow callbacks running in parallel to finish
      //          //          _ <- TestClock.adjust(50.milliseconds) // doesn't work on first run on JS, hmm..
      //        } yield {
      //          assertTrue(intermediaryCallbackResults.map(_.sorted).toSet == Set(
      //            List(1, 2), // query result after 2 was saved
      //            List(1, 2, 3, 4), // query result after 3 and 4 were inserted
      //            List(1, 3, 4, 20), // query result after 2 was updated to 20
      //            List(1, 3, 4), // query result after 20 was deleted
      //          ))
      //        }
      //      }.provide(types.orDie),

      //      // todo: Make more zio-idiomatic. There's some concurrency issue with setting the Ref...
      //
      //      test("Subscription") {
      //        for {
      //          r <- zio.Ref.make(List.empty[List[Int]])
      //          // _ <- r.getAndUpdate(_ :+ List(42)) // this works fine
      //          _ <- Entity.i(1).save.transact
      //          // start subscription in separate thread
      //          _ <- Entity.i.query.subscribe { freshResult =>
      //            println("####### " + freshResult)
      //            // todo: why is the ref not updated?
      //            r.getAndUpdate(_ :+ freshResult) // in another thread/fiber?
      //          }
      //          _ <- Entity.i(2).save.transact
      //          _ <- Entity.i(3).save.transact
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
