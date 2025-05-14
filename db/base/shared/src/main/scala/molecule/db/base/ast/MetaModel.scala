package molecule.db.base.ast

import molecule.db.base.util.BaseHelpers


sealed trait MetaModel

case class MetaDomain(
  pkg: String,
  domain: String,
  maxArity: Int,
  segments: Seq[MetaSegment]
) extends MetaModel {
  import BaseHelpers._ // import BaseHelpers methods instead of extending to avoid polluting namespace
  def render(tabs: Int = 0): String = {
    val p           = indent(tabs)
    val pad         = s"\n$p  "
    val segmentsStr = if (segments.isEmpty) "" else
      segments.map(_.render(tabs + 1)).mkString(pad, s",\n\n$pad", s"\n$p")
    s"""MetaDomain("$pkg", "$domain", $maxArity, Seq($segmentsStr))"""
  }

  override def toString: String = render(0)

  def entityMap(tabs: Int = 0): String = {
    val p        = indent(tabs)
    val pad      = s"\n$p  "
    val pairs    = for {
      segment <- segments
      entity <- segment.ents
    } yield {
      s""""${entity.ent}" -> $pad  ${entity.render(tabs + 2)}"""
    }
    val attrsStr = if (pairs.isEmpty) "" else pairs.mkString(pad, s",\n$pad", s"\n$p")
    s"Map($attrsStr)"
  }

  def attrMap(tabs: Int = 0): String = {
    val p        = indent(tabs)
    val pad      = s"\n$p  "
    val attrData = for {
      segment <- segments
      entity <- segment.ents
      attr <- entity.attrs
    } yield {
      (s"${entity.ent}.${attr.attr}", attr.card, attr.baseTpe, attr.requiredAttrs)
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
      segment <- segments
      entity <- segment.ents
      attr <- entity.attrs if attr.options.exists(s => s == "unique" || s == "uniqueIdentity")
    } yield {
      s""""${entity.ent}.${attr.attr}""""
    }
    val attrsStr = if (attrs.isEmpty) "" else attrs.mkString("\n    ", s",\n    ", s"\n  ")
    s"List($attrsStr)"
  }
}


case class MetaSegment(
  segment: String,
  ents: Seq[MetaEntity]
) extends MetaModel {
  import BaseHelpers._
  def render(tabs: Int): String = {
    val p           = indent(tabs)
    val pad         = s"\n$p  "
    val entitiesStr = if (ents.isEmpty) "" else
      ents.map(_.render(tabs + 1)).mkString(pad, s",\n$pad", s"\n$p")
    s"""MetaSegment("$segment", Seq($entitiesStr))"""
  }

  override def toString: String = render(0)
}


case class MetaEntity(
  ent: String,
  attrs: Seq[MetaAttribute],
  backRefs: Seq[String] = Nil,
  mandatoryAttrs: Seq[String] = Nil,
  mandatoryRefs: Seq[(String, String)] = Nil
) extends MetaModel {
  import BaseHelpers._
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
        val ref           = o(attr.ref)
        val options       = sq(attr.options)
        val descr         = o(attr.description)
        val alias         = o(attr.alias)
        val requiredAttrs = sq(attr.requiredAttrs)
        val valueAttrs    = sq(attr.valueAttrs)
        val validations1  = renderValidations(attr.validations)
        s"""MetaAttribute($attr1, $card, $tpe, $ref, $options, $descr, $alias, $requiredAttrs, $valueAttrs, $validations1)"""
      }.mkString(pad, s",$pad", s"\n$p")
    }
    val backRefs1         = if (backRefs.isEmpty) "" else backRefs.mkString("\"", "\", \"", "\"")
    val mandatoryAttrsStr = if (mandatoryAttrs.isEmpty) "" else mandatoryAttrs.mkString("\"", "\", \"", "\"")
    val mandatoryRefsStr  = if (mandatoryRefs.isEmpty) "" else mandatoryRefs.map {
      case (attr, ref) => s"""\"$attr\" -> \"$ref\""""
    }.mkString(", ")
    s"""MetaEntity("$ent", Seq($attrsStr), Seq($backRefs1), Seq($mandatoryAttrsStr), Seq($mandatoryRefsStr))"""
  }

  override def toString: String = render(0)
}


case class MetaAttribute(
  attr: String,
  card: Card,
  baseTpe: String,
  ref: Option[String] = None,
  options: Seq[String] = Nil,
  description: Option[String] = None,
  alias: Option[String] = None,
  requiredAttrs: Seq[String] = Nil,
  valueAttrs: Seq[String] = Nil,
  validations: Seq[(String, String)] = Nil
) extends MetaModel {
  import BaseHelpers._
  override def toString: String = {
    val validations1 = renderValidations(validations)
    s"""MetaAttribute("$attr", $card, "$baseTpe", ${o(ref)}, ${sq(options)}, ${o(description)}, ${o(alias)}, ${sq(requiredAttrs)}, ${sq(valueAttrs)}, $validations1)"""
  }
}



