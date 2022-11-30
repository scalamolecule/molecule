package molecule.db.datomic.test.composite

import molecule.db.datomic.setup.DatomicTestSuite
import utest._

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._

object CompositeBasic extends DatomicTestSuite {


  lazy val tests = Tests {

    "basic" - refs { implicit conn =>
      (Ns.i.s + R2.s.i).insert((1, "a"), ("b", 2)).transact

      (Ns.i.s + R2.s.i).query.get ==> List(((1, "a"), ("b", 2)))
      (Ns.i.s + R2.s).query.get ==> List(((1, "a"), "b"))
      (Ns.i + R2.s.i).query.get ==> List((1, ("b", 2)))
      (Ns.i + R2.s).query.get ==> List((1, "b"))
    }


    "tacit" - refs { implicit conn =>
      // Ns.i with and without associated data Rs.s
      (Ns.i + R2.s).insert(1, "x").transact
      Ns.i.insert(2).transact

      // Ns.i asserted twice
      Ns.i.query.get ==> List(1, 2)

      // Ns.i only asserted once with associated R2.s
      (Ns.i + R2.s_).query.get ==> List(1)
    }


    "Ref" - refs { implicit conn =>
      (R3.i + Ns.i.R1.i).insert(0, (1, 2)).transact

      (R3.i + Ns.i.R1.i).query.get ==> List((0, (1, 2)))
      (R3.i + Ns.i.R1.i_).query.get ==> List((0, 1))
      (R3.i + Ns.i_.R1.i).query.get ==> List((0, 2))
      (R3.i + Ns.R1.i).query.get ==> List((0, 2))
      (R3.i + Ns.R1.i_).query.get ==> List(0)

      (R3.i_ + Ns.i.R1.i).query.get ==> List((1, 2))
      (R3.i_ + Ns.i.R1.i_).query.get ==> List(1)
      (R3.i_ + Ns.i_.R1.i).query.get ==> List(2)
      (R3.i_ + Ns.R1.i).query.get ==> List(2)


      (Ns.i.R1.i + R2.i).insert((1, 2), 3).transact

      (Ns.i.R1.i + R2.i).query.get ==> List(((1, 2), 3))
      (Ns.i.R1.i_ + R2.i).query.get ==> List((1, 3))
      (Ns.i_.R1.i + R2.i).query.get ==> List((2, 3))
      (Ns.R1.i + R2.i).query.get ==> List((2, 3))
      (Ns.R1.i_ + R2.i).query.get ==> List(3)

      (Ns.i.R1.i + R2.i_).query.get ==> List((1, 2))
      (Ns.i.R1.i_ + R2.i_).query.get ==> List(1)
      (Ns.i_.R1.i + R2.i_).query.get ==> List(2)
      (Ns.R1.i + R2.i_).query.get ==> List(2)
    }


    "Expr" - refs { implicit conn =>
      (R3.i.s + Ns.i.s).insert(
        ((1, "a"), (4, "x")),
        ((2, "b"), (5, "y")),
        ((3, "c"), (6, "z")),
      ).transact

      (R3.i.a1.s + Ns.i.s).query.get ==> List(
        ((1, "a"), (4, "x")),
        ((2, "b"), (5, "y")),
        ((3, "c"), (6, "z")),
      )
      (R3.i.<(3).a1.s + Ns.i.s).query.get ==> List(
        ((1, "a"), (4, "x")),
        ((2, "b"), (5, "y")),
      )
      (R3.i.<(3).a1.s + Ns.i.>(4).s).query.get ==> List(
        ((2, "b"), (5, "y")),
      )
    }
  }
}
