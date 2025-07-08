// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.time.LocalTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSeq_LocalTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(localTime1, localTime2))
  val b = (2, List(localTime2, localTime3, localTime3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localTimeSeq.has(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.has(localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.has(localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(localTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localTimeSeq.has(localTime0, localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(localTime1, localTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime0, localTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime1, localTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.has(List(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.localTimeSeq.has(List.empty[LocalTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.localTimeSeq.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localTimeSeq_.has(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.has(localTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.has(localTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(localTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localTimeSeq_.has(localTime0, localTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(localTime1, localTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(localTime3, localTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime0, localTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime1, localTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.has(List(localTime3, localTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.localTimeSeq_.has(List.empty[LocalTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(localTime1, localTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List(localTime1, localTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.localTimeSeq_.hasNo(List.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}