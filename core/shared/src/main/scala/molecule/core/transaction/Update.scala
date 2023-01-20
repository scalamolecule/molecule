package molecule.core.transaction

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.base.util.exceptions.MoleculeError
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec

class Update(
  uniqueAttrs: List[String],
  val isUpsert: Boolean,
  val isMultiple: Boolean,
) { self: UpdateOps =>

  val update = if (isUpsert) "upsert" else "update"

  @tailrec
  final def resolve(
    elements: List[Element],
    eids: Seq[AnyRef],
    filterElements: List[Element],
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)]
  ): (Seq[AnyRef], List[Element], Seq[(String, String, String, Seq[AnyRef], Boolean)]) = {
    elements match {
      case element :: tail => element match {
        case a: Attr => a match {
          case a: AttrOneTac => oneTac(tail, eids, filterElements, data, a)
          case a: AttrOneMan => oneMan(tail, eids, filterElements, data, a)
          case a: AttrSetMan => a.op match {
            case Appl   => setApply(tail, eids, filterElements, data, a)
            case Add    => resolve(tail, eids, filterElements, data :+ setAdd(a))
            case Swap   => resolve(tail, eids, filterElements, data ++ setSwap(a))
            case Remove => resolve(tail, eids, filterElements, data ++ setRemove(a))
            case _      => throw MoleculeError(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
          }

          case _: AttrSetTac => throw MoleculeError("Can only lookup entity with card-one attribute value. Found:\n" + a)
          case _: AttrOneOpt => throw MoleculeError(s"Can't $update optional values. Found:\n" + a)
          case _: AttrSetOpt => throw MoleculeError(s"Can't $update optional values. Found:\n" + a)
          case _             => throw MoleculeError(s"Not implemented yet. Found:\n" + a)
        }

        case _: Nested    => throw MoleculeError(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw MoleculeError(s"Optional nested data structure not allowed in $update molecule.")

        case r@Ref(ns, attr, _, CardOne) => resolve(tail, eids, filterElements :+ r, data :+ ("ref", ns, attr, Nil, false))
        case r: Ref                      => throw MoleculeError(
          s"Can't $update attributes in card-many referenced namespaces. Found `${r.refAttr.capitalize}`"
        )
        case b: BackRef                  => resolve(tail, eids, filterElements :+ b, data)

        case Composite(es) =>
          val (eids1, filters1, data1) = extractSubElements(es)
          resolve(tail, eids ++ eids1, filterElements ++ filters1, data ++ data1)

        case TxMetaData(es) =>
          if (data.isEmpty)
            throw MoleculeError(s"Can't $update tx meta data only.")
          val (eids1, filters1, data1) = extractSubElements(es)
          resolve(tail, eids ++ eids1, filterElements ++ filters1, (data :+ ("tx", null, null, Nil, false)) ++ data1)
      }
      case Nil             => (eids, filterElements, data)
    }
  }


  private def oneMan(
    tail: List[Element],
    eids: Seq[AnyRef],
    filterElements: List[Element],
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)],
    dataAttr: AttrOneMan
  ): (Seq[AnyRef], List[Element], Seq[(String, String, String, Seq[AnyRef], Boolean)]) = {
    if (dataAttr.op != Appl)
      throw MoleculeError(s"Can't $update attributes without an applied value. Found:\n" + dataAttr)
    if (isUpsert) {
      // Disregard if value already exists
      resolve(tail, eids, filterElements, data :+ oneApply(dataAttr))
    } else {
      // Make sure current value exists
      val dummyFilterAttr = AttrOneTacInt(dataAttr.ns, dataAttr.attr, V, Nil, None, None, None)
      resolve(tail, eids, filterElements :+ dummyFilterAttr, data :+ oneApply(dataAttr))
    }
  }
  private def oneApply(attr: AttrOneMan): (String, String, String, Seq[AnyRef], Boolean) = attr match {
    case a if a.ns == "_Generic" => throw MoleculeError(
      s"Generic attributes not allowed in update molecule. Found:\n" + a)

    case AttrOneManString(_, _, _, vs, _, _, _)     => addOneV[String](attr, vs, valueString)
    case AttrOneManInt(_, _, _, vs, _, _, _)        => addOneV[Int](attr, vs, valueInt)
    case AttrOneManLong(_, _, _, vs, _, _, _, _)    => addOneV[Long](attr, vs, valueLong)
    case AttrOneManFloat(_, _, _, vs, _, _, _)      => addOneV[Float](attr, vs, valueFloat)
    case AttrOneManDouble(_, _, _, vs, _, _, _)     => addOneV[Double](attr, vs, valueDouble)
    case AttrOneManBoolean(_, _, _, vs, _, _, _)    => addOneV[Boolean](attr, vs, valueBoolean)
    case AttrOneManBigInt(_, _, _, vs, _, _, _)     => addOneV[BigInt](attr, vs, valueBigInt)
    case AttrOneManBigDecimal(_, _, _, vs, _, _, _) => addOneV[BigDecimal](attr, vs, valueBigDecimal)
    case AttrOneManDate(_, _, _, vs, _, _, _)       => addOneV[Date](attr, vs, valueDate)
    case AttrOneManUUID(_, _, _, vs, _, _, _)       => addOneV[UUID](attr, vs, valueUUID)
    case AttrOneManURI(_, _, _, vs, _, _, _)        => addOneV[URI](attr, vs, valueURI)
    case AttrOneManByte(_, _, _, vs, _, _, _)       => addOneV[Byte](attr, vs, valueByte)
    case AttrOneManShort(_, _, _, vs, _, _, _)      => addOneV[Short](attr, vs, valueShort)
    case AttrOneManChar(_, _, _, vs, _, _, _)       => addOneV[Char](attr, vs, valueChar)
  }
  private def addOneV[T](a: Attr, vs: Seq[T], transform: T => Any): (String, String, String, Seq[AnyRef], Boolean) = {
    vs match {
      case Seq(v) => ("add", a.ns, a.attr, Seq(transform(v).asInstanceOf[AnyRef]), false)
      case Nil    => ("retract", a.ns, a.attr, Nil, false)
      case vs     => throw MoleculeError(
        s"Can only $update one value for attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
  }

  private def oneTac(
    tail: List[Element],
    eids: Seq[AnyRef],
    filterElements: List[Element],
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)],
    filterAttr: AttrOneTac
  ): (Seq[AnyRef], List[Element], Seq[(String, String, String, Seq[AnyRef], Boolean)]) = {
    filterAttr match {
      case AttrOneTacLong("_Generic", "eids", Appl, eids1, _, _, _, _) =>
        if (eids.nonEmpty)
          throw MoleculeError(s"Can't apply entity ids twice in $update.")
        resolve(tail, eids1.asInstanceOf[Seq[AnyRef]], filterElements, data)

      case AttrOneTacLong("_Generic", "e", Appl, _, _, _, _, _) => throw MoleculeError(
        "Can't update by applying entity ids to e_")

      case a if a.ns == "_Generic" => throw MoleculeError(
        s"Generic attributes not allowed in update molecule. Found:\n" + a)

      case uniqueFilterAttr if uniqueAttrs.contains(uniqueFilterAttr.name) =>
        if (eids.nonEmpty)
          throw MoleculeError(
            s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
          )
        val lookupRefs = uniqueEids(filterAttr, uniqueFilterAttr.ns, uniqueFilterAttr.attr)
        resolve(tail, eids ++ lookupRefs, filterElements, data)

      case nonUniqueFilterAttr =>
        resolve(tail, eids, filterElements :+ nonUniqueFilterAttr, data)
    }
  }


  private def setApply(
    tail: List[Element],
    eids: Seq[AnyRef],
    filterElements: List[Element],
    data: Seq[(String, String, String, Seq[AnyRef], Boolean)],
    dataAttr: AttrSetMan
  ): (Seq[AnyRef], List[Element], Seq[(String, String, String, Seq[AnyRef], Boolean)]) = {
    if (isUpsert) {
      // Disregard if value already exists
      resolve(tail, eids, filterElements, data :+ setAdd(dataAttr, true))
    } else {
      // Make sure current value exists
      val dummyFilterAttr = AttrOneTacInt(dataAttr.ns, dataAttr.attr, V, Nil, None, None, None)
      resolve(tail, eids, filterElements :+ dummyFilterAttr, data :+ setAdd(dataAttr, true))
    }
  }


  private def setAdd(
    attr: AttrSetMan,
    retractCur: Boolean = false
  ): (String, String, String, Seq[AnyRef], Boolean) = attr match {
    case AttrSetManString(_, _, _, sets, _, _, _)     => addSetVs[String](attr, sets, valueString, retractCur)
    case AttrSetManInt(_, _, _, sets, _, _, _)        => addSetVs[Int](attr, sets, valueInt, retractCur)
    case AttrSetManLong(_, _, _, sets, _, _, _, _)    => addSetVs[Long](attr, sets, valueLong, retractCur)
    case AttrSetManFloat(_, _, _, sets, _, _, _)      => addSetVs[Float](attr, sets, valueFloat, retractCur)
    case AttrSetManDouble(_, _, _, sets, _, _, _)     => addSetVs[Double](attr, sets, valueDouble, retractCur)
    case AttrSetManBoolean(_, _, _, sets, _, _, _)    => addSetVs[Boolean](attr, sets, valueBoolean, retractCur)
    case AttrSetManBigInt(_, _, _, sets, _, _, _)     => addSetVs[BigInt](attr, sets, valueBigInt, retractCur)
    case AttrSetManBigDecimal(_, _, _, sets, _, _, _) => addSetVs[BigDecimal](attr, sets, valueBigDecimal, retractCur)
    case AttrSetManDate(_, _, _, sets, _, _, _)       => addSetVs[Date](attr, sets, valueDate, retractCur)
    case AttrSetManUUID(_, _, _, sets, _, _, _)       => addSetVs[UUID](attr, sets, valueUUID, retractCur)
    case AttrSetManURI(_, _, _, sets, _, _, _)        => addSetVs[URI](attr, sets, valueURI, retractCur)
    case AttrSetManByte(_, _, _, sets, _, _, _)       => addSetVs[Byte](attr, sets, valueByte, retractCur)
    case AttrSetManShort(_, _, _, sets, _, _, _)      => addSetVs[Short](attr, sets, valueShort, retractCur)
    case AttrSetManChar(_, _, _, sets, _, _, _)       => addSetVs[Char](attr, sets, valueChar, retractCur)
  }
  private def addSetVs[T](
    a: Attr,
    sets: Seq[Set[T]],
    transform: T => Any,
    retractCur: Boolean
  ): (String, String, String, Seq[AnyRef], Boolean) = {
    sets match {
      case Seq(set) => ("add", a.ns, a.attr, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, retractCur)
      case Nil      => ("retract", a.ns, a.attr, Nil, retractCur)
      case vs       => throw MoleculeError(
        s"Can only $update one Set of values for Set attribute `${a.name}`. Found: " + vs.mkString(", ")
      )
    }
  }


  private def setSwap(attr: AttrSetMan): Seq[(String, String, String, Seq[AnyRef], Boolean)] = attr match {
    case AttrSetManString(_, _, _, sets, _, _, _)     => swapSetVs[String](attr, sets, valueString)
    case AttrSetManInt(_, _, _, sets, _, _, _)        => swapSetVs[Int](attr, sets, valueInt)
    case AttrSetManLong(_, _, _, sets, _, _, _, _)    => swapSetVs[Long](attr, sets, valueLong)
    case AttrSetManFloat(_, _, _, sets, _, _, _)      => swapSetVs[Float](attr, sets, valueFloat)
    case AttrSetManDouble(_, _, _, sets, _, _, _)     => swapSetVs[Double](attr, sets, valueDouble)
    case AttrSetManBoolean(_, _, _, sets, _, _, _)    => swapSetVs[Boolean](attr, sets, valueBoolean)
    case AttrSetManBigInt(_, _, _, sets, _, _, _)     => swapSetVs[BigInt](attr, sets, valueBigInt)
    case AttrSetManBigDecimal(_, _, _, sets, _, _, _) => swapSetVs[BigDecimal](attr, sets, valueBigDecimal)
    case AttrSetManDate(_, _, _, sets, _, _, _)       => swapSetVs[Date](attr, sets, valueDate)
    case AttrSetManUUID(_, _, _, sets, _, _, _)       => swapSetVs[UUID](attr, sets, valueUUID)
    case AttrSetManURI(_, _, _, sets, _, _, _)        => swapSetVs[URI](attr, sets, valueURI)
    case AttrSetManByte(_, _, _, sets, _, _, _)       => swapSetVs[Byte](attr, sets, valueByte)
    case AttrSetManShort(_, _, _, sets, _, _, _)      => swapSetVs[Short](attr, sets, valueShort)
    case AttrSetManChar(_, _, _, sets, _, _, _)       => swapSetVs[Char](attr, sets, valueChar)
  }
  private def swapSetVs[T](
    a: Attr,
    sets: Seq[Set[T]],
    transform: T => Any
  ): Seq[(String, String, String, Seq[AnyRef], Boolean)] = {
    val (retracts0, adds0) = sets.splitAt(sets.length / 2)
    val (retracts, adds)   = (retracts0.flatten, adds0.flatten)
    if (retracts.length != retracts.distinct.length)
      throw MoleculeError(s"Can't swap from duplicate retract values.")

    if (adds.length != adds.distinct.length)
      throw MoleculeError(s"Can't swap to duplicate replacement values.")

    if (retracts.isEmpty) {
      Nil
    } else {
      if (retracts.size != adds.size)
        throw MoleculeError(
          s"Can't swap duplicate keys/values: "
        )
      Seq(
        ("retract", a.ns, a.attr, retracts.map(v => transform(v).asInstanceOf[AnyRef]), false),
        ("add", a.ns, a.attr, adds.map(v => transform(v).asInstanceOf[AnyRef]), false),
      )
    }
  }


  private def setRemove(attr: AttrSetMan): Seq[(String, String, String, Seq[AnyRef], Boolean)] = attr match {
    case AttrSetManString(_, _, _, Seq(set), _, _, _)     => removeSetVs[String](attr, set, valueString)
    case AttrSetManInt(_, _, _, Seq(set), _, _, _)        => removeSetVs[Int](attr, set, valueInt)
    case AttrSetManLong(_, _, _, Seq(set), _, _, _, _)    => removeSetVs[Long](attr, set, valueLong)
    case AttrSetManFloat(_, _, _, Seq(set), _, _, _)      => removeSetVs[Float](attr, set, valueFloat)
    case AttrSetManDouble(_, _, _, Seq(set), _, _, _)     => removeSetVs[Double](attr, set, valueDouble)
    case AttrSetManBoolean(_, _, _, Seq(set), _, _, _)    => removeSetVs[Boolean](attr, set, valueBoolean)
    case AttrSetManBigInt(_, _, _, Seq(set), _, _, _)     => removeSetVs[BigInt](attr, set, valueBigInt)
    case AttrSetManBigDecimal(_, _, _, Seq(set), _, _, _) => removeSetVs[BigDecimal](attr, set, valueBigDecimal)
    case AttrSetManDate(_, _, _, Seq(set), _, _, _)       => removeSetVs[Date](attr, set, valueDate)
    case AttrSetManUUID(_, _, _, Seq(set), _, _, _)       => removeSetVs[UUID](attr, set, valueUUID)
    case AttrSetManURI(_, _, _, Seq(set), _, _, _)        => removeSetVs[URI](attr, set, valueURI)
    case AttrSetManByte(_, _, _, Seq(set), _, _, _)       => removeSetVs[Byte](attr, set, valueByte)
    case AttrSetManShort(_, _, _, Seq(set), _, _, _)      => removeSetVs[Short](attr, set, valueShort)
    case AttrSetManChar(_, _, _, Seq(set), _, _, _)       => removeSetVs[Char](attr, set, valueChar)
    case _                                                => throw MoleculeError(
      s"Can only remove one Set of values for Set attribute `${attr.name}`. Found: $attr"
    )
  }
  private def removeSetVs[T](
    a: Attr,
    set: Set[T],
    transform: T => Any
  ): Seq[(String, String, String, Seq[AnyRef], Boolean)] = {
    if (set.isEmpty) Nil else Seq(("retract", a.ns, a.attr, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, false))
  }


  private def extractSubElements(
    elements: List[Element]
  ): (Seq[AnyRef], List[Element], Seq[(String, String, String, Seq[AnyRef], Boolean)]) = {
    resolve(elements, Nil, Nil, Nil)
  }

}