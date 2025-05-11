// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.seq.types

import java.net.URI
import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class FilterSeq_URI_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, List(uri1, uri2))
  val b = (2, List(uri2, uri3, uri3))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uriSeq.has(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.has(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.has(uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(uri3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uriSeq.has(List(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.has(List(uri1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.has(List(uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(List(uri3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uriSeq.has(uri0, uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.has(uri1, uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(uri1, uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(uri2, uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uriSeq.has(List(uri0, uri1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.has(List(uri1, uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(List(uri1, uri3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(List(uri2, uri3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.has(List(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.uriSeq.has(List.empty[URI]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uriSeq.hasNo(uri0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.hasNo(uri1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriSeq.hasNo(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.hasNo(uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.hasNo(uri5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uriSeq.hasNo(uri1, uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(uri1, uri5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.uriSeq.hasNo(List.empty[URI]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSeq.insert(a, b).transact

      // Seqs with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uriSeq_.has(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.has(uri1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.has(uri2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(uri3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriSeq_.has(List(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.has(List(uri1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.has(List(uri2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(List(uri3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uriSeq_.has(uri0, uri1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.has(uri1, uri2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(uri1, uri3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(uri3, uri4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriSeq_.has(List(uri0, uri1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.has(List(uri1, uri2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(List(uri1, uri3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(List(uri2, uri3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.has(List(uri3, uri4)).query.get.map(_ ==> List(2))

      // Empty Seq/Seqs match nothing
      _ <- Entity.i.a1.uriSeq_.has(List.empty[URI]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSeq.insert(a, b).transact

      // Seqs without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uriSeq_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.hasNo(uri1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriSeq_.hasNo(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(uri3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.hasNo(uri3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.hasNo(uri5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uriSeq_.hasNo(uri1, uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(uri1, uri5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri1, uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSeq_.hasNo(List(uri1, uri5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs has no effect
      _ <- Entity.i.a1.uriSeq_.hasNo(List.empty[URI]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Seq attributes
}