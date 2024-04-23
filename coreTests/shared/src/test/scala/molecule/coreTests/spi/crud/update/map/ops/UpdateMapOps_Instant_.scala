// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.Instant
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Instant_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).instantMap(Map(pinstant1, pinstant2)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).instantMap(Map(pinstant2, pinstant3)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant2, pinstant3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").instantMap(Map(pinstant3, pinstant4)).update.transact
        _ <- Ns.i.s.instantMap.query.get.map(_.head ==> (42, "foo", Map(pinstant3, pinstant4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).instantMap(Map.empty[String, Instant]).update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).instantMap(Map(pinstant1, pinstant2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).instantMap().update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).instantMap.add("a" -> instant0).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map("a" -> instant0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).instantMap.add("a" -> instant0).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map("a" -> instant0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).instantMap.add("a" -> instant1).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1)) // "a" -> instant1

        // Add new pair
        _ <- Ns(id).instantMap.add(pinstant2).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))

        // Add multiple pairs with varargs
        _ <- Ns(id).instantMap.add(pinstant3, pinstant4).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).instantMap.add(List(pinstant5, pinstant6)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).instantMap.add(Vector.empty[(String, Instant)]).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).instantMap.remove(string1).update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).instantMap.add(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6, pinstant7).update.transact

        // Remove pair by String key
        _ <- Ns(id).instantMap.remove(string7).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Removing non-existing key has no effect
        _ <- Ns(id).instantMap.remove(string9).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).instantMap.remove(string6, string6).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).instantMap.remove(string4, string5).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).instantMap.remove(List(string2, string3)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).instantMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).instantMap.remove(Seq(string1)).update.transact
        _ <- Ns.instantMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
