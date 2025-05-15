package molecule.db.compliance.test.filter.set

import molecule.db.base.error.ModelError
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class SetSemantics(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "equal" - types { implicit conn =>
    for {
      _ <- Entity.i.intSet(Set(int1)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intSet) not supported in queries."
        }

      _ <- Entity.i.intSet_(Set(int1)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intSet) not supported in queries."
        }

      _ <- Entity.i.intSet_?(Some(Set(int1))).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Matching collections (Entity.intSet) not supported in queries."
        }
    } yield ()
  }


  "equal nothing" - types { implicit conn =>
    for {
      _ <- Entity.i.intSet_?.insert(List(
        (0, None),
        (1, Some(Set(int1, int2))),
      )).transact

      // Match non-asserted attribute (null) with tacit attribute
      _ <- Entity.i.intSet_().query.get.map(_ ==> List(0))

      // Can't query for empty attribute
      _ <- Entity.i.intSet().query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Applying nothing to mandatory attribute (Entity.intSet) is reserved for updates to retract."
        }
    } yield ()
  }
}