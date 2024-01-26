package molecule.datalog.datomic

import datomic.{Peer, Util}
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
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
      val a = (1, Some(Set(int1, int2)))
      val b = (2, Some(Set(int2, int3, int4)))
      val c = (3, None)
      for {
        _ <- Ns.i.ints_?.insert(a, b, c).transact

        _ <- Ns.i.a1.ints_?.query.get.map(_ ==> List(a, b, c))



        //        _ <- Ns.i(42).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(42))

        //        _ <- Ns.i.ints.insert(List(
        //          (1, Set(int1, int2)),
        //          (2, Set(int2, int3)),
        //          (2, Set(int3, int4)),
        //          (2, Set(int3, int4)),
        //        )).transact
        //
        //        // Sum of unique values (Set semantics)
        //
        //        _ <- Ns.ints(sum).query.get.map(_.head.head ==~ int1 + int2 + int3 + int4)
        //
        //        _ <- Ns.i.ints(sum).query.get.map(_.map {
        //          case (1, setWithSum) => setWithSum.head ==~ int1 + int2
        //          case (2, setWithSum) => setWithSum.head ==~ int2 + int3 + int4
        //        })



        _ <- Ns.i.ii.ints.insert(List(
          (0, Set(int1, int2), Set(int1, int2)),
          //          (2, Set(int2, int3), Set(int2, int3)),
          //          (2, Set(int3, int4), Set(int3, int4)),
          //          (2, Set(int3, int4), Set(int3, int4)),
        )).i.transact

        // Sum of unique values (Set semantics)

        //        _ <- Ns.ints(sum).query.get.map(_.head.head ==~ int1 + int2 + int3 + int4)

        //        _ <- Ns.i.ii.ints.query.i.get.map(println(_))
        //        _ <- Ns.i.ii.ints(sum).query.i.get.map(println(_))
        //
        //        _ <- Ns.i.ints(sum).query.get.map(_.map {
        //          case (1, setWithSum) => setWithSum.head ==~ int1 + int2
        //          case (2, setWithSum) => setWithSum.head ==~ int2 + int3 + int4
        //        })


        //        _ <- Ns.i.ii.ints.insert(List((1, Set(1, 2), Set(1, 2)))).transact
        //        //        _ <- Ns.i.ii.longs.insert(List((1, Set(1, 2), Set(1L, 2L)))).transact
        //
        //        //        _ <- A.i.B.ii.C.ii.query.get.map(println(_))
        //
        //        //        _ <- Ns.i.ii(sum).query.get.map(println(_))
        ////        _ <- Ns.i.ii(sum).ints(sum).query.i.get.map(println(_))
        ////        _ <- Ns.i.i(sum).ints(sum).query.i.get.map(println(_))
        //        //        _ <- Ns.i.ii(avg).ints(sum).query.get.map(println(_))
        //
        //
        _ = {
          println("-------")
          Peer.q(
            """[:find  ?b ?c ?d
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]]
              |""".stripMargin, conn.db
          ).forEach { r => println(r) }
        }
        _ = {
          println("-------")
          Peer.q(
            """[:find  ?b ?c ?d
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]]
              |""".stripMargin, conn.db
          ).forEach { r => println(r) }
        }
        _ = {
          println("-------")
          Peer.q(
            """[:find  ?b
              |        (distinct ?c)
              |        (distinct ?d)
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]]
              |""".stripMargin, conn.db
          ).forEach { r => println(r) }
        }
        _ = {
          println("-------")
          Peer.q(
            """[:find  ?b
              |        (distinct ?c)
              |        (sum ?d)
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]]
              |""".stripMargin, conn.db
          ).forEach { r => println(r) }
        }
        _ = {
          println("-------")
          Peer.q(
            """[:find  ?b
              |        (sum ?c)
              |        (sum ?d)
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ii ?c]
              |        [?a :Ns/ints ?d]]
              |""".stripMargin, conn.db
          ).forEach { r => println(r) }
        }


        //        _ <- Ns.i.ii(sum).longs(sum).query.get.map(println(_))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.i.ii.insert(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(7)),
          (3, Set(3)),
          (4, Set())
        ).transact


        // Mandatory ========================================

        _ <- A.i.a1.ii.query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)), // 2 rows coalesced
          (3, Set(3)),
        ))


        _ <- A.i.a1.ii(Set(1)).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.ii(Set(1, 2)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.ii(Set.empty[Int]).query.get.map(_ ==> Nil)


        _ <- A.i.a1.ii.not(Set(1)).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.not(Set(2)).query.i.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.not(Set(1, 2)).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.not(Set.empty[Int]).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
          (3, Set(3)),
        ))


        _ <- A.i.a1.ii.has(1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
        ))
        _ <- A.i.a1.ii.has(2).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))


        _ <- A.i.a1.ii.has(2, 1).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
        ))
        _ <- A.i.a1.ii.has(2, 7).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7)),
        ))
        _ <- A.i.a1.ii.has(2, 3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (3, Set(3)),
        ))

        _ <- A.i.a1.ii.hasNo(1).query.get.map(_ ==> List(
          (2, Set(2, 7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.hasNo(2).query.get.map(_ ==> List(
          (2, Set(7)),
          (3, Set(3)),
        ))
        _ <- A.i.a1.ii.hasNo(3).query.get.map(_ ==> List(
          (1, Set(1, 2)),
          (2, Set(2, 7))
        ))


        // tacit ========================================

        _ <- A.i.a1.ii_.query.get.map(_ ==> List(1, 2, 3))

        _ <- A.i.a1.ii_(Set(1)).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_(Set(1, 2)).query.get.map(_ ==> List(1))
        _ <- A.i.a1.ii_(Set.empty[Int]).query.get.map(_ ==> Nil)


        _ <- A.i.a1.ii_.not(Set(1)).query.get.map(_ ==> List(1, 2, 3))
        _ <- A.i.a1.ii_.not(Set(1, 2)).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.not(Set.empty[Int]).query.get.map(_ ==> List(1, 2, 3))


        _ <- A.i.a1.ii_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.ii_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.ii_.has(2, 7).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.ii_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        _ <- A.i.a1.ii_.hasNo(1).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.ii_.hasNo(3).query.get.map(_ ==> List(1, 2))



        // optional ========================================

        allAssertedOptional = List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))), // 2 rows coalesced
          (3, Some(Set(3))),
        )

        // All
        _ <- A.i.a1.ii_?.query.i.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
          (4, None)
        ))

        _ <- A.i.a1.ii_?(Some(Set(1))).query.get.map(_ ==> List(
          // Set(1, 2) != Set(1)
        ))
        _ <- A.i.a1.ii_?(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.ii_?(Option.empty[Set[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.ii_?(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets are ignored (use None to match non-asserted card-set attributes)
        _ <- A.i.a1.ii_?(Some(Set.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> Nil)






        _ <- A.i.a1.ii_?.not(Some(Set(1))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.not(Some(Set(1, 2))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))


        // Negating None matches all asserted values
        _ <- A.i.a1.ii_?.not(Option.empty[Set[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.not(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.ii_?.not(Some(Set.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.not(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.not(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> allAssertedOptional)




        _ <- A.i.a1.ii_?.has(Some(1)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))
        _ <- A.i.a1.ii_?.has(Some(2)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // same as
        _ <- A.i.a1.ii_?.has(Some(Seq(2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
        ))
        // has 2 or 3
        _ <- A.i.a1.ii_?.has(Some(Seq(2, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2))),
          (3, Some(Set(3))),
        ))
        // has 2 and 3
        _ <- A.i.a1.ii_?.has(Some(Set(2, 3))).query.get.map(_ ==> List(

        ))
        // has 1 and 2
        _ <- A.i.a1.ii_?.has(Some(Set(1, 2))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
        ))

        // None matches non-asserted values
        _ <- A.i.a1.ii_?.has(Option.empty[Set[Int]]).query.get.map(_ ==> List((4, None)))
        _ <- A.i.a1.ii_?.has(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> List((4, None)))

        // Empty Sets match nothing
        _ <- A.i.a1.ii_?.has(Some(Set.empty[Int])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?.has(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> Nil)
        _ <- A.i.a1.ii_?.has(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> Nil)




        _ <- A.i.a1.ii_?.hasNo(Some(1)).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        // When 1 value in set, then the same as
        _ <- A.i.a1.ii_?.hasNo(Some(Set(1))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(2)).query.get.map(_ ==> List(
          (2, Some(Set(7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(3)).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7)))
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(Set(1, 2))).query.get.map(_ ==> List(
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))
        _ <- A.i.a1.ii_?.hasNo(Some(Set(1, 3))).query.get.map(_ ==> List(
          (1, Some(Set(1, 2))),
          (2, Some(Set(2, 7))),
          (3, Some(Set(3))),
        ))

        // Negating None matches all asserted values
        _ <- A.i.a1.ii_?.hasNo(Option.empty[Set[Int]]).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.hasNo(Option.empty[Seq[Set[Int]]]).query.get.map(_ ==> allAssertedOptional)

        // Negating empty Sets match nothing
        _ <- A.i.a1.ii_?.hasNo(Some(Set.empty[Int])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.hasNo(Some(Seq.empty[Set[Int]])).query.get.map(_ ==> allAssertedOptional)
        _ <- A.i.a1.ii_?.hasNo(Some(Seq(Set.empty[Int]))).query.get.map(_ ==> allAssertedOptional)


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