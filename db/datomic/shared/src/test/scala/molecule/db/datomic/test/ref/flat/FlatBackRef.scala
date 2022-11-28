package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object FlatBackRef extends DatomicTestSuite {


  lazy val tests = Tests {

    "save" - refs { implicit conn =>
      Ns.i(0).R1.i(1)._Ns.Rs1.i(2).save.transact

      Ns.i.R1.i._Ns.Rs1.i.query.get ==> List((0, 1, 2))
    }


    "insert" - refs { implicit conn =>
      Ns.s.R1.s._Ns.Rs1.i.insert(List(
        ("a", "a1", 1),
        ("b", "b1", 2))).transact

      Ns.s.R1.s._Ns.Rs1.i.query.get ==> List(
        ("a", "a1", 1),
        ("b", "b1", 2)
      )

      Ns.s.Rs1.i._Ns.R1.s.query.get ==> List(
        ("a", 1, "a1"),
        ("b", 2, "b1")
      )
    }


    "complex" - refs { implicit conn =>
      Ns.i(0).s("a")
        .R1.i(1).s("b")
        .Rs2.i(22)._R1
        .R2.i(2).s("c")._R1._Ns
        .Rs1.i(11)
        .save.transact

      Ns.i.R1.i.query.get ==> List((0, 1))
      Ns.i.R1.i._Ns.s.query.get ==> List((0, 1, "a"))
      Ns.i.R1.i._Ns.Rs1.i.query.get ==> List((0, 1, 11))
      Ns.i.R1.i.R2.i._R1.s.query.get ==> List((0, 1, 2, "b"))
      Ns.i.R1.R2.i._R1.i.query.get ==> List((0, 2, 1))
      Ns.i.R1.i.R2.i._R1.Rs2.i.query.get ==> List((0, 1, 2, 22))
      Ns.i.R1.R2.i._R1.Rs2.i.query.get ==> List((0, 2, 22))
      Ns.i.R1.i.R2.i._R1.s._Ns.s.query.get ==> List((0, 1, 2, "b", "a"))
      Ns.i.R1.i.R2.i._R1._Ns.s.query.get ==> List((0, 1, 2, "a"))
      Ns.i.R1.R2.i._R1._Ns.s.query.get ==> List((0, 2, "a"))
      Ns.i.R1.i.R2.i._R1.s._Ns.Rs1.i.query.get ==> List((0, 1, 2, "b", 11))
      Ns.i.R1.i.R2.i._R1._Ns.Rs1.i.query.get ==> List((0, 1, 2, 11))
      Ns.i.R1.R2.i._R1._Ns.Rs1.i.query.get ==> List((0, 2, 11))
      Ns.R1.R2.s._R1._Ns.Rs1.i.query.get ==> List(("c", 11))
    }

    //    "Many attribute + ref" - refs { implicit conn =>
    //        m(Ns.s.Rs1.*(R1.i)) insert List(("a", List(1, 2)))
    //
    //        expectCompileError("m(Ns.s.refs1.Rs1.i)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Rs1.e ...`")
    //
    //        expectCompileError("m(Ns.refs1.s.Rs1.i)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Rs1.e ...`")
    //    }
  }
}
