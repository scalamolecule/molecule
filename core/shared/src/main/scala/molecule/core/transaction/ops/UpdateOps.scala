package molecule.core.transaction.ops

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.Action2Data

trait UpdateOps extends Action2Data {

  def updateOne[T](
    ns: String,
    attr: String,
    vs: Seq[T],
    transformValue: T => Any,
    handleValue: T => Any
  ): Unit

  def updateSetEq[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String]
  ): Unit

  def updateSetAdd[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String]
  ): Unit

  def updateSetSwap[T](
    ns: String,
    attr: String,
    sets: Seq[Set[T]],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String]
  ): Unit

  def updateSetRemove[T](
    ns: String,
    attr: String,
    set: Set[T],
    transform: T => Any,
    handleValue: T => Any,
    refNs: Option[String],
    exts: List[String]
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

  protected lazy val extsString     = List("", "")
  protected lazy val extsInt        = List("", "")
  protected lazy val extsLong       = List("", "")
  protected lazy val extsFloat      = List("", "")
  protected lazy val extsDouble     = List("", "")
  protected lazy val extsBoolean    = List("", "")
  protected lazy val extsBigInt     = List("", "")
  protected lazy val extsBigDecimal = List("", "")
  protected lazy val extsDate       = List("", "")
  protected lazy val extsUUID       = List("", "")
  protected lazy val extsURI        = List("", "")
  protected lazy val extsByte       = List("", "")
  protected lazy val extsShort      = List("", "")
  protected lazy val extsChar       = List("", "")
}