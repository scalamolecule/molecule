package molecule.db.compliance.test.authorization

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.SocialApp.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality


case class Conditions(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "1 role" - social {
    for {
      _ <- Post.published(true).save.transact


      _ <- Post.published.query.get.map(_ ==> true)


    } yield ()
  }
}
