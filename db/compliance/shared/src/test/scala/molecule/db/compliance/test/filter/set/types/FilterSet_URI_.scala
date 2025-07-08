// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import java.net.URI
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSet_URI_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(uri1, uri2))
  val b = (2, Set(uri2, uri3, uri4))

  import api.*
  import suite.*


  "Mandatory: has" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uriSet.has(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.has(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSet.has(uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(uri3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uriSet.has(Seq(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.has(Seq(uri1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSet.has(Seq(uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(Seq(uri3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uriSet.has(uri1, uri2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(uri1, uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(uri2, uri3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(uri1, uri2, uri3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uriSet.has(Seq(uri1, uri2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(Seq(uri1, uri3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(Seq(uri2, uri3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.has(Seq(uri1, uri2, uri3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.uriSet.has(Seq.empty[URI]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uriSet.hasNo(uri0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.hasNo(uri1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriSet.hasNo(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(uri3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSet.hasNo(uri4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSet.hasNo(uri5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uriSet.hasNo(uri1, uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(uri1, uri4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(uri1, uri5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri1, uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri1, uri4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet.hasNo(Seq(uri1, uri5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.uriSet.hasNo(Seq.empty[URI]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.uriSet_.has(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.has(uri1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.has(uri2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(uri3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriSet_.has(Seq(uri0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.has(Seq(uri1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.has(Seq(uri2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(Seq(uri3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.uriSet_.has(uri0, uri1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.has(uri1, uri2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(uri1, uri3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(uri2, uri3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(uri3, uri4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriSet_.has(Seq(uri0, uri1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.has(Seq(uri1, uri2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(Seq(uri1, uri3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(Seq(uri2, uri3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.has(Seq(uri3, uri4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.uriSet_.has(Seq.empty[URI]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types { implicit conn =>
    for {
      _ <- Entity.i.uriSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.uriSet_.hasNo(uri0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.hasNo(uri1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriSet_.hasNo(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(uri3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.hasNo(uri4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.hasNo(uri5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.uriSet_.hasNo(uri1, uri2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(uri1, uri3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(uri1, uri4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(uri1, uri5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri1, uri2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri1, uri3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri1, uri4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uriSet_.hasNo(Seq(uri1, uri5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.uriSet_.hasNo(Seq.empty[URI]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}