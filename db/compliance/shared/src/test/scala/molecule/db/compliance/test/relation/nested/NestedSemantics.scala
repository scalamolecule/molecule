package molecule.db.compliance.test.relation.nested

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Refs.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*


case class NestedSemantics(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Nested entity must match" - refs { implicit conn =>
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


  "Mixing *?/* not allowed" - refs { implicit conn =>
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
