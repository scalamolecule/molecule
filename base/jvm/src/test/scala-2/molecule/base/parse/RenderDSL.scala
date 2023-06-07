package molecule.base.parse

import molecule.base.codegen.extract.DataModel2MetaSchema
import molecule.base.codegen.render._
import utest._


object RenderDSL extends TestSuite {
  val projectRoot = System.getProperty("user.dir")
  //  lazy val scala2path       = projectRoot + "/base/src/test/scala-2/molecule/base/dataModel/"
  //  lazy val scala3path       = projectRoot + "/base/src/test/scala-3/molecule/base/dataModel/"
  //  lazy val schemaPartitions = DataModel2MetaSchema(scala2path + "Partitions.scala", "213")
  //  lazy val schema3          = DataModel2MetaSchema(scala3path + "Nss3.scala", "3")

  lazy val basePath         = projectRoot + "/base/jvm/src/test/scala-2/molecule/base/dataModel/"
  lazy val typesNss         = DataModel2MetaSchema(basePath + "Types.scala", "213")
  lazy val refsNss          = DataModel2MetaSchema(basePath + "Refs.scala", "213")
  lazy val validationNss    = DataModel2MetaSchema(basePath + "Validation.scala", "213")
  lazy val schemaPartitions = DataModel2MetaSchema(basePath + "Partitions.scala", "213")

  override def tests: Tests = Tests {

    "DSL" - {
      //      schemaNss.parts.head.nss(0) ==> "check"
      //      Dsl(schemaNss, "", schemaNss.parts.head.nss(2)).get ==> "check"
      //
      //      Dsl(typesNss, "", typesNss.parts.head.nss(0)).get ==> "check"
      //      Dsl(validationNss, "", validationNss.parts.head.nss(4)).get ==> "check"
      //
      //      validationNss ==> "check"
      //      validationNss.parts.head.nss(11) ==> "check"
      //      validationNss.parts.head.nss(12) ==> "check"
      //      validationNss.attrMap() ==> "check"
      //      validationNss.parts.head ==> "check"


//      Dsl(typesNss, "", typesNss.parts.head.nss(0)).get ==> "check"
      //      Dsl(refsNss, "", refsNss.parts.head.nss(0)).get ==> "check"

      //      Schema(typesNss).get ==> "check"
      //      Schema_Datomic(typesNss).get ==> "check"
            Schema_Sql(typesNss).get ==> "check"
      //      Schema_Sql(refsNss).get ==> "check"
    }
  }
}
