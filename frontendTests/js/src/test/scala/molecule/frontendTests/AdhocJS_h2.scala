package molecule.frontendTests

import boopickle.Default._
import molecule.base.error.{InsertErrors, ModelError, MoleculeError, ValidationErrors}
import molecule.core.marshalling.Boopicklers._
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.frontendTests.domains.dsl.Types._
import molecule.frontendTests.setup.{MUnitSuite, MUnitSuiteWithArrays, Test, TestData}
import molecule.sql.h2.async._
import scala.concurrent.Future


//class AdhocJS_h2 extends MUnitSuiteWithArrays with Test with TestData {
class AdhocJS_h2 extends MUnitSuite with Test with TestData {


  "types" - types { implicit conn =>
    for {
      List(a, b) <- Entity.int.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int(3).save.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
      _ <- Entity(a).int(10).update.transact
      _ <- Entity(b).delete.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(3, 10))

    } yield ()
  }

  //    runZIO(Entity.int(3).save.transact)
  //    runZIO(Entity.int.a1.query.get.map(_ ==> List(1, 2, 3)))
  //    runZIO(Entity(a).int(10).update.transact)
  //    runZIO(Entity(b).delete.transact)
  //    runZIO(Entity.int.a1.query.get.map(_ ==> List(3, 10)))


  //  "Opt ref" - refs { implicit conn =>
  //    import molecule.frontendTests.domains.dsl.Refs._
  //
  //    runZIO(A.i(1).save.transact)
  //
  //    // Optional card-one ref (SQL left join)
  //    runZIO(A.i.B.?(B.i).query.get) ==> List(
  //      (1, None),
  //    )
  //
  //    runZIO(A.i(2).B.i(3).s("b").save.transact)
  //
  //    // Optional card-one ref (SQL left join)
  //    runZIO(A.i.a1.B.?(B.i.s).query.get) ==> List(
  //      (1, None),
  //      (2, Some((3, "b"))),
  //    )
  //  }
  //
  //
  //  "Validation" - validation { implicit conn =>
  //    import molecule.frontendTests.domains.dsl.Validation.Type
  //
  //    intercept[ValidationErrors](
  //      runZIO(Type.string("a").save.transact)
  //    ) match {
  //      case ValidationErrors(errorMap) =>
  //        errorMap.head._2.head ==>
  //          s"""Type.string with value `a` doesn't satisfy validation:
  //             |_ > "b"
  //             |""".stripMargin
  //    }
  //    intercept[InsertErrors](
  //      runZIO(Type.string.insert("a").transact)
  //    ) match {
  //      case InsertErrors(errors, _) =>
  //        errors.head._2.head.errors.head ==
  //          s"""Type.string with value `a` doesn't satisfy validation:
  //             |_ > "b"
  //             |""".stripMargin
  //    }
  //
  //    val id = runZIO(Type.string("c").save.transact).ids.head
  //    intercept[ValidationErrors](
  //      runZIO(Type(id).string("a").update.transact)
  //    ) match {
  //      case ValidationErrors(errorMap) =>
  //        errorMap.head._2.head ==>
  //          s"""Type.string with value `a` doesn't satisfy validation:
  //             |_ > "b"
  //             |""".stripMargin
  //    }
  //  }
  //
  //
  //  "Inspection" - types { implicit conn =>
  //    val List(a, b) = runZIO(Entity.int.insert(1, 2).transact).ids // Need data for update and delete
  //    runZIO(Entity.int.insert(1, 2).inspect)
  //    runZIO(Entity.int(3).save.inspect)
  //    runZIO(Entity.int.query.inspect)
  //    runZIO(Entity(a).int(10).update.inspect)
  //    runZIO(Entity(b).delete.inspect)
  //  }
  //
  //
  //  "Offset query" - types { implicit conn =>
  //    runZIO(Entity.int.insert(1, 2, 3).transact)
  //    runZIO(Entity.int.a1.query.get) ==> List(1, 2, 3)
  //    runZIO(Entity.int.a1.query.limit(2).get) ==> List(1, 2)
  //    runZIO(Entity.int.a1.query.offset(1).get) ==> (List(2, 3), 3, false)
  //    runZIO(Entity.int.a1.query.offset(1).limit(1).get) ==> (List(2), 3, true)
  //  }
  //
  //
  //  "Cursor query" - unique { implicit conn =>
  //    import molecule.frontendTests.domains.dsl.Uniques._
  //
  //    val query = Uniques.int.a1.query
  //    runZIO(Uniques.int.insert(1, 2, 3, 4, 5).transact)
  //    val c1 = runZIO(query.from("").limit(2).get) match {
  //      case (List(1, 2), c, true) => c
  //    }
  //    val c2 = runZIO(query.from(c1).limit(2).get) match {
  //      case (List(3, 4), c, true) => c
  //    }
  //    val c3 = runZIO(query.from(c2).limit(2).get) match {
  //      case (List(5), c, false) => c
  //    }
  //    val c4 = runZIO(query.from(c3).limit(-2).get) match {
  //      case (List(3, 4), c, true) => c
  //    }
  //    runZIO(query.from(c4).limit(-2).get) match {
  //      case (List(1, 2), _, false) => ()
  //    }
  //  }
  //
  //
  //  "Subscription" - types { implicit conn =>
  //    var intermediaryCallbackResults = List.empty[List[Int]]
  //
  //    // Initial data
  //    runZIO(Entity.i(1).save.transact)
  //
  //    // Start subscription
  //    runZIO(Entity.i.query.subscribe { freshResult =>
  //      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
  //    })
  //
  //    // Mutations to be monitored by subscription
  //    val id = runZIO(Entity.i(2).save.transact).id
  //    runZIO(Entity.i.a1.query.get) ==> List(1, 2)
  //
  //    runZIO(Entity.i.insert(3, 4).transact)
  //    runZIO(Entity.i.a1.query.get) ==> List(1, 2, 3, 4)
  //
  //    runZIO(Entity(id).i(20).update.transact)
  //    runZIO(Entity.i.a1.query.get) ==> List(1, 3, 4, 20)
  //
  //    runZIO(Entity(id).delete.transact)
  //    runZIO(Entity.i.a1.query.get) ==> List(1, 3, 4)
  //
  //    // Mutations with no callback-involved attributes don't call back
  //    runZIO(Entity.string("foo").save.transact)
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
