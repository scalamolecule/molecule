package molecule.coreTests.spi.crud.update.attrOp.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpDecimal_BigDecimal_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {
    implicit val tolerance = tolerantBigDecimalEquality(toleranceBigDecimal)

    "plus" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal1).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal.+(bigDecimal2).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==~ bigDecimal3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal3).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal.-(bigDecimal2).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==~ bigDecimal1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal2).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal.*(bigDecimal2).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==~ bigDecimal2 * bigDecimal2)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal4).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal./(bigDecimal2).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==~ bigDecimal4 / bigDecimal2)
      } yield ()
    }

    "modulo" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal4).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal.%(bigDecimal3).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==~ bigDecimal1)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
        _ <- Ns(ids).bigDecimal.negate.update.transact
        _ <- Ns.bigDecimal.d1.query.get.map(_ ==> List(bigDecimal1, -bigDecimal2))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
        _ <- Ns(ids).bigDecimal.abs.update.transact
        _ <- Ns.bigDecimal.a1.query.get.map(_ ==> List(bigDecimal1, bigDecimal2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.bigDecimal.insert(-bigDecimal1, bigDecimal2).transact.map(_.ids)
        _ <- Ns(ids).bigDecimal.absNeg.update.transact
        // (sorting on result to avoid incorrect sorting of negative BigDecimal in SQlite)
        _ <- Ns.bigDecimal.query.get.map(_.sorted.reverse ==> List(-bigDecimal1, -bigDecimal2))
      } yield ()
    }

    "ceil" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal3).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal.ceil.update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==> int4)
      } yield ()
    }

    "floor" - types { implicit conn =>
      for {
        id <- Ns.bigDecimal(bigDecimal3).save.transact.map(_.id)
        _ <- Ns(id).bigDecimal.floor.update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==> int3)
      } yield ()
    }
  }
}
