// GENERATED CODE ********************************
package molecule.coreTests.spi.action.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AttrOpInteger_Short_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "plus" - types { implicit conn =>
    for {
      id <- Entity.short(short1).save.transact.map(_.id)
      _ <- Entity(id).short.+(short2).update.transact
      _ <- Entity.short.query.get.map(_.head ==> short3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.short(short3).save.transact.map(_.id)
      _ <- Entity(id).short.-(short2).update.transact
      _ <- Entity.short.query.get.map(_.head ==> short1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.short(short2).save.transact.map(_.id)
      _ <- Entity(id).short.*(short2).update.transact
      _ <- Entity.short.query.get.map(_.head ==> short4)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.short(short4).save.transact.map(_.id)
      _ <- Entity(id).short./(short2).update.transact
      _ <- Entity.short.query.get.map(_.head ==> short2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.short.insert(-1.toShort, short2).transact.map(_.ids)
      _ <- Entity(ids).short.negate.update.transact
      _ <- Entity.short.d1.query.get.map(_ ==> List(short1, -2.toShort))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.short.insert(-1.toShort, short2).transact.map(_.ids)
      _ <- Entity(ids).short.abs.update.transact
      _ <- Entity.short.a1.query.get.map(_ ==> List(short1, short2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.short.insert(-1.toShort, short2).transact.map(_.ids)
      _ <- Entity(ids).short.absNeg.update.transact
      _ <- Entity.short.d1.query.get.map(_ ==> List(-1.toShort, -2.toShort))
    } yield ()
  }


  // even/odd/modulo not allowed for updates

  "No even" - types { implicit conn =>
    for {
      id <- Entity.short(short4).save.transact.map(_.id)
      _ <- Entity(id).short.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.short.even and Entity.short.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types { implicit conn =>
    for {
      id <- Entity.short(short4).save.transact.map(_.id)
      _ <- Entity(id).short.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.short.even and Entity.short.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types { implicit conn =>
    for {
      id <- Entity.short(short4).save.transact.map(_.id)
      _ <- Entity(id).short.%(short3, short2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.short.%(3) can't be used with updates."
        }
    } yield ()
  }
}
