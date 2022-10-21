package molecule.base.codeGeneration.parseDataModel

import molecule.base.util.BaseHelpers

object parserAST extends BaseHelpers {

  case class DataModelException(message: String) extends RuntimeException(message)

  case class ParseModel(pkg: String, maxArity: Int, domain: String,
                        curPart: String, curPartDescr: String, nss: Seq[Namespace]) {
    def addAttr(attr: DefAttr): ParseModel = {
      val previousNs = nss.init
      val lastNs     = nss.last
      copy(nss = previousNs :+ lastNs.copy(attrs = lastNs.attrs :+ attr))
    }
    override def toString: String =
      s"""ParseModel("$pkg", $maxArity, "$domain", "$curPart", "$curPartDescr", ${if (nss.isEmpty) "Nil" else nss.mkString("List(\n  ", ",\n  ", ")")})"""
  }

  case class Namespace(part: String, partDescr: Option[String], ns: String, nsDescr: Option[String],
                       opt: Option[Extension] = None, attrs: Seq[DefAttr] = Seq()) {
    override def toString: String =
      s"""Namespace("$part", ${o(partDescr)}, "$ns", ${o(nsDescr)}, ${o(opt)}, ${if (attrs.isEmpty) "Nil" else attrs.mkString("List(\n    ", ",\n    ", ")")})"""
  }

  sealed trait Extension
  case object Edge extends Extension

  sealed trait DefAttr {
    val attr     : String
    val card     : Int
    val clazz    : String
    val tpe      : String
    val baseTpe  : String
    val options  : Seq[Optional]
    val attrGroup: Option[String]
  }

  case class Val(attr: String, card: Int, clazz: String, tpe: String, baseTpe: String, datomicTpe: String,
                 options: Seq[Optional] = Seq(), bi: Option[String] = None, revRef: String = "",
                 attrGroup: Option[String] = None, alias: String = "") extends DefAttr {
    override def toString: String = s"""Val("$attr", $card, "$clazz", "$tpe", "$baseTpe", "$datomicTpe", ${sq(options)}, ${o(bi)}, "$revRef", ${o(attrGroup)})"""
  }

  case class Enum(attr: String, card: Int, clazz: String, tpe: String, baseTpe: String, enums: Seq[String],
                  options: Seq[Optional] = Seq(), bi: Option[String] = None, revRef: String = "",
                  attrGroup: Option[String] = None, alias: String = "") extends DefAttr {
    override def toString: String = s"""Enum("$attr", $card, "$clazz", "$tpe", "$baseTpe", ${sq(enums)}, ${sq(options)}, ${o(bi)}, "$revRef", ${o(attrGroup)})"""
  }

  case class Ref(attr: String, card: Int, clazz: String, clazz2: String, tpe: String, baseTpe: String, refNs: String,
                 options: Seq[Optional] = Seq(), bi: Option[String] = None, revRef: String = "",
                 attrGroup: Option[String] = None) extends DefAttr {
    override def toString: String = s"""Ref("$attr", $card, "$clazz", "$clazz2", "$tpe", "$baseTpe", "$refNs", ${sq(options)}, ${o(bi)}, "$revRef", ${o(attrGroup)})"""
  }

  case class BackRef(attr: String, card: Int, clazz: String, clazz2: String, tpe: String, baseTpe: String, backRefNs: String,
                     options: Seq[Optional] = Seq(), attrGroup: Option[String] = None) extends DefAttr {
    override def toString: String = s"""BackRef("$attr", "$clazz", "$clazz2", "$tpe", "$baseTpe", "$backRefNs", ${sq(options)}, ${o(attrGroup)})"""
  }

  case class Optional(datomicKeyValue: String, clazz: String) {
    override def toString: String = """Optional(""""" + s""""$datomicKeyValue""""" + s"""", "$clazz")"""
  }

}
