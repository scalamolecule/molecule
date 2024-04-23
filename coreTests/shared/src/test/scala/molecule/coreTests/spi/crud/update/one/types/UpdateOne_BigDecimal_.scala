// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.one.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateOne_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Attribute not yet asserted
        _ <- Ns.bigDecimal.query.get.map(_ ==> Nil)

        // Applying value to non-asserted attribute adds the attribute in the update
        _ <- Ns(id).bigDecimal(bigDecimal1).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal1)

        // Update value to current value doesn't change anything
        _ <- Ns(id).bigDecimal(bigDecimal1).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal1)

        // Update value
        _ <- Ns(id).bigDecimal(bigDecimal2).update.transact
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal2)

        // Add new attribute and update value in one go
        _ <- Ns(id).s("foo").bigDecimal(bigDecimal3).update.transact
        _ <- Ns.i.s.bigDecimal.query.get.map(_.head ==> (42, "foo", bigDecimal3))

        // Apply nothing to delete value
        _ <- Ns(id).bigDecimal().update.transact
        _ <- Ns.bigDecimal.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }
  }
}
