package molecule.coreTests.spi.action.update.ops

import molecule.base.error.ModelError
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Refs.{A, B}
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class OpsOne(
  suite: MUnitSuite,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "apply" - types { implicit conn =>
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Attribute not yet asserted
      _ <- Entity.int.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).int(int1).update.transact
      _ <- Entity.int.query.get.map(_ ==> Nil)

      // To insert the attribute value if not already asserted, use `upsert`
      _ <- Entity(id).int(int1).upsert.transact
      // Now the attribute value was inserted
      _ <- Entity.int.query.get.map(_.head ==> int1)

      // Update value to current value doesn't change anything
      _ <- Entity(id).int(int1).update.transact
      _ <- Entity.int.query.get.map(_.head ==> int1)

      // Update existing value
      _ <- Entity(id).int(int2).update.transact
      _ <- Entity.int.query.get.map(_.head ==> int2)

      // Upsert existing value - same effect as update
      _ <- Entity(id).int(int3).upsert.transact
      _ <- Entity.int.query.get.map(_.head ==> int3)

      // OBS: all attributes have to be asserted for any value to be updated!
      _ <- Entity(id).s("foo").int(int4).update.transact
      // Nothing is updated
      _ <- Entity.s_?.int.query.get.map(_.head ==> (None, int3))

      // Use upsert to guarantee that all values are updated/inserted
      _ <- Entity(id).s("foo").int(int4).upsert.transact
      _ <- Entity.s_?.int.query.get.map(_.head ==> (Some("foo"), int4))

      // Apply nothing to delete value
      _ <- Entity(id).int().update.transact
      _ <- Entity.int.query.get.map(_ ==> Nil)

      // Entity still has other attributes
      _ <- Entity.i.s.query.get.map(_.head ==> (42, "foo"))
    } yield ()
  }


  "Delete individual ref value(s) with update" - refs { implicit conn =>
    for {
      refId <- B.i(7).save.transact.map(_.id)
      id <- A.i.b.insert(1, refId).transact.map(_.id)
      _ <- A.i.b.query.get.map(_ ==> List((1, refId)))

      // Apply empty value to delete ref id of entity (entity remains)
      _ <- A(id).b().update.transact
      _ <- A.i.b_?.query.get.map(_ ==> List((1, None)))
    } yield ()
  }

  "Update multiple values" - types { implicit conn =>
    for {
      List(a, b, c) <- Entity.i.int_?.insert(
        (1, None),
        (1, Some(1)),
        (2, Some(2)),
      ).transact.map(_.ids)

      // Update all entities where i is 1
      _ <- Entity.i_(1).int(3).update.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.int_?.query.get.map(_ ==> List(
        (a, 1, None), //    no value to update
        (b, 1, Some(3)), // value updated
        (c, 2, Some(2)),
      ))

      // Upsert all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).int(4).upsert.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.int_?.query.get.map(_ ==> List(
        (a, 1, Some(4)), // attribute inserted
        (b, 1, Some(4)), // value updated
        (c, 2, Some(2)),
      ))
    } yield ()
  }

  "Can't update multiple values for one card-one attribute" - types { implicit conn =>
    for {
      _ <- Entity(42).int(2, 3).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Can only update one value for attribute `Entity.int`. Found: 2, 3"
        }
    } yield ()
  }


  "Types apply" - types { implicit conn =>
    for {
      List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

      id1 <- Entity.string(string1).save.transact.map(_.id)
      id2 <- Entity.int(int1).save.transact.map(_.id)
      id3 <- Entity.long(long1).save.transact.map(_.id)
      id4 <- Entity.float(float1).save.transact.map(_.id)
      id5 <- Entity.double(double1).save.transact.map(_.id)
      id6 <- Entity.boolean(boolean1).save.transact.map(_.id)
      id7 <- Entity.bigInt(bigInt1).save.transact.map(_.id)
      id8 <- Entity.bigDecimal(bigDecimal1).save.transact.map(_.id)
      id9 <- Entity.date(date1).save.transact.map(_.id)
      id10 <- Entity.duration(duration1).save.transact.map(_.id)
      id11 <- Entity.instant(instant1).save.transact.map(_.id)
      id12 <- Entity.localDate(localDate1).save.transact.map(_.id)
      id13 <- Entity.localTime(localTime1).save.transact.map(_.id)
      id14 <- Entity.localDateTime(localDateTime1).save.transact.map(_.id)
      id15 <- Entity.offsetTime(offsetTime1).save.transact.map(_.id)
      id16 <- Entity.offsetDateTime(offsetDateTime1).save.transact.map(_.id)
      id17 <- Entity.zonedDateTime(zonedDateTime1).save.transact.map(_.id)
      id18 <- Entity.uuid(uuid1).save.transact.map(_.id)
      id19 <- Entity.uri(uri1).save.transact.map(_.id)
      id20 <- Entity.byte(byte1).save.transact.map(_.id)
      id21 <- Entity.short(short1).save.transact.map(_.id)
      id22 <- Entity.char(char1).save.transact.map(_.id)
      id23 <- Entity.ref(ref1).save.transact.map(_.id)

      _ <- Entity(id1).string(string2).update.transact
      _ <- Entity(id2).int(int2).update.transact
      _ <- Entity(id3).long(long2).update.transact
      _ <- Entity(id4).float(float2).update.transact
      _ <- Entity(id5).double(double2).update.transact
      _ <- Entity(id6).boolean(boolean2).update.transact
      _ <- Entity(id7).bigInt(bigInt2).update.transact
      _ <- Entity(id8).bigDecimal(bigDecimal2).update.transact
      _ <- Entity(id9).date(date2).update.transact
      _ <- Entity(id10).duration(duration2).update.transact
      _ <- Entity(id11).instant(instant2).update.transact
      _ <- Entity(id12).localDate(localDate2).update.transact
      _ <- Entity(id13).localTime(localTime2).update.transact
      _ <- Entity(id14).localDateTime(localDateTime2).update.transact
      _ <- Entity(id15).offsetTime(offsetTime2).update.transact
      _ <- Entity(id16).offsetDateTime(offsetDateTime2).update.transact
      _ <- Entity(id17).zonedDateTime(zonedDateTime2).update.transact
      _ <- Entity(id18).uuid(uuid2).update.transact
      _ <- Entity(id19).uri(uri2).update.transact
      _ <- Entity(id20).byte(byte2).update.transact
      _ <- Entity(id21).short(short2).update.transact
      _ <- Entity(id22).char(char2).update.transact
      _ <- Entity(id23).ref(ref2).update.transact

      _ <- Entity.string.query.get.map(_.head ==> string2)
      _ <- Entity.int.query.get.map(_.head ==> int2)
      _ <- Entity.long.query.get.map(_.head ==> long2)
      _ <- Entity.float.query.get.map(_.head ==> float2)
      _ <- Entity.double.query.get.map(_.head ==> double2)
      _ <- Entity.boolean.query.get.map(_.head ==> boolean2)
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt2)
      _ <- Entity.bigDecimal.query.get.map(_.head ==> bigDecimal2)
      _ <- Entity.date.query.get.map(_.head ==> date2)
      _ <- Entity.duration.query.get.map(_.head ==> duration2)
      _ <- Entity.instant.query.get.map(_.head ==> instant2)
      _ <- Entity.localDate.query.get.map(_.head ==> localDate2)
      _ <- Entity.localTime.query.get.map(_.head ==> localTime2)
      _ <- Entity.localDateTime.query.get.map(_.head ==> localDateTime2)
      _ <- Entity.offsetTime.query.get.map(_.head ==> offsetTime2)
      _ <- Entity.offsetDateTime.query.get.map(_.head ==> offsetDateTime2)
      _ <- Entity.zonedDateTime.query.get.map(_.head ==> zonedDateTime2)
      _ <- Entity.uuid.query.get.map(_.head ==> uuid2)
      _ <- Entity.uri.query.get.map(_.head ==> uri2)
      _ <- Entity.byte.query.get.map(_.head ==> byte2)
      _ <- Entity.short.query.get.map(_.head ==> short2)
      _ <- Entity.char.query.get.map(_.head ==> char2)
      _ <- Entity.ref.query.get.map(_.head ==> ref2)

      _ <- Entity(id1).string(string3).upsert.transact
      _ <- Entity(id2).int(int3).upsert.transact
      _ <- Entity(id3).long(long3).upsert.transact
      _ <- Entity(id4).float(float3).upsert.transact
      _ <- Entity(id5).double(double3).upsert.transact
      _ <- Entity(id6).boolean(boolean3).upsert.transact
      _ <- Entity(id7).bigInt(bigInt3).upsert.transact
      _ <- Entity(id8).bigDecimal(bigDecimal3).upsert.transact
      _ <- Entity(id9).date(date3).upsert.transact
      _ <- Entity(id10).duration(duration3).upsert.transact
      _ <- Entity(id11).instant(instant3).upsert.transact
      _ <- Entity(id12).localDate(localDate3).upsert.transact
      _ <- Entity(id13).localTime(localTime3).upsert.transact
      _ <- Entity(id14).localDateTime(localDateTime3).upsert.transact
      _ <- Entity(id15).offsetTime(offsetTime3).upsert.transact
      _ <- Entity(id16).offsetDateTime(offsetDateTime3).upsert.transact
      _ <- Entity(id17).zonedDateTime(zonedDateTime3).upsert.transact
      _ <- Entity(id18).uuid(uuid3).upsert.transact
      _ <- Entity(id19).uri(uri3).upsert.transact
      _ <- Entity(id20).byte(byte3).upsert.transact
      _ <- Entity(id21).short(short3).upsert.transact
      _ <- Entity(id22).char(char3).upsert.transact
      _ <- Entity(id23).ref(ref3).upsert.transact

      _ <- Entity.string.query.get.map(_.head ==> string3)
      _ <- Entity.int.query.get.map(_.head ==> int3)
      _ <- Entity.long.query.get.map(_.head ==> long3)
      _ <- Entity.float.query.get.map(_.head ==> float3)
      _ <- Entity.double.query.get.map(_.head ==> double3)
      _ <- Entity.boolean.query.get.map(_.head ==> boolean3)
      _ <- Entity.bigInt.query.get.map(_.head ==> bigInt3)
      _ <- Entity.bigDecimal.query.get.map(_.head ==> bigDecimal3)
      _ <- Entity.date.query.get.map(_.head ==> date3)
      _ <- Entity.duration.query.get.map(_.head ==> duration3)
      _ <- Entity.instant.query.get.map(_.head ==> instant3)
      _ <- Entity.localDate.query.get.map(_.head ==> localDate3)
      _ <- Entity.localTime.query.get.map(_.head ==> localTime3)
      _ <- Entity.localDateTime.query.get.map(_.head ==> localDateTime3)
      _ <- Entity.offsetTime.query.get.map(_.head ==> offsetTime3)
      _ <- Entity.offsetDateTime.query.get.map(_.head ==> offsetDateTime3)
      _ <- Entity.zonedDateTime.query.get.map(_.head ==> zonedDateTime3)
      _ <- Entity.uuid.query.get.map(_.head ==> uuid3)
      _ <- Entity.uri.query.get.map(_.head ==> uri3)
      _ <- Entity.byte.query.get.map(_.head ==> byte3)
      _ <- Entity.short.query.get.map(_.head ==> short3)
      _ <- Entity.char.query.get.map(_.head ==> char3)
      _ <- Entity.ref.query.get.map(_.head ==> ref3)
    } yield ()
  }
}
