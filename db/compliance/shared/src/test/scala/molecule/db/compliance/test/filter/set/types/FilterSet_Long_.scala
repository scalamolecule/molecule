// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSet_Long_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(long1, long2))
  val b = (2, Set(long2, long3, long4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.longSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.longSet.has(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.has(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSet.has(long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(long3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.longSet.has(Seq(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.has(Seq(long1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSet.has(Seq(long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(Seq(long3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.longSet.has(long1, long2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(long1, long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(long2, long3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(long1, long2, long3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.longSet.has(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(Seq(long1, long3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(Seq(long2, long3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.has(Seq(long1, long2, long3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.longSet.has(Seq.empty[Long]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.longSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.longSet.hasNo(long0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.hasNo(long1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longSet.hasNo(long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(long3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSet.hasNo(long4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSet.hasNo(long5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.longSet.hasNo(Seq(long0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.longSet.hasNo(Seq(long1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.longSet.hasNo(Seq(long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(Seq(long3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSet.hasNo(Seq(long4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.longSet.hasNo(Seq(long5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.longSet.hasNo(long1, long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(long1, long4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(long1, long5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.longSet.hasNo(Seq(long1, long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(Seq(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(Seq(long1, long4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet.hasNo(Seq(long1, long5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.longSet.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.longSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.longSet_.has(long0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.has(long1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.has(long2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(long3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longSet_.has(Seq(long0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.has(Seq(long1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.has(Seq(long2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(Seq(long3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.longSet_.has(long0, long1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.has(long1, long2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(long1, long3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(long2, long3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(long3, long4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longSet_.has(Seq(long0, long1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.has(Seq(long1, long2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(Seq(long1, long3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(Seq(long2, long3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.has(Seq(long3, long4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.longSet_.has(Seq.empty[Long]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.longSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.longSet_.hasNo(long0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.hasNo(long1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longSet_.hasNo(long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(long3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.hasNo(long4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.hasNo(long5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.longSet_.hasNo(long1, long2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(long1, long3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(long1, long4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(long1, long5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long1, long2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long1, long3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long1, long4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.longSet_.hasNo(Seq(long1, long5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.longSet_.hasNo(Seq.empty[Long]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}