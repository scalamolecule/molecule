// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.util.UUID
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterSet_UUID_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

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
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
          _ <- Ns.i.a1.uuidSet_.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(1, 2))
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


    "Optional" - {

      "has" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          // Sets with one or more values matching

          // "Has this"
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid0)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid1)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid2)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(uuid3)).query.get.map(_ ==> List(b))
          // Same as
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid0))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid3))).query.get.map(_ ==> List(b))


          // OR semantics when multiple values

          // "Has this OR that"
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid2, uuid3))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq(uuid1, uuid2, uuid3))).query.get.map(_ ==> List(a, b))

          // Empty Seq/Sets match nothing
          _ <- Ns.i.a1.uuidSet_?.has(Some(Seq.empty[UUID])).query.get.map(_ ==> List())

          // None matches non-asserted values
          _ <- Ns.i.a1.uuidSet_?.has(Option.empty[UUID]).query.get.map(_ ==> List(c))
          _ <- Ns.i.a1.uuidSet_?.has(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(c))
        } yield ()
      }


      "hasNo" - types { implicit conn =>
        val a = (1, Some(Set(uuid1, uuid2)))
        val b = (2, Some(Set(uuid2, uuid3, uuid4)))
        val c = (3, None)
        for {
          _ <- Ns.i.uuidSet_?.insert(a, b, c).transact

          // Sets without one or more values matching

          // "Doesn't have this"
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid0)).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid1)).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid2)).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid3)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid4)).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(uuid5)).query.get.map(_ ==> List(a, b))
          // Same as
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid0))).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1))).query.get.map(_ ==> List(b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid3))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid4))).query.get.map(_ ==> List(a))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid5))).query.get.map(_ ==> List(a, b))


          // OR semantics when multiple values

          // "Has neither this OR that"
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid2))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid3))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid4))).query.get.map(_ ==> List())
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq(uuid1, uuid5))).query.get.map(_ ==> List(b))


          // Negating empty Seqs/Sets has no effect
          _ <- Ns.i.a1.uuidSet_?.hasNo(Some(Seq.empty[UUID])).query.get.map(_ ==> List(a, b))

          // Negating None returns all asserted
          _ <- Ns.i.a1.uuidSet_?.hasNo(Option.empty[UUID]).query.get.map(_ ==> List(a, b))
          _ <- Ns.i.a1.uuidSet_?.hasNo(Option.empty[Seq[UUID]]).query.get.map(_ ==> List(a, b))
        } yield ()
      }
    }
  }
}