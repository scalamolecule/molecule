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

trait SaveCardSeq extends CoreTestSuite with Array2List with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      val multipleArrays =
        """Can only save one Seq of values for Seq attribute `Ns.intSeq`. Found multiple seqs:
          |List(1)
          |List(2)""".stripMargin
      for {
        // Can't save multiple Sets of values (use insert for that)
        _ <- Ns.intSeq(Seq(Seq(1), Seq(2))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> multipleArrays
          }

        // Same as
        _ <- Ns.intSeq(Seq(1), Seq(2)).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> multipleArrays
          }

        // Same as
        _ <- Ns.intSeq(Seq(1), Seq(2)).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> multipleArrays
          }

        // Empty values are ignored
        _ <- Ns.intSeq.query.get.map(_ ==> List())
        _ <- Ns.intSeq(List.empty[List[Int]]).save.transact
        _ <- Ns.intSeq(List(List.empty[Int])).save.transact
        _ <- Ns.intSeq.query.get.map(_ ==> List())

        _ <- Ns.i(1).stringSeq(List(string1, string2)).save.transact
        _ <- Ns.i(1).intSeq(List(int1, int2)).save.transact
        _ <- Ns.i(1).longSeq(List(long1, long2)).save.transact
        _ <- Ns.i(1).floatSeq(List(float1, float2)).save.transact
        _ <- Ns.i(1).doubleSeq(List(double1, double2)).save.transact
        _ <- Ns.i(1).booleanSeq(List(boolean0)).save.transact
        _ <- Ns.i(1).bigIntSeq(List(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalSeq(List(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateSeq(List(date1, date2)).save.transact
        _ <- Ns.i(1).durationSeq(List(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantSeq(List(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateSeq(List(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeSeq(List(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeSeq(List(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeSeq(List(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeSeq(List(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeSeq(List(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidSeq(List(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriSeq(List(uri1, uri2)).save.i.transact
        _ <- Ns.i(1).byteSeq(Array(byte1, byte2)).save.i.transact
        _ <- Ns.i(1).shortSeq(List(short1, short2)).save.transact
        _ <- Ns.i(1).charSeq(List(char1, char2)).save.transact

        // Array of Bytes transparently mapped to any available optimized database byte array storage format

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
        _ <- Ns.i.byteSeq.query.get.map(_ ==> List((1, Array(byte1, byte2))))
        _ <- Ns.i.shortSeq.query.get.map(_ ==> List((1, List(short1, short2))))
        _ <- Ns.i.charSeq.query.get.map(_ ==> List((1, List(char1, char2))))

        // (Refs have Set semantics only)
      } yield ()
    }


    "optional" - types { implicit conn =>
      for {
        // Can't save multiple Arrays of values (use insert for that)
        _ <- Ns.intSeq_?(Some(List(List(1), List(2)))).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==>
              """Can only save one Seq of values for Seq attribute `Ns.intSeq`. Found multiple seqs:
                |List(1)
                |List(2)""".stripMargin
          }

        // Empty option of Array of values saves nothing
        _ <- Ns.intSeq_?(Option.empty[List[List[Int]]]).save.transact
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
        _ <- Ns.int(20).i(1).byteSeq_?(Option.empty[Array[Byte]]).save.transact
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
        _ <- Ns.int(20).i(2).byteSeq_?(Some(Array.empty[Byte])).save.transact
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
        _ <- Ns.int(20).i(3).byteSeq_?(Some(Array(byte1, byte2))).save.transact
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
        _ <- Ns.int_(20).i.a1.byteSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Array(byte1, byte2)))))
        _ <- Ns.int_(21).i.a1.shortSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(short1, short2)))))
        _ <- Ns.int_(22).i.a1.charSeq_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(List(char1, char2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      for {
        _ <- Ns.i(1).stringSeq_(List(string1, string2)).save.transact
        _ <- Ns.i(1).intSeq_(List(int1, int2)).save.transact
        _ <- Ns.i(1).longSeq_(List(long1, long2)).save.transact
        _ <- Ns.i(1).floatSeq_(List(float1, float2)).save.transact
        _ <- Ns.i(1).doubleSeq_(List(double1, double2)).save.transact
        _ <- Ns.i(1).booleanSeq_(List(boolean1, boolean2)).save.transact
        _ <- Ns.i(1).bigIntSeq_(List(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalSeq_(List(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateSeq_(List(date1, date2)).save.transact
        _ <- Ns.i(1).durationSeq_(List(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantSeq_(List(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateSeq_(List(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeSeq_(List(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeSeq_(List(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeSeq_(List(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeSeq_(List(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeSeq_(List(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidSeq_(List(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriSeq_(List(uri1, uri2)).save.transact
        _ <- Ns.i(1).byteSeq_(Array(byte1, byte2)).save.transact
        _ <- Ns.i(1).shortSeq_(List(short1, short2)).save.transact
        _ <- Ns.i(1).charSeq_(List(char1, char2)).save.transact

        _ <- Ns.i.stringSeq.query.get.map(_.head ==> (1, List(string1, string2)))
        _ <- Ns.i.intSeq.query.get.map(_.head ==> (1, List(int1, int2)))
        _ <- Ns.i.longSeq.query.get.map(_.head ==> (1, List(long1, long2)))
        _ <- Ns.i.floatSeq.query.get.map(_.head ==> (1, List(float1, float2)))
        _ <- Ns.i.doubleSeq.query.get.map(_.head ==> (1, List(double1, double2)))
        _ <- Ns.i.booleanSeq.query.get.map(_.head ==> (1, List(boolean1, boolean2)))
        _ <- Ns.i.bigIntSeq.query.get.map(_.head ==> (1, List(bigInt1, bigInt2)))
        _ <- Ns.i.bigDecimalSeq.query.get.map(_.head ==> (1, List(bigDecimal1, bigDecimal2)))
        _ <- Ns.i.dateSeq.query.get.map(_.head ==> (1, List(date1, date2)))
        _ <- Ns.i.durationSeq.query.get.map(_.head ==> (1, List(duration1, duration2)))
        _ <- Ns.i.instantSeq.query.get.map(_.head ==> (1, List(instant1, instant2)))
        _ <- Ns.i.localDateSeq.query.get.map(_.head ==> (1, List(localDate1, localDate2)))
        _ <- Ns.i.localTimeSeq.query.get.map(_.head ==> (1, List(localTime1, localTime2)))
        _ <- Ns.i.localDateTimeSeq.query.get.map(_.head ==> (1, List(localDateTime1, localDateTime2)))
        _ <- Ns.i.offsetTimeSeq.query.get.map(_.head ==> (1, List(offsetTime1, offsetTime2)))
        _ <- Ns.i.offsetDateTimeSeq.query.get.map(_.head ==> (1, List(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.i.zonedDateTimeSeq.query.get.map(_.head ==> (1, List(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.i.uuidSeq.query.get.map(_.head ==> (1, List(uuid1, uuid2)))
        _ <- Ns.i.uriSeq.query.get.map(_.head ==> (1, List(uri1, uri2)))
        _ <- Ns.i.byteSeq.query.get.map(_.head ==> (1, Array(byte1, byte2)))
        _ <- Ns.i.shortSeq.query.get.map(_.head ==> (1, List(short1, short2)))
        _ <- Ns.i.charSeq.query.get.map(_.head ==> (1, List(char1, char2)))
      } yield ()
    }
  }
}
