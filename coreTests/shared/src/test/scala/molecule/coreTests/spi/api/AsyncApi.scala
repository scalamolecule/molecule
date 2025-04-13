package molecule.coreTests.spi.api

import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.annotation.nowarn

@nowarn
case class AsyncApi(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Crud actions" - types { implicit conn =>
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))
    } yield ()
  }


  "Opt ref" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._
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


  "Validation" - validation { implicit conn =>
    import molecule.coreTests.domains.dsl.Validation.Type
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


  "Inspection" - types { implicit conn =>
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids) // Need data for update and delete
      _ <- Entity.int.insert(1, 2).inspect
      _ <- Entity.int(3).save.inspect
      _ <- Entity.int.query.inspect
      _ <- Entity(a).int(10).update.inspect
      _ <- Entity(b).delete.inspect
    } yield ()
  }


  "Offset query" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1, 2, 3).transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
      _ <- Entity.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
      _ <- Entity.int.a1.query.offset(1).limit(1).get.map(_ ==> (List(2), 3, true))
    } yield ()
  }


  "Cursor query" - unique { implicit conn =>
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
  }


  "Subscription" - types { implicit conn =>
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

