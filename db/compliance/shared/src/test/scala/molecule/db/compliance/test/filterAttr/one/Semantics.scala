package molecule.db.compliance.test.filterAttr.one

import molecule.db.base.error.ModelError
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Semantics(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Missing filter attributes" - types { implicit conn =>
    for {
      _ <- Entity.int(Ref.int_).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Please add missing filter attribute Ref.int"
        }
    } yield ()
  }


  "No expression in cross-ns definition" - types { implicit conn =>
    for {
      _ <- Entity.s.i(Ref.int_.not(3)).Ref.int.query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Filtering inside cross-entity attribute filter not allowed."
        }
    } yield ()
  }


  "Can't filter by same attribute" - types { implicit conn =>
    for {
      _ <- Entity.s.i(Entity.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't filter by the same attribute `Entity.i`"
        }
    } yield ()
  }
}
