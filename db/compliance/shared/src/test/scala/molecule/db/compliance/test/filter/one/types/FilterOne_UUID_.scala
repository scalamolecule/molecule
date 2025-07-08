// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.util.UUID
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_UUID_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, uuid1)
    val b = (2, uuid2)
    val c = (3, uuid3)
    for {
      _ <- Entity.i.uuid.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.uuid.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.uuid(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuid(uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid(Seq(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuid(Seq(uuid1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.uuid(uuid1, uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid(uuid1, uuid0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid(Seq(uuid1, uuid0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.uuid(Seq.empty[UUID]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.uuid.not(uuid0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uuid.not(uuid1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid.not(uuid2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uuid.not(uuid3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid.not(Seq(uuid0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uuid.not(Seq(uuid1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid.not(Seq(uuid2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uuid.not(Seq(uuid3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.uuid.not(uuid0, uuid1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid.not(uuid1, uuid2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uuid.not(uuid2, uuid3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid.not(Seq(uuid0, uuid1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid.not(Seq(uuid1, uuid2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uuid.not(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.uuid.not(Seq.empty[UUID]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.uuid.<(uuid2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid.>(uuid2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uuid.<=(uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid.>=(uuid2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.uuid_?.insert(List(
        (a, Some(uuid1)),
        (b, Some(uuid2)),
        (c, Some(uuid3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.uuid_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.uuid_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.uuid_(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuid_(uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid_(Seq(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuid_(Seq(uuid1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.uuid_(uuid1, uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid_(uuid1, uuid0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid_(Seq(uuid1, uuid2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid_(Seq(uuid1, uuid0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.uuid_(Seq.empty[UUID]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.uuid_.not(uuid0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uuid_.not(uuid1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid_.not(uuid2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uuid_.not(uuid3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.uuid_.not(uuid0, uuid1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid_.not(uuid1, uuid2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uuid_.not(uuid2, uuid3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid0, uuid1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid1, uuid2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uuid_.not(Seq(uuid2, uuid3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.uuid_.not(Seq.empty[UUID]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.uuid_.<(uuid2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid_.>(uuid2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.uuid_.<=(uuid2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid_.>=(uuid2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(uuid1))
    val b = (2, Some(uuid2))
    val c = (3, Some(uuid3))
    val x = (4, Option.empty[UUID])
    for {
      _ <- Entity.i.uuid_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.uuid_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.uuid_?(Some(uuid0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.uuid_?(Some(uuid1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.uuid_?(Option.empty[UUID]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.uuid_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.uuid.insert(
        (1, uuid1),
        (2, uuid2),
        (3, uuid3),
        (4, uuid4),
        (5, uuid5),
        (6, uuid6),
        (7, uuid7),
        (8, uuid8),
        (9, uuid9),
      ).transact

      _ <- Entity.i.a1.uuid_.>(uuid2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.uuid_.>(uuid2).uuid_.<=(uuid8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.uuid_.>(uuid2).uuid_.<=(uuid8).uuid_.not(uuid4, uuid5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
