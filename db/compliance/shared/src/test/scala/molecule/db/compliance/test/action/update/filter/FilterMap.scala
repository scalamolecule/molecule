package molecule.db.compliance.test.action.update.filter

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterMap(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  // OBS: Updates by filters will update _all_ matching entities!

  // Use tacit attributes as filters to match entities to be updated.
  // Apply new values to mandatory attributes.

  "Map asserted" - types { implicit conn =>
    for {
      case List(a, b, c) <- Entity.iMap_?.int.insert(
        (None, 0),
        (Some(Map("a" -> 1, "b" -> 2)), 1),
        (Some(Map("b" -> 2, "c" -> 3)), 2),
      ).transact.map(_.ids)

      // Update all entities where `iMap` is asserted
      _ <- Entity
        .iMap_ // filter by tacit attribute
        .int(3) // apply new value to mandatory attribute
        .update.transact

      // 2 entities updated
      _ <- Entity.id.a1.iMap_?.int.query.get.map(_ ==> List(
        (a, None, 0),
        (b, Some(Map("a" -> 1, "b" -> 2)), 3), // updated
        (c, Some(Map("b" -> 2, "c" -> 3)), 3), // updated
      ))

      // Order of attributes in update molecule makes no difference
      _ <- Entity.int(4).iMap_.update.transact
      _ <- Entity.id.a1.iMap_?.int.query.get.map(_ ==> List(
        (a, None, 0),
        (b, Some(Map("a" -> 1, "b" -> 2)), 4), // updated
        (c, Some(Map("b" -> 2, "c" -> 3)), 4), // updated
      ))
    } yield ()
  }


  "Map not asserted" - types { implicit conn =>
    for {
      List(a, b, c) <- Entity.iMap_?.int.insert(
        (None, 0),
        (Some(Map("a" -> 1, "b" -> 2)), 1),
        (Some(Map("b" -> 2, "c" -> 3)), 2),
      ).transact.map(_.ids)

      // Update all entities where `iMap` is not asserted (null)
      _ <- Entity.iMap_().int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iMap_?.int.query.get.map(_ ==> List(
        (a, None, 3), // updated
        (b, Some(Map("a" -> 1, "b" -> 2)), 1),
        (c, Some(Map("b" -> 2, "c" -> 3)), 2),
      ))
    } yield ()
  }


  "Key" - types { implicit conn =>
    // Filtering updates by equality of collections is not supported by molecule.
    // Instead, use has/hasNo.
    for {
      case List(a, b) <- Entity.iMap.int.insert(
        (Map("a" -> 1, "b" -> 2), 1),
        (Map("b" -> 2, "c" -> 3), 2),
      ).transact.map(_.ids)

      // Update all entities where `iMap` has a key = "a"
      _ <- Entity.iMap_("a").int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 3), // updated
        (b, Map("b" -> 2, "c" -> 3), 2),
      ))

      // Update all entities where `iMap` has keys "a" or "c"
      _ <- Entity.iMap_(Seq("a", "c")).int(4).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 4), // updated
        (b, Map("b" -> 2, "c" -> 3), 4), // updated
      ))

      // Update all entities where `iMap` has keys "x" or "c"
      _ <- Entity.iMap_("x", "c").int(5).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 4),
        (b, Map("b" -> 2, "c" -> 3), 5), // updated
      ))

      // Nothing updated since no `iMap` has key "x"
      _ <- Entity.iMap_("x").int(5).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 4),
        (b, Map("b" -> 2, "c" -> 3), 5),
      ))
    } yield ()
  }


  "No key" - types { implicit conn =>
    // Filtering updates by equality of collections is not supported by molecule.
    // Instead, use has/hasNo.
    for {
      case List(a, b) <- Entity.iMap.int.insert(
        (Map("a" -> 1, "b" -> 2), 1),
        (Map("b" -> 2, "c" -> 3), 2),
      ).transact.map(_.ids)

      // Update all entities where `iMap` has no key = "a"
      _ <- Entity.iMap_.not("a").int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 1),
        (b, Map("b" -> 2, "c" -> 3), 3), // updated
      ))

      // Update all entities where `iMap` has no keys "x" or "c"
      _ <- Entity.iMap_.not(Seq("x", "c")).int(4).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 4), // updated
        (b, Map("b" -> 2, "c" -> 3), 3),
      ))

      // All updated since all maps have no "x" key
      _ <- Entity.iMap_.not("x").int(5).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2), 5), // updated
        (b, Map("b" -> 2, "c" -> 3), 5), // updated
      ))
    } yield ()
  }


  "Has value" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.iMap.int.insert(
        (Map("a" -> 1, "b" -> 2, "c" -> 3), 1),
        (Map("c" -> 3, "d" -> 4, "e" -> 5), 2),
      ).transact.map(_.ids)

      // Update all entities where `iMap` has a value 1
      _ <- Entity.iMap_.has(1).int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2, "c" -> 3), 3), // updated
        (b, Map("c" -> 3, "d" -> 4, "e" -> 5), 2),
      ))

      // Update all entities where iMap has any values of Seq
      _ <- Entity.iMap_.has(Seq(2, 3)).int(4).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2, "c" -> 3), 4), // updated
        (b, Map("c" -> 3, "d" -> 4, "e" -> 5), 4), // updated
      ))

      // Nothing updated since no iMap has any values of Seq
      _ <- Entity.iMap_.has(Seq(6, 7)).int(5).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2, "c" -> 3), 4),
        (b, Map("c" -> 3, "d" -> 4, "e" -> 5), 4),
      ))
    } yield ()
  }


  "Doesn't have value" - types { implicit conn =>
    for {
      case List(a, b) <- Entity.iMap.int.insert(
        (Map("a" -> 1, "b" -> 2, "c" -> 3), 1),
        (Map("c" -> 3, "d" -> 4, "e" -> 5), 2),
      ).transact.map(_.ids)

      // Update all entities where `iMap` has no value 1
      _ <- Entity.iMap_.hasNo(1).int(3).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2, "c" -> 3), 1),
        (b, Map("c" -> 3, "d" -> 4, "e" -> 5), 3), // updated
      ))

      // Update all entities where iMap has neither value 4 or 5
      _ <- Entity.iMap_.hasNo(Seq(4, 5)).int(4).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2, "c" -> 3), 4), // updated
        (b, Map("c" -> 3, "d" -> 4, "e" -> 5), 3),
      ))

      // Nothing updated since no iMap has neither value 3 or 7
      _ <- Entity.iMap_.hasNo(3, 7).int(5).update.transact
      _ <- Entity.id.a1.iMap.int.query.get.map(_ ==> List(
        (a, Map("a" -> 1, "b" -> 2, "c" -> 3), 4),
        (b, Map("c" -> 3, "d" -> 4, "e" -> 5), 3),
      ))
    } yield ()
  }


  "Types, filter asserted" - types { implicit conn =>
    for {
      // Initial values
      _ <- Entity.i(1).stringMap(Map(pstring1)).save.transact
      _ <- Entity.i(1).intMap(Map(pint1)).save.transact
      _ <- Entity.i(1).longMap(Map(plong1)).save.transact
      _ <- Entity.i(1).floatMap(Map(pfloat1)).save.transact
      _ <- Entity.i(1).doubleMap(Map(pdouble1)).save.transact
      _ <- Entity.i(1).booleanMap(Map(pboolean1)).save.transact
      _ <- Entity.i(1).bigIntMap(Map(pbigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalMap(Map(pbigDecimal1)).save.transact
      _ <- Entity.i(1).dateMap(Map(pdate1)).save.transact
      _ <- Entity.i(1).durationMap(Map(pduration1)).save.transact
      _ <- Entity.i(1).instantMap(Map(pinstant1)).save.transact
      _ <- Entity.i(1).localDateMap(Map(plocalDate1)).save.transact
      _ <- Entity.i(1).localTimeMap(Map(plocalTime1)).save.transact
      _ <- Entity.i(1).localDateTimeMap(Map(plocalDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeMap(Map(poffsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeMap(Map(poffsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeMap(Map(pzonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidMap(Map(puuid1)).save.transact
      _ <- Entity.i(1).uriMap(Map(puri1)).save.transact
      _ <- Entity.i(1).byteMap(Map(pbyte1)).save.transact
      _ <- Entity.i(1).shortMap(Map(pshort1)).save.transact
      _ <- Entity.i(1).charMap(Map(pchar1)).save.transact

      // Update i using asserted filter
      _ <- Entity.i(2).stringMap_.update.transact
      _ <- Entity.i(2).intMap_.update.transact
      _ <- Entity.i(2).longMap_.update.transact
      _ <- Entity.i(2).floatMap_.update.transact
      _ <- Entity.i(2).doubleMap_.update.transact
      _ <- Entity.i(2).booleanMap_.update.transact
      _ <- Entity.i(2).bigIntMap_.update.transact
      _ <- Entity.i(2).bigDecimalMap_.update.transact
      _ <- Entity.i(2).dateMap_.update.transact
      _ <- Entity.i(2).durationMap_.update.transact
      _ <- Entity.i(2).instantMap_.update.transact
      _ <- Entity.i(2).localDateMap_.update.transact
      _ <- Entity.i(2).localTimeMap_.update.transact
      _ <- Entity.i(2).localDateTimeMap_.update.transact
      _ <- Entity.i(2).offsetTimeMap_.update.transact
      _ <- Entity.i(2).offsetDateTimeMap_.update.transact
      _ <- Entity.i(2).zonedDateTimeMap_.update.transact
      _ <- Entity.i(2).uuidMap_.update.transact
      _ <- Entity.i(2).uriMap_.update.transact
      _ <- Entity.i(2).byteMap_.update.transact
      _ <- Entity.i(2).shortMap_.update.transact
      _ <- Entity.i(2).charMap_.update.transact

      // i has been updated
      _ <- Entity.i.stringMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charMap_.query.get.map(_.head ==> 2)
    } yield ()
  }


  "Types, filter key" - types { implicit conn =>
    for {
      // Initial values
      _ <- Entity.i(1).stringMap(Map(pstring1)).save.transact
      _ <- Entity.i(1).intMap(Map(pint1)).save.transact
      _ <- Entity.i(1).longMap(Map(plong1)).save.transact
      _ <- Entity.i(1).floatMap(Map(pfloat1)).save.transact
      _ <- Entity.i(1).doubleMap(Map(pdouble1)).save.transact
      _ <- Entity.i(1).booleanMap(Map(pboolean1)).save.transact
      _ <- Entity.i(1).bigIntMap(Map(pbigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalMap(Map(pbigDecimal1)).save.transact
      _ <- Entity.i(1).dateMap(Map(pdate1)).save.transact
      _ <- Entity.i(1).durationMap(Map(pduration1)).save.transact
      _ <- Entity.i(1).instantMap(Map(pinstant1)).save.transact
      _ <- Entity.i(1).localDateMap(Map(plocalDate1)).save.transact
      _ <- Entity.i(1).localTimeMap(Map(plocalTime1)).save.transact
      _ <- Entity.i(1).localDateTimeMap(Map(plocalDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeMap(Map(poffsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeMap(Map(poffsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeMap(Map(pzonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidMap(Map(puuid1)).save.transact
      _ <- Entity.i(1).uriMap(Map(puri1)).save.transact
      _ <- Entity.i(1).byteMap(Map(pbyte1)).save.transact
      _ <- Entity.i(1).shortMap(Map(pshort1)).save.transact
      _ <- Entity.i(1).charMap(Map(pchar1)).save.transact

      // Update i where map has key
      _ <- Entity.i(2).stringMap_(string1).update.transact
      _ <- Entity.i(2).intMap_(string1).update.transact
      _ <- Entity.i(2).longMap_(string1).update.transact
      _ <- Entity.i(2).floatMap_(string1).update.transact
      _ <- Entity.i(2).doubleMap_(string1).update.transact
      _ <- Entity.i(2).booleanMap_(string1).update.transact
      _ <- Entity.i(2).bigIntMap_(string1).update.transact
      _ <- Entity.i(2).bigDecimalMap_(string1).update.transact
      _ <- Entity.i(2).dateMap_(string1).update.transact
      _ <- Entity.i(2).durationMap_(string1).update.transact
      _ <- Entity.i(2).instantMap_(string1).update.transact
      _ <- Entity.i(2).localDateMap_(string1).update.transact
      _ <- Entity.i(2).localTimeMap_(string1).update.transact
      _ <- Entity.i(2).localDateTimeMap_(string1).update.transact
      _ <- Entity.i(2).offsetTimeMap_(string1).update.transact
      _ <- Entity.i(2).offsetDateTimeMap_(string1).update.transact
      _ <- Entity.i(2).zonedDateTimeMap_(string1).update.transact
      _ <- Entity.i(2).uuidMap_(string1).update.transact
      _ <- Entity.i(2).uriMap_(string1).update.transact
      _ <- Entity.i(2).byteMap_(string1).update.transact
      _ <- Entity.i(2).shortMap_(string1).update.transact
      _ <- Entity.i(2).charMap_(string1).update.transact

      // i has been updated
      _ <- Entity.i.stringMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charMap_.query.get.map(_.head ==> 2)
    } yield ()
  }


  "Types, filter not key" - types { implicit conn =>
    for {
      // Initial values
      _ <- Entity.i(1).stringMap(Map(pstring1)).save.transact
      _ <- Entity.i(1).intMap(Map(pint1)).save.transact
      _ <- Entity.i(1).longMap(Map(plong1)).save.transact
      _ <- Entity.i(1).floatMap(Map(pfloat1)).save.transact
      _ <- Entity.i(1).doubleMap(Map(pdouble1)).save.transact
      _ <- Entity.i(1).booleanMap(Map(pboolean1)).save.transact
      _ <- Entity.i(1).bigIntMap(Map(pbigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalMap(Map(pbigDecimal1)).save.transact
      _ <- Entity.i(1).dateMap(Map(pdate1)).save.transact
      _ <- Entity.i(1).durationMap(Map(pduration1)).save.transact
      _ <- Entity.i(1).instantMap(Map(pinstant1)).save.transact
      _ <- Entity.i(1).localDateMap(Map(plocalDate1)).save.transact
      _ <- Entity.i(1).localTimeMap(Map(plocalTime1)).save.transact
      _ <- Entity.i(1).localDateTimeMap(Map(plocalDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeMap(Map(poffsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeMap(Map(poffsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeMap(Map(pzonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidMap(Map(puuid1)).save.transact
      _ <- Entity.i(1).uriMap(Map(puri1)).save.transact
      _ <- Entity.i(1).byteMap(Map(pbyte1)).save.transact
      _ <- Entity.i(1).shortMap(Map(pshort1)).save.transact
      _ <- Entity.i(1).charMap(Map(pchar1)).save.transact

      // Update i where map doesn't have key
      _ <- Entity.i(2).stringMap_.not(string2).update.transact
      _ <- Entity.i(2).intMap_.not(string2).update.transact
      _ <- Entity.i(2).longMap_.not(string2).update.transact
      _ <- Entity.i(2).floatMap_.not(string2).update.transact
      _ <- Entity.i(2).doubleMap_.not(string2).update.transact
      _ <- Entity.i(2).booleanMap_.not(string2).update.transact
      _ <- Entity.i(2).bigIntMap_.not(string2).update.transact
      _ <- Entity.i(2).bigDecimalMap_.not(string2).update.transact
      _ <- Entity.i(2).dateMap_.not(string2).update.transact
      _ <- Entity.i(2).durationMap_.not(string2).update.transact
      _ <- Entity.i(2).instantMap_.not(string2).update.transact
      _ <- Entity.i(2).localDateMap_.not(string2).update.transact
      _ <- Entity.i(2).localTimeMap_.not(string2).update.transact
      _ <- Entity.i(2).localDateTimeMap_.not(string2).update.transact
      _ <- Entity.i(2).offsetTimeMap_.not(string2).update.transact
      _ <- Entity.i(2).offsetDateTimeMap_.not(string2).update.transact
      _ <- Entity.i(2).zonedDateTimeMap_.not(string2).update.transact
      _ <- Entity.i(2).uuidMap_.not(string2).update.transact
      _ <- Entity.i(2).uriMap_.not(string2).update.transact
      _ <- Entity.i(2).byteMap_.not(string2).update.transact
      _ <- Entity.i(2).shortMap_.not(string2).update.transact
      _ <- Entity.i(2).charMap_.not(string2).update.transact

      // i has been updated
      _ <- Entity.i.stringMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charMap_.query.get.map(_.head ==> 2)
    } yield ()
  }


  "Types, filter has value" - types { implicit conn =>
    for {
      // Initial values
      _ <- Entity.i(1).stringMap(Map(pstring1)).save.transact
      _ <- Entity.i(1).intMap(Map(pint1)).save.transact
      _ <- Entity.i(1).longMap(Map(plong1)).save.transact
      _ <- Entity.i(1).floatMap(Map(pfloat1)).save.transact
      _ <- Entity.i(1).doubleMap(Map(pdouble1)).save.transact
      _ <- Entity.i(1).booleanMap(Map(pboolean1)).save.transact
      _ <- Entity.i(1).bigIntMap(Map(pbigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalMap(Map(pbigDecimal1)).save.transact
      _ <- Entity.i(1).dateMap(Map(pdate1)).save.transact
      _ <- Entity.i(1).durationMap(Map(pduration1)).save.transact
      _ <- Entity.i(1).instantMap(Map(pinstant1)).save.transact
      _ <- Entity.i(1).localDateMap(Map(plocalDate1)).save.transact
      _ <- Entity.i(1).localTimeMap(Map(plocalTime1)).save.transact
      _ <- Entity.i(1).localDateTimeMap(Map(plocalDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeMap(Map(poffsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeMap(Map(poffsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeMap(Map(pzonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidMap(Map(puuid1)).save.transact
      _ <- Entity.i(1).uriMap(Map(puri1)).save.transact
      _ <- Entity.i(1).byteMap(Map(pbyte1)).save.transact
      _ <- Entity.i(1).shortMap(Map(pshort1)).save.transact
      _ <- Entity.i(1).charMap(Map(pchar1)).save.transact

      // Update i where map has key
      _ <- Entity.i(2).stringMap_.has(string1).update.transact
      _ <- Entity.i(2).intMap_.has(int1).update.transact
      _ <- Entity.i(2).longMap_.has(long1).update.transact
      _ <- Entity.i(2).floatMap_.has(float1).update.transact
      _ <- Entity.i(2).doubleMap_.has(double1).update.transact
      _ <- Entity.i(2).booleanMap_.has(boolean1).update.transact
      _ <- Entity.i(2).bigIntMap_.has(bigInt1).update.transact
      _ <- Entity.i(2).bigDecimalMap_.has(bigDecimal1).update.transact
      _ <- Entity.i(2).dateMap_.has(date1).update.transact
      _ <- Entity.i(2).durationMap_.has(duration1).update.transact
      _ <- Entity.i(2).instantMap_.has(instant1).update.transact
      _ <- Entity.i(2).localDateMap_.has(localDate1).update.transact
      _ <- Entity.i(2).localTimeMap_.has(localTime1).update.transact
      _ <- Entity.i(2).localDateTimeMap_.has(localDateTime1).update.transact
      _ <- Entity.i(2).offsetTimeMap_.has(offsetTime1).update.transact
      _ <- Entity.i(2).offsetDateTimeMap_.has(offsetDateTime1).update.transact
      _ <- Entity.i(2).zonedDateTimeMap_.has(zonedDateTime1).update.transact
      _ <- Entity.i(2).uuidMap_.has(uuid1).update.transact
      _ <- Entity.i(2).uriMap_.has(uri1).update.transact
      _ <- Entity.i(2).byteMap_.has(byte1).update.transact
      _ <- Entity.i(2).shortMap_.has(short1).update.transact
      _ <- Entity.i(2).charMap_.has(char1).update.transact

      // i has been updated
      _ <- Entity.i.stringMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charMap_.query.get.map(_.head ==> 2)
    } yield ()
  }


  "Types, filter hasNo value" - types { implicit conn =>
    for {
      // Initial values
      _ <- Entity.i(1).stringMap(Map(pstring1)).save.transact
      _ <- Entity.i(1).intMap(Map(pint1)).save.transact
      _ <- Entity.i(1).longMap(Map(plong1)).save.transact
      _ <- Entity.i(1).floatMap(Map(pfloat1)).save.transact
      _ <- Entity.i(1).doubleMap(Map(pdouble1)).save.transact
      _ <- Entity.i(1).booleanMap(Map(pboolean1)).save.transact
      _ <- Entity.i(1).bigIntMap(Map(pbigInt1)).save.transact
      _ <- Entity.i(1).bigDecimalMap(Map(pbigDecimal1)).save.transact
      _ <- Entity.i(1).dateMap(Map(pdate1)).save.transact
      _ <- Entity.i(1).durationMap(Map(pduration1)).save.transact
      _ <- Entity.i(1).instantMap(Map(pinstant1)).save.transact
      _ <- Entity.i(1).localDateMap(Map(plocalDate1)).save.transact
      _ <- Entity.i(1).localTimeMap(Map(plocalTime1)).save.transact
      _ <- Entity.i(1).localDateTimeMap(Map(plocalDateTime1)).save.transact
      _ <- Entity.i(1).offsetTimeMap(Map(poffsetTime1)).save.transact
      _ <- Entity.i(1).offsetDateTimeMap(Map(poffsetDateTime1)).save.transact
      _ <- Entity.i(1).zonedDateTimeMap(Map(pzonedDateTime1)).save.transact
      _ <- Entity.i(1).uuidMap(Map(puuid1)).save.transact
      _ <- Entity.i(1).uriMap(Map(puri1)).save.transact
      _ <- Entity.i(1).byteMap(Map(pbyte1)).save.transact
      _ <- Entity.i(1).shortMap(Map(pshort1)).save.transact
      _ <- Entity.i(1).charMap(Map(pchar1)).save.transact

      // Update i where map doesn't have key
      _ <- Entity.i(2).stringMap_.hasNo(string2).update.transact
      _ <- Entity.i(2).intMap_.hasNo(int2).update.transact
      _ <- Entity.i(2).longMap_.hasNo(long2).update.transact
      _ <- Entity.i(2).floatMap_.hasNo(float2).update.transact
      _ <- Entity.i(2).doubleMap_.hasNo(double2).update.transact
      _ <- Entity.i(2).booleanMap_.hasNo(boolean2).update.transact
      _ <- Entity.i(2).bigIntMap_.hasNo(bigInt2).update.transact
      _ <- Entity.i(2).bigDecimalMap_.hasNo(bigDecimal2).update.transact
      _ <- Entity.i(2).dateMap_.hasNo(date2).update.transact
      _ <- Entity.i(2).durationMap_.hasNo(duration2).update.transact
      _ <- Entity.i(2).instantMap_.hasNo(instant2).update.transact
      _ <- Entity.i(2).localDateMap_.hasNo(localDate2).update.transact
      _ <- Entity.i(2).localTimeMap_.hasNo(localTime2).update.transact
      _ <- Entity.i(2).localDateTimeMap_.hasNo(localDateTime2).update.transact
      _ <- Entity.i(2).offsetTimeMap_.hasNo(offsetTime2).update.transact
      _ <- Entity.i(2).offsetDateTimeMap_.hasNo(offsetDateTime2).update.transact
      _ <- Entity.i(2).zonedDateTimeMap_.hasNo(zonedDateTime2).update.transact
      _ <- Entity.i(2).uuidMap_.hasNo(uuid2).update.transact
      _ <- Entity.i(2).uriMap_.hasNo(uri2).update.transact
      _ <- Entity.i(2).byteMap_.hasNo(byte2).update.transact
      _ <- Entity.i(2).shortMap_.hasNo(short2).update.transact
      _ <- Entity.i(2).charMap_.hasNo(char2).update.transact

      // i has been updated
      _ <- Entity.i.stringMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.intMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.longMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.floatMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.doubleMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.booleanMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigIntMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimalMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.dateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.durationMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instantMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTimeMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuidMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uriMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byteMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.shortMap_.query.get.map(_.head ==> 2)
      _ <- Entity.i.charMap_.query.get.map(_.head ==> 2)
    } yield ()
  }
}
