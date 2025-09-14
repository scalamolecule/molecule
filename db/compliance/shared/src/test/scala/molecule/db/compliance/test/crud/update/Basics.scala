package molecule.db.compliance.test.crud.update

import scala.collection.immutable.*
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.A
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Basics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Update entity with id" - types {
    for {
      id <- Entity.i(1).save.transact.map(_.id)
      _ <- Entity.i.query.get.map(_ ==> List(1))

      _ <- Entity(id).i(2).update.transact
      _ <- Entity.i.query.get.map(_ ==> List(2))
    } yield ()
  }


  "Can't update optional values" - types {
    for {
      _ <- Entity(42).int_?(Some(1)).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.int_?)"
        }

      _ <- Entity(42).intSet_?(Some(Set(1))).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.intSet_?)"
        }

      _ <- Entity(42).intSeq_?(Some(Seq(1))).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.intSeq_?)"
        }

      _ <- Entity(42).intMap_?(Some(Map("a" -> 1))).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't update optional values (Entity.intMap_?)"
        }
    } yield ()
  }


  "Can't upsert related data" - types {
    for {
      id <- Entity.i(1).Ref.i(2).save.transact.map(_.id)

      _ <- Entity(id).i(10).Ref.i(20).upsert.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Upsert of related data not allowed. Please use update instead."
          // (it would be too magic to insert missing relationships etc..)
        }

      // update allowed - will only affect already saved relationships and values
      _ <- Entity(id).i(11).Ref.i(21).update.transact
      _ <- Entity.i.Ref.i.query.get.map(_ ==> List((11, 21)))
    } yield ()
  }


  "backref" - refs {
    for {
      _ <- A(42).i(10).B.i(20)._A.C.i(30).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Backref in update not supported."
        }
    } yield ()
  }
}