package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._
import molecule.base.util.{BaseHelpers, RegexMatching}


case class Schema_Sql(schema: MetaSchema) extends BaseHelpers with RegexMatching {

  private val nss: Seq[MetaNs] = schema.parts.flatMap(_.nss)

  private def field(max: Int, a: MetaAttr): String = {
    val indent  = padS(max, a.attr) + " "
    val t       = a.baseTpe match {
      case "UUID" => "uuid"
      case "URI"  => "uri"
      case other  => "" + other.head.toLower + other.tail
    }
    val options = a.baseTpe match {
      case "BigDecimal" => if (a.options.isEmpty) {
        // Default precision and modest scale
        // todo: make default configurable
        "(65535, 25)"
      } else {
        a.options.flatMap {
          case r"(\d+)$precision,(\d+)$scale" => Some(s"($precision, $scale)")
          case _                              => None
        }.mkString("")
      }
      case _            => ""
    }
    val tpe     = "$" + (if (a.refNs.isEmpty) t else "ref")
    "       |  " + a.attr + indent + tpe + options
  }

  private def table(metaNs: MetaNs): Seq[String] = {
    val ns     = metaNs.ns
    val attrs  = metaNs.attrs.filterNot(a => a.card == CardSet && a.refNs.nonEmpty)
    val max    = attrs.map(a => a.attr.length).max.max(2)
    val fields = attrs.map(a => field(max, a)).mkString(s",\n|")
    val id     = "id" + padS(max, "id") + " $id,"
    val table  =
      s"""       |CREATE TABLE $ns (
         |       |  $id
         |$fields
         |       |);
         |       |"""

    val joinTables = metaNs.attrs.collect {
      case MetaAttr(refAttr, CardSet, _, Some(refNs), _, _, _, _, _, _) =>
        val (id1, id2) = if (ns == refNs) ("1_id", "2_id") else ("id", "id")
        val (l1, l2)   = (ns.length, refNs.length)
        val (p1, p2)   = if (l1 > l2) ("", " " * (l1 - l2)) else (" " * (l2 - l1), "")
        s"""       |CREATE TABLE ${ns}_${refAttr}_$refNs (
           |       |  ${ns}_$id1$p1 BIGINT,
           |       |  ${refNs}_$id2$p2 BIGINT
           |       |);
           |       |"""
    }

    table +: joinTables
  }

  private val tables: String = nss.flatMap(table).mkString(s"\n|")

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
        |    "Float"      -> List("DOUBLE"     , "DOUBLE"),
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
        |    lazy val ref = long
        |
        |    s\"\"\"
        |$tables\"\"\".stripMargin
        |  }
        |}""".stripMargin
}
