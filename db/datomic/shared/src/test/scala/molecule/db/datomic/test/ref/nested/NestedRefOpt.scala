package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedRefOpt extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory attributes" - refs { implicit conn =>
      Ns.n.Rs1.*(R1.n1.R2.n2).insert(1, List((1, 2))).transact
      Ns.n.Rs1.*(R1.n1.int1.R2.n2).insert(2, List((1, 11, 2))).transact
      Ns.n.Rs1.*(R1.n1.R2.n2.int2).insert(3, List((1, 2, 22))).transact
      Ns.n.Rs1.*(R1.n1.int1.R2.n2.int2).insert(4, List((1, 11, 2, 22))).transact


      Ns.n_(1).Rs1.*?(R1.n1.R2.n2).query.get ==> List(List((1, 2)))
      Ns.n_(2).Rs1.*?(R1.n1.int1.R2.n2).query.get ==> List(List((1, 11, 2)))
      Ns.n_(3).Rs1.*?(R1.n1.R2.n2.int2).query.get ==> List(List((1, 2, 22)))
      Ns.n_(4).Rs1.*?(R1.n1.int1.R2.n2.int2).query.get ==> List(List((1, 11, 2, 22)))


      //      Ns.n.Rs1.*(R1.n1.R2.n2.R3.n3).insert(2, List((1, 2, 3))).transact
      //      Ns.n_(2).Rs1.*(R1.n1.R2.n2.R3.n3).query.get ==> List(List((1, 2, 3)))
      //      Ns.n_(2).Rs1.*?(R1.n1.R2.n2.R3.n3).query.get ==> List(List((1, 2, 3)))


    }

    "Optional attribute" - refs { implicit conn =>
      Ns.n.Rs1.*(R1.n1.int1_?.R2.n2).insert(
        (1, List((1, Some(11), 2))),
        (2, List((1, None, 2))),
        (3, Nil),
      ).transact

      Ns.n.Rs1.*?(R1.n1.int1_?.R2.n2).query.get ==> List(
        (1, List((1, Some(11), 2))),
        (2, List((1, None, 2))),
        (3, Nil),
      )


      Ns.n.Rs1.*(R1.n1.R2.n2.int2_?).insert(
        (4, List((1, 2, Some(22)))),
        (5, List((1, 2, None))),
        (6, Nil),
      ).transact

      Ns.n.>(3).a1.Rs1.*?(R1.n1.R2.n2.int2_?).query.get ==> List(
        (4, List((1, 2, Some(22)))),
        (5, List((1, 2, None))),
        (6, Nil)
      )
    }


    "Tacit attributes" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.n_(1).Rs1.*?(R1.n1.R2.n2_).query.get
      ).message ==>
        """Tacit attributes not allowed in optional nested data structure. Found:
          |AttrOneTacInt(R2,n2,V,List(),None,None,None)""".stripMargin
    }
  }
}
