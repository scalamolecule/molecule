// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSeq_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSeq.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.has(List(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSeq.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.has(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq.has(List.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, List(uuid1, uuid2))
        val b = (2, List(uuid2, uuid3, uuid3))
        for {
          _ <- Ns.i.uuidSeq.insert(List(a, b)).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uuidSeq.hasNo(List.empty[UUID]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSeq.insert(List(
            (1, List(uuid1, uuid2)),
            (2, List(uuid2, uuid3, uuid3))
          )).transact

          // Seqs with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSeq_.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.has(uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.has(uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSeq_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.has(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Seqs match nothing
          _ <- Ns.i.a1.uuidSeq_.has(List.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSeq.insert(List(
            (1, List(uuid1, uuid2)),
            (2, List(uuid2, uuid3, uuid3))
          )).transact

          // Seqs without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSeq_.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs has no effect
          _ <- Ns.i.a1.uuidSeq_.hasNo(List.empty[UUID]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Seq attributes
  }
}