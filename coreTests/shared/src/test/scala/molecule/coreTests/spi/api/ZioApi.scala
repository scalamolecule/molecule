package molecule.coreTests.spi.api

import molecule.base.error.MoleculeError
import molecule.core.api.Api_zio
import molecule.core.spi.{Conn, Spi_zio}
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup.{DbProviders, MUnitSuite, TestUtils}
import zio._


case class ZioApi(
  suite: MUnitSuite,
  api: Api_zio with Spi_zio with DbProviders
) extends TestUtils {

  import api._
  import suite._

  def runIO[A](io: ZIO[Conn, MoleculeError, A])(implicit conn: Conn): A = {
    Unsafe.unsafe { implicit unsafe =>
      val runtime = Runtime.default
      runtime.unsafe.run(io.provideEnvironment(ZEnvironment(conn))).getOrThrow()
    }
  }

  "Crud actions" - types { implicit conn =>
    val List(a, b) = runIO(Entity.int.insert(1, 2).transact.map(_.ids))
    runIO(Entity.int(3).save.transact)
    runIO(Entity.int.a1.query.get.map(_ ==> List(1, 2, 3)))
    runIO(Entity(a).int(10).update.transact)
    runIO(Entity(b).delete.transact)
    runIO(Entity.int.a1.query.get.map(_ ==> List(3, 10)))
  }


  "Opt ref" - refs { implicit conn =>
    import molecule.coreTests.domains.dsl.Refs._

    runIO(A.i(1).save.transact)

    // Optional card-one ref (SQL left join)
    runIO(A.i.B.?(B.i).query.get) ==> List(
      (1, None),
    )

    runIO(A.i(2).B.i(3).s("b").save.transact)

    // Optional card-one ref (SQL left join)
    runIO(A.i.a1.B.?(B.i.s).query.i.get) ==> List(
      (1, None),
      (2, Some((3, "b"))),
    )
  }


  //  "Validation" - validation { implicit conn =>
  //    import molecule.coreTests.domains.dsl.Validation.Type
  //
  //    intercept[ValidationErrors](
  //      Type.string("a").save.transact
  //    ) match {
  //      case ValidationErrors(errorMap) =>
  //        errorMap.head._2.head ==>
  //          s"""Type.string with value `a` doesn't satisfy validation:
  //             |_ > "b"
  //             |""".stripMargin
  //    }
  //    intercept[InsertErrors](
  //      Type.string.insert("a").transact
  //    ) match {
  //      case InsertErrors(errors, _) =>
  //        errors.head._2.head.errors.head ==
  //          s"""Type.string with value `a` doesn't satisfy validation:
  //             |_ > "b"
  //             |""".stripMargin
  //    }
  //
  //    val id = Type.string("c").save.transact.ids.head
  //    intercept[ValidationErrors](
  //      Type(id).string("a").update.transact
  //    ) match {
  //      case ValidationErrors(errorMap) =>
  //        errorMap.head._2.head ==>
  //          s"""Type.string with value `a` doesn't satisfy validation:
  //             |_ > "b"
  //             |""".stripMargin
  //    }
  //  }


  "Inspection" - types { implicit conn =>
    val List(a, b) = runIO(Entity.int.insert(1, 2).transact).ids // Need data for update and delete
    runIO(Entity.int.insert(1, 2).inspect)
    runIO(Entity.int(3).save.inspect)
    runIO(Entity.int.query.inspect)
    runIO(Entity(a).int(10).update.inspect)
    runIO(Entity(b).delete.inspect)
  }


  "Offset query" - types { implicit conn =>
    runIO(Entity.int.insert(1, 2, 3).transact)
    runIO(Entity.int.a1.query.get) ==> List(1, 2, 3)
    runIO(Entity.int.a1.query.limit(2).get) ==> List(1, 2)
    runIO(Entity.int.a1.query.offset(1).get) ==> (List(2, 3), 3, false)
    runIO(Entity.int.a1.query.offset(1).limit(1).get) ==> (List(2), 3, true)
  }


  //  "Cursor query" - unique { implicit conn =>
  //    import molecule.coreTests.domains.dsl.Uniques._
  //
  //    val query = runIO(Uniques.int.a1.query)
  //    Uniques.int.insert(1, 2, 3, 4, 5).transact
  //    val c1 = query.from("").limit(2).get match {
  //      case (List(1, 2), c, true) => c
  //    }
  //    val c2 = query.from(c1).limit(2).get match {
  //      case (List(3, 4), c, true) => c
  //    }
  //    val c3 = query.from(c2).limit(2).get match {
  //      case (List(5), c, false) => c
  //    }
  //    val c4 = query.from(c3).limit(-2).get match {
  //      case (List(3, 4), c, true) => c
  //    }
  //    query.from(c4).limit(-2).get match {
  //      case (List(1, 2), _, false) => ()
  //    }
  //  }
  //
  //
  //  "Subscription" - types { implicit conn =>
  //    var intermediaryCallbackResults = List.empty[List[Int]]
  //
  //    // Initial data
  //    Entity.i(1).save.transact
  //
  //    // Start subscription
  //    Entity.i.query.subscribe { freshResult =>
  //      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
  //    }
  //
  //    // Mutations to be monitored by subscription
  //    val id = Entity.i(2).save.transact.id
  //    Entity.i.a1.query.get ==> List(1, 2)
  //
  //    Entity.i.insert(3, 4).transact
  //    Entity.i.a1.query.get ==> List(1, 2, 3, 4)
  //
  //    Entity(id).i(20).update.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4, 20)
  //
  //    Entity(id).delete.transact
  //    Entity.i.a1.query.get ==> List(1, 3, 4)
  //
  //    // Mutations with no callback-involved attributes don't call back
  //    Entity.string("foo").save.transact
  //
  //    // Callback catched all intermediary results correctly
  //    intermediaryCallbackResults.map(_.sorted) ==> List(
  //      List(1, 2), // query result after 2 was saved
  //      List(1, 2, 3, 4), // query result after 3 and 4 were inserted
  //      List(1, 3, 4, 20), // query result after 2 was updated to 20
  //      List(1, 3, 4), // query result after 20 was deleted
  //    )
  //  }
}
