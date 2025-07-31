package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class One_Boolean(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "boolean 0-1" - types {
    // Mandatory
    lazy val a1: Boolean = Entity.boolean.query.get.head
    lazy val a2: Boolean = Entity.boolean.apply(boolean1).query.get.head
    lazy val a3: Boolean = Entity.boolean.apply(?).query(boolean1).get.head
    lazy val a4: Boolean = Entity.boolean.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: Boolean = Entity.boolean.a1.query.get.head
    lazy val a6: Boolean = Entity.boolean.apply(boolean1).a1.query.get.head
    lazy val a7: Boolean = Entity.boolean.apply(?).a1.query(boolean1).get.head
    lazy val a8: Boolean = Entity.boolean.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int = Entity.boolean.apply(count).query.get.head
    lazy val b2: Int = Entity.boolean.apply(count).a1.query.get.head

    lazy val c1: Int = Entity.boolean.apply(count).>(1).query.get.head
    lazy val c2: Int = Entity.boolean.apply(count).>(1).a1.query.get.head


    // Optional
    lazy val d1: Option[Boolean] = Entity.boolean_?.query.get.head
    lazy val d2: Option[Boolean] = Entity.boolean_?.apply(Some(boolean1)).query.get.head

    lazy val e1: Option[Boolean] = Entity.boolean_?.a1.query.get.head
    lazy val e2: Option[Boolean] = Entity.boolean_?.apply(Some(boolean1)).a1.query.get.head
  }


  "boolean 1-n" - types {
    // Tacit - stays a String
    lazy val a1: String = Entity.s.boolean_.query.get.head
    lazy val a2: String = Entity.s.boolean_.apply(boolean1).query.get.head
    lazy val a3: String = Entity.s.boolean_.apply(?).query(boolean1).get.head
    lazy val a4: String = Entity.s.boolean_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (String, Boolean) = Entity.s.boolean.query.get.head
    lazy val b2: (String, Boolean) = Entity.s.boolean.apply(boolean1).query.get.head
    lazy val b3: (String, Boolean) = Entity.s.boolean.apply(?).query(boolean1).get.head
    lazy val b4: (String, Boolean) = Entity.s.boolean.apply(Entity.i_).query.get.head

    lazy val b5: (String, Boolean) = Entity.s.boolean.a1.query.get.head
    lazy val b6: (String, Boolean) = Entity.s.boolean.apply(boolean1).a1.query.get.head
    lazy val b7: (String, Boolean) = Entity.s.boolean.apply(?).a1.query(boolean1).get.head
    lazy val b8: (String, Boolean) = Entity.s.boolean.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (String, Int) = Entity.s.boolean.apply(count).query.get.head
    lazy val c2: (String, Int) = Entity.s.boolean.apply(count).a1.query.get.head

    lazy val d1: (String, Int) = Entity.s.boolean.apply(count).>(1).query.get.head
    lazy val d2: (String, Int) = Entity.s.boolean.apply(count).>(1).a1.query.get.head


    // Optional
    lazy val e1: (String, Option[Boolean]) = Entity.s.boolean_?.query.get.head
    lazy val e2: (String, Option[Boolean]) = Entity.s.boolean_?.apply(Some(boolean1)).query.get.head

    lazy val f1: (String, Option[Boolean]) = Entity.s.boolean_?.a1.query.get.head
    lazy val f2: (String, Option[Boolean]) = Entity.s.boolean_?.apply(Some(boolean1)).a1.query.get.head
  }


  "boolean n-n" - types {
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.boolean_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.boolean_.apply(boolean1).query.get.head
    lazy val a3: (Int, String) = Entity.i.s.boolean_.apply(?).query(boolean1).get.head
    lazy val a4: (Int, String) = Entity.i.s.boolean_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Boolean) = Entity.i.s.boolean.query.get.head
    lazy val b2: (Int, String, Boolean) = Entity.i.s.boolean.apply(boolean1).query.get.head
    lazy val b3: (Int, String, Boolean) = Entity.i.s.boolean.apply(?).query(boolean1).get.head
    lazy val b4: (Int, String, Boolean) = Entity.i.s.boolean.apply(Entity.i_).query.get.head

    lazy val b5: (Int, String, Boolean) = Entity.i.s.boolean.a1.query.get.head
    lazy val b6: (Int, String, Boolean) = Entity.i.s.boolean.apply(boolean1).a1.query.get.head
    lazy val b7: (Int, String, Boolean) = Entity.i.s.boolean.apply(?).a1.query(boolean1).get.head
    lazy val b8: (Int, String, Boolean) = Entity.i.s.boolean.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (Int, String, Int) = Entity.i.s.boolean.apply(count).query.get.head
    lazy val c2: (Int, String, Int) = Entity.i.s.boolean.apply(count).a1.query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Boolean]) = Entity.i.s.boolean_?.query.get.head
    lazy val d2: (Int, String, Option[Boolean]) = Entity.i.s.boolean_?.apply(Some(boolean1)).query.get.head

    lazy val d3: (Int, String, Option[Boolean]) = Entity.i.s.boolean_?.a1.query.get.head
    lazy val d4: (Int, String, Option[Boolean]) = Entity.i.s.boolean_?.apply(Some(boolean1)).a1.query.get.head
  }
}
