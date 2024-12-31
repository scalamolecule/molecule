// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.set.types

import java.time.LocalDateTime
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterSet_LocalDateTime_(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  val a = (1, Set(localDateTime1, localDateTime2))
  val b = (2, Set(localDateTime2, localDateTime3, localDateTime4))

  import api._
  import suite._


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(localDateTime1, localDateTime2, localDateTime3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.has(Seq(localDateTime1, localDateTime2, localDateTime3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.localDateTimeSet.has(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq(localDateTime1, localDateTime5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.localDateTimeSet.hasNo(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime0, localDateTime1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime1, localDateTime2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime1, localDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime2, localDateTime3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(localDateTime3, localDateTime4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime0, localDateTime1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq(localDateTime3, localDateTime4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.localDateTimeSet_.has(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.localDateTimeSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(localDateTime1, localDateTime5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq(localDateTime1, localDateTime5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.localDateTimeSet_.hasNo(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}