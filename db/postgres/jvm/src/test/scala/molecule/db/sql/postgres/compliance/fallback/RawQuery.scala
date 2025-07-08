package molecule.db.sql.postgres.compliance.fallback

import java.math.BigDecimal as jBigDecimal
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.sql.postgres.async.*
import molecule.db.sql.postgres.setup.DbProviders_postgres
import org.postgresql.jdbc.PgArray
import org.postgresql.util.PGobject
import upickle.default.read


class RawQuery extends MUnit with DbProviders_postgres with TestUtils {

  "Lists of Lists of Any" - types { implicit conn =>
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


  "Base types" - types { implicit conn =>
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

      // java.time types, UUID, URI and Char are saved as Strings
      // `localTime` is a reserved keyword in h2, so it is transparently suffixed with underscore
      _ <- rawQuery(q("string")).map(_.head ==> List(string1))
      _ <- rawQuery(q("int")).map(_.head ==> List(int1))
      _ <- rawQuery(q("long")).map(_.head ==> List(long1))
      _ <- rawQuery(q("float")).map(_.head ==> List(new jBigDecimal(float1.toString)))
      _ <- rawQuery(q("double")).map(_.head ==> List(double1))
      _ <- rawQuery(q("boolean")).map(_.head ==> List(boolean1))
      _ <- rawQuery(q("bigInt")).map(_.head ==> List(new jBigDecimal(bigInt1.bigInteger)))
      _ <- rawQuery(q("bigDecimal")).map(_.head ==> List(bigDecimal1.bigDecimal))
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


  "Set" - types { implicit conn =>
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringSet(Set(string1, string2)).save.transact
      _ <- Entity.intSet(Set(int1, int2)).save.transact

      // Set's are saved as PgArray's in Postgres
      _ <- rawQuery(q("stringSet")).map(_.head.head
        .asInstanceOf[PgArray].getArray.asInstanceOf[Array[?]].toSet ==> Set(string1, string2)
      )

      _ <- rawQuery(q("intSet")).map(_.head.head
        .asInstanceOf[PgArray].getArray.asInstanceOf[Array[?]].toSet ==> Set(int1, int2)
      )
    } yield ()
  }


  "Seq" - types { implicit conn =>
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringSet(Set(string1, string2)).save.transact
      _ <- Entity.intSet(Set(int1, int2)).save.transact

      // Set's are saved as PgArray's in Postgres
      _ <- rawQuery(q("stringSet")).map(_.head.head
        .asInstanceOf[PgArray].getArray.asInstanceOf[Array[?]].toSeq ==> Seq(string1, string2)
      )

      _ <- rawQuery(q("intSet")).map(_.head.head
        .asInstanceOf[PgArray].getArray.asInstanceOf[Array[?]].toSeq ==> Seq(int1, int2)
      )
      // etc..
    } yield ()
  }


  "Map" - types { implicit conn =>
    def q(attr: String): String = s"SELECT DISTINCT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringMap(Map("a" -> "foo", "b" -> "bar")).save.transact
      _ <- Entity.intMap(Map("a" -> 1, "b" -> 2)).save.transact

      // Map's are saved as PGobject's in Postgres
      _ <- rawQuery(q("stringMap")).map { rows =>

        // Create json String from PGobject
        val json = rows.head.head.asInstanceOf[PGobject].toString
        json ==> """{"a": "foo", "b": "bar"}"""

        // Convert json to map with for instance upickle
        read[Map[String, String]](json) ==> Map("a" -> "foo", "b" -> "bar")
      }

      _ <- rawQuery(q("intMap")).map { rows =>
        val json = rows.head.head.asInstanceOf[PGobject].toString
        json ==> """{"a": 1, "b": 2}"""
        read[Map[String, Int]](json) ==> Map("a" -> 1, "b" -> 2)
      }
    } yield ()
  }


  "Optional values" - types { implicit conn =>
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