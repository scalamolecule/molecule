// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(double1, double2))
        val b = (2, List(double2, double3, double3))
        for {
          _ <- Ns.i.doubleSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.doubleSeq.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.has(double1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq.has(double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(double3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleSeq.has(List(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.has(List(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq.has(List(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(List(double3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubleSeq.has(double1, double2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(double1, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(double2, double3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(double1, double2, double3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSeq.has(List(double1, double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(List(double1, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(List(double2, double3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.has(List(double1, double2, double3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.doubleSeq.has(List.empty[Double]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(double1, double2))
        val b = (2, List(double2, double3, double3))
        for {
          _ <- Ns.i.doubleSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleSeq.hasNo(double0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.hasNo(double1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSeq.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq.hasNo(double3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq.hasNo(double5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleSeq.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(double1, double5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq.hasNo(List(double1, double5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.doubleSeq.hasNo(List.empty[Double]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleSeq.insert(List(
            (1, List(double1, double2)),
            (2, List(double2, double3, double3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.doubleSeq_.has(double0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.has(double1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSeq_.has(double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(double3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubleSeq_.has(List(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.has(List(double1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSeq_.has(List(double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(List(double3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubleSeq_.has(double1, double2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(double1, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(double2, double3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(double1, double2, double3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleSeq_.has(List(double1, double2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(List(double1, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(List(double2, double3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.has(List(double1, double2, double3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.doubleSeq_.has(List.empty[Double]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.doubleSeq.insert(List(
            (1, List(double1, double2)),
            (2, List(double2, double3, double3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleSeq_.hasNo(double0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.hasNo(double1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSeq_.hasNo(double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(double3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSeq_.hasNo(double3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSeq_.hasNo(double5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleSeq_.hasNo(double1, double2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(double1, double3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(double1, double5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double1, double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double1, double3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_.hasNo(List(double1, double5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.doubleSeq_.hasNo(List.empty[Double]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(List(double1, double2)))
        val b = (2, Some(List(double2, double3, double3)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSeq_?.insert(a, b, c).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.doubleSeq_?.has(Some(double0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.has(Some(double1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(double2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(double3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double1, double2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double1, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double2, double3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List(double1, double2, double3))).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.doubleSeq_?.has(Some(List.empty[Double])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.doubleSeq_?.has(Option.empty[Double]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.doubleSeq_?.has(Option.empty[List[Double]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(List(double1, double2)))
        val b = (2, Some(List(double2, double3, double3)))
        val c = (3, None)
        for {
          _ <- Ns.i.doubleSeq_?.insert(a, b, c).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(double0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(double1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(double2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(double3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(double5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double1, double2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double1, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double1, double3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List(double1, double5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Some(List.empty[Double])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Option.empty[Double]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.doubleSeq_?.hasNo(Option.empty[List[Double]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}