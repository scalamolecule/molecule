package molecule.db.compliance.test.filter.seq

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterSeq_Enum(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import Color.*
  import api.*
  import suite.*

  val a = (1, List(Red.toString, Green.toString))
  val b = (2, List(Green.toString, Blue.toString, Blue.toString))


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.colorSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.colorSeq.has(White).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.has(Red).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSeq.has(Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(Blue).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.colorSeq.has(List(White)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.has(List(Red)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSeq.has(List(Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(List(Blue)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.colorSeq.has(White, Red).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSeq.has(Red, Green).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(Red, Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(Green, Blue).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(Red, Green, Blue).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.colorSeq.has(List(White, Red)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSeq.has(List(Red, Green)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(List(Red, Blue)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(List(Green, Blue)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.has(List(Red, Green, Blue)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.colorSeq.has(List.empty[Color]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.colorSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.colorSeq.hasNo(White).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.hasNo(Red).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.colorSeq.hasNo(Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.hasNo(Blue).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSeq.hasNo(Yellow).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.colorSeq.hasNo(List(White)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.colorSeq.hasNo(List(Red)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.colorSeq.hasNo(List(Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.hasNo(List(Blue)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.colorSeq.hasNo(List(Yellow)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.colorSeq.hasNo(Red, Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.hasNo(Red, Blue).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.hasNo(Red, Yellow).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.colorSeq.hasNo(List(Red, Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.hasNo(List(Red, Blue)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq.hasNo(List(Red, Yellow)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.colorSeq.hasNo(List.empty[Color]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.colorSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.colorSeq_.has(White).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.has(Red).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSeq_.has(Green).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(Blue).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.colorSeq_.has(List(White)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.has(List(Red)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSeq_.has(List(Green)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(List(Blue)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.colorSeq_.has(White, Red).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSeq_.has(Red, Green).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(Red, Blue).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(Green, Blue).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(Blue, Yellow).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.colorSeq_.has(List(White, Red)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSeq_.has(List(Red, Green)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(List(Red, Blue)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(List(Green, Blue)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.has(List(Blue, Yellow)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.colorSeq_.has(List.empty[Color]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.colorSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.colorSeq_.hasNo(White).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.hasNo(Red).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.colorSeq_.hasNo(Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.hasNo(Blue).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSeq_.hasNo(Yellow).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.colorSeq_.hasNo(List(White)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Red)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Blue)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Yellow)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.colorSeq_.hasNo(Red, Green).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.hasNo(Red, Blue).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.hasNo(Red, Yellow).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Red, Green)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Red, Blue)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.colorSeq_.hasNo(List(Red, Yellow)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.colorSeq_.hasNo(List.empty[Color]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}