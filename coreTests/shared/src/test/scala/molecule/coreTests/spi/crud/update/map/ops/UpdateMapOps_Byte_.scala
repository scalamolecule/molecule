// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).byteMap(Map(pbyte1, pbyte2)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).byteMap(Map(pbyte2, pbyte3)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte2, pbyte3))

        // Add other attribute and update Map attribute in one go
        _ <- Ns(id).s("foo").byteMap(Map(pbyte3, pbyte4)).update.transact
        _ <- Ns.i.s.byteMap.query.get.map(_.head ==> (42, "foo", Map(pbyte3, pbyte4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).byteMap(Map.empty[String, Byte]).update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).byteMap(Map(pbyte1, pbyte2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).byteMap().update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        // Adding value to non-asserted Map attribute adds the attribute with the update
        _ <- Ns(id).byteMap.add("a" -> byte0).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map("a" -> byte0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).byteMap.add("a" -> byte0).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map("a" -> byte0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).byteMap.add("a" -> byte1).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1)) // "a" -> byte1

        // Add new pair
        _ <- Ns(id).byteMap.add(pbyte2).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))

        // Add multiple pairs with varargs
        _ <- Ns(id).byteMap.add(pbyte3, pbyte4).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).byteMap.add(List(pbyte5, pbyte6)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).byteMap.add(Vector.empty[(String, Byte)]).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).byteMap.remove(string1).update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).byteMap.add(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6, pbyte7).update.transact

        // Remove pair by String key
        _ <- Ns(id).byteMap.remove(string7).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Removing non-existing key has no effect
        _ <- Ns(id).byteMap.remove(string9).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).byteMap.remove(string6, string6).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).byteMap.remove(string4, string5).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).byteMap.remove(List(string2, string3)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).byteMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).byteMap.remove(Seq(string1)).update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
