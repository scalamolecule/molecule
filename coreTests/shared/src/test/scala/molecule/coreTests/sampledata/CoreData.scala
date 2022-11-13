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


  lazy val (n0, n1, n2, n3, n4, n5)                               = (0, 1, 2, 3, 4, 5)
  lazy val (string0, string1, string2, string3, string4, string5) = ("-", "a", "b", "c", "d", "e")
  lazy val (int0, int1, int2, int3, int4, int5)                   = (0, 1, 2, 3, 4, 5)
  lazy val (long0, long1, long2, long3, long4, long5)             = (0L, 1L, 2L, 3L, 4L, 5L)
  lazy val (float0, float1, float2, float3, float4, float5)       = (0.0f, 1.1f, 2.2f, 3.3f, 4.4f, 5.5f)
  lazy val (double0, double1, double2, double3, double4, double5) = (0.0, 1.1, 2.2, 3.3, 4.4, 5.5)
  lazy val (boolean0, boolean1)                                   = (false, true)
  lazy val (date0, date1, date2, date3, date4, date5)             = (da(0), da(1), da(2), da(3), da(4), da(5))
  lazy val (uri0, uri1, uri2, uri3, uri4, uri5)                   = (ur(0), ur(1), ur(2), ur(3), ur(4), ur(5))
  lazy val (bigInt0, bigInt1, bigInt2, bigInt3, bigInt4, bigInt5) = (bi(0), bi(1), bi(2), bi(3), bi(4), bi(5))
  lazy val (ref0, ref1, ref2, ref3, ref4, ref5)                   = (0L, 1L, 2L, 3L, 4L, 5L)

  lazy val (bigDecimal0, bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4, bigDecimal5) =
    (bd(0.0), bd(1.1), bd(2.2), bd(3.3), bd(4.4), bd(5.5))

  lazy val List(uuid0, uuid1, uuid2, uuid3, uuid4, uuid5) = List(uu, uu, uu, uu, uu, uu).sorted

  lazy val (char0, char1, char2, char3, char4, char5)      : (Char, Char, Char, Char, Char, Char)       = ('a', 'b', 'c', 'd', 'e', 'f')
  lazy val (byte0, byte1, byte2, byte3, byte4, byte5)      : (Byte, Byte, Byte, Byte, Byte, Byte)       = (0, 1, 2, 3, 4, 5)
  lazy val (short0, short1, short2, short3, short4, short5): (Short, Short, Short, Short, Short, Short) = (0, 1, 2, 3, 4, 5)
}