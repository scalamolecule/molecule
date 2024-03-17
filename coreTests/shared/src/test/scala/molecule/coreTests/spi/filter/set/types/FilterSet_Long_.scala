// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          _ <- Ns.i.a1.longs.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.longs(Set(long1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Set(long1, long2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.longs(Set(long1, long2, long3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs(Seq(Set(long1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs(Set(long1), Set(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set.empty[Long], Set(long1, long2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs(Set.empty[Long], Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs(Seq(Set.empty[Long])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.longs().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.longs.not(Set(long1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.longs.not(Set(long1, long2, long3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs.not(Seq(Set(long1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.longs.not(Set(long1), Set(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs.not(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.longs.not(Seq(Set(long1, long2), Set.empty[Long])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.not(Set.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.longs.has(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.has(long1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.has(long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(long3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs.has(Seq(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.has(Seq(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.has(Seq(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(Seq(long3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longs.has(long1, long2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(long1, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(long2, long3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(long1, long2, long3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs.has(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(Seq(long1, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(Seq(long2, long3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.has(Seq(long1, long2, long3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs.has(Seq.empty[Long]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(long1, long2))
        val b = (2, Set(long2, long3, long4))
        for {
          _ <- Ns.i.longs.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.longs.hasNo(long0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.hasNo(long1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.hasNo(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(long3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.hasNo(long4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.hasNo(long5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs.hasNo(Seq(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs.hasNo(Seq(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs.hasNo(Seq(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(Seq(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.hasNo(Seq(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs.hasNo(Seq(long5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.longs.hasNo(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(long1, long4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(long1, long5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs.hasNo(Seq(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(Seq(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(Seq(long1, long4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs.hasNo(Seq(long1, long5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longs.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longs.insert(List(
            (1, Set(long1, long2)),
            (2, Set(long2, long3, long4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // longs not asserted for i = 0
          _ <- Ns.i.a1.longs_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.longs_?.insert(List(
            (0, None),
            (1, Some(Set(long1, long2))),
            (2, Some(Set(long2, long3, long4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.longs_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.longs_(Set(long1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Set(long1, long2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.longs_(Set(long1, long2, long3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_(Seq(Set(long1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs_(Set(long1), Set(long2, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longs_(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_(Set(long1, long2), Set.empty[Long]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_(Set.empty[Long]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq.empty[Set[Long]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_(Seq(Set.empty[Long])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longs.insert(List(
            (1, Set(long1, long2)),
            (2, Set(long2, long3, long4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.longs_.not(Set(long1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.longs_.not(Set(long1, long2, long3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2, long3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.longs_.not(Set(long1), Set(long2, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set(long2, long3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longs_.not(Set(long1, long2), Set(long2, long3, long4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1), Set(long2, long3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set(long2, long3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set(long2, long3, long4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.longs_.not(Seq(Set(long1, long2), Set.empty[Long])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longs_.not(Set.empty[Long]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.not(Seq.empty[Set[Long]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longs.insert(List(
            (1, Set(long1, long2)),
            (2, Set(long2, long3, long4))
          )).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.longs_.has(long0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.has(long1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_.has(long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(long3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longs_.has(Seq(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.has(Seq(long1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_.has(Seq(long2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(Seq(long3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longs_.has(long1, long2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(long1, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(long2, long3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(long1, long2, long3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longs_.has(Seq(long1, long2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(Seq(long1, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(Seq(long2, long3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.has(Seq(long1, long2, long3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_.has(Seq.empty[Long]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.longs.insert(List(
            (1, Set(long1, long2)),
            (2, Set(long2, long3, long4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.longs_.hasNo(long0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.hasNo(long1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longs_.hasNo(long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(long3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_.hasNo(long4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_.hasNo(long5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.longs_.hasNo(Seq(long0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.longs_.hasNo(Seq(long1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.longs_.hasNo(Seq(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(Seq(long3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_.hasNo(Seq(long4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.longs_.hasNo(Seq(long5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.longs_.hasNo(long1, long2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(long1, long3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(long1, long4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(long1, long5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.longs_.hasNo(Seq(long1, long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(Seq(long1, long3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(Seq(long1, long4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_.hasNo(Seq(long1, long5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longs_.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          _ <- Ns.i.a1.longs_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no long value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.longs_?.query.get.map(_ ==> List(
            (2, Some(Set(long2, long3, long4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.longs_?(Some(Set(long1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Set(long1, long2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.longs_?(Some(Set(long1, long2, long3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2, long3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1), Set(long2, long3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2), Set(long2, long3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?(Some(Seq(Set(long1, long2), Set(long2, long3, long4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_?(Some(Set.empty[Long])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?(Some(Seq(Set.empty[Long]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.longs_?(Option.empty[Set[Long]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.longs_?.not(Some(Set(long1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Set(long1, long2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.longs_?.not(Some(Set(long1, long2, long3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2, long3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1), Set(long2, long3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set(long2, long3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set(long2, long3, long4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.longs_?.not(Some(Seq(Set(long1, long2), Set.empty[Long]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.not(Some(Set.empty[Long])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Some(Seq.empty[Set[Long]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.longs_?.not(Option.empty[Set[Long]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.not(Option.empty[Seq[Set[Long]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this value"
          _ <- Ns.i.a1.longs_?.has(Some(long0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.has(Some(long1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.has(Some(long2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.has(Some(long3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long1, long2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long1, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long2, long3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.has(Some(Seq(long1, long2, long3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.longs_?.has(Some(Seq.empty[Long])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.longs_?.has(Option.empty[Long]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.longs_?.has(Option.empty[Seq[Long]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(long1, long2)))
        val b = (2, Some(Set(long2, long3, long4)))
        val c = (3, None)
        for {
          _ <- Ns.i.longs_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this value"
          _ <- Ns.i.a1.longs_?.hasNo(Some(long0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.hasNo(Some(long1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.hasNo(Some(long2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.hasNo(Some(long3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.hasNo(Some(long4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.hasNo(Some(long5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Not (has this OR that)"
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long1, long2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long1, long3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long1, long4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq(long1, long5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.longs_?.hasNo(Some(Seq.empty[Long])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.longs_?.hasNo(Option.empty[Long]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.longs_?.hasNo(Option.empty[Seq[Long]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}