// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.LocalDate
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_LocalDate_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(localDate1, localDate2))
        val b = (2, Set(localDate2, localDate3, localDate4))
        for {
          _ <- Ns.i.localDateSet.insert(List(a, b)).transact

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
        val a = (1, Set(localDate1, localDate2))
        val b = (2, Set(localDate2, localDate3, localDate4))
        for {
          _ <- Ns.i.localDateSet.insert(List(a, b)).transact

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
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSet.insert(List(
            (1, Set(localDate1, localDate2)),
            (2, Set(localDate2, localDate3, localDate4))
          )).transact

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
          _ <- Ns.i.a1.localDateSet_.has(localDate1, localDate2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate1, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(localDate1, localDate2, localDate3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate1, localDate2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate1, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.localDateSet_.has(Seq(localDate1, localDate2, localDate3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateSet_.has(Seq.empty[LocalDate]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.localDateSet.insert(List(
            (1, Set(localDate1, localDate2)),
            (2, Set(localDate2, localDate3, localDate4))
          )).transact

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


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(localDate1, localDate2)))
        val b = (2, Some(Set(localDate2, localDate3, localDate4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.localDateSet_?.has(Some(localDate0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.has(Some(localDate1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet_?.has(Some(localDate2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.has(Some(localDate3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate1, localDate2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate1, localDate3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate2, localDate3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq(localDate1, localDate2, localDate3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.localDateSet_?.has(Some(Seq.empty[LocalDate])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.localDateSet_?.has(Option.empty[LocalDate]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.localDateSet_?.has(Option.empty[Seq[LocalDate]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(localDate1, localDate2)))
        val b = (2, Some(Set(localDate2, localDate3, localDate4)))
        val c = (3, None)
        for {
          _ <- Ns.i.localDateSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(localDate0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(localDate1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(localDate2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(localDate3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(localDate4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(localDate5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate1, localDate2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate1, localDate3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate1, localDate4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq(localDate1, localDate5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.localDateSet_?.hasNo(Some(Seq.empty[LocalDate])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.localDateSet_?.hasNo(Option.empty[LocalDate]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.localDateSet_?.hasNo(Option.empty[Seq[LocalDate]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}