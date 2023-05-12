package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._
import molecule.base.util.{BaseHelpers, RegexMatching}


case class Schema_Sql(schema: MetaSchema) extends BaseHelpers with RegexMatching {

  private val nss: Seq[MetaNs] = schema.parts.flatMap(_.nss)

  private def field(max: Int, a: MetaAttr): String = {
    val options = "" // todo
    //    val options = a.options.flatMap {
    //      case "index"          => Seq("")
    //      case "noHistory"      => Seq("")
    //      case "unique"         => Seq("")
    //      case "uniqueIdentity" => Seq("")
    //      case "fulltext"       => Seq("")
    //      case "owner"          => Seq("")
    //      case _                => Nil
    //    }.mkString(" ")
    val indent  = padS(max, a.attr) + " "
    val t       = a.baseTpe match {
      case "UUID" => "uuid"
      case "URI"  => "uri"
      case other  => "" + other.head.toLower + other.tail
    }
    val tpe     = "$" + (if (a.refNs.isEmpty) t else "ref")
    "       |  " + a.attr + indent + tpe + options
  }

  private def table(metaNs: MetaNs): String = {
    val ns     = metaNs.ns
    val max    = metaNs.attrs.map(a => a.attr.length).max.max(2)
    val fields = metaNs.attrs.map(a => field(max, a)).mkString(s",\n|")
    val id     = "id" + padS(max, "id") + " $id,"
    s"""       |CREATE TABLE $ns
       |       |(
       |       |  $id
       |$fields
       |       |);
       |       |"""
  }

  private val tables: String = nss.map(table).mkString(s"\n|")

  def get: String =
    s"""|/*
        |* AUTO-GENERATED schema boilerplate code
        |*
        |* To change:
        |* 1. edit data model file in `${schema.pkg}.dataModel/`
        |* 2. `sbt compile -Dmolecule=true`
        |*/
        |package ${schema.pkg}.schema
        |
        |import molecule.base.api.Schema
        |import molecule.base.ast.SchemaAST._
        |
        |
        |trait ${schema.domain}Schema_Sql extends Schema {
        |
        |  private val dbs = List(
        |    "h2",
        |    "mysql",
        |    "mssql",
        |    "oracle",
        |    "derby",
        |    "db2",
        |    // etc..
        |  )
        |
        |  private val types = List(
        |    //                    h2             mysql
        |    "String"     -> List("LONGVARCHAR", "LONGVARCHAR"),
        |    "Int"        -> List("INT"        , "INT"),
        |    "Long"       -> List("BIGINT"     , "BIGINT"),
        |    "Float"      -> List("FLOAT"      , "FLOAT"),
        |    "Double"     -> List("DOUBLE"     , "DOUBLE"),
        |    "Boolean"    -> List("BOOLEAN"    , "BOOLEAN"),
        |    "BigInt"     -> List("DECIMAL"    , "DECIMAL"),
        |    "BigDecimal" -> List("DECIMAL"    , "DECIMAL"),
        |    "Date"       -> List("DATE"       , "DATE"),
        |    "UUID"       -> List("UUID"       , "UUID"),
        |    "URI"        -> List("VARCHAR"    , "VARCHAR"),
        |    "Byte"       -> List("TINYINT"    , "TINYINT"),
        |    "Short"      -> List("SMALLINT"   , "SMALLINT"),
        |    "Char"       -> List("CHAR"       , "CHAR"),
        |  )
        |
        |  def sqlSchema(db: String) = {
        |    val dbIndex = dbs.indexOf(db, 0) match {
        |      case -1 => throw new Exception(
        |        s"Database `$$db` not found among databases with implemented jdbc drivers:\\n  " + dbs.mkString("\\n  ")
        |      )
        |      case i  => i
        |    }
        |    
        |    val tpe = types.map { case (scalaType, sqlTpes) => scalaType -> sqlTpes(dbIndex) }.toMap
        |    val id  = "BIGINT AUTO_INCREMENT PRIMARY KEY"
        |
        |    lazy val string     = tpe("String")
        |    lazy val int        = tpe("Int")
        |    lazy val long       = tpe("Long")
        |    lazy val float      = tpe("Float")
        |    lazy val double     = tpe("Double")
        |    lazy val boolean    = tpe("Boolean")
        |    lazy val bigInt     = tpe("BigInt")
        |    lazy val bigDecimal = tpe("BigDecimal")
        |    lazy val date       = tpe("Date")
        |    lazy val uuid       = tpe("UUID")
        |    lazy val uri        = tpe("URI")
        |    lazy val byte       = tpe("Byte")
        |    lazy val short      = tpe("Short")
        |    lazy val char       = tpe("Char")
        |
        |    lazy val ref = long // + " FK"
        |
        |    s\"\"\"
        |$tables\"\"\".stripMargin
        |  }
        |}""".stripMargin
}
