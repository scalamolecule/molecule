package molecule.db.datomic.test

import datomic.Peer
import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.exprSet.ExprSet_Int.{int1, int2}
import utest._
import java.util.{Collections, List => jList, Set => jSet}
import datomic.Peer.tempid
import molecule.coreTests.dataModels.core.types.dsl.CardOne.One
import molecule.db.datomic.facade.Conn_Peer
import molecule.db.datomic.test.exprSet.ExprSet_Float_.float1
import datomic.Connection.{DB_AFTER, DB_BEFORE, TEMPIDS, TX_DATA}


object AdhocJVM extends DatomicTestSuite {

  lazy val tests = Tests {

    //    "one" - cardOne { implicit conn =>
    //      val a = (1, float1)
    //      val b = (2, float2)
    //      val c = (3, float3)
    //      One.n.float.insert(List(a, b, c)).transact
    //
    //      // Find all attribute values
    //      One.n.a1.float.query.get ==> List(a, b, c)
    //
    //      One.n.a1.float(float1).query.get ==> List(a)
    //
    //    }

    "set double" - cardSet { implicit conn =>

      val t  = (1, Some(Set(true)))
      val f  = (2, Some(Set(false)))
      val tf = (3, Some(Set(true, false)))
      val x  = (4, None)
      NsSet.n.booleans_?.insert(t, f, tf, x).transact

      //      val e1 = datomic.Peer.tempid(":db.part/user")
      //      val e2 = datomic.Peer.tempid(":db.part/user")
      //
      //      val txr = conn.asInstanceOf[Conn_Peer].peerConn.transact(
      //        datomic.Util.list(
      //          datomic.Util.list(":db/add", e1, ":NsSet/n", 1),
      //          datomic.Util.list(":db/add", e1, ":NsSet/booleans", true.asInstanceOf[java.lang.Boolean]),
      //          datomic.Util.list(":db/add", e2, ":NsSet/n", 2),
      //          datomic.Util.list(":db/add", e2, ":NsSet/booleans", false.asInstanceOf[java.lang.Boolean])
      //        )
      //      )
      //      println(txr.get())
      //      val db = txr.get().get(datomic.Connection.DB_AFTER)

      Peer.q(
        """[:find  ?b
          |        (pull ?a-?c [[:NsSet/booleans :limit nil]])
          | :where [(identity ?a) ?a-?c]
          |        (not [?a :NsSet/booleans])
          |        [?a :NsSet/n ?b]]""".stripMargin,
        conn.db
      ).forEach { r =>
        println(r)
      }



      //      NsSet.n.a1.booleans.query.get.foreach(println)
      //            NsSet.n.a1.booleans_?.query.get ==> List(t, f, tf, x)


      // None matches non-asserted values
      NsSet.n.a1.booleans_?(Option.empty[Boolean]).query.get ==> List(x)
      //        NsSet.n.a1.booleans_?(Option.empty[Seq[Boolean]]).query.get ==> List(x)
      //        NsSet.n.a1.booleans_?(Option.empty[Set[Boolean]]).query.get ==> List(x)
      //        NsSet.n.a1.booleans_?(Option.empty[Seq[Set[Boolean]]]).query.get ==> List(x)
    }
  }
}
