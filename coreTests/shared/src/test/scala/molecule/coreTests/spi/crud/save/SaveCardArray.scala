package molecule.coreTests.spi.crud.save

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import molecule.coreTests.util.Array2List
import utest._

trait SaveCardArray extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      val multipleArrays =
        """Can only save one Array of values for Array attribute `Ns.intArray`. Found multiple arrays:
          |Array(1)
          |Array(2)""".stripMargin
      for {
        // Can't save multiple Sets of values (use insert for that)
        _ <- Ns.intArray(Seq(Array(1), Array(2))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> multipleArrays
          }

        // Same as
        _ <- Ns.intArray(Array(1), Array(2)).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> multipleArrays
          }

        // Same as
        _ <- Ns.intArray(1, 2).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> multipleArrays
          }

        // Empty values are ignored
        _ <- Ns.ii.query.get.map(_ ==> List())
        _ <- Ns.ii(Seq.empty[Set[Int]]).save.transact
        _ <- Ns.ii(Seq(Set.empty[Int])).save.transact
        _ <- Ns.ii.query.get.map(_ ==> List())

        _ <- Ns.i(1).stringArray(Array(string1, string2)).save.transact
        _ <- Ns.i(1).intArray(Array(int1, int2)).save.transact
        _ <- Ns.i(1).longArray(Array(long1, long2)).save.transact
        _ <- Ns.i(1).floatArray(Array(float1, float2)).save.transact
        _ <- Ns.i(1).doubleArray(Array(double1, double2)).save.transact
        _ <- Ns.i(1).booleanArray(Array(boolean0)).save.transact
        _ <- Ns.i(1).bigIntArray(Array(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalArray(Array(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateArray(Array(date1, date2)).save.transact
        _ <- Ns.i(1).durationArray(Array(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantArray(Array(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateArray(Array(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeArray(Array(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeArray(Array(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeArray(Array(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeArray(Array(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeArray(Array(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidArray(Array(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriArray(Array(uri1, uri2)).save.transact
        _ <- Ns.i(1).byteArray(Array(byte1, byte2)).save.transact
        _ <- Ns.i(1).shortArray(Array(short1, short2)).save.transact
        _ <- Ns.i(1).charArray(Array(char1, char2)).save.transact

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


    "optional" - types { implicit conn =>
      for {
        // Can't save multiple Arrays of values (use insert for that)
        _ <- Ns.intArray_?(Some(Seq(Array(1), Array(2)))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==>
              """Can only save one Array of values for Array attribute `Ns.intArray`. Found multiple arrays:
                |Array(1)
                |Array(2)""".stripMargin
          }

        // Empty option of Array of values saves nothing
        _ <- Ns.intArray_?(Option.empty[Seq[Array[Int]]]).save.transact
        _ <- Ns.intArray.query.get.map(_ ==> List())

        _ <- Ns.int(1).i(1).stringArray_?(Option.empty[Array[String]]).save.transact
        _ <- Ns.int(2).i(1).intArray_?(Option.empty[Array[Int]]).save.transact
        _ <- Ns.int(3).i(1).longArray_?(Option.empty[Array[Long]]).save.transact
        _ <- Ns.int(4).i(1).floatArray_?(Option.empty[Array[Float]]).save.transact
        _ <- Ns.int(5).i(1).doubleArray_?(Option.empty[Array[Double]]).save.transact
        _ <- Ns.int(6).i(1).booleanArray_?(Option.empty[Array[Boolean]]).save.transact
        _ <- Ns.int(7).i(1).bigIntArray_?(Option.empty[Array[BigInt]]).save.transact
        _ <- Ns.int(8).i(1).bigDecimalArray_?(Option.empty[Array[BigDecimal]]).save.transact
        _ <- Ns.int(9).i(1).dateArray_?(Option.empty[Array[Date]]).save.transact
        _ <- Ns.int(10).i(1).durationArray_?(Option.empty[Array[Duration]]).save.transact
        _ <- Ns.int(11).i(1).instantArray_?(Option.empty[Array[Instant]]).save.transact
        _ <- Ns.int(12).i(1).localDateArray_?(Option.empty[Array[LocalDate]]).save.transact
        _ <- Ns.int(13).i(1).localTimeArray_?(Option.empty[Array[LocalTime]]).save.transact
        _ <- Ns.int(14).i(1).localDateTimeArray_?(Option.empty[Array[LocalDateTime]]).save.transact
        _ <- Ns.int(15).i(1).offsetTimeArray_?(Option.empty[Array[OffsetTime]]).save.transact
        _ <- Ns.int(16).i(1).offsetDateTimeArray_?(Option.empty[Array[OffsetDateTime]]).save.transact
        _ <- Ns.int(17).i(1).zonedDateTimeArray_?(Option.empty[Array[ZonedDateTime]]).save.transact
        _ <- Ns.int(18).i(1).uuidArray_?(Option.empty[Array[UUID]]).save.transact
        _ <- Ns.int(19).i(1).uriArray_?(Option.empty[Array[URI]]).save.transact
        _ <- Ns.int(20).i(1).byteArray_?(Option.empty[Array[Byte]]).save.transact
        _ <- Ns.int(21).i(1).shortArray_?(Option.empty[Array[Short]]).save.transact
        _ <- Ns.int(22).i(1).charArray_?(Option.empty[Array[Char]]).save.transact

        _ <- Ns.int(1).i(2).stringArray_?(Some(Array.empty[String])).save.transact
        _ <- Ns.int(2).i(2).intArray_?(Some(Array.empty[Int])).save.transact
        _ <- Ns.int(3).i(2).longArray_?(Some(Array.empty[Long])).save.transact
        _ <- Ns.int(4).i(2).floatArray_?(Some(Array.empty[Float])).save.transact
        _ <- Ns.int(5).i(2).doubleArray_?(Some(Array.empty[Double])).save.transact
        _ <- Ns.int(6).i(2).booleanArray_?(Some(Array.empty[Boolean])).save.transact
        _ <- Ns.int(7).i(2).bigIntArray_?(Some(Array.empty[BigInt])).save.transact
        _ <- Ns.int(8).i(2).bigDecimalArray_?(Some(Array.empty[BigDecimal])).save.transact
        _ <- Ns.int(9).i(2).dateArray_?(Some(Array.empty[Date])).save.transact
        _ <- Ns.int(10).i(2).durationArray_?(Some(Array.empty[Duration])).save.transact
        _ <- Ns.int(11).i(2).instantArray_?(Some(Array.empty[Instant])).save.transact
        _ <- Ns.int(12).i(2).localDateArray_?(Some(Array.empty[LocalDate])).save.transact
        _ <- Ns.int(13).i(2).localTimeArray_?(Some(Array.empty[LocalTime])).save.transact
        _ <- Ns.int(14).i(2).localDateTimeArray_?(Some(Array.empty[LocalDateTime])).save.transact
        _ <- Ns.int(15).i(2).offsetTimeArray_?(Some(Array.empty[OffsetTime])).save.transact
        _ <- Ns.int(16).i(2).offsetDateTimeArray_?(Some(Array.empty[OffsetDateTime])).save.transact
        _ <- Ns.int(17).i(2).zonedDateTimeArray_?(Some(Array.empty[ZonedDateTime])).save.transact
        _ <- Ns.int(18).i(2).uuidArray_?(Some(Array.empty[UUID])).save.transact
        _ <- Ns.int(19).i(2).uriArray_?(Some(Array.empty[URI])).save.transact
        _ <- Ns.int(20).i(2).byteArray_?(Some(Array.empty[Byte])).save.transact
        _ <- Ns.int(21).i(2).shortArray_?(Some(Array.empty[Short])).save.transact
        _ <- Ns.int(22).i(2).charArray_?(Some(Array.empty[Char])).save.transact

        _ <- Ns.int(1).i(3).stringArray_?(Some(Array(string1, string2))).save.transact
        _ <- Ns.int(2).i(3).intArray_?(Some(Array(int1, int2))).save.transact
        _ <- Ns.int(3).i(3).longArray_?(Some(Array(long1, long2))).save.transact
        _ <- Ns.int(4).i(3).floatArray_?(Some(Array(float1, float2))).save.transact
        _ <- Ns.int(5).i(3).doubleArray_?(Some(Array(double1, double2))).save.transact
        _ <- Ns.int(6).i(3).booleanArray_?(Some(Array(boolean1, boolean2))).save.transact
        _ <- Ns.int(7).i(3).bigIntArray_?(Some(Array(bigInt1, bigInt2))).save.transact
        _ <- Ns.int(8).i(3).bigDecimalArray_?(Some(Array(bigDecimal1, bigDecimal2))).save.transact
        _ <- Ns.int(9).i(3).dateArray_?(Some(Array(date1, date2))).save.transact
        _ <- Ns.int(10).i(3).durationArray_?(Some(Array(duration1, duration2))).save.transact
        _ <- Ns.int(11).i(3).instantArray_?(Some(Array(instant1, instant2))).save.transact
        _ <- Ns.int(12).i(3).localDateArray_?(Some(Array(localDate1, localDate2))).save.transact
        _ <- Ns.int(13).i(3).localTimeArray_?(Some(Array(localTime1, localTime2))).save.transact
        _ <- Ns.int(14).i(3).localDateTimeArray_?(Some(Array(localDateTime1, localDateTime2))).save.transact
        _ <- Ns.int(15).i(3).offsetTimeArray_?(Some(Array(offsetTime1, offsetTime2))).save.transact
        _ <- Ns.int(16).i(3).offsetDateTimeArray_?(Some(Array(offsetDateTime1, offsetDateTime2))).save.transact
        _ <- Ns.int(17).i(3).zonedDateTimeArray_?(Some(Array(zonedDateTime1, zonedDateTime2))).save.transact
        _ <- Ns.int(18).i(3).uuidArray_?(Some(Array(uuid1, uuid2))).save.transact
        _ <- Ns.int(19).i(3).uriArray_?(Some(Array(uri1, uri2))).save.transact
        _ <- Ns.int(20).i(3).byteArray_?(Some(Array(byte1, byte2))).save.transact
        _ <- Ns.int(21).i(3).shortArray_?(Some(Array(short1, short2))).save.transact
        _ <- Ns.int(22).i(3).charArray_?(Some(Array(char1, char2))).save.transact

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
        _ <- Ns.i(1).stringArray_(Array(string1, string2)).save.transact
        _ <- Ns.i(1).intArray_(Array(int1, int2)).save.transact
        _ <- Ns.i(1).longArray_(Array(long1, long2)).save.transact
        _ <- Ns.i(1).floatArray_(Array(float1, float2)).save.transact
        _ <- Ns.i(1).doubleArray_(Array(double1, double2)).save.transact
        _ <- Ns.i(1).booleanArray_(Array(boolean1, boolean2)).save.transact
        _ <- Ns.i(1).bigIntArray_(Array(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalArray_(Array(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateArray_(Array(date1, date2)).save.transact
        _ <- Ns.i(1).durationArray_(Array(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantArray_(Array(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateArray_(Array(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeArray_(Array(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeArray_(Array(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeArray_(Array(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeArray_(Array(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeArray_(Array(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidArray_(Array(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriArray_(Array(uri1, uri2)).save.transact
        _ <- Ns.i(1).byteArray_(Array(byte1, byte2)).save.transact
        _ <- Ns.i(1).shortArray_(Array(short1, short2)).save.transact
        _ <- Ns.i(1).charArray_(Array(char1, char2)).save.transact

        _ <- Ns.i.stringArray.query.get.map(_.head ==> (1, Array(string1, string2)))
        _ <- Ns.i.intArray.query.get.map(_.head ==> (1, Array(int1, int2)))
        _ <- Ns.i.longArray.query.get.map(_.head ==> (1, Array(long1, long2)))
        _ <- Ns.i.floatArray.query.get.map(_.head ==> (1, Array(float1, float2)))
        _ <- Ns.i.doubleArray.query.get.map(_.head ==> (1, Array(double1, double2)))
        _ <- Ns.i.booleanArray.query.get.map(_.head ==> (1, Array(boolean1, boolean2)))
        _ <- Ns.i.bigIntArray.query.get.map(_.head ==> (1, Array(bigInt1, bigInt2)))
        _ <- Ns.i.bigDecimalArray.query.get.map(_.head ==> (1, Array(bigDecimal1, bigDecimal2)))
        _ <- Ns.i.dateArray.query.get.map(_.head ==> (1, Array(date1, date2)))
        _ <- Ns.i.durationArray.query.get.map(_.head ==> (1, Array(duration1, duration2)))
        _ <- Ns.i.instantArray.query.get.map(_.head ==> (1, Array(instant1, instant2)))
        _ <- Ns.i.localDateArray.query.get.map(_.head ==> (1, Array(localDate1, localDate2)))
        _ <- Ns.i.localTimeArray.query.get.map(_.head ==> (1, Array(localTime1, localTime2)))
        _ <- Ns.i.localDateTimeArray.query.get.map(_.head ==> (1, Array(localDateTime1, localDateTime2)))
        _ <- Ns.i.offsetTimeArray.query.get.map(_.head ==> (1, Array(offsetTime1, offsetTime2)))
        _ <- Ns.i.offsetDateTimeArray.query.get.map(_.head ==> (1, Array(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.i.zonedDateTimeArray.query.get.map(_.head ==> (1, Array(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.i.uuidArray.query.get.map(_.head ==> (1, Array(uuid1, uuid2)))
        _ <- Ns.i.uriArray.query.get.map(_.head ==> (1, Array(uri1, uri2)))
        _ <- Ns.i.byteArray.query.get.map(_.head ==> (1, Array(byte1, byte2)))
        _ <- Ns.i.shortArray.query.get.map(_.head ==> (1, Array(short1, short2)))
        _ <- Ns.i.charArray.query.get.map(_.head ==> (1, Array(char1, char2)))
      } yield ()
    }
  }
}
