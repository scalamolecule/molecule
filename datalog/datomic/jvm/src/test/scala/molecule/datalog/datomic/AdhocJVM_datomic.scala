package molecule.datalog.datomic

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ExecutionError
import molecule.core.action.Query
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.{TestSuiteArray_datomic, TestSuite_datomic}
import utest._
import scala.language.implicitConversions

//object AdhocJVM_datomic extends TestSuiteArray_datomic with Array2List {
object AdhocJVM_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      //      val x: Query[Set[Int]] = Ns.intSeq.apply(sample).query
      //      val y: Query[Set[Int]] = Ns.intSeq.apply(sample(1)).query

      //      val a: Query[Set[Int]] = Ns.intSet.apply(sample).query
      //      val b: Query[Set[Int]] = Ns.intSet.apply(sample(1)).query

      for {

        //        _ = {
        //          println("----------- 2")
        //          datomic.Peer.q(
        //            """[:find  ?b ?c3
        //              | :where [?a :Ns/i ?b]
        //              |        [?a :Ns/intSeq _]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?c-pair)
        //              |            :in $ ?a
        //              |            :where [?a :Ns/intSeq ?c]
        //              |                   [?c :Ns.intSeq/i_ ?c-i]
        //              |                   [?c :Ns.intSeq/v_ ?c-v]
        //              |                   [(vector ?c-i ?c-v) ?c-pair]]" $ ?a) [[?c1]]]
        //              |        [(sort-by first ?c1) ?c2]
        //              |        [(map second ?c2) ?c3]]
        //              |""".stripMargin, conn.db,
        //            //            Seq(true, false).asJava
        //          ).forEach(r => println(r))
        //
        //          println("----------- 3")
        //          datomic.Peer.q(
        //            """[:find  ?b (distinct ?c3)
        //              | :where [?a :Ns/i ?b]
        //              |        [?a :Ns/intSeq _]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?c-pair)
        //              |            :in $ ?a
        //              |            :where [?a :Ns/intSeq ?c]
        //              |                   [?c :Ns.intSeq/i_ ?c-i]
        //              |                   [?c :Ns.intSeq/v_ ?c-v]
        //              |                   [(vector ?c-i ?c-v) ?c-pair]]" $ ?a) [[?c1]]]
        //              |        [(sort-by first ?c1) ?c2]
        //              |        [(map second ?c2) ?c3]]
        //              |""".stripMargin, conn.db,
        //            //            Seq(false).asJava
        //          ).forEach(r => println(r))
        //        }


        _ <- Ns.i.intSeq.insert(List(
          (1, List(int1, int2, int2)),
          (2, List(int2)),
          (2, List(int3, int4, int4)),
          (2, List(int3, int4, int4)),
        )).transact

        _ <- Ns.i(count).query.get.map(_ ==> List(4))
        _ <- Ns.i(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.intSeq(count).query.get.map(_ ==> List(10))
        _ <- Ns.intSeq(countDistinct).query.get.map(_ ==> List(4))

        // We can sort by counts

        _ <- Ns.i.intSeq(count).a1.query.i.get.map(_ ==> List(
          (1, 3),
          (2, 7),
        ))
        _ <- Ns.i.intSeq(count).d1.query.i.get.map(_ ==> List(
          (2, 7),
          (1, 3),
        ))

        _ <- Ns.i.intSeq(countDistinct).a1.query.get.map(_ ==> List(
          (1, 2),
          (2, 3),
        ))
        _ <- Ns.i.intSeq(countDistinct).d1.query.get.map(_ ==> List(
          (2, 3),
          (1, 2),
        ))

      } yield ()
    }
    "types2" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      for {


        _ <- Ns.i.intSet.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        _ = {
          println("----------- 2")
          datomic.Peer.q(
            """[:find  ?b
              |        (distinct ?c2)
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/intSet _]
              |        [(datomic.api/q
              |          "[:find (distinct ?c1)
              |            :in $ ?a1
              |            :where [?a1 :Ns/intSet ?c1]]" $ ?a) [[?c2]]]
              |        [?a :Ns/intSet ?c]]
              |""".stripMargin, conn.db,
            //            Seq(true, false).asJava
          ).forEach(r => println(r))

          println("----------- 3")
          datomic.Peer.q(
            """[:find  ?b
              |        ?c2
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/intSet _]
              |        [(datomic.api/q
              |          "[:find (distinct ?c1)
              |            :in $ ?a1
              |            :where [?a1 :Ns/intSet ?c1]]" $ ?a) [[?c2]]]
              |        [?a :Ns/intSet ?c]]
              |""".stripMargin, conn.db,
            //            Seq(false).asJava
          ).forEach(r => println(r))
        }
        // Non-aggregated card-many Set of attribute values coalesce
        _ <- Ns.i.a1.intSet.query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4)), // 3 rows coalesced
        ))

        // Use `distinct` keyword to retrieve unique Sets of Sets
        _ <- Ns.i.a1.intSet(distinct).query.i.get.map(_ ==> List(
          (1, Set(Set(int1, int2))),
          (2, Set(
            Set(int2),
            Set(int3, int4) // 2 rows coalesced
          ))
        ))

        _ <- Ns.intSet(distinct).query.get.map(_ ==> List(
          Set(
            Set(int1, int2),
            Set(int2),
            Set(int3, int4),
          )
        ))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      val all  = 1 + 2 + 2 + 3 + 4 + 3 + 4
      val all2 = 2 + 3 + 4 + 3 + 4
      for {


//        _ = {
//          println("----------- 1")
//          val res = datomic.Peer.q(
//            """[:find  ?a
//              | :in    $ [?c4 ...]
//              | :where [?a :A/i ?b]
//              |        [?a :A/iSeq ?c]
//              |        [(datomic.api/q
//              |          "[:find (distinct ?c-pair)
//              |            :in $ ?a
//              |            :where [?a :A/iSeq ?c]
//              |                   [?c :A.iSeq/i_ ?c-i]
//              |                   [?c :A.iSeq/v_ ?c-v]
//              |                   [(vector ?c-i ?c-v) ?c-pair]]" $ ?a) [[?c1]]]
//              |        [(sort-by first ?c1) ?c2]
//              |        [(map second ?c2) ?c3]
//              |        [(= ?c3 ?c4)]]
//              |""".stripMargin, conn.db,
//            //            Seq(Seq(1, 2, 2).asJava).asJava
//            Seq(1, 2, 2).asJava
//          )
//          res.forEach(r => println(r))
//
//          println("----------- 2")
//          datomic.Peer.q(
//            """[:find  ?b ?c3
//              | :in    $ ?c-blacklist
//              | :where [?a :A/i ?b]
//              |        [?a :A/iSeq ?c]
//              |        [(contains? ?c-blacklist ?a) ?c-blacklisted]
//              |        [(not ?c-blacklisted)]
//              |        [(datomic.api/q
//              |          "[:find (distinct ?c-pair)
//              |            :in $ ?a
//              |            :where [?a :A/iSeq ?c]
//              |                   [?c :A.iSeq/i_ ?c-i]
//              |                   [?c :A.iSeq/v_ ?c-v]
//              |                   [(vector ?c-i ?c-v) ?c-pair]]" $ ?a) [[?c1]]]
//              |        [(sort-by first ?c1) ?c2]
//              |        [(map second ?c2) ?c3]]
//              |""".stripMargin, conn.db,
//            res
//            //            Seq(false).asJava
//          ).forEach(r => println(r))
//        }


        _ <- A.i.B.iSet.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact

        _ <- A.B.iSet(stddev).query.get.map(_.head ==~ stdDevOf(1, 2, 2, 3, 4, 3, 4))

        _ <- A.i.B.iSet(stddev).a1.query.get.map(_ ==> List(
          (1,  stdDevOf(1, 2)),
          (2,  stdDevOf(2, 3, 4, 3, 4)),
        ))
        _ <- A.i.B.iSet(stddev).d1.query.get.map(_ ==> List(
          (2,  stdDevOf(2, 3, 4, 3, 4)),
          (1,  stdDevOf(1, 2)),
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


    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //
    ////        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
    //        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf")).save.transact.map(_.id)
    //
    ////        // We can remove a value from a Set as long as it's not the last value
    ////        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact
    //
    //        // Can't remove the last value of a mandatory attribute Set of values
    //        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ModelError(error) =>
    //              error ==>
    //                """Can't delete mandatory attributes (or remove last values of card-many attributes):
    //                  |  MandatoryAttr.hobbies
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