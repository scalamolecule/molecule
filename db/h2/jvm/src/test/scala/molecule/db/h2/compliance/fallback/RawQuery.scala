package molecule.db.h2.compliance.fallback

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.h2.async.*
import molecule.db.h2.setup.DbProviders_h2
import org.h2.jdbc.JdbcArray
import upickle.default.read


class RawQuery extends MUnit with DbProviders_h2 with TestUtils {

  "Lists of Lists of Any" - types {
    for {
      _ <- Entity.string("a").int(1).save.transact

      _ <- Entity.string.int.query.i.get.map(_ ==> List(
        ("a", 1) // First row as typed tuple
      ))

      // Each Row returned as a List of Any
      _ <- rawQuery(
        """SELECT DISTINCT
          |  Entity.string,
          |  Entity.int
          |FROM Entity
          |WHERE
          |  Entity.string IS NOT NULL AND
          |  Entity.int    IS NOT NULL;
          |""".stripMargin,
        true // set to true to print debug info
      ).map(_ ==> List(
        List("a", 1) // First row
      ))
    } yield ()
  }


  "Base types" - types {
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.string(string1).save.transact
      _ <- Entity.int(int1).save.transact
      _ <- Entity.long(long1).save.transact
      _ <- Entity.float(float1).save.transact
      _ <- Entity.double(double1).save.transact
      _ <- Entity.boolean(boolean1).save.transact
      _ <- Entity.bigInt(bigInt1).save.transact
      _ <- Entity.bigDecimal(bigDecimal1).save.transact
      _ <- Entity.date(date1).save.transact
      _ <- Entity.duration(duration1).save.transact
      _ <- Entity.instant(instant1).save.transact
      _ <- Entity.localDate(localDate1).save.transact
      _ <- Entity.localTime(localTime1).save.transact
      _ <- Entity.localDateTime(localDateTime1).save.transact
      _ <- Entity.offsetTime(offsetTime1).save.transact
      _ <- Entity.offsetDateTime(offsetDateTime1).save.transact
      _ <- Entity.zonedDateTime(zonedDateTime1).save.transact
      _ <- Entity.uuid(uuid1).save.transact
      _ <- Entity.uri(uri1).save.transact
      _ <- Entity.byte(byte1).save.transact
      _ <- Entity.short(short1).save.transact
      _ <- Entity.char(char1).save.transact

      _ <- rawQuery(q("string")).map(_.head ==> List(string1))
      _ <- rawQuery(q("int")).map(_.head ==> List(int1))
      _ <- rawQuery(q("long")).map(_.head ==> List(long1))
      _ <- rawQuery(q("float")).map(_.head ==> List(float1))
      _ <- rawQuery(q("double")).map(_.head ==> List(double1))
      _ <- rawQuery(q("boolean")).map(_.head ==> List(boolean1))
      _ <- rawQuery(q("bigInt")).map(_.head ==> List(new java.math.BigDecimal(bigInt1.bigInteger))) // Java BigDecimal
      _ <- rawQuery(q("bigDecimal")).map(_.head ==> List(bigDecimal1.bigDecimal.setScale(38))) // Java BigDecimal
      _ <- rawQuery(q("date")).map(_.head ==> List(date1.getTime))
      _ <- rawQuery(q("duration")).map(_.head ==> List(duration1.toString))
      _ <- rawQuery(q("instant")).map(_.head ==> List(instant1.toString))
      _ <- rawQuery(q("localDate")).map(_.head ==> List(localDate1.toString))
      _ <- rawQuery(q("localTime_")).map(_.head ==> List(localTime1.toString))
      _ <- rawQuery(q("localDateTime")).map(_.head ==> List(localDateTime1.toString))
      _ <- rawQuery(q("offsetTime")).map(_.head ==> List(offsetTime1.toString))
      _ <- rawQuery(q("offsetDateTime")).map(_.head ==> List(offsetDateTime1.toString))
      _ <- rawQuery(q("zonedDateTime")).map(_.head ==> List(zonedDateTime1.toString))
      _ <- rawQuery(q("uuid")).map(_.head ==> List(uuid1))
      _ <- rawQuery(q("uri")).map(_.head ==> List(uri1.toString))
      _ <- rawQuery(q("byte")).map(_.head ==> List(byte1))
      _ <- rawQuery(q("short")).map(_.head ==> List(short1))
      _ <- rawQuery(q("char")).map(_.head ==> List(char1.toString))
    } yield ()
  }


  "Set" - types {
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringSet(Set(string1, string2)).save.transact
      _ <- Entity.intSet(Set(int1, int2)).save.transact

      // Set's are saved as JDBC Arrays in H2
      _ <- rawQuery(q("stringSet")).map(_.head.head
        .asInstanceOf[JdbcArray].getArray.asInstanceOf[Array[?]].toSet ==> Set(string1, string2)
      )

      _ <- rawQuery(q("intSet")).map(_.head.head
        .asInstanceOf[JdbcArray].getArray.asInstanceOf[Array[?]].toSet ==> Set(int1, int2)
      )
    } yield ()
  }


  "Seq" - types {
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringSet(Set(string1, string2)).save.transact
      _ <- Entity.intSet(Set(int1, int2)).save.transact

      // Set's are saved as JDBC Arrays in H2
      _ <- rawQuery(q("stringSet")).map(_.head.head
        .asInstanceOf[JdbcArray].getArray.asInstanceOf[Array[?]].toSeq ==> Seq(string1, string2)
      )

      _ <- rawQuery(q("intSet")).map(_.head.head
        .asInstanceOf[JdbcArray].getArray.asInstanceOf[Array[?]].toSeq ==> Seq(int1, int2)
      )
      // etc..
    } yield ()
  }


  "Map" - types {
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringMap(Map("a" -> "foo", "b" -> "bar")).save.transact
      _ <- Entity.intMap(Map("a" -> 1, "b" -> 2)).save.transact

      // Map's are saved as json byte arrays in H2
      _ <- rawQuery(q("stringMap")).map { rows =>

        // Create json String from bytes
        val json = new String(rows.head.head.asInstanceOf[Array[Byte]])
        json ==> """{"a":"foo","b":"bar"}"""

        // Convert json to map with for instance upickle
        read[Map[String, String]](json) ==> Map("a" -> "foo", "b" -> "bar")
      }

      _ <- rawQuery(q("intMap")).map { rows =>
        val json = new String(rows.head.head.asInstanceOf[Array[Byte]])
        json ==> """{"a":1,"b":2}"""
        read[Map[String, Int]](json) ==> Map("a" -> 1, "b" -> 2)
      }
    } yield ()
  }


  "Optional values" - types {
    for {
      _ <- Entity.i.string_?.insert(
        (1, Option.empty[String]),
        (2, Some("foo")),
      ).transact

      _ <- Entity.i.a1.string_?.query.i.get.map(_ ==> List(
        (1, None),
        (2, Some("foo"))
      ))

      _ <- rawQuery("SELECT i, string FROM Entity ORDER BY i").map(_ ==> List(
        List(1, null),
        List(2, "foo")
      ))
    } yield ()
  }
}