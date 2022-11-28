package molecule.db.datomic.test.ref.nested

import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object NestedRef extends DatomicTestSuite {


  lazy val tests = Tests {

    "Ref" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.R2.i).insert(0, List((1, 2))).transact

      Ns.i.Rs1.*(R1.i.R2.i).query.get ==> List((0, List((1, 2))))
      Ns.i.Rs1.*?(R1.i.R2.i).query.get ==> List((0, List((1, 2))))

      Ns.i.Rs1.*(R1.R2.i).query.get ==> List((0, List(2)))
      Ns.i.Rs1.*?(R1.R2.i).query.get ==> List((0, List(2)))

      Ns.Rs1.*(R1.R2.i).query.get ==> List(List(2))
      Ns.Rs1.*?(R1.R2.i).query.get ==> List(List(2))


      Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i)).insert(1, List((1, 2, List((3, 4))))).transact
      Ns.i(1).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i)).query.get ==> List((1, List((1, 2, List((3, 4))))))
      Ns.i(1).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i)).query.get ==> List((1, List((1, 2, List((3, 4))))))

      // Ref before nested
      Ns.i.R1.i.Rs2.*(R2.i).insert(0, 1, List(2)).transact
      Ns.i.R1.i.Rs2.*(R2.i).query.get ==> List((0, 1, List(2)))
      Ns.i.R1.i.Rs2.*?(R2.i).query.get ==> List((0, 1, List(2)))
    }

    "Ref with card-set attr" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.R2.ii).insert(0, List((1, Set(2, 3)))).transact

      Ns.i.Rs1.*(R1.i.R2.ii).query.get ==> List((0, List((1, Set(2, 3)))))
      Ns.i.Rs1.*?(R1.i.R2.ii).query.get ==> List((0, List((1, Set(2, 3)))))

      Ns.i.Rs1.*(R1.R2.ii).query.get ==> List((0, List(Set(2, 3))))
      Ns.i.Rs1.*?(R1.R2.ii).query.get ==> List((0, List(Set(2, 3))))

      Ns.Rs1.*(R1.R2.ii).query.get ==> List(List(Set(2, 3)))
      Ns.Rs1.*?(R1.R2.ii).query.get ==> List(List(Set(2, 3)))


//      Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i)).insert(1, List((1, 2, List((3, 4))))).transact
//      Ns.i(1).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i)).query.get ==> List((1, List((1, 2, List((3, 4))))))
//      Ns.i(1).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i)).query.get ==> List((1, List((1, 2, List((3, 4))))))
//
//      // Ref before nested
//      Ns.i.R1.i.Rs2.*(R2.i).insert(0, 1, List(2)).transact
//      Ns.i.R1.i.Rs2.*(R2.i).query.get ==> List((0, 1, List(2)))
//      Ns.i.R1.i.Rs2.*?(R2.i).query.get ==> List((0, 1, List(2)))
    }


    "Backref, 1 step back" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i).insert(0, List((1, 2, "a", 3))).transact
      Ns.i_.Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i).query.get ==> List(List((1, 2, "a", 3)))
      Ns.i_.Rs1.*?(R1.i.R2.i._R1.R2a.s.R3.i).query.get ==> List(List((1, 2, "a", 3)))

      Ns.i_.Rs1.*(R1.i.R2.i._R1.R2a.R3.i).query.get ==> List(List((1, 2, 3)))
      Ns.i_.Rs1.*?(R1.i.R2.i._R1.R2a.R3.i).query.get ==> List(List((1, 2, 3)))

      Ns.i.Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).insert(1, List((1, 2, "a", 3, List((4, 5))))).transact
      Ns.i_(1).Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).query.get ==> List(List((1, 2, "a", 3, List((4, 5)))))
      Ns.i_(1).Rs1.*?(R1.i.R2.i._R1.R2a.s.R3.i.Rs4.*?(R4.i.R5.i)).query.get ==> List(List((1, 2, "a", 3, List((4, 5)))))

      Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i._R3.R4a.s.R5.i)).insert(2, List((1, 2, List((3, 4, "a", 5))))).transact
      Ns.i_(2).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i._R3.R4a.s.R5.i)).query.get ==> List(List((1, 2, List((3, 4, "a", 5)))))
      Ns.i_(2).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i._R3.R4a.s.R5.i)).query.get ==> List(List((1, 2, List((3, 4, "a", 5)))))
    }

    "Backref, 2 steps back" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i).insert(0, List((1, 2, 3, "a", 33))).transact
      Ns.i_.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i).query.get ==> List(List((1, 2, 3, "a", 33)))
      Ns.i_.Rs1.*?(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i).query.get ==> List(List((1, 2, 3, "a", 33)))

      Ns.i.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).insert(1, List((1, 2, 3, "a", 3, List((4, 5))))).transact
      Ns.i_(1).Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).query.get ==> List(List((1, 2, 3, "a", 3, List((4, 5)))))
      Ns.i_(1).Rs1.*?(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i.Rs4.*?(R4.i.R5.i)).query.get ==> List(List((1, 2, 3, "a", 3, List((4, 5)))))

      Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i.R5.i._R4._R3.R4a.s.R5.i)).insert(2, List((1, 2, List((3, 4, 5, "a", 55))))).transact
      Ns.i_(2).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i.R5.i._R4._R3.R4a.s.R5.i)).query.get ==> List(List((1, 2, List((3, 4, 5, "a", 55)))))
      Ns.i_(2).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i.R5.i._R4._R3.R4a.s.R5.i)).query.get ==> List(List((1, 2, List((3, 4, 5, "a", 55)))))
    }

    "Backref before nested, 1 step back" - refs { implicit conn =>
      Ns.i.R1.i._Ns.Rs1.*(R1.i).insert(0, 1, List(2)).transact
      Ns.i.R1.i._Ns.Rs1.*(R1.i).query.get ==> List((0, 1, List(2)))
      Ns.i.R1.i._Ns.Rs1.*?(R1.i).query.get ==> List((0, 1, List(2)))
    }

    "Backref insert: no ref re-use after" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i.Rs1.*(R1.i.R2.i._R1.R2.s).insert(0, List((1, 2, "a"))).transact
      ).message ==> "Can't re-use previous namespace R2 after backref _R1."

      intercept[MoleculeException](
        Ns.i.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2.s).insert(0, List((1, 2, 3, "a"))).transact
      ).message ==> "Can't re-use previous namespace R2 after backref _R1."
    }


    "Optional attributes in nested" - refs { implicit conn =>
      Ns.i.Rs1.*(R1.i.s_?.R2.i).insert(
        (1, List((1, Some("a"), 2))),
        (2, List((1, None, 2))),
        (3, Nil),
      ).transact

      Ns.i.Rs1.*(R1.i.s_?.R2.i).query.get ==> List(
        (1, List((1, Some("a"), 2))),
        (2, List((1, None, 2))),
      )
      Ns.i.Rs1.*?(R1.i.s_?.R2.i).query.get ==> List(
        (1, List((1, Some("a"), 2))),
        (2, List((1, None, 2))),
        (3, Nil),
      )

      Ns.i.Rs1.*(R1.i.R2.i.s_?).insert(
        (4, List((1, 2, Some("a")))),
        (5, List((1, 2, None))),
        (6, Nil),
      ).transact

      Ns.i.>(3).a1.Rs1.*(R1.i.R2.i.s_?).query.get ==> List(
        (4, List((1, 2, Some("a")))),
        (5, List((1, 2, None))),
      )
      Ns.i.>(3).a1.Rs1.*?(R1.i.R2.i.s_?).query.get ==> List(
        (4, List((1, 2, Some("a")))),
        (5, List((1, 2, None))),
        (6, Nil)
      )
    }

    "Optional attributes before nested" - refs { implicit conn =>
      Ns.s_?.Rs1.i.Rs2.*(R2.i).insert(List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List()),
      )).transact

      Ns.s_?.Rs1.i.a1.Rs2.*?(R2.i).query.get ==> List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List())
      )
    }


    "Optional Nested semantics" - refs { implicit conn =>
      intercept[MoleculeException](
        Ns.i.Rs1.*?(R1.s.i_).query.get
      ).message ==>
        """Tacit attributes not allowed in optional nested data structure. Found:
          |AttrOneTacInt(R1,i,V,List(),None,None,None)""".stripMargin

      intercept[MoleculeException](
        Ns.i.Rs1.*?(R1.i.R2.i_).query.get
      ).message ==>
        """Tacit attributes not allowed in optional nested data structure. Found:
          |AttrOneTacInt(R2,i,V,List(),None,None,None)""".stripMargin

      // Ok:
      Ns.i.Rs1.*?(R1.s.i).query.get
      Ns.i.Rs1.*?(R1.i.R2.i).query.get


      intercept[MoleculeException](
        Ns.i.Rs1.*?(R1.i.Rs2.i).query.get
      ).message ==> "Only cardinality-one refs allowed in optional nested data structures. Found: " +
        "Ref(R1,rs2,R2,CardSet)"
      // Ok:
      Ns.i.Rs1.*?(R1.i.R2.i).query.get


      intercept[MoleculeException](
        Ns.i.Rs1.*?(R1.i.R2.i._R1.s.R2a.i).query.get
      ).message ==> "Expected ref after backref _R1. " +
        "Please add attribute :R1/s to initial namespace R1 instead of after backref _R1."
      // Ok:
      Ns.i.Rs1.*?(R1.i.s.R2.i._R1.R2a.i).query.get


      intercept[MoleculeException](
        Ns.s_?.Rs1.*?(R1.i.R2.i.s_?).query.get
      ).message ==> "Single optional attribute before optional nested data structure is not allowed."
      // Ok:
      Ns.s.Rs1.*?(R1.i.R2.i.s_?).query.get
      Ns.s_.Rs1.*?(R1.i.R2.i.s_?).query.get
    }
  }
}
