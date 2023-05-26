package molecule.coreTests.test.sort

import molecule.coreTests.api.ApiAsyncImplicits
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import molecule.core.spi.SpiAsync
import utest._


trait SortComposites extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>


  override lazy val tests = Tests {

    "1 + 1" - refs { implicit conn =>
      for {
        _ <- (A.s + B.i).insert(
          ("A", 1),
          ("A", 2),
          ("B", 1),
          ("B", 2),
        ).transact

        _ <- (A.s.a1 + B.i.a2).query.get.map(_ ==> List(
          ("A", 1),
          ("A", 2),
          ("B", 1),
          ("B", 2),
        ))
        _ <- (A.s.a1 + B.i.d2).query.get.map(_ ==> List(
          ("A", 2),
          ("A", 1),
          ("B", 2),
          ("B", 1),
        ))
        _ <- (A.s.d1 + B.i.a2).query.get.map(_ ==> List(
          ("B", 1),
          ("B", 2),
          ("A", 1),
          ("A", 2),
        ))
        _ <- (A.s.d1 + B.i.d2).query.get.map(_ ==> List(
          ("B", 2),
          ("B", 1),
          ("A", 2),
          ("A", 1),
        ))

        _ <- (A.s.a2 + B.i.a1).query.get.map(_ ==> List(
          ("A", 1),
          ("B", 1),
          ("A", 2),
          ("B", 2),
        ))
        _ <- (A.s.d2 + B.i.a1).query.get.map(_ ==> List(
          ("B", 1),
          ("A", 1),
          ("B", 2),
          ("A", 2),
        ))
        _ <- (A.s.a2 + B.i.d1).query.get.map(_ ==> List(
          ("A", 2),
          ("B", 2),
          ("A", 1),
          ("B", 1),
        ))
        _ <- (A.s.d2 + B.i.d1).query.get.map(_ ==> List(
          ("B", 2),
          ("A", 2),
          ("B", 1),
          ("A", 1),
        ))
      } yield ()
    }


    "1 + 2" - refs { implicit conn =>
      for {
        _ <- (A.s + B.i.s).insert(
          ("A", (1, "x")),
          ("A", (2, "y")),
          ("B", (1, "x")),
          ("B", (2, "y")),
        ).transact

        _ <- (A.s.a1 + B.i.a2.s).query.get.map(_ ==> List(
          ("A", (1, "x")),
          ("A", (2, "y")),
          ("B", (1, "x")),
          ("B", (2, "y")),
        ))
        _ <- (A.s.a1 + B.i.s.d2).query.get.map(_ ==> List(
          ("A", (2, "y")),
          ("A", (1, "x")),
          ("B", (2, "y")),
          ("B", (1, "x")),
        ))
        _ <- (A.s.d1 + B.i.s.a2).query.get.map(_ ==> List(
          ("B", (1, "x")),
          ("B", (2, "y")),
          ("A", (1, "x")),
          ("A", (2, "y")),
        ))
        _ <- (A.s.d1 + B.i.d2.s).query.get.map(_ ==> List(
          ("B", (2, "y")),
          ("B", (1, "x")),
          ("A", (2, "y")),
          ("A", (1, "x")),
        ))

        _ <- (A.s.a2 + B.i.a1.s).query.get.map(_ ==> List(
          ("A", (1, "x")),
          ("B", (1, "x")),
          ("A", (2, "y")),
          ("B", (2, "y")),
        ))
        _ <- (A.s.d2 + B.i.s.a1).query.get.map(_ ==> List(
          ("B", (1, "x")),
          ("A", (1, "x")),
          ("B", (2, "y")),
          ("A", (2, "y")),
        ))
        _ <- (A.s.a2 + B.i.s.d1).query.get.map(_ ==> List(
          ("A", (2, "y")),
          ("B", (2, "y")),
          ("A", (1, "x")),
          ("B", (1, "x")),
        ))
        _ <- (A.s.d2 + B.i.d1.s).query.get.map(_ ==> List(
          ("B", (2, "y")),
          ("A", (2, "y")),
          ("B", (1, "x")),
          ("A", (1, "x")),
        ))
      } yield ()
    }


    "2 + 2" - refs { implicit conn =>
      for {
        _ <- (A.s.i + B.i.s).insert(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
          (("B", 4), (2, "y")),
        ).transact

        _ <- (A.s.a1.i + B.i.a2.s).query.get.map(_ ==> List(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
          (("B", 4), (2, "y")),
        ))
        _ <- (A.s.i.a1 + B.i.s.d2).query.get.map(_ ==> List(
          (("A", 3), (2, "y")),
          (("A", 3), (1, "x")),
          (("B", 4), (2, "y")),
          (("B", 4), (1, "x")),
        ))
        _ <- (A.s.i.d1 + B.i.s.a2).query.get.map(_ ==> List(
          (("B", 4), (1, "x")),
          (("B", 4), (2, "y")),
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
        ))
        _ <- (A.s.d1.i + B.i.d2.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y")),
          (("B", 4), (1, "x")),
          (("A", 3), (2, "y")),
          (("A", 3), (1, "x")),
        ))

        _ <- (A.s.a2.i + B.i.a1.s).query.get.map(_ ==> List(
          (("A", 3), (1, "x")),
          (("B", 4), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (2, "y")),
        ))
        _ <- (A.s.i.d2 + B.i.s.a1).query.get.map(_ ==> List(
          (("B", 4), (1, "x")),
          (("A", 3), (1, "x")),
          (("B", 4), (2, "y")),
          (("A", 3), (2, "y")),
        ))
        _ <- (A.s.i.a2 + B.i.s.d1).query.get.map(_ ==> List(
          (("A", 3), (2, "y")),
          (("B", 4), (2, "y")),
          (("A", 3), (1, "x")),
          (("B", 4), (1, "x")),
        ))
        _ <- (A.s.d2.i + B.i.d1.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
          (("A", 3), (1, "x")),
        ))
      } yield ()
    }


    "2 + 2.tx(2)" - refs { implicit conn =>
      for {
        _ <- (A.s.i + B.i.s).Tx(D.i_(7).s_("hello")).insert(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
          (("B", 4), (1, "x")),
        ).transact

        _ <- (A.s.i + B.i.s).Tx(D.i_(8).s_("world")).insert(
          (("B", 4), (2, "y")),
        ).transact

        _ <- (A.s.a2.i + B.i.a3.s).Tx(D.i.a1.s).query.get.map(_ ==> List(
          (("A", 3), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))
        _ <- (A.s.i.a2 + B.i.s.d3).Tx(D.i.s.a1).query.get.map(_ ==> List(
          (("A", 3), (2, "y"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))
        _ <- (A.s.i.d2 + B.i.s.a3).Tx(D.i.s.a1).query.get.map(_ ==> List(
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))
        _ <- (A.s.d2.i + B.i.d3.s).Tx(D.i.a1.s).query.get.map(_ ==> List(
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (2, "y"), 8, "world"),
        ))

        _ <- (A.s.a3.i + B.i.a2.s).Tx(D.i.d1.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
        ))
        _ <- (A.s.i.d3 + B.i.s.a2).Tx(D.i.s.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("A", 3), (2, "y"), 7, "hello"),
        ))
        _ <- (A.s.i.a3 + B.i.s.d2).Tx(D.i.s.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
        ))
        _ <- (A.s.d3.i + B.i.d2.s).Tx(D.i.d1.s).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), 8, "world"),
          (("A", 3), (2, "y"), 7, "hello"),
          (("B", 4), (1, "x"), 7, "hello"),
          (("A", 3), (1, "x"), 7, "hello"),
        ))
      } yield ()
    }


    "2 + 2.tx(2 + 1)" - refs { implicit conn =>
      for {
        _ <- (A.s.i + B.i.s).Tx(D.i_(7).s_("hello") + E.i_(30)).insert(
          (("A", 3), (1, "x")),
          (("A", 3), (2, "y")),
        ).transact

        _ <- (A.s.i + B.i.s).Tx(D.i_(7).s_("hello") + E.i_(40)).insert(
          (("B", 4), (1, "x")),
        ).transact

        _ <- (A.s.i + B.i.s).Tx(D.i_(8).s_("world") + E.i_(40)).insert(
          (("B", 4), (2, "y")),
        ).transact

        _ <- (A.s.a3.i + B.i.a4.s).Tx(D.i.a2.s + E.i.a1).query.get.map(_ ==> List(
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))
        _ <- (A.s.i.a3 + B.i.s.d4).Tx(D.i.s.a2 + E.i.a1).query.get.map(_ ==> List(
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))
        _ <- (A.s.i.d3 + B.i.s.a4).Tx(D.i.s.a2 + E.i.a1).query.get.map(_ ==> List(
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))
        _ <- (A.s.d3.i + B.i.d4.s).Tx(D.i.a2.s + E.i.a1).query.get.map(_ ==> List(
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("B", 4), (2, "y"), (8, "world"), 40),
        ))

        _ <- (A.s.a4.i + B.i.a3.s).Tx(D.i.d2.s + E.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
        ))
        _ <- (A.s.i.d4 + B.i.s.a3).Tx(D.i.s.d2 + E.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (1, "x"), (7, "hello"), 30),
          (("A", 3), (2, "y"), (7, "hello"), 30),
        ))
        _ <- (A.s.i.a4 + B.i.s.d3).Tx(D.i.s.d2 + E.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
        ))
        _ <- (A.s.d4.i + B.i.d3.s).Tx(D.i.d2.s + E.i.d1).query.get.map(_ ==> List(
          (("B", 4), (2, "y"), (8, "world"), 40),
          (("B", 4), (1, "x"), (7, "hello"), 40),
          (("A", 3), (2, "y"), (7, "hello"), 30),
          (("A", 3), (1, "x"), (7, "hello"), 30),
        ))
      } yield ()
    }
  }
}
