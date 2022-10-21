package molecule.base.parse

import molecule.base.ast.schemaAST._
import molecule.base.codeGeneration.extract.Model2MetaSchema
import utest._
import scala.meta._


object ScalaMetaTest extends TestSuite {

  val projectRoot = System.getProperty("user.dir")
  val basePath    = projectRoot + "/base/src/test/scala/molecule/base/parseFiles/"

  val path        = java.nio.file.Paths.get(basePath, "A.scala")
  val bytes       = java.nio.file.Files.readAllBytes(path)
  val text        = new String(bytes, "UTF-8")
  val input       = Input.VirtualFile(path.toString, text)
  val exampleTree = input.parse[Source].get

  val x = MetaSchema("molecule.base.parseFiles", "A", 3, Seq(
    MetaPart("", Seq(
      MetaNs("Ns", Seq(
        MetaAttr("str", 1, "String", None, Nil, Some("foo"), None, None),
        MetaAttr("int", 1, "Int", None, Seq("unique"), Some("bar"), None, Some("_ > 7")))),

      MetaNs("Ref1", Seq(
        MetaAttr("str1", 1, "String", None, Nil, Some("foo"), None, None),
        MetaAttr("int1", 1, "Int", None, Seq("unique"), Some("bar"), None, None)))))))



  override def tests: Tests = Tests {

    "First" - {


      val schema = Model2MetaSchema(basePath + "A.scala")


    }
  }
}
