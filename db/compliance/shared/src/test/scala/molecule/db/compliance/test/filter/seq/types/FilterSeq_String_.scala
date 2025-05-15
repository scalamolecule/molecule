// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterSeq_String_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(string1, string2))
  val b = (2, List(string2, string3, string3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.stringSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.stringSeq.has(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.has(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.has(string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(string3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.stringSeq.has(List(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.has(List(string1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.has(List(string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(List(string3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.stringSeq.has(string0, string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.has(string1, string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(string1, string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(string2, string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.stringSeq.has(List(string0, string1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.has(List(string1, string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(List(string1, string3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(List(string2, string3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.has(List(string1, string2, string3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.stringSeq.has(List.empty[String]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.stringSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.stringSeq.hasNo(string0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.hasNo(string1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringSeq.hasNo(string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.hasNo(string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.hasNo(string5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.stringSeq.hasNo(List(string0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSeq.hasNo(List(string1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringSeq.hasNo(List(string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(List(string3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.hasNo(List(string3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSeq.hasNo(List(string5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.stringSeq.hasNo(string1, string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(string1, string5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.stringSeq.hasNo(List(string1, string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(List(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(List(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq.hasNo(List(string1, string5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.stringSeq.hasNo(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.stringSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.stringSeq_.has(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.has(string1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.has(string2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(string3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringSeq_.has(List(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.has(List(string1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.has(List(string2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(List(string3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.stringSeq_.has(string0, string1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.has(string1, string2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(string1, string3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(string2, string3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(string3, string4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringSeq_.has(List(string0, string1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.has(List(string1, string2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(List(string1, string3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(List(string2, string3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.has(List(string3, string4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.stringSeq_.has(List.empty[String]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.stringSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.stringSeq_.hasNo(string0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.hasNo(string1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringSeq_.hasNo(string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(string3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.hasNo(string3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.hasNo(string5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.stringSeq_.hasNo(string1, string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(string1, string5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string1, string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSeq_.hasNo(List(string1, string5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.stringSeq_.hasNo(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}