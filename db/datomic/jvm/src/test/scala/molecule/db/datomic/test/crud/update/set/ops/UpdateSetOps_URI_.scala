// GENERATED CODE ********************************
package molecule.db.datomic.test.crud.update.set.ops

import java.net.URI
import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateSetOps_URI_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      val eid = Ns.uris(Set(uri1, uri2)).save.transact.eids.head

      Ns(eid).uris(Set(uri3, uri4)).update.transact
      Ns.uris.query.get.head ==> Set(uri3, uri4)

      // Apply Seq of values
      Ns(eid).uris(Set(uri4, uri5)).update.transact
      Ns.uris.query.get.head ==> Set(uri4, uri5)

      // Apply empty Seq of values (deleting all values!)
      Ns(eid).uris(Seq.empty[URI]).update.transact
      Ns.uris.query.get ==> Nil

      Ns(eid).uris(Set(uri1, uri2)).update.transact

      // Delete all (apply no values)
      Ns(eid).uris().update.transact
      Ns.uris.query.get ==> Nil
    }


    "add" - types { implicit conn =>
      val eid = Ns.uris(Set(uri1)).save.transact.eids.head

      // Add value
      Ns(eid).uris.add(uri2).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2)

      // Add existing value (no effect)
      Ns(eid).uris.add(uri2).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2)

      // Add multiple values (vararg)
      Ns(eid).uris.add(uri3, uri4).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4)

      // Add Iterable of values (existing values unaffected)
      // Seq
      Ns(eid).uris.add(Seq(uri4, uri5)).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5)
      // Set
      Ns(eid).uris.add(Set(uri6)).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6)
      // Iterable
      Ns(eid).uris.add(Iterable(uri7)).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7)

      // Add empty Seq of values (no effect)
      Ns(eid).uris.add(Seq.empty[URI]).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri6, uri7)
    }


    "swap" - types { implicit conn =>
      val eid = Ns.uris(Set(uri1, uri2, uri3, uri4, uri5, uri6)).save.transact.eids.head

      // Replace value
      Ns(eid).uris.swap(uri6 -> uri8).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5, uri8)

      // Replacing value to existing value simply deletes it
      Ns(eid).uris.swap(uri5 -> uri8).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri8)

      // Replace multiple values (vararg)
      Ns(eid).uris.swap(uri3 -> uri6, uri4 -> uri7).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri6, uri7, uri8)

      // Missing old value has no effect. The new value is inserted (upsert semantics)
      Ns(eid).uris.swap(uri4 -> uri9).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri6, uri7, uri8, uri9)

      // Replace with Seq of oldValue->newValue pairs
      Ns(eid).uris.swap(Seq(uri2 -> uri5)).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri5, uri6, uri7, uri8, uri9)

      // Replacing with empty Seq of oldValue->newValue pairs has no effect
      Ns(eid).uris.swap(Seq.empty[(URI, URI)]).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri5, uri6, uri7, uri8, uri9)


      // Can't swap duplicate from/to values
      intercept[MoleculeException](
        Ns(42).uris.swap(uri1 -> uri2, uri1 -> uri3).update.transact
      ).message ==> "Can't swap from duplicate retract values."

      intercept[MoleculeException](
        Ns(42).uris.swap(uri1 -> uri3, uri2 -> uri3).update.transact
      ).message ==> "Can't swap to duplicate replacement values."
    }


    "remove" - types { implicit conn =>
      val eid = Ns.uris(Set(uri1, uri2, uri3, uri4, uri5, uri6)).save.transact.eids.head

      // Retract value
      Ns(eid).uris.remove(uri6).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5)

      // Retracting non-existing value has no effect
      Ns(eid).uris.remove(uri7).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4, uri5)

      // Retracting duplicate values removes the distinct value
      Ns(eid).uris.remove(uri5, uri5).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2, uri3, uri4)

      // Retract multiple values (vararg)
      Ns(eid).uris.remove(uri3, uri4).update.transact
      Ns.uris.query.get.head ==> Set(uri1, uri2)

      // Retract Seq of values
      Ns(eid).uris.remove(Seq(uri2)).update.transact
      Ns.uris.query.get.head ==> Set(uri1)

      // Retracting empty Seq of values has no effect
      Ns(eid).uris.remove(Seq.empty[URI]).update.transact
      Ns.uris.query.get.head ==> Set(uri1)
    }
  }
}
