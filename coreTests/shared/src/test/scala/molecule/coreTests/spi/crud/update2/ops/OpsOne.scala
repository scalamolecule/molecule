package molecule.coreTests.spi.crud.update2.ops

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

        // Applying value to non-asserted attribute adds the attribute in the update
        _ <- Ns(id).int(int1).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int1)

        // Update value to current value doesn't change anything
        _ <- Ns(id).int(int1).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int1)

        // Update value
        _ <- Ns(id).int(int2).update.transact
        _ <- Ns.int.query.get.map(_.head ==> int2)

        // Add new attribute and update value in one go
        _ <- Ns(id).s("foo").int(int3).update.transact
        _ <- Ns.i.s.int.query.get.map(_.head ==> (42, "foo", int3))

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
      // Not relevant for embedded documents without entity in Mongo
      if (database != "MongoDB") {
        for {
          id <- A.i(1).OwnB.i(7).save.transact.map(_.id)
          _ <- A.i.OwnB.i.query.get.map(_ ==> List((1, 7)))

          // Apply empty value to delete ref id of entity (entity remains)
          _ <- A(id).ownB().update.transact
          _ <- A.i.ownB_?.query.get.map(_ ==> List((1, None)))
        } yield ()
      }
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
      } yield ()
    }
  }
}
