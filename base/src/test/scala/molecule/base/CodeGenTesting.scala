package molecule.base

import java.io.File
import molecule.base.codeGeneration.parseDataModel.DataModelParser
import molecule.base.util.RegexMatching
import molecule.base.codeGeneration.parseDataModel.parserAST.ParseModel
import molecule.base.codeGeneration.renderDsl.{NsArity, NsBase}
import utest._
import scala.io.Source

trait CodeGenTesting extends TestSuite with RegexMatching {


  def trimCodeStr(codeStr: String): String = {
    codeStr.linesIterator.map {
      case r"\s*" => ""
      case other  => other
    }.mkString("\n").trim
  }

  private def getParseModel(path: String): ParseModel = {
    val dataModelFile       = new File(path)
    val dataModelFileSource = Source.fromFile(dataModelFile)
    val parseModel          = DataModelParser(
      dataModelFile.getName,
      dataModelFileSource.getLines().toList,
      true
    ).parse
    dataModelFileSource.close()
    //    println(parseModel)
    parseModel
  }

  implicit class parseModel2executor(parseModel: ParseModel) {
    protected def _nsBase(ns: String): String =
      NsBase(parseModel, parseModel.nss.find(_.ns == ns).get).get

    protected def _nsArity(ns: String, arity: Int): String =
      NsArity(parseModel, parseModel.nss.find(_.ns == ns).get, arity).get

    def nsBaseStr(ns: String): String = trimCodeStr(_nsBase(ns))
    def nsArityStr(ns: String, arity: Int): String = trimCodeStr(_nsArity(ns, arity))

    def nsBase(ns: String): Unit = println(_nsBase(ns))
    def nsArity(ns: String, arity: Int): Unit = println(_nsArity(ns, arity))
  }


  object dataModel {
    val projectRoot   = System.getProperty("user.dir")
    val genericModels = projectRoot + "/molecule-boilerplate/src/main/scala/molecule/boilerplate/api/generic/dataModel/"
    val customModels  = projectRoot + "/molecule-tests/shared/src/main/scala/moleculeTests/dataModels/"

    case class generic(ns: String) {
      val parseModel = getParseModel(genericModels + ns + ".scala")
      val printer    = parseModel2executor(parseModel)
      def nsBase = printer.nsBase(ns)
      def nsArity(arity: Int) = printer.nsArity(ns, arity)
    }

    lazy val datom  = generic("Datom")
    lazy val schema = generic("Schema")
    lazy val log    = generic("Log")
    lazy val aevt   = generic("AEVT")
    lazy val avet   = generic("AVET")
    lazy val eavt   = generic("EAVT")
    lazy val vaet   = generic("VAET")

    lazy val coreTest      = getParseModel(customModels + "core/base/dataModel/CoreTest.scala")
    lazy val bidirectional = getParseModel(customModels + "core/bidirectionals/dataModel/Bidirectional.scala")
    lazy val nested        = getParseModel(customModels + "core/ref/dataModel/Nested.scala")
    lazy val selfJoint     = getParseModel(customModels + "core/ref/dataModel/SelfJoin.scala")
    lazy val partitionTest = getParseModel(customModels + "core/schemaDef/dataModel/PartitionTest.scala")
    lazy val schema1       = getParseModel(customModels + "core/schemaDef/dataModel/Schema1.scala")

    lazy val aggregates    = getParseModel(customModels + "examples/datomic/dayOfDatomic/dataModel/Aggregates.scala")
    lazy val db            = getParseModel(customModels + "examples/datomic/dayOfDatomic/dataModel/Db.scala")
    lazy val graph         = getParseModel(customModels + "examples/datomic/dayOfDatomic/dataModel/Graph.scala")
    lazy val graph2        = getParseModel(customModels + "examples/datomic/dayOfDatomic/dataModel/Graph2.scala")
    lazy val productsOrder = getParseModel(customModels + "examples/datomic/dayOfDatomic/dataModel/ProductsOrder.scala")
    lazy val socialNews    = getParseModel(customModels + "examples/datomic/dayOfDatomic/dataModel/SocialNews.scala")
    lazy val mBrainz       = getParseModel(customModels + "examples/datomic/mbrainz/dataModel/MBrainz.scala")
    lazy val seattle       = getParseModel(customModels + "examples/datomic/seattle/dataModel/Seattle.scala")

    lazy val modernGraph1 = getParseModel(customModels + "gremlin/gettingStarted/dataModel/ModernGraph1.scala")
    lazy val modernGraph2 = getParseModel(customModels + "gremlin/gettingStarted/dataModel/ModernGraph2.scala")
  }

  def nsBase(dataModel: String, expectedCode: String): Unit = {
    val parseModel = DataModelParser("", dataModel.linesIterator.toList, true).parse
    trimCodeStr(NsBase(parseModel, parseModel.nss.head).get) ==> expectedCode
  }

  def nsArity(arity: Int, dataModel: String, expectedCode: String): Unit = {
    val parseModel = DataModelParser("", dataModel.linesIterator.toList, true).parse
    trimCodeStr(NsArity(parseModel, parseModel.nss.head, arity).get) ==> expectedCode
  }
}
