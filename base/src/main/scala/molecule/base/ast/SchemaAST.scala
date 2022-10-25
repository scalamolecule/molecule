package molecule.base.ast

import molecule.base.util.BaseHelpers

object SchemaAST extends BaseHelpers {

  sealed trait Cardinality {
    def marker: String
  }

  case object one extends Cardinality {
    override def marker = "One"
  }
  case object set extends Cardinality {
    override def marker = "Set"
  }
  case object array extends Cardinality {
    override def marker = "Array"
  }
  case object map extends Cardinality {
    override def marker = "Map"
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
      val p     = indent(tabs)
      val pad   = s"\n$p  "
      val pairs = for {
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
        (s":${ns.ns}/${attr.attr}", attr.card, attr.tpe)
      }
      val maxSp    = attrData.map(_._1.length).max
      val attrs    = attrData.map {
        case (a, card, tpe) => s""""$a"${padS(maxSp, a)} -> ($card, "$tpe")"""
      }
      val attrsStr = if (attrs.isEmpty) "" else attrs.mkString(pad, s",$pad", s"\n$p")
      s"Map($attrsStr)"
    }
  }


  case class MetaPart(
    part: String,
    nss: Seq[MetaNs] = Nil
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
    attrs: Seq[MetaAttr] = Nil
  ) {
    def render(tabs: Int): String = {
      val maxAttr  = attrs.map(_.attr.length).max
      val maxTpe   = attrs.map(_.tpe.length).max
      val attrsStr = if (attrs.isEmpty) "" else {
        val p   = indent(tabs)
        val pad = s"\n$p  "
        attrs.map { attr =>
          val attr1      = "\"" + attr.attr + "\"" + padS(maxAttr, attr.attr)
          val card       = attr.card
          val tpe        = "\"" + attr.tpe + "\"" + padS(maxTpe, attr.tpe)
          val refNs      = o(attr.refNs)
          val options    = sq(attr.options)
          val descr      = o(attr.descr)
          val alias      = o(attr.alias)
          val validation = oStr2(attr.validation)
          s"""MetaAttr($attr1, $card, $tpe, $refNs, $options, $descr, $alias, $validation)"""
        }.mkString(pad, s",$pad", s"\n$p")
      }
      s"""MetaNs("$ns", Seq($attrsStr))"""
    }

    override def toString: String = render(0)
  }


  case class MetaAttr(
    attr: String,
    card: Cardinality,
    tpe: String,
    refNs: Option[String] = None,
    options: Seq[String] = Nil,
    descr: Option[String] = None,
    alias: Option[String] = None,
    validation: Option[String] = None
  ) {
    override def toString: String = {
      s"""MetaAttr("$attr", $card, "$tpe", ${o(refNs)}, ${sq(options)}, ${o(descr)}, ${o(alias)}, ${oStr2(validation)})"""
    }
  }
}
