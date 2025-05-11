package molecule.db.compliance.test.validation

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*


case class KeywordSubstitution(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Colliding keyword in sql correctly resolved" - types { implicit conn =>

    for {
      _ <- Other.select(1).save.transact
      _ <- Other.select.query.get.map(_ ==> List(1))
    } yield ()
  }
}
