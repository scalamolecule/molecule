package molecule.db.datomic.transaction

import java.net.URI
import java.util.{Date, UUID}
import clojure.lang.Keyword
import datomic.Util.list
import molecule.base.ast.SchemaAST._
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.db.datomic.query.DatomicModel2Query
import scala.annotation.tailrec

class UpdateStmts(
  uniqueAttrs: List[String],
  elements: Seq[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean,
) extends DatomicTransactionBase(elements, isUpsert) {

  def resolve: (Seq[AnyRef], Option[String], Seq[AnyRef], Seq[(String, Keyword, Seq[AnyRef], Boolean)]) = {
    checkConflictingAttributes(elements, distinguishMode = true)

    val (eids, filterElements, data) = extract(elements, Nil, Nil, Nil)

    if (!isMultiple && eids.length > 1)
      multipleModifierMissing(eids.length)

    val (filterQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("_Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }
    (eids, filterQuery, inputs, data)
  }

  @tailrec
  final private def extract(
    elements: Seq[Element],
    eids: Seq[AnyRef],
    filterElements: Seq[Element],
    data: Seq[(String, Keyword, Seq[AnyRef], Boolean)]
  ): (Seq[AnyRef], Seq[Element], Seq[(String, Keyword, Seq[AnyRef], Boolean)]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr => attr match {
          case a: AttrOneTac => oneTac(tail, eids, filterElements, data, a)
          case a: AttrOneMan => oneMan(tail, eids, filterElements, data, a)
          case a: AttrSetMan => a.op match {
            case Appl   => setApply(tail, eids, filterElements, data, a)
            case Add    => extract(tail, eids, filterElements, data :+ setAdd(a))
            case Swap   => extract(tail, eids, filterElements, data ++ setSwap(a))
            case Remove => extract(tail, eids, filterElements, data ++ setRemove(a))
            case _      => throw MoleculeException(s"Unexpected $update operation for card-many attribute. Found:\n" + a)
          }

          case _: AttrSetTac => throw MoleculeException("Can only lookup entity with card-one attribute value. Found:\n" + attr)
          case _: AttrOneOpt => throw MoleculeException(s"Can't $update optional values. Found:\n" + attr)
          case _: AttrSetOpt => throw MoleculeException(s"Can't $update optional values. Found:\n" + attr)
          case _             => throw MoleculeException(s"Not implemented yet. Found:\n" + attr)
        }

        case _: Nested    => throw MoleculeException(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw MoleculeException(s"Optional nested data structure not allowed in $update molecule.")

        case r@Ref(ns, a, _, CardOne) => extract(tail, eids, filterElements :+ r, data :+ ("ref", kw(ns, a), Nil, false))
        case r: Ref                   => throw MoleculeException(
          s"Can't $update attributes in card-many referenced namespaces. Found `${r.refAttr.capitalize}`"
        )
        case b: BackRef               => extract(tail, eids, filterElements :+ b, data)

        case Composite(es) =>
          val (eids1, filters1, data1) = extractSubElements(es)
          extract(tail, eids ++ eids1, filterElements ++ filters1, data ++ data1)

        case TxMetaData(es) =>
          if (data.isEmpty)
            throw MoleculeException(s"Can't $update tx meta data only.")
          val (eids1, filters1, data1) = extractSubElements(es)
          extract(tail, eids ++ eids1, filterElements ++ filters1, (data :+ ("tx", null, Nil, false)) ++ data1)

        case element => unexpected(element)
      }
      case Nil             => (eids, filterElements, data)
    }
  }


  private def oneMan(
    tail: Seq[Element],
    eids: Seq[AnyRef],
    filterElements: Seq[Element],
    data: Seq[(String, Keyword, Seq[AnyRef], Boolean)],
    dataAttr: AttrOneMan
  ): (Seq[AnyRef], Seq[Element], Seq[(String, Keyword, Seq[AnyRef], Boolean)]) = {
    if (dataAttr.op != Appl)
      throw MoleculeException(s"Can't $update attributes without an applied value. Found:\n" + dataAttr)
    if (isUpsert) {
      // Disregard if value already exists
      extract(tail, eids, filterElements, data :+ oneApply(dataAttr))
    } else {
      // Make sure current value exists
      val dummyFilterAttr = AttrOneTacInt(dataAttr.ns, dataAttr.attr, V, Nil, None, None, None)
      extract(tail, eids, filterElements :+ dummyFilterAttr, data :+ oneApply(dataAttr))
    }
  }
  private def oneApply(attr: AttrOneMan): (String, Keyword, Seq[AnyRef], Boolean) = attr match {
    case a if a.ns == "_Generic" => throw MoleculeException(
      s"Generic attributes not allowed in update molecule. Found:\n" + a)

    case AttrOneManString(_, _, _, vs, _, _, _)     => addOneV[String](attr, vs, identity)
    case AttrOneManInt(_, _, _, vs, _, _, _)        => addOneV[Int](attr, vs, identity)
    case AttrOneManLong(_, _, _, vs, _, _, _, _)       => addOneV[Long](attr, vs, identity)
    case AttrOneManFloat(_, _, _, vs, _, _, _)      => addOneV[Float](attr, vs, identity)
    case AttrOneManDouble(_, _, _, vs, _, _, _)     => addOneV[Double](attr, vs, identity)
    case AttrOneManBoolean(_, _, _, vs, _, _, _)    => addOneV[Boolean](attr, vs, identity)
    case AttrOneManBigInt(_, _, _, vs, _, _, _)     => addOneV[BigInt](attr, vs, _.bigInteger)
    case AttrOneManBigDecimal(_, _, _, vs, _, _, _) => addOneV[BigDecimal](attr, vs, _.bigDecimal)
    case AttrOneManDate(_, _, _, vs, _, _, _)       => addOneV[Date](attr, vs, identity)
    case AttrOneManUUID(_, _, _, vs, _, _, _)       => addOneV[UUID](attr, vs, identity)
    case AttrOneManURI(_, _, _, vs, _, _, _)        => addOneV[URI](attr, vs, identity)
    case AttrOneManByte(_, _, _, vs, _, _, _)       => addOneV[Byte](attr, vs, _.toInt)
    case AttrOneManShort(_, _, _, vs, _, _, _)      => addOneV[Short](attr, vs, _.toInt)
    case AttrOneManChar(_, _, _, vs, _, _, _)       => addOneV[Char](attr, vs, _.toString)
    case _                                          => throw MoleculeException(
      s"Can only $update one applied value for attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }
  private def addOneV[T](attr: Attr, vs: Seq[T], transform: T => Any): (String, Keyword, Seq[AnyRef], Boolean) = {
    val a = kw(attr.ns, attr.attr)
    vs match {
      case Seq(v) => ("add", a, Seq(transform(v).asInstanceOf[AnyRef]), false)
      case Nil    => ("retract", a, Nil, false)
      case vs     => throw MoleculeException(
        s"Can only $update one value for attribute `${attr.name}`. Found: " + vs
      )
    }
  }

  private def oneTac(
    tail: Seq[Element],
    eids: Seq[AnyRef],
    filterElements: Seq[Element],
    data: Seq[(String, Keyword, Seq[AnyRef], Boolean)],
    filterAttr: AttrOneTac
  ): (Seq[AnyRef], Seq[Element], Seq[(String, Keyword, Seq[AnyRef], Boolean)]) = {
    filterAttr match {
      case AttrOneTacLong("_Generic", "eids", Appl, eids1, _, _, _, _) =>
        if (eids.nonEmpty)
          throw MoleculeException(s"Can't apply entity ids twice in $update.")
        extract(tail, eids1.asInstanceOf[Seq[AnyRef]], filterElements, data)

      case AttrOneTacLong("_Generic", "e", Appl, _, _, _, _, _) => throw MoleculeException(
        "Can't update by applying entity ids to e_")

      case a if a.ns == "_Generic" => throw MoleculeException(
        s"Generic attributes not allowed in update molecule. Found:\n" + a)

      case uniqueFilterAttr if uniqueAttrs.contains(uniqueFilterAttr.name) =>
        if (eids.nonEmpty)
          throw MoleculeException(
            s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
          )
        val at         = s":${uniqueFilterAttr.ns}/${uniqueFilterAttr.attr}"
        val lookupRefs = filterAttr match {
          case AttrOneTacString(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v))
          case AttrOneTacInt(_, _, _, vs, _, _, _)        => vs.map(v => list(at, v))
          case AttrOneTacLong(_, _, _, vs, _, _, _, _)       => vs.map(v => list(at, v))
          case AttrOneTacFloat(_, _, _, vs, _, _, _)      => vs.map(v => list(at, v))
          case AttrOneTacDouble(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v))
          case AttrOneTacBoolean(_, _, _, vs, _, _, _)    => vs.map(v => list(at, v))
          case AttrOneTacBigInt(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v.bigInteger))
          case AttrOneTacBigDecimal(_, _, _, vs, _, _, _) => vs.map(v => list(at, v.bigDecimal))
          case AttrOneTacDate(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v))
          case AttrOneTacUUID(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v))
          case AttrOneTacURI(_, _, _, vs, _, _, _)        => vs.map(v => list(at, v))
          case AttrOneTacByte(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v.toInt))
          case AttrOneTacShort(_, _, _, vs, _, _, _)      => vs.map(v => list(at, v.toInt))
          case AttrOneTacChar(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v.toString))
        }
        extract(tail, eids ++ lookupRefs, filterElements, data)

      case nonUniqueFilterAttr =>
        extract(tail, eids, filterElements :+ nonUniqueFilterAttr, data)
    }
  }


  private def setApply(
    tail: Seq[Element],
    eids: Seq[AnyRef],
    filterElements: Seq[Element],
    data: Seq[(String, Keyword, Seq[AnyRef], Boolean)],
    dataAttr: AttrSetMan
  ): (Seq[AnyRef], Seq[Element], Seq[(String, Keyword, Seq[AnyRef], Boolean)]) = {
    if (isUpsert) {
      // Disregard if value already exists
      extract(tail, eids, filterElements, data :+ setAdd(dataAttr, true))
    } else {
      // Make sure current value exists
      val dummyFilterAttr = AttrOneTacInt(dataAttr.ns, dataAttr.attr, V, Nil, None, None, None)
      extract(tail, eids, filterElements :+ dummyFilterAttr, data :+ setAdd(dataAttr, true))
    }
  }


  private def setAdd(
    attr: AttrSetMan,
    retractCur: Boolean = false
  ): (String, Keyword, Seq[AnyRef], Boolean) = attr match {
    case AttrSetManString(_, _, _, sets, _, _, _)     => addSetVs[String](attr, sets, identity, retractCur)
    case AttrSetManInt(_, _, _, sets, _, _, _)        => addSetVs[Int](attr, sets, identity, retractCur)
    case AttrSetManLong(_, _, _, sets, _, _, _, _)       => addSetVs[Long](attr, sets, identity, retractCur)
    case AttrSetManFloat(_, _, _, sets, _, _, _)      => addSetVs[Float](attr, sets, identity, retractCur)
    case AttrSetManDouble(_, _, _, sets, _, _, _)     => addSetVs[Double](attr, sets, identity, retractCur)
    case AttrSetManBoolean(_, _, _, sets, _, _, _)    => addSetVs[Boolean](attr, sets, identity, retractCur)
    case AttrSetManBigInt(_, _, _, sets, _, _, _)     => addSetVs[BigInt](attr, sets, _.bigInteger, retractCur)
    case AttrSetManBigDecimal(_, _, _, sets, _, _, _) => addSetVs[BigDecimal](attr, sets, _.bigDecimal, retractCur)
    case AttrSetManDate(_, _, _, sets, _, _, _)       => addSetVs[Date](attr, sets, identity, retractCur)
    case AttrSetManUUID(_, _, _, sets, _, _, _)       => addSetVs[UUID](attr, sets, identity, retractCur)
    case AttrSetManURI(_, _, _, sets, _, _, _)        => addSetVs[URI](attr, sets, identity, retractCur)
    case AttrSetManByte(_, _, _, sets, _, _, _)       => addSetVs[Byte](attr, sets, _.toInt, retractCur)
    case AttrSetManShort(_, _, _, sets, _, _, _)      => addSetVs[Short](attr, sets, _.toInt, retractCur)
    case AttrSetManChar(_, _, _, sets, _, _, _)       => addSetVs[Char](attr, sets, _.toString, retractCur)
  }
  private def addSetVs[T](
    attr: Attr,
    sets: Seq[Set[T]],
    transform: T => Any,
    retractCur: Boolean
  ): (String, Keyword, Seq[AnyRef], Boolean) = {
    val a = kw(attr.ns, attr.attr)
    sets match {
      case Seq(set) => ("add", a, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, retractCur)
      case Nil      => ("retract", a, Nil, retractCur)
      case vs       => throw MoleculeException(
        s"Can only $update one Set of values for Set attribute `${attr.name}`. Found: " + vs
      )
    }
  }


  private def setSwap(attr: AttrSetMan): Seq[(String, Keyword, Seq[AnyRef], Boolean)] = attr match {
    case AttrSetManString(_, _, _, sets, _, _, _)     => swapSetVs[String](attr, sets, identity)
    case AttrSetManInt(_, _, _, sets, _, _, _)        => swapSetVs[Int](attr, sets, identity)
    case AttrSetManLong(_, _, _, sets, _, _, _, _)       => swapSetVs[Long](attr, sets, identity)
    case AttrSetManFloat(_, _, _, sets, _, _, _)      => swapSetVs[Float](attr, sets, identity)
    case AttrSetManDouble(_, _, _, sets, _, _, _)     => swapSetVs[Double](attr, sets, identity)
    case AttrSetManBoolean(_, _, _, sets, _, _, _)    => swapSetVs[Boolean](attr, sets, identity)
    case AttrSetManBigInt(_, _, _, sets, _, _, _)     => swapSetVs[BigInt](attr, sets, _.bigInteger)
    case AttrSetManBigDecimal(_, _, _, sets, _, _, _) => swapSetVs[BigDecimal](attr, sets, _.bigDecimal)
    case AttrSetManDate(_, _, _, sets, _, _, _)       => swapSetVs[Date](attr, sets, identity)
    case AttrSetManUUID(_, _, _, sets, _, _, _)       => swapSetVs[UUID](attr, sets, identity)
    case AttrSetManURI(_, _, _, sets, _, _, _)        => swapSetVs[URI](attr, sets, identity)
    case AttrSetManByte(_, _, _, sets, _, _, _)       => swapSetVs[Byte](attr, sets, _.toInt)
    case AttrSetManShort(_, _, _, sets, _, _, _)      => swapSetVs[Short](attr, sets, _.toInt)
    case AttrSetManChar(_, _, _, sets, _, _, _)       => swapSetVs[Char](attr, sets, _.toString)
  }
  private def swapSetVs[T](
    attr: Attr,
    sets: Seq[Set[T]],
    transform: T => Any
  ): Seq[(String, Keyword, Seq[AnyRef], Boolean)] = {
    val (retracts0, adds0) = sets.splitAt(sets.length / 2)
    val (retracts, adds)   = (retracts0.flatten, adds0.flatten)
    if (retracts.length != retracts.distinct.length)
      throw MoleculeException(s"Can't swap from duplicate retract values.")

    if (adds.length != adds.distinct.length)
      throw MoleculeException(s"Can't swap to duplicate replacement values.")

    if (retracts.isEmpty) {
      Nil
    } else {
      val a = kw(attr.ns, attr.attr)
      if (retracts.size != adds.size)
        throw MoleculeException(
          s"Can't swap duplicate keys/values: "
        )
      Seq(
        ("retract", a, retracts.map(v => transform(v).asInstanceOf[AnyRef]), false),
        ("add", a, adds.map(v => transform(v).asInstanceOf[AnyRef]), false),
      )
    }
  }


  private def setRemove(attr: AttrSetMan): Seq[(String, Keyword, Seq[AnyRef], Boolean)] = attr match {
    case AttrSetManString(_, _, _, Seq(set), _, _, _)     => removeSetVs[String](attr, set, identity)
    case AttrSetManInt(_, _, _, Seq(set), _, _, _)        => removeSetVs[Int](attr, set, identity)
    case AttrSetManLong(_, _, _, Seq(set), _, _, _, _)       => removeSetVs[Long](attr, set, identity)
    case AttrSetManFloat(_, _, _, Seq(set), _, _, _)      => removeSetVs[Float](attr, set, identity)
    case AttrSetManDouble(_, _, _, Seq(set), _, _, _)     => removeSetVs[Double](attr, set, identity)
    case AttrSetManBoolean(_, _, _, Seq(set), _, _, _)    => removeSetVs[Boolean](attr, set, identity)
    case AttrSetManBigInt(_, _, _, Seq(set), _, _, _)     => removeSetVs[BigInt](attr, set, _.bigInteger)
    case AttrSetManBigDecimal(_, _, _, Seq(set), _, _, _) => removeSetVs[BigDecimal](attr, set, _.bigDecimal)
    case AttrSetManDate(_, _, _, Seq(set), _, _, _)       => removeSetVs[Date](attr, set, identity)
    case AttrSetManUUID(_, _, _, Seq(set), _, _, _)       => removeSetVs[UUID](attr, set, identity)
    case AttrSetManURI(_, _, _, Seq(set), _, _, _)        => removeSetVs[URI](attr, set, identity)
    case AttrSetManByte(_, _, _, Seq(set), _, _, _)       => removeSetVs[Byte](attr, set, _.toInt)
    case AttrSetManShort(_, _, _, Seq(set), _, _, _)      => removeSetVs[Short](attr, set, _.toInt)
    case AttrSetManChar(_, _, _, Seq(set), _, _, _)       => removeSetVs[Char](attr, set, _.toString)
    case _                                                => throw MoleculeException(
      s"Can only remove one Set of values for Set attribute `${attr.name}`. Found: $attr"
    )
  }
  private def removeSetVs[T](
    attr: Attr,
    set: Set[T],
    transform: T => Any
  ): Seq[(String, Keyword, Seq[AnyRef], Boolean)] = {
    val a = kw(attr.ns, attr.attr)
    if (set.isEmpty) Nil else Seq(("retract", a, set.map(v => transform(v).asInstanceOf[AnyRef]).toSeq, false))
  }


  private def extractSubElements(
    elements: Seq[Element]
  ): (Seq[AnyRef], Seq[Element], Seq[(String, Keyword, Seq[AnyRef], Boolean)]) = {
    extract(elements, Nil, Nil, Nil)
  }

}