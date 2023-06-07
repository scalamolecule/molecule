// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_ref_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(ref1, ref2))
      val b = (2, Set(ref2, ref3, ref4))
      for {
        _ <- Ns.i.refs.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.refs.hasLt(ref0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs.hasLt(ref1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs.hasLt(ref2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.refs.hasLt(ref3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.refs.hasLe(ref0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs.hasLe(ref1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.refs.hasLe(ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs.hasLe(ref3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.refs.hasGt(ref0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs.hasGt(ref1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs.hasGt(ref2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.refs.hasGt(ref3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.refs.hasGe(ref0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs.hasGe(ref1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs.hasGe(ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs.hasGe(ref3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.refs.insert(List(
          (a, Set(ref1, ref2)),
          (b, Set(ref2, ref3, ref4))
        )).transact

        // <
        _ <- Ns.i.a1.refs_.hasLt(ref0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs_.hasLt(ref1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs_.hasLt(ref2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.refs_.hasLt(ref3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.refs_.hasLe(ref0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs_.hasLe(ref1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.refs_.hasLe(ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_.hasLe(ref3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.refs_.hasGt(ref0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_.hasGt(ref1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_.hasGt(ref2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.refs_.hasGt(ref3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.refs_.hasGe(ref0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_.hasGe(ref1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_.hasGe(ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_.hasGe(ref3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(ref1, ref2)))
      val b = (2, Some(Set(ref2, ref3, ref4)))
      val c = (3, None)
      for {
        _ <- Ns.i.refs_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.refs_?.hasLt(Some(ref0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs_?.hasLt(Some(ref1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs_?.hasLt(Some(ref2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.refs_?.hasLt(Some(ref3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.refs_?.hasLe(Some(ref0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.refs_?.hasLe(Some(ref1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.refs_?.hasLe(Some(ref2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasLe(Some(ref3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.refs_?.hasGt(Some(ref0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGt(Some(ref1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGt(Some(ref2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.refs_?.hasGt(Some(ref3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.refs_?.hasGe(Some(ref0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGe(Some(ref1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGe(Some(ref2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGe(Some(ref3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.refs_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.refs_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}