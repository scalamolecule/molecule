package molecule.coreTests.spi.filter.set.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.intSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSet.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.has(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet.has(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(int3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSet.has(Seq(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.has(Seq(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet.has(Seq(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(Seq(int3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSet.has(int1, int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(int1, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(int2, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSet.has(Seq(int1, int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(Seq(int1, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(Seq(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.has(Seq(int1, int2, int3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.intSet.has(Seq.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(int1, int2))
        val b = (2, Set(int2, int3, int4))
        for {
          _ <- Ns.i.intSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSet.hasNo(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.hasNo(int1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSet.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet.hasNo(int4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet.hasNo(int5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSet.hasNo(Seq(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet.hasNo(Seq(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSet.hasNo(Seq(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(Seq(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet.hasNo(Seq(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet.hasNo(Seq(int5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSet.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(int1, int5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSet.hasNo(Seq(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(Seq(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(Seq(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet.hasNo(Seq(int1, int5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.intSet.hasNo(Seq.empty[Int]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSet.insert(List(
            (1, Set(int1, int2)),
            (2, Set(int2, int3, int4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSet_.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.has(int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSet_.has(int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(int3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intSet_.has(Seq(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.has(Seq(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSet_.has(Seq(int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(Seq(int3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSet_.has(int1, int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(int1, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(int2, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(int1, int2, int3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSet_.has(Seq(int1, int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(Seq(int1, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(Seq(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.has(Seq(int1, int2, int3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.intSet_.has(Seq.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSet.insert(List(
            (1, Set(int1, int2)),
            (2, Set(int2, int3, int4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSet_.hasNo(int0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.hasNo(int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSet_.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSet_.hasNo(int4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSet_.hasNo(int5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSet_.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(int1, int4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(int1, int5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int1, int4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_.hasNo(Seq(int1, int5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.intSet_.hasNo(Seq.empty[Int]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSet_?.has(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.has(Some(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet_?.has(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.has(Some(int3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int1, int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int1, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.has(Some(Seq(int1, int2, int3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.intSet_?.has(Some(Seq.empty[Int])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.intSet_?.has(Option.empty[Int]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.intSet_?.has(Option.empty[Seq[Int]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(int1, int2)))
        val b = (2, Some(Set(int2, int3, int4)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSet_?.hasNo(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(int4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(int5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int1, int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int1, int4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq(int1, int5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.intSet_?.hasNo(Some(Seq.empty[Int])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.intSet_?.hasNo(Option.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSet_?.hasNo(Option.empty[Seq[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}