package molecule.db.datomic.test.crud.updateOne


import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "With single entity id" - types { implicit conn =>

      val eid = Ns.int.insert(1).transact.eids.head
      Ns.int.query.get ==> List(1)

      // Apply new value to attribute for entity id `eid`
      Ns(eid).int(2).update.transact
      Ns.int.query.get ==> List(2)

      // Same as
      Ns.e_(eid).int(3).update.transact
      Ns.int.query.get ==> List(3)
    }


    "With multiple entity ids" - types { implicit conn =>
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

      // Forgetting to add `multiple` aborts
      intercept[MoleculeException](
        Ns(List(b, c)).int(5).update.transact
      ).message ==> "Please provide explicit `update.multiple` to update multiple entities."
    }


    "With unique attribute value" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      Ns.iUnique(1).s("a").save.transact

      // Find entity by applying value to unique attribute
      Ns.iUnique_(1).s("b").update.transact
      Ns.s.query.get ==> List("b")

      intercept[MoleculeException](
        Ns.iUnique_(1, 2).s("c").update.transact
      ).message ==> "Can only update with one applied value for tacit attribute `Ns.iUnique_`. Found:\n" +
        "AttrOneTacInt(Ns,iUnique,Appl,ArraySeq(1, 2),None,None,None)"

      intercept[MoleculeException](
        Ns.iUnique_(1).iUniqueI_(1).s("c").update.transact
      ).message ==> "Can only apply one unique attribute value for update. Found:\n" +
        "AttrOneTacInt(Ns,iUniqueI,Appl,List(1),None,None,None)"
    }





    //
    //
    //    "With unique attribute value" - types { implicit conn =>
    //
    //    }
    //
    //    "Matching" - types { implicit conn =>
    //      Ns.int_.>(2).string("new value").update.multiple.transact ==> List(2)
    //    }
  }
}
