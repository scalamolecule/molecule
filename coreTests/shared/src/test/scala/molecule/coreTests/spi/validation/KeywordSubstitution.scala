package molecule.coreTests.spi.validation

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.setup._
import scala.language.implicitConversions

case class KeywordSubstitution(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Colliding keyword in sql correctly resolved" - types { implicit conn =>
    import molecule.coreTests.domains.dsl.Types._
    for {
      _ <- Other.select(1).save.transact
      _ <- Other.select.query.get.map(_ ==> List(1))
    } yield ()
  }
}
