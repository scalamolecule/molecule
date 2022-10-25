package molecule.base.codeGeneration.extract

import java.nio.file.{Files, Paths}
import molecule.base.ast.SchemaAST._
import scala.annotation.tailrec
import scala.meta._

object DataModel2MetaSchema {
  def apply(path: String): MetaSchema = {
    val bytes     = Files.readAllBytes(Paths.get(path))
    val dataModel = new String(bytes, "UTF-8")
    new DataModel2MetaSchema(path, dataModel).schema
  }
  def apply(path: String, dataModel: String): MetaSchema = {
    new DataModel2MetaSchema(path, dataModel).schema
  }
}

class DataModel2MetaSchema(path: String, dataModel: String) {
  val tree = Input.VirtualFile(path, dataModel).parse[Source].get

  private val noMix = "Mixing prefixed and non-prefixed namespaces is not allowed."
  private def unexpectedCode(c: Tree) = throw new Exception(
    s"Unexpected DataModel code in file $path:\n" + c
  )

  private val (pkg, domain, maxArity, body) = tree.children.headOption match {
    case Some(q"""package $pkg.dataModel {
        ..$imports
        object $domain extends DataModel($maxArity) {
          ..$body
        }
      }""")    => (pkg.toString, domain.toString, maxArity.toString.toInt, body)
    case None  => unexpectedCode(tree)
    case other => unexpectedCode(other.get)
  }

  private def getNss(partPrefix: String, nss: Seq[Stat]): Seq[MetaNs] = nss.map {
    case q"trait $ns { ..$attrs }" => MetaNs(partPrefix + ns.toString, getAttrs(attrs))
    case q"object $o { ..$_ }"     => throw new Exception(noMix)
    case other                     => unexpectedCode(other)
  }

  private def getAttrs(attrs: Seq[Stat]): Seq[MetaAttr] = attrs.map {
    case q"val $attr = $defs" =>
      val a = attr.toString
      getAttr(a, defs, (Nil, None, None, None, MetaAttr(a, one, "")))._5
    case other                => unexpectedCode(other)
  }

  val reservedAttrNames = List(
    "a", "e", "v", "t", "tx", "txInstant", "op",
    "save", "insert", "update", "retract ",
    "self", "apply", "assert", "replace", "not", "contains", "k"
  )

  @tailrec
  private def getAttr(
    a: String,
    t: Tree,
    x: (List[String], Option[String], Option[String], Option[String], MetaAttr)
  ): (List[String], Option[String], Option[String], Option[String], MetaAttr) = {
    t match {
      case q"$prev.index"                   => getAttr(a, prev, ("index" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.noHistory"               => getAttr(a, prev, ("noHistory" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.uniqueIdentity"          => getAttr(a, prev, ("uniqueIdentity" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.unique"                  => getAttr(a, prev, ("unique" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.mandatory"               => getAttr(a, prev, ("mandatory" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.fulltext"                => getAttr(a, prev, ("fulltext" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.descr(${Lit.String(s)})" =>
        val descr = if (s.isEmpty)
          throw new Exception(s"Can't apply empty String as description option for attribute $a.")
        else if (s.contains("\""))
          throw new Exception(s"Description option for attribute $a can't contain quotes.")
        else
          Some(s)
        getAttr(a, prev, (x._1, descr, x._3, x._4, x._5))

      case q"$prev.alias(${Lit.String(s)})" => s match {
        case r"([a-zA-Z0-9]+)$alias" =>
          if (reservedAttrNames.contains(alias)) {
            throw new Exception(
              s"Alias `$alias` for attribute $a can't be any of the reserved molecule attribute names:\n  " +
                reservedAttrNames.mkString("\n  ")
            )
          } else {
            getAttr(a, prev, (x._1, x._2, Some(alias), x._4, x._5))
          }
        case other                   =>
          throw new Exception(s"Invalid alias for attribute $a: " + other)
      }

      case q"$prev.validation($lambda)" => getAttr(a, prev, (x._1, x._2, x._3, Some(lambda.toString()), x._5))

      case q"oneString"     => attr(x, one, "String")
      case q"oneChar"       => attr(x, one, "Char")
      case q"oneByte"       => attr(x, one, "Byte")
      case q"oneShort"      => attr(x, one, "Short")
      case q"oneInt"        => attr(x, one, "Int")
      case q"oneLong"       => attr(x, one, "Long")
      case q"oneFloat"      => attr(x, one, "Float")
      case q"oneDouble"     => attr(x, one, "Double")
      case q"oneBoolean"    => attr(x, one, "Boolean")
      case q"oneBigInt"     => attr(x, one, "BigInt")
      case q"oneBigDecimal" => attr(x, one, "BigDecimal")
      case q"oneDate"       => attr(x, one, "Date")
      case q"oneUUID"       => attr(x, one, "UUID")
      case q"oneURI"        => attr(x, one, "URI")

      case q"one[$refNs]"  => attr(x, one, "Long", Some(refNs.toString.replace('.', '_')))
      case q"many[$refNs]" => attr(x, set, "Long", Some(refNs.toString.replace('.', '_')))

      case q"setString"     => attr(x, set, "String")
      case q"setChar"       => attr(x, set, "Char")
      case q"setByte"       => attr(x, set, "Byte")
      case q"setShort"      => attr(x, set, "Short")
      case q"setInt"        => attr(x, set, "Int")
      case q"setLong"       => attr(x, set, "Long")
      case q"setFloat"      => attr(x, set, "Float")
      case q"setDouble"     => attr(x, set, "Double")
      case q"setBoolean"    => attr(x, set, "Boolean")
      case q"setBigInt"     => attr(x, set, "BigInt")
      case q"setBigDecimal" => attr(x, set, "BigDecimal")
      case q"setDate"       => attr(x, set, "Date")
      case q"setUUID"       => attr(x, set, "UUID")
      case q"setURI"        => attr(x, set, "URI")
      case other            => unexpectedCode(other)
    }
  }

  private def attr(
    acc: (List[String], Option[String], Option[String], Option[String], MetaAttr),
    card: Cardinality,
    tpe: String,
    refNs: Option[String] = None
  ): (List[String], Option[String], Option[String], Option[String], MetaAttr) = {
    (Nil, None, None, None, acc._5.copy(
      card = card,
      tpe = tpe,
      refNs = refNs,
      options = acc._1,
      descr = acc._2,
      alias = acc._3,
      validation = acc._4
    ))
  }

  private val schema: MetaSchema = {
    val parts = body.head match {
      case q"object $_ { ..$_ }" => body.map {
        case q"object $part { ..$nss }" => MetaPart(part.toString, getNss(part.toString + "_", nss))
        case q"trait $ns $template"     => throw new Exception(noMix)
      }
      case _                     => Seq(MetaPart("", getNss("", body)))
    }
    MetaSchema(pkg, domain, maxArity, parts)
  }
}

