package molecule.document.mongodb

import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.{TestSuite_mongodb, TestSuite_mongodb2}
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb2 extends TestSuite_mongodb2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.document.mongodb.dsl.Types2._
      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))

        _ <- Ns.i.insert(1, 2).transact
        _ <- Ns.i_(1).delete.transact
        _ <- Ns.i.query.get.map(_ ==> List(2))

        //        _ <- rawTransact(
        //          """{
        //            |  "collection": "Ns",
        //            |  "data": [
        //            |    {
        //            |      "i": 1,
        //            |      "s": "a"
        //            |    },
        //            |    {
        //            |      "i": 2,
        //            |      "s": "b"
        //            |    },
        //            |  ]
        //            |}
        //            |""".stripMargin)
        //        _ <- rawQuery(
        //          """{
        //            |  "collection": "A",
        //            |  "pipeline": [
        //            |    {
        //            |      "$match": {
        //            |        "$and": [
        //            |          {
        //            |            "i": {
        //            |              "$ne": null
        //            |            }
        //            |          }
        //            |        ]
        //            |      }
        //            |    },
        //            |  ]
        //            |}
        //            |""".stripMargin, false)//.map(println)

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.document.mongodb.dsl.Refs2._
      for {
        _ <- A.i(0).s("a").B.i(1)
          //          .s("b").Cc.i(22)
          //          ._B.C.i(2).s("c")
          //          ._B._A.Bb.i(11)
          .save.i.transact

        _ <- A.i.B.i.query.i.get.map(_ ==> List((0, 1)))
        //        _ <- A.i.B.i._A.s.query.get.map(_ ==> List((0, 1, "a")))
        //        _ <- A.i.B.i._A.Bb.i.query.get.map(_ ==> List((0, 1, 11)))
        //        _ <- A.i.B.i.C.i._B.s.query.get.map(_ ==> List((0, 1, 2, "b")))
        //        _ <- A.i.B.C.i.query.get.map(_ ==> List((0, 2)))
        //        _ <- A.i.B.C.i._B.i.query.get.map(_ ==> List((0, 2, 1)))
        //        _ <- A.i.B.i.C.i._B.Cc.i.query.get.map(_ ==> List((0, 1, 2, 22)))
        //        _ <- A.i.B.C.i._B.Cc.i.query.get.map(_ ==> List((0, 2, 22)))
        //        _ <- A.i.B.i.C.i._B.s._A.s.query.get.map(_ ==> List((0, 1, 2, "b", "a")))
        //        _ <- A.i.B.i.C.i._B._A.s.query.get.map(_ ==> List((0, 1, 2, "a")))
        //        _ <- A.i.B.C.i._B._A.s.query.get.map(_ ==> List((0, 2, "a")))
        //        _ <- A.i.B.i.C.i._B.s._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, "b", 11)))
        //        _ <- A.i.B.i.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 1, 2, 11)))
        //        _ <- A.i.B.C.i._B._A.Bb.i.query.get.map(_ ==> List((0, 2, 11)))
        //        _ <- A.B.C.s._B._A.Bb.i.query.get.map(_ ==> List(("c", 11)))
/*

========================================
SAVE:
AttrOneManInt("A", "i", Eq, Seq(0), None, None, Nil, Nil, None, None, Seq(0, 1))
AttrOneManString("A", "s", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 3))
Ref("A", "b", "B", CardOne, true, Seq(0, 6, 1))
AttrOneManInt("B", "i", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(1, 20))

{
  "action": "insert",
  "A": [
    {
      "i": 0,
      "s": "a",
      "b": {
        "i": 1
      }
    }
  ]
}
----------------------------------------

========================================
QUERY:
AttrOneManInt("A", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 1))
Ref("A", "b", "B", CardOne, true, Seq(0, 6, 1))
AttrOneManInt("B", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(1, 20))

{
  "collection": "A",
  "pipeline": [
    {
      "$match": {
        "$and": [
          {
            "i": {
              "$ne": null
            }
          },
          {
            "b": {
              "$ne": null
            }
          },
          {
            "b.i": {
              "$ne": null
            }
          }
        ]
      }
    },
    {
      "$project": {
        "i": 1,
        "b": {
          "i": 1
        },
        "_id": 0
      }
    }
  ]
}
----------------------------------------

RESULT ---------------------------------------------
{
  "i": 0,
  "b": {
    "i": 1
  }
}

{
  "i": 0,
  "b": {
    "i": 1
  }
}

 */

      } yield ()
    }



    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}
