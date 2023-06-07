// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Double_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(double1, double2))
      val b = (2, Set(double2, double3, double4))
      for {
        _ <- Ns.i.doubles.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.doubles.hasLt(double0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles.hasLt(double1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles.hasLt(double2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.doubles.hasLt(double3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.doubles.hasLe(double0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles.hasLe(double1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.doubles.hasLe(double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles.hasLe(double3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.doubles.hasGt(double0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles.hasGt(double1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles.hasGt(double2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.doubles.hasGt(double3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.doubles.hasGe(double0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles.hasGe(double1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles.hasGe(double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles.hasGe(double3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.doubles.insert(List(
          (a, Set(double1, double2)),
          (b, Set(double2, double3, double4))
        )).transact

        // <
        _ <- Ns.i.a1.doubles_.hasLt(double0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles_.hasLt(double1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles_.hasLt(double2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.doubles_.hasLt(double3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.doubles_.hasLe(double0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles_.hasLe(double1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.doubles_.hasLe(double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_.hasLe(double3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.doubles_.hasGt(double0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_.hasGt(double1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_.hasGt(double2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.doubles_.hasGt(double3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.doubles_.hasGe(double0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_.hasGe(double1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_.hasGe(double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_.hasGe(double3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(double1, double2)))
      val b = (2, Some(Set(double2, double3, double4)))
      val c = (3, None)
      for {
        _ <- Ns.i.doubles_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.doubles_?.hasLt(Some(double0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles_?.hasLt(Some(double1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles_?.hasLt(Some(double2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.doubles_?.hasLt(Some(double3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.doubles_?.hasLe(Some(double0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.doubles_?.hasLe(Some(double1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.doubles_?.hasLe(Some(double2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasLe(Some(double3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.doubles_?.hasGt(Some(double0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGt(Some(double1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGt(Some(double2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.doubles_?.hasGt(Some(double3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.doubles_?.hasGe(Some(double0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGe(Some(double1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGe(Some(double2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGe(Some(double3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.doubles_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.doubles_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}