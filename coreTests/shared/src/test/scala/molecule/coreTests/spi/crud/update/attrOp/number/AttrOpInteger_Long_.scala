// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpInteger_Long_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "plus" - types { implicit conn =>
      for {
        id <- Ns.long(long1).save.transact.map(_.id)
        _ <- Ns(id).long.+(long2).update.transact
        _ <- Ns.long.query.get.map(_.head ==> long3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.long(long3).save.transact.map(_.id)
        _ <- Ns(id).long.-(long2).update.transact
        _ <- Ns.long.query.get.map(_.head ==> long1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.long(long2).save.transact.map(_.id)
        _ <- Ns(id).long.*(long2).update.transact
        _ <- Ns.long.query.get.map(_.head ==> long4)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.long(long4).save.transact.map(_.id)
        _ <- Ns(id).long./(long2).update.transact
        _ <- Ns.long.query.get.map(_.head ==> long2)
      } yield ()
    }

    "modulo" - types { implicit conn =>
      for {
        id <- Ns.long(long4).save.transact.map(_.id)
        _ <- Ns(id).long.%(long3).update.transact
        _ <- Ns.long.query.get.map(_.head ==> long1)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.long.insert(-long1, long2).transact.map(_.ids)
        _ <- Ns(ids).long.negate.update.transact
        _ <- Ns.long.d1.query.get.map(_ ==> List(long1, -long2))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.long.insert(-long1, long2).transact.map(_.ids)
        _ <- Ns(ids).long.abs.update.transact
        _ <- Ns.long.a1.query.get.map(_ ==> List(long1, long2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.long.insert(-long1, long2).transact.map(_.ids)
        _ <- Ns(ids).long.absNeg.update.transact
        // (sorting on result to avoid incorrect sorting of negative BigInt in SQlite)
        _ <- Ns.long.query.get.map(_.sorted.reverse ==> List(-long1, -long2))
      } yield ()
    }


    "No even" - types { implicit conn =>
      for {
        id <- Ns.long(long4).save.transact.map(_.id)
        _ <- Ns(id).long.even.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.long.even and Ns.long.odd can't be used with updates."
          }
      } yield ()
    }

    "No odd" - types { implicit conn =>
      for {
        id <- Ns.long(long4).save.transact.map(_.id)
        _ <- Ns(id).long.odd.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.long.even and Ns.long.odd can't be used with updates."
          }
      } yield ()
    }

    "Modulo with only divider" - types { implicit conn =>
      for {
        id <- Ns.long(long4).save.transact.map(_.id)
        _ <- Ns(id).long.%(long3, long2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please use `Ns.long.%(3)` to update attribute to the remainder after dividing by 3."
          }
      } yield ()
    }
  }
}
