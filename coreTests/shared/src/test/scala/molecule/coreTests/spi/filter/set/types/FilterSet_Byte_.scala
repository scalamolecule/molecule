// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Byte_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "attr" - types { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        for {
          _ <- Ns.i.byteSet.insert(List(a, b)).transact

          _ <- Ns.i.a1.byteSet.query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        for {
          _ <- Ns.i.byteSet.insert(List(a, b)).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.byteSet(Set(byte1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Set(byte1, byte2)).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.byteSet(Set(byte1, byte2, byte3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.byteSet(Seq(Set(byte1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Seq(Set(byte1, byte2))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet(Seq(Set(byte1, byte2, byte3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.byteSet(Set(byte1), Set(byte2, byte3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Set(byte1, byte2), Set(byte2, byte3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.byteSet(Seq(Set(byte1), Set(byte2, byte3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.byteSet(Set(byte1, byte2), Set.empty[Byte]).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet(Set.empty[Byte], Set(byte1, byte2)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet(Set.empty[Byte], Set.empty[Byte]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Set.empty[Byte]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Seq.empty[Set[Byte]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet(Seq(Set.empty[Byte])).query.get.map(_ ==> List())

          // Applying nothing matches nothing
          _ <- Ns.i.a1.byteSet().query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        for {
          _ <- Ns.i.byteSet.insert(List(a, b)).transact

          // Exact Set non-matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.byteSet.not(Set(byte1)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.not(Set(byte1, byte2)).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.byteSet.not(Set(byte1, byte2, byte3)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1, byte2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1, byte2))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1, byte2, byte3))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.byteSet.not(Set(byte1), Set(byte2, byte3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.not(Set(byte1, byte2), Set(byte2, byte3)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.not(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1), Set(byte2, byte3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.byteSet.not(Seq(Set(byte1, byte2), Set.empty[Byte])).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.not(Set.empty[Byte]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.not(Seq.empty[Set[Byte]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        for {
          _ <- Ns.i.byteSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.byteSet.has(byte0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.has(byte1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet.has(byte2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(byte3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.byteSet.has(Seq(byte0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.has(Seq(byte1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet.has(Seq(byte2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(Seq(byte3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.byteSet.has(byte1, byte2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(byte1, byte3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(byte2, byte3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(byte1, byte2, byte3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.byteSet.has(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(Seq(byte1, byte3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(Seq(byte2, byte3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.has(Seq(byte1, byte2, byte3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.byteSet.has(Seq.empty[Byte]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(byte1, byte2))
        val b = (2, Set(byte2, byte3, byte4))
        for {
          _ <- Ns.i.byteSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.byteSet.hasNo(byte0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.hasNo(byte1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.hasNo(byte2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(byte3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet.hasNo(byte4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet.hasNo(byte5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.byteSet.hasNo(byte1, byte2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(byte1, byte3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(byte1, byte4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(byte1, byte5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte1, byte2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte1, byte3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte1, byte4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet.hasNo(Seq(byte1, byte5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.byteSet.hasNo(Seq.empty[Byte]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "attr" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.byteSet.insert(List(
            (1, Set(byte1, byte2)),
            (2, Set(byte2, byte3, byte4))
          )).transact

          _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))

          // byteSet not asserted for i = 0
          _ <- Ns.i.a1.byteSet_.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        for {
          _ <- Ns.i.byteSet_?.insert(List(
            (0, None),
            (1, Some(Set(byte1, byte2))),
            (2, Some(Set(byte2, byte3, byte4))),
          )).transact

          // Match non-asserted attribute (null)
          _ <- Ns.i.a1.byteSet_().query.get.map(_ ==> List(0))

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.byteSet_(Set(byte1)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_(Set(byte1, byte2)).query.get.map(_ ==> List(1)) // include exact match
          _ <- Ns.i.a1.byteSet_(Set(byte1, byte2, byte3)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.byteSet_(Seq(Set(byte1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_(Seq(Set(byte1, byte2))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_(Seq(Set(byte1, byte2, byte3))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.byteSet_(Set(byte1), Set(byte2, byte3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_(Set(byte1, byte2), Set(byte2, byte3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.byteSet_(Seq(Set(byte1), Set(byte2, byte3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.byteSet_(Set(byte1, byte2), Set.empty[Byte]).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_(Set.empty[Byte]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_(Seq.empty[Set[Byte]]).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_(Seq(Set.empty[Byte])).query.get.map(_ ==> List())
        } yield ()
      }


      "not equal" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.byteSet.insert(List(
            (1, Set(byte1, byte2)),
            (2, Set(byte2, byte3, byte4))
          )).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.byteSet_.not(Set(byte1)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.not(Set(byte1, byte2)).query.get.map(_ ==> List(2)) // exclude exact match
          _ <- Ns.i.a1.byteSet_.not(Set(byte1, byte2, byte3)).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1, byte2))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1, byte2, byte3))).query.get.map(_ ==> List(1, 2))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.byteSet_.not(Set(byte1), Set(byte2, byte3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.not(Set(byte1, byte2), Set(byte2, byte3)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteSet_.not(Set(byte1, byte2), Set(byte2, byte3, byte4)).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1), Set(byte2, byte3))).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1, byte2), Set(byte2, byte3))).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4))).query.get.map(_ ==> List())


          // Empty Seq/Sets
          _ <- Ns.i.a1.byteSet_.not(Seq(Set(byte1, byte2), Set.empty[Byte])).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteSet_.not(Set.empty[Byte]).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.not(Seq.empty[Set[Byte]]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.byteSet.insert(List(
            (1, Set(byte1, byte2)),
            (2, Set(byte2, byte3, byte4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.byteSet_.has(byte0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.has(byte1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_.has(byte2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(byte3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.byteSet_.has(Seq(byte0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.has(Seq(byte1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_.has(Seq(byte2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(Seq(byte3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.byteSet_.has(byte1, byte2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(byte1, byte3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(byte2, byte3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(byte1, byte2, byte3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.byteSet_.has(Seq(byte1, byte2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(Seq(byte1, byte3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(Seq(byte2, byte3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.has(Seq(byte1, byte2, byte3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.byteSet_.has(Seq.empty[Byte]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.byteSet.insert(List(
            (1, Set(byte1, byte2)),
            (2, Set(byte2, byte3, byte4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.byteSet_.hasNo(byte0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.hasNo(byte1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteSet_.hasNo(byte2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(byte3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_.hasNo(byte4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_.hasNo(byte5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.byteSet_.hasNo(byte1, byte2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(byte1, byte3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(byte1, byte4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(byte1, byte5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte1, byte2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte1, byte3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte1, byte4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_.hasNo(Seq(byte1, byte5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.byteSet_.hasNo(Seq.empty[Byte]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "attr" - types { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteSet_?.insert(a, b, c).transact

          _ <- Ns.i.a1.byteSet_?.query.get.map(_ ==> List(a, b, c))

          // Distinct result even with redundant None's (two i = 3 with no byte value)
          _ <- Ns.i.insert(3).transact
          _ <- Ns.i.>=(2).a1.byteSet_?.query.get.map(_ ==> List(
            (2, Some(Set(byte2, byte3, byte4))),
            (3, None),
          ))
        } yield ()
      }


      "apply (equal)" - types { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteSet_?.insert(a, b, c).transact

          // Exact Set matches

          // AND semantics
          // "Is exactly this AND that"
          _ <- Ns.i.a1.byteSet_?(Some(Set(byte1))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?(Some(Set(byte1, byte2))).query.get.map(_ ==> List(a)) // include exact match
          _ <- Ns.i.a1.byteSet_?(Some(Set(byte1, byte2, byte3))).query.get.map(_ ==> List())
          // Same as
          _ <- Ns.i.a1.byteSet_?(Some(Seq(Set(byte1)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?(Some(Seq(Set(byte1, byte2)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?(Some(Seq(Set(byte1, byte2, byte3)))).query.get.map(_ ==> List())


          // AND/OR semantics with multiple Sets

          // "(exactly this AND that) OR (exactly this AND that)"
          _ <- Ns.i.a1.byteSet_?(Some(Seq(Set(byte1), Set(byte2, byte3)))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.apply(Some(Seq(Set(byte1, byte2), Set(byte2, byte3)))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?(Some(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4)))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.byteSet_?(Some(Set.empty[Byte])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?(Some(Seq.empty[Set[Byte]])).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?(Some(Seq(Set.empty[Byte]))).query.get.map(_ ==> List())


          // None matches non-asserted values
          _ <- Ns.i.a1.byteSet_?(Option.empty[Set[Byte]]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.byteSet_?(Option.empty[Seq[Set[Byte]]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "not equal" - types { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteSet_?.insert(a, b, c).transact

          // Non-exact Set matches

          // AND semantics
          // "Not (exactly this AND that)"
          _ <- Ns.i.a1.byteSet_?.not(Some(Set(byte1))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Set(byte1, byte2))).query.get.map(_ ==> List(b)) // exclude exact match
          _ <- Ns.i.a1.byteSet_?.not(Some(Set(byte1, byte2, byte3))).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1, byte2)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1, byte2, byte3)))).query.get.map(_ ==> List(a, b))


          // AND/OR semantics with multiple Sets

          // "NEITHER (exactly this AND that) NOR (exactly this AND that)"
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1), Set(byte2, byte3)))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1, byte2), Set(byte2, byte3)))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1, byte2), Set(byte2, byte3, byte4)))).query.get.map(_ ==> List())

          // Empty Sets are ignored
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq(Set(byte1, byte2), Set.empty[Byte]))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Set.empty[Byte])).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.not(Some(Seq.empty[Set[Byte]])).query.get.map(_ ==> List(a, b))

          // Negation of None matches all asserted
          _ <- Ns.i.a1.byteSet_?.not(Option.empty[Set[Byte]]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.not(Option.empty[Seq[Set[Byte]]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }


      "has" - types { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.byteSet_?.has(Some(byte0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.has(Some(byte1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?.has(Some(byte2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.has(Some(byte3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte1, byte2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte1, byte3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte2, byte3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq(byte1, byte2, byte3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.byteSet_?.has(Some(Seq.empty[Byte])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.byteSet_?.has(Option.empty[Byte]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.byteSet_?.has(Option.empty[Seq[Byte]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(byte1, byte2)))
        val b = (2, Some(Set(byte2, byte3, byte4)))
        val c = (3, None)
        for {
          _ <- Ns.i.byteSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(byte0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(byte1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(byte2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(byte3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(byte4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(byte5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte1, byte2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte1, byte3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte1, byte4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq(byte1, byte5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.byteSet_?.hasNo(Some(Seq.empty[Byte])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.byteSet_?.hasNo(Option.empty[Byte]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.byteSet_?.hasNo(Option.empty[Seq[Byte]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}