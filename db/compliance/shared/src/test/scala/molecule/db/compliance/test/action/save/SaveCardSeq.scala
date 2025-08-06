package molecule.db.compliance.test.action.save

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import scala.collection.immutable.*
import molecule.core.setup.{MUnit_arrays, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders

case class SaveCardSeq(
  suite: MUnit_arrays,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "mandatory" - types {
    for {
      // Empty Seq of values is ignored
      _ <- Entity.intSeq(List.empty[Int]).save.transact
      _ <- Entity.intSeq.query.get.map(_ ==> List())

      _ <- Entity.stringSeq(List(string1, string2)).save.transact
      _ <- Entity.intSeq(List(int1, int2)).save.transact
      _ <- Entity.longSeq(List(long1, long2)).save.transact
      _ <- Entity.floatSeq(List(float1, float2)).save.transact
      _ <- Entity.doubleSeq(List(double1, double2)).save.transact
      _ <- Entity.booleanSeq(List(boolean0)).save.transact
      _ <- Entity.bigIntSeq(List(bigInt1, bigInt2)).save.transact
      _ <- Entity.bigDecimalSeq(List(bigDecimal1, bigDecimal2)).save.transact
      _ <- Entity.dateSeq(List(date1, date2)).save.transact
      _ <- Entity.durationSeq(List(duration1, duration2)).save.transact
      _ <- Entity.instantSeq(List(instant1, instant2)).save.transact
      _ <- Entity.localDateSeq(List(localDate1, localDate2)).save.transact
      _ <- Entity.localTimeSeq(List(localTime1, localTime2)).save.transact
      _ <- Entity.localDateTimeSeq(List(localDateTime1, localDateTime2)).save.transact
      _ <- Entity.offsetTimeSeq(List(offsetTime1, offsetTime2)).save.transact
      _ <- Entity.offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2)).save.transact
      _ <- Entity.zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2)).save.transact
      _ <- Entity.uuidSeq(List(uuid1, uuid2)).save.transact
      _ <- Entity.uriSeq(List(uri1, uri2)).save.transact
      _ <- Entity.byteArray(Array(byte1, byte2)).save.transact // Note Byte Array semantics
      _ <- Entity.shortSeq(List(short1, short2)).save.transact
      _ <- Entity.charSeq(List(char1, char2)).save.transact

      _ <- Entity.stringSeq.query.get.map(_.head ==> List(string1, string2))
      _ <- Entity.intSeq.query.get.map(_.head ==> List(int1, int2))
      _ <- Entity.longSeq.query.get.map(_.head ==> List(long1, long2))
      _ <- Entity.floatSeq.query.get.map(_.head ==> List(float1, float2))
      _ <- Entity.doubleSeq.query.get.map(_.head ==> List(double1, double2))
      _ <- Entity.booleanSeq.query.get.map(_.head ==> List(boolean0))
      _ <- Entity.bigIntSeq.query.get.map(_.head ==> List(bigInt1, bigInt2))
      _ <- Entity.bigDecimalSeq.query.get.map(_.head ==> List(bigDecimal1, bigDecimal2))
      _ <- Entity.dateSeq.query.get.map(_.head ==> List(date1, date2))
      _ <- Entity.durationSeq.query.get.map(_.head ==> List(duration1, duration2))
      _ <- Entity.instantSeq.query.get.map(_.head ==> List(instant1, instant2))
      _ <- Entity.localDateSeq.query.get.map(_.head ==> List(localDate1, localDate2))
      _ <- Entity.localTimeSeq.query.get.map(_.head ==> List(localTime1, localTime2))
      _ <- Entity.localDateTimeSeq.query.get.map(_.head ==> List(localDateTime1, localDateTime2))
      _ <- Entity.offsetTimeSeq.query.get.map(_.head ==> List(offsetTime1, offsetTime2))
      _ <- Entity.offsetDateTimeSeq.query.get.map(_.head ==> List(offsetDateTime1, offsetDateTime2))
      _ <- Entity.zonedDateTimeSeq.query.get.map(_.head ==> List(zonedDateTime1, zonedDateTime2))
      _ <- Entity.uuidSeq.query.get.map(_.head ==> List(uuid1, uuid2))
      _ <- Entity.uriSeq.query.get.map(_.head ==> List(uri1, uri2))
      _ <- Entity.byteArray.query.get.map(_.head ==> Array(byte1, byte2)) // Note that Bytes are saved in Arrays
      _ <- Entity.shortSeq.query.get.map(_.head ==> List(short1, short2))
      _ <- Entity.charSeq.query.get.map(_.head ==> List(char1, char2))

      // (Refs have Set semantics only)
    } yield ()
  }


  "optional" - types {
    for {
      // Empty option of Seq of values is ignored
      _ <- Entity.intSeq_?(Option.empty[List[Int]]).save.transact
      _ <- Entity.intSeq.query.get.map(_ ==> List())

      _ <- Entity.int(1).i(1).stringSeq_?(Option.empty[List[String]]).save.transact
      _ <- Entity.int(2).i(1).intSeq_?(Option.empty[List[Int]]).save.transact
      _ <- Entity.int(3).i(1).longSeq_?(Option.empty[List[Long]]).save.transact
      _ <- Entity.int(4).i(1).floatSeq_?(Option.empty[List[Float]]).save.transact
      _ <- Entity.int(5).i(1).doubleSeq_?(Option.empty[List[Double]]).save.transact
      _ <- Entity.int(6).i(1).booleanSeq_?(Option.empty[List[Boolean]]).save.transact
      _ <- Entity.int(7).i(1).bigIntSeq_?(Option.empty[List[BigInt]]).save.transact
      _ <- Entity.int(8).i(1).bigDecimalSeq_?(Option.empty[List[BigDecimal]]).save.transact
      _ <- Entity.int(9).i(1).dateSeq_?(Option.empty[List[Date]]).save.transact
      _ <- Entity.int(10).i(1).durationSeq_?(Option.empty[List[Duration]]).save.transact
      _ <- Entity.int(11).i(1).instantSeq_?(Option.empty[List[Instant]]).save.transact
      _ <- Entity.int(12).i(1).localDateSeq_?(Option.empty[List[LocalDate]]).save.transact
      _ <- Entity.int(13).i(1).localTimeSeq_?(Option.empty[List[LocalTime]]).save.transact
      _ <- Entity.int(14).i(1).localDateTimeSeq_?(Option.empty[List[LocalDateTime]]).save.transact
      _ <- Entity.int(15).i(1).offsetTimeSeq_?(Option.empty[List[OffsetTime]]).save.transact
      _ <- Entity.int(16).i(1).offsetDateTimeSeq_?(Option.empty[List[OffsetDateTime]]).save.transact
      _ <- Entity.int(17).i(1).zonedDateTimeSeq_?(Option.empty[List[ZonedDateTime]]).save.transact
      _ <- Entity.int(18).i(1).uuidSeq_?(Option.empty[List[UUID]]).save.transact
      _ <- Entity.int(19).i(1).uriSeq_?(Option.empty[List[URI]]).save.transact
      _ <- Entity.int(20).i(1).byteArray_?(Option.empty[Array[Byte]]).save.transact
      _ <- Entity.int(21).i(1).shortSeq_?(Option.empty[List[Short]]).save.transact
      _ <- Entity.int(22).i(1).charSeq_?(Option.empty[List[Char]]).save.transact

      _ <- Entity.int(1).i(2).stringSeq_?(Some(List.empty[String])).save.transact
      _ <- Entity.int(2).i(2).intSeq_?(Some(List.empty[Int])).save.transact
      _ <- Entity.int(3).i(2).longSeq_?(Some(List.empty[Long])).save.transact
      _ <- Entity.int(4).i(2).floatSeq_?(Some(List.empty[Float])).save.transact
      _ <- Entity.int(5).i(2).doubleSeq_?(Some(List.empty[Double])).save.transact
      _ <- Entity.int(6).i(2).booleanSeq_?(Some(List.empty[Boolean])).save.transact
      _ <- Entity.int(7).i(2).bigIntSeq_?(Some(List.empty[BigInt])).save.transact
      _ <- Entity.int(8).i(2).bigDecimalSeq_?(Some(List.empty[BigDecimal])).save.transact
      _ <- Entity.int(9).i(2).dateSeq_?(Some(List.empty[Date])).save.transact
      _ <- Entity.int(10).i(2).durationSeq_?(Some(List.empty[Duration])).save.transact
      _ <- Entity.int(11).i(2).instantSeq_?(Some(List.empty[Instant])).save.transact
      _ <- Entity.int(12).i(2).localDateSeq_?(Some(List.empty[LocalDate])).save.transact
      _ <- Entity.int(13).i(2).localTimeSeq_?(Some(List.empty[LocalTime])).save.transact
      _ <- Entity.int(14).i(2).localDateTimeSeq_?(Some(List.empty[LocalDateTime])).save.transact
      _ <- Entity.int(15).i(2).offsetTimeSeq_?(Some(List.empty[OffsetTime])).save.transact
      _ <- Entity.int(16).i(2).offsetDateTimeSeq_?(Some(List.empty[OffsetDateTime])).save.transact
      _ <- Entity.int(17).i(2).zonedDateTimeSeq_?(Some(List.empty[ZonedDateTime])).save.transact
      _ <- Entity.int(18).i(2).uuidSeq_?(Some(List.empty[UUID])).save.transact
      _ <- Entity.int(19).i(2).uriSeq_?(Some(List.empty[URI])).save.transact
      _ <- Entity.int(20).i(2).byteArray_?(Some(Array.empty[Byte])).save.transact
      _ <- Entity.int(21).i(2).shortSeq_?(Some(List.empty[Short])).save.transact
      _ <- Entity.int(22).i(2).charSeq_?(Some(List.empty[Char])).save.transact

      _ <- Entity.int(1).i(3).stringSeq_?(Some(List(string1, string2))).save.transact
      _ <- Entity.int(2).i(3).intSeq_?(Some(List(int1, int2))).save.transact
      _ <- Entity.int(3).i(3).longSeq_?(Some(List(long1, long2))).save.transact
      _ <- Entity.int(4).i(3).floatSeq_?(Some(List(float1, float2))).save.transact
      _ <- Entity.int(5).i(3).doubleSeq_?(Some(List(double1, double2))).save.transact
      _ <- Entity.int(6).i(3).booleanSeq_?(Some(List(boolean1, boolean2))).save.transact
      _ <- Entity.int(7).i(3).bigIntSeq_?(Some(List(bigInt1, bigInt2))).save.transact
      _ <- Entity.int(8).i(3).bigDecimalSeq_?(Some(List(bigDecimal1, bigDecimal2))).save.transact
      _ <- Entity.int(9).i(3).dateSeq_?(Some(List(date1, date2))).save.transact
      _ <- Entity.int(10).i(3).durationSeq_?(Some(List(duration1, duration2))).save.transact
      _ <- Entity.int(11).i(3).instantSeq_?(Some(List(instant1, instant2))).save.transact
      _ <- Entity.int(12).i(3).localDateSeq_?(Some(List(localDate1, localDate2))).save.transact
      _ <- Entity.int(13).i(3).localTimeSeq_?(Some(List(localTime1, localTime2))).save.transact
      _ <- Entity.int(14).i(3).localDateTimeSeq_?(Some(List(localDateTime1, localDateTime2))).save.transact
      _ <- Entity.int(15).i(3).offsetTimeSeq_?(Some(List(offsetTime1, offsetTime2))).save.transact
      _ <- Entity.int(16).i(3).offsetDateTimeSeq_?(Some(List(offsetDateTime1, offsetDateTime2))).save.transact
      _ <- Entity.int(17).i(3).zonedDateTimeSeq_?(Some(List(zonedDateTime1, zonedDateTime2))).save.transact
      _ <- Entity.int(18).i(3).uuidSeq_?(Some(List(uuid1, uuid2))).save.transact
      _ <- Entity.int(19).i(3).uriSeq_?(Some(List(uri1, uri2))).save.transact
      _ <- Entity.int(20).i(3).byteArray_?(Some(Array(byte1, byte2))).save.transact
      _ <- Entity.int(21).i(3).shortSeq_?(Some(List(short1, short2))).save.transact
      _ <- Entity.int(22).i(3).charSeq_?(Some(List(char1, char2))).save.transact

      _ <- Entity.int_(1).i.a1.stringSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(string1, string2)))))
      _ <- Entity.int_(2).i.a1.intSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(int1, int2)))))
      _ <- Entity.int_(3).i.a1.longSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(long1, long2)))))
      _ <- Entity.int_(4).i.a1.floatSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(float1, float2)))))
      _ <- Entity.int_(5).i.a1.doubleSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(double1, double2)))))
      _ <- Entity.int_(6).i.a1.booleanSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(boolean1, boolean2)))))
      _ <- Entity.int_(7).i.a1.bigIntSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(bigInt1, bigInt2)))))
      _ <- Entity.int_(8).i.a1.bigDecimalSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(bigDecimal1, bigDecimal2)))))
      _ <- Entity.int_(9).i.a1.dateSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(date1, date2)))))
      _ <- Entity.int_(10).i.a1.durationSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(duration1, duration2)))))
      _ <- Entity.int_(11).i.a1.instantSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(instant1, instant2)))))
      _ <- Entity.int_(12).i.a1.localDateSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(localDate1, localDate2)))))
      _ <- Entity.int_(13).i.a1.localTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(localTime1, localTime2)))))
      _ <- Entity.int_(14).i.a1.localDateTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(localDateTime1, localDateTime2)))))
      _ <- Entity.int_(15).i.a1.offsetTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(offsetTime1, offsetTime2)))))
      _ <- Entity.int_(16).i.a1.offsetDateTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(offsetDateTime1, offsetDateTime2)))))
      _ <- Entity.int_(17).i.a1.zonedDateTimeSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(zonedDateTime1, zonedDateTime2)))))
      _ <- Entity.int_(18).i.a1.uuidSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(uuid1, uuid2)))))
      _ <- Entity.int_(19).i.a1.uriSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(uri1, uri2)))))
      _ <- Entity.int_(20).i.a1.byteArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(byte1, byte2)))))
      _ <- Entity.int_(21).i.a1.shortSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(short1, short2)))))
      _ <- Entity.int_(22).i.a1.charSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(char1, char2)))))
    } yield ()
  }


  "Tacit" - types {
    // Applying value to tacit or mandatory attribute has same effect
    for {
      _ <- Entity.i(1).stringSeq_(List(string1, string2)).save.transact
      _ <- Entity.i(2).intSeq_(List(int1, int2)).save.transact
      _ <- Entity.i(3).longSeq_(List(long1, long2)).save.transact
      _ <- Entity.i(4).floatSeq_(List(float1, float2)).save.transact
      _ <- Entity.i(5).doubleSeq_(List(double1, double2)).save.transact
      _ <- Entity.i(6).booleanSeq_(List(boolean1, boolean2)).save.transact
      _ <- Entity.i(7).bigIntSeq_(List(bigInt1, bigInt2)).save.transact
      _ <- Entity.i(8).bigDecimalSeq_(List(bigDecimal1, bigDecimal2)).save.transact
      _ <- Entity.i(9).dateSeq_(List(date1, date2)).save.transact
      _ <- Entity.i(10).durationSeq_(List(duration1, duration2)).save.transact
      _ <- Entity.i(11).instantSeq_(List(instant1, instant2)).save.transact
      _ <- Entity.i(12).localDateSeq_(List(localDate1, localDate2)).save.transact
      _ <- Entity.i(13).localTimeSeq_(List(localTime1, localTime2)).save.transact
      _ <- Entity.i(14).localDateTimeSeq_(List(localDateTime1, localDateTime2)).save.transact
      _ <- Entity.i(15).offsetTimeSeq_(List(offsetTime1, offsetTime2)).save.transact
      _ <- Entity.i(16).offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2)).save.transact
      _ <- Entity.i(17).zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2)).save.transact
      _ <- Entity.i(18).uuidSeq_(List(uuid1, uuid2)).save.transact
      _ <- Entity.i(19).uriSeq_(List(uri1, uri2)).save.transact
      _ <- Entity.i(20).byteArray_(Array(byte1, byte2)).save.transact
      _ <- Entity.i(21).shortSeq_(List(short1, short2)).save.transact
      _ <- Entity.i(22).charSeq_(List(char1, char2)).save.transact

      _ <- Entity.i.stringSeq_.query.get.map(_.head ==> 1)
      _ <- Entity.i.intSeq_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longSeq_.query.get.map(_.head ==> 3)
      _ <- Entity.i.floatSeq_.query.get.map(_.head ==> 4)
      _ <- Entity.i.doubleSeq_.query.get.map(_.head ==> 5)
      _ <- Entity.i.booleanSeq_.query.get.map(_.head ==> 6)
      _ <- Entity.i.bigIntSeq_.query.get.map(_.head ==> 7)
      _ <- Entity.i.bigDecimalSeq_.query.get.map(_.head ==> 8)
      _ <- Entity.i.dateSeq_.query.get.map(_.head ==> 9)
      _ <- Entity.i.durationSeq_.query.get.map(_.head ==> 10)
      _ <- Entity.i.instantSeq_.query.get.map(_.head ==> 11)
      _ <- Entity.i.localDateSeq_.query.get.map(_.head ==> 12)
      _ <- Entity.i.localTimeSeq_.query.get.map(_.head ==> 13)
      _ <- Entity.i.localDateTimeSeq_.query.get.map(_.head ==> 14)
      _ <- Entity.i.offsetTimeSeq_.query.get.map(_.head ==> 15)
      _ <- Entity.i.offsetDateTimeSeq_.query.get.map(_.head ==> 16)
      _ <- Entity.i.zonedDateTimeSeq_.query.get.map(_.head ==> 17)
      _ <- Entity.i.uuidSeq_.query.get.map(_.head ==> 18)
      _ <- Entity.i.uriSeq_.query.get.map(_.head ==> 19)
      _ <- Entity.i.byteArray_.query.get.map(_.head ==> 20)
      _ <- Entity.i.shortSeq_.query.get.map(_.head ==> 21)
      _ <- Entity.i.charSeq_.query.get.map(_.head ==> 22)
    } yield ()
  }
}
