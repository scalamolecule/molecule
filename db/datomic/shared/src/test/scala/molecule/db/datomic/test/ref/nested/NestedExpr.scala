package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedExpr extends DatomicTestSuite {


  lazy val tests = Tests {

    "Expressions in nested" - refs { implicit conn =>
      Ns.n.Rs1.*(R1.n1).insert(List((1, List(1, 2, 3)))).transact

      Ns.n_.Rs1.*(R1.n1.a1).query.get ==> List(List(1, 2, 3))
      Ns.n_.Rs1.*(R1.n1(1).a1).query.get ==> List(List(1))
      Ns.n_.Rs1.*(R1.n1(1, 2).a1).query.get ==> List(List(1, 2))
      Ns.n_.Rs1.*(R1.n1.not(1).a1).query.get ==> List(List(2, 3))
      Ns.n_.Rs1.*(R1.n1.not(1, 2).a1).query.get ==> List(List(3))
      Ns.n_.Rs1.*(R1.n1.<(2).a1).query.get ==> List(List(1))
      Ns.n_.Rs1.*(R1.n1.<=(2).a1).query.get ==> List(List(1, 2))
      Ns.n_.Rs1.*(R1.n1.>(2).a1).query.get ==> List(List(3))
      Ns.n_.Rs1.*(R1.n1.>=(2).a1).query.get ==> List(List(2, 3))
    }


    "Expression inside optional nested not allowed" - refs { implicit conn =>
      Ns.n.Rs1.*(R1.n1).insert(List((1, List(1, 2, 3)))).transact

      // Expression before optional nested ok
      Ns.n(1).Rs1.*?(R1.n1).query.get ==> List((1, List(1, 2, 3)))


      // Expressions inside optional nested not allowed

      intercept[MoleculeException](
        Ns.n_.Rs1.*?(R1.n1(1)).query.get
      ).message ==> "Expressions not allowed in nested data structure. Found:\n" +
        "AttrOneManInt(R1,n1,Appl,List(1),None,None,None)"

      intercept[MoleculeException](
        Ns.n_.Rs1.*?(R1.n1.not(1)).query.get
      ).message ==> "Expressions not allowed in nested data structure. Found:\n" +
        "AttrOneManInt(R1,n1,Not,List(1),None,None,None)"

      intercept[MoleculeException](
        Ns.n_.Rs1.*?(R1.n1.<(2)).query.get
      ).message ==> "Expressions not allowed in nested data structure. Found:\n" +
        "AttrOneManInt(R1,n1,Lt,List(2),None,None,None)"

      intercept[MoleculeException](
        Ns.n_.Rs1.*?(R1.n1.<=(2)).query.get
      ).message ==> "Expressions not allowed in nested data structure. Found:\n" +
        "AttrOneManInt(R1,n1,Le,List(2),None,None,None)"

      intercept[MoleculeException](
        Ns.n_.Rs1.*?(R1.n1.>(2)).query.get
      ).message ==> "Expressions not allowed in nested data structure. Found:\n" +
        "AttrOneManInt(R1,n1,Gt,List(2),None,None,None)"

      intercept[MoleculeException](
        Ns.n_.Rs1.*?(R1.n1.>=(2)).query.get
      ).message ==> "Expressions not allowed in nested data structure. Found:\n" +
        "AttrOneManInt(R1,n1,Ge,List(2),None,None,None)"
    }

  }
}
