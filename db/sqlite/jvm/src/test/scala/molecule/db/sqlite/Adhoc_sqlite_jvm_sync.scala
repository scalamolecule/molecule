package molecule.db.sqlite

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.sqlite.setup.DbProviders_sqlite
import sync.*


class Adhoc_sqlite_jvm_sync extends MUnit with DbProviders_sqlite with TestUtils {

  //  "types" - types { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Types._
  //
  //    Entity.int(1).save.transact
  //    Entity.int.query.get.head ==> 1
  //  }

  "refs" - refs { implicit conn =>
    import molecule.db.compliance.domains.dsl.Refs.*

    A.i(1).save.transact
    A.i.query.get.head ==> 1

    //    val List(e1, e2, _) = A.i.insert(1, 2, 3).transact.ids
    //    A.i.a1.query.get ==> List(1, 2, 3)

    //    Thread.sleep(500)

    //    B.i(1).save.transact
    //    A.i(1).save.transact
    //
    //    A.i(1).B.i(2).save.transact
    //    A.i.B.i.query.i.get
    //
    //    rawQuery(
    ////      """select * from A""",
    //      """select * from A_ownAa_A""",
    //    true
    //    )
    //
    ////    A(e1).delete.transact
    //    A(1).delete.transact
    // or
    //    A.id_(e2).delete.transact
    //    A.i.query.get ==> List(3)
  }


  //  "validation" - validation { implicit conn =>
  //    import molecule.db.compliance.domains.dsl.Validation._
  //
  //    try {
  //      transact(
  //        Type.int.insert(5, 6),
  //        Type.int(1).save,
  //      )
  //    } catch {
  //      case ValidationErrors(errorMap) =>
  //        errorMap.head._2.head ==>
  //          s"""Type.int with value `1` doesn't satisfy validation:
  //             |_ > 2
  //             |""".stripMargin
  //      case NonFatal(e)                =>
  //        println("ERROR: " + e)
  //    }
  //  }
}
