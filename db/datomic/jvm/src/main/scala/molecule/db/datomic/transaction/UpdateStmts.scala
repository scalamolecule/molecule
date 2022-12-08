package molecule.db.datomic.transaction

import java.net.URI
import java.util.{Collections, Date, UUID, List => jList}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import scala.annotation.tailrec


class UpdateStmts(
  uniqueAttrs: List[String],
  elements: Seq[Element],
  isMultiple: Boolean,
) extends DatomicTransactionBase(elements) {

  def getStmts: (jList[jList[AnyRef]], Option[String]) = {
    println("\n--- UPDATE --------------")
    elements.foreach(println)
    checkConflictingAttributes(elements)

//    val eids =

    elements.head match {
      // Update one with entity id
      case AttrOneTacLong("Generic", "e", Appl, Seq(eid), _, _, _) =>

        e = eid.asInstanceOf[AnyRef]
        e0 = e
        updateWithEids(elements.tail)

      // Update multiple with entity ids
      case AttrOneTacLong("Generic", "e", Appl, eids, _, _, _) =>
        if (!isMultiple)
          throw MoleculeException("Please provide explicit `update.multiple` to update multiple entities.")
        e = eids.head.asInstanceOf[AnyRef]
        e0 = e
        updateWithEids(elements.tail)

      case other => throw MoleculeException(
        "Please apply one or more entity ids to the initial namespace."
      )
    }
    println("---")
    stmts.forEach(stmt => println(stmt))
    (Collections.unmodifiableList(stmts), None)
  }

  private def uniqueIdentifier(elements: Seq[Element]) = {
    val uniqueIdentifiers = elements.collect {
      case AttrOneTacLong("Generic", "e", Appl, Seq(eid), _, _, _) =>

      case a: Attr if uniqueAttrs.contains(s"${a.ns}.${a.attr}")   =>
    }
  }

  private def noFilters(attr: Attr): Unit = {
    throw MoleculeException(
      "Can't filter update by tacit attribute values when using entity ids. Found:\n" + attr
    )
  }

  @tailrec
  final protected def updateWithEids(elements: Seq[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case attr: Attr =>
          if (attr.op != Appl) {
            throw MoleculeException("Can't save attributes without applied value. Found:\n" + attr)
          }
          backRefs = backRefs + (attr.ns -> e)
          val a = kw(attr.ns, attr.attr)
          attr match {
            case attr: AttrOne =>
              attr match {
                case attr: AttrOneMan => resolveAttrOneMan(attr, a); updateWithEids(tail)
                case attr: AttrOneOpt => resolveAttrOneOpt(attr, a); updateWithEids(tail)
                case attr: AttrOneTac => noFilters(attr)
              }
            case attr: AttrSet =>
              attr match {
                case attr: AttrSetMan => resolveAttrSetMan(attr, a); updateWithEids(tail)
                case attr: AttrSetOpt => resolveAttrSetOpt(attr, a); updateWithEids(tail)
                case attr: AttrSetTac => noFilters(attr)
              }
          }

        case Ref(ns, refAttr, _, _)       => ref(kw(ns, refAttr)); updateWithEids(tail)
        case BackRef(backRefNs)           => backRef(backRefNs); updateWithEids(tail)
        case _: Nested                    => throw MoleculeException(
          "Nested data structure not allowed in save molecule. Please use insert instead."
        )
        case _: NestedOpt                 => throw MoleculeException(
          "Optional nested data structure not allowed in save molecule. Please use insert instead."
        )
        case Composite(compositeElements) =>
          updateWithEids(compositeElements ++ tail)

        case TxMetaData(txElements) =>
          e = tx
          e0 = tx
          updateWithEids(txElements) // tail is empty (no more attributes possible after Tx)

        case element => unexpected(element)
      }
      case Nil             => ()
    }
  }


  private def oneV[T](attrName: String, vs: Seq[T], transform: T => Any): Option[Any] = {
    vs match {
      case Seq(v) => Some(transform(v))
      case Nil    => None
      case vs     => throw MoleculeException(
        s"Can only save one value for attribute `$attrName`. Found: " + vs
      )
    }
  }
  def resolveAttrOneMan(attr: AttrOneMan, a: Keyword): Unit = {
    val an = a.toString
    attr match {
      case AttrOneManString(_, _, Appl, vs, _, _, _)     => addV(a, oneV[String](an, vs, identity))
      case AttrOneManInt(_, _, Appl, vs, _, _, _)        => addV(a, oneV[Int](an, vs, identity))
      case AttrOneManLong(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Long](an, vs, identity))
      case AttrOneManFloat(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Float](an, vs, identity))
      case AttrOneManDouble(_, _, Appl, vs, _, _, _)     => addV(a, oneV[Double](an, vs, identity))
      case AttrOneManBoolean(_, _, Appl, vs, _, _, _)    => addV(a, oneV[Boolean](an, vs, identity))
      case AttrOneManBigInt(_, _, Appl, vs, _, _, _)     => addV(a, oneV[BigInt](an, vs, _.bigInteger))
      case AttrOneManBigDecimal(_, _, Appl, vs, _, _, _) => addV(a, oneV[BigDecimal](an, vs, _.bigDecimal))
      case AttrOneManDate(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Date](an, vs, identity))
      case AttrOneManUUID(_, _, Appl, vs, _, _, _)       => addV(a, oneV[UUID](an, vs, identity))
      case AttrOneManURI(_, _, Appl, vs, _, _, _)        => addV(a, oneV[URI](an, vs, identity))
      case AttrOneManByte(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Byte](an, vs, _.toInt))
      case AttrOneManShort(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Short](an, vs, _.toInt))
      case AttrOneManChar(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Char](an, vs, _.toString))
      case _                                             => throw MoleculeException(
        s"Can only save one applied value for attribute `$an`. Found expression: ${attr.op}"
      )
    }
  }
  def resolveAttrOneTac(attr: AttrOneTac, a: Keyword): Unit = {
    val an = a.toString
    attr match {
      case AttrOneTacString(_, _, Appl, vs, _, _, _)     => addV(a, oneV[String](an, vs, identity))
      case AttrOneTacInt(_, _, Appl, vs, _, _, _)        => addV(a, oneV[Int](an, vs, identity))
      case AttrOneTacLong(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Long](an, vs, identity))
      case AttrOneTacFloat(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Float](an, vs, identity))
      case AttrOneTacDouble(_, _, Appl, vs, _, _, _)     => addV(a, oneV[Double](an, vs, identity))
      case AttrOneTacBoolean(_, _, Appl, vs, _, _, _)    => addV(a, oneV[Boolean](an, vs, identity))
      case AttrOneTacBigInt(_, _, Appl, vs, _, _, _)     => addV(a, oneV[BigInt](an, vs, _.bigInteger))
      case AttrOneTacBigDecimal(_, _, Appl, vs, _, _, _) => addV(a, oneV[BigDecimal](an, vs, _.bigDecimal))
      case AttrOneTacDate(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Date](an, vs, identity))
      case AttrOneTacUUID(_, _, Appl, vs, _, _, _)       => addV(a, oneV[UUID](an, vs, identity))
      case AttrOneTacURI(_, _, Appl, vs, _, _, _)        => addV(a, oneV[URI](an, vs, identity))
      case AttrOneTacByte(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Byte](an, vs, _.toInt))
      case AttrOneTacShort(_, _, Appl, vs, _, _, _)      => addV(a, oneV[Short](an, vs, _.toInt))
      case AttrOneTacChar(_, _, Appl, vs, _, _, _)       => addV(a, oneV[Char](an, vs, _.toString))
      case _                                             => throw MoleculeException(
        s"Can only save one applied value for tacit attribute `$an`. Found expression: ${attr.op}"
      )
    }
  }


  private def oneOptV[T](attrName: String, optVs: Option[Seq[T]], transform: T => Any): Option[Any] = {
    optVs.flatMap {
      case Seq(v) => Some(transform(v))
      case Nil    => None
      case vs     => throw MoleculeException(
        s"Can only save one value for optional attribute `$attrName`. Found: " + vs
      )
    }
  }
  def resolveAttrOneOpt(attr: AttrOneOpt, a: Keyword): Unit = {
    val an = a.toString
    attr match {
      case AttrOneOptString(_, _, Appl, optVs, _, _, _)     => addV(a, oneOptV[String](an, optVs, identity))
      case AttrOneOptInt(_, _, Appl, optVs, _, _, _)        => addV(a, oneOptV[Int](an, optVs, identity))
      case AttrOneOptLong(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Long](an, optVs, identity))
      case AttrOneOptFloat(_, _, Appl, optVs, _, _, _)      => addV(a, oneOptV[Float](an, optVs, identity))
      case AttrOneOptDouble(_, _, Appl, optVs, _, _, _)     => addV(a, oneOptV[Double](an, optVs, identity))
      case AttrOneOptBoolean(_, _, Appl, optVs, _, _, _)    => addV(a, oneOptV[Boolean](an, optVs, identity))
      case AttrOneOptBigInt(_, _, Appl, optVs, _, _, _)     => addV(a, oneOptV[BigInt](an, optVs, _.bigInteger))
      case AttrOneOptBigDecimal(_, _, Appl, optVs, _, _, _) => addV(a, oneOptV[BigDecimal](an, optVs, _.bigDecimal))
      case AttrOneOptDate(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Date](an, optVs, identity))
      case AttrOneOptUUID(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[UUID](an, optVs, identity))
      case AttrOneOptURI(_, _, Appl, optVs, _, _, _)        => addV(a, oneOptV[URI](an, optVs, identity))
      case AttrOneOptByte(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Byte](an, optVs, _.toInt))
      case AttrOneOptShort(_, _, Appl, optVs, _, _, _)      => addV(a, oneOptV[Short](an, optVs, _.toInt))
      case AttrOneOptChar(_, _, Appl, optVs, _, _, _)       => addV(a, oneOptV[Char](an, optVs, _.toString))
      case _                                                => throw MoleculeException(
        s"Can only save one applied value for optional attribute `$an`. Found expression: ${attr.op}"
      )
    }
  }


  private def oneSet[T](attrName: String, sets: Seq[Set[T]], transform: T => Any): Option[Set[Any]] = {
    sets match {
      case Seq(set)     => Some(set.map(transform))
      case Nil          => None
      case multipleSets => throw MoleculeException(
        s"Can only save one Set of values for Set attribute `$attrName`. Found: " + multipleSets
      )
    }
  }
  def resolveAttrSetMan(attr: AttrSetMan, a: Keyword): Unit = {
    val an = a.toString
    attr match {
      case AttrSetManString(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[String](an, sets, identity))
      case AttrSetManInt(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[Int](an, sets, identity))
      case AttrSetManLong(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Long](an, sets, identity))
      case AttrSetManFloat(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Float](an, sets, identity))
      case AttrSetManDouble(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[Double](an, sets, identity))
      case AttrSetManBoolean(_, _, Appl, sets, _, _, _)    => addSet(a, oneSet[Boolean](an, sets, identity))
      case AttrSetManBigInt(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[BigInt](an, sets, _.bigInteger))
      case AttrSetManBigDecimal(_, _, Appl, sets, _, _, _) => addSet(a, oneSet[BigDecimal](an, sets, _.bigDecimal))
      case AttrSetManDate(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Date](an, sets, identity))
      case AttrSetManUUID(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[UUID](an, sets, identity))
      case AttrSetManURI(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[URI](an, sets, identity))
      case AttrSetManByte(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Byte](an, sets, _.toInt))
      case AttrSetManShort(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Short](an, sets, _.toInt))
      case AttrSetManChar(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Char](an, sets, _.toString))
      case _                                               => throw MoleculeException(
        s"Can only save one applied Set of values for Set attribute `$an`. Found expression: ${attr.op}"
      )
    }
  }
  def resolveAttrSetTac(attr: AttrSetTac, a: Keyword): Unit = {
    val an = a.toString
    attr match {
      case AttrSetTacString(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[String](an, sets, identity))
      case AttrSetTacInt(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[Int](an, sets, identity))
      case AttrSetTacLong(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Long](an, sets, identity))
      case AttrSetTacFloat(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Float](an, sets, identity))
      case AttrSetTacDouble(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[Double](an, sets, identity))
      case AttrSetTacBoolean(_, _, Appl, sets, _, _, _)    => addSet(a, oneSet[Boolean](an, sets, identity))
      case AttrSetTacBigInt(_, _, Appl, sets, _, _, _)     => addSet(a, oneSet[BigInt](an, sets, _.bigInteger))
      case AttrSetTacBigDecimal(_, _, Appl, sets, _, _, _) => addSet(a, oneSet[BigDecimal](an, sets, _.bigDecimal))
      case AttrSetTacDate(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Date](an, sets, identity))
      case AttrSetTacUUID(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[UUID](an, sets, identity))
      case AttrSetTacURI(_, _, Appl, sets, _, _, _)        => addSet(a, oneSet[URI](an, sets, identity))
      case AttrSetTacByte(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Byte](an, sets, _.toInt))
      case AttrSetTacShort(_, _, Appl, sets, _, _, _)      => addSet(a, oneSet[Short](an, sets, _.toInt))
      case AttrSetTacChar(_, _, Appl, sets, _, _, _)       => addSet(a, oneSet[Char](an, sets, _.toString))
      case _                                               => throw MoleculeException(
        s"Can only save one applied Set of values for tacit Set attribute `$an`. Found expression: ${attr.op}"
      )
    }
  }


  private def oneOptSet[T](attrName: String, optSets: Option[Seq[Set[T]]], transform: T => Any): Option[Set[Any]] = {
    optSets.flatMap {
      case Seq(set) => Some(set.map(transform))
      case Nil      => None
      case vs       => throw MoleculeException(
        s"Can only save one Set of values for optional Set attribute `$attrName`. Found: " + vs
      )
    }
  }
  def resolveAttrSetOpt(attr: AttrSetOpt, a: Keyword): Unit = {
    val an = a.toString
    attr match {
      case AttrSetOptString(_, _, Appl, optSets, _, _, _)     => addSet(a, oneOptSet[String](an, optSets, identity))
      case AttrSetOptInt(_, _, Appl, optSets, _, _, _)        => addSet(a, oneOptSet[Int](an, optSets, identity))
      case AttrSetOptLong(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Long](an, optSets, identity))
      case AttrSetOptFloat(_, _, Appl, optSets, _, _, _)      => addSet(a, oneOptSet[Float](an, optSets, identity))
      case AttrSetOptDouble(_, _, Appl, optSets, _, _, _)     => addSet(a, oneOptSet[Double](an, optSets, identity))
      case AttrSetOptBoolean(_, _, Appl, optSets, _, _, _)    => addSet(a, oneOptSet[Boolean](an, optSets, identity))
      case AttrSetOptBigInt(_, _, Appl, optSets, _, _, _)     => addSet(a, oneOptSet[BigInt](an, optSets, _.bigInteger))
      case AttrSetOptBigDecimal(_, _, Appl, optSets, _, _, _) => addSet(a, oneOptSet[BigDecimal](an, optSets, _.bigDecimal))
      case AttrSetOptDate(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Date](an, optSets, identity))
      case AttrSetOptUUID(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[UUID](an, optSets, identity))
      case AttrSetOptURI(_, _, Appl, optSets, _, _, _)        => addSet(a, oneOptSet[URI](an, optSets, identity))
      case AttrSetOptByte(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Byte](an, optSets, _.toInt))
      case AttrSetOptShort(_, _, Appl, optSets, _, _, _)      => addSet(a, oneOptSet[Short](an, optSets, _.toInt))
      case AttrSetOptChar(_, _, Appl, optSets, _, _, _)       => addSet(a, oneOptSet[Char](an, optSets, _.toString))
      case _                                                  => throw MoleculeException(
        s"Can only save one applied Set of values for optional Set attribute `$an`. Found expression: ${attr.op}"
      )
    }
  }

  protected def addV(a: Keyword, optValue: Option[Any]): Unit = {
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

  protected def addSet[T](a: Keyword, optSet: Option[Set[T]]): Unit = {
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

  protected def addNested(a: Keyword): String => Unit = {
    (nestedBaseId: String) => {
      e = nestedBaseId
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      e = newId
      stmt.add(e.asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }

  protected def backRef(backRefNs: String): Unit = {
    e = backRefs(backRefNs)
  }

  protected def ref(refAttr: Keyword): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(refAttr)
    e = newId
    stmt.add(e.asInstanceOf[AnyRef])
    stmts.add(stmt)
  }
}