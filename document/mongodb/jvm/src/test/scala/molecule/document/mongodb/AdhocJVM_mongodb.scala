package molecule.document.mongodb

import java.time.LocalTime
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
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
        ).i.transact

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
        // Two entities to be referenced
        List(b1, b2) <- B.i.insert(1, 2).transact.map(_.ids)

        // Reference Set of entities
        _ <- A.i(0).bb(Set(b1, b2)).save.transact

        // Saving individual ref ids (not in a Set) is not allowed
        _ <- A.i(0).bb(b1, b2).save.transact
          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
            err ==> "Can only save one Set of values for Set attribute `A.bb`. " +
              s"Found: Set($b1), Set($b2)"
          }

        // Referencing namespace attributes repeat for each referenced entity
        _ <- A.i.Bb.i.a1.query.get.map(_ ==> List(
          (0, 1),
          (0, 2), // 0 is repeated
        ))

        // Card many ref attributes return Set of ref ids
        _ <- A.i.bb.query.get.map(_ ==> List((0, Set(b1, b2))))
      } yield ()


      if (database == "MongoDB") {
        // Can't query for non-existing ids of embedded documents in MongoDB
        for {
          // Card one ref attr
          _ <- A.i.b.query.get
            .map(_ ==> "Unexpected success")
            .recover { case ModelError(err) =>
              err ==> "Can't query for non-existing ids of embedded documents in MongoDB."
            }

          // Card many ref attr
          _ <- A.i.bb.query.get
            .map(_ ==> "Unexpected success")
            .recover { case ModelError(err) =>
              err ==> "Can't query for non-existing set of ids of embedded documents in MongoDB."
            }
        } yield ()

      } else {
        for {
          // Two entities to be referenced
          List(b1, b2) <- B.i.insert(1, 2).transact.map(_.ids)

          // Reference Set of entities
          _ <- A.i(0).bb(Set(b1, b2)).save.transact

          // Saving individual ref ids (not in a Set) is not allowed
          _ <- A.i(0).bb(b1, b2).save.transact
            .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
              err ==> "Can only save one Set of values for Set attribute `A.bb`. " +
                s"Found: Set($b1), Set($b2)"
            }

          // Referencing namespace attributes repeat for each referenced entity
          _ <- A.i.Bb.i.a1.query.get.map(_ ==> List(
            (0, 1),
            (0, 2), // 0 is repeated
          ))

          // Card many ref attributes return Set of ref ids
          _ <- A.i.bb.query.get.map(_ ==> List((0, Set(b1, b2))))
        } yield ()
      }
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
