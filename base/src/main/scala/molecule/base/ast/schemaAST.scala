package molecule.base.ast

import molecule.base.util.BaseHelpers

object schemaAST extends BaseHelpers {

  case class MetaSchema(
    pkg: String,
    domain: String,
    maxArity: Int,
    parts: Seq[MetaPart]
  ) {
    override def toString: String = {
      val partsStr = if (parts.isEmpty) "" else parts.mkString("\n  ", ",\n\n\n  ", "")
      s"""MetaSchema("$pkg", "$domain", $maxArity, Seq($partsStr))"""
    }

    def nsFull(part: String, ns: String): String = if (part.isEmpty) ns else part + "_ " + ns

    def nsMapValues: Map[String, MetaNs] = {
      (for {
        part <- parts
        ns <- part.nss
      } yield {
        nsFull(part.part, ns.ns) -> ns
      }).toMap
    }

    def nsMap: String = {
      val parts2 = parts.map(part =>
        part.nss.map(ns => s""""${nsFull(part.part, ns.ns)}" -> $ns""").mkString("\n    ", ",\n\n    ", "")
      )
      val nss    = if (parts2.isEmpty) "" else parts2.mkString(",\n\n      ")
      s"Map($nss)"
    }

    def attrMapValues: Map[String, (Int, String)] = {
      (for {
        part <- parts
        ns <- part.nss
        attr <- ns.attrs
      } yield {
        s":${nsFull(part.part, ns.ns)}/${attr.attr}" -> (attr.card, attr.tpe)
      }).toMap
    }

    def attrMap: String = {
      val attrData = for {
        part <- parts
        ns <- part.nss
        attr <- ns.attrs
      } yield {
        (s":${nsFull(part.part, ns.ns)}/${attr.attr}", attr.card, attr.tpe)
      }
      val maxSp    = attrData.map(_._1.length).max
      val attrs    = attrData.map {
        case (a, card, tpe) => s""""$a"${padS(maxSp, a)} -> ($card, "$tpe")"""
      }
      val attrsStr = if (attrs.isEmpty) "" else attrs.mkString("\n      ", ",\n      ", "")
      s"Map($attrsStr)"
    }
  }


  case class MetaPart(
    part: String,
    nss: Seq[MetaNs] = Nil
  ) {
    override def toString: String = {
      val nssStr = if (nss.isEmpty) "" else nss.mkString("\n    ", ",\n\n    ", "")
      s"""MetaPart("$part", Seq($nssStr))"""
    }
  }


  case class MetaNs(
    ns: String,
    attrs: Seq[MetaAttr] = Nil
  ) {
    override def toString: String = {
      val attrsStr = if (attrs.isEmpty) "" else attrs.mkString("\n      ", ",\n      ", "")
      s"""MetaNs("$ns", Seq($attrsStr))"""
    }
  }


  case class MetaAttr(
    attr: String,
    card: Int,
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

  //  case class TopValue(
  //    entityCount: Int,
  //    value: String,
  //    label: Option[String]
  //  ) {
  //    override def toString = s"""TopValue($entityCount, "$value", ${o(label)})"""
  //  }
  //
  //
  //  type FlatSchema = Seq[FlatAttr]
  //
  //  case class FlatAttr(
  //    pos: Int,
  //    part: String,
  //    partComment: Option[String],
  //    ns: String,
  //    nsFull: String,
  //    nsComment: Option[String],
  //    attr: String,
  //    card: Int,
  //    tpe: String,
  //    enums: Seq[String] = Nil,
  //    refNs: Option[String] = None,
  //    options: Seq[String] = Nil,
  //    descr: Option[String] = None,
  //    attrGroup: Option[String] = None,
  //    entityCount: Option[Int] = None,
  //    distinctValueCount: Option[Int] = None,
  //    comment: Option[String] = None,
  //    topValues: Seq[TopValue] = Nil
  //  ) {
  //    override def toString: String =
  //      s"""FlatAttr($pos, "$part", ${o(partComment)}, "$ns", "$nsFull", ${o(nsComment)}, "$attr", $card, "$tpe", """ +
  //        s"""${sq(enums)}, ${o(refNs)}, ${sq(options)}, ${o(descr)}, """ +
  //        s"""${o(attrGroup)}, ${o(entityCount)}, ${o(distinctValueCount)}, ${o(comment)}, Seq(${
  //          if (topValues.isEmpty) "" else topValues.mkString("\n        ", ",\n        ", "")
  //        }))"""
  //  }
}
