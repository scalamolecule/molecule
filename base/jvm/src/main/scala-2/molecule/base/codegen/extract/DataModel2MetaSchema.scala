package molecule.base.codegen.extract

import java.nio.file.{Files, Paths}
import molecule.base.ast.SchemaAST._
import molecule.base.util.exceptions.MoleculeError
import scala.annotation.tailrec
import scala.meta._

object DataModel2MetaSchema {
  def apply(filePath: String, scalaVersion: String = "3"): MetaSchema = {
    val bytes   = Files.readAllBytes(Paths.get(filePath))
    val pkgPath = new String(bytes, "UTF-8")
    new DataModel2MetaSchema(filePath, pkgPath, scalaVersion).schema
  }
}

class DataModel2MetaSchema(filePath: String, pkgPath: String, scalaVersion: String) {
  private val virtualFile = Input.VirtualFile(filePath, pkgPath)
  private val dialect     = scalaVersion match {
    case "3"   => dialects.Scala3(virtualFile)
    case "213" => dialects.Scala213(virtualFile)
    case "212" => dialects.Scala212(virtualFile)
  }
  private val tree        = dialect.parse[Source].get

  private var backRefs = Map.empty[String, Seq[String]]

  private def noMix() = throw new Exception(
    "Mixing prefixed and non-prefixed namespaces is not allowed."
  )
  private def unexpected(c: Tree, msg: String = ":") = throw new Exception(
    s"Unexpected DataModel code in file $filePath$msg\n" + c
  )

  private val (pkg, afterPkg) = tree.children.collectFirst {
    case Pkg(Term.Select(pkg, Term.Name("dataModel")), afterPkg) => (pkg.toString, afterPkg)
  }.getOrElse(unexpected(tree, ". Couldn't find package definition in code:\n"))

  private val (domain, maxArity, body) = afterPkg.collectFirst {
    case Defn.Object(_, Term.Name(domain),
    Template.internal.Impl(_,
    List(Init.internal.Impl(Type.Name("DataModel"), _,
    List(Term.ArgClause(List(Lit.Int(maxArity)), _))
    )), _, body, _)) => (domain, maxArity, body)
  }.getOrElse(unexpected(tree,
    ". Couldn't find `object <YourDataModel> extends DataModel(<arity>) {...}` in code:\n"))

  def schema: MetaSchema = {
    val parts = body.head match {
      case q"object $_ { ..$_ }" => body.map {
        case q"object $part { ..$nss }" => MetaPart(part.toString, getNss(part.toString + "_", nss))
        case q"trait $ns $template"     => noMix()
      }
      case _                     => Seq(MetaPart("", getNss("", body)))
    }
    MetaSchema(pkg, domain, maxArity, parts)
  }

  private def getNss(partPrefix: String, nss: Seq[Stat]): Seq[MetaNs] = nss.map {
    case q"trait $nsTpe { ..$attrs }" =>
      val ns = nsTpe.toString
      if (ns.head.isLower)
        throw MoleculeError("Namespace traits have to start with upper case letter.")
      val metaAttrs  = getAttrs(ns, attrs)
      val backRefNss = backRefs.getOrElse(ns, Nil).distinct.sorted
      MetaNs(partPrefix + ns, metaAttrs, backRefNss)
    case q"object $o { ..$_ }"        => noMix()
    case other                        => unexpected(other)
  }

  private def getAttrs(ns: String, attrs: Seq[Stat]): Seq[MetaAttr] = attrs.map {
    case q"val $attr = $defs" =>
      val a = attr.toString
      getAttr(ns, a, defs, (Nil, None, None, Nil, MetaAttr(a, CardOne, "")))._5
    case other                => unexpected(other)
  }

  private val reservedAttrNames = List(
    "a", "e", "v", "t", "tx", "txInstant", "op",
    "save", "insert", "update", "retract",
    "self", "apply", "assert", "replace", "not", "contains", "k",
  )

  @tailrec
  private def getAttr(
    ns: String,
    a: String,
    t: Tree,
    x: (List[String], Option[String], Option[String], Seq[(String, String)], MetaAttr)
  ): (List[String], Option[String], Option[String], Seq[(String, String)], MetaAttr) = {
    t match {
      case q"$prev.index"                   => getAttr(ns, a, prev, ("index" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.noHistory"               => getAttr(ns, a, prev, ("noHistory" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.uniqueIdentity"          => getAttr(ns, a, prev, ("uniqueIdentity" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.unique"                  => getAttr(ns, a, prev, ("unique" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.fulltext"                => getAttr(ns, a, prev, ("fulltext" :: x._1, x._2, x._3, x._4, x._5))
      case q"$prev.owner"                   => getAttr(ns, a, prev, ("owner" :: x._1, x._2, x._3, x._4, x._5))
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


      // Validations ................................................

      case q"$prev.validate { ..case $cases }" =>
        val validations = cases.map {
          case Case(v, Some(test), Lit.String(error))                                               =>
            (indent(s"$v => $test"), error)
          case Case(v, Some(test), Term.Select(Lit.String(multilineMsg), Term.Name("stripMargin"))) =>
            (indent(s"$v => $test"), multilineMsg)
          case Case(v, None, Lit.String(error))                                                     =>
            throw new Exception(s"""Please provide if-expression: case $v if <test..> = "$error"""")
          case other                                                                                =>
            throw new Exception("Unexpected validation case: " + other)
        }
        getAttr(ns, a, prev, (x._1, x._2, x._3, validations, x._5))

      case q"$prev.validate($test)" =>
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(indent(test.toString()) -> ""), x._5))

      case q"$prev.validate($test, ${Lit.String(error)})" =>
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(indent(test.toString()) -> error), x._5))

      case q"$prev.validate($test, ${Term.Select(Lit.String(multilineMsg), Term.Name("stripMargin"))})" =>
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(indent(test.toString()) -> multilineMsg), x._5))

      case q"$prev.email" =>
        val test  = "(s: String) => emailRegex.findFirstMatchIn(s).isDefined"
        val error = "Invalid email"
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(test -> error), x._5))

      case q"$prev.email(${Lit.String(error)})" =>
        val test = "(s: String) => emailRegex.findFirstMatchIn(s).isDefined"
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(test -> error), x._5))

      case q"$prev.regex(${Lit.String(regex)})" =>
        val test  = s"""(s: String) => "$regex".r.findFirstMatchIn(s).isDefined"""
        val error = s"String doesn't match regex: $regex"
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(test -> error), x._5))

      case q"$prev.regex(${Lit.String(regex)}, ${Lit.String(error)})" =>
        val test = s"""(s: String) => "$regex".r.findFirstMatchIn(s).isDefined"""
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(test -> error), x._5))

      case q"$prev.allowed(..$vs)" =>
        val test  = s"""v => Seq$vs.contains(v)"""
        val error = s"Value `$$v` is not one of the allowed values in Seq$vs"
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(test -> error), x._5))


      case q"$prev.require(..$attrs)" =>
        val test  = s""
        val error = s""
        getAttr(ns, a, prev, (x._1, x._2, x._3, List(test -> error), x._5))

      case q"$prev.mandatory" =>
        getAttr(ns, a, prev, ("mandatory" :: x._1, x._2, x._3, x._4, x._5))

      case q"$prev.value" =>
        getAttr(ns, a, prev, ("value" :: x._1, x._2, x._3, x._4, x._5))

      case other => unexpected(other)
    }
  }

  def indent(code: String): String = {
    if (code.contains('\n')) {
      val testIndented = {
        val lines  = code.split('\n').toList
        val indent = lines.map(_.takeWhile(_ == ' ').length).filterNot(_ == 0).min
        lines.map(_.replaceFirst(s"\\s{$indent}", "")).mkString("\n")
      }
      testIndented
    } else {
      code
    }
  }

  private def addBackRef(ns: String, backRefNs: String): Unit = {
    val cur = backRefs.getOrElse(backRefNs, Nil)
    backRefs = backRefs + (backRefNs -> (cur :+ ns))
  }

  private def attr(
    acc: (List[String], Option[String], Option[String], Seq[(String, String)], MetaAttr),
    card: Cardinality,
    tpe: String,
    refNs: Option[String] = None
  ): (List[String], Option[String], Option[String], Seq[(String, String)], MetaAttr) = {
    (Nil, None, None, Nil, acc._5.copy(
      card = card,
      tpe = tpe,
      refNs = refNs,
      options = acc._1,
      descr = acc._2,
      alias = acc._3,
      validations = acc._4
    ))
  }
}

