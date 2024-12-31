package molecule.coreTests.spi.action.insert

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._
import scala.concurrent.Future

case class InsertCardSet(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    for {
      // No Entity.iSet inserted yet
      _ <- Entity.iSet.query.get.map(_ ==> Nil)

      // Inserting empty list/set of values is ignored
      // (See InsertSemantic for further details)
      _ <- Entity.iSet.insert(Seq.empty[Set[Int]]).transact
      _ <- Entity.iSet.insert(Seq(Set.empty[Int])).transact
      _ <- Entity.iSet.query.get.map(_ ==> List())

      _ <- Entity.i.stringSet.insert(1, Set(string1, string2)).transact
      _ <- Entity.i.intSet.insert(1, Set(int1, int2)).transact
      _ <- Entity.i.longSet.insert(1, Set(long1, long2)).transact
      _ <- Entity.i.floatSet.insert(1, Set(float1, float2)).transact
      _ <- Entity.i.doubleSet.insert(1, Set(double1, double2)).transact
      _ <- Entity.i.booleanSet.insert(1, Set(boolean0)).transact
      _ <- Entity.i.bigIntSet.insert(1, Set(bigInt1, bigInt2)).transact
      _ <- Entity.i.bigDecimalSet.insert(1, Set(bigDecimal1, bigDecimal2)).transact
      _ <- Entity.i.dateSet.insert(1, Set(date1, date2)).transact
      _ <- Entity.i.durationSet.insert(1, Set(duration1, duration2)).transact
      _ <- Entity.i.instantSet.insert(1, Set(instant1, instant2)).transact
      _ <- Entity.i.localDateSet.insert(1, Set(localDate1, localDate2)).transact
      _ <- Entity.i.localTimeSet.insert(1, Set(localTime1, localTime2)).transact
      _ <- Entity.i.localDateTimeSet.insert(1, Set(localDateTime1, localDateTime2)).transact
      _ <- Entity.i.offsetTimeSet.insert(1, Set(offsetTime1, offsetTime2)).transact
      _ <- Entity.i.offsetDateTimeSet.insert(1, Set(offsetDateTime1, offsetDateTime2)).transact
      _ <- Entity.i.zonedDateTimeSet.insert(1, Set(zonedDateTime1, zonedDateTime2)).transact
      _ <- Entity.i.uuidSet.insert(1, Set(uuid1, uuid2)).transact
      _ <- Entity.i.uriSet.insert(1, Set(uri1, uri2)).transact
      _ <- Entity.i.byteSet.insert(1, Set(byte1, byte2)).transact
      _ <- Entity.i.shortSet.insert(1, Set(short1, short2)).transact
      _ <- Entity.i.charSet.insert(1, Set(char1, char2)).transact

      _ <- Entity.i.stringSet.query.get.map(_ ==> List((1, Set(string1, string2))))
      _ <- Entity.i.intSet.query.get.map(_ ==> List((1, Set(int1, int2))))
      _ <- Entity.i.longSet.query.get.map(_ ==> List((1, Set(long1, long2))))
      _ <- Entity.i.floatSet.query.get.map(_ ==> List((1, Set(float1, float2))))
      _ <- Entity.i.doubleSet.query.get.map(_ ==> List((1, Set(double1, double2))))
      _ <- Entity.i.booleanSet.query.get.map(_ ==> List((1, Set(boolean0))))
      _ <- Entity.i.bigIntSet.query.get.map(_ ==> List((1, Set(bigInt1, bigInt2))))
      _ <- Entity.i.bigDecimalSet.query.get.map(_ ==> List((1, Set(bigDecimal1, bigDecimal2))))
      _ <- Entity.i.dateSet.query.get.map(_ ==> List((1, Set(date1, date2))))
      _ <- Entity.i.durationSet.query.get.map(_ ==> List((1, Set(duration1, duration2))))
      _ <- Entity.i.instantSet.query.get.map(_ ==> List((1, Set(instant1, instant2))))
      _ <- Entity.i.localDateSet.query.get.map(_ ==> List((1, Set(localDate1, localDate2))))
      _ <- Entity.i.localTimeSet.query.get.map(_ ==> List((1, Set(localTime1, localTime2))))
      _ <- Entity.i.localDateTimeSet.query.get.map(_ ==> List((1, Set(localDateTime1, localDateTime2))))
      _ <- Entity.i.offsetTimeSet.query.get.map(_ ==> List((1, Set(offsetTime1, offsetTime2))))
      _ <- Entity.i.offsetDateTimeSet.query.get.map(_ ==> List((1, Set(offsetDateTime1, offsetDateTime2))))
      _ <- Entity.i.zonedDateTimeSet.query.get.map(_ ==> List((1, Set(zonedDateTime1, zonedDateTime2))))
      _ <- Entity.i.uuidSet.query.get.map(_ ==> List((1, Set(uuid1, uuid2))))
      _ <- Entity.i.uriSet.query.get.map(_ ==> List((1, Set(uri1, uri2))))
      _ <- Entity.i.byteSet.query.get.map(_ ==> List((1, Set(byte1, byte2))))
      _ <- Entity.i.shortSet.query.get.map(_ ==> List((1, Set(short1, short2))))
      _ <- Entity.i.charSet.query.get.map(_ ==> List((1, Set(char1, char2))))

      List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
      _ <- Entity.i.refs.insert(1, Set(r1, r2)).transact
      _ <- Entity.i.refs.query.get.map(_ ==> List((1, Set(r1, r2))))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    for {
      _ <- Entity.int.i.stringSet_?.insert(1, 1, Option.empty[Set[String]]).transact
      _ <- Entity.int.i.intSet_?.insert(2, 1, Option.empty[Set[Int]]).transact
      _ <- Entity.int.i.longSet_?.insert(3, 1, Option.empty[Set[Long]]).transact
      _ <- Entity.int.i.floatSet_?.insert(4, 1, Option.empty[Set[Float]]).transact
      _ <- Entity.int.i.doubleSet_?.insert(5, 1, Option.empty[Set[Double]]).transact
      _ <- Entity.int.i.booleanSet_?.insert(6, 1, Option.empty[Set[Boolean]]).transact
      _ <- Entity.int.i.bigIntSet_?.insert(7, 1, Option.empty[Set[BigInt]]).transact
      _ <- Entity.int.i.bigDecimalSet_?.insert(8, 1, Option.empty[Set[BigDecimal]]).transact
      _ <- Entity.int.i.dateSet_?.insert(9, 1, Option.empty[Set[Date]]).transact
      _ <- Entity.int.i.durationSet_?.insert(10, 1, Option.empty[Set[Duration]]).transact
      _ <- Entity.int.i.instantSet_?.insert(11, 1, Option.empty[Set[Instant]]).transact
      _ <- Entity.int.i.localDateSet_?.insert(12, 1, Option.empty[Set[LocalDate]]).transact
      _ <- Entity.int.i.localTimeSet_?.insert(13, 1, Option.empty[Set[LocalTime]]).transact
      _ <- Entity.int.i.localDateTimeSet_?.insert(14, 1, Option.empty[Set[LocalDateTime]]).transact
      _ <- Entity.int.i.offsetTimeSet_?.insert(15, 1, Option.empty[Set[OffsetTime]]).transact
      _ <- Entity.int.i.offsetDateTimeSet_?.insert(16, 1, Option.empty[Set[OffsetDateTime]]).transact
      _ <- Entity.int.i.zonedDateTimeSet_?.insert(17, 1, Option.empty[Set[ZonedDateTime]]).transact
      _ <- Entity.int.i.uuidSet_?.insert(18, 1, Option.empty[Set[UUID]]).transact
      _ <- Entity.int.i.uriSet_?.insert(19, 1, Option.empty[Set[URI]]).transact
      _ <- Entity.int.i.byteSet_?.insert(20, 1, Option.empty[Set[Byte]]).transact
      _ <- Entity.int.i.shortSet_?.insert(21, 1, Option.empty[Set[Short]]).transact
      _ <- Entity.int.i.charSet_?.insert(22, 1, Option.empty[Set[Char]]).transact

      _ <- Entity.int.i.stringSet_?.insert(1, 2, Some(Set.empty[String])).transact
      _ <- Entity.int.i.intSet_?.insert(2, 2, Some(Set.empty[Int])).transact
      _ <- Entity.int.i.longSet_?.insert(3, 2, Some(Set.empty[Long])).transact
      _ <- Entity.int.i.floatSet_?.insert(4, 2, Some(Set.empty[Float])).transact
      _ <- Entity.int.i.doubleSet_?.insert(5, 2, Some(Set.empty[Double])).transact
      _ <- Entity.int.i.booleanSet_?.insert(6, 2, Some(Set.empty[Boolean])).transact
      _ <- Entity.int.i.bigIntSet_?.insert(7, 2, Some(Set.empty[BigInt])).transact
      _ <- Entity.int.i.bigDecimalSet_?.insert(8, 2, Some(Set.empty[BigDecimal])).transact
      _ <- Entity.int.i.dateSet_?.insert(9, 2, Some(Set.empty[Date])).transact
      _ <- Entity.int.i.durationSet_?.insert(10, 2, Some(Set.empty[Duration])).transact
      _ <- Entity.int.i.instantSet_?.insert(11, 2, Some(Set.empty[Instant])).transact
      _ <- Entity.int.i.localDateSet_?.insert(12, 2, Some(Set.empty[LocalDate])).transact
      _ <- Entity.int.i.localTimeSet_?.insert(13, 2, Some(Set.empty[LocalTime])).transact
      _ <- Entity.int.i.localDateTimeSet_?.insert(14, 2, Some(Set.empty[LocalDateTime])).transact
      _ <- Entity.int.i.offsetTimeSet_?.insert(15, 2, Some(Set.empty[OffsetTime])).transact
      _ <- Entity.int.i.offsetDateTimeSet_?.insert(16, 2, Some(Set.empty[OffsetDateTime])).transact
      _ <- Entity.int.i.zonedDateTimeSet_?.insert(17, 2, Some(Set.empty[ZonedDateTime])).transact
      _ <- Entity.int.i.uuidSet_?.insert(18, 2, Some(Set.empty[UUID])).transact
      _ <- Entity.int.i.uriSet_?.insert(19, 2, Some(Set.empty[URI])).transact
      _ <- Entity.int.i.byteSet_?.insert(20, 2, Some(Set.empty[Byte])).transact
      _ <- Entity.int.i.shortSet_?.insert(21, 2, Some(Set.empty[Short])).transact
      _ <- Entity.int.i.charSet_?.insert(22, 2, Some(Set.empty[Char])).transact

      _ <- Entity.int.i.stringSet_?.insert(1, 3, Some(Set(string1, string2))).transact
      _ <- Entity.int.i.intSet_?.insert(2, 3, Some(Set(int1, int2))).transact
      _ <- Entity.int.i.longSet_?.insert(3, 3, Some(Set(long1, long2))).transact
      _ <- Entity.int.i.floatSet_?.insert(4, 3, Some(Set(float1, float2))).transact
      _ <- Entity.int.i.doubleSet_?.insert(5, 3, Some(Set(double1, double2))).transact
      _ <- Entity.int.i.booleanSet_?.insert(6, 3, Some(Set(boolean1, boolean2))).transact
      _ <- Entity.int.i.bigIntSet_?.insert(7, 3, Some(Set(bigInt1, bigInt2))).transact
      _ <- Entity.int.i.bigDecimalSet_?.insert(8, 3, Some(Set(bigDecimal1, bigDecimal2))).transact
      _ <- Entity.int.i.dateSet_?.insert(9, 3, Some(Set(date1, date2))).transact
      _ <- Entity.int.i.durationSet_?.insert(10, 3, Some(Set(duration1, duration2))).transact
      _ <- Entity.int.i.instantSet_?.insert(11, 3, Some(Set(instant1, instant2))).transact
      _ <- Entity.int.i.localDateSet_?.insert(12, 3, Some(Set(localDate1, localDate2))).transact
      _ <- Entity.int.i.localTimeSet_?.insert(13, 3, Some(Set(localTime1, localTime2))).transact
      _ <- Entity.int.i.localDateTimeSet_?.insert(14, 3, Some(Set(localDateTime1, localDateTime2))).transact
      _ <- Entity.int.i.offsetTimeSet_?.insert(15, 3, Some(Set(offsetTime1, offsetTime2))).transact
      _ <- Entity.int.i.offsetDateTimeSet_?.insert(16, 3, Some(Set(offsetDateTime1, offsetDateTime2))).transact
      _ <- Entity.int.i.zonedDateTimeSet_?.insert(17, 3, Some(Set(zonedDateTime1, zonedDateTime2))).transact
      _ <- Entity.int.i.uuidSet_?.insert(18, 3, Some(Set(uuid1, uuid2))).transact
      _ <- Entity.int.i.uriSet_?.insert(19, 3, Some(Set(uri1, uri2))).transact
      _ <- Entity.int.i.byteSet_?.insert(20, 3, Some(Set(byte1, byte2))).transact
      _ <- Entity.int.i.shortSet_?.insert(21, 3, Some(Set(short1, short2))).transact
      _ <- Entity.int.i.charSet_?.insert(22, 3, Some(Set(char1, char2))).transact

      _ <- Entity.int_(1).i.a1.stringSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(string1, string2)))))
      _ <- Entity.int_(2).i.a1.intSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(int1, int2)))))
      _ <- Entity.int_(3).i.a1.longSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(long1, long2)))))
      _ <- Entity.int_(4).i.a1.floatSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(float1, float2)))))
      _ <- Entity.int_(5).i.a1.doubleSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(double1, double2)))))
      _ <- Entity.int_(6).i.a1.booleanSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(boolean1, boolean2)))))
      _ <- Entity.int_(7).i.a1.bigIntSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(bigInt1, bigInt2)))))
      _ <- Entity.int_(8).i.a1.bigDecimalSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(bigDecimal1, bigDecimal2)))))
      _ <- Entity.int_(9).i.a1.dateSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(date1, date2)))))
      _ <- Entity.int_(10).i.a1.durationSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(duration1, duration2)))))
      _ <- Entity.int_(11).i.a1.instantSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(instant1, instant2)))))
      _ <- Entity.int_(12).i.a1.localDateSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localDate1, localDate2)))))
      _ <- Entity.int_(13).i.a1.localTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localTime1, localTime2)))))
      _ <- Entity.int_(14).i.a1.localDateTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(localDateTime1, localDateTime2)))))
      _ <- Entity.int_(15).i.a1.offsetTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(offsetTime1, offsetTime2)))))
      _ <- Entity.int_(16).i.a1.offsetDateTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(offsetDateTime1, offsetDateTime2)))))
      _ <- Entity.int_(17).i.a1.zonedDateTimeSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(zonedDateTime1, zonedDateTime2)))))
      _ <- Entity.int_(18).i.a1.uuidSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(uuid1, uuid2)))))
      _ <- Entity.int_(19).i.a1.uriSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(uri1, uri2)))))
      _ <- Entity.int_(20).i.a1.byteSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(byte1, byte2)))))
      _ <- Entity.int_(21).i.a1.shortSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(short1, short2)))))
      _ <- Entity.int_(22).i.a1.charSet_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(char1, char2)))))

      List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
      _ <- Entity.int.i.refs_?.insert(23, 1, Option.empty[Set[Long]]).transact
      _ <- Entity.int.i.refs_?.insert(23, 2, Some(Set.empty[Long])).transact
      _ <- Entity.int.i.refs_?.insert(23, 3, Some(Set(r1, r2))).transact
      _ <- Entity.int_(23).i.a1.refs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    for {
      // Can't insert tacit attributes
      _ <- Future(compileErrors("Entity.i.stringSet_.insert(1, Set(string1))"))
    } yield ()
  }
}
