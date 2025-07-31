package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class One_String(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "string 0-1" - types {
    // Mandatory
    lazy val a1: String = Entity.string.query.get.head
    lazy val a2: String = Entity.string.apply(string1).query.get.head
    lazy val a3: String = Entity.string.apply(?).query(string1).get.head
    lazy val a4: String = Entity.string.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: String = Entity.string.a1.query.get.head
    lazy val a6: String = Entity.string.apply(string1).a1.query.get.head
    lazy val a7: String = Entity.string.apply(?).a1.query(string1).get.head
    lazy val a8: String = Entity.string.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int         = Entity.string.apply(count).query.get.head
    lazy val b2: String      = Entity.string.apply(min).query.get.head
    lazy val b3: Set[String] = Entity.string.apply(min(3)).query.get.head

    lazy val c1: Int         = Entity.string.apply(count).a1.query.get.head
    lazy val c2: String      = Entity.string.apply(min).a1.query.get.head
    lazy val c3: Set[String] = Entity.string.apply(min(3)).a1.query.get.head

    lazy val d1: Int         = Entity.string.apply(count).>(1).query.get.head
    lazy val d2: String      = Entity.string.apply(min).>("a").query.get.head

    lazy val e1: Int         = Entity.string.apply(count).>(1).a1.query.get.head
    lazy val e2: String      = Entity.string.apply(min).>("a").a1.query.get.head


    // Optional
    lazy val f1: Option[String] = Entity.string_?.query.get.head
    lazy val f2: Option[String] = Entity.string_?.apply(Some(string1)).query.get.head

    lazy val g1: Option[String] = Entity.string_?.a1.query.get.head
    lazy val g2: Option[String] = Entity.string_?.apply(Some(string1)).a1.query.get.head
  }


  "string 1-n" - types {
    // Tacit - stays a Boolean
    lazy val a1: Boolean = Entity.boolean.string_.query.get.head
    lazy val a2: Boolean = Entity.boolean.string_.apply(string1).query.get.head
    lazy val a3: Boolean = Entity.boolean.string_.apply(?).query(string1).get.head
    lazy val a4: Boolean = Entity.boolean.string_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (Boolean, String) = Entity.boolean.string.query.get.head
    lazy val b2: (Boolean, String) = Entity.boolean.string.apply(string1).query.get.head
    lazy val b3: (Boolean, String) = Entity.boolean.string.apply(?).query(string1).get.head
    lazy val b4: (Boolean, String) = Entity.boolean.string.apply(Entity.i_).query.get.head

    lazy val b5: (Boolean, String) = Entity.boolean.string.a1.query.get.head
    lazy val b6: (Boolean, String) = Entity.boolean.string.apply(string1).a1.query.get.head
    lazy val b7: (Boolean, String) = Entity.boolean.string.apply(?).a1.query(string1).get.head
    lazy val b8: (Boolean, String) = Entity.boolean.string.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (Boolean, Int)         = Entity.boolean.string.apply(count).query.get.head
    lazy val c2: (Boolean, String)      = Entity.boolean.string.apply(min).query.get.head
    lazy val c3: (Boolean, Set[String]) = Entity.boolean.string.apply(min(3)).query.get.head

    lazy val d1: (Boolean, Int)         = Entity.boolean.string.apply(count).a1.query.get.head
    lazy val d2: (Boolean, String)      = Entity.boolean.string.apply(min).a1.query.get.head
    lazy val d3: (Boolean, Set[String]) = Entity.boolean.string.apply(min(3)).a1.query.get.head

    lazy val e1: (Boolean, Int)         = Entity.boolean.string.apply(count).>(1).a1.query.get.head
    lazy val e2: (Boolean, String)      = Entity.boolean.string.apply(min).>("a").a1.query.get.head

    lazy val f1: (Boolean, Int)         = Entity.boolean.string.apply(count).>(1).a1.query.get.head
    lazy val f2: (Boolean, String)      = Entity.boolean.string.apply(min).>("a").a1.query.get.head


    // Optional
    lazy val g1: (Boolean, Option[String]) = Entity.boolean.string_?.query.get.head
    lazy val g2: (Boolean, Option[String]) = Entity.boolean.string_?.apply(Some(string1)).query.get.head

    lazy val h1: (Boolean, Option[String]) = Entity.boolean.string_?.a1.query.get.head
    lazy val h2: (Boolean, Option[String]) = Entity.boolean.string_?.apply(Some(string1)).a1.query.get.head
  }


  "string n-n" - types {
    // Tacit - stays an (Int, Boolean)
    lazy val a1: (Int, Boolean) = Entity.i.boolean.string_.query.get.head
    lazy val a2: (Int, Boolean) = Entity.i.boolean.string_.apply(string1).query.get.head
    lazy val a3: (Int, Boolean) = Entity.i.boolean.string_.apply(?).query(string1).get.head
    lazy val a4: (Int, Boolean) = Entity.i.boolean.string_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (Int, Boolean, String) = Entity.i.boolean.string.query.get.head
    lazy val b2: (Int, Boolean, String) = Entity.i.boolean.string.apply(string1).query.get.head
    lazy val b3: (Int, Boolean, String) = Entity.i.boolean.string.apply(?).query(string1).get.head
    lazy val b4: (Int, Boolean, String) = Entity.i.boolean.string.apply(Entity.i_).query.get.head

    lazy val b5: (Int, Boolean, String) = Entity.i.boolean.string.a1.query.get.head
    lazy val b6: (Int, Boolean, String) = Entity.i.boolean.string.apply(string1).a1.query.get.head
    lazy val b7: (Int, Boolean, String) = Entity.i.boolean.string.apply(?).a1.query(string1).get.head
    lazy val b8: (Int, Boolean, String) = Entity.i.boolean.string.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (Int, Boolean, Int)         = Entity.i.boolean.string.apply(count).query.get.head
    lazy val c2: (Int, Boolean, String)      = Entity.i.boolean.string.apply(min).query.get.head
    lazy val c3: (Int, Boolean, Set[String]) = Entity.i.boolean.string.apply(min(3)).query.get.head

    lazy val c5: (Int, Boolean, Int)         = Entity.i.boolean.string.apply(count).a1.query.get.head
    lazy val c6: (Int, Boolean, String)      = Entity.i.boolean.string.apply(min).a1.query.get.head
    lazy val c7: (Int, Boolean, Set[String]) = Entity.i.boolean.string.apply(min(3)).a1.query.get.head


    // Optional
    lazy val d1: (Int, Boolean, Option[String]) = Entity.i.boolean.string_?.query.get.head
    lazy val d2: (Int, Boolean, Option[String]) = Entity.i.boolean.string_?.apply(Some(string1)).query.get.head

    lazy val d3: (Int, Boolean, Option[String]) = Entity.i.boolean.string_?.a1.query.get.head
    lazy val d4: (Int, Boolean, Option[String]) = Entity.i.boolean.string_?.apply(Some(string1)).a1.query.get.head
  }
}
