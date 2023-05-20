package molecule.datalog.datomic.test

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions
import molecule.datalog.datomic.sync._

object InsertFlat extends DatomicTestSuite {

  val i1 = 1
  val i2 = (1, 2)
  val i3 = (1, 2, 3)
  val i4 = (1, 2, 3, 4)
  val i5 = (1, 2, 3, 4, 5)

  override lazy val tests = Tests {

    "one" - refs { implicit conn =>
//      A.i.B.s.insert(
//        (1, "a"),
//        (2, "b"),
//      ).transact
//
//      A.i.B.s.query.get ==> List(
//        (1, "a"),
//        (2, "b"),
//      )


      A.B.C.D.i.insert(i1).transact
      A.B.C.D.i.query.get ==> List(i1)

      A.B.C.i.insert(i1).transact
      A.B.C.i.query.get ==> List(i1)
      A.B.C.i.D.i.insert(i2).transact
      A.B.C.i.D.i.query.get ==> List(i2)

      A.B.i.insert(i1).transact
      A.B.i.query.get ==> List(i1)
      A.B.i.C.i.insert(i2).transact
      A.B.i.C.i.query.get ==> List(i2)
      A.B.i.C.i.D.i.insert(i3).transact
      A.B.i.C.i.D.i.query.get ==> List(i3)

      A.i.B.i.insert(i2).transact
      A.i.B.i.query.get ==> List(i2)
      A.i.B.i.C.i.insert(i3).transact
      A.i.B.i.C.i.query.get ==> List(i3)
      A.i.B.i.C.i.D.i.insert(i4).transact
      A.i.B.i.C.i.D.i.query.get ==> List(i4)






    }


    "many" - refs { implicit conn =>

      A.Bb.i.insert(i1).inspect
      A.Bb.i.insert(i1).transact
      A.Bb.i.query.get ==> List(i1)

      A.i.Bb.i.insert(i2).transact
      A.i.Bb.i.query.get ==> List(i2)


//      A.i.Bb.i.insert(i2).transact
//      A.i.Bb.i.Cc.i.insert(i3).transact
//      A.Bb.i.insert(i1).transact
//      A.Bb.i.Cc.i.insert(i2).transact
//      A.Bb.Cc.i.insert(i1).transact
//
//      A.i.Bb.i.query.get ==> List(i2)
//      A.i.Bb.i.Cc.i.query.get ==> List(i3)
//      A.Bb.i.query.get ==> List(i1)
//      A.Bb.i.Cc.i.query.get ==> List(i2)
//      A.Bb.Cc.i.query.get ==> List(i1)
    }


    "one/many" - refs { implicit conn =>
      A.i.Bb.i.insert(i2).transact
      A.i.B.i.Cc.i.insert(i3).transact

      A.i.Bb.i.C.i.insert(i3).transact
      A.i.Bb.i.Cc.i.insert(i3).transact
      A.i.Bb.C.i.insert(i2).transact



      A.i.Bb.i.query.get ==> List(i2)
      A.i.Bb.i.C.i.query.get ==> List(i3)
      A.i.B.i.Cc.i.query.get ==> List(i3)
      A.i.Bb.i.Cc.i.query.get ==> List(i3)
      A.i.B.i.C.i.D.i.query.get ==> List(i4)
      A.i.Bb.C.i.query.get ==> List(i2)
    }


    "backref" - refs { implicit conn =>
      A.i.B.i._A.i.insert(i3).transact
      A.i.B.i._A.i.C.i.insert(i4).transact
      A.i.B.i._A.Aa.i.insert(i3).transact
      A.i.B.i._A.Bb.i.insert(i3).transact
      A.i.B.i._A.Cc.i.insert(i3).transact
      A.i.B.i.C.i._B.i.insert(i4).transact
      A.i.B.i.C.i._B.i._A.i.insert(i5).transact
      A.i.B.i.C.i._B.i._A.Bb.i.insert(i5).transact
      A.i.B.i.C.i._B.Cc.i.insert(i4).transact
      A.i.B.i.C.i._B._A.i.insert(i4).transact
      A.i.B.i.C.i._B._A.Bb.i.insert(i4).transact
      A.i.B.C.i._B.i.insert(i3).transact
      A.i.B.C.i._B.Cc.i.insert(i3).transact
      A.i.B.C.i._B._A.i.insert(i3).transact
      A.i.B.C.i._B._A.Bb.i.insert(i3).transact
      A.B.C.i._B._A.Bb.i.insert(i2).transact

      A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.insert(
        (0, "a", 1, "b", 22, 2, "c", 11),
        (1, "a", 1, "b", 22, 2, "c", 11),
      ).transact

      A.i.s.B.i.s.Cc.i._B.C.i.s._B._A.Bb.i.query.get ==> List(
        (0, "a", 1, "b", 22, 2, "c", 11),
        (1, "a", 1, "b", 22, 2, "c", 11),
      )
    }


    "one self" - refs { implicit conn =>
      A.i.A.i.insert(i2).transact
      A.i.B.i.B.i.insert(i3).transact

      A.i.A.i.query.get ==> List(i2)
    }

    "set self" - refs { implicit conn =>
      A.i.Aa.i.insert(i2).transact

      A.i.Aa.i.query.get ==> List(i2)
    }
  }
}
