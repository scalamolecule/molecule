package molecule.datalog.datomic.test.txMetaData

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._


object TxNested extends DatomicTestSuite {

  lazy val tests = Tests {

    "Basic" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.s).Tx(R3.i_(1)).insert(List(
          (1, List("a", "b")),
          (2, Nil)
        )).transact

        _ <- Ns.i.Rs1.*(R1.s).Tx(R3.i).query.get.map(_ ==> List(
          (1, List("a", "b"), 1)
        ))

        _ <- Ns.i.a1.Rs1.*?(R1.s).Tx(R3.i).query.get.map(_ ==> List(
          (1, List("a", "b"), 1),
          (2, Nil, 1),
        ))
      } yield ()
    }

    "1 level" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.s + R3.s.i).insert(0, List(((1, "a"), ("b", 2)))).transact

        _ <- Ns.i.Rs1.*(R1.i.s + R3.s.i).query.get.map(_ ==> List((0, List(((1, "a"), ("b", 2))))))
        _ <- Ns.i.Rs1.*(R1.i.s + R3.s).query.get.map(_ ==> List((0, List(((1, "a"), "b")))))
        _ <- Ns.i.Rs1.*(R1.i + R3.s.i).query.get.map(_ ==> List((0, List((1, ("b", 2))))))
        _ <- Ns.i.Rs1.*(R1.i + R3.i).query.get.map(_ ==> List((0, List((1, 2)))))
      } yield ()
    }


    "2 levels" - refs { implicit conn =>
      for {
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s.i)).insert(
          0, List(
            (1, List(
              ((2, "a"), ("b", 3)))))
        ).transact

        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), ("b", 3))))))
        ))
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              ((2, "a"), "b")))))
        ))
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i + R4.s.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, ("b", 3))))))
        ))
        _ <- Ns.i.Rs1.*(R1.i.Rs2.*(R2.i + R4.i)).query.get.map(_ ==> List(
          (0, List(
            (1, List(
              (2, 3)))))
        ))
      } yield ()
    }

    "Nested ref + flat tx meta data" - refs { implicit conn =>
      for {
        _ <- Ns.s.Rs1.*(R1.i_?.R2.i_?.s).Tx(R3.i_(1)).insert(List(
          ("A", List((Some(11), Some(12), "a"))),
          ("B", List((Some(13), None, "b"))),
          ("C", List((None, Some(14), "c"))),
          ("D", List((None, None, "d"))),
          ("E", List())
        )).transact

        _ <- Ns.s.a1.Rs1.*?(R1.i_?.R2.i_?.s).Tx(R3.i).query.get.map(_ ==> List(
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
        _ <- Ns.s.Rs1.*(R1.i_?.R2.i_?.s).Tx(R4.i_(7777) + R3.i_(8888).s_("meta")).insert(List(
          ("A", List((Some(11), Some(12), "a"), (None, Some(120), "aa"))),
          ("B", List((Some(13), None, "b"))),
          ("C", List((None, Some(14), "c"))),
          ("D", List((None, None, "d"))),
          ("E", List())
        )).transact

        _ <- Ns.s.a1.Rs1.*?(R1.i_?.R2.i_?.s).Tx(R4.i + R3.i.s).query.get.map(_ ==> List(
          ("A", List((Some(11), Some(12), "a"), (None, Some(120), "aa")), 7777, (8888, "meta")),
          ("B", List((Some(13), None, "b")), 7777, (8888, "meta")),
          ("C", List((None, Some(14), "c")), 7777, (8888, "meta")),
          ("D", List((None, None, "d")), 7777, (8888, "meta")),
          ("E", List(), 7777, (8888, "meta"))
        ))

        _ <- Ns.s.a1.Rs1.*(R1.i_?.R2.i_?.s).Tx(R4.i + R3.i.s).query.get.map(_ ==> List(
          ("A", List((Some(11), Some(12), "a"), (None, Some(120), "aa")), 7777, (8888, "meta")),
          ("B", List((Some(13), None, "b")), 7777, (8888, "meta")),
          ("C", List((None, Some(14), "c")), 7777, (8888, "meta")),
          ("D", List((None, None, "d")), 7777, (8888, "meta")),
        ))
      } yield ()
    }


    "Nested, with empty tx meta data composites" - refs { implicit conn =>
      for {
        _ <- Ns.s.i.Rs1.*(R1.i).Tx(R5.s_("x").i_(4) + R4.i_(5)).insert(
          "a", 1, List(2, 3)
        ).transact

        // Mandatory nested
        _ <- Ns.s.i.Rs1.*(R1.i.a1).Tx(R5.s.i + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), ("x", 4), 5)
        ))
        _ <- Ns.s.i.Rs1.*(R1.i.a1).Tx(R5.s.i_ + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- Ns.s.i.Rs1.*(R1.i.a1).Tx(R5.s_.i + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), 4, 5)
        ))
        _ <- Ns.s.i.Rs1.*(R1.i.a1).Tx(R5.s + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- Ns.s.Rs1.*(R1.i.a1).Tx(R5.s + R4.i).query.get.map(_ ==> List(
          ("a", List(2, 3), "x", 5)
        ))
        _ <- Ns.s.Rs1.*(R1.i.a1).Tx(R5.s + R4.i_).query.get.map(_ ==> List(
          ("a", List(2, 3), "x")
        ))
        _ <- Ns.s.Rs1.*(R1.i.a1).Tx(R5.s_ + R4.i).query.get.map(_ ==> List(
          ("a", List(2, 3), 5)
        ))
        _ <- Ns.s.Rs1.*(R1.i.a1).Tx(R5.s_ + R4.i_).query.get.map(_ ==> List(
          ("a", List(2, 3))
        ))

        // Optional nested
        _ <- Ns.s.i.Rs1.*?(R1.i.a1).Tx(R5.s.i + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), ("x", 4), 5)
        ))
        _ <- Ns.s.i.Rs1.*?(R1.i.a1).Tx(R5.s.i_ + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- Ns.s.i.Rs1.*?(R1.i.a1).Tx(R5.s_.i + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), 4, 5)
        ))
        _ <- Ns.s.i.Rs1.*?(R1.i.a1).Tx(R5.s + R4.i).query.get.map(_ ==> List(
          ("a", 1, List(2, 3), "x", 5)
        ))
        _ <- Ns.s.Rs1.*?(R1.i.a1).Tx(R5.s + R4.i).query.get.map(_ ==> List(
          ("a", List(2, 3), "x", 5)
        ))
        _ <- Ns.s.Rs1.*?(R1.i.a1).Tx(R5.s + R4.i_).query.get.map(_ ==> List(
          ("a", List(2, 3), "x")
        ))
        _ <- Ns.s.Rs1.*?(R1.i.a1).Tx(R5.s_ + R4.i).query.get.map(_ ==> List(
          ("a", List(2, 3), 5)
        ))
        _ <- Ns.s.Rs1.*?(R1.i.a1).Tx(R5.s_ + R4.i_).query.get.map(_ ==> List(
          ("a", List(2, 3))
        ))
      } yield ()
    }


    "Nested ref + composite tx meta data with ref" - refs { implicit conn =>
      for {
        _ <- m(Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
          .Tx(R5.s_("b").i_(5).R6.s_("c") + R4.i_(6).s_("d"))).insert(List(
          ("A", List(
            (1, 2, "a", List(3, 4)),
            (11, 22, "aa", Nil))),
          ("B", Nil)
        )).transact

        _ <- Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
          .Tx(R5.s.i.R6.s + R4.i.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5, "c"), (6, "d")),
          ("B", Nil, ("b", 5, "c"), (6, "d"))
        ))
        _ <- Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
          .Tx(R5.s.i.R6.s + R4.i.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5, "c"), (6, "d"))
        ))

        _ <- Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
          .Tx(R5.s.i.R6.s + R4.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5, "c"), 6),
          ("B", Nil, ("b", 5, "c"), 6)
        ))
        _ <- Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
          .Tx(R5.s.i.R6.s + R4.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5, "c"), 6)
        ))

        _ <- Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
          .Tx(R5.s.i.R6.s + R4.i_.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5, "c")),
          ("B", Nil, ("b", 5, "c"))
        ))
        _ <- Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
          .Tx(R5.s.i.R6.s + R4.i_.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5, "c"))
        ))

        _ <- Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
          .Tx(R5.s.i.R6.s_ + R4.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", 5), 6),
          ("B", Nil, ("b", 5), 6)
        ))
        _ <- Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
          .Tx(R5.s.i.R6.s_ + R4.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", 5), 6)
        ))

        _ <- Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
          .Tx(R5.s.i_.R6.s + R4.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), ("b", "c"), 6),
          ("B", Nil, ("b", "c"), 6)
        ))
        _ <- Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
          .Tx(R5.s.i_.R6.s + R4.i.s_).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), ("b", "c"), 6)
        ))

        _ <- Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
          .Tx(R5.s.i.R6.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4)), (11, 22, "aa", Nil)), "b", 5, "c"),
          ("B", Nil, "b", 5, "c")
        ))
        _ <- Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
          .Tx(R5.s.i.R6.s).query.get.map(_ ==> List(
          ("A", List((1, 2, "a", List(3, 4))), "b", 5, "c")
        ))
      } yield ()
    }
  }
}