package molecule.base.codegen.extract

import java.nio.file.{Files, Paths}
import molecule.base.ast.SchemaAST._
import molecule.base.error.ModelError
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer
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

  private val reservedAttrNames = List(
    "a", "e", "v", "t", "tx", "txInstant", "op", // Generic attributes
    "save", "insert", "update", "delete", // Actions
    //    "self", // if self-reference keyword is re-introduced
    "apply", "not", "add", "swap", "remove", // Expressions
  )

  private var backRefs   = Map.empty[String, Seq[String]]
  private val valueAttrs = ListBuffer.empty[String]

  private def noMix() = throw ModelError(
    "Mixing prefixed and non-prefixed namespaces is not allowed."
  )
  private def unexpected(c: Tree, msg: String = ":") = throw ModelError(
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
  }.getOrElse(
    unexpected(tree, ". Couldn't find `object <YourDataModel> extends DataModel(<arity>) {...}` in code:\n")
  )

  private def err(msg: String, ns: String = "", attr: String = "") = {
    val fullNs = if (ns.isEmpty && attr.isEmpty) "" else
      s" for attribute $ns.$attr"
    throw ModelError(
      s"""Problem in data model $pkg.dataModel.$domain$fullNs:
         |$msg
         |""".stripMargin
    )
  }

  def schema: MetaSchema = {
    val parts = body.head match {
      case q"object $_ { ..$_ }" => body.map {
        case q"object $part { ..$nss }" => MetaPart(part.toString, getNss(part.toString + "_", nss))
        case q"trait $ns $template"     => noMix()
      }
      case _                     => Seq(MetaPart("", getNss("", body)))
    }
    checkCircularMandatoryRefs(parts)
    val parts1 = addBackRefs(parts)
    MetaSchema(pkg, domain, maxArity, parts1)
  }

  private def addBackRefs(parts: Seq[MetaPart]): Seq[MetaPart] = {
    parts.map { part =>
      val nss1 = part.nss.map { ns =>
        ns.copy(backRefNss = backRefs.getOrElse(ns.ns, Nil).distinct.sorted)
      }
      part.copy(nss = nss1)
    }
  }
  private def checkCircularMandatoryRefs(parts: Seq[MetaPart]): Unit = {
    val mappings: Map[String, Seq[(String, String)]] = parts
      .flatMap(_.nss
        .filter(_.attrs.exists(ref => ref.refNs.nonEmpty && ref.options.contains("mandatory"))))
      .map(ns => ns.ns -> ns.attrs.collect {
        case ref if ref.refNs.nonEmpty && ref.options.contains("mandatory") =>
          s"${ns.ns}.${ref.attr}" -> ref.refNs.get
      }).toMap

    def check(prevNss: Seq[String], graph: Seq[String], ns: String): Unit = {
      mappings.get(ns).foreach { refs =>
        // Referenced namespace has mandatory refs. Keep checking
        refs.foreach {
          case (refAttr, refNs) if prevNss.contains(refNs) =>
            val last = if (graph.length == 1) refNs else s"$refAttr --> $refNs"
            err(
              s"""Circular mandatory references not allowed. Found:
                 |  ${graph.mkString(" --> ")} --> $last
                 |""".stripMargin
            )
          case (refAttr, refNs)                            =>
            check(prevNss :+ refNs, graph :+ refAttr, refNs)
        }
      }
    }

    mappings.foreach {
      case (ns, refs) => refs.foreach {
        case (refAttr, refNs) =>
          // Recursively check each mandatory ref. Can likely be optimized...
          check(Seq(ns), Seq(refAttr), refNs)
      }
    }
  }

  private def getNss(partPrefix: String, nss: Seq[Stat]): Seq[MetaNs] = nss.map {
    case q"trait $nsTpe { ..$attrs }" =>
      val ns = nsTpe.toString
      if (ns.head.isLower) {
        err(s"Please change namespace trait name `$ns` to start with upper case letter.")
      }
      if (ns.length == 1) {
        err(
          s"Please change namespace trait name `$ns` to have at least 2 characters " +
            "(to avoid interference with type parameters).")
      }
      if (attrs.isEmpty) {
        err(s"Please define attribute(s) in namespace $ns")
      }
      val metaAttrs      = getAttrs(ns, attrs)
      val mandatoryAttrs = metaAttrs.collect {
        case a if a.refNs.isEmpty && a.options.contains("mandatory") => a.attr
      }
      val mandatoryRefs  = metaAttrs.collect {
        case a if a.refNs.nonEmpty && a.options.contains("mandatory") => a.attr -> a.refNs.get
      }

      // Merge required groups with common attributes
      val reqGroups       = metaAttrs.collect {
        case a if a.requiredAttrs.nonEmpty => a.requiredAttrs
      }
      val reqGroupsMerged = reqGroups.map { group =>
        reqGroups.flatMap {
          case otherGroup if otherGroup.intersect(group).nonEmpty => (otherGroup ++ group).distinct.sorted
          case _                                                  => group
        }.distinct.sorted
      }.distinct

      //      metaAttrs.foreach(println)
      //      println("--------")
      //      reqGroups.foreach(println)
      //      println("--------")
      //      reqGroupsMerged.foreach(println)

      val reqAttrs   = reqGroupsMerged.flatten
      val metaAttrs1 = metaAttrs.map { a =>
        //        val attr = ns + "." + a.attr
        val attr = a.attr
        if (reqAttrs.contains(attr)) {
          val otherAttrs = reqGroupsMerged.collectFirst {
            case group if group.contains(attr) => group.filterNot(_ == attr)
          }
          a.copy(requiredAttrs = otherAttrs.get)
        } else a
      }

      MetaNs(partPrefix + ns, metaAttrs1, Nil, mandatoryAttrs, mandatoryRefs)
    case q"object $o { ..$_ }"        => noMix()
    case other                        => unexpected(other)
  }

  private def getAttrs(ns: String, attrs: Seq[Stat]): Seq[MetaAttr] = attrs.map {
    case q"val $attr = $defs" =>
      val a = attr.toString
      if (reservedAttrNames.contains(a)) {
        err(
          s"Please change attribute name $ns.$a to avoid colliding with reserved molecule names:\n  " +
            reservedAttrNames.mkString("\n  ")
        )
      }
      acc(ns, defs, MetaAttr(a, CardOne, ""))

    case other => unexpected(other)
  }

  @tailrec
  private def acc(ns: String, t: Tree, a: MetaAttr): MetaAttr = {
    val attr = ns + "." + a.attr
    t match {
      case q"$prev.index"          => acc(ns, prev, a.copy(options = a.options :+ "index"))
      case q"$prev.noHistory"      => acc(ns, prev, a.copy(options = a.options :+ "noHistory"))
      case q"$prev.uniqueIdentity" => acc(ns, prev, a.copy(options = a.options :+ "uniqueIdentity"))
      case q"$prev.unique"         => acc(ns, prev, a.copy(options = a.options :+ "unique"))
      case q"$prev.fulltext"       => acc(ns, prev, a.copy(options = a.options :+ "fulltext"))
      case q"$prev.owner"          => acc(ns, prev, a.copy(options = a.options :+ "owner"))
      case q"$prev.mandatory"      => acc(ns, prev, a.copy(options = a.options :+ "mandatory"))

      case q"$prev.description(${Lit.String(s)})" =>
        if (s.isEmpty)
          err(s"Can't apply empty String as description option for attribute $ns.$a")
        else if (s.contains("\""))
          err(s"Description option for attribute $ns.$a can't contain quotation marks.")
        else
          acc(ns, prev, a.copy(description = Some(s)))

      case q"$prev.alias(${Lit.String(s)})" => s match {
        case r"([a-zA-Z0-9]+)$alias" =>
          if (reservedAttrNames.contains(alias)) {
            err(
              s"Alias `$alias` for attribute $ns.$a can't be any of the reserved molecule attribute names:\n  " +
                reservedAttrNames.mkString("\n  ")
            )
          } else {
            acc(ns, prev, a.copy(alias = Some(alias)))
          }
        case other                   =>
          err(s"Invalid alias for attribute $ns.$a: " + other)
      }

      case q"oneString"     => a.copy(card = CardOne, baseTpe = "String")
      case q"oneChar"       => a.copy(card = CardOne, baseTpe = "Char")
      case q"oneByte"       => a.copy(card = CardOne, baseTpe = "Byte")
      case q"oneShort"      => a.copy(card = CardOne, baseTpe = "Short")
      case q"oneInt"        => a.copy(card = CardOne, baseTpe = "Int")
      case q"oneLong"       => a.copy(card = CardOne, baseTpe = "Long")
      case q"oneFloat"      => a.copy(card = CardOne, baseTpe = "Float")
      case q"oneDouble"     => a.copy(card = CardOne, baseTpe = "Double")
      case q"oneBoolean"    => a.copy(card = CardOne, baseTpe = "Boolean")
      case q"oneBigInt"     => a.copy(card = CardOne, baseTpe = "BigInt")
      case q"oneBigDecimal" => a.copy(card = CardOne, baseTpe = "BigDecimal")
      case q"oneDate"       => a.copy(card = CardOne, baseTpe = "Date")
      case q"oneUUID"       => a.copy(card = CardOne, baseTpe = "UUID")
      case q"oneURI"        => a.copy(card = CardOne, baseTpe = "URI")

      case q"one[$refNs]" =>
        addBackRef(ns, refNs.toString)
        a.copy(card = CardOne, baseTpe = "Long", refNs = Some(refNs.toString.replace('.', '_')))

      case q"many[$refNs]" =>
        addBackRef(ns, refNs.toString)
        a.copy(card = CardSet, baseTpe = "Long", refNs = Some(refNs.toString.replace('.', '_')))

      case q"setString"     => a.copy(card = CardSet, baseTpe = "String")
      case q"setChar"       => a.copy(card = CardSet, baseTpe = "Char")
      case q"setByte"       => a.copy(card = CardSet, baseTpe = "Byte")
      case q"setShort"      => a.copy(card = CardSet, baseTpe = "Short")
      case q"setInt"        => a.copy(card = CardSet, baseTpe = "Int")
      case q"setLong"       => a.copy(card = CardSet, baseTpe = "Long")
      case q"setFloat"      => a.copy(card = CardSet, baseTpe = "Float")
      case q"setDouble"     => a.copy(card = CardSet, baseTpe = "Double")
      case q"setBoolean"    => a.copy(card = CardSet, baseTpe = "Boolean")
      case q"setBigInt"     => a.copy(card = CardSet, baseTpe = "BigInt")
      case q"setBigDecimal" => a.copy(card = CardSet, baseTpe = "BigDecimal")
      case q"setDate"       => a.copy(card = CardSet, baseTpe = "Date")
      case q"setUUID"       => a.copy(card = CardSet, baseTpe = "UUID")
      case q"setURI"        => a.copy(card = CardSet, baseTpe = "URI")


      // Validations ................................................

      case q"$prev.validate { ..case $cases }" =>
        oneValidationCall(ns, a)
        val (valueAttrs, validations) = cases.map {
          case Case(v, Some(test), Lit.String(error)) =>
            val valueAttrs = extractValueAttrs(ns, a, q"$test")
            val validation = (indent(s"$v => $test"), error)
            (valueAttrs, validation)

          case Case(v, Some(test), Term.Select(Lit.String(multilineMsg), Term.Name("stripMargin"))) =>
            val valueAttrs = extractValueAttrs(ns, a, q"$test")
            val validation = (indent(s"$v => $test"), multilineMsg)
            (valueAttrs, validation)

          case Case(v, Some(test), Term.Interpolate(Term.Name("s"), _, _)) =>
            err(
              s"String interpolation not allowed for validation error messages of `$attr`. " +
                s"Please remove the s prefix."
            )

          case Case(v, None, Lit.String(error)) =>
            err(s"""Please provide if-expression: case $v if <test..> = "$error"""", ns, a.attr)

          case other => err("Unexpected validation case: " + other, ns, a.attr)
        }.unzip

        val valueAttrs1 = valueAttrs.flatten.distinct.sorted
        val valueAttrs2 = if (valueAttrs1.isEmpty) Nil else (a.attr +: valueAttrs1).distinct.sorted
        val reqAttrs1   = a.requiredAttrs ++ valueAttrs2
        acc(ns, prev, a.copy(requiredAttrs = reqAttrs1, valueAttrs = valueAttrs1, validations = validations))

      case q"$prev.validate($test)" =>
        oneValidationCall(ns, a)
        val valueAttrs1  = extractValueAttrs(ns, a, q"$test")
        val valueAttrs2  = if (valueAttrs1.isEmpty) Nil else (a.attr +: valueAttrs1).distinct.sorted
        val reqAttrs1    = a.requiredAttrs ++ valueAttrs2
        val validations1 = Seq(indent(test.toString()) -> "")
        acc(ns, prev, a.copy(requiredAttrs = reqAttrs1, valueAttrs = valueAttrs1, validations = validations1))

      case q"$prev.validate($test, ${Lit.String(error)})" =>
        oneValidationCall(ns, a)
        val valueAttrs1  = extractValueAttrs(ns, a, q"$test")
        val valueAttrs2  = if (valueAttrs1.isEmpty) Nil else (a.attr +: valueAttrs1).distinct.sorted
        val reqAttrs1    = a.requiredAttrs ++ valueAttrs2
        val validations1 = Seq(indent(test.toString()) -> error)
        acc(ns, prev, a.copy(requiredAttrs = reqAttrs1, valueAttrs = valueAttrs1, validations = validations1))

      case q"$prev.validate($test, ${Term.Select(Lit.String(multilineMsg), Term.Name("stripMargin"))})" =>
        oneValidationCall(ns, a)
        val valueAttrs1  = extractValueAttrs(ns, a, q"$test")
        val valueAttrs2  = if (valueAttrs1.isEmpty) Nil else (a.attr +: valueAttrs1).distinct.sorted
        val reqAttrs1    = a.requiredAttrs ++ valueAttrs2
        val validations1 = Seq(indent(test.toString()) -> multilineMsg)
        acc(ns, prev, a.copy(requiredAttrs = reqAttrs1, valueAttrs = valueAttrs1, validations = validations1))

      case q"$prev.validate($test, ${Term.Interpolate(Term.Name("s"), _, _)})" =>
        err(
          s"String interpolation not allowed for validation error messages of `$attr`. " +
            s"Please remove the s prefix."
        )

      case q"$prev.email" =>
        oneValidationCall(ns, a)
        val test  = "(s: String) => emailRegex.findFirstMatchIn(s).isDefined"
        val error = s"""`$$v` is not a valid email"""
        acc(ns, prev, a.copy(validations = Seq(test -> error)))

      case q"$prev.email(${Lit.String(error)})" =>
        oneValidationCall(ns, a)
        val test = "(s: String) => emailRegex.findFirstMatchIn(s).isDefined"
        acc(ns, prev, a.copy(validations = Seq(test -> error)))

      case q"$prev.regex(${Lit.String(regex)})" =>
        oneValidationCall(ns, a)
        val test  = s"""(s: String) => "$regex".r.findFirstMatchIn(s).isDefined"""
        val error = s"""\"$$v\" doesn't match regex pattern: ${regex.replace("$", "$$")}"""
        acc(ns, prev, a.copy(validations = Seq(test -> error)))

      case q"$prev.regex(${Lit.String(regex)}, ${Lit.String(error)})" =>
        oneValidationCall(ns, a)
        val test = s"""(s: String) => "$regex".r.findFirstMatchIn(s).isDefined"""
        acc(ns, prev, a.copy(validations = Seq(test -> error)))

      case q"$prev.enums(Seq(..$vs), ${Lit.String(error)})" =>
        oneValidationCall(ns, a)
        val test = s"""v => Seq$vs.contains(v)"""
        acc(ns, prev, a.copy(validations = Seq(test -> error)))

      case q"$prev.enums(..$vs)" =>
        oneValidationCall(ns, a)
        val test  = s"""v => Seq$vs.contains(v)"""
        val error = s"""Value `$$v` is not one of the allowed values in Seq$vs"""
        acc(ns, prev, a.copy(validations = Seq(test -> error)))

      case q"$prev.require(..$otherAttrs)" =>
        //        val reqAttrs1 = attr +: otherAttrs.map(ns + "." + _)
        val reqAttrs1 = a.attr +: otherAttrs.map(_.toString)
        acc(ns, prev, a.copy(requiredAttrs = reqAttrs1))

      case q"$prev.value" => err(
        s"Calling `value` on attribute `$attr` is only allowed in validations code of other attributes."
      )

      case other =>
        println(other.structure)
        unexpected(other)
    }
  }

  private def oneValidationCall(ns: String, a: MetaAttr) = if (a.validations.nonEmpty) {
    throw ModelError(
      s"Please use `validate { ..<pattern matches> }` for multiple validations of attribute `$ns.${a.attr}`"
    )
  }

  private def indent(code0: String): String = {
    val code = code0.replaceAll("\\.value", "")
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

  // Recursively traverse test code trees to extract attribute names
  private lazy val traverser = (ns: String) => new Traverser {
    override def apply(tree: Tree): Unit = tree match {
      case Term.Select(Term.Name(attr), Term.Name("value")) => valueAttrs += attr
      case node                                             => super.apply(node)
    }
  }

  private def extractValueAttrs(ns: String, a: MetaAttr, test: Stat): List[String] = {
    valueAttrs.clear()
    traverser(ns)(test)
    valueAttrs.result().distinct.sorted
  }


  private def addBackRef(ns: String, backRefNs: String): Unit = {
    val cur = backRefs.getOrElse(backRefNs, Nil)
    backRefs = backRefs + (backRefNs -> (cur :+ ns))
  }
}

