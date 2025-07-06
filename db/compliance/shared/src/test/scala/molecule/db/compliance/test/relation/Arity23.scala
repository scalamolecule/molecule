package molecule.db.compliance.test.relation

import molecule.core.setup.TestUtils
import molecule.db.compliance.domains.dsl.Types.*

trait Arity23 extends TestUtils {

  val ent23 = Entity
    .string
    .int
    .long
    .float
    .double
    .boolean
    .bigInt
    .bigDecimal
    .date
    .duration
    .instant
    .localDate
    .localTime
    .localDateTime
    .offsetTime
    .offsetDateTime
    .zonedDateTime
    .uuid
    .uri
    .byte
    .short
    .char
    .i

  val ref23 = Ref
    .string
    .int
    .long
    .float
    .double
    .boolean
    .bigInt
    .bigDecimal
    .date
    .duration
    .instant
    .localDate
    .localTime
    .localDateTime
    .offsetTime
    .offsetDateTime
    .zonedDateTime
    .uuid
    .uri
    .byte
    .short
    .char
    .i

  val entOptRef23 = Entity.i.Ref.?(
    Ref
      .string
      .int
      .long
      .float
      .double
      .boolean
      .bigInt
      .bigDecimal
      .date
      .duration
      .instant
      .localDate
      .localTime
      .localDateTime
      .offsetTime
      .offsetDateTime
      .zonedDateTime
      .uuid
      .uri
      .byte
      .short
      .char
      .i
  )

  val tpl23_1 = (
    string1,
    int1,
    long1,
    float1,
    double1,
    boolean1,
    bigInt1,
    bigDecimal1,
    date1,
    duration1,
    instant1,
    localDate1,
    localTime1,
    localDateTime1,
    offsetTime1,
    offsetDateTime1,
    zonedDateTime1,
    uuid1,
    uri1,
    byte1,
    short1,
    char1,
    int1,
  )
  val tpl23_2 = (
    string2,
    int2,
    long2,
    float2,
    double2,
    boolean2,
    bigInt2,
    bigDecimal2,
    date2,
    duration2,
    instant2,
    localDate2,
    localTime2,
    localDateTime2,
    offsetTime2,
    offsetDateTime2,
    zonedDateTime2,
    uuid2,
    uri2,
    byte2,
    short2,
    char2,
    int2,
  )
}
