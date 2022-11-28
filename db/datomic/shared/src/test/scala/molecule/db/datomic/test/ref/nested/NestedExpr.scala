package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedExpr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Expressions in nested" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i).insert(List((1, List(1, 2, 3)))).transact

      Ns.i_.Rs1.*(R1.i.a1).query.get ==> List(List(1, 2, 3))
      Ns.i_.Rs1.*(R1.i(1).a1).query.get ==> List(List(1))
      Ns.i_.Rs1.*(R1.i(1, 2).a1).query.get ==> List(List(1, 2))
      Ns.i_.Rs1.*(R1.i.not(1).a1).query.get ==> List(List(2, 3))
      Ns.i_.Rs1.*(R1.i.not(1, 2).a1).query.get ==> List(List(3))
      Ns.i_.Rs1.*(R1.i.<(2).a1).query.get ==> List(List(1))
      Ns.i_.Rs1.*(R1.i.<=(2).a1).query.get ==> List(List(1, 2))
      Ns.i_.Rs1.*(R1.i.>(2).a1).query.get ==> List(List(3))
      Ns.i_.Rs1.*(R1.i.>=(2).a1).query.get ==> List(List(2, 3))
    }


    "Expression inside optional nested not allowed" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i).insert(List((1, List(1, 2, 3)))).transact

      // Expression before optional nested ok
      Ns.i(1).Rs1.*?(R1.i).query.get ==> List((1, List(1, 2, 3)))

      // Expressions inside optional nested not allowed

      intercept[MoleculeException](
        Ns.i_.Rs1.*?(R1.i(1)).query.get
      ).message ==> "Expressions not allowed in optional nested data structure. Found:\n" +
        "AttrOneManInt(R1,i,Appl,List(1),None,None,None)"

      intercept[MoleculeException](
        Ns.i_.Rs1.*?(R1.i.not(1)).query.get
      ).message ==> "Expressions not allowed in optional nested data structure. Found:\n" +
        "AttrOneManInt(R1,i,Not,List(1),None,None,None)"

      intercept[MoleculeException](
        Ns.i_.Rs1.*?(R1.i.<(2)).query.get
      ).message ==> "Expressions not allowed in optional nested data structure. Found:\n" +
        "AttrOneManInt(R1,i,Lt,List(2),None,None,None)"

      intercept[MoleculeException](
        Ns.i_.Rs1.*?(R1.i.<=(2)).query.get
      ).message ==> "Expressions not allowed in optional nested data structure. Found:\n" +
        "AttrOneManInt(R1,i,Le,List(2),None,None,None)"

      intercept[MoleculeException](
        Ns.i_.Rs1.*?(R1.i.>(2)).query.get
      ).message ==> "Expressions not allowed in optional nested data structure. Found:\n" +
        "AttrOneManInt(R1,i,Gt,List(2),None,None,None)"

      intercept[MoleculeException](
        Ns.i_.Rs1.*?(R1.i.>=(2)).query.get
      ).message ==> "Expressions not allowed in optional nested data structure. Found:\n" +
        "AttrOneManInt(R1,i,Ge,List(2),None,None,None)"
    }

  }
}
