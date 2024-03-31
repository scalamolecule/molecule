// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.util.Date
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Date_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.dateSet.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.has(date1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.has(date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSet.has(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.has(Seq(date1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.has(Seq(date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSet.has(date1, date2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date1, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date2, date3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(date1, date2, date3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet.has(Seq(date1, date2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date1, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date2, date3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet.has(Seq.empty[Date]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(date1, date2))
        val b = (2, Set(date2, date3, date4))
        for {
          _ <- Ns.i.dateSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.dateSet.hasNo(date0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.hasNo(date1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(date4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(date5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.dateSet.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(date1, date5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dateSet.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSet.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.dateSet_.has(date0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.has(date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.has(date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSet_.has(Seq(date0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.has(Seq(date1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.has(Seq(date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.dateSet_.has(date0, date1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.has(date1, date2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date1, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date2, date3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(date3, date4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSet_.has(Seq(date1, date2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date1, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date2, date3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.has(Seq(date1, date2, date3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.dateSet_.has(Seq.empty[Date]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.dateSet.insert(List(
            (1, Set(date1, date2)),
            (2, Set(date2, date3, date4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.dateSet_.hasNo(date0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.hasNo(date1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.hasNo(date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(date4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(date5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(date1, date5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.dateSet_.hasNo(Seq(date1, date5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.dateSet_.hasNo(Seq.empty[Date]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}