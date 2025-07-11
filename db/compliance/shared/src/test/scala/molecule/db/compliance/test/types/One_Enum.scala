package molecule.db.compliance.test.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Types.Color.*
import molecule.db.compliance.setup.DbProviders

case class One_Enum(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "enum 0-1" - types { implicit conn =>
    // Mandatory
    lazy val a1: String = Entity.color.query.get.head
    lazy val a2: String = Entity.color.apply(Blue).query.get.head
    lazy val a3: String = Entity.color.apply(?).query(Blue).get.head

    // Adding sort should still return the same type
    lazy val a5: String = Entity.color.a1.query.get.head
    lazy val a6: String = Entity.color.apply(Blue).a1.query.get.head
    lazy val a7: String = Entity.color.apply(?).a1.query(Blue).get.head


    // Optional
    lazy val c1: Option[String] = Entity.color_?.query.get.head
    lazy val c2: Option[String] = Entity.color_?.apply(Some(Blue)).query.get.head

    lazy val c3: Option[String] = Entity.color_?.a1.query.get.head
    lazy val c4: Option[String] = Entity.color_?.apply(Some(Blue)).a1.query.get.head
  }


  "enum 1-n" - types { implicit conn =>
    // Tacit - stays a String
    lazy val a1: String = Entity.s.color_.query.get.head
    lazy val a2: String = Entity.s.color_.apply(Blue).query.get.head
    lazy val a3: String = Entity.s.color_.apply(?).query(Blue).get.head


    // Mandatory
    lazy val b1: (String, String) = Entity.s.color.query.get.head
    lazy val b2: (String, String) = Entity.s.color.apply(Blue).query.get.head
    lazy val b3: (String, String) = Entity.s.color.apply(?).query(Blue).get.head

    lazy val b5: (String, String) = Entity.s.color.a1.query.get.head
    lazy val b6: (String, String) = Entity.s.color.apply(Blue).a1.query.get.head
    lazy val b7: (String, String) = Entity.s.color.apply(?).a1.query(Blue).get.head


    // Optional
    lazy val d1: (String, Option[String]) = Entity.s.color_?.query.get.head
    lazy val d2: (String, Option[String]) = Entity.s.color_?.apply(Some(Blue)).query.get.head

    lazy val d3: (String, Option[String]) = Entity.s.color_?.a1.query.get.head
    lazy val d4: (String, Option[String]) = Entity.s.color_?.apply(Some(Blue)).a1.query.get.head
  }


  "enum n-n" - types { implicit conn =>
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.color_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.color_.apply(Blue).query.get.head
    lazy val a3: (Int, String) = Entity.i.s.color_.apply(?).query(Blue).get.head


    // Mandatory
    lazy val b1: (Int, String, String) = Entity.i.s.color.query.get.head
    lazy val b2: (Int, String, String) = Entity.i.s.color.apply(Blue).query.get.head
    lazy val b3: (Int, String, String) = Entity.i.s.color.apply(?).query(Blue).get.head

    lazy val b5: (Int, String, String) = Entity.i.s.color.a1.query.get.head
    lazy val b6: (Int, String, String) = Entity.i.s.color.apply(Blue).a1.query.get.head
    lazy val b7: (Int, String, String) = Entity.i.s.color.apply(?).a1.query(Blue).get.head


    // Optional
    lazy val d1: (Int, String, Option[String]) = Entity.i.s.color_?.query.get.head
    lazy val d2: (Int, String, Option[String]) = Entity.i.s.color_?.apply(Some(Blue)).query.get.head

    lazy val d3: (Int, String, Option[String]) = Entity.i.s.color_?.a1.query.get.head
    lazy val d4: (Int, String, Option[String]) = Entity.i.s.color_?.apply(Some(Blue)).a1.query.get.head
  }
}
