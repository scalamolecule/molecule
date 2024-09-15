package molecule.coreTests.spi.crud.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.coreTests.util.Array2List
import utest._
import scala.concurrent.Future

trait InsertCardSeq extends CoreTestSuiteBase with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        // No Ns.intSeq inserted yet
        _ <- Ns.intSeq.query.get.map(_ ==> Nil)

        // Inserting empty list/set of values is ignored
        // (See InsertSemantics for further details)
        _ <- Ns.intSeq.insert(List.empty[List[Int]]).transact
        _ <- Ns.intSeq.insert(List(List.empty[Int])).transact
        _ <- Ns.intSeq.query.get.map(_ ==> List())

        _ <- Ns.i.stringSeq.insert(1, List(string1, string2)).transact
        _ <- Ns.i.intSeq.insert(1, List(int1, int2)).transact
        _ <- Ns.i.longSeq.insert(1, List(long1, long2)).transact
        _ <- Ns.i.floatSeq.insert(1, List(float1, float2)).transact
        _ <- Ns.i.doubleSeq.insert(1, List(double1, double2)).transact
        _ <- Ns.i.booleanSeq.insert(1, List(boolean0)).transact
        _ <- Ns.i.bigIntSeq.insert(1, List(bigInt1, bigInt2)).transact
        _ <- Ns.i.bigDecimalSeq.insert(1, List(bigDecimal1, bigDecimal2)).transact
        _ <- Ns.i.dateSeq.insert(1, List(date1, date2)).transact
        _ <- Ns.i.durationSeq.insert(1, List(duration1, duration2)).transact
        _ <- Ns.i.instantSeq.insert(1, List(instant1, instant2)).transact
        _ <- Ns.i.localDateSeq.insert(1, List(localDate1, localDate2)).transact
        _ <- Ns.i.localTimeSeq.insert(1, List(localTime1, localTime2)).transact
        _ <- Ns.i.localDateTimeSeq.insert(1, List(localDateTime1, localDateTime2)).transact
        _ <- Ns.i.offsetTimeSeq.insert(1, List(offsetTime1, offsetTime2)).transact
        _ <- Ns.i.offsetDateTimeSeq.insert(1, List(offsetDateTime1, offsetDateTime2)).transact
        _ <- Ns.i.zonedDateTimeSeq.insert(1, List(zonedDateTime1, zonedDateTime2)).transact
        _ <- Ns.i.uuidSeq.insert(1, List(uuid1, uuid2)).transact
        _ <- Ns.i.uriSeq.insert(1, List(uri1, uri2)).transact
        _ <- Ns.i.byteArray.insert(1, Array(byte1, byte2)).transact
        _ <- Ns.i.shortSeq.insert(1, List(short1, short2)).transact
        _ <- Ns.i.charSeq.insert(1, List(char1, char2)).transact

        _ <- Ns.i.stringSeq.query.get.map(_ ==> List((1, List(string1, string2))))
        _ <- Ns.i.intSeq.query.get.map(_ ==> List((1, List(int1, int2))))
        _ <- Ns.i.longSeq.query.get.map(_ ==> List((1, List(long1, long2))))
        _ <- Ns.i.floatSeq.query.get.map(_ ==> List((1, List(float1, float2))))
        _ <- Ns.i.doubleSeq.query.get.map(_ ==> List((1, List(double1, double2))))
        _ <- Ns.i.booleanSeq.query.get.map(_ ==> List((1, List(boolean0))))
        _ <- Ns.i.bigIntSeq.query.get.map(_ ==> List((1, List(bigInt1, bigInt2))))
        _ <- Ns.i.bigDecimalSeq.query.get.map(_ ==> List((1, List(bigDecimal1, bigDecimal2))))
        _ <- Ns.i.dateSeq.query.get.map(_ ==> List((1, List(date1, date2))))
        _ <- Ns.i.durationSeq.query.get.map(_ ==> List((1, List(duration1, duration2))))
        _ <- Ns.i.instantSeq.query.get.map(_ ==> List((1, List(instant1, instant2))))
        _ <- Ns.i.localDateSeq.query.get.map(_ ==> List((1, List(localDate1, localDate2))))
        _ <- Ns.i.localTimeSeq.query.get.map(_ ==> List((1, List(localTime1, localTime2))))
        _ <- Ns.i.localDateTimeSeq.query.get.map(_ ==> List((1, List(localDateTime1, localDateTime2))))
        _ <- Ns.i.offsetTimeSeq.query.get.map(_ ==> List((1, List(offsetTime1, offsetTime2))))
        _ <- Ns.i.offsetDateTimeSeq.query.get.map(_ ==> List((1, List(offsetDateTime1, offsetDateTime2))))
        _ <- Ns.i.zonedDateTimeSeq.query.get.map(_ ==> List((1, List(zonedDateTime1, zonedDateTime2))))
        _ <- Ns.i.uuidSeq.query.get.map(_ ==> List((1, List(uuid1, uuid2))))
        _ <- Ns.i.uriSeq.query.get.map(_ ==> List((1, List(uri1, uri2))))
        _ <- Ns.i.byteArray.query.get.map(_ ==> List((1, Array(byte1, byte2))))
        _ <- Ns.i.shortSeq.query.get.map(_ ==> List((1, List(short1, short2))))
        _ <- Ns.i.charSeq.query.get.map(_ ==> List((1, List(char1, char2))))

        // (Refs have Set semantics only)
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        _ <- Ns.int.i.stringSeq_?.insert(1, 1, Option.empty[List[String]]).transact
        _ <- Ns.int.i.intSeq_?.insert(2, 1, Option.empty[List[Int]]).transact
        _ <- Ns.int.i.longSeq_?.insert(3, 1, Option.empty[List[Long]]).transact
        _ <- Ns.int.i.floatSeq_?.insert(4, 1, Option.empty[List[Float]]).transact
        _ <- Ns.int.i.doubleSeq_?.insert(5, 1, Option.empty[List[Double]]).transact
        _ <- Ns.int.i.booleanSeq_?.insert(6, 1, Option.empty[List[Boolean]]).transact
        _ <- Ns.int.i.bigIntSeq_?.insert(7, 1, Option.empty[List[BigInt]]).transact
        _ <- Ns.int.i.bigDecimalSeq_?.insert(8, 1, Option.empty[List[BigDecimal]]).transact
        _ <- Ns.int.i.dateSeq_?.insert(9, 1, Option.empty[List[Date]]).transact
        _ <- Ns.int.i.durationSeq_?.insert(10, 1, Option.empty[List[Duration]]).transact
        _ <- Ns.int.i.instantSeq_?.insert(11, 1, Option.empty[List[Instant]]).transact
        _ <- Ns.int.i.localDateSeq_?.insert(12, 1, Option.empty[List[LocalDate]]).transact
        _ <- Ns.int.i.localTimeSeq_?.insert(13, 1, Option.empty[List[LocalTime]]).transact
        _ <- Ns.int.i.localDateTimeSeq_?.insert(14, 1, Option.empty[List[LocalDateTime]]).transact
        _ <- Ns.int.i.offsetTimeSeq_?.insert(15, 1, Option.empty[List[OffsetTime]]).transact
        _ <- Ns.int.i.offsetDateTimeSeq_?.insert(16, 1, Option.empty[List[OffsetDateTime]]).transact
        _ <- Ns.int.i.zonedDateTimeSeq_?.insert(17, 1, Option.empty[List[ZonedDateTime]]).transact
        _ <- Ns.int.i.uuidSeq_?.insert(18, 1, Option.empty[List[UUID]]).transact
        _ <- Ns.int.i.uriSeq_?.insert(19, 1, Option.empty[List[URI]]).transact
        _ <- Ns.int.i.byteArray_?.insert(20, 1, Option.empty[Array[Byte]]).transact
        _ <- Ns.int.i.shortSeq_?.insert(21, 1, Option.empty[List[Short]]).transact
        _ <- Ns.int.i.charSeq_?.insert(22, 1, Option.empty[List[Char]]).transact

        _ <- Ns.int.i.stringSeq_?.insert(1, 2, Some(List.empty[String])).transact
        _ <- Ns.int.i.intSeq_?.insert(2, 2, Some(List.empty[Int])).transact
        _ <- Ns.int.i.longSeq_?.insert(3, 2, Some(List.empty[Long])).transact
        _ <- Ns.int.i.floatSeq_?.insert(4, 2, Some(List.empty[Float])).transact
        _ <- Ns.int.i.doubleSeq_?.insert(5, 2, Some(List.empty[Double])).transact
        _ <- Ns.int.i.booleanSeq_?.insert(6, 2, Some(List.empty[Boolean])).transact
        _ <- Ns.int.i.bigIntSeq_?.insert(7, 2, Some(List.empty[BigInt])).transact
        _ <- Ns.int.i.bigDecimalSeq_?.insert(8, 2, Some(List.empty[BigDecimal])).transact
        _ <- Ns.int.i.dateSeq_?.insert(9, 2, Some(List.empty[Date])).transact
        _ <- Ns.int.i.durationSeq_?.insert(10, 2, Some(List.empty[Duration])).transact
        _ <- Ns.int.i.instantSeq_?.insert(11, 2, Some(List.empty[Instant])).transact
        _ <- Ns.int.i.localDateSeq_?.insert(12, 2, Some(List.empty[LocalDate])).transact
        _ <- Ns.int.i.localTimeSeq_?.insert(13, 2, Some(List.empty[LocalTime])).transact
        _ <- Ns.int.i.localDateTimeSeq_?.insert(14, 2, Some(List.empty[LocalDateTime])).transact
        _ <- Ns.int.i.offsetTimeSeq_?.insert(15, 2, Some(List.empty[OffsetTime])).transact
        _ <- Ns.int.i.offsetDateTimeSeq_?.insert(16, 2, Some(List.empty[OffsetDateTime])).transact
        _ <- Ns.int.i.zonedDateTimeSeq_?.insert(17, 2, Some(List.empty[ZonedDateTime])).transact
        _ <- Ns.int.i.uuidSeq_?.insert(18, 2, Some(List.empty[UUID])).transact
        _ <- Ns.int.i.uriSeq_?.insert(19, 2, Some(List.empty[URI])).transact
        _ <- Ns.int.i.byteArray_?.insert(20, 2, Some(Array.empty[Byte])).transact
        _ <- Ns.int.i.shortSeq_?.insert(21, 2, Some(List.empty[Short])).transact
        _ <- Ns.int.i.charSeq_?.insert(22, 2, Some(List.empty[Char])).transact

        _ <- Ns.int.i.stringSeq_?.insert(1, 3, Some(List(string1, string2))).transact
        _ <- Ns.int.i.intSeq_?.insert(2, 3, Some(List(int1, int2))).transact
        _ <- Ns.int.i.longSeq_?.insert(3, 3, Some(List(long1, long2))).transact
        _ <- Ns.int.i.floatSeq_?.insert(4, 3, Some(List(float1, float2))).transact
        _ <- Ns.int.i.doubleSeq_?.insert(5, 3, Some(List(double1, double2))).transact
        _ <- Ns.int.i.booleanSeq_?.insert(6, 3, Some(List(boolean1, boolean2))).transact
        _ <- Ns.int.i.bigIntSeq_?.insert(7, 3, Some(List(bigInt1, bigInt2))).transact
        _ <- Ns.int.i.bigDecimalSeq_?.insert(8, 3, Some(List(bigDecimal1, bigDecimal2))).transact
        _ <- Ns.int.i.dateSeq_?.insert(9, 3, Some(List(date1, date2))).transact
        _ <- Ns.int.i.durationSeq_?.insert(10, 3, Some(List(duration1, duration2))).transact
        _ <- Ns.int.i.instantSeq_?.insert(11, 3, Some(List(instant1, instant2))).transact
        _ <- Ns.int.i.localDateSeq_?.insert(12, 3, Some(List(localDate1, localDate2))).transact
        _ <- Ns.int.i.localTimeSeq_?.insert(13, 3, Some(List(localTime1, localTime2))).transact
        _ <- Ns.int.i.localDateTimeSeq_?.insert(14, 3, Some(List(localDateTime1, localDateTime2))).transact
        _ <- Ns.int.i.offsetTimeSeq_?.insert(15, 3, Some(List(offsetTime1, offsetTime2))).transact
        _ <- Ns.int.i.offsetDateTimeSeq_?.insert(16, 3, Some(List(offsetDateTime1, offsetDateTime2))).transact
        _ <- Ns.int.i.zonedDateTimeSeq_?.insert(17, 3, Some(List(zonedDateTime1, zonedDateTime2))).transact
        _ <- Ns.int.i.uuidSeq_?.insert(18, 3, Some(List(uuid1, uuid2))).transact
        _ <- Ns.int.i.uriSeq_?.insert(19, 3, Some(List(uri1, uri2))).transact
        _ <- Ns.int.i.byteArray_?.insert(20, 3, Some(Array(byte1, byte2))).transact
        _ <- Ns.int.i.shortSeq_?.insert(21, 3, Some(List(short1, short2))).transact
        _ <- Ns.int.i.charSeq_?.insert(22, 3, Some(List(char1, char2))).transact

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
      for {
        // Can't insert tacit attributes
        _ <- Future(compileError("Ns.i.stringSeq_.insert(1, List(string1))"))
      } yield ()
    }
  }
}
