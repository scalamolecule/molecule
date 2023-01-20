//package molecule.base
//
//import molecule.base.ast.SchemaAST.MetaSchema
//import molecule.base.codegen.extract.DataModel2MetaSchema
//import molecule.base.codegen.render.Dsl
//import molecule.base.util.RegexMatching
//import utest._
//
//trait CodeGenTesting extends TestSuite with RegexMatching {
//
//
//  def trimCodeStr(codeStr: String): String = {
//    codeStr.linesIterator.map {
//      case r"\s*" => ""
//      case other  => other
//    }.mkString("\n").trim
//  }
//
//
//  class Renderer(schema: MetaSchema) {
//    protected def _dsl(ns: String): String = {
//      val ns1 = schema.parts.flatMap(_.nss).find(_.ns == ns).getOrElse(
//        throw new Exception(s"Couldn't find namespace `$ns`")
//      )
//      Dsl(schema, ns1).get
//    }
//    def dslStr(ns: String): String = trimCodeStr(_dsl(ns))
//    def dslFirst: String = trimCodeStr(_dsl(schema.parts.head.nss.head.ns))
//    def dsl(ns: String): Unit = println(_dsl(ns))
//  }
//
//  object Renderer {
//    def apply(path: String) = new Renderer(DataModel2MetaSchema(path))
//    def apply(schema: MetaSchema) = new Renderer(schema)
//  }
//
//
//  object dataModel {
//    val projectRoot   = System.getProperty("user.dir")
//    val genericModels = projectRoot + "/molecule-boilerplate/src/main/scala/molecule/boilerplate/api/generic/dataModel/"
//    val customModels  = projectRoot + "/molecule-tests/shared/src/main/scala/moleculeTests/dataModels/"
//
//    def generic(ns: String) = Renderer(genericModels + ns + ".scala")
//
//    lazy val datom  = generic("Datom")
//    lazy val schema = generic("Schema")
//    lazy val log    = generic("Log")
//    lazy val aevt   = generic("AEVT")
//    lazy val avet   = generic("AVET")
//    lazy val eavt   = generic("EAVT")
//    lazy val vaet   = generic("VAET")
//
//    lazy val coreTest      = Renderer(customModels + "core/base/dataModel/CoreTest.scala")
//    lazy val bidirectional = Renderer(customModels + "core/bidirectionals/dataModel/Bidirectional.scala")
//    lazy val nested        = Renderer(customModels + "core/ref/dataModel/Nested.scala")
//    lazy val selfJoint     = Renderer(customModels + "core/ref/dataModel/SelfJoin.scala")
//    lazy val partitionTest = Renderer(customModels + "core/schemaDef/dataModel/PartitionTest.scala")
//    lazy val schema1       = Renderer(customModels + "core/schemaDef/dataModel/Schema1.scala")
//
//    lazy val aggregates    = Renderer(customModels + "examples/datomic/dayOfDatomic/dataModel/Aggregates.scala")
//    lazy val db            = Renderer(customModels + "examples/datomic/dayOfDatomic/dataModel/Db.scala")
//    lazy val graph         = Renderer(customModels + "examples/datomic/dayOfDatomic/dataModel/Graph.scala")
//    lazy val graph2        = Renderer(customModels + "examples/datomic/dayOfDatomic/dataModel/Graph2.scala")
//    lazy val productsOrder = Renderer(customModels + "examples/datomic/dayOfDatomic/dataModel/ProductsOrder.scala")
//    lazy val socialNews    = Renderer(customModels + "examples/datomic/dayOfDatomic/dataModel/SocialNews.scala")
//    lazy val mBrainz       = Renderer(customModels + "examples/datomic/mbrainz/dataModel/MBrainz.scala")
//    lazy val seattle       = Renderer(customModels + "examples/datomic/seattle/dataModel/Seattle.scala")
//
//    lazy val modernGraph1 = Renderer(customModels + "gremlin/gettingStarted/dataModel/ModernGraph1.scala")
//    lazy val modernGraph2 = Renderer(customModels + "gremlin/gettingStarted/dataModel/ModernGraph2.scala")
//  }
//
//  def dslFirst(dataModel: String, expectedCode: String): Unit = {
//    trimCodeStr(Renderer(DataModel2MetaSchema("", dataModel)).dslFirst) ==> expectedCode
//  }
//}
