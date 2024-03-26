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
  private def r(i: Int): String = if (database == "MongoDB") "12345678901234567890123" + i else s"$i"

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

  lazy val (boolean0, boolean1, boolean2, boolean3, boolean4, boolean5, boolean6, boolean7, boolean8, boolean9) =
    (false, false, true, false, true, false, true, false, true, false) // make boolean1 sort before boolean2 (false before true)

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

  lazy val (pstring0, pstring1, pstring2, pstring3, pstring4, pstring5, pstring6, pstring7, pstring8, pstring9)                                                                                 = ("-" -> string0, "a" -> string1, "b" -> string2, "c" -> string3, "d" -> string4, "e" -> string5, "f" -> string6, "g" -> string7, "h" -> string8, "i" -> string9)
  lazy val (pint0, pint1, pint2, pint3, pint4, pint5, pint6, pint7, pint8, pint9)                                                                                                               = ("-" -> int0, "a" -> int1, "b" -> int2, "c" -> int3, "d" -> int4, "e" -> int5, "f" -> int6, "g" -> int7, "h" -> int8, "i" -> int9)
  lazy val (plong0, plong1, plong2, plong3, plong4, plong5, plong6, plong7, plong8, plong9)                                                                                                     = ("-" -> long0, "a" -> long1, "b" -> long2, "c" -> long3, "d" -> long4, "e" -> long5, "f" -> long6, "g" -> long7, "h" -> long8, "i" -> long9)
  lazy val (pfloat0, pfloat1, pfloat2, pfloat3, pfloat4, pfloat5, pfloat6, pfloat7, pfloat8, pfloat9)                                                                                           = ("-" -> float0, "a" -> float1, "b" -> float2, "c" -> float3, "d" -> float4, "e" -> float5, "f" -> float6, "g" -> float7, "h" -> float8, "i" -> float9)
  lazy val (pdouble0, pdouble1, pdouble2, pdouble3, pdouble4, pdouble5, pdouble6, pdouble7, pdouble8, pdouble9)                                                                                 = ("-" -> double0, "a" -> double1, "b" -> double2, "c" -> double3, "d" -> double4, "e" -> double5, "f" -> double6, "g" -> double7, "h" -> double8, "i" -> double9)
  lazy val (pboolean0, pboolean1, pboolean2, pboolean3, pboolean4, pboolean5, pboolean6, pboolean7, pboolean8, pboolean9)                                                                       = ("-" -> boolean0, "a" -> boolean1, "b" -> boolean2, "c" -> boolean3, "d" -> boolean4, "e" -> boolean5, "f" -> boolean6, "g" -> boolean7, "h" -> boolean8, "i" -> boolean9)
  lazy val (pbigInt0, pbigInt1, pbigInt2, pbigInt3, pbigInt4, pbigInt5, pbigInt6, pbigInt7, pbigInt8, pbigInt9)                                                                                 = ("-" -> bigInt0, "a" -> bigInt1, "b" -> bigInt2, "c" -> bigInt3, "d" -> bigInt4, "e" -> bigInt5, "f" -> bigInt6, "g" -> bigInt7, "h" -> bigInt8, "i" -> bigInt9)
  lazy val (pbigDecimal0, pbigDecimal1, pbigDecimal2, pbigDecimal3, pbigDecimal4, pbigDecimal5, pbigDecimal6, pbigDecimal7, pbigDecimal8, pbigDecimal9)                                         = ("-" -> bigDecimal0, "a" -> bigDecimal1, "b" -> bigDecimal2, "c" -> bigDecimal3, "d" -> bigDecimal4, "e" -> bigDecimal5, "f" -> bigDecimal6, "g" -> bigDecimal7, "h" -> bigDecimal8, "i" -> bigDecimal9)
  lazy val (pdate0, pdate1, pdate2, pdate3, pdate4, pdate5, pdate6, pdate7, pdate8, pdate9)                                                                                                     = ("-" -> date0, "a" -> date1, "b" -> date2, "c" -> date3, "d" -> date4, "e" -> date5, "f" -> date6, "g" -> date7, "h" -> date8, "i" -> date9)
  lazy val (pduration0, pduration1, pduration2, pduration3, pduration4, pduration5, pduration6, pduration7, pduration8, pduration9)                                                             = ("-" -> duration0, "a" -> duration1, "b" -> duration2, "c" -> duration3, "d" -> duration4, "e" -> duration5, "f" -> duration6, "g" -> duration7, "h" -> duration8, "i" -> duration9)
  lazy val (pinstant0, pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6, pinstant7, pinstant8, pinstant9)                                                                       = ("-" -> instant0, "a" -> instant1, "b" -> instant2, "c" -> instant3, "d" -> instant4, "e" -> instant5, "f" -> instant6, "g" -> instant7, "h" -> instant8, "i" -> instant9)
  lazy val (plocalDate0, plocalDate1, plocalDate2, plocalDate3, plocalDate4, plocalDate5, plocalDate6, plocalDate7, plocalDate8, plocalDate9)                                                   = ("-" -> localDate0, "a" -> localDate1, "b" -> localDate2, "c" -> localDate3, "d" -> localDate4, "e" -> localDate5, "f" -> localDate6, "g" -> localDate7, "h" -> localDate8, "i" -> localDate9)
  lazy val (plocalTime0, plocalTime1, plocalTime2, plocalTime3, plocalTime4, plocalTime5, plocalTime6, plocalTime7, plocalTime8, plocalTime9)                                                   = ("-" -> localTime0, "a" -> localTime1, "b" -> localTime2, "c" -> localTime3, "d" -> localTime4, "e" -> localTime5, "f" -> localTime6, "g" -> localTime7, "h" -> localTime8, "i" -> localTime9)
  lazy val (plocalDateTime0, plocalDateTime1, plocalDateTime2, plocalDateTime3, plocalDateTime4, plocalDateTime5, plocalDateTime6, plocalDateTime7, plocalDateTime8, plocalDateTime9)           = ("-" -> localDateTime0, "a" -> localDateTime1, "b" -> localDateTime2, "c" -> localDateTime3, "d" -> localDateTime4, "e" -> localDateTime5, "f" -> localDateTime6, "g" -> localDateTime7, "h" -> localDateTime8, "i" -> localDateTime9)
  lazy val (poffsetTime0, poffsetTime1, poffsetTime2, poffsetTime3, poffsetTime4, poffsetTime5, poffsetTime6, poffsetTime7, poffsetTime8, poffsetTime9)                                         = ("-" -> offsetTime0, "a" -> offsetTime1, "b" -> offsetTime2, "c" -> offsetTime3, "d" -> offsetTime4, "e" -> offsetTime5, "f" -> offsetTime6, "g" -> offsetTime7, "h" -> offsetTime8, "i" -> offsetTime9)
  lazy val (poffsetDateTime0, poffsetDateTime1, poffsetDateTime2, poffsetDateTime3, poffsetDateTime4, poffsetDateTime5, poffsetDateTime6, poffsetDateTime7, poffsetDateTime8, poffsetDateTime9) = ("-" -> offsetDateTime0, "a" -> offsetDateTime1, "b" -> offsetDateTime2, "c" -> offsetDateTime3, "d" -> offsetDateTime4, "e" -> offsetDateTime5, "f" -> offsetDateTime6, "g" -> offsetDateTime7, "h" -> offsetDateTime8, "i" -> offsetDateTime9)
  lazy val (pzonedDateTime0, pzonedDateTime1, pzonedDateTime2, pzonedDateTime3, pzonedDateTime4, pzonedDateTime5, pzonedDateTime6, pzonedDateTime7, pzonedDateTime8, pzonedDateTime9)           = ("-" -> zonedDateTime0, "a" -> zonedDateTime1, "b" -> zonedDateTime2, "c" -> zonedDateTime3, "d" -> zonedDateTime4, "e" -> zonedDateTime5, "f" -> zonedDateTime6, "g" -> zonedDateTime7, "h" -> zonedDateTime8, "i" -> zonedDateTime9)
  lazy val (puuid0, puuid1, puuid2, puuid3, puuid4, puuid5, puuid6, puuid7, puuid8, puuid9)                                                                                                     = ("-" -> uuid0, "a" -> uuid1, "b" -> uuid2, "c" -> uuid3, "d" -> uuid4, "e" -> uuid5, "f" -> uuid6, "g" -> uuid7, "h" -> uuid8, "i" -> uuid9)
  lazy val (puri0, puri1, puri2, puri3, puri4, puri5, puri6, puri7, puri8, puri9)                                                                                                               = ("-" -> uri0, "a" -> uri1, "b" -> uri2, "c" -> uri3, "d" -> uri4, "e" -> uri5, "f" -> uri6, "g" -> uri7, "h" -> uri8, "i" -> uri9)
  lazy val (pbyte0, pbyte1, pbyte2, pbyte3, pbyte4, pbyte5, pbyte6, pbyte7, pbyte8, pbyte9)                                                                                                     = ("-" -> byte0, "a" -> byte1, "b" -> byte2, "c" -> byte3, "d" -> byte4, "e" -> byte5, "f" -> byte6, "g" -> byte7, "h" -> byte8, "i" -> byte9)
  lazy val (pshort0, pshort1, pshort2, pshort3, pshort4, pshort5, pshort6, pshort7, pshort8, pshort9)                                                                                           = ("-" -> short0, "a" -> short1, "b" -> short2, "c" -> short3, "d" -> short4, "e" -> short5, "f" -> short6, "g" -> short7, "h" -> short8, "i" -> short9)
  lazy val (pchar0, pchar1, pchar2, pchar3, pchar4, pchar5, pchar6, pchar7, pchar8, pchar9)                                                                                                     = ("-" -> char0, "a" -> char1, "b" -> char2, "c" -> char3, "d" -> char4, "e" -> char5, "f" -> char6, "g" -> char7, "h" -> char8, "i" -> char9)
}