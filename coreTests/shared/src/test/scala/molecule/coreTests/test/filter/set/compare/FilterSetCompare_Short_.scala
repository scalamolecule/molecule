// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Short_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(short1, short2))
      val b = (2, Set(short2, short3, short4))
      for {
        _ <- Ns.i.shorts.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.shorts.hasLt(short0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts.hasLt(short1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts.hasLt(short2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts.hasLt(short3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.shorts.hasLe(short0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts.hasLe(short1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts.hasLe(short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts.hasLe(short3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.shorts.hasGt(short0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts.hasGt(short1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts.hasGt(short2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.shorts.hasGt(short3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.shorts.hasGe(short0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts.hasGe(short1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts.hasGe(short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts.hasGe(short3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.shorts.insert(List(
          (a, Set(short1, short2)),
          (b, Set(short2, short3, short4))
        )).transact

        // <
        _ <- Ns.i.a1.shorts_.hasLt(short0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_.hasLt(short1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_.hasLt(short2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_.hasLt(short3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.shorts_.hasLe(short0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_.hasLe(short1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_.hasLe(short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_.hasLe(short3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.shorts_.hasGt(short0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_.hasGt(short1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_.hasGt(short2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.shorts_.hasGt(short3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.shorts_.hasGe(short0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_.hasGe(short1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_.hasGe(short2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_.hasGe(short3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(short1, short2)))
      val b = (2, Some(Set(short2, short3, short4)))
      val c = (3, None)
      for {
        _ <- Ns.i.shorts_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.shorts_?.hasLt(Some(short0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.hasLt(Some(short1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.hasLt(Some(short2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.hasLt(Some(short3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.shorts_?.hasLe(Some(short0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.shorts_?.hasLe(Some(short1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.shorts_?.hasLe(Some(short2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasLe(Some(short3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.shorts_?.hasGt(Some(short0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGt(Some(short1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGt(Some(short2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.shorts_?.hasGt(Some(short3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.shorts_?.hasGe(Some(short0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGe(Some(short1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGe(Some(short2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGe(Some(short3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.shorts_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.shorts_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}