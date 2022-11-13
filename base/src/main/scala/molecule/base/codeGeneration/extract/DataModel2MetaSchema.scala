package molecule.base.codeGeneration.extract

import java.nio.file.{Files, Paths}
import molecule.base.ast.SchemaAST._
import molecule.base.util.exceptions.MoleculeException
import scala.annotation.tailrec
import scala.collection.mutable
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

  var backRefs = Map.empty[String, Seq[String]]

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
    case q"trait $nsTpe { ..$attrs }" =>
      val ns = nsTpe.toString
      if (ns.head.isLower)
        throw MoleculeException("Namespace traits have to start with upper case letter.")
      val metaAttrs  = getAttrs(ns, attrs)
      val backRefNss = backRefs.getOrElse(ns, Nil).distinct.sorted
      MetaNs(partPrefix + ns, metaAttrs, backRefNss)
    case q"object $o { ..$_ }"        => throw new Exception(noMix)
    case other                        => unexpectedCode(other)
  }

  private def getAttrs(ns: String, attrs: Seq[Stat]): Seq[MetaAttr] = attrs.map {
    case q"val $attr = $defs" =>
      val a = attr.toString
      getAttr(ns, a, defs, (Nil, None, None, None, MetaAttr(a, CardOne, "")))._5
    case other                => unexpectedCode(other)
  }

  val reservedAttrNames = List(
    "a", "e", "v", "t", "tx", "txInstant", "op",
    "save", "insert", "update", "retract",
    "self", "apply", "assert", "replace", "not", "contains", "k",
  )

  @tailrec
  private def getAttr(
    ns: String,
    a: String,
    t: Tree,
    x: (List[String], Option[String], Option[String], Option[String], MetaAttr)
  ): (List[String], Option[String], Option[String], Option[String], MetaAttr) = {
    t match {
      case q"$prev.index"                   => getAttr(ns, a, prev, ("index" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.noHistory"               => getAttr(ns, a, prev, ("noHistory" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.uniqueIdentity"          => getAttr(ns, a, prev, ("uniqueIdentity" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.unique"                  => getAttr(ns, a, prev, ("unique" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.mandatory"               => getAttr(ns, a, prev, ("mandatory" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.fulltext"                => getAttr(ns, a, prev, ("fulltext" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.descr(${Lit.String(s)})" =>
        val descr = if (s.isEmpty)
          throw new Exception(s"Can't apply empty String as description option for attribute $a.")
        else if (s.contains("\""))
          throw new Exception(s"Description option for attribute $a can't contain quotes.")
        else
          Some(s)
        getAttr(ns, a, prev, (x._1, descr, x._3, x._4, x._5))

      case q"$prev.alias(${Lit.String(s)})" => s match {
        case r"([a-zA-Z0-9]+)$alias" =>
          if (reservedAttrNames.contains(alias)) {
            throw new Exception(
              s"Alias `$alias` for attribute $a can't be any of the reserved molecule attribute names:\n  " +
                reservedAttrNames.mkString("\n  ")
            )
          } else {
            getAttr(ns, a, prev, (x._1, x._2, Some(alias), x._4, x._5))
          }
        case other                   =>
          throw new Exception(s"Invalid alias for attribute $a: " + other)
      }

      case q"$prev.validation($lambda)" => getAttr(ns, a, prev, (x._1, x._2, x._3, Some(lambda.toString()), x._5))

      case q"oneString"     => attr(x, CardOne, "String")
      case q"oneChar"       => attr(x, CardOne, "Char")
      case q"oneByte"       => attr(x, CardOne, "Byte")
      case q"oneShort"      => attr(x, CardOne, "Short")
      case q"oneInt"        => attr(x, CardOne, "Int")
      case q"oneLong"       => attr(x, CardOne, "Long")
      case q"oneFloat"      => attr(x, CardOne, "Float")
      case q"oneDouble"     => attr(x, CardOne, "Double")
      case q"oneBoolean"    => attr(x, CardOne, "Boolean")
      case q"oneBigInt"     => attr(x, CardOne, "BigInt")
      case q"oneBigDecimal" => attr(x, CardOne, "BigDecimal")
      case q"oneDate"       => attr(x, CardOne, "Date")
      case q"oneUUID"       => attr(x, CardOne, "UUID")
      case q"oneURI"        => attr(x, CardOne, "URI")

      case q"one[$refNs]" =>
        addBackRef(ns, refNs.toString)
        attr(x, CardOne, "Long", Some(refNs.toString.replace('.', '_')))

      case q"many[$refNs]" =>
        addBackRef(ns, refNs.toString)
        attr(x, CardSet, "Long", Some(refNs.toString.replace('.', '_')))

      case q"setString"     => attr(x, CardSet, "String")
      case q"setChar"       => attr(x, CardSet, "Char")
      case q"setByte"       => attr(x, CardSet, "Byte")
      case q"setShort"      => attr(x, CardSet, "Short")
      case q"setInt"        => attr(x, CardSet, "Int")
      case q"setLong"       => attr(x, CardSet, "Long")
      case q"setFloat"      => attr(x, CardSet, "Float")
      case q"setDouble"     => attr(x, CardSet, "Double")
      case q"setBoolean"    => attr(x, CardSet, "Boolean")
      case q"setBigInt"     => attr(x, CardSet, "BigInt")
      case q"setBigDecimal" => attr(x, CardSet, "BigDecimal")
      case q"setDate"       => attr(x, CardSet, "Date")
      case q"setUUID"       => attr(x, CardSet, "UUID")
      case q"setURI"        => attr(x, CardSet, "URI")
      case other            => unexpectedCode(other)
    }
  }

  private def addBackRef(ns: String, backRefNs: String): Unit = {
    val cur = backRefs.getOrElse(backRefNs, Nil)
    backRefs = backRefs + (backRefNs -> (cur :+ ns))
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

