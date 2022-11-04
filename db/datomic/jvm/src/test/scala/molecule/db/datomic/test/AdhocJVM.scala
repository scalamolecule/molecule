package molecule.db.datomic.test

import datomic.Peer
import molecule.coreTests.dataModels.core.types.dsl.CardSet._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.test.exprSet.ExprSet_Int.{int1, int2}
import utest._
import java.util.{Collections, List => jList, Set => jSet}


object AdhocJVM extends DatomicTestSuite {

  lazy val tests = Tests {

    "adhocJVM" - cardSet { implicit conn =>

      //      val a = (Set(int1, int2), Set(1, 2), 1)
      //      val b = (Set(int2, int3, int4), Set(2, 3, 4), 2)
      //      NsSet.ints.nn.n.insert(List(a, b)).transact

      val a = (1, Set(int1, int2))
      val b = (2, Set(int2, int3, int4))
      NsSet.n.ints.insert(List(a, b)).transact


      val aa = new java.util.HashSet[Long](2)
      aa.add(1L)
      aa.add(2L)
      val bb = new java.util.HashSet[Long](2)
      bb.add(1L)
      bb.add(2L)
      val cc = new java.util.HashSet[jSet[Long]](2)
      cc.add(aa)
      cc.add(bb)

      Peer.q(
        """[:find  ?b
          |        (distinct ?c)
          | :in    $ [?c-set ...]
          | :where [?a :NsSet/n ?b]
          |        [?a :NsSet/ints ?c]
          |        [(datomic.api/q
          |          "[:find (distinct ?c1)
          |            :in $ ?a1
          |            :where [?a1 :NsSet/ints ?c1]]" $ ?a) [[?c2]]]
          |        [(into #{} ?c-set) ?c-set1]
          |        [(= ?c2 ?c-set1) ]
          |]""".stripMargin,
        conn.db,
                Array(Seq(1, 2).asJava, Seq(2, 3).asJava)
//        cc
      ).forEach(r => println(r))

      //      Peer.q(
      //        """[:find  ?b
      //          |        (distinct ?c)  ?c2 ?c-set
      //          | :in    $ [?c-set ...]
      //          | :where [?a :NsSet/n ?b]
      //          |        [?a :NsSet/ints ?c]
      //          |        [(datomic.api/q
      //          |          "[:find (distinct ?c1)
      //          |            :in $ ?a1
      //          |            :where [?a1 :NsSet/ints ?c1]]" $ ?a) [[?c2]]]
      //          |        ]""".stripMargin,
      //        //          |        [(!= ?c2 ?c-set)]
      //        conn.db,
      ////        Array(Seq(1, 2).asJava, Seq(2, 3).asJava)
      //        42
      //      ).forEach(r => println(r))


      //      Peer.q(
      //        """[:find  ?b
      //          |        (distinct ?c)
      //          | :in    $ ?c-args
      //          | :where [?a :NsSet/n ?b]
      //          |        [?a :NsSet/ints ?c]
      //          |        [(datomic.api/q
      //          |          "[:find (distinct ?c1)
      //          |            :in $ ?a1
      //          |            :where [?a1 :NsSet/ints ?c1]]" $ ?a) [[?c2]]]
      //          |        [(into #{} ?c-args) ?c-set]
      //          |        [(clojure.set/intersection ?c-set ?c2) ?c-is]
      //          |        [(empty? ?c-is)]]""".stripMargin,
      //        conn.db,
      //        Array(1, 5)
      //      ).forEach(r => println(r))


      //      println("--------------- A")
      //
      //      val aa = Peer.q(
      //        """[:find ?a (distinct ?c1)
      //          | :where [?a :NsSet/n ?b][?a :NsSet/ints ?c1]]""".stripMargin,
      //        conn.db,
      //      )
      //      aa.forEach(r => println(r))
      //      println("------")
      //
      //
      //      Peer.q(
      //        """[:find  ?b
      //          |        (distinct ?c)
      //          | :in    $ [[_ ?c2]] ?c-args
      //          | :where [?a :NsSet/n ?b]
      //          |        [?a :NsSet/ints ?c]
      //          |        [(into #{} ?c-args) ?c-set]
      //          |        [(clojure.set/intersection ?c-set ?c2) ?c-is]
      //          |        [(empty? ?c-is)]]""".stripMargin,
      //        conn.db,
      //        aa,
      //        Array(1, 5)
      //      ).forEach(r => println(r))
      //
      //
      //      println("--------------- B")
      //
      //
      ////            val r = Peer.q(
      ////              """[:find  ?a
      ////                | :in    $ %
      ////                | :where [?a :NsSet/ints ?c]
      ////                |        (rule?c ?a)]""".stripMargin,
      ////              conn.db,
      ////              """[
      ////                |  [(rule?c ?a)
      ////                |    [?a :NsSet/ints 1]
      ////                |    [?a :NsSet/ints 5]]
      ////                |]""".stripMargin
      ////            )
      ////            r.forEach(r => println(r))
      //
      //      val r = Peer.q(
      //        """[:find  ?a
      //          | :in    $ %
      //          | :where [?a :NsSet/ints ?c]
      //          |        (rule?c ?a)]""".stripMargin,
      //        conn.db,
      //        """[
      //          |  [(rule?c ?a) [?a :NsSet/ints 1]]
      //          |  [(rule?c ?a) [?a :NsSet/ints 5]]
      //          |]""".stripMargin
      //      )
      //      r.forEach(r => println(r))
      //      println(r)
      //      println(r.getClass)
      //
      //      println("------")
      //
      //      Peer.q(
      //        """[:find  ?a ?b
      //          |        (distinct ?c) ?c-blacklist ?c-blacklisted
      //          | :in    $ ?c-blacklist
      //          | :where [?a :NsSet/n ?b]
      //          |        [?a :NsSet/ints ?c]
      //          |        [(contains? ?c-blacklist ?a) ?c-blacklisted]
      //          |        [(not ?c-blacklisted)]]""".stripMargin,
      //        conn.db,
      ////        r
      ////          Array(1, 5)
      ////          Array(17592186045418L.asInstanceOf[AnyRef]).asInstanceOf[AnyRef]
      //        datomic.Util.read("17592186045418")
      //
      //      ).forEach(r => println(r))


      //      One.string.int.insert(("Bob", 42), ("Liz", 35)).transact
      //      One.string.int.query.get ==> List(("Liz", 35), ("Bob", 42))
    }
  }
}
