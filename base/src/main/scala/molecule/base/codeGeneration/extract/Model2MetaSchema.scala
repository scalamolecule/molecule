package molecule.base.codeGeneration.extract

import java.nio.file.{Files, Paths}
import molecule.base.ast.schemaAST._
import scala.annotation.tailrec
import scala.meta._

object Model2MetaSchema {
  def apply(path: String): Model2MetaSchema = {
    val bytes = Files.readAllBytes(Paths.get(path))
    val text  = new String(bytes, "UTF-8")
    new Model2MetaSchema(path, text)
  }
}

class Model2MetaSchema(path: String, source: String) {
  val tree = Input.VirtualFile(path, source).parse[Source].get

  private val noMix = "Mixing prefixed and non-prefixed namespaces is not allowed."
  private def unexpectedCode(c: Tree) = throw new Exception("Unexpected DataModel code:\n" + c)


  val (pkg, domain, maxArity, body) = tree.children.head match {
    case q"""package $pkg {
        ..$imports
        object $domain extends DataModel($maxArity) {
          ..$body
        }
      }"""     => (pkg.toString, domain.toString, maxArity.toString.toInt, body)
    case other => unexpectedCode(other)
  }

  private def getNss(nss: Seq[Stat]): Seq[MetaNs] = nss.map {
    case q"trait $ns { ..$attrs }" => MetaNs(ns.toString, getAttrs(attrs))
    case q"object $o { ..$_ }"     => throw new Exception(noMix)
    case other                     => unexpectedCode(other)
  }

  private def getAttrs(attrs: Seq[Stat]): Seq[MetaAttr] = attrs.map {
    case q"val $attr = $defs" => getAttr(defs, (Nil, None, None, None, MetaAttr(attr.toString, 1, "")))._5
    case other                => unexpectedCode(other)
  }

  @tailrec
  private def getAttr(
    t: Tree,
    a: (List[String], Option[String], Option[String], Option[String], MetaAttr)
  ): (List[String], Option[String], Option[String], Option[String], MetaAttr) = {
    t match {
      case q"$prev.index"                   => getAttr(prev, ("index" :: a._1, a._2, a._3, a._4, a._5))
      case q"$prev.noHistory"               => getAttr(prev, ("noHistory" :: a._1, a._2, a._3, a._4, a._5))
      case q"$prev.uniqueIdentity"          => getAttr(prev, ("uniqueIdentity" :: a._1, a._2, a._3, a._4, a._5))
      case q"$prev.unique"                  => getAttr(prev, ("unique" :: a._1, a._2, a._3, a._4, a._5))
      case q"$prev.mandatory"               => getAttr(prev, ("mandatory" :: a._1, a._2, a._3, a._4, a._5))
      case q"$prev.fulltext"                => getAttr(prev, ("fulltext" :: a._1, a._2, a._3, a._4, a._5))
      case q"$prev.descr(${Lit.String(s)})" => getAttr(prev, (a._1, Some(s), a._3, a._4, a._5))
      case q"$prev.alias(${Lit.String(s)})" => getAttr(prev, (a._1, a._2, Some(s), a._4, a._5))
      case q"$prev.validation($lambda)"     => getAttr(prev, (a._1, a._2, a._3, Some(lambda.toString()), a._5))
      case q"oneString"                     => attr(a, 1, "String")
      case q"oneChar"                       => attr(a, 1, "Char")
      case q"oneByte"                       => attr(a, 1, "Byte")
      case q"oneShort"                      => attr(a, 1, "Short")
      case q"oneInt"                        => attr(a, 1, "Int")
      case q"oneLong"                       => attr(a, 1, "Long")
      case q"oneFloat"                      => attr(a, 1, "Float")
      case q"oneDouble"                     => attr(a, 1, "Double")
      case q"oneBoolean"                    => attr(a, 1, "Boolean")
      case q"oneBigInt"                     => attr(a, 1, "BigInt")
      case q"oneBigDecimal"                 => attr(a, 1, "BigDecimal")
      case q"oneDate"                       => attr(a, 1, "Date")
      case q"oneUUID"                       => attr(a, 1, "UUID")
      case q"oneURI"                        => attr(a, 1, "URI")
      case q"setInt"                        => attr(a, 2, "Int")
      case q"one[$refNs]"                   => attr(a, 1, "ref", Some(refNs.toString))
      case q"many[$refNs]"                  => attr(a, 2, "ref", Some(refNs.toString))
    }
  }

  private def attr(
    acc: (List[String], Option[String], Option[String], Option[String], MetaAttr),
    card: Int,
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

  val schema: MetaSchema = {
    val parts = body.head match {
      case q"object $_ { ..$_ }" => body.map {
        case q"object $part { ..$nss }" => MetaPart(part.toString, getNss(nss))
        case q"trait $ns $template"     => throw new Exception(noMix)
      }
      case _                     => Seq(MetaPart("", getNss(body)))
    }
    MetaSchema(pkg, domain, maxArity, parts)
  }


  //  schema.parts foreach println
  println(schema)
}

