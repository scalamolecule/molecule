package molecule.datalog.datomic

import molecule.base.error.ModelError
import molecule.boilerplate.api.{NestedInit_01, NestedInit_02}
import molecule.core.util.Executor._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

//object AdhocJVM_datomic extends TestSuiteArray_datomic {
object AdhocJVM_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      val (a1, b2) = ("a" -> int1, "b" -> int2)
      val (b3, c4) = ("b" -> int3, "c" -> int4)

      for {

        _ <- Ns.i.intMap.insert(List(
          (1, Map(a1, b2)),
          (2, Map(b3, c4)),
        )).transact

        _ <- Ns.i.a1.intMap_.hasNo(List.empty[Int]).query.get.map(_ ==> List(1, 2))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        //        _ <- A.i.a1.B.?(B.s.i.C.s.i).query.get.map(_ ==> List(
        //          (1, None),
        //          (2, None),
        //          (3, Some(("b", 3, "c", 3)))
        //        ))
        //
        //        _ <- A.i.B.s.i.C.s.i.query.get.map(_ ==> List(
        //          (3, "b", 3, "c", 3)
        //        ))

        //        _ <- A.s.i.insert("a", 1).transact
        //        _ <- A.s.i.query.get.map(_ ==> List(("a", 1)))

        //        _ <- A.i.B.?(B.i.s.C.?(C.s.i)).D.?(D.s.i.E.?(E.i)).insert(List(
        //          //        _ <- A.i.B.?(B.i.C.?(C.s.i)).D.?(D.s.i.E.?(E.i)).insert(List(
        //          //            (1, None, None),
        //          //            (2, Some(20), None),
        //          //            (3, None, Some(300)),
        //          //                    (4, Some(40), Some(400)),
        //          (4, Some((40, "b", Some(("c", 400)))), Some(("d", 42, Some(4000)))),
        //          //          (4, Some((40, Some(("c", 400)))), Some(("d", 42, Some(4000)))),
        //
        //        )).transact
        //
        //        _ <- A.i.a1.B.?(B.i.s.C.?(C.s.i)).query.get
        //        _ = println("@@@@@@@@@@@@@@@@@@@@@@@@@")
        //        _ <- A.i.a1.B.?(B.i.s.C.?(C.s.i)).D.?(D.s.E.?(E.i)).query.get

        //
        _ <- A.s.i.B.?(B.i.C.?(C.i)).D.?(D.i).insert(List(
          ("a", 1, Some((2, Some(3))), Some(4)),
        )).transact

//        _ <- A.s.i.a1.B.?(B.i).query.get.map(_ ==> List(
//          ("a", 1, Some(2)),
//        ))
//        _ = println("@@@@@@@@@@@@@@@@@@@@@@@@@")
//        _ <- A.s.i.a1.B.?(B.i.C.?(C.i)).query.get.map(_ ==> List(
//          ("a", 1, Some((2, Some(3)))),
//        ))
//        _ = println("@@@@@@@@@@@@@@@@@@@@@@@@@")

        //        _ <- rawQuery(
        //          """[:find  ?b ?c
        //            |        (
        //            |          pull ?id1 [
        //            |            {(:A/b :default "__none__") [
        //            |              (:B/i :default "__none__")
        //            |            ]}
        //            |          ]
        //            |        )
        //            |        (
        //            |          pull ?id2 [
        //            |            {(:A/d :default "__none__") [
        //            |              (:D/i :default "__none__")
        //            |            ]}
        //            |          ]
        //            |        )
        //            | :where [?a :A/s ?b]
        //            |        [?a :A/i ?c]
        //            |        [(identity ?a) ?id1]
        //            |        [(identity ?a) ?id2]]
        //            |""".stripMargin, true)


        _ <- A.s.i.a1.B.?(B.i).D.?(D.i).query.i.get.map(_ ==> List(
          ("a", 1, Some(2), Some(4)),
        ))


        //        _ <- A.i
        //          .B.?(B.i.s)
        //          .C.?(C.s.i).insert(List(
        ////            (1, None, None),
        ////            (2, Some((20, "b")), None),
        ////            (3, None, Some(("c", 300))),
        //            (4, Some((40, "b")), Some(("c", 400))),
        //          )).transact
        //
        ////        _ <- rawQuery(
        ////          """[:find  ?b
        ////            |        (
        ////            |          pull ?id1 [
        ////            |            {(:A/b :default "__none__") [
        ////            |              (:B/i :default "__none__")
        ////            |              (:B/s :default "__none__")
        ////            |            ]}
        ////            |          ]
        ////            |        )
        ////            |        (
        ////            |          pull ?id2 [
        ////            |            {(:A/c :default "__none__") [
        ////            |              (:C/s :default "__none__")
        ////            |              (:C/i :default "__none__")
        ////            |            ]}
        ////            |          ]
        ////            |        )
        ////            | :where [?a :A/i ?b]
        ////            |        [(identity ?a) ?id1]
        ////            |        [(identity ?a) ?id2]]
        ////            |""".stripMargin, true)
        //
        //        _ <- A.i.a1.B.?(B.i.s.C.?(C.s.i)).query.get
        //        _ = println("@@@@@@@@@@@@@@@@@@@@@@@@@")
        //        _ <- A.i.a1.B.?(B.i.s).C.?(C.s.i).query.get.map(_ ==> List(
        //            //            (1, None, None),
        //            //            (2, Some((20, "b")), None),
        //            //            (3, None, Some(("c", 300))),
        //            (4, Some((40, "b")), Some(("c", 400))),
        //          ))

        //
        //        _ <- rawQuery(
        //          """[:find  ?b
        //            |        (
        //            |          pull ?id0 [
        //            |            {:A/b [
        //            |                (:B/i :default "__none__")
        //            |              ]
        //            |            }
        //            |          ]
        //            |        )
        //            | :where [?a :A/i ?b]
        //            |        [(identity ?a) ?id0]]
        //            |""".stripMargin, true)


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