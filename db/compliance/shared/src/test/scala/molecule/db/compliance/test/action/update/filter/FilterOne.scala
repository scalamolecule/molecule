package molecule.db.compliance.test.action.update.filter

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*

case class FilterOne(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  // OBS: Updates by filters will update _all_ matching entities!

  // Use tacit attributes as filters to match entities to be updated.
  // Apply new values to mandatory attributes.

  "Value asserted" - types { implicit conn =>
    for {
      _ <- Entity.i_?.int.insert(
        (None, 0), // entity with missing i
        (Some(1), 1),
        (Some(2), 2),
      ).transact

      // Update all entities where `i` is asserted
      _ <- Entity
        .i_ // filter by tacit attribute
        .int(3) // apply new value to mandatory attribute
        .update.transact

      // 2 entities updated
      _ <- Entity.i_?.int.query.get.map(_ ==> List(
        (None, 0),
        (Some(1), 3), // updated
        (Some(2), 3), // updated
      ))

      // Order of attributes in update molecule makes no difference
      _ <- Entity.int(4).i_.update.transact
      _ <- Entity.i_?.a1.int.query.get.map(_ ==> List(
        (None, 0),
        (Some(1), 4), // updated
        (Some(2), 4), // updated
      ))
    } yield ()
  }


  "Value not asserted" - types { implicit conn =>
    for {
      _ <- Entity.i_?.int.insert(
        (None, 0), // entity with missing i
        (Some(1), 1),
        (Some(2), 2),
      ).transact

      // Update all entities where `i` is not asserted (null)
      _ <- Entity.i_().int(3).update.transact

      // 1 entity updated
      _ <- Entity.i_?.a1.int.query.get.map(_ ==> List(
        (None, 3), // updated
        (Some(1), 1),
        (Some(2), 2),
      ))
    } yield ()
  }


  "Equals id" - types { implicit conn =>
    for {
      List(a, b, c) <- Entity.i.int.insert(
        (1, 1),
        (1, 2),
        (2, 3),
      ).transact.map(_.ids)

      // Update entities with id a or b
      _ <- Entity(a, b).int(4).update.transact

      // 2 entities updated
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 4), // updated
        (b, 1, 4), // updated
        (c, 2, 3),
      ))

      // Nothing updated if no match
      _ <- Entity(42).int(5).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 4),
        (b, 1, 4),
        (c, 2, 3),
      ))
    } yield ()
  }


  "Equals value" - types { implicit conn =>
    for {
      List(a, b, c) <- Entity.i.int.insert(
        (1, 1),
        (1, 2),
        (2, 3),
      ).transact.map(_.ids)

      // Update all entities where `i` = 1
      _ <- Entity.i_(1).int(4).update.transact

      // 2 entities updated
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 4), // updated
        (b, 1, 4), // updated
        (c, 2, 3),
      ))

      // Nothing updated if no match
      _ <- Entity.i_(3).int(5).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 4),
        (b, 1, 4),
        (c, 2, 3),
      ))
    } yield ()
  }


  "Not equals" - types { implicit conn =>
    for {
      List(a, b, c) <- Entity.i.int.insert(
        (1, 1),
        (1, 2),
        (2, 3),
      ).transact.map(_.ids)

      // Update all entities where `i` != 1
      _ <- Entity.i_.not(1).int(4).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 1),
        (b, 1, 2),
        (c, 2, 4), // updated
      ))

      // Nothing updated if no match
      _ <- Entity.i_.not(1, 2).int(5).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 1),
        (b, 1, 2),
        (c, 2, 4),
      ))
    } yield ()
  }


  "Comparison" - types { implicit conn =>
    for {
      List(a, b, c) <- Entity.i.int.insert(
        (1, 1),
        (1, 2),
        (2, 3),
      ).transact.map(_.ids)

      // Update all entities where `i` > 1
      _ <- Entity.i_.>(1).int(4).update.transact

      // 1 entity updated
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 1),
        (b, 1, 2),
        (c, 2, 4), // updated
      ))

      _ <- Entity.i_.>=(1).int(5).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 5), // updated
        (b, 1, 5), // updated
        (c, 2, 5), // updated
      ))

      _ <- Entity.i_.<(2).int(6).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 6), // updated
        (b, 1, 6), // updated
        (c, 2, 5),
      ))

      _ <- Entity.i_.<=(2).int(7).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 7), // updated
        (b, 1, 7), // updated
        (c, 2, 7), // updated
      ))

      // Nothing updated if no match
      _ <- Entity.i_.>(5).int(8).update.transact
      _ <- Entity.id.a1.i.int.query.get.map(_ ==> List(
        (a, 1, 7),
        (b, 1, 7),
        (c, 2, 7),
      ))
    } yield ()
  }


  "Multiple filters" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (1, 1),
        (2, 2),
        (3, 3),
        (4, 4),
      ).transact

      // Update all entities where `i` is between 1 and 4
      _ <- Entity.i_.>(1).i_.<(4).int(5).update.transact

      // 2 entities deleted
      _ <- Entity.i.a1.int.query.get.map(_ ==> List(
        (1, 1),
        (2, 5), // updated
        (3, 5), // updated
        (4, 4),
      ))
    } yield ()
  }


  "String ops" - types { implicit conn =>
    for {
      _ <- Entity.s.int.insert(
        ("bar", 1),
        ("baz", 2),
        ("foo", 3),
      ).transact

      // Update all entities where `s` starts with "ba"
      _ <- Entity.s_.startsWith("ba").int(4).update.transact

      // 2 entities updated
      _ <- Entity.s.a1.int.query.get.map(_ ==> List(
        ("bar", 4), // updated
        ("baz", 4), // updated
        ("foo", 3),
      ))

      _ <- Entity.s_.endsWith("oo").int(5).update.transact
      _ <- Entity.s.a1.int.query.get.map(_ ==> List(
        ("bar", 4),
        ("baz", 4),
        ("foo", 5), // updated
      ))

      _ <- Entity.s_.contains("a").int(6).update.transact
      _ <- Entity.s.a1.int.query.get.map(_ ==> List(
        ("bar", 6), // updated
        ("baz", 6), // updated
        ("foo", 5),
      ))

      _ <- Entity.s_.matches("[a-z]{3}").int(7).update.transact
      _ <- Entity.s.a1.int.query.get.map(_ ==> List(
        ("bar", 7), // updated
        ("baz", 7), // updated
        ("foo", 7), // updated
      ))

      // No-match updates that won't change data
      _ <- Entity.s_.startsWith("bo").int(8).update.transact
      _ <- Entity.s_.endsWith("aa").int(8).update.transact
      _ <- Entity.s_.contains("x").int(8).update.transact

      // case-sensitive reg-ex won't match lower-case strings foo/bar/baz
      _ <- Entity.s_.matches("[A_Z]+").int(8).update.transact

      // Nothing updated if no match
      _ <- Entity.s.a1.int.query.get.map(_ ==> List(
        ("bar", 7),
        ("baz", 7),
        ("foo", 7),
      ))
    } yield ()
  }


  "Number ops" - types { implicit conn =>
    for {
      _ <- Entity.i.int.insert(
        (1, 1),
        (2, 2),
        (3, 3),
      ).transact

      // Update all entities where `i` is divisible by 3
      _ <- Entity.i_.%(3, 0).int(4).update.transact

      // 1 entity updated
      _ <- Entity.i.a1.int.query.get.map(_ ==> List(
        (1, 1),
        (2, 2),
        (3, 4), // updated
      ))

      _ <- Entity.i_.even.int(5).update.transact
      _ <- Entity.i.a1.int.query.get.map(_ ==> List(
        (1, 1),
        (2, 5), // updated
        (3, 4),
      ))

      _ <- Entity.i_.odd.int(6).update.transact
      _ <- Entity.i.a1.int.query.get.map(_ ==> List(
        (1, 6), // updated
        (2, 5),
        (3, 6), // updated
      ))

      // Nothing updated if no match
      _ <- Entity.i_.%(10, 8).int(7).update.transact
      _ <- Entity.i.a1.int.query.get.map(_ ==> List(
        (1, 6),
        (2, 5),
        (3, 6),
      ))
    } yield ()
  }


  "Update filter value itself" - types { implicit conn =>
    for {
      _ <- Entity.i.insert(1).transact
      _ <- Entity.i.query.get.map(_ ==> List(1))

      // Filter by tacit i_(1), update to new mandatory i(2)
      _ <- Entity.i_(1).i(2).update.transact
      _ <- Entity.i.query.get.map(_ ==> List(2))
    } yield ()
  }


  "Types, filter equal" - types { implicit conn =>
    for {
      // Initial values
      _ <- Entity.i(1).string(string1).save.transact
      _ <- Entity.i(1).int(int1).save.transact
      _ <- Entity.i(1).long(long1).save.transact
      _ <- Entity.i(1).float(float1).save.transact
      _ <- Entity.i(1).double(double1).save.transact
      _ <- Entity.i(1).boolean(boolean1).save.transact
      _ <- Entity.i(1).bigInt(bigInt1).save.transact
      _ <- Entity.i(1).bigDecimal(bigDecimal1).save.transact
      _ <- Entity.i(1).date(date1).save.transact
      _ <- Entity.i(1).duration(duration1).save.transact
      _ <- Entity.i(1).instant(instant1).save.transact
      _ <- Entity.i(1).localDate(localDate1).save.transact
      _ <- Entity.i(1).localTime(localTime1).save.transact
      _ <- Entity.i(1).localDateTime(localDateTime1).save.transact
      _ <- Entity.i(1).offsetTime(offsetTime1).save.transact
      _ <- Entity.i(1).offsetDateTime(offsetDateTime1).save.transact
      _ <- Entity.i(1).zonedDateTime(zonedDateTime1).save.transact
      _ <- Entity.i(1).uuid(uuid1).save.transact
      _ <- Entity.i(1).uri(uri1).save.transact
      _ <- Entity.i(1).byte(byte1).save.transact
      _ <- Entity.i(1).short(short1).save.transact
      _ <- Entity.i(1).char(char1).save.transact

      ref1 <- Ref.i(1).save.transact.map(_.id)
      _ <- Entity.i(1).ref(ref1).save.transact

      // Update i using equal filter
      _ <- Entity.i(2).string_(string1).update.transact
      _ <- Entity.i(2).int_(int1).update.transact
      _ <- Entity.i(2).long_(long1).update.transact
      _ <- Entity.i(2).float_(float1).update.transact
      _ <- Entity.i(2).double_(double1).update.transact
      _ <- Entity.i(2).boolean_(boolean1).update.transact
      _ <- Entity.i(2).bigInt_(bigInt1).update.transact
      _ <- Entity.i(2).bigDecimal_(bigDecimal1).update.transact
      _ <- Entity.i(2).date_(date1).update.transact
      _ <- Entity.i(2).duration_(duration1).update.transact
      _ <- Entity.i(2).instant_(instant1).update.transact
      _ <- Entity.i(2).localDate_(localDate1).update.transact
      _ <- Entity.i(2).localTime_(localTime1).update.transact
      _ <- Entity.i(2).localDateTime_(localDateTime1).update.transact
      _ <- Entity.i(2).offsetTime_(offsetTime1).update.transact
      _ <- Entity.i(2).offsetDateTime_(offsetDateTime1).update.transact
      _ <- Entity.i(2).zonedDateTime_(zonedDateTime1).update.transact
      _ <- Entity.i(2).uuid_(uuid1).update.transact
      _ <- Entity.i(2).uri_(uri1).update.transact
      _ <- Entity.i(2).byte_(byte1).update.transact
      _ <- Entity.i(2).short_(short1).update.transact
      _ <- Entity.i(2).char_(char1).update.transact
      _ <- Entity.i(2).ref_(ref1).update.transact

      // i has been updated
      _ <- Entity.i.string_.query.get.map(_.head ==> 2)
      _ <- Entity.i.int_.query.get.map(_.head ==> 2)
      _ <- Entity.i.long_.query.get.map(_.head ==> 2)
      _ <- Entity.i.float_.query.get.map(_.head ==> 2)
      _ <- Entity.i.double_.query.get.map(_.head ==> 2)
      _ <- Entity.i.boolean_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigInt_.query.get.map(_.head ==> 2)
      _ <- Entity.i.bigDecimal_.query.get.map(_.head ==> 2)
      _ <- Entity.i.date_.query.get.map(_.head ==> 2)
      _ <- Entity.i.duration_.query.get.map(_.head ==> 2)
      _ <- Entity.i.instant_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDate_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localTime_.query.get.map(_.head ==> 2)
      _ <- Entity.i.localDateTime_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetTime_.query.get.map(_.head ==> 2)
      _ <- Entity.i.offsetDateTime_.query.get.map(_.head ==> 2)
      _ <- Entity.i.zonedDateTime_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uuid_.query.get.map(_.head ==> 2)
      _ <- Entity.i.uri_.query.get.map(_.head ==> 2)
      _ <- Entity.i.byte_.query.get.map(_.head ==> 2)
      _ <- Entity.i.short_.query.get.map(_.head ==> 2)
      _ <- Entity.i.char_.query.get.map(_.head ==> 2)
      _ <- Entity.i.ref_.query.get.map(_.head ==> 2)
    } yield ()
  }
}
