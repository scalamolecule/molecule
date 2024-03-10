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

      for {

        //        _ <- rawTransact(
        //          """[
        //            |  ;;[:db/add #db/id[db.part/user -1] :Ns/i 42]
        //            |  [:db/add #db/id[db.part/user -1] :Ns/intArray #db/id[db.part/user -2]]
        //            |  [:db/add #db/id[db.part/user -2] :Ns.intArray/i_ 0]
        //            |  [:db/add #db/id[db.part/user -2] :Ns.intArray/intArray 5]
        //            |  [:db/add #db/id[db.part/user -1] :Ns/intArray #db/id[db.part/user -3]]
        //            |  [:db/add #db/id[db.part/user -3] :Ns.intArray/i_ 1]
        //            |  [:db/add #db/id[db.part/user -3] :Ns.intArray/intArray 7]
        //            |  [:db/add #db/id[db.part/user -1] :Ns/intArray #db/id[db.part/user -4]]
        //            |  [:db/add #db/id[db.part/user -4] :Ns.intArray/i_ 2]
        //            |  [:db/add #db/id[db.part/user -4] :Ns.intArray/intArray 7]
        //            |]
        //            |""".stripMargin)

        //        _ = {
        //          println(datomic.Peer.q(
        //            """[:find  ?b
        //              |        (distinct ?c4)
        //              | :where [?a :Ns/i ?b]
        //              |        [(datomic.api/q
        //              |          "[:find (pull ?a [
        //              |              {(:Ns/stringArray :limit nil) [
        //              |                  :Ns.stringArray/i_
        //              |                  :Ns.stringArray/v_
        //              |                ]}
        //              |             ])
        //              |            :in $ ?a]" $ ?a) [[?c1]]]
        //              |        [(if (nil? ?c1) {:Ns/stringArray []} ?c1) ?c2]
        //              |        [(:Ns/stringArray ?c2) ?c3]
        //              |        [(map vals  ?c3) ?c4]
        //              |        ]
        //              |""".stripMargin, conn.db))
        //          println("-----------")
        //        }




        _ <- Ns.i(1).stringArray_(Array(string1, string2)).save.transact
        _ <- Ns.i(1).intArray_(Array(int1, int2)).save.transact
        _ <- Ns.i(1).longArray_(Array(long1, long2)).save.transact
        _ <- Ns.i(1).floatArray_(Array(float1, float2)).save.transact
        _ <- Ns.i(1).doubleArray_(Array(double1, double2)).save.transact
        _ <- Ns.i(1).booleanArray_(Array(boolean1, boolean2)).save.transact
        _ <- Ns.i(1).bigIntArray_(Array(bigInt1, bigInt2)).save.transact
        _ <- Ns.i(1).bigDecimalArray_(Array(bigDecimal1, bigDecimal2)).save.transact
        _ <- Ns.i(1).dateArray_(Array(date1, date2)).save.transact
        _ <- Ns.i(1).durationArray_(Array(duration1, duration2)).save.transact
        _ <- Ns.i(1).instantArray_(Array(instant1, instant2)).save.transact
        _ <- Ns.i(1).localDateArray_(Array(localDate1, localDate2)).save.transact
        _ <- Ns.i(1).localTimeArray_(Array(localTime1, localTime2)).save.transact
        _ <- Ns.i(1).localDateTimeArray_(Array(localDateTime1, localDateTime2)).save.transact
        _ <- Ns.i(1).offsetTimeArray_(Array(offsetTime1, offsetTime2)).save.transact
        _ <- Ns.i(1).offsetDateTimeArray_(Array(offsetDateTime1, offsetDateTime2)).save.transact
        _ <- Ns.i(1).zonedDateTimeArray_(Array(zonedDateTime1, zonedDateTime2)).save.transact
        _ <- Ns.i(1).uuidArray_(Array(uuid1, uuid2)).save.transact
        _ <- Ns.i(1).uriArray_(Array(uri1, uri2)).save.transact
        _ <- Ns.i(1).byteArray_(Array(byte1, byte2)).save.transact
        _ <- Ns.i(1).shortArray_(Array(short1, short2)).save.transact
        _ <- Ns.i(1).charArray_(Array(char1, char2)).save.transact

        _ <- Ns.i.stringArray.query.get.map(_.head ==> (1, Array(string1, string2)))
        _ <- Ns.i.intArray.query.get.map(_.head ==> (1, Array(int1, int2)))
        _ <- Ns.i.longArray.query.get.map(_.head ==> (1, Array(long1, long2)))
        _ <- Ns.i.floatArray.query.get.map(_.head ==> (1, Array(float1, float2)))
        _ <- Ns.i.doubleArray.query.get.map(_.head ==> (1, Array(double1, double2)))
        _ <- Ns.i.booleanArray.query.get.map(_.head ==> (1, Array(boolean1, boolean2)))
        _ <- Ns.i.bigIntArray.query.get.map(_.head ==> (1, Array(bigInt1, bigInt2)))
        _ <- Ns.i.bigDecimalArray.query.get.map(_.head ==> (1, Array(bigDecimal1, bigDecimal2)))
        _ <- Ns.i.dateArray.query.get.map(_.head ==> (1, Array(date1, date2)))
        _ <- Ns.i.durationArray.query.get.map(_.head ==> (1, Array(duration1, duration2)))
        _ <- Ns.i.instantArray.query.get.map(_.head ==> (1, Array(instant1, instant2)))
        _ <- Ns.i.localDateArray.query.get.map(_.head ==> (1, Array(localDate1, localDate2)))
        _ <- Ns.i.localTimeArray.query.get.map(_.head ==> (1, Array(localTime1, localTime2)))
        _ <- Ns.i.localDateTimeArray.query.get.map(_.head ==> (1, Array(localDateTime1, localDateTime2)))
        _ <- Ns.i.offsetTimeArray.query.get.map(_.head ==> (1, Array(offsetTime1, offsetTime2)))
        _ <- Ns.i.offsetDateTimeArray.query.get.map(_.head ==> (1, Array(offsetDateTime1, offsetDateTime2)))
        _ <- Ns.i.zonedDateTimeArray.query.get.map(_.head ==> (1, Array(zonedDateTime1, zonedDateTime2)))
        _ <- Ns.i.uuidArray.query.get.map(_.head ==> (1, Array(uuid1, uuid2)))
        _ <- Ns.i.uriArray.query.get.map(_.head ==> (1, Array(uri1, uri2)))
        _ <- Ns.i.byteArray.query.get.map(_.head ==> (1, Array(byte1, byte2)))
        _ <- Ns.i.shortArray.query.get.map(_.head ==> (1, Array(short1, short2)))
        _ <- Ns.i.charArray.query.get.map(_.head ==> (1, Array(char1, char2)))
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