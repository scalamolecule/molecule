// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_Char_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.charMap(Map(pchar1, pchar2)).save.transact.map(_.id)
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).charMap(Map(pchar3, pchar4)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar3, pchar4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).charMap(Map.empty[String, Char]).update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)

        id <- Ns.charMap(Map(pchar1, pchar2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).charMap().update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.charMap(Map(pchar1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).charMap.add(pchar2).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).charMap.add(pchar2).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).charMap.add(pchar3, pchar4).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).charMap.add(Seq(pchar5, pchar6)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).charMap.add(Seq.empty[(String, Char)]).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.charMap(Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6, pchar7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).charMap.remove(string7).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))

        // Removing non-existing key has no effect
        _ <- Ns(id).charMap.remove(string9).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5, pchar6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).charMap.remove(string6, string6).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3, pchar4, pchar5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).charMap.remove(string4, string5).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).charMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).charMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).charMap.remove(Seq(string1)).update.transact
        _ <- Ns.charMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
