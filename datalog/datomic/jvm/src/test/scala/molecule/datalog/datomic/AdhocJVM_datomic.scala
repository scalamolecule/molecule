package molecule.datalog.datomic

import java.net.URI
import java.time._
import java.util.{Date, UUID}
import molecule.base.error.ExecutionError
import molecule.core.util.Executor._
import molecule.coreTests.util.Array2List
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.language.implicitConversions

object AdhocJVM_datomic extends TestSuite_datomic with Array2List {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      val a = (1, List(int1, int2))
      val b = (2, List(int2, int3, int3))

      val a1 = (1, Set(int1, int2))
      val b1 = (2, Set(int2, int3, int4))
      for {

        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4))
        )).transact

//        _ <- Ns.i.a1.ints.has(int1, 2).query.i.get.map(_ ==> List(a1))


        _ <- Ns.i.intSeq.insert(List(a, b)).transact

        _ = {
          datomic.Peer.q(
            """[:find  ?b
              |        (distinct ?c)
              | :in    $ %
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ints ?c]
              |        (rule?c ?a)]
              |""".stripMargin, conn.db,
            """[
              |  [(rule?c ?a)
              |    [?a :Ns/ints 1]]
              |  [(rule?c ?a)
              |    [?a :Ns/ints 2]]
              |]
              |""".stripMargin

          ).forEach(r => println(r))
          println("-----------")


          datomic.Peer.q(
            """[:find  ?b
              |        (distinct ?c)
              | :in    $ %
              | :where [?a :Ns/i ?b]
              |        [?a :Ns/ints ?c]
              |        (rule?c ?a)]
              |""".stripMargin, conn.db,
            """[
              |  [(rule?c ?a)
              |    [?a :Ns/ints 1]]
              |]
              |""".stripMargin

          ).forEach(r => println(r))
          println("-----------")

          //          datomic.Peer.q(
          //            """[:find  ?b ?c3
          //              | :in    $ ?c-blacklist
          //              | :where [?a :Ns/i ?b]
          //              |        [?a :Ns/intSeq ?c]
          //              |   ;;     [(into #{} ?c-blacklist) ?c-blackset]
          //              |        [(contains? ?c-blacklist ?a) ?c-blacklisted]
          //              |        [(not ?c-blacklisted)]
          //              |        [(datomic.api/q
          //              |          "[:find (distinct ?c-pair)
          //              |            :in $ ?a
          //              |            :where
          //              |                   [?a :Ns/intSeq ?c]
          //              |                   [?c :Ns.intSeq/i_ ?c-i]
          //              |                   [?c :Ns.intSeq/v_ ?c-v]
          //              |                   [(vector ?c-i ?c-v) ?c-pair]]" $ ?a) [[?c1]]]
          //              |        [(sort-by first ?c1) ?c2]
          //              |        [(map second ?c2) ?c3]
          //              |        ]
          //              |""".stripMargin, conn.db, Set(17592186045418L).asJava).forEach(r => println("-- B -- " + r))
        }





        _ <- Ns.i.a1.intSeq.has(int0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.intSeq.has(int1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.intSeq.has(int2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(int3).query.get.map(_ ==> List(b))
        // Same as
        _ <- Ns.i.a1.intSeq.has(List(int0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.intSeq.has(List(int1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.intSeq.has(List(int2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(List(int3)).query.get.map(_ ==> List(b))


        // OR semantics when multiple values

        // "Has this OR that"
        _ <- Ns.i.a1.intSeq.has(int1, int2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(int1, int3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(int2, int3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(int1, int2, int3).query.get.map(_ ==> List(a, b))
        // Same as
        _ <- Ns.i.a1.intSeq.has(List(int1, int2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(List(int1, int3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(List(int2, int3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.intSeq.has(List(int1, int2, int3)).query.get.map(_ ==> List(a, b))


        // Empty Seq/Seqs match nothing
        _ <- Ns.i.a1.intSeq.has(List.empty[Int]).query.get.map(_ ==> List())

        // Exact Array matches

        // AND semantics
        // "Is exactly this AND that"
        //        _ <- Ns.i.a1.intList(List(int1)).query.i.get.map(_ ==> List())
        //
        //
        //
        //
        //
        //
        //        _ <- Ns.i.a1.intSeq.query.i.get.map(_ ==> List(a)) // include exact match
        //        _ <- Ns.i.a1.intList(List(int1, int2)).query.i.get.map(_ ==> List(a)) // include exact match
        //        _ <- Ns.i.a1.intList(List(int1, int2, int3)).query.get.map(_ ==> List())
        //        // Same as
        //        _ <- Ns.i.a1.intList(Seq(List(int1))).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.intList(Seq(List(int1, int2))).query.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.intList(Seq(List(int1, int2, int3))).query.get.map(_ ==> List())
        //
        //
        //        // AND/OR semantics with multiple Arrays
        //
        //        // "(exactly this AND that) OR (exactly this AND that)"
        //        _ <- Ns.i.a1.intList(List(int1), List(int2, int3)).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.intList(List(int1, int2), List(int2, int3)).query.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.intList(List(int1, int2), List(int2, int3, int4)).query.get.map(_ ==> List(a, b))
        //        // Same as
        //        _ <- Ns.i.a1.intList(Seq(List(int1), List(int2, int3))).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.intList(Seq(List(int1, int2), List(int2, int3))).query.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.intList(Seq(List(int1, int2), List(int2, int3, int4))).query.get.map(_ ==> List(a, b))
        //
        //
        //        // Empty Seq/Arrays match nothing
        //        _ <- Ns.i.a1.intList(List(int1, int2), Seq.empty[Int]).query.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.intList(Seq.empty[Int], List(int1, int2)).query.get.map(_ ==> List(a))
        //        _ <- Ns.i.a1.intList(Seq.empty[Int], Seq.empty[Int]).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.intList(Seq.empty[Int]).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.intList(Seq.empty[Array[Int]]).query.get.map(_ ==> List())
        //        _ <- Ns.i.a1.intList(Seq(Seq.empty[Int])).query.get.map(_ ==> List())
        //

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.i.Bb.*(B.ii).insert(List((2, List(Set(3, 4))))).transact
        _ <- A.i.Bb.*(B.ii).query.get.map(_ ==> List((2, List(Set(3, 4)))))





        //        _ <- A.i.Bb.*(B.i.ii).insert((1, List((2, Set.empty[Int])))).transact
        //
        //        // A.i was inserted
        //        _ <- A.i.query.get.map(_ ==> List(1))
        //
        //        _ <- A.i.Bb.*?(B.i.ii).query.get.map(_ ==> List((1, Nil)))
        //        _ <- A.i.Bb.*(B.i.ii).query.get.map(_ ==> Nil)
        //
        //        // No optional B.ii value
        //        _ <- A.i.Bb.i.ii_?.query.get.map(_ ==> List((1, 2, None)))
        //        _ <- A.i.Bb.i.ii.query.get.map(_ ==> Nil)


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