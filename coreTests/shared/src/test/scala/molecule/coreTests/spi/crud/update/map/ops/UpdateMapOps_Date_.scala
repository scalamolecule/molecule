// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).dateMap(Map(pdate1, pdate2)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).dateMap(Map(pdate2, pdate3)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate2, pdate3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").dateMap(Map(pdate3, pdate4)).update.transact
        _ <- Ns.i.s.dateMap.query.get.map(_.head ==> (42, "foo", Map(pdate3, pdate4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).dateMap(Map.empty[String, Date]).update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).dateMap(Map(pdate1, pdate2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).dateMap().update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).dateMap.add("a" -> date0).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map("a" -> date0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).dateMap.add("a" -> date0).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map("a" -> date0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).dateMap.add("a" -> date1).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1)) // "a" -> date1

        // Add new pair
        _ <- Ns(id).dateMap.add(pdate2).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))

        // Add multiple pairs with varargs
        _ <- Ns(id).dateMap.add(pdate3, pdate4).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).dateMap.add(List(pdate5, pdate6)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).dateMap.add(Vector.empty[(String, Date)]).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).dateMap.remove(string1).update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).dateMap.add(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6, pdate7).update.transact

        // Remove pair by String key
        _ <- Ns(id).dateMap.remove(string7).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))

        // Removing non-existing key has no effect
        _ <- Ns(id).dateMap.remove(string9).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5, pdate6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).dateMap.remove(string6, string6).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3, pdate4, pdate5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).dateMap.remove(string4, string5).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).dateMap.remove(List(string2, string3)).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).dateMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).dateMap.remove(Seq(string1)).update.transact
        _ <- Ns.dateMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
