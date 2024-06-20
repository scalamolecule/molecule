package molecule.coreTests.spi.crud.update.ops

import molecule.base.error.ExecutionError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OpsOne extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "apply" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)

        // Attribute not yet asserted
        _ <- Ns.int.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).int(int1).update.transact
        _ <- Ns.int.query.get.map(_ ==> Nil)

        // To insert the attribute value if not already asserted, use `upsert`
        _ <- Ns(id).int(int1).upsert.transact
        // Now the attribute value was inserted
        _ <- Ns.int.query.get.map(_.head ==> int1)

        // Update value to current value doesn't change anything
        _ <- Ns(id).int(int1).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int1)

        // Update existing value
        _ <- Ns(id).int(int2).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int2)

        // Upsert existing value - same effect as update
        _ <- Ns(id).int(int3).upsert.transact
        _ <- Ns.int.query.get.map(_.head ==> int3)

        // OBS: all attributes have to be asserted for any value to be updated!
        _ <- Ns(id).s("foo").int(int4).update.transact
        // Nothing is updated
        _ <- Ns.s_?.int.query.get.map(_.head ==> (None, int3))

        // Use upsert to guarantee that all values are updated/inserted
        _ <- Ns(id).s("foo").int(int4).upsert.transact
        _ <- Ns.s_?.int.query.get.map(_.head ==> (Some("foo"), int4))

        // Apply nothing to delete value
        _ <- Ns(id).int().update.transact
        _ <- Ns.int.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
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

    "Delete individual owned ref value(s) with update" - refs { implicit conn =>
      for {
        id <- A.i(1).OwnB.i(7).save.transact.map(_.id)
        _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 7)))

        // Apply empty value to delete ref id of entity (entity remains)
        _ <- A(id).ownB().update.transact
        _ <- A.i.ownB_?.query.get.map(_ ==> List((1, None)))
      } yield ()
    }

    "Update multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.int_?.insert(
          (1, None),
          (1, Some(1)),
          (2, Some(2)),
        ).transact.map(_.ids)

        // Update all entities where i is 1
        _ <- Ns.i_(1).int(3).update.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.int_?.query.get.map(_ ==> List(
          (a, 1, None), //    no value to update
          (b, 1, Some(3)), // value updated
          (c, 2, Some(2)),
        ))

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).int(4).upsert.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.int_?.query.get.map(_ ==> List(
          (a, 1, Some(4)), // attribute inserted
          (b, 1, Some(4)), // value updated
          (c, 2, Some(2)),
        ))
      } yield ()
    }

    "Can't update multiple values for one card-one attribute" - types { implicit conn =>
      for {
        _ <- Ns("42").int(2, 3).update.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only update one value for attribute `Ns.int`. Found: 2, 3"
          }
      } yield ()
    }


    "Types apply" - types { implicit conn =>
      for {
        List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

        id1 <- Ns.string(string1).save.transact.map(_.id)
        id2 <- Ns.int(int1).save.transact.map(_.id)
        id3 <- Ns.long(long1).save.transact.map(_.id)
        id4 <- Ns.float(float1).save.transact.map(_.id)
        id5 <- Ns.double(double1).save.transact.map(_.id)
        id6 <- Ns.boolean(boolean1).save.transact.map(_.id)
        id7 <- Ns.bigInt(bigInt1).save.transact.map(_.id)
        id8 <- Ns.bigDecimal(bigDecimal1).save.transact.map(_.id)
        id9 <- Ns.date(date1).save.transact.map(_.id)
        id10 <- Ns.duration(duration1).save.transact.map(_.id)
        id11 <- Ns.instant(instant1).save.transact.map(_.id)
        id12 <- Ns.localDate(localDate1).save.transact.map(_.id)
        id13 <- Ns.localTime(localTime1).save.transact.map(_.id)
        id14 <- Ns.localDateTime(localDateTime1).save.transact.map(_.id)
        id15 <- Ns.offsetTime(offsetTime1).save.transact.map(_.id)
        id16 <- Ns.offsetDateTime(offsetDateTime1).save.transact.map(_.id)
        id17 <- Ns.zonedDateTime(zonedDateTime1).save.transact.map(_.id)
        id18 <- Ns.uuid(uuid1).save.transact.map(_.id)
        id19 <- Ns.uri(uri1).save.transact.map(_.id)
        id20 <- Ns.byte(byte1).save.transact.map(_.id)
        id21 <- Ns.short(short1).save.transact.map(_.id)
        id22 <- Ns.char(char1).save.transact.map(_.id)
        id23 <- Ns.ref(ref1).save.transact.map(_.id)

        _ <- Ns(id1).string(string2).update.transact
        _ <- Ns(id2).int(int2).update.transact
        _ <- Ns(id3).long(long2).update.transact
        _ <- Ns(id4).float(float2).update.transact
        _ <- Ns(id5).double(double2).update.transact
        _ <- Ns(id6).boolean(boolean2).update.transact
        _ <- Ns(id7).bigInt(bigInt2).update.transact
        _ <- Ns(id8).bigDecimal(bigDecimal2).update.transact
        _ <- Ns(id9).date(date2).update.transact
        _ <- Ns(id10).duration(duration2).update.transact
        _ <- Ns(id11).instant(instant2).update.transact
        _ <- Ns(id12).localDate(localDate2).update.transact
        _ <- Ns(id13).localTime(localTime2).update.transact
        _ <- Ns(id14).localDateTime(localDateTime2).update.transact
        _ <- Ns(id15).offsetTime(offsetTime2).update.transact
        _ <- Ns(id16).offsetDateTime(offsetDateTime2).update.transact
        _ <- Ns(id17).zonedDateTime(zonedDateTime2).update.transact
        _ <- Ns(id18).uuid(uuid2).update.transact
        _ <- Ns(id19).uri(uri2).update.transact
        _ <- Ns(id20).byte(byte2).update.transact
        _ <- Ns(id21).short(short2).update.transact
        _ <- Ns(id22).char(char2).update.transact
        _ <- Ns(id23).ref(ref2).update.transact

        _ <- Ns.string.query.get.map(_.head ==> string2)
        _ <- Ns.int.query.get.map(_.head ==> int2)
        _ <- Ns.long.query.get.map(_.head ==> long2)
        _ <- Ns.float.query.get.map(_.head ==> float2)
        _ <- Ns.double.query.get.map(_.head ==> double2)
        _ <- Ns.boolean.query.get.map(_.head ==> boolean2)
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt2)
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal2)
        _ <- Ns.date.query.get.map(_.head ==> date2)
        _ <- Ns.duration.query.get.map(_.head ==> duration2)
        _ <- Ns.instant.query.get.map(_.head ==> instant2)
        _ <- Ns.localDate.query.get.map(_.head ==> localDate2)
        _ <- Ns.localTime.query.get.map(_.head ==> localTime2)
        _ <- Ns.localDateTime.query.get.map(_.head ==> localDateTime2)
        _ <- Ns.offsetTime.query.get.map(_.head ==> offsetTime2)
        _ <- Ns.offsetDateTime.query.get.map(_.head ==> offsetDateTime2)
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime2)
        _ <- Ns.uuid.query.get.map(_.head ==> uuid2)
        _ <- Ns.uri.query.get.map(_.head ==> uri2)
        _ <- Ns.byte.query.get.map(_.head ==> byte2)
        _ <- Ns.short.query.get.map(_.head ==> short2)
        _ <- Ns.char.query.get.map(_.head ==> char2)
        _ <- Ns.ref.query.get.map(_.head ==> ref2)

        _ <- Ns(id1).string(string3).upsert.transact
        _ <- Ns(id2).int(int3).upsert.transact
        _ <- Ns(id3).long(long3).upsert.transact
        _ <- Ns(id4).float(float3).upsert.transact
        _ <- Ns(id5).double(double3).upsert.transact
        _ <- Ns(id6).boolean(boolean3).upsert.transact
        _ <- Ns(id7).bigInt(bigInt3).upsert.transact
        _ <- Ns(id8).bigDecimal(bigDecimal3).upsert.transact
        _ <- Ns(id9).date(date3).upsert.transact
        _ <- Ns(id10).duration(duration3).upsert.transact
        _ <- Ns(id11).instant(instant3).upsert.transact
        _ <- Ns(id12).localDate(localDate3).upsert.transact
        _ <- Ns(id13).localTime(localTime3).upsert.transact
        _ <- Ns(id14).localDateTime(localDateTime3).upsert.transact
        _ <- Ns(id15).offsetTime(offsetTime3).upsert.transact
        _ <- Ns(id16).offsetDateTime(offsetDateTime3).upsert.transact
        _ <- Ns(id17).zonedDateTime(zonedDateTime3).upsert.transact
        _ <- Ns(id18).uuid(uuid3).upsert.transact
        _ <- Ns(id19).uri(uri3).upsert.transact
        _ <- Ns(id20).byte(byte3).upsert.transact
        _ <- Ns(id21).short(short3).upsert.transact
        _ <- Ns(id22).char(char3).upsert.transact
        _ <- Ns(id23).ref(ref3).upsert.transact

        _ <- Ns.string.query.get.map(_.head ==> string3)
        _ <- Ns.int.query.get.map(_.head ==> int3)
        _ <- Ns.long.query.get.map(_.head ==> long3)
        _ <- Ns.float.query.get.map(_.head ==> float3)
        _ <- Ns.double.query.get.map(_.head ==> double3)
        _ <- Ns.boolean.query.get.map(_.head ==> boolean3)
        _ <- Ns.bigInt.query.get.map(_.head ==> bigInt3)
        _ <- Ns.bigDecimal.query.get.map(_.head ==> bigDecimal3)
        _ <- Ns.date.query.get.map(_.head ==> date3)
        _ <- Ns.duration.query.get.map(_.head ==> duration3)
        _ <- Ns.instant.query.get.map(_.head ==> instant3)
        _ <- Ns.localDate.query.get.map(_.head ==> localDate3)
        _ <- Ns.localTime.query.get.map(_.head ==> localTime3)
        _ <- Ns.localDateTime.query.get.map(_.head ==> localDateTime3)
        _ <- Ns.offsetTime.query.get.map(_.head ==> offsetTime3)
        _ <- Ns.offsetDateTime.query.get.map(_.head ==> offsetDateTime3)
        _ <- Ns.zonedDateTime.query.get.map(_.head ==> zonedDateTime3)
        _ <- Ns.uuid.query.get.map(_.head ==> uuid3)
        _ <- Ns.uri.query.get.map(_.head ==> uri3)
        _ <- Ns.byte.query.get.map(_.head ==> byte3)
        _ <- Ns.short.query.get.map(_.head ==> short3)
        _ <- Ns.char.query.get.map(_.head ==> char3)
        _ <- Ns.ref.query.get.map(_.head ==> ref3)
      } yield ()
    }
  }
}
