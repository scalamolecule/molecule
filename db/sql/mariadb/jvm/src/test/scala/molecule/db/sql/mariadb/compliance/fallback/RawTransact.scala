package molecule.db.sql.mariadb.compliance.fallback

import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Types.*
import molecule.coreTests.setup.{Test, TestUtils}
import molecule.db.sql
import molecule.db.sql.mariadb.async.*
import molecule.db.sql.mariadb.setup.DbProviders_mariadb
//import scala.language.implicitConversions

class RawTransact extends Test with DbProviders_mariadb with TestUtils {

  "Semantics" - types { implicit conn =>
    for {
      // Insert 1 row with 2 values
      txReport <- rawTransact(
        """INSERT INTO Entity (
          |  string,
          |  int_
          |) VALUES ('a', 1)
          |""".stripMargin)

      // Values saved
      _ <- Entity.string.int.query.get.map(_ ==> List(("a", 1)))

      // Transaction report has id of inserted entity
      _ <- Entity(txReport.id).string.int.query.get.map(_ ==> List(("a", 1)))

      // Insert 2 rows
      _ <- rawTransact("insert into Entity (i, boolean) values (1, true), (2, false)")
      // Rows saved
      _ <- Entity.i.boolean.query.get.map(_ ==> List((1, true), (2, false)))
    } yield ()
  }


  "Insert" - types { implicit conn =>
    for {
      _ <- rawTransact(s"insert into Entity (string        ) values ('$string1')")
      _ <- rawTransact(s"insert into Entity (int_          ) values ($int1)")
      _ <- rawTransact(s"insert into Entity (long_         ) values ($long1)")
      _ <- rawTransact(s"insert into Entity (float_        ) values ($float1)")
      _ <- rawTransact(s"insert into Entity (double_       ) values ($double1)")
      _ <- rawTransact(s"insert into Entity (boolean       ) values ($boolean1)")
      _ <- rawTransact(s"insert into Entity (bigInt_       ) values ($bigInt1)")
      _ <- rawTransact(s"insert into Entity (bigDecimal    ) values ($bigDecimal1)")
      _ <- rawTransact(s"insert into Entity (date_         ) values (${date1.getTime})") // epoch time saved
      _ <- rawTransact(s"insert into Entity (duration      ) values ('${duration1.toString}')")
      _ <- rawTransact(s"insert into Entity (instant       ) values ('${instant1.toString}')")
      _ <- rawTransact(s"insert into Entity (localDate     ) values ('${localDate1.toString}')")
      _ <- rawTransact(s"insert into Entity (localTime_    ) values ('${localTime1.toString}')")
      _ <- rawTransact(s"insert into Entity (localDateTime ) values ('${localDateTime1.toString}')")
      _ <- rawTransact(s"insert into Entity (offsetTime    ) values ('${offsetTime1.toString}')")
      _ <- rawTransact(s"insert into Entity (offsetDateTime) values ('${offsetDateTime1.toString}')")
      _ <- rawTransact(s"insert into Entity (zonedDateTime ) values ('${zonedDateTime1.toString}')")
      _ <- rawTransact(s"insert into Entity (uuid          ) values ('$uuid1')")
      _ <- rawTransact(s"insert into Entity (uri           ) values ('$uri1')")
      _ <- rawTransact(s"insert into Entity (byte          ) values ($byte1)")
      _ <- rawTransact(s"insert into Entity (short         ) values ($short1)")
      _ <- rawTransact(s"insert into Entity (char_         ) values ('$char1')")

      // All types properly inserted
      _ <- Entity.string.query.get.map(_.head ==> string1)
      _ <- Entity.int.query.get.map(_.head ==> int1)
      _ <- Entity.long.query.get.map(_.head ==> long1)
      _ <- Entity.float.query.get.map(_.head ==> float1)
      _ <- Entity.double.query.get.map(_.head ==> double1)
      _ <- Entity.boolean.query.get.map(_.head ==> boolean1)
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt1)
      _ <- Entity.bigDecimal.query.get.map(_.head ==> bigDecimal1)
      _ <- Entity.date.query.get.map(_.head ==> date1)
      _ <- Entity.duration.query.get.map(_.head ==> duration1)
      _ <- Entity.instant.query.get.map(_.head ==> instant1)
      _ <- Entity.localDate.query.get.map(_.head ==> localDate1)
      _ <- Entity.localTime.query.get.map(_.head ==> localTime1)
      _ <- Entity.localDateTime.query.get.map(_.head ==> localDateTime1)
      _ <- Entity.offsetTime.query.get.map(_.head ==> offsetTime1)
      _ <- Entity.offsetDateTime.query.get.map(_.head ==> offsetDateTime1)
      _ <- Entity.zonedDateTime.query.get.map(_.head ==> zonedDateTime1)
      _ <- Entity.uuid.query.get.map(_.head ==> uuid1)
      _ <- Entity.uri.query.get.map(_.head ==> uri1)
      _ <- Entity.byte.query.get.map(_.head ==> byte1)
      _ <- Entity.short.query.get.map(_.head ==> short1)
      _ <- Entity.char.query.get.map(_.head ==> char1)
    } yield ()
  }


  "Insert Set" - types { implicit conn =>
    for {
      _ <- Entity.stringSet(Set("a", "b")).save.inspect
      _ <- rawTransact(s"""insert into Entity (stringSet        ) values ('["$string1", "$string2"]')""")
      _ <- rawTransact(s"""insert into Entity (intSet           ) values ('[$int1, $int2]')""")
      _ <- rawTransact(s"""insert into Entity (longSet          ) values ('[$long1, $long2]')""")
      _ <- rawTransact(s"""insert into Entity (floatSet         ) values ('[$float1, $float2]')""")
      _ <- rawTransact(s"""insert into Entity (doubleSet        ) values ('[$double1, $double2]')""")
      _ <- rawTransact(s"""insert into Entity (booleanSet       ) values ('[$boolean1, $boolean2]')""")
      _ <- rawTransact(s"""insert into Entity (bigIntSet        ) values ('[$bigInt1, $bigInt2]')""")
      _ <- rawTransact(s"""insert into Entity (bigDecimalSet    ) values ('[$bigDecimal1, $bigDecimal2]')""")
      _ <- rawTransact(s"""insert into Entity (dateSet          ) values ('[${date1.getTime}, ${date2.getTime}]')""")
      _ <- rawTransact(s"""insert into Entity (durationSet      ) values ('["${duration1.toString}", "${duration2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (instantSet       ) values ('["${instant1.toString}", "${instant2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (localDateSet     ) values ('["${localDate1.toString}", "${localDate2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (localTimeSet     ) values ('["${localTime1.toString}", "${localTime2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (localDateTimeSet ) values ('["${localDateTime1.toString}", "${localDateTime2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (offsetTimeSet    ) values ('["${offsetTime1.toString}", "${offsetTime2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (offsetDateTimeSet) values ('["${offsetDateTime1.toString}", "${offsetDateTime2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (zonedDateTimeSet ) values ('["${zonedDateTime1.toString}", "${zonedDateTime2.toString}"]')""")
      _ <- rawTransact(s"""insert into Entity (uuidSet          ) values ('["$uuid1", "$uuid2"]')""")
      _ <- rawTransact(s"""insert into Entity (uriSet           ) values ('["$uri1", "$uri2"]')""")
      _ <- rawTransact(s"""insert into Entity (byteSet          ) values ('[$byte1, $byte2]')""")
      _ <- rawTransact(s"""insert into Entity (shortSet         ) values ('[$short1, $short2]')""")
      _ <- rawTransact(s"""insert into Entity (charSet          ) values ('["$char1", "$char2"]')""")

      // All types properly inserted
      _ <- Entity.stringSet.query.get.map(_.head ==> Set(string1, string2))
      _ <- Entity.intSet.query.get.map(_.head ==> Set(int1, int2))
      _ <- Entity.longSet.query.get.map(_.head ==> Set(long1, long2))
      _ <- Entity.floatSet.query.get.map(_.head ==> Set(float1, float2))
      _ <- Entity.doubleSet.query.get.map(_.head ==> Set(double1, double2))
      _ <- Entity.booleanSet.query.get.map(_.head ==> Set(boolean1, boolean2))
      _ <- Entity.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))
      _ <- Entity.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))
      _ <- Entity.dateSet.query.get.map(_.head ==> Set(date1, date2))
      _ <- Entity.durationSet.query.get.map(_.head ==> Set(duration1, duration2))
      _ <- Entity.instantSet.query.get.map(_.head ==> Set(instant1, instant2))
      _ <- Entity.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))
      _ <- Entity.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))
      _ <- Entity.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))
      _ <- Entity.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))
      _ <- Entity.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))
      _ <- Entity.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))
      _ <- Entity.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))
      _ <- Entity.uriSet.query.get.map(_.head ==> Set(uri1, uri2))
      _ <- Entity.byteSet.query.get.map(_.head ==> Set(byte1, byte2))
      _ <- Entity.shortSet.query.get.map(_.head ==> Set(short1, short2))
      _ <- Entity.charSet.query.get.map(_.head ==> Set(char1, char2))
    } yield ()
  }


  "Update" - types { implicit conn =>
    for {
      // Initial values
      id1 <- Entity.string(string1).save.transact.map(_.id)
      id2 <- Entity.int(int1).save.transact.map(_.id)
      id3 <- Entity.long(long1).save.transact.map(_.id)
      id4 <- Entity.float(float1).save.transact.map(_.id)
      id5 <- Entity.double(double1).save.transact.map(_.id)
      id6 <- Entity.boolean(boolean1).save.transact.map(_.id)
      id7 <- Entity.bigInt(bigInt1).save.transact.map(_.id)
      id8 <- Entity.bigDecimal(bigDecimal1).save.transact.map(_.id)
      id9 <- Entity.date(date1).save.transact.map(_.id)
      id10 <- Entity.duration(duration1).save.transact.map(_.id)
      id11 <- Entity.instant(instant1).save.transact.map(_.id)
      id12 <- Entity.localDate(localDate1).save.transact.map(_.id)
      id13 <- Entity.localTime(localTime1).save.transact.map(_.id)
      id14 <- Entity.localDateTime(localDateTime1).save.transact.map(_.id)
      id15 <- Entity.offsetTime(offsetTime1).save.transact.map(_.id)
      id16 <- Entity.offsetDateTime(offsetDateTime1).save.transact.map(_.id)
      id17 <- Entity.zonedDateTime(zonedDateTime1).save.transact.map(_.id)
      id18 <- Entity.uuid(uuid1).save.transact.map(_.id)
      id19 <- Entity.uri(uri1).save.transact.map(_.id)
      id20 <- Entity.byte(byte1).save.transact.map(_.id)
      id21 <- Entity.short(short1).save.transact.map(_.id)
      id22 <- Entity.char(char1).save.transact.map(_.id)

      // Update all values
      _ <- rawTransact(s"update Entity set string         = '$string2'                    where id = $id1")
      _ <- rawTransact(s"update Entity set int_           = $int2                         where id = $id2")
      _ <- rawTransact(s"update Entity set long_          = $long2                        where id = $id3")
      _ <- rawTransact(s"update Entity set float_         = $float2                       where id = $id4")
      _ <- rawTransact(s"update Entity set double_        = $double2                      where id = $id5")
      _ <- rawTransact(s"update Entity set boolean        = $boolean2                     where id = $id6")
      _ <- rawTransact(s"update Entity set bigInt_        = $bigInt2                      where id = $id7")
      _ <- rawTransact(s"update Entity set bigDecimal     = $bigDecimal2                  where id = $id8")
      _ <- rawTransact(s"update Entity set date_          = ${date2.getTime}              where id = $id9")
      _ <- rawTransact(s"update Entity set duration       = '${duration2.toString}'       where id = $id10")
      _ <- rawTransact(s"update Entity set instant        = '${instant2.toString}'        where id = $id11")
      _ <- rawTransact(s"update Entity set localDate      = '${localDate2.toString}'      where id = $id12")
      _ <- rawTransact(s"update Entity set localTime_     = '${localTime2.toString}'      where id = $id13")
      _ <- rawTransact(s"update Entity set localDateTime  = '${localDateTime2.toString}'  where id = $id14")
      _ <- rawTransact(s"update Entity set offsetTime     = '${offsetTime2.toString}'     where id = $id15")
      _ <- rawTransact(s"update Entity set offsetDateTime = '${offsetDateTime2.toString}' where id = $id16")
      _ <- rawTransact(s"update Entity set zonedDateTime  = '${zonedDateTime2.toString}'  where id = $id17")
      _ <- rawTransact(s"update Entity set uuid           = '$uuid2'                      where id = $id18")
      _ <- rawTransact(s"update Entity set uri            = '$uri2'                       where id = $id19")
      _ <- rawTransact(s"update Entity set byte           = $byte2                        where id = $id20")
      _ <- rawTransact(s"update Entity set short          = $short2                       where id = $id21")
      _ <- rawTransact(s"update Entity set char_          = '$char2'                      where id = $id22")

      // All types properly updated
      _ <- Entity.string.query.get.map(_.head ==> string2)
      _ <- Entity.int.query.get.map(_.head ==> int2)
      _ <- Entity.long.query.get.map(_.head ==> long2)
      _ <- Entity.float.query.get.map(_.head ==> float2)
      _ <- Entity.double.query.get.map(_.head ==> double2)
      _ <- Entity.boolean.query.get.map(_.head ==> boolean2)
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt2)
      _ <- Entity.bigDecimal.query.get.map(_.head ==> bigDecimal2)
      _ <- Entity.date.query.get.map(_.head ==> date2)
      _ <- Entity.duration.query.get.map(_.head ==> duration2)
      _ <- Entity.instant.query.get.map(_.head ==> instant2)
      _ <- Entity.localDate.query.get.map(_.head ==> localDate2)
      _ <- Entity.localTime.query.get.map(_.head ==> localTime2)
      _ <- Entity.localDateTime.query.get.map(_.head ==> localDateTime2)
      _ <- Entity.offsetTime.query.get.map(_.head ==> offsetTime2)
      _ <- Entity.offsetDateTime.query.get.map(_.head ==> offsetDateTime2)
      _ <- Entity.zonedDateTime.query.get.map(_.head ==> zonedDateTime2)
      _ <- Entity.uuid.query.get.map(_.head ==> uuid2)
      _ <- Entity.uri.query.get.map(_.head ==> uri2)
      _ <- Entity.byte.query.get.map(_.head ==> byte2)
      _ <- Entity.short.query.get.map(_.head ==> short2)
      _ <- Entity.char.query.get.map(_.head ==> char2)
    } yield ()
  }


  "Delete" - types { implicit conn =>
    for {
      // Initial values
      List(id1, _) <- Entity.string.insert(string1, string2).transact.map(_.ids)
      List(id2, _) <- Entity.int.insert(int1, int2).transact.map(_.ids)
      List(id3, _) <- Entity.long.insert(long1, long2).transact.map(_.ids)
      List(id4, _) <- Entity.float.insert(float1, float2).transact.map(_.ids)
      List(id5, _) <- Entity.double.insert(double1, double2).transact.map(_.ids)
      List(id6, _) <- Entity.boolean.insert(boolean1, boolean2).transact.map(_.ids)
      List(id7, _) <- Entity.bigInt.insert(bigInt1, bigInt2).transact.map(_.ids)
      List(id8, _) <- Entity.bigDecimal.insert(bigDecimal1, bigDecimal2).transact.map(_.ids)
      List(id9, _) <- Entity.date.insert(date1, date2).transact.map(_.ids)
      List(id10, _) <- Entity.duration.insert(duration1, duration2).transact.map(_.ids)
      List(id11, _) <- Entity.instant.insert(instant1, instant2).transact.map(_.ids)
      List(id12, _) <- Entity.localDate.insert(localDate1, localDate2).transact.map(_.ids)
      List(id13, _) <- Entity.localTime.insert(localTime1, localTime2).transact.map(_.ids)
      List(id14, _) <- Entity.localDateTime.insert(localDateTime1, localDateTime2).transact.map(_.ids)
      List(id15, _) <- Entity.offsetTime.insert(offsetTime1, offsetTime2).transact.map(_.ids)
      List(id16, _) <- Entity.offsetDateTime.insert(offsetDateTime1, offsetDateTime2).transact.map(_.ids)
      List(id17, _) <- Entity.zonedDateTime.insert(zonedDateTime1, zonedDateTime2).transact.map(_.ids)
      List(id18, _) <- Entity.uuid.insert(uuid1, uuid2).transact.map(_.ids)
      List(id19, _) <- Entity.uri.insert(uri1, uri2).transact.map(_.ids)
      List(id20, _) <- Entity.byte.insert(byte1, byte2).transact.map(_.ids)
      List(id21, _) <- Entity.short.insert(short1, short2).transact.map(_.ids)
      List(id22, _) <- Entity.char.insert(char1, char2).transact.map(_.ids)

      // Delete all first values
      _ <- rawTransact(s"delete from Entity where id = $id1")
      _ <- rawTransact(s"delete from Entity where id = $id2")
      _ <- rawTransact(s"delete from Entity where id = $id3")
      _ <- rawTransact(s"delete from Entity where id = $id4")
      _ <- rawTransact(s"delete from Entity where id = $id5")
      _ <- rawTransact(s"delete from Entity where id = $id6")
      _ <- rawTransact(s"delete from Entity where id = $id7")
      _ <- rawTransact(s"delete from Entity where id = $id8")
      _ <- rawTransact(s"delete from Entity where id = $id9")
      _ <- rawTransact(s"delete from Entity where id = $id10")
      _ <- rawTransact(s"delete from Entity where id = $id11")
      _ <- rawTransact(s"delete from Entity where id = $id12")
      _ <- rawTransact(s"delete from Entity where id = $id13")
      _ <- rawTransact(s"delete from Entity where id = $id14")
      _ <- rawTransact(s"delete from Entity where id = $id15")
      _ <- rawTransact(s"delete from Entity where id = $id16")
      _ <- rawTransact(s"delete from Entity where id = $id17")
      _ <- rawTransact(s"delete from Entity where id = $id18")
      _ <- rawTransact(s"delete from Entity where id = $id19")
      _ <- rawTransact(s"delete from Entity where id = $id20")
      _ <- rawTransact(s"delete from Entity where id = $id21")
      _ <- rawTransact(s"delete from Entity where id = $id22")

      // First values deleted
      _ <- Entity.string.query.get.map(_ ==> List(string2))
      _ <- Entity.int.query.get.map(_ ==> List(int2))
      _ <- Entity.long.query.get.map(_ ==> List(long2))
      _ <- Entity.float.query.get.map(_ ==> List(float2))
      _ <- Entity.double.query.get.map(_ ==> List(double2))
      _ <- Entity.boolean.query.get.map(_ ==> List(boolean2))
      _ <- Entity.bigInt.query.get.map(_ ==> List(bigInt2))
      _ <- Entity.bigDecimal.query.get.map(_ ==> List(bigDecimal2))
      _ <- Entity.date.query.get.map(_ ==> List(date2))
      _ <- Entity.duration.query.get.map(_ ==> List(duration2))
      _ <- Entity.instant.query.get.map(_ ==> List(instant2))
      _ <- Entity.localDate.query.get.map(_ ==> List(localDate2))
      _ <- Entity.localTime.query.get.map(_ ==> List(localTime2))
      _ <- Entity.localDateTime.query.get.map(_ ==> List(localDateTime2))
      _ <- Entity.offsetTime.query.get.map(_ ==> List(offsetTime2))
      _ <- Entity.offsetDateTime.query.get.map(_ ==> List(offsetDateTime2))
      _ <- Entity.zonedDateTime.query.get.map(_ ==> List(zonedDateTime2))
      _ <- Entity.uuid.query.get.map(_ ==> List(uuid2))
      _ <- Entity.uri.query.get.map(_ ==> List(uri2))
      _ <- Entity.byte.query.get.map(_ ==> List(byte2))
      _ <- Entity.short.query.get.map(_ ==> List(short2))
      _ <- Entity.char.query.get.map(_ ==> List(char2))
    } yield ()
  }
}