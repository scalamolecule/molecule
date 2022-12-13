package molecule.db.datomic.test.relation.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedSemantics extends DatomicTestSuite {


  lazy val tests = Tests {

    "Nested namespace must match" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i.Rs1.*(R4.i).query.get
      ).message ==> "`Rs1` can only nest to `R1`. Found: `R4`"

      intercept[MoleculeException](
        Ns.i.Rs1.*?(R4.i).query.get
      ).message ==> "`Rs1` can only nest to `R1`. Found: `R4`"
    }


    "Mixing *?/* not allowed" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i.Rs1.*(R1.i.Rs2.*?(R2.i)).query.get
      ).message ==> "Can't mix mandatory/optional nested data structures."

      intercept[MoleculeException](
        Ns.i.Rs1.*?(R1.i.Rs2.*(R2.i)).query.get
      ).message ==> "Can't mix mandatory/optional nested data structures."
    }
  }
}
