// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.map.types

import java.time.LocalDate
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class FilterMap_LocalDate_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  val a = (1, Map("a" -> localDate1, "b" -> localDate2))
  val b = (2, Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4))

  import api.*
  import suite.*


  "Mandatory: Mandatory map (no filter)" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact
      _ <- Entity.i.a1.localDateMap.query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain keys" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // Get Map value by key

      // Like calling `apply` on a Scala Map.
      _ <- Entity.i.a1.localDateMap("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap("a").query.get.map(_ ==> List((1, localDate1), (2, localDate2)))
      _ <- Entity.i.a1.localDateMap("b").query.get.map(_ ==> List((1, localDate2), (2, localDate3)))
      _ <- Entity.i.a1.localDateMap("c").query.get.map(_ ==> List((2, localDate4)))
    } yield ()
  }


  "Mandatory: Map without certain keys" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.localDateMap.not("_").query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.not("c").query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.not("_", "c").query.get.map(_ ==> List(a))
      // Same as
      _ <- Entity.i.a1.localDateMap.not(List("_")).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.not(List("c")).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.not(List("_", "c")).query.get.map(_ ==> List(a))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.localDateMap.not(List.empty[String]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Mandatory: Map with certain values" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // Maps containing value

      _ <- Entity.i.a1.localDateMap.has(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.has(localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.has(localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.has(localDate3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.has(localDate4).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate3)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate4)).query.get.map(_ ==> List(b))

      // OR semantics when multiple values

      // "Has this OR that" value
      _ <- Entity.i.a1.localDateMap.has(localDate0, localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.has(localDate1, localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.has(localDate2, localDate3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.has(localDate3, localDate4).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.has(localDate4, localDate5).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.has(localDate5, localDate6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate0, localDate1)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate2, localDate3)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate3, localDate4)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate4, localDate5)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.has(Seq(localDate5, localDate6)).query.get.map(_ ==> List())

      // No values match nothing
      _ <- Entity.i.a1.localDateMap.has(Seq.empty[LocalDate]).query.get.map(_ ==> List())
    } yield ()
  }


  "Mandatory: Map without certain values" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // "Doesn't have this"
      _ <- Entity.i.a1.localDateMap.hasNo(localDate0).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.hasNo(localDate1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.hasNo(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(localDate3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.hasNo(localDate3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.hasNo(localDate5).query.get.map(_ ==> List(a, b))
      // Same as
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate0)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate1)).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate3)).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate5)).query.get.map(_ ==> List(a, b))

      // OR semantics when multiple values

      // "Has neither this OR that"
      _ <- Entity.i.a1.localDateMap.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(localDate1, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(localDate1, localDate5).query.get.map(_ ==> List(b))
      // Same as
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate1, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap.hasNo(List(localDate1, localDate5)).query.get.map(_ ==> List(b))

      // No values match nothing
      _ <- Entity.i.a1.localDateMap.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(a, b))
    } yield ()
  }


  "Tacit: Tacit Map (no filter)" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact
      _ <- Entity.i.a1.localDateMap_.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain keys" - types {
    for {
      _ <- Entity.i.insert(0).transact // Entity without map attribute
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // "Map contains this OR that key"
      _ <- Entity.i.a1.localDateMap_("_").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_("a").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_("b").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_("c").query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_("a", "c").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_("_", "c").query.get.map(_ ==> List(2))
      // Same as
      _ <- Entity.i.a1.localDateMap_(List("_")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_(List("a")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_(List("b")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_(List("c")).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_(List("a", "c")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_(List("_", "c")).query.get.map(_ ==> List(2))

      // Empty Seq of keys matches nothing
      _ <- Entity.i.a1.localDateMap_(List.empty[String]).query.get.map(_ ==> List())

      // Match entities without map attribute
      _ <- Entity.i.a1.localDateMap_().query.get.map(_ ==> List(0))
    } yield ()
  }


  "Tacit: Match map without certain keys" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // "Map contains neither this OR that key"
      _ <- Entity.i.a1.localDateMap_.not("_").query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.not("a").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.not("b").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.not("c").query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.not("a", "c").query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.not("_", "c").query.get.map(_ ==> List(1))
      // Same as
      _ <- Entity.i.a1.localDateMap_.not(List("_")).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.not(List("a")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.not(List("b")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.not(List("c")).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.not(List("a", "c")).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.not(List("_", "c")).query.get.map(_ ==> List(1))

      // Negating empty Seq of keys matches all
      _ <- Entity.i.a1.localDateMap_.not(List.empty[String]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Tacit: Match map with certain values" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // "Map contains this OR that value"
      _ <- Entity.i.a1.localDateMap_.has(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.has(localDate1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.has(localDate2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.has(localDate3).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(localDate4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(localDate0, localDate1).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.has(localDate1, localDate2).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.has(localDate2, localDate3).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.has(localDate3, localDate4).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(localDate4, localDate5).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(localDate5, localDate6).query.get.map(_ ==> List())
      // Same as
      _ <- Entity.i.a1.localDateMap_.has(List(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.has(List(localDate1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate3)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate0, localDate1)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate1, localDate2)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate2, localDate3)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate3, localDate4)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate4, localDate5)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.has(List(localDate5, localDate6)).query.get.map(_ ==> List())

      // Empty Seq of values matches nothing
      _ <- Entity.i.a1.localDateMap_.has(List.empty[LocalDate]).query.get.map(_ ==> List())
    } yield ()
  }


  "Tacit: Match map without certain values" - types {
    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact

      // "Map contains neither this OR that value"
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate0).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate3).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate0, localDate1).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate1, localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate2, localDate3).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate3, localDate4).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate4, localDate5).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(localDate5, localDate6).query.get.map(_ ==> List(1, 2))
      // Same as
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate0)).query.get.map(_ ==> List(1, 2))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate3)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate0, localDate1)).query.get.map(_ ==> List(2))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate1, localDate2)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate2, localDate3)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate3, localDate4)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate4, localDate5)).query.get.map(_ ==> List(1))
      _ <- Entity.i.a1.localDateMap_.hasNo(List(localDate5, localDate6)).query.get.map(_ ==> List(1, 2))

      // Negating empty Seq of values matches all
      _ <- Entity.i.a1.localDateMap_.hasNo(List.empty[LocalDate]).query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Optional map (no filter)" - types {
    val a = (1, Some(Map("a" -> localDate1, "b" -> localDate2)))
    val b = (2, Some(Map("a" -> localDate2, "b" -> localDate3, "c" -> localDate4)))
    val c = (3, None)
    for {
      _ <- Entity.i.localDateMap_?.insert(a, b, c).transact
      _ <- Entity.i.a1.localDateMap_?.query.get.map(_ ==> List(a, b, c))
    } yield ()
  }


  "Optional map values by key" - types {

    for {
      _ <- Entity.i.localDateMap.insert(a, b).transact
      _ <- Entity.i(3).save.transact // entity without localDateMap

      // Like calling `get` on a Scala Map.
      _ <- Entity.i.a1.localDateMap_?("_").query.get.map(_ ==> List((1, None), (2, None), (3, None)))
      _ <- Entity.i.a1.localDateMap_?("a").query.get.map(_ ==> List((1, Some(localDate1)), (2, Some(localDate2)), (3, None)))
      _ <- Entity.i.a1.localDateMap_?("b").query.get.map(_ ==> List((1, Some(localDate2)), (2, Some(localDate3)), (3, None)))
      _ <- Entity.i.a1.localDateMap_?("c").query.get.map(_ ==> List((1, None), (2, Some(localDate4)), (3, None)))
    } yield ()
  }
}