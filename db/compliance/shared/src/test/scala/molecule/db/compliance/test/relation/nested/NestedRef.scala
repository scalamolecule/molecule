package molecule.db.compliance.test.relation.nested

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


case class NestedRef(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Ref" - refs {
    for {
      _ <- A.i.Bb.*(B.i.C.i).insert(
        (0, Nil),
        (1, List((1, 2))),
        (2, List((3, 4), (5, 6))),
      ).transact

      _ <- A.i.a1.Bb.*?(B.i.a1.C.i).query.get.map(_ ==> List(
        (0, Nil),
        (1, List((1, 2))),
        (2, List((3, 4), (5, 6))),
      ))
      _ <- A.i.a1.Bb.*(B.i.a1.C.i).query.get.map(_ ==> List(
        (1, List((1, 2))),
        (2, List((3, 4), (5, 6))),
      ))

      _ <- A.i.a1.Bb.*?(B.C.i.a1).query.get.map(_ ==> List(
        (0, Nil),
        (1, List(2)),
        (2, List(4, 6)),
      ))
      _ <- A.i.a1.Bb.*(B.C.i.a1).query.get.map(_ ==> List(
        (1, List(2)),
        (2, List(4, 6)),
      ))

      _ <- A.Bb.*?(B.C.i.a1).query.get.map(_ ==> List(
        // List(), // empty list is not returned when no attributes are present before nesting
        List(2),
        List(4, 6),
      ))
      _ <- A.Bb.*(B.C.i.a1).query.get.map(_ ==> List(
        List(2),
        List(4, 6),
      ))
    } yield ()
  }


  "Ref, 2 levels" - refs {
    for {
      _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i)).insert(
        (0, Nil),
        (1, List(
          (1, 1, Nil),
        )),
        (2, List(
          (1, 1, Nil),
          (2, 2, List((1, 2))),
          (3, 3, List((1, 2), (3, 4))),
        )),
      ).transact

      _ <- A.i.a1.Bb.*?(B.i.a1.C.i.Dd.*?(D.i.a1.E.i)).query.get.map(_ ==> List(
        (0, Nil),
        (1, List(
          (1, 1, Nil),
        )),
        (2, List(
          (1, 1, Nil),
          (2, 2, List((1, 2))),
          (3, 3, List((1, 2), (3, 4))),
        )),
      ))

      _ <- A.i.Bb.*(B.i.a1.C.i.Dd.*(D.i.a1.E.i)).query.get.map(_ ==> List(
        (2, List(
          (2, 2, List((1, 2))),
          (3, 3, List((1, 2), (3, 4))),
        )),
      ))
    } yield ()
  }


  "Ref before nested" - refs {
    for {
      // Ref before nested
      _ <- A.i.B.i.Cc.*(C.i).insert(
        (0, 0, Nil),
        (1, 1, List(1)),
        (2, 2, List(1, 2)),
      ).transact

      _ <- A.i.a1.B.i.Cc.*?(C.i.a1).query.get.map(_ ==> List(
        (0, 0, Nil),
        (1, 1, List(1)),
        (2, 2, List(1, 2)),
      ))

      _ <- A.i.a1.B.i.Cc.*(C.i.a1).query.get.map(_ ==> List(
        (1, 1, List(1)),
        (2, 2, List(1, 2)),
      ))
    } yield ()
  }


  "Nested ref with card-set attr" - refs {
    for {
      _ <- A.i.Bb.*(B.i.C.iSet).insert(
        (0, Nil),
        (1, List(
          (1, Set.empty[Int])
        )),
        (2, List(
          (1, Set.empty[Int]),
          (2, Set(0)),
          (3, Set(1, 2)),
        )),
      ).transact


      _ <- A.i.a1.Bb.*?(B.i.C.iSet).query.get.map(_ ==> List(
        (0, Nil),
        (1, Nil),
        (2, List(
          (2, Set(0)),
          (3, Set(1, 2)),
        )),
      ))
      _ <- A.i.Bb.*(B.i.a1.C.iSet).query.get.map(_ ==> List(
        (2, List(
          (2, Set(0)),
          (3, Set(1, 2)),
        )),
      ))

      _ <- A.i.a1.Bb.*?(B.C.iSet).query.get.map(_ ==> List(
        (0, Nil),
        (1, Nil),
        (2, List(
          Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        )),
      ))
      _ <- A.i.Bb.*(B.C.iSet).query.get.map(_ ==> List(
        (2, List(
          Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        )),
      ))

      _ <- A.Bb.*?(B.C.iSet).query.get.map(_ ==> List(
        List(
          Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        ),
      ))
      _ <- A.Bb.*(B.C.iSet).query.get.map(_ ==> List(
        List(
          Set(0, 1, 2), // Set(0) and Set(1, 2) coalesced to one Set
        ),
      ))
    } yield ()
  }


  "Backref, 1 step back" - refs {
    for {
      _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i).insert((0, List((1, 2, "a", 3)))).transact
      _ <- A.i_.Bb.*(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))
      _ <- A.i_.Bb.*?(B.i.C.i._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, "a", 3))))

      _ <- A.i_.Bb.*(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))
      _ <- A.i_.Bb.*?(B.i.C.i._B.C1.D.i).query.get.map(_ ==> List(List((1, 2, 3))))

      _ <- A.i.Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).insert((1, List((1, 2, "a", 3, List((4, 5)))))).transact
      _ <- A.i_(1).Bb.*(B.i.C.i._B.C1.s.D.i.Ee.*(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))
      _ <- A.i_(1).Bb.*?(B.i.C.i._B.C1.s.D.i.Ee.*?(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, "a", 3, List((4, 5))))))

      _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).insert((2, List((1, 2, List((3, 4, "a", 5)))))).transact
      _ <- A.i_(2).Bb.*(B.i.C.i.Dd.*(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
      _ <- A.i_(2).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, "a", 5))))))
    } yield ()
  }


  "Backref, 2 steps back" - refs {
    for {
      _ <- A.i.Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i).insert((0, List((1, 2, 3, "a", 33)))).transact
      _ <- A.i_.Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, 3, "a", 33))))
      _ <- A.i_.Bb.*?(B.i.C.i.D.i._C._B.C1.s.D.i).query.get.map(_ ==> List(List((1, 2, 3, "a", 33))))

      _ <- A.i.Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i.Ee.*(E.i.F.i)).insert((1, List((1, 2, 3, "a", 3, List((4, 5)))))).transact
      _ <- A.i_(1).Bb.*(B.i.C.i.D.i._C._B.C1.s.D.i.Ee.*(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, 3, "a", 3, List((4, 5))))))
      _ <- A.i_(1).Bb.*?(B.i.C.i.D.i._C._B.C1.s.D.i.Ee.*?(E.i.F.i)).query.get.map(_ ==> List(List((1, 2, 3, "a", 3, List((4, 5))))))

      _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i.F.i._E._D.E1.s.F.i)).insert((2, List((1, 2, List((3, 4, 5, "a", 55)))))).transact
      _ <- A.i_(2).Bb.*(B.i.C.i.Dd.*(D.i.E.i.F.i._E._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, 5, "a", 55))))))
      _ <- A.i_(2).Bb.*?(B.i.C.i.Dd.*?(D.i.E.i.F.i._E._D.E1.s.F.i)).query.get.map(_ ==> List(List((1, 2, List((3, 4, 5, "a", 55))))))
    } yield ()
  }

  "Backref before nested" - refs {
    for {
      _ <- A.i.B.i._A.Bb.*(B.i).insert((0, 1, List(2))).transact
      _ <- A.i.B.i._A.Bb.*(B.i).query.get.map(_ ==> List((0, 1, List(2))))
      _ <- A.i.B.i._A.Bb.*?(B.i).query.get.map(_ ==> List((0, 1, List(2))))
    } yield ()
  }

  "Backref insert: no ref re-use after" - refs {
    for {
      _ <- A.i.Bb.*(B.i.C.i._B.C.s).insert((0, List((1, 2, "a")))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't re-use previous entity C after backref _B."
        }

      _ <- A.i.Bb.*(B.i.C.i.D.i._C._B.C.s).insert((0, List((1, 2, 3, "a")))).transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't re-use previous entity C after backref _B."
        }
    } yield ()
  }


  "Optional attributes in nested" - refs {
    for {
      _ <- A.i.Bb.*(B.i.s_?.C.i).insert(
        (1, List((10, Some("a"), 11))),
        (2, List((20, None, 21))),
        (3, Nil),
      ).transact

      _ <- A.i.a1.Bb.*(B.i.s_?.C.i).query.get.map(_ ==> List(
        (1, List((10, Some("a"), 11))),
        (2, List((20, None, 21))),
      ))
      _ <- A.i.a1.Bb.*?(B.i.s_?.C.i).query.get.map(_ ==> List(
        (1, List((10, Some("a"), 11))),
        (2, List((20, None, 21))),
        (3, Nil),
      ))

      _ <- A.i.Bb.*(B.i.C.i.s_?).insert(
        (4, List((10, 11, Some("a")))),
        (5, List((20, 21, None))),
        (6, Nil),
      ).transact

      _ <- A.i.>(3).a1.Bb.*(B.i.C.i.s_?).query.get.map(_ ==> List(
        (4, List((10, 11, Some("a")))),
        (5, List((20, 21, None))),
      ))
      _ <- A.i.>(3).a1.Bb.*?(B.i.C.i.s_?).query.get.map(_ ==> List(
        (4, List((10, 11, Some("a")))),
        (5, List((20, 21, None))),
        (6, Nil)
      ))
    } yield ()
  }


  "Optional attributes before nested" - refs {
    for {
      _ <- A.s_?.Bb.i.Cc.*(C.i).insert(List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List()),
      )).transact

      _ <- A.s_?.Bb.i.a1.Cc.*?(C.i.a1).query.get.map(_ ==> List(
        (Some("a"), 10, List(1, 2)),
        (Some("b"), 20, List(3)),
        (None, 30, List()),
        (Some("d"), 40, List())
      ))
    } yield ()
  }


  "Optional Nested semantics" - refs {
    for {
      _ <- A.i.Bb.*?(B.s.i_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Tacit attributes not allowed in optional nested queries (B.i_)."
        }

      _ <- A.i.Bb.*?(B.i.C.i_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Tacit attributes not allowed in optional nested queries (C.i_)."
        }

      // Ok:
      _ <- A.i.Bb.*?(B.s.i).query.get
      _ <- A.i.Bb.*?(B.i.C.i).query.get


      _ <- A.i.Bb.*?(B.i.Cc.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Only cardinality-one refs allowed in optional nested queries (B.cc)."
        }
      // Ok:
      _ <- A.i.Bb.*?(B.i.C.i).query.get


      _ <- A.i.Bb.*?(B.i.C.i._B.s.C1.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Expected ref after backref _B. " +
            "Please add attribute B.s to initial entity B instead of after backref _B."
        }
      // Ok:
      _ <- A.i.Bb.*?(B.i.s.C.i._B.C1.i).query.get


      _ <- A.s_?.Bb.*?(B.i.C.i.s_?).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Single optional attribute before optional nested data structure " +
            "is not allowed (id attribute doesn't count)."
        }
      // Ok:
      _ <- A.s.Bb.*?(B.i.C.i.s_?).query.get
      _ <- A.s_.Bb.*?(B.i.C.i.s_?).query.get
    } yield ()
  }
}
