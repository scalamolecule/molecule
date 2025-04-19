package molecule.coreTests.spi.validation

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.setup.*
import scala.language.implicitConversions

case class KeywordSubstitution(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Colliding keyword in sql correctly resolved" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types.*
    for {
      _ <- Other.select(1).save.transact
      _ <- Other.select.query.get.map(_ ==> List(1))
    } yield ()
  }
}
