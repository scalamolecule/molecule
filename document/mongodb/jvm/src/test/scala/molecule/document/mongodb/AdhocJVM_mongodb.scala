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

        _ <- A.i(1).save.transact
//        _ <- A.i(2).B.s("b").save.transact
//        _ <- A.i(3).B.s("c").iSet(Set(1, 2)).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.B.iSet.add(2, 3).update.transact

        _ <- A.i.a1.B.iSet.query.get.map(_ ==> List(
          (1, Set(2, 3)), //    relationship to B created, B attribute added
//          (2, Set(2, 3)), //    B attribute added
//          (3, Set(1, 2, 3)), // B attribute updated (2 not added - already exists in Set)
        ))




//        _ <- A.i.OwnBb.*?(B.s_?.iSet_?).insert(
//          (1, List()),
////          (2, List((Some("a"), None))),
////          (3, List((Some("b"), None), (Some("c"), None))),
////          (4, List((Some("d"), Some(Set(1, 2))))),
////          (5, List((Some("e"), Some(Set(2, 3))), (Some("f"), Some(Set(3, 4))))),
////          (6, List((Some("g"), Some(Set(4, 5))), (Some("h"), None))),
//        ).i.transact
//
//        // Filter by A ids, update B values
//        _ <- A.i_.OwnBb.iSet.add(4, 5).update.transact
//
//        _ <- A.i.a1.OwnBb.*?(B.s_?.iSet).query.get.map(_ ==> List(
//          (1, List((None, Set(4, 5)))), //                                       ref + addition
////          (2, List((Some("a"), Set(4, 5)))), //                                  addition in 1 ref entity
////          (3, List((Some("b"), Set(4, 5)), (Some("c"), Set(4, 5)))), //          addition in 2 ref entities
////          (4, List((Some("d"), Set(1, 2, 4, 5)))), //                            update in 1 ref entity
////          (5, List((Some("e"), Set(2, 3, 4, 5)), (Some("f"), Set(3, 4, 5)))), // update in 2 ref entities
////          (6, List((Some("g"), Set(4, 5)), (Some("h"), Set(4, 5)))), //          update in one ref entity and addition in another
//        ))



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
