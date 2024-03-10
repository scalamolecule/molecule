// GENERATED CODE ********************************
package molecule.coreTests.spi.crud.update.set.ops

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait UpdateSetOps_ref_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply (replace/add all)" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1, ref2)).save.transact.map(_.id)
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Applying Set of values replaces previous Set
        _ <- Ns(id).refs(Set(ref3, ref4)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref3, ref4))

        // Applying empty Set of values deletes previous Set
        _ <- Ns(id).refs(Seq.empty[String]).update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)

        id <- Ns.refs(Set(ref1, ref2)).save.transact.map(_.id)
        // Applying empty value deletes previous Set
        _ <- Ns(id).refs().update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1)).save.transact.map(_.id)

        // Add value
        _ <- Ns(id).refs.add(ref2).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Adding existing value has no effect (Set semantics of only unique values)
        _ <- Ns(id).refs.add(ref2).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Add multiple values (vararg)
        _ <- Ns(id).refs.add(ref3, ref4).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4))

        // Add Iterable of values (existing values unaffected)
        // Seq
        _ <- Ns(id).refs.add(Seq(ref4, ref5)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))
        // Set
        _ <- Ns(id).refs.add(Set(ref6)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6))
        // Iterable
        _ <- Ns(id).refs.add(Iterable(ref7)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7))

        // Adding empty Iterable of values has no effect
        _ <- Ns(id).refs.add(Seq.empty[String]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1, ref2, ref3, ref4, ref5, ref6)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).refs.remove(ref6).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))

        // Removing non-existing value has no effect
        _ <- Ns(id).refs.remove(ref7).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).refs.remove(ref5, ref5).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4))

        // Remove multiple values (vararg)
        _ <- Ns(id).refs.remove(ref3, ref4).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2))

        // Remove Iterable of values
        _ <- Ns(id).refs.remove(Seq(ref2)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))

        // Removing empty Iterable of values has no effect
        _ <- Ns(id).refs.remove(Seq.empty[String]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))

        // Removing all elements retracts the attribute
        _ <- Ns(id).refs.remove(Seq(ref1)).update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
