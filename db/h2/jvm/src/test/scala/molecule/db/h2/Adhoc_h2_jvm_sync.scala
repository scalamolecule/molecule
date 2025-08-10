package molecule.db.h2

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.setup.DbProviders_h2
import molecule.db.h2.sync.*

class Adhoc_h2_jvm_sync extends MUnit with DbProviders_h2 with TestUtils {


  //  "basic" - types {
  //    import molecule.db.compliance.domains.dsl.Types.*
  //
  //    val List(a, b) = Entity.int.insert(1, 2).transact.ids
  //    Entity.int(3).save.transact
  //    Entity.int.a1.query.get ==> List(1, 2, 3)
  //    Entity(a).int(10).update.transact
  //    Entity(b).delete.transact
  //    Entity.int.a1.query.get ==> List(3, 10)
  //  }

  "accounting" - accounting {
    import molecule.db.compliance.domains.relationship.dsl.Accounting.*

    //    InvoiceLine.amount(10).Invoice.no(1).save.transact
    //    rawQuery(
    //      """SELECT DISTINCT
    //        |  InvoiceLine.amount,
    //        |  Invoice.no
    //        |FROM InvoiceLine
    //        |  INNER JOIN Invoice ON
    //        |    InvoiceLine.invoice = Invoice.id
    //        |WHERE
    //        |  InvoiceLine.amount IS NOT NULL AND
    //        |  Invoice.no         IS NOT NULL;
    //        |
    //        |""".stripMargin, true
    //    )
    //    rawQuery(
    //      """SELECT DISTINCT
    //        |  Invoice.no,
    //        |  InvoiceLine.amount
    //        |FROM Invoice
    //        |  INNER JOIN InvoiceLine ON
    //        |    Invoice.id = InvoiceLine.invoice
    //        |WHERE
    //        |  InvoiceLine.amount IS NOT NULL AND
    //        |  Invoice.no         IS NOT NULL;
    //        |""".stripMargin, true
    //    )




//    Invoice.no.Lines.*(InvoiceLine.amount).insert(
//      (1, List(10, 20, 30)),
//      (2, List(20, 70)),
//    ).i.transact
/*
DataModel(
  List(
    AttrOneManInt("Invoice", "no", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 1)),
    Nested(
      Ref("Invoice", "lines", "InvoiceLine", OneToMany, true, List(0, 2, 1), Some(invoice)),
      List(
        AttrOneManInt("InvoiceLine", "amount", V, Seq(), None, None, Nil, Nil, None, None, false, List(1, 5))))
  ),
  Set(1, 2, 5), -1, 0, Nil
)

Insert(
  Entity(
    Nested(
      INSERT INTO InvoiceLine (
        amount
      ) VALUES (?)
    )
    ---------------------------
    INSERT INTO Invoice (
      no
    ) VALUES (?)
  )
  ---------------------------
  NestedJoins(
    INSERT INTO Invoice_lines_InvoiceLine (
      Invoice_id, InvoiceLine_id
    ) VALUES (?, ?)
  )
)

(1,List(10, 20, 30))
(2,List(20, 70))
 */

    Invoice.no.Lines.amount.insert(
      (1, 10)
    ).i.transact

    /*
DataModel(
  List(
    AttrOneManInt("Invoice", "no", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 1)),
    Ref("Invoice", "lines", "InvoiceLine", OneToMany, true, List(0, 2, 1), Some(invoice)),
    AttrOneManInt("InvoiceLine", "amount", V, Seq(), None, None, Nil, Nil, None, None, false, List(1, 5))
  ),
  Set(1, 2, 5), 0, 0, Nil
)

Insert(
  Entity(
    RefMany(
      INSERT INTO InvoiceLine (
        amount
      ) VALUES (?)
    )
    ---------------------------
    INSERT INTO Invoice (
      no
    ) VALUES (?)
  )
  ---------------------------
  RefJoin(
    INSERT INTO Invoice_lines_InvoiceLine (
      Invoice_id, InvoiceLine_id
    ) VALUES (?, ?)
  )
)
     */

//    InvoiceLine.amount.Invoice.no.insert(
//      (20, 2)
//    ).i.transact
    /*
DataModel(
  List(
    AttrOneManInt("InvoiceLine", "amount", V, Seq(), None, None, Nil, Nil, None, None, false, List(1, 5)),
    Ref("InvoiceLine", "invoice", "Invoice", ManyToOne, false, List(1, 4, 0), Some(lines)),
    AttrOneManInt("Invoice", "no", V, Seq(), None, None, Nil, Nil, None, None, false, List(0, 1))
  ),
  Set(5, 4, 1), 0, 0, Nil
)

Insert(
  Entity(
    RefOne(
      INSERT INTO Invoice (
        no
      ) VALUES (?)
    )
    ---------------------------
    INSERT INTO InvoiceLine (
      amount,
      invoice
    ) VALUES (?, ?)
  )
)
     */

    //    val List(a, b) = Invoice.no.insert(1, 2).transact.ids
    //    InvoiceLine.amount.invoice.insert(
    //      (10, a),
    //      (20, a),
    //      (30, a),
    //      (20, b),
    //      (70, b),
    //    ).transact
    //

    //    InvoiceLine.amount.Invoice.no.query.get ==> List(
    //      (10, 1),
    //      (20, 1),
    //      (20, 2),
    //      (30, 1),
    //      (70, 2),
    //    )
    //    Invoice.no.Lines.amount.query.get ==> List(
    //      (1, 10),
    //      (1, 20),
    //      (1, 30),
    //      (2, 20),
    //      (2, 70),
    //    )

    //    rawQuery(
    //      """SELECT DISTINCT
    //        |  Invoice.id,
    //        |  Invoice.no,
    //        |  InvoiceLine.amount
    //        |FROM Invoice
    //        |  INNER JOIN InvoiceLine ON
    //        |    Invoice.id = InvoiceLine.invoice
    //        |WHERE
    //        |  InvoiceLine.amount IS NOT NULL AND
    //        |  Invoice.no         IS NOT NULL;
    //        |""".stripMargin, true
    //    )

    Invoice.no.Lines.*(InvoiceLine.amount).query.get ==> List(
      (1, List(10, 20, 30)),
      (2, List(20, 70)),
    )

    //      rawQuery(
    //        """SELECT DISTINCT
    //          |  Invoice.no,
    //          |  InvoiceLine.amount
    //          |FROM InvoiceLine
    //          |  RIGHT JOIN Invoice ON
    //          |    Invoice.id = InvoiceLine.invoice
    //          |WHERE
    //          |  Invoice.no IS NOT NULL
    //          |
    //          |""".stripMargin, true
    //      )


    //    Invoice.no.Lines.?(InvoiceLine.amount).query.get ==> List(
    //      (1, Some(10)),
    //      (1, Some(20)),
    //      (1, Some(30)),
    //      (2, Some(20)),
    //      (2, Some(70)),
    //    )
    //    InvoiceLine.amount.Invoice.no.query.get ==> List(
    //      (10, 1),
    //      (20, 1),
    //      (20, 2),
    //      (30, 1),
    //      (70, 2),
    //    )
    //    InvoiceLine.amount.Invoice.?(Invoice.no).query.get ==> List(
    //      (10, Some(1)),
    //      (20, Some(1)),
    //      (20, Some(2)),
    //      (30, Some(1)),
    //      (70, Some(2)),
    //    )
    //
    //
    //    Invoice.?(Invoice.no).Lines.amount.query.get ==> List(
    //      (Some(1), 10),
    //      (Some(1), 20),
    //      (Some(1), 30),
    //      (Some(2), 20),
    //      (Some(2), 70),
    //    )
    //    InvoiceLine.?(InvoiceLine.amount).Invoice.no.query.get ==> List(
    //      (Some(10), 1),
    //      (Some(20), 1),
    //      (Some(20), 2),
    //      (Some(30), 1),
    //      (Some(70), 2),
    //    )
  }


  //  "integer" - types {
  //    import molecule.db.compliance.domains.dsl.Types.*
  //
  //    // Optional
  //    val s2: (Int, String, Option[Long]) = Entity.i.s.long_?.apply(Some(long1)).query.get.head
  //
  //
  //    val s2: String = Entity.s.i.apply("foo").query.get.head
  //    val s2: String = Entity.s.i.apply("foo").query.get.head
  //    val s2: String = Entity.s.i.apply("foo").query.get.head
  //
  //    val s2a: String = Entity.i_.s.query.get.head
  //
  //    val s2a: (Int, String) = Entity.i.s.query.get.head
  //    val s2a: String        = Entity.i_.s.query.get.head
  //    val s2a: Int           = Entity.i.s_.query.get.head
  //
  //    val s2a: Int            = Entity.i.s_.apply("foo").query.get.head
  //    val s2a: (Boolean, Int) = Entity.boolean.i.s_.apply("foo").query.get.head
  //
  //    val s2a: (Int, Set[Int]) = Entity.i.intSet.has(3).query.get.head
  //    val s2a: Int             = Entity.i.intSet_.has(3).query.get.head
  //
  //    val s3: (Int, String) = Entity.i.s.query.get.head
  //    val s4: (Int, String) = Entity.i.s.apply("foo").query.get.head
  //    val s5: (Int, String) = Entity.i.color.apply(Color.Blue).query.get.head
  //
  //    //    Entity(id2).aget(43).update.transact
  //  }


  //  "Subscription" - types {
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


  //  "commit" - refs {
  //    import molecule.db.compliance.domains.dsl.Refs.*
  //
  //    A.i.insert(1, 2, 3).transact
  //    A.i.query.stream.toList.sorted ==> List(1, 2, 3)
  //  }
}
