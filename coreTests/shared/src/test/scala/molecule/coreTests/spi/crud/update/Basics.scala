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

    "Update entity with id" - types { implicit conn =>
      for {
        id <- Ns.i(1).save.transact.map(_.id)
        _ <- Ns.i.query.get.map(_ ==> List(1))

        _ <- Ns(id).i(2).update.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))
      } yield ()
    }


    "Can't update optional values" - types { implicit conn =>
      for {
        _ <- Ns(42).int_?(Some(1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values (Ns.int_?)"
          }

        _ <- Ns(42).intSet_?(Some(Set(1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values (Ns.intSet_?)"
          }

        _ <- Ns(42).intSeq_?(Some(Seq(1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values (Ns.intSeq_?)"
          }

        _ <- Ns(42).intMap_?(Some(Map("a" -> 1))).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't update optional values (Ns.intMap_?)"
          }
      } yield ()
    }
  }
}
