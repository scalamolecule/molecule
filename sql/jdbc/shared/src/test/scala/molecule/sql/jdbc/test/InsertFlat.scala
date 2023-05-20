package molecule.sql.jdbc.test

import molecule.base.error.ModelError
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.sql.jdbc.setup.JdbcTestSuite
import molecule.sql.jdbc.sync._
import utest._
import scala.language.implicitConversions

object InsertFlat extends JdbcTestSuite {

  val i1 = 1
  val i2 = (1, 2)
  val i3 = (1, 2, 3)
  val i4 = (1, 2, 3, 4)
  val i5 = (1, 2, 3, 4, 5)

  override lazy val tests = Tests {

    "card one" - refs { implicit conn =>
      A.i.B.s.insert(
        (1, "a"),
        (2, "b"),
      ).transact

      A.i.B.s.query.get ==> List(
        (1, "a"),
        (2, "b"),
      )

      A.B.i.insert(i1).transact
      A.B.i.query.get ==> List(i1)

      A.i.B.i.insert(i2).transact
      A.i.B.i.query.get ==> List(i2)


      A.B.C.i.insert(i1).transact
      A.B.C.i.query.get ==> List(i1)

      A.B.i.C.i.insert(i2).transact
      A.B.i.C.i.query.get ==> List(i2)

      A.i.B.C.i.insert(i2).transact
      A.i.B.C.i.query.get ==> List(i2)

      A.i.B.i.C.i.insert(i3).transact
      A.i.B.i.C.i.query.get ==> List(i3)
    }


    "card many" - refs { implicit conn =>
      A.Bb.i.insert(i1).transact
      A.Bb.i.query.get ==> List(i1)

      A.i.Bb.i.insert(i2).transact
      A.i.Bb.i.query.get ==> List(i2)


      A.Bb.Cc.i.insert(i1).transact
      A.Bb.Cc.i.query.get ==> List(i1)

      A.Bb.i.Cc.i.insert(i2).transact
      A.Bb.i.Cc.i.query.get ==> List(i2)

      A.i.Bb.Cc.i.insert(i2).transact
      A.i.Bb.Cc.i.query.get ==> List(i2)

      A.i.Bb.i.Cc.i.insert(i3).transact
      A.i.Bb.i.Cc.i.query.get ==> List(i3)
    }


    "card one/many" - refs { implicit conn =>
      A.B.Cc.i.insert(i1).transact
      A.B.Cc.i.query.get ==> List(i1)

      A.i.B.Cc.i.insert(i2).transact
      A.i.B.Cc.i.query.get ==> List(i2)

      A.B.i.Cc.i.insert(i2).transact
      A.B.i.Cc.i.query.get ==> List(i2)

      A.i.B.i.Cc.i.insert(i3).transact
      A.i.B.i.Cc.i.query.get ==> List(i3)
    }


    "card many/one" - refs { implicit conn =>
      A.Bb.C.i.insert(i1).transact
      A.Bb.C.i.query.get ==> List(i1)

      A.Bb.i.C.i.insert(i2).transact
      A.Bb.i.C.i.query.get ==> List(i2)

      A.i.Bb.C.i.insert(i2).transact
      A.i.Bb.C.i.query.get ==> List(i2)

      A.i.Bb.i.C.i.insert(i3).transact
      A.i.Bb.i.C.i.query.get ==> List(i3)
    }


    "backref, card one" - refs { implicit conn =>
      // Can't go back from empty namespaces
      try {
        A.i.B._A.s.insert(1, "a").transact
      } catch {
        case ModelError(msg) =>
          msg ==> "Please add attributes to namespace B before going back to namespace A"
      }

      A.i.B.i._A.s.insert(1, 2, "a").transact
      A.i.B.i._A.s.query.get ==> List((1, 2, "a"))

      A.B.i._A.s.insert(2, "a").transact
      A.B.i._A.s.query.get ==> List((2, "a"))


      A.i.B.i._A.s.C.i.insert(1, 2, "a", 3).transact
      A.i.B.i._A.s.C.i.query.get ==> List((1, 2, "a", 3))

      A.i.B.i._A.C.i.insert(1, 2, 3).transact
      A.i.B.i._A.C.i.query.get ==> List((1, 2, 3))

      A.B.i._A.s.C.i.insert(2, "a", 3).transact
      A.B.i._A.s.C.i.query.get ==> List((2, "a", 3))

      A.B.i._A.C.i.insert(2, 3).transact
      A.B.i._A.C.i.query.get ==> List((2, 3))


      A.i.B.i.C.i._B.s.insert(1, 2, 3, "a").transact
      A.i.B.i.C.i._B.s.query.get ==> List((1, 2, 3, "a"))

      A.B.i.C.i._B.s.insert(2, 3, "a").transact
      A.B.i.C.i._B.s.query.get ==> List((2, 3, "a"))

      A.B.C.i._B.s.insert(3, "a").transact
      A.B.C.i._B.s.query.get ==> List((3, "a"))


      A.i.B.i.C.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
      A.i.B.i.C.i._B.s.D.i.query.get ==> List((1, 2, 3, "a", 4))

      A.i.B.i.C.i._B.D.i.insert(1, 2, 3, 4).transact
      A.i.B.i.C.i._B.D.i.query.get ==> List((1, 2, 3, 4))

      A.i.B.C.i._B.D.i.insert(1, 3, 4).transact
      A.i.B.C.i._B.D.i.query.get ==> List((1, 3, 4))

      A.B.C.i._B.D.i.insert(3, 4).transact
      A.B.C.i._B.D.i.query.get ==> List((3, 4))


      A.i.B.i.C.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
      A.i.B.i.C.i._B.s._A.s.query.get ==> List((1, 2, 3, "a", "b"))

      A.i.B.i.C.i._B._A.s.insert(1, 2, 3, "b").transact
      A.i.B.i.C.i._B._A.s.query.get ==> List((1, 2, 3, "b"))

      A.i.B.C.i._B._A.s.insert(1, 3, "b").transact
      A.i.B.C.i._B._A.s.query.get ==> List((1, 3, "b"))

      A.B.C.i._B._A.s.insert(3, "b").transact
      A.B.C.i._B._A.s.query.get ==> List((3, "b"))


      A.i.B.i.C.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
      A.i.B.i.C.i._B.s._A.s.D.i.query.get ==> List((1, 2, 3, "a", "b", 4))


      // Distinguish separate relationships to same namespace
      A.i.B.i._A.B1.i.insert(1, 2, 3).transact
      A.i.B.i._A.B1.i.query.get ==> List((1, 2, 3))

      A.i.B.i._A.B1.i._A.B2.i.insert(1, 2, 3, 4).transact
      A.i.B.i._A.B1.i._A.B2.i.query.get ==> List((1, 2, 3, 4))
    }


    "backref, card many" - refs { implicit conn =>
      A.i.Bb.i._A.s.insert(1, 2, "a").transact
      A.i.Bb.i._A.s.query.get ==> List((1, 2, "a"))

      A.B.i._A.s.insert(2, "a").transact
      A.B.i._A.s.query.get ==> List((2, "a"))

      A.i.Bb.i._A.s.insert(1, 2, "a").transact
      A.i.Bb.i._A.s.query.get ==> List((1, 2, "a"))

      A.Bb.i._A.s.insert(2, "a").transact
      A.Bb.i._A.s.query.get ==> List((2, "a"))


      A.i.Bb.i._A.s.C.i.insert(1, 2, "a", 3).transact
      A.i.Bb.i._A.s.C.i.query.get ==> List((1, 2, "a", 3))

      A.i.Bb.i._A.C.i.insert(1, 2, 3).transact
      A.i.Bb.i._A.C.i.query.get ==> List((1, 2, 3))

      A.Bb.i._A.s.C.i.insert(2, "a", 3).transact
      A.Bb.i._A.s.C.i.query.get ==> List((2, "a", 3))

      A.Bb.i._A.C.i.insert(2, 3).transact
      A.Bb.i._A.C.i.query.get ==> List((2, 3))


      A.i.Bb.i.Cc.i._B.s.insert(1, 2, 3, "a").transact
      A.i.Bb.i.Cc.i._B.s.query.get ==> List((1, 2, 3, "a"))

      A.Bb.i.Cc.i._B.s.insert(2, 3, "a").transact
      A.Bb.i.Cc.i._B.s.query.get ==> List((2, 3, "a"))

      A.Bb.Cc.i._B.s.insert(3, "a").transact
      A.Bb.Cc.i._B.s.query.get ==> List((3, "a"))


      A.i.Bb.i.Cc.i._B.s.D.i.insert(1, 2, 3, "a", 4).transact
      A.i.Bb.i.Cc.i._B.s.D.i.query.get ==> List((1, 2, 3, "a", 4))

      A.i.Bb.i.Cc.i._B.D.i.insert(1, 2, 3, 4).transact
      A.i.Bb.i.Cc.i._B.D.i.query.get ==> List((1, 2, 3, 4))

      A.i.Bb.Cc.i._B.D.i.insert(1, 3, 4).transact
      A.i.Bb.Cc.i._B.D.i.query.get ==> List((1, 3, 4))

      A.Bb.Cc.i._B.D.i.insert(3, 4).transact
      A.Bb.Cc.i._B.D.i.query.get ==> List((3, 4))


      A.i.Bb.i.Cc.i._B.s._A.s.insert(1, 2, 3, "a", "b").transact
      A.i.Bb.i.Cc.i._B.s._A.s.query.get ==> List((1, 2, 3, "a", "b"))

      A.i.Bb.i.Cc.i._B._A.s.insert(1, 2, 3, "b").transact
      A.i.Bb.i.Cc.i._B._A.s.query.get ==> List((1, 2, 3, "b"))

      A.i.Bb.Cc.i._B._A.s.insert(1, 3, "b").transact
      A.i.Bb.Cc.i._B._A.s.query.get ==> List((1, 3, "b"))

      A.Bb.Cc.i._B._A.s.insert(3, "b").transact
      A.Bb.Cc.i._B._A.s.query.get ==> List((3, "b"))


      A.i.Bb.i.Cc.i._B.s._A.s.D.i.insert(1, 2, 3, "a", "b", 4).transact
      A.i.Bb.i.Cc.i._B.s._A.s.D.i.query.get ==> List((1, 2, 3, "a", "b", 4))

      // Distinguish separate relationships to same namespace
      // card-many B and card-one B should be distinguished from each other
      A.i.Bb.i._A.s.B.s.insert(1, 2, "a", "b").transact
      A.i.Bb.i._A.s.B.s.query.get ==> List((1, 2, "a", "b"))

      A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.insert(
        (0, "a", 1, "b", 22, 2, "c", 11),
        (1, "a", 1, "b", 22, 2, "c", 11),
      ).transact

      A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.query.get ==> List(
        (0, "a", 1, "b", 22, 2, "c", 11),
        (1, "a", 1, "b", 22, 2, "c", 11),
      )
    }


    "self-join, one" - refs { implicit conn =>
      A.i.A.i.insert(i2).transact
      A.i.A.i.query.get ==> List(i2)

      A.i.B.i.B.i.insert(i3).transact
      A.i.B.i.B.i.query.get ==> List(i3)
    }


    "set self" - refs { implicit conn =>
      A.i.Aa.i.insert(i2).transact
      A.i.Aa.i.query.get ==> List(i2)

      A.i.Bb.i.B.i.insert(1, 2, 3).transact
      A.i.Bb.i.B.i.query.get ==> List((1, 2, 3))
    }
  }
}
