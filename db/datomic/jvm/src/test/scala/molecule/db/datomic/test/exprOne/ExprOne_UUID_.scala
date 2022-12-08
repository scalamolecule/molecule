// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import java.util.UUID
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_UUID_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, uuid1)
      val b = (2, uuid2)
      val c = (3, uuid3)
      Ns.i.uuid.insert(List(a, b, c)).transact

      // Find all attribute values
      Ns.i.a1.uuid.query.get ==> List(a, b, c)

      // Find value(s) matching
      Ns.i.a1.uuid(uuid0).query.get ==> List()
      Ns.i.a1.uuid(uuid1).query.get ==> List(a)
      Ns.i.a1.uuid(Seq(uuid0)).query.get ==> List()
      Ns.i.a1.uuid(Seq(uuid1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.uuid(uuid1, uuid2).query.get ==> List(a, b)
      Ns.i.a1.uuid(uuid1, uuid0).query.get ==> List(a)
      Ns.i.a1.uuid(Seq(uuid1, uuid2)).query.get ==> List(a, b)
      Ns.i.a1.uuid(Seq(uuid1, uuid0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.uuid(Seq.empty[UUID]).query.get ==> List()

      // Find values not matching
      Ns.i.a1.uuid.not(uuid0).query.get ==> List(a, b, c)
      Ns.i.a1.uuid.not(uuid1).query.get ==> List(b, c)
      Ns.i.a1.uuid.not(uuid2).query.get ==> List(a, c)
      Ns.i.a1.uuid.not(uuid3).query.get ==> List(a, b)
      Ns.i.a1.uuid.not(Seq(uuid0)).query.get ==> List(a, b, c)
      Ns.i.a1.uuid.not(Seq(uuid1)).query.get ==> List(b, c)
      Ns.i.a1.uuid.not(Seq(uuid2)).query.get ==> List(a, c)
      Ns.i.a1.uuid.not(Seq(uuid3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.uuid.not(uuid0, uuid1).query.get ==> List(b, c)
      Ns.i.a1.uuid.not(uuid1, uuid2).query.get ==> List(c)
      Ns.i.a1.uuid.not(uuid2, uuid3).query.get ==> List(a)
      Ns.i.a1.uuid.not(Seq(uuid0, uuid1)).query.get ==> List(b, c)
      Ns.i.a1.uuid.not(Seq(uuid1, uuid2)).query.get ==> List(c)
      Ns.i.a1.uuid.not(Seq(uuid2, uuid3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      Ns.i.a1.uuid.not(Seq.empty[UUID]).query.get ==> List(a, b, c)

      // Find values in range
      Ns.i.a1.uuid.<(uuid2).query.get ==> List(a)
      Ns.i.a1.uuid.>(uuid2).query.get ==> List(c)
      Ns.i.a1.uuid.<=(uuid2).query.get ==> List(a, b)
      Ns.i.a1.uuid.>=(uuid2).query.get ==> List(b, c)
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      Ns.i.uuid_?.insert(List(
        (a, Some(uuid1)),
        (b, Some(uuid2)),
        (c, Some(uuid3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      Ns.i.a1.uuid_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      Ns.i.a1.uuid_().query.get ==> List(x)

      // Match attribute values without returning them
      Ns.i.a1.uuid_(uuid0).query.get ==> List()
      Ns.i.a1.uuid_(uuid1).query.get ==> List(a)
      Ns.i.a1.uuid_(Seq(uuid0)).query.get ==> List()
      Ns.i.a1.uuid_(Seq(uuid1)).query.get ==> List(a)
      // OR semantics for multiple args
      Ns.i.a1.uuid_(uuid1, uuid2).query.get ==> List(a, b)
      Ns.i.a1.uuid_(uuid1, uuid0).query.get ==> List(a)
      Ns.i.a1.uuid_(Seq(uuid1, uuid2)).query.get ==> List(a, b)
      Ns.i.a1.uuid_(Seq(uuid1, uuid0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.uuid_(Seq.empty[UUID]).query.get ==> List()

      // Match non-matching values without returning them
      Ns.i.a1.uuid_.not(uuid0).query.get ==> List(a, b, c)
      Ns.i.a1.uuid_.not(uuid1).query.get ==> List(b, c)
      Ns.i.a1.uuid_.not(uuid2).query.get ==> List(a, c)
      Ns.i.a1.uuid_.not(uuid3).query.get ==> List(a, b)
      Ns.i.a1.uuid_.not(Seq(uuid0)).query.get ==> List(a, b, c)
      Ns.i.a1.uuid_.not(Seq(uuid1)).query.get ==> List(b, c)
      Ns.i.a1.uuid_.not(Seq(uuid2)).query.get ==> List(a, c)
      Ns.i.a1.uuid_.not(Seq(uuid3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      Ns.i.a1.uuid_.not(uuid0, uuid1).query.get ==> List(b, c)
      Ns.i.a1.uuid_.not(uuid1, uuid2).query.get ==> List(c)
      Ns.i.a1.uuid_.not(uuid2, uuid3).query.get ==> List(a)
      Ns.i.a1.uuid_.not(Seq(uuid0, uuid1)).query.get ==> List(b, c)
      Ns.i.a1.uuid_.not(Seq(uuid1, uuid2)).query.get ==> List(c)
      Ns.i.a1.uuid_.not(Seq(uuid2, uuid3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.uuid_.not(Seq.empty[UUID]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      Ns.i.a1.uuid_.<(uuid2).query.get ==> List(a)
      Ns.i.a1.uuid_.>(uuid2).query.get ==> List(c)
      Ns.i.a1.uuid_.<=(uuid2).query.get ==> List(a, b)
      Ns.i.a1.uuid_.>=(uuid2).query.get ==> List(b, c)
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(uuid1))
      val b = (2, Some(uuid2))
      val c = (3, Some(uuid3))
      val x = (4, Option.empty[UUID])
      Ns.i.uuid_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      Ns.i.a1.uuid_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      Ns.i.a1.uuid_?(Some(uuid0)).query.get ==> List()
      Ns.i.a1.uuid_?(Some(uuid1)).query.get ==> List(a)
      Ns.i.a1.uuid_?(Some(Seq(uuid0))).query.get ==> List()
      Ns.i.a1.uuid_?(Some(Seq(uuid1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      Ns.i.a1.uuid_?(Some(Seq(uuid1, uuid2))).query.get ==> List(a, b)
      Ns.i.a1.uuid_?(Some(Seq(uuid1, uuid0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      Ns.i.a1.uuid_?(Some(Seq.empty[UUID])).query.get ==> List()
      // None matches non-asserted/null values
      Ns.i.a1.uuid_?(Option.empty[UUID]).query.get ==> List(x)
      Ns.i.a1.uuid_?(Option.empty[Seq[UUID]]).query.get ==> List(x)

      // Find optional values not matching
      Ns.i.a1.uuid_?.not(Some(uuid0)).query.get ==> List(a, b, c)
      Ns.i.a1.uuid_?.not(Some(uuid1)).query.get ==> List(b, c)
      Ns.i.a1.uuid_?.not(Some(uuid2)).query.get ==> List(a, c)
      Ns.i.a1.uuid_?.not(Some(uuid3)).query.get ==> List(a, b)
      Ns.i.a1.uuid_?.not(Some(Seq(uuid0))).query.get ==> List(a, b, c)
      Ns.i.a1.uuid_?.not(Some(Seq(uuid1))).query.get ==> List(b, c)
      Ns.i.a1.uuid_?.not(Some(Seq(uuid2))).query.get ==> List(a, c)
      Ns.i.a1.uuid_?.not(Some(Seq(uuid3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      Ns.i.a1.uuid_?.not(Some(Seq(uuid0, uuid1))).query.get ==> List(b, c)
      Ns.i.a1.uuid_?.not(Some(Seq(uuid1, uuid2))).query.get ==> List(c)
      Ns.i.a1.uuid_?.not(Some(Seq(uuid2, uuid3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      Ns.i.a1.uuid_?.not(Some(Seq.empty[UUID])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      Ns.i.a1.uuid_?.not(Option.empty[UUID]).query.get ==> List(a, b, c)
      Ns.i.a1.uuid_?.not(Option.empty[Seq[UUID]]).query.get ==> List(a, b, c)

      // Find optional values in range
      Ns.i.a1.uuid_?.<(Some(uuid2)).query.get ==> List(a)
      Ns.i.a1.uuid_?.>(Some(uuid2)).query.get ==> List(c)
      Ns.i.a1.uuid_?.<=(Some(uuid2)).query.get ==> List(a, b)
      Ns.i.a1.uuid_?.>=(Some(uuid2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      Ns.i.a1.uuid_?.<(None).query.get ==> List()
      Ns.i.a1.uuid_?.<=(None).query.get ==> List()
      Ns.i.a1.uuid_?.>(None).query.get ==> List()
      Ns.i.a1.uuid_?.>=(None).query.get ==> List()
    }
  }
}