package molecule.sql.jdbc.test

import molecule.base.ast.SchemaAST
import molecule.boilerplate.api._
import molecule.boilerplate.api.expression.{ExprOneMan_2_Number, ExprOneMan_3_Number, ExprOneMan_4_Number}
import molecule.core.action.Query
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.sql.jdbc.async._
import molecule.sql.jdbc.setup.JdbcTestSuite
import utest._
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJdbcJVM extends JdbcTestSuite {

  //  val name   = Map("en" -> "hello", "de" -> "hello", "da" -> "hej")
  //  val name_k = Set("en", "de", "da")
  //  val name_v = Set("hello", "hej")
  //
  //  val name_   = 7
  //  val name_k_ = 7
  //  val name_v_ = 7
  //
  //  val name_?   = 7
  //  val name_k_? = 7
  //  val name_v_? = 7
  //
  //  val ranked   = List("Peter", "Bob", "Mary")
  //  val ranked_i = List(0, 1, 2)
  //
  //  val rankedA   = Array("Peter", "Bob", "Mary")
  //  val rankedA_i = Array(0, 1, 2)

  //  val x: Instant = ???
  //  val y: Long    = x.toEpochMilli

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      for {
        _ <- Ns.i(1).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }



    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        _ <- Require.int1.errorMsg.insert(
    //          (1, 2),
    //          (2, 2),
    //          (3, 2),
    //        ).transact
    //
    //        _ <- Variables.int1.errorMsg.query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.inspect
    //        _ <- Variables.int1.<(Variables.errorMsg).query.get.map(_ ==> List())
    //
    //
    //      } yield ()
    //    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

//      val a1: Nested2TxInit_01[Int, (String, Int), Tx_2]              = A.i.Bb.*(B.s.i)
//      val a1: Query[(Int, Seq[(String, Int)])]                        = A.i.Bb.*(B.s.i).query
//      val a1: Tx_2[Int, Seq[(String, Int)], Nothing]                  = A.i.Bb.*(B.s.i).Tx
//      val a1: Tx_3[Int, Seq[(String, Int)], Int, Int]                 = A.i.Bb.*(B.s.i).Tx.myTxAttr
//      val a1: Molecule_04[Int, Seq[(String, Int)], Int, String]       = A.i.Bb.*(B.s.i).Tx.apply(D.i.s)
//      val a1: Query[(Int, Seq[(String, Int)], Int, String)]           = A.i.Bb.*(B.s.i).Tx.apply(D.i.s).query
//      val a2: Query[(Int, Seq[(String, Int)], Int)]                   = A.i.Bb.*(B.s.i).Tx.myTxAttr.query
//      val a2: Molecule_05[Int, Seq[(String, Int)], Int, Int, String]  = A.i.Bb.*(B.s.i).Tx.myTxAttr.apply(D.i.s)
//      val a2: Molecule_06[Int, String, Seq[String], Int, Int, String] = A.i.s.Bb.*(B.s).Tx.myTxAttr(D.i.s)
//      val a1: Molecule_05[Int, Seq[String], Long, Int, String]        = A.i.Bb.*(B.s).Tx.id(D.i.s)
//      val a3: Molecule_05[Int, Seq[String], Int, Int, String]         = A.i.Bb.*?(B.s).Tx.myTxAttr(D.i.s)
//
//      val b1: Molecule_00                        = A.i_.Tx.apply(D.i_)
//      val b1: Molecule_02[Int, Int]              = A.i_.Tx.apply(D.i + E.i)
//      val b1: Molecule_01[Int]                   = A.i.Tx.apply(D.i_)
//      val b1: Molecule_02[Int, Int]              = A.i.Tx.apply(D.i)
//      val b1: Molecule_03[Int, Int, String]      = A.i.Tx.apply(D.i.s)
//      val b1: Molecule_03[Int, Long, Int]        = A.i.Tx.id.apply(D.i)
//      val b2: Molecule_03[Int, Int, Int]         = A.i.Tx.myTxAttr.apply(D.i) // no?
//      val b2: Molecule_04[Int, Int, Int, String] = A.i.Tx.myTxAttr.apply(D.i.s) // no?
//      val b2: Molecule_03[Int, Int, Int]         = A.i.Tx.myTxAttr.meta(D.i)
//      val b3: Molecule_03[Int, Long, Int]        = A.i.Tx.created.apply(D.i)
//      val b3: Molecule_02[Int, Int]              = A.i.apply(D.i) // filter attr ok
//      //val b3: Molecule_03[Int, Int, String]      = A.i.apply(D.i.s)
//      val b4: Molecule_03[Int, Long, Int]        = A.i.Tx.updated(D.i)
//      val b4: Molecule_03[Int, Long, Int]        = A.i.Tx.updated.>(7).apply(D.i)
//
//      val e2: Composite_00[Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                                                   = A.i_.+(B.s_)
//      val e2: Composite_01[Int, Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                                              = A.i.+(B.s_)
//      val e2: Composite_02[Int, String, Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                                      = A.i.+(B.s)
//      val e2: Composite_02[Int, (String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                               = A.i.+(B.s.i)
//      val e2: Composite_01[(String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                                    = A.i_.+(B.i_).+(C.s.i)
//      val e2: Composite_02[Int, (String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                               = A.i_.+(B.i).+(C.s.i)
//      val e2: Composite_02[Int, (String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                               = A.i.+(B.i_).+(C.s.i)
//      val e2: Composite_03[Int, Int, (String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                          = A.i.+(B.i).+(C.s.i)
//      val e2: Composite_03[Int, (Int, String), (String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23]                = A.i.+(B.i.s).+(C.s.i)
//      val e2: Composite_04[Int, (Int, String), (String, Int), (String, Int), Tx_0, Tx_1, Tx_2, Tx_3, Tx_4, Tx_5, Tx_6, Tx_7, Tx_8, Tx_9, Tx_10, X12, X13, X14, X15, X16, X17, X18, X19, X20, X21, X22, X23] = A.i.+(B.i.s).+(C.s.i).+(D.s.i)
//
//      val e2: Tx_0[Nothing]               = A.i_.+(B.s_).Tx
//      val e2: Molecule_01[Int]            = A.i_.+(B.s_).Tx.apply(D.i)
//      val e2: Tx_2[Int, String, Nothing]  = A.i.+(B.s).Tx
//      val e2: Tx_3[Int, String, Int, Int] = A.i.+(B.s).Tx.myTxAttr
//
//      val e2: Tx_2[Int, (String, Int), Nothing]              = A.i.+(B.s.i).Tx
//      val e2: Tx_3[Int, (String, Int), Int, Int]             = A.i.+(B.s.i).Tx.myTxAttr
//      val e2: Molecule_03[Int, (String, Int), Int]           = A.i.+(B.s.i).Tx.apply(D.i)
//      val e3: Tx_3[(Int, String), (String, Int), Int, Int]   = A.i.s.+(B.s.i).Tx.myTxAttr
//      val e2: Molecule_03[(Int, String), (String, Int), Int] = A.i.s.+(B.s.i).Tx.apply(D.i)
//
//      val e2: Tx_3[Int, Int, (String, Int), Nothing]                         = A.i.+(B.i).+(C.s.i).Tx
//      val e2: Tx_4[Int, Int, (String, Int), Int, Int]                        = A.i.+(B.i).+(C.s.i).Tx.myTxAttr
//      val e2: Molecule_05[Int, Int, (String, Int), Int, Int]                 = A.i.+(B.i).+(C.s.i).Tx.myTxAttr.apply(D.i)
//      val e2: Molecule_04[Int, Int, (String, Int), Int]                      = A.i.+(B.i).+(C.s.i).Tx.apply(D.i)
//      val e2: Molecule_04[Int, Int, (String, Int), Int]                      = (A.i + B.i + C.s.i).Tx.apply(D.i)
//      val e3: Tx_3[Int, (Int, String), (String, Int), Nothing]               = A.i.+(B.i.s).+(C.s.i).Tx
//      val e2: Tx_4[Int, (Int, String), (String, Int), Int, Int]              = A.i.+(B.i.s).+(C.s.i).Tx.myTxAttr
//      val e2: Molecule_04[Int, (Int, String), (String, Int), String]         = A.i.+(B.i.s).+(C.s.i).Tx.apply(D.s)
//      val e2: Molecule_05[Int, (Int, String), (String, Int), String, String] = A.i.+(B.i.s).+(C.s.i).Tx.apply(D.s.s)
//
//      val e2: Tx_4[Int, (Int, String), (String, Int), (String, Int), Nothing]               = A.i.+(B.i.s).+(C.s.i).+(D.s.i).Tx
//      val e2: Tx_5[Int, (Int, String), (String, Int), (String, Int), Long, Long]            = A.i.+(B.i.s).+(C.s.i).+(D.s.i).Tx.id
//      val e2: Tx_1[Int, Int]                                                                = A.i_.+(B.i_.s_).+(C.s_.i_).+(D.s_.i_).Tx.myTxAttr
//      val e2: Tx_2[Int, Int, Int]                                                           = A.i_.+(B.i_.s_).+(C.s_.i_).+(D.s_.i).Tx.myTxAttr
//      val e2: Tx_2[(String, Int), Int, Int]                                                 = A.i_.+(B.i_.s_).+(C.s_.i_).+(D.s.i).Tx.myTxAttr
//      val e2: Tx_3[Int, (String, Int), Int, Int]                                            = A.i_.+(B.i_.s_).+(C.s_.i).+(D.s.i).Tx.myTxAttr
//      val e2: Tx_3[(String, Int), (String, Int), Int, Int]                                  = A.i_.+(B.i_.s_).+(C.s.i).+(D.s.i).Tx.myTxAttr
//      val e2: Tx_4[String, (String, Int), (String, Int), Int, Int]                          = A.i_.+(B.i_.s).+(C.s.i).+(D.s.i).Tx.myTxAttr
//      val e2: Tx_4[(Int, String), (String, Int), (String, Int), Int, Int]                   = A.i_.+(B.i.s).+(C.s.i).+(D.s.i).Tx.myTxAttr
//      val e2: Tx_5[Int, (Int, String), (String, Int), (String, Int), Int, Int]              = A.i.+(B.i.s).+(C.s.i).+(D.s.i).Tx.myTxAttr
//      val e2: Tx_5[Int, (Int, String), (String, Int), String, Int, Int]                     = A.i.+(B.i.s).+(C.s.i).+(D.s.i_).Tx.myTxAttr
//      val e2: Tx_4[Int, (Int, String), (String, Int), Int, Int]                             = A.i.+(B.i.s).+(C.s.i).+(D.s_.i_).Tx.myTxAttr
//      val e2: Tx_4[Int, (Int, String), String, Int, Int]                                    = A.i.+(B.i.s).+(C.s.i_).+(D.s_.i_).Tx.myTxAttr
//      val e2: Tx_3[Int, (Int, String), Int, Int]                                            = A.i.+(B.i.s).+(C.s_.i_).+(D.s_.i_).Tx.myTxAttr
//      val e2: Tx_3[Int, Int, Int, Int]                                                      = A.i.+(B.i.s_).+(C.s_.i_).+(D.s_.i_).Tx.myTxAttr
//      val e2: Tx_2[Int, Int, Int]                                                           = A.i.+(B.i_.s_).+(C.s_.i_).+(D.s_.i_).Tx.myTxAttr
//      val e2: Tx_1[Int, Int]                                                                = A.i_.+(B.i_.s_).+(C.s_.i_).+(D.s_.i_).Tx.myTxAttr
//      val e2: Molecule_05[Int, (Int, String), (String, Int), (String, Int), String]         = A.i.+(B.i.s).+(C.s.i).+(D.s.i).Tx.apply(D.s)
//      val e2: Molecule_06[Int, (Int, String), (String, Int), (String, Int), String, String] = A.i.+(B.i.s).+(C.s.i).+(D.s.i).Tx.apply(D.s.s)

      for {
        _ <- A.i(1).s("a").+(B.i(2).s("b")).Tx(D.i(3).s("c")).save.transact
        _ <- (A.i.s + B.s.i).Tx(D.i).query.get.map(_ ==> List(((1, "a"), ("b", 2), 3)))
      } yield ()
    }

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
