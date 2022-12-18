package molecule.db.datomic.test.sort

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object SortTxMetaData extends DatomicTestSuite {


  lazy val tests = Tests {

    "flat" - refs { implicit conn =>
      // 2 transactions with different tx meta data
      Ns.i.Tx(R1.s_("A")).insert(1, 2).transact
      Ns.i.Tx(R1.s_("B")).insert(1, 2).transact

      Ns.i.a2.Tx(R1.s.a1).query.get ==> List(
        (1, "A"),
        (2, "A"),
        (1, "B"),
        (2, "B"),
      )
      Ns.i.d2.Tx(R1.s.a1).query.get ==> List(
        (2, "A"),
        (1, "A"),
        (2, "B"),
        (1, "B"),
      )
      Ns.i.a2.Tx(R1.s.d1).query.get ==> List(
        (1, "B"),
        (2, "B"),
        (1, "A"),
        (2, "A"),
      )
      Ns.i.d2.Tx(R1.s.d1).query.get ==> List(
        (2, "B"),
        (1, "B"),
        (2, "A"),
        (1, "A"),
      )

      Ns.i.a1.Tx(R1.s.a2).query.get ==> List(
        (1, "A"),
        (1, "B"),
        (2, "A"),
        (2, "B"),
      )
      Ns.i.d1.Tx(R1.s.a2).query.get ==> List(
        (2, "A"),
        (2, "B"),
        (1, "A"),
        (1, "B"),
      )
      Ns.i.a1.Tx(R1.s.d2).query.get ==> List(
        (1, "B"),
        (1, "A"),
        (2, "B"),
        (2, "A"),
      )
      Ns.i.d1.Tx(R1.s.d2).query.get ==> List(
        (2, "B"),
        (2, "A"),
        (1, "B"),
        (1, "A"),
      )
    }


    "composites" - refs { implicit conn =>
      (Ns.i.s + R1.s.i).Tx(R3.s_("hello")).insert(
        ((1, "a"), ("aa", 11)),
        ((2, "b"), ("bb", 22))
      ).transact

      (Ns.i.s + R1.s.i).Tx(R3.s_("world")).insert(
        ((3, "a"), ("aa", 11)),
        ((4, "b"), ("bb", 22))
      ).transact

      (Ns.i.a2.s + R1.s.i).Tx(R3.s.a1).query.get ==> List(
        ((1, "a"), ("aa", 11), "hello"),
        ((2, "b"), ("bb", 22), "hello"),
        ((3, "a"), ("aa", 11), "world"),
        ((4, "b"), ("bb", 22), "world"),
      )
      (Ns.i.d2.s + R1.s.i).Tx(R3.s.a1).query.get ==> List(
        ((2, "b"), ("bb", 22), "hello"),
        ((1, "a"), ("aa", 11), "hello"),
        ((4, "b"), ("bb", 22), "world"),
        ((3, "a"), ("aa", 11), "world"),
      )
      (Ns.i.s + R1.s.a2.i).Tx(R3.s.a1).query.get ==> List(
        ((1, "a"), ("aa", 11), "hello"),
        ((2, "b"), ("bb", 22), "hello"),
        ((3, "a"), ("aa", 11), "world"),
        ((4, "b"), ("bb", 22), "world"),
      )
      (Ns.i.s + R1.s.d2.i).Tx(R3.s.a1).query.get ==> List(
        ((2, "b"), ("bb", 22), "hello"),
        ((1, "a"), ("aa", 11), "hello"),
        ((4, "b"), ("bb", 22), "world"),
        ((3, "a"), ("aa", 11), "world"),
      )

      (Ns.i.a2.s + R1.s.i).Tx(R3.s.d1).query.get ==> List(
        ((3, "a"), ("aa", 11), "world"),
        ((4, "b"), ("bb", 22), "world"),
        ((1, "a"), ("aa", 11), "hello"),
        ((2, "b"), ("bb", 22), "hello"),
      )
      (Ns.i.d2.s + R1.s.i).Tx(R3.s.d1).query.get ==> List(
        ((4, "b"), ("bb", 22), "world"),
        ((3, "a"), ("aa", 11), "world"),
        ((2, "b"), ("bb", 22), "hello"),
        ((1, "a"), ("aa", 11), "hello"),
      )
      (Ns.i.s + R1.s.a2.i).Tx(R3.s.d1).query.get ==> List(
        ((3, "a"), ("aa", 11), "world"),
        ((4, "b"), ("bb", 22), "world"),
        ((1, "a"), ("aa", 11), "hello"),
        ((2, "b"), ("bb", 22), "hello"),
      )
      (Ns.i.s + R1.s.d2.i).Tx(R3.s.d1).query.get ==> List(
        ((4, "b"), ("bb", 22), "world"),
        ((3, "a"), ("aa", 11), "world"),
        ((2, "b"), ("bb", 22), "hello"),
        ((1, "a"), ("aa", 11), "hello"),
      )

      (Ns.i.d4.s.d3 + R1.s.d2.i.d5).Tx(R3.s.d1).query.get ==> List(
        ((4, "b"), ("bb", 22), "world"),
        ((3, "a"), ("aa", 11), "world"),
        ((2, "b"), ("bb", 22), "hello"),
        ((1, "a"), ("aa", 11), "hello"),
      )
    }


    "nested" - refs { implicit conn =>
      Ns.s.Rs1.*(R1.i).Tx(R3.s_("X")).insert(
        ("a", List(1, 2)),
        ("b", List(1, 2)),
        ("c", Nil),
      ).transact

      Ns.s.a1.Rs1.*(R1.i.a1).Tx(R3.s).query.get ==> List(
        ("a", List(1, 2), "X"),
        ("b", List(1, 2), "X"),
      )
      Ns.s.d1.Rs1.*(R1.i.d1).Tx(R3.s).query.get ==> List(
        ("b", List(2, 1), "X"),
        ("a", List(2, 1), "X"),
      )

      Ns.s.Rs1.*(R1.i).Tx(R3.s_("Y")).insert(
        ("a", List(1, 2)),
        ("b", List(1, 2)),
        ("c", Nil),
      ).transact

      // Mandatory nested
      Ns.s.a2.Rs1.*(R1.i.a1).Tx(R3.s.a1).query.get ==> List(
        ("a", List(1, 2), "X"),
        ("b", List(1, 2), "X"),
        ("a", List(1, 2), "Y"),
        ("b", List(1, 2), "Y"),
      )
      Ns.s.d2.Rs1.*(R1.i.a1).Tx(R3.s.a1).query.get ==> List(
        ("b", List(1, 2), "X"),
        ("a", List(1, 2), "X"),
        ("b", List(1, 2), "Y"),
        ("a", List(1, 2), "Y"),
      )
      Ns.s.a2.Rs1.*(R1.i.d1).Tx(R3.s.d1).query.get ==> List(
        ("a", List(2, 1), "Y"),
        ("b", List(2, 1), "Y"),
        ("a", List(2, 1), "X"),
        ("b", List(2, 1), "X"),
      )
      Ns.s.d2.Rs1.*(R1.i.d1).Tx(R3.s.d1).query.get ==> List(
        ("b", List(2, 1), "Y"),
        ("a", List(2, 1), "Y"),
        ("b", List(2, 1), "X"),
        ("a", List(2, 1), "X"),
      )
      Ns.s.a1.Rs1.*(R1.i.d1).Tx(R3.s.d2).query.get ==> List(
        ("a", List(2, 1), "Y"),
        ("a", List(2, 1), "X"),
        ("b", List(2, 1), "Y"),
        ("b", List(2, 1), "X"),
      )

      // Optional nested
      Ns.s.a2.Rs1.*?(R1.i.a1).Tx(R3.s.a1).query.get ==> List(
        ("a", List(1, 2), "X"),
        ("b", List(1, 2), "X"),
        ("c", Nil, "X"),
        ("a", List(1, 2), "Y"),
        ("b", List(1, 2), "Y"),
        ("c", Nil, "Y"),
      )
      Ns.s.d2.Rs1.*?(R1.i.a1).Tx(R3.s.a1).query.get ==> List(
        ("c", Nil, "X"),
        ("b", List(1, 2), "X"),
        ("a", List(1, 2), "X"),
        ("c", Nil, "Y"),
        ("b", List(1, 2), "Y"),
        ("a", List(1, 2), "Y"),
      )
      Ns.s.a2.Rs1.*?(R1.i.d1).Tx(R3.s.d1).query.get ==> List(
        ("a", List(2, 1), "Y"),
        ("b", List(2, 1), "Y"),
        ("c", Nil, "Y"),
        ("a", List(2, 1), "X"),
        ("b", List(2, 1), "X"),
        ("c", Nil, "X"),
      )
      Ns.s.d2.Rs1.*?(R1.i.d1).Tx(R3.s.d1).query.get ==> List(
        ("c", Nil, "Y"),
        ("b", List(2, 1), "Y"),
        ("a", List(2, 1), "Y"),
        ("c", Nil, "X"),
        ("b", List(2, 1), "X"),
        ("a", List(2, 1), "X"),
      )
      Ns.s.a1.Rs1.*?(R1.i.d1).Tx(R3.s.d2).query.get ==> List(
        ("a", List(2, 1), "Y"),
        ("a", List(2, 1), "X"),
        ("b", List(2, 1), "Y"),
        ("b", List(2, 1), "X"),
        ("c", Nil, "Y"),
        ("c", Nil, "X"),
      )
    }


    "nested complex" - refs { implicit conn =>
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s_("x1").i_(111).R3.s_("x2") + Ns.i_(222).b_(true)).insert(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(20, 21)),
          (3, 7, "c", Nil))),
        ("B", Nil)
      ).transact

      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s_("y1").i_(333).R3.s_("y2") + Ns.i_(444).b_(false)).insert(
        ("C", List(
          (4, 5, "d", List(40, 41)))),
      ).transact


      // Ns.s ...............................

      // asc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.d1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.d1.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
      )


      // R1.i ...............................

      // asc
      Ns.s.a1.Rs1.*?(R1.i.a1.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.a1.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.a1.Rs1.*?(R1.i.d1.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.d1.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(10, 11))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // + previous
      Ns.s.d1.Rs1.*?(R1.i.d1.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.d1.Rs1.*(R1.i.d1.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(10, 11))),
          ("x1", 111, "x2"), (222, true)),
      )


      // R2.i ...............................

      // asc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.a1.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.a1.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.d1.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.d1.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(10, 11))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // + previous
      Ns.s.d1.Rs1.*?(R1.i.d2.R2.i.d1.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.d1.Rs1.*(R1.i.d2.R2.i.d1.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(10, 11))),
          ("x1", 111, "x2"), (222, true)),
      )


      // R2.s ...............................

      // asc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.s.a1.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.s.a1.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.s.d1.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.s.d1.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(10, 11))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // + previous
      Ns.s.d1.Rs1.*?(R1.i.d3.R2.i.d2.s.d1.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.d1.Rs1.*(R1.i.d3.R2.i.d2.s.d1.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(10, 11))),
          ("x1", 111, "x2"), (222, true)),
      )


      // R3.i ...............................

      // asc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.a1))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.a1))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(20, 21))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.a1.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i.d1))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a1.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i.d1))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(11, 10)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(41, 40))),
          ("y1", 333, "y2"), (444, false)),
      )

      // + previous
      Ns.s.d1.Rs1.*?(R1.i.d3.R2.i.d2.s.d1.Rs3.*?(R3.i.d1))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.d1.Rs1.*(R1.i.d3.R2.i.d2.s.d1.Rs3.*(R3.i.d1))
        .Tx(R2.s.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(41, 40))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(11, 10))),
          ("x1", 111, "x2"), (222, true)),
      )


      // Tx.R2.s ..............................

      // asc
      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.a1.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.a1.i.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.d1.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.d1.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
      )

      // + previous
      // Note that attributes before nested and after (in tx meta data) are on the
      // same level. Sort indexes should therefore increase across these attributes.
      // That's why we start with `Ns.s.d2` which should be sorted after `Tx.R2.s.d1`
      Ns.s.d2.Rs1.*?(R1.i.d3.R2.i.d2.s.d1.Rs3.*?(R3.i.d1))
        .Tx(R2.s.d1.i.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.d2.Rs1.*(R1.i.d3.R2.i.d2.s.d1.Rs3.*(R3.i.d1))
        .Tx(R2.s.d1.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(41, 40))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(11, 10))),
          ("x1", 111, "x2"), (222, true)),
      )


      // Tx.R2.i ..............................

      // asc
      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.a1.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.a1.R3.s + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.d1.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.d1.i.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
      )

      // + previous
      // Top level Ns.s is now asc and sorted after Tx.R2.i that is also considered on top level.
      Ns.s.a2.Rs1.*?(R1.i.d3.R2.i.d2.s.d1.Rs3.*?(R3.i.d1))
        .Tx(R2.s.i.d1.R3.s + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a2.Rs1.*(R1.i.d3.R2.i.d2.s.d1.Rs3.*(R3.i.d1))
        .Tx(R2.s.i.d1.R3.s + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(41, 40))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(11, 10))),
          ("x1", 111, "x2"), (222, true)),
      )


      // Tx.R2.R3.s ...............................

      // asc
      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s.a1 + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s.a1 + Ns.i.b).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      // desc
      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s.d1 + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s.d1 + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
      )

      // + previous
      Ns.s.a3.Rs1.*?(R1.i.d3.R2.i.d2.s.d1.Rs3.*?(R3.i.d1))
        .Tx(R2.s.i.d2.R3.s.d1 + Ns.i.b).query.get ==> List(
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
      )
      Ns.s.a3.Rs1.*(R1.i.d3.R2.i.d2.s.d1.Rs3.*(R3.i.d1))
        .Tx(R2.s.i.d2.R3.s.d1 + Ns.i.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(41, 40))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(11, 10))),
          ("x1", 111, "x2"), (222, true)),
      )


      // Tx.Ns.int/s ...............................

      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.a1.b).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b.d1).query.get ==> List(
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
      )

      Ns.s.Rs1.*?(R1.i.R2.i.s.Rs3.*?(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.b.a1).query.get ==> List(
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
      )
      Ns.s.Rs1.*(R1.i.R2.i.s.Rs3.*(R3.i))
        .Tx(R2.s.i.R3.s + Ns.i.d1.b).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(40, 41))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (1, 5, "a", List(10, 11)),
          (2, 6, "b", List(21, 20))),
          ("x1", 111, "x2"), (222, true)),
      )

      // + previous
      Ns.s.a4.Rs1.*?(R1.i.d3.R2.i.d2.s.d1.Rs3.*?(R3.i.d1))
        .Tx(R2.s.i.d3.R3.s.d2 + Ns.i.d1.b).query.get ==> List(
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
      )
      Ns.s.a4.Rs1.*(R1.i.d3.R2.i.d2.s.d1.Rs3.*(R3.i.d1))
        .Tx(R2.s.i.d3.R3.s.d2 + Ns.i.b.a1).query.get ==> List(
        ("C", List(
          (4, 5, "d", List(41, 40))),
          ("y1", 333, "y2"), (444, false)),
        ("A", List(
          (2, 6, "b", List(21, 20)),
          (1, 5, "a", List(11, 10))),
          ("x1", 111, "x2"), (222, true)),
      )
    }
  }
}
