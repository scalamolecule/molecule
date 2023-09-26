package molecule.core.transaction

import molecule.base.ast._
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.UpdateOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class ResolveUpdate(
  proxy: ConnProxy,
  val isUpsert: Boolean,
) extends ModelUtils { self: UpdateOps =>

  private val checkReservedKeywords = proxy.reserved.isDefined
  val update = if (isUpsert) "upsert" else "update"

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
                case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
                case _: AttrOneOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
              }

            case a: AttrSet =>
              a match {
                case a: AttrSetMan => a.op match {
                  case Eq     => resolveAttrSetMan(a); resolve(tail)
                  case Add    => resolveAttrSetAdd(a); resolve(tail)
                  case Swap   => resolveAttrSetSwap(a); resolve(tail)
                  case Remove => resolveAttrSetRemove(a); resolve(tail)
                  case _      => throw ModelError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
                }
                case _: AttrSetTac => throw ModelError("Can only lookup entity with card-one attribute value. Found:\n" + a)
                case _: AttrSetOpt => throw ModelError(s"Can't $update optional values. Found:\n" + a)
              }
          }

        case r@Ref(_, _, _, CardOne, _) => handleRefNs(r); resolve(tail)
        case br: BackRef                => handleBackRef(br); resolve(tail)

        case ref: Ref     => throw ModelError(
          s"Can't $update attributes in card-many referenced namespace `${ref.refAttr.capitalize}`"
        )
        case _: Nested    => throw ModelError(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw ModelError(s"Optional nested data structure not allowed in $update molecule.")
      }
      case Nil             => ()
    }
  }


  private def attrOneNames(a: Attr): (String, String) = {
    if (checkReservedKeywords) nonReservedAttr(a, proxy) else (a.ns, a.attr)
  }

  private def attrSetNames(a: Attr): (String, String, Option[String]) = {
    if (checkReservedKeywords) nonReservedAttrSet(a, proxy) else (a.ns, a.attr, a.refNs)
  }

  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = attrOneNames(a)
    a match {
      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      case a if a.op != Eq => throw ModelError(
        s"Can't $update attributes without an applied value. Found:\n" + a)

      case a: AttrOneManString     => updateOne(ns, attr, a.vs, transformString, handleString)
      case a: AttrOneManInt        => updateOne(ns, attr, a.vs, transformInt, handleInt)
      case a: AttrOneManLong       => updateOne(ns, attr, a.vs, transformLong, handleLong)
      case a: AttrOneManFloat      => updateOne(ns, attr, a.vs, transformFloat, handleFloat)
      case a: AttrOneManDouble     => updateOne(ns, attr, a.vs, transformDouble, handleDouble)
      case a: AttrOneManBoolean    => updateOne(ns, attr, a.vs, transformBoolean, handleBoolean)
      case a: AttrOneManBigInt     => updateOne(ns, attr, a.vs, transformBigInt, handleBigInt)
      case a: AttrOneManBigDecimal => updateOne(ns, attr, a.vs, transformBigDecimal, handleBigDecimal)
      case a: AttrOneManDate       => updateOne(ns, attr, a.vs, transformDate, handleDate)
      case a: AttrOneManUUID       => updateOne(ns, attr, a.vs, transformUUID, handleUUID)
      case a: AttrOneManURI        => updateOne(ns, attr, a.vs, transformURI, handleURI)
      case a: AttrOneManByte       => updateOne(ns, attr, a.vs, transformByte, handleByte)
      case a: AttrOneManShort      => updateOne(ns, attr, a.vs, transformShort, handleShort)
      case a: AttrOneManChar       => updateOne(ns, attr, a.vs, transformChar, handleChar)
    }
  }

  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ns, attr) = attrOneNames(a)
    a match {
      case AttrOneTacLong(_, "id", Eq, ids1, _, _, _, _, _, _, _) => handleIds(ids1)

      case a if a.attr == "id" => throw ModelError(
        s"Generic id attribute not allowed in update molecule. Found:\n" + a)

      case a if proxy.uniqueAttrs.contains(a.name) =>
        val a1 = if (checkReservedKeywords) {
          a match {
            case a: AttrOneTacString     => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacInt        => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacLong       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacFloat      => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacDouble     => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacBoolean    => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacBigInt     => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacBigDecimal => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacDate       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacUUID       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacURI        => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacByte       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacShort      => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacChar       => a.copy(ns = ns, attr = attr)
          }
        } else a
        handleUniqueFilterAttr(a1)

      case a =>
        val a1 = if (checkReservedKeywords) {
          a match {
            case a: AttrOneTacString     => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacInt        => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacLong       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacFloat      => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacDouble     => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacBoolean    => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacBigInt     => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacBigDecimal => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacDate       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacUUID       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacURI        => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacByte       => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacShort      => a.copy(ns = ns, attr = attr)
            case a: AttrOneTacChar       => a.copy(ns = ns, attr = attr)
          }
        } else a
        handleFilterAttr(a1)
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr) = attrOneNames(a)
    a match {
      case a: AttrSetManString     => updateSetEq(ns, attr, a.vs, transformString, set2arrayString, a.refNs, extsString)
      case a: AttrSetManInt        => updateSetEq(ns, attr, a.vs, transformInt, set2arrayInt, a.refNs, extsInt)
      case a: AttrSetManLong       => updateSetEq(ns, attr, a.vs, transformLong, set2arrayLong, a.refNs, extsLong)
      case a: AttrSetManFloat      => updateSetEq(ns, attr, a.vs, transformFloat, set2arrayFloat, a.refNs, extsFloat)
      case a: AttrSetManDouble     => updateSetEq(ns, attr, a.vs, transformDouble, set2arrayDouble, a.refNs, extsDouble)
      case a: AttrSetManBoolean    => updateSetEq(ns, attr, a.vs, transformBoolean, set2arrayBoolean, a.refNs, extsBoolean)
      case a: AttrSetManBigInt     => updateSetEq(ns, attr, a.vs, transformBigInt, set2arrayBigInt, a.refNs, extsBigInt)
      case a: AttrSetManBigDecimal => updateSetEq(ns, attr, a.vs, transformBigDecimal, set2arrayBigDecimal, a.refNs, extsBigDecimal)
      case a: AttrSetManDate       => updateSetEq(ns, attr, a.vs, transformDate, set2arrayDate, a.refNs, extsDate)
      case a: AttrSetManUUID       => updateSetEq(ns, attr, a.vs, transformUUID, set2arrayUUID, a.refNs, extsUUID)
      case a: AttrSetManURI        => updateSetEq(ns, attr, a.vs, transformURI, set2arrayURI, a.refNs, extsURI)
      case a: AttrSetManByte       => updateSetEq(ns, attr, a.vs, transformByte, set2arrayByte, a.refNs, extsByte)
      case a: AttrSetManShort      => updateSetEq(ns, attr, a.vs, transformShort, set2arrayShort, a.refNs, extsShort)
      case a: AttrSetManChar       => updateSetEq(ns, attr, a.vs, transformChar, set2arrayChar, a.refNs, extsChar)
    }
  }

  private def resolveAttrSetAdd(a: AttrSetMan): Unit = {
    val (ns, attr) = attrOneNames(a)
    a match {
      case a: AttrSetManString     => updateSetAdd(ns, attr, a.vs, transformString, set2arrayString, a.refNs, extsString)
      case a: AttrSetManInt        => updateSetAdd(ns, attr, a.vs, transformInt, set2arrayInt, a.refNs, extsInt)
      case a: AttrSetManLong       => updateSetAdd(ns, attr, a.vs, transformLong, set2arrayLong, a.refNs, extsLong)
      case a: AttrSetManFloat      => updateSetAdd(ns, attr, a.vs, transformFloat, set2arrayFloat, a.refNs, extsFloat)
      case a: AttrSetManDouble     => updateSetAdd(ns, attr, a.vs, transformDouble, set2arrayDouble, a.refNs, extsDouble)
      case a: AttrSetManBoolean    => updateSetAdd(ns, attr, a.vs, transformBoolean, set2arrayBoolean, a.refNs, extsBoolean)
      case a: AttrSetManBigInt     => updateSetAdd(ns, attr, a.vs, transformBigInt, set2arrayBigInt, a.refNs, extsBigInt)
      case a: AttrSetManBigDecimal => updateSetAdd(ns, attr, a.vs, transformBigDecimal, set2arrayBigDecimal, a.refNs, extsBigDecimal)
      case a: AttrSetManDate       => updateSetAdd(ns, attr, a.vs, transformDate, set2arrayDate, a.refNs, extsDate)
      case a: AttrSetManUUID       => updateSetAdd(ns, attr, a.vs, transformUUID, set2arrayUUID, a.refNs, extsUUID)
      case a: AttrSetManURI        => updateSetAdd(ns, attr, a.vs, transformURI, set2arrayURI, a.refNs, extsURI)
      case a: AttrSetManByte       => updateSetAdd(ns, attr, a.vs, transformByte, set2arrayByte, a.refNs, extsByte)
      case a: AttrSetManShort      => updateSetAdd(ns, attr, a.vs, transformShort, set2arrayShort, a.refNs, extsShort)
      case a: AttrSetManChar       => updateSetAdd(ns, attr, a.vs, transformChar, set2arrayChar, a.refNs, extsChar)
    }
  }

  private def resolveAttrSetSwap(a: AttrSetMan): Unit = {
    val (ns, attr) = attrOneNames(a)
    a match {
      case a: AttrSetManString     => updateSetSwap(ns, attr, a.vs, transformString, handleString, a.refNs, extsString)
      case a: AttrSetManInt        => updateSetSwap(ns, attr, a.vs, transformInt, handleInt, a.refNs, extsInt)
      case a: AttrSetManLong       => updateSetSwap(ns, attr, a.vs, transformLong, handleLong, a.refNs, extsLong)
      case a: AttrSetManFloat      => updateSetSwap(ns, attr, a.vs, transformFloat, handleFloat, a.refNs, extsFloat)
      case a: AttrSetManDouble     => updateSetSwap(ns, attr, a.vs, transformDouble, handleDouble, a.refNs, extsDouble)
      case a: AttrSetManBoolean    => updateSetSwap(ns, attr, a.vs, transformBoolean, handleBoolean, a.refNs, extsBoolean)
      case a: AttrSetManBigInt     => updateSetSwap(ns, attr, a.vs, transformBigInt, handleBigInt, a.refNs, extsBigInt)
      case a: AttrSetManBigDecimal => updateSetSwap(ns, attr, a.vs, transformBigDecimal, handleBigDecimal, a.refNs, extsBigDecimal)
      case a: AttrSetManDate       => updateSetSwap(ns, attr, a.vs, transformDate, handleDate, a.refNs, extsDate)
      case a: AttrSetManUUID       => updateSetSwap(ns, attr, a.vs, transformUUID, handleUUID, a.refNs, extsUUID)
      case a: AttrSetManURI        => updateSetSwap(ns, attr, a.vs, transformURI, handleURI, a.refNs, extsURI)
      case a: AttrSetManByte       => updateSetSwap(ns, attr, a.vs, transformByte, handleByte, a.refNs, extsByte)
      case a: AttrSetManShort      => updateSetSwap(ns, attr, a.vs, transformShort, handleShort, a.refNs, extsShort)
      case a: AttrSetManChar       => updateSetSwap(ns, attr, a.vs, transformChar, handleChar, a.refNs, extsChar)
    }
  }

  private def resolveAttrSetRemove(a: AttrSetMan): Unit = {
    val (ns, attr) = attrOneNames(a)
    a match {
      case a: AttrSetManString     => updateSetRemove(ns, attr, a.vs.head, transformString, handleString, a.refNs, extsString)
      case a: AttrSetManInt        => updateSetRemove(ns, attr, a.vs.head, transformInt, handleInt, a.refNs, extsInt)
      case a: AttrSetManLong       => updateSetRemove(ns, attr, a.vs.head, transformLong, handleLong, a.refNs, extsLong)
      case a: AttrSetManFloat      => updateSetRemove(ns, attr, a.vs.head, transformFloat, handleFloat, a.refNs, extsFloat)
      case a: AttrSetManDouble     => updateSetRemove(ns, attr, a.vs.head, transformDouble, handleDouble, a.refNs, extsDouble)
      case a: AttrSetManBoolean    => updateSetRemove(ns, attr, a.vs.head, transformBoolean, handleBoolean, a.refNs, extsBoolean)
      case a: AttrSetManBigInt     => updateSetRemove(ns, attr, a.vs.head, transformBigInt, handleBigInt, a.refNs, extsBigInt)
      case a: AttrSetManBigDecimal => updateSetRemove(ns, attr, a.vs.head, transformBigDecimal, handleBigDecimal, a.refNs, extsBigDecimal)
      case a: AttrSetManDate       => updateSetRemove(ns, attr, a.vs.head, transformDate, handleDate, a.refNs, extsDate)
      case a: AttrSetManUUID       => updateSetRemove(ns, attr, a.vs.head, transformUUID, handleUUID, a.refNs, extsUUID)
      case a: AttrSetManURI        => updateSetRemove(ns, attr, a.vs.head, transformURI, handleURI, a.refNs, extsURI)
      case a: AttrSetManByte       => updateSetRemove(ns, attr, a.vs.head, transformByte, handleByte, a.refNs, extsByte)
      case a: AttrSetManShort      => updateSetRemove(ns, attr, a.vs.head, transformShort, handleShort, a.refNs, extsShort)
      case a: AttrSetManChar       => updateSetRemove(ns, attr, a.vs.head, transformChar, handleChar, a.refNs, extsChar)
    }
  }
}