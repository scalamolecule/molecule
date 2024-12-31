// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSeq_Long_(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  val a = (1, List(long1, long2))
  val b = (2, List(long2, long3, long3))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.longSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.longSeq.has(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.has(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.has(long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(long3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.longSeq.has(List(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.has(List(long1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.has(List(long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(List(long3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.longSeq.has(long0, long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.has(long1, long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(long1, long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(long2, long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(long1, long2, long3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.longSeq.has(List(long0, long1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.has(List(long1, long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(List(long1, long3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(List(long2, long3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.has(List(long1, long2, long3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.longSeq.has(List.empty[Long]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.longSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.longSeq.hasNo(long0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.hasNo(long1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longSeq.hasNo(long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.hasNo(long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.hasNo(long5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.longSeq.hasNo(List(long0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSeq.hasNo(List(long1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longSeq.hasNo(List(long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(List(long3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.hasNo(List(long3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSeq.hasNo(List(long5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.longSeq.hasNo(long1, long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(long1, long5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.longSeq.hasNo(List(long1, long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(List(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(List(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq.hasNo(List(long1, long5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.longSeq.hasNo(List.empty[Long]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.longSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.longSeq_.has(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.has(long1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.has(long2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(long3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longSeq_.has(List(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.has(List(long1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.has(List(long2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(List(long3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.longSeq_.has(long0, long1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.has(long1, long2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(long1, long3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(long2, long3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(long3, long4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longSeq_.has(List(long0, long1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.has(List(long1, long2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(List(long1, long3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(List(long2, long3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.has(List(long3, long4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.longSeq_.has(List.empty[Long]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.longSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.longSeq_.hasNo(long0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.hasNo(long1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longSeq_.hasNo(long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(long3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.hasNo(long3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.hasNo(long5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.longSeq_.hasNo(List(long0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSeq_.hasNo(List(long1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longSeq_.hasNo(List(long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(List(long3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.hasNo(List(long3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSeq_.hasNo(List(long5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.longSeq_.hasNo(long1, long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(long1, long5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longSeq_.hasNo(List(long1, long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(List(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(List(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSeq_.hasNo(List(long1, long5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.longSeq_.hasNo(List.empty[Long]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}