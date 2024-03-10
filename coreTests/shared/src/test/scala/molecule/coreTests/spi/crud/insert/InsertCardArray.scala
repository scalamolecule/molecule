package molecule.coreTests.spi.crud.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._
import scala.concurrent.Future

trait InsertCardArray extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        // No Ns.ii inserted yet
        _ <- Ns.ii.query.get.map(_ ==> Nil)

        // Inserting empty list/set of values is ignored
        // (See InsertSemantic for further details)
        _ <- Ns.ii.insert(Seq.empty[Set[Int]]).transact
        _ <- Ns.ii.insert(Seq(Set.empty[Int])).transact
        _ <- Ns.ii.query.get.map(_ ==> List())

        _ <- Ns.i.stringArray.insert(1, Array(string1, string2)).transact
        _ <- Ns.i.intArray.insert(1, Array(int1, int2)).transact
        _ <- Ns.i.longArray.insert(1, Array(long1, long2)).transact
        _ <- Ns.i.floatArray.insert(1, Array(float1, float2)).transact
        _ <- Ns.i.doubleArray.insert(1, Array(double1, double2)).transact
        _ <- Ns.i.booleanArray.insert(1, Array(boolean0)).transact
        _ <- Ns.i.bigIntArray.insert(1, Array(bigInt1, bigInt2)).transact
        _ <- Ns.i.bigDecimalArray.insert(1, Array(bigDecimal1, bigDecimal2)).transact
        _ <- Ns.i.dateArray.insert(1, Array(date1, date2)).transact
        _ <- Ns.i.durationArray.insert(1, Array(duration1, duration2)).transact
        _ <- Ns.i.instantArray.insert(1, Array(instant1, instant2)).transact
        _ <- Ns.i.localDateArray.insert(1, Array(localDate1, localDate2)).transact
        _ <- Ns.i.localTimeArray.insert(1, Array(localTime1, localTime2)).transact
        _ <- Ns.i.localDateTimeArray.insert(1, Array(localDateTime1, localDateTime2)).transact
        _ <- Ns.i.offsetTimeArray.insert(1, Array(offsetTime1, offsetTime2)).transact
        _ <- Ns.i.offsetDateTimeArray.insert(1, Array(offsetDateTime1, offsetDateTime2)).transact
        _ <- Ns.i.zonedDateTimeArray.insert(1, Array(zonedDateTime1, zonedDateTime2)).transact
        _ <- Ns.i.uuidArray.insert(1, Array(uuid1, uuid2)).transact
        _ <- Ns.i.uriArray.insert(1, Array(uri1, uri2)).transact
        _ <- Ns.i.byteArray.insert(1, Array(byte1, byte2)).transact
        _ <- Ns.i.shortArray.insert(1, Array(short1, short2)).transact
        _ <- Ns.i.charArray.insert(1, Array(char1, char2)).transact

        _ <- Ns.i.stringArray.query.get.map(_ ==> List((1, Array(string1, string2))))
        _ <- Ns.i.intArray.query.get.map(_ ==> List((1, Array(int1, int2))))
        _ <- Ns.i.longArray.query.get.map(_ ==> List((1, Array(long1, long2))))
        _ <- Ns.i.floatArray.query.get.map(_ ==> List((1, Array(float1, float2))))
        _ <- Ns.i.doubleArray.query.get.map(_ ==> List((1, Array(double1, double2))))
        _ <- Ns.i.booleanArray.query.get.map(_ ==> List((1, Array(boolean0))))
        _ <- Ns.i.bigIntArray.query.get.map(_ ==> List((1, Array(bigInt1, bigInt2))))
        _ <- Ns.i.bigDecimalArray.query.get.map(_ ==> List((1, Array(bigDecimal1, bigDecimal2))))
        _ <- Ns.i.dateArray.query.get.map(_ ==> List((1, Array(date1, date2))))
        _ <- Ns.i.durationArray.query.get.map(_ ==> List((1, Array(duration1, duration2))))
        _ <- Ns.i.instantArray.query.get.map(_ ==> List((1, Array(instant1, instant2))))
        _ <- Ns.i.localDateArray.query.get.map(_ ==> List((1, Array(localDate1, localDate2))))
        _ <- Ns.i.localTimeArray.query.get.map(_ ==> List((1, Array(localTime1, localTime2))))
        _ <- Ns.i.localDateTimeArray.query.get.map(_ ==> List((1, Array(localDateTime1, localDateTime2))))
        _ <- Ns.i.offsetTimeArray.query.get.map(_ ==> List((1, Array(offsetTime1, offsetTime2))))
        _ <- Ns.i.offsetDateTimeArray.query.get.map(_ ==> List((1, Array(offsetDateTime1, offsetDateTime2))))
        _ <- Ns.i.zonedDateTimeArray.query.get.map(_ ==> List((1, Array(zonedDateTime1, zonedDateTime2))))
        _ <- Ns.i.uuidArray.query.get.map(_ ==> List((1, Array(uuid1, uuid2))))
        _ <- Ns.i.uriArray.query.get.map(_ ==> List((1, Array(uri1, uri2))))
        _ <- Ns.i.byteArray.query.get.map(_ ==> List((1, Array(byte1, byte2))))
        _ <- Ns.i.shortArray.query.get.map(_ ==> List((1, Array(short1, short2))))
        _ <- Ns.i.charArray.query.get.map(_ ==> List((1, Array(char1, char2))))

        // (Refs have Set semantics only)
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        _ <- Ns.int.i.stringArray_?.insert(1, 1, Option.empty[Array[String]]).transact
        _ <- Ns.int.i.intArray_?.insert(2, 1, Option.empty[Array[Int]]).transact
        _ <- Ns.int.i.longArray_?.insert(3, 1, Option.empty[Array[Long]]).transact
        _ <- Ns.int.i.floatArray_?.insert(4, 1, Option.empty[Array[Float]]).transact
        _ <- Ns.int.i.doubleArray_?.insert(5, 1, Option.empty[Array[Double]]).transact
        _ <- Ns.int.i.booleanArray_?.insert(6, 1, Option.empty[Array[Boolean]]).transact
        _ <- Ns.int.i.bigIntArray_?.insert(7, 1, Option.empty[Array[BigInt]]).transact
        _ <- Ns.int.i.bigDecimalArray_?.insert(8, 1, Option.empty[Array[BigDecimal]]).transact
        _ <- Ns.int.i.dateArray_?.insert(9, 1, Option.empty[Array[Date]]).transact
        _ <- Ns.int.i.durationArray_?.insert(10, 1, Option.empty[Array[Duration]]).transact
        _ <- Ns.int.i.instantArray_?.insert(11, 1, Option.empty[Array[Instant]]).transact
        _ <- Ns.int.i.localDateArray_?.insert(12, 1, Option.empty[Array[LocalDate]]).transact
        _ <- Ns.int.i.localTimeArray_?.insert(13, 1, Option.empty[Array[LocalTime]]).transact
        _ <- Ns.int.i.localDateTimeArray_?.insert(14, 1, Option.empty[Array[LocalDateTime]]).transact
        _ <- Ns.int.i.offsetTimeArray_?.insert(15, 1, Option.empty[Array[OffsetTime]]).transact
        _ <- Ns.int.i.offsetDateTimeArray_?.insert(16, 1, Option.empty[Array[OffsetDateTime]]).transact
        _ <- Ns.int.i.zonedDateTimeArray_?.insert(17, 1, Option.empty[Array[ZonedDateTime]]).transact
        _ <- Ns.int.i.uuidArray_?.insert(18, 1, Option.empty[Array[UUID]]).transact
        _ <- Ns.int.i.uriArray_?.insert(19, 1, Option.empty[Array[URI]]).transact
        _ <- Ns.int.i.byteArray_?.insert(20, 1, Option.empty[Array[Byte]]).transact
        _ <- Ns.int.i.shortArray_?.insert(21, 1, Option.empty[Array[Short]]).transact
        _ <- Ns.int.i.charArray_?.insert(22, 1, Option.empty[Array[Char]]).transact

        _ <- Ns.int.i.stringArray_?.insert(1, 2, Some(Array.empty[String])).transact
        _ <- Ns.int.i.intArray_?.insert(2, 2, Some(Array.empty[Int])).transact
        _ <- Ns.int.i.longArray_?.insert(3, 2, Some(Array.empty[Long])).transact
        _ <- Ns.int.i.floatArray_?.insert(4, 2, Some(Array.empty[Float])).transact
        _ <- Ns.int.i.doubleArray_?.insert(5, 2, Some(Array.empty[Double])).transact
        _ <- Ns.int.i.booleanArray_?.insert(6, 2, Some(Array.empty[Boolean])).transact
        _ <- Ns.int.i.bigIntArray_?.insert(7, 2, Some(Array.empty[BigInt])).transact
        _ <- Ns.int.i.bigDecimalArray_?.insert(8, 2, Some(Array.empty[BigDecimal])).transact
        _ <- Ns.int.i.dateArray_?.insert(9, 2, Some(Array.empty[Date])).transact
        _ <- Ns.int.i.durationArray_?.insert(10, 2, Some(Array.empty[Duration])).transact
        _ <- Ns.int.i.instantArray_?.insert(11, 2, Some(Array.empty[Instant])).transact
        _ <- Ns.int.i.localDateArray_?.insert(12, 2, Some(Array.empty[LocalDate])).transact
        _ <- Ns.int.i.localTimeArray_?.insert(13, 2, Some(Array.empty[LocalTime])).transact
        _ <- Ns.int.i.localDateTimeArray_?.insert(14, 2, Some(Array.empty[LocalDateTime])).transact
        _ <- Ns.int.i.offsetTimeArray_?.insert(15, 2, Some(Array.empty[OffsetTime])).transact
        _ <- Ns.int.i.offsetDateTimeArray_?.insert(16, 2, Some(Array.empty[OffsetDateTime])).transact
        _ <- Ns.int.i.zonedDateTimeArray_?.insert(17, 2, Some(Array.empty[ZonedDateTime])).transact
        _ <- Ns.int.i.uuidArray_?.insert(18, 2, Some(Array.empty[UUID])).transact
        _ <- Ns.int.i.uriArray_?.insert(19, 2, Some(Array.empty[URI])).transact
        _ <- Ns.int.i.byteArray_?.insert(20, 2, Some(Array.empty[Byte])).transact
        _ <- Ns.int.i.shortArray_?.insert(21, 2, Some(Array.empty[Short])).transact
        _ <- Ns.int.i.charArray_?.insert(22, 2, Some(Array.empty[Char])).transact

        _ <- Ns.int.i.stringArray_?.insert(1, 3, Some(Array(string1, string2))).transact
        _ <- Ns.int.i.intArray_?.insert(2, 3, Some(Array(int1, int2))).transact
        _ <- Ns.int.i.longArray_?.insert(3, 3, Some(Array(long1, long2))).transact
        _ <- Ns.int.i.floatArray_?.insert(4, 3, Some(Array(float1, float2))).transact
        _ <- Ns.int.i.doubleArray_?.insert(5, 3, Some(Array(double1, double2))).transact
        _ <- Ns.int.i.booleanArray_?.insert(6, 3, Some(Array(boolean1, boolean2))).transact
        _ <- Ns.int.i.bigIntArray_?.insert(7, 3, Some(Array(bigInt1, bigInt2))).transact
        _ <- Ns.int.i.bigDecimalArray_?.insert(8, 3, Some(Array(bigDecimal1, bigDecimal2))).transact
        _ <- Ns.int.i.dateArray_?.insert(9, 3, Some(Array(date1, date2))).transact
        _ <- Ns.int.i.durationArray_?.insert(10, 3, Some(Array(duration1, duration2))).transact
        _ <- Ns.int.i.instantArray_?.insert(11, 3, Some(Array(instant1, instant2))).transact
        _ <- Ns.int.i.localDateArray_?.insert(12, 3, Some(Array(localDate1, localDate2))).transact
        _ <- Ns.int.i.localTimeArray_?.insert(13, 3, Some(Array(localTime1, localTime2))).transact
        _ <- Ns.int.i.localDateTimeArray_?.insert(14, 3, Some(Array(localDateTime1, localDateTime2))).transact
        _ <- Ns.int.i.offsetTimeArray_?.insert(15, 3, Some(Array(offsetTime1, offsetTime2))).transact
        _ <- Ns.int.i.offsetDateTimeArray_?.insert(16, 3, Some(Array(offsetDateTime1, offsetDateTime2))).transact
        _ <- Ns.int.i.zonedDateTimeArray_?.insert(17, 3, Some(Array(zonedDateTime1, zonedDateTime2))).transact
        _ <- Ns.int.i.uuidArray_?.insert(18, 3, Some(Array(uuid1, uuid2))).transact
        _ <- Ns.int.i.uriArray_?.insert(19, 3, Some(Array(uri1, uri2))).transact
        _ <- Ns.int.i.byteArray_?.insert(20, 3, Some(Array(byte1, byte2))).transact
        _ <- Ns.int.i.shortArray_?.insert(21, 3, Some(Array(short1, short2))).transact
        _ <- Ns.int.i.charArray_?.insert(22, 3, Some(Array(char1, char2))).transact

        _ <- Ns.int_(1).i.a1.stringArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(string1, string2)))))
        _ <- Ns.int_(2).i.a1.intArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(int1, int2)))))
        _ <- Ns.int_(3).i.a1.longArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(long1, long2)))))
        _ <- Ns.int_(4).i.a1.floatArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(float1, float2)))))
        _ <- Ns.int_(5).i.a1.doubleArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(double1, double2)))))
        _ <- Ns.int_(6).i.a1.booleanArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(boolean1, boolean2)))))
        _ <- Ns.int_(7).i.a1.bigIntArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(bigInt1, bigInt2)))))
        _ <- Ns.int_(8).i.a1.bigDecimalArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(bigDecimal1, bigDecimal2)))))
        _ <- Ns.int_(9).i.a1.dateArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(date1, date2)))))
        _ <- Ns.int_(10).i.a1.durationArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(duration1, duration2)))))
        _ <- Ns.int_(11).i.a1.instantArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(instant1, instant2)))))
        _ <- Ns.int_(12).i.a1.localDateArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(localDate1, localDate2)))))
        _ <- Ns.int_(13).i.a1.localTimeArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(localTime1, localTime2)))))
        _ <- Ns.int_(14).i.a1.localDateTimeArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(localDateTime1, localDateTime2)))))
        _ <- Ns.int_(15).i.a1.offsetTimeArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(offsetTime1, offsetTime2)))))
        _ <- Ns.int_(16).i.a1.offsetDateTimeArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(offsetDateTime1, offsetDateTime2)))))
        _ <- Ns.int_(17).i.a1.zonedDateTimeArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(zonedDateTime1, zonedDateTime2)))))
        _ <- Ns.int_(18).i.a1.uuidArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(uuid1, uuid2)))))
        _ <- Ns.int_(19).i.a1.uriArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(uri1, uri2)))))
        _ <- Ns.int_(20).i.a1.byteArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(byte1, byte2)))))
        _ <- Ns.int_(21).i.a1.shortArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(short1, short2)))))
        _ <- Ns.int_(22).i.a1.charArray_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(char1, char2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        // Can't insert tacit attributes
        _ <- Future(compileError("Ns.i.stringArray_.insert(1, Array(string1))"))
      } yield ()
    }
  }
}
