// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.decimal

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.*

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