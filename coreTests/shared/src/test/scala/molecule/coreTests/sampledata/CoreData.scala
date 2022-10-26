package molecule.coreTests.sampledata

import java.net.URI
import java.util.UUID._
import java.util.{Date, UUID}
import molecule.base.util.DateHandling

trait CoreData extends DateHandling {

  private def da(i: Int): Date = {
    // Alternate between winter/summer time to test daylight savings too
    val month = i % 2 * 6 + 1
    str2date((2000 + i).toString + "-" + month)
  }
  private def uu: UUID = randomUUID()
  private def ur(i: Int): URI = new URI("uri" + i)
  private def bi(i: Int): BigInt = BigInt(i)
  private def bd(d: Double): BigDecimal = BigDecimal(d)
  private def r(i: Int): Long = 17194139534365L + i // dummy ref/entity id

  // Sample data

  lazy val (n0, n1, n2, n3, n4)                               = (0, 1, 2, 3, 4)
  lazy val (string0, string1, string2, string3, string4)      = ("-", "a", "b", "c", "d")
  lazy val (int0, int1, int2, int3, int4)                     = (0, 1, 2, 3, 4)
  lazy val (long0, long1, long2, long3, long4)                = (0L, 1L, 2L, 3L, 4L)
  lazy val (float0, float1, float2, float3, float4)           = (0.0f, 1.1f, 2.2f, 3.3f, 4.4f)
  lazy val (double0, double1, double2, double3, double4)      = (0.0, 1.1, 2.2, 3.3, 4.4)
  lazy val (boolean0, boolean1, boolean2, boolean3, boolean4) = (false, true, false, true, false)
  lazy val (date0, date1, date2, date3, date4, date5)         = (da(0), da(1), da(2), da(3), da(4), da(5))
  lazy val (uri0, uri1, uri2, uri3, uri4, uri5)               = (ur(0), ur(1), ur(2), ur(3), ur(4), ur(5))
  lazy val (bigInt0, bigInt1, bigInt2, bigInt3, bigInt4)      = (bi(0), bi(1), bi(2), bi(3), bi(4))

  lazy val (bigDecimal0, bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4) =
    (bd(0.0), bd(1.1), bd(2.2), bd(3.3), bd(4.4))

  lazy val List(uuid0, uuid1, uuid2, uuid3, uuid4) = List(uu, uu, uu, uu, uu).sortBy(_.toString)

  lazy val (char0, char1, char2, char3, char4)     : (Char, Char, Char, Char, Char)      = ('a', 'b', 'c', 'd', 'e')
  lazy val (byte0, byte1, byte2, byte3, byte4)     : (Byte, Byte, Byte, Byte, Byte)      = (1, 2, 3, 4, 5)
  lazy val (short0, short1, short2, short3, short4): (Short, Short, Short, Short, Short) = (1, 2, 3, 4, 5)

  lazy val List(r0, r1, r2, r3, r4) = List(r(0), r(1), r(2), r(3), r(4))


  //  lazy val (strs0, ints0, longs0, doubles0, bools0, dates0, uuids0, uris0, bigInts0, bigDecs0, enums0, rs0) = (
  //    Set(str0), Set(int0), Set(long0), Set(double0), Set(bool0), Set(date0), Set(uuid0), Set(uri0), Set(bigInt0), Set(bigDec0), Set(enum0), Set(r0)
  //  )
  //
  //  lazy val (strs1, ints1, longs1, doubles1, bools1, dates1, uuids1, uris1, bigInts1, bigDecs1, enums1, rs1) = (
  //    Set(str1), Set(int1), Set(long1), Set(double1), Set(bool1), Set(date1), Set(uuid1), Set(uri1), Set(bigInt1), Set(bigDec1), Set(enum1), Set(r1)
  //  )
  //
  //  lazy val (strs2, ints2, longs2, doubles2, bools2, dates2, uuids2, uris2, bigInts2, bigDecs2, enums2, rs2) = (
  //    Set(str1, str2), Set(int1, int2), Set(long1, long2), Set(double1, double2),
  //    Set(bool1, bool2), Set(date1, date2), Set(uuid1, uuid2), Set(uri1, uri2), Set(bigInt1, bigInt2), Set(bigDec1, bigDec2), Set(enum1, enum2), Set(r1, r2)
  //  )


  protected def round(value: Double, decimals: Int = 6): Double = {
    val factor = scala.math.pow(10, decimals)
    (value * factor).round / factor
  }
}