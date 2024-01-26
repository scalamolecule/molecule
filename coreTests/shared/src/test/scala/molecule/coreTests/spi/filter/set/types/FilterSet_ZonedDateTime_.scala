// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimes.insert(List(a, b)).transact

          _ <- Ns.i.a1.zonedDateTimes.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimes.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime2, zonedDateTime1)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime2, zonedDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimes(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes(Set.empty[ZonedDateTime], Set(zonedDateTime2, zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes(Set.empty[ZonedDateTime], Set.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Set.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes(Seq(Set.empty[ZonedDateTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimes.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime2, zonedDateTime1)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime2, zonedDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.not(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.zonedDateTimes.not(Seq(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.not(Set.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.not(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimes.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimes.has(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.has(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.has(Set.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.has(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(zonedDateTime1, zonedDateTime2))
        val b = (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
        for {
          _ <- Ns.i.zonedDateTimes.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime1, zonedDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime1, zonedDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Set.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes.hasNo(Seq(Set.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimes.insert(List(
            (1, Set(zonedDateTime1, zonedDateTime2)),
            (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.zonedDateTimes_?.insert(List(
            (0, None),
            (1, Some(Set(zonedDateTime1, zonedDateTime2))),
            (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))),
          )).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime2, zonedDateTime1)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime2, zonedDateTime1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimes_(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_(Set.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_(Seq(Set.empty[ZonedDateTime])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimes.insert(List(
            (1, Set(zonedDateTime1, zonedDateTime2)),
            (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime2, zonedDateTime1)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime2, zonedDateTime1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Set.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.not(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimes.insert(List(
            (1, Set(zonedDateTime1, zonedDateTime2)),
            (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(zonedDateTime1, zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime2))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(2))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimes_.has(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.has(Set.empty[ZonedDateTime]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.has(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.zonedDateTimes.insert(List(
            (1, Set(zonedDateTime1, zonedDateTime2)),
            (2, Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime1, zonedDateTime3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime1, zonedDateTime4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(zonedDateTime1, zonedDateTime5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime1, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime1, zonedDateTime4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(zonedDateTime1, zonedDateTime5)).query.get.map(_ ==> List(2))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List(1))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(1))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Set.empty[ZonedDateTime]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.zonedDateTimes_.hasNo(Seq(Set.empty[ZonedDateTime])).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimes_?.insert(a, b, c).transact

          _ <- Ns.i.a1.zonedDateTimes_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no zonedDateTime value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.zonedDateTimes_?.query.get.map(_ ==> List(
            (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimes_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Set(zonedDateTime1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Set(zonedDateTime2, zonedDateTime1))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime1, zonedDateTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime2, zonedDateTime1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Set.empty[ZonedDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq.empty[Set[ZonedDateTime]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?(Some(Seq(Set.empty[ZonedDateTime]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.zonedDateTimes_?(Option.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimes_?(Option.empty[Seq[Set[ZonedDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimes_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Set(zonedDateTime1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Set(zonedDateTime2, zonedDateTime1))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime1, zonedDateTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime2, zonedDateTime1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime1), Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime2, zonedDateTime1), Set(zonedDateTime4, zonedDateTime3, zonedDateTime2)))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set.empty[ZonedDateTime]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Set.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.not(Some(Seq.empty[Set[ZonedDateTime]])).query.get.map(_ ==> List(a, b))


          // None matches non-asserted values
          _ <- Ns.i.a1.zonedDateTimes_?(Option.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimes_?(Option.empty[Seq[Set[ZonedDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimes_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(zonedDateTime0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(zonedDateTime1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(zonedDateTime2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(zonedDateTime3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))


          // AND semantics when multiple values in a _Set_

          // "Has this AND that"
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set(zonedDateTime1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set(zonedDateTime2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1, zonedDateTime2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime2)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))).query.get.map(_ ==> List(b))


          // AND/OR semantics with multiple Sets

          // "(has this AND that) OR (has this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq.empty[ZonedDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Set.empty[ZonedDateTime])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.has(Some(Seq.empty[Set[ZonedDateTime]])).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.zonedDateTimes_?.has(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Option.empty[Seq[ZonedDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Option.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.zonedDateTimes_?.has(Option.empty[Seq[Set[ZonedDateTime]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(zonedDateTime1, zonedDateTime2)))
        val b = (2, Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))
        val c = (3, None)
        for {
          _ <- Ns.i.zonedDateTimes_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(zonedDateTime0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(zonedDateTime1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(zonedDateTime2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(zonedDateTime3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(zonedDateTime4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(zonedDateTime5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime1, zonedDateTime3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime1, zonedDateTime4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(zonedDateTime1, zonedDateTime5))).query.get.map(_ ==> List(b))


          // AND semantics when multiple values in a _Set_

          // "Not (has this AND that)"
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set(zonedDateTime1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set(zonedDateTime2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4))).query.get.map(_ ==> List(a))
          // Same as
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1, zonedDateTime2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime2)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))).query.get.map(_ ==> List(a))


          // AND/OR semantics with multiple Sets

          // "Not ((has this AND that) OR (has this AND that))"
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime0, zonedDateTime3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3, zonedDateTime4)))).query.get.map(_ ==> List())


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Set.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq.empty[Set[ZonedDateTime]])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Some(Seq(Set.empty[ZonedDateTime]))).query.get.map(_ ==> List(a, b))


          // Negating None returns all asserted
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Option.empty[Seq[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Option.empty[Set[ZonedDateTime]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.zonedDateTimes_?.hasNo(Option.empty[Seq[Set[ZonedDateTime]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}