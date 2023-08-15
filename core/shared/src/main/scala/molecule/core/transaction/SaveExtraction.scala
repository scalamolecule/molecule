package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class SaveExtraction(isTxMetaData: Boolean = false)
  extends ModelUtils with MoleculeLogging { self: SaveOps =>

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Eq) {
            throw ModelError(s"Missing applied value for attribute ${a.ns}.${a.attr}")
          }
          //          handleNs(a.ns)
          a match {
            case a: AttrOne  =>
              a match {
                case a: AttrOneMan => resolveAttrOneMan(a); resolve(tail)
                case a: AttrOneOpt => resolveAttrOneOpt(a); resolve(tail)
                case a: AttrOneTac => resolveAttrOneTac(a); resolve(tail)
              }
            case at: AttrSet =>
              at match {
                case a: AttrSetMan => resolveAttrSetMan(a); resolve(tail)
                case a: AttrSetOpt => resolveAttrSetOpt(a); resolve(tail)
                case a: AttrSetTac => resolveAttrSetTac(a); resolve(tail)
              }
          }

        // todo
        case Ref(ns, refAttr, "Tx", card, _) => resolve(tail)

        case Ref(ns, refAttr, refNs, card, _) => addRef(ns, refAttr, refNs, card); resolve(tail)
        case BackRef(backRefNs, _)            => addBackRef(backRefNs); resolve(tail)
        case _: Nested                        => throw ModelError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                     => throw ModelError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
        case Composite(compositeElements)     =>
          // Start from initial entity id for each composite sub group
          handleComposite(isTxMetaData)
          resolve(compositeElements ++ tail)

        case TxMetaData(txElements) =>
          handleTxMetaData(getInitialNs(txElements))
          resolve(txElements) // tail is empty (no more attributes possible after Tx)
      }
      case Nil             => ()
    }
  }

  private def oneV[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transform: T => Any
  ): Option[Any] = {
    vs match {
      case Seq(v) => Some(transform(v))
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManString     => addOne(ns, attr, oneV(ns, attr, a.vs, valueString))
      case a: AttrOneManInt        => addOne(ns, attr, oneV(ns, attr, a.vs, valueInt))
      case a: AttrOneManLong       => addOne(ns, attr, oneV(ns, attr, a.vs, valueLong))
      case a: AttrOneManFloat      => addOne(ns, attr, oneV(ns, attr, a.vs, valueFloat))
      case a: AttrOneManDouble     => addOne(ns, attr, oneV(ns, attr, a.vs, valueDouble))
      case a: AttrOneManBoolean    => addOne(ns, attr, oneV(ns, attr, a.vs, valueBoolean))
      case a: AttrOneManBigInt     => addOne(ns, attr, oneV(ns, attr, a.vs, valueBigInt))
      case a: AttrOneManBigDecimal => addOne(ns, attr, oneV(ns, attr, a.vs, valueBigDecimal))
      case a: AttrOneManDate       => addOne(ns, attr, oneV(ns, attr, a.vs, valueDate))
      case a: AttrOneManUUID       => addOne(ns, attr, oneV(ns, attr, a.vs, valueUUID))
      case a: AttrOneManURI        => addOne(ns, attr, oneV(ns, attr, a.vs, valueURI))
      case a: AttrOneManByte       => addOne(ns, attr, oneV(ns, attr, a.vs, valueByte))
      case a: AttrOneManShort      => addOne(ns, attr, oneV(ns, attr, a.vs, valueShort))
      case a: AttrOneManChar       => addOne(ns, attr, oneV(ns, attr, a.vs, valueChar))
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneTacString     => addOne(ns, attr, oneV(ns, attr, a.vs, valueString))
      case a: AttrOneTacInt        => addOne(ns, attr, oneV(ns, attr, a.vs, valueInt))
      case a: AttrOneTacLong       => addOne(ns, attr, oneV(ns, attr, a.vs, valueLong))
      case a: AttrOneTacFloat      => addOne(ns, attr, oneV(ns, attr, a.vs, valueFloat))
      case a: AttrOneTacDouble     => addOne(ns, attr, oneV(ns, attr, a.vs, valueDouble))
      case a: AttrOneTacBoolean    => addOne(ns, attr, oneV(ns, attr, a.vs, valueBoolean))
      case a: AttrOneTacBigInt     => addOne(ns, attr, oneV(ns, attr, a.vs, valueBigInt))
      case a: AttrOneTacBigDecimal => addOne(ns, attr, oneV(ns, attr, a.vs, valueBigDecimal))
      case a: AttrOneTacDate       => addOne(ns, attr, oneV(ns, attr, a.vs, valueDate))
      case a: AttrOneTacUUID       => addOne(ns, attr, oneV(ns, attr, a.vs, valueUUID))
      case a: AttrOneTacURI        => addOne(ns, attr, oneV(ns, attr, a.vs, valueURI))
      case a: AttrOneTacByte       => addOne(ns, attr, oneV(ns, attr, a.vs, valueByte))
      case a: AttrOneTacShort      => addOne(ns, attr, oneV(ns, attr, a.vs, valueShort))
      case a: AttrOneTacChar       => addOne(ns, attr, oneV(ns, attr, a.vs, valueChar))
    }
  }


  private def oneOptV[T](
    ns: String,
    attr: String,
    optVs: Option[Seq[T]],
    transform: T => Any
  ): Option[Any] = {
    optVs.flatMap {
      case Seq(v) => Some(transform(v))
      case Nil    => None
      case vs     => throw ExecutionError(
        s"Can only save one value for optional attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptString     => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueString))
      case a: AttrOneOptInt        => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueInt))
      case a: AttrOneOptLong       => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueLong))
      case a: AttrOneOptFloat      => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueFloat))
      case a: AttrOneOptDouble     => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueDouble))
      case a: AttrOneOptBoolean    => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueBoolean))
      case a: AttrOneOptBigInt     => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueBigInt))
      case a: AttrOneOptBigDecimal => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueBigDecimal))
      case a: AttrOneOptDate       => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueDate))
      case a: AttrOneOptUUID       => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueUUID))
      case a: AttrOneOptURI        => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueURI))
      case a: AttrOneOptByte       => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueByte))
      case a: AttrOneOptShort      => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueShort))
      case a: AttrOneOptChar       => addOne(ns, attr, oneOptV(ns, attr, a.vs, valueChar))
    }
  }


  private def oneSet[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
  ): Option[Set[T]] = {
    sets match {
      // Jdbc inserts untyped arrays so we don't need to resolve values here
      case Seq(set)     => Some(set)
      case Nil          => None
      case multipleSets => throw ExecutionError(
        s"Can only save one Set of values for Set attribute `$ns.$attr`. Found: " + multipleSets.mkString(", ")
      )
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManString     => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayString)
      case a: AttrSetManInt        => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayInt)
      case a: AttrSetManLong       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayLong)
      case a: AttrSetManFloat      => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayFloat)
      case a: AttrSetManDouble     => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayDouble)
      case a: AttrSetManBoolean    => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayBoolean)
      case a: AttrSetManBigInt     => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayBigInt)
      case a: AttrSetManBigDecimal => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayBigDecimal)
      case a: AttrSetManDate       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayDate)
      case a: AttrSetManUUID       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayUUID)
      case a: AttrSetManURI        => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayURI)
      case a: AttrSetManByte       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayByte)
      case a: AttrSetManShort      => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayShort)
      case a: AttrSetManChar       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayChar)
    }
  }
  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetTacString     => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayString)
      case a: AttrSetTacInt        => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayInt)
      case a: AttrSetTacLong       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayLong)
      case a: AttrSetTacFloat      => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayFloat)
      case a: AttrSetTacDouble     => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayDouble)
      case a: AttrSetTacBoolean    => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayBoolean)
      case a: AttrSetTacBigInt     => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayBigInt)
      case a: AttrSetTacBigDecimal => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayBigDecimal)
      case a: AttrSetTacDate       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayDate)
      case a: AttrSetTacUUID       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayUUID)
      case a: AttrSetTacURI        => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayURI)
      case a: AttrSetTacByte       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayByte)
      case a: AttrSetTacShort      => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayShort)
      case a: AttrSetTacChar       => addSet(ns, attr, oneSet(ns, attr, a.vs), set2arrayChar)
    }
  }

  private def oneOptSet[T](
    ns: String,
    attr: String,
    optSets: Option[Seq[Set[T]]],
  ): Option[Set[T]] = {
    optSets.flatMap {
      case Seq(set) => Some(set)
      case Nil      => None
      case vs       => throw ExecutionError(
        s"Can only save one Set of values for optional Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }

  private def resolveAttrSetOpt(at: AttrSetOpt): Unit = {
    val (ns, attr) = (at.ns, at.attr)
    at match {
      case a: AttrSetOptString     => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayString)
      case a: AttrSetOptInt        => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayInt)
      case a: AttrSetOptLong       => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayLong)
      case a: AttrSetOptFloat      => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayFloat)
      case a: AttrSetOptDouble     => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayDouble)
      case a: AttrSetOptBoolean    => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayBoolean)
      case a: AttrSetOptBigInt     => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayBigInt)
      case a: AttrSetOptBigDecimal => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayBigDecimal)
      case a: AttrSetOptDate       => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayDate)
      case a: AttrSetOptUUID       => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayUUID)
      case a: AttrSetOptURI        => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayURI)
      case a: AttrSetOptByte       => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayByte)
      case a: AttrSetOptShort      => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayShort)
      case a: AttrSetOptChar       => addSet(ns, attr, oneOptSet(ns, attr, a.vs), set2arrayChar)
    }
  }
}