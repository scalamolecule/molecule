// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import java.util.UUID
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_UUID_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, uuid1)
      val b = (2, uuid2)
      val c = (3, uuid3)
      One.n.uuid.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.uuid(uuid0).query.get ==> List()
      One.n.a1.uuid(uuid1).query.get ==> List(a)
      One.n.a1.uuid(uuid1, uuid2).query.get ==> List(a, b)
      One.n.a1.uuid(uuid1, uuid0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.uuid(Seq(uuid0)).query.get ==> List()
      One.n.a1.uuid(Seq(uuid1)).query.get ==> List(a)
      One.n.a1.uuid(Seq(uuid1, uuid2)).query.get ==> List(a, b)
      One.n.a1.uuid(Seq(uuid1, uuid0)).query.get ==> List(a)
      One.n.a1.uuid(Seq.empty[UUID]).query.get ==> List()


      One.n.a1.uuid.not(uuid0).query.get ==> List(a, b, c)
      One.n.a1.uuid.not(uuid1).query.get ==> List(b, c)
      One.n.a1.uuid.not(uuid2).query.get ==> List(a, c)
      One.n.a1.uuid.not(uuid3).query.get ==> List(a, b)
      One.n.a1.uuid.not(uuid0, uuid1).query.get ==> List(b, c)
      One.n.a1.uuid.not(uuid1, uuid2).query.get ==> List(c)
      One.n.a1.uuid.not(uuid2, uuid3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.uuid.not(Seq(uuid0)).query.get ==> List(a, b, c)
      One.n.a1.uuid.not(Seq(uuid1)).query.get ==> List(b, c)
      One.n.a1.uuid.not(Seq(uuid2)).query.get ==> List(a, c)
      One.n.a1.uuid.not(Seq(uuid3)).query.get ==> List(a, b)
      One.n.a1.uuid.not(Seq(uuid0, uuid1)).query.get ==> List(b, c)
      One.n.a1.uuid.not(Seq(uuid1, uuid2)).query.get ==> List(c)
      One.n.a1.uuid.not(Seq(uuid2, uuid3)).query.get ==> List(a)
      One.n.a1.uuid.not(Seq.empty[UUID]).query.get ==> List(a, b, c)


      One.n.a1.uuid.<(uuid2).query.get ==> List(a)
      One.n.a1.uuid.>(uuid2).query.get ==> List(c)
      One.n.a1.uuid.<=(uuid2).query.get ==> List(a, b)
      One.n.a1.uuid.>=(uuid2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.uuid_?.insert(List(
        (1, Some(uuid1)),
        (2, Some(uuid2)),
        (3, Some(uuid3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.uuid_.apply().query.get ==> List(d)

      One.n.a1.uuid_(uuid0).query.get ==> List()
      One.n.a1.uuid_.apply(uuid1).query.get ==> List(a)
      One.n.a1.uuid_(uuid1, uuid2).query.get ==> List(a, b)
      One.n.a1.uuid_(uuid1, uuid0).query.get ==> List(a)
      // Same as
      One.n.a1.uuid_(Seq(uuid0)).query.get ==> List()
      One.n.a1.uuid_(Seq(uuid1)).query.get ==> List(a)
      One.n.a1.uuid_(Seq(uuid1, uuid2)).query.get ==> List(a, b)
      One.n.a1.uuid_(Seq(uuid1, uuid0)).query.get ==> List(a)
      One.n.a1.uuid_(Seq.empty[UUID]).query.get ==> List()


      One.n.a1.uuid_.not(uuid0).query.get ==> List(a, b, c)
      One.n.a1.uuid_.not(uuid1).query.get ==> List(b, c)
      One.n.a1.uuid_.not(uuid2).query.get ==> List(a, c)
      One.n.a1.uuid_.not(uuid3).query.get ==> List(a, b)
      One.n.a1.uuid_.not(uuid0, uuid1).query.get ==> List(b, c)
      One.n.a1.uuid_.not(uuid1, uuid2).query.get ==> List(c)
      One.n.a1.uuid_.not(uuid2, uuid3).query.get ==> List(a)
      // Same as
      One.n.a1.uuid_.not(Seq(uuid0)).query.get ==> List(a, b, c)
      One.n.a1.uuid_.not(Seq(uuid1)).query.get ==> List(b, c)
      One.n.a1.uuid_.not(Seq(uuid2)).query.get ==> List(a, c)
      One.n.a1.uuid_.not(Seq(uuid3)).query.get ==> List(a, b)
      One.n.a1.uuid_.not(Seq(uuid0, uuid1)).query.get ==> List(b, c)
      One.n.a1.uuid_.not(Seq(uuid1, uuid2)).query.get ==> List(c)
      One.n.a1.uuid_.not(Seq(uuid2, uuid3)).query.get ==> List(a)
      One.n.a1.uuid_.not(Seq.empty[UUID]).query.get ==> List(a, b, c)


      One.n.a1.uuid_.<(uuid2).query.get ==> List(a)
      One.n.a1.uuid_.>(uuid2).query.get ==> List(c)
      One.n.a1.uuid_.<=(uuid2).query.get ==> List(a, b)
      One.n.a1.uuid_.>=(uuid2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(uuid1))
      val b = (2, Some(uuid2))
      val c = (3, Some(uuid3))
      val x = (4, Option.empty[UUID])

      One.n.uuid_?.insert(List(a, b, c, x)).transact

      One.n.a1.uuid_?(Some(uuid0)).query.get ==> List()
      One.n.a1.uuid_?.apply(Some(uuid1)).query.get ==> List(a)

      One.n.a1.uuid_?(Some(Seq(uuid0))).query.get ==> List()
      One.n.a1.uuid_?(Some(Seq(uuid1))).query.get ==> List(a)
      One.n.a1.uuid_?(Some(Seq(uuid1, uuid2))).query.get ==> List(a, b)
      One.n.a1.uuid_?(Some(Seq(uuid1, uuid0))).query.get ==> List(a)
      One.n.a1.uuid_?(Some(Seq.empty[UUID])).query.get ==> List()

      One.n.a1.uuid_?(Option.empty[UUID]).query.get ==> List(x)
      One.n.a1.uuid_?(Option.empty[Seq[UUID]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.uuid_?.not(Some(uuid0)).query.get ==> List(a, b, c)
      One.n.a1.uuid_?.not(Some(uuid1)).query.get ==> List(b, c)
      One.n.a1.uuid_?.not(Some(uuid2)).query.get ==> List(a, c)
      One.n.a1.uuid_?.not(Some(uuid3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.uuid_?.not(Some(Seq(uuid0))).query.get ==> List(a, b, c)
      One.n.a1.uuid_?.not(Some(Seq(uuid1))).query.get ==> List(b, c)
      One.n.a1.uuid_?.not(Some(Seq(uuid2))).query.get ==> List(a, c)
      One.n.a1.uuid_?.not(Some(Seq(uuid3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.uuid_?.not(Some(Seq(uuid0, uuid1))).query.get ==> List(b, c)
      One.n.a1.uuid_?.not(Some(Seq(uuid1, uuid2))).query.get ==> List(c)
      One.n.a1.uuid_?.not(Some(Seq(uuid2, uuid3))).query.get ==> List(a)
      One.n.a1.uuid_?.not(Some(Seq.empty[UUID])).query.get ==> List(a, b, c)

      One.n.a1.uuid_?.not(Option.empty[UUID]).query.get ==> List(a, b, c)
      One.n.a1.uuid_?.not(Option.empty[Seq[UUID]]).query.get ==> List(a, b, c)


      One.n.a1.uuid_?.<(Some(uuid2)).query.get ==> List(a)
      One.n.a1.uuid_?.>(Some(uuid2)).query.get ==> List(c)
      One.n.a1.uuid_?.<=(Some(uuid2)).query.get ==> List(a, b)
      One.n.a1.uuid_?.>=(Some(uuid2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.uuid_?.<(None).query.get ==> List()
      One.n.a1.uuid_?.<=(None).query.get ==> List()
      One.n.a1.uuid_?.>(None).query.get ==> List()
      One.n.a1.uuid_?.>=(None).query.get ==> List()
    }
  }
}
