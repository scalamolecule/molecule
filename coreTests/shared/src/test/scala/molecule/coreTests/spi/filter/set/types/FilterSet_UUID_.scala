// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.util.UUID
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_UUID_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - {

      "has" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSet.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.has(uuid1).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.has(uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid3).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid3)).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSet.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet.has(Seq.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Set(uuid1, uuid2))
        val b = (2, Set(uuid2, uuid3, uuid4))
        for {
          _ <- Ns.i.uuidSet.insert(List(a, b)).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSet.hasNo(uuid0).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid3).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid4).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(uuid5).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid5)).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuidSet.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }


    "Tacit" - {

      "has" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSet.insert(List(
            (1, Set(uuid1, uuid2)),
            (2, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSet_.has(uuid0).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.has(uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.has(uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid3).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid3)).query.get.map(_ ==> List(2))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSet_.has(uuid0, uuid1).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid3, uuid4).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(1, 2))


          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet_.has(Seq.empty[UUID]).query.get.map(_ ==> List())
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        for {
          _ <- Ns.i(0).save.transact
          _ <- Ns.i.uuidSet.insert(List(
            (1, Set(uuid1, uuid2)),
            (2, Set(uuid2, uuid3, uuid4))
          )).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid3).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid4).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid5).query.get.map(_ ==> List(1, 2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid0)).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1)).query.get.map(_ ==> List(2))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid3)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid4)).query.get.map(_ ==> List(1))
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid5)).query.get.map(_ ==> List(1, 2))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(2))
          // Same as
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(2))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuidSet_.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(1, 2))
        } yield ()
      }
    }

    // No filtering on optional Set attributes
  }
}