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
        id <- Ns.byteMap(Map(pbyte1, pbyte2)).save.transact.map(_.id)
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).byteMap(Map(pbyte3, pbyte4)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte3, pbyte4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).byteMap(Map.empty[String, Byte]).update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)

        id <- Ns.byteMap(Map(pbyte1, pbyte2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).byteMap().update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.byteMap(Map("a" -> byte0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).byteMap.add("a" -> byte1).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1))

        // Update doesn't add pair if no map attribute already exists
        _ <- Ns(id).iMap.add("a" -> 1).update.transact
        _ <- Ns.byteMap.iMap_?.query.get.map(_ ==> List((Map(pbyte1), None)))

        // Upsert adds pair to new map attribute if it wasn't already saved
        _ <- Ns(id).iMap.add("a" -> 1).upsert.transact
        _ <- Ns.byteMap.iMap_?.query.get.map(_ ==> List((Map(pbyte1), Some(Map("a" -> 1)))))

        // Add pair
        _ <- Ns(id).byteMap.add(pbyte2).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).byteMap.add(pbyte3, pbyte4).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).byteMap.add(Seq(pbyte5, pbyte6)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).byteMap.add(Seq.empty[(String, Byte)]).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.byteMap(Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6, pbyte7, pbyte8)).save.transact.map(_.id)

        // Remove pair by String key with update and upsert has same semantics
        _ <- Ns(id).byteMap.remove(string8).update.transact
        _ <- Ns(id).byteMap.remove(string7).upsert.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Removing a pair in a non-asserted map attribute has no effect
        _ <- Ns.byteMap.iMap_?.query.get.map(_.head._2 ==> None)
        _ <- Ns(id).iMap.remove("a").update.transact
        _ <- Ns(id).iMap.remove("a").upsert.transact
        _ <- Ns.byteMap.iMap_?.query.get.map(_.head._2 ==> None)

        // Removing non-existing key has no effect
        _ <- Ns(id).byteMap.remove(string9).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Removing duplicate keys removes the distinct key only
        _ <- Ns(id).byteMap.remove(string6, string6).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).byteMap.remove(string4, string5).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).byteMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).byteMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).byteMap.remove(Seq(string1)).update.transact
        _ <- Ns.byteMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
