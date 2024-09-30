// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.LocalDate
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_LocalDate_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  val a = (1, Set(localDate1, localDate2))
  val b = (2, Set(localDate2, localDate3, localDate4))

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateSet.insert(a, b).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateSet.has(localDate0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.has(localDate1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet.has(localDate2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(localDate3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate3)).query.get.map(_ ==> List(b))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSet.has(localDate1, localDate2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(localDate1, localDate3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(localDate2, localDate3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate1, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate2, localDate3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.has(Seq(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateSet.has(Seq.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateSet.insert(a, b).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateSet.hasNo(localDate0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.hasNo(localDate1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSet.hasNo(localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(localDate3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet.hasNo(localDate4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet.hasNo(localDate5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate5)).query.get.map(_ ==> List(a, b))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateSet.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(localDate1, localDate4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(localDate1, localDate5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate1, localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate1, localDate4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet.hasNo(Seq(localDate1, localDate5)).query.get.map(_ ==> List(b))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localDateSet.hasNo(Seq.empty[LocalDate]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateSet.insert(a, b).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateSet_.has(localDate0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.has(localDate1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.has(localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate3)).query.get.map(_ ==> List(2))

          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSet_.has(localDate0, localDate1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.has(localDate1, localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate1, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate3, localDate4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate0, localDate1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate1, localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate1, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate3, localDate4)).query.get.map(_ ==> List(2))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateSet_.has(Seq.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i.localDateSet.insert(a, b).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate5)).query.get.map(_ ==> List(1, 2))

          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate1, localDate4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(localDate1, localDate5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate1, localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate1, localDate3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate1, localDate4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq(localDate1, localDate5)).query.get.map(_ ==> List(2))

          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localDateSet_.hasNo(Seq.empty[LocalDate]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}