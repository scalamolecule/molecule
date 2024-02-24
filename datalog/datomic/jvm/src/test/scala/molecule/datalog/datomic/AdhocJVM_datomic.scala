package molecule.datalog.datomic

import datomic.{Peer, Util}
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import org.scalactic.Equality
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJVM_datomic extends TestSuite_datomic {

  implicit val tolerantInt   : Equality[Int]    = tolerantIntEquality(toleranceInt)
  implicit val tolerantDouble: Equality[Double] = tolerantDoubleEquality(toleranceDouble)


  // Use whole decimal numbers since only sorting is checked here
  override lazy val (float1, float2, float3, float4)                     = (1.0f, 2.0f, 3.0f, 4.0f)
  override lazy val (double1, double2, double3, double4)                 = (1.0, 2.0, 3.0, 4.0)
  override lazy val (bigDecimal1, bigDecimal2, bigDecimal3, bigDecimal4) =
    (BigDecimal(1.0), BigDecimal(2.0), BigDecimal(3.0), BigDecimal(4.0))

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {

        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
        ).transact


        //        _ <- Ns.i.ints_?.query.i.get.map(_.toSet ==> Set( // (since we can't sort by Sets)
        //          (1, None),
        //          (1, Some(Set(2))),
        //          (2, Some(Set(3))),
        //        ))

        //        // Update all entities where non-unique attribute i is 1
        //        _ <- Ns.i_(1).ints(Set(4)).update.transact
        //        _ <- Ns.id.a1.i.ints_?.query.get.map(_ ==> List(
        //          (a, 1, None), // not updated since there were no previous value
        //          (b, 1, Some(Set(4))), // 2 updated to 4
        //          (c, 2, Some(Set(3))),
        //        ))
        //
        //        // Upsert all entities where non-unique attribute i is 1
        //        _ <- Ns.i_(1).ints(Set(5)).upsert.transact
        //        _ <- Ns.id.a1.i.ints_?.query.get.map(_ ==> List(
        //          (a, 1, Some(Set(5))), // 5 inserted
        //          (b, 1, Some(Set(5))), // 4 updated to 5
        //          (c, 2, Some(Set(3))),
        //        ))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)


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