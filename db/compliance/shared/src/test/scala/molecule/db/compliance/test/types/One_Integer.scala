package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class One_Integer(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "integer 0-1" - types {
    // Mandatory
    lazy val a1: Long = Entity.long.query.get.head
    lazy val a2: Long = Entity.long.apply(long1).query.get.head
    lazy val a3: Long = Entity.long.apply(?).query(long1).get.head
    lazy val a4: Long = Entity.long.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: Long = Entity.long.a1.query.get.head
    lazy val a6: Long = Entity.long.apply(long1).a1.query.get.head
    lazy val a7: Long = Entity.long.apply(?).a1.query(long1).get.head
    lazy val a8: Long = Entity.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int       = Entity.long.apply(count).query.get.head
    lazy val b2: Long      = Entity.long.apply(min).query.get.head
    lazy val b3: Set[Long] = Entity.long.apply(min(3)).query.get.head
    lazy val b4: Double    = Entity.long.apply(avg).query.get.head

    lazy val c1: Int       = Entity.long.apply(count).a1.query.get.head
    lazy val c2: Long      = Entity.long.apply(min).a1.query.get.head
    lazy val c3: Set[Long] = Entity.long.apply(min(3)).a1.query.get.head
    lazy val c4: Double    = Entity.long.apply(avg).a1.query.get.head

    lazy val d1: Int       = Entity.long.apply(count).>(1).query.get.head
    lazy val d2: Long      = Entity.long.apply(min).>(1).query.get.head
    lazy val d3: Double    = Entity.long.apply(avg).>(1).query.get.head

    lazy val e1: Int       = Entity.long.apply(count).>(1).a1.query.get.head
    lazy val e2: Long      = Entity.long.apply(min).>(1).a1.query.get.head
    lazy val e3: Double    = Entity.long.apply(avg).>(1).a1.query.get.head


    // Optional
    lazy val f1: Option[Long] = Entity.long_?.query.get.head
    lazy val f2: Option[Long] = Entity.long_?.apply(Some(long1)).query.get.head

    lazy val g1: Option[Long] = Entity.long_?.a1.query.get.head
    lazy val g2: Option[Long] = Entity.long_?.apply(Some(long1)).a1.query.get.head
  }


  "integer 1-n" - types {
    // Tacit - stays a String
    lazy val a1: String = Entity.s.long_.query.get.head
    lazy val a2: String = Entity.s.long_.apply(long1).query.get.head
    lazy val a3: String = Entity.s.long_.apply(?).query(long1).get.head
    lazy val a4: String = Entity.s.long_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (String, Long) = Entity.s.long.query.get.head
    lazy val b2: (String, Long) = Entity.s.long.apply(long1).query.get.head
    lazy val b3: (String, Long) = Entity.s.long.apply(?).query(long1).get.head
    lazy val b4: (String, Long) = Entity.s.long.apply(Entity.i_).query.get.head

    lazy val b5: (String, Long) = Entity.s.long.a1.query.get.head
    lazy val b6: (String, Long) = Entity.s.long.apply(long1).a1.query.get.head
    lazy val b7: (String, Long) = Entity.s.long.apply(?).a1.query(long1).get.head
    lazy val b8: (String, Long) = Entity.s.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (String, Int)       = Entity.s.long.apply(count).query.get.head
    lazy val c2: (String, Long)      = Entity.s.long.apply(min).query.get.head
    lazy val c3: (String, Set[Long]) = Entity.s.long.apply(min(3)).query.get.head
    lazy val c4: (String, Double)    = Entity.s.long.apply(avg).query.get.head

    lazy val d1: (String, Int)       = Entity.s.long.apply(count).a1.query.get.head
    lazy val d2: (String, Long)      = Entity.s.long.apply(min).a1.query.get.head
    lazy val d3: (String, Set[Long]) = Entity.s.long.apply(min(3)).a1.query.get.head
    lazy val d4: (String, Double)    = Entity.s.long.apply(avg).a1.query.get.head

    lazy val e1: (String, Int)       = Entity.s.long.apply(count).>(1).query.get.head
    lazy val e2: (String, Long)      = Entity.s.long.apply(min).>(1L).query.get.head
    lazy val e3: (String, Double)    = Entity.s.long.apply(avg).>(1.5).query.get.head

    lazy val f1: (String, Int)       = Entity.s.long.apply(count).>(1).a1.query.get.head
    lazy val f2: (String, Long)      = Entity.s.long.apply(min).>(1L).a1.query.get.head
    lazy val f3: (String, Double)    = Entity.s.long.apply(avg).>(1.5).a1.query.get.head


    // Optional
    lazy val g1: (String, Option[Long]) = Entity.s.long_?.query.get.head
    lazy val g2: (String, Option[Long]) = Entity.s.long_?.apply(Some(long1)).query.get.head

    lazy val h3: (String, Option[Long]) = Entity.s.long_?.a1.query.get.head
    lazy val h4: (String, Option[Long]) = Entity.s.long_?.apply(Some(long1)).a1.query.get.head
  }


  "integer n-n" - types {
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.long_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.long_.apply(long1).query.get.head
    lazy val a3: (Int, String) = Entity.i.s.long_.apply(?).query(long1).get.head
    lazy val a4: (Int, String) = Entity.i.s.long_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Long) = Entity.i.s.long.query.get.head
    lazy val b2: (Int, String, Long) = Entity.i.s.long.apply(long1).query.get.head
    lazy val b3: (Int, String, Long) = Entity.i.s.long.apply(?).query(long1).get.head
    lazy val b4: (Int, String, Long) = Entity.i.s.long.apply(Entity.i_).query.get.head

    lazy val b5: (Int, String, Long) = Entity.i.s.long.a1.query.get.head
    lazy val b6: (Int, String, Long) = Entity.i.s.long.apply(long1).a1.query.get.head
    lazy val b7: (Int, String, Long) = Entity.i.s.long.apply(?).a1.query(long1).get.head
    lazy val b8: (Int, String, Long) = Entity.i.s.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (Int, String, Int)       = Entity.i.s.long.apply(count).query.get.head
    lazy val c2: (Int, String, Long)      = Entity.i.s.long.apply(min).query.get.head
    lazy val c3: (Int, String, Set[Long]) = Entity.i.s.long.apply(min(3)).query.get.head
    lazy val c4: (Int, String, Double)    = Entity.i.s.long.apply(avg).query.get.head

    lazy val c5: (Int, String, Int)       = Entity.i.s.long.apply(count).a1.query.get.head
    lazy val c6: (Int, String, Long)      = Entity.i.s.long.apply(min).a1.query.get.head
    lazy val c7: (Int, String, Set[Long]) = Entity.i.s.long.apply(min(3)).a1.query.get.head
    lazy val c8: (Int, String, Double)    = Entity.i.s.long.apply(avg).a1.query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Long]) = Entity.i.s.long_?.query.get.head
    lazy val d2: (Int, String, Option[Long]) = Entity.i.s.long_?.apply(Some(long1)).query.get.head

    lazy val d3: (Int, String, Option[Long]) = Entity.i.s.long_?.a1.query.get.head
    lazy val d4: (Int, String, Option[Long]) = Entity.i.s.long_?.apply(Some(long1)).a1.query.get.head
  }


  "integer aggr 1" - types {
    // Mandatory
    lazy val a1: Long = Entity.long.query.get.head
    lazy val a2: Long = Entity.long.apply(long1).query.get.head
    lazy val a3: Long = Entity.long.apply(?).query(long1).get.head
    lazy val a4: Long = Entity.long.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: Long = Entity.long.a1.query.get.head
    lazy val a6: Long = Entity.long.apply(long1).a1.query.get.head
    lazy val a7: Long = Entity.long.apply(?).a1.query(long1).get.head
    lazy val a8: Long = Entity.long.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int       = Entity.long.apply(count).query.get.head
    lazy val b2: Long      = Entity.long.apply(min).query.get.head
    lazy val b3: Set[Long] = Entity.long.apply(min(3)).query.get.head
    lazy val b4: Double    = Entity.long.apply(avg).query.get.head

    lazy val b5: Int       = Entity.long.apply(count).a1.query.get.head
    lazy val b6: Long      = Entity.long.apply(min).a1.query.get.head
    lazy val b7: Set[Long] = Entity.long.apply(min(3)).a1.query.get.head
    lazy val b8: Double    = Entity.long.apply(avg).a1.query.get.head


    // Optional
    lazy val c1: Option[Long] = Entity.long_?.query.get.head
    lazy val c2: Option[Long] = Entity.long_?.apply(Some(long1)).query.get.head

    lazy val c3: Option[Long] = Entity.long_?.a1.query.get.head
    lazy val c4: Option[Long] = Entity.long_?.apply(Some(long1)).a1.query.get.head
  }
}
