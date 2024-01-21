package molecule.datalog.datomic

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJVM_datomic extends TestSuite_datomic {


  def getTriples: List[(String, Int, Int)] = (1 to 5).toList.map { int =>
    val s = ('a' + scala.util.Random.nextInt(3)).toChar.toString // "a" or "b"
    val i = scala.util.Random.nextInt(3) + 1 // 1 or 2
    (s, i, int)
  }

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        //        _ <- Ns.i(42).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(42))

        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
        ).transact
        _ <- Ns.i.long.insert((1, long1), (1, long3), (2, long4)).transact
        _ <- Ns.i.float.insert((1, float1), (1, float3), (2, float4)).transact
        _ <- Ns.i.double.insert((1, double1), (1, double3), (2, double4)).transact
        _ <- Ns.i.bigInt.insert((1, bigInt1), (1, bigInt3), (2, bigInt4)).transact
        _ <- Ns.i.bigDecimal.insert((1, bigDecimal1), (1, bigDecimal3), (2, bigDecimal4)).transact
        _ <- Ns.i.byte.insert((1, byte1), (1, byte3), (2, byte4)).transact
        _ <- Ns.i.short.insert((1, short1), (1, short3), (2, short4)).transact

        _ <- Ns.i.int(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.long(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.float(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.double(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.bigInt(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.bigDecimal(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.byte(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))
        _ <- Ns.i.short(median).a1.query.get.map(_ ==> List((1, 2), (2, 4)))

        _ <- Ns.i.int(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.long(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.float(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.double(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.bigInt(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.bigDecimal(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.byte(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))
        _ <- Ns.i.short(median).d1.query.get.map(_ ==> List((2, 4), (1, 2)))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        // Card one ref attr
        List(_, e1) <- A.i.B.i.insert(1, 2).i.transact.map(_.ids)
        _ <- A.i.b.query.i.get.map(_ ==> List((1, e1)))

        //        // Card many ref attr (returned as Set)
        //        List(_, e2) <- A.i.Bb.i.insert(1, 2).transact.map(_.ids)
        //        _ <- A.i.bb.query.get.map(_ ==> List((1, Set(e2))))


      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      val triples             = getTriples.map(t => (t._3, t._1, t._2))
      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
      for {
        _ <- Uniques.int.s.i.insert(triples).transact
        c1 <- query("", 2).get.map { case (List(`a`, `b`), cursor, true) => cursor }
        c2 <- query(c1, 2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        //        c3 <- query(c2, 2).get.map { case (List(`e`), cursor, false) => cursor }
        //        c2 <- query(c3, -2).get.map { case (List(`c`, `d`), cursor, true) => cursor }
        //        _ <- query(c2, -2).get.map { case (List(`a`, `b`), _, false) => () }

      } yield ()
    }


    "validation" - validation { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Validation._
      for {

//        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf")).save.transact.map(_.id)

//        // We can remove a value from a Set as long as it's not the last value
//        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact

        // Can't remove the last value of a mandatory attribute Set of values
        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Can't delete mandatory attributes (or remove last values of card-many attributes):
                  |  MandatoryAttr.hobbies
                  |""".stripMargin
          }

      } yield ()
    }


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