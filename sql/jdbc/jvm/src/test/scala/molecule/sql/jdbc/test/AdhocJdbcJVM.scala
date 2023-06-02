package molecule.sql.jdbc.test

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs._
//import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.language.implicitConversions


object AdhocJdbcJVM extends JdbcTestSuite {

  override lazy val tests = Tests {

    "refs" - refs { implicit conn =>
      for {

        _ <- A.s.Bb.*(B.i).insert(
          ("a", List(1, 2)),
          ("b", List(3)),
        ).transact

//        _ <- A.s.query.get.map(_ ==> List("a", "b"))
//        _ <- B.i.query.get.map(_ ==> List(1, 2, 3))
        _ <- A.s.Bb.i.query.get.map(_ ==> List(
          ("a", 1),
          ("a", 2),
          ("b", 3),
        ))

        _ <- A.s.Bb.*(B.i).query.get.map(_ ==> List(
          ("a", List(1, 2)),
          ("b", List(3)),
        ))

//        _ <- A.i.Bb.*(B.i.C.i.Dd.*(D.i.E.i.F.i._E._D.E1.s.F.i)).insert(2, List((1, 2, List((3, 4, 5, "a", 55))))).transact


        //                _ <- A.s.Bb.*(B.i.Cc.*(C.i)).insert(
        //                  ("a", List(
        //                    (10, List(1, 2)),
        //                    (20, List(3)),
        //                  )),
        //                  ("b", List(
        //                    (30, List(4, 5))
        //                  )),
        //                ).transact
        //                //        _ <- A.s.query.get.map(_ ==> List("a", "b"))
        //                //        _ <- B.i.query.get.map(_ ==> List(10, 20, 30))
        //                //        _ <- C.i.query.get.map(_ ==> List(1, 2, 3, 4, 5))
        //                _ <- A.s.Bb.i.query.get.map(_ ==> List(
        //                  ("a", 10),
        //                  ("a", 20),
        //                  ("b", 30),
        //                ))
        //
        //                _ <- A.s.i.Bb.*(B.i.s.Cc.*(C.i)).insert(
        //                  ("a", 100, List(
        //                    (10, "a1", List(1, 2)),
        //                    (20, "a2", List(3)),
        //                  )),
        //                  ("b", 300, List(
        //                    (30, "b1", List(4, 5))
        //                  )),
        //                ).transact


        //        _ <- A.i.B.s.insert((1, "a"), (2, "b")).transact
        //        _ <- A.i.B.s.query.get.map(_ ==> List((1, "a"), (2, "b")))
        //
        //        _ <- A.i.Bb.s.insert((1, "a"), (2, "b")).transact
        //        _ <- A.i.Bb.s.query.get.map(_ ==> List((1, "a"), (2, "b")))
        //
        //        _ <- A.i.Bb.s.C.i.insert((1, "a", 3), (2, "b", 4)).transact
        //        _ <- A.i.Bb.s.C.i.query.get.map(_ ==> List((1, "a", 3), (2, "b", 4)))
        //
        //
        //        _ <- A.i.s.Bb.s.insert(
        //          (1, "x", "a"),
        //          (2, "y", "b"),
        //        ).transact
        //        _ <- A.i.s.Bb.s.query.get.map(_ ==> List(
        //          (1, "x", "a"),
        //          (2, "y", "b"),
        //        ))
        //
        //        _ <- A.B.i.insert(1).transact
        //        _ <- A.B.i.query.get.map(_ ==> List(1))
        //
        //        _ <- A.i.B.i.insert(1, 2).transact
        //        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))
        //
        //
        //        _ <- A.B.C.i.insert(1).transact
        //        _ <- A.B.C.i.query.get.map(_ ==> List(1))
        //
        //        _ <- A.B.i.C.i.insert(1, 2).transact
        //        _ <- A.B.i.C.i.query.get.map(_ ==> List((1, 2)))
        //
        //        _ <- A.i.B.C.i.insert(1, 2).transact
        //        _ <- A.i.B.C.i.query.get.map(_ ==> List((1, 2)))
        //
        //        _ <- A.i.B.i.C.i.insert(1, 2, 3).transact
        //        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


      } yield ()
    }

    //    "types" - types { implicit conn =>
    //      for {
    //        //        _ <- Ns.i(3).save.transact
    //        _ <- Ns.i.insert(3).transact
    //        _ <- Ns.i.query.get.map(_ ==> List(3))
    //      } yield ()
    //    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        _ <- Require.int1.errorMsg.insert(
    //          (1, 2),
    //          (2, 2),
    //          (3, 2),
    //        ).transact
    //
    //        _ <- Variables.int1.errorMsg.query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.get.map(_ ==> List())
    //
    //
    //      } yield ()
    //    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //
    //        _ <- Ns.i(1).R1.i(2).Ns1.i(3).save.transact
    //
    //        // Directional
    //        _ <- Ns.i.R1.i.Ns1.i.query.get.map(_ ==> List((1, 2, 3)))
    //        _ <- Ns.i(1).R1.i.Ns1.i.query.get.map(_ ==> List((1, 2, 3)))
    //        _ <- Ns.i(3).R1.i.Ns1.i.query.get.map(_ ==> List())
    //
    //
    //
    //        // Bidirectional
    ////        _ <- Ns.i.a1.Self(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
    ////        _ <- Ns.i(1).Self(bi).i.query.get.map(_ ==> List((1, 2)))
    ////        _ <- Ns.i(2).Self(bi).i.query.get.map(_ ==> List((2, 1)))
    //
    //      } yield ()
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
  }
}
