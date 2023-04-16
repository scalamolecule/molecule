package molecule.datomic.test

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions
//import molecule.boilerplate.ast.Model._
import molecule.base.error.ModelError


object AdhocJVM extends DatomicTestSuite {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      for {
//        _ <- Ns.i.insert(1).transact



        _ <- Ns.i.int.insert(
          (1, 2),
          (2, 2),
          (3, 2),
        ).transact
//        a = AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None)
//        b = AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None)
//        _ = a ==> b
//
//        _ <- Ns.i.<(7).query.inspect
//        _ <- Ns.i.int.query.inspect
//        _ <- Ns.i.int.query.get.map(x => x ==> List((2, 2)))
//        _ <- Ns.i.<(2).int.query.get.map(_ ==> List((1, 2)))
////        _ <- Ns.i.Ref.int.query.inspect
//        _ = {
//          println(datomic.Peer.q(
//            """[:find  ?b
//              | :in    $ ?b1
//              | :where [(< ?b ?b1)]
//              |        [?a :Ns/i ?b]
//              |        ]""".stripMargin,
//            conn.db, 2
//          ))
//          println(datomic.Peer.q(
//            """[:find  ?c ?a
//              | :where
//              | [?b :Ns/i ?c]
//              | [(<= ?c ?a)]
//              | [?b :Ns/int ?a]
//              |        ]""".stripMargin,
//            conn.db
//          ))
//
//        }
//        _ <- Ns.i.<(Ns.int).query.inspect
        _ <- Ns.i.<(Ns.int_).query.inspect



        _ <- Ns.i.<(Ns.int.<(Ref.int_)).query.get
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==> "Nested expression attributes not allowed in Ns.i"

        }

//        _ <- Ns.i.<(Ns.int).query.get.map(_ ==> List((1, 2)))
        _ <- Ns.i.<(Ns.int_).query.get.map(_ ==> List(1))

        _ <- Ns.i.<(Other.i).query.get
          .map(_ ==> "Unexpected success").recover {
          case ModelError(error) =>
            error ==>
              """Please add missing expression attributes:
                |  Other.i""".stripMargin
        }


        _ <- Ns.i.Ref.int.insert(
          (1, 3),
          (2, 3),
          (3, 3),
        ).transact


        _ <- Ns.i.<(Ref.int).a1.Ref.int.query.inspect
        _ <- Ns.i.<(Ref.int).a1.Ref.int.query.get.map(_ ==> List((1, 3), (2, 3)))






//        _ <- Ns.i.<(Ns.int(sum)).query.get.map(_ ==> List())
//        _ <- Ns.i.<(Ns.string(count)).query.get.map(_ ==> List())
//        _ <- Ns.ints.has(Ns.int(max(3))).query.get.map(_ ==> List())







        //        _ <- Ns.int_.>(Ns.i_).string.query.get.map(_ ==> List())
        //        _ <- Ns.int_.>(Ns.i).string.query.get.map(_ ==> List())
        //
//        _ = {
//
//          val a1: Ns_1[Int, Int] with ExprOneMan_1[Int, Int, Ns_1, Ns_2]                             = Ns.int
//          val a2: Ns_2[Int, Float, Float] with ExprOneMan_2[Int, Float, Float, Ns_2, Ns_3]           = Ns.int.float
//
//          val a3: Ns_1[Int, Int] with SortAttrs_1[Int, Int, Ns_1]                                    = Ns.int.<(7)
//          val b3: Ns_1[Int, Int] with SortAttrs_1[Int, Int, Ns_1]                                    = Ns.int.<(Ns.i_)
//
//          val a4: Ns_2[Int, Float, Float] with ExprOneMan_2[Int, Float, Float, Ns_2, Ns_3]           = Ns.int.<(7).float
//          val b4: Ns_2[Int, Float, Float] with ExprOneMan_2[Int, Float, Float, Ns_2, Ns_3]           = Ns.int.<(Ns.i_).float
//
//          val a5: Ns_2[Int, Int, Int] with SortAttrs_2[Int, Int, Int, Ns_2]                          = Ns.int.i.<(7)
//          val b5: Ns_2[Int, Int, Int] with SortAttrs_2[Int, Int, Int, Ns_2]                          = Ns.int.<(Ns.i)
//
//          val a6: Ns_3[Int, Int, Float, Float] with ExprOneMan_3[Int, Int, Float, Float, Ns_3, Ns_4] = Ns.int.i.<(7).float
//          val b6: Ns_3[Int, Int, Float, Float] with ExprOneMan_3[Int, Int, Float, Float, Ns_3, Ns_4] = Ns.int.<(Ns.i).float
//        }
//
////
//        _ <- Ns.string.long.float.int.<(Ns.i_).query.get.map(_ ==> List())
//        _ <- Ns.string.long.float.int.<(Ns.i).query.get.map(_ ==> List())
//
//        _ <- Ns.string.long.float.double.int.<(Ns.i_).query.get.map(_ ==> List())
//        _ <- Ns.string.long.float.double.int_.<(Ns.i).query.get.map(_ ==> List())
////        _ <- Ns.string.long.float.double.int.<(Ns.i).query.get.map(_ ==> List())
//
////        _ <- Ns.int.string.long.float.<(Ns.i).query.get.map(_ ==> List())
////        _ <- Ns.int.string.<(Ns.i).query.get.map(_ ==> List())
//        _ <- Ns.int_.<(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int_.<(Ns.i.a1).long.Ref.i.query.get.map(_ ==> List())
//
//        _ <- Ns.int.<(Ns.i_).a1.long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.<(Ns.i.a2).a1.long.Ref.i.query.get.map(_ ==> List())
//
//        _ <- Ns.int_?.<(Ns.i_).a1.long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int_?.<(Ns.i.a2).a1.long.Ref.i.query.get.map(_ ==> List())


//        _ <- Ns.ints_.<(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.ints_.<(Ns.i.a1).long.Ref.i.query.get.map(_ ==> List())
//
//        _ <- Ns.ints.<(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.ints.<(Ns.i.a1).long.Ref.i.query.get.map(_ ==> List())
//
////        _ <- Ns.ints.<(Set(2)).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.ints.<(Ns.ints_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.ints.<(Ns.ints).long.Ref.i.query.get.map(_ ==> List())
//
//
//
//        _ <- Ns.ints_?.<(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.ints_?.<(Ns.i.a1).long.Ref.i.query.get.map(_ ==> List())
//
//        _ <- Ns.ints_?.<(Ns.ints_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.ints_?.<(Ns.ints).long.Ref.i.query.get.map(_ ==> List())





//        _ <- Ns.int.==(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//
//        _ <- Ns.int(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.not(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.<(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.<=(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.>(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.>=(Ns.i).long.Ref.i.query.get.map(_ ==> List())
//
//        _ <- Ns.int(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.not(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.<(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.<=(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.>(Ns.i_).long.Ref.i.query.get.map(_ ==> List())
//        _ <- Ns.int.>=(Ns.i_).long.Ref.i.query.get.map(_ ==> List())

//        _ <- Ns.int.>(Ref.i).long.Ref.i.query.get.map(_ ==> List())


//        _ <- Ns.int.>(Ref.i).long.Ref.i.query.get.map(_ ==> List())

//        _ <- Ns.string.>(Ns.i_).long.query.get.map(_ ==> List())

        //        _ <- Ns.int_.>(Ns.i).query.get.map(_ ==> List())
        //        _ <- Ns.int_.>(Ns.i_).string.query.get.map(_ ==> List())
        //
        //        _ <- Ns.i.int.>(Ns.i).query.get.map(_ ==> List())
        //        _ <- Ns.i.string.>(Ns.string).query.get.map(_ ==> List())
        //        _ <- Ns.i.uuid.>(Ns.uuid).query.get.map(_ ==> List())

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


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- (R1.i(1).s("a") + R2.i(2).s("b").Tx.apply(R3.i(3).s("c"))).save.transact
    //
    //        _ <- (R1.i_ + R2.i.s.Tx(R3.i.s)).query.get.map(_ ==> List(((2, "b"), 3, "c")))
    //
    //      } yield ()
    //
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


    //    "one" - typesOne { implicit conn =>
    //      val a = (1, float1)
    //      val b = (2, float2)
    //      val c = (3, float3)
    //      One.n.float.insert(List(a, b, c)).transact
    //
    //      // Find all attribute values
    //      One.n.a1.float.query.get ==> List(a, b, c)
    //
    //      One.n.a1.float(float1).query.get ==> List(a)
    //
    //    }
  }
}
