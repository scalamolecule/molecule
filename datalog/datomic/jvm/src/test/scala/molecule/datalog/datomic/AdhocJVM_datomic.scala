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

        _ <- Ns("x").dateMap.apply(Map(pdate1, pdate2)).update.transact


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

    
    "id-filter - ref - value" - refs { implicit conn =>
      for {
        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.iMap.add(pint4, pint5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List()), //                                   no B value to update
          (2, List()), //                                   no B value to update
          (3, List()), //                                   no B value to update
          (4, List( //                                      update in 1 ref entity
            (Some("d"), Map(pint1, pint2, pint4, pint5)))),
          (5, List( //                                      update in 2 ref entities
            (Some("e"), Map(pint2, pint3, pint4, pint5)),
            (Some("f"), Map(pint3, pint4, pint4, pint5)))),
          (6, List( //                                      update one ref entity
            (Some("g"), Map(pint4, pint5, pint4, pint5)))),
        ))

        // Filter by A ids, upsert B values
        _ <- A(a, b, c, d, e, f).Bb.iMap.add(pint5, pint6).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List( //                                                    ref + insertion
            (None, Map(pint5, pint6)))),
          (2, List( //                                                    insertion in 1 ref entity
            (Some("a"), Map(pint5, pint6)))),
          (3, List( //                                                    insertion in 2 ref entities
            (Some("b"), Map(pint5, pint6)),
            (Some("c"), Map(pint5, pint6)))),
          (4, List( //                                                    update in 1 ref entity
            (Some("d"), Map(pint1, pint2, pint4, pint5, pint5, pint6)))),
          (5, List( //                                                    update in 2 ref entities
            (Some("e"), Map(pint2, pint3, pint4, pint5, pint5, pint6)),
            (Some("f"), Map(pint3, pint4, pint4, pint5, pint5, pint6)))),
          (6, List( //                                                    update in one ref entity and insertion in another
            (Some("g"), Map(pint4, pint5, pint4, pint5, pint5, pint6)),
            (Some("h"), Map(pint5, pint6)))),
        ))
      } yield ()
    }


    "filter - ref - value" - refs { implicit conn =>
      for {
        _ <- A.i.a1.Bb.*?(B.s_?.iMap_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(Map(pint1, pint2))))),
          (5, List((Some("e"), Some(Map(pint2, pint3))), (Some("f"), Some(Map(pint3, pint4))))),
          (6, List((Some("g"), Some(Map(pint4, pint5))), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iMap.add(pint4, pint5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List()), //                                   no B value to update
          (2, List()), //                                   no B value to update
          (3, List()), //                                   no B value to update
          (4, List( //                                      update in 1 ref entity
            (Some("d"), Map(pint1, pint2, pint4, pint5)))),
          (5, List( //                                      update in 2 ref entities
            (Some("e"), Map(pint2, pint3, pint4, pint5)),
            (Some("f"), Map(pint3, pint4, pint4, pint5)))),
          (6, List( //                                      update one ref entity
            (Some("g"), Map(pint4, pint5, pint4, pint5)))),
        ))

        // Filter by A ids, upsert B values
        _ <- A.i_.Bb.iMap.add(pint5, pint6).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.iMap).query.get.map(_ ==> List(
          (1, List( //                                                    ref + insertion
            (None, Map(pint5, pint6)))),
          (2, List( //                                                    insertion in 1 ref entity
            (Some("a"), Map(pint5, pint6)))),
          (3, List( //                                                    insertion in 2 ref entities
            (Some("b"), Map(pint5, pint6)),
            (Some("c"), Map(pint5, pint6)))),
          (4, List( //                                                    update in 1 ref entity
            (Some("d"), Map(pint1, pint2, pint4, pint5, pint5, pint6)))),
          (5, List( //                                                    update in 2 ref entities
            (Some("e"), Map(pint2, pint3, pint4, pint5, pint5, pint6)),
            (Some("f"), Map(pint3, pint4, pint4, pint5, pint5, pint6)))),
          (6, List( //                                                    update in one ref entity and insertion in another
            (Some("g"), Map(pint4, pint5, pint4, pint5, pint5, pint6)),
            (Some("h"), Map(pint5, pint6)))),
        ))
      } yield ()
    }


    "value - ref - filter" - refs { implicit conn =>
      for {
        _ <- A.iMap_?.Bb.*?(B.s).insert(
          (Some(Map(pint0, pint1)), List()),
          (None, List("a")),
          (Some(Map(pint1, pint2)), List("b", "c")),
          (Some(Map(pint2, pint3)), List("d", "e")),
        ).transact

        // Filter by B attribute, update A values
        _ <- A.iMap.add(pint3, pint4).Bb.s_.update.transact

        _ <- A.iMap.Bb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Map(pint0, pint1), List()), //                       nothing updated since this A entity has no ref to B
          // (<none>, List("a")), //                            no A attribute to update
          (Map(pint1, pint2, pint3, pint4), List("b", "c")), // A attribute updated
          (Map(pint2, pint3, pint3, pint4), List("d", "e")), // A attribute updated
        ))

        // Filter by B attribute, update A values
        _ <- A.iMap.add(pint4, pint5).Bb.s_.upsert.transact

        _ <- A.iMap.Bb.*?(B.s).query.get.map(_.sortBy(_._2.headOption.toString) ==> List(
          (Map(pint0, pint1), List()), //                                     nothing updated since this A entity has no ref to B
          (Map(pint4, pint5), List("a")), //                                  A attribute inserted
          (Map(pint1, pint2, pint3, pint4, pint4, pint5), List("b", "c")), // A attribute updated
          (Map(pint2, pint3, pint3, pint4, pint4, pint5), List("d", "e")), // A attribute updated
        ))
      } yield ()
    }


    "ref - filter/value" - refs { implicit conn =>
      for {
        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iMap(Map(pint0, pint1)).save.transact
        _ <- A.i(1).save.transact

        // will be updated
        _ <- A.i.Bb.*(B.s_?.iMap_?).insert(
          (2, List(
            (None, None), // no relationship to B created
            (None, Some(Map(pint1, pint2))),
            (Some("a"), None),
            (Some("b"), Some(Map(pint2, pint3))),
          ))
        ).transact

        // Filter by B attribute, update B values
        _ <- A.Bb.s_.iMap.add(pint3, pint4).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.get.map(_ ==> List(
          (1, List()), //                                          no change to entity without relationship to B
          (2, List(
            // (None, None),                                       no relationship to B
            (None, Some(Map(pint1, pint2))), //                    no change without filter match
            (Some("a"), None), //                                  no B attribute to update
            (Some("b"), Some(Map(pint2, pint3, pint3, pint4))), // B attribute updated
          ))
        ))

        // Filter by B attribute, upsert B values
        _ <- A.Bb.s_.iMap.add(pint4, pint5).upsert.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iMap_?).query.get.map(_ ==> List(
          (1, List()), //                                                        no change to entity without relationship to B
          (2, List(
            // (None, None),                                                     no relationship to B
            (None, Some(Map(pint1, pint2))), //                                  no change without filter match
            (Some("a"), Some(Map(pint4, pint5))), //                             B attribute added
            (Some("b"), Some(Map(pint2, pint3, pint3, pint4, pint4, pint5))), // B attribute updated
          ))
        ))

        _ <- B.s_?.a1.iMap.query.get.map(_ ==> List(
          (None, Map(pint1, pint2)),
          (Some("a"), Map(pint4, pint5)),
          (Some("b"), Map(pint2, pint3, pint3, pint4, pint4, pint5)),
          (Some("x"), Map(pint0, pint1)), // no change to entity without relationship from A
        ))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        // will not be updated since entities have no A -> B relationship
        _ <- B.s("x").iSet(Set(0, 1)).save.transact



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