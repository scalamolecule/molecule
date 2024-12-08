// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpInteger_Byte_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "plus" - types { implicit conn =>
      for {
        id <- Ns.byte(byte1).save.transact.map(_.id)
        _ <- Ns(id).byte.+(byte2).update.transact
        _ <- Ns.byte.query.get.map(_.head ==> byte3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.byte(byte3).save.transact.map(_.id)
        _ <- Ns(id).byte.-(byte2).update.transact
        _ <- Ns.byte.query.get.map(_.head ==> byte1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.byte(byte2).save.transact.map(_.id)
        _ <- Ns(id).byte.*(byte2).update.transact
        _ <- Ns.byte.query.get.map(_.head ==> byte4)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.byte(byte4).save.transact.map(_.id)
        _ <- Ns(id).byte./(byte2).update.transact
        _ <- Ns.byte.query.get.map(_.head ==> byte2)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.byte.insert(-1.toByte, byte2).transact.map(_.ids)
        _ <- Ns(ids).byte.negate.update.transact
        _ <- Ns.byte.d1.query.get.map(_ ==> List(byte1, -2.toByte))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.byte.insert(-1.toByte, byte2).transact.map(_.ids)
        _ <- Ns(ids).byte.abs.update.transact
        _ <- Ns.byte.a1.query.get.map(_ ==> List(byte1, byte2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.byte.insert(-1.toByte, byte2).transact.map(_.ids)
        _ <- Ns(ids).byte.absNeg.update.transact
        _ <- Ns.byte.d1.query.get.map(_ ==> List(-1.toByte, -2.toByte))
      } yield ()
    }


    // even/odd/modulo not allowed for updates

    "No even" - types { implicit conn =>
      for {
        id <- Ns.byte(byte4).save.transact.map(_.id)
        _ <- Ns(id).byte.even.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.byte.even and Ns.byte.odd can't be used with updates."
          }
      } yield ()
    }

    "No odd" - types { implicit conn =>
      for {
        id <- Ns.byte(byte4).save.transact.map(_.id)
        _ <- Ns(id).byte.odd.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.byte.even and Ns.byte.odd can't be used with updates."
          }
      } yield ()
    }

    "No modulo" - types { implicit conn =>
      for {
        id <- Ns.byte(byte4).save.transact.map(_.id)
        _ <- Ns(id).byte.%(byte3, byte2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Modulo operations like Ns.byte.%(3) can't be used with updates."
          }
      } yield ()
    }
  }
}
