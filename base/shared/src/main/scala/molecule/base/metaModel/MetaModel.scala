package molecule.base.metaModel

import molecule.base.util.BaseHelpers._

case class MetaDomain(
  pkg: String,
  domain: String,
  maxArity: Int,
  segments: List[MetaSegment]
) {
  def render(tabs: Int = 0): String = {
    val p           = indent(tabs)
    val pad         = s"\n$p  "
    val segmentsStr = if (segments.isEmpty) "" else
      segments.map(_.render(tabs + 1)).mkString(pad, s",\n\n$pad", s"\n$p")
    s"""MetaDomain("$pkg", "$domain", $maxArity, List($segmentsStr))"""
  }

  override def toString: String = render(0)

  def entityMap(tabs: Int = 0): String = {
    val p        = indent(tabs)
    val pad      = s"\n$p  "
    val pairs    = for {
      segment <- segments
      entity <- segment.entities
    } yield {
      s""""${entity.entity}" -> $pad  ${entity.render(tabs + 2)}"""
    }
    val attrsStr = if (pairs.isEmpty) "" else pairs.mkString(pad, s",\n$pad", s"\n$p")
    s"Map($attrsStr)"
  }

  def attrMap(tabs: Int = 0): String = {
    val p        = indent(tabs)
    val pad      = s"\n$p  "
    val attrData = for {
      segment <- segments
      entity <- segment.entities
      attr <- entity.attributes
    } yield {
      (s"${entity.entity}.${attr.attribute}", attr.cardinality, attr.baseTpe, attr.requiredAttrs)
    }
    val maxSp    = attrData.map(_._1.length).max
    val attrs    = attrData.map {
      case (a, card, tpe, reqAttrs) =>
        val reqAttrsStr = reqAttrs.map(a => s""""$a"""").mkString(", ")
        s""""$a"${padS(maxSp, a)} -> ($card, "$tpe"${padS(14, tpe)}, List($reqAttrsStr))"""
    }
    val attrsStr = if (attrs.isEmpty) "" else attrs.mkString(pad, s",$pad", s"\n$p")
    s"Map($attrsStr)"
  }

  def uniqueAttrs: String = {
    val attrs    = for {
      segment <- segments
      entity <- segment.entities
      attr <- entity.attributes if attr.options.exists(s => s == "unique" || s == "uniqueIdentity")
    } yield {
      s""""${entity.entity}.${attr.attribute}""""
    }
    val attrsStr = if (attrs.isEmpty) "" else attrs.mkString("\n    ", s",\n    ", s"\n  ")
    s"List($attrsStr)"
  }
}


case class MetaSegment(
  segment: String,
  entities: List[MetaEntity]
) {
  def render(tabs: Int): String = {
    val p           = indent(tabs)
    val pad         = s"\n$p  "
    val entitiesStr = if (entities.isEmpty) "" else
      entities.map(_.render(tabs + 1)).mkString(pad, s",\n$pad", s"\n$p")
    s"""MetaSegment("$segment", List($entitiesStr))"""
  }

  override def toString: String = render(0)
}


case class MetaEntity(
  entity: String,
  attributes: List[MetaAttribute],
  backRefs: List[String] = Nil,
  mandatoryAttrs: List[String] = Nil,
  mandatoryRefs: List[(String, String)] = Nil,
  description: Option[String] = None
) {
  def render(tabs: Int): String = {
    val attrsStr          = if (attributes.isEmpty) "" else {
      val maxAttr = attributes.map(_.attribute.length).max
      val maxTpe  = attributes.map(_.baseTpe.length).max
      val p       = indent(tabs)
      val pad     = s"\n$p  "
      attributes.map { attr =>
        val attr1         = "\"" + attr.attribute + "\"" + padS(maxAttr, attr.attribute)
        val card          = attr.cardinality
        val tpe           = "\"" + attr.baseTpe + "\"" + padS(maxTpe, attr.baseTpe)
        val args          = list(attr.arguments)
        val ref           = o(attr.ref)
        val enumTpe       = o(attr.enumTpe)
        val options       = list(attr.options)
        val descr         = o(attr.description)
        val alias         = o(attr.alias)
        val requiredAttrs = list(attr.requiredAttrs)
        val valueAttrs    = list(attr.valueAttrs)
        val validations1  = renderValidations(attr.validations)
        s"""MetaAttribute($attr1, $card, $tpe, $args, $ref, $enumTpe, $options, $alias, $requiredAttrs, $valueAttrs, $validations1, $descr)"""
      }.mkString(pad, s",$pad", s"\n$p")
    }
    val backRefs1         = if (backRefs.isEmpty) "" else backRefs.mkString("\"", "\", \"", "\"")
    val mandatoryAttrsStr = if (mandatoryAttrs.isEmpty) "" else mandatoryAttrs.mkString("\"", "\", \"", "\"")
    val mandatoryRefsStr  = if (mandatoryRefs.isEmpty) "" else mandatoryRefs.map {
      case (attr, ref) => s"""\"$attr\" -> \"$ref\""""
    }.mkString(", ")
    s"""MetaEntity("$entity", List($attrsStr), List($backRefs1), List($mandatoryAttrsStr), List($mandatoryRefsStr), ${o(description)})"""
  }

  override def toString: String = render(0)
}


case class MetaAttribute(
  attribute: String,
  cardinality: Cardinality,
  baseTpe: String,
  arguments: List[MetaArgument] = Nil,
  ref: Option[String] = None,
  enumTpe: Option[String] = None,
  options: List[String] = Nil,
  alias: Option[String] = None,
  requiredAttrs: List[String] = Nil,
  valueAttrs: List[String] = Nil,
  validations: List[(String, String)] = Nil,
  description: Option[String] = None,
) {
  override def toString: String = {
    val validations1 = renderValidations(validations)
    s"""MetaAttribute("$attribute", $cardinality, "$baseTpe", ${list(arguments)}, ${o(ref)}, ${o(enumTpe)}, ${list(options)}, ${o(alias)}, ${list(requiredAttrs)}, ${list(valueAttrs)}, $validations1, ${o(description)})"""
  }
}

case class MetaArgument(
  argument: String,
  cardinality: Cardinality,
  baseTpe: String,
  mandatory: Boolean = false,
  defaultValue: Option[String] = None, // All values added as String
  description: Option[String] = None,
) {
  override def toString: String = {
    s"""MetaArgument("$argument", $cardinality, "$baseTpe", $mandatory, ${o(defaultValue)}, ${o(description)})"""
  }
}


