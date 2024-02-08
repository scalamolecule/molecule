package molecule.datalog.datomic

import datomic.{Peer, Util}
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJVM_datomic extends TestSuite_datomic {

  implicit val tolerant = tolerantIntEquality(toleranceInt)


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        _ <- Ns.i.ii.ints.insert(
          0, Set(1, 2), Set(1, 2)
        ).transact

        _ = {
          println("-------")
          Peer.q(
            """[:find  ?b
              |        (sum ?c)
              |        (sum ?d)
              | :with  ?a
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]
              |
              |        ]
              |        """.stripMargin,
            conn.db
          ).forEach { r => println(r) }

          println("-------")
          Peer.q(
            """[:find  ?b
              |        (sum ?c)
              |        (sum ?d)
              | :with  ?a
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]
              | :group-by ?b
              |        ]
              |        """.stripMargin,
            conn.db
          ).forEach { r => println(r) }
        }

        _ <- Ns.i.ii(sum).ints(sum).query.i.get.map(_ ==> List(
          (0, 3, 3)
        ))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.B.i.ii.insert(
          (1, 2, Set.empty[Int]),
          (3, 4, Set(5, 6)),
        ).i.transact

        // A.i was inserted
        _ <- A.i.query.get.map(_ ==> List(1, 3))
        _ <- B.i.query.get.map(_ ==> List(2, 4))

        // Only one relationship to B was not created since no value of B was present
        _ <- A.i_.b.query.i.get.map(_.size ==> 2)

        _ <- A.i.a1.B.i.ii_?.query.i.get.map(_ ==> List(
          (1, 2, None), // Relationship to B exists since B.i has value 2
          (3, 4, Some(Set(5, 6)))
        ))

        _ <- A.i.a1.B.ii_?.query.i.get.map(_ ==> List(
          (3, Some(Set(5, 6)))
        ))
        _ <- A.i.B.ii.query.get.map(_ ==> List(
          (3, Set(5, 6))
        ))





//        _ <- A.i.B.ii.insert(
//          (1, Set.empty[Int]),
//          (3, Set(5, 6)),
//        ).i.transact
//
//        // A.i was inserted
//        _ <- A.i.query.get.map(_ ==> List(1, 3))
//
//        // Relationship to B was not created since no value of B was present
//        _ <- A.i_.b.query.get.map(_.size ==> 1)
//
//        _ <- A.i.a1.B.ii_?.query.i.get.map(_ ==> List(
//          // (1, 2, None), // No relationship to B exists since B has no attributes with values
//          (3, Some(Set(5, 6)))
//        ))
//
//        _ <- A.i.a1.B.ii_?.query.i.get.map(_ ==> List(
//          (3, Some(Set(5, 6)))
//        ))
//        _ <- A.i.B.ii.query.get.map(_ ==> List(
//          (3, Set(5, 6))
//        ))


//        _ <- A.i.B.s.insert(
//          (1, "a"),
//          (2, "b"),
//        ).transact
//
//        _ <- A.i.a1.B.s.query.get.map(_ ==> List(
//          (1, "a"),
//          (2, "b"),
//        ))
//
//        _ <- A.B.i.insert(1).transact
//        _ <- A.B.i.query.get.map(_ ==> List(1))
//
//        _ <- A.i.B.i.insert(1, 2).transact
//        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))
//
//
//        _ <- A.B.C.i.insert(1).transact
//        _ <- A.B.C.i.query.get.map(_ ==> List(1))






//        _ <- A.i.B.ii.insert((1, Set.empty[Int])).i.transact
//
//        // A.i was inserted
//        _ <- A.i.query.get.map(_ ==> List(1))
//
//        // Relationship to B was not created since no value of B was present
//        _ <- A.i_.b.query.get.map(_.size ==> 0)
//
//
//
//        _ <- rawQuery(
//          """[:find  ?b
//            |        (distinct ?d4)
//            | :where [?a :A/i ?b]
//            |        [(datomic.api/q
//            |          "[:find (pull ?a1 [{:A/b [:B/ii]}] :limit nil)
//            |            :in $ ?a1]" $ ?a) [[?d1]]]
//            |        [(if (nil? ?d1) {:A/b {:B/ii []}} ?d1) ?d2]
//            |        [(:A/b ?d2) ?d3]
//            |        [(:B/ii ?d3) ?d4]
//            |        [(not-empty ?d4)]
//            |        ]
//            |""".stripMargin).map(println)
//
//
//        _ <- A.i.B.ii_?.query.i.get.map(_ ==> Nil)
//        _ <- A.i.B.ii.query.get.map(_ ==> Nil)


        //        _ = {
        //          println("-------")
        //          Peer.q(
        //            """[:find  ?id0
        //              |        (distinct ?d)
        //              | :where [(identity ?a) ?id0]
        //              |        [?a :A/bb ?b]
        //              |        [?b :B/c ?c]
        //              |        [?c :C/ii ?d]]""".stripMargin,
        //            conn.db
        //          ).forEach { r => println(r) }
        //        }
        //
        //        _ <- rawQuery(
        //          """
        //            |[:find  ?id0
        //            |        (distinct ?d)
        //            | :where [(identity ?a) ?id0]
        //            |        [?a :A/bb ?b]
        //            |        [?b :B/c ?c]
        //            |        [?c :C/ii ?d]]
        //            |""".stripMargin).map(println)

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
    //          |          "[:find  (pull ?a-?c [[:A/ii :limit nil]])
    //          |            :in $ ?a1
    //          |            :where [?a1 :A/i ?b1]
    //          |                   [(identity ?a1) ?a-?c]]" $ ?a ) [[?c2]]]
    //          |        [(if (nil? ?c2) {:A/ii []} ?c2) ?c3]
    //          |        [(:A/ii ?c3) ?c4]
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