package molecule.db.datomic.test

import molecule.core.transaction.Save
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.transaction.Save_stmts
import utest._
import molecule.boilerplate.ast.Model._


object AdhocJVM extends DatomicTestSuite {


  lazy val tests = Tests {


    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i(1).s("a").save.transact
        //        _ <- Ns.i(2).s("b").save.transact
        //        _ <- Ns.i.s.query.get.map(_ ==> List((1, "a"), (2, "b")))

        //        _ <- Ns.i(1).Ref.s("a").save.transact
        //        _ <- Ns.i(2).Ref.s("b").save.transact
        //        _ <- Ns.i.Ref.s.query.get.map(_ ==> List((1, "a"), (2, "b")))

//        _ <- Ns.i.s.insert((1, "a"), (2, "b")).transact
//        _ <- Ns.i.s.query.get.map(_ ==> List((1, "a"), (2, "b")))


        //        _ <- Ns.i.Refs.*(Ref.i).insert(
        //          (1, List(2, 3))
        //        ).transact
        //        _ <- Ns.i.Refs.*(Ref.i.a1).query.get.map(_.head ==>
        //          //        _ <- Ns.i.Refs.*(Ref.i).query.get.map(_.head ==>
        //          (1, List(2, 3))
        //        )


        eid <- Ns.int.insert(1).transact.map(_.eids.head)
//        _ <- Ns.int.query.get.map(_ ==> List(1))
//
//        // Update existing value
//        _ <- Ns(eid).int(2).update.transact
//        _ <- Ns.int.query.get.map(_ ==> List(2))
      } yield ()
    }


//    "refs" - refs { implicit conn =>
//
//
//      //      for {
//      //        _ <- Ns.i(7).save.transact
//      //
//      //      } yield ()
//
//    }

    //    "set" - typesSet { implicit conn =>
    //
    //      NsSet.n.ints.insert(List(
    //        (1, Set(int1, int2)),
    //        (2, Set(int2, int3)),
    //        (2, Set(int3, int4)),
    //        (2, Set(int3, int4)),
    //      )).transact
    //
    //
    //      println("------ A")
    //      val rows = Peer.q(
    //        """[:find  ?a (distinct ?b)
    //          | :where [?a :NsSet/ints ?b]]""".stripMargin,
    //        conn.db
    //      )
    //      rows.forEach { r => println(r) }
    //
    //
    //      val sets = new java.util.HashSet[java.util.Set[Integer]](4)
    //      rows.forEach(row => sets.add(row.get(1).asInstanceOf[java.util.Set[Integer]]))
    //
    //
    //      println("------ B")
    ////        """[:find  ?n ?set ?c2 ?x
    //      Peer.q(
    //        """[:find  ?n (distinct ?set)
    //          | :in    $ [?set ...]
    //          | :where [?a :NsSet/n ?n]
    //          |        [?a :NsSet/ints ?b]
    //          |        [(datomic.api/q
    //          |          "[:find (distinct ?c1)
    //          |            :in $ ?a
    //          |            :where [?a :NsSet/ints ?c1]]" $ ?a) [[?c2]]]
    //          |         [(= ?c2 ?set)]
    //          | ]""".stripMargin,
    //        conn.db,
    //        sets
    //      ).forEach { r => println(r) }
    //
    //      println("------ B")
    ////        """[:find  ?n ?set ?c2 ?x
    //      Peer.q(
    //        """[:find  ?n (distinct ?c2)
    //          | :where [?a :NsSet/n ?n]
    //          |        [?a :NsSet/ints ?b]
    //          |        [(datomic.api/q
    //          |          "[:find (distinct ?c1)
    //          |            :in $ ?a1
    //          |            :where [?a1 :NsSet/ints ?c1]]" $ ?a) [[?c2]]]
    //          | ]""".stripMargin,
    //        conn.db
    //      ).forEach { r => println(r) }
    //
    //      NsSet.n.a1.ints(distinct).query.get ==> List(
    //        (1, Set(Set(int1, int2))),
    //        (2, Set(
    //          Set(int2, int3),
    //          Set(int3, int4), // 2 rows coalesced
    //        ))
    //      )
    //
    //
    //      //      val e1 = datomic.Peer.tempid(":db.part/user")
    //      //      val e2 = datomic.Peer.tempid(":db.part/user")
    //      //
    //      //      val txr = conn.asInstanceOf[DatomicConn_JVM].peerConn.transact(
    //      //        datomic.Util.list(
    //      //          datomic.Util.list(":db/add", e1, ":NsSet/n", 1),
    //      //          datomic.Util.list(":db/add", e1, ":NsSet/booleans", true.asInstanceOf[java.lang.Boolean]),
    //      //          datomic.Util.list(":db/add", e2, ":NsSet/n", 2),
    //      //          datomic.Util.list(":db/add", e2, ":NsSet/booleans", false.asInstanceOf[java.lang.Boolean])
    //      //        )
    //      //      )
    //      //      println(txr.get())
    //      //      val db = txr.get().get(datomic.Connection.DB_AFTER)
    //    }


    //    "one" - typesOne { implicit conn =>
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
  }
}
