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
        _ <- Ns(id).refs(Set.empty[String]).update.transact
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

        // Add multiple values (Seq)
        _ <- Ns(id).refs.add(Seq(ref5, ref6)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6))

        // Adding empty Seq of values has no effect
        _ <- Ns(id).refs.add(Seq.empty[String]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      for {
        id <- Ns.refs(Set(ref1, ref2, ref3, ref4, ref5, ref6, ref7)).save.transact.map(_.id)

        // Remove value
        _ <- Ns(id).refs.remove(ref7).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6))

        // Removing non-existing value has no effect
        _ <- Ns(id).refs.remove(ref9).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5, ref6))

        // Removing duplicate values removes the distinct value
        _ <- Ns(id).refs.remove(ref6, ref6).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3, ref4, ref5))

        // Remove multiple values (vararg)
        _ <- Ns(id).refs.remove(ref4, ref5).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1, ref2, ref3))

        // Remove multiple values (Seq)
        _ <- Ns(id).refs.remove(Seq(ref2, ref3)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))

        // Removing empty Seq of values has no effect
        _ <- Ns(id).refs.remove(Seq.empty[String]).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref1))

        // Removing all remaining elements deletes the attribute
        _ <- Ns(id).refs.remove(Seq(ref1)).update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)
      } yield ()
    }
  }
}
