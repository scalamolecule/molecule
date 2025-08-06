// GENERATED CODE ********************************
package molecule.db.compliance.test.action.update.attrOp.number

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class AttrOpInteger_Long_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "plus" - types {
    for {
      id <- Entity.long(long1).save.transact.map(_.id)
      _ <- Entity(id).long.+(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long3)
    } yield ()
  }

  "minus" - types {
    for {
      id <- Entity.long(long3).save.transact.map(_.id)
      _ <- Entity(id).long.-(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long1)
    } yield ()
  }

  "times" - types {
    for {
      id <- Entity.long(long2).save.transact.map(_.id)
      _ <- Entity(id).long.*(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long4)
    } yield ()
  }

  "divide" - types {
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long./(long2).update.transact
      _ <- Entity.long.query.get.map(_.head ==> long2)
    } yield ()
  }

  "negate" - types {
    for {
      ids <- Entity.long.insert(-long1, long2).transact.map(_.ids)
      _ <- Entity(ids).long.negate.update.transact
      _ <- Entity.long.d1.query.get.map(_ ==> List(long1, -long2))
    } yield ()
  }

  "absolute" - types {
    for {
      ids <- Entity.long.insert(-long1, long2).transact.map(_.ids)
      _ <- Entity(ids).long.abs.update.transact
      _ <- Entity.long.a1.query.get.map(_ ==> List(long1, long2))
    } yield ()
  }

  "absolute negative" - types {
    for {
      ids <- Entity.long.insert(-long1, long2).transact.map(_.ids)
      _ <- Entity(ids).long.absNeg.update.transact
      _ <- Entity.long.d1.query.get.map(_ ==> List(-long1, -long2))
    } yield ()
  }


  // even/odd/modulo not allowed for updates

  "No even" - types {
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.long.even and Entity.long.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types {
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.long.even and Entity.long.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types {
    for {
      id <- Entity.long(long4).save.transact.map(_.id)
      _ <- Entity(id).long.%(long3, long2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.long.%(3) can't be used with updates."
        }
    } yield ()
  }
}
