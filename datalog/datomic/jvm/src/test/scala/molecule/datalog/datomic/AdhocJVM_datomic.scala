package molecule.datalog.datomic

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.language.implicitConversions

object AdhocJVM_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i(42).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(42))

        _ <- Ns.i.booleans.insert(List(
          (1, Set(true)),
          (2, Set(false)),
          (2, Set(true, false))
        )).transact

        _ <- Ns.i.booleans(count).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregate functions not implemented for Sets of boolean values."
          }

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.s.Bb.*(B.i_?.s_?).insert(
          //          ("0", List((Some(1), Some("x")), (Some(2), Some("y")))),
          //
          //          ("1a", List((None, Some("x")), (Some(2), Some("y")))),
          //          ("1b", List((Some(1), None), (Some(2), Some("y")))),
          ("1c", List((Some(1), Some("x")), (None, Some("y")))),
          //          ("1d", List((Some(1), Some("x")), (Some(2), None))),
          //
          //          ("2a", List((None, None), (Some(2), Some("y")))),
          //          ("2b", List((None, Some("x")), (None, Some("y")))),
          //          ("2c", List((None, Some("x")), (Some(2), None))),
          //          ("2d", List((Some(1), None), (None, Some("y")))),
          //          ("2e", List((Some(1), None), (Some(2), None))),
          //          ("2f", List((Some(1), Some("x")), (None, None))),
          //
          //          ("3a", List((None, None), (None, Some("y")))),
          //          ("3b", List((None, None), (Some(2), None))),
          //          ("3c", List((None, Some("x")), (None, None))),
          //          ("3d", List((Some(1), None), (None, None))),
          //
          //          ("4", List((None, None), (None, None))),
          //
          //          ("a", Nil),
        ).i.transact

        _ <- A.s.a1.Bb.*?(B.i_?.a1.s_?.a2).query.i.get.map(_ ==> List(
          //          ("0", List((Some(1), Some("x")), (Some(2), Some("y")))),
          //
          //          ("1a", List((None, Some("x")), (Some(2), Some("y")))),
          //          ("1b", List((Some(1), None), (Some(2), Some("y")))),
          ("1c", List((None, Some("y")), (Some(1), Some("x")))), // None sorted first
          //          ("1d", List((Some(1), Some("x")), (Some(2), None))),
          //
          //          ("2a", List((Some(2), Some("y")))), // (None, None) not included
          //          ("2b", List((None, Some("x")), (None, Some("y")))),
          //          ("2c", List((None, Some("x")), (Some(2), None))),
          //          ("2d", List((None, Some("y")), (Some(1), None))), // None sorted first
          //          ("2e", List((Some(1), None), (Some(2), None))),
          //          ("2f", List((Some(1), Some("x")))), // (None, None) not included
          //
          //          ("3a", List((None, Some("y")))),
          //          ("3b", List((Some(2), None))),
          //          ("3c", List((None, Some("x")))),
          //          ("3d", List((Some(1), None))),
          //
          //          ("4", Nil), // List((None, None), (None, None)) collapsing to Nil
          //
          //          ("a", Nil),
        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.int.i.s.insert(0, 1, "a").transact
    //        _ <- Uniques.i.s.query.get.map(_ ==> List((1, "a")))
    //
    //
    //      } yield ()
    //    }


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)
    //
    //        // Mandatory refs can be removed as long as some ref ids remain
    //        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact
    //
    //        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
    //        _ <- MandatoryRefsB(id).refsB.remove(r1).update.i.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryRefsB.refsB
    //                  |""".stripMargin
    //          }
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

  }
}