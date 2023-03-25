package codegen.core.transaction

import codegen.CoreGenBase

object _InsertExtractions extends CoreGenBase("InsertExtraction", "/transaction") {

  override val content = {
    s"""// GENERATED CODE ********************************
       |package molecule.core.transaction
       |
       |import java.net.URI
       |import java.util.{Date, UUID}
       |import molecule.base.util.exceptions.ExecutionError
       |import molecule.boilerplate.ast.Model._
       |import scala.annotation.tailrec
       |
       |
       |class ${fileName}_ extends InsertResolvers_ { self: InsertOps =>
       |
       |  @tailrec
       |  final override def resolve(
       |    elements: List[Element],
       |    resolvers: List[Product => Seq[InsertError]],
       |    outerTpl: Int,
       |    tplIndex: Int
       |  ): List[Product => Seq[InsertError]] = {
       |    elements match {
       |      case element :: tail => element match {
       |        case a: Attr =>
       |          if (a.op != V) {
       |            throw ExecutionError("Can't insert attributes with an applied value. Found:\\n" + a)
       |          }
       |          a match {
       |            case a: AttrOne =>
       |              a match {
       |                case a: AttrOneMan => resolve(tail, resolvers :+
       |                  resolveAttrOneMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |
       |                case a: AttrOneOpt => resolve(tail, resolvers :+
       |                  resolveAttrOneOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |              }
       |            case a: AttrSet =>
       |              a match {
       |                case a: AttrSetMan => resolve(tail, resolvers :+
       |                  resolveAttrSetMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |
       |                case a: AttrSetOpt => resolve(tail, resolvers :+
       |                  resolveAttrSetOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)
       |              }
       |          }
       |
       |        case Ref(ns, refAttr, _, _) =>
       |          prevRefs += refAttr
       |          resolve(tail, resolvers :+ addRef(ns, refAttr), outerTpl, tplIndex)
       |
       |        case BackRef(backRefNs) =>
       |          tail.head match {
       |            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ExecutionError(
       |              s"Can't re-use previous namespace $${refAttr.capitalize} after backref _$$backRefNs."
       |            )
       |            case _                                                   => // ok
       |          }
       |          resolve(tail, resolvers :+ addBackRef(backRefNs), outerTpl, tplIndex)
       |
       |        case Nested(Ref(ns, refAttr, _, _), nestedElements) =>
       |          prevRefs.clear()
       |          resolve(tail, resolvers :+
       |            addNested(tplIndex, ns, refAttr, nestedElements), 0, tplIndex)
       |
       |        case NestedOpt(Ref(ns, refAttr, _, _), nestedElements) =>
       |          prevRefs.clear()
       |          resolve(tail, resolvers :+
       |            addNested(tplIndex, ns, refAttr, nestedElements), 0, tplIndex)
       |
       |        case Composite(compositeElements) =>
       |          resolve(tail, resolvers :+
       |            addComposite(tplIndex, outerTpl + 1, compositeElements), outerTpl + 1, tplIndex + 1)
       |
       |        // TxMetaData is handed separately in Insert_stmts with call to save_stmts
       |
       |        case other => throw ExecutionError("Unexpected element: " + other)
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
       |  private def resolveAttrSetMan(a: AttrSetMan, outerTpl: Int, tplIndex: Int): Product => Seq[InsertError] = {
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
    baseTypes.map(baseType =>
      s"""case at: Attr$card$mode$baseType =>
         |        val validate = at.validation.fold((_: $baseType) => Seq.empty[String])(validation =>
         |          (v: $baseType) => validation.validate(v)
         |        )
         |        $op(ns, attr, outerTpl, tplIndex, value$baseType, validate)""".stripMargin
    ).mkString("\n\n      ")
  }
}
