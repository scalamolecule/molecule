package molecule.core.transaction

import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class ResolveSave extends ModelUtils with MoleculeLogging { self: SaveOps =>

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Eq) {
            throw ModelError(s"Missing applied value for attribute ${a.ns}.${a.attr}")
          }
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
                case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
                case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
                case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
                case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
              }
          }

        case Ref(ns, refAttr, refNs, card, _) => addRef(ns, refAttr, refNs, card); resolve(tail)
        case BackRef(backRefNs, _)            => addBackRef(backRefNs); resolve(tail)
        case _: Nested                        => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                     => throw ModelError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
      }
      case Nil             => ()
    }
  }

  private def oneV[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any
  ): Option[Any] = {
    vs match {
      case Seq(v) => Some(transformValue(v))
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManString     => addOne(ns, attr, oneV(ns, attr, a.vs, transformString), handleString)
      case a: AttrOneManInt        => addOne(ns, attr, oneV(ns, attr, a.vs, transformInt), handleInt)
      case a: AttrOneManLong       => addOne(ns, attr, oneV(ns, attr, a.vs, transformLong), handleLong)
      case a: AttrOneManFloat      => addOne(ns, attr, oneV(ns, attr, a.vs, transformFloat), handleFloat)
      case a: AttrOneManDouble     => addOne(ns, attr, oneV(ns, attr, a.vs, transformDouble), handleDouble)
      case a: AttrOneManBoolean    => addOne(ns, attr, oneV(ns, attr, a.vs, transformBoolean), handleBoolean)
      case a: AttrOneManBigInt     => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigInt), handleBigInt)
      case a: AttrOneManBigDecimal => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigDecimal), handleBigDecimal)
      case a: AttrOneManDate       => addOne(ns, attr, oneV(ns, attr, a.vs, transformDate), handleDate)
      case a: AttrOneManUUID       => addOne(ns, attr, oneV(ns, attr, a.vs, transformUUID), handleUUID)
      case a: AttrOneManURI        => addOne(ns, attr, oneV(ns, attr, a.vs, transformURI), handleURI)
      case a: AttrOneManByte       => addOne(ns, attr, oneV(ns, attr, a.vs, transformByte), handleByte)
      case a: AttrOneManShort      => addOne(ns, attr, oneV(ns, attr, a.vs, transformShort), handleShort)
      case a: AttrOneManChar       => addOne(ns, attr, oneV(ns, attr, a.vs, transformChar), handleChar)
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneTacString     => addOne(ns, attr, oneV(ns, attr, a.vs, transformString), handleString)
      case a: AttrOneTacInt        => addOne(ns, attr, oneV(ns, attr, a.vs, transformInt), handleInt)
      case a: AttrOneTacLong       => addOne(ns, attr, oneV(ns, attr, a.vs, transformLong), handleLong)
      case a: AttrOneTacFloat      => addOne(ns, attr, oneV(ns, attr, a.vs, transformFloat), handleFloat)
      case a: AttrOneTacDouble     => addOne(ns, attr, oneV(ns, attr, a.vs, transformDouble), handleDouble)
      case a: AttrOneTacBoolean    => addOne(ns, attr, oneV(ns, attr, a.vs, transformBoolean), handleBoolean)
      case a: AttrOneTacBigInt     => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigInt), handleBigInt)
      case a: AttrOneTacBigDecimal => addOne(ns, attr, oneV(ns, attr, a.vs, transformBigDecimal), handleBigDecimal)
      case a: AttrOneTacDate       => addOne(ns, attr, oneV(ns, attr, a.vs, transformDate), handleDate)
      case a: AttrOneTacUUID       => addOne(ns, attr, oneV(ns, attr, a.vs, transformUUID), handleUUID)
      case a: AttrOneTacURI        => addOne(ns, attr, oneV(ns, attr, a.vs, transformURI), handleURI)
      case a: AttrOneTacByte       => addOne(ns, attr, oneV(ns, attr, a.vs, transformByte), handleByte)
      case a: AttrOneTacShort      => addOne(ns, attr, oneV(ns, attr, a.vs, transformShort), handleShort)
      case a: AttrOneTacChar       => addOne(ns, attr, oneV(ns, attr, a.vs, transformChar), handleChar)
    }
  }


  private def oneOptV[T](
    ns: String,
    attr: String,
    optVs: Option[Seq[T]],
    transformValue: T => Any
  ): Option[Any] = {
    optVs.flatMap {
      case Seq(v) => Some(transformValue(v))
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for optional attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptString     => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformString), handleString)
      case a: AttrOneOptInt        => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformInt), handleInt)
      case a: AttrOneOptLong       => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformLong), handleLong)
      case a: AttrOneOptFloat      => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformFloat), handleFloat)
      case a: AttrOneOptDouble     => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformDouble), handleDouble)
      case a: AttrOneOptBoolean    => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformBoolean), handleBoolean)
      case a: AttrOneOptBigInt     => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformBigInt), handleBigInt)
      case a: AttrOneOptBigDecimal => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformBigDecimal), handleBigDecimal)
      case a: AttrOneOptDate       => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformDate), handleDate)
      case a: AttrOneOptUUID       => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformUUID), handleUUID)
      case a: AttrOneOptURI        => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformURI), handleURI)
      case a: AttrOneOptByte       => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformByte), handleByte)
      case a: AttrOneOptShort      => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformShort), handleShort)
      case a: AttrOneOptChar       => addOne(ns, attr, oneOptV(ns, attr, a.vs, transformChar), handleChar)
    }
  }


  private def oneSet[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transformValue: T => Any
  ): Option[Set[Any]] = {
    sets match {
      case Seq(set)     => Some(set.map(transformValue))
      case Nil          => None
      case multipleSets => throw ExecutionError(
        s"Can only save one Set of values for Set attribute `$ns.$attr`. Found: " + multipleSets.mkString(", ")
      )
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetManString     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformString), handleString, set2arrayString, refNs)
      case a: AttrSetManInt        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformInt), handleInt, set2arrayInt, refNs)
      case a: AttrSetManLong       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLong), handleLong, set2arrayLong, refNs)
      case a: AttrSetManFloat      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformFloat), handleFloat, set2arrayFloat, refNs)
      case a: AttrSetManDouble     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDouble), handleDouble, set2arrayDouble, refNs)
      case a: AttrSetManBoolean    => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBoolean), handleBoolean, set2arrayBoolean, refNs)
      case a: AttrSetManBigInt     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigInt), handleBigInt, set2arrayBigInt, refNs)
      case a: AttrSetManBigDecimal => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, set2arrayBigDecimal, refNs)
      case a: AttrSetManDate       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDate), handleDate, set2arrayDate, refNs)
      case a: AttrSetManUUID       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformUUID), handleUUID, set2arrayUUID, refNs)
      case a: AttrSetManURI        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformURI), handleURI, set2arrayURI, refNs)
      case a: AttrSetManByte       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformByte), handleByte, set2arrayByte, refNs)
      case a: AttrSetManShort      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformShort), handleShort, set2arrayShort, refNs)
      case a: AttrSetManChar       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformChar), handleChar, set2arrayChar, refNs)
    }
  }
  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetTacString     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformString), handleString, set2arrayString, refNs)
      case a: AttrSetTacInt        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformInt), handleInt, set2arrayInt, refNs)
      case a: AttrSetTacLong       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformLong), handleLong, set2arrayLong, refNs)
      case a: AttrSetTacFloat      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformFloat), handleFloat, set2arrayFloat, refNs)
      case a: AttrSetTacDouble     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDouble), handleDouble, set2arrayDouble, refNs)
      case a: AttrSetTacBoolean    => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBoolean), handleBoolean, set2arrayBoolean, refNs)
      case a: AttrSetTacBigInt     => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigInt), handleBigInt, set2arrayBigInt, refNs)
      case a: AttrSetTacBigDecimal => addSet(ns, attr, oneSet(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, set2arrayBigDecimal, refNs)
      case a: AttrSetTacDate       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformDate), handleDate, set2arrayDate, refNs)
      case a: AttrSetTacUUID       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformUUID), handleUUID, set2arrayUUID, refNs)
      case a: AttrSetTacURI        => addSet(ns, attr, oneSet(ns, attr, a.vs, transformURI), handleURI, set2arrayURI, refNs)
      case a: AttrSetTacByte       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformByte), handleByte, set2arrayByte, refNs)
      case a: AttrSetTacShort      => addSet(ns, attr, oneSet(ns, attr, a.vs, transformShort), handleShort, set2arrayShort, refNs)
      case a: AttrSetTacChar       => addSet(ns, attr, oneSet(ns, attr, a.vs, transformChar), handleChar, set2arrayChar, refNs)
    }
  }

  private def oneOptSet[T](
    ns: String,
    attr: String,
    optSets: Option[Seq[Set[T]]],
    transformValue: T => Any
  ): Option[Set[Any]] = {
    optSets.flatMap {
      case Seq(set) => Some(set.map(transformValue))
      case Nil      => None
      case vs       => throw ExecutionError(
        s"Can only save one Set of values for optional Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt): Unit = {
    val (ns, attr, refNs) = (a.ns, a.attr, a.refNs)
    a match {
      case a: AttrSetOptString     => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformString), handleString, set2arrayString, refNs)
      case a: AttrSetOptInt        => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformInt), handleInt, set2arrayInt, refNs)
      case a: AttrSetOptLong       => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformLong), handleLong, set2arrayLong, refNs)
      case a: AttrSetOptFloat      => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformFloat), handleFloat, set2arrayFloat, refNs)
      case a: AttrSetOptDouble     => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformDouble), handleDouble, set2arrayDouble, refNs)
      case a: AttrSetOptBoolean    => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformBoolean), handleBoolean, set2arrayBoolean, refNs)
      case a: AttrSetOptBigInt     => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformBigInt), handleBigInt, set2arrayBigInt, refNs)
      case a: AttrSetOptBigDecimal => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformBigDecimal), handleBigDecimal, set2arrayBigDecimal, refNs)
      case a: AttrSetOptDate       => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformDate), handleDate, set2arrayDate, refNs)
      case a: AttrSetOptUUID       => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformUUID), handleUUID, set2arrayUUID, refNs)
      case a: AttrSetOptURI        => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformURI), handleURI, set2arrayURI, refNs)
      case a: AttrSetOptByte       => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformByte), handleByte, set2arrayByte, refNs)
      case a: AttrSetOptShort      => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformShort), handleShort, set2arrayShort, refNs)
      case a: AttrSetOptChar       => addSet(ns, attr, oneOptSet(ns, attr, a.vs, transformChar), handleChar, set2arrayChar, refNs)
    }
  }
}