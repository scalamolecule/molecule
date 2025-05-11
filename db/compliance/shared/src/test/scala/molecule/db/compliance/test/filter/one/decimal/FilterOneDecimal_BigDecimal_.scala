// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.decimal

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class FilterOneDecimal_BigDecimal_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "ceil/floor" - types { implicit conn =>
    for {
      _ <- Entity.bigDecimal.insert(
        -bigDecimal2, // -2.2
        -bigDecimal1, // -1.1
        bigDecimal0, //   0.0
        bigDecimal1, //   1.1
        bigDecimal2 //    2.2
      ).transact

      // To avoid changing type, Molecule returns whole decimal numbers

      _ <- Entity.bigDecimal.ceil.query.get.map(_.sorted ==> List(
        -bigDecimal20, // -2.0
        -bigDecimal10, // -1.0
        bigDecimal0, //    0.0
        bigDecimal20, //   2.0
        bigDecimal30, //   3.0
      ))

      _ <- Entity.bigDecimal.floor.query.get.map(_.sorted ==> List(
        -bigDecimal30, // -3.0
        -bigDecimal20, // -2.0
        bigDecimal0, //    0.0
        bigDecimal10, //   1.0
        bigDecimal20, //   2.0
      ))
    } yield ()
  }
}