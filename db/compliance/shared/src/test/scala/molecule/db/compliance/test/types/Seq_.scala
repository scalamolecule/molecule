package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class Seq_(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "set 0-1" - types {
    // Mandatory
    lazy val a1: Seq[Int] = Entity.intSeq.query.get.head
    lazy val a2: Seq[Int] = Entity.intSeq.apply(Seq(int1)).query.get.head
    lazy val a4: Seq[Int] = Entity.intSeq.has(Entity.int_).query.get.head


    // Optional
    lazy val c1: Option[Seq[Int]] = Entity.intSeq_?.query.get.head
    lazy val c2: Option[Seq[Int]] = Entity.intSeq_?.apply(Some(Seq(int1))).query.get.head
  }


  "set 1-n" - types {
    // Tacit - stays a String
    lazy val a1: String = Entity.s.intSeq_.query.get.head
    lazy val a2: String = Entity.s.intSeq_.apply(Seq(int1)).query.get.head
    lazy val a4: String = Entity.s.intSeq_.has(Entity.int_).query.get.head


    // Mandatory
    lazy val b1: (String, Seq[Int]) = Entity.s.intSeq.query.get.head
    lazy val b2: (String, Seq[Int]) = Entity.s.intSeq.apply(Seq(int1)).query.get.head
    lazy val b4: (String, Seq[Int]) = Entity.s.intSeq.has(Entity.int_).query.get.head

    // Optional
    lazy val d1: (String, Option[Seq[Int]]) = Entity.s.intSeq_?.query.get.head
    lazy val d2: (String, Option[Seq[Int]]) = Entity.s.intSeq_?.apply(Some(Seq(int1))).query.get.head
  }


  "set n-n" - types {
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.intSeq_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.intSeq_.apply(Seq(int1)).query.get.head
    lazy val a4: (Int, String) = Entity.i.s.intSeq_.has(Entity.int_).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Seq[Int]) = Entity.i.s.intSeq.query.get.head
    lazy val b2: (Int, String, Seq[Int]) = Entity.i.s.intSeq.apply(Seq(int1)).query.get.head
    lazy val b4: (Int, String, Seq[Int]) = Entity.i.s.intSeq.has(Entity.int_).query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Seq[Int]]) = Entity.i.s.intSeq_?.query.get.head
    lazy val d2: (Int, String, Option[Seq[Int]]) = Entity.i.s.intSeq_?.apply(Some(Seq(int1))).query.get.head
  }
}
