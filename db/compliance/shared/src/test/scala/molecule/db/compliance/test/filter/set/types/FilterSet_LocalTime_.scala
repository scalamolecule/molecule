// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import java.time.LocalTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSet_LocalTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(localTime1, localTime2))
  val b = (2, Set(localTime2, localTime3, localTime4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localTimeSet.has(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.has(localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSet.has(localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(localTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localTimeSet.has(localTime1, localTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(localTime1, localTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(localTime2, localTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(localTime1, localTime2, localTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime1, localTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.has(Seq(localTime1, localTime2, localTime3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.localTimeSet.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime1, localTime4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(localTime1, localTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq(localTime1, localTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.localTimeSet.hasNo(Seq.empty[LocalTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localTimeSet_.has(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.has(localTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.has(localTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(localTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localTimeSet_.has(localTime0, localTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.has(localTime1, localTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(localTime1, localTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(localTime2, localTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(localTime3, localTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime0, localTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime1, localTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime1, localTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime2, localTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.has(Seq(localTime3, localTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.localTimeSet_.has(Seq.empty[LocalTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localTimeSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime1, localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime1, localTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime1, localTime4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(localTime1, localTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq(localTime1, localTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.localTimeSet_.hasNo(Seq.empty[LocalTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}