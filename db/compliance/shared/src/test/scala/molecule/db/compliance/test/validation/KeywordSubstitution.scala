package molecule.db.compliance.test.validation

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*


case class KeywordSubstitution(
  suite: MUnit,
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
