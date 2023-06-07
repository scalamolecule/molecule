// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._


trait FilterSetCompare_Boolean extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>


  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val t  = (1, Set(true))
      val f  = (2, Set(false))
      val tf = (3, Set(true, false))
      for {
        _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

        _ <- Ns.i.a1.booleans.hasLt(true).query.get.map(_ ==> List(f, tf))
        _ <- Ns.i.a1.booleans.hasLt(false).query.get.map(_ ==> List())

        _ <- Ns.i.a1.booleans.hasLe(true).query.get.map(_ ==> List(t, f, tf))
        _ <- Ns.i.a1.booleans.hasLe(false).query.get.map(_ ==> List(f, tf))

        _ <- Ns.i.a1.booleans.hasGt(true).query.get.map(_ ==> List())
        _ <- Ns.i.a1.booleans.hasGt(false).query.get.map(_ ==> List(t, tf))

        _ <- Ns.i.a1.booleans.hasGe(true).query.get.map(_ ==> List(t, tf))
        _ <- Ns.i.a1.booleans.hasGe(false).query.get.map(_ ==> List(t, f, tf))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (t, f, tf) = (1, 2, 3)
      for {
        _ <- Ns.i.booleans.insert(List(
          (t, Set(true)),
          (f, Set(false)),
          (tf, Set(true, false))
        )).transact

        _ <- Ns.i.a1.booleans_.hasLt(true).query.get.map(_ ==> List(f, tf))
        _ <- Ns.i.a1.booleans_.hasLt(false).query.get.map(_ ==> List())

        _ <- Ns.i.a1.booleans_.hasLe(true).query.get.map(_ ==> List(t, f, tf))
        _ <- Ns.i.a1.booleans_.hasLe(false).query.get.map(_ ==> List(f, tf))

        _ <- Ns.i.a1.booleans_.hasGt(true).query.get.map(_ ==> List())
        _ <- Ns.i.a1.booleans_.hasGt(false).query.get.map(_ ==> List(t, tf))

        _ <- Ns.i.a1.booleans_.hasGe(true).query.get.map(_ ==> List(t, tf))
        _ <- Ns.i.a1.booleans_.hasGe(false).query.get.map(_ ==> List(t, f, tf))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val t  = (1, Some(Set(true)))
      val f  = (2, Some(Set(false)))
      val tf = (3, Some(Set(true, false)))
      val x  = (4, None)
      for {
        _ <- Ns.i.booleans_?.insert(t, f, tf, x).transact

        // <
        _ <- Ns.i.a1.booleans_?.hasLt(Some(true)).query.get.map(_ ==> List(f, tf))
        _ <- Ns.i.a1.booleans_?.hasLt(Some(false)).query.get.map(_ ==> List())

        // <=
        _ <- Ns.i.a1.booleans_?.hasLe(Some(true)).query.get.map(_ ==> List(t, f, tf))
        _ <- Ns.i.a1.booleans_?.hasLe(Some(false)).query.get.map(_ ==> List(f, tf))

        // >
        _ <- Ns.i.a1.booleans_?.hasGt(Some(true)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.booleans_?.hasGt(Some(false)).query.get.map(_ ==> List(t, tf))

        // >=
        _ <- Ns.i.a1.booleans_?.hasGe(Some(true)).query.get.map(_ ==> List(t, tf))
        _ <- Ns.i.a1.booleans_?.hasGe(Some(false)).query.get.map(_ ==> List(t, f, tf))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.booleans_?.hasLt(None).query.get.map(_ ==> List(t, f, tf))
        _ <- Ns.i.a1.booleans_?.hasGt(None).query.get.map(_ ==> List(t, f, tf))
        _ <- Ns.i.a1.booleans_?.hasLe(None).query.get.map(_ ==> List(t, f, tf))
        _ <- Ns.i.a1.booleans_?.hasGe(None).query.get.map(_ ==> List(t, f, tf))
      } yield ()
    }
  }
}