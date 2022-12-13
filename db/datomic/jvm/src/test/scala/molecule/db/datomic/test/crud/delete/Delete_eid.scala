package molecule.db.datomic.test.crud.delete

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Delete_eid extends DatomicTestSuite {


  lazy val tests = Tests {

    "1 entity" - refs { implicit conn =>
      val List(e1, _) = Ns.i.insert(1, 2).transact.eids
      Ns.i.query.get ==> List(1, 2)

      // When deleting one entity, `multiple` modifier is not required
      Ns(e1).delete.transact
      Ns.i.query.get ==> List(2)
    }


    "n entities, vararg" - refs { implicit conn =>
      val List(e1, e2, _) = Ns.i.insert(1, 2, 3).transact.eids
      Ns.i.query.get ==> List(1, 2, 3)

      // When deleting multiple entities, `multiple` modifier is required
      Ns(e1, e2).delete.multiple.transact
      Ns.i.query.get ==> List(3)
    }

    "n entities, iterable" - refs { implicit conn =>
      val List(e1, e2, _) = Ns.i.insert(1, 2, 3).transact.eids
      Ns.i.query.get ==> List(1, 2, 3)

      Ns(Seq(e1, e2)).delete.multiple.transact
      Ns.i.query.get ==> List(3)
    }

    "0 entities" - refs { implicit conn =>
      Ns.i.insert(1, 2, 3).transact.eids
      Ns.i.query.get ==> List(1, 2, 3)

      Ns(Nil).delete.transact

      // No entities deleted
      Ns.i.query.get ==> List(1, 2, 3)
    }


    "Referenced entities" - {

      "Card-one" - refs { implicit conn =>
        val e1 = Ns.i.R1.i.insert(
          (1, 10),
          (2, 20)
        ).transact.eids.head

        // 2 entities, each referencing another entity
        Ns.i.a1.R1.i.query.get ==> List(
          (1, 10),
          (2, 20)
        )
        // 2 other entities
        R1.i.a1.query.get ==> List(10, 20)

        Ns(e1).delete.transact

        // 1 entity with a referenced to another entity left
        Ns.i.R1.i.query.get ==> List((2, 20))

        // 2 other entities remain
        R1.i.a1.query.get ==> List(10, 20)
      }


      "Card-many" - refs { implicit conn =>
        val e1 = Ns.i.Rs1.*(R1.i).insert(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        ).transact.eids.head

        // 2 entities, each with 2 owned sub-entities
        Ns.i.a1.Rs1.*(R1.i.a1).query.get ==> List(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        )
        // 4 referenced entities
        R1.i.a1.query.get ==> List(10, 11, 20, 21)

        Ns(e1).delete.transact

        // 1 entity with 2 owned sub-entities left
        Ns.i.Rs1.*(R1.i.a1).query.get ==> List(
          (2, Seq(20, 21))
        )
        // 2 other entities remain
        R1.i.a1.query.get ==> List(10, 11, 20, 21)
      }
    }


    "Owned entities" - {

      "Card-one" - refs { implicit conn =>
        val e1 = Ns.i.Owned1.i.insert(
          (1, 10),
          (2, 20)
        ).transact.eids.head

        // 2 entities, each with an owned sub-entity
        Ns.i.a1.Owned1.i.query.get ==> List(
          (1, 10),
          (2, 20)
        )
        // 2 sub-entities
        R1.i.a1.query.get ==> List(10, 20)

        Ns(e1).delete.transact

        // 1 entity with 1 owned sub-entity left
        Ns.i.Owned1.i.query.get ==> List((2, 20))
        R1.i.query.get ==> List(20)
      }


      "Card-many" - refs { implicit conn =>
        val e1 = Ns.i.OwnedMany1.*(R1.i).insert(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        ).transact.eids.head

        // 2 entities, each with 2 owned sub-entities
        Ns.i.a1.OwnedMany1.*(R1.i.a1).query.get ==> List(
          (1, Seq(10, 11)),
          (2, Seq(20, 21))
        )
        // 4 sub-entities
        R1.i.a1.query.get ==> List(10, 11, 20, 21)

        Ns(e1).delete.transact

        // 1 entity with 2 owned sub-entities left
        Ns.i.OwnedMany1.*(R1.i.a1).query.get ==> List(
          (2, Seq(20, 21))
        )
        R1.i.a1.query.get ==> List(20, 21)
      }
    }


    "Composite" - refs { implicit conn =>
      val List(e1, e2, _) = (Ns.i + R1.i).insert(
        (1, 10),
        (2, 20),
        (3, 30),
      ).transact.eids

      // 3 composite entities, each with 2 attributes from 2 namespaces
      (Ns.i.a1 + R1.i).query.get ==> List(
        (1, 10),
        (2, 20),
        (3, 30),
      )
      // 3 composite sub-groups
      R1.i.a1.query.get ==> List(10, 20, 30)

      // Delete entity using namespace of first group
      Ns(e1).delete.transact
      // Delete entity using namespace of second group
      R1(e2).delete.transact

      // 1 composite entity with 2 attributes from 2 namespaces left
      (Ns.i + R1.i).query.get ==> List((3, 30))
      R1.i.query.get ==> List(30)
    }


    "Semantics" - {

      "e_(eid) not allowed" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.e_(42).i(2).delete.transact
        ).message ==> "Can't delete by applying entity ids to e_"
      }

      "Tacit generic attributes not allowed" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns(42).a_("x").delete.transact
        ).message ==> "Generic attributes not allowed in update molecule. Found:\n" +
          "AttrOneTacString(_Generic,a,Appl,List(x),None,None,None)"
      }

      "Mandatory generic attributes not allowed" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns(42).a("x").delete.transact
        ).message ==> "Generic attributes not allowed in update molecule. Found:\n" +
          "AttrOneManString(_Generic,a,Appl,List(x),None,None,None)"
      }

      "Can't update multiple values for one card-one attribute" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns(42).i(2, 3).update.transact
        ).message ==> "Can only update one value for attribute `Ns.i`. Found: ArraySeq(2, 3)"
      }

      "Can't update optional values" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns(42).i_?(Some(1)).update.transact
        ).message ==> "Can't update optional values. Found:\n" +
          "AttrOneOptInt(Ns,i,Appl,Some(List(1)),None,None,None)"
      }

      "Can't update card-many referenced attributes" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns(42).i(1).Rs1.i(2).update.transact
        ).message ==> "Can't update attributes in card-many referenced namespaces. Found `Rs1`"
      }
    }
  }
}
