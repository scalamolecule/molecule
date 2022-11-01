package molecule.db.datomic.transaction

import java.util
import java.util.{Collections, List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.util.fns
import scala.annotation.tailrec
import scala.collection.immutable.Seq


class SaveStmts(elements: Seq[Element]) {

  // Accumulate java insert data
  final private val stmts: util.ArrayList[jList[AnyRef]] = new java.util.ArrayList[jList[AnyRef]]()

  def getStmts: jList[jList[_]] = {
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
        // Prepare java-compatible types
        case attr: Attr =>
          val a = kw(attr.ns, attr.attr)
          attr match {
            case attr: AttrOneMan => resolveAttrOneMan(attr, a); resolve(tail)
            case attr: AttrOneOpt => resolveAttrOneOpt(attr, a); resolve(tail)
          }

        case Ref(ns, refAttr, refNs, one) => ()
        case element                      => unexpected(element)
      }
      case Nil             => ()
    }
  }

  import clojure.lang.Keyword

  private def kw(ns: String, attr: String) = Keyword.intern(ns, attr)

  private lazy val add     = kw("db", "add")
  private lazy val retract = kw("db", "retract")

  private def unexpected(element: Element) = throw MoleculeException("Unexpected element: " + element)

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
      case AttrOneManChar(_, _, Appl, Seq(v), _, _, _)       => addV(a, v.toString)
      case AttrOneManByte(_, _, Appl, Seq(v), _, _, _)       => addV(a, v.toInt)
      case AttrOneManShort(_, _, Appl, Seq(v), _, _, _)      => addV(a, v.toInt)
      case _                                                 =>
        if (attr.op != Appl)
          throw MoleculeException("Can only save one applied value for each attribute. " +
            s"Found other expression `${attr.op}` in: " + attr)
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
      case AttrOneOptChar(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v.toString)
      case AttrOneOptByte(_, _, Appl, Some(Seq(v)), _, _, _)       => addV(a, v.toInt)
      case AttrOneOptShort(_, _, Appl, Some(Seq(v)), _, _, _)      => addV(a, v.toInt)
      case _                                                       =>
        if (attr.op != Appl)
          throw MoleculeException("Can only save one applied value for each attribute. " +
            s"Found other expression `${attr.op}` in: " + attr)
        throw MoleculeException("Can only save one value per attribute. Found: " + attr)
    }
  }

  @tailrec
  private def getNs(elements: Seq[Element]): String = elements.head match {
    case a: Attr       => a.ns
    case b: Ref        => b.ns
    case Composite(es) => getNs(es)
    case element       => unexpected(element)
  }

  private val nsFull  : String        = getNs(elements)
  private val part    : String        = fns.partNs(nsFull).head
  private   var tempId: Int           = 0
  protected var lowest: Int           = 0
  protected var e     : String        = ""
  protected var stmt  : jList[AnyRef] = null
  protected def stmtList = new java.util.ArrayList[AnyRef](4)


  protected def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }
  protected def prevId: String = {
    tempId += 1
    "#db/id[" + part + " " + tempId + "]"
  }


  //  protected def oneValue(tpe: String): Any => AnyRef = tpe match {
  //    case "Int"        => (v: Any) => v.toString.toLong.asInstanceOf[AnyRef]
  //    case "BigInt"     => (v: Any) => new java.math.BigInteger(v.toString)
  //    case "BigDecimal" => (v: Any) => new java.math.BigDecimal(v.toString)
  //    case _            => (v: Any) => v.asInstanceOf[AnyRef]
  //  }


  protected def addV(a: Keyword, value: Any): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(a)
    stmt.add(value.asInstanceOf[AnyRef])
    stmts.add(stmt)
  }

  protected def addSet(a: Keyword, value: Any => AnyRef): Iterable[Any] => Unit = {
    (set: Iterable[Any]) => {
      set.foreach { v =>
        stmt = stmtList
        stmt.add(add)
        stmt.add(e)
        stmt.add(a)
        stmt.add(value(v))
        stmts.add(stmt)
      }
    }
  }

  protected def addRef(refAttr: Keyword): () => Unit = {
    () => bond(refAttr)
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


  // Tx meta data is already extracted in the query preparation process.

  protected def vs[T](a: Keyword, array: Array[T]): Unit = {
    if (array.length != 1) {
      throw MoleculeException(
        "Only a single value can be applied to a tx meta attribute when inserting."
      )
    }
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(a)
    stmt.add(array.head.asInstanceOf[AnyRef])
    stmts.add(stmt)
  }

  protected def cvs[T](a: Keyword, vs: Seq[T]): Unit = {
    vs.foreach { v =>
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(v.asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }

  protected def ccs[T](a: Keyword, sets: Seq[Set[T]]): Unit = {
    if (sets.length != 1) {
      throw MoleculeException(
        "Only a single set of values can be applied to a tx meta attribute when inserting."
      )
    }
    sets.head.foreach { v =>
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(v.asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }

  protected def bond(refAttr: Keyword): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(refAttr)
    e = newId
    stmt.add(e.asInstanceOf[AnyRef])
    stmts.add(stmt)
  }
}