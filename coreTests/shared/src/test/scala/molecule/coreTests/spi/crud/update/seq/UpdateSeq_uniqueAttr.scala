package molecule.coreTests.spi.crud.update.seq

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Uniques._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._


trait UpdateSeq_uniqueAttr extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - unique { implicit conn =>
      for {
        _ <- Uniques.intSeq_(Seq(1)).s("b").update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can only lookup entity with card-one attribute value. Found:\n" +
              """AttrSeqTacInt("Uniques", "intSeq", Eq, Seq(Seq(1)), None, None, Nil, Nil, None, None, Seq(0, 26))"""
          }
      } yield ()
    }
  }
}
