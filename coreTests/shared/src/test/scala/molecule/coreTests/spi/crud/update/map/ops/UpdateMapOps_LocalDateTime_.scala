// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.map.ops

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateMapOps_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeMap(Map(plocalDateTime1, plocalDateTime2)).save.transact.map(_.id)
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))

        // Applying Map of pairs replaces map
        _ <- Ns(id).localDateTimeMap(Map(plocalDateTime3, plocalDateTime4)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime3, plocalDateTime4))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).localDateTimeMap(Map.empty[String, LocalDateTime]).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)

        id <- Ns.localDateTimeMap(Map(plocalDateTime1, plocalDateTime2)).save.transact.map(_.id)
        // Applying empty value deletes map
        _ <- Ns(id).localDateTimeMap().update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeMap(Map(plocalDateTime1)).save.transact.map(_.id)

        // Add pair
        _ <- Ns(id).localDateTimeMap.add(plocalDateTime2).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))

        // Adding existing pair has no effect (Map semantics of only unique pairs)
        _ <- Ns(id).localDateTimeMap.add(plocalDateTime2).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).localDateTimeMap.add(plocalDateTime3, plocalDateTime4).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).localDateTimeMap.add(Seq(plocalDateTime5, plocalDateTime6)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).localDateTimeMap.add(Seq.empty[(String, LocalDateTime)]).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.localDateTimeMap(Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6, plocalDateTime7)).save.transact.map(_.id)

        // Remove pair by String key
        _ <- Ns(id).localDateTimeMap.remove(string7).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))

        // Removing non-existing key has no effect
        _ <- Ns(id).localDateTimeMap.remove(string9).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6))

        // Removing duplicate keys removes the distinct pair
        _ <- Ns(id).localDateTimeMap.remove(string6, string6).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5))

        // Remove multiple keys (vararg)
        _ <- Ns(id).localDateTimeMap.remove(string4, string5).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3))

        // Remove multiple keys (Seq)
        _ <- Ns(id).localDateTimeMap.remove(Seq(string2, string3)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1))

        // Removing empty Seq of keys has no effect
        _ <- Ns(id).localDateTimeMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1))

        // Removing all remaining keys deletes the attribute
        _ <- Ns(id).localDateTimeMap.remove(Seq(string1)).update.transact
        _ <- Ns.localDateTimeMap.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
