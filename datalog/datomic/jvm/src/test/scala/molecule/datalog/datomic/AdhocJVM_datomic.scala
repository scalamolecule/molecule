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

object AdhocJVM_datomic extends TestSuiteArray_datomic {
  //object AdhocJVM_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

//        id <- Ns.i(42).save.transact.map(_.id)
        // Map attribute not yet asserted
//        _ <- Ns.dateMap.query.get.map(_ ==> Nil)

        // Applying Map of pairs to non-asserted Map attribute adds the attribute with the update
        _ <- Ns("x").dateMap.apply(Map(pdate1, pdate2)).update.transact
//        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate1, pdate2))
//
//        // Applying Map of pairs replaces previous Map
//        _ <- Ns(id).dateMap(Map(pdate2, pdate3)).update.transact
//        _ <- Ns.dateMap.query.get.map(_.head ==> Map(pdate2, pdate3))
//
//        // Add other attribute and update Map attribute in one go
//        _ <- Ns(id).s("foo").dateMap(Map(pdate3, pdate4)).update.transact
//        _ <- Ns.i.s.dateMap.query.get.map(_.head ==> (42, "foo", Map(pdate3, pdate4)))
//
//        // Applying empty Map of pairs deletes map
//        _ <- Ns(id).dateMap(Map.empty[String, Date]).update.transact
//        _ <- Ns.dateMap.query.get.map(_ ==> Nil)
//
//        _ <- Ns(id).dateMap(Map(pdate1, pdate2)).update.transact
//        // Apply nothing to delete attribute
//        _ <- Ns(id).dateMap().update.transact
//        _ <- Ns.dateMap.query.get.map(_ ==> Nil)
//
//        // Entity still has other attributes
//        _ <- Ns.i.s.query.get.map(_.head ==> (42, "foo"))


        //        _ <- rawTransact(
        //          """[
        //            |  [:db/add 17592186045418 :Ns/doubleMap #db/id[ -1]]
        //            |  [:db/add #db/id[ -1] :Ns.doubleMap/k_ a]
        //            |  [:db/add #db/id[ -1] :Ns.doubleMap/v_ 1.1]
        //            |]
        //            |""".stripMargin)


        //        _ = {
        //          println("----------- 1")
        //          val res = datomic.Peer.q(
        //            """[:find  ?b
        //              | :in    $ ?c1
        //              | :where [?a :Ns/i ?b]
        //              |        [?a :Ns/booleanMap _]
        //              |        [(datomic.api/q
        //              |          "[:find (distinct ?c)
        //              |            :in $ ?a [?c1 ...]
        //              |            :where [?a :Ns/booleanMap ?c]
        //              |                   [?c :Ns.booleanMap/v_ ?c1]]" $ ?a ?c1) [[?c2]]]]
        //              |""".stripMargin, conn.db,
        //            //            Seq(true, false).asJava
        //            //            Seq(Set(List("a", 1).asJava, List("b", 2).asJava).asJava).asJava
        //            //            Set(List("a", 1).asJava, List("b", 2).asJava).asJava
        //            //            Set(x, y).asJava
        //            //            Set("c").asJava
        //            Seq(true).asJava
        //            //            "a"
        //          )
        //          res.forEach(r => println(r))
        //        }


      } yield ()
    }




    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {



        id <- A.iMap(Map(pint1)).Bb.iMap(Map(pint2))._A.Cc.iMap(Map(pint3)).save.transact.map(_.id)
        _ <- A.iMap.Bb.iMap._A.Cc.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint2), Map(pint3))))

        // Updating A.Bb.iMap and A.Cc.iMap
        _ <- A(id).iMap(Map(pint1)).Bb.iMap(Map(pint1))._A.Cc.iMap(Map(pint1)).update.transact
        _ <- A.iMap.Bb.iMap._A.Cc.iMap.query.get.map(_ ==> List((Map(pint1), Map(pint1), Map(pint1))))

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