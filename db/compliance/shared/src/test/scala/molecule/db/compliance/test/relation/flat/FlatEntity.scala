package molecule.db.compliance.test.relation.flat

import molecule.core.setup.MUnit
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.compliance.test.relation.Arity23
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FlatEntity(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends Arity23 {

  import api.*
  import suite.*

  "Arity 23 save" - types { implicit conn =>
    for {
      _ <- Entity
        .string(string1)
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

      _ <- ent23.query.get.map(_.head ==> tpl23_1)
    } yield ()
  }


  "Arity 23 insert" - types { implicit conn =>
    for {
      _ <- ent23.insert(tpl23_1).transact
      _ <- ent23.query.get.map(_ ==> List(tpl23_1))
    } yield ()
  }


  "Arity 23 update" - types { implicit conn =>
    for {
      id <- ent23.insert(tpl23_1).transact.map(_.id)
      _ <- Entity(id)
        .string(string2)
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

      _ <- ent23.query.get.map(_ ==> List(tpl23_2)) // values updated
    } yield ()
  }

}
