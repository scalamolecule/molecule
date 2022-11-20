package molecule.db.datomic.transaction

import java.util.{Collections, List => jList}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import scala.annotation.tailrec


class SaveStmts(elements: Seq[Element]) extends TransactionBase(elements, 0) {

  def getStmts: jList[jList[_]] = {
    e = newId
    resolve(elements)

    elements.foreach(println)
    println("--- SAVE --------------")
    stmts.forEach(stmt => println(stmt))

    Collections.unmodifiableList(stmts)
  }

  @tailrec
  final private def resolve(elements: Seq[Element]): Unit = {
    elements match {
      case element :: tail => element match {
        case attr: Attr =>
          backRefs = backRefs + (attr.ns -> e)
          val a = kw(attr.ns, attr.attr)
          attr match {
            case attr: AttrOne =>
              attr match {
                case attr: AttrOneMan => resolveAttrOneMan(attr, a); resolve(tail)
                case attr: AttrOneOpt => resolveAttrOneOpt(attr, a); resolve(tail)
              }
            case attr: AttrSet =>
              attr match {
                case attr: AttrSetMan => resolveAttrSetMan(attr, a); resolve(tail)
                case attr: AttrSetOpt => resolveAttrSetOpt(attr, a); resolve(tail)
              }
          }

        case Ref(ns, refAttr, _, _) => ref(kw(ns, refAttr)); resolve(tail)
        case BackRef(backRefNs)     => backRef(backRefNs); resolve(tail)
        case _: Nested              => throw MoleculeException("Nested data structure not allowed in save molecule.")
        case _: NestedOpt           => throw MoleculeException("Optional nested data structure not allowed in save molecule.")
        case element                => unexpected(element)
      }
      case Nil             => ()
    }
  }


  def resolveAttrOneMan(attr: AttrOneMan, a: Keyword): Unit = {
    attr match {
      case AttrOneManString(_, _, Appl, Seq(v), _, _, _)     => addV(a, v)
      case AttrOneManInt(_, _, Appl, Seq(v), _, _, _)        => addV(a, v)
      case AttrOneManLong(_, _, Appl, Seq(v), _, _, _)       => addV(a, v)
      case AttrOneManFloat(_, _, Appl, Seq(v), _, _, _)      => addV(a, v)
      case AttrOneManDouble(_, _, Appl, Seq(v), _, _, _)     => addV(a, v)
      case AttrOneManBoolean(_, _, Appl, Seq(v), _, _, _)    => addV(a, v)
      case AttrOneManBigInt(_, _, Appl, Seq(v), _, _, _)     => addV(a, v.bigInteger)
      case AttrOneManBigDecimal(_, _, Appl, Seq(v), _, _, _) => addV(a, v.bigDecimal)
      case AttrOneManDate(_, _, Appl, Seq(v), _, _, _)       => addV(a, v)
      case AttrOneManUUID(_, _, Appl, Seq(v), _, _, _)       => addV(a, v)
      case AttrOneManURI(_, _, Appl, Seq(v), _, _, _)        => addV(a, v)
      case AttrOneManByte(_, _, Appl, Seq(v), _, _, _)       => addV(a, v.toInt)
      case AttrOneManShort(_, _, Appl, Seq(v), _, _, _)      => addV(a, v.toInt)
      case AttrOneManChar(_, _, Appl, Seq(v), _, _, _)       => addV(a, v.toString)
      case _                                                 =>
        if (attr.op != Appl)
          throw MoleculeException("Can only save one applied value for each attribute. " +
            s"Found other expression `${
              attr.op
            }` in: " + attr)
        throw MoleculeException("Can only save one value per attribute. Found: " + attr)
    }
  }

  def resolveAttrOneOpt(attr: AttrOneOpt, a: Keyword): Unit = {
    attr match {
      case AttrOneOptString(_, _, Appl, Some(Seq(v)), _, _, _)     => addV(a, v)
      case AttrOneOptInt(_, _, Appl, Some(Seq(v)), _, _, _)        => addV(a, v)
      case AttrOneOptLong(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v)
      case AttrOneOptFloat(_, _, Appl, Some(Seq(v)), _, _, _)      => addV(a, v)
      case AttrOneOptDouble(_, _, Appl, Some(Seq(v)), _, _, _)     => addV(a, v)
      case AttrOneOptBoolean(_, _, Appl, Some(Seq(v)), _, _, _)    => addV(a, v)
      case AttrOneOptBigInt(_, _, Appl, Some(Seq(v)), _, _, _)     => addV(a, v.bigInteger)
      case AttrOneOptBigDecimal(_, _, Appl, Some(Seq(v)), _, _, _) => addV(a, v.bigDecimal)
      case AttrOneOptDate(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v)
      case AttrOneOptUUID(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v)
      case AttrOneOptURI(_, _, Appl, Some(Seq(v)), _, _, _)        => addV(a, v)
      case AttrOneOptByte(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v.toInt)
      case AttrOneOptShort(_, _, Appl, Some(Seq(v)), _, _, _)      => addV(a, v.toInt)
      case AttrOneOptChar(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v.toString)
      case _                                                       =>
        if (attr.op != Appl)
          throw MoleculeException("Can only save one applied value for each attribute. " +
            s"Found other expression `${
              attr.op
            }` in: " + attr)
        throw MoleculeException("Can only save one value per attribute. Found: " + attr)
    }
  }


  def resolveAttrSetMan(attr: AttrSetMan, a: Keyword): Unit = {
    attr match {
      case AttrSetManString(_, _, Appl, Seq(set), _, _, _)     => addSet(a, set)
      case AttrSetManInt(_, _, Appl, Seq(set), _, _, _)        => addSet(a, set)
      case AttrSetManLong(_, _, Appl, Seq(set), _, _, _)       => addSet(a, set)
      case AttrSetManFloat(_, _, Appl, Seq(set), _, _, _)      => addSet(a, set)
      case AttrSetManDouble(_, _, Appl, Seq(set), _, _, _)     => addSet(a, set)
      case AttrSetManBoolean(_, _, Appl, Seq(set), _, _, _)    => addSet(a, set)
      case AttrSetManBigInt(_, _, Appl, Seq(set), _, _, _)     => addSet(a, set.map(_.bigInteger))
      case AttrSetManBigDecimal(_, _, Appl, Seq(set), _, _, _) => addSet(a, set.map(_.bigDecimal))
      case AttrSetManDate(_, _, Appl, Seq(set), _, _, _)       => addSet(a, set)
      case AttrSetManUUID(_, _, Appl, Seq(set), _, _, _)       => addSet(a, set)
      case AttrSetManURI(_, _, Appl, Seq(set), _, _, _)        => addSet(a, set)
      case AttrSetManByte(_, _, Appl, Seq(set), _, _, _)       => addSet(a, set.map(_.toInt))
      case AttrSetManShort(_, _, Appl, Seq(set), _, _, _)      => addSet(a, set.map(_.toInt))
      case AttrSetManChar(_, _, Appl, Seq(set), _, _, _)       => addSet(a, set.map(_.toString))
      case _                                                   =>
        if (attr.op != Appl)
          throw MoleculeException("Can only save one applied Set of values for each Set attribute. " +
            s"Found other expression `${
              attr.op
            }` in: " + attr)
        throw MoleculeException("Can only save one Set of values per Set attribute. Found: " + attr)
    }
  }

  def resolveAttrSetOpt(attr: AttrSetOpt, a: Keyword): Unit = {
    attr match {
      case AttrSetOptString(_, _, Appl, Some(Seq(set)), _, _, _)     => addSet(a, set)
      case AttrSetOptInt(_, _, Appl, Some(Seq(set)), _, _, _)        => addSet(a, set)
      case AttrSetOptLong(_, _, Appl, Some(Seq(set)), _, _, _)       => addSet(a, set)
      case AttrSetOptFloat(_, _, Appl, Some(Seq(set)), _, _, _)      => addSet(a, set)
      case AttrSetOptDouble(_, _, Appl, Some(Seq(set)), _, _, _)     => addSet(a, set)
      case AttrSetOptBoolean(_, _, Appl, Some(Seq(set)), _, _, _)    => addSet(a, set)
      case AttrSetOptBigInt(_, _, Appl, Some(Seq(set)), _, _, _)     => addSet(a, set.map(_.bigInteger))
      case AttrSetOptBigDecimal(_, _, Appl, Some(Seq(set)), _, _, _) => addSet(a, set.map(_.bigDecimal))
      case AttrSetOptDate(_, _, Appl, Some(Seq(set)), _, _, _)       => addSet(a, set)
      case AttrSetOptUUID(_, _, Appl, Some(Seq(set)), _, _, _)       => addSet(a, set)
      case AttrSetOptURI(_, _, Appl, Some(Seq(set)), _, _, _)        => addSet(a, set)
      case AttrSetOptByte(_, _, Appl, Some(Seq(set)), _, _, _)       => addSet(a, set.map(_.toInt))
      case AttrSetOptShort(_, _, Appl, Some(Seq(set)), _, _, _)      => addSet(a, set.map(_.toInt))
      case AttrSetOptChar(_, _, Appl, Some(Seq(set)), _, _, _)       => addSet(a, set.map(_.toString))
      case _                                                         =>
        if (attr.op != Appl)
          throw MoleculeException("Can only save one applied Set of values for each Set attribute. " +
            s"Found other expression `${
              attr.op
            }` in: " + attr)
        throw MoleculeException("Can only save one Set of values per Set attribute. Found: " + attr)
    }
  }


  protected def addV(a: Keyword, value: Any): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(a)
    stmt.add(value.asInstanceOf[AnyRef])
    stmts.add(stmt)
  }

  protected def addSet[T](a: Keyword, set: Set[T]): Unit = {
    set.foreach { v =>
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(v.asInstanceOf[AnyRef])
      stmts.add(stmt)
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