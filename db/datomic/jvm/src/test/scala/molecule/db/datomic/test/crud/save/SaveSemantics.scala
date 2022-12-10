package molecule.db.datomic.test.crud.save

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object SaveSemantics extends DatomicTestSuite {


  lazy val tests = Tests {

    "Can't mix save/insert" - refs { implicit conn =>
      intercept[MoleculeException](
        (Ns.i + R2.i).save.transact
      ).message ==> "Can't save attributes without an applied value. Found:\n" +
        "AttrOneManInt(Ns,i,V,List(),None,None,None)"

      intercept[MoleculeException](
        (Ns.i(1) + R2.i).save.transact
      ).message ==> "Can't save attributes without an applied value. Found:\n" +
        "AttrOneManInt(R2,i,V,List(),None,None,None)"
    }


    "Duplicate attributes not allowed, flat" - {

      "Same ns" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.i(1).i(2).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."
      }

      "After backref" - refs { implicit conn =>
        intercept[MoleculeException](
          Ns.i(1).R1.i(2)._Ns.i(3).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."

        intercept[MoleculeException](
          Ns.i(1).R1.i(2).R2.i(3)._R1.i(4).save.transact
        ).message ==> "Can't transact duplicate attribute `R1.i`."

        intercept[MoleculeException](
          Ns.i(1).R1.i(2).R2.i(3)._R1._Ns.i(4).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."
      }
    }


    "Duplicate attributes not allowed, composite" - {

      "Within sub tuple" - refs { implicit conn =>
        // Each sub tuple has same semantics as flat molecule

        // Same ns
        intercept[MoleculeException](
          (R2.i(1) + Ns.i(2).i(3)).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."

        // After backref
        intercept[MoleculeException](
          (R2.i(1) + Ns.i(2).R1.i(3)._Ns.i(4)).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."

        intercept[MoleculeException](
          (R2.i(1) + Ns.i(2).R1.i(3).R2.i(4)._R1.i(5)).save.transact
        ).message ==> "Can't transact duplicate attribute `R1.i`."

        intercept[MoleculeException](
          (R2.i(1) + Ns.i(2).R1.i(3).R2.i(4)._R1._Ns.i(5)).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."
      }

      "Across sub tuples, top level" - refs { implicit conn =>
        intercept[MoleculeException](
          (Ns.i(1) + Ns.i(2)).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.i`."
      }

      "Across sub tuples, ref" - refs { implicit conn =>
        // On different levels is ok
        (Ns.i(1).R1.i(2) + R1.i(3)).save.transact

        // Can't reference same ns twice
        intercept[MoleculeException](
          (Ns.i(1).R1.i(2) + Ns.s("a").R1.s("b")).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.r1`."
      }

      "Across sub tuples, after backref" - refs { implicit conn =>
        intercept[MoleculeException](
          (Ns.s("a") + Ns.i(1).R1.i(2)._Ns.s("b")).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.s`."

        intercept[MoleculeException](
          (Ns.s("a") + Ns.i(1).R1.i(2).R2.i(3)._R1._Ns.s("b")).save.transact
        ).message ==> "Can't transact duplicate attribute `Ns.s`."
      }
    }


    "Nested data can only be inserted, not saved" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i(0).Rs1.*(R1.i(1)).save.transact
      ).message ==> "Nested data structure not allowed in save molecule. " +
        "Please use insert instead."

      intercept[MoleculeException](
        Ns.i(0).Rs1.*?(R1.i(1)).save.transact
      ).message ==> "Optional nested data structure not allowed in save molecule. " +
        "Please use insert instead."

      // ok
      Ns.i.Rs1.*(R1.i).insert(0, List(1)).transact
      Ns.i.Rs1.*?(R1.i).insert(0, List(1)).transact
    }
  }
}
