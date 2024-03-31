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
import utest._

trait SaveCardSet extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "mandatory" - types { implicit conn =>
      for {
        // Empty Set of values is ignored
        _ <- Ns.iSet.query.get.map(_ ==> List())
        _ <- Ns.iSet(Set.empty[Int]).save.transact
        _ <- Ns.iSet.query.get.map(_ ==> List())

        _ <- Ns.i(1).stringSet.apply(Set(string1, string2)).save.transact
        _ <- Ns.i(1).intSet(Set(int1, int2)).save.transact
        _ <- Ns.i(1).longSet(Set(long1, long2)).save.transact
        _ <- Ns.i(1).floatSet(Set(float1, float2)).save.transact
        _ <- Ns.i(1).doubleSet(Set(double1, double2)).save.transact
        _ <- Ns.i(1).booleanSet(Set(boolean0)).save.transact
        _ <- Ns.i(1).bigIntSet(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalSet(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateSet(Set(date1, date2)).save.transact
        _ <- Ns.i(1).durationSet(Set(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantSet(Set(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateSet(Set(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeSet(Set(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeSet(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeSet(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeSet(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeSet(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidSet(Set(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriSet(Set(uri1, uri2)).save.transact
        _ <- Ns.i(1).byteSet(Set(byte1, byte2)).save.transact
        _ <- Ns.i(1).shortSet(Set(short1, short2)).save.transact
        _ <- Ns.i(1).charSet(Set(char1, char2)).save.transact

        _ <- Ns.i.stringSet.query.get.map(_ ==> List((1, Set(string1, string2))))
        _ <- Ns.i.intSet.query.get.map(_ ==> List((1, Set(int1, int2))))
        _ <- Ns.i.longSet.query.get.map(_ ==> List((1, Set(long1, long2))))
        _ <- Ns.i.floatSet.query.get.map(_ ==> List((1, Set(float1, float2))))
        _ <- Ns.i.doubleSet.query.get.map(_ ==> List((1, Set(double1, double2))))
        _ <- Ns.i.booleanSet.query.get.map(_ ==> List((1, Set(boolean0))))
        _ <- Ns.i.bigIntSet.query.get.map(_ ==> List((1, Set(bigInt1, bigInt2))))
        _ <- Ns.i.bigDecimalSet.query.get.map(_ ==> List((1, Set(bigDecimal1, bigDecimal2))))
        _ <- Ns.i.dateSet.query.get.map(_ ==> List((1, Set(date1, date2))))
        _ <- Ns.i.durationSet.query.get.map(_ ==> List((1, Set(duration1, duration2))))
        _ <- Ns.i.instantSet.query.get.map(_ ==> List((1, Set(instant1, instant2))))
        _ <- Ns.i.localDateSet.query.get.map(_ ==> List((1, Set(localDate1, localDate2))))
        _ <- Ns.i.localTimeSet.query.get.map(_ ==> List((1, Set(localTime1, localTime2))))
        _ <- Ns.i.localDateTimeSet.query.get.map(_ ==> List((1, Set(localDateTime1, localDateTime2))))
        _ <- Ns.i.offsetTimeSet.query.get.map(_ ==> List((1, Set(offsetTime1, offsetTime2))))
        _ <- Ns.i.offsetDateTimeSet.query.get.map(_ ==> List((1, Set(offsetDateTime1, offsetDateTime2))))
        _ <- Ns.i.zonedDateTimeSet.query.get.map(_ ==> List((1, Set(zonedDateTime1, zonedDateTime2))))
        _ <- Ns.i.uuidSet.query.get.map(_ ==> List((1, Set(uuid1, uuid2))))
        _ <- Ns.i.uriSet.query.get.map(_ ==> List((1, Set(uri1, uri2))))
        _ <- Ns.i.byteSet.query.get.map(_ ==> List((1, Set(byte1, byte2))))
        _ <- Ns.i.shortSet.query.get.map(_ ==> List((1, Set(short1, short2))))
        _ <- Ns.i.charSet.query.get.map(_ ==> List((1, Set(char1, char2))))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.i(1).refs(Set(r1, r2)).save.transact
        _ <- Ns.i.refs.query.get.map(_ ==> List((1, Set(r1, r2))))
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
        _ <- Ns.int(23).i(1).refs_?(Option.empty[Set[String]]).save.transact
        _ <- Ns.int(23).i(2).refs_?(Some(Set.empty[String])).save.transact
        _ <- Ns.int(23).i(3).refs_?(Some(Set(r1, r2))).save.transact
        _ <- Ns.int_(23).i.a1.refs_?.query.get.map(_ ==> List((1, None), (2, None), (3, Some(Set(r1, r2)))))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      // Applying value to tacit or mandatory attribute has same effect
      for {
        _ <- Ns.i(1).stringSet_(Set(string1, string2)).save.transact
        _ <- Ns.i(1).intSet_(Set(int1, int2)).save.transact
        _ <- Ns.i(1).longSet_(Set(long1, long2)).save.transact
        _ <- Ns.i(1).floatSet_(Set(float1, float2)).save.transact
        _ <- Ns.i(1).doubleSet_(Set(double1, double2)).save.transact
        _ <- Ns.i(1).booleanSet_(Set(boolean1, boolean2)).save.transact
        _ <- Ns.i(1).bigIntSet_(Set(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalSet_(Set(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateSet_(Set(date1, date2)).save.transact
        _ <- Ns.i(1).durationSet_(Set(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantSet_(Set(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateSet_(Set(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeSet_(Set(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeSet_(Set(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeSet_(Set(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeSet_(Set(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeSet_(Set(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidSet_(Set(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriSet_(Set(uri1, uri2)).save.transact
        _ <- Ns.i(1).byteSet_(Set(byte1, byte2)).save.transact
        _ <- Ns.i(1).shortSet_(Set(short1, short2)).save.transact
        _ <- Ns.i(1).charSet_(Set(char1, char2)).save.transact

        _ <- Ns.i.stringSet.query.get.map(_.head ==> (1, Set(string1, string2)))
        _ <- Ns.i.intSet.query.get.map(_.head ==> (1, Set(int1, int2)))
        _ <- Ns.i.longSet.query.get.map(_.head ==> (1, Set(long1, long2)))
        _ <- Ns.i.floatSet.query.get.map(_.head ==> (1, Set(float1, float2)))
        _ <- Ns.i.doubleSet.query.get.map(_.head ==> (1, Set(double1, double2)))
        _ <- Ns.i.booleanSet.query.get.map(_.head ==> (1, Set(boolean1, boolean2)))
        _ <- Ns.i.bigIntSet.query.get.map(_.head ==> (1, Set(bigInt1, bigInt2)))
        _ <- Ns.i.bigDecimalSet.query.get.map(_.head ==> (1, Set(bigDecimal1, bigDecimal2)))
        _ <- Ns.i.dateSet.query.get.map(_.head ==> (1, Set(date1, date2)))
        _ <- Ns.i.durationSet.query.get.map(_.head ==> (1, Set(duration1, duration2)))
        _ <- Ns.i.instantSet.query.get.map(_.head ==> (1, Set(instant1, instant2)))
        _ <- Ns.i.localDateSet.query.get.map(_.head ==> (1, Set(localDate1, localDate2)))
        _ <- Ns.i.localTimeSet.query.get.map(_.head ==> (1, Set(localTime1, localTime2)))
        _ <- Ns.i.localDateTimeSet.query.get.map(_.head ==> (1, Set(localDateTime1, localDateTime2)))
        _ <- Ns.i.offsetTimeSet.query.get.map(_.head ==> (1, Set(offsetTime1, offsetTime2)))
        _ <- Ns.i.offsetDateTimeSet.query.get.map(_.head ==> (1, Set(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.i.zonedDateTimeSet.query.get.map(_.head ==> (1, Set(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.i.uuidSet.query.get.map(_.head ==> (1, Set(uuid1, uuid2)))
        _ <- Ns.i.uriSet.query.get.map(_.head ==> (1, Set(uri1, uri2)))
        _ <- Ns.i.byteSet.query.get.map(_.head ==> (1, Set(byte1, byte2)))
        _ <- Ns.i.shortSet.query.get.map(_.head ==> (1, Set(short1, short2)))
        _ <- Ns.i.charSet.query.get.map(_.head ==> (1, Set(char1, char2)))

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.i(1).refs_(Set(r1, r2)).save.transact
        _ <- Ns.i.refs.query.get.map(_.head ==> (1, Set(r1, r2)))
      } yield ()
    }
  }
}
