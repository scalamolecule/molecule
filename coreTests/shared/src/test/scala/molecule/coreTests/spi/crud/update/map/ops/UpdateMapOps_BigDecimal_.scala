// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).bigDecimalMap(Map(pbigDecimal1, pbigDecimal2)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).bigDecimalMap(Map(pbigDecimal2, pbigDecimal3)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal2, pbigDecimal3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").bigDecimalMap(Map(pbigDecimal3, pbigDecimal4)).update.transact
        _ <- Ns.i.s.bigDecimalMap.query.get.map(_.head ==> (42, "foo", Map(pbigDecimal3, pbigDecimal4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).bigDecimalMap(Map.empty[String, BigDecimal]).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigDecimalMap(Map(pbigDecimal1, pbigDecimal2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).bigDecimalMap().update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).bigDecimalMap.add("a" -> bigDecimal0).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map("a" -> bigDecimal0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).bigDecimalMap.add("a" -> bigDecimal0).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map("a" -> bigDecimal0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).bigDecimalMap.add("a" -> bigDecimal1).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1)) // "a" -> bigDecimal1

        // Add new pair
        _ <- Ns(id).bigDecimalMap.add(pbigDecimal2).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))

        // Add multiple pairs with varargs
        _ <- Ns(id).bigDecimalMap.add(pbigDecimal3, pbigDecimal4).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).bigDecimalMap.add(List(pbigDecimal5, pbigDecimal6)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).bigDecimalMap.add(Vector.empty[(String, BigDecimal)]).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).bigDecimalMap.remove(string1).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).bigDecimalMap.add(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6, pbigDecimal7).update.transact

        // Remove pair by String key
        _ <- Ns(id).bigDecimalMap.remove(string7).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))

        // Removing non-existing key has no effect
        _ <- Ns(id).bigDecimalMap.remove(string9).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).bigDecimalMap.remove(string6, string6).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).bigDecimalMap.remove(string4, string5).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).bigDecimalMap.remove(List(string2, string3)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).bigDecimalMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).bigDecimalMap.remove(Seq(string1)).update.transact
        _ <- Ns.bigDecimalMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
