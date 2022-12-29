package molecule.db.datomic.transaction

import java.net.URI
import java.util.{Collections, Date, UUID, ArrayList => jArrayList, List => jList}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import scala.annotation.tailrec


class SaveStmts(
  elements: Seq[Element],
  isInsertTxMetaData: Boolean = false,
  debug: Boolean = true
) extends DatomicTransactionBase(elements) {

  def getRawStmts(eid: String): jArrayList[jList[AnyRef]] = {
    if (debug) {
      println("\n\n--- SAVE -----------------------------------------------------------------------")
      elements.foreach(println)
    }
    checkConflictingAttributes(elements)
    e = eid
    e0 = e
    resolve(elements)

    if (debug) {
      println("---")
      stmts.forEach(stmt => println(stmt))
    }
    stmts
  }

//  def getStmts: jList[jList[AnyRef]] = getRawStmts(newId)
  def getStmts: Data = getRawStmts(newId)


  @tailrec
  final private def resolve(elements: Seq[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case attr: Attr =>
          if (attr.op != Appl) {
            if (isInsertTxMetaData)
              throw MoleculeException("Please apply tx meta data to tacit attributes. Found:\n" + attr)
            else
              throw MoleculeException("Can't save attributes without an applied value. Found:\n" + attr)
          }
          backRefs = backRefs + (attr.ns -> e)
          val a = kw(attr.ns, attr.attr)
          attr match {
            case attr: AttrOne =>
              attr match {
                case attr: AttrOneMan => resolveAttrOneMan(attr, a); resolve(tail)
                case attr: AttrOneOpt => resolveAttrOneOpt(attr, a); resolve(tail)
                case attr: AttrOneTac => resolveAttrOneTac(attr, a); resolve(tail)
              }
            case attr: AttrSet =>
              attr match {
                case attr: AttrSetMan => resolveAttrSetMan(attr, a); resolve(tail)
                case attr: AttrSetOpt => resolveAttrSetOpt(attr, a); resolve(tail)
                case attr: AttrSetTac => resolveAttrSetTac(attr, a); resolve(tail)
              }
          }

        case Ref(ns, refAttr, _, _)       => ref(kw(ns, refAttr)); resolve(tail)
        case BackRef(backRefNs)           => backRef(backRefNs); resolve(tail)
        case _: Nested                    => throw MoleculeException(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                 => throw MoleculeException(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
        case Composite(compositeElements) =>
          // Start from initial entity id for each composite sub group
          e = if (isInsertTxMetaData) datomicTx else e0
          resolve(compositeElements ++ tail)

        case TxMetaData(txElements) =>
          e = datomicTx
          e0 = datomicTx
          resolve(txElements) // tail is empty (no more attributes possible after Tx)

        case element => unexpected(element)
      }
      case Nil             => ()
    }
  }


  private def oneV[T](attr: Attr, vs: Seq[T], transform: T => Any): Option[Any] = {
    vs match {
      case Seq(v) => Some(transform(v))
      case Nil    => None
      case vs     => throw MoleculeException(
        s"Can only save one value for attribute `${attr.name}`. Found: " + vs
      )
    }
  }
  private def resolveAttrOneMan(attr: AttrOneMan, a: Keyword): Unit = attr match {
    case AttrOneManString(_, _, Appl, vs, _, _, _)     => addV(a, oneV[String](attr, vs, identity))
    case AttrOneManInt(_, _, Appl, vs, _, _, _)        => addV(a, oneV[Int](attr, vs, identity))
    case AttrOneManLong(_, _, Appl, vs, _, _, _, _)    => addV(a, oneV[Long](attr, vs, identity))
    case AttrOneManFloat(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Float](attr, vs, identity))
    case AttrOneManDouble(_, _, Appl, vs, _, _, _)     => addV(a, oneV[Double](attr, vs, identity))
    case AttrOneManBoolean(_, _, Appl, vs, _, _, _)    => addV(a, oneV[Boolean](attr, vs, identity))
    case AttrOneManBigInt(_, _, Appl, vs, _, _, _)     => addV(a, oneV[BigInt](attr, vs, _.bigInteger))
    case AttrOneManBigDecimal(_, _, Appl, vs, _, _, _) => addV(a, oneV[BigDecimal](attr, vs, _.bigDecimal))
    case AttrOneManDate(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Date](attr, vs, identity))
    case AttrOneManUUID(_, _, Appl, vs, _, _, _)       => addV(a, oneV[UUID](attr, vs, identity))
    case AttrOneManURI(_, _, Appl, vs, _, _, _)        => addV(a, oneV[URI](attr, vs, identity))
    case AttrOneManByte(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Byte](attr, vs, _.toInt))
    case AttrOneManShort(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Short](attr, vs, _.toInt))
    case AttrOneManChar(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Char](attr, vs, _.toString))
    case _                                             => throw MoleculeException(
      s"Can only save one applied value for attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }
  private def resolveAttrOneTac(attr: AttrOneTac, a: Keyword): Unit = attr match {
    case AttrOneTacString(_, _, Appl, vs, _, _, _)     => addV(a, oneV[String](attr, vs, identity))
    case AttrOneTacInt(_, _, Appl, vs, _, _, _)        => addV(a, oneV[Int](attr, vs, identity))
    case AttrOneTacLong(_, _, Appl, vs, _, _, _, _)    => addV(a, oneV[Long](attr, vs, identity))
    case AttrOneTacFloat(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Float](attr, vs, identity))
    case AttrOneTacDouble(_, _, Appl, vs, _, _, _)     => addV(a, oneV[Double](attr, vs, identity))
    case AttrOneTacBoolean(_, _, Appl, vs, _, _, _)    => addV(a, oneV[Boolean](attr, vs, identity))
    case AttrOneTacBigInt(_, _, Appl, vs, _, _, _)     => addV(a, oneV[BigInt](attr, vs, _.bigInteger))
    case AttrOneTacBigDecimal(_, _, Appl, vs, _, _, _) => addV(a, oneV[BigDecimal](attr, vs, _.bigDecimal))
    case AttrOneTacDate(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Date](attr, vs, identity))
    case AttrOneTacUUID(_, _, Appl, vs, _, _, _)       => addV(a, oneV[UUID](attr, vs, identity))
    case AttrOneTacURI(_, _, Appl, vs, _, _, _)        => addV(a, oneV[URI](attr, vs, identity))
    case AttrOneTacByte(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Byte](attr, vs, _.toInt))
    case AttrOneTacShort(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Short](attr, vs, _.toInt))
    case AttrOneTacChar(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Char](attr, vs, _.toString))
    case _                                             => throw MoleculeException(
      s"Can only save one applied value for tacit attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }


  private def oneOptV[T](attr: Attr, optVs: Option[Seq[T]], transform: T => Any): Option[Any] = {
    optVs.flatMap {
      case Seq(v) => Some(transform(v))
      case Nil    => None
      case vs     => throw MoleculeException(
        s"Can only save one value for optional attribute `${attr.name}`. Found: " + vs
      )
    }
  }
  private def resolveAttrOneOpt(attr: AttrOneOpt, a: Keyword): Unit = attr match {
    case AttrOneOptString(_, _, Appl, optVs, _, _, _)     => addV(a, oneOptV[String](attr, optVs, identity))
    case AttrOneOptInt(_, _, Appl, optVs, _, _, _)        => addV(a, oneOptV[Int](attr, optVs, identity))
    case AttrOneOptLong(_, _, Appl, optVs, _, _, _, _)    => addV(a, oneOptV[Long](attr, optVs, identity))
    case AttrOneOptFloat(_, _, Appl, optVs, _, _, _)      => addV(a, oneOptV[Float](attr, optVs, identity))
    case AttrOneOptDouble(_, _, Appl, optVs, _, _, _)     => addV(a, oneOptV[Double](attr, optVs, identity))
    case AttrOneOptBoolean(_, _, Appl, optVs, _, _, _)    => addV(a, oneOptV[Boolean](attr, optVs, identity))
    case AttrOneOptBigInt(_, _, Appl, optVs, _, _, _)     => addV(a, oneOptV[BigInt](attr, optVs, _.bigInteger))
    case AttrOneOptBigDecimal(_, _, Appl, optVs, _, _, _) => addV(a, oneOptV[BigDecimal](attr, optVs, _.bigDecimal))
    case AttrOneOptDate(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Date](attr, optVs, identity))
    case AttrOneOptUUID(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[UUID](attr, optVs, identity))
    case AttrOneOptURI(_, _, Appl, optVs, _, _, _)        => addV(a, oneOptV[URI](attr, optVs, identity))
    case AttrOneOptByte(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Byte](attr, optVs, _.toInt))
    case AttrOneOptShort(_, _, Appl, optVs, _, _, _)      => addV(a, oneOptV[Short](attr, optVs, _.toInt))
    case AttrOneOptChar(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Char](attr, optVs, _.toString))
    case _                                                => throw MoleculeException(
      s"Can only save one applied value for optional attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }


  private def oneSet[T](attr: Attr, sets: Seq[Set[T]], transform: T => Any): Option[Set[Any]] = {
    sets match {
      case Seq(set)     => Some(set.map(transform))
      case Nil          => None
      case multipleSets => throw MoleculeException(
        s"Can only save one Set of values for Set attribute `${attr.name}`. Found: " + multipleSets
      )
    }
  }
  private def resolveAttrSetMan(attr: AttrSetMan, a: Keyword): Unit = attr match {
    case AttrSetManString(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[String](attr, sets, identity))
    case AttrSetManInt(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[Int](attr, sets, identity))
    case AttrSetManLong(_, _, Appl, sets, _, _, _, _)    => addSet(a, oneSet[Long](attr, sets, identity))
    case AttrSetManFloat(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Float](attr, sets, identity))
    case AttrSetManDouble(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[Double](attr, sets, identity))
    case AttrSetManBoolean(_, _, Appl, sets, _, _, _)    => addSet(a, oneSet[Boolean](attr, sets, identity))
    case AttrSetManBigInt(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[BigInt](attr, sets, _.bigInteger))
    case AttrSetManBigDecimal(_, _, Appl, sets, _, _, _) => addSet(a, oneSet[BigDecimal](attr, sets, _.bigDecimal))
    case AttrSetManDate(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Date](attr, sets, identity))
    case AttrSetManUUID(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[UUID](attr, sets, identity))
    case AttrSetManURI(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[URI](attr, sets, identity))
    case AttrSetManByte(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Byte](attr, sets, _.toInt))
    case AttrSetManShort(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Short](attr, sets, _.toInt))
    case AttrSetManChar(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Char](attr, sets, _.toString))
    case _                                               => throw MoleculeException(
      s"Can only save one applied Set of values for Set attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }
  private def resolveAttrSetTac(attr: AttrSetTac, a: Keyword): Unit = attr match {
    case AttrSetTacString(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[String](attr, sets, identity))
    case AttrSetTacInt(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[Int](attr, sets, identity))
    case AttrSetTacLong(_, _, Appl, sets, _, _, _, _)    => addSet(a, oneSet[Long](attr, sets, identity))
    case AttrSetTacFloat(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Float](attr, sets, identity))
    case AttrSetTacDouble(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[Double](attr, sets, identity))
    case AttrSetTacBoolean(_, _, Appl, sets, _, _, _)    => addSet(a, oneSet[Boolean](attr, sets, identity))
    case AttrSetTacBigInt(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[BigInt](attr, sets, _.bigInteger))
    case AttrSetTacBigDecimal(_, _, Appl, sets, _, _, _) => addSet(a, oneSet[BigDecimal](attr, sets, _.bigDecimal))
    case AttrSetTacDate(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Date](attr, sets, identity))
    case AttrSetTacUUID(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[UUID](attr, sets, identity))
    case AttrSetTacURI(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[URI](attr, sets, identity))
    case AttrSetTacByte(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Byte](attr, sets, _.toInt))
    case AttrSetTacShort(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Short](attr, sets, _.toInt))
    case AttrSetTacChar(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Char](attr, sets, _.toString))
    case _                                               => throw MoleculeException(
      s"Can only save one applied Set of values for tacit Set attribute `${attr.name}`. Found expression: ${attr.op}"
    )
  }


  private def oneOptSet[T](attr: Attr, optSets: Option[Seq[Set[T]]], transform: T => Any): Option[Set[Any]] = {
    optSets.flatMap {
      case Seq(set) => Some(set.map(transform))
      case Nil      => None
      case vs       => throw MoleculeException(
        s"Can only save one Set of values for optional Set attribute `${attr.name}`. Found: " + vs
      )
    }
  }
  private def resolveAttrSetOpt(attr: AttrSetOpt, a: Keyword): Unit = attr match {
    case AttrSetOptString(_, _, Appl, optSets, _, _, _)     => addSet(a, oneOptSet[String](attr, optSets, identity))
    case AttrSetOptInt(_, _, Appl, optSets, _, _, _)        => addSet(a, oneOptSet[Int](attr, optSets, identity))
    case AttrSetOptLong(_, _, Appl, optSets, _, _, _, _)    => addSet(a, oneOptSet[Long](attr, optSets, identity))
    case AttrSetOptFloat(_, _, Appl, optSets, _, _, _)      => addSet(a, oneOptSet[Float](attr, optSets, identity))
    case AttrSetOptDouble(_, _, Appl, optSets, _, _, _)     => addSet(a, oneOptSet[Double](attr, optSets, identity))
    case AttrSetOptBoolean(_, _, Appl, optSets, _, _, _)    => addSet(a, oneOptSet[Boolean](attr, optSets, identity))
    case AttrSetOptBigInt(_, _, Appl, optSets, _, _, _)     => addSet(a, oneOptSet[BigInt](attr, optSets, _.bigInteger))
    case AttrSetOptBigDecimal(_, _, Appl, optSets, _, _, _) => addSet(a, oneOptSet[BigDecimal](attr, optSets, _.bigDecimal))
    case AttrSetOptDate(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Date](attr, optSets, identity))
    case AttrSetOptUUID(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[UUID](attr, optSets, identity))
    case AttrSetOptURI(_, _, Appl, optSets, _, _, _)        => addSet(a, oneOptSet[URI](attr, optSets, identity))
    case AttrSetOptByte(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Byte](attr, optSets, _.toInt))
    case AttrSetOptShort(_, _, Appl, optSets, _, _, _)      => addSet(a, oneOptSet[Short](attr, optSets, _.toInt))
    case AttrSetOptChar(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Char](attr, optSets, _.toString))
    case _                                                  => throw MoleculeException(
      s"Can only save one applied Set of values for optional Set attribute `${attr.name}`. " +
        s"Found expression: ${attr.op}"
    )
  }

  private def addV(a: Keyword, optValue: Option[Any]): Unit = {
    // No stmts if None
    optValue.foreach { value =>
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(value.asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }

  private def addSet[T](a: Keyword, optSet: Option[Set[T]]): Unit = {
    optSet.foreach { set =>
      set.foreach { v =>
        stmt = stmtList
        stmt.add(add)
        stmt.add(e)
        stmt.add(a)
        stmt.add(v.asInstanceOf[AnyRef])
        stmts.add(stmt)
      }
    }
  }

  private def backRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  private def ref(refAttr: Keyword): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(refAttr)
    e = newId
    stmt.add(e)
    stmts.add(stmt)
  }
}