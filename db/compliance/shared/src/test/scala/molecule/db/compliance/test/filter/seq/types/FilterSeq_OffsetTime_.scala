// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.time.OffsetTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_OffsetTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(offsetTime1, offsetTime2))
  val b = (2, List(offsetTime2, offsetTime3, offsetTime3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime0, offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime0, offsetTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.has(List(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.offsetTimeSeq.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.offsetTimeSeq.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime0, offsetTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(offsetTime3, offsetTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime0, offsetTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.has(List(offsetTime3, offsetTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.offsetTimeSeq_.has(List.empty[OffsetTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.offsetTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List(offsetTime1, offsetTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.offsetTimeSeq_.hasNo(List.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}