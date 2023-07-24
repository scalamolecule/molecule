package molecule.datalog.datomic.test

import boopickle.Default._
import molecule.base.api.Schema
import molecule.base.ast.SchemaAST.{Card, MetaNs, MetaSchema}
import molecule.core.marshalling.Boopicklers._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object AdhocDatomic extends DatomicTestSuite {

//  object Schema extends Schema {
//    override val metaSchema       : MetaSchema                                                                                                                                                      = ???
//    override val nsMap            : Map[String, SchemaAST.MetaNs]                                                                                          = ???
//    override val attrMap          : Map[String, (SchemaAST.Card, String, Seq[String])] = ???
//    override val uniqueAttrs      : List[String]                                                                                                                                    = ???
//    override val datomicPartitions: String                                                                                                                                                                            = ???
//    override val datomicSchema    : String                                                                                                                                                                            = ???
//    override val datomicAliases   : String                                                                                                                                                                            = ???
//    override def sqlSchema(db: String): String = ???
//  }

  override lazy val tests = Tests {


    "types" - types { implicit conn =>

//      val a = MetaSchema("a", "b", 1, Nil)
//      val a = SchemaProxy(
//        MetaSchema("a", "b", 1, Nil),
//        Map.empty[String, MetaNs],
//        Map.empty[String, (Card, String, Seq[String])],
//        List.empty[String]
//      )
//      val a1 = Pickle.intoBytes(a)
//      val a2 = Unpickle.apply[Schema].fromBytes(a1)
//      a2 ==> a


      for {
        _ <- Ns.int(3).save.transact
        _ <- Ns.int.query.get.map(_ ==> List(3))


        //        _ <- Ns.i.insert(3, 3, 3, 2, 1, 3).transact
        //        _ <- Ns.i.insert(2, 1, 3).transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1, 2, 3))
        //        _ <- Ns.i.d1.query.get.map(_ ==> List(3, 2, 1))
        //        _ <- Ns.id.i.query.get.map(_ ==> List(1, 2, 3))


      } yield ()
    }


    //    "validation" - validation { implicit conn =>
    //      for {
    //        _ <- Type.int(3).long(3L).save.transact
    //
    //      } yield ()
    //    }


    //    "Compare with other attr value" - validation { implicit conn =>
    //      for {
    //        _ <- AttrValue.low(5).high(5).save.transact
    //          .map(_ ==> "Unexpected success").recover {
    //          case ValidationErrors(errorMap) =>
    //            errorMap ==>
    //              Map(
    //                "AttrValue.low" -> Seq(
    //                  s"""AttrValue.low with value `5` doesn't satisfy validation:
    //                     |  _ < high.value
    //                     |""".stripMargin
    //                )
    //              )
    //        }
    //      } yield ()
    //    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case ExecutionError(err) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, None, Nil, Nil, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
