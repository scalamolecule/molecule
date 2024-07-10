package molecule.coreTests.spi.relation.flat

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FlatRefOptNested extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Optional ref" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.i(20).save.transact
        _ <- A.i(3).B.i(30).C.s("300").i(300).save.transact
        _ <- A.i(4).B.i(40).s("40").save.transact
        _ <- A.i(5).B.i(50).s("50").C.s("500").save.transact
        _ <- A.i(6).B.i(60).s("60").C.s("600").i(600).save.transact

        _ <- A.i.B.?(B.i.s.C.?(C.s.i)).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, None),
          (4, Some((40, "40", None))),
          (5, Some((50, "50", None))),
          (6, Some((60, "60", Some(("600", 600))))),
        ))

        _ <- A.i.B.?(B.i.s.C.?(C.s.i_?)).query.i.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, None),
          (4, Some((40, "40", None))),
          (5, Some((50, "50", Some(("500", None))))),
          (6, Some((60, "60", Some(("600", Some(600)))))),
        ))

        _ <- A.i.B.?(B.i.s_?.C.?(C.s.i)).query.i.get.map(_ ==> List(
          (1, None),
          (2, Some((20, None, None))),
          (3, Some((30, None, Some(("300", 300))))),
          (4, Some((40, Some("40"), None))),
          (5, Some((50, Some("50"), None))),
          (6, Some((60, Some("60"), Some(("600", 600))))),
        ))

        _ <- A.i.B.?(B.i.s_?.C.?(C.s.i_?)).query.i.get.map(_ ==> List(
          (1, None),
          (2, Some((20, None, None))),
          (3, Some((30, None, Some(("300", Some(300)))))),
          (4, Some((40, Some("40"), None))),
          (5, Some((50, Some("50"), Some(("500", None))))),
          (6, Some((60, Some("60"), Some(("600", Some(600)))))),
        ))
      } yield ()
    }

    "Ref" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.i(20).save.transact
        _ <- A.i(3).B.i(30).C.s("300").i(300).save.transact
        _ <- A.i(4).B.i(40).s("40").save.transact
        _ <- A.i(5).B.i(50).s("50").C.s("500").save.transact
        _ <- A.i(6).B.i(60).s("60").C.s("600").i(600).save.transact

        _ <- A.i.B.i.s.C.s.i.query.get.map(_ ==> List(
          (6, 60, "60", "600", 600),
        ))
        _ <- A.i.B.i.s.C.s.i_?.query.get.map(_ ==> List(
          (5, 50, "50", "500", None),
          (6, 60, "60", "600", Some(600)),
        ))
        _ <- A.i.B.i.s_?.C.s.i.query.get.map(_ ==> List(
          (3, 30, None, "300", 300),
          (6, 60, Some("60"), "600", 600),
        ))
        _ <- A.i.B.i.s_?.C.s.i_?.query.get.map(_ ==> List(
          (3, 30, None, "300", Some(300)),
          (5, 50, Some("50"), "500", None),
          (6, 60, Some("60"), "600", Some(600)),
        ))
      } yield ()
    }

  }
}
