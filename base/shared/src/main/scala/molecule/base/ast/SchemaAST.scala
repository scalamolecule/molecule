package molecule.base.ast

import molecule.base.util.BaseHelpers

object SchemaAST extends BaseHelpers {

  sealed trait Cardinality {
    def marker: String
    def tpe: String
  }

  case object CardOne extends Cardinality {
    override def marker = "One"
    override def tpe = ""
  }
  case object CardSet extends Cardinality {
    override def marker = "Set"
    override def tpe = "Set"
  }
  case object CardArr extends Cardinality {
    override def marker = "Arr"
    override def tpe = "Array"
  }
  case object CardMap extends Cardinality {
    override def marker = "Map"
    override def tpe = "Map"
  }

  case class MetaSchema(
    pkg: String,
    domain: String,
    maxArity: Int,
    parts: Seq[MetaPart]
  ) {
    def render(tabs: Int = 0): String = {
      val p        = indent(tabs)
      val pad      = s"\n$p  "
      val partsStr = if (parts.isEmpty) "" else parts.map(_.render(tabs + 1)).mkString(pad, s",\n\n$pad", s"\n$p")
      s"""MetaSchema("$pkg", "$domain", $maxArity, Seq($partsStr))"""
    }

    override def toString: String = render(0)

    def nsMap(tabs: Int = 0): String = {
      val p        = indent(tabs)
      val pad      = s"\n$p  "
      val pairs    = for {
        part <- parts
        ns <- part.nss
      } yield {
        s""""${ns.ns}" -> $pad  ${ns.render(tabs + 2)}"""
      }
      val attrsStr = if (pairs.isEmpty) "" else pairs.mkString(pad, s",\n$pad", s"\n$p")
      s"Map($attrsStr)"
    }

    def attrMap(tabs: Int = 0): String = {
      val p        = indent(tabs)
      val pad      = s"\n$p  "
      val attrData = for {
        part <- parts
        ns <- part.nss
        attr <- ns.attrs
      } yield {
        (s"${ns.ns}.${attr.attr}", attr.card, attr.tpe)
      }
      val maxSp    = attrData.map(_._1.length).max
      val attrs    = attrData.map {
        case (a, card, tpe) => s""""$a"${padS(maxSp, a)} -> ($card, "$tpe")"""
      }
      val attrsStr = if (attrs.isEmpty) "" else attrs.mkString(pad, s",$pad", s"\n$p")
      s"Map($attrsStr)"
    }

    def uniqueAttrs: String = {
      val attrs    = for {
        part <- parts
        ns <- part.nss
        attr <- ns.attrs if attr.options.exists(s => s == "unique" || s == "uniqueIdentity")
      } yield {
        s""""${ns.ns}.${attr.attr}""""
      }
      val attrsStr = if (attrs.isEmpty) "" else attrs.mkString("\n    ", s",\n    ", s"\n  ")
      s"List($attrsStr)"
    }
  }


  case class MetaPart(
    part: String,
    nss: Seq[MetaNs]
  ) {
    def render(tabs: Int): String = {
      val p      = indent(tabs)
      val pad    = s"\n$p  "
      val nssStr = if (nss.isEmpty) "" else nss.map(_.render(tabs + 1)).mkString(pad, s",\n$pad", s"\n$p")
      s"""MetaPart("$part", Seq($nssStr))"""
    }

    override def toString: String = render(0)
  }


  case class MetaNs(
    ns: String,
    attrs: Seq[MetaAttr],
    backRefNss: Seq[String] = Nil,
    mandatoryAttrs: Seq[String] = Nil,
    mandatoryRefs: Seq[(String, String)] = Nil,
    tuples: Seq[String] = Nil
  ) {
    def render(tabs: Int): String = {
      val maxAttr           = attrs.map(_.attr.length).max
      val maxTpe            = attrs.map(_.tpe.length).max
      val attrsStr          = if (attrs.isEmpty) "" else {
        val p   = indent(tabs)
        val pad = s"\n$p  "
        attrs.map { attr =>
          val attr1         = "\"" + attr.attr + "\"" + padS(maxAttr, attr.attr)
          val card          = attr.card
          val tpe           = "\"" + attr.tpe + "\"" + padS(maxTpe, attr.tpe)
          val refNs         = o(attr.refNs)
          val options       = sq(attr.options)
          val descr         = o(attr.description)
          val alias         = o(attr.alias)
          val requiredAttrs = sq(attr.requiredAttrs)
          val validations1  = renderValidations(attr.validations)
          s"""MetaAttr($attr1, $card, $tpe, $refNs, $options, $descr, $alias, $requiredAttrs, $validations1)"""
        }.mkString(pad, s",$pad", s"\n$p")
      }
      val backRefs          = if (backRefNss.isEmpty) "" else backRefNss.mkString("\"", "\", \"", "\"")
      val mandatoryAttrsStr = if (mandatoryAttrs.isEmpty) "" else mandatoryAttrs.mkString("\"", "\", \"", "\"")
      val mandatoryRefsStr  = if (mandatoryRefs.isEmpty) "" else mandatoryRefs.map {
        case (attr, refNs) => s"""\"$attr\" -> \"$refNs\""""
      }.mkString(", ")
      val tupleStr          = if (tuples.isEmpty) "" else tuples.mkString("\"", "\", \"", "\"")
      s"""MetaNs("$ns", Seq($attrsStr), Seq($backRefs), Seq($mandatoryAttrsStr), Seq($mandatoryRefsStr), Seq($tupleStr))"""
    }

    override def toString: String = render(0)
  }


  case class MetaAttr(
    attr: String,
    card: Cardinality,
    tpe: String,
    refNs: Option[String] = None,
    options: Seq[String] = Nil,
    description: Option[String] = None,
    alias: Option[String] = None,
    requiredAttrs: Seq[String] = Nil,
    validations: Seq[(String, String)] = Nil
  ) {
    override def toString: String = {
      val validations1 = renderValidations(validations)
      s"""MetaAttr("$attr", $card, "$tpe", ${o(refNs)}, ${sq(options)}, ${o(description)}, ${o(alias)}, ${sq(requiredAttrs)}, $validations1)"""
    }
  }


  private def renderValidations(validations: Seq[(String, String)]): String = {
    if (validations.isEmpty) {
      "Nil"
    } else {
      validations.map {
        case (test, error) =>
          val errorStr = if (error.isEmpty) "\"\"" else s"""\"\"\"$error\"\"\""""
          s"""            (
             |              \"\"\"$test\"\"\",
             |              $errorStr
             |            )""".stripMargin
      }.mkString("Seq(\n", ",\n", ")")

    }
  }
}
