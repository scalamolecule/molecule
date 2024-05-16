package molecule.coreTests.spi.crud.update

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait Basics extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Can't update optional values" - types { implicit conn =>
      for {
        _ <- Ns("42").int_?(Some(1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrOneOptInt("Ns", "int", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None, Seq(0, 8))"""
          }

        _ <- Ns("42").intSet_?(Some(Set(1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrSetOptInt("Ns", "intSet", Eq, Some(Set(1)), None, None, Nil, Nil, None, None, Seq(0, 32))"""
          }

        _ <- Ns("42").intSeq_?(Some(Seq(1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrSeqOptInt("Ns", "intSeq", Eq, Some(Seq(1)), None, None, Nil, Nil, None, None, Seq(0, 55))"""
          }

        _ <- Ns("42").intMap_?(Some(Map("a" -> 1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values. Found:\n" +
              s"""AttrMapOptInt("Ns", "intMap", Eq, Some(Map(("a", 1))), None, None, Nil, Nil, None, None, Seq(0, 77))"""
          }
      } yield ()
    }
  }
}
