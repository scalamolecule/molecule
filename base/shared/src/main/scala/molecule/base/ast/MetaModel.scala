package molecule.base.ast

import molecule.base.util.BaseHelpers


sealed trait MetaModel

case class MetaSchema(
  pkg: String,
  domain: String,
  maxArity: Int,
  parts: Seq[MetaPart]
) extends MetaModel with BaseHelpers {
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
      (s"${ns.ns}.${attr.attr}", attr.card, attr.baseTpe, attr.requiredAttrs)
    }
    val maxSp    = attrData.map(_._1.length).max
    val attrs    = attrData.map {
      case (a, card, tpe, reqAttrs) =>
        val reqAttrsStr = reqAttrs.map(a => s""""$a"""").mkString(", ")
        s""""$a"${padS(maxSp, a)} -> ($card, "$tpe"${padS(14, tpe)}, Seq($reqAttrsStr))"""
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
) extends MetaModel with BaseHelpers {
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
  mandatoryRefs: Seq[(String, String)] = Nil
) extends MetaModel with BaseHelpers {
  def render(tabs: Int): String = {
    val maxAttr           = attrs.map(_.attr.length).max
    val maxTpe            = attrs.map(_.baseTpe.length).max
    val attrsStr          = if (attrs.isEmpty) "" else {
      val p   = indent(tabs)
      val pad = s"\n$p  "
      attrs.map { attr =>
        val attr1         = "\"" + attr.attr + "\"" + padS(maxAttr, attr.attr)
        val card          = attr.card
        val tpe           = "\"" + attr.baseTpe + "\"" + padS(maxTpe, attr.baseTpe)
        val refNs         = o(attr.refNs)
        val options       = sq(attr.options)
        val descr         = o(attr.description)
        val alias         = o(attr.alias)
        val requiredAttrs = sq(attr.requiredAttrs)
        val valueAttrs    = sq(attr.valueAttrs)
        val validations1  = renderValidations(attr.validations)
        s"""MetaAttr($attr1, $card, $tpe, $refNs, $options, $descr, $alias, $requiredAttrs, $valueAttrs, $validations1)"""
      }.mkString(pad, s",$pad", s"\n$p")
    }
    val backRefs          = if (backRefNss.isEmpty) "" else backRefNss.mkString("\"", "\", \"", "\"")
    val mandatoryAttrsStr = if (mandatoryAttrs.isEmpty) "" else mandatoryAttrs.mkString("\"", "\", \"", "\"")
    val mandatoryRefsStr  = if (mandatoryRefs.isEmpty) "" else mandatoryRefs.map {
      case (attr, refNs) => s"""\"$attr\" -> \"$refNs\""""
    }.mkString(", ")
    s"""MetaNs("$ns", Seq($attrsStr), Seq($backRefs), Seq($mandatoryAttrsStr), Seq($mandatoryRefsStr))"""
  }

  override def toString: String = render(0)
}


case class MetaAttr(
  attr: String,
  card: Card,
  baseTpe: String,
  refNs: Option[String] = None,
  options: Seq[String] = Nil,
  description: Option[String] = None,
  alias: Option[String] = None,
  requiredAttrs: Seq[String] = Nil,
  valueAttrs: Seq[String] = Nil,
  validations: Seq[(String, String)] = Nil
) extends MetaModel with BaseHelpers {
  override def toString: String = {
    val validations1 = renderValidations(validations)
    s"""MetaAttr("$attr", $card, "$baseTpe", ${o(refNs)}, ${sq(options)}, ${o(description)}, ${o(alias)}, ${sq(requiredAttrs)}, ${sq(valueAttrs)}, $validations1)"""
  }
}



