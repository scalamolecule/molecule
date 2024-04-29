package molecule.document.mongodb

import java.util
import com.mongodb.client.MongoDatabase
import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
import molecule.boilerplate.api.expression.{ExprSeqMan_2, ExprSeqTac_1}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.{TestSuiteArray_mongodb, TestSuite_mongodb}
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.document.mongodb.AdhocJVM_mongodb.{int2, int3, int4}
import molecule.document.mongodb.facade.MongoConn_JVM
import molecule.document.mongodb.util.BsonUtils
import org.bson.conversions.Bson
import org.bson.{BsonBinarySubType, BsonDocument, BsonInt32, BsonObjectId}
import scala.collection.immutable.Set
import scala.concurrent.Future
import scala.util.Random

//object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {
object AdhocJVM_mongodb extends TestSuiteArray_mongodb with AggrUtils with BsonUtils {

  //  org.bson.types.Binary
  //  org.bson.types.Binary(BsonBinarySubType.BINARY, Array.empty[Byte])
  //  org.bson.types.Binary(Array.empty[Byte])

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      for {
        id2 <- Ns.intSeq(List(int1)).save.transact.map(_.id)
        _ <- Ns(id2).intSeq(List(int2)).update.i.transact
        _ <- Ns.intSeq.query.get.map(_.head ==> List(int2))

        id20 <- Ns.byteArray(Array(byte1)).save.transact.map(_.id)
        _ = println(id20)

        _ <- Ns(id20).byteArray(Array(byte2)).update.i.transact

        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte2))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        List(a, b, c, d, e, f) <- A.i.a1.Bb.*?(B.s_?.i_?).insert(
          (1, List()),
          (2, List((Some("a"), None))),
          (3, List((Some("b"), None), (Some("c"), None))),
          (4, List((Some("d"), Some(1)))),
          (5, List((Some("e"), Some(2)), (Some("f"), Some(3)))),
          (6, List((Some("g"), Some(4)), (Some("h"), None))),
        ).transact.map(_.ids)

        // Filter by A ids, update B values
        _ <- A(a, b, c, d, e, f).Bb.i(4).update.i.transact

        _ <- A.i.a1.Bb.*?(B.s_?.i).query.get.map(_ ==> List(
          (1, List((None, 4))), //                       ref + addition
          (2, List((Some("a"), 4))), //                  addition in 1 ref entity
          (3, List((Some("b"), 4), (Some("c"), 4))), //  addition in 2 ref entities
          (4, List((Some("d"), 4))), //                  update in 1 ref entity
          (5, List((Some("e"), 4), (Some("f"), 4))), //  update in 2 ref entities
          (6, List((Some("g"), 4), (Some("h"), 4))), //  update in one ref entity and addition in another
        ))


      } yield ()


    }

    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {

        _ <- Uniques.int(0).i(1).Ref.i(2).save.transact
        //        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((1, 2)))

        _ <- Uniques.int_(0).i(3).Ref.i(4).update.transact
        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((3, 4)))

      } yield ()
    }

    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //
    //        id <- Type.intSeq(Seq(4)).save.transact.map(_.id)
    //        _ <- Type(id).intSeq(Seq(1, 2, 2)).update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ValidationErrors(errorMap) =>
    //              errorMap.head._2 ==> Seq(
    //                s"""Type.intSeq with value `1` doesn't satisfy validation:
    //                   |_ > 3
    //                   |""".stripMargin,
    //                s"""Type.intSeq with value `2` doesn't satisfy validation:
    //                   |_ > 3
    //                   |""".stripMargin
    //              )
    //          }
    //
    //      } yield ()
    //    }
  }
}
