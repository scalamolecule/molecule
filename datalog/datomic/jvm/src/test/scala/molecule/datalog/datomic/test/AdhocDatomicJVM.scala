package molecule.datalog.datomic.test

import molecule.core.util.Executor._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object AdhocDatomicJVM extends DatomicTestSuite {


  override lazy val tests = Tests {

    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- A.i.Aa.*(A.i).insert((1, List(2, 3))).transact
    //
    //
    //        //                _ <- A.s.Bb.*(B.i_?.s).insert(
    //        //        //          ("a", List(Some(1))),
    //        //                  ("b", List((None, "x"))),
    //        //        //          ("c", Nil),
    //        //                ).inspect
    //        //
    //        //        _ <- A.s.Bb.*(B.i_?).insert(
    //        ////          ("a", List(Some(1))),
    //        //          ("b", List(None)),
    //        ////          ("c", Nil),
    //        //        ).inspect
    //        //
    //        //        _ <- A.s.Bb.*(B.i_?).insert(
    //        ////          ("a", List(Some(1))),
    //        //          ("b", List(None)),
    //        ////          ("c", Nil),
    //        //        ).transact
    //
    //        //        _ <- A.s.Bb.*(B.i).insert(
    //        //          ("a", List(1, 2)),
    //        //          ("b", List(3)),
    //        //        ).transact
    //        //
    //        //        _ <- A.s.Bb.*(B.i.a1).query.get.map(_ ==> List(
    //        //          ("a", List(1, 2)),
    //        //          ("b", List(3)),
    //        //        ))
    //
    //
    //        //        _ <- A.s.Bb.*(B.i_?).insert(
    //        //          ("a", List(Some(1))),
    //        //          ("b", List(None)),
    //        //          ("c", Nil),
    //        //        ).transact
    //        //
    //        //        _ <- A.s.a1.Bb.*?(B.i_?).query.get.map(_ ==> List(
    //        //          ("a", List(Some(1))),
    //        //          ("b", Nil),
    //        //          ("c", Nil),
    //        //        ))
    //        //        _ <- A.s.d1.Bb.*?(B.i_?).query.get.map(_ ==> List(
    //        //          ("c", Nil),
    //        //          ("b", Nil),
    //        //          ("a", List(Some(1))),
    //        //        ))
    //
    //
    //        //        _ <- A.s.Bb.*(B.i_?).insert(
    //        ////          ("a", List(Some(1))),
    //        //          ("b", List(None)),
    //        ////          ("c", Nil),
    //        //        ).transact
    //
    //        //        _ <- A.s.Bb.i_?.insert(
    //        //          ("b", None),
    //        //        ).transact
    //
    //        //        _ <- A.s.Bb.i_?.Cc.i_?.insert(
    //        //          ("b", None, None),
    //        //        ).transact
    //
    //        //        _ <- A.s.a1.Bb.*?(B.i_?).query.get.map(_ ==> List(
    //        //          ("a", List(Some(1))),
    //        //          ("b", Nil),
    //        //          ("c", Nil),
    //        //        ))
    //        //        _ <- A.s.d1.Bb.*?(B.i_?).query.get.map(_ ==> List(
    //        //          ("c", Nil),
    //        //          ("b", Nil),
    //        //          ("a", List(Some(1))),
    //        //        ))
    //
    //        //        List(
    //        //          (A,List((Some(11),Some(12),a)),1),
    //        //          (B,List((Some(13),None,b)),1),
    //        //          (C,List(),1),
    //        //          (D,List(),1),
    //        //          (E,List(),1))
    //
    //        //        _ <- A.s.Bb.*(B.i.s_?).insert(
    //        //          ("a", List((1, Some("x")), (2, Some("y")))),
    //        //          ("b", List((1, Some("x")), (2, None))),
    //        //          ("c", List((1, None), (2, Some("y")))),
    //        //          ("d", List((1, None), (2, None))),
    //        //          ("e", Nil),
    //        //        ).inspect
    //        //
    //        //        _ <- A.s.Bb.*(B.i.s_?).insert(
    //        //          ("a", List((1, Some("x")), (2, Some("y")))),
    //        //          ("b", List((1, Some("x")), (2, None))),
    //        //          ("c", List((1, None), (2, Some("y")))),
    //        //          ("d", List((1, None), (2, None))),
    //        //          ("e", Nil),
    //        //        ).transact
    //
    //        //        _ <- A.s.a1.Bb.*?(B.i.a1.s_?).query.get.map(_ ==> List(
    //        //          ("a", List((1, Some("x")), (2, Some("y")))),
    //        //          ("b", List((1, Some("x")), (2, None))),
    //        //          ("c", List((1, None), (2, Some("y")))),
    //        //          ("d", List((1, None), (2, None))),
    //        //          ("e", Nil),
    //        //        ))
    //        //        _ <- A.s.d1.Bb.*?(B.i.a1.s_?).query.get.map(_ ==> List(
    //        //          ("e", Nil),
    //        //          ("d", List((1, None), (2, None))),
    //        //          ("c", List((1, None), (2, Some("y")))),
    //        //          ("b", List((1, Some("x")), (2, None))),
    //        //          ("a", List((1, Some("x")), (2, Some("y")))),
    //        //        ))
    //
    //
    //      } yield ()
    //    }

    //    clojure.lang.Keyword
    //    clojure.lang.PersistentHashMap



    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        _ <- Ns.ints.insert(Seq.empty[Set[Int]]).transact
        _ <- Ns.ints.query.get.map(_ ==> List())

        _ <- Ns.ints(Seq.empty[Set[Int]]).save.transact
        _ <- Ns.ints.query.get.map(_ ==> List())

//        _ <- Ns.i(42).save.transact
//        _ <- Ns.i.query.get.map(_ ==> List(42))
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


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    ////        _ <- A.s.Bb.*(B.i).insert(
    ////          ("a", List(1, 2)),
    ////          ("b", Nil),
    ////        ).transact
    //
    //        // Mandatory nested data
    //        _ <- A.s.id.Bb.*(B.i.a1).query.inspect
    ////        _ <- A.id.s.Bb.*(B.i).query.get.map(_ ==> List(
    ////          ("a", List(1, 2)),
    ////        ))
    ////
    ////        // Optional nested data
    ////        _ <- A.s.Bb.*?(B.i.a1).query.get.map(_ ==> List(
    ////          ("a", List(1, 2)),
    ////          ("b", Nil),
    ////        ))
    //
    //        //        _ = {
    //        //          println("-------")
    //        //          Peer.q(
    //        //            """[:find  ?b ?d ?f
    //        //              | :in    $ %
    //        //              | :where [?a :A/i ?b]
    //        //              |        (rule?a ?a ?c)
    //        //              |        [?c :B/i ?d]
    //        //              |        (rule?c ?c ?e)
    //        //              |        [?e :A/i ?f]]""".stripMargin,
    //        //            conn.db,
    //        //            """[
    //        //              |  [(rule?a ?a ?c) [?a :A/b ?c]]
    //        //              |  [(rule?a ?c ?e) [?c :B/a ?e]]
    //        //              |
    //        //              |  [(rule?c ?a ?c) [?c :A/b ?a]]
    //        //              |  [(rule?c ?c ?e) [?e :B/a ?c]]
    //        //              |]
    //        //              |""".stripMargin
    //        //          ).forEach { r => println(r) }
    //        //        }
    //
    //        // Bidirectional
    //        //        _ <- A.i.a1.Self(bi).i.query.get.map(_ ==> List((1, 2), (2, 1)))
    //        //        _ <- A.i(1).Self(bi).i.query.get.map(_ ==> List((1, 2)))
    //        //        _ <- A.i(2).Self(bi).i.query.get.map(_ ==> List((2, 1)))
    //
    //      } yield ()
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

    //    "types" - types { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Types._
    //
    //      for {
    //        _ <- Ns.int.Tx(Other.s_("tx")).insert(1).transact.map(_.id)
    //
    //      } yield ()
    //    }

    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Unique._
    //      for {
    //        _ <- Unique.int.i.Tx(Other.i_(42).s_("tx")).insert(0, 1).inspect
    //        /*
    //        ========================================
    //        INSERT:
    //        AttrOneManInt("Unique", "int", V, Seq(), None, Nil, Nil, None, None)
    //        AttrOneManInt("Unique", "i", V, Seq(), None, Nil, Nil, None, None)
    //        TxMetaData(List(
    //          AttrOneTacInt("Other", "i", Appl, Seq(42), None, Nil, Nil, None, None),
    //          AttrOneTacString("Other", "s", Appl, Seq("tx"), None, Nil, Nil, None, None)))
    //
    //        [:db/add, #db/id[db.part/user -1], :Unique/int, 0]
    //        [:db/add, #db/id[db.part/user -1], :Unique/i, 1]
    //        [:db/add, datomic.tx, :Other/i, 42]
    //        [:db/add, datomic.tx, :Other/s, tx]
    //         */
    //        _ <- Unique.int.i.Tx(Other.i_(42).s_("tx")).insert(0, 1).transact
    //      } yield ()
    //    }
  }
}
