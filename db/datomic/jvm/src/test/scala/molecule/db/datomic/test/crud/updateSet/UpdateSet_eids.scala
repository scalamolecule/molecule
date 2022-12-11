package molecule.db.datomic.test.crud.updateSet

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSet_eids extends DatomicTestSuite {


  lazy val tests = Tests {

    "Update/upsert" - types { implicit conn =>
      val eid = Ns.ints.insert(Set(1)).transact.eids.head
      Ns.ints.query.get ==> List(Set(1))

      Ns(eid).ints(Set(2)).update.transact
      Ns.ints.query.get ==> List(Set(2))

      // Updating a non-asserted attribute has no effect
      Ns(eid).strings(Set("a")).update.transact
      Ns.ints.strings_?.query.get ==> List((Set(2), None))

      // Upserting a non-asserted attribute adds the value
      Ns(eid).strings(Set("a")).upsert.transact
      Ns.ints.strings_?.query.get ==> List((Set(2), Some(Set("a"))))
    }


    "Multiple entities updated" - types { implicit conn =>
      val List(a, b, c) = Ns.ints.insert(Set(1), Set(2), Set(3)).transact.eids
      Ns.e.a1.ints.query.get ==> List(
        (a, Set(1)),
        (b, Set(2)),
        (c, Set(3)),
      )

      // Explicitly add `multiple` to update multiple entities
      Ns(List(b, c)).ints(4).update.multiple.transact
      Ns.e.a1.ints.query.get ==> List(
        (a, Set(1)),
        (b, Set(4)),
        (c, Set(4)),
      )

      // Not adding `multiple` prevents unintentional update of multiple (possible all!) entities
      intercept[MoleculeException](
        Ns(List(b, c)).ints(5).update.transact
      ).message ==> "Please provide explicit `update.multiple` to update multiple entities."
    }


    "Delete individual attribute value(s) with update" - types { implicit conn =>
      val eid = Ns.ints.strings.insert(Set(1), Set("a")).transact.eids.head
      Ns.ints.strings.query.get ==> List((Set(1), Set("a")))

      // Apply empty value to delete attribute of entity (entity remains)
      Ns(eid).strings().update.transact
      Ns.ints.strings_?.query.get ==> List((Set(1), None))
    }


    "Update multiple attributes" - types { implicit conn =>
      val eid = Ns.ints.strings.insert(Set(1), Set("a")).transact.eids.head
      Ns.ints.strings.query.get ==> List((Set(1), Set("a")))

      // Apply empty value to delete attribute of entity (entity remains)
      Ns(eid).ints(2).strings("b").update.transact
      Ns.ints.strings.query.get ==> List((Set(2), Set("b")))
    }


    "Referenced attributes" - types { implicit conn =>
      val eid = Ns.ints(Set(1)).Ref.ii(Set(2)).save.transact.eids.head
      Ns.ints.Ref.ii.query.get ==> List((Set(1), Set(2)))

      Ns(eid).ints(Set(3)).Ref.ii(Set(4)).update.transact
      Ns.ints.Ref.ii.query.get ==> List((Set(3), Set(4)))

      Ns(eid).Ref.ii(5).update.transact
      Ns.ints.Ref.ii.query.get ==> List((Set(3), Set(5)))
    }


    "Update composite attributes" - types { implicit conn =>
      val eid = (Ns.ints.strings + Ref.ii.ss)
        .insert((Set(1), Set("a")), (Set(2), Set("b"))).transact.eids.head
      (Ns.ints.strings + Ref.ii.ss).query.get.head ==> ((Set(1), Set("a")), (Set(2), Set("b")))


      // Composite sub groups share the same entity id
      (Ns(eid).ints(Set(3)).strings(Set("c")) + Ref.ii(Set(4)).ss(Set("d"))).update.transact
      (Ns.ints.strings + Ref.ii.ss).query.get.head ==> ((Set(3), Set("c")), (Set(4), Set("d")))

      // Updating sub group with same entity id
      Ref(eid).ii(Set(5)).update.transact
      (Ns.ints.strings + Ref.ii.ss).query.get.head ==> ((Set(3), Set("c")), (Set(5), Set("d")))
    }


    "Update tx meta data" - types { implicit conn =>
      val eid = Ns.ints.Tx(Other.ss_(Set("tx"))).insert(Set(1)).transact.eids.head
      Ns.ints.Tx(Other.ss).query.get.head ==> (Set(1), Set("tx"))

      val tx = Ns(eid).ints(2).Tx(Other.ss(Set("tx2"))).update.transact.tx
      Ns.ints.Tx(Other.ss).query.get.head ==> (Set(2), Set("tx2"))

      intercept[MoleculeException](
        Ns(eid).Tx(Other.ss(Set("tx3"))).update.transact
      ).message ==> "Can't update tx meta data only."

      // We can though update the tx entity itself
      Other(tx).ss(Set("tx3")).update.transact
      Ns.ints.Tx(Other.ss).query.get.head ==> (Set(2), Set("tx3"))
    }


    "Composite + tx meta data" - types { implicit conn =>
      val eid = (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii_(Set(42)))
        .insert((Set(1), Set("a")), (Set(2), Set("b"))).transact.eids.head
      (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii).query.get.head ==>
        ((Set(1), Set("a")), (Set(2), Set("b")), Set(42))

      // Composite sub groups share the same entity id
      (Ns(eid).ints(Set(3)).strings(Set("c")) +
        Ref.ii(Set(4)).ss(Set("d"))).Tx(Other.ii(Set(43))).update.transact
      (Ns.ints.strings + Ref.ii.ss).Tx(Other.ii).query.get.head ==>
        ((Set(3), Set("c")), (Set(4), Set("d")), Set(43))
    }


    "Semantics" - {

      "e_(eid)" - types { implicit conn =>
        val eid = Ns.ints.insert(Set(1)).transact.eids.head
        Ns.ints.query.get ==> List(Set(1))

        // Works the same way as Ns(eid)
        Ns.e_(eid).ints(2).update.transact
        Ns.ints.query.get ==> List(Set(2))

        // e_ doesn't have to be first
        Ns.ints(3).e_(eid).update.transact
        Ns.ints.query.get ==> List(Set(3))
      }

      "Can't update multiple values for one card-one attribute" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(42).ints(Seq(Set(1), Set(2))).update.transact
        ).message ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: List(Set(1), Set(2))"

        // Same as
        intercept[MoleculeException](
          Ns(42).ints(Set(1), Set(2)).update.transact
        ).message ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: ArraySeq(Set(1), Set(2))"

        // Same as
        intercept[MoleculeException](
          Ns(42).ints(1, 2).update.transact
        ).message ==> "Can only update one Set of values for Set attribute `Ns.ints`. Found: ArraySeq(Set(1), Set(2))"
      }

      "Can't update optional values" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(42).ints_?(Some(Set(1))).update.transact
        ).message ==> "Can't update optional values. Found:\n" +
          "AttrSetOptInt(Ns,ints,Appl,Some(List(Set(1))),None,None,None)"
      }

      "Empty list of eids not allowed" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(Nil).ints(Set(2)).update.transact
        ).message ==> "Empty list of entity ids not allowed."
      }

      "Can't update card-many referenced attributes" - types { implicit conn =>
        intercept[MoleculeException](
          Ns(42).i(1).Refs.i(2).update.transact
        ).message ==> "Can't update attributes in card-many referenced namespaces. Found `Refs`"
      }
    }
  }
}
