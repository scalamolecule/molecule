package molecule.db.datomic.test.sort

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object SortComposites extends DatomicTestSuite {


  lazy val tests = Tests {

    "1 + 1" - refs { implicit conn =>
      for {
        _ <- (Ns.s + R1.i).insert(
          ("A", 1),
          ("A", 2),
          ("B", 1),
          ("B", 2),
        ).transact

        _ <- (Ns.s.a1 + R1.i.a2).query.get.map(_ ==> List(
          ("A", 1),
          ("A", 2),
          ("B", 1),
          ("B", 2),
        ))
        _ <- (Ns.s.a1 + R1.i.d2).query.get.map(_ ==> List(
          ("A", 2),
          ("A", 1),
          ("B", 2),
          ("B", 1),
        ))
        _ <- (Ns.s.d1 + R1.i.a2).query.get.map(_ ==> List(
          ("B", 1),
          ("B", 2),
          ("A", 1),
          ("A", 2),
        ))
        _ <- (Ns.s.d1 + R1.i.d2).query.get.map(_ ==> List(
          ("B", 2),
          ("B", 1),
          ("A", 2),
          ("A", 1),
        ))

        _ <- (Ns.s.a2 + R1.i.a1).query.get.map(_ ==> List(
          ("A", 1),
          ("B", 1),
          ("A", 2),
          ("B", 2),
        ))
        _ <- (Ns.s.d2 + R1.i.a1).query.get.map(_ ==> List(
          ("B", 1),
          ("A", 1),
          ("B", 2),
          ("A", 2),
        ))
        _ <- (Ns.s.a2 + R1.i.d1).query.get.map(_ ==> List(
          ("A", 2),
          ("B", 2),
          ("A", 1),
          ("B", 1),
        ))
        _ <- (Ns.s.d2 + R1.i.d1).query.get.map(_ ==> List(
          ("B", 2),
          ("A", 2),
          ("B", 1),
          ("A", 1),
        ))
      } yield ()
    }


    "1 + 2" - refs { implicit conn =>
      for {
        _ <- (Ns.s + R1.i.s).insert(
          ("A", (1, "x")),
          ("A", (2, "y")),
          ("B", (1, "x")),
          ("B", (2, "y")),
        ).transact

        _ <- (Ns.s.a1 + R1.i.a2.s).query.get.map(_ ==> List(
          ("A", (1, "x")),
          ("A", (2, "y")),
          ("B", (1, "x")),
          ("B", (2, "y")),
        ))
        _ <- (Ns.s.a1 + R1.i.s.d2).query.get.map(_ ==> List(
          ("A", (2, "y")),
          ("A", (1, "x")),
          ("B", (2, "y")),
          ("B", (1, "x")),
        ))
        _ <- (Ns.s.d1 + R1.i.s.a2).query.get.map(_ ==> List(
          ("B", (1, "x")),
          ("B", (2, "y")),
          ("A", (1, "x")),
          ("A", (2, "y")),
        ))
        _ <- (Ns.s.d1 + R1.i.d2.s).query.get.map(_ ==> List(
          ("B", (2, "y")),
          ("B", (1, "x")),
          ("A", (2, "y")),
          ("A", (1, "x")),
        ))

        _ <- (Ns.s.a2 + R1.i.a1.s).query.get.map(_ ==> List(
          ("A", (1, "x")),
          ("B", (1, "x")),
          ("A", (2, "y")),
          ("B", (2, "y")),
        ))
        _ <- (Ns.s.d2 + R1.i.s.a1).query.get.map(_ ==> List(
          ("B", (1, "x")),
          ("A", (1, "x")),
          ("B", (2, "y")),
          ("A", (2, "y")),
        ))
        _ <- (Ns.s.a2 + R1.i.s.d1).query.get.map(_ ==> List(
          ("A", (2, "y")),
          ("B", (2, "y")),
          ("A", (1, "x")),
          ("B", (1, "x")),
        ))
        _ <- (Ns.s.d2 + R1.i.d1.s).query.get.map(_ ==> List(
          ("B", (2, "y")),
          ("A", (2, "y")),
          ("B", (1, "x")),
          ("A", (1, "x")),
        ))
      } yield ()
    }


    "2 + 2" - refs { implicit conn =>
      for {
        _ <- (Ns.s.i + R1.i.s).insert(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
          (("B", 4), (2, "y")),
        ).transact

        _ <- (Ns.s.a1.i + R1.i.a2.s).query.get.map(_ ==> List(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
          (("B", 4), (2, "y")),
        ))
        _ <- (Ns.s.i.a1 + R1.i.s.d2).query.get.map(_ ==> List(
          (("A", 3), (2, "y")),
          (("A", 3), (1, "x")),
          (("B", 4), (2, "y")),
          (("B", 4), (1, "x")),
        ))
        _ <- (Ns.s.i.d1 + R1.i.s.a2).query.get.map(_ ==> List(
          (("B", 4), (1, "x")),
          (("B", 4), (2, "y")),
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
        ))
        _ <- (Ns.s.d1.i + R1.i.d2.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y")),
          (("B", 4), (1, "x")),
          (("A", 3), (2, "y")),
          (("A", 3), (1, "x")),
        ))

        _ <- (Ns.s.a2.i + R1.i.a1.s).query.get.map(_ ==> List(
          (("A", 3), (1, "x")),
          (("B", 4), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (2, "y")),
        ))
        _ <- (Ns.s.i.d2 + R1.i.s.a1).query.get.map(_ ==> List(
          (("B", 4), (1, "x")),
          (("A", 3), (1, "x")),
          (("B", 4), (2, "y")),
          (("A", 3), (2, "y")),
        ))
        _ <- (Ns.s.i.a2 + R1.i.s.d1).query.get.map(_ ==> List(
          (("A", 3), (2, "y")),
          (("B", 4), (2, "y")),
          (("A", 3), (1, "x")),
          (("B", 4), (1, "x")),
        ))
        _ <- (Ns.s.d2.i + R1.i.d1.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
          (("A", 3), (1, "x")),
        ))
      } yield ()
    }


    "2 + 2.tx(2)" - refs { implicit conn =>
      for {
        _ <- (Ns.s.i + R1.i.s).Tx(R3.i_(7).s_("hello")).insert(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
        ).transact

        _ <- (Ns.s.i + R1.i.s).Tx(R3.i_(8).s_("world")).insert(
          (("B", 4), (2, "y")),
        ).transact

        _ <- (Ns.s.a2.i + R1.i.a3.s).Tx(R3.i.a1.s).query.get.map(_ ==> List(
          (("A", 3), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))
        _ <- (Ns.s.i.a2 + R1.i.s.d3).Tx(R3.i.s.a1).query.get.map(_ ==> List(
          (("A", 3), (2, "y"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))
        _ <- (Ns.s.i.d2 + R1.i.s.a3).Tx(R3.i.s.a1).query.get.map(_ ==> List(
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))
        _ <- (Ns.s.d2.i + R1.i.d3.s).Tx(R3.i.a1.s).query.get.map(_ ==> List(
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))

        _ <- (Ns.s.a3.i + R1.i.a2.s).Tx(R3.i.d1.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
        ))
        _ <- (Ns.s.i.d3 + R1.i.s.a2).Tx(R3.i.s.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
        ))
        _ <- (Ns.s.i.a3 + R1.i.s.d2).Tx(R3.i.s.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
        ))
        _ <- (Ns.s.d3.i + R1.i.d2.s).Tx(R3.i.d1.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
        ))
      } yield ()
    }


    "2 + 2.tx(2 + 1)" - refs { implicit conn =>
      for {
        _ <- (Ns.s.i + R1.i.s).Tx(R3.i_(7).s_("hello") + R4.i_(30)).insert(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
        ).transact

        _ <- (Ns.s.i + R1.i.s).Tx(R3.i_(7).s_("hello") + R4.i_(40)).insert(
          (("B", 4), (1, "x")),
        ).transact

        _ <- (Ns.s.i + R1.i.s).Tx(R3.i_(8).s_("world") + R4.i_(40)).insert(
          (("B", 4), (2, "y")),
        ).transact

        _ <- (Ns.s.a3.i + R1.i.a4.s).Tx(R3.i.a2.s + R4.i.a1).query.get.map(_ ==> List(
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))
        _ <- (Ns.s.i.a3 + R1.i.s.d4).Tx(R3.i.s.a2 + R4.i.a1).query.get.map(_ ==> List(
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))
        _ <- (Ns.s.i.d3 + R1.i.s.a4).Tx(R3.i.s.a2 + R4.i.a1).query.get.map(_ ==> List(
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))
        _ <- (Ns.s.d3.i + R1.i.d4.s).Tx(R3.i.a2.s + R4.i.a1).query.get.map(_ ==> List(
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))

        _ <- (Ns.s.a4.i + R1.i.a3.s).Tx(R3.i.d2.s + R4.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
        ))
        _ <- (Ns.s.i.d4 + R1.i.s.a3).Tx(R3.i.s.d2 + R4.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
        ))
        _ <- (Ns.s.i.a4 + R1.i.s.d3).Tx(R3.i.s.d2 + R4.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
        ))
        _ <- (Ns.s.d4.i + R1.i.d3.s).Tx(R3.i.d2.s + R4.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
        ))
      } yield ()
    }
  }
}
