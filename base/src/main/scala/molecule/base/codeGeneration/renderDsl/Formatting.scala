package molecule.base.codeGeneration.renderDsl

import molecule.base.codeGeneration.parseDataModel.parserAST.{BackRef, DefAttr, Namespace, ParseModel, Ref}
import molecule.base.codeGeneration.parseDataModel.parserAST._


class Formatting(model: ParseModel, namespace: Namespace, arity: Int = 0) {

  lazy val pkg      = model.pkg + ".dsl"
  lazy val domain   = model.domain
  lazy val maxArity = model.maxArity
  lazy val ns       = namespace.ns
  lazy val attrs0   = namespace.attrs
  lazy val attrs    = namespace.attrs.filterNot(_.isInstanceOf[BackRef])

  // todo: skip genericPkg and match domain name instead
  lazy val isDatom   = domain == "Datom"
  lazy val isSchema  = domain == "Schema"
  lazy val isGeneric = Seq(
    "Datom", "Schema", "Log", "AEVT", "AVET", "EAVT", "VAET"
  ).contains(domain)

  val optSchemaAttrs = Seq(
    "ident",
    "valueType",
    "cardinality",
    "doc",
    "unique",
    "isComponent",
    "noHistory",
    "index",
    "fulltext"
  )

  lazy val padAttr     = (s: String) => padS(attrs.map(_.attr).map(_.length).max, s)
  lazy val padType     = (s: String) => padS(attrs.map(_.tpe).map(_.length).max, s)
  lazy val padBaseType = (s: String) => padS(attrs.map(_.baseTpe).map(_.length).max, s)
  lazy val padClass    = (s: String) => padS(attrs.map(_.clazz).map(_.length).max, s)

  lazy val maxRefs    : Seq[Int] = 0 +: attrs0.collect {
    case Ref(attr, _, _, _, _, _, _, _, _, _, _) => attr.length
    case BackRef(attr, _, _, _, _, _, _, _, _)   => attr.length
  }
  lazy val maxRefNs   : Seq[Int] = 0 +: attrs.collect {
    case Ref(_, _, _, _, _, _, refNs, _, _, _, _) => refNs.length
  }
  lazy val maxBackRefs: Seq[Int] = 0 +: attrs0.collect {
    case BackRef(_, _, _, _, _, _, backRef, _, _) => backRef.length
  }

  lazy val padRef      = (attr: String) => padS(maxRefs.max, attr)
  lazy val padRefNs    = (refNs: String) => padS(maxRefNs.max, refNs)
  lazy val padBackRefs = (backRef: String) => padS(maxBackRefs.max, backRef)

  def formatted(a: DefAttr) = {
    val attrSp     = padAttr(a.attr)
    val typeSp     = padType(a.tpe)
    val baseTypeSp = padBaseType(a.baseTpe)
    val ref        = a.attr.capitalize + padRef(a.attr)
    val refNsPad   = (refNs: String) => refNs + "_" + padRefNs(refNs)
    (
      a.card,
      ns + "_" + a.attr + attrSp,
      ns + "_" + a.attr + "_" + attrSp,
      ns + "__" + ref,
      a.attr + attrSp,
      a.attr + "$" + attrSp,
      a.attr + "_" + attrSp,
      a.tpe + typeSp,
      a.baseTpe + baseTypeSp,
      "Option[" + a.tpe + typeSp + "]",
      ref,
      refNsPad
    )
  }

  def nn(i: Int) = if (i < 10) s"0$i" else s"$i"

  lazy val _0       = "_" + arity
  lazy val _1       = "_" + (arity + 1)
  lazy val ns_      = ns + "_"
  lazy val ns_0     = ns + _0
  lazy val ns_1     = ns + _1
  lazy val `, _`    = (0 until arity).map(_ => s", _").mkString("")
  lazy val `, A`    = if (arity == 0) "" else ", " + (65 until (65 + arity)).map(_.toChar).mkString(", ")
  lazy val `A..V`   = if (arity == 0) "" else (65 until (65 + arity)).map(_.toChar).mkString(", ")
  lazy val `A..V, ` = if (arity == 0) "" else (65 until (65 + arity)).map(_.toChar).mkString("", ", ", ", ")
  lazy val `[A..V]` = if (arity == 0) "" else (65 until (65 + arity)).map(_.toChar).mkString("[", ", ", "]")

  lazy val NS = s"Molecule_${nn(arity)}${`[A..V]`}"


  def nsData(a: DefAttr) = (
    a.clazz + padClass(a.clazz),
    a.clazz + "_" + padClass(a.clazz),
    a.clazz + "$" + padClass(a.clazz)
  )

  def getOptions(a: DefAttr, bi: Option[String], next: Boolean = true): String = {
    if (isDatom) {
      " with GenericAttr"
    } else if (isGeneric) {
      ""
    } else {
      val classes          = a.options.filterNot(_.datomicKeyValue == "alias").filter(_.clazz.nonEmpty).map(_.clazz)
      val index            = if (classes.contains("Index")) Seq("Index") else Nil
      val optsWithoutIndex = classes.filterNot(_ == "Index")
      def render(opts: Seq[String]) = {
        val biOptions = a match {
          case Ref(_, _, _, _, _, _, refNs, _, bi, revRef, _) =>
            bi match {
              case Some("BiSelfRef_")     => Seq(s"BiSelfRefAttr_")
              case Some("BiOtherRef_")    => Seq(s"BiOtherRefAttr_[${refNs}_$revRef]")
              case Some("BiEdgeRef_")     => Seq(s"BiEdgeRefAttr_[${refNs}_$revRef]")
              case Some("BiEdgePropAttr") => Seq(s"BiEdgePropAttr_")
              case Some("BiEdgePropRef_") => Seq(s"BiEdgePropRefAttr_")
              case Some("BiTargetRef_")   => Seq(s"BiTargetRefAttr_[${refNs}_$revRef]")
              case _                      => Nil
            }
          case _                                              => bi.toList
        }
        val allOps    = index ++ opts ++ biOptions
        if (allOps.isEmpty) "" else allOps.mkString(" with ", " with ", "")
      }
      render(optsWithoutIndex)
    }
  }


  def getRef(r: Ref) = {
    val Ref(_, _, _, clazz2, _, _, refNs, _, _, _, _)                                         = r
    val (card, ns_attrM, ns_attrO, ns__ref, attr, attrO, attr_, tpe, tpB, tpO, ref, refNsPad) = formatted(r)

    val biDirectionals = r match {
      case Ref(_, _, _, _, _, _, refNs, _, bi, revRef, _) =>
        bi match {
          case Some("BiSelfRef_")     => Seq(s"BiSelfRef_")
          case Some("BiOtherRef_")    => Seq(s"BiOtherRef_ [${refNs}_$revRef]")
          case Some("BiEdgePropRef_") => Seq(s"BiEdgePropRef_")
          case Some("BiEdgeRef_")     => Seq(s"BiEdgeRef_  [${refNs}_$revRef]")
          case Some("BiTargetRef_")   => Seq(s"BiTargetRef_[${refNs}_$revRef]")
          case _                      => Nil
        }

      case _ => Nil
    }
    val cls            = if (clazz2 == "OneRef") "OneRef " else "ManyRef"
    val refNsDef       = s"${refNs}_$arity${padRefNs(refNs)}${`[A..V]`}"
    val nestedDef      = if (clazz2 == "ManyRef" && arity < maxArity) {
      if (arity == 0)
        Seq(s"Nested${_0}[$ns_1]")
      else
        Seq(s"Nested${_0}[${`A..V`}, $ns_1]")
    } else {
      Nil
    }

    val extras    = biDirectionals ++ nestedDef
    val extrasStr = if (extras.isEmpty) "" else extras.mkString(" with ", " with ", "")
    s"final def $ref: $cls[$ns, $refNs${padRefNs(refNs)}] with $refNsDef$extrasStr = ??? "
  }

  def getBackRef(br: BackRef) = {
    val backRefNs         = br.backRefNs
    val sp                = padBackRefs(backRefNs)
    val backRefNsPrefixed = "_" + backRefNs + sp
    val backRef_0         = backRefNs + "_" + arity + sp
    s"final def $backRefNsPrefixed: $backRef_0${`[A..V]`} = ???"
  }
}
