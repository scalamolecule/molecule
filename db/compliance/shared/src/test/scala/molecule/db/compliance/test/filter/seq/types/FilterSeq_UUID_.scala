// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.util.UUID
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_UUID_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(uuid1, uuid2))
  val b = (2, List(uuid2, uuid3, uuid3))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.uuidSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uuidSeq.has(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.has(uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.has(uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(uuid3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uuidSeq.has(List(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.has(List(uuid1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.has(List(uuid2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(List(uuid3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uuidSeq.has(uuid0, uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.has(uuid1, uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(uuid1, uuid3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(uuid2, uuid3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(uuid1, uuid2, uuid3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uuidSeq.has(List(uuid0, uuid1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.has(List(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(List(uuid1, uuid3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(List(uuid2, uuid3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.has(List(uuid1, uuid2, uuid3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.uuidSeq.has(List.empty[UUID]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.uuidSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(uuid1, uuid5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.uuidSeq.hasNo(List.empty[UUID]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.uuidSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uuidSeq_.has(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.has(uuid1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.has(uuid2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(uuid3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uuidSeq_.has(uuid0, uuid1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.has(uuid1, uuid2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(uuid1, uuid3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(uuid2, uuid3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(uuid3, uuid4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid0, uuid1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid1, uuid2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid1, uuid3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid2, uuid3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.has(List(uuid3, uuid4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.uuidSeq_.has(List.empty[UUID]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.uuidSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid1, uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid1, uuid3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(uuid1, uuid5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid1, uuid2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid1, uuid3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuidSeq_.hasNo(List(uuid1, uuid5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.uuidSeq_.hasNo(List.empty[UUID]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}