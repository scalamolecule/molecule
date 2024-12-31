// GENERATED CODE ********************************
package molecule.coreTests.spi.transaction.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AttrOpInteger_BigInt(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "plus" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt1).save.transact.map(_.id)
      _ <- Entity(id).bigInt.+(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt3).save.transact.map(_.id)
      _ <- Entity(id).bigInt.-(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt2).save.transact.map(_.id)
      _ <- Entity(id).bigInt.*(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt4)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt./(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
      _ <- Entity(ids).bigInt.negate.update.transact
      _ <- Entity.bigInt.d1.query.get.map(_ ==> List(bigInt1, -bigInt2))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
      _ <- Entity(ids).bigInt.abs.update.transact
      _ <- Entity.bigInt.a1.query.get.map(_ ==> List(bigInt1, bigInt2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
      _ <- Entity(ids).bigInt.absNeg.update.transact

      _ <- if (database == "sqlite") {
        // Sort output instead since BigInts saved as Text in SQlite sort lexicographically
        Entity.bigInt.query.get.map(_.sorted.reverse ==> List(-bigInt1, -bigInt2))
      } else {
        Entity.bigInt.d1.query.get.map(_ ==> List(-bigInt1, -bigInt2))
      }
    } yield ()
  }


  // even/odd/modulo not allowed for updates

  "No even" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.bigInt.even and Entity.bigInt.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.bigInt.even and Entity.bigInt.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types { implicit conn =>
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt.%(bigInt3, bigInt2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.bigInt.%(3) can't be used with updates."
        }
    } yield ()
  }
}
