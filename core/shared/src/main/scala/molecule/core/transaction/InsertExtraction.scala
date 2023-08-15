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
    if (resolvers.isEmpty) {
      curElements = elements
    }
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
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  resolve(nsMap, tail, resolvers :+
                    resolveAttrSetMan(a, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetOpt => resolve(nsMap, tail, resolvers :+
                  resolveAttrSetOpt(a, tplIndex), outerTpl, tplIndex + 1)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
          }

        case Ref(ns, refAttr, refNs, card, _) =>
          prevRefs += refAttr
          resolve(nsMap, tail, resolvers :+ addRef(ns, refAttr, refNs, card), outerTpl, tplIndex)

        case BackRef(backRefNs, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                      => // ok
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

        // TxMetaData is handled separately in Insert_stmts with call to save_stmts

        case other => throw ModelError("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManString     => addOne(ns, attr, tplIndex, valueString)
      case _: AttrOneManInt        => addOne(ns, attr, tplIndex, valueInt)
      case _: AttrOneManLong       => addOne(ns, attr, tplIndex, valueLong)
      case _: AttrOneManFloat      => addOne(ns, attr, tplIndex, valueFloat)
      case _: AttrOneManDouble     => addOne(ns, attr, tplIndex, valueDouble)
      case _: AttrOneManBoolean    => addOne(ns, attr, tplIndex, valueBoolean)
      case _: AttrOneManBigInt     => addOne(ns, attr, tplIndex, valueBigInt)
      case _: AttrOneManBigDecimal => addOne(ns, attr, tplIndex, valueBigDecimal)
      case _: AttrOneManDate       => addOne(ns, attr, tplIndex, valueDate)
      case _: AttrOneManUUID       => addOne(ns, attr, tplIndex, valueUUID)
      case _: AttrOneManURI        => addOne(ns, attr, tplIndex, valueURI)
      case _: AttrOneManByte       => addOne(ns, attr, tplIndex, valueByte)
      case _: AttrOneManShort      => addOne(ns, attr, tplIndex, valueShort)
      case _: AttrOneManChar       => addOne(ns, attr, tplIndex, valueChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptString     => addOneOpt(ns, attr, tplIndex, valueString)
      case _: AttrOneOptInt        => addOneOpt(ns, attr, tplIndex, valueInt)
      case _: AttrOneOptLong       => addOneOpt(ns, attr, tplIndex, valueLong)
      case _: AttrOneOptFloat      => addOneOpt(ns, attr, tplIndex, valueFloat)
      case _: AttrOneOptDouble     => addOneOpt(ns, attr, tplIndex, valueDouble)
      case _: AttrOneOptBoolean    => addOneOpt(ns, attr, tplIndex, valueBoolean)
      case _: AttrOneOptBigInt     => addOneOpt(ns, attr, tplIndex, valueBigInt)
      case _: AttrOneOptBigDecimal => addOneOpt(ns, attr, tplIndex, valueBigDecimal)
      case _: AttrOneOptDate       => addOneOpt(ns, attr, tplIndex, valueDate)
      case _: AttrOneOptUUID       => addOneOpt(ns, attr, tplIndex, valueUUID)
      case _: AttrOneOptURI        => addOneOpt(ns, attr, tplIndex, valueURI)
      case _: AttrOneOptByte       => addOneOpt(ns, attr, tplIndex, valueByte)
      case _: AttrOneOptShort      => addOneOpt(ns, attr, tplIndex, valueShort)
      case _: AttrOneOptChar       => addOneOpt(ns, attr, tplIndex, valueChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetManString     => addSet(ns, attr, set2arrayString, refNs, tplIndex, valueString)
      case _: AttrSetManInt        => addSet(ns, attr, set2arrayInt, refNs, tplIndex, valueInt)
      case _: AttrSetManLong       => addSet(ns, attr, set2arrayLong, refNs, tplIndex, valueLong)
      case _: AttrSetManFloat      => addSet(ns, attr, set2arrayFloat, refNs, tplIndex, valueFloat)
      case _: AttrSetManDouble     => addSet(ns, attr, set2arrayDouble, refNs, tplIndex, valueDouble)
      case _: AttrSetManBoolean    => addSet(ns, attr, set2arrayBoolean, refNs, tplIndex, valueBoolean)
      case _: AttrSetManBigInt     => addSet(ns, attr, set2arrayBigInt, refNs, tplIndex, valueBigInt)
      case _: AttrSetManBigDecimal => addSet(ns, attr, set2arrayBigDecimal, refNs, tplIndex, valueBigDecimal)
      case _: AttrSetManDate       => addSet(ns, attr, set2arrayDate, refNs, tplIndex, valueDate)
      case _: AttrSetManUUID       => addSet(ns, attr, set2arrayUUID, refNs, tplIndex, valueUUID)
      case _: AttrSetManURI        => addSet(ns, attr, set2arrayURI, refNs, tplIndex, valueURI)
      case _: AttrSetManByte       => addSet(ns, attr, set2arrayByte, refNs, tplIndex, valueByte)
      case _: AttrSetManShort      => addSet(ns, attr, set2arrayShort, refNs, tplIndex, valueShort)
      case _: AttrSetManChar       => addSet(ns, attr, set2arrayChar, refNs, tplIndex, valueChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetOptString     => addSetOpt(ns, attr, set2arrayString, refNs, tplIndex, valueString)
      case _: AttrSetOptInt        => addSetOpt(ns, attr, set2arrayInt, refNs, tplIndex, valueInt)
      case _: AttrSetOptLong       => addSetOpt(ns, attr, set2arrayLong, refNs, tplIndex, valueLong)
      case _: AttrSetOptFloat      => addSetOpt(ns, attr, set2arrayFloat, refNs, tplIndex, valueFloat)
      case _: AttrSetOptDouble     => addSetOpt(ns, attr, set2arrayDouble, refNs, tplIndex, valueDouble)
      case _: AttrSetOptBoolean    => addSetOpt(ns, attr, set2arrayBoolean, refNs, tplIndex, valueBoolean)
      case _: AttrSetOptBigInt     => addSetOpt(ns, attr, set2arrayBigInt, refNs, tplIndex, valueBigInt)
      case _: AttrSetOptBigDecimal => addSetOpt(ns, attr, set2arrayBigDecimal, refNs, tplIndex, valueBigDecimal)
      case _: AttrSetOptDate       => addSetOpt(ns, attr, set2arrayDate, refNs, tplIndex, valueDate)
      case _: AttrSetOptUUID       => addSetOpt(ns, attr, set2arrayUUID, refNs, tplIndex, valueUUID)
      case _: AttrSetOptURI        => addSetOpt(ns, attr, set2arrayURI, refNs, tplIndex, valueURI)
      case _: AttrSetOptByte       => addSetOpt(ns, attr, set2arrayByte, refNs, tplIndex, valueByte)
      case _: AttrSetOptShort      => addSetOpt(ns, attr, set2arrayShort, refNs, tplIndex, valueShort)
      case _: AttrSetOptChar       => addSetOpt(ns, attr, set2arrayChar, refNs, tplIndex, valueChar)
    }
  }
}