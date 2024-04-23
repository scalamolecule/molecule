// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.OffsetTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_OffsetTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).offsetTimeMap(Map(poffsetTime1, poffsetTime2)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).offsetTimeMap(Map(poffsetTime2, poffsetTime3)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime2, poffsetTime3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").offsetTimeMap(Map(poffsetTime3, poffsetTime4)).update.transact
        _ <- Ns.i.s.offsetTimeMap.query.get.map(_.head ==> (42, "foo", Map(poffsetTime3, poffsetTime4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).offsetTimeMap(Map.empty[String, OffsetTime]).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).offsetTimeMap(Map(poffsetTime1, poffsetTime2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).offsetTimeMap().update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).offsetTimeMap.add("a" -> offsetTime0).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map("a" -> offsetTime0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).offsetTimeMap.add("a" -> offsetTime0).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map("a" -> offsetTime0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).offsetTimeMap.add("a" -> offsetTime1).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1)) // "a" -> offsetTime1

        // Add new pair
        _ <- Ns(id).offsetTimeMap.add(poffsetTime2).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))

        // Add multiple pairs with varargs
        _ <- Ns(id).offsetTimeMap.add(poffsetTime3, poffsetTime4).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).offsetTimeMap.add(List(poffsetTime5, poffsetTime6)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).offsetTimeMap.add(Vector.empty[(String, OffsetTime)]).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).offsetTimeMap.remove(string1).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).offsetTimeMap.add(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6, poffsetTime7).update.transact

        // Remove pair by String key
        _ <- Ns(id).offsetTimeMap.remove(string7).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).offsetTimeMap.remove(string9).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).offsetTimeMap.remove(string6, string6).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).offsetTimeMap.remove(string4, string5).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).offsetTimeMap.remove(List(string2, string3)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).offsetTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).offsetTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.offsetTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
