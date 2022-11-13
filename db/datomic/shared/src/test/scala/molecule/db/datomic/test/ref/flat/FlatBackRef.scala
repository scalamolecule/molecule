package molecule.db.datomic.test.ref.flat

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object FlatBackRef extends DatomicTestSuite {


  lazy val tests = Tests {

    "save" - refs { implicit conn =>
      Ns.n(0).Ref1.n1(1)._Ns.Refs1.n1(2).save.transact

      Ns.n.Ref1.n1._Ns.Refs1.n1.query.get ==> List((0, 1, 2))
    }


    "insert" - refs { implicit conn =>
      Ns.str.Ref1.str1._Ns.Refs1.int1.insert(List(
        ("a", "a1", 1),
        ("b", "b1", 2))).transact

      Ns.str.Ref1.str1._Ns.Refs1.int1.query.get ==> List(
        ("a", "a1", 1),
        ("b", "b1", 2)
      )

      Ns.str.Refs1.int1._Ns.Ref1.str1.query.get ==> List(
        ("a", 1, "a1"),
        ("b", 2, "b1")
      )
    }


    "complex" - refs { implicit conn =>
      Ns.int(0).str("a")
        .Ref1.int1(1).str1("b")
        .Refs2.int2(22)._Ref1
        .Ref2.int2(2).str2("c")._Ref1._Ns
        .Refs1.int1(11)
        .save.transact

      Ns.int.Ref1.int1.query.get ==> List((0, 1))
      Ns.int.Ref1.int1._Ns.str.query.get ==> List((0, 1, "a"))
      Ns.int.Ref1.int1._Ns.Refs1.int1.query.get ==> List((0, 1, 11))
      Ns.int.Ref1.int1.Ref2.int2._Ref1.str1.query.get ==> List((0, 1, 2, "b"))
      Ns.int.Ref1.Ref2.int2._Ref1.int1.query.get ==> List((0, 2, 1))
      Ns.int.Ref1.int1.Ref2.int2._Ref1.Refs2.int2.query.get ==> List((0, 1, 2, 22))
      Ns.int.Ref1.Ref2.int2._Ref1.Refs2.int2.query.get ==> List((0, 2, 22))
      Ns.int.Ref1.int1.Ref2.int2._Ref1.str1._Ns.str.query.get ==> List((0, 1, 2, "b", "a"))
      Ns.int.Ref1.int1.Ref2.int2._Ref1._Ns.str.query.get ==> List((0, 1, 2, "a"))
      Ns.int.Ref1.Ref2.int2._Ref1._Ns.str.query.get ==> List((0, 2, "a"))
      Ns.int.Ref1.int1.Ref2.int2._Ref1.str1._Ns.Refs1.int1.query.get ==> List((0, 1, 2, "b", 11))
      Ns.int.Ref1.int1.Ref2.int2._Ref1._Ns.Refs1.int1.query.get ==> List((0, 1, 2, 11))
      Ns.int.Ref1.Ref2.int2._Ref1._Ns.Refs1.int1.query.get ==> List((0, 2, 11))
      Ns.Ref1.Ref2.str2._Ref1._Ns.Refs1.int1.query.get ==> List(("c", 11))
    }

    //    "Many attribute + ref" - refs { implicit conn =>
    //        m(Ns.str.Refs1.*(Ref1.int1)) insert List(("a", List(1, 2)))
    //
    //        expectCompileError("m(Ns.str.refs1.Refs1.int1)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Refs1.e ...`")
    //
    //        expectCompileError("m(Ns.refs1.str.Refs1.int1)",
    //          "molecule.refs.ops.exception.VerifyRawModelException: " +
    //            "Instead of getting the ref id with `refs1` please get it via the referenced namespace: `Refs1.e ...`")
    //    }
  }
}
