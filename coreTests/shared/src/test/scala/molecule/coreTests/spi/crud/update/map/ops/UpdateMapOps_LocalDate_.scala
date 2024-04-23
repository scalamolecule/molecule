// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).localDateMap(Map(plocalDate1, plocalDate2)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).localDateMap(Map(plocalDate2, plocalDate3)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate2, plocalDate3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").localDateMap(Map(plocalDate3, plocalDate4)).update.transact
        _ <- Ns.i.s.localDateMap.query.get.map(_.head ==> (42, "foo", Map(plocalDate3, plocalDate4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).localDateMap(Map.empty[String, LocalDate]).update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).localDateMap(Map(plocalDate1, plocalDate2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).localDateMap().update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).localDateMap.add("a" -> localDate0).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map("a" -> localDate0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).localDateMap.add("a" -> localDate0).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map("a" -> localDate0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).localDateMap.add("a" -> localDate1).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1)) // "a" -> localDate1

        // Add new pair
        _ <- Ns(id).localDateMap.add(plocalDate2).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))

        // Add multiple pairs with varargs
        _ <- Ns(id).localDateMap.add(plocalDate3, plocalDate4).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).localDateMap.add(List(plocalDate5, plocalDate6)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).localDateMap.add(Vector.empty[(String, LocalDate)]).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).localDateMap.remove(string1).update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).localDateMap.add(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6, plocalDate7).update.transact

        // Remove pair by String key
        _ <- Ns(id).localDateMap.remove(string7).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))

        // Removing non-existing key has no effect
        _ <- Ns(id).localDateMap.remove(string9).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).localDateMap.remove(string6, string6).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).localDateMap.remove(string4, string5).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).localDateMap.remove(List(string2, string3)).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).localDateMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).localDateMap.remove(Seq(string1)).update.transact
        _ <- Ns.localDateMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
