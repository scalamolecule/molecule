package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.util.ModelUtils
import scala.annotation.tailrec

class SaveExtraction(isTxMetaData: Boolean = false)
  extends ModelUtils with MoleculeLogging { self: SaveOps =>

  @tailrec
  final def resolve(elements: List[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          if (a.op != Appl) {
            throw MoleculeError("Missing applied value for attribute:\n" + a)
          }
          handleNs(a.ns)
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

        case Ref(ns, refAttr, _, _)       => ref(ns, refAttr); resolve(tail)
        case BackRef(backRefNs)           => backRef(backRefNs); resolve(tail)
        case _: Nested                    => throw MoleculeError(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                 => throw MoleculeError(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
        case Composite(compositeElements) =>
          // Start from initial entity id for each composite sub group
          handleComposite(isTxMetaData)
          resolve(compositeElements ++ tail)

        case TxMetaData(txElements) =>
          handleTxMetaData()
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
      case vs     => throw MoleculeError(
        s"Can only save one value for attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneMan(a: AttrOneMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneManString     => addV(ns, attr, oneV[String](ns, attr, a.vs, valueString))
      case a: AttrOneManInt        => addV(ns, attr, oneV[Int](ns, attr, a.vs, valueInt))
      case a: AttrOneManLong       => addV(ns, attr, oneV[Long](ns, attr, a.vs, valueLong))
      case a: AttrOneManFloat      => addV(ns, attr, oneV[Float](ns, attr, a.vs, valueFloat))
      case a: AttrOneManDouble     => addV(ns, attr, oneV[Double](ns, attr, a.vs, valueDouble))
      case a: AttrOneManBoolean    => addV(ns, attr, oneV[Boolean](ns, attr, a.vs, valueBoolean))
      case a: AttrOneManBigInt     => addV(ns, attr, oneV[BigInt](ns, attr, a.vs, valueBigInt))
      case a: AttrOneManBigDecimal => addV(ns, attr, oneV[BigDecimal](ns, attr, a.vs, valueBigDecimal))
      case a: AttrOneManDate       => addV(ns, attr, oneV[Date](ns, attr, a.vs, valueDate))
      case a: AttrOneManUUID       => addV(ns, attr, oneV[UUID](ns, attr, a.vs, valueUUID))
      case a: AttrOneManURI        => addV(ns, attr, oneV[URI](ns, attr, a.vs, valueURI))
      case a: AttrOneManByte       => addV(ns, attr, oneV[Byte](ns, attr, a.vs, valueByte))
      case a: AttrOneManShort      => addV(ns, attr, oneV[Short](ns, attr, a.vs, valueShort))
      case a: AttrOneManChar       => addV(ns, attr, oneV[Char](ns, attr, a.vs, valueChar))
    }
  }
  private def resolveAttrOneTac(a: AttrOneTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneTacString     => addV(ns, attr, oneV[String](ns, attr, a.vs, valueString))
      case a: AttrOneTacInt        => addV(ns, attr, oneV[Int](ns, attr, a.vs, valueInt))
      case a: AttrOneTacLong       => addV(ns, attr, oneV[Long](ns, attr, a.vs, valueLong))
      case a: AttrOneTacFloat      => addV(ns, attr, oneV[Float](ns, attr, a.vs, valueFloat))
      case a: AttrOneTacDouble     => addV(ns, attr, oneV[Double](ns, attr, a.vs, valueDouble))
      case a: AttrOneTacBoolean    => addV(ns, attr, oneV[Boolean](ns, attr, a.vs, valueBoolean))
      case a: AttrOneTacBigInt     => addV(ns, attr, oneV[BigInt](ns, attr, a.vs, valueBigInt))
      case a: AttrOneTacBigDecimal => addV(ns, attr, oneV[BigDecimal](ns, attr, a.vs, valueBigDecimal))
      case a: AttrOneTacDate       => addV(ns, attr, oneV[Date](ns, attr, a.vs, valueDate))
      case a: AttrOneTacUUID       => addV(ns, attr, oneV[UUID](ns, attr, a.vs, valueUUID))
      case a: AttrOneTacURI        => addV(ns, attr, oneV[URI](ns, attr, a.vs, valueURI))
      case a: AttrOneTacByte       => addV(ns, attr, oneV[Byte](ns, attr, a.vs, valueByte))
      case a: AttrOneTacShort      => addV(ns, attr, oneV[Short](ns, attr, a.vs, valueShort))
      case a: AttrOneTacChar       => addV(ns, attr, oneV[Char](ns, attr, a.vs, valueChar))
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
      case vs     => throw MoleculeError(
        s"Can only save one value for optional attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrOneOpt(a: AttrOneOpt): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrOneOptString     => addV(ns, attr, oneOptV[String](ns, attr, a.vs, valueString))
      case a: AttrOneOptInt        => addV(ns, attr, oneOptV[Int](ns, attr, a.vs, valueInt))
      case a: AttrOneOptLong       => addV(ns, attr, oneOptV[Long](ns, attr, a.vs, valueLong))
      case a: AttrOneOptFloat      => addV(ns, attr, oneOptV[Float](ns, attr, a.vs, valueFloat))
      case a: AttrOneOptDouble     => addV(ns, attr, oneOptV[Double](ns, attr, a.vs, valueDouble))
      case a: AttrOneOptBoolean    => addV(ns, attr, oneOptV[Boolean](ns, attr, a.vs, valueBoolean))
      case a: AttrOneOptBigInt     => addV(ns, attr, oneOptV[BigInt](ns, attr, a.vs, valueBigInt))
      case a: AttrOneOptBigDecimal => addV(ns, attr, oneOptV[BigDecimal](ns, attr, a.vs, valueBigDecimal))
      case a: AttrOneOptDate       => addV(ns, attr, oneOptV[Date](ns, attr, a.vs, valueDate))
      case a: AttrOneOptUUID       => addV(ns, attr, oneOptV[UUID](ns, attr, a.vs, valueUUID))
      case a: AttrOneOptURI        => addV(ns, attr, oneOptV[URI](ns, attr, a.vs, valueURI))
      case a: AttrOneOptByte       => addV(ns, attr, oneOptV[Byte](ns, attr, a.vs, valueByte))
      case a: AttrOneOptShort      => addV(ns, attr, oneOptV[Short](ns, attr, a.vs, valueShort))
      case a: AttrOneOptChar       => addV(ns, attr, oneOptV[Char](ns, attr, a.vs, valueChar))
    }
  }


  private def oneSet[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any
  ): Option[Set[Any]] = {
    sets match {
      case Seq(set)     => Some(set.map(transform))
      case Nil          => None
      case multipleSets => throw MoleculeError(
        s"Can only save one Set of values for Set attribute `$ns.$attr`. Found: " + multipleSets.mkString(", ")
      )
    }
  }
  private def resolveAttrSetMan(a: AttrSetMan): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetManString     => addSet(ns, attr, oneSet[String](ns, attr, a.vs, valueString))
      case a: AttrSetManInt        => addSet(ns, attr, oneSet[Int](ns, attr, a.vs, valueInt))
      case a: AttrSetManLong       => addSet(ns, attr, oneSet[Long](ns, attr, a.vs, valueLong))
      case a: AttrSetManFloat      => addSet(ns, attr, oneSet[Float](ns, attr, a.vs, valueFloat))
      case a: AttrSetManDouble     => addSet(ns, attr, oneSet[Double](ns, attr, a.vs, valueDouble))
      case a: AttrSetManBoolean    => addSet(ns, attr, oneSet[Boolean](ns, attr, a.vs, valueBoolean))
      case a: AttrSetManBigInt     => addSet(ns, attr, oneSet[BigInt](ns, attr, a.vs, valueBigInt))
      case a: AttrSetManBigDecimal => addSet(ns, attr, oneSet[BigDecimal](ns, attr, a.vs, valueBigDecimal))
      case a: AttrSetManDate       => addSet(ns, attr, oneSet[Date](ns, attr, a.vs, valueDate))
      case a: AttrSetManUUID       => addSet(ns, attr, oneSet[UUID](ns, attr, a.vs, valueUUID))
      case a: AttrSetManURI        => addSet(ns, attr, oneSet[URI](ns, attr, a.vs, valueURI))
      case a: AttrSetManByte       => addSet(ns, attr, oneSet[Byte](ns, attr, a.vs, valueByte))
      case a: AttrSetManShort      => addSet(ns, attr, oneSet[Short](ns, attr, a.vs, valueShort))
      case a: AttrSetManChar       => addSet(ns, attr, oneSet[Char](ns, attr, a.vs, valueChar))
    }
  }
  private def resolveAttrSetTac(a: AttrSetTac): Unit = {
    val (ns, attr) = (a.ns, a.attr)
    a match {
      case a: AttrSetTacString     => addSet(ns, attr, oneSet[String](ns, attr, a.vs, valueString))
      case a: AttrSetTacInt        => addSet(ns, attr, oneSet[Int](ns, attr, a.vs, valueInt))
      case a: AttrSetTacLong       => addSet(ns, attr, oneSet[Long](ns, attr, a.vs, valueLong))
      case a: AttrSetTacFloat      => addSet(ns, attr, oneSet[Float](ns, attr, a.vs, valueFloat))
      case a: AttrSetTacDouble     => addSet(ns, attr, oneSet[Double](ns, attr, a.vs, valueDouble))
      case a: AttrSetTacBoolean    => addSet(ns, attr, oneSet[Boolean](ns, attr, a.vs, valueBoolean))
      case a: AttrSetTacBigInt     => addSet(ns, attr, oneSet[BigInt](ns, attr, a.vs, valueBigInt))
      case a: AttrSetTacBigDecimal => addSet(ns, attr, oneSet[BigDecimal](ns, attr, a.vs, valueBigDecimal))
      case a: AttrSetTacDate       => addSet(ns, attr, oneSet[Date](ns, attr, a.vs, valueDate))
      case a: AttrSetTacUUID       => addSet(ns, attr, oneSet[UUID](ns, attr, a.vs, valueUUID))
      case a: AttrSetTacURI        => addSet(ns, attr, oneSet[URI](ns, attr, a.vs, valueURI))
      case a: AttrSetTacByte       => addSet(ns, attr, oneSet[Byte](ns, attr, a.vs, valueByte))
      case a: AttrSetTacShort      => addSet(ns, attr, oneSet[Short](ns, attr, a.vs, valueShort))
      case a: AttrSetTacChar       => addSet(ns, attr, oneSet[Char](ns, attr, a.vs, valueChar))
    }
  }


  private def oneOptSet[T](
    ns: String,
    attr: String,
    optSets: Option[Seq[Set[T]]],
    transform: T => Any
  ): Option[Set[Any]] = {
    optSets.flatMap {
      case Seq(set) => Some(set.map(transform))
      case Nil      => None
      case vs       => throw MoleculeError(
        s"Can only save one Set of values for optional Set attribute `$ns.$attr`. Found: " + vs.mkString(", ")
      )
    }
  }
  private def resolveAttrSetOpt(at: AttrSetOpt): Unit = {
    val (ns, attr) = (at.ns, at.attr)
    at match {
      case a: AttrSetOptString     => addSet(ns, attr, oneOptSet[String](ns, attr, a.vs, valueString))
      case a: AttrSetOptInt        => addSet(ns, attr, oneOptSet[Int](ns, attr, a.vs, valueInt))
      case a: AttrSetOptLong       => addSet(ns, attr, oneOptSet[Long](ns, attr, a.vs, valueLong))
      case a: AttrSetOptFloat      => addSet(ns, attr, oneOptSet[Float](ns, attr, a.vs, valueFloat))
      case a: AttrSetOptDouble     => addSet(ns, attr, oneOptSet[Double](ns, attr, a.vs, valueDouble))
      case a: AttrSetOptBoolean    => addSet(ns, attr, oneOptSet[Boolean](ns, attr, a.vs, valueBoolean))
      case a: AttrSetOptBigInt     => addSet(ns, attr, oneOptSet[BigInt](ns, attr, a.vs, valueBigInt))
      case a: AttrSetOptBigDecimal => addSet(ns, attr, oneOptSet[BigDecimal](ns, attr, a.vs, valueBigDecimal))
      case a: AttrSetOptDate       => addSet(ns, attr, oneOptSet[Date](ns, attr, a.vs, valueDate))
      case a: AttrSetOptUUID       => addSet(ns, attr, oneOptSet[UUID](ns, attr, a.vs, valueUUID))
      case a: AttrSetOptURI        => addSet(ns, attr, oneOptSet[URI](ns, attr, a.vs, valueURI))
      case a: AttrSetOptByte       => addSet(ns, attr, oneOptSet[Byte](ns, attr, a.vs, valueByte))
      case a: AttrSetOptShort      => addSet(ns, attr, oneOptSet[Short](ns, attr, a.vs, valueShort))
      case a: AttrSetOptChar       => addSet(ns, attr, oneOptSet[Char](ns, attr, a.vs, valueChar))
    }
  }
}