package molecule.coreTests.test.sort

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait SortTxMetaData extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "flat" - refs { implicit conn =>
      for {
        // 2 transactions with different tx meta data
        _ <- A.i.Tx(B.s_("A")).insert(1, 2).transact
        _ <- A.i.Tx(B.s_("B")).insert(1, 2).transact

        _ <- A.i.a2.Tx(B.s.a1).query.get.map(_ ==> List(
          (1, "A"),
          (2, "A"),
          (1, "B"),
          (2, "B"),
        ))
        _ <- A.i.d2.Tx(B.s.a1).query.get.map(_ ==> List(
          (2, "A"),
          (1, "A"),
          (2, "B"),
          (1, "B"),
        ))
        _ <- A.i.a2.Tx(B.s.d1).query.get.map(_ ==> List(
          (1, "B"),
          (2, "B"),
          (1, "A"),
          (2, "A"),
        ))
        _ <- A.i.d2.Tx(B.s.d1).query.get.map(_ ==> List(
          (2, "B"),
          (1, "B"),
          (2, "A"),
          (1, "A"),
        ))

        _ <- A.i.a1.Tx(B.s.a2).query.get.map(_ ==> List(
          (1, "A"),
          (1, "B"),
          (2, "A"),
          (2, "B"),
        ))
        _ <- A.i.d1.Tx(B.s.a2).query.get.map(_ ==> List(
          (2, "A"),
          (2, "B"),
          (1, "A"),
          (1, "B"),
        ))
        _ <- A.i.a1.Tx(B.s.d2).query.get.map(_ ==> List(
          (1, "B"),
          (1, "A"),
          (2, "B"),
          (2, "A"),
        ))
        _ <- A.i.d1.Tx(B.s.d2).query.get.map(_ ==> List(
          (2, "B"),
          (2, "A"),
          (1, "B"),
          (1, "A"),
        ))
      } yield ()
    }


    "composites" - refs { implicit conn =>
      for {
        _ <- (A.i.s + B.s.i).Tx(D.s_("hello")).insert(
          ((1, "a"), ("aa", 11)),
          ((2, "b"), ("bb", 22))
        ).transact

        _ <- (A.i.s + B.s.i).Tx(D.s_("world")).insert(
          ((3, "a"), ("aa", 11)),
          ((4, "b"), ("bb", 22))
        ).transact

        _ <- (A.i.a2.s + B.s.i).Tx(D.s.a1).query.get.map(_ ==> List(
          ((1, "a"), ("aa", 11), "hello"),
          ((2, "b"), ("bb", 22), "hello"),
          ((3, "a"), ("aa", 11), "world"),
          ((4, "b"), ("bb", 22), "world"),
        ))
        _ <- (A.i.d2.s + B.s.i).Tx(D.s.a1).query.get.map(_ ==> List(
          ((2, "b"), ("bb", 22), "hello"),
          ((1, "a"), ("aa", 11), "hello"),
          ((4, "b"), ("bb", 22), "world"),
          ((3, "a"), ("aa", 11), "world"),
        ))
        _ <- (A.i.s + B.s.a2.i).Tx(D.s.a1).query.get.map(_ ==> List(
          ((1, "a"), ("aa", 11), "hello"),
          ((2, "b"), ("bb", 22), "hello"),
          ((3, "a"), ("aa", 11), "world"),
          ((4, "b"), ("bb", 22), "world"),
        ))
        _ <- (A.i.s + B.s.d2.i).Tx(D.s.a1).query.get.map(_ ==> List(
          ((2, "b"), ("bb", 22), "hello"),
          ((1, "a"), ("aa", 11), "hello"),
          ((4, "b"), ("bb", 22), "world"),
          ((3, "a"), ("aa", 11), "world"),
        ))

        _ <- (A.i.a2.s + B.s.i).Tx(D.s.d1).query.get.map(_ ==> List(
          ((3, "a"), ("aa", 11), "world"),
          ((4, "b"), ("bb", 22), "world"),
          ((1, "a"), ("aa", 11), "hello"),
          ((2, "b"), ("bb", 22), "hello"),
        ))
        _ <- (A.i.d2.s + B.s.i).Tx(D.s.d1).query.get.map(_ ==> List(
          ((4, "b"), ("bb", 22), "world"),
          ((3, "a"), ("aa", 11), "world"),
          ((2, "b"), ("bb", 22), "hello"),
          ((1, "a"), ("aa", 11), "hello"),
        ))
        _ <- (A.i.s + B.s.a2.i).Tx(D.s.d1).query.get.map(_ ==> List(
          ((3, "a"), ("aa", 11), "world"),
          ((4, "b"), ("bb", 22), "world"),
          ((1, "a"), ("aa", 11), "hello"),
          ((2, "b"), ("bb", 22), "hello"),
        ))
        _ <- (A.i.s + B.s.d2.i).Tx(D.s.d1).query.get.map(_ ==> List(
          ((4, "b"), ("bb", 22), "world"),
          ((3, "a"), ("aa", 11), "world"),
          ((2, "b"), ("bb", 22), "hello"),
          ((1, "a"), ("aa", 11), "hello"),
        ))

        _ <- (A.i.d4.s.d3 + B.s.d2.i.d5).Tx(D.s.d1).query.get.map(_ ==> List(
          ((4, "b"), ("bb", 22), "world"),
          ((3, "a"), ("aa", 11), "world"),
          ((2, "b"), ("bb", 22), "hello"),
          ((1, "a"), ("aa", 11), "hello"),
        ))
      } yield ()
    }


    "nested" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i).Tx(D.s_("X")).insert(
          ("a", List(1, 2)),
          ("b", List(1, 2)),
          ("c", Nil),
        ).transact

        _ <- A.s.a1.Bb.*(B.i.a1).Tx(D.s).query.get.map(_ ==> List(
          ("a", List(1, 2), "X"),
          ("b", List(1, 2), "X"),
        ))
        _ <- A.s.d1.Bb.*(B.i.d1).Tx(D.s).query.get.map(_ ==> List(
          ("b", List(2, 1), "X"),
          ("a", List(2, 1), "X"),
        ))

        _ <- A.s.Bb.*(B.i).Tx(D.s_("Y")).insert(
          ("a", List(1, 2)),
          ("b", List(1, 2)),
          ("c", Nil),
        ).transact

        // Mandatory nested
        _ <- A.s.a2.Bb.*(B.i.a1).Tx(D.s.a1).query.get.map(_ ==> List(
          ("a", List(1, 2), "X"),
          ("b", List(1, 2), "X"),
          ("a", List(1, 2), "Y"),
          ("b", List(1, 2), "Y"),
        ))
        _ <- A.s.d2.Bb.*(B.i.a1).Tx(D.s.a1).query.get.map(_ ==> List(
          ("b", List(1, 2), "X"),
          ("a", List(1, 2), "X"),
          ("b", List(1, 2), "Y"),
          ("a", List(1, 2), "Y"),
        ))
        _ <- A.s.a2.Bb.*(B.i.d1).Tx(D.s.d1).query.get.map(_ ==> List(
          ("a", List(2, 1), "Y"),
          ("b", List(2, 1), "Y"),
          ("a", List(2, 1), "X"),
          ("b", List(2, 1), "X"),
        ))
        _ <- A.s.d2.Bb.*(B.i.d1).Tx(D.s.d1).query.get.map(_ ==> List(
          ("b", List(2, 1), "Y"),
          ("a", List(2, 1), "Y"),
          ("b", List(2, 1), "X"),
          ("a", List(2, 1), "X"),
        ))
        _ <- A.s.a1.Bb.*(B.i.d1).Tx(D.s.d2).query.get.map(_ ==> List(
          ("a", List(2, 1), "Y"),
          ("a", List(2, 1), "X"),
          ("b", List(2, 1), "Y"),
          ("b", List(2, 1), "X"),
        ))

        // Optional nested
        _ <- A.s.a2.Bb.*?(B.i.a1).Tx(D.s.a1).query.get.map(_ ==> List(
          ("a", List(1, 2), "X"),
          ("b", List(1, 2), "X"),
          ("c", Nil, "X"),
          ("a", List(1, 2), "Y"),
          ("b", List(1, 2), "Y"),
          ("c", Nil, "Y"),
        ))
        _ <- A.s.d2.Bb.*?(B.i.a1).Tx(D.s.a1).query.get.map(_ ==> List(
          ("c", Nil, "X"),
          ("b", List(1, 2), "X"),
          ("a", List(1, 2), "X"),
          ("c", Nil, "Y"),
          ("b", List(1, 2), "Y"),
          ("a", List(1, 2), "Y"),
        ))
        _ <- A.s.a2.Bb.*?(B.i.d1).Tx(D.s.d1).query.get.map(_ ==> List(
          ("a", List(2, 1), "Y"),
          ("b", List(2, 1), "Y"),
          ("c", Nil, "Y"),
          ("a", List(2, 1), "X"),
          ("b", List(2, 1), "X"),
          ("c", Nil, "X"),
        ))
        _ <- A.s.d2.Bb.*?(B.i.d1).Tx(D.s.d1).query.get.map(_ ==> List(
          ("c", Nil, "Y"),
          ("b", List(2, 1), "Y"),
          ("a", List(2, 1), "Y"),
          ("c", Nil, "X"),
          ("b", List(2, 1), "X"),
          ("a", List(2, 1), "X"),
        ))
        _ <- A.s.a1.Bb.*?(B.i.d1).Tx(D.s.d2).query.get.map(_ ==> List(
          ("a", List(2, 1), "Y"),
          ("a", List(2, 1), "X"),
          ("b", List(2, 1), "Y"),
          ("b", List(2, 1), "X"),
          ("c", Nil, "Y"),
          ("c", Nil, "X"),
        ))
      } yield ()
    }


    "nested complex" - refs { implicit conn =>
      for {
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s_("x1").i_(111).D.s_("x2") + A.i_(222).bool_(true)).insert(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil))),
          ("B", Nil)
        ).transact

        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s_("y1").i_(333).D.s_("y2") + A.i_(444).bool_(false)).insert(
          ("C", List(
            (4, 5, "d", List(40, 41)))),
        ).transact


        // A.s ...............................

        // asc
        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.d1.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.d1.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // B.i ...............................

        // asc
        _ <- A.s.a1.Bb.*?(B.i.a1.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.a1.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.a1.Bb.*?(B.i.d1.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(20, 21)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.d1.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // + previous
        _ <- A.s.d1.Bb.*?(B.i.d1.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(20, 21)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.d1.Bb.*(B.i.d1.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // C.i ...............................

        // asc
        _ <- A.s.a1.Bb.*?(B.i.C.i.a1.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.a1.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.a1.Bb.*?(B.i.C.i.d1.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(20, 21)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.d1.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // + previous
        _ <- A.s.d1.Bb.*?(B.i.d2.C.i.d1.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(20, 21)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.d1.Bb.*(B.i.d2.C.i.d1.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // C.s ...............................

        // asc
        _ <- A.s.a1.Bb.*?(B.i.C.i.s.a1.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.s.a1.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.a1.Bb.*?(B.i.C.i.s.d1.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(20, 21)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.s.d1.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // + previous
        _ <- A.s.d1.Bb.*?(B.i.d3.C.i.d2.s.d1.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(20, 21)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.d1.Bb.*(B.i.d3.C.i.d2.s.d1.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(10, 11))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // D.i ...............................

        // asc
        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.a1))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.s.Dd.*(D.i.a1))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.a1.Bb.*?(B.i.C.i.s.Dd.*?(D.i.d1))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(11, 10)),
            (2, 6, "b", List(21, 20)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.a1.Bb.*(B.i.C.i.s.Dd.*(D.i.d1))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(11, 10)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // + previous
        _ <- A.s.d1.Bb.*?(B.i.d3.C.i.d2.s.d1.Dd.*?(D.i.d1))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.d1.Bb.*(B.i.d3.C.i.d2.s.d1.Dd.*(D.i.d1))
          .Tx(C.s.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // Tx.C.s ..............................

        // asc
        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.a1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.a1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.d1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.d1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
        ))

        // + previous
        // Note that attributes before nested and after (in tx meta data) are on the
        // same level. Sort indexes should therefore increase across these attributes.
        // That's why we start with `A.s.d2` which should be sorted after `Tx.C.s.d1`
        _ <- A.s.d2.Bb.*?(B.i.d3.C.i.d2.s.d1.Dd.*?(D.i.d1))
          .Tx(C.s.d1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.d2.Bb.*(B.i.d3.C.i.d2.s.d1.Dd.*(D.i.d1))
          .Tx(C.s.d1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // Tx.C.i ..............................

        // asc
        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.a1.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.a1.D.s + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.d1.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.d1.i.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
        ))

        // + previous
        // Top level A.s is now asc and sorted after Tx.C.i that is also considered on top level.
        _ <- A.s.a2.Bb.*?(B.i.d3.C.i.d2.s.d1.Dd.*?(D.i.d1))
          .Tx(C.s.i.d1.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.a2.Bb.*(B.i.d3.C.i.d2.s.d1.Dd.*(D.i.d1))
          .Tx(C.s.i.d1.D.s + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // Tx.C.D.s ...............................

        // asc
        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s.a1 + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s.a1 + A.i.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        // desc
        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s.d1 + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s.d1 + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
        ))

        // + previous
        _ <- A.s.a3.Bb.*?(B.i.d3.C.i.d2.s.d1.Dd.*?(D.i.d1))
          .Tx(C.s.i.d2.D.s.d1 + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.a3.Bb.*(B.i.d3.C.i.d2.s.d1.Dd.*(D.i.d1))
          .Tx(C.s.i.d2.D.s.d1 + A.i.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))


        // Tx.A.int/s ...............................

        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.a1.bool).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.bool.d1).query.get.map(_ ==> List(
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
        ))

        _ <- A.s.Bb.*?(B.i.C.i.s.Dd.*?(D.i))
          .Tx(C.s.i.D.s + A.i.bool.a1).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(20, 21)),
            (3, 7, "c", Nil)),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.Bb.*(B.i.C.i.s.Dd.*(D.i))
          .Tx(C.s.i.D.s + A.i.d1.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(40, 41))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (1, 5, "a", List(10, 11)),
            (2, 6, "b", List(21, 20))),
            ("x1", 111, "x2"), (222, true)),
        ))

        // + previous
        _ <- A.s.a4.Bb.*?(B.i.d3.C.i.d2.s.d1.Dd.*?(D.i.d1))
          .Tx(C.s.i.d3.D.s.d2 + A.i.d1.bool).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (3, 7, "c", Nil),
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
          ("B", Nil,
            ("x1", 111, "x2"), (222, true)),
        ))
        _ <- A.s.a4.Bb.*(B.i.d3.C.i.d2.s.d1.Dd.*(D.i.d1))
          .Tx(C.s.i.d3.D.s.d2 + A.i.bool.a1).query.get.map(_ ==> List(
          ("C", List(
            (4, 5, "d", List(41, 40))),
            ("y1", 333, "y2"), (444, false)),
          ("A", List(
            (2, 6, "b", List(21, 20)),
            (1, 5, "a", List(11, 10))),
            ("x1", 111, "x2"), (222, true)),
        ))
      } yield ()
    }
  }
}
