package molecule.db.datomic.test.crud.updateOne


import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.dsl.Unique.Other
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_eid extends DatomicTestSuite {


  lazy val tests = Tests {

    "1 attribute of 1 entity updated" - types { implicit conn =>
      val eid = Ns.int.insert(1).transact.eids.head
      Ns.int.query.get ==> List(1)

      Ns(eid).int(2).update.transact
      Ns.int.query.get ==> List(2)
    }


    "Multiple entities updated" - types { implicit conn =>
      val List(a, b, c) = Ns.int.insert(1, 2, 3).transact.eids
      Ns.e.a1.int.query.get ==> List(
        (a, 1),
        (b, 2),
        (c, 3),
      )

      // Explicitly add `multiple` to update multiple entities
      Ns(List(b, c)).int(4).update.multiple.transact
      Ns.e.a1.int.query.get ==> List(
        (a, 1),
        (b, 4),
        (c, 4),
      )

      // Not adding `multiple` prevents unintentional update of multiple (possible all!) entities
      intercept[MoleculeException](
        Ns(List(b, c)).int(5).update.transact
      ).message ==> "Please provide explicit `update.multiple` to update multiple entities."
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      val eid = Ns.int.string.insert(1, "a").transact.eids.head
      Ns.int.string.query.get ==> List((1, "a"))

      // Apply empty value to delete attribute of entity (entity remains)
      Ns(eid).string().update.transact
      Ns.int.string_?.query.get ==> List((1, None))
    }


    "Update multiple attributes" - types { implicit conn =>
      val eid = Ns.int.string.insert(1, "a").transact.eids.head
      Ns.int.string.query.get ==> List((1, "a"))

      // Apply empty value to delete attribute of entity (entity remains)
      Ns(eid).int(2).string("b").update.transact
      Ns.int.string.query.get ==> List((2, "b"))
    }


    "Referenced attributes" - types { implicit conn =>
      val eid = Ns.i(1).Ref.i(2).save.transact.eids.head
      Ns.i.Ref.i.query.get ==> List((1, 2))

      Ns(eid).i(3).Ref.i(4).update.transact
      Ns.i.Ref.i.query.get ==> List((3, 4))

      Ns(eid).Ref.i(5).update.transact
      Ns.i.Ref.i.query.get ==> List((3, 5))
    }


    "Update composite attributes" - types { implicit conn =>
      val eid = (Ns.int.string + Ref.i.s).insert((1, "a"), (2, "b")).transact.eids.head
      (Ns.int.string + Ref.i.s).query.get ==> List(((1, "a"), (2, "b")))

      // Composite sub groups share the same entity id
      (Ns(eid).int(3).string("c") + Ref.i(4).s("d")).update.transact
      (Ns.int.string + Ref.i.s).query.get ==> List(((3, "c"), (4, "d")))

      // Updating sub group with same entity id
      Ref(eid).i(5).update.transact
      (Ns.int.string + Ref.i.s).query.get ==> List(((3, "c"), (5, "d")))
    }


    "Update tx meta data" - types { implicit conn =>
      val eid = Ns.int.Tx(Other.s_("tx")).insert(1).transact.eids.head
      Ns.int.Tx(Ref.s).query.get ==> List((1, "tx"))

      val tx = Ns(eid).int(2).Tx(Other.s("tx2")).update.transact.tx
      Ns.int.Tx(Ref.s).query.get ==> List((2, "tx2"))

      intercept[MoleculeException](
        Ns(eid).Tx(Other.s("tx3")).update.transact
      ).message ==> "Can't update tx meta data only."

      // We can though update the tx entity itself
      Ref(tx).s("tx3").update.transact
      Ns.int.Tx(Other.s).query.get ==> List((2, "tx3"))
    }


    "Composite + tx meta data" - types { implicit conn =>
      val eid = (Ns.int.string + Ref.i.s).Tx(Other.i_(42)).insert((1, "a"), (2, "b")).transact.eids.head
      (Ns.int.string + Ref.i.s).Tx(Other.i).query.get ==> List(((1, "a"), (2, "b"), 42))

      // Composite sub groups share the same entity id
      (Ns(eid).int(3).string("c") + Ref.i(4).s("d")).Tx(Other.i(43)).update.transact
      (Ns.int.string + Ref.i.s).Tx(Other.i).query.get ==> List(((3, "c"), (4, "d"), 43))
    }


    "Semantics" - {

      "e_(eid)" - types { implicit conn =>
        val eid = Ns.int.insert(1).transact.eids.head
        Ns.int.query.get ==> List(1)

        // Works the same way as Ns(eid)
        Ns.e_(eid).int(2).update.transact
        Ns.int.query.get ==> List(2)

        // e_ doesn't have to be first
        Ns.int(3).e_(eid).update.transact
        Ns.int.query.get ==> List(3)
      }

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(42).int(2, 3).update.transact
        ).message ==> "Can only update one value for attribute `Ns.int`. Found: ArraySeq(2, 3)"
      }

      "Can't update optional values" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(42).int_?(Some(1)).update.transact
        ).message ==> "Can't update optional values. Found:\n" +
          "AttrOneOptInt(Ns,int,Appl,Some(List(1)),None,None,None)"
      }

      "Empty list of eids not allowed" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(Nil).int(2).update.transact
        ).message ==> "Empty list of entity ids not allowed."
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(42).i(1).Refs.i(2).update.transact
        ).message ==> "Can't update ambiguous attributes in card-many referenced namespaces. Found `Refs`"
      }
    }
  }
}
