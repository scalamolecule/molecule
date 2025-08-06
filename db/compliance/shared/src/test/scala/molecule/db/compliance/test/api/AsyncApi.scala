package molecule.db.compliance.test.api

import scala.annotation.nowarn
import cats.effect.unsafe.implicits.global as ioRuntime
import molecule.core.error.{InsertErrors, ValidationErrors}
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Platform}

@nowarn
case class AsyncApi(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils with Platform {

  import api.*
  import suite.*

  "Crud actions" - types {
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
    } yield ()
  }


  "Streaming" - types {
    for {
      _ <- Entity.i.insert(1, 2, 3).transact

      _ <- if (platform == "jvm") {
        // Returning an fs2.Stream[IO, Int]
        Entity.i.query.stream
          .compile
          .toList
          .unsafeToFuture()
          .map(_.sorted ==> List(1, 2, 3))
      } else {
        Entity.i.query.stream
          .compile
          .toList
          .attempt
          .map {
            case Left(e)      =>
              assertEquals(
                e.getMessage,
                "Streaming not implemented on JS platform. Maybe use subscribe instead?"
              )
            case Right(value) =>
              fail(s"Expected exception but got value: $value")
          }
          .unsafeToFuture()
      }
    } yield ()
  }


  "Opt ref" - refs {

    for {
      _ <- A.i(1).save.transact

      // Optional card-one ref (SQL left join)
      _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
        (1, None),
      ))

      _ <- A.i(2).B.i(3).s("b").save.transact

      // Optional card-one ref (SQL left join)
      _ <- A.i.a1.B.?(B.i.s).query.i.get.map(_ ==> List(
        (1, None),
        (2, Some((3, "b"))),
      ))
    } yield ()
  }


  "Validation" - validation {
    import molecule.db.compliance.domains.dsl.Validation.*
    for {
      _ <- Type.string("a").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      _ <- Type.string.insert("a").transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      id <- Type.string("c").save.transact.map(_.id)
      _ <- Type(id).string("a").update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }
    } yield ()
  }


  "Offset query" - types {
    for {
      _ <- Entity.int.insert(1, 2, 3).transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(1).limit(1).get.map(_ ==> (List(2), 3, true))
    } yield ()
  }


  "Cursor query" - unique {
    import molecule.db.compliance.domains.dsl.Uniques.*
    val query = Uniques.int.a1.query
    for {
      _ <- Uniques.int.insert(1, 2, 3, 4, 5).transact
      c1 <- query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
      c2 <- query.from(c1).limit(2).get.map { case (List(3, 4), c, true) => c }
      c3 <- query.from(c2).limit(2).get.map { case (List(5), c, false) => c }
      c4 <- query.from(c3).limit(-2).get.map { case (List(3, 4), c, true) => c }
      _ <- query.from(c4).limit(-2).get.map { case (List(1, 2), _, false) => () }
    } yield ()
  }


  "Subscription" - types {
    var intermediaryCallbackResults = List.empty[List[Int]]
    for {
      // Initial data
      _ <- Entity.i(1).save.transact

      // Start subscription
      _ <- Entity.i.query.subscribe { freshResult =>
        intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
      }

      // When calling from ScalaJS, calls are asynchronous, and we need to wait
      // a bit for the subscription websockets to be ready to serve callbacks.
      _ <- delay(500)

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

      // Callback catched all intermediary results correctly
      _ = intermediaryCallbackResults.map(_.sorted).toSet ==> Set(
        List(1, 2), // query result after 2 was saved
        List(1, 2, 3, 4), // query result after 3 and 4 were inserted
        List(1, 3, 4, 20), // query result after 2 was updated to 20
        List(1, 3, 4), // query result after 20 was deleted
      )
    } yield ()
  }
}

