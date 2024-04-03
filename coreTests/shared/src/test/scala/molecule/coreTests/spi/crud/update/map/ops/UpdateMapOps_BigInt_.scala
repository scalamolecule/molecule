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
        id <- Ns.bigIntMap(Map(pbigInt1, pbigInt2)).save.transact.map(_.id)
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).bigIntMap(Map(pbigInt3, pbigInt4)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt3, pbigInt4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).bigIntMap(Map.empty[String, BigInt]).update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)

        id <- Ns.bigIntMap(Map(pbigInt1, pbigInt2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).bigIntMap().update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.bigIntMap(Map("a" -> bigInt0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).bigIntMap.add("a" -> bigInt1).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.bigIntMap.iMap_?.query.get.map(_ ==> List((Map(pbigInt1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.bigIntMap.iMap_?.query.get.map(_ ==> List((Map(pbigInt1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).bigIntMap.add(pbigInt2).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).bigIntMap.add(pbigInt3, pbigInt4).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).bigIntMap.add(Seq(pbigInt5, pbigInt6)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).bigIntMap.add(Seq.empty[(String, BigInt)]).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.bigIntMap(Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6, pbigInt7, pbigInt8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).bigIntMap.remove(string8).update.transact
        _ <- Ns(id).bigIntMap.remove(string7).upsert.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.bigIntMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.bigIntMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).bigIntMap.remove(string9).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).bigIntMap.remove(string6, string6).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).bigIntMap.remove(string4, string5).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).bigIntMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).bigIntMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).bigIntMap.remove(Seq(string1)).update.transact
        _ <- Ns.bigIntMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
