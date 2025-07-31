package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class One_Decimal(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "decimal 0-1" - types {
    // Mandatory
    lazy val a1: Float = Entity.float.query.get.head
    lazy val a2: Float = Entity.float.apply(float1).query.get.head
    lazy val a3: Float = Entity.float.apply(?).query(float1).get.head
    lazy val a4: Float = Entity.float.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: Float = Entity.float.a1.query.get.head
    lazy val a6: Float = Entity.float.apply(float1).a1.query.get.head
    lazy val a7: Float = Entity.float.apply(?).a1.query(float1).get.head
    lazy val a8: Float = Entity.float.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int        = Entity.float.apply(count).query.get.head
    lazy val b2: Float      = Entity.float.apply(min).query.get.head
    lazy val b3: Set[Float] = Entity.float.apply(min(3)).query.get.head
    lazy val b4: Double     = Entity.float.apply(avg).query.get.head

    lazy val c1: Int        = Entity.float.apply(count).a1.query.get.head
    lazy val c2: Float      = Entity.float.apply(min).a1.query.get.head
    lazy val c3: Set[Float] = Entity.float.apply(min(3)).a1.query.get.head
    lazy val c4: Double     = Entity.float.apply(avg).a1.query.get.head

    lazy val d1: Int        = Entity.float.apply(count).>(1).query.get.head
    lazy val d2: Float      = Entity.float.apply(min).>(1.5f).query.get.head
    lazy val d3: Double     = Entity.float.apply(avg).>(1.5).query.get.head

    lazy val e1: Int        = Entity.float.apply(count).>(1).a1.query.get.head
    lazy val e2: Float      = Entity.float.apply(min).>(1.5f).a1.query.get.head
    lazy val e3: Double     = Entity.float.apply(avg).>(1.5).a1.query.get.head


    // Optional
    lazy val f1: Option[Float] = Entity.float_?.query.get.head
    lazy val f2: Option[Float] = Entity.float_?.apply(Some(float1)).query.get.head

    lazy val g1: Option[Float] = Entity.float_?.a1.query.get.head
    lazy val g2: Option[Float] = Entity.float_?.apply(Some(float1)).a1.query.get.head
  }


  "decimal 1-n" - types {
    // Tacit - stays a String
    lazy val a1: String = Entity.s.float_.query.get.head
    lazy val a2: String = Entity.s.float_.apply(float1).query.get.head
    lazy val a3: String = Entity.s.float_.apply(?).query(float1).get.head
    lazy val a4: String = Entity.s.float_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (String, Float) = Entity.s.float.query.get.head
    lazy val b2: (String, Float) = Entity.s.float.apply(float1).query.get.head
    lazy val b3: (String, Float) = Entity.s.float.apply(?).query(float1).get.head
    lazy val b4: (String, Float) = Entity.s.float.apply(Entity.i_).query.get.head

    lazy val b5: (String, Float) = Entity.s.float.a1.query.get.head
    lazy val b6: (String, Float) = Entity.s.float.apply(float1).a1.query.get.head
    lazy val b7: (String, Float) = Entity.s.float.apply(?).a1.query(float1).get.head
    lazy val b8: (String, Float) = Entity.s.float.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (String, Int)        = Entity.s.float.apply(count).query.get.head
    lazy val c2: (String, Float)      = Entity.s.float.apply(min).query.get.head
    lazy val c3: (String, Set[Float]) = Entity.s.float.apply(min(3)).query.get.head
    lazy val c4: (String, Double)     = Entity.s.float.apply(avg).query.get.head

    lazy val d1: (String, Int)        = Entity.s.float.apply(count).a1.query.get.head
    lazy val d2: (String, Float)      = Entity.s.float.apply(min).a1.query.get.head
    lazy val d3: (String, Set[Float]) = Entity.s.float.apply(min(3)).a1.query.get.head
    lazy val d4: (String, Double)     = Entity.s.float.apply(avg).a1.query.get.head

    lazy val e1: (String, Int)        = Entity.s.float.apply(count).>(1).query.get.head
    lazy val e2: (String, Float)      = Entity.s.float.apply(min).>(1.5f).query.get.head
    lazy val e3: (String, Double)     = Entity.s.float.apply(avg).>(1.5).query.get.head

    lazy val f1: (String, Int)        = Entity.s.float.apply(count).>(1).a1.query.get.head
    lazy val f2: (String, Float)      = Entity.s.float.apply(min).>(1.5f).a1.query.get.head
    lazy val f3: (String, Double)     = Entity.s.float.apply(avg).>(1.5).a1.query.get.head


    // Optional
    lazy val g1: (String, Option[Float]) = Entity.s.float_?.query.get.head
    lazy val g2: (String, Option[Float]) = Entity.s.float_?.apply(Some(float1)).query.get.head

    lazy val h1: (String, Option[Float]) = Entity.s.float_?.a1.query.get.head
    lazy val h2: (String, Option[Float]) = Entity.s.float_?.apply(Some(float1)).a1.query.get.head
  }


  "decimal n-n" - types {
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.float_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.float_.apply(float1).query.get.head
    lazy val a3: (Int, String) = Entity.i.s.float_.apply(?).query(float1).get.head
    lazy val a4: (Int, String) = Entity.i.s.float_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Float) = Entity.i.s.float.query.get.head
    lazy val b2: (Int, String, Float) = Entity.i.s.float.apply(float1).query.get.head
    lazy val b3: (Int, String, Float) = Entity.i.s.float.apply(?).query(float1).get.head
    lazy val b4: (Int, String, Float) = Entity.i.s.float.apply(Entity.i_).query.get.head

    lazy val b5: (Int, String, Float) = Entity.i.s.float.a1.query.get.head
    lazy val b6: (Int, String, Float) = Entity.i.s.float.apply(float1).a1.query.get.head
    lazy val b7: (Int, String, Float) = Entity.i.s.float.apply(?).a1.query(float1).get.head
    lazy val b8: (Int, String, Float) = Entity.i.s.float.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (Int, String, Int)        = Entity.i.s.float.apply(count).query.get.head
    lazy val c2: (Int, String, Float)      = Entity.i.s.float.apply(min).query.get.head
    lazy val c3: (Int, String, Set[Float]) = Entity.i.s.float.apply(min(3)).query.get.head
    lazy val c4: (Int, String, Double)     = Entity.i.s.float.apply(avg).query.get.head

    lazy val c5: (Int, String, Int)        = Entity.i.s.float.apply(count).a1.query.get.head
    lazy val c6: (Int, String, Float)      = Entity.i.s.float.apply(min).a1.query.get.head
    lazy val c7: (Int, String, Set[Float]) = Entity.i.s.float.apply(min(3)).a1.query.get.head
    lazy val c8: (Int, String, Double)     = Entity.i.s.float.apply(avg).a1.query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Float]) = Entity.i.s.float_?.query.get.head
    lazy val d2: (Int, String, Option[Float]) = Entity.i.s.float_?.apply(Some(float1)).query.get.head

    lazy val d3: (Int, String, Option[Float]) = Entity.i.s.float_?.a1.query.get.head
    lazy val d4: (Int, String, Option[Float]) = Entity.i.s.float_?.apply(Some(float1)).a1.query.get.head
  }
}
