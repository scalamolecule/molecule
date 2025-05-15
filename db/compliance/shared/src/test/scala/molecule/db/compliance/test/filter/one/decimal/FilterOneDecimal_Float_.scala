// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.decimal

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterOneDecimal_Float_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ceil/floor" - types { implicit conn =>
    for {
      _ <- Entity.float.insert(
        -float2, // -2.2
        -float1, // -1.1
        float0, //   0.0
        float1, //   1.1
        float2 //    2.2
      ).transact

      // To avoid changing type, Molecule returns whole decimal numbers

      _ <- Entity.float.ceil.query.get.map(_.sorted ==> List(
        -float20, // -2.0
        -float10, // -1.0
        float0, //    0.0
        float20, //   2.0
        float30, //   3.0
      ))

      _ <- Entity.float.floor.query.get.map(_.sorted ==> List(
        -float30, // -3.0
        -float20, // -2.0
        float0, //    0.0
        float10, //   1.0
        float20, //   2.0
      ))
    } yield ()
  }
}