// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).durationMap(Map(pduration1, pduration2)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).durationMap(Map(pduration2, pduration3)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration2, pduration3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").durationMap(Map(pduration3, pduration4)).update.transact
        _ <- Ns.i.s.durationMap.query.get.map(_.head ==> (42, "foo", Map(pduration3, pduration4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).durationMap(Map.empty[String, Duration]).update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).durationMap(Map(pduration1, pduration2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).durationMap().update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).durationMap.add("a" -> duration0).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map("a" -> duration0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).durationMap.add("a" -> duration0).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map("a" -> duration0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).durationMap.add("a" -> duration1).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1)) // "a" -> duration1

        // Add new pair
        _ <- Ns(id).durationMap.add(pduration2).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))

        // Add multiple pairs with varargs
        _ <- Ns(id).durationMap.add(pduration3, pduration4).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).durationMap.add(List(pduration5, pduration6)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).durationMap.add(Vector.empty[(String, Duration)]).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).durationMap.remove(string1).update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).durationMap.add(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6, pduration7).update.transact

        // Remove pair by String key
        _ <- Ns(id).durationMap.remove(string7).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))

        // Removing non-existing key has no effect
        _ <- Ns(id).durationMap.remove(string9).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5, pduration6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).durationMap.remove(string6, string6).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3, pduration4, pduration5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).durationMap.remove(string4, string5).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).durationMap.remove(List(string2, string3)).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).durationMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).durationMap.remove(Seq(string1)).update.transact
        _ <- Ns.durationMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
