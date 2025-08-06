package molecule.db.compliance.test.filter.set

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterSet_Enum(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import Color.*
  import api.*
  import suite.*

  val a = (1, Set(Red.toString, Green.toString))
  val b = (2, Set(Green.toString, Blue.toString, Yellow.toString))


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.colorSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.colorSet.has(White).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.has(Red).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.has(Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Blue).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.colorSet.has(Seq(White)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.has(Seq(Red)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.has(Seq(Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Blue)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.colorSet.has(Red, Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Red, Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Green, Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Red, Green, Blue).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.colorSet.has(Seq(Red, Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Red, Blue)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Green, Blue)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.has(Seq(Red, Green, Blue)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.colorSet.has(Seq.empty[Color]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.colorSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.colorSet.hasNo(White).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.hasNo(Red).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.colorSet.hasNo(Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Blue).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.hasNo(Yellow).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.hasNo(Black).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.colorSet.hasNo(Seq(White)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Red)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Blue)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Yellow)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Black)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.colorSet.hasNo(Red, Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Red, Blue).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Red, Yellow).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Red, Black).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Red, Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Red, Blue)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Red, Yellow)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet.hasNo(Seq(Red, Black)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.colorSet.hasNo(Seq.empty[Color]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.colorSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.colorSet_.has(White).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.has(Red).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.has(Green).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Blue).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.colorSet_.has(Seq(White)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.has(Seq(Red)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.has(Seq(Green)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Seq(Blue)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.colorSet_.has(White, Red).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.has(Red, Green).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Red, Blue).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Green, Blue).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Blue, Yellow).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.colorSet_.has(Seq(White, Red)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.has(Seq(Red, Green)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Seq(Red, Blue)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Seq(Green, Blue)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.has(Seq(Blue, Yellow)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.colorSet_.has(Seq.empty[Color]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.colorSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.colorSet_.hasNo(White).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.hasNo(Red).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.colorSet_.hasNo(Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Blue).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.hasNo(Yellow).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.hasNo(Black).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(White)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Red)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Blue)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Yellow)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Black)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.colorSet_.hasNo(Red, Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Red, Blue).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Red, Yellow).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Red, Black).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Red, Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Red, Blue)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Red, Yellow)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSet_.hasNo(Seq(Red, Black)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.colorSet_.hasNo(Seq.empty[Color]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}