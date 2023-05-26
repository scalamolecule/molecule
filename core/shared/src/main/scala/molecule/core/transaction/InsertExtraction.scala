package molecule.core.transaction

import molecule.base.ast.SchemaAST._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ops.InsertOps
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class InsertExtraction extends InsertResolvers_ with InsertValidators_ { self: InsertOps =>

  private var curElements: List[Element]      = List.empty[Element]
  private val prevRefs   : ListBuffer[AnyRef] = ListBuffer.empty[AnyRef]

  @tailrec
  final override def resolve(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    resolvers: List[Product => Unit],
    outerTpl: Int,
    tplIndex: Int
  ): List[Product => Unit] = {
    if (resolvers.isEmpty)
      curElements = elements
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ModelError("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolve(nsMap, tail, resolvers :+
                  resolveAttrOneMan(a, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrOneOpt(a, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                  resolve(nsMap, tail, resolvers :+
                    resolveAttrSetMan(a, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrSetOpt(a, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx data part). Found: " + a
                )
              }
            case a          => throw new Exception("Attribute family not implemented for " + a)
          }

        case Ref(ns, refAttr, refNs, card, _) =>
          prevRefs += refAttr
          resolve(nsMap, tail, resolvers :+ addRef(ns, refAttr, refNs, card), outerTpl, tplIndex)

        case BackRef(backRefNs, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          resolve(nsMap, tail, resolvers :+ addBackRef(backRefNs), outerTpl, tplIndex)

        case Composite(compositeElements) =>
          curElements = compositeElements
          resolve(nsMap, tail, resolvers :+
            addComposite(nsMap, outerTpl, tplIndex, compositeElements), outerTpl + 1, tplIndex + 1)

        case Nested(Ref(ns, refAttr, refNs, _, _), nestedElements) =>
          curElements = nestedElements
          prevRefs.clear()
          resolve(nsMap, tail, resolvers :+
            addNested(nsMap, tplIndex, ns, refAttr, refNs, nestedElements), 0, tplIndex)

        case NestedOpt(Ref(ns, refAttr, refNs, _, _), nestedElements) =>
          curElements = nestedElements
          prevRefs.clear()
          resolve(nsMap, tail, resolvers :+
            addNested(nsMap, tplIndex, ns, refAttr, refNs, nestedElements), 0, tplIndex)

        // TxData is handed separately in Insert_stmts with call to save_stmts

        case other => throw ModelError("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManString     => addV(ns, attr, tplIndex, valueString)
      case _: AttrOneManInt        => addV(ns, attr, tplIndex, valueInt)
      case _: AttrOneManLong       => addV(ns, attr, tplIndex, valueLong)
      case _: AttrOneManFloat      => addV(ns, attr, tplIndex, valueFloat)
      case _: AttrOneManDouble     => addV(ns, attr, tplIndex, valueDouble)
      case _: AttrOneManBoolean    => addV(ns, attr, tplIndex, valueBoolean)
      case _: AttrOneManBigInt     => addV(ns, attr, tplIndex, valueBigInt)
      case _: AttrOneManBigDecimal => addV(ns, attr, tplIndex, valueBigDecimal)
      case _: AttrOneManDate       => addV(ns, attr, tplIndex, valueDate)
      case _: AttrOneManUUID       => addV(ns, attr, tplIndex, valueUUID)
      case _: AttrOneManURI        => addV(ns, attr, tplIndex, valueURI)
      case _: AttrOneManByte       => addV(ns, attr, tplIndex, valueByte)
      case _: AttrOneManShort      => addV(ns, attr, tplIndex, valueShort)
      case _: AttrOneManChar       => addV(ns, attr, tplIndex, valueChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptString     => addOptV(ns, attr, tplIndex, valueString)
      case _: AttrOneOptInt        => addOptV(ns, attr, tplIndex, valueInt)
      case _: AttrOneOptLong       => addOptV(ns, attr, tplIndex, valueLong)
      case _: AttrOneOptFloat      => addOptV(ns, attr, tplIndex, valueFloat)
      case _: AttrOneOptDouble     => addOptV(ns, attr, tplIndex, valueDouble)
      case _: AttrOneOptBoolean    => addOptV(ns, attr, tplIndex, valueBoolean)
      case _: AttrOneOptBigInt     => addOptV(ns, attr, tplIndex, valueBigInt)
      case _: AttrOneOptBigDecimal => addOptV(ns, attr, tplIndex, valueBigDecimal)
      case _: AttrOneOptDate       => addOptV(ns, attr, tplIndex, valueDate)
      case _: AttrOneOptUUID       => addOptV(ns, attr, tplIndex, valueUUID)
      case _: AttrOneOptURI        => addOptV(ns, attr, tplIndex, valueURI)
      case _: AttrOneOptByte       => addOptV(ns, attr, tplIndex, valueByte)
      case _: AttrOneOptShort      => addOptV(ns, attr, tplIndex, valueShort)
      case _: AttrOneOptChar       => addOptV(ns, attr, tplIndex, valueChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrSetManString     => addSet(ns, attr, tplIndex, valueString)
      case _: AttrSetManInt        => addSet(ns, attr, tplIndex, valueInt)
      case _: AttrSetManLong       => addSet(ns, attr, tplIndex, valueLong)
      case _: AttrSetManFloat      => addSet(ns, attr, tplIndex, valueFloat)
      case _: AttrSetManDouble     => addSet(ns, attr, tplIndex, valueDouble)
      case _: AttrSetManBoolean    => addSet(ns, attr, tplIndex, valueBoolean)
      case _: AttrSetManBigInt     => addSet(ns, attr, tplIndex, valueBigInt)
      case _: AttrSetManBigDecimal => addSet(ns, attr, tplIndex, valueBigDecimal)
      case _: AttrSetManDate       => addSet(ns, attr, tplIndex, valueDate)
      case _: AttrSetManUUID       => addSet(ns, attr, tplIndex, valueUUID)
      case _: AttrSetManURI        => addSet(ns, attr, tplIndex, valueURI)
      case _: AttrSetManByte       => addSet(ns, attr, tplIndex, valueByte)
      case _: AttrSetManShort      => addSet(ns, attr, tplIndex, valueShort)
      case _: AttrSetManChar       => addSet(ns, attr, tplIndex, valueChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrSetOptString     => addOptSet(ns, attr, tplIndex, valueString)
      case _: AttrSetOptInt        => addOptSet(ns, attr, tplIndex, valueInt)
      case _: AttrSetOptLong       => addOptSet(ns, attr, tplIndex, valueLong)
      case _: AttrSetOptFloat      => addOptSet(ns, attr, tplIndex, valueFloat)
      case _: AttrSetOptDouble     => addOptSet(ns, attr, tplIndex, valueDouble)
      case _: AttrSetOptBoolean    => addOptSet(ns, attr, tplIndex, valueBoolean)
      case _: AttrSetOptBigInt     => addOptSet(ns, attr, tplIndex, valueBigInt)
      case _: AttrSetOptBigDecimal => addOptSet(ns, attr, tplIndex, valueBigDecimal)
      case _: AttrSetOptDate       => addOptSet(ns, attr, tplIndex, valueDate)
      case _: AttrSetOptUUID       => addOptSet(ns, attr, tplIndex, valueUUID)
      case _: AttrSetOptURI        => addOptSet(ns, attr, tplIndex, valueURI)
      case _: AttrSetOptByte       => addOptSet(ns, attr, tplIndex, valueByte)
      case _: AttrSetOptShort      => addOptSet(ns, attr, tplIndex, valueShort)
      case _: AttrSetOptChar       => addOptSet(ns, attr, tplIndex, valueChar)
    }
  }
}