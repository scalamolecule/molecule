// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import java.time.OffsetTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSet_OffsetTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(offsetTime1, offsetTime2))
  val b = (2, Set(offsetTime2, offsetTime3, offsetTime4))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.offsetTimeSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(offsetTime1, offsetTime2, offsetTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.has(Seq(offsetTime1, offsetTime2, offsetTime3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.offsetTimeSet.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.offsetTimeSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq(offsetTime1, offsetTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.offsetTimeSet.hasNo(Seq.empty[OffsetTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.offsetTimeSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime0, offsetTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime1, offsetTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime1, offsetTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime2, offsetTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(offsetTime3, offsetTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime0, offsetTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime2, offsetTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq(offsetTime3, offsetTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.offsetTimeSet_.has(Seq.empty[OffsetTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.offsetTimeSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(offsetTime1, offsetTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq(offsetTime1, offsetTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.offsetTimeSet_.hasNo(Seq.empty[OffsetTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}