// GENERATED CODE ********************************
package molecule.coreTests.spi.transaction.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AttrOpInteger_Byte_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "plus" - types { implicit conn =>
    for {
      id <- Entity.byte(byte1).save.transact.map(_.id)
      _ <- Entity(id).byte.+(byte2).update.transact
      _ <- Entity.byte.query.get.map(_.head ==> byte3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.byte(byte3).save.transact.map(_.id)
      _ <- Entity(id).byte.-(byte2).update.transact
      _ <- Entity.byte.query.get.map(_.head ==> byte1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.byte(byte2).save.transact.map(_.id)
      _ <- Entity(id).byte.*(byte2).update.transact
      _ <- Entity.byte.query.get.map(_.head ==> byte4)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.byte(byte4).save.transact.map(_.id)
      _ <- Entity(id).byte./(byte2).update.transact
      _ <- Entity.byte.query.get.map(_.head ==> byte2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.byte.insert(-1.toByte, byte2).transact.map(_.ids)
      _ <- Entity(ids).byte.negate.update.transact
      _ <- Entity.byte.d1.query.get.map(_ ==> List(byte1, -2.toByte))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.byte.insert(-1.toByte, byte2).transact.map(_.ids)
      _ <- Entity(ids).byte.abs.update.transact
      _ <- Entity.byte.a1.query.get.map(_ ==> List(byte1, byte2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.byte.insert(-1.toByte, byte2).transact.map(_.ids)
      _ <- Entity(ids).byte.absNeg.update.transact
      _ <- Entity.byte.d1.query.get.map(_ ==> List(-1.toByte, -2.toByte))
    } yield ()
  }


  // even/odd/modulo not allowed for updates

  "No even" - types { implicit conn =>
    for {
      id <- Entity.byte(byte4).save.transact.map(_.id)
      _ <- Entity(id).byte.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.byte.even and Entity.byte.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types { implicit conn =>
    for {
      id <- Entity.byte(byte4).save.transact.map(_.id)
      _ <- Entity(id).byte.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.byte.even and Entity.byte.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types { implicit conn =>
    for {
      id <- Entity.byte(byte4).save.transact.map(_.id)
      _ <- Entity(id).byte.%(byte3, byte2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.byte.%(3) can't be used with updates."
        }
    } yield ()
  }
}
