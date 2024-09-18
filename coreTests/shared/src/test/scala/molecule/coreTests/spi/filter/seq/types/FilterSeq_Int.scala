package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
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

    // No filtering on optional Seq attributes
  }
}