package molecule.datalog.datomic.compliance.fallback

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.language.implicitConversions

object RawTransact extends TestSuite_datomic {

  override lazy val tests = Tests {

    "Semantics" - types { implicit conn =>
      for {
        // Insert 1 row with 2 values
        txReport <- rawTransact(
          """[
            |  [:db/add -1 :Ns/string "a"]
            |  [:db/add -1 :Ns/int     1 ]
            |]
            |""".stripMargin)

        // Values saved
        _ <- Ns.string.int.query.get.map(_ ==> List(("a", 1)))

        // Transaction report has id of inserted entity
        _ <- Ns(txReport.id).string.int.query.get.map(_ ==> List(("a", 1)))

        // Insert 2 rows
        _ <- rawTransact(
          """[
            |  [:db/add -1 :Ns/i       1    ]
            |  [:db/add -1 :Ns/boolean true ]
            |  [:db/add -2 :Ns/i       2    ]
            |  [:db/add -2 :Ns/boolean false]
            |]
            |""".stripMargin)
        // Rows saved
        _ <- Ns.i.a1.boolean.query.get.map(_ ==> List((1, true), (2, false)))
      } yield ()
    }


    "Insert" - types { implicit conn =>
      // URIs within EDN requires tricks and is therefore not tested here
      for {
        _ <- rawTransact(s"""[[:db/add -1 :Ns/string         "$string1"                    ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/int            $int1                         ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/long           $long1                        ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/float          $float1                       ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/double         $double1                      ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/boolean        $boolean1                     ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/bigInt         ${bigInt1}N                   ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/bigDecimal     ${bigDecimal1}M               ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/date           #inst "${date2datomic(date1)}"]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/duration       "${duration1.toString}"       ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/instant        "${instant1.toString}"        ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/localDate      "${localDate1.toString}"      ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/localTime      "${localTime1.toString}"      ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/localDateTime  "${localDateTime1.toString}"  ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/offsetTime     "${offsetTime1.toString}"     ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/offsetDateTime "${offsetDateTime1.toString}" ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/zonedDateTime  "${zonedDateTime1.toString}"  ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/uuid           #uuid "$uuid1"                ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/byte           $byte1                        ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/short          $short1                       ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/char           "$char1"                      ]]""")


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
        _ <- Ns.byte.query.get.map(_.head ==> byte1)
        _ <- Ns.short.query.get.map(_.head ==> short1)
        _ <- Ns.char.query.get.map(_.head ==> char1)
      } yield ()
    }


    "Insert Set" - types { implicit conn =>
      // URIs within EDN requires tricks and is therefore not tested here
      for {
        _ <- Ns.iSet(Set(1, 2)).save.inspect
        _ <- rawTransact(s"""[[:db/add -1 :Ns/stringSet         "$string1"                    ][:db/add -1 :Ns/stringSet         "$string2"                    ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/intSet            $int1                         ][:db/add -1 :Ns/intSet            $int2                         ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/longSet           $long1                        ][:db/add -1 :Ns/longSet           $long2                        ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/floatSet          $float1                       ][:db/add -1 :Ns/floatSet          $float2                       ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/doubleSet         $double1                      ][:db/add -1 :Ns/doubleSet         $double2                      ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/booleanSet        $boolean1                     ][:db/add -1 :Ns/booleanSet        $boolean2                     ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/bigIntSet         ${bigInt1}N                   ][:db/add -1 :Ns/bigIntSet         ${bigInt2}N                   ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/bigDecimalSet     ${bigDecimal1}M               ][:db/add -1 :Ns/bigDecimalSet     ${bigDecimal2}M               ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/dateSet           #inst "${date2datomic(date1)}"][:db/add -1 :Ns/dateSet           #inst "${date2datomic(date2)}"]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/durationSet       "${duration1.toString}"       ][:db/add -1 :Ns/durationSet       "${duration2.toString}"       ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/instantSet        "${instant1.toString}"        ][:db/add -1 :Ns/instantSet        "${instant2.toString}"        ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/localDateSet      "${localDate1.toString}"      ][:db/add -1 :Ns/localDateSet      "${localDate2.toString}"      ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/localTimeSet      "${localTime1.toString}"      ][:db/add -1 :Ns/localTimeSet      "${localTime2.toString}"      ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/localDateTimeSet  "${localDateTime1.toString}"  ][:db/add -1 :Ns/localDateTimeSet  "${localDateTime2.toString}"  ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/offsetTimeSet     "${offsetTime1.toString}"     ][:db/add -1 :Ns/offsetTimeSet     "${offsetTime2.toString}"     ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/offsetDateTimeSet "${offsetDateTime1.toString}" ][:db/add -1 :Ns/offsetDateTimeSet "${offsetDateTime2.toString}" ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/zonedDateTimeSet  "${zonedDateTime1.toString}"  ][:db/add -1 :Ns/zonedDateTimeSet  "${zonedDateTime2.toString}"  ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/uuidSet           #uuid "$uuid1"                ][:db/add -1 :Ns/uuidSet           #uuid "$uuid2"                ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/byteSet           $byte1                        ][:db/add -1 :Ns/byteSet           $byte2                        ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/shortSet          $short1                       ][:db/add -1 :Ns/shortSet          $short2                       ]]""")
        _ <- rawTransact(s"""[[:db/add -1 :Ns/charSet           "$char1"                      ][:db/add -1 :Ns/charSet           "$char2"                      ]]""")
        //        _ <- rawTransact(s"""insert into Ns (uriSet           ) values (array['$uri1', '$uri2'])""")

        // All types properly inserted
        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2))
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2))
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))
        _ <- Ns.booleanSet.query.get.map(_.head ==> Set(boolean1, boolean2))
        _ <- Ns.bigIntSet.query.get.map(_.head ==> Set(bigInt1, bigInt2))
        _ <- Ns.bigDecimalSet.query.get.map(_.head ==> Set(bigDecimal1, bigDecimal2))
        _ <- Ns.dateSet.query.get.map(_.head ==> Set(date1, date2))
        _ <- Ns.durationSet.query.get.map(_.head ==> Set(duration1, duration2))
        _ <- Ns.instantSet.query.get.map(_.head ==> Set(instant1, instant2))
        _ <- Ns.localDateSet.query.get.map(_.head ==> Set(localDate1, localDate2))
        _ <- Ns.localTimeSet.query.get.map(_.head ==> Set(localTime1, localTime2))
        _ <- Ns.localDateTimeSet.query.get.map(_.head ==> Set(localDateTime1, localDateTime2))
        _ <- Ns.offsetTimeSet.query.get.map(_.head ==> Set(offsetTime1, offsetTime2))
        _ <- Ns.offsetDateTimeSet.query.get.map(_.head ==> Set(offsetDateTime1, offsetDateTime2))
        _ <- Ns.zonedDateTimeSet.query.get.map(_.head ==> Set(zonedDateTime1, zonedDateTime2))
        _ <- Ns.uuidSet.query.get.map(_.head ==> Set(uuid1, uuid2))
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2))
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2))
        //        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))
      } yield ()
    }


    "Update" - types { implicit conn =>
      // URIs within EDN requires tricks and is therefore not tested here
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
        id19 <- Ns.byte(byte1).save.transact.map(_.id)
        id20 <- Ns.short(short1).save.transact.map(_.id)
        id21 <- Ns.char(char1).save.transact.map(_.id)

        // Update all values
        _ <- rawTransact(s"""[[:db/add $id1  :Ns/string         "$string2"                    ]]""")
        _ <- rawTransact(s"""[[:db/add $id2  :Ns/int            $int2                         ]]""")
        _ <- rawTransact(s"""[[:db/add $id3  :Ns/long           $long2                        ]]""")
        _ <- rawTransact(s"""[[:db/add $id4  :Ns/float          $float2                       ]]""")
        _ <- rawTransact(s"""[[:db/add $id5  :Ns/double         $double2                      ]]""")
        _ <- rawTransact(s"""[[:db/add $id6  :Ns/boolean        $boolean2                     ]]""")
        _ <- rawTransact(s"""[[:db/add $id7  :Ns/bigInt         ${bigInt2}N                   ]]""")
        _ <- rawTransact(s"""[[:db/add $id8  :Ns/bigDecimal     ${bigDecimal2}M               ]]""")
        _ <- rawTransact(s"""[[:db/add $id9  :Ns/date           #inst "${date2datomic(date2)}"]]""")
        _ <- rawTransact(s"""[[:db/add $id10 :Ns/duration       "${duration2.toString}"       ]]""")
        _ <- rawTransact(s"""[[:db/add $id11 :Ns/instant        "${instant2.toString}"        ]]""")
        _ <- rawTransact(s"""[[:db/add $id12 :Ns/localDate      "${localDate2.toString}"      ]]""")
        _ <- rawTransact(s"""[[:db/add $id13 :Ns/localTime      "${localTime2.toString}"      ]]""")
        _ <- rawTransact(s"""[[:db/add $id14 :Ns/localDateTime  "${localDateTime2.toString}"  ]]""")
        _ <- rawTransact(s"""[[:db/add $id15 :Ns/offsetTime     "${offsetTime2.toString}"     ]]""")
        _ <- rawTransact(s"""[[:db/add $id16 :Ns/offsetDateTime "${offsetDateTime2.toString}" ]]""")
        _ <- rawTransact(s"""[[:db/add $id17 :Ns/zonedDateTime  "${zonedDateTime2.toString}"  ]]""")
        _ <- rawTransact(s"""[[:db/add $id18 :Ns/uuid           #uuid "$uuid2"                ]]""")
        _ <- rawTransact(s"""[[:db/add $id19 :Ns/byte           $byte2                        ]]""")
        _ <- rawTransact(s"""[[:db/add $id20 :Ns/short          $short2                       ]]""")
        _ <- rawTransact(s"""[[:db/add $id21 :Ns/char           "$char2"                      ]]""")

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
        _ <- Ns.byte.query.get.map(_.head ==> byte2)
        _ <- Ns.short.query.get.map(_.head ==> short2)
        _ <- Ns.char.query.get.map(_.head ==> char2)
      } yield ()
    }


    "Delete" - types { implicit conn =>
      for {
        // Initial 2 rows
        List(id1, _) <- Ns.string.insert(string1, string2).transact.map(_.ids)

        // Delete first row
        _ <- rawTransact(s"[[:db/retractEntity $id1]]")

        // First row deleted
        _ <- Ns.string.query.get.map(_ ==> List(string2))
      } yield ()
    }
  }
}