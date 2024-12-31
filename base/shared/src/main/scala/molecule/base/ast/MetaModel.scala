package molecule.base.ast

import molecule.base.util.BaseHelpers


sealed trait MetaModel

case class MetaDomain(
  pkg: String,
  domain: String,
  maxArity: Int,
  groups: Seq[MetaGroup]
) extends MetaModel with BaseHelpers {
  def render(tabs: Int = 0): String = {
    val p         = indent(tabs)
    val pad       = s"\n$p  "
    val groupsStr = if (groups.isEmpty) "" else groups.map(_.render(tabs + 1)).mkString(pad, s",\n\n$pad", s"\n$p")
    s"""MetaDomain("$pkg", "$domain", $maxArity, Seq($groupsStr))"""
  }

  override def toString: String = render(0)

  def entityMap(tabs: Int = 0): String = {
    val p        = indent(tabs)
    val pad      = s"\n$p  "
    val pairs    = for {
      group <- groups
      entity <- group.entities
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
      group <- groups
      entity <- group.entities
      attr <- entity.attrs
    } yield {
      (s"${entity.entity}.${attr.attribute}", attr.card, attr.baseTpe, attr.requiredAttrs)
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
      group <- groups
      entity <- group.entities
      attr <- entity.attrs if attr.options.exists(s => s == "unique" || s == "uniqueIdentity")
    } yield {
      s""""${entity.entity}.${attr.attribute}""""
    }
    val attrsStr = if (attrs.isEmpty) "" else attrs.mkString("\n    ", s",\n    ", s"\n  ")
    s"List($attrsStr)"
  }
}


case class MetaGroup(
  group: String,
  entities: Seq[MetaEntity]
) extends MetaModel with BaseHelpers {
  def render(tabs: Int): String = {
    val p           = indent(tabs)
    val pad         = s"\n$p  "
    val entitiesStr = if (entities.isEmpty) "" else
      entities.map(_.render(tabs + 1)).mkString(pad, s",\n$pad", s"\n$p")
    s"""MetaGroup("$group", Seq($entitiesStr))"""
  }

  override def toString: String = render(0)
}


case class MetaEntity(
  entity: String,
  attrs: Seq[MetaAttribute],
  backRefEntities: Seq[String] = Nil,
  mandatoryAttrs: Seq[String] = Nil,
  mandatoryRefs: Seq[(String, String)] = Nil
) extends MetaModel with BaseHelpers {
  def render(tabs: Int): String = {
    val maxAttr           = attrs.map(_.attribute.length).max
    val maxTpe            = attrs.map(_.baseTpe.length).max
    val attrsStr          = if (attrs.isEmpty) "" else {
      val p   = indent(tabs)
      val pad = s"\n$p  "
      attrs.map { attr =>
        val attr1         = "\"" + attr.attribute + "\"" + padS(maxAttr, attr.attribute)
        val card          = attr.card
        val tpe           = "\"" + attr.baseTpe + "\"" + padS(maxTpe, attr.baseTpe)
        val refNs         = o(attr.refEntity)
        val options       = sq(attr.options)
        val descr         = o(attr.description)
        val alias         = o(attr.alias)
        val requiredAttrs = sq(attr.requiredAttrs)
        val valueAttrs    = sq(attr.valueAttrs)
        val validations1  = renderValidations(attr.validations)
        s"""MetaAttribute($attr1, $card, $tpe, $refNs, $options, $descr, $alias, $requiredAttrs, $valueAttrs, $validations1)"""
      }.mkString(pad, s",$pad", s"\n$p")
    }
    val backRefs          = if (backRefEntities.isEmpty) "" else backRefEntities.mkString("\"", "\", \"", "\"")
    val mandatoryAttrsStr = if (mandatoryAttrs.isEmpty) "" else mandatoryAttrs.mkString("\"", "\", \"", "\"")
    val mandatoryRefsStr  = if (mandatoryRefs.isEmpty) "" else mandatoryRefs.map {
      case (attr, refNs) => s"""\"$attr\" -> \"$refNs\""""
    }.mkString(", ")
    s"""MetaEntity("$entity", Seq($attrsStr), Seq($backRefs), Seq($mandatoryAttrsStr), Seq($mandatoryRefsStr))"""
  }

  override def toString: String = render(0)
}


case class MetaAttribute(
  attribute: String,
  card: Card,
  baseTpe: String,
  refEntity: Option[String] = None,
  options: Seq[String] = Nil,
  description: Option[String] = None,
  alias: Option[String] = None,
  requiredAttrs: Seq[String] = Nil,
  valueAttrs: Seq[String] = Nil,
  validations: Seq[(String, String)] = Nil
) extends MetaModel with BaseHelpers {
  override def toString: String = {
    val validations1 = renderValidations(validations)
    s"""MetaAttribute("$attribute", $card, "$baseTpe", ${o(refEntity)}, ${sq(options)}, ${o(description)}, ${o(alias)}, ${sq(requiredAttrs)}, ${sq(valueAttrs)}, $validations1)"""
  }
}



