package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedSemantics extends DatomicTestSuite {


  lazy val tests = Tests {

    "Nested data can only be inserted, not saved" - refs { implicit conn =>
      intercept[MoleculeException] {
        Ns.int(0).Rs1.*(R1.int1(1)).save.transact
      }.message ==> "Nested data structure not allowed in save molecule."

      intercept[MoleculeException] {
        Ns.int(0).Rs1.*?(R1.int1(1)).save.transact
      }.message ==> "Optional nested data structure not allowed in save molecule."
    }


    "Nested namespace must match" - refs { implicit conn =>
      intercept[MoleculeException] {
        Ns.n.Rs1.*(R4.n4).query.get
      }.message ==> "`Rs1` can only nest to `R1`. Found: `R4`"

      intercept[MoleculeException] {
        Ns.n.Rs1.*?(R4.n4).query.get
      }.message ==> "`Rs1` can only nest to `R1`. Found: `R4`"
    }


    "Mixing *?/* not allowed" - refs { implicit conn =>
      intercept[MoleculeException] {
        Ns.n.Rs1.*(R1.n1.Rs2.*?(R2.n2)).query.get
      }.message ==> "Can't mix mandatory/optional nested data structures."

      intercept[MoleculeException] {
        Ns.n.Rs1.*?(R1.n1.Rs2.*(R2.n2)).query.get
      }.message ==> "Can't mix mandatory/optional nested data structures."
    }



  }
}
