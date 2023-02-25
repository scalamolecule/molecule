package molecule.base.parse

import molecule.base.codegen.extract.DataModel2MetaSchema
import molecule.base.codegen.render.Schema
import utest._


object RenderTest extends TestSuite {
  val projectRoot = System.getProperty("user.dir")
  //  lazy val scala2path       = projectRoot + "/base/src/test/scala-2/molecule/base/dataModel/"
  //  lazy val scala3path       = projectRoot + "/base/src/test/scala-3/molecule/base/dataModel/"
  //  lazy val schemaNss        = DataModel2MetaSchema(scala2path + "Nss.scala", "213")
  //  lazy val schemaPartitions = DataModel2MetaSchema(scala2path + "Partitions.scala", "213")
  //  lazy val schema3          = DataModel2MetaSchema(scala3path + "Nss3.scala", "3")


  lazy val basePath         = projectRoot + "/base/src/test/scala-2/molecule/base/dataModel/"
  lazy val schemaNss        = DataModel2MetaSchema(basePath + "Nss.scala", "213")
  lazy val schemaPartitions = DataModel2MetaSchema(basePath + "Partitions.scala", "213")


  override def tests: Tests = Tests {

    //    "Schema" - {
    //      schema ==> MetaSchema("molecule.base.parseFiles", "A", 3, Seq(
    //        MetaPart("", Seq(
    //          MetaNs("Ns", Seq(
    //            MetaAttr("str", CardOne, "String", None, Nil, Some("foo"), None, None),
    //            MetaAttr("int", CardOne, "Int", None, Seq("unique"), Some("bar"), None, Some("_ > 7")),
    //            MetaAttr("ref", CardOne, "Long", Some("Ref1"), Nil, None, None, None))),
    //
    //          MetaNs("Ref1", Seq(
    //            MetaAttr("str1", CardOne, "String", None, Nil, Some("foo"), None, None),
    //            MetaAttr("int1", CardOne, "Int", None, Seq("unique"), Some("bar"), None, None)))))))
    //    }
    //
    //
    //    "Dsl" - {
    //      Dsl_Arities(schema, schema.parts.head.nss(1), 1).get ==> "hi"
    //    }

    "Schema" - {
//      Schema(schemaNss).get ==> "hej"
//      Schema(schemaPartitions).get ==> "hej"
    }
  }
}