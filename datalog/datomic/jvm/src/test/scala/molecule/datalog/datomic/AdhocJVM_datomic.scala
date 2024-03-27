package molecule.datalog.datomic

import java.net.URI
import java.time._
import java.util
import java.util.{Date, UUID}
import molecule.base.error.ExecutionError
import molecule.core.action.Query
import molecule.core.spi.TxReport
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.{TestSuiteArray_datomic, TestSuite_datomic}
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

//object AdhocJVM_datomic extends TestSuiteArray_datomic with Array2List {
object AdhocJVM_datomic extends TestSuite_datomic {


  val (a1, b2) = ("a" -> int1, "b" -> int2)
  val (b3, c4) = ("b" -> int3, "c" -> int4)

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val aFalse = "a" -> boolean1
      val bFalse = "b" -> boolean1
      val cTrue  = "c" -> boolean2
      for {
        _ <- Ns.i.booleanMap.insert(List(
          (1, Map(aFalse)),
          (2, Map(bFalse, cTrue)),
        )).transact

        // OBS: Not that since only 2 Boolean values exist, the value tests are layed out differently to test

        // "Map contains this OR that value"
        _ <- Ns.i.a1.booleanMap_.hasNo(boolean1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.booleanMap_.hasNo(boolean2).query.get.map(_ ==> List(1))
        _ <- Ns.i.a1.booleanMap_.hasNo(boolean1, boolean2).query.get.map(_ ==> List())
        // Same as
        _ <- Ns.i.a1.booleanMap_.hasNo(List(boolean1)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.booleanMap_.hasNo(List(boolean2)).query.get.map(_ ==> List(1))
        _ <- Ns.i.a1.booleanMap_.hasNo(List(boolean1, boolean2)).query.get.map(_ ==> List())

        // Empty Seq of values matches nothing
        _ <- Ns.i.a1.booleanMap_.hasNo(List.empty[Boolean]).query.get.map(_ ==> List(1, 2))

        // Combine with retrieval
        _ <- Ns.i.a1.booleanMap.booleanMap_.hasNo(boolean1).query.get.map(_ ==> List())
        _ <- Ns.i.a1.booleanMap.booleanMap_.hasNo(boolean2).query.get.map(_ ==> List((1, Map(aFalse))))
        _ <- Ns.i.a1.booleanMap.booleanMap_.hasNo(boolean1, boolean2).query.get.map(_ ==> List())


        _ = {
          println("----------- 1")
          val res = datomic.Peer.q(
            """[:find  ?b
              | :in    $ ?c1
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/booleanMap _]
              |        [(datomic.api/q
              |          "[:find (distinct ?c)
              |            :in $ ?a [?c1 ...]
              |            :where [?a :Ns/booleanMap ?c]
              |                   [?c :Ns.booleanMap/v_ ?c1]]" $ ?a ?c1) [[?c2]]]]
              |""".stripMargin, conn.db,
            //            Seq(true, false).asJava
            //            Seq(Set(List("a", 1).asJava, List("b", 2).asJava).asJava).asJava
            //            Set(List("a", 1).asJava, List("b", 2).asJava).asJava
            //            Set(x, y).asJava
            //            Set("c").asJava
            Seq(true).asJava
            //            "a"
          )
          res.forEach(r => println(r))
        }


      } yield ()
    }


    "types2" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val a = (1, List(int1, int2))
      val b = (2, List(int2, int3, int3))
      for {
        _ <- Ns.i.intSeq.insert(List(a, b)).transact


        // Applying nothing matches nothing
        _ <- Ns.i.a1.intSeq().query.i.get.map(_ ==> List())



        //        _ = {
        //          println("----------- 2")
        //          datomic.Peer.q(
        //            """[:find  ?b
        //              | :where [?a :Ns/i ?b]
        //              |        [?a :Ns/intSeq]
        //              |        ]
        //              |""".stripMargin, conn.db,
        //            //            Seq(true, false).asJava
        //          ).forEach(r => println(r))
        //
        //          println("----------- 3")
        //          datomic.Peer.q(
        //            """[:find  ?c ?e3
        //              | :where [?b :Ns/i ?c]
        //              |        [?b :Ns/iSeq ?d]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?d1)
        //              |            :in $ ?b1
        //              |            :where [?b1 :Ns/iSeq ?d1]]" $ ?b) [[?d2]]]
        //              |        [?b :Ns/intSeq _]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?e-pair)
        //              |            :in $ ?b
        //              |            :where [?b :Ns/intSeq ?e]
        //              |                   [?e :Ns.intSeq/i_ ?e-i]
        //              |                   [?e :Ns.intSeq/v_ ?e-v]
        //              |                   [(vector ?e-i ?e-v) ?e-pair]]" $ ?b) [[?e1]]]
        //              |        [(sort-by first ?e1) ?e2]
        //              |        [(map second ?e2) ?e3]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?e1)
        //              |            :in $ ?b1
        //              |            :where [?b1 :Ns/intSeq ?e1]]" $ ?b) [[?e2]]]
        //              |        [(= ?d2 ?e2)]]
        //              |""".stripMargin, conn.db,
        //            //            Seq(false).asJava
        //          ).forEach(r => println(r))
        //        }

        //        _ <- Ns.i.a1.intSeq_.hasNo(List.empty[Int]).query.i.get.map(_ ==> List())


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      val a = (1, List(1, 2), List(1, 2, 3), 3)
      val b = (2, List(2, 3), List(2, 3), 3)
      val c = (2, List(4), List(4), 4)

      val d = (2, List(4), List(3), 4)

      for {


        List(_, a2, a3) <- A.i.iSeq.B.iSeq.i.insert(a, b, c).transact.map(_.ids)

        _ = {
          println("----------- 1")
          val res = datomic.Peer.q(
            """[:find  ?c ?f3
              | :where [?b :A/i ?c]
              |        [?b :A/iSeq _]
              |        [(datomic.api/q
              |          "[:find (distinct ?d-pair)
              |            :in $ ?b
              |            :where [?b :A/iSeq ?d]
              |                   [?d :A.iSeq/i_ ?d-i]
              |                   [?d :A.iSeq/v_ ?d-v]
              |                   [(vector ?d-i ?d-v) ?d-pair]]" $ ?b) [[?d1]]]
              |        [(sort-by first ?d1) ?d2]
              |        [(map second ?d2) ?d3]
              |        [?b :A/b ?e]
              |        [?e :B/iSeq _]
              |        [(datomic.api/q
              |          "[:find (distinct ?f-pair)
              |            :in $ ?e
              |            :where [?e :B/iSeq ?f]
              |                   [?f :B.iSeq/i_ ?f-i]
              |                   [?f :B.iSeq/v_ ?f-v]
              |                   [(vector ?f-i ?f-v) ?f-pair]]" $ ?e) [[?f1]]]
              |        [(sort-by first ?f1) ?f2]
              |        [(map second ?f2) ?f3]
              |        [(= ?d3 ?f3)]]
              |""".stripMargin, conn.db,
            //            Seq(Seq(1, 2, 2).asJava).asJava
            //            Seq(1, 2, 2).asJava
          )
          res.forEach(r => println(r))

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
        }



        _ <- A.i.iSeq_(B.iSeq_).B.iSeq.query.i.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3)), // (Lists are note coalesced as Sets are)
          (2, List(4))
        ))
        _ <- A.i.iSeq_.B.iSeq(A.iSeq_).query.get.map(_.sortBy(_._2.head) ==> List(
          (2, List(2, 3)),
          (2, List(4))
        ))

        _ <- A.id.a1.i.iSeq_(B.iSeq_).B.iSeq.query.get.map(_ ==> List(
          (a2, 2, List(2, 3)),
          (a3, 2, List(4))
        ))
        _ <- A.id.a1.i.iSeq_.B.iSeq(A.iSeq_).query.get.map(_ ==> List(
          (a2, 2, List(2, 3)),
          (a3, 2, List(4))
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