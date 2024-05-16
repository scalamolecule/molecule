package molecule.coreTests.spi.crud.update.ops

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait OpsMap extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Valid keys" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map("a" -> int0)).save.transact.map(_.id)

        // Allowed characters in a key name
        _ <- Ns(id).intMap(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).update.transact

        // No spaces
        _ <- Ns(id).intMap(Map("foo bar" -> 1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }

        // No special characters
        _ <- Ns(id).intMap(Map("foo:" -> 1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }
        _ <- Ns(id).intMap.add("bar?" -> 2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }
      } yield ()
    }

    "apply" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).intMap(Map(pint1, pint2)).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // To insert the attribute value if not already asserted, use `upsert`
        _ <- Ns(id).intMap(Map(pint1, pint2)).upsert.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Applying Map of pairs replaces previous Map
        _ <- Ns(id).intMap(Map(pint2, pint3)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint2, pint3))

        // OBS: all attributes have to be asserted for any value to be updated!
        _ <- Ns(id).s("foo").intMap(Map(pint3, pint4)).update.transact
        // Nothing is updated
        _ <- Ns.s_?.intMap.query.get.map(_.head ==> (None, Map(pint2, pint3)))

        // Use upsert to guarantee that all values are updated/inserted
        _ <- Ns(id).s("foo").intMap(Map(pint3, pint4)).upsert.transact
        _ <- Ns.s_?.intMap.query.get.map(_.head ==> (Some("foo"), Map(pint3, pint4)))

        // Applying empty Map of pairs deletes map
        _ <- Ns(id).intMap(Map.empty[String, Int]).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        _ <- Ns(id).intMap(Map(pint1, pint2)).update.transact
        // Apply nothing to delete attribute
        _ <- Ns(id).intMap().update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Entity still has other attributes
        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))
      } yield ()
    }


    "add" - types { implicit conn =>
      for {
        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // When attribute is not already asserted, an update has no effect
        _ <- Ns(id).intMap.add("a" -> int0).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // To add values to the attribute if not already asserted, use `upsert`
        _ <- Ns(id).intMap.add("a" -> int0).upsert.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map("a" -> int0))

        // Adding existing pair to Map changes nothing
        _ <- Ns(id).intMap.add("a" -> int0).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map("a" -> int0))

        // Adding pair with existing key replaces the value of the pair
        _ <- Ns(id).intMap.add("a" -> int1).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1)) // "a" -> int1

        // Add new pair
        _ <- Ns(id).intMap.add(pint2).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))

        // Add multiple pairs with varargs
        _ <- Ns(id).intMap.add(pint3, pint4).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4))

        // Add multiple pairs with Iterable
        _ <- Ns(id).intMap.add(List(pint5, pint6)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Adding empty Iterable of pairs has no effect
        _ <- Ns(id).intMap.add(Vector.empty[(String, Int)]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
      } yield ()
    }


    "remove" - types { implicit conn =>
      // No semantic difference between update/upsert when removing
      for {
        id <- Ns.i(42).save.transact.map(_.id)

        // Map attribute not yet asserted
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Removing pair by key from non-asserted Map has no effect
        _ <- Ns(id).intMap.remove(string1).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // Start with some pairs
        _ <- Ns(id).intMap.add(pint1, pint2, pint3, pint4, pint5, pint6, pint7).upsert.transact

        // Remove pair by String key
        _ <- Ns(id).intMap.remove(string7).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing non-existing key has no effect
        _ <- Ns(id).intMap.remove(string9).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

        // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
        _ <- Ns(id).intMap.remove(string6, string6).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5))

        // Remove multiple pairs by varargs of keys
        _ <- Ns(id).intMap.remove(string4, string5).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))

        // Remove multiple pairs by Seq of keys (not Iterable)
        _ <- Ns(id).intMap.remove(List(string2, string3)).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing pairs with empty Seq of keys has no effect
        _ <- Ns(id).intMap.remove(Seq.empty[String]).update.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).intMap.remove(Seq(string1)).update.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
      } yield ()
    }


    "Update multiple values" - types { implicit conn =>
      for {
        List(a, b, c) <- Ns.i.intMap_?.insert(
          (1, None),
          (1, Some(Map(pint1))),
          (2, Some(Map(pint2))),
        ).transact.map(_.ids)

        // Update all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intMap(Map(pint3)).update.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.intMap_?.query.get.map(_ ==> List(
          (a, 1, None), //         no value to update
          (b, 1, Some(Map(pint3))), // value updated
          (c, 2, Some(Map(pint2))),
        ))

        // Upsert all entities where non-unique attribute i is 1
        _ <- Ns.i_(1).intMap(Map(pint4)).upsert.transact

        // 2 matching entities updated
        _ <- Ns.id.a1.i.intMap_?.query.get.map(_ ==> List(
          (a, 1, Some(Map(pint4))), // attribute inserted
          (b, 1, Some(Map(pint4))), // value updated
          (c, 2, Some(Map(pint2))),
        ))
      } yield ()
    }


    "Types apply" - types { implicit conn =>
      for {
        id1 <- Ns.stringMap(Map(pstring1)).save.transact.map(_.id)
        id2 <- Ns.intMap(Map(pint1)).save.transact.map(_.id)
        id3 <- Ns.longMap(Map(plong1)).save.transact.map(_.id)
        id4 <- Ns.floatMap(Map(pfloat1)).save.transact.map(_.id)
        id5 <- Ns.doubleMap(Map(pdouble1)).save.transact.map(_.id)
        id6 <- Ns.booleanMap(Map(pboolean1)).save.transact.map(_.id)
        id7 <- Ns.bigIntMap(Map(pbigInt1)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalMap(Map(pbigDecimal1)).save.transact.map(_.id)
        id9 <- Ns.dateMap(Map(pdate1)).save.transact.map(_.id)
        id10 <- Ns.durationMap(Map(pduration1)).save.transact.map(_.id)
        id11 <- Ns.instantMap(Map(pinstant1)).save.transact.map(_.id)
        id12 <- Ns.localDateMap(Map(plocalDate1)).save.transact.map(_.id)
        id13 <- Ns.localTimeMap(Map(plocalTime1)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeMap(Map(plocalDateTime1)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeMap(Map(poffsetTime1)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeMap(Map(poffsetDateTime1)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeMap(Map(pzonedDateTime1)).save.transact.map(_.id)
        id18 <- Ns.uuidMap(Map(puuid1)).save.transact.map(_.id)
        id19 <- Ns.uriMap(Map(puri1)).save.transact.map(_.id)
        id20 <- Ns.byteMap(Map(pbyte1)).save.transact.map(_.id)
        id21 <- Ns.shortMap(Map(pshort1)).save.transact.map(_.id)
        id22 <- Ns.charMap(Map(pchar1)).save.transact.map(_.id)

        // Update
        _ <- Ns(id1).stringMap(Map(pstring2)).update.transact
        _ <- Ns(id2).intMap(Map(pint2)).update.transact
        _ <- Ns(id3).longMap(Map(plong2)).update.transact
        _ <- Ns(id4).floatMap(Map(pfloat2)).update.transact
        _ <- Ns(id5).doubleMap(Map(pdouble2)).update.transact
        _ <- Ns(id6).booleanMap(Map(pboolean2)).update.transact
        _ <- Ns(id7).bigIntMap(Map(pbigInt2)).update.transact
        _ <- Ns(id8).bigDecimalMap(Map(pbigDecimal2)).update.transact
        _ <- Ns(id9).dateMap(Map(pdate2)).update.transact
        _ <- Ns(id10).durationMap(Map(pduration2)).update.transact
        _ <- Ns(id11).instantMap(Map(pinstant2)).update.transact
        _ <- Ns(id12).localDateMap(Map(plocalDate2)).update.transact
        _ <- Ns(id13).localTimeMap(Map(plocalTime2)).update.transact
        _ <- Ns(id14).localDateTimeMap(Map(plocalDateTime2)).update.transact
        _ <- Ns(id15).offsetTimeMap(Map(poffsetTime2)).update.transact
        _ <- Ns(id16).offsetDateTimeMap(Map(poffsetDateTime2)).update.transact
        _ <- Ns(id17).zonedDateTimeMap(Map(pzonedDateTime2)).update.transact
        _ <- Ns(id18).uuidMap(Map(puuid2)).update.transact
        _ <- Ns(id19).uriMap(Map(puri2)).update.transact
        _ <- Ns(id20).byteMap(Map(pbyte2)).update.transact
        _ <- Ns(id21).shortMap(Map(pshort2)).update.transact
        _ <- Ns(id22).charMap(Map(pchar2)).update.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring2))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint2))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong2))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat2))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble2))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean2))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt2))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal2))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate2))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration2))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant2))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate2))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime2))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime2))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime2))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime2))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime2))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid2))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri2))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte2))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort2))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar2))

        // Upsert
        _ <- Ns(id1).stringMap(Map(pstring3)).upsert.transact
        _ <- Ns(id2).intMap(Map(pint3)).upsert.transact
        _ <- Ns(id3).longMap(Map(plong3)).upsert.transact
        _ <- Ns(id4).floatMap(Map(pfloat3)).upsert.transact
        _ <- Ns(id5).doubleMap(Map(pdouble3)).upsert.transact
        _ <- Ns(id6).booleanMap(Map(pboolean3)).upsert.transact
        _ <- Ns(id7).bigIntMap(Map(pbigInt3)).upsert.transact
        _ <- Ns(id8).bigDecimalMap(Map(pbigDecimal3)).upsert.transact
        _ <- Ns(id9).dateMap(Map(pdate3)).upsert.transact
        _ <- Ns(id10).durationMap(Map(pduration3)).upsert.transact
        _ <- Ns(id11).instantMap(Map(pinstant3)).upsert.transact
        _ <- Ns(id12).localDateMap(Map(plocalDate3)).upsert.transact
        _ <- Ns(id13).localTimeMap(Map(plocalTime3)).upsert.transact
        _ <- Ns(id14).localDateTimeMap(Map(plocalDateTime3)).upsert.transact
        _ <- Ns(id15).offsetTimeMap(Map(poffsetTime3)).upsert.transact
        _ <- Ns(id16).offsetDateTimeMap(Map(poffsetDateTime3)).upsert.transact
        _ <- Ns(id17).zonedDateTimeMap(Map(pzonedDateTime3)).upsert.transact
        _ <- Ns(id18).uuidMap(Map(puuid3)).upsert.transact
        _ <- Ns(id19).uriMap(Map(puri3)).upsert.transact
        _ <- Ns(id20).byteMap(Map(pbyte3)).upsert.transact
        _ <- Ns(id21).shortMap(Map(pshort3)).upsert.transact
        _ <- Ns(id22).charMap(Map(pchar3)).upsert.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring3))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint3))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong3))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat3))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble3))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean3))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt3))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal3))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate3))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration3))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant3))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate3))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime3))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime3))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime3))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime3))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime3))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid3))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri3))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte3))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort3))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar3))
      } yield ()
    }


    "Types add" - types { implicit conn =>
      for {
        id1 <- Ns.stringMap(Map(pstring1)).save.transact.map(_.id)
        id2 <- Ns.intMap(Map(pint1)).save.transact.map(_.id)
        id3 <- Ns.longMap(Map(plong1)).save.transact.map(_.id)
        id4 <- Ns.floatMap(Map(pfloat1)).save.transact.map(_.id)
        id5 <- Ns.doubleMap(Map(pdouble1)).save.transact.map(_.id)
        id6 <- Ns.booleanMap(Map(pboolean1)).save.transact.map(_.id)
        id7 <- Ns.bigIntMap(Map(pbigInt1)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalMap(Map(pbigDecimal1)).save.transact.map(_.id)
        id9 <- Ns.dateMap(Map(pdate1)).save.transact.map(_.id)
        id10 <- Ns.durationMap(Map(pduration1)).save.transact.map(_.id)
        id11 <- Ns.instantMap(Map(pinstant1)).save.transact.map(_.id)
        id12 <- Ns.localDateMap(Map(plocalDate1)).save.transact.map(_.id)
        id13 <- Ns.localTimeMap(Map(plocalTime1)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeMap(Map(plocalDateTime1)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeMap(Map(poffsetTime1)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeMap(Map(poffsetDateTime1)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeMap(Map(pzonedDateTime1)).save.transact.map(_.id)
        id18 <- Ns.uuidMap(Map(puuid1)).save.transact.map(_.id)
        id19 <- Ns.uriMap(Map(puri1)).save.transact.map(_.id)
        id20 <- Ns.byteMap(Map(pbyte1)).save.transact.map(_.id)
        id21 <- Ns.shortMap(Map(pshort1)).save.transact.map(_.id)
        id22 <- Ns.charMap(Map(pchar1)).save.transact.map(_.id)

        // Update
        _ <- Ns(id1).stringMap.add(pstring2).update.transact
        _ <- Ns(id2).intMap.add(pint2).update.transact
        _ <- Ns(id3).longMap.add(plong2).update.transact
        _ <- Ns(id4).floatMap.add(pfloat2).update.transact
        _ <- Ns(id5).doubleMap.add(pdouble2).update.transact
        _ <- Ns(id6).booleanMap.add(pboolean2).update.transact
        _ <- Ns(id7).bigIntMap.add(pbigInt2).update.transact
        _ <- Ns(id8).bigDecimalMap.add(pbigDecimal2).update.transact
        _ <- Ns(id9).dateMap.add(pdate2).update.transact
        _ <- Ns(id10).durationMap.add(pduration2).update.transact
        _ <- Ns(id11).instantMap.add(pinstant2).update.transact
        _ <- Ns(id12).localDateMap.add(plocalDate2).update.transact
        _ <- Ns(id13).localTimeMap.add(plocalTime2).update.transact
        _ <- Ns(id14).localDateTimeMap.add(plocalDateTime2).update.transact
        _ <- Ns(id15).offsetTimeMap.add(poffsetTime2).update.transact
        _ <- Ns(id16).offsetDateTimeMap.add(poffsetDateTime2).update.transact
        _ <- Ns(id17).zonedDateTimeMap.add(pzonedDateTime2).update.transact
        _ <- Ns(id18).uuidMap.add(puuid2).update.transact
        _ <- Ns(id19).uriMap.add(puri2).update.transact
        _ <- Ns(id20).byteMap.add(pbyte2).update.transact
        _ <- Ns(id21).shortMap.add(pshort2).update.transact
        _ <- Ns(id22).charMap.add(pchar2).update.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Upsert
        _ <- Ns(id1).stringMap.add(pstring3).upsert.transact
        _ <- Ns(id2).intMap.add(pint3).upsert.transact
        _ <- Ns(id3).longMap.add(plong3).upsert.transact
        _ <- Ns(id4).floatMap.add(pfloat3).upsert.transact
        _ <- Ns(id5).doubleMap.add(pdouble3).upsert.transact
        _ <- Ns(id6).booleanMap.add(pboolean3).upsert.transact
        _ <- Ns(id7).bigIntMap.add(pbigInt3).upsert.transact
        _ <- Ns(id8).bigDecimalMap.add(pbigDecimal3).upsert.transact
        _ <- Ns(id9).dateMap.add(pdate3).upsert.transact
        _ <- Ns(id10).durationMap.add(pduration3).upsert.transact
        _ <- Ns(id11).instantMap.add(pinstant3).upsert.transact
        _ <- Ns(id12).localDateMap.add(plocalDate3).upsert.transact
        _ <- Ns(id13).localTimeMap.add(plocalTime3).upsert.transact
        _ <- Ns(id14).localDateTimeMap.add(plocalDateTime3).upsert.transact
        _ <- Ns(id15).offsetTimeMap.add(poffsetTime3).upsert.transact
        _ <- Ns(id16).offsetDateTimeMap.add(poffsetDateTime3).upsert.transact
        _ <- Ns(id17).zonedDateTimeMap.add(pzonedDateTime3).upsert.transact
        _ <- Ns(id18).uuidMap.add(puuid3).upsert.transact
        _ <- Ns(id19).uriMap.add(puri3).upsert.transact
        _ <- Ns(id20).byteMap.add(pbyte3).upsert.transact
        _ <- Ns(id21).shortMap.add(pshort3).upsert.transact
        _ <- Ns(id22).charMap.add(pchar3).upsert.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2, pboolean3))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3))
      } yield ()
    }


    "Types remove" - types { implicit conn =>
      for {
        id1 <- Ns.stringMap(Map(pstring1, pstring2, pstring3)).save.transact.map(_.id)
        id2 <- Ns.intMap(Map(pint1, pint2, pint3)).save.transact.map(_.id)
        id3 <- Ns.longMap(Map(plong1, plong2, plong3)).save.transact.map(_.id)
        id4 <- Ns.floatMap(Map(pfloat1, pfloat2, pfloat3)).save.transact.map(_.id)
        id5 <- Ns.doubleMap(Map(pdouble1, pdouble2, pdouble3)).save.transact.map(_.id)
        id6 <- Ns.booleanMap(Map(pboolean1, pboolean2, pboolean3)).save.transact.map(_.id)
        id7 <- Ns.bigIntMap(Map(pbigInt1, pbigInt2, pbigInt3)).save.transact.map(_.id)
        id8 <- Ns.bigDecimalMap(Map(pbigDecimal1, pbigDecimal2, pbigDecimal3)).save.transact.map(_.id)
        id9 <- Ns.dateMap(Map(pdate1, pdate2, pdate3)).save.transact.map(_.id)
        id10 <- Ns.durationMap(Map(pduration1, pduration2, pduration3)).save.transact.map(_.id)
        id11 <- Ns.instantMap(Map(pinstant1, pinstant2, pinstant3)).save.transact.map(_.id)
        id12 <- Ns.localDateMap(Map(plocalDate1, plocalDate2, plocalDate3)).save.transact.map(_.id)
        id13 <- Ns.localTimeMap(Map(plocalTime1, plocalTime2, plocalTime3)).save.transact.map(_.id)
        id14 <- Ns.localDateTimeMap(Map(plocalDateTime1, plocalDateTime2, plocalDateTime3)).save.transact.map(_.id)
        id15 <- Ns.offsetTimeMap(Map(poffsetTime1, poffsetTime2, poffsetTime3)).save.transact.map(_.id)
        id16 <- Ns.offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3)).save.transact.map(_.id)
        id17 <- Ns.zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3)).save.transact.map(_.id)
        id18 <- Ns.uuidMap(Map(puuid1, puuid2, puuid3)).save.transact.map(_.id)
        id19 <- Ns.uriMap(Map(puri1, puri2, puri3)).save.transact.map(_.id)
        id20 <- Ns.byteMap(Map(pbyte1, pbyte2, pbyte3)).save.transact.map(_.id)
        id21 <- Ns.shortMap(Map(pshort1, pshort2, pshort3)).save.transact.map(_.id)
        id22 <- Ns.charMap(Map(pchar1, pchar2, pchar3)).save.transact.map(_.id)

        // Update
        // Remove pair having string2 key
        _ <- Ns(id1).stringMap.remove(string3).update.transact
        _ <- Ns(id2).intMap.remove(string3).update.transact
        _ <- Ns(id3).longMap.remove(string3).update.transact
        _ <- Ns(id4).floatMap.remove(string3).update.transact
        _ <- Ns(id5).doubleMap.remove(string3).update.transact
        _ <- Ns(id6).booleanMap.remove(string3).update.transact
        _ <- Ns(id7).bigIntMap.remove(string3).update.transact
        _ <- Ns(id8).bigDecimalMap.remove(string3).update.transact
        _ <- Ns(id9).dateMap.remove(string3).update.transact
        _ <- Ns(id10).durationMap.remove(string3).update.transact
        _ <- Ns(id11).instantMap.remove(string3).update.transact
        _ <- Ns(id12).localDateMap.remove(string3).update.transact
        _ <- Ns(id13).localTimeMap.remove(string3).update.transact
        _ <- Ns(id14).localDateTimeMap.remove(string3).update.transact
        _ <- Ns(id15).offsetTimeMap.remove(string3).update.transact
        _ <- Ns(id16).offsetDateTimeMap.remove(string3).update.transact
        _ <- Ns(id17).zonedDateTimeMap.remove(string3).update.transact
        _ <- Ns(id18).uuidMap.remove(string3).update.transact
        _ <- Ns(id19).uriMap.remove(string3).update.transact
        _ <- Ns(id20).byteMap.remove(string3).update.transact
        _ <- Ns(id21).shortMap.remove(string3).update.transact
        _ <- Ns(id22).charMap.remove(string3).update.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1, plong2))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1, puri2))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

        // Upsert
        // Remove pair having string2 key
        _ <- Ns(id1).stringMap.remove(string2).upsert.transact
        _ <- Ns(id2).intMap.remove(string2).upsert.transact
        _ <- Ns(id3).longMap.remove(string2).upsert.transact
        _ <- Ns(id4).floatMap.remove(string2).upsert.transact
        _ <- Ns(id5).doubleMap.remove(string2).upsert.transact
        _ <- Ns(id6).booleanMap.remove(string2).upsert.transact
        _ <- Ns(id7).bigIntMap.remove(string2).upsert.transact
        _ <- Ns(id8).bigDecimalMap.remove(string2).upsert.transact
        _ <- Ns(id9).dateMap.remove(string2).upsert.transact
        _ <- Ns(id10).durationMap.remove(string2).upsert.transact
        _ <- Ns(id11).instantMap.remove(string2).upsert.transact
        _ <- Ns(id12).localDateMap.remove(string2).upsert.transact
        _ <- Ns(id13).localTimeMap.remove(string2).upsert.transact
        _ <- Ns(id14).localDateTimeMap.remove(string2).upsert.transact
        _ <- Ns(id15).offsetTimeMap.remove(string2).upsert.transact
        _ <- Ns(id16).offsetDateTimeMap.remove(string2).upsert.transact
        _ <- Ns(id17).zonedDateTimeMap.remove(string2).upsert.transact
        _ <- Ns(id18).uuidMap.remove(string2).upsert.transact
        _ <- Ns(id19).uriMap.remove(string2).upsert.transact
        _ <- Ns(id20).byteMap.remove(string2).upsert.transact
        _ <- Ns(id21).shortMap.remove(string2).upsert.transact
        _ <- Ns(id22).charMap.remove(string2).upsert.transact

        _ <- Ns.stringMap.query.get.map(_.head ==> Map(pstring1))
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))
        _ <- Ns.longMap.query.get.map(_.head ==> Map(plong1))
        _ <- Ns.floatMap.query.get.map(_.head ==> Map(pfloat1))
        _ <- Ns.doubleMap.query.get.map(_.head ==> Map(pdouble1))
        _ <- Ns.booleanMap.query.get.map(_.head ==> Map(pboolean1))
        _ <- Ns.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))
        _ <- Ns.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1))
        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1))
        _ <- Ns.durationMap.query.get.map(_.head ==> Map(pduration1))
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))
        _ <- Ns.localDateMap.query.get.map(_.head ==> Map(plocalDate1))
        _ <- Ns.localTimeMap.query.get.map(_.head ==> Map(plocalTime1))
        _ <- Ns.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1))
        _ <- Ns.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))
        _ <- Ns.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))
        _ <- Ns.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1))
        _ <- Ns.uuidMap.query.get.map(_.head ==> Map(puuid1))
        _ <- Ns.uriMap.query.get.map(_.head ==> Map(puri1))
        _ <- Ns.byteMap.query.get.map(_.head ==> Map(pbyte1))
        _ <- Ns.shortMap.query.get.map(_.head ==> Map(pshort1))
        _ <- Ns.charMap.query.get.map(_.head ==> Map(pchar1))
      } yield ()
    }


    "Valid keys" - types { implicit conn =>
      for {
        id <- Ns.intMap(Map("a" -> int0)).save.transact.map(_.id)

        // Allowed characters in a key name
        _ <- Ns(id).intMap(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).update.transact

        // No spaces
        _ <- Ns(id).intMap(Map("foo bar" -> 1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }

        // No special characters
        _ <- Ns(id).intMap(Map("foo:" -> 1)).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }
        _ <- Ns(id).intMap.add("bar?" -> 2).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
          }
      } yield ()
    }
  }
}
