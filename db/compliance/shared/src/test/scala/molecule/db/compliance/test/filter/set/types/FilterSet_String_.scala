// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.set.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterSet_String_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Set(string1, string2))
  val b = (2, Set(string2, string3, string4))

  import api.*
  import suite.*


  "Mandatory: has" - types {
    for {
      _ <- Entity.i.stringSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.stringSet.has(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.has(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSet.has(string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(string3).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.stringSet.has(Seq(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.has(Seq(string1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSet.has(Seq(string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(Seq(string3)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.stringSet.has(string1, string2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(string1, string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(string2, string3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(string1, string2, string3).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.stringSet.has(Seq(string1, string2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(Seq(string1, string3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(Seq(string2, string3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.has(Seq(string1, string2, string3)).query.get.map(_ ==> List(a, b))


      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.stringSet.has(Seq.empty[String]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: hasNo" - types {
    for {
      _ <- Entity.i.stringSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.stringSet.hasNo(string0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.hasNo(string1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringSet.hasNo(string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(string3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSet.hasNo(string4).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSet.hasNo(string5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string4)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.stringSet.hasNo(string1, string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(string1, string4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(string1, string5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string1, string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string1, string4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet.hasNo(Seq(string1, string5)).query.get.map(_ ==> List(b))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.stringSet.hasNo(Seq.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: has" - types {
    for {
      _ <- Entity.i.stringSet.insert(a, b).transact

      // Sets with one or more values matching

      // "Has this"
      _ <- Entity.i.a1.stringSet_.has(string0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.has(string1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.has(string2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(string3).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringSet_.has(Seq(string0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.has(Seq(string1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.has(Seq(string2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(Seq(string3)).query.get.map(_ ==> List(2))

      // OR semantics when multiple values

      // "Has this OR that"
      _ <- Entity.i.a1.stringSet_.has(string0, string1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.has(string1, string2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(string1, string3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(string2, string3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(string3, string4).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringSet_.has(Seq(string0, string1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.has(Seq(string1, string2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(Seq(string1, string3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(Seq(string2, string3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.has(Seq(string3, string4)).query.get.map(_ ==> List(2))

      // Empty Seq/Sets match nothing
      _ <- Entity.i.a1.stringSet_.has(Seq.empty[String]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: hasNo" - types {
    for {
      _ <- Entity.i.stringSet.insert(a, b).transact

      // Sets without one or more values matching

      // "Doesn't have this"
      _ <- Entity.i.a1.stringSet_.hasNo(string0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.hasNo(string1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringSet_.hasNo(string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(string3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.hasNo(string4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.hasNo(string5).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string5)).query.get.map(_ ==> List(1, 2))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.stringSet_.hasNo(string1, string2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(string1, string3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(string1, string4).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(string1, string5).query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string1, string2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string1, string3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string1, string4)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.stringSet_.hasNo(Seq(string1, string5)).query.get.map(_ ==> List(2))

      // Negating empty Seqs/Sets has no effect
      _ <- Entity.i.a1.stringSet_.hasNo(Seq.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }

  // No filtering on optional Set attributes
}