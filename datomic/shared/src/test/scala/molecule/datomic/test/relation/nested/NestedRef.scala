package molecule.datomic.test.relation.nested

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._
import scala.concurrent.Future


object NestedRef extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Ref" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.i).insert(0, List((1, 2))).transact

        _ <- Ns.i.Rs1.*(R1.i.R2.i).query.get.map(_ ==> List((0, List((1, 2)))))
        _ <- Ns.i.Rs1.*?(R1.i.R2.i).query.get.map(_ ==> List((0, List((1, 2)))))

        _ <- Ns.i.Rs1.*(R1.R2.i).query.get.map(_ ==> List((0, List(2))))
        _ <- Ns.i.Rs1.*?(R1.R2.i).query.get.map(_ ==> List((0, List(2))))

        _ <- Ns.Rs1.*(R1.R2.i).query.get.map(_ ==> List(List(2)))
        _ <- Ns.Rs1.*?(R1.R2.i).query.get.map(_ ==> List(List(2)))


        _ <- Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i)).insert(1, List((1, 2, List((3, 4))))).transact
        _ <- Ns.i(1).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i)).query.get.map(_ ==> List((1, List((1, 2, List((3, 4)))))))
        _ <- Ns.i(1).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i)).query.get.map(_ ==> List((1, List((1, 2, List((3, 4)))))))

        // Ref before nested
        _ <- Ns.i.R1.i.Rs2.*(R2.i).insert(0, 1, List(2)).transact
        _ <- Ns.i.R1.i.Rs2.*(R2.i).query.get.map(_ ==> List((0, 1, List(2))))
        _ <- Ns.i.R1.i.Rs2.*?(R2.i).query.get.map(_ ==> List((0, 1, List(2))))
      } yield ()
    }

    "Ref with card-set attr" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.ii).insert(0, List((1, Set(2, 3)))).transact

        _ <- Ns.i.Rs1.*(R1.i.R2.ii).query.get.map(_ ==> List((0, List((1, Set(2, 3))))))
        _ <- Ns.i.Rs1.*?(R1.i.R2.ii).query.get.map(_ ==> List((0, List((1, Set(2, 3))))))

        _ <- Ns.i.Rs1.*(R1.R2.ii).query.get.map(_ ==> List((0, List(Set(2, 3)))))
        _ <- Ns.i.Rs1.*?(R1.R2.ii).query.get.map(_ ==> List((0, List(Set(2, 3)))))

        _ <- Ns.Rs1.*(R1.R2.ii).query.get.map(_ ==> List(List(Set(2, 3))))
        _ <- Ns.Rs1.*?(R1.R2.ii).query.get.map(_ ==> List(List(Set(2, 3))))
      } yield ()
    }


    "Backref, 1 step back" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i).insert(0, List((1, 2, "a", 3))).transact
        _ <- Ns.i_.Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
        _ <- Ns.i_.Rs1.*?(R1.i.R2.i._R1.R2a.s.R3.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))

        _ <- Ns.i_.Rs1.*(R1.i.R2.i._R1.R2a.R3.i).query.get.map(_ ==> List(List((1, 2, 3))))
        _ <- Ns.i_.Rs1.*?(R1.i.R2.i._R1.R2a.R3.i).query.get.map(_ ==> List(List((1, 2, 3))))

        _ <- Ns.i.Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).insert(1, List((1, 2, "a", 3, List((4, 5))))).transact
        _ <- Ns.i_(1).Rs1.*(R1.i.R2.i._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
        _ <- Ns.i_(1).Rs1.*?(R1.i.R2.i._R1.R2a.s.R3.i.Rs4.*?(R4.i.R5.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))

        _ <- Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i._R3.R4a.s.R5.i)).insert(2, List((1, 2, List((3, 4, "a", 5))))).transact
        _ <- Ns.i_(2).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i._R3.R4a.s.R5.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
        _ <- Ns.i_(2).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i._R3.R4a.s.R5.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
      } yield ()
    }

    "Backref, 2 steps back" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i).insert(0, List((1, 2, 3, "a", 33))).transact
        _ <- Ns.i_.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i).query.get.map(_ ==> List(List((1, 2, 3, "a", 33))))
        _ <- Ns.i_.Rs1.*?(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i).query.get.map(_ ==> List(List((1, 2, 3, "a", 33))))

        _ <- Ns.i.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).insert(1, List((1, 2, 3, "a", 3, List((4, 5))))).transact
        _ <- Ns.i_(1).Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i.Rs4.*(R4.i.R5.i)).query.get.map(_ ==> List(List((1, 2, 3, "a", 3, List((4, 5))))))
        _ <- Ns.i_(1).Rs1.*?(R1.i.R2.i.R3.i._R2._R1.R2a.s.R3.i.Rs4.*?(R4.i.R5.i)).query.get.map(_ ==> List(List((1, 2, 3, "a", 3, List((4, 5))))))

        _ <- Ns.i.Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i.R5.i._R4._R3.R4a.s.R5.i)).insert(2, List((1, 2, List((3, 4, 5, "a", 55))))).transact
        _ <- Ns.i_(2).Rs1.*(R1.i.R2.i.Rs3.*(R3.i.R4.i.R5.i._R4._R3.R4a.s.R5.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, 5, "a", 55))))))
        _ <- Ns.i_(2).Rs1.*?(R1.i.R2.i.Rs3.*?(R3.i.R4.i.R5.i._R4._R3.R4a.s.R5.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, 5, "a", 55))))))
      } yield ()
    }

    "Backref before nested" - refs { implicit conn =>
      for {
        _ <- Ns.i.R1.i._Ns.Rs1.*(R1.i).insert(0, 1, List(2)).transact
        _ <- Ns.i.R1.i._Ns.Rs1.*(R1.i).query.get.map(_ ==> List((0, 1, List(2))))
        _ <- Ns.i.R1.i._Ns.Rs1.*?(R1.i).query.get.map(_ ==> List((0, 1, List(2))))
      } yield ()
    }

    "Backref insert: no ref re-use after" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.R2.i._R1.R2.s).insert(0, List((1, 2, "a"))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't re-use previous namespace R2 after backref _R1."
          }

        _ <- Ns.i.Rs1.*(R1.i.R2.i.R3.i._R2._R1.R2.s).insert(0, List((1, 2, 3, "a"))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't re-use previous namespace R2 after backref _R1."
          }
      } yield ()
    }


    "Optional attributes in nested" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s_?.R2.i).insert(
          (1, List((1, Some("a"), 2))),
          (2, List((1, None, 2))),
          (3, Nil),
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.s_?.R2.i).query.get.map(_ ==> List(
          (1, List((1, Some("a"), 2))),
          (2, List((1, None, 2))),
        ))
        _ <- Ns.i.Rs1.*?(R1.i.s_?.R2.i).query.get.map(_ ==> List(
          (1, List((1, Some("a"), 2))),
          (2, List((1, None, 2))),
          (3, Nil),
        ))

        _ <- Ns.i.Rs1.*(R1.i.R2.i.s_?).insert(
          (4, List((1, 2, Some("a")))),
          (5, List((1, 2, None))),
          (6, Nil),
        ).transact

        _ <- Ns.i.>(3).a1.Rs1.*(R1.i.R2.i.s_?).query.get.map(_ ==> List(
          (4, List((1, 2, Some("a")))),
          (5, List((1, 2, None))),
        ))
        _ <- Ns.i.>(3).a1.Rs1.*?(R1.i.R2.i.s_?).query.get.map(_ ==> List(
          (4, List((1, 2, Some("a")))),
          (5, List((1, 2, None))),
          (6, Nil)
        ))
      } yield ()
    }


    "Optional attributes before nested" - refs { implicit conn =>
      for {
        _ <- Ns.s_?.Rs1.i.Rs2.*(R2.i).insert(List(
          (Some("a"), 10, List(1, 2)),
          (Some("b"), 20, List(3)),
          (None, 30, List()),
          (Some("d"), 40, List()),
        )).transact

        _ <- Ns.s_?.Rs1.i.a1.Rs2.*?(R2.i).query.get.map(_ ==> List(
          (Some("a"), 10, List(1, 2)),
          (Some("b"), 20, List(3)),
          (None, 30, List()),
          (Some("d"), 40, List())
        ))
      } yield ()
    }


    "Optional Nested semantics" - refs { implicit conn =>
      for {
        _ <- Future("start for-comprehension with Future...")

        _ <- Ns.i.Rs1.*?(R1.s.i_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Tacit attributes not allowed in optional nested data structure. Found:
              |AttrOneTacInt("R1", "i", V, Seq(), None, Nil, Nil, None, None)""".stripMargin
        }

        _ <- Ns.i.Rs1.*?(R1.i.R2.i_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Tacit attributes not allowed in optional nested data structure. Found:
              |AttrOneTacInt("R2", "i", V, Seq(), None, Nil, Nil, None, None)""".stripMargin
        }

        // Ok:
        _ <- Ns.i.Rs1.*?(R1.s.i).query.get
        _ <- Ns.i.Rs1.*?(R1.i.R2.i).query.get


        _ <- Ns.i.Rs1.*?(R1.i.Rs2.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Only cardinality-one refs allowed in optional nested data structures. Found: " +
            """Ref("R1", "rs2", "R2", CardSet)"""
        }
        // Ok:
        _ <- Ns.i.Rs1.*?(R1.i.R2.i).query.get


        _ <- Ns.i.Rs1.*?(R1.i.R2.i._R1.s.R2a.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expected ref after backref _R1. " +
            "Please add attribute :R1/s to initial namespace R1 instead of after backref _R1."
        }
        // Ok:
        _ <- Ns.i.Rs1.*?(R1.i.s.R2.i._R1.R2a.i).query.get


        _ <- Ns.s_?.Rs1.*?(R1.i.R2.i.s_?).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Single optional attribute before optional nested data structure is not allowed."
        }
        // Ok:
        _ <- Ns.s.Rs1.*?(R1.i.R2.i.s_?).query.get
        _ <- Ns.s_.Rs1.*?(R1.i.R2.i.s_?).query.get
      } yield ()
    }
  }
}
