// GENERATED CODE ********************************
package molecule.datomic.test.crud.update.set.ops

import java.net.URI
import molecule.base.util.exceptions.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.async._
import utest._

object UpdateSetOps_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        eid <- Ns.uris(Set(uri1, uri2)).save.transact.map(_.eids.head)

        _ <- Ns(eid).uris(Set(uri3, uri4)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri3, uri4))

        // Apply Seq of values
        _ <- Ns(eid).uris(Set(uri4, uri5)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri4, uri5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(eid).uris(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)

        _ <- Ns(eid).uris(Set(uri1, uri2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(eid).uris().update.transact
        _ <- Ns.uris.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        eid <- Ns.uris(Set(uri1)).save.transact.map(_.eids.head)

        // Add value
        _ <- Ns(eid).uris.add(uri2).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Add existing value (no effect)
        _ <- Ns(eid).uris.add(uri2).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Add multiple values (vararg)
        _ <- Ns(eid).uris.add(uri3, uri4).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(eid).uris.add(Seq(uri4, uri5)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))
        // Set
        _ <- Ns(eid).uris.add(Set(uri6)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6))
        // Iterable
        _ <- Ns(eid).uris.add(Iterable(uri7)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7))

        // Add empty Seq of values (no effect)
        _ <- Ns(eid).uris.add(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7))
      } yield ()
    }


    "swap" - types { implicit conn =>
      for {
        eid <- Ns.uris(Set(uri1, uri2, uri3, uri4, uri5, uri6)).save.transact.map(_.eids.head)

        // Replace value
        _ <- Ns(eid).uris.swap(uri6 -> uri8).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri8))

        // Replacing value to existing value simply deletes it
        _ <- Ns(eid).uris.swap(uri5 -> uri8).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri8))

        // Replace multiple values (vararg)
        _ <- Ns(eid).uris.swap(uri3 -> uri6, uri4 -> uri7).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri6, uri7, uri8))

        // Missing old value has no effect. The new value is inserted (upsert semantics)
        _ <- Ns(eid).uris.swap(uri4 -> uri9).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri6, uri7, uri8, uri9))

        // Replace with Seq of oldValue->newValue pairs
        _ <- Ns(eid).uris.swap(Seq(uri2 -> uri5)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri5, uri6, uri7, uri8, uri9))

        // Replacing with empty Seq of oldValue->newValue pairs has no effect
        _ <- Ns(eid).uris.swap(Seq.empty[(URI, URI)]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri5, uri6, uri7, uri8, uri9))


        // Can't swap duplicate from/to values
        _ <- Ns(42).uris.swap(uri1 -> uri2, uri1 -> uri3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can't swap from duplicate retract values."
        }

        _ <- Ns(42).uris.swap(uri1 -> uri3, uri2 -> uri3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err, _) =>
          err ==> "Can't swap to duplicate replacement values."
        }
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        eid <- Ns.uris(Set(uri1, uri2, uri3, uri4, uri5, uri6)).save.transact.map(_.eids.head)

        // Retract value
        _ <- Ns(eid).uris.remove(uri6).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))

        // Retracting non-existing value has no effect
        _ <- Ns(eid).uris.remove(uri7).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4, uri5))

        // Retracting duplicate values removes the distinct value
        _ <- Ns(eid).uris.remove(uri5, uri5).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2, uri3, uri4))

        // Retract multiple values (vararg)
        _ <- Ns(eid).uris.remove(uri3, uri4).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))

        // Retract Seq of values
        _ <- Ns(eid).uris.remove(Seq(uri2)).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1))

        // Retracting empty Seq of values has no effect
        _ <- Ns(eid).uris.remove(Seq.empty[URI]).update.transact
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1))
      } yield ()
    }
  }
}
