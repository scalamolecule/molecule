package molecule.db.compliance.test.bind

import molecule.db.base.error.ModelError
import molecule.db.compliance.domains.dsl.Types.Entity
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


  "Runtime input type checking" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1, 2, 3).transact

      eq = Entity.int(?).query

      // Ok
      _ <- eq(1).get.map(_ ==> List(1))

      // Expects Int, not String
      _ <- eq("1").get.map(_ ==> "Unexpected success").recover {
        case ModelError(error) =>
          error ==> "First bind value `1` is of type String but should be of type Int."
      }
    } yield ()
  }


  "Mutations don't support bind params" - types { implicit conn =>
    interceptMessage[ModelError](
      "Save action does not support bind parameters."
    )(Entity.i(?).save)

    interceptMessage[ModelError](
      "Insert action does not support bind parameters."
    )(Entity.i(?).insert)

    interceptMessage[ModelError](
      "Update action does not support bind parameters."
    )(Entity(42).i(?).update)

    interceptMessage[ModelError](
      "Upsert action does not support bind parameters."
    )(Entity(42).i(?).upsert)

    interceptMessage[ModelError](
      "Delete action does not support bind parameters."
    )(Entity.i(?).delete)
  }
}
