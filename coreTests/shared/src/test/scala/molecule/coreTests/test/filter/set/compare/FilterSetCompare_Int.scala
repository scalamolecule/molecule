package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Int extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(int1, int2))
      val b = (2, Set(int2, int3, int4))
      for {
        _ <- Ns.i.ints.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.ints.hasLt(int0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints.hasLt(int1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints.hasLt(int2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ints.hasLt(int3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.ints.hasLe(int0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints.hasLe(int1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ints.hasLe(int2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints.hasLe(int3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.ints.hasGt(int0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints.hasGt(int1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints.hasGt(int2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.ints.hasGt(int3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.ints.hasGe(int0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints.hasGe(int1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints.hasGe(int2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints.hasGe(int3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.ints.insert(List(
          (a, Set(int1, int2)),
          (b, Set(int2, int3, int4))
        )).transact

        // <
        _ <- Ns.i.a1.ints_.hasLt(int0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints_.hasLt(int1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints_.hasLt(int2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ints_.hasLt(int3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.ints_.hasLe(int0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints_.hasLe(int1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ints_.hasLe(int2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_.hasLe(int3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.ints_.hasGt(int0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_.hasGt(int1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_.hasGt(int2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.ints_.hasGt(int3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.ints_.hasGe(int0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_.hasGe(int1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_.hasGe(int2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_.hasGe(int3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(int1, int2)))
      val b = (2, Some(Set(int2, int3, int4)))
      val c = (3, None)
      for {
        _ <- Ns.i.ints_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.ints_?.hasLt(Some(int0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints_?.hasLt(Some(int1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints_?.hasLt(Some(int2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ints_?.hasLt(Some(int3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.ints_?.hasLe(Some(int0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ints_?.hasLe(Some(int1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ints_?.hasLe(Some(int2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasLe(Some(int3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.ints_?.hasGt(Some(int0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGt(Some(int1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGt(Some(int2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.ints_?.hasGt(Some(int3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.ints_?.hasGe(Some(int0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGe(Some(int1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGe(Some(int2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGe(Some(int3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.ints_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ints_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}