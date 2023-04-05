package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._
import molecule.base.util.{BaseHelpers, RegexMatching}


case class Schema_Datomic(schema: MetaSchema) extends BaseHelpers with RegexMatching {

  val flatNss: Seq[MetaNs] = schema.parts.flatMap(_.nss)

  val datomicPartitions: String = {
    val parts = schema.parts.filterNot(_.part.isEmpty).map(_.part)
    if (parts.isEmpty) "\"\"" else {
      edn(parts.map { part =>
        s"""|        {:db/id      "$part"
            |         :db/ident   :$part}
            |        [:db/add :db.part/db :db.install/partition "$part"]""".stripMargin
      }.mkString("\n\n"))
    }
  }

  val datomicAliases: String = {
    val attrsWithAlias = flatNss.flatMap(_.attrs).filter(_.alias.nonEmpty)
    if (attrsWithAlias.isEmpty) "\"\"" else {
      edn(attrsWithAlias.map { a =>
        val (attr, alias) = (a.attr, a.alias.get)
        s"""|        {:db/id      "$alias"
            |         :db/ident   :$attr}""".stripMargin
      }.mkString("\n\n"))
    }
  }

  def datomicCardinality(a: MetaAttr): String = a.card match {
    case CardOne => "one"
    case CardSet => "many"
    case other   => throw new Exception("Yet unsupported cardinality: " + other)
  }

  def datomicType(a: MetaAttr): String = a.baseTpe match {
    case "String"                   => "string"
    case "Char"                     => "string"
    case "Byte"                     => "long"
    case "Short"                    => "long"
    case "Int"                      => "long"
    case "Long" if a.refNs.nonEmpty => "ref"
    case "Long"                     => "long"
    case "Float"                    => "float"
    case "Double"                   => "double"
    case "Boolean"                  => "boolean"
    case "BigInt"                   => "bigint"
    case "BigDecimal"               => "bigdec"
    case "Date"                     => "instant"
    case "UUID"                     => "uuid"
    case "URI"                      => "uri"
  }

  def attrStmts(ns: String, a: MetaAttr): String = {
    val mandatory = Seq(
      s""":db/ident         :$ns/${a.attr}""",
      s""":db/valueType     :db.type/${datomicType(a)}""",
      s""":db/cardinality   :db.cardinality/${datomicCardinality(a)}""",
      s""":db/index         true"""
    )
    val options   = a.options.flatMap {
      case "index"          => Seq(s""":db/index         true""")
      case "noHistory"      => Seq(s""":db/noHistory     true""")
      case "unique"         => Seq(s""":db/unique        :db.unique/value""")
      case "uniqueIdentity" => Seq(s""":db/unique        :db.unique/identity""")
      case "fulltext"       => Seq(s""":db/fulltext      true""")
      case "owner"          => Seq(s""":db/isComponent   true""")
      case _                => Nil
    }
    val descr     = a.description.fold(Seq.empty[String])(txt => Seq(s""":db/doc           "$txt""""))

    (mandatory ++ options ++ descr).distinct.mkString("\n         ")
  }

  def attrDefs(ns: MetaNs): String = ns.attrs
    .map(attrStmts(ns.ns, _))
    .mkString("{", "}\n\n        {", "}")

  val datomicSchema: String = edn(flatNss.map { ns =>
    val delimiter = "-" * (50 - ns.ns.length)
    s"""|        ;; ${ns.ns} $delimiter
        |
        |        ${attrDefs(ns)}""".stripMargin
  }.mkString("\n\n\n"))

  def edn(defs: String): String =
    s"""|
        |    \"\"\"
        |      [
        |$defs
        |      ]
        |    \"\"\"""".stripMargin

  def get: String =
    s"""|  val datomicPartitions: String = $datomicPartitions
        |
        |
        |  val datomicSchema: String = $datomicSchema
        |
        |
        |  val datomicAliases: String = $datomicAliases""".stripMargin
}
