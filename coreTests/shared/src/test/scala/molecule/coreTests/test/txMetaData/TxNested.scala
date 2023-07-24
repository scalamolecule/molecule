package molecule.coreTests.test.txMetaData

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait TxNested extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "Basic" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.s).Tx(D.i_(1)).insert(List(
          (1, List("a", "b")),
          (2, Nil)
        )).transact

        _ <- A.i.Bb.*(B.s).Tx(D.i).query.get.map(_ ==> List(
          (1, List("a", "b"), 1)
        ))

        _ <- A.i.a1.Bb.*?(B.s).Tx(D.i).query.get.map(_ ==> List(
          (1, List("a", "b"), 1),
          (2, Nil, 1),
        ))
      } yield ()
    }

    "1 level" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.s + D.s.i).insert(0, List(((1, "a"), ("b", 2)))).transact

        _ <- A.i.Bb.*(B.i.s + D.s.i).query.get.map(_ ==> List((0, List(((1, "a"), ("b", 2))))))
        _ <- A.i.Bb.*(B.i.s + D.s).query.get.map(_ ==> List((0, List(((1, "a"), "b")))))
        _ <- A.i.Bb.*(B.i + D.s.i).query.get.map(_ ==> List((0, List((1, ("b", 2))))))
        _ <- A.i.Bb.*(B.i + D.i).query.get.map(_ ==> List((0, List((1, 2)))))
      } yield ()
    }


    "2 levels" - refs { implicit conn =>
      for {
        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.s.i)).insert(
          0, List(
            (1, List(
              ((2, "a"), ("b", 3)))))
        ).transact

        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), ("b", 3))))))
        ))
        _ <- A.i.Bb.*(B.i.Cc.*(C.i.s + E.s)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), "b")))))
        ))
        _ <- A.i.Bb.*(B.i.Cc.*(C.i + E.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, ("b", 3))))))
        ))
        _ <- A.i.Bb.*(B.i.Cc.*(C.i + E.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, 3)))))
        ))
      } yield ()
    }

    "Nested ref + flat tx meta data" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i_?.C.i_?.s).Tx(D.i_(1)).insert(List(
          ("A", List((Some(11), Some(12), "a"))),
          ("B", List((Some(13), None, "b"))),
          ("C", List((None, Some(14), "c"))),
          ("D", List((None, None, "d"))),
          ("E", List())
        )).transact

        _ <- A.s.a1.Bb.*?(B.i_?.C.i_?.s).Tx(D.i).query.get.map(_ ==> List(
          ("A", List((Some(11), Some(12), "a")), 1),
          ("B", List((Some(13), None, "b")), 1),
          ("C", List((None, Some(14), "c")), 1),
          ("D", List((None, None, "d")), 1),
          ("E", List(), 1)
        ))
      } yield ()
    }


    "Nested ref + composite tx meta data" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i_?.C.i_?.s).Tx(E.i_(7777) + D.i_(8888).s_("meta")).insert(List(
          ("A", List((Some(11), Some(12), "a"), (None, Some(120), "aa"))),
          ("B", List((Some(13), None, "b"))),
          ("C", List((None, Some(14), "c"))),
          ("D", List((None, None, "d"))),
          ("E", List())
        )).transact

        _ <- A.s.a1.Bb.*?(B.i_?.C.i_?.s).Tx(E.i + D.i.s).query.get.map(_ ==> List(
          ("A", List((Some(11), Some(12), "a"), (None, Some(120), "aa")), 7777, (8888, "meta")),
          ("B", List((Some(13), None, "b")), 7777, (8888, "meta")),
          ("C", List((None, Some(14), "c")), 7777, (8888, "meta")),
          ("D", List((None, None, "d")), 7777, (8888, "meta")),
          ("E", List(), 7777, (8888, "meta"))
        ))

        _ <- A.s.a1.Bb.*(B.i_?.C.i_?.s).Tx(E.i + D.i.s).query.get.map(_ ==> List(
          ("A", List((Some(11), Some(12), "a"), (None, Some(120), "aa")), 7777, (8888, "meta")),
          ("B", List((Some(13), None, "b")), 7777, (8888, "meta")),
          ("C", List((None, Some(14), "c")), 7777, (8888, "meta")),
          ("D", List((None, None, "d")), 7777, (8888, "meta")),
        ))
      } yield ()
    }


    "Nested, with empty tx meta data composites" - refs { implicit conn =>
      for {
        _ <- A.s.i.Bb.*(B.i).Tx(F.s_("x").i_(4) + E.i_(5)).insert(
          "a", 1, List(2, 3)
        ).transact

        // Mandatory nested
        _ <- A.s.i.Bb.*(B.i.a1).Tx(F.s.i + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), ("x", 4), 5)
        ))
        _ <- A.s.i.Bb.*(B.i.a1).Tx(F.s.i_ + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- A.s.i.Bb.*(B.i.a1).Tx(F.s_.i + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), 4, 5)
        ))
        _ <- A.s.i.Bb.*(B.i.a1).Tx(F.s + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- A.s.Bb.*(B.i.a1).Tx(F.s + E.i).query.get.map(_ ==> List(
          ("a", List(2, 3), "x", 5)
        ))
        _ <- A.s.Bb.*(B.i.a1).Tx(F.s + E.i_).query.get.map(_ ==> List(
          ("a", List(2, 3), "x")
        ))
        _ <- A.s.Bb.*(B.i.a1).Tx(F.s_ + E.i).query.get.map(_ ==> List(
          ("a", List(2, 3), 5)
        ))
        _ <- A.s.Bb.*(B.i.a1).Tx(F.s_ + E.i_).query.get.map(_ ==> List(
          ("a", List(2, 3))
        ))

        // Optional nested
        _ <- A.s.i.Bb.*?(B.i.a1).Tx(F.s.i + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), ("x", 4), 5)
        ))
        _ <- A.s.i.Bb.*?(B.i.a1).Tx(F.s.i_ + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- A.s.i.Bb.*?(B.i.a1).Tx(F.s_.i + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), 4, 5)
        ))
        _ <- A.s.i.Bb.*?(B.i.a1).Tx(F.s + E.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- A.s.Bb.*?(B.i.a1).Tx(F.s + E.i).query.get.map(_ ==> List(
          ("a", List(2, 3), "x", 5)
        ))
        _ <- A.s.Bb.*?(B.i.a1).Tx(F.s + E.i_).query.get.map(_ ==> List(
          ("a", List(2, 3), "x")
        ))
        _ <- A.s.Bb.*?(B.i.a1).Tx(F.s_ + E.i).query.get.map(_ ==> List(
          ("a", List(2, 3), 5)
        ))
        _ <- A.s.Bb.*?(B.i.a1).Tx(F.s_ + E.i_).query.get.map(_ ==> List(
          ("a", List(2, 3))
        ))
      } yield ()
    }


    "Nested ref + composite tx meta data with ref" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i)).Tx(F.s_("b").i_(5).G.s_("c") + E.i_(6).s_("d")).insert(List(
          ("A", List(
            (1, 2, "a", List(3, 4)),
            (11, 22, "aa", Nil))),
          ("B", Nil)
        )).transact

        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(F.s.i.G.s + E.i.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5, "c"), (6, "d")),
          ("B", Nil, ("b", 5, "c"), (6, "d"))
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(F.s.i.G.s + E.i.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5, "c"), (6, "d"))
        ))

        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(F.s.i.G.s + E.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5, "c"), 6),
          ("B", Nil, ("b", 5, "c"), 6)
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(F.s.i.G.s + E.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5, "c"), 6)
        ))

        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(F.s.i.G.s + E.i_.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5, "c")),
          ("B", Nil, ("b", 5, "c"))
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(F.s.i.G.s + E.i_.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5, "c"))
        ))

        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(F.s.i.G.s_ + E.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5), 6),
          ("B", Nil, ("b", 5), 6)
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(F.s.i.G.s_ + E.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5), 6)
        ))

        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(F.s.i_.G.s + E.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", "c"), 6),
          ("B", Nil, ("b", "c"), 6)
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(F.s.i_.G.s + E.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", "c"), 6)
        ))

        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(F.s.i.G.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), "b", 5, "c"),
          ("B", Nil, "b", 5, "c")
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(F.s.i.G.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), "b", 5, "c")
        ))
      } yield ()
    }
  }
}