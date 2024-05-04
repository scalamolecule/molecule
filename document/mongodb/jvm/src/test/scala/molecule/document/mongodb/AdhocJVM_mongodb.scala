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



        _ <- A.iSet.Bb.*?(B.s).insert(
//          (Set(1, 3), List()),
//          (Set(1, 2), List("a")),
          (Set(2, 3), List("b")),
//          (Set(3, 4), List("d", "e")),
        ).i.transact.map(_.ids)

        // Filter by B attribute, update A values
//        _ <- A.iSet.remove(3, 4).Bb.s_.update.i.transact

        // 2 A entities updated
        _ <- A.iSet_?.Bb.*(B.s.a1).query.i.get.map(_ ==> List(
//          (Some(Set(1, 2)), List("a")),
//          (Some(Set(2)), List("b", "c")), // 1 value removed
          (Some(Set(2, 3)), List("b")), // 1 value removed
//          (None, List("d", "e")), //         both values removed (refs to B still exist)
        ))

      } yield ()
    }





    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //
    //        _ <- Uniques.int(0).i(1).Ref.i(2).save.transact
    //        //        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((1, 2)))
    //
    //        _ <- Uniques.int_(0).i(3).Ref.i(4).update.transact
    //        _ <- Uniques.i.Ref.i.query.get.map(_ ==> List((3, 4)))
    //
    //      } yield ()
    //    }
    //
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
