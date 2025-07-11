package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Map_(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "map 0-1" - types { implicit conn =>
    // Mandatory
    lazy val a1: Map[String, Int] = Entity.intMap.query.get.head
    lazy val a2: Map[String, Int] = Entity.intMap.apply(Map(string1 -> int1)).query.get.head


    // Optional
    lazy val c1: Option[Map[String, Int]] = Entity.intMap_?.query.get.head
    lazy val c2: Option[Map[String, Int]] = Entity.intMap_?.apply(Some(Map(string1 -> int1))).query.get.head
  }


  "map 1-n" - types { implicit conn =>
    // Tacit - stays a String
    lazy val a1: String = Entity.s.intMap_.query.get.head
    lazy val a2: String = Entity.s.intMap_.has(int1).query.get.head


    // Mandatory
    lazy val b1: (String, Map[String, Int]) = Entity.s.intMap.query.get.head
    lazy val b2: (String, Map[String, Int]) = Entity.s.intMap.apply(Map(string1 -> int1)).query.get.head

    // Optional
    lazy val d1: (String, Option[Map[String, Int]]) = Entity.s.intMap_?.query.get.head
    lazy val d2: (String, Option[Map[String, Int]]) = Entity.s.intMap_?.apply(Some(Map(string1 -> int1))).query.get.head
  }


  "map n-n" - types { implicit conn =>
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.intMap_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.intMap_.has(int1).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Map[String, Int]) = Entity.i.s.intMap.query.get.head
    lazy val b2: (Int, String, Map[String, Int]) = Entity.i.s.intMap.apply(Map(string1 -> int1)).query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Map[String, Int]]) = Entity.i.s.intMap_?.query.get.head
    lazy val d2: (Int, String, Option[Map[String, Int]]) = Entity.i.s.intMap_?.apply(Some(Map(string1 -> int1))).query.get.head
  }
}
