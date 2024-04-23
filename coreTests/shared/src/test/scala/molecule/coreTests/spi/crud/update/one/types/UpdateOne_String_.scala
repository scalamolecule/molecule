// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.one.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateOne_String_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>


  override lazy val tests = Tests {

    "Semantics" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Attribute not yet asserted
        _ <- Ns.string.query.get.map(_ ==> Nil)

        // Applying value to non-asserted attribute adds the attribute in the update
        _ <- Ns(id).string(string1).update.transact
        _ <- Ns.string.query.get.map(_.head ==> string1)

        // Update value to current value doesn't change anything
        _ <- Ns(id).string(string1).update.transact
        _ <- Ns.string.query.get.map(_.head ==> string1)

        // Update value
        _ <- Ns(id).string(string2).update.transact
        _ <- Ns.string.query.get.map(_.head ==> string2)

        // Add new attribute and update value in one go
        _ <- Ns(id).s("foo").string(string3).update.transact
        _ <- Ns.i.s.string.query.get.map(_.head ==> (42, "foo", string3))

        // Apply nothing to delete value
        _ <- Ns(id).string().update.transact
        _ <- Ns.string.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }
  }
}
