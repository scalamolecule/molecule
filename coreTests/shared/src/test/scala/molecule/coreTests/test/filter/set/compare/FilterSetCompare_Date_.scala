// GENERATED CODE ********************************
package molecule.coreTests.test.filter.set.compare

import java.util.Date
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSetCompare_Date_ extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, Set(date1, date2))
      val b = (2, Set(date2, date3, date4))
      for {
        _ <- Ns.i.dates.insert(List(a, b)).transact

        // <
        _ <- Ns.i.a1.dates.hasLt(date0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates.hasLt(date1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates.hasLt(date2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.dates.hasLt(date3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.dates.hasLe(date0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates.hasLe(date1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.dates.hasLe(date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates.hasLe(date3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.dates.hasGt(date0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates.hasGt(date1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates.hasGt(date2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.dates.hasGt(date3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.dates.hasGe(date0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates.hasGe(date1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates.hasGe(date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates.hasGe(date3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b) = (1, 2)
      for {
        _ <- Ns.i.dates.insert(List(
          (a, Set(date1, date2)),
          (b, Set(date2, date3, date4))
        )).transact

        // <
        _ <- Ns.i.a1.dates_.hasLt(date0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates_.hasLt(date1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates_.hasLt(date2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.dates_.hasLt(date3).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.dates_.hasLe(date0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates_.hasLe(date1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.dates_.hasLe(date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_.hasLe(date3).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.dates_.hasGt(date0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_.hasGt(date1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_.hasGt(date2).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.dates_.hasGt(date3).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.dates_.hasGe(date0).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_.hasGe(date1).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_.hasGe(date2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_.hasGe(date3).query.get.map(_ ==> List(b))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(Set(date1, date2)))
      val b = (2, Some(Set(date2, date3, date4)))
      val c = (3, None)
      for {
        _ <- Ns.i.dates_?.insert(a, b, c).transact

        // <
        _ <- Ns.i.a1.dates_?.hasLt(Some(date0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates_?.hasLt(Some(date1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates_?.hasLt(Some(date2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.dates_?.hasLt(Some(date3)).query.get.map(_ ==> List(a, b))

        // <=
        _ <- Ns.i.a1.dates_?.hasLe(Some(date0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.dates_?.hasLe(Some(date1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.dates_?.hasLe(Some(date2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasLe(Some(date3)).query.get.map(_ ==> List(a, b))

        // >
        _ <- Ns.i.a1.dates_?.hasGt(Some(date0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGt(Some(date1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGt(Some(date2)).query.get.map(_ ==> List(b))
        _ <- Ns.i.a1.dates_?.hasGt(Some(date3)).query.get.map(_ ==> List(b))

        // >=
        _ <- Ns.i.a1.dates_?.hasGe(Some(date0)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGe(Some(date1)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGe(Some(date2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGe(Some(date3)).query.get.map(_ ==> List(b))


        // None comparison matches any asserted values
        _ <- Ns.i.a1.dates_?.hasLt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGt(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasLe(None).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.dates_?.hasGe(None).query.get.map(_ ==> List(a, b))
      } yield ()
    }
  }
}