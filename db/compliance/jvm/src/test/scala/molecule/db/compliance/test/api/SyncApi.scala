package molecule.db.compliance.test.api

import molecule.db.base.error.{InsertErrors, ValidationErrors}
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_sync
import molecule.db.core.spi.Spi_sync
import scala.annotation.nowarn
import molecule.db.compliance.domains.dsl.Types.*

// Testing the synchronous api only on the JVM platform since the JS platform
// is dependent on asynchronous retrieval of data from the server.
@nowarn
case class SyncApi(
  suite: Test,
  api: Api_sync with Spi_sync with DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "Crud actions" - types { implicit conn =>
    val List(a, b) = Entity.int.insert(1, 2).transact.ids
    Entity.int(3).save.transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity(a).int(10).update.transact
    Entity(b).delete.transact
    Entity.int.a1.query.get ==> List(3, 10)
  }


  "Streaming" - types { implicit conn =>
    Entity.i.insert(1, 2, 3).transact

    // Returning a geny Generator[Int] from the lihaoyi ecosystem
    Entity.i.query.stream
      .toList.sorted ==> List(1, 2, 3)
  }


  "Opt ref" - refs { implicit conn =>
    import molecule.db.compliance.domains.dsl.Refs.*

    A.i(1).save.transact

    // Optional card-one ref (SQL left join)
    A.i.B.?(B.i).query.get ==> List(
      (1, None),
    )

    A.i(2).B.i(3).s("b").save.transact

    // Optional card-one ref (SQL left join)
    A.i.a1.B.?(B.i.s).query.i.get ==> List(
      (1, None),
      (2, Some((3, "b"))),
    )
  }


  "Validation" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*

    intercept[ValidationErrors](
      Type.string("a").save.transact
    ) match {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.string with value `a` doesn't satisfy validation:
             |_ > "b"
             |""".stripMargin
    }
    intercept[InsertErrors](
      Type.string.insert("a").transact
    ) match {
      case InsertErrors(errors, _) =>
        errors.head._2.head.errors.head ==>
          s"""Type.string with value `a` doesn't satisfy validation:
             |_ > "b"
             |""".stripMargin
    }

    val id = Type.string("c").save.transact.ids.head
    intercept[ValidationErrors](
      Type(id).string("a").update.transact
    ) match {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.string with value `a` doesn't satisfy validation:
             |_ > "b"
             |""".stripMargin
    }
  }


  "Inspection" - types { implicit conn =>
    val List(a, b) = Entity.int.insert(1, 2).transact.ids // Need data for update and delete
    Entity.int.insert(1, 2).inspect
    Entity.int(3).save.inspect
    Entity.int.query.inspect
    Entity(a).int(10).update.inspect
    Entity(b).delete.inspect
  }


  "Offset query" - types { implicit conn =>
    Entity.int.insert(1, 2, 3).transact
    Entity.int.a1.query.get ==> List(1, 2, 3)
    Entity.int.a1.query.limit(2).get ==> List(1, 2)
    Entity.int.a1.query.offset(1).get ==> (List(2, 3), 3, false)
    Entity.int.a1.query.offset(1).limit(1).get ==> (List(2), 3, true)
  }


  "Cursor query" - unique { implicit conn =>
    import molecule.db.compliance.domains.dsl.Uniques.*

    val query = Uniques.int.a1.query
    Uniques.int.insert(1, 2, 3, 4, 5).transact
    val c1 = query.from("").limit(2).get match {
      case (List(1, 2), c, true) => c
    }
    val c2 = query.from(c1).limit(2).get match {
      case (List(3, 4), c, true) => c
    }
    val c3 = query.from(c2).limit(2).get match {
      case (List(5), c, false) => c
    }
    val c4 = query.from(c3).limit(-2).get match {
      case (List(3, 4), c, true) => c
    }
    query.from(c4).limit(-2).get match {
      case (List(1, 2), _, false) => ()
    }
  }


  "Subscription" - types { implicit conn =>
    var intermediaryCallbackResults = List.empty[List[Int]]

    // Initial data
    Entity.i(1).save.transact

    // Start subscription
    Entity.i.query.subscribe { freshResult =>
      intermediaryCallbackResults = intermediaryCallbackResults :+ freshResult
    }

    // Mutations to be monitored by subscription
    val id = Entity.i(2).save.transact.id
    Entity.i.a1.query.get ==> List(1, 2)

    Entity.i.insert(3, 4).transact
    Entity.i.a1.query.get ==> List(1, 2, 3, 4)

    Entity(id).i(20).update.transact
    Entity.i.a1.query.get ==> List(1, 3, 4, 20)

    Entity(id).delete.transact
    Entity.i.a1.query.get ==> List(1, 3, 4)

    // Mutations with no callback-involved attributes don't call back
    Entity.string("foo").save.transact

    // Callback catched all intermediary results correctly
    intermediaryCallbackResults.map(_.sorted) ==> List(
      List(1, 2), // query result after 2 was saved
      List(1, 2, 3, 4), // query result after 3 and 4 were inserted
      List(1, 3, 4, 20), // query result after 2 was updated to 20
      List(1, 3, 4), // query result after 20 was deleted
    )
  }
}
