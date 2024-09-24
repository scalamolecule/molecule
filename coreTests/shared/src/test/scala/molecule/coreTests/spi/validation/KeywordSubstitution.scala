package molecule.coreTests.spi.validation

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait KeywordSubstitution extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Colliding keyword in sql correctly resolved" - types { implicit conn =>
      import molecule.coreTests.dataModels.dsl.Types._
      for {
        _ <- Other.select(1).save.transact
        _ <- Other.select.query.get.map(_ ==> List(1))
      } yield ()
    }
  }
}
