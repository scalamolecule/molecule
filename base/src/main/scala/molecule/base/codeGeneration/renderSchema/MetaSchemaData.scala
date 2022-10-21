package molecule.base.codeGeneration.renderSchema

import molecule.base.codeGeneration.parseDataModel.parserAST._
import molecule.base.ast.schemaAST._
import molecule.base.codeGeneration.parseDataModel.parserAST.{BackRef, Enum, ParseModel, Ref, Val}
import molecule.base.util.{BaseHelpers, RegexMatching}


trait MetaSchemaData extends BaseHelpers with RegexMatching {

  def getMetaSchema(d: ParseModel): MetaSchema = {
//    var prevPart  = ""
//    var parts     = Seq(MetaPart(-1, "", None, None, Nil))
//    var nss       = Seq.empty[MetaNs]
//    var partIndex = -1
//    var nsIndex   = -1
//    var attrIndex = -1
//
//    d.nss.foreach {
//      case Namespace(part, partDescr, ns, nsDescr, _, attrs) =>
//        if (prevPart != part) {
//          // Start new partition with empty seq of nss
//          partIndex += 1
//          prevPart = part
//          parts = parts.init :+
//            parts.last.copy(nss = nss) :+
//            MetaPart(partIndex, part, partDescr, None, Nil)
//          nss = Seq.empty[MetaNs]
//        }
//
//        nsIndex += 1
//        attrIndex = -1
//        val metaAttrs = attrs.flatMap {
//          case _: BackRef => None
//          case a          =>
//            attrIndex += 1
//            val (attr, attrGroup)    = (a.attr, a.attrGroup)
//            val card                 = a.clazz.take(3) match {
//              case "One" => 1
//              case "Man" => 2
//              case "Map" => 3
//            }
//            val opts: Seq[String]    = a.options
//              .filterNot(o => o.datomicKeyValue.startsWith(":db/index") || o.datomicKeyValue.startsWith(":db/doc"))
//              .flatMap {
//                case Optional(r":db/unique\s+.*", opt) => Some(s"${opt.head.toLower}" + opt.tail)
//                case Optional(r":db/(.*)$opt\s+.*", _) => Some(opt.trim)
//                case Optional("alias", _)              => None
//                case other                             => throw DataModelException("Unexpected Optional: " + other)
//              }
//            val doc : Option[String] = opts.collectFirst {
//              case r":db/doc(.*)$txt" => txt.trim.tail.init
//            }
//            a match {
//              case v: Val  => card match {
//                case 1 => Some(MetaAttr(attrIndex, attr, card, v.tpe, Nil, None, opts, doc, attrGroup))
//                case _ => Some(MetaAttr(attrIndex, attr, card, v.baseTpe, Nil, None, opts, doc, attrGroup))
//              }
//              case e: Enum => Some(MetaAttr(attrIndex, attr, card, "enum", e.enums, None, opts, doc, attrGroup))
//              case r: Ref  => Some(MetaAttr(attrIndex, attr, card, "ref", Nil, Some(r.refNs), opts, doc, attrGroup))
//              case _       => None
//            }
//        }
//        val nsClean   = if (part == "db.part/user") ns else ns.split("_").last
//        nss = nss :+ MetaNs(nsIndex, nsClean, ns, nsDescr, None, metaAttrs)
//    }
//
//    // Add nss to last partition
//    MetaSchema((parts.init :+ parts.last.copy(nss = nss)).tail)

    MetaSchema("", "", Nil)
  }
}
