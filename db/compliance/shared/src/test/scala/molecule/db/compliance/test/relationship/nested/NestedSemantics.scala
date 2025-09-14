package molecule.db.compliance.test.relationship.nested

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders


case class NestedSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Nested entity must match" - refs {
    for {
      _ <- A.i.Bb.*(E.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "`Bb` can only nest to `B`. Found: `E`"
        }

      _ <- A.i.Bb.*?(E.i).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "`Bb` can only nest to `B`. Found: `E`"
        }
    } yield ()
  }


  "Mixing *?/* not allowed" - refs {
    for {
      _ <- A.i.Bb.*(B.i.Cc.*?(C.i)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't mix mandatory/optional nested queries."
        }

      _ <- A.i.Bb.*?(B.i.Cc.*(C.i)).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can't mix mandatory/optional nested queries."
        }
    } yield ()
  }
}
