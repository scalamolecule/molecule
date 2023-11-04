package molecule.document.mongodb

import java.time.LocalTime
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import org.bson.BsonInvalidOperationException
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.s.insert(
          (1, "a"),
          (2, "b"),
          (3, "c"),
        ).transact

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
        //            |  "collection": "Ns",
        //            |  "$match": {
        //            |    "$and": [
        //            |      {
        //            |        "i": 1
        //            |      },
        //            |      {
        //            |        "s": "a"
        //            |      }
        //            |    ]
        //            |  },
        //            |  "$project": {
        //            |    "i": 1,
        //            |    "s": 1,
        //            |    "_id": 0
        //            |  }
        //            |}
        //            |""".stripMargin).map(_.foreach(println))

        //        _ <- rawQuery("""{"collection": "Ns"}""", true)

        _ <- Ns.i(1).s("a").query.get.map(_ ==> List((1, "a")))
      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


//        _ <- rawQuery(
//          """{
//            |  "collection": "A",
//            |  "$match": {
//            |    "$and": [
//            |      {
//            |        "s": {
//            |          "$ne": null
//            |        }
//            |      }
//            |    ]
//            |  }
//            |}
//            |""".stripMargin, true)

//        _ <- A.i(1).save.transact
//        _ <- A.i(2).query.i.get.map(_ ==> List(1))

        _ <- A.i.Bb.*(B.i).insert(List((7, List(1, 2, 3)))).transact
/*
db.subscriptions.aggregate([
  {
    $match: { _id: ObjectId('5f9a4f3170bd08b002498d44') },
  },
  {
    $unwind: {
      path: '$subscriptions',
    },
  },
  {
    $match: {
      'subscriptions.active.email': true,
    },
  },
  {
    $project: {
      activeUserId: '$subscriptions.user',
      _id: 0,
    },
  },
])
 */

//        _ <- rawQuery(
//          """{
//            |  "collection": "A",
//            |  "$unwind": "$bb",
//            |  "$match": {
//            |    "$and": [
//            |      {
//            |        "i": {
//            |          "$ne": null
//            |        }
//            |      },
//            |      {
//            |        "bb.i": 1
//            |      }
//            |    ]
//            |  },
//            |
//            |  "$project": {
//            |    "_id": 0,
//            |    "i": 1
//            |    "bb.i": 1
//            |  }
//            |}
//            |""".stripMargin, true)
/*

db.sales.aggregate( [
   {
      $project: {
         items: {
            $filter: {
               input: "$items",
               as: "item",
               cond: { $gte: [ "$$item.price", 100 ] }
            }
         }
      }
   }
] )
 */
        _ <- rawQuery(
          """{
            |  "collection": "A",
            |  "$match": {
            |    "$and": [
            |      {
            |        "i": {
            |          "$ne": null
            |        }
            |      },
            |      {
            |        "bb": {
            |          "$ne": []
            |        }
            |      }
            |    ]
            |  },
            |
            |  "$project": {
            |    "_id": 0,
            |    "i": 1
            |    "bb": {
            |      "$filter": {
            |        input: "$bb",
            |        cond: { "$eq": [ "$$this.i", 1 ] }
            |      }
            |    }
            |  }
            |}
            |""".stripMargin, true)

//        _ <- A.i_.Bb.*(B.i.a1).query.i.get.map(_ ==> List(List(1, 2, 3)))
        _ <- A.i_.Bb.*(B.i(1).a1).query.i.get.map(_ ==> List(List(1)))
//        _ <- A.i_.Bb.*(B.i(1, 2).a1).query.get.map(_ ==> List(List(1, 2)))
//        _ <- A.i_.Bb.*(B.i.not(1).a1).query.get.map(_ ==> List(List(2, 3)))
//        _ <- A.i_.Bb.*(B.i.not(1, 2).a1).query.get.map(_ ==> List(List(3)))
//        _ <- A.i_.Bb.*(B.i.<(2).a1).query.get.map(_ ==> List(List(1)))
//        _ <- A.i_.Bb.*(B.i.<=(2).a1).query.get.map(_ ==> List(List(1, 2)))
//        _ <- A.i_.Bb.*(B.i.>(2).a1).query.get.map(_ ==> List(List(3)))
//        _ <- A.i_.Bb.*(B.i.>=(2).a1).query.get.map(_ ==> List(List(2, 3)))
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
