package molecule.sql.core.test

import datomic.Peer
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.core.async._
import molecule.sql.core.setup.SqlTestSuite
import utest._
import scala.language.implicitConversions


object AdhocJVM extends SqlTestSuite {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
        _ <- Ns.int.insert.apply(1).transact
//        _ <- Ns.int.query.get.map(_ ==> List(1))



        _ <- Ns.int.Ref.i.query.inspect

//        _ <- Ns.int.Ref(bi).i.query.inspect
//        _ <- Ns.int.Ref(bi).i.query.get.map(_ ==> List(int1))

//        _ <- Ns.int.RefBi.i.query.get.map(_ ==> List(int1))


      } yield ()
    }


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


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- Ns.i(1).R1.i(2).Ns1.i(3).save.transact

        // Directional
        _ <- Ns.i.R1.i.Ns1.i.query.get.map(_ ==> List((1, 2, 3)))
        _ <- Ns.i(1).R1.i.Ns1.i.query.get.map(_ ==> List((1, 2, 3)))
        _ <- Ns.i(3).R1.i.Ns1.i.query.get.map(_ ==> List())



        // Bidirectional
//        _ <- Ns.i.a1.Self(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
//        _ <- Ns.i(1).Self(bi).i.query.get.map(_ ==> List((1, 2)))
//        _ <- Ns.i(2).Self(bi).i.query.get.map(_ ==> List((2, 1)))

      } yield ()

    }

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
