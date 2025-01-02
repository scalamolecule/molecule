package molecule.coreTests.spi.api

import molecule.base.error.{InsertErrors, ValidationErrors}
import molecule.core.api.Api_io
import molecule.core.spi.Spi_io
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{DbProviders, Test, TestUtils}
import scala.annotation.nowarn
import scala.language.implicitConversions

case class IOApi(
  suite: Test,
  api: Api_io with Spi_io with DbProviders
) extends TestUtils {

  import api._
  import suite._


  "Crud actions" - types { implicit conn =>
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

  test("Crud actions") {
    types { implicit conn =>
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
  }

  test("Validation") {
    import molecule.coreTests.domains.dsl.Validation.Type
    validation { implicit conn =>
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
  }

  test("Inspection") {
    types { implicit conn =>
      Entity.int.insert(1, 2).transact.map(_.ids).flatMap {
        case List(a, b) =>
          for {
            _ <- Entity.int.insert(1, 2).inspect
            _ <- Entity.int(3).save.inspect
            _ <- Entity.int.query.inspect
            _ <- Entity(a).int(10).update.inspect
            _ <- Entity(b).delete.inspect
          } yield ()
      }: @nowarn
    }
  }

  test("Offset query") {
    types { implicit conn =>
      for {
        _ <- Entity.int.insert(1, 2, 3).transact
        _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
        _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
        _ <- Entity.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
        _ <- Entity.int.a1.query.offset(1).limit(1).get.map(_ ==> (List(2), 3, true))
      } yield ()
    }
  }

  test("Cursor query") {
    unique { implicit conn =>
      import molecule.coreTests.domains.dsl.Uniques._
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
  }

  test("Subscription") {
    types { implicit conn =>
      var intermediaryCallbackResults = List.empty[List[Int]]
      for {
        // Initial data
        _ <- Entity.i(1).save.transact

        // Start subscription
        _ <- Entity.i.query.subscribe { freshResult =>
          intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
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
}