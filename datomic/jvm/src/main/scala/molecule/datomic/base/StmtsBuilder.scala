package molecule.datomic.base

import java.util
import java.util.{Collections, List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.moleculeModel._
import molecule.core.util.fns

import scala.annotation.tailrec

/** Statements builder helper class for inserts
 *
 * @param elements
 * @param tempIdInit
 */
class StmtsBuilder(elements: Seq[Element], tpls: Seq[Product], tempIdInit: Int = 0)
  extends StmtsBuilderN(elements) {

  def getStmts: jList[jList[_]] = {
    val tpl2stmts = getResolver
    tpls.foreach { tpl =>
      e = newId
      tpl2stmts(tpl)
    }
    stmts.forEach(s => println(s))
    Collections.unmodifiableList(stmts)
  }

  @tailrec
  final override protected def resolve(
    elements: Seq[Element],
    acc: List[Product => Unit],
    n: Int = 0
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case atom: AtomMandatory =>
          val a = kw(atom.ns, atom.attr)
          val (v, n1) = atom match {
            case _: AtomManString => (addV(a, n, _.toString), n + 1)
            case _: AtomManInt    => (addV(a, n, _.toString.toInt.asInstanceOf[AnyRef]), n + 1)
            case other            => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
          }
          resolve(tail, acc :+ v, n1)
//          atom match {
//            case _: AtomManString => res(tail, acc :+ addV(a, n, _.toString), n + 1)
//            case _: AtomManInt    => res(tail, acc :+ addV(a, n, _.toString.toInt.asInstanceOf[AnyRef]), n + 1)
//            case other            => throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
//          }

        case Bond(ns, refAttr, refNs, 1) => acc
        case other                       =>
          throw MoleculeException("StmtsBuilder.res: Unexpected element: " + other)
      }
      case Nil             => acc
    }
  }

  import clojure.lang.Keyword

  protected def kw(ns: String, attr: String) = Keyword.intern(ns, attr)

  protected lazy val add     = kw("db", "add")
  protected lazy val retract = kw("db", "retract")

  // Accumulate java insert data
  val stmts: util.ArrayList[jList[AnyRef]] = new java.util.ArrayList[jList[AnyRef]]()


  @tailrec
  private def getNs(elements: Seq[Element]): String = elements.head match {
    case a: Atom       => a.ns
    case b: Bond       => b.ns
    case Composite(es) => getNs(es)
    case other         =>
      throw MoleculeException("StmtsBuilder: Unexpected head element: " + other)
  }

  private val nsFull  : String        = getNs(elements)
  private val part    : String        = fns.partNs(nsFull).head
  private   var tempId: Int           = tempIdInit
  protected var lowest: Int           = tempIdInit
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


  protected def oneValue(tpe: String): Any => AnyRef = tpe match {
    case "Int"        => (v: Any) => v.toString.toLong.asInstanceOf[AnyRef]
    case "BigInt"     => (v: Any) => new java.math.BigInteger(v.toString)
    case "BigDecimal" => (v: Any) => new java.math.BigDecimal(v.toString)
    case _            => (v: Any) => v.asInstanceOf[AnyRef]
  }


  protected def addV(a: Keyword, n: Int, value: Any => AnyRef): Product => Unit = {
    (tpl: Product) => {
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(value(tpl.productElement(n)))
      stmts.add(stmt)
    }
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

  protected def addBond(refAttr: Keyword): () => Unit = {
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