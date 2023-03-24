package molecule.base.parse

import molecule.base.codegen.extract.DataModel2MetaSchema
import molecule.base.codegen.render.Dsl
import utest._


object RenderTest extends TestSuite {
  val projectRoot = System.getProperty("user.dir")
  //  lazy val scala2path       = projectRoot + "/base/src/test/scala-2/molecule/base/dataModel/"
  //  lazy val scala3path       = projectRoot + "/base/src/test/scala-3/molecule/base/dataModel/"
  //  lazy val schemaNss        = DataModel2MetaSchema(scala2path + "Nss.scala", "213")
  //  lazy val schemaPartitions = DataModel2MetaSchema(scala2path + "Partitions.scala", "213")
  //  lazy val schema3          = DataModel2MetaSchema(scala3path + "Nss3.scala", "3")

  lazy val basePath         = projectRoot + "/base/jvm/src/test/scala-2/molecule/base/dataModel/"
  lazy val schemaNss        = DataModel2MetaSchema(basePath + "Nss.scala", "213")
  lazy val validationNss    = DataModel2MetaSchema(basePath + "Validation.scala", "213")
  lazy val schemaPartitions = DataModel2MetaSchema(basePath + "Partitions.scala", "213")

  override def tests: Tests = Tests {

    "MetaSchema to dsl code" - {
      //      schemaNss.parts.head.nss(0) ==> "check"
      //      Dsl(schemaNss, "", schemaNss.parts.head.nss(2)).get ==> "check"

      Dsl(validationNss, "", validationNss.parts.head.nss(0)).get ==> "check"
      //      validationNss ==> "check"
    }
  }


}
