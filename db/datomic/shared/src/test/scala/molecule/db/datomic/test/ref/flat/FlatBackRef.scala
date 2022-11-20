package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object FlatBackRef extends DatomicTestSuite {


  lazy val tests = Tests {

    "save" - refs { implicit conn =>
      Ns.n(0).R1.n1(1)._Ns.Rs1.n1(2).save.transact

      Ns.n.R1.n1._Ns.Rs1.n1.query.get ==> List((0, 1, 2))
    }


    "insert" - refs { implicit conn =>
      Ns.str.R1.str1._Ns.Rs1.int1.insert(List(
        ("a", "a1", 1),
        ("b", "b1", 2))).transact

      Ns.str.R1.str1._Ns.Rs1.int1.query.get ==> List(
        ("a", "a1", 1),
        ("b", "b1", 2)
      )

      Ns.str.Rs1.int1._Ns.R1.str1.query.get ==> List(
        ("a", 1, "a1"),
        ("b", 2, "b1")
      )
    }


    "complex" - refs { implicit conn =>
      Ns.int(0).str("a")
        .R1.int1(1).str1("b")
        .Rs2.int2(22)._R1
        .R2.int2(2).str2("c")._R1._Ns
        .Rs1.int1(11)
        .save.transact

      Ns.int.R1.int1.query.get ==> List((0, 1))
      Ns.int.R1.int1._Ns.str.query.get ==> List((0, 1, "a"))
      Ns.int.R1.int1._Ns.Rs1.int1.query.get ==> List((0, 1, 11))
      Ns.int.R1.int1.R2.int2._R1.str1.query.get ==> List((0, 1, 2, "b"))
      Ns.int.R1.R2.int2._R1.int1.query.get ==> List((0, 2, 1))
      Ns.int.R1.int1.R2.int2._R1.Rs2.int2.query.get ==> List((0, 1, 2, 22))
      Ns.int.R1.R2.int2._R1.Rs2.int2.query.get ==> List((0, 2, 22))
      Ns.int.R1.int1.R2.int2._R1.str1._Ns.str.query.get ==> List((0, 1, 2, "b", "a"))
      Ns.int.R1.int1.R2.int2._R1._Ns.str.query.get ==> List((0, 1, 2, "a"))
      Ns.int.R1.R2.int2._R1._Ns.str.query.get ==> List((0, 2, "a"))
      Ns.int.R1.int1.R2.int2._R1.str1._Ns.Rs1.int1.query.get ==> List((0, 1, 2, "b", 11))
      Ns.int.R1.int1.R2.int2._R1._Ns.Rs1.int1.query.get ==> List((0, 1, 2, 11))
      Ns.int.R1.R2.int2._R1._Ns.Rs1.int1.query.get ==> List((0, 2, 11))
      Ns.R1.R2.str2._R1._Ns.Rs1.int1.query.get ==> List(("c", 11))
    }

    //    "Many attribute + ref" - refs { implicit conn =>
    //        m(Ns.str.Rs1.*(R1.int1)) insert List(("a", List(1, 2)))
    //
    //        expectCompileError("m(Ns.str.refs1.Rs1.int1)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Rs1.e ...`")
    //
    //        expectCompileError("m(Ns.refs1.str.Rs1.int1)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Rs1.e ...`")
    //    }
  }
}
