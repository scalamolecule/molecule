// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.one.types

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateOne_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Attribute not yet asserted
        _ <- Ns.zonedDateTime.query.get.map(_ ==> Nil)

        // Applying value to non-asserted attribute adds the attribute in the update
        _ <- Ns(id).zonedDateTime(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime1)

        // Update value to current value doesn't change anything
        _ <- Ns(id).zonedDateTime(zonedDateTime1).update.transact
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime1)

        // Update value
        _ <- Ns(id).zonedDateTime(zonedDateTime2).update.transact
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime2)

        // Add new attribute and update value in one go
        _ <- Ns(id).s("foo").zonedDateTime(zonedDateTime3).update.transact
        _ <- Ns.i.s.zonedDateTime.query.get.map(_.head ==> (42, "foo", zonedDateTime3))

        // Apply nothing to delete value
        _ <- Ns(id).zonedDateTime().update.transact
        _ <- Ns.zonedDateTime.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }
  }
}
