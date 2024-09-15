package molecule.coreTests.spi.validation

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait KeywordSubstitution extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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
