package molecule.db.compliance.test.arity

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class Arity23(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

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

  val tpl23a = (
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
  val tpl23b = (
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

  "save" - types { implicit conn =>
    for {
      _ <- Entity
        .string(string1) // string1 is a dummy String value, etc for all types...
        .int(int1)
        .long(long1)
        .float(float1)
        .double(double1)
        .boolean(boolean1)
        .bigInt(bigInt1)
        .bigDecimal(bigDecimal1)
        .date(date1)
        .duration(duration1)
        .instant(instant1)
        .localDate(localDate1)
        .localTime(localTime1)
        .localDateTime(localDateTime1)
        .offsetTime(offsetTime1)
        .offsetDateTime(offsetDateTime1)
        .zonedDateTime(zonedDateTime1)
        .uuid(uuid1)
        .uri(uri1)
        .byte(byte1)
        .short(short1)
        .char(char1)
        .i(int1)
        .save.transact

      _ <- ent23.query.get.map(_.head ==> tpl23a)
    } yield ()
  }


  "insert 1 row" - types { implicit conn =>
    for {
      _ <- ent23.insert(tpl23a).transact
      _ <- ent23.query.get.map(_ ==> List(tpl23a))
    } yield ()
  }

  "insert 2 rows" - types { implicit conn =>
    for {
      _ <- ent23.insert(tpl23a, tpl23b).transact
      _ <- ent23.query.get.map(_ ==> List(tpl23a, tpl23b))
    } yield ()
  }


  "update" - types { implicit conn =>
    for {
      id <- ent23.insert(tpl23a).transact.map(_.id)
      _ <- Entity(id)
        .string(string2) // string1 is a dummy String value, etc for all types...
        .int(int2)
        .long(long2)
        .float(float2)
        .double(double2)
        .boolean(boolean2)
        .bigInt(bigInt2)
        .bigDecimal(bigDecimal2)
        .date(date2)
        .duration(duration2)
        .instant(instant2)
        .localDate(localDate2)
        .localTime(localTime2)
        .localDateTime(localDateTime2)
        .offsetTime(offsetTime2)
        .offsetDateTime(offsetDateTime2)
        .zonedDateTime(zonedDateTime2)
        .uuid(uuid2)
        .uri(uri2)
        .byte(byte2)
        .short(short2)
        .char(char2)
        .i(int2).update.transact

      _ <- ent23.query.get.map(_ ==> List(tpl23b)) // values updated
    } yield ()
  }

}
