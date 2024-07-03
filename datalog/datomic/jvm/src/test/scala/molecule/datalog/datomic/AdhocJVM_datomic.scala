package molecule.datalog.datomic

import java.net.URI
import java.time._
import java.util
import java.util.{Date, UUID}
import datomic.Peer
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.action.Query
import molecule.core.spi.TxReport
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.{TestSuiteArray_datomic, TestSuite_datomic}
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

//object AdhocJVM_datomic extends TestSuiteArray_datomic {
object AdhocJVM_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.string.int.Ref.string.query.inspect



      } yield ()
    }


        "refs" - refs { implicit conn =>
          import molecule.coreTests.dataModels.core.dsl.Refs._
          implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

          val x: B_1[Int, Int] = A.i_.B.iMap.remove(string3, string4)

          for {

            _ <- A.i.Bb.*?(B.s_?.i_?).insert(
              (1, List()),
              (2, List((Some("a"), None))),
              (3, List((Some("b"), None), (Some("c"), None))),
              (4, List((Some("d"), Some(1)))),
              (5, List((Some("e"), Some(2)), (Some("f"), Some(3)))),
              (6, List((Some("g"), Some(4)), (Some("h"), None))),
            ).i.transact

            // Filter by A ids, update B values
            _ <- A.i_.Bb.i(4).update.i.transact

            _ = {
              Peer.q(
                """[:find  ?b
                  |        (pull ?id0 [
                  |          {(:A/bb :limit nil :default "__none__") [
                  |            (:B/s :limit nil :default "__none__")
                  |            (:B/i :limit nil :default "__none__")]}])
                  | :where [?a :A/i ?b]
                  |        [(identity ?a) ?id0]]
                  |        """.stripMargin,
                conn.db
              ).forEach { r => println(r) }
            }



            _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.i.get.map(_ ==> List(
              (1, List()), //                               no B.i value
              (2, List()), //                               no B.i value
              (3, List()), //                               no B.i value
              (4, List((Some("d"), 4))), //                 update in 1 ref entity
              (5, List((Some("e"), 4), (Some("f"), 4))), // update in 2 ref entities
              (6, List((Some("g"), 4))), //                 already had same value
            ))

            // Filter by A ids, upsert B values
            _ <- A.i_.Bb.i(5).upsert.transact

            _ <- A.i.a1.Bb.*?(B.s_?.a1.i).query.get.map(_ ==> List(
              (1, List((None, 5))), //                      ref + insert
              (2, List((Some("a"), 5))), //                 addition in 1 ref entity
              (3, List((Some("b"), 5), (Some("c"), 5))), // addition in 2 ref entities
              (4, List((Some("d"), 5))), //                 update in 1 ref entity
              (5, List((Some("e"), 5), (Some("f"), 5))), // update in 2 ref entities
              (6, List((Some("g"), 5), (Some("h"), 5))), // update in one ref entity and insertion in another
            ))


          } yield ()
        }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }
    //
    //
    //        "validation" - validation { implicit conn =>
    //          import molecule.coreTests.dataModels.core.dsl.Validation._
    //          for {
    //
    //            id <- MandatoryRefB.i(1).RefB.i(2).save.transact.map(_.id)
    //
    //            _ <- MandatoryRefB(id).refB().update.i.transact
    //              .map(_ ==> "Unexpected success").recover {
    //                case ModelError(error) =>
    //                  error ==>
    //                    """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                      |  MandatoryRefB.refB
    //                      |""".stripMargin
    //              }
    //
    //          } yield ()
    //        }
    //
    //
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
    //    _ = {
    //      println("-------")
    //      Peer.q(
    //        """[:find  ?b
    //          |        ;;?c5
    //          |        (distinct ?c4)
    //          | :where [?a :A/i ?b]
    //          |        [(datomic.api/q
    //          |          "[:find  (pull ?a-?c [[:A/iSet :limit nil]])
    //          |            :in $ ?a1
    //          |            :where [?a1 :A/i ?b1]
    //          |                   [(identity ?a1) ?a-?c]]" $ ?a ) [[?c2]]]
    //          |        [(if (nil? ?c2) {:A/iSet []} ?c2) ?c3]
    //          |        [(:A/iSet ?c3) ?c4]
    //          |        ]""".stripMargin,
    //        conn.db
    //      ).forEach { r => println(r) }
    //    }
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
    //      NsSet.n.intSet.insert(List(
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
    //          | :where [?a :NsSet/intSet ?b]]""".stripMargin,
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
    //          |        [?a :NsSet/intSet ?b]
    //          |        [(datomic.api/q
    //          |          "[:find (distinct ?c1)
    //          |            :in $ ?a
    //          |            :where [?a :NsSet/intSet ?c1]]" $ ?a) [[?c2]]]
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
    //          |        [?a :NsSet/intSet ?b]
    //          |        [(datomic.api/q
    //          |          "[:find (distinct ?c1)
    //          |            :in $ ?a1
    //          |            :where [?a1 :NsSet/intSet ?c1]]" $ ?a) [[?c2]]]
    //          | ]""".stripMargin,
    //        conn.db
    //      ).forEach { r => println(r) }
    //
    //      NsSet.n.a1.intSet(distinct).query.get ==> List(
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
    //      //          datomic.Util.list(":db/add", e1, ":NsSet/booleanSet", true.asInstanceOf[java.lang.Boolean]),
    //      //          datomic.Util.list(":db/add", e2, ":NsSet/n", 2),
    //      //          datomic.Util.list(":db/add", e2, ":NsSet/booleanSet", false.asInstanceOf[java.lang.Boolean])
    //      //        )
    //      //      )
    //      //      println(txr.get())
    //      //      val db = txr.get().get(datomic.Connection.DB_AFTER)
    //    }

  }
}