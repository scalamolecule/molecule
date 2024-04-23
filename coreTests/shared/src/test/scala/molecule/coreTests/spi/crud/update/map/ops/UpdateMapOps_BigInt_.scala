// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_BigInt_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).bigIntMap(Map(pbigInt1, pbigInt2)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).bigIntMap(Map(pbigInt2, pbigInt3)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt2, pbigInt3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").bigIntMap(Map(pbigInt3, pbigInt4)).update.transact
        _ <- Ns.i.s.bigIntMap.query.get.map(_.head ==> (42, "foo", Map(pbigInt3, pbigInt4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).bigIntMap(Map.empty[String, BigInt]).update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).bigIntMap(Map(pbigInt1, pbigInt2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).bigIntMap().update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).bigIntMap.add("a" -> bigInt0).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map("a" -> bigInt0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).bigIntMap.add("a" -> bigInt0).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map("a" -> bigInt0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).bigIntMap.add("a" -> bigInt1).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1)) // "a" -> bigInt1

        // Add new pair
        _ <- Ns(id).bigIntMap.add(pbigInt2).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))

        // Add multiple pairs with varargs
        _ <- Ns(id).bigIntMap.add(pbigInt3, pbigInt4).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).bigIntMap.add(List(pbigInt5, pbigInt6)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).bigIntMap.add(Vector.empty[(String, BigInt)]).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).bigIntMap.remove(string1).update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).bigIntMap.add(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6, pbigInt7).update.transact

        // Remove pair by String key
        _ <- Ns(id).bigIntMap.remove(string7).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))

        // Removing non-existing key has no effect
        _ <- Ns(id).bigIntMap.remove(string9).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).bigIntMap.remove(string6, string6).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).bigIntMap.remove(string4, string5).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).bigIntMap.remove(List(string2, string3)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).bigIntMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).bigIntMap.remove(Seq(string1)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
