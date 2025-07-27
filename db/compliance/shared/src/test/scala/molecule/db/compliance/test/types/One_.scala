package molecule.db.compliance.test.types

import java.util.Date
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_sync
import molecule.db.common.spi.Spi_sync
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class One_(
  suite: MUnit,
  api: Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "other type 0-1" - types {
    // Mandatory
    lazy val a1: Date = Entity.date.query.get.head
    lazy val a2: Date = Entity.date.apply(date1).query.get.head
    lazy val a3: Date = Entity.date.apply(?).query(date1).get.head
    lazy val a4: Date = Entity.date.apply(Entity.i_).query.get.head

    // Adding sort should still return the same type
    lazy val a5: Date = Entity.date.a1.query.get.head
    lazy val a6: Date = Entity.date.apply(date1).a1.query.get.head
    lazy val a7: Date = Entity.date.apply(?).a1.query(date1).get.head
    lazy val a8: Date = Entity.date.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val b1: Int       = Entity.date.apply(count).query.get.head
    lazy val b2: Date      = Entity.date.apply(min).query.get.head
    lazy val b3: Set[Date] = Entity.date.apply(min(3)).query.get.head

    lazy val b5: Int       = Entity.date.apply(count).a1.query.get.head
    lazy val b6: Date      = Entity.date.apply(min).a1.query.get.head
    lazy val b7: Set[Date] = Entity.date.apply(min(3)).a1.query.get.head


    // Optional
    lazy val c1: Option[Date] = Entity.date_?.query.get.head
    lazy val c2: Option[Date] = Entity.date_?.apply(Some(date1)).query.get.head

    lazy val c3: Option[Date] = Entity.date_?.a1.query.get.head
    lazy val c4: Option[Date] = Entity.date_?.apply(Some(date1)).a1.query.get.head
  }


  "other type 1-n" - types {
    // Tacit - stays a String
    lazy val a1: String = Entity.s.date_.query.get.head
    lazy val a2: String = Entity.s.date_.apply(date1).query.get.head
    lazy val a3: String = Entity.s.date_.apply(?).query(date1).get.head
    lazy val a4: String = Entity.s.date_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (String, Date) = Entity.s.date.query.get.head
    lazy val b2: (String, Date) = Entity.s.date.apply(date1).query.get.head
    lazy val b3: (String, Date) = Entity.s.date.apply(?).query(date1).get.head
    lazy val b4: (String, Date) = Entity.s.date.apply(Entity.i_).query.get.head

    lazy val b5: (String, Date) = Entity.s.date.a1.query.get.head
    lazy val b6: (String, Date) = Entity.s.date.apply(date1).a1.query.get.head
    lazy val b7: (String, Date) = Entity.s.date.apply(?).a1.query(date1).get.head
    lazy val b8: (String, Date) = Entity.s.date.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (String, Int)       = Entity.s.date.apply(count).query.get.head
    lazy val c2: (String, Date)      = Entity.s.date.apply(min).query.get.head
    lazy val c3: (String, Set[Date]) = Entity.s.date.apply(min(3)).query.get.head

    lazy val c5: (String, Int)       = Entity.s.date.apply(count).a1.query.get.head
    lazy val c6: (String, Date)      = Entity.s.date.apply(min).a1.query.get.head
    lazy val c7: (String, Set[Date]) = Entity.s.date.apply(min(3)).a1.query.get.head


    // Optional
    lazy val d1: (String, Option[Date]) = Entity.s.date_?.query.get.head
    lazy val d2: (String, Option[Date]) = Entity.s.date_?.apply(Some(date1)).query.get.head

    lazy val d3: (String, Option[Date]) = Entity.s.date_?.a1.query.get.head
    lazy val d4: (String, Option[Date]) = Entity.s.date_?.apply(Some(date1)).a1.query.get.head
  }


  "other type n-n" - types {
    // Tacit - stays an (Int, String)
    lazy val a1: (Int, String) = Entity.i.s.date_.query.get.head
    lazy val a2: (Int, String) = Entity.i.s.date_.apply(date1).query.get.head
    lazy val a3: (Int, String) = Entity.i.s.date_.apply(?).query(date1).get.head
    lazy val a4: (Int, String) = Entity.i.s.date_.apply(Entity.i_).query.get.head


    // Mandatory
    lazy val b1: (Int, String, Date) = Entity.i.s.date.query.get.head
    lazy val b2: (Int, String, Date) = Entity.i.s.date.apply(date1).query.get.head
    lazy val b3: (Int, String, Date) = Entity.i.s.date.apply(?).query(date1).get.head
    lazy val b4: (Int, String, Date) = Entity.i.s.date.apply(Entity.i_).query.get.head

    lazy val b5: (Int, String, Date) = Entity.i.s.date.a1.query.get.head
    lazy val b6: (Int, String, Date) = Entity.i.s.date.apply(date1).a1.query.get.head
    lazy val b7: (Int, String, Date) = Entity.i.s.date.apply(?).a1.query(date1).get.head
    lazy val b8: (Int, String, Date) = Entity.i.s.date.apply(Entity.i_).a1.query.get.head


    // Aggregates, some change type
    lazy val c1: (Int, String, Int)       = Entity.i.s.date.apply(count).query.get.head
    lazy val c2: (Int, String, Date)      = Entity.i.s.date.apply(min).query.get.head
    lazy val c3: (Int, String, Set[Date]) = Entity.i.s.date.apply(min(3)).query.get.head

    lazy val c5: (Int, String, Int)       = Entity.i.s.date.apply(count).a1.query.get.head
    lazy val c6: (Int, String, Date)      = Entity.i.s.date.apply(min).a1.query.get.head
    lazy val c7: (Int, String, Set[Date]) = Entity.i.s.date.apply(min(3)).a1.query.get.head


    // Optional
    lazy val d1: (Int, String, Option[Date]) = Entity.i.s.date_?.query.get.head
    lazy val d2: (Int, String, Option[Date]) = Entity.i.s.date_?.apply(Some(date1)).query.get.head

    lazy val d3: (Int, String, Option[Date]) = Entity.i.s.date_?.a1.query.get.head
    lazy val d4: (Int, String, Option[Date]) = Entity.i.s.date_?.apply(Some(date1)).a1.query.get.head
  }
}
