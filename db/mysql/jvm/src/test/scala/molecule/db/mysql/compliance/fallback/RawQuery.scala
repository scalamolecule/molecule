package molecule.db.mysql.compliance.fallback

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.mysql.async.*
import molecule.db.mysql.setup.DbProviders_mysql
import upickle.default.read


class RawQuery extends MUnit with DbProviders_mysql with TestUtils {

  "Lists of Lists of Any" - types {
    for {
      _ <- Entity.string("a").int(1).save.transact

      _ <- Entity.string.int.query.i.get.map(_ ==> List(
        ("a", 1) // First row as typed tuple
      ))

      // Each Row returned as a List of Any
      _ <- rawQuery(
        """SELECT DISTINCT
          |  Entity.string_,
          |  Entity.int_
          |FROM Entity
          |WHERE
          |  Entity.string_ IS NOT NULL AND
          |  Entity.int_    IS NOT NULL;
          |""".stripMargin,
        true // set to true to print debug info
      ).map(_ ==> List(
        List("a", 1) // First row as List[Any]
      ))
    } yield ()
  }


  "Base types" - types {
    def q(attr: String): String = s"SELECT $attr FROM Entity WHERE $attr IS NOT NULL"
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

      _ <- rawQuery(q("string_")).map(_.head ==> List(string1))
      _ <- rawQuery(q("int_")).map(_.head ==> List(int1))
      _ <- rawQuery(q("long_")).map(_.head ==> List(long1))
      _ <- rawQuery(q("float_")).map(_.head ==> List(float1.toString.toDouble))
      _ <- rawQuery(q("double_")).map(_.head ==> List(double1))
      _ <- rawQuery(q("boolean_")).map(_.head ==> List(false))
      _ <- rawQuery(q("bigInt_")).map(_.head ==> List(new java.math.BigDecimal(bigInt1.bigInteger))) // Java BigDecimal
      _ <- rawQuery(q("bigDecimal")).map(_.head ==> List(bigDecimal1.bigDecimal.setScale(30))) // Java BigDecimal
      _ <- rawQuery(q("date_")).map(_.head ==> List(date1.getTime))
      _ <- rawQuery(q("duration")).map(_.head ==> List(duration1.toString))
      _ <- rawQuery(q("instant")).map(_.head ==> List(instant1.toString))
      _ <- rawQuery(q("localDate")).map(_.head ==> List(localDate1.toString))
      _ <- rawQuery(q("localTime_")).map(_.head ==> List(localTime1.toString))
      _ <- rawQuery(q("localDateTime")).map(_.head ==> List(localDateTime1.toString))
      _ <- rawQuery(q("offsetTime")).map(_.head ==> List(offsetTime1.toString))
      _ <- rawQuery(q("offsetDateTime")).map(_.head ==> List(offsetDateTime1.toString))
      _ <- rawQuery(q("zonedDateTime")).map(_.head ==> List(zonedDateTime1.toString))
      _ <- rawQuery(q("uuid")).map(_.head ==> List(uuid1.toString))
      _ <- rawQuery(q("uri")).map(_.head ==> List(uri1.toString))
      _ <- rawQuery(q("byte_")).map(_.head ==> List(byte1))
      _ <- rawQuery(q("short")).map(_.head ==> List(short1))
      _ <- rawQuery(q("char_")).map(_.head ==> List(char1.toString))
    } yield ()
  }


  "Set" - types {
    def q(attr: String): String = s"SELECT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringSet(Set(string1, string2)).save.transact
      _ <- Entity.intSet(Set(int1, int2)).save.transact

      // Set's are saved as json String in MariaDB
      _ <- rawQuery(q("stringSet")).map(_.head ==> List("""["a", "b"]"""))
      _ <- rawQuery(q("intSet")).map(_.head ==> List("[1, 2]"))

      // Convert json to Set with upickle for instance:
      _ <- rawQuery(q("stringSet")).map(rows => read[Set[String]](rows.head.head.toString) ==> Set("a", "b"))
      _ <- rawQuery(q("intSet")).map(rows => read[Set[Int]](rows.head.head.toString) ==> Set(1, 2))
      // etc..
    } yield ()
  }


  "Seq" - types {
    def q(attr: String): String = s"SELECT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringSeq(Seq(string1, string2)).save.transact
      _ <- Entity.intSeq(Seq(int1, int2)).save.transact

      // Seq's are saved as json String in MariaDB
      _ <- rawQuery(q("stringSeq")).map(_.head ==> List("""["a", "b"]"""))
      _ <- rawQuery(q("intSeq")).map(_.head ==> List("[1, 2]"))

      // Convert json to Seq with upickle for instance:
      _ <- rawQuery(q("stringSeq")).map(rows => read[Seq[String]](rows.head.head.toString) ==> Seq("a", "b"))
      _ <- rawQuery(q("intSeq")).map(rows => read[Seq[Int]](rows.head.head.toString) ==> Seq(1, 2))
    } yield ()
  }


  "Map" - types {
    def q(attr: String): String = s"SELECT $attr FROM Entity WHERE $attr IS NOT NULL"
    for {
      _ <- Entity.stringMap(Map("a" -> "foo", "b" -> "bar")).save.transact
      _ <- Entity.intMap(Map("a" -> 1, "b" -> 2)).save.transact

      // Map's are saved as json String in MariaDB
      _ <- rawQuery(q("stringMap")).map(_.head ==> List("""{"a": "foo", "b": "bar"}"""))
      _ <- rawQuery(q("intMap")).map(_.head ==> List("""{"a": 1, "b": 2}"""))

      // Convert json to Map with upickle for instance:
      _ <- rawQuery(q("stringMap")).map(rows =>
        read[Map[String, String]](rows.head.head.toString) ==> Map("a" -> "foo", "b" -> "bar")
      )
      _ <- rawQuery(q("intMap")).map(rows =>
        read[Map[String, Int]](rows.head.head.toString) ==> Map("a" -> 1, "b" -> 2)
      )
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

      _ <- rawQuery("SELECT i, string_ FROM Entity ORDER BY i").map(_ ==> List(
        List(1, null),
        List(2, "foo")
      ))
    } yield ()
  }
}