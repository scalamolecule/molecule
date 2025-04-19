package molecule.coreTests.spi.api

import molecule.base.error.{ExecutionError, InsertErrors, MoleculeError, ValidationErrors}
import molecule.core.api.Api_zio
import molecule.core.spi.{Conn, Spi_zio}
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.{DbProviders_zio, TestUtils}
import zio.*
import zio.test.*
import zio.test.TestAspect.*
import scala.annotation.nowarn

@nowarn
case class ZioApi(api: Api_zio with Spi_zio with DbProviders_zio)
  extends ZIOSpecDefault with TestUtils {

  import api.*

  @nowarn override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Molecule ZIO api")(

      test("Crud actions") {
        for {
          ids <- Entity.int.insert(1, 2).transact.map(_.ids)
          _ <- Entity.int(3).save.transact
          a <- Entity.int.a1.query.get.map(x => assertTrue(x == List(1, 2, 3)))
          _ <- Entity(ids(0)).int(10).update.transact
          _ <- Entity(ids(1)).delete.transact
          b <- Entity.int.a1.query.get.map(x => assertTrue(x == List(3, 10)))
        } yield a && b
      }.provide(types.orDie),


      test("Streaming") {
        for {
          _ <- Entity.i.insert(1, 2, 3).transact
          result <- if (platform == "jvm") {
            Entity.i.query.stream
              .runCollect
              .map(chunk => assertTrue(chunk.toList.sorted == List(1, 2, 3)))
          } else {
            Entity.i.query.stream
              .runCollect
              .either
              .map {
                case Left(ExecutionError(msg)) =>
                  assertTrue(
                    msg == "Streaming not implemented on JS platform. Maybe use subscribe instead?"
                  )
                case Left(other)               =>
                  println(s"Unexpected error: $other")
                  assertTrue(false)

                case Right(value) =>
                  println(s"Unexpected success: $value")
                  assertTrue(false)
              }
          }
        } yield result
      }.provide(types.orDie),


      test("Opt ref") {
        import molecule.coreTests.domains.dsl.Refs.*
        for {
          _ <- A.i(1).save.transact

          // Optional card-one ref (SQL left join)
          a <- A.i.B.?(B.i).query.get.map(x => assertTrue(x == List(
            (1, None)
          )))

          _ <- A.i(2).B.i(3).s("b").save.transact

          // Optional card-one ref (SQL left join)
          b <- A.i.a1.B.?(B.i.s).query.i.get.map(x => assertTrue(x == List(
            (1, None),
            (2, Some((3, "b"))),
          )))
        } yield a && b
      }.provide(refs.orDie),


      test("Validation") {
        import molecule.coreTests.domains.dsl.Validation.Type
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
          ids <- Entity.int.insert(1, 2).transact.map(_.ids) // Need data for update and delete
          _ <- Entity.int.insert(1, 2).inspect
          _ <- Entity.int(3).save.inspect
          _ <- Entity.int.query.inspect
          _ <- Entity.int.query.get
          _ <- Entity(ids(0)).int(10).update.inspect
          _ <- Entity(ids(1)).delete.inspect
        } yield assertTrue(true)
      }.provide(types.orDie),


      test("Offset query")(
        for {
          _ <- Entity.int.insert(1, 2, 3).transact
          a <- Entity.int.a1.query.get.map(x => assertTrue(
            x == List(1, 2, 3)
          ))
          b <- Entity.int.a1.query.limit(2).get.map(x => assertTrue(
            x == List(1, 2)
          ))
          c <- Entity.int.a1.query.offset(1).get.map(x => assertTrue(
            x == (List(2, 3), 3, false)
          ))
          d <- Entity.int.a1.query.offset(1).limit(1).get.map(x => assertTrue(
            x == (List(2), 3, true)
          ))
        } yield a && b && c && d
      ).provide(types.orDie),


      test("Cursor query") {
        import molecule.coreTests.domains.dsl.Uniques.*
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
          _ <- Entity.i(1).save.transact

          // Start subscription in separate thread
          _ <- Entity.i.query.subscribe { freshResult =>
            intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
          }

          // Mutations to be monitored by subscription
          id <- Entity.i(2).save.transact.map(_.id)

          _ <- Entity.i.insert(3, 4).transact

          _ <- Entity(id).i(20).update.transact

          _ <- Entity(id).delete.transact

          // Mutations with no callback-involved attributes don't call back
          _ <- Entity.string("foo").save.transact

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

    ) @@ sequential
}
