package molecule.coreTests.spi.relation.flat

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FlatRefOptAdjacent extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Basic optional ref nested" - refs { implicit conn =>
      for {
        _ <- A.i
          .B.?(B.i.s)
          .C.?(C.s.i).insert(List(
            (1, None, None),
            (2, Some((20, "b")), None),
            (3, None, Some(("c", 300))),
            (4, Some((40, "b")), Some(("c", 400))),
          )).transact

        _ <- A.i
          .B.?(B.i.s)
          .C.?(C.s.i).query.get.map(_ ==> List(
            (1, None, None),
            (2, Some((20, "b")), None),
            (3, None, Some(("c", 300))),
            (4, Some((40, "b")), Some(("c", 400))),
          ))
      } yield ()
    }


    "Optional ref adjacent" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.i(20).save.transact
        _ <- A.i(3).B.i(30)._A.C.s("c").i(300).save.transact
        _ <- A.i(4).B.i(40).s("b").save.transact
        _ <- A.i(5).B.i(50).s("b")._A.C.s("c").save.transact
        _ <- A.i(6).B.i(60).s("b")._A.C.s("c").i(600).save.transact

        _ <- A.i.B.?(B.i.s).C.?(C.s.i).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, None),
          (4, Some((40, "b")), None),
          (5, Some((50, "b")), None),
          (6, Some((60, "b")), Some(("c", 600))),
        ))

        _ <- A.i.B.?(B.i.s).C.?(C.s.i_?).query.i.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, None),
          (4, Some((40, "b")), None),
          (5, Some((50, "b")), Some(("c", None))),
          (6, Some((60, "b")), Some(("c", Some(600)))),
        ))

        _ <- A.i.B.?(B.i.s_?).C.?(C.s.i).query.i.get.map(_ ==> List(
          (1, None),
          (2, Some((20, None)), None),
          (3, Some((30, None)), Some(("c", 300))),
          (4, Some((40, Some("b"))), None),
          (5, Some((50, Some("b"))), None),
          (6, Some((60, Some("b"))), Some(("c", 600))),
        ))

        _ <- A.i.B.?(B.i.s_?).C.?(C.s.i_?).query.i.get.map(_ ==> List(
          (1, None),
          (2, Some((20, None)), None),
          (3, Some((30, None)), Some(("c", Some(300)))),
          (4, Some((40, Some("b"))), None),
          (5, Some((50, Some("b"))), Some(("c", None))),
          (6, Some((60, Some("b"))), Some(("c", Some(600)))),
        ))
      } yield ()
    }


    "Ref (for comparison)" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.i(20).save.transact
        _ <- A.i(3).B.i(30)._A.C.s("c").i(300).save.transact
        _ <- A.i(4).B.i(40).s("b").save.transact
        _ <- A.i(5).B.i(50).s("b")._A.C.s("c").save.transact
        _ <- A.i(6).B.i(60).s("b")._A.C.s("c").i(600).save.transact

        _ <- A.i.B.i.s._A.C.s.i.query.get.map(_ ==> List(
          (6, 60, "b", "c", 600),
        ))
        _ <- A.i.B.i.s._A.C.s.i_?.query.get.map(_ ==> List(
          (5, 50, "b", "c", None),
          (6, 60, "b", "c", Some(600)),
        ))
        _ <- A.i.B.i.s_?._A.C.s.i.query.get.map(_ ==> List(
          (3, 30, None, "c", 300),
          (6, 60, Some("b"), "c", 600),
        ))
        _ <- A.i.B.i.s_?._A.C.s.i_?.query.get.map(_ ==> List(
          (3, 30, None, "c", Some(300)),
          (5, 50, Some("b"), "c", None),
          (6, 60, Some("b"), "c", Some(600)),
        ))
      } yield ()
    }


    "Basic optional ref nested" - refs { implicit conn =>
      for {
        _ <- A.i.D.s.i.insert(
          1, "d", 1
        ).transact

        _ <- A.i.B.s.i._A.D.s.i.insert(
          2, "b", 2, "d", 2
        ).transact

        _ <- A.i.B.s.i.C.s.i.insert(
          3, "b", 3, "c", 3
        ).transact

        _ <- A.i.B.s.i.C.s.i._A.D.s.i.insert(
          4, "b", 4, "c", 4, "d", 4
        ).transact

        _ <- A.i.B.?(B.s.i.C.s.i).D.?(D.s.i).query.get.map(_ ==> List(
          (1, None, Some(("d", 4))),
          (2, None, Some(("d", 2))),
          (3, Some(("b", 3, "c", 3)), None),
          (4, Some(("b", 4, "c", 4)), Some(("d", 4))),
        ))
      } yield ()
    }
  }
}
