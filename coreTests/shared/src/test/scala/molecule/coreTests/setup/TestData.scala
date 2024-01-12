package molecule.coreTests.setup

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.util.DateHandling

trait TestData extends DateHandling { self: CoreTest =>

  private def da(i: Int): Date = {
    // Alternate between winter/summer time to test daylight savings too
    val month = i % 2 * 6 + 1
    str2date((2000 + i).toString + "-" + month)
  }
  private def ur(i: Int): URI = new URI("uri" + i)
  private def bi(i: Int): BigInt = BigInt(i)
  private def bd(d: Double): BigDecimal = BigDecimal(d)
  private def r(i: Int): String = {

    println(s"database: $database   $i")
    if (database == "MongoDB") "12345678901234567890123" + i else s"$i"
  }

  lazy val (string0, string1, string2, string3, string4, string5, string6, string7, string8, string9) =
    ("-", "a", "b", "c", "d", "e", "f", "g", "h", "i")

  lazy val (int0, int1, int2, int3, int4, int5, int6, int7, int8, int9) =
    (0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

  lazy val (long0, long1, long2, long3, long4, long5, long6, long7, long8, long9) =
    (0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)

  lazy val (float0, float1, float2, float3, float4, float5, float6, float7, float8, float9) =
    (0.0f, 1.1f, 2.2f, 3.3f, 4.4f, 5.5f, 6.6f, 7.7f, 8.8f, 9.9f)

  lazy val (double0, double1, double2, double3, double4, double5, double6, double7, double8, double9) =
    (0.0, 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9)

  lazy val (boolean0, boolean1, boolean2, boolean3) =
    (false, false, true, false) // make boolean1 sort before boolean2 (false before true)

  lazy val (bigInt0, bigInt1, bigInt2, bigInt3, bigInt4, bigInt5, bigInt6, bigInt7, bigInt8, bigInt9) =
    (bi(0), bi(1), bi(2), bi(3), bi(4), bi(5), bi(6), bi(7), bi(8), bi(9))

  lazy val (bigDecimal0, bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5, bigDecimal6, bigDecimal7, bigDecimal8, bigDecimal9) =
    (bd(0.0), bd(1.1), bd(2.2), bd(3.3), bd(4.4), bd(5.5), bd(6.6), bd(7.7), bd(8.8), bd(9.9))

  lazy val (date0, date1, date2, date3, date4, date5, date6, date7, date8, date9) =
    (da(0), da(1), da(2), da(3), da(4), da(5), da(6), da(7), da(8), da(9))

  lazy val List(
  duration0,
  duration1,
  duration2,
  duration3,
  duration4,
  duration5,
  duration6,
  duration7,
  duration8,
  duration9
  ) = (0 to 9).toList.map(i => Duration.ofMinutes(i))

  lazy val List(
  instant0,
  instant1,
  instant2,
  instant3,
  instant4,
  instant5,
  instant6,
  instant7,
  instant8,
  instant9
  ) = (0L to 9L).toList.map(i => Instant.ofEpochSecond(i))

  lazy val List(
  localDate0,
  localDate1,
  localDate2,
  localDate3,
  localDate4,
  localDate5,
  localDate6,
  localDate7,
  localDate8,
  localDate9
  ) = (0 to 9).toList.map(i => LocalDate.of(2000 + i, 1, 1))

  lazy val List(
  localTime0,
  localTime1,
  localTime2,
  localTime3,
  localTime4,
  localTime5,
  localTime6,
  localTime7,
  localTime8,
  localTime9
  ) = (0 to 9).toList.map(i => LocalTime.of(i, i))

  lazy val List(
  localDateTime0,
  localDateTime1,
  localDateTime2,
  localDateTime3,
  localDateTime4,
  localDateTime5,
  localDateTime6,
  localDateTime7,
  localDateTime8,
  localDateTime9
  ) = (0 to 9).toList.map(i => LocalDateTime.of(2000 + i, 1, 1, 1, i))

  lazy val List(
  offsetTime0,
  offsetTime1,
  offsetTime2,
  offsetTime3,
  offsetTime4,
  offsetTime5,
  offsetTime6,
  offsetTime7,
  offsetTime8,
  offsetTime9
  ) = (0 to 9).toList.map(i => OffsetTime.of(i, i, i, i, ZoneOffset.ofHours(i)))

  lazy val List(
  offsetDateTime0,
  offsetDateTime1,
  offsetDateTime2,
  offsetDateTime3,
  offsetDateTime4,
  offsetDateTime5,
  offsetDateTime6,
  offsetDateTime7,
  offsetDateTime8,
  offsetDateTime9
  ) = (0 to 9).toList.map(i => OffsetDateTime.of(2000 + i, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(i)))

  lazy val List(
  zonedDateTime0,
  zonedDateTime1,
  zonedDateTime2,
  zonedDateTime3,
  zonedDateTime4,
  zonedDateTime5,
  zonedDateTime6,
  zonedDateTime7,
  zonedDateTime8,
  zonedDateTime9
  ) = (0 to 9).toList.map(i => ZonedDateTime.of(2000 + i, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(i)))

  lazy val List(uuid0, uuid1, uuid2, uuid3, uuid4, uuid5, uuid6, uuid7, uuid8, uuid9) =
    (0 to 9).toList.map(i => new UUID(0, i))

  lazy val (uri0, uri1, uri2, uri3, uri4, uri5, uri6, uri7, uri8, uri9) =
    (ur(0), ur(1), ur(2), ur(3), ur(4), ur(5), ur(6), ur(7), ur(8), ur(9))


  lazy val (char0, char1, char2, char3, char4, char5, char6, char7, char8, char9)
  : (Char, Char, Char, Char, Char, Char, Char, Char, Char, Char) =
    ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j')

  lazy val List(byte0, byte1, byte2, byte3, byte4, byte5, byte6, byte7, byte8, byte9) =
    (0 to 9).toList.map(_.toByte)

  lazy val List(short0, short1, short2, short3, short4, short5, short6, short7, short8, short9) =
    (0 to 9).toList.map(_.toShort)

  lazy val (ref0, ref1, ref2, ref3, ref4, ref5, ref6, ref7, ref8, ref9) =
    (r(0), r(1), r(2), r(3), r(4), r(5), r(6), r(7), r(8), r(9))

  lazy val toleranceBigDecimal: BigDecimal = BigDecimal(0.001)
  lazy val toleranceDouble    : Double     = 0.001
  lazy val toleranceFloat     : Float      = 0.001F
  lazy val toleranceBigInt    : BigInt     = BigInt(0)
  lazy val toleranceLong      : Long       = 0L
  lazy val toleranceInt       : Int        = 0
  lazy val toleranceShort     : Short      = 0.toShort
  lazy val toleranceByte      : Byte       = 0.toByte
}