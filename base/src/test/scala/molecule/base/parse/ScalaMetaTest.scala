package molecule.base.parse

import molecule.base.ast.SchemaAST._
import molecule.base.codeGeneration.extract.DataModel2MetaSchema
import molecule.base.codeGeneration.render.{Dsl, Schema}
import utest._
import scala.meta._


object ScalaMetaTest extends TestSuite {
  val projectRoot = System.getProperty("user.dir")
  val basePath    = projectRoot + "/base/src/test/scala/molecule/base/parseFiles/"
  val schema      = DataModel2MetaSchema(basePath + "A.scala")

  override def tests: Tests = Tests {

//    "Schema" - {
//      schema ==> MetaSchema("molecule.base.parseFiles", "A", 3, Seq(
//        MetaPart("", Seq(
//          MetaNs("Ns", Seq(
//            MetaAttr("str", one, "String", None, Nil, Some("foo"), None, None),
//            MetaAttr("int", one, "Int", None, Seq("unique"), Some("bar"), None, Some("_ > 7")),
//            MetaAttr("ref", one, "Long", Some("Ref1"), Nil, None, None, None))),
//
//          MetaNs("Ref1", Seq(
//            MetaAttr("str1", one, "String", None, Nil, Some("foo"), None, None),
//            MetaAttr("int1", one, "Int", None, Seq("unique"), Some("bar"), None, None)))))))
//    }
//
//
//    "Dsl" - {
//      MetaSchema2Dsl(schema, schema.parts.head.nss.head).get ==> "hej"
//    }

    "Schema" - {
      Schema(schema).get ==> "hej"
    }
  }
}
