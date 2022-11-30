package molecule.db.datomic.test.crud.insert

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs.{Ns, _}
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object InsertSemantics extends DatomicTestSuite {


  lazy val tests = Tests {

    "Can't mix save/insert" - refs { implicit conn =>
      intercept[MoleculeException](
        (Ns.i(1) + R2.i(2)).insert(1, 2).transact
      ).message ==> "Can't insert attributes with applied value. Found:\n" +
        "AttrOneManInt(Ns,i,Appl,List(1),None,None,None)"

      intercept[MoleculeException](
        (Ns.i + R2.i(2)).insert(1, 2).transact
      ).message ==> "Can't insert attributes with applied value. Found:\n" +
        "AttrOneManInt(R2,i,Appl,List(2),None,None,None)"
    }


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.i.i.insert(1, 2).transact
        ).message ==> "Can't transact duplicate element: Ns.i"
      }

      "After backref" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.i.R1.i._Ns.i.insert(1, 2, 3).transact
        ).message ==> "Can't transact duplicate element: Ns.i"

        intercept[MoleculeException](
          Ns.i.R1.i.R2.i._R1.i.insert(1, 2, 3, 4).transact
        ).message ==> "Can't transact duplicate element: R1.i"

        intercept[MoleculeException](
          Ns.i.R1.i.R2.i._R1._Ns.i.insert(1, 2, 3, 4).transact
        ).message ==> "Can't transact duplicate element: Ns.i"
      }
    }


    "Duplicate attributes not allowed, composite" - {

      "Within sub tuple" - refs { implicit conn =>
        // Each sub tuple has same semantics as flat molecule

        // Same ns
        intercept[MoleculeException](
          (R2.i + Ns.i.i).insert(0, (1, 2)).transact
        ).message ==> "Can't transact duplicate element: Ns.i"

        // After backref
        intercept[MoleculeException](
          (R2.i + Ns.i.R1.i._Ns.i).insert(0, (1, 2, 3)).transact
        ).message ==> "Can't transact duplicate element: Ns.i"

        intercept[MoleculeException](
          (R2.i + Ns.i.R1.i.R2.i._R1.i).insert(0, (1, 2, 3, 4)).transact
        ).message ==> "Can't transact duplicate element: R1.i"

        intercept[MoleculeException](
          (R2.i + Ns.i.R1.i.R2.i._R1._Ns.i).insert(0, (1, 2, 3, 4)).transact
        ).message ==> "Can't transact duplicate element: Ns.i"
      }

      "Across sub tuples, top level" - refs { implicit conn =>
        intercept[MoleculeException](
          (Ns.i + Ns.i).insert(1, 2).transact
        ).message ==> "Can't transact duplicate element: Ns.i"
      }

      "Across sub tuples, ref" - refs { implicit conn =>
        // On different levels is ok
        (Ns.i.R1.i + R1.i).insert((1, 2), 3).transact

        // Duplicates on same referenced level not allowed
        intercept[MoleculeException](
          (Ns.i.R1.i + Ns.s.R1.i).insert((1, 2), ("a", 3)).transact
        ).message ==> "Can't transact duplicate element: R1.i"
      }

      "Across sub tuples, after backref" - refs { implicit conn =>
        intercept[MoleculeException](
          (Ns.s + Ns.i.R1.i._Ns.s).insert("a", (1, 2, "b")).transact
        ).message ==> "Can't transact duplicate element: Ns.s"

        intercept[MoleculeException](
          (Ns.s + Ns.i.R1.i.R2.i._R1._Ns.s).insert("a", (1, 2, 3, "b")).transact
        ).message ==> "Can't transact duplicate element: Ns.s"
      }
    }


    "Duplicate attributes not allowed, nested" - {

      "Same ns" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.i.Rs1.*(R1.i.i).insert(1, List((2, 3))).transact
        ).message ==> "Can't transact duplicate element: R1.i"

        intercept[MoleculeException](
          Ns.i.Rs1.*?(R1.i.i).insert(1, List((2, 3))).transact
        ).message ==> "Can't transact duplicate element: R1.i"
      }

      "Backref in nested" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.i.Rs1.*(R1.i.R2.i._R1.i).insert(1, List((2, 3, 4))).transact
        ).message ==> "Can't transact duplicate element: R1.i"

        intercept[MoleculeException](
          Ns.i.Rs1.*?(R1.i.R2.i._R1.i).insert(1, List((2, 3, 4))).transact
        ).message ==> "Can't transact duplicate element: R1.i"
      }
    }


    "Backref in nested" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i.Rs1.*(R1.i._Ns.i).insert(1, List((2, 3))).transact
      ).message ==> "Can't use backref from here."

      intercept[MoleculeException](
        Ns.i.Rs1.*?(R1.i._Ns.i).insert(1, List((2, 3))).transact
      ).message ==> "Can't use backref from here."

      // ok
      Ns.i.Rs1.*(R1.i.R2.i._R1.s).insert(1, List((2, 3, "a"))).transact
      Ns.i.Rs1.*?(R1.i.R2.i._R1.s).insert(1, List((2, 3, "a"))).transact
    }
  }
}
