package molecule.datalog.datomic.test.relation.nested

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.concurrent.Future


object NestedRef extends DatomicTestSuite {


  override lazy val tests = Tests {

    "Ref" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.i).insert(0, List((1, 2))).transact

        _ <- A.i.Bb.*(B.i.C.i).query.get.map(_ ==> List((0, List((1, 2)))))
        _ <- A.i.Bb.*?(B.i.C.i).query.get.map(_ ==> List((0, List((1, 2)))))

        _ <- A.i.Bb.*(B.C.i).query.get.map(_ ==> List((0, List(2))))
        _ <- A.i.Bb.*?(B.C.i).query.get.map(_ ==> List((0, List(2))))

        _ <- A.Bb.*(B.C.i).query.get.map(_ ==> List(List(2)))
        _ <- A.Bb.*?(B.C.i).query.get.map(_ ==> List(List(2)))


        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i)).insert(1, List((1, 2, List((3, 4))))).transact
        _ <- A.i(1).Bb.*(B.i.C.i.Dd.*(D.i.E.i)).query.get.map(_ ==> List((1, List((1, 2, List((3, 4)))))))
        _ <- A.i(1).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i)).query.get.map(_ ==> List((1, List((1, 2, List((3, 4)))))))

        // Ref before nested
        _ <- A.i.B.i.Cc.*(C.i).insert(0, 1, List(2)).transact
        _ <- A.i.B.i.Cc.*(C.i).query.get.map(_ ==> List((0, 1, List(2))))
        _ <- A.i.B.i.Cc.*?(C.i).query.get.map(_ ==> List((0, 1, List(2))))
      } yield ()
    }

    "Ref with card-set attr" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.ii).insert(0, List((1, Set(2, 3)))).transact

        _ <- A.i.Bb.*(B.i.C.ii).query.get.map(_ ==> List((0, List((1, Set(2, 3))))))
        _ <- A.i.Bb.*?(B.i.C.ii).query.get.map(_ ==> List((0, List((1, Set(2, 3))))))

        _ <- A.i.Bb.*(B.C.ii).query.get.map(_ ==> List((0, List(Set(2, 3)))))
        _ <- A.i.Bb.*?(B.C.ii).query.get.map(_ ==> List((0, List(Set(2, 3)))))

        _ <- A.Bb.*(B.C.ii).query.get.map(_ ==> List(List(Set(2, 3))))
        _ <- A.Bb.*?(B.C.ii).query.get.map(_ ==> List(List(Set(2, 3))))
      } yield ()
    }


    "Backref, 1 step back" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i).insert(0, List((1, 2, "a", 3))).transact
        _ <- A.i_.Bb.*(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
        _ <- A.i_.Bb.*?(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))

        _ <- A.i_.Bb.*(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))
        _ <- A.i_.Bb.*?(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))

        _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).insert(1, List((1, 2, "a", 3, List((4, 5))))).transact
        _ <- A.i_(1).Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
        _ <- A.i_(1).Bb.*?(B.i.C.i._B.C1.s.D.i.Ee.*?(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))

        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).insert(2, List((1, 2, List((3, 4, "a", 5))))).transact
        _ <- A.i_(2).Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
        _ <- A.i_(2).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
      } yield ()
    }

    "Backref, 2 steps back" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i).insert(0, List((1, 2, 3, "a", 33))).transact
        _ <- A.i_.Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, 3, "a", 33))))
        _ <- A.i_.Bb.*?(B.i.C.i.D.i._C._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, 3, "a", 33))))

        _ <- A.i.Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i.Ee.*(E.i.F.i)).insert(1, List((1, 2, 3, "a", 3, List((4, 5))))).transact
        _ <- A.i_(1).Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i.Ee.*(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, 3, "a", 3, List((4, 5))))))
        _ <- A.i_(1).Bb.*?(B.i.C.i.D.i._C._B.C1.s.D.i.Ee.*?(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, 3, "a", 3, List((4, 5))))))

        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i.F.i._E._D.E1.s.F.i)).insert(2, List((1, 2, List((3, 4, 5, "a", 55))))).transact
        _ <- A.i_(2).Bb.*(B.i.C.i.Dd.*(D.i.E.i.F.i._E._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, 5, "a", 55))))))
        _ <- A.i_(2).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i.F.i._E._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, 5, "a", 55))))))
      } yield ()
    }

    "Backref before nested" - refs { implicit conn =>
      for {
        _ <- A.i.B.i._A.Bb.*(B.i).insert(0, 1, List(2)).transact
        _ <- A.i.B.i._A.Bb.*(B.i).query.get.map(_ ==> List((0, 1, List(2))))
        _ <- A.i.B.i._A.Bb.*?(B.i).query.get.map(_ ==> List((0, 1, List(2))))
      } yield ()
    }

    "Backref insert: no ref re-use after" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.C.i._B.C.s).insert(0, List((1, 2, "a"))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't re-use previous namespace C after backref _B."
          }

        _ <- A.i.Bb.*(B.i.C.i.D.i._C._B.C.s).insert(0, List((1, 2, 3, "a"))).transact
            .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't re-use previous namespace C after backref _B."
          }
      } yield ()
    }


    "Optional attributes in nested" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s_?.C.i).insert(
          (1, List((1, Some("a"), 2))),
          (2, List((1, None, 2))),
          (3, Nil),
        ).transact

        _ <- A.i.Bb.*(B.i.s_?.C.i).query.get.map(_ ==> List(
          (1, List((1, Some("a"), 2))),
          (2, List((1, None, 2))),
        ))
        _ <- A.i.Bb.*?(B.i.s_?.C.i).query.get.map(_ ==> List(
          (1, List((1, Some("a"), 2))),
          (2, List((1, None, 2))),
          (3, Nil),
        ))

        _ <- A.i.Bb.*(B.i.C.i.s_?).insert(
          (4, List((1, 2, Some("a")))),
          (5, List((1, 2, None))),
          (6, Nil),
        ).transact

        _ <- A.i.>(3).a1.Bb.*(B.i.C.i.s_?).query.get.map(_ ==> List(
          (4, List((1, 2, Some("a")))),
          (5, List((1, 2, None))),
        ))
        _ <- A.i.>(3).a1.Bb.*?(B.i.C.i.s_?).query.get.map(_ ==> List(
          (4, List((1, 2, Some("a")))),
          (5, List((1, 2, None))),
          (6, Nil)
        ))
      } yield ()
    }


    "Optional attributes before nested" - refs { implicit conn =>
      for {
        _ <- A.s_?.Bb.i.Cc.*(C.i).insert(List(
          (Some("a"), 10, List(1, 2)),
          (Some("b"), 20, List(3)),
          (None, 30, List()),
          (Some("d"), 40, List()),
        )).transact

        _ <- A.s_?.Bb.i.a1.Cc.*?(C.i).query.get.map(_ ==> List(
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

        _ <- A.i.Bb.*?(B.s.i_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Tacit attributes not allowed in optional nested data structure. Found:
              |AttrOneTacInt("B", "i", V, Seq(), None, None, Nil, Nil, None, None)""".stripMargin
        }

        _ <- A.i.Bb.*?(B.i.C.i_).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==>
            """Tacit attributes not allowed in optional nested data structure. Found:
              |AttrOneTacInt("C", "i", V, Seq(), None, None, Nil, Nil, None, None)""".stripMargin
        }

        // Ok:
        _ <- A.i.Bb.*?(B.s.i).query.get
        _ <- A.i.Bb.*?(B.i.C.i).query.get


        _ <- A.i.Bb.*?(B.i.Cc.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Only cardinality-one refs allowed in optional nested data structures. Found: " +
            """Ref("B", "cc", "C", CardSet, false)"""
        }
        // Ok:
        _ <- A.i.Bb.*?(B.i.C.i).query.get


        _ <- A.i.Bb.*?(B.i.C.i._B.s.C1.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expected ref after backref _B. " +
            "Please add attribute B.s to initial namespace B instead of after backref _B."
        }
        // Ok:
        _ <- A.i.Bb.*?(B.i.s.C.i._B.C1.i).query.get


        _ <- A.s_?.Bb.*?(B.i.C.i.s_?).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Single optional attribute before optional nested data structure is not allowed."
        }
        // Ok:
        _ <- A.s.Bb.*?(B.i.C.i.s_?).query.get
        _ <- A.s_.Bb.*?(B.i.C.i.s_?).query.get
      } yield ()
    }
  }
}
