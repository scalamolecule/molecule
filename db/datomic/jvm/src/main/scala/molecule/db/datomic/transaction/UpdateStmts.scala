package molecule.db.datomic.transaction

import java.net.URI
import java.util.{Date, UUID}
import clojure.lang.Keyword
import datomic.Util.list
import molecule.base.util.exceptions.MoleculeException
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.MoleculeModel._
import molecule.db.datomic.query.DatomicModel2Query
import scala.annotation.tailrec

class UpdateStmts(
  uniqueAttrs: List[String],
  elements: Seq[Element],
  isUpsert: Boolean = false,
  isMultiple: Boolean,
) extends DatomicTransactionBase(elements, isUpsert) {

  def getStmts: (Seq[AnyRef], Option[String], Seq[AnyRef], Seq[(String, Keyword, AnyRef)]) = {
    println("\n--- UPDATE --------------")
    elements.foreach(println)
    checkConflictingAttributes(elements, distinguishMode = true)

    val (eids, filterElements, data) = extract(elements, Nil, Nil, Nil)

    if (!isMultiple && eids.length > 1)
      throw MoleculeException(s"Please provide explicit `$update.multiple` to $update multiple entities.")

    val (idQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }
    (eids.asInstanceOf[Seq[AnyRef]], idQuery, inputs, data)
  }

  @tailrec
  final private def extract(
    elements: Seq[Element],
    eids: Seq[Any],
    filter: Seq[Element],
    data: Seq[(String, Keyword, AnyRef)]
  ): (Seq[Any], Seq[Element], Seq[(String, Keyword, AnyRef)]) = {
    elements match {
      case element :: tail => element match {
        case attr: Attr => attr match {
          // Mandatory value attribute
          case dataAttr: AttrOneMan =>
            if (dataAttr.op != Appl)
              throw MoleculeException(s"Can't $update attributes without an applied value. Found:\n" + dataAttr)
            if (isUpsert) {
              // Disregard if value already exists
              extract(tail, eids, filter, data :+ getData(dataAttr))
            } else {
              // Make sure current value exists
              val dummyTacitAttr = AttrOneTacInt(dataAttr.ns, dataAttr.attr, V, Nil, None, None, None)
              extract(tail, eids, filter :+ dummyTacitAttr, data :+ getData(dataAttr))
            }

          // Tacit filter attribute
          case filterAttr: AttrOneTac => filterAttr match {
            case AttrOneTacLong("Generic", "e", _, eids1, _, _, _) =>
              if (eids.nonEmpty)
                throw MoleculeException(s"Can't apply entity ids twice in $update.")
              if (eids1.isEmpty)
                throw MoleculeException("Empty list of entity ids not allowed.")
              extract(tail, eids1, filter, data)

            case uniqueFilterAttr if uniqueAttrs.contains(uniqueFilterAttr.name) =>
              if (eids.nonEmpty)
                throw MoleculeException(
                  s"Can only apply one unique attribute value for $update. Found:\n" + uniqueFilterAttr
                )
              val at         = s":${uniqueFilterAttr.ns}/${uniqueFilterAttr.attr}"
              val lookupRefs = filterAttr match {
                case AttrOneTacString(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v))
                case AttrOneTacInt(_, _, _, vs, _, _, _)        => vs.map(v => list(at, v))
                case AttrOneTacLong(_, _, _, vs, _, _, _)       => vs.map(v => list(at, v))
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
              extract(tail, eids ++ lookupRefs, filter, data)

            case nonUniqueFilterAttr =>
              extract(tail, eids, filter :+ nonUniqueFilterAttr, data)
          }

          case _: AttrOneOpt => throw MoleculeException(s"Can't $update optional values. Found:\n" + attr)
          case _: AttrSetTac => throw MoleculeException(
            "Can only lookup entity with card-one attribute value. Found:\n" + attr
          )
          case _             => extract(tail, eids, filter, data)
        }

        case _: Nested    => throw MoleculeException(s"Nested data structure not allowed in $update molecule.")
        case _: NestedOpt => throw MoleculeException(s"Optional nested data structure not allowed in $update molecule.")

        case r@Ref(ns, a, _, CardOne) => extract(tail, eids, filter :+ r, data :+ ("ref", kw(ns, a), null))
        case r: Ref                   => throw MoleculeException(
          s"Can't $update ambiguous attributes in card-many referenced namespaces. Found `${r.refAttr.capitalize}`"
        )

        case b: BackRef => extract(tail, eids, filter :+ b, data)

        case Composite(es) =>
          val (eids1, filters1, data1) = extractSubElements(es)
          extract(tail, eids ++ eids1, filter ++ filters1, data ++ data1)

        case TxMetaData(es) =>
          if (data.isEmpty)
            throw MoleculeException(s"Can't $update tx meta data only.")
          val (eids1, filters1, data1) = extractSubElements(es)
          extract(tail, eids ++ eids1, filter ++ filters1, (data :+ ("tx", null, null)) ++ data1)

        case element => unexpected(element)
      }

      case Nil => (eids, filter, data)
    }
  }

  private def extractSubElements(
    elements: Seq[Element]
  ): (Seq[Any], Seq[Element], Seq[(String, Keyword, AnyRef)]) = {
    extract(elements, Nil, Nil, Nil)
  }

  private def getData(attr: AttrOneMan): (String, Keyword, AnyRef) = attr match {
    case AttrOneManString(_, _, _, vs, _, _, _)     => oneV[String](attr, vs, identity)
    case AttrOneManInt(_, _, _, vs, _, _, _)        => oneV[Int](attr, vs, identity)
    case AttrOneManLong(_, _, _, vs, _, _, _)       => oneV[Long](attr, vs, identity)
    case AttrOneManFloat(_, _, _, vs, _, _, _)      => oneV[Float](attr, vs, identity)
    case AttrOneManDouble(_, _, _, vs, _, _, _)     => oneV[Double](attr, vs, identity)
    case AttrOneManBoolean(_, _, _, vs, _, _, _)    => oneV[Boolean](attr, vs, identity)
    case AttrOneManBigInt(_, _, _, vs, _, _, _)     => oneV[BigInt](attr, vs, _.bigInteger)
    case AttrOneManBigDecimal(_, _, _, vs, _, _, _) => oneV[BigDecimal](attr, vs, _.bigDecimal)
    case AttrOneManDate(_, _, _, vs, _, _, _)       => oneV[Date](attr, vs, identity)
    case AttrOneManUUID(_, _, _, vs, _, _, _)       => oneV[UUID](attr, vs, identity)
    case AttrOneManURI(_, _, _, vs, _, _, _)        => oneV[URI](attr, vs, identity)
    case AttrOneManByte(_, _, _, vs, _, _, _)       => oneV[Byte](attr, vs, _.toInt)
    case AttrOneManShort(_, _, _, vs, _, _, _)      => oneV[Short](attr, vs, _.toInt)
    case AttrOneManChar(_, _, _, vs, _, _, _)       => oneV[Char](attr, vs, _.toString)
    case _                                          => throw MoleculeException(
      s"Can only $update one applied value for attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }

  private def oneV[T](attr: Attr, vs: Seq[T], transform: T => Any): (String, Keyword, AnyRef) = {
    val a = kw(attr.ns, attr.attr)
    vs match {
      case Seq(v) => ("add", a, transform(v).asInstanceOf[AnyRef])
      case Nil    => ("retract", a, null)
      case vs     => throw MoleculeException(
        s"Can only $update one value for attribute `${attr.name}`. Found: " + vs
      )
    }
  }
}