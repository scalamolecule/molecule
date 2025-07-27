package molecule.db.compliance.test.action.update.ops

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class OpsMap(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Valid keys" - types {
    for {
      id <- Entity.intMap(Map("a" -> int0)).save.transact.map(_.id)

      // Allowed characters in a key name
      _ <- Entity(id).intMap(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).update.transact

      // No spaces
      _ <- Entity(id).intMap(Map("foo bar" -> 1)).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }

      // No special characters
      _ <- Entity(id).intMap(Map("foo:" -> 1)).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }
      _ <- Entity(id).intMap.add("bar?" -> 2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }
    } yield ()
  }

  "apply" - types {
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Map attribute not yet asserted
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intMap(Map(pint1, pint2)).update.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // To insert the attribute value if not already asserted, use `upsert`
      _ <- Entity(id).intMap(Map(pint1, pint2)).upsert.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2))

      // Applying Map of pairs replaces previous Map
      _ <- Entity(id).intMap(Map(pint2, pint3)).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint2, pint3))

      // OBS: all attributes have to be asserted for any value to be updated!
      _ <- Entity(id).s("foo").intMap(Map(pint3, pint4)).update.transact
      // Nothing is updated
      _ <- Entity.s_?.intMap.query.get.map(_.head ==> (None, Map(pint2, pint3)))

      // Use upsert to guarantee that all values are updated/inserted
      _ <- Entity(id).s("foo").intMap(Map(pint3, pint4)).upsert.transact
      _ <- Entity.s_?.intMap.query.get.map(_.head ==> (Some("foo"), Map(pint3, pint4)))

      // Applying empty Map of pairs deletes map
      _ <- Entity(id).intMap(Map.empty[String, Int]).update.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      _ <- Entity(id).intMap(Map(pint1, pint2)).update.transact
      // Apply nothing to delete attribute
      _ <- Entity(id).intMap().update.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // Entity still has other attributes
      _ <- Entity.i.s.query.get.map(_.head ==> (42, "foo"))
    } yield ()
  }


  "add" - types {
    for {
      id <- Entity.i(42).save.transact.map(_.id)
      // Map attribute not yet asserted
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // When attribute is not already asserted, an update has no effect
      _ <- Entity(id).intMap.add("a" -> int0).update.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // To add values to the attribute if not already asserted, use `upsert`
      _ <- Entity(id).intMap.add("a" -> int0).upsert.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map("a" -> int0))

      // Adding existing pair to Map changes nothing
      _ <- Entity(id).intMap.add("a" -> int0).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map("a" -> int0))

      // Adding pair with existing key replaces the value of the pair
      _ <- Entity(id).intMap.add("a" -> int1).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1)) // "a" -> int1

      // Add new pair
      _ <- Entity(id).intMap.add(pint2).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2))

      // Add multiple pairs with varargs
      _ <- Entity(id).intMap.add(pint3, pint4).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4))

      // Add multiple pairs with Iterable
      _ <- Entity(id).intMap.add(List(pint5, pint6)).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

      // Adding empty Seq of pairs has no effect
      _ <- Entity(id).intMap.add(Seq.empty[(String, Int)]).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
    } yield ()
  }


  "remove" - types {
    // No semantic difference between update/upsert when removing
    for {
      id <- Entity.i(42).save.transact.map(_.id)

      // Map attribute not yet asserted
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // Removing pair by key from non-asserted Map has no effect
      _ <- Entity(id).intMap.remove(string1).update.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)

      // Start with some pairs
      _ <- Entity(id).intMap.add(pint1, pint2, pint3, pint4, pint5, pint6, pint7).upsert.transact

      // Remove pair by String key
      _ <- Entity(id).intMap.remove(string7).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

      // Removing non-existing key has no effect
      _ <- Entity(id).intMap.remove(string9).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))

      // Removing duplicate keys removes only pairs with the distinct key (distinct key value semantics of Map)
      _ <- Entity(id).intMap.remove(string6, string6).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5))

      // Remove multiple pairs by varargs of keys
      _ <- Entity(id).intMap.remove(string4, string5).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))

      // Remove multiple pairs by Seq of keys (not Iterable)
      _ <- Entity(id).intMap.remove(List(string2, string3)).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1))

      // Removing pairs with empty Seq of keys has no effect
      _ <- Entity(id).intMap.remove(Seq.empty[String]).update.transact
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1))

      // Removing all remaining pairs deletes the attribute
      _ <- Entity(id).intMap.remove(Seq(string1)).update.transact
      _ <- Entity.intMap.query.get.map(_ ==> Nil)
    } yield ()
  }


  "Update multiple values" - types {
    for {
      case List(a, b, c) <- Entity.i.intMap_?.insert(
        (1, None),
        (1, Some(Map(pint1))),
        (2, Some(Map(pint2))),
      ).transact.map(_.ids)

      // Update all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).intMap(Map(pint3)).update.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.intMap_?.query.get.map(_ ==> List(
        (a, 1, None), //         no value to update
        (b, 1, Some(Map(pint3))), // value updated
        (c, 2, Some(Map(pint2))),
      ))

      // Upsert all entities where non-unique attribute i is 1
      _ <- Entity.i_(1).intMap(Map(pint4)).upsert.transact

      // 2 matching entities updated
      _ <- Entity.id.a1.i.intMap_?.query.get.map(_ ==> List(
        (a, 1, Some(Map(pint4))), // attribute inserted
        (b, 1, Some(Map(pint4))), // value updated
        (c, 2, Some(Map(pint2))),
      ))
    } yield ()
  }


  "Types apply" - types {
    for {
      id1 <- Entity.stringMap(Map(pstring1)).save.transact.map(_.id)
      id2 <- Entity.intMap(Map(pint1)).save.transact.map(_.id)
      id3 <- Entity.longMap(Map(plong1)).save.transact.map(_.id)
      id4 <- Entity.floatMap(Map(pfloat1)).save.transact.map(_.id)
      id5 <- Entity.doubleMap(Map(pdouble1)).save.transact.map(_.id)
      id6 <- Entity.booleanMap(Map(pboolean1)).save.transact.map(_.id)
      id7 <- Entity.bigIntMap(Map(pbigInt1)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalMap(Map(pbigDecimal1)).save.transact.map(_.id)
      id9 <- Entity.dateMap(Map(pdate1)).save.transact.map(_.id)
      id10 <- Entity.durationMap(Map(pduration1)).save.transact.map(_.id)
      id11 <- Entity.instantMap(Map(pinstant1)).save.transact.map(_.id)
      id12 <- Entity.localDateMap(Map(plocalDate1)).save.transact.map(_.id)
      id13 <- Entity.localTimeMap(Map(plocalTime1)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeMap(Map(plocalDateTime1)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeMap(Map(poffsetTime1)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeMap(Map(poffsetDateTime1)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeMap(Map(pzonedDateTime1)).save.transact.map(_.id)
      id18 <- Entity.uuidMap(Map(puuid1)).save.transact.map(_.id)
      id19 <- Entity.uriMap(Map(puri1)).save.transact.map(_.id)
      id20 <- Entity.byteMap(Map(pbyte1)).save.transact.map(_.id)
      id21 <- Entity.shortMap(Map(pshort1)).save.transact.map(_.id)
      id22 <- Entity.charMap(Map(pchar1)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringMap(Map(pstring2)).update.transact
      _ <- Entity(id2).intMap(Map(pint2)).update.transact
      _ <- Entity(id3).longMap(Map(plong2)).update.transact
      _ <- Entity(id4).floatMap(Map(pfloat2)).update.transact
      _ <- Entity(id5).doubleMap(Map(pdouble2)).update.transact
      _ <- Entity(id6).booleanMap(Map(pboolean2)).update.transact
      _ <- Entity(id7).bigIntMap(Map(pbigInt2)).update.transact
      _ <- Entity(id8).bigDecimalMap(Map(pbigDecimal2)).update.transact
      _ <- Entity(id9).dateMap(Map(pdate2)).update.transact
      _ <- Entity(id10).durationMap(Map(pduration2)).update.transact
      _ <- Entity(id11).instantMap(Map(pinstant2)).update.transact
      _ <- Entity(id12).localDateMap(Map(plocalDate2)).update.transact
      _ <- Entity(id13).localTimeMap(Map(plocalTime2)).update.transact
      _ <- Entity(id14).localDateTimeMap(Map(plocalDateTime2)).update.transact
      _ <- Entity(id15).offsetTimeMap(Map(poffsetTime2)).update.transact
      _ <- Entity(id16).offsetDateTimeMap(Map(poffsetDateTime2)).update.transact
      _ <- Entity(id17).zonedDateTimeMap(Map(pzonedDateTime2)).update.transact
      _ <- Entity(id18).uuidMap(Map(puuid2)).update.transact
      _ <- Entity(id19).uriMap(Map(puri2)).update.transact
      _ <- Entity(id20).byteMap(Map(pbyte2)).update.transact
      _ <- Entity(id21).shortMap(Map(pshort2)).update.transact
      _ <- Entity(id22).charMap(Map(pchar2)).update.transact

      _ <- Entity.stringMap.query.get.map(_.head ==> Map(pstring2))
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint2))
      _ <- Entity.longMap.query.get.map(_.head ==> Map(plong2))
      _ <- Entity.floatMap.query.get.map(_.head ==> Map(pfloat2))
      _ <- Entity.doubleMap.query.get.map(_.head ==> Map(pdouble2))
      _ <- Entity.booleanMap.query.get.map(_.head ==> Map(pboolean2))
      _ <- Entity.bigIntMap.query.get.map(_.head ==> Map(pbigInt2))
      _ <- Entity.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal2))
      _ <- Entity.dateMap.query.get.map(_.head ==> Map(pdate2))
      _ <- Entity.durationMap.query.get.map(_.head ==> Map(pduration2))
      _ <- Entity.instantMap.query.get.map(_.head ==> Map(pinstant2))
      _ <- Entity.localDateMap.query.get.map(_.head ==> Map(plocalDate2))
      _ <- Entity.localTimeMap.query.get.map(_.head ==> Map(plocalTime2))
      _ <- Entity.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime2))
      _ <- Entity.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime2))
      _ <- Entity.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime2))
      _ <- Entity.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime2))
      _ <- Entity.uuidMap.query.get.map(_.head ==> Map(puuid2))
      _ <- Entity.uriMap.query.get.map(_.head ==> Map(puri2))
      _ <- Entity.byteMap.query.get.map(_.head ==> Map(pbyte2))
      _ <- Entity.shortMap.query.get.map(_.head ==> Map(pshort2))
      _ <- Entity.charMap.query.get.map(_.head ==> Map(pchar2))

      // Upsert
      _ <- Entity(id1).stringMap(Map(pstring3)).upsert.transact
      _ <- Entity(id2).intMap(Map(pint3)).upsert.transact
      _ <- Entity(id3).longMap(Map(plong3)).upsert.transact
      _ <- Entity(id4).floatMap(Map(pfloat3)).upsert.transact
      _ <- Entity(id5).doubleMap(Map(pdouble3)).upsert.transact
      _ <- Entity(id6).booleanMap(Map(pboolean3)).upsert.transact
      _ <- Entity(id7).bigIntMap(Map(pbigInt3)).upsert.transact
      _ <- Entity(id8).bigDecimalMap(Map(pbigDecimal3)).upsert.transact
      _ <- Entity(id9).dateMap(Map(pdate3)).upsert.transact
      _ <- Entity(id10).durationMap(Map(pduration3)).upsert.transact
      _ <- Entity(id11).instantMap(Map(pinstant3)).upsert.transact
      _ <- Entity(id12).localDateMap(Map(plocalDate3)).upsert.transact
      _ <- Entity(id13).localTimeMap(Map(plocalTime3)).upsert.transact
      _ <- Entity(id14).localDateTimeMap(Map(plocalDateTime3)).upsert.transact
      _ <- Entity(id15).offsetTimeMap(Map(poffsetTime3)).upsert.transact
      _ <- Entity(id16).offsetDateTimeMap(Map(poffsetDateTime3)).upsert.transact
      _ <- Entity(id17).zonedDateTimeMap(Map(pzonedDateTime3)).upsert.transact
      _ <- Entity(id18).uuidMap(Map(puuid3)).upsert.transact
      _ <- Entity(id19).uriMap(Map(puri3)).upsert.transact
      _ <- Entity(id20).byteMap(Map(pbyte3)).upsert.transact
      _ <- Entity(id21).shortMap(Map(pshort3)).upsert.transact
      _ <- Entity(id22).charMap(Map(pchar3)).upsert.transact

      _ <- Entity.stringMap.query.get.map(_.head ==> Map(pstring3))
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint3))
      _ <- Entity.longMap.query.get.map(_.head ==> Map(plong3))
      _ <- Entity.floatMap.query.get.map(_.head ==> Map(pfloat3))
      _ <- Entity.doubleMap.query.get.map(_.head ==> Map(pdouble3))
      _ <- Entity.booleanMap.query.get.map(_.head ==> Map(pboolean3))
      _ <- Entity.bigIntMap.query.get.map(_.head ==> Map(pbigInt3))
      _ <- Entity.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal3))
      _ <- Entity.dateMap.query.get.map(_.head ==> Map(pdate3))
      _ <- Entity.durationMap.query.get.map(_.head ==> Map(pduration3))
      _ <- Entity.instantMap.query.get.map(_.head ==> Map(pinstant3))
      _ <- Entity.localDateMap.query.get.map(_.head ==> Map(plocalDate3))
      _ <- Entity.localTimeMap.query.get.map(_.head ==> Map(plocalTime3))
      _ <- Entity.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime3))
      _ <- Entity.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime3))
      _ <- Entity.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime3))
      _ <- Entity.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime3))
      _ <- Entity.uuidMap.query.get.map(_.head ==> Map(puuid3))
      _ <- Entity.uriMap.query.get.map(_.head ==> Map(puri3))
      _ <- Entity.byteMap.query.get.map(_.head ==> Map(pbyte3))
      _ <- Entity.shortMap.query.get.map(_.head ==> Map(pshort3))
      _ <- Entity.charMap.query.get.map(_.head ==> Map(pchar3))
    } yield ()
  }


  "Types add" - types {
    for {
      id1 <- Entity.stringMap(Map(pstring1)).save.transact.map(_.id)
      id2 <- Entity.intMap(Map(pint1)).save.transact.map(_.id)
      id3 <- Entity.longMap(Map(plong1)).save.transact.map(_.id)
      id4 <- Entity.floatMap(Map(pfloat1)).save.transact.map(_.id)
      id5 <- Entity.doubleMap(Map(pdouble1)).save.transact.map(_.id)
      id6 <- Entity.booleanMap(Map(pboolean1)).save.transact.map(_.id)
      id7 <- Entity.bigIntMap(Map(pbigInt1)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalMap(Map(pbigDecimal1)).save.transact.map(_.id)
      id9 <- Entity.dateMap(Map(pdate1)).save.transact.map(_.id)
      id10 <- Entity.durationMap(Map(pduration1)).save.transact.map(_.id)
      id11 <- Entity.instantMap(Map(pinstant1)).save.transact.map(_.id)
      id12 <- Entity.localDateMap(Map(plocalDate1)).save.transact.map(_.id)
      id13 <- Entity.localTimeMap(Map(plocalTime1)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeMap(Map(plocalDateTime1)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeMap(Map(poffsetTime1)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeMap(Map(poffsetDateTime1)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeMap(Map(pzonedDateTime1)).save.transact.map(_.id)
      id18 <- Entity.uuidMap(Map(puuid1)).save.transact.map(_.id)
      id19 <- Entity.uriMap(Map(puri1)).save.transact.map(_.id)
      id20 <- Entity.byteMap(Map(pbyte1)).save.transact.map(_.id)
      id21 <- Entity.shortMap(Map(pshort1)).save.transact.map(_.id)
      id22 <- Entity.charMap(Map(pchar1)).save.transact.map(_.id)

      // Update
      _ <- Entity(id1).stringMap.add(pstring2).update.transact
      _ <- Entity(id2).intMap.add(pint2).update.transact
      _ <- Entity(id3).longMap.add(plong2).update.transact
      _ <- Entity(id4).floatMap.add(pfloat2).update.transact
      _ <- Entity(id5).doubleMap.add(pdouble2).update.transact
      _ <- Entity(id6).booleanMap.add(pboolean2).update.transact
      _ <- Entity(id7).bigIntMap.add(pbigInt2).update.transact
      _ <- Entity(id8).bigDecimalMap.add(pbigDecimal2).update.transact
      _ <- Entity(id9).dateMap.add(pdate2).update.transact
      _ <- Entity(id10).durationMap.add(pduration2).update.transact
      _ <- Entity(id11).instantMap.add(pinstant2).update.transact
      _ <- Entity(id12).localDateMap.add(plocalDate2).update.transact
      _ <- Entity(id13).localTimeMap.add(plocalTime2).update.transact
      _ <- Entity(id14).localDateTimeMap.add(plocalDateTime2).update.transact
      _ <- Entity(id15).offsetTimeMap.add(poffsetTime2).update.transact
      _ <- Entity(id16).offsetDateTimeMap.add(poffsetDateTime2).update.transact
      _ <- Entity(id17).zonedDateTimeMap.add(pzonedDateTime2).update.transact
      _ <- Entity(id18).uuidMap.add(puuid2).update.transact
      _ <- Entity(id19).uriMap.add(puri2).update.transact
      _ <- Entity(id20).byteMap.add(pbyte2).update.transact
      _ <- Entity(id21).shortMap.add(pshort2).update.transact
      _ <- Entity(id22).charMap.add(pchar2).update.transact

      _ <- Entity.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2))
      _ <- Entity.longMap.query.get.map(_.head ==> Map(plong1, plong2))
      _ <- Entity.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))
      _ <- Entity.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))
      _ <- Entity.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2))
      _ <- Entity.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))
      _ <- Entity.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))
      _ <- Entity.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))
      _ <- Entity.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))
      _ <- Entity.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))
      _ <- Entity.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))
      _ <- Entity.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))
      _ <- Entity.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))
      _ <- Entity.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))
      _ <- Entity.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))
      _ <- Entity.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))
      _ <- Entity.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))
      _ <- Entity.uriMap.query.get.map(_.head ==> Map(puri1, puri2))
      _ <- Entity.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))
      _ <- Entity.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))
      _ <- Entity.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

      // Upsert
      _ <- Entity(id1).stringMap.add(pstring3).upsert.transact
      _ <- Entity(id2).intMap.add(pint3).upsert.transact
      _ <- Entity(id3).longMap.add(plong3).upsert.transact
      _ <- Entity(id4).floatMap.add(pfloat3).upsert.transact
      _ <- Entity(id5).doubleMap.add(pdouble3).upsert.transact
      _ <- Entity(id6).booleanMap.add(pboolean3).upsert.transact
      _ <- Entity(id7).bigIntMap.add(pbigInt3).upsert.transact
      _ <- Entity(id8).bigDecimalMap.add(pbigDecimal3).upsert.transact
      _ <- Entity(id9).dateMap.add(pdate3).upsert.transact
      _ <- Entity(id10).durationMap.add(pduration3).upsert.transact
      _ <- Entity(id11).instantMap.add(pinstant3).upsert.transact
      _ <- Entity(id12).localDateMap.add(plocalDate3).upsert.transact
      _ <- Entity(id13).localTimeMap.add(plocalTime3).upsert.transact
      _ <- Entity(id14).localDateTimeMap.add(plocalDateTime3).upsert.transact
      _ <- Entity(id15).offsetTimeMap.add(poffsetTime3).upsert.transact
      _ <- Entity(id16).offsetDateTimeMap.add(poffsetDateTime3).upsert.transact
      _ <- Entity(id17).zonedDateTimeMap.add(pzonedDateTime3).upsert.transact
      _ <- Entity(id18).uuidMap.add(puuid3).upsert.transact
      _ <- Entity(id19).uriMap.add(puri3).upsert.transact
      _ <- Entity(id20).byteMap.add(pbyte3).upsert.transact
      _ <- Entity(id21).shortMap.add(pshort3).upsert.transact
      _ <- Entity(id22).charMap.add(pchar3).upsert.transact

      _ <- Entity.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2, pstring3))
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3))
      _ <- Entity.longMap.query.get.map(_.head ==> Map(plong1, plong2, plong3))
      _ <- Entity.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2, pfloat3))
      _ <- Entity.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2, pdouble3))
      _ <- Entity.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2, pboolean3))
      _ <- Entity.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2, pbigInt3))
      _ <- Entity.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2, pbigDecimal3))
      _ <- Entity.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2, pdate3))
      _ <- Entity.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2, pduration3))
      _ <- Entity.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3))
      _ <- Entity.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2, plocalDate3))
      _ <- Entity.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2, plocalTime3))
      _ <- Entity.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2, plocalDateTime3))
      _ <- Entity.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2, poffsetTime3))
      _ <- Entity.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3))
      _ <- Entity.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3))
      _ <- Entity.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2, puuid3))
      _ <- Entity.uriMap.query.get.map(_.head ==> Map(puri1, puri2, puri3))
      _ <- Entity.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2, pbyte3))
      _ <- Entity.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2, pshort3))
      _ <- Entity.charMap.query.get.map(_.head ==> Map(pchar1, pchar2, pchar3))
    } yield ()
  }


  "Types remove" - types {
    for {
      id1 <- Entity.stringMap(Map(pstring1, pstring2, pstring3)).save.transact.map(_.id)
      id2 <- Entity.intMap(Map(pint1, pint2, pint3)).save.transact.map(_.id)
      id3 <- Entity.longMap(Map(plong1, plong2, plong3)).save.transact.map(_.id)
      id4 <- Entity.floatMap(Map(pfloat1, pfloat2, pfloat3)).save.transact.map(_.id)
      id5 <- Entity.doubleMap(Map(pdouble1, pdouble2, pdouble3)).save.transact.map(_.id)
      id6 <- Entity.booleanMap(Map(pboolean1, pboolean2, pboolean3)).save.transact.map(_.id)
      id7 <- Entity.bigIntMap(Map(pbigInt1, pbigInt2, pbigInt3)).save.transact.map(_.id)
      id8 <- Entity.bigDecimalMap(Map(pbigDecimal1, pbigDecimal2, pbigDecimal3)).save.transact.map(_.id)
      id9 <- Entity.dateMap(Map(pdate1, pdate2, pdate3)).save.transact.map(_.id)
      id10 <- Entity.durationMap(Map(pduration1, pduration2, pduration3)).save.transact.map(_.id)
      id11 <- Entity.instantMap(Map(pinstant1, pinstant2, pinstant3)).save.transact.map(_.id)
      id12 <- Entity.localDateMap(Map(plocalDate1, plocalDate2, plocalDate3)).save.transact.map(_.id)
      id13 <- Entity.localTimeMap(Map(plocalTime1, plocalTime2, plocalTime3)).save.transact.map(_.id)
      id14 <- Entity.localDateTimeMap(Map(plocalDateTime1, plocalDateTime2, plocalDateTime3)).save.transact.map(_.id)
      id15 <- Entity.offsetTimeMap(Map(poffsetTime1, poffsetTime2, poffsetTime3)).save.transact.map(_.id)
      id16 <- Entity.offsetDateTimeMap(Map(poffsetDateTime1, poffsetDateTime2, poffsetDateTime3)).save.transact.map(_.id)
      id17 <- Entity.zonedDateTimeMap(Map(pzonedDateTime1, pzonedDateTime2, pzonedDateTime3)).save.transact.map(_.id)
      id18 <- Entity.uuidMap(Map(puuid1, puuid2, puuid3)).save.transact.map(_.id)
      id19 <- Entity.uriMap(Map(puri1, puri2, puri3)).save.transact.map(_.id)
      id20 <- Entity.byteMap(Map(pbyte1, pbyte2, pbyte3)).save.transact.map(_.id)
      id21 <- Entity.shortMap(Map(pshort1, pshort2, pshort3)).save.transact.map(_.id)
      id22 <- Entity.charMap(Map(pchar1, pchar2, pchar3)).save.transact.map(_.id)

      // Update
      // Remove pair having string2 key
      _ <- Entity(id1).stringMap.remove(string3).update.transact
      _ <- Entity(id2).intMap.remove(string3).update.transact
      _ <- Entity(id3).longMap.remove(string3).update.transact
      _ <- Entity(id4).floatMap.remove(string3).update.transact
      _ <- Entity(id5).doubleMap.remove(string3).update.transact
      _ <- Entity(id6).booleanMap.remove(string3).update.transact
      _ <- Entity(id7).bigIntMap.remove(string3).update.transact
      _ <- Entity(id8).bigDecimalMap.remove(string3).update.transact
      _ <- Entity(id9).dateMap.remove(string3).update.transact
      _ <- Entity(id10).durationMap.remove(string3).update.transact
      _ <- Entity(id11).instantMap.remove(string3).update.transact
      _ <- Entity(id12).localDateMap.remove(string3).update.transact
      _ <- Entity(id13).localTimeMap.remove(string3).update.transact
      _ <- Entity(id14).localDateTimeMap.remove(string3).update.transact
      _ <- Entity(id15).offsetTimeMap.remove(string3).update.transact
      _ <- Entity(id16).offsetDateTimeMap.remove(string3).update.transact
      _ <- Entity(id17).zonedDateTimeMap.remove(string3).update.transact
      _ <- Entity(id18).uuidMap.remove(string3).update.transact
      _ <- Entity(id19).uriMap.remove(string3).update.transact
      _ <- Entity(id20).byteMap.remove(string3).update.transact
      _ <- Entity(id21).shortMap.remove(string3).update.transact
      _ <- Entity(id22).charMap.remove(string3).update.transact

      _ <- Entity.stringMap.query.get.map(_.head ==> Map(pstring1, pstring2))
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1, pint2))
      _ <- Entity.longMap.query.get.map(_.head ==> Map(plong1, plong2))
      _ <- Entity.floatMap.query.get.map(_.head ==> Map(pfloat1, pfloat2))
      _ <- Entity.doubleMap.query.get.map(_.head ==> Map(pdouble1, pdouble2))
      _ <- Entity.booleanMap.query.get.map(_.head ==> Map(pboolean1, pboolean2))
      _ <- Entity.bigIntMap.query.get.map(_.head ==> Map(pbigInt1, pbigInt2))
      _ <- Entity.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1, pbigDecimal2))
      _ <- Entity.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))
      _ <- Entity.durationMap.query.get.map(_.head ==> Map(pduration1, pduration2))
      _ <- Entity.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))
      _ <- Entity.localDateMap.query.get.map(_.head ==> Map(plocalDate1, plocalDate2))
      _ <- Entity.localTimeMap.query.get.map(_.head ==> Map(plocalTime1, plocalTime2))
      _ <- Entity.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1, plocalDateTime2))
      _ <- Entity.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1, poffsetTime2))
      _ <- Entity.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1, poffsetDateTime2))
      _ <- Entity.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1, pzonedDateTime2))
      _ <- Entity.uuidMap.query.get.map(_.head ==> Map(puuid1, puuid2))
      _ <- Entity.uriMap.query.get.map(_.head ==> Map(puri1, puri2))
      _ <- Entity.byteMap.query.get.map(_.head ==> Map(pbyte1, pbyte2))
      _ <- Entity.shortMap.query.get.map(_.head ==> Map(pshort1, pshort2))
      _ <- Entity.charMap.query.get.map(_.head ==> Map(pchar1, pchar2))

      // Upsert
      // Remove pair having string2 key
      _ <- Entity(id1).stringMap.remove(string2).upsert.transact
      _ <- Entity(id2).intMap.remove(string2).upsert.transact
      _ <- Entity(id3).longMap.remove(string2).upsert.transact
      _ <- Entity(id4).floatMap.remove(string2).upsert.transact
      _ <- Entity(id5).doubleMap.remove(string2).upsert.transact
      _ <- Entity(id6).booleanMap.remove(string2).upsert.transact
      _ <- Entity(id7).bigIntMap.remove(string2).upsert.transact
      _ <- Entity(id8).bigDecimalMap.remove(string2).upsert.transact
      _ <- Entity(id9).dateMap.remove(string2).upsert.transact
      _ <- Entity(id10).durationMap.remove(string2).upsert.transact
      _ <- Entity(id11).instantMap.remove(string2).upsert.transact
      _ <- Entity(id12).localDateMap.remove(string2).upsert.transact
      _ <- Entity(id13).localTimeMap.remove(string2).upsert.transact
      _ <- Entity(id14).localDateTimeMap.remove(string2).upsert.transact
      _ <- Entity(id15).offsetTimeMap.remove(string2).upsert.transact
      _ <- Entity(id16).offsetDateTimeMap.remove(string2).upsert.transact
      _ <- Entity(id17).zonedDateTimeMap.remove(string2).upsert.transact
      _ <- Entity(id18).uuidMap.remove(string2).upsert.transact
      _ <- Entity(id19).uriMap.remove(string2).upsert.transact
      _ <- Entity(id20).byteMap.remove(string2).upsert.transact
      _ <- Entity(id21).shortMap.remove(string2).upsert.transact
      _ <- Entity(id22).charMap.remove(string2).upsert.transact

      _ <- Entity.stringMap.query.get.map(_.head ==> Map(pstring1))
      _ <- Entity.intMap.query.get.map(_.head ==> Map(pint1))
      _ <- Entity.longMap.query.get.map(_.head ==> Map(plong1))
      _ <- Entity.floatMap.query.get.map(_.head ==> Map(pfloat1))
      _ <- Entity.doubleMap.query.get.map(_.head ==> Map(pdouble1))
      _ <- Entity.booleanMap.query.get.map(_.head ==> Map(pboolean1))
      _ <- Entity.bigIntMap.query.get.map(_.head ==> Map(pbigInt1))
      _ <- Entity.bigDecimalMap.query.get.map(_.head ==> Map(pbigDecimal1))
      _ <- Entity.dateMap.query.get.map(_.head ==> Map(pdate1))
      _ <- Entity.durationMap.query.get.map(_.head ==> Map(pduration1))
      _ <- Entity.instantMap.query.get.map(_.head ==> Map(pinstant1))
      _ <- Entity.localDateMap.query.get.map(_.head ==> Map(plocalDate1))
      _ <- Entity.localTimeMap.query.get.map(_.head ==> Map(plocalTime1))
      _ <- Entity.localDateTimeMap.query.get.map(_.head ==> Map(plocalDateTime1))
      _ <- Entity.offsetTimeMap.query.get.map(_.head ==> Map(poffsetTime1))
      _ <- Entity.offsetDateTimeMap.query.get.map(_.head ==> Map(poffsetDateTime1))
      _ <- Entity.zonedDateTimeMap.query.get.map(_.head ==> Map(pzonedDateTime1))
      _ <- Entity.uuidMap.query.get.map(_.head ==> Map(puuid1))
      _ <- Entity.uriMap.query.get.map(_.head ==> Map(puri1))
      _ <- Entity.byteMap.query.get.map(_.head ==> Map(pbyte1))
      _ <- Entity.shortMap.query.get.map(_.head ==> Map(pshort1))
      _ <- Entity.charMap.query.get.map(_.head ==> Map(pchar1))
    } yield ()
  }


  "Valid keys" - types {
    for {
      id <- Entity.intMap(Map("a" -> int0)).save.transact.map(_.id)

      // Allowed characters in a key name
      _ <- Entity(id).intMap(Map("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789" -> 1)).update.transact

      // No spaces
      _ <- Entity(id).intMap(Map("foo bar" -> 1)).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }

      // No special characters
      _ <- Entity(id).intMap(Map("foo:" -> 1)).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }
      _ <- Entity(id).intMap.add("bar?" -> 2).update.transact
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Keys of map attributes can only contain [a-zA-Z_\\-0-9] (no spaces or special characters)."
        }
    } yield ()
  }
}
