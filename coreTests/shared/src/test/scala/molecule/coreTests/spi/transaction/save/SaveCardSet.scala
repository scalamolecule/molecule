package molecule.coreTests.spi.transaction.save

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.collection.immutable._

trait SaveCardSet extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        // Empty Set of values is ignored
        _ <- Ns.iSet.query.get.map(_ ==> List())
        _ <- Ns.iSet(Set.empty[Int]).save.transact
        _ <- Ns.iSet.query.get.map(_ ==> List())

        _ <- Ns.stringSet(Set(string1, string2)).save.transact
        _ <- Ns.intSet(Set(int1, int2)).save.transact
        _ <- Ns.longSet(Set(long1, long2)).save.transact
        _ <- Ns.floatSet(Set(float1, float2)).save.transact
        _ <- Ns.doubleSet(Set(double1, double2)).save.transact
        _ <- Ns.booleanSet(Set(boolean0)).save.transact
        _ <- Ns.bigIntSet(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.bigDecimalSet(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.dateSet(Set(date1, date2)).save.transact
        _ <- Ns.durationSet(Set(duration1, duration2)).save.transact
        _ <- Ns.instantSet(Set(instant1, instant2)).save.transact
        _ <- Ns.localDateSet(Set(localDate1, localDate2)).save.transact
        _ <- Ns.localTimeSet(Set(localTime1, localTime2)).save.transact
        _ <- Ns.localDateTimeSet(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.offsetTimeSet(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.uuidSet(Set(uuid1, uuid2)).save.transact
        _ <- Ns.uriSet(Set(uri1, uri2)).save.transact
        _ <- Ns.byteSet(Set(byte1, byte2)).save.transact
        _ <- Ns.shortSet(Set(short1, short2)).save.transact
        _ <- Ns.charSet(Set(char1, char2)).save.transact

        _ <- Ns.stringSet.query.get.map(_.head ==> Set(string1, string2))
        _ <- Ns.intSet.query.get.map(_.head ==> Set(int1, int2))
        _ <- Ns.longSet.query.get.map(_.head ==> Set(long1, long2))
        _ <- Ns.floatSet.query.get.map(_.head ==> Set(float1, float2))
        _ <- Ns.doubleSet.query.get.map(_.head ==> Set(double1, double2))
        _ <- Ns.booleanSet.query.get.map(_.head ==> Set(boolean0))
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
        _ <- Ns.uriSet.query.get.map(_.head ==> Set(uri1, uri2))
        _ <- Ns.byteSet.query.get.map(_.head ==> Set(byte1, byte2))
        _ <- Ns.shortSet.query.get.map(_.head ==> Set(short1, short2))
        _ <- Ns.charSet.query.get.map(_.head ==> Set(char1, char2))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.refs(Set(r1, r2)).save.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(r1, r2))
      } yield ()
    }


    "optional" - types { implicit conn =>
      for {
        // Empty option of Set of values is ignored
        _ <- Ns.intSet_?(Option.empty[Set[Int]]).save.transact
        _ <- Ns.intSet.query.get.map(_ ==> List())

        _ <- Ns.int(1).i(1).stringSet_?(Option.empty[Set[String]]).save.transact
        _ <- Ns.int(2).i(1).intSet_?(Option.empty[Set[Int]]).save.transact
        _ <- Ns.int(3).i(1).longSet_?(Option.empty[Set[Long]]).save.transact
        _ <- Ns.int(4).i(1).floatSet_?(Option.empty[Set[Float]]).save.transact
        _ <- Ns.int(5).i(1).doubleSet_?(Option.empty[Set[Double]]).save.transact
        _ <- Ns.int(6).i(1).booleanSet_?(Option.empty[Set[Boolean]]).save.transact
        _ <- Ns.int(7).i(1).bigIntSet_?(Option.empty[Set[BigInt]]).save.transact
        _ <- Ns.int(8).i(1).bigDecimalSet_?(Option.empty[Set[BigDecimal]]).save.transact
        _ <- Ns.int(9).i(1).dateSet_?(Option.empty[Set[Date]]).save.transact
        _ <- Ns.int(10).i(1).durationSet_?(Option.empty[Set[Duration]]).save.transact
        _ <- Ns.int(11).i(1).instantSet_?(Option.empty[Set[Instant]]).save.transact
        _ <- Ns.int(12).i(1).localDateSet_?(Option.empty[Set[LocalDate]]).save.transact
        _ <- Ns.int(13).i(1).localTimeSet_?(Option.empty[Set[LocalTime]]).save.transact
        _ <- Ns.int(14).i(1).localDateTimeSet_?(Option.empty[Set[LocalDateTime]]).save.transact
        _ <- Ns.int(15).i(1).offsetTimeSet_?(Option.empty[Set[OffsetTime]]).save.transact
        _ <- Ns.int(16).i(1).offsetDateTimeSet_?(Option.empty[Set[OffsetDateTime]]).save.transact
        _ <- Ns.int(17).i(1).zonedDateTimeSet_?(Option.empty[Set[ZonedDateTime]]).save.transact
        _ <- Ns.int(18).i(1).uuidSet_?(Option.empty[Set[UUID]]).save.transact
        _ <- Ns.int(19).i(1).uriSet_?(Option.empty[Set[URI]]).save.transact
        _ <- Ns.int(20).i(1).byteSet_?(Option.empty[Set[Byte]]).save.transact
        _ <- Ns.int(21).i(1).shortSet_?(Option.empty[Set[Short]]).save.transact
        _ <- Ns.int(22).i(1).charSet_?(Option.empty[Set[Char]]).save.transact

        _ <- Ns.int(1).i(2).stringSet_?(Some(Set.empty[String])).save.transact
        _ <- Ns.int(2).i(2).intSet_?(Some(Set.empty[Int])).save.transact
        _ <- Ns.int(3).i(2).longSet_?(Some(Set.empty[Long])).save.transact
        _ <- Ns.int(4).i(2).floatSet_?(Some(Set.empty[Float])).save.transact
        _ <- Ns.int(5).i(2).doubleSet_?(Some(Set.empty[Double])).save.transact
        _ <- Ns.int(6).i(2).booleanSet_?(Some(Set.empty[Boolean])).save.transact
        _ <- Ns.int(7).i(2).bigIntSet_?(Some(Set.empty[BigInt])).save.transact
        _ <- Ns.int(8).i(2).bigDecimalSet_?(Some(Set.empty[BigDecimal])).save.transact
        _ <- Ns.int(9).i(2).dateSet_?(Some(Set.empty[Date])).save.transact
        _ <- Ns.int(10).i(2).durationSet_?(Some(Set.empty[Duration])).save.transact
        _ <- Ns.int(11).i(2).instantSet_?(Some(Set.empty[Instant])).save.transact
        _ <- Ns.int(12).i(2).localDateSet_?(Some(Set.empty[LocalDate])).save.transact
        _ <- Ns.int(13).i(2).localTimeSet_?(Some(Set.empty[LocalTime])).save.transact
        _ <- Ns.int(14).i(2).localDateTimeSet_?(Some(Set.empty[LocalDateTime])).save.transact
        _ <- Ns.int(15).i(2).offsetTimeSet_?(Some(Set.empty[OffsetTime])).save.transact
        _ <- Ns.int(16).i(2).offsetDateTimeSet_?(Some(Set.empty[OffsetDateTime])).save.transact
        _ <- Ns.int(17).i(2).zonedDateTimeSet_?(Some(Set.empty[ZonedDateTime])).save.transact
        _ <- Ns.int(18).i(2).uuidSet_?(Some(Set.empty[UUID])).save.transact
        _ <- Ns.int(19).i(2).uriSet_?(Some(Set.empty[URI])).save.transact
        _ <- Ns.int(20).i(2).byteSet_?(Some(Set.empty[Byte])).save.transact
        _ <- Ns.int(21).i(2).shortSet_?(Some(Set.empty[Short])).save.transact
        _ <- Ns.int(22).i(2).charSet_?(Some(Set.empty[Char])).save.transact

        _ <- Ns.int(1).i(3).stringSet_?(Some(Set(string1, string2))).save.transact
        _ <- Ns.int(2).i(3).intSet_?(Some(Set(int1, int2))).save.transact
        _ <- Ns.int(3).i(3).longSet_?(Some(Set(long1, long2))).save.transact
        _ <- Ns.int(4).i(3).floatSet_?(Some(Set(float1, float2))).save.transact
        _ <- Ns.int(5).i(3).doubleSet_?(Some(Set(double1, double2))).save.transact
        _ <- Ns.int(6).i(3).booleanSet_?(Some(Set(boolean1, boolean2))).save.transact
        _ <- Ns.int(7).i(3).bigIntSet_?(Some(Set(bigInt1, bigInt2))).save.transact
        _ <- Ns.int(8).i(3).bigDecimalSet_?(Some(Set(bigDecimal1, bigDecimal2))).save.transact
        _ <- Ns.int(9).i(3).dateSet_?(Some(Set(date1, date2))).save.transact
        _ <- Ns.int(10).i(3).durationSet_?(Some(Set(duration1, duration2))).save.transact
        _ <- Ns.int(11).i(3).instantSet_?(Some(Set(instant1, instant2))).save.transact
        _ <- Ns.int(12).i(3).localDateSet_?(Some(Set(localDate1, localDate2))).save.transact
        _ <- Ns.int(13).i(3).localTimeSet_?(Some(Set(localTime1, localTime2))).save.transact
        _ <- Ns.int(14).i(3).localDateTimeSet_?(Some(Set(localDateTime1, localDateTime2))).save.transact
        _ <- Ns.int(15).i(3).offsetTimeSet_?(Some(Set(offsetTime1, offsetTime2))).save.transact
        _ <- Ns.int(16).i(3).offsetDateTimeSet_?(Some(Set(offsetDateTime1, offsetDateTime2))).save.transact
        _ <- Ns.int(17).i(3).zonedDateTimeSet_?(Some(Set(zonedDateTime1, zonedDateTime2))).save.transact
        _ <- Ns.int(18).i(3).uuidSet_?(Some(Set(uuid1, uuid2))).save.transact
        _ <- Ns.int(19).i(3).uriSet_?(Some(Set(uri1, uri2))).save.transact
        _ <- Ns.int(20).i(3).byteSet_?(Some(Set(byte1, byte2))).save.transact
        _ <- Ns.int(21).i(3).shortSet_?(Some(Set(short1, short2))).save.transact
        _ <- Ns.int(22).i(3).charSet_?(Some(Set(char1, char2))).save.transact

        _ <- Ns.int_(1).i.a1.stringSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(string1, string2)))))
        _ <- Ns.int_(2).i.a1.intSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(int1, int2)))))
        _ <- Ns.int_(3).i.a1.longSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(long1, long2)))))
        _ <- Ns.int_(4).i.a1.floatSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(float1, float2)))))
        _ <- Ns.int_(5).i.a1.doubleSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(double1, double2)))))
        _ <- Ns.int_(6).i.a1.booleanSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(boolean1, boolean2)))))
        _ <- Ns.int_(7).i.a1.bigIntSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(bigInt1, bigInt2)))))
        _ <- Ns.int_(8).i.a1.bigDecimalSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(bigDecimal1, bigDecimal2)))))
        _ <- Ns.int_(9).i.a1.dateSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(date1, date2)))))
        _ <- Ns.int_(10).i.a1.durationSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(duration1, duration2)))))
        _ <- Ns.int_(11).i.a1.instantSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(instant1, instant2)))))
        _ <- Ns.int_(12).i.a1.localDateSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localDate1, localDate2)))))
        _ <- Ns.int_(13).i.a1.localTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localTime1, localTime2)))))
        _ <- Ns.int_(14).i.a1.localDateTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localDateTime1, localDateTime2)))))
        _ <- Ns.int_(15).i.a1.offsetTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(offsetTime1, offsetTime2)))))
        _ <- Ns.int_(16).i.a1.offsetDateTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(offsetDateTime1, offsetDateTime2)))))
        _ <- Ns.int_(17).i.a1.zonedDateTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(zonedDateTime1, zonedDateTime2)))))
        _ <- Ns.int_(18).i.a1.uuidSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(uuid1, uuid2)))))
        _ <- Ns.int_(19).i.a1.uriSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(uri1, uri2)))))
        _ <- Ns.int_(20).i.a1.byteSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(byte1, byte2)))))
        _ <- Ns.int_(21).i.a1.shortSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(short1, short2)))))
        _ <- Ns.int_(22).i.a1.charSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(char1, char2)))))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.int(23).i(1).refs_?(Option.empty[Set[Long]]).save.transact
        _ <- Ns.int(23).i(2).refs_?(Some(Set.empty[Long])).save.transact
        _ <- Ns.int(23).i(3).refs_?(Some(Set(r1, r2))).save.transact
        _ <- Ns.int_(23).i.a1.refs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      // Applying value to tacit or mandatory attribute has same effect
      for {
        _ <- Ns.i(1).stringSet_(Set(string1, string2)).save.transact
        _ <- Ns.i(2).intSet_(Set(int1, int2)).save.transact
        _ <- Ns.i(3).longSet_(Set(long1, long2)).save.transact
        _ <- Ns.i(4).floatSet_(Set(float1, float2)).save.transact
        _ <- Ns.i(5).doubleSet_(Set(double1, double2)).save.transact
        _ <- Ns.i(6).booleanSet_(Set(boolean1, boolean2)).save.transact
        _ <- Ns.i(7).bigIntSet_(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(8).bigDecimalSet_(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(9).dateSet_(Set(date1, date2)).save.transact
        _ <- Ns.i(10).durationSet_(Set(duration1, duration2)).save.transact
        _ <- Ns.i(11).instantSet_(Set(instant1, instant2)).save.transact
        _ <- Ns.i(12).localDateSet_(Set(localDate1, localDate2)).save.transact
        _ <- Ns.i(13).localTimeSet_(Set(localTime1, localTime2)).save.transact
        _ <- Ns.i(14).localDateTimeSet_(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(15).offsetTimeSet_(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(16).offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(17).zonedDateTimeSet_(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(18).uuidSet_(Set(uuid1, uuid2)).save.transact
        _ <- Ns.i(19).uriSet_(Set(uri1, uri2)).save.transact
        _ <- Ns.i(20).byteSet_(Set(byte1, byte2)).save.transact
        _ <- Ns.i(21).shortSet_(Set(short1, short2)).save.transact
        _ <- Ns.i(22).charSet_(Set(char1, char2)).save.transact

        _ <- Ns.i.stringSet_.query.get.map(_.head ==> 1)
        _ <- Ns.i.intSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.longSet_.query.get.map(_.head ==> 3)
        _ <- Ns.i.floatSet_.query.get.map(_.head ==> 4)
        _ <- Ns.i.doubleSet_.query.get.map(_.head ==> 5)
        _ <- Ns.i.booleanSet_.query.get.map(_.head ==> 6)
        _ <- Ns.i.bigIntSet_.query.get.map(_.head ==> 7)
        _ <- Ns.i.bigDecimalSet_.query.get.map(_.head ==> 8)
        _ <- Ns.i.dateSet_.query.get.map(_.head ==> 9)
        _ <- Ns.i.durationSet_.query.get.map(_.head ==> 10)
        _ <- Ns.i.instantSet_.query.get.map(_.head ==> 11)
        _ <- Ns.i.localDateSet_.query.get.map(_.head ==> 12)
        _ <- Ns.i.localTimeSet_.query.get.map(_.head ==> 13)
        _ <- Ns.i.localDateTimeSet_.query.get.map(_.head ==> 14)
        _ <- Ns.i.offsetTimeSet_.query.get.map(_.head ==> 15)
        _ <- Ns.i.offsetDateTimeSet_.query.get.map(_.head ==> 16)
        _ <- Ns.i.zonedDateTimeSet_.query.get.map(_.head ==> 17)
        _ <- Ns.i.uuidSet_.query.get.map(_.head ==> 18)
        _ <- Ns.i.uriSet_.query.get.map(_.head ==> 19)
        _ <- Ns.i.byteSet_.query.get.map(_.head ==> 20)
        _ <- Ns.i.shortSet_.query.get.map(_.head ==> 21)
        _ <- Ns.i.charSet_.query.get.map(_.head ==> 22)

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.i(23).refs_(Set(r1, r2)).save.transact
        _ <- Ns.i.refs_.query.get.map(_.head ==> 23)
      } yield ()
    }
  }
}
