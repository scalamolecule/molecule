package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Int extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSeq.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.has(int1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.has(int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSeq.has(List(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.has(List(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.has(List(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSeq.has(int1, int2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int1, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int2, int3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq.has(List(int1, int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int1, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int2, int3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.has(List(int1, int2, int3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq.has(List.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(int1, int2))
        val b = (2, List(int2, int3, int3))
        for {
          _ <- Ns.i.intSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSeq.hasNo(int0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.hasNo(int1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(int3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(int5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq.hasNo(List(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq.hasNo(List(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq.hasNo(List(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(List(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq.hasNo(List(int5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSeq.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(int1, int5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq.hasNo(List(int1, int5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.intSeq.hasNo(List.empty[Int]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSeq.insert(List(
            (1, List(int1, int2)),
            (2, List(int2, int3, int3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSeq_.has(int0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.has(int1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.has(int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intSeq_.has(List(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.has(List(int1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.has(List(int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSeq_.has(int1, int2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int1, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int2, int3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(int1, int2, int3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSeq_.has(List(int1, int2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int1, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int2, int3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.has(List(int1, int2, int3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq_.has(List.empty[Int]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.intSeq.insert(List(
            (1, List(int1, int2)),
            (2, List(int2, int3, int3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSeq_.hasNo(int0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.hasNo(int1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.hasNo(int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(int3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(int5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.intSeq_.hasNo(List(int0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.intSeq_.hasNo(List(int5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(int1, int5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_.hasNo(List(int1, int5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.intSeq_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.intSeq_?.has(Some(int0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.has(Some(int1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.has(Some(int2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(int3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1, int2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int2, int3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.has(Some(List(int1, int2, int3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.intSeq_?.has(Some(List.empty[Int])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.intSeq_?.has(Option.empty[Int]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.intSeq_?.has(Option.empty[List[Int]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(int1, int2)))
        val b = (2, Some(List(int2, int3, int3)))
        val c = (3, None)
        for {
          _ <- Ns.i.intSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(int5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List(int1, int5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.intSeq_?.hasNo(Some(List.empty[Int])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.intSeq_?.hasNo(Option.empty[Int]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.intSeq_?.hasNo(Option.empty[List[Int]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}