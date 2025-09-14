package molecule.db.compliance.test.crud.update.attrOp.number

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class AttrOpInteger_BigInt(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "plus" - types {
    for {
      id <- Entity.bigInt(bigInt1).save.transact.map(_.id)
      _ <- Entity(id).bigInt.+(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt3)
    } yield ()
  }

  "minus" - types {
    for {
      id <- Entity.bigInt(bigInt3).save.transact.map(_.id)
      _ <- Entity(id).bigInt.-(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt1)
    } yield ()
  }

  "times" - types {
    for {
      id <- Entity.bigInt(bigInt2).save.transact.map(_.id)
      _ <- Entity(id).bigInt.*(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt4)
    } yield ()
  }

  "divide" - types {
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt./(bigInt2).update.transact
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt2)
    } yield ()
  }

  "negate" - types {
    for {
      ids <- Entity.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
      _ <- Entity(ids).bigInt.negate.update.transact
      _ <- Entity.bigInt.d1.query.get.map(_ ==> List(bigInt1, -bigInt2))
    } yield ()
  }

  "absolute" - types {
    for {
      ids <- Entity.bigInt.insert(-bigInt1, bigInt2).transact.map(_.ids)
      _ <- Entity(ids).bigInt.abs.update.transact
      _ <- Entity.bigInt.a1.query.get.map(_ ==> List(bigInt1, bigInt2))
    } yield ()
  }

  "absolute negative" - types {
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

  "No even" - types {
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.bigInt.even and Entity.bigInt.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types {
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.bigInt.even and Entity.bigInt.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types {
    for {
      id <- Entity.bigInt(bigInt4).save.transact.map(_.id)
      _ <- Entity(id).bigInt.%(bigInt3, bigInt2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.bigInt.%(3) can't be used with updates."
        }
    } yield ()
  }
}
