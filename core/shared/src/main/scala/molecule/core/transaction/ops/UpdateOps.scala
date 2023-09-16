package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.Action2Data

trait UpdateOps extends Action2Data {

  def updateOne[T](
    a: AttrOne,
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit

  def updateSetEq[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String]
  ): Unit

  def updateSetAdd[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String]
  ): Unit

  def updateSetSwap[T](
    a: AttrSet,
    sets: Seq[Set[T]],
    transform: T => Any,
    handleValue: T => Any,
    dbType: String,
    refNs: Option[String]
  ): Unit

  def updateSetRemove[T](
    a: AttrSet,
    set: Set[T],
    transform: T => Any,
    handleValue: T => Any,
    dbType: String,
    refNs: Option[String]
  ): Unit

  protected def handleIds(ids: Seq[Long]): Unit
  protected def handleUniqueFilterAttr(filterAttr: AttrOneTac): Unit
  protected def handleFilterAttr(filterAttr: AttrOneTac): Unit

  protected def handleBackRef(backRef: BackRef): Unit
  protected def handleRefNs(ref: Ref): Unit

  protected lazy val transformString    : String => Any     = identity
  protected lazy val transformInt       : Int => Any        = identity
  protected lazy val transformLong      : Long => Any       = identity
  protected lazy val transformFloat     : Float => Any      = identity
  protected lazy val transformDouble    : Double => Any     = identity
  protected lazy val transformBoolean   : Boolean => Any    = identity
  protected lazy val transformBigInt    : BigInt => Any     = identity
  protected lazy val transformBigDecimal: BigDecimal => Any = identity
  protected lazy val transformDate      : Date => Any       = identity
  protected lazy val transformUUID      : UUID => Any       = identity
  protected lazy val transformURI       : URI => Any        = identity
  protected lazy val transformByte      : Byte => Any       = identity
  protected lazy val transformShort     : Short => Any      = identity
  protected lazy val transformChar      : Char => Any       = identity

  protected lazy val handleString    : Any => Any = identity
  protected lazy val handleInt       : Any => Any = identity
  protected lazy val handleLong      : Any => Any = identity
  protected lazy val handleFloat     : Any => Any = identity
  protected lazy val handleDouble    : Any => Any = identity
  protected lazy val handleBoolean   : Any => Any = identity
  protected lazy val handleBigInt    : Any => Any = identity
  protected lazy val handleBigDecimal: Any => Any = identity
  protected lazy val handleDate      : Any => Any = identity
  protected lazy val handleUUID      : Any => Any = identity
  protected lazy val handleURI       : Any => Any = identity
  protected lazy val handleByte      : Any => Any = identity
  protected lazy val handleShort     : Any => Any = identity
  protected lazy val handleChar      : Any => Any = identity

  protected lazy val set2arrayString    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayInt       : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayLong      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayFloat     : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayDouble    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBoolean   : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBigInt    : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayBigDecimal: Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayDate      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayUUID      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayURI       : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayByte      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayShort     : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]
  protected lazy val set2arrayChar      : Set[Any] => Array[AnyRef] = (_: Set[Any]) => Array.empty[AnyRef]

  protected lazy val dbTypeString    : String = ""
  protected lazy val dbTypeInt       : String = ""
  protected lazy val dbTypeLong      : String = ""
  protected lazy val dbTypeFloat     : String = ""
  protected lazy val dbTypeDouble    : String = ""
  protected lazy val dbTypeBoolean   : String = ""
  protected lazy val dbTypeBigInt    : String = ""
  protected lazy val dbTypeBigDecimal: String = ""
  protected lazy val dbTypeDate      : String = ""
  protected lazy val dbTypeUUID      : String = ""
  protected lazy val dbTypeURI       : String = ""
  protected lazy val dbTypeByte      : String = ""
  protected lazy val dbTypeShort     : String = ""
  protected lazy val dbTypeChar      : String = ""
}