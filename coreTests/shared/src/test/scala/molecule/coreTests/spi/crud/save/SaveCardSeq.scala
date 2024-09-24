package molecule.coreTests.spi.crud.save

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuiteBase
import molecule.coreTests.util.Array2List
import utest._

trait SaveCardSeq extends CoreTestSuiteBase with Array2List with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        // Empty Seq of values is ignored
        _ <- Ns.intSeq(List.empty[Int]).save.transact
        _ <- Ns.intSeq.query.get.map(_ ==> List())

        _ <- Ns.stringSeq(List(string1, string2)).save.transact
        _ <- Ns.intSeq(List(int1, int2)).save.transact
        _ <- Ns.longSeq(List(long1, long2)).save.transact
        _ <- Ns.floatSeq(List(float1, float2)).save.transact
        _ <- Ns.doubleSeq(List(double1, double2)).save.transact
        _ <- Ns.booleanSeq(List(boolean0)).save.transact
        _ <- Ns.bigIntSeq(List(bigInt1, bigInt2)).save.transact
        _ <- Ns.bigDecimalSeq(List(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.dateSeq(List(date1, date2)).save.transact
        _ <- Ns.durationSeq(List(duration1, duration2)).save.transact
        _ <- Ns.instantSeq(List(instant1, instant2)).save.transact
        _ <- Ns.localDateSeq(List(localDate1, localDate2)).save.transact
        _ <- Ns.localTimeSeq(List(localTime1, localTime2)).save.transact
        _ <- Ns.localDateTimeSeq(List(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.offsetTimeSeq(List(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.uuidSeq(List(uuid1, uuid2)).save.transact
        _ <- Ns.uriSeq(List(uri1, uri2)).save.transact
        _ <- Ns.byteArray(Array(byte1, byte2)).save.transact // Note Byte Array semantics
        _ <- Ns.shortSeq(List(short1, short2)).save.transact
        _ <- Ns.charSeq(List(char1, char2)).save.transact

        _ <- Ns.stringSeq.query.get.map(_.head ==> List(string1, string2))
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2))
        _ <- Ns.longSeq.query.get.map(_.head ==> List(long1, long2))
        _ <- Ns.floatSeq.query.get.map(_.head ==> List(float1, float2))
        _ <- Ns.doubleSeq.query.get.map(_.head ==> List(double1, double2))
        _ <- Ns.booleanSeq.query.get.map(_.head ==> List(boolean0))
        _ <- Ns.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))
        _ <- Ns.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))
        _ <- Ns.dateSeq.query.get.map(_.head ==> List(date1, date2))
        _ <- Ns.durationSeq.query.get.map(_.head ==> List(duration1, duration2))
        _ <- Ns.instantSeq.query.get.map(_.head ==> List(instant1, instant2))
        _ <- Ns.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))
        _ <- Ns.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))
        _ <- Ns.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))
        _ <- Ns.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))
        _ <- Ns.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))
        _ <- Ns.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))
        _ <- Ns.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))
        _ <- Ns.uriSeq.query.get.map(_.head ==> List(uri1, uri2))
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte1, byte2)) // Note that Bytes are saved in Arrays
        _ <- Ns.shortSeq.query.get.map(_.head ==> List(short1, short2))
        _ <- Ns.charSeq.query.get.map(_.head ==> List(char1, char2))

        // (Refs have Set semantics only)
      } yield ()
    }


    "optional" - types { implicit conn =>
      for {
        // Empty option of Seq of values is ignored
        _ <- Ns.intSeq_?(Option.empty[List[Int]]).save.transact
        _ <- Ns.intSeq.query.get.map(_ ==> List())

        _ <- Ns.int(1).i(1).stringSeq_?(Option.empty[List[String]]).save.transact
        _ <- Ns.int(2).i(1).intSeq_?(Option.empty[List[Int]]).save.transact
        _ <- Ns.int(3).i(1).longSeq_?(Option.empty[List[Long]]).save.transact
        _ <- Ns.int(4).i(1).floatSeq_?(Option.empty[List[Float]]).save.transact
        _ <- Ns.int(5).i(1).doubleSeq_?(Option.empty[List[Double]]).save.transact
        _ <- Ns.int(6).i(1).booleanSeq_?(Option.empty[List[Boolean]]).save.transact
        _ <- Ns.int(7).i(1).bigIntSeq_?(Option.empty[List[BigInt]]).save.transact
        _ <- Ns.int(8).i(1).bigDecimalSeq_?(Option.empty[List[BigDecimal]]).save.transact
        _ <- Ns.int(9).i(1).dateSeq_?(Option.empty[List[Date]]).save.transact
        _ <- Ns.int(10).i(1).durationSeq_?(Option.empty[List[Duration]]).save.transact
        _ <- Ns.int(11).i(1).instantSeq_?(Option.empty[List[Instant]]).save.transact
        _ <- Ns.int(12).i(1).localDateSeq_?(Option.empty[List[LocalDate]]).save.transact
        _ <- Ns.int(13).i(1).localTimeSeq_?(Option.empty[List[LocalTime]]).save.transact
        _ <- Ns.int(14).i(1).localDateTimeSeq_?(Option.empty[List[LocalDateTime]]).save.transact
        _ <- Ns.int(15).i(1).offsetTimeSeq_?(Option.empty[List[OffsetTime]]).save.transact
        _ <- Ns.int(16).i(1).offsetDateTimeSeq_?(Option.empty[List[OffsetDateTime]]).save.transact
        _ <- Ns.int(17).i(1).zonedDateTimeSeq_?(Option.empty[List[ZonedDateTime]]).save.transact
        _ <- Ns.int(18).i(1).uuidSeq_?(Option.empty[List[UUID]]).save.transact
        _ <- Ns.int(19).i(1).uriSeq_?(Option.empty[List[URI]]).save.transact
        _ <- Ns.int(20).i(1).byteArray_?(Option.empty[Array[Byte]]).save.transact
        _ <- Ns.int(21).i(1).shortSeq_?(Option.empty[List[Short]]).save.transact
        _ <- Ns.int(22).i(1).charSeq_?(Option.empty[List[Char]]).save.transact

        _ <- Ns.int(1).i(2).stringSeq_?(Some(List.empty[String])).save.transact
        _ <- Ns.int(2).i(2).intSeq_?(Some(List.empty[Int])).save.transact
        _ <- Ns.int(3).i(2).longSeq_?(Some(List.empty[Long])).save.transact
        _ <- Ns.int(4).i(2).floatSeq_?(Some(List.empty[Float])).save.transact
        _ <- Ns.int(5).i(2).doubleSeq_?(Some(List.empty[Double])).save.transact
        _ <- Ns.int(6).i(2).booleanSeq_?(Some(List.empty[Boolean])).save.transact
        _ <- Ns.int(7).i(2).bigIntSeq_?(Some(List.empty[BigInt])).save.transact
        _ <- Ns.int(8).i(2).bigDecimalSeq_?(Some(List.empty[BigDecimal])).save.transact
        _ <- Ns.int(9).i(2).dateSeq_?(Some(List.empty[Date])).save.transact
        _ <- Ns.int(10).i(2).durationSeq_?(Some(List.empty[Duration])).save.transact
        _ <- Ns.int(11).i(2).instantSeq_?(Some(List.empty[Instant])).save.transact
        _ <- Ns.int(12).i(2).localDateSeq_?(Some(List.empty[LocalDate])).save.transact
        _ <- Ns.int(13).i(2).localTimeSeq_?(Some(List.empty[LocalTime])).save.transact
        _ <- Ns.int(14).i(2).localDateTimeSeq_?(Some(List.empty[LocalDateTime])).save.transact
        _ <- Ns.int(15).i(2).offsetTimeSeq_?(Some(List.empty[OffsetTime])).save.transact
        _ <- Ns.int(16).i(2).offsetDateTimeSeq_?(Some(List.empty[OffsetDateTime])).save.transact
        _ <- Ns.int(17).i(2).zonedDateTimeSeq_?(Some(List.empty[ZonedDateTime])).save.transact
        _ <- Ns.int(18).i(2).uuidSeq_?(Some(List.empty[UUID])).save.transact
        _ <- Ns.int(19).i(2).uriSeq_?(Some(List.empty[URI])).save.transact
        _ <- Ns.int(20).i(2).byteArray_?(Some(Array.empty[Byte])).save.transact
        _ <- Ns.int(21).i(2).shortSeq_?(Some(List.empty[Short])).save.transact
        _ <- Ns.int(22).i(2).charSeq_?(Some(List.empty[Char])).save.transact

        _ <- Ns.int(1).i(3).stringSeq_?(Some(List(string1, string2))).save.transact
        _ <- Ns.int(2).i(3).intSeq_?(Some(List(int1, int2))).save.transact
        _ <- Ns.int(3).i(3).longSeq_?(Some(List(long1, long2))).save.transact
        _ <- Ns.int(4).i(3).floatSeq_?(Some(List(float1, float2))).save.transact
        _ <- Ns.int(5).i(3).doubleSeq_?(Some(List(double1, double2))).save.transact
        _ <- Ns.int(6).i(3).booleanSeq_?(Some(List(boolean1, boolean2))).save.transact
        _ <- Ns.int(7).i(3).bigIntSeq_?(Some(List(bigInt1, bigInt2))).save.transact
        _ <- Ns.int(8).i(3).bigDecimalSeq_?(Some(List(bigDecimal1, bigDecimal2))).save.transact
        _ <- Ns.int(9).i(3).dateSeq_?(Some(List(date1, date2))).save.transact
        _ <- Ns.int(10).i(3).durationSeq_?(Some(List(duration1, duration2))).save.transact
        _ <- Ns.int(11).i(3).instantSeq_?(Some(List(instant1, instant2))).save.transact
        _ <- Ns.int(12).i(3).localDateSeq_?(Some(List(localDate1, localDate2))).save.transact
        _ <- Ns.int(13).i(3).localTimeSeq_?(Some(List(localTime1, localTime2))).save.transact
        _ <- Ns.int(14).i(3).localDateTimeSeq_?(Some(List(localDateTime1, localDateTime2))).save.transact
        _ <- Ns.int(15).i(3).offsetTimeSeq_?(Some(List(offsetTime1, offsetTime2))).save.transact
        _ <- Ns.int(16).i(3).offsetDateTimeSeq_?(Some(List(offsetDateTime1, offsetDateTime2))).save.transact
        _ <- Ns.int(17).i(3).zonedDateTimeSeq_?(Some(List(zonedDateTime1, zonedDateTime2))).save.transact
        _ <- Ns.int(18).i(3).uuidSeq_?(Some(List(uuid1, uuid2))).save.transact
        _ <- Ns.int(19).i(3).uriSeq_?(Some(List(uri1, uri2))).save.transact
        _ <- Ns.int(20).i(3).byteArray_?(Some(Array(byte1, byte2))).save.transact
        _ <- Ns.int(21).i(3).shortSeq_?(Some(List(short1, short2))).save.transact
        _ <- Ns.int(22).i(3).charSeq_?(Some(List(char1, char2))).save.transact

        _ <- Ns.int_(1).i.a1.stringSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(string1, string2)))))
        _ <- Ns.int_(2).i.a1.intSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(int1, int2)))))
        _ <- Ns.int_(3).i.a1.longSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(long1, long2)))))
        _ <- Ns.int_(4).i.a1.floatSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(float1, float2)))))
        _ <- Ns.int_(5).i.a1.doubleSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(double1, double2)))))
        _ <- Ns.int_(6).i.a1.booleanSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(boolean1, boolean2)))))
        _ <- Ns.int_(7).i.a1.bigIntSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(bigInt1, bigInt2)))))
        _ <- Ns.int_(8).i.a1.bigDecimalSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(bigDecimal1, bigDecimal2)))))
        _ <- Ns.int_(9).i.a1.dateSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(date1, date2)))))
        _ <- Ns.int_(10).i.a1.durationSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(duration1, duration2)))))
        _ <- Ns.int_(11).i.a1.instantSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(instant1, instant2)))))
        _ <- Ns.int_(12).i.a1.localDateSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(localDate1, localDate2)))))
        _ <- Ns.int_(13).i.a1.localTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(localTime1, localTime2)))))
        _ <- Ns.int_(14).i.a1.localDateTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(localDateTime1, localDateTime2)))))
        _ <- Ns.int_(15).i.a1.offsetTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(offsetTime1, offsetTime2)))))
        _ <- Ns.int_(16).i.a1.offsetDateTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(offsetDateTime1, offsetDateTime2)))))
        _ <- Ns.int_(17).i.a1.zonedDateTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(zonedDateTime1, zonedDateTime2)))))
        _ <- Ns.int_(18).i.a1.uuidSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(uuid1, uuid2)))))
        _ <- Ns.int_(19).i.a1.uriSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(uri1, uri2)))))
        _ <- Ns.int_(20).i.a1.byteArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(byte1, byte2)))))
        _ <- Ns.int_(21).i.a1.shortSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(short1, short2)))))
        _ <- Ns.int_(22).i.a1.charSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(char1, char2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      // Applying value to tacit or mandatory attribute has same effect
      for {
        _ <- Ns.i(1).stringSeq_(List(string1, string2)).save.transact
        _ <- Ns.i(2).intSeq_(List(int1, int2)).save.transact
        _ <- Ns.i(3).longSeq_(List(long1, long2)).save.transact
        _ <- Ns.i(4).floatSeq_(List(float1, float2)).save.transact
        _ <- Ns.i(5).doubleSeq_(List(double1, double2)).save.transact
        _ <- Ns.i(6).booleanSeq_(List(boolean1, boolean2)).save.transact
        _ <- Ns.i(7).bigIntSeq_(List(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(8).bigDecimalSeq_(List(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(9).dateSeq_(List(date1, date2)).save.transact
        _ <- Ns.i(10).durationSeq_(List(duration1, duration2)).save.transact
        _ <- Ns.i(11).instantSeq_(List(instant1, instant2)).save.transact
        _ <- Ns.i(12).localDateSeq_(List(localDate1, localDate2)).save.transact
        _ <- Ns.i(13).localTimeSeq_(List(localTime1, localTime2)).save.transact
        _ <- Ns.i(14).localDateTimeSeq_(List(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(15).offsetTimeSeq_(List(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(16).offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(17).zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(18).uuidSeq_(List(uuid1, uuid2)).save.transact
        _ <- Ns.i(19).uriSeq_(List(uri1, uri2)).save.transact
        _ <- Ns.i(20).byteArray_(Array(byte1, byte2)).save.transact
        _ <- Ns.i(21).shortSeq_(List(short1, short2)).save.transact
        _ <- Ns.i(22).charSeq_(List(char1, char2)).save.transact

        _ <- Ns.i.stringSeq_.query.get.map(_.head ==> 1)
        _ <- Ns.i.intSeq_.query.get.map(_.head ==> 2)
        _ <- Ns.i.longSeq_.query.get.map(_.head ==> 3)
        _ <- Ns.i.floatSeq_.query.get.map(_.head ==> 4)
        _ <- Ns.i.doubleSeq_.query.get.map(_.head ==> 5)
        _ <- Ns.i.booleanSeq_.query.get.map(_.head ==> 6)
        _ <- Ns.i.bigIntSeq_.query.get.map(_.head ==> 7)
        _ <- Ns.i.bigDecimalSeq_.query.get.map(_.head ==> 8)
        _ <- Ns.i.dateSeq_.query.get.map(_.head ==> 9)
        _ <- Ns.i.durationSeq_.query.get.map(_.head ==> 10)
        _ <- Ns.i.instantSeq_.query.get.map(_.head ==> 11)
        _ <- Ns.i.localDateSeq_.query.get.map(_.head ==> 12)
        _ <- Ns.i.localTimeSeq_.query.get.map(_.head ==> 13)
        _ <- Ns.i.localDateTimeSeq_.query.get.map(_.head ==> 14)
        _ <- Ns.i.offsetTimeSeq_.query.get.map(_.head ==> 15)
        _ <- Ns.i.offsetDateTimeSeq_.query.get.map(_.head ==> 16)
        _ <- Ns.i.zonedDateTimeSeq_.query.get.map(_.head ==> 17)
        _ <- Ns.i.uuidSeq_.query.get.map(_.head ==> 18)
        _ <- Ns.i.uriSeq_.query.get.map(_.head ==> 19)
        _ <- Ns.i.byteArray_.query.get.map(_.head ==> 20)
        _ <- Ns.i.shortSeq_.query.get.map(_.head ==> 21)
        _ <- Ns.i.charSeq_.query.get.map(_.head ==> 22)
      } yield ()
    }
  }
}
