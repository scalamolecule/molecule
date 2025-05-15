// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import java.util.UUID
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSet_UUID_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(uuid1, uuid2))
  val b = (2, Set(uuid2, uuid3, uuid4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.uuidSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uuidSet.has(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.has(uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSet.has(uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(uuid3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uuidSet.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.has(Seq(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.uuidSet.has(Seq.empty[UUID]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.uuidSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uuidSet.hasNo(uuid0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.hasNo(uuid1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuidSet.hasNo(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(uuid3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSet.hasNo(uuid4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSet.hasNo(uuid5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uuidSet.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.uuidSet.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.uuidSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uuidSet_.has(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.has(uuid1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.has(uuid2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(uuid3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uuidSet_.has(uuid0, uuid1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(uuid3, uuid4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid0, uuid1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid1, uuid3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.has(Seq(uuid3, uuid4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.uuidSet_.has(Seq.empty[UUID]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.uuidSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid1, uuid4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq(uuid1, uuid5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.uuidSet_.hasNo(Seq.empty[UUID]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}