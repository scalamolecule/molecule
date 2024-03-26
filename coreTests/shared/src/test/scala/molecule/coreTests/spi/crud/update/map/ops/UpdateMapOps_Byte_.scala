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
        id <- Ns.byteMap(Map(pbyte1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).byteMap.add(pbyte2).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
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
        id <- Ns.byteMap(Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6, pbyte7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).byteMap.remove(string7).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Removing non-existing key has no effect
        _ <- Ns(id).byteMap.remove(string9).update.transact
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6))

        // Removing duplicate keys removes the distinct pair
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
