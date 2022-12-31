package molecule.db.datomic.transaction

import datomic.Util.list
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import molecule.core.transaction.{Update, Update2Data}
import molecule.core.validation.CheckConflictingAttrs
import molecule.db.datomic.query.DatomicModel2Query

trait Update_stmts extends Update2Data { self: Update =>

  def getStmtsData(
    elements: Seq[Element],
    isMultiple: Boolean,
  ): (Seq[AnyRef], Option[String], Seq[AnyRef], Seq[(String, String, String, Seq[AnyRef], Boolean)]) = {
    CheckConflictingAttrs(elements, distinguishMode = true)

    val (eids, filterElements, data) = resolve(elements, Nil, Nil, Nil)

    if (!isMultiple && eids.length > 1) {
      val update = if (self.isUpsert) "upsert" else "update"
      throw MoleculeException(
        s"Please provide explicit `$update.multiple` to $update multiple entities " +
          s"(found ${eids.length} matching entities)."
      )
    }

    val (filterQuery, inputs) = if (eids.isEmpty && filterElements.nonEmpty) {
      val filterElements1 = AttrOneManLong("_Generic", "e", V) +: filterElements
      val (query, inputs) = new DatomicModel2Query[Any](filterElements1).getEidQueryWithInputs
      (Some(query), inputs)
    } else {
      (None, Nil)
    }
    (eids, filterQuery, inputs, data)
  }

  override protected def uniqueEids(
    filterAttr: AttrOneTac,
    ns: String,
    attr: String
  ): Seq[AnyRef] = {
    val at = s":$ns/$attr"
    filterAttr match {
      case AttrOneTacString(_, _, _, vs, _, _, _)     => vs.map(v => list(at, v))
      case AttrOneTacInt(_, _, _, vs, _, _, _)        => vs.map(v => list(at, v))
      case AttrOneTacLong(_, _, _, vs, _, _, _, _)    => vs.map(v => list(at, v))
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
  }

  override protected lazy val valueString     = identity
  override protected lazy val valueInt        = identity
  override protected lazy val valueLong       = identity
  override protected lazy val valueFloat      = identity
  override protected lazy val valueDouble     = identity
  override protected lazy val valueBoolean    = identity
  override protected lazy val valueBigInt     = (v: BigInt) => v.bigInteger
  override protected lazy val valueBigDecimal = (v: BigDecimal) => v.bigDecimal
  override protected lazy val valueDate       = identity
  override protected lazy val valueUUID       = identity
  override protected lazy val valueURI        = identity
  override protected lazy val valueByte       = (v: Byte) => v.toInt
  override protected lazy val valueShort      = (v: Short) => v.toInt
  override protected lazy val valueChar       = (v: Char) => v.toString
}