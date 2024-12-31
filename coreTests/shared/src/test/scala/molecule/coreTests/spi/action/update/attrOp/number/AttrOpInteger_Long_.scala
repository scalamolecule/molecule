// GENERATED CODE ********************************
package molecule.coreTests.spi.action.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class AttrOpInteger_Long_(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "plus" - types { implicit conn =>
    for {
      id <- Entity.long(long1).save.transact.map(_.id)
      _ <- Entity(id).long.+(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long3)
    } yield ()
  }

  "minus" - types { implicit conn =>
    for {
      id <- Entity.long(long3).save.transact.map(_.id)
      _ <- Entity(id).long.-(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long1)
    } yield ()
  }

  "times" - types { implicit conn =>
    for {
      id <- Entity.long(long2).save.transact.map(_.id)
      _ <- Entity(id).long.*(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long4)
    } yield ()
  }

  "divide" - types { implicit conn =>
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long./(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long2)
    } yield ()
  }

  "negate" - types { implicit conn =>
    for {
      ids <- Entity.long.insert(-long1, long2).transact.map(_.ids)
      _ <- Entity(ids).long.negate.update.transact
      _ <- Entity.long.d1.query.get.map(_ ==> List(long1, -long2))
    } yield ()
  }

  "absolute" - types { implicit conn =>
    for {
      ids <- Entity.long.insert(-long1, long2).transact.map(_.ids)
      _ <- Entity(ids).long.abs.update.transact
      _ <- Entity.long.a1.query.get.map(_ ==> List(long1, long2))
    } yield ()
  }

  "absolute negative" - types { implicit conn =>
    for {
      ids <- Entity.long.insert(-long1, long2).transact.map(_.ids)
      _ <- Entity(ids).long.absNeg.update.transact
      _ <- Entity.long.d1.query.get.map(_ ==> List(-long1, -long2))
    } yield ()
  }


  // even/odd/modulo not allowed for updates

  "No even" - types { implicit conn =>
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.long.even and Entity.long.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types { implicit conn =>
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.long.even and Entity.long.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types { implicit conn =>
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long.%(long3, long2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.long.%(3) can't be used with updates."
        }
    } yield ()
  }
}
