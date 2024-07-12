package molecule.coreTests.spi.relation.flat

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FlatRefOpt extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Basic optional ref" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.i(20).save.transact

        _ <- A.i.B.?(B.i).query.get.map(_ ==> List(
          (1, None),
          (2, Some(20)),
        ))
      } yield ()
    }


    "Mix with optional attribute" - refs { implicit conn =>
      for {
        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("a").save.transact
        _ <- A.i(3).B.i(30).save.transact
        _ <- A.i(4).B.i(40).s("b").save.transact

        _ <- A.i.B.?(B.i_?.s).query.get.map(_ ==> List(
          (1, None),
          (2, Some((None, "a"))),
          (3, None),
          (4, Some((Some(40), "b"))),
        ))

        _ <- A.i.B.?(B.i.s_?).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, Some((30, None))),
          (4, Some((40, Some("b")))),
        ))

        _ <- A.i.B.?(B.i.s).query.get.map(_ ==> List(
          (1, None),
          (2, None),
          (3, None),
          (4, Some((40, "b"))),
        ))
      } yield ()
    }


    "Adjacent optional refs" - refs { implicit conn =>
      for {

        _ <- A.i.B.?(B.i.s).C.i.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only further optional refs allowed after optional ref."
          }
        _ <- A.i.B.?(B.i.s).Cc.*(C.i).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Only further optional refs allowed after optional ref."
          }
      } yield ()
    }
  }
}
