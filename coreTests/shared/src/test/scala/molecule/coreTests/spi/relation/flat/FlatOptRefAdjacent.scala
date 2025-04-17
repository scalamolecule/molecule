package molecule.coreTests.spi.relation.flat

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs._
import molecule.coreTests.setup._


case class FlatOptRefAdjacent(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Basic adjacent optional refs" - refs { implicit conn =>
    for {
      _ <- A.i
        .B.?(B.i.s)
        .C.?(C.s.i).insert(List(
          (1, None, None),
          (2, Some((20, "b")), None),
          (3, None, Some(("c", 300))),
          (4, Some((40, "b")), Some(("c", 400))),
        )).transact

      _ <- A.i.a1
        .B.?(B.i.s)
        .C.?(C.s.i).query.get.map(_ ==> List(
          (1, None, None),
          (2, Some((20, "b")), None),
          (3, None, Some(("c", 300))),
          (4, Some((40, "b")), Some(("c", 400))),
        ))
    } yield ()
  }


  "Adjacent optional refs with opt attr" - refs { implicit conn =>
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.i(20).save.transact
      _ <- A.i(3).B.i(30).s("b").save.transact
      _ <- A.i(4).B.i(40)._A.C.s("c").save.transact
      _ <- A.i(5).B.i(50)._A.C.s("c").i(500).save.transact
      _ <- A.i(6).B.i(60).s("b").save.transact
      _ <- A.i(7).B.i(70).s("b")._A.C.s("c").save.transact
      _ <- A.i(8).B.i(80).s("b")._A.C.s("c").i(800).save.transact

      _ <- A.i.a1.B.?(B.i.s).C.?(C.s.i).query.get.map(_ ==> List(
        (1, None, None),
        (2, None, None),
        (3, Some((30, "b")), None),
        (4, None, None),
        (5, None, Some(("c", 500))),
        (6, Some((60, "b")), None),
        (7, Some((70, "b")), None),
        (8, Some((80, "b")), Some(("c", 800))),
      ))

      _ <- A.i.a1.B.?(B.i.s).C.?(C.s.i_?).query.get.map(_ ==> List(
        (1, None, None),
        (2, None, None),
        (3, Some((30, "b")), None),
        (4, None, Some(("c", None))),
        (5, None, Some(("c", Some(500)))),
        (6, Some((60, "b")), None),
        (7, Some((70, "b")), Some(("c", None))),
        (8, Some((80, "b")), Some(("c", Some(800)))),
      ))

      _ <- A.i.a1.B.?(B.i.s_?).C.?(C.s.i).query.get.map(_ ==> List(
        (1, None, None),
        (2, Some((20, None)), None),
        (3, Some((30, Some("b"))), None),
        (4, Some((40, None)), None),
        (5, Some((50, None)), Some(("c", 500))),
        (6, Some((60, Some("b"))), None),
        (7, Some((70, Some("b"))), None),
        (8, Some((80, Some("b"))), Some(("c", 800))),
      ))

      _ <- A.i.a1.B.?(B.i.s_?).C.?(C.s.i_?).query.get.map(_ ==> List(
        (1, None, None),
        (2, Some((20, None)), None),
        (3, Some((30, Some("b"))), None),
        (4, Some((40, None)), Some(("c", None))),
        (5, Some((50, None)), Some(("c", Some(500)))),
        (6, Some((60, Some("b"))), None),
        (7, Some((70, Some("b"))), Some(("c", None))),
        (8, Some((80, Some("b"))), Some(("c", Some(800)))),
      ))
    } yield ()
  }


  "Adjacent refs for comparison" - refs { implicit conn =>
    for {
      _ <- A.i(1).save.transact
      _ <- A.i(2).B.i(20).save.transact
      _ <- A.i(3).B.i(30).s("b").save.transact
      _ <- A.i(4).B.i(40)._A.C.s("c").save.transact
      _ <- A.i(5).B.i(50)._A.C.s("c").i(500).save.transact
      _ <- A.i(6).B.i(60).s("b").save.transact
      _ <- A.i(7).B.i(70).s("b")._A.C.s("c").save.transact
      _ <- A.i(8).B.i(80).s("b")._A.C.s("c").i(800).save.transact

      _ <- A.i.B.i.s._A.C.s.i.query.get.map(_ ==> List(
        (8, 80, "b", "c", 800),
      ))
      _ <- A.i.a1.B.i.s._A.C.s.i_?.query.get.map(_ ==> List(
        (7, 70, "b", "c", None),
        (8, 80, "b", "c", Some(800)),
      ))
      _ <- A.i.a1.B.i.s_?._A.C.s.i.query.get.map(_ ==> List(
        (5, 50, None, "c", 500),
        (8, 80, Some("b"), "c", 800),
      ))
      _ <- A.i.a1.B.i.s_?._A.C.s.i_?.query.get.map(_ ==> List(
        (4, 40, None, "c", None),
        (5, 50, None, "c", Some(500)),
        (7, 70, Some("b"), "c", None),
        (8, 80, Some("b"), "c", Some(800)),
      ))
    } yield ()
  }


  "Adjacent optional refs with inner ref" - refs { implicit conn =>
    for {
      _ <- A.i.a1.B.?(B.s.i.C.s.i).D.?(D.s.i).insert(List(
        (1, None, Some(("d", 1))),
        (2, Some(("b", 2, "c", 2)), None),
        (3, Some(("b", 3, "c", 3)), Some(("d", 3))),
      )).transact

      _ <- A.i.a1.B.?(B.s.i.C.s.i).D.?(D.s.i).query.get.map(_ ==> List(
        (1, None, Some(("d", 1))),
        (2, Some(("b", 2, "c", 2)), None),
        (3, Some(("b", 3, "c", 3)), Some(("d", 3))),
      ))
    } yield ()
  }
}
