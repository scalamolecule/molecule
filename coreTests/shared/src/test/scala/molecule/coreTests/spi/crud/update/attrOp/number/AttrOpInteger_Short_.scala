// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpInteger_Short_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "plus" - types { implicit conn =>
      for {
        id <- Ns.short(short1).save.transact.map(_.id)
        _ <- Ns(id).short.+(short2).update.transact
        _ <- Ns.short.query.get.map(_.head ==> short3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.short(short3).save.transact.map(_.id)
        _ <- Ns(id).short.-(short2).update.transact
        _ <- Ns.short.query.get.map(_.head ==> short1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.short(short2).save.transact.map(_.id)
        _ <- Ns(id).short.*(short2).update.transact
        _ <- Ns.short.query.get.map(_.head ==> short4)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.short(short4).save.transact.map(_.id)
        _ <- Ns(id).short./(short2).update.transact
        _ <- Ns.short.query.get.map(_.head ==> short2)
      } yield ()
    }

    "modulo" - types { implicit conn =>
      for {
        id <- Ns.short(short4).save.transact.map(_.id)
        _ <- Ns(id).short.%(short3).update.transact
        _ <- Ns.short.query.get.map(_.head ==> short1)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.short.insert(-1.toShort, short2).transact.map(_.ids)
        _ <- Ns(ids).short.negate.update.transact
        _ <- Ns.short.d1.query.get.map(_ ==> List(short1, -2.toShort))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.short.insert(-1.toShort, short2).transact.map(_.ids)
        _ <- Ns(ids).short.abs.update.transact
        _ <- Ns.short.a1.query.get.map(_ ==> List(short1, short2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.short.insert(-1.toShort, short2).transact.map(_.ids)
        _ <- Ns(ids).short.absNeg.update.transact
        _ <- Ns.short.d1.query.get.map(_ ==> List(-1.toShort, -2.toShort))
      } yield ()
    }


    "No even" - types { implicit conn =>
      for {
        id <- Ns.short(short4).save.transact.map(_.id)
        _ <- Ns(id).short.even.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.short.even and Ns.short.odd can't be used with updates."
          }
      } yield ()
    }

    "No odd" - types { implicit conn =>
      for {
        id <- Ns.short(short4).save.transact.map(_.id)
        _ <- Ns(id).short.odd.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.short.even and Ns.short.odd can't be used with updates."
          }
      } yield ()
    }

    "Modulo with only divider" - types { implicit conn =>
      for {
        id <- Ns.short(short4).save.transact.map(_.id)
        _ <- Ns(id).short.%(short3, short2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Please use `Ns.short.%(3)` to update attribute to the remainder after dividing by 3."
          }
      } yield ()
    }
  }
}
