//package molecule.base.parse
//
//import molecule.base.ast.SchemaAST._
//import molecule.base.codegen.extract.DataModel2MetaSchema
//import molecule.base.codegen.render.{Dsl, Dsl_Arities, Schema}
//import utest._
//import scala.meta._
//
//
//object RenderTest extends TestSuite {
//  val projectRoot = System.getProperty("user.dir")
//  val basePath    = projectRoot + "/base/src/test/scala/molecule/base/dataModel/"
//  val schema      = DataModel2MetaSchema(basePath + "A.scala")
//
//  override def tests: Tests = Tests {
//
////    "Schema" - {
////      schema ==> MetaSchema("molecule.base.parseFiles", "A", 3, Seq(
////        MetaPart("", Seq(
////          MetaNs("Ns", Seq(
////            MetaAttr("str", CardOne, "String", None, Nil, Some("foo"), None, None),
////            MetaAttr("int", CardOne, "Int", None, Seq("unique"), Some("bar"), None, Some("_ > 7")),
////            MetaAttr("ref", CardOne, "Long", Some("Ref1"), Nil, None, None, None))),
////
////          MetaNs("Ref1", Seq(
////            MetaAttr("str1", CardOne, "String", None, Nil, Some("foo"), None, None),
////            MetaAttr("int1", CardOne, "Int", None, Seq("unique"), Some("bar"), None, None)))))))
////    }
//
//
//    "Dsl" - {
//      Dsl_Arities(schema, schema.parts.head.nss(1), 1).get ==> "hi"
//    }
//
////    "Schema" - {
////      Schema(schema).get ==> "hej"
////    }
//  }
//}
