package molecule.sql.jdbc.test

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.sql.jdbc.setup.JdbcTestSuite
import molecule.sql.jdbc.sync._
import utest._
import scala.language.implicitConversions

object Inserts extends JdbcTestSuite {

  override lazy val tests = Tests {

    "one" - refs { implicit conn =>
      Ns.i.R1.s.insert(
        (1, "a"),
        (2, "b"),
      ).transact

      Ns.i.R1.s.query.get ==> List(
        (1, "a"),
        (2, "b"),
      )

      Ns.i.R1.s.R2.i.insert(0, "a", 2).transact
      Ns.i.R1.s.R2.i.query.get ==> List((0, "a", 2))

      Ns.i.R1.s.R2.i.R3.s.insert(0, "a", 2, "b").transact
      Ns.i.R1.s.R2.i.R3.s.query.get ==> List((0, "a", 2, "b"))
    }


    "one self" - refs { implicit conn =>
      Ns.i.Spouse.s.insert(
        (3, "a"),
        (4, "b"),
      ).transact

      Ns.i.Spouse.s.query.get ==> List(
        (3, "a"),
        (4, "b"),
      )


      Ns.i.Spouse.i.insert(
        (1, 2),
        (3, 4),
      ).transact

      Ns.i.Spouse.i.query.get ==> List(
        (1, 2),
        (3, 4),
      )
    }


    "set" - refs { implicit conn =>
      Ns.i.Rs1.s.insert(
        (3, "a"),
        (4, "b"),
      ).transact

      Ns.i.Rs1.s.query.get ==> List(
        (3, "a"),
        (4, "b"),
      )

      Ns.i.Rs1.i.insert(
        (1, 2),
        (3, 4),
      ).transact

      Ns.i.Rs1.i.query.get ==> List(
        (1, 2),
        (3, 4),
      )
    }


    "set self" - refs { implicit conn =>

      Ns.i.Friends.i.insert(
        (1, 2),
        (3, 4),
      ).transact

      Ns.i.Friends.i.query.get ==> List(
        (1, 2),
        (3, 4),
      )


      Ns.i.Friends.s.insert(
        (1, "a"),
        (2, "b"),
      ).transact

      Ns.i.Friends.s.query.get ==> List(
        (1, "a"),
        (2, "b"),
      )
    }


    "one/set" - refs { implicit conn =>

      Ns.i.Rs1.s.R2.i.insert(0, "a", 2).transact
      Ns.i.Rs1.s.R2.i.query.get ==> List((0, "a", 2))

      Ns.i.R1.s.Rs2.i.insert(0, "a", 2).transact
      Ns.i.R1.s.Rs2.i.query.get ==> List((0, "a", 2))

      Ns.i.Rs1.s.Rs2.i.insert(0, "a", 2).transact
      Ns.i.Rs1.s.Rs2.i.query.get ==> List((0, "a", 2))


      Ns.i.R1.s.R2.i.R3.s.insert(0, "a", 2, "b").transact
      Ns.i.R1.s.R2.i.R3.s.query.get ==> List((0, "a", 2, "b"))


      Ns.i.Rs1.R2.s.insert(0, "a").transact
      Ns.i.Rs1.R2.s.query.get ==> List((0, "a"))

      Ns.i.R1.i._Ns.s.insert(0, 1, "a").transact
      Ns.i.R1.i._Ns.s.query.get ==> List((0, 1, "a"))


      Ns.i
        .R1.s._Ns
        .Friends.i.insert(0, "a", 1).transact

      Ns.i
        .R1.s._Ns
        .Friends.i.query.get ==> List((0, "a", 1))

      Ns.i.R1.i._Ns.s.insert(0, 1, "a").transact
      Ns.i.R1.i._Ns.s.query.get ==> List((0, 1, "a"))

      Ns.i.R1.i._Ns.s.Owned1.s.insert(0, 1, "a", "x").transact
      Ns.i.R1.i._Ns.s.Owned1.s.query.get ==> List((0, 1, "a", "x"))

      Ns.i.R1.i._Ns.OwnedMany1.s.insert(0, 1, "x").transact
      Ns.i.R1.i._Ns.OwnedMany1.s.query.get ==> List((0, 1, "x"))

      Ns.i.R1.i._Ns.Rs1.s.insert(0, 1, "x").transact
      Ns.i.R1.i._Ns.Rs1.s.query.get ==> List((0, 1, "x"))

      //      Ns.i.R1.i._Ns.Rs1.i.insert(0, 1, 11).transact
      //      Ns.i.R1.i._Ns.Rs1.i.query.get ==> List((0, 1, 11))


      //      Ns.i.R1.i.R2.i._R1.s.insert(0, 1, 2, "b").transact
      //      Ns.i.R1.i.R2.i._R1.s.query.get ==> List((0, 1, 2, "b"))
      //
      //      Ns.i.R1.R2.i._R1.i.insert(0, 2, 1).transact
      //      Ns.i.R1.R2.i._R1.i.query.get ==> List((0, 2, 1))
      //
      //      Ns.i.R1.i.R2.i._R1.Rs2.i.insert(0, 1, 2, 22).transact
      //      Ns.i.R1.i.R2.i._R1.Rs2.i.query.get ==> List((0, 1, 2, 22))
      //
      //      Ns.i.R1.R2.i._R1.Rs2.i.insert(0, 2, 22).transact
      //      Ns.i.R1.R2.i._R1.Rs2.i.query.get ==> List((0, 2, 22))
      //
      //      Ns.i.R1.i.R2.i._R1.s._Ns.s.insert(0, 1, 2, "b", "a").transact
      //      Ns.i.R1.i.R2.i._R1.s._Ns.s.query.get ==> List((0, 1, 2, "b", "a"))
      //
      //      Ns.i.R1.i.R2.i._R1._Ns.s.insert(0, 1, 2, "a").transact
      //      Ns.i.R1.i.R2.i._R1._Ns.s.query.get ==> List((0, 1, 2, "a"))
      //
      //      Ns.i.R1.R2.i._R1._Ns.s.insert(0, 2, "a").transact
      //      Ns.i.R1.R2.i._R1._Ns.s.query.get ==> List((0, 2, "a"))
      //
      //      Ns.i.R1.i.R2.i._R1.s._Ns.Rs1.i.insert(0, 1, 2, "b", 11).transact
      //      Ns.i.R1.i.R2.i._R1.s._Ns.Rs1.i.query.get ==> List((0, 1, 2, "b", 11))
      //
      //      Ns.i.R1.i.R2.i._R1._Ns.Rs1.i.insert(0, 1, 2, 11).transact
      //      Ns.i.R1.i.R2.i._R1._Ns.Rs1.i.query.get ==> List((0, 1, 2, 11))
      //
      //      Ns.i.R1.R2.i._R1._Ns.Rs1.i.insert(0, 2, 11).transact
      //      Ns.i.R1.R2.i._R1._Ns.Rs1.i.query.get ==> List((0, 2, 11))
      //
      //      Ns.R1.R2.s._R1._Ns.Rs1.i.insert("c", 11).transact
      //      Ns.R1.R2.s._R1._Ns.Rs1.i.query.get ==> List(("c", 11))


      //      Ns.i.Rs1.i.query.get ==> List((0, 1))
      //
      //      Ns.i.query.get ==> List((0, 1, "a"))
      //      R1.i.query.get ==> List((0, 1, "a"))
      //      Ns.i.R1.i.query.get ==> List((0, 1))
      //      Ns.i.R1.i._Ns.Rs1.s.query.get ==> List((0, 1, "a"))
      //      Ns.i.R1.i._Ns.Rs1.i.query.get ==> List((0, 1, 11))


      //      Ns.i.R1.i.query.get ==> List((0, 1))
      //      Ns.i.R1.i._Ns.s.query.get ==> List((0, 1, "a"))
      //      Ns.i.R1.i._Ns.Rs1.i.query.get ==> List((0, 1, 11))
      //      Ns.i.R1.i.R2.i._R1.s.query.get ==> List((0, 1, 2, "b"))
      //      Ns.i.R1.R2.i._R1.i.query.get ==> List((0, 2, 1))
      //      Ns.i.R1.i.R2.i._R1.Rs2.i.query.get ==> List((0, 1, 2, 22))
      //      Ns.i.R1.R2.i._R1.Rs2.i.query.get ==> List((0, 2, 22))
      //      Ns.i.R1.i.R2.i._R1.s._Ns.s.query.get ==> List((0, 1, 2, "b", "a"))
      //      Ns.i.R1.i.R2.i._R1._Ns.s.query.get ==> List((0, 1, 2, "a"))
      //      Ns.i.R1.R2.i._R1._Ns.s.query.get ==> List((0, 2, "a"))
      //      Ns.i.R1.i.R2.i._R1.s._Ns.Rs1.i.query.get ==> List((0, 1, 2, "b", 11))
      //      Ns.i.R1.i.R2.i._R1._Ns.Rs1.i.query.get ==> List((0, 1, 2, 11))
      //      Ns.i.R1.R2.i._R1._Ns.Rs1.i.query.get ==> List((0, 2, 11))
      //      Ns.R1.R2.s._R1._Ns.Rs1.i.query.get ==> List(("c", 11))
      //
      //
      //      Ns.i(0).s("a").R1.i(1).s("b").Rs2.i(22)._R1.R2.i(2).s("c")._R1._Ns.Rs1.i(11)
      //        .save.transact
      //
      //      Ns.i.s.R1.i.s.Rs2.i._R1.R2.i.s._R1._Ns.Rs1.i.query.get ==> List(
      //        (0, "a", 1, "b", 22, 2, "c", 11)
      //      )
      //
      //      Ns.i.s.R1.i.s.Rs2.i._R1.R2.i.s._R1._Ns.Rs1.i
      //        .insert(0, "a", 1, "b", 22, 2, "c", 11).transact
      //
      //      Ns.i.s.R1.i.s.Rs2.i._R1.R2.i.s._R1._Ns.Rs1.i.insert(
      //        (0, "a", 1, "b", 22, 2, "c", 11),
      //        (1, "a", 1, "b", 22, 2, "c", 11),
      //      ).transact
      //
      //      Ns.i.s.R1.i.s.Rs2.i._R1.R2.i.s._R1._Ns.Rs1.i.query.get ==> List(
      //        (0, "a", 1, "b", 22, 2, "c", 11),
      //        (1, "a", 1, "b", 22, 2, "c", 11),
      //      )


      //      Ns.s.Rs1.*(R1.i).insert(
      //        ("a", List(1, 2)),
      //        ("b", List(3, 4)),
      //      )
    }
  }
}
