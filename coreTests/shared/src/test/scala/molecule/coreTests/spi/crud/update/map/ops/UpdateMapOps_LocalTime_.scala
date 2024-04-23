// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.LocalTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_LocalTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).localTimeMap(Map(plocalTime1, plocalTime2)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).localTimeMap(Map(plocalTime2, plocalTime3)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime2, plocalTime3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").localTimeMap(Map(plocalTime3, plocalTime4)).update.transact
        _ <- Ns.i.s.localTimeMap.query.get.map(_.head ==> (42, "foo", Map(plocalTime3, plocalTime4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).localTimeMap(Map.empty[String, LocalTime]).update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).localTimeMap(Map(plocalTime1, plocalTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).localTimeMap().update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).localTimeMap.add("a" -> localTime0).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map("a" -> localTime0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).localTimeMap.add("a" -> localTime0).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map("a" -> localTime0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).localTimeMap.add("a" -> localTime1).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1)) // "a" -> localTime1

        // Add new pair
        _ <- Ns(id).localTimeMap.add(plocalTime2).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))

        // Add multiple pairs with varargs
        _ <- Ns(id).localTimeMap.add(plocalTime3, plocalTime4).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).localTimeMap.add(List(plocalTime5, plocalTime6)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).localTimeMap.add(Vector.empty[(String, LocalTime)]).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).localTimeMap.remove(string1).update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).localTimeMap.add(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6, plocalTime7).update.transact

        // Remove pair by String key
        _ <- Ns(id).localTimeMap.remove(string7).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).localTimeMap.remove(string9).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).localTimeMap.remove(string6, string6).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).localTimeMap.remove(string4, string5).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).localTimeMap.remove(List(string2, string3)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).localTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).localTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.localTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
