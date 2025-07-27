package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Set_(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "set 0-1" - types {
    // Mandatory
    lazy val a1: Set[Int] = Entity.intSet.query.get.head
    lazy val a2: Set[Int] = Entity.intSet.apply(Set(int1)).query.get.head
    lazy val a4: Set[Int] = Entity.intSet.has(Entity.int_).query.get.head


    // Optional
    lazy val c1: Option[Set[Int]] = Entity.intSet_?.query.get.head
    lazy val c2: Option[Set[Int]] = Entity.intSet_?.apply(Some(Set(int1))).query.get.head
  }


  "set 1-n" - types {
    // Tacit - stays a String
    lazy val a1: String = Entity.s.intSet_.query.get.head
    lazy val a2: String = Entity.s.intSet_.apply(Set(int1)).query.get.head
    lazy val a4: String = Entity.s.intSet_.has(Entity.int_).query.get.head


    // Mandatory
    lazy val b1: (String, Set[Int]) = Entity.s.intSet.query.get.head
    lazy val b2: (String, Set[Int]) = Entity.s.intSet.apply(Set(int1)).query.get.head
    lazy val b4: (String, Set[Int]) = Entity.s.intSet.has(Entity.int_).query.get.head

    // Optional
    lazy val d1: (String, Option[Set[Int]]) = Entity.s.intSet_?.query.get.head
    lazy val d2: (String, Option[Set[Int]]) = Entity.s.intSet_?.apply(Some(Set(int1))).query.get.head
  }


  "set n-n" - types {
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.intSet_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.intSet_.apply(Set(int1)).query.get.head
    lazy val a4: (Int, String) = Entity.i.s.intSet_.has(Entity.int_).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Set[Int]) = Entity.i.s.intSet.query.get.head
    lazy val b2: (Int, String, Set[Int]) = Entity.i.s.intSet.apply(Set(int1)).query.get.head
    lazy val b4: (Int, String, Set[Int]) = Entity.i.s.intSet.has(Entity.int_).query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Set[Int]]) = Entity.i.s.intSet_?.query.get.head
    lazy val d2: (Int, String, Option[Set[Int]]) = Entity.i.s.intSet_?.apply(Some(Set(int1))).query.get.head
  }
}
