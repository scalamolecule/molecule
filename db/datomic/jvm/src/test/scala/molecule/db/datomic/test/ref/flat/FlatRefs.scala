package molecule.db.datomic.test.ref.flat

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object FlatRefs extends DatomicTestSuite {


  lazy val tests = Tests {

    "ref" - refs { implicit conn =>
      Ns.i.R1.i.insert(1, 2).transact
      Ns.i.R1.i.query.get ==> List((1, 2))
    }


    "backref" - refs { implicit conn =>
      Ns.i.R1.i._Ns.Rs1.i.insert(1, 2, 3).transact
      Ns.i.R1.i._Ns.Rs1.i.query.get ==> List((1, 2, 3))
    }


    "Adjacent ref without attribute" - refs { implicit conn =>
      Ns.i.R1.i.R2.i.insert(1, 2, 3).transact
      Ns.i.R1.R2.i.query.get ==> List((1, 3))
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


    "ref attributes" - refs { implicit conn =>
      // Card one ref attr
      val List(_, e1) = Ns.i.R1.i.insert(1, 2).transact.eids
      Ns.i.r1.query.get ==> List((1, e1))

      // Card many ref attr (returned as Set)
      val List(_, e2) = Ns.i.Rs1.i.insert(1, 2).transact.eids
      Ns.i.rs1.query.get ==> List((1, Set(e2)))
    }


    "multiple card-many refs" - refs { implicit conn =>
      // Two entities to be referenced
      val List(b1, b2) = R1.i.insert(1, 2).transact.eids

      // Reference Set of entities
      Ns.i(0).rs1(Set(b1, b2)).save.transact

      // Saving individual ref ids (not in a Set) is not allowed
      intercept[MoleculeException](
        Ns.i(0).rs1(b1, b2).save.transact
      ).message ==> "Can only save one Set of values for Set attribute `Ns.rs1`. " +
        s"Found: ArraySeq(Set($b1), Set($b2))"

      // Referencing namespace attributes repeat for each referenced entity
      Ns.i.Rs1.i.query.get ==> List(
        (0, 1),
        (0, 2), // 0 is repeated
      )

      // Card many ref attributes return Set of ref ids
      Ns.i.rs1.query.get ==> List((0, Set(b1, b2)))
    }
  }
}
