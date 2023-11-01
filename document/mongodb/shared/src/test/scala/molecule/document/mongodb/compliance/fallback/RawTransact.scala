package molecule.document.mongodb.compliance.fallback

import molecule.core.spi.{Conn, TxReport}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object RawTransact extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "Semantics" - types { implicit conn =>
      for {
        // Insert 1 row with 2 values
        txReport <- rawTransact(
          """{
            |  "collection": "Ns",
            |  "data": [
            |    {
            |      "i": 1,
            |      "s": "a"
            |    },
            |    {
            |      "i": 2,
            |      "s": "b"
            |    }
            |  ]
            |}
            |""".stripMargin)

        // Values saved
        _ <- Ns.i.s.query.get.map(_ ==> List((1, "a"), (2, "b")))

        // Transaction report has ids of inserted entities
        _ <- Ns(txReport.id).i.s.query.get.map(_ ==> List((1, "a")))
      } yield ()
    }

    def insert(attr: String, value: Any)(implicit conn: Conn): Future[TxReport] = rawTransact(
      s"""{
         |  "insert": "Ns",
         |  "data": [
         |    {
         |      "$attr": $value
         |    }
         |  ]
         |}
         |""".stripMargin
    )

    "Insert" - types { implicit conn =>
      for {
        _ <- insert("string", s"\"$string1\"")
        _ <- insert("int", int1)
        _ <- insert("long", long1)
        _ <- insert("float", float1)
        _ <- insert("double", double1)
        _ <- insert("boolean", boolean1)
        _ <- insert("bigInt", s"\"$bigInt1\"")
        _ <- insert("bigDecimal", s"\"$bigDecimal1\"")
        _ <- insert("date", date1.getTime)
        _ <- insert("duration", s"\"$duration1\"")
        _ <- insert("instant", s"\"$instant1\"")
        _ <- insert("localDate", s"\"$localDate1\"")
        _ <- insert("localTime", s"\"$localTime1\"")
        _ <- insert("localDateTime", s"\"$localDateTime1\"")
        _ <- insert("offsetTime", s"\"$offsetTime1\"")
        _ <- insert("offsetDateTime", s"\"$offsetDateTime1\"")
        _ <- insert("zonedDateTime", s"\"$zonedDateTime1\"")
        _ <- insert("uuid", s"\"$uuid1\"")
        _ <- insert("uri", s"\"$uri1\"")
        _ <- insert("byte", byte1)
        _ <- insert("short", short1)
        _ <- insert("char", s"\"$char1\"")

        // All types properly inserted
        _ <- Ns.string.query.get.map(_.head ==> string1)
        _ <- Ns.int.query.get.map(_.head ==> int1)
        _ <- Ns.long.query.get.map(_.head ==> long1)
        _ <- Ns.float.query.get.map(_.head ==> float1)
        _ <- Ns.double.query.get.map(_.head ==> double1)
        _ <- Ns.boolean.query.get.map(_.head ==> boolean1)
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt1)
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal1)
        _ <- Ns.date.query.get.map(_.head ==> date1)
        _ <- Ns.duration.query.get.map(_.head ==> duration1)
        _ <- Ns.instant.query.get.map(_.head ==> instant1)
        _ <- Ns.localDate.query.get.map(_.head ==> localDate1)
        _ <- Ns.localTime.query.get.map(_.head ==> localTime1)
        _ <- Ns.localDateTime.query.get.map(_.head ==> localDateTime1)
        _ <- Ns.offsetTime.query.get.map(_.head ==> offsetTime1)
        _ <- Ns.offsetDateTime.query.get.map(_.head ==> offsetDateTime1)
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime1)
        _ <- Ns.uuid.query.get.map(_.head ==> uuid1)
        _ <- Ns.uri.query.get.map(_.head ==> uri1)
        _ <- Ns.byte.query.get.map(_.head ==> byte1)
        _ <- Ns.short.query.get.map(_.head ==> short1)
        _ <- Ns.char.query.get.map(_.head ==> char1)
      } yield ()
    }


    "Insert Set" - types { implicit conn =>
      for {
        _ <- insert("strings", s"""["$string1", "$string2"]""")
        _ <- insert("ints", s"""[$int1, $int2]""")
        _ <- insert("longs", s"""[$long1, $long2]""")
        _ <- insert("floats", s"""[$float1, $float2]""")
        _ <- insert("doubles", s"""[$double1, $double2]""")
        _ <- insert("booleans", s"""[$boolean1, $boolean2]""")
        _ <- insert("bigInts", s"""["$bigInt1", "$bigInt2"]""")
        _ <- insert("bigDecimals", s"""["$bigDecimal1", "$bigDecimal2"]""")
        _ <- insert("dates", s"""[${date1.getTime}, ${date2.getTime}]""")
        _ <- insert("durations", s"""["$duration1", "$duration2"]""")
        _ <- insert("instants", s"""["$instant1", "$instant2"]""")
        _ <- insert("localDates", s"""["$localDate1", "$localDate2"]""")
        _ <- insert("localTimes", s"""["$localTime1", "$localTime2"]""")
        _ <- insert("localDateTimes", s"""["$localDateTime1", "$localDateTime2"]""")
        _ <- insert("offsetTimes", s"""["$offsetTime1", "$offsetTime2"]""")
        _ <- insert("offsetDateTimes", s"""["$offsetDateTime1", "$offsetDateTime2"]""")
        _ <- insert("zonedDateTimes", s"""["$zonedDateTime1", "$zonedDateTime2"]""")
        _ <- insert("uuids", s"""["$uuid1", "$uuid2"]""")
        _ <- insert("uris", s"""["$uri1", "$uri2"]""")
        _ <- insert("bytes", s"""[$byte1, $byte2]""")
        _ <- insert("shorts", s"""[$short1, $short2]""")
        _ <- insert("chars", s"""["$char1", "$char2"]""")

        // All types properly inserted
        _ <- Ns.strings.query.get.map(_.head ==> Set(string1, string2))
        _ <- Ns.ints.query.get.map(_.head ==> Set(int1, int2))
        _ <- Ns.longs.query.get.map(_.head ==> Set(long1, long2))
        _ <- Ns.floats.query.get.map(_.head ==> Set(float1, float2))
        _ <- Ns.doubles.query.get.map(_.head ==> Set(double1, double2))
        _ <- Ns.booleans.query.get.map(_.head ==> Set(boolean1, boolean2))
        _ <- Ns.bigInts.query.get.map(_.head ==> Set(bigInt1, bigInt2))
        _ <- Ns.bigDecimals.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))
        _ <- Ns.dates.query.get.map(_.head ==> Set(date1, date2))
        _ <- Ns.durations.query.get.map(_.head ==> Set(duration1, duration2))
        _ <- Ns.instants.query.get.map(_.head ==> Set(instant1, instant2))
        _ <- Ns.localDates.query.get.map(_.head ==> Set(localDate1, localDate2))
        _ <- Ns.localTimes.query.get.map(_.head ==> Set(localTime1, localTime2))
        _ <- Ns.localDateTimes.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))
        _ <- Ns.offsetTimes.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))
        _ <- Ns.offsetDateTimes.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))
        _ <- Ns.zonedDateTimes.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))
        _ <- Ns.uuids.query.get.map(_.head ==> Set(uuid1, uuid2))
        _ <- Ns.uris.query.get.map(_.head ==> Set(uri1, uri2))
        _ <- Ns.bytes.query.get.map(_.head ==> Set(byte1, byte2))
        _ <- Ns.shorts.query.get.map(_.head ==> Set(short1, short2))
        _ <- Ns.chars.query.get.map(_.head ==> Set(char1, char2))
      } yield ()
    }


    "Update" - types { implicit conn =>
      for {
        // Initial values
        id1 <- Ns.string(string1).save.transact.map(_.id)
        id2 <- Ns.int(int1).save.transact.map(_.id)
        id3 <- Ns.long(long1).save.transact.map(_.id)
        id4 <- Ns.float(float1).save.transact.map(_.id)
        id5 <- Ns.double(double1).save.transact.map(_.id)
        id6 <- Ns.boolean(boolean1).save.transact.map(_.id)
        id7 <- Ns.bigInt(bigInt1).save.transact.map(_.id)
        id8 <- Ns.bigDecimal(bigDecimal1).save.transact.map(_.id)
        id9 <- Ns.date(date1).save.transact.map(_.id)
        id10 <- Ns.duration(duration1).save.transact.map(_.id)
        id11 <- Ns.instant(instant1).save.transact.map(_.id)
        id12 <- Ns.localDate(localDate1).save.transact.map(_.id)
        id13 <- Ns.localTime(localTime1).save.transact.map(_.id)
        id14 <- Ns.localDateTime(localDateTime1).save.transact.map(_.id)
        id15 <- Ns.offsetTime(offsetTime1).save.transact.map(_.id)
        id16 <- Ns.offsetDateTime(offsetDateTime1).save.transact.map(_.id)
        id17 <- Ns.zonedDateTime(zonedDateTime1).save.transact.map(_.id)
        id18 <- Ns.uuid(uuid1).save.transact.map(_.id)
        id19 <- Ns.uri(uri1).save.transact.map(_.id)
        id20 <- Ns.byte(byte1).save.transact.map(_.id)
        id21 <- Ns.short(short1).save.transact.map(_.id)
        id22 <- Ns.char(char1).save.transact.map(_.id)

        // Update all values
        _ <- rawTransact(s"update Ns set string         = '$string2'                    where id = $id1")
        _ <- rawTransact(s"update Ns set int            = $int2                         where id = $id2")
        _ <- rawTransact(s"update Ns set long           = $long2                        where id = $id3")
        _ <- rawTransact(s"update Ns set float          = $float2                       where id = $id4")
        _ <- rawTransact(s"update Ns set double         = $double2                      where id = $id5")
        _ <- rawTransact(s"update Ns set boolean        = $boolean2                     where id = $id6")
        _ <- rawTransact(s"update Ns set bigInt         = $bigInt2                      where id = $id7")
        _ <- rawTransact(s"update Ns set bigDecimal     = $bigDecimal2                  where id = $id8")
        _ <- rawTransact(s"update Ns set date           = '2002-01-01'                  where id = $id9")
        _ <- rawTransact(s"update Ns set duration       = '${duration2.toString}'       where id = $id10")
        _ <- rawTransact(s"update Ns set instant        = '${instant2.toString}'        where id = $id11")
        _ <- rawTransact(s"update Ns set localDate      = '${localDate2.toString}'      where id = $id12")
        _ <- rawTransact(s"update Ns set localTime      = '${localTime2.toString}'      where id = $id13")
        _ <- rawTransact(s"update Ns set localDateTime  = '${localDateTime2.toString}'  where id = $id14")
        _ <- rawTransact(s"update Ns set offsetTime     = '${offsetTime2.toString}'     where id = $id15")
        _ <- rawTransact(s"update Ns set offsetDateTime = '${offsetDateTime2.toString}' where id = $id16")
        _ <- rawTransact(s"update Ns set zonedDateTime  = '${zonedDateTime2.toString}'  where id = $id17")
        _ <- rawTransact(s"update Ns set uuid           = '$uuid2'                      where id = $id18")
        _ <- rawTransact(s"update Ns set uri            = '$uri2'                       where id = $id19")
        _ <- rawTransact(s"update Ns set byte           = $byte2                        where id = $id20")
        _ <- rawTransact(s"update Ns set short          = $short2                       where id = $id21")
        _ <- rawTransact(s"update Ns set char           = '$char2'                      where id = $id22")

        // All types properly updated
        _ <- Ns.string.query.get.map(_.head ==> string2)
        _ <- Ns.int.query.get.map(_.head ==> int2)
        _ <- Ns.long.query.get.map(_.head ==> long2)
        _ <- Ns.float.query.get.map(_.head ==> float2)
        _ <- Ns.double.query.get.map(_.head ==> double2)
        _ <- Ns.boolean.query.get.map(_.head ==> boolean2)
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt2)
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal2)
        _ <- Ns.date.query.get.map(_.head ==> date2)
        _ <- Ns.duration.query.get.map(_.head ==> duration2)
        _ <- Ns.instant.query.get.map(_.head ==> instant2)
        _ <- Ns.localDate.query.get.map(_.head ==> localDate2)
        _ <- Ns.localTime.query.get.map(_.head ==> localTime2)
        _ <- Ns.localDateTime.query.get.map(_.head ==> localDateTime2)
        _ <- Ns.offsetTime.query.get.map(_.head ==> offsetTime2)
        _ <- Ns.offsetDateTime.query.get.map(_.head ==> offsetDateTime2)
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime2)
        _ <- Ns.uuid.query.get.map(_.head ==> uuid2)
        _ <- Ns.uri.query.get.map(_.head ==> uri2)
        _ <- Ns.byte.query.get.map(_.head ==> byte2)
        _ <- Ns.short.query.get.map(_.head ==> short2)
        _ <- Ns.char.query.get.map(_.head ==> char2)
      } yield ()
    }


    "Delete" - types { implicit conn =>
      for {
        // Initial values
        List(id1, _) <- Ns.string.insert(string1, string2).transact.map(_.ids)
        List(id2, _) <- Ns.int.insert(int1, int2).transact.map(_.ids)
        List(id3, _) <- Ns.long.insert(long1, long2).transact.map(_.ids)
        List(id4, _) <- Ns.float.insert(float1, float2).transact.map(_.ids)
        List(id5, _) <- Ns.double.insert(double1, double2).transact.map(_.ids)
        List(id6, _) <- Ns.boolean.insert(boolean1, boolean2).transact.map(_.ids)
        List(id7, _) <- Ns.bigInt.insert(bigInt1, bigInt2).transact.map(_.ids)
        List(id8, _) <- Ns.bigDecimal.insert(bigDecimal1, bigDecimal2).transact.map(_.ids)
        List(id9, _) <- Ns.date.insert(date1, date2).transact.map(_.ids)
        List(id10, _) <- Ns.duration.insert(duration1, duration2).transact.map(_.ids)
        List(id11, _) <- Ns.instant.insert(instant1, instant2).transact.map(_.ids)
        List(id12, _) <- Ns.localDate.insert(localDate1, localDate2).transact.map(_.ids)
        List(id13, _) <- Ns.localTime.insert(localTime1, localTime2).transact.map(_.ids)
        List(id14, _) <- Ns.localDateTime.insert(localDateTime1, localDateTime2).transact.map(_.ids)
        List(id15, _) <- Ns.offsetTime.insert(offsetTime1, offsetTime2).transact.map(_.ids)
        List(id16, _) <- Ns.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact.map(_.ids)
        List(id17, _) <- Ns.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact.map(_.ids)
        List(id18, _) <- Ns.uuid.insert(uuid1, uuid2).transact.map(_.ids)
        List(id19, _) <- Ns.uri.insert(uri1, uri2).transact.map(_.ids)
        List(id20, _) <- Ns.byte.insert(byte1, byte2).transact.map(_.ids)
        List(id21, _) <- Ns.short.insert(short1, short2).transact.map(_.ids)
        List(id22, _) <- Ns.char.insert(char1, char2).transact.map(_.ids)

        // Delete all first values
        _ <- rawTransact(s"delete from Ns where id = $id1")
        _ <- rawTransact(s"delete from Ns where id = $id2")
        _ <- rawTransact(s"delete from Ns where id = $id3")
        _ <- rawTransact(s"delete from Ns where id = $id4")
        _ <- rawTransact(s"delete from Ns where id = $id5")
        _ <- rawTransact(s"delete from Ns where id = $id6")
        _ <- rawTransact(s"delete from Ns where id = $id7")
        _ <- rawTransact(s"delete from Ns where id = $id8")
        _ <- rawTransact(s"delete from Ns where id = $id9")
        _ <- rawTransact(s"delete from Ns where id = $id10")
        _ <- rawTransact(s"delete from Ns where id = $id11")
        _ <- rawTransact(s"delete from Ns where id = $id12")
        _ <- rawTransact(s"delete from Ns where id = $id13")
        _ <- rawTransact(s"delete from Ns where id = $id14")
        _ <- rawTransact(s"delete from Ns where id = $id15")
        _ <- rawTransact(s"delete from Ns where id = $id16")
        _ <- rawTransact(s"delete from Ns where id = $id17")
        _ <- rawTransact(s"delete from Ns where id = $id18")
        _ <- rawTransact(s"delete from Ns where id = $id19")
        _ <- rawTransact(s"delete from Ns where id = $id20")
        _ <- rawTransact(s"delete from Ns where id = $id21")
        _ <- rawTransact(s"delete from Ns where id = $id22")

        // First values deleted
        _ <- Ns.string.query.get.map(_ ==> List(string2))
        _ <- Ns.int.query.get.map(_ ==> List(int2))
        _ <- Ns.long.query.get.map(_ ==> List(long2))
        _ <- Ns.float.query.get.map(_ ==> List(float2))
        _ <- Ns.double.query.get.map(_ ==> List(double2))
        _ <- Ns.boolean.query.get.map(_ ==> List(boolean2))
        _ <- Ns.bigInt.query.get.map(_ ==> List(bigInt2))
        _ <- Ns.bigDecimal.query.get.map(_ ==> List(bigDecimal2))
        _ <- Ns.date.query.get.map(_ ==> List(date2))
        _ <- Ns.duration.query.get.map(_ ==> List(duration2))
        _ <- Ns.instant.query.get.map(_ ==> List(instant2))
        _ <- Ns.localDate.query.get.map(_ ==> List(localDate2))
        _ <- Ns.localTime.query.get.map(_ ==> List(localTime2))
        _ <- Ns.localDateTime.query.get.map(_ ==> List(localDateTime2))
        _ <- Ns.offsetTime.query.get.map(_ ==> List(offsetTime2))
        _ <- Ns.offsetDateTime.query.get.map(_ ==> List(offsetDateTime2))
        _ <- Ns.zonedDateTime.query.get.map(_ ==> List(zonedDateTime2))
        _ <- Ns.uuid.query.get.map(_ ==> List(uuid2))
        _ <- Ns.uri.query.get.map(_ ==> List(uri2))
        _ <- Ns.byte.query.get.map(_ ==> List(byte2))
        _ <- Ns.short.query.get.map(_ ==> List(short2))
        _ <- Ns.char.query.get.map(_ ==> List(char2))
      } yield ()
    }
  }
}