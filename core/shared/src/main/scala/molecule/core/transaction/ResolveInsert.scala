package molecule.core.transaction

import molecule.base.ast._
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.ops.InsertOps
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class ResolveInsert extends InsertResolvers_ with InsertValidators_ { self: InsertOps =>

  private val prevRefs: ListBuffer[AnyRef] = ListBuffer.empty[AnyRef]

  @tailrec
  final override def resolve(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    resolvers: List[Product => Unit],
    outerTplIndex: Int,
    tplIndex: Int
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != V) {
            throw ModelError("Can't insert attributes with an applied value. Found:\n" + a)
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan =>
                  val attrOneManResolver = resolveAttrOneMan(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrOneManResolver, outerTplIndex, tplIndex + 1)

                case a: AttrOneOpt =>
                  val attrOneOptResolver = resolveAttrOneOpt(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrOneOptResolver, outerTplIndex, tplIndex + 1)

                case a: AttrOneTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan =>
                  val attrsSetManResolver = resolveAttrSetMan(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrsSetManResolver, outerTplIndex, tplIndex + 1)

                case a: AttrSetOpt =>
                  val attrSetOptResolver = resolveAttrSetOpt(a, tplIndex)
                  resolve(nsMap, tail, resolvers :+ attrSetOptResolver, outerTplIndex, tplIndex + 1)

                case a: AttrSetTac => throw new Exception(
                  "Can't use tacit attributes in insert molecule (except in tx meta data part). Found: " + a
                )
              }
          }

        case Ref(ns, refAttr, refNs, card) =>
          prevRefs += refAttr
          val refResolver = addRef(ns, refAttr, refNs, card)
          resolve(nsMap, tail, resolvers :+ refResolver, outerTplIndex, tplIndex)

        case BackRef(backRefNs, _) =>
          tail.head match {
            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw ModelError(
              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
            )
            case _                                                   => // ok
          }
          val backRefResolver = addBackRef(backRefNs)
          resolve(nsMap, tail, resolvers :+ backRefResolver, outerTplIndex, tplIndex)

        case Nested(Ref(ns, refAttr, refNs, _), nestedElements) =>
          prevRefs.clear()
          val nestedResolver = addNested(nsMap, tplIndex, ns, refAttr, refNs, nestedElements)
          resolve(nsMap, tail, resolvers :+ nestedResolver, 0, tplIndex)

        case NestedOpt(Ref(ns, refAttr, refNs, _), nestedElements) =>
          prevRefs.clear()
          val optNestedResolver = addNested(nsMap, tplIndex, ns, refAttr, refNs, nestedElements)
          resolve(nsMap, tail, resolvers :+ optNestedResolver, 0, tplIndex)
      }
      case Nil             => resolvers
    }
  }


  private def resolveAttrOneMan(a: AttrOneMan, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneManString     => addOne(ns, attr, tplIndex, transformString, handleString, extsString)
      case _: AttrOneManInt        => addOne(ns, attr, tplIndex, transformInt, handleInt, extsInt)
      case _: AttrOneManLong       => addOne(ns, attr, tplIndex, transformLong, handleLong, extsLong)
      case _: AttrOneManFloat      => addOne(ns, attr, tplIndex, transformFloat, handleFloat, extsFloat)
      case _: AttrOneManDouble     => addOne(ns, attr, tplIndex, transformDouble, handleDouble, extsDouble)
      case _: AttrOneManBoolean    => addOne(ns, attr, tplIndex, transformBoolean, handleBoolean, extsBoolean)
      case _: AttrOneManBigInt     => addOne(ns, attr, tplIndex, transformBigInt, handleBigInt, extsBigInt)
      case _: AttrOneManBigDecimal => addOne(ns, attr, tplIndex, transformBigDecimal, handleBigDecimal, extsBigDecimal)
      case _: AttrOneManDate       => addOne(ns, attr, tplIndex, transformDate, handleDate, extsDate)
      case _: AttrOneManUUID       => addOne(ns, attr, tplIndex, transformUUID, handleUUID, extsUUID)
      case _: AttrOneManURI        => addOne(ns, attr, tplIndex, transformURI, handleURI, extsURI)
      case _: AttrOneManByte       => addOne(ns, attr, tplIndex, transformByte, handleByte, extsByte)
      case _: AttrOneManShort      => addOne(ns, attr, tplIndex, transformShort, handleShort, extsShort)
      case _: AttrOneManChar       => addOne(ns, attr, tplIndex, transformChar, handleChar, extsChar)
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case _: AttrOneOptString     => addOneOpt(ns, attr, tplIndex, transformString, handleString, extsString)
      case _: AttrOneOptInt        => addOneOpt(ns, attr, tplIndex, transformInt, handleInt, extsInt)
      case _: AttrOneOptLong       => addOneOpt(ns, attr, tplIndex, transformLong, handleLong, extsLong)
      case _: AttrOneOptFloat      => addOneOpt(ns, attr, tplIndex, transformFloat, handleFloat, extsFloat)
      case _: AttrOneOptDouble     => addOneOpt(ns, attr, tplIndex, transformDouble, handleDouble, extsDouble)
      case _: AttrOneOptBoolean    => addOneOpt(ns, attr, tplIndex, transformBoolean, handleBoolean, extsBoolean)
      case _: AttrOneOptBigInt     => addOneOpt(ns, attr, tplIndex, transformBigInt, handleBigInt, extsBigInt)
      case _: AttrOneOptBigDecimal => addOneOpt(ns, attr, tplIndex, transformBigDecimal, handleBigDecimal, extsBigDecimal)
      case _: AttrOneOptDate       => addOneOpt(ns, attr, tplIndex, transformDate, handleDate, extsDate)
      case _: AttrOneOptUUID       => addOneOpt(ns, attr, tplIndex, transformUUID, handleUUID, extsUUID)
      case _: AttrOneOptURI        => addOneOpt(ns, attr, tplIndex, transformURI, handleURI, extsURI)
      case _: AttrOneOptByte       => addOneOpt(ns, attr, tplIndex, transformByte, handleByte, extsByte)
      case _: AttrOneOptShort      => addOneOpt(ns, attr, tplIndex, transformShort, handleShort, extsShort)
      case _: AttrOneOptChar       => addOneOpt(ns, attr, tplIndex, transformChar, handleChar, extsChar)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetManString     => addSet(ns, attr, set2arrayString, refNs, tplIndex, transformString, extsString)
      case _: AttrSetManInt        => addSet(ns, attr, set2arrayInt, refNs, tplIndex, transformInt, extsInt)
      case _: AttrSetManLong       => addSet(ns, attr, set2arrayLong, refNs, tplIndex, transformLong, extsLong)
      case _: AttrSetManFloat      => addSet(ns, attr, set2arrayFloat, refNs, tplIndex, transformFloat, extsFloat)
      case _: AttrSetManDouble     => addSet(ns, attr, set2arrayDouble, refNs, tplIndex, transformDouble, extsDouble)
      case _: AttrSetManBoolean    => addSet(ns, attr, set2arrayBoolean, refNs, tplIndex, transformBoolean, extsBoolean)
      case _: AttrSetManBigInt     => addSet(ns, attr, set2arrayBigInt, refNs, tplIndex, transformBigInt, extsBigInt)
      case _: AttrSetManBigDecimal => addSet(ns, attr, set2arrayBigDecimal, refNs, tplIndex, transformBigDecimal, extsBigDecimal)
      case _: AttrSetManDate       => addSet(ns, attr, set2arrayDate, refNs, tplIndex, transformDate, extsDate)
      case _: AttrSetManUUID       => addSet(ns, attr, set2arrayUUID, refNs, tplIndex, transformUUID, extsUUID)
      case _: AttrSetManURI        => addSet(ns, attr, set2arrayURI, refNs, tplIndex, transformURI, extsURI)
      case _: AttrSetManByte       => addSet(ns, attr, set2arrayByte, refNs, tplIndex, transformByte, extsByte)
      case _: AttrSetManShort      => addSet(ns, attr, set2arrayShort, refNs, tplIndex, transformShort, extsShort)
      case _: AttrSetManChar       => addSet(ns, attr, set2arrayChar, refNs, tplIndex, transformChar, extsChar)
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, tplIndex: Int): Product => Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case _: AttrSetOptString     => addSetOpt(ns, attr, set2arrayString, refNs, tplIndex, transformString, extsString)
      case _: AttrSetOptInt        => addSetOpt(ns, attr, set2arrayInt, refNs, tplIndex, transformInt, extsInt)
      case _: AttrSetOptLong       => addSetOpt(ns, attr, set2arrayLong, refNs, tplIndex, transformLong, extsLong)
      case _: AttrSetOptFloat      => addSetOpt(ns, attr, set2arrayFloat, refNs, tplIndex, transformFloat, extsFloat)
      case _: AttrSetOptDouble     => addSetOpt(ns, attr, set2arrayDouble, refNs, tplIndex, transformDouble, extsDouble)
      case _: AttrSetOptBoolean    => addSetOpt(ns, attr, set2arrayBoolean, refNs, tplIndex, transformBoolean, extsBoolean)
      case _: AttrSetOptBigInt     => addSetOpt(ns, attr, set2arrayBigInt, refNs, tplIndex, transformBigInt, extsBigInt)
      case _: AttrSetOptBigDecimal => addSetOpt(ns, attr, set2arrayBigDecimal, refNs, tplIndex, transformBigDecimal, extsBigDecimal)
      case _: AttrSetOptDate       => addSetOpt(ns, attr, set2arrayDate, refNs, tplIndex, transformDate, extsDate)
      case _: AttrSetOptUUID       => addSetOpt(ns, attr, set2arrayUUID, refNs, tplIndex, transformUUID, extsUUID)
      case _: AttrSetOptURI        => addSetOpt(ns, attr, set2arrayURI, refNs, tplIndex, transformURI, extsURI)
      case _: AttrSetOptByte       => addSetOpt(ns, attr, set2arrayByte, refNs, tplIndex, transformByte, extsByte)
      case _: AttrSetOptShort      => addSetOpt(ns, attr, set2arrayShort, refNs, tplIndex, transformShort, extsShort)
      case _: AttrSetOptChar       => addSetOpt(ns, attr, set2arrayChar, refNs, tplIndex, transformChar, extsChar)
    }
  }
}