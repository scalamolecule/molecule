// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.seq.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSeq_BigDecimal_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(bigDecimal1, bigDecimal2))
  val b = (2, List(bigDecimal2, bigDecimal3, bigDecimal3))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(bigDecimal1, bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.has(List(bigDecimal1, bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.bigDecimalSeq.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.bigDecimalSeq.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal1, bigDecimal3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(bigDecimal3, bigDecimal4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.has(List(bigDecimal3, bigDecimal4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.bigDecimalSeq_.has(List.empty[BigDecimal]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.bigDecimalSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(bigDecimal1, bigDecimal5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List(bigDecimal1, bigDecimal5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.bigDecimalSeq_.hasNo(List.empty[BigDecimal]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}