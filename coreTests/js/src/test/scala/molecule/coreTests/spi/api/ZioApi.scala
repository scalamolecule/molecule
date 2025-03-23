package molecule.coreTests.spi.api

import molecule.base.error.{InsertErrors, MoleculeError, ValidationErrors}
import molecule.core.api.Api_zio
import molecule.core.spi.{Conn, Spi_zio}
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{DbProviders, Test, TestUtils}
import zio._
import scala.annotation.nowarn
import scala.concurrent.Future

@nowarn
case class ZioApi(
  suite: Test,
  api: Api_zio with Spi_zio with DbProviders
) extends TestUtils {

  import api._
  import suite._

  // Need a future on ScalaJS side with munit.
  // Can't use this on JVM side since `provideEnvironment` closes the connection for each call (rpc unaffected on JS side)
  def runZIO[A](io: ZIO[Conn, MoleculeError, A])(implicit conn: Conn): Future[A] = {
    Unsafe.unsafe { implicit unsafe => // Provide implicit Unsafe instance
      val runtime = Runtime.default
      runtime.unsafe.runToFuture(io.provideEnvironment(ZEnvironment(conn)))
    }
  }


  "Crud actions" - types { implicit conn =>
    for {
      List(a, b) <- runZIO(Entity.int.insert(1, 2).transact.map(_.ids))
      _ <- runZIO(Entity.int(3).save.transact)
      _ <- runZIO(Entity.int.a1.query.get.map(_ ==> List(1, 2, 3)))
      _ <- runZIO(Entity(a).int(10).update.transact)
      _ <- runZIO(Entity(b).delete.transact)
      _ <- runZIO(Entity.int.a1.query.get.map(_ ==> List(3, 10)))
    } yield ()
  }


  "Opt ref" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._
    for {
      _ <- runZIO(A.i(1).save.transact)

      // Optional card-one ref (SQL left join)
      _ <- runZIO(A.i.B.?(B.i).query.get).map(_ ==> List(
        (1, None),
      ))

      _ <- runZIO(A.i(2).B.i(3).s("b").save.transact)

      // Optional card-one ref (SQL left join)
      _ <- runZIO(A.i.a1.B.?(B.i.s).query.get).map(_ ==> List(
        (1, None),
        (2, Some((3, "b"))),
      ))
    } yield ()
  }


  "Validation" - validation { implicit conn =>
    import molecule.coreTests.domains.dsl.Validation.Type
    for {
      _ <- runZIO(Type.string("a").save.transact)
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      _ <- runZIO(Type.string.insert("a").transact)
        .map(_ ==> "Unexpected success").recover {
          case InsertErrors(errors, _) =>
            errors.head._2.head.errors.head ==
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }

      id <- runZIO(Type.string("c").save.transact).map(_.ids.head)

      _ <- runZIO(Type(id).string("a").update.transact)
        .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |_ > "b"
                 |""".stripMargin
        }
    } yield ()
  }


  "Inspection" - types { implicit conn =>
    for {
      List(a, b) <- runZIO(Entity.int.insert(1, 2).transact).map(_.ids) // Need data for update and delete
      _ <- runZIO(Entity.int.insert(1, 2).inspect)
      _ <- runZIO(Entity.int(3).save.inspect)
      _ <- runZIO(Entity.int.query.inspect)
      _ <- runZIO(Entity(a).int(10).update.inspect)
      _ <- runZIO(Entity(b).delete.inspect)
    } yield ()
  }


  "Offset query" - types { implicit conn =>
    for {
      _ <- runZIO(Entity.int.insert(1, 2, 3).transact)
      _ <- runZIO(Entity.int.a1.query.get).map(_ ==> List(1, 2, 3))
      _ <- runZIO(Entity.int.a1.query.limit(2).get).map(_ ==> List(1, 2))
      _ <- runZIO(Entity.int.a1.query.offset(1).get).map(_ ==> (List(2, 3), 3, false))
      _ <- runZIO(Entity.int.a1.query.offset(1).limit(1).get).map(_ ==> (List(2), 3, true))
    } yield ()
  }


  "Cursor query" - unique { implicit conn =>
    import molecule.coreTests.domains.dsl.Uniques._

    val query = Uniques.int.a1.query
    for {
      _ <- runZIO(Uniques.int.insert(1, 2, 3, 4, 5).transact)
      c1 <- runZIO(query.from("").limit(2).get).map {
        case (List(1, 2), c, true) => c
      }
      c2 <- runZIO(query.from(c1).limit(2).get).map {
        case (List(3, 4), c, true) => c
      }
      c3 <- runZIO(query.from(c2).limit(2).get).map {
        case (List(5), c, false) => c
      }
      c4 <- runZIO(query.from(c3).limit(-2).get).map {
        case (List(3, 4), c, true) => c
      }
      _ <- runZIO(query.from(c4).limit(-2).get).map {
        case (List(1, 2), _, false) => ()
      }
    } yield ()
  }


  "Subscription" - types { implicit conn =>
    var intermediaryCallbackResults = List.empty[List[Int]]

    for {
      // Initial data
      _ <- runZIO(Entity.i(1).save.transact)

      // Start subscription
      _ <- runZIO(Entity.i.query.subscribe { freshResult =>
        intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
      })

      // Mutations to be monitored by subscription
      id <- runZIO(Entity.i(2).save.transact).map(_.id)
      _ <- runZIO(Entity.i.a1.query.get).map(_ ==> List(1, 2))

      _ <- runZIO(Entity.i.insert(3, 4).transact)
      _ <- runZIO(Entity.i.a1.query.get).map(_ ==> List(1, 2, 3, 4))

      _ <- runZIO(Entity(id).i(20).update.transact)
      _ <- runZIO(Entity.i.a1.query.get).map(_ ==> List(1, 3, 4, 20))

      _ <- runZIO(Entity(id).delete.transact)
      _ <- runZIO(Entity.i.a1.query.get).map(_ ==> List(1, 3, 4))

      // Mutations with no callback-involved attributes don't call back
      _ <- runZIO(Entity.string("foo").save.transact)

      // Callback catched all intermediary results correctly
      _ = intermediaryCallbackResults.map(_.sorted) ==> List(
        List(1, 2), // query result after 2 was saved
        List(1, 2, 3, 4), // query result after 3 and 4 were inserted
        List(1, 3, 4, 20), // query result after 2 was updated to 20
        List(1, 3, 4), // query result after 20 was deleted
      )
    } yield ()
  }
}
