package molecule.coreTests.spi.filterAttr.one

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class Semantics(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

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
