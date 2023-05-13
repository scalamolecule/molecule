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
                  resolveAttrOneMan(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrOneOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrOneTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val mandatory = nsMap(a.ns).mandatoryAttrs.contains(a.attr)
                  resolve(nsMap, tail, resolvers :+
                    resolveAttrSetMan(a, outerTpl, tplIndex, mandatory), outerTpl, tplIndex + 1)

                case a: AttrSetOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrSetOpt(a, outerTpl, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a          => throw new Exception("Attribute family not implemented for " + a)
          }

        case Ref(ns, refAttr, refNs, _, _) =>
          prevRefs += refAttr
          resolve(nsMap, tail, resolvers :+ addRef(ns, refAttr, refNs), outerTpl, tplIndex)

        case BackRef(backRefNs) =>
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

        // TxMetaData is handed separately in Insert_stmts with call to save_stmts

        case other => throw ModelError("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, outerTpl: Int, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManString     => addV(ns, attr, outerTpl, tplIndex, valueString)
      case _: AttrOneManInt        => addV(ns, attr, outerTpl, tplIndex, valueInt)
      case _: AttrOneManLong       => addV(ns, attr, outerTpl, tplIndex, valueLong)
      case _: AttrOneManFloat      => addV(ns, attr, outerTpl, tplIndex, valueFloat)
      case _: AttrOneManDouble     => addV(ns, attr, outerTpl, tplIndex, valueDouble)
      case _: AttrOneManBoolean    => addV(ns, attr, outerTpl, tplIndex, valueBoolean)
      case _: AttrOneManBigInt     => addV(ns, attr, outerTpl, tplIndex, valueBigInt)
      case _: AttrOneManBigDecimal => addV(ns, attr, outerTpl, tplIndex, valueBigDecimal)
      case _: AttrOneManDate       => addV(ns, attr, outerTpl, tplIndex, valueDate)
      case _: AttrOneManUUID       => addV(ns, attr, outerTpl, tplIndex, valueUUID)
      case _: AttrOneManURI        => addV(ns, attr, outerTpl, tplIndex, valueURI)
      case _: AttrOneManByte       => addV(ns, attr, outerTpl, tplIndex, valueByte)
      case _: AttrOneManShort      => addV(ns, attr, outerTpl, tplIndex, valueShort)
      case _: AttrOneManChar       => addV(ns, attr, outerTpl, tplIndex, valueChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, outerTpl: Int, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptString     => addOptV(ns, attr, outerTpl, tplIndex, valueString)
      case _: AttrOneOptInt        => addOptV(ns, attr, outerTpl, tplIndex, valueInt)
      case _: AttrOneOptLong       => addOptV(ns, attr, outerTpl, tplIndex, valueLong)
      case _: AttrOneOptFloat      => addOptV(ns, attr, outerTpl, tplIndex, valueFloat)
      case _: AttrOneOptDouble     => addOptV(ns, attr, outerTpl, tplIndex, valueDouble)
      case _: AttrOneOptBoolean    => addOptV(ns, attr, outerTpl, tplIndex, valueBoolean)
      case _: AttrOneOptBigInt     => addOptV(ns, attr, outerTpl, tplIndex, valueBigInt)
      case _: AttrOneOptBigDecimal => addOptV(ns, attr, outerTpl, tplIndex, valueBigDecimal)
      case _: AttrOneOptDate       => addOptV(ns, attr, outerTpl, tplIndex, valueDate)
      case _: AttrOneOptUUID       => addOptV(ns, attr, outerTpl, tplIndex, valueUUID)
      case _: AttrOneOptURI        => addOptV(ns, attr, outerTpl, tplIndex, valueURI)
      case _: AttrOneOptByte       => addOptV(ns, attr, outerTpl, tplIndex, valueByte)
      case _: AttrOneOptShort      => addOptV(ns, attr, outerTpl, tplIndex, valueShort)
      case _: AttrOneOptChar       => addOptV(ns, attr, outerTpl, tplIndex, valueChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, outerTpl: Int, tplIndex: Int, mandatory: Boolean): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrSetManString     => addSet(ns, attr, outerTpl, tplIndex, valueString)
      case _: AttrSetManInt        => addSet(ns, attr, outerTpl, tplIndex, valueInt)
      case _: AttrSetManLong       => addSet(ns, attr, outerTpl, tplIndex, valueLong)
      case _: AttrSetManFloat      => addSet(ns, attr, outerTpl, tplIndex, valueFloat)
      case _: AttrSetManDouble     => addSet(ns, attr, outerTpl, tplIndex, valueDouble)
      case _: AttrSetManBoolean    => addSet(ns, attr, outerTpl, tplIndex, valueBoolean)
      case _: AttrSetManBigInt     => addSet(ns, attr, outerTpl, tplIndex, valueBigInt)
      case _: AttrSetManBigDecimal => addSet(ns, attr, outerTpl, tplIndex, valueBigDecimal)
      case _: AttrSetManDate       => addSet(ns, attr, outerTpl, tplIndex, valueDate)
      case _: AttrSetManUUID       => addSet(ns, attr, outerTpl, tplIndex, valueUUID)
      case _: AttrSetManURI        => addSet(ns, attr, outerTpl, tplIndex, valueURI)
      case _: AttrSetManByte       => addSet(ns, attr, outerTpl, tplIndex, valueByte)
      case _: AttrSetManShort      => addSet(ns, attr, outerTpl, tplIndex, valueShort)
      case _: AttrSetManChar       => addSet(ns, attr, outerTpl, tplIndex, valueChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, outerTpl: Int, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrSetOptString     => addOptSet(ns, attr, outerTpl, tplIndex, valueString)
      case _: AttrSetOptInt        => addOptSet(ns, attr, outerTpl, tplIndex, valueInt)
      case _: AttrSetOptLong       => addOptSet(ns, attr, outerTpl, tplIndex, valueLong)
      case _: AttrSetOptFloat      => addOptSet(ns, attr, outerTpl, tplIndex, valueFloat)
      case _: AttrSetOptDouble     => addOptSet(ns, attr, outerTpl, tplIndex, valueDouble)
      case _: AttrSetOptBoolean    => addOptSet(ns, attr, outerTpl, tplIndex, valueBoolean)
      case _: AttrSetOptBigInt     => addOptSet(ns, attr, outerTpl, tplIndex, valueBigInt)
      case _: AttrSetOptBigDecimal => addOptSet(ns, attr, outerTpl, tplIndex, valueBigDecimal)
      case _: AttrSetOptDate       => addOptSet(ns, attr, outerTpl, tplIndex, valueDate)
      case _: AttrSetOptUUID       => addOptSet(ns, attr, outerTpl, tplIndex, valueUUID)
      case _: AttrSetOptURI        => addOptSet(ns, attr, outerTpl, tplIndex, valueURI)
      case _: AttrSetOptByte       => addOptSet(ns, attr, outerTpl, tplIndex, valueByte)
      case _: AttrSetOptShort      => addOptSet(ns, attr, outerTpl, tplIndex, valueShort)
      case _: AttrSetOptChar       => addOptSet(ns, attr, outerTpl, tplIndex, valueChar)
    }
  }
}