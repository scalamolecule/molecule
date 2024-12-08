// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AttrOpInteger_BigInt extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "plus" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt1).save.transact.map(_.id)
        _ <- Ns(id).bigInt.+(bigInt2).update.transact
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt3)
      } yield ()
    }

    "minus" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt3).save.transact.map(_.id)
        _ <- Ns(id).bigInt.-(bigInt2).update.transact
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt1)
      } yield ()
    }

    "times" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt2).save.transact.map(_.id)
        _ <- Ns(id).bigInt.*(bigInt2).update.transact
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt4)
      } yield ()
    }

    "divide" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt4).save.transact.map(_.id)
        _ <- Ns(id).bigInt./(bigInt2).update.transact
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt2)
      } yield ()
    }

    "negate" - types { implicit conn =>
      for {
        ids <- Ns.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
        _ <- Ns(ids).bigInt.negate.update.transact
        _ <- Ns.bigInt.d1.query.get.map(_ ==> List(bigInt1, -bigInt2))
      } yield ()
    }

    "absolute" - types { implicit conn =>
      for {
        ids <- Ns.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
        _ <- Ns(ids).bigInt.abs.update.transact
        _ <- Ns.bigInt.a1.query.get.map(_ ==> List(bigInt1, bigInt2))
      } yield ()
    }

    "absolute negative" - types { implicit conn =>
      for {
        ids <- Ns.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
        _ <- Ns(ids).bigInt.absNeg.update.transact

        _ <- if (database == "SQlite") {
          // Sort output instead since BigInts saved as Text in SQlite sort lexicographically
          Ns.bigInt.query.get.map(_.sorted.reverse ==> List(-bigInt1, -bigInt2))
        } else {
          Ns.bigInt.d1.query.get.map(_ ==> List(-bigInt1, -bigInt2))
        }
      } yield ()
    }


    // even/odd/modulo not allowed for updates

    "No even" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt4).save.transact.map(_.id)
        _ <- Ns(id).bigInt.even.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.bigInt.even and Ns.bigInt.odd can't be used with updates."
          }
      } yield ()
    }

    "No odd" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt4).save.transact.map(_.id)
        _ <- Ns(id).bigInt.odd.update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Ns.bigInt.even and Ns.bigInt.odd can't be used with updates."
          }
      } yield ()
    }

    "No modulo" - types { implicit conn =>
      for {
        id <- Ns.bigInt(bigInt4).save.transact.map(_.id)
        _ <- Ns(id).bigInt.%(bigInt3, bigInt2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Modulo operations like Ns.bigInt.%(3) can't be used with updates."
          }
      } yield ()
    }
  }
}
