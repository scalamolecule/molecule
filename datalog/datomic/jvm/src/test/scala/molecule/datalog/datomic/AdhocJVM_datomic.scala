package molecule.datalog.datomic

import java.net.URI
import java.time._
import java.util
import java.util.{Date, UUID}
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


        _ <- Ns.i_?.int.insert(
          (None, 0), // entity with missing i
          (Some(1), 1),
          (Some(2), 2),
        ).transact

        //        // Update all entities where `i` is not asserted (null)
        //        _ <- Ns.i_().int(3).update.transact
        //          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
        //            err ==> "Please add at least one tacit filter attribute (applying empty value not counting)."
        //          }

        // Add at least one tacit filter attribute apart from filter applying nothing
        //        _ <- Ns.i_().int_.int(3).update.transact
        _ <- Ns.i_().int(3).update.transact

        // 1 entity updated
        _ <- Ns.i_?.int.query.get.map(_ ==> List(
          (None, 3), // updated
          (Some(1), 1),
          (Some(2), 2),
        ))

        //        List(a, b) <- Ns.i.int.insert(
        //          (1, 1),
        //          (2, 2),
        //        ).transact.map(_.ids)
        //
        //        // Update entities with id a or b
        //        _ <- Ns(x, a, b).int(3).update.transact
        //
        //        // 2 entities updated
        //        _ <- Ns.i.a1.int_?.query.get.map(_ ==> List(
        //          (0, None), // updated
        //          (1, Some(3)), // updated
        //          (2, Some(3)),
        //        ))
        //
        //        // Nothing updated if no match
        //        _ <- Ns("42").int(5).update.transact
        //        _ <- Ns.id.a1.i.int.query.get.map(_ ==> List(
        //          (a, 1, 4),
        //          (b, 1, 4),
        //          (c, 2, 3),
        //        ))
        //
        //        // Nothing updated if no match
        //        _ <- Ns("42").int(5).update.transact
        //        _ <- Ns.id.a1.i.int.query.get.map(_ ==> List(
        //          (a, 1, 4),
        //          (b, 1, 4),
        //          (c, 2, 3),
        //        ))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- A.i(1).s("a").save.transact.map(_.id)
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.i(2).save.transact
        _ <- A.i(4).s("d").save.transact.map(_.id)
        _ <- A.i(5).s("e").B.i(5).save.transact.map(_.id)
        _ <- A.i(6).s("f").B.i(6).save.transact.map(_.id)

        _ <- A.i.s.a1.B.i.query.get.map(_ ==> List(
          (5, "e", 5),
          (6, "f", 6),
        ))

        _ <- A.i_.s("x").B.i(7).update.transact

        _ <- A.i.a1.s.B.i.query.get.map(_ ==> List(
          (5, "x", 7), // A and B values updated
          (6, "x", 7), // A and B values updated
        ))

        //        _ <- A.i_.B.i(8).upsert.transact
        _ <- A.i_.s("y").B.i(8).upsert.transact

        _ <- A.i.a1.s.B.i.query.get.map(_ ==> List(
          (1, "y", 8), // A value updated and ref to B and B value inserted
          (2, "y", 8), // A value inserted and B value inserted
          (3, "y", 8), // A value inserted and B value updated
          (4, "y", 8), // A and B values updated
          (5, "y", 8), // A and B values updated
          (6, "y", 8), // A and B values updated
        ))


        //        _ = {
        //          println("----------- 2")
        //          datomic.Peer.q(
        //            """[:find  ?b
        //              | :with  ?d3
        //              | :in    $ ?d5
        //              | :where [?a :A/i ?b]
        //              |        [?a :A/b ?c]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?d-pair)
        //              |            :in $ ?c
        //              |            :where [?c :B/iSeq ?d]
        //              |                   [?d :B.iSeq/i_ ?d-i]
        //              |                   [?d :B.iSeq/v_ ?d-v]
        //              |                   [(vector ?d-i ?d-v) ?d-pair]]" $ ?c) [[?d1]]]
        //              |        [(sort-by first ?d1) ?d2]
        //              |        [(map second ?d2) ?d3]
        //              |        [(set ?d3) ?d4]
        //              |        [(some ?d4 ?d5)]]
        //              |""".stripMargin, conn.db,
        //            Seq(2, 7).asJava
        //          ).forEach(r => println(r))
        //        }

        //        _ = {
        //          println("----------- 2")
        //          datomic.Peer.q(
        //            """[:find  (pull ?id0 [
        //              |          {(:A/bb :limit nil :default "__none__") [
        //              |            {(:B/c :limit nil :default "__none__") [
        //              |              (:C/iSet :limit nil :default "__none__")]}]}])
        //              | :where [?a :A/bb ?b]
        //              |        [(identity ?a) ?id0]]
        //              |""".stripMargin, conn.db,
        ////            Seq(1).asJava
        //          ).forEach(r => println(r))
        //        }


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