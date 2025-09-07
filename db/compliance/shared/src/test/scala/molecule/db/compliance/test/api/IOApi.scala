package molecule.db.compliance.test.api

import scala.annotation.nowarn
import scala.concurrent.duration.DurationInt
import cats.effect.IO
import molecule.core.error.{InsertErrors, ValidationErrors}
import molecule.core.setup.{MUnit_io, TestUtils}
import molecule.db.common.api.Api_io
import molecule.db.common.spi.Spi_io
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Platform}


case class IOApi(
  suite: MUnit_io,
  api: Api_io & Spi_io & DbProviders
) extends TestUtils with Platform {

  import api.*
  import suite.*

  "Crud actions" - types {
    // (can't have this line in for expression since Cats IO doesn't
    // have withFilter and better-monadic-for doesn't fix this on scalajs)
    Entity.int.insert(1, 2).transact.map(_.ids).flatMap {
      case List(a, b) =>
        for {
          _ <- Entity.int(3).save.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
          _ <- Entity(a).int(10).update.transact
          _ <- Entity(b).delete.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
        } yield ()
    }: @nowarn
  }


  "Streaming" - types {
    for {
      _ <- Entity.i.insert(1, 2, 3).transact

      _ <- if (platform == "jvm") {
        // Returning an fs2.Stream[IO, Int]
        Entity.i.query.stream
          .compile
          .toList
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
      }
    } yield ()
  }


  "Validation" - validation {
    import molecule.db.compliance.domains.dsl.Validation.*
    for {
      _ <- Tpe.string("a").save.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Tpe.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      _ <- Tpe.string.insert("a").transact
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==
              s"""Tpe.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      id <- Tpe.string("c").save.transact.map(_.id)
      _ <- Tpe(id).string("a").update.transact
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==
              s"""Tpe.string with value `a` doesn't satisfy validation:
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
  }: @nowarn


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
      _ <- IO.sleep(500.milliseconds)

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

      // Intermediary callback results
      _ = intermediaryCallbackResults.map(_.sorted) ==> List(
        List(1, 2), // query result after 2 was saved
        List(1, 2, 3, 4), // query result after 3 and 4 were inserted
        List(1, 3, 4, 20), // query result after 2 was updated to 20
        List(1, 3, 4), // query result after 20 was deleted
      )
    } yield ()
  }
}