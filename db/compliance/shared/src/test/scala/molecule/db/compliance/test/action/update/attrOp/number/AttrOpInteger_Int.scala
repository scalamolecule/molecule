package molecule.db.compliance.test.action.update.attrOp.number

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class AttrOpInteger_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "plus" - types {
    for {
      id <- Entity.int(int1).save.transact.map(_.id)
      _ <- Entity(id).int.+(int2).update.transact
      _ <- Entity.int.query.get.map(_.head ==> int3)
    } yield ()
  }

  "minus" - types {
    for {
      id <- Entity.int(int3).save.transact.map(_.id)
      _ <- Entity(id).int.-(int2).update.transact
      _ <- Entity.int.query.get.map(_.head ==> int1)
    } yield ()
  }

  "times" - types {
    for {
      id <- Entity.int(int2).save.transact.map(_.id)
      _ <- Entity(id).int.*(int2).update.transact
      _ <- Entity.int.query.get.map(_.head ==> int4)
    } yield ()
  }

  "divide" - types {
    for {
      id <- Entity.int(int4).save.transact.map(_.id)
      _ <- Entity(id).int./(int2).update.transact
      _ <- Entity.int.query.get.map(_.head ==> int2)
    } yield ()
  }

  "negate" - types {
    for {
      ids <- Entity.int.insert(-int1, int2).transact.map(_.ids)
      _ <- Entity(ids).int.negate.update.transact
      _ <- Entity.int.d1.query.get.map(_ ==> List(int1, -int2))
    } yield ()
  }

  "absolute" - types {
    for {
      ids <- Entity.int.insert(-int1, int2).transact.map(_.ids)
      _ <- Entity(ids).int.abs.update.transact
      _ <- Entity.int.a1.query.get.map(_ ==> List(int1, int2))
    } yield ()
  }

  "absolute negative" - types {
    for {
      ids <- Entity.int.insert(-int1, int2).transact.map(_.ids)
      _ <- Entity(ids).int.absNeg.update.transact
      _ <- Entity.int.d1.query.get.map(_ ==> List(-int1, -int2))
    } yield ()
  }


  // even/odd/modulo not allowed for updates

  "No even" - types {
    for {
      id <- Entity.int(int4).save.transact.map(_.id)
      _ <- Entity(id).int.even.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.int.even and Entity.int.odd can't be used with updates."
        }
    } yield ()
  }

  "No odd" - types {
    for {
      id <- Entity.int(int4).save.transact.map(_.id)
      _ <- Entity(id).int.odd.update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Entity.int.even and Entity.int.odd can't be used with updates."
        }
    } yield ()
  }

  "No modulo" - types {
    for {
      id <- Entity.int(int4).save.transact.map(_.id)
      _ <- Entity(id).int.%(int3, int2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Modulo operations like Entity.int.%(3) can't be used with updates."
        }
    } yield ()
  }
}
