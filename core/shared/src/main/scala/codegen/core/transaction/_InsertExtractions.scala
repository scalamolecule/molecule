package codegen.core.transaction

import codegen.CoreGenBase

object _InsertExtractions extends CoreGenBase("InsertExtraction", "/transaction") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.core.transaction
       |
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.base.ast.SchemaAST.MetaNs
       |import molecule.base.error.{InsertError, ModelError}
       |import molecule.boilerplate.ast.Model._
       |import scala.annotation.tailrec
       |
       |
       |class InsertExtraction_ extends InsertResolvers_ { self: InsertOps =>
       |
       |  @tailrec
       |  final override def resolve(
       |    nsMap: Map[String, MetaNs],
       |    elements: List[Element],
       |    resolvers: List[Product => Seq[InsertError]],
       |    outerTpl: Int,
       |    tplIndex: Int
       |  ): List[Product => Seq[InsertError]] = {
       |    elements match {
       |      case element :: tail => element match {
       |        case a: Attr =>
       |          if (a.op != V) {
       |            throw ModelError("Can't insert attributes with an applied value. Found:\\n" + a)
       |          }
       |          a match {
       |            case a: AttrOne =>
       |              a match {
       |                case a: AttrOneMan => resolve(nsMap, tail, resolvers :+
       |                  resolveAttrOneMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |
       |                case a: AttrOneOpt => resolve(nsMap, tail, resolvers :+
       |                  resolveAttrOneOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |              }
       |            case a: AttrSet =>
       |              a match {
       |                case a: AttrSetMan =>
       |                  val mandatory = nsMap(a.ns).mandatory.contains(a.attr)
       |                  resolve(nsMap, tail, resolvers :+
       |                    resolveAttrSetMan(a, outerTpl, tplIndex, mandatory), outerTpl, tplIndex + 1)
       |
       |                case a: AttrSetOpt => resolve(nsMap, tail, resolvers :+
       |                  resolveAttrSetOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |              }
       |          }
       |
       |        case Ref(ns, refAttr, _, _) =>
       |          prevRefs += refAttr
       |          resolve(nsMap, tail, resolvers :+ addRef(ns, refAttr), outerTpl, tplIndex)
       |
       |        case BackRef(backRefNs) =>
       |          tail.head match {
       |            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
       |              s"Can't re-use previous namespace $${refAttr.capitalize} after backref _$$backRefNs."
       |            )
       |            case _                                                   => // ok
       |          }
       |          resolve(nsMap, tail, resolvers :+ addBackRef(backRefNs), outerTpl, tplIndex)
       |
       |        case Nested(Ref(ns, refAttr, _, _), nestedElements) =>
       |          prevRefs.clear()
       |          resolve(nsMap, tail, resolvers :+
       |            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), 0, tplIndex)
       |
       |        case NestedOpt(Ref(ns, refAttr, _, _), nestedElements) =>
       |          prevRefs.clear()
       |          resolve(nsMap, tail, resolvers :+
       |            addNested(nsMap, tplIndex, ns, refAttr, nestedElements), 0, tplIndex)
       |
       |        case Composite(compositeElements) =>
       |          resolve(nsMap, tail, resolvers :+
       |            addComposite(nsMap, outerTpl, tplIndex, compositeElements), outerTpl + 1, tplIndex + 1)
       |
       |        // TxMetaData is handed separately in Insert_stmts with call to save_stmts
       |
       |        case other => throw ModelError("Unexpected element: " + other)
       |      }
       |      case Nil             => resolvers
       |    }
       |  }
       |
       |
       |  private def resolveAttrOneMan(a: AttrOneMan, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
       |    val (ns, attr) = (a.ns, a.attr)
       |    a match {
       |      ${addOp("One", "Man", "addV")}
       |    }
       |  }
       |
       |
       |  private def resolveAttrOneOpt(a: AttrOneOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
       |    val (ns, attr) = (a.ns, a.attr)
       |    a match {
       |      ${addOp("One", "Opt", "addOptV")}
       |    }
       |  }
       |
       |
       |  private def resolveAttrSetMan(
       |    a: AttrSetMan,
       |    outerTpl: Int,
       |    tplIndex: Int,
       |    mandatory: Boolean
       |  ): Product => Seq[InsertError] = {
       |    val (ns, attr) = (a.ns, a.attr)
       |    a match {
       |      ${addOp("Set", "Man", "addSet")}
       |    }
       |  }
       |
       |
       |  private def resolveAttrSetOpt(a: AttrSetOpt, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
       |    val (ns, attr) = (a.ns, a.attr)
       |    a match {
       |      ${addOp("Set", "Opt", "addOptSet")}
       |    }
       |  }
       |}""".stripMargin
  }

  private def addOp(card: String, mode: String, op: String): String = {
    baseTypes.map { baseType =>
      val params = if (card + mode == "SetMan")
        s"ns, attr, outerTpl, tplIndex, mandatory, value$baseType, validate"
      else
        s"ns, attr, outerTpl, tplIndex, value$baseType, validate"

      s"""case at: Attr$card$mode$baseType =>
         |        val validate = at.validation.fold((_: $baseType) => Seq.empty[String])(validation =>
         |          (v: $baseType) => validation.validate(v)
         |        )
         |        $op($params)""".stripMargin
    }.mkString("\n\n      ")
  }
}
