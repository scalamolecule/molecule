package molecule.db.datomic.transaction

import java.math.BigInteger
import java.util
import java.util.{Collections, List => jList}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import molecule.core.util.fns
import scala.annotation.tailrec


class InsertStmts(elements: Seq[Element], tpls: Seq[Product], tempIdInit: Int = 0)
  extends InsertResolvers(elements) {

  // Accumulate java insert data
  final private val stmts: util.ArrayList[jList[AnyRef]] = new java.util.ArrayList[jList[AnyRef]]()

  def getStmts: jList[jList[_]] = {
    val tpl2stmts = getResolver
    tpls.foreach { tpl =>
      e = newId
      tpl2stmts(tpl)
    }

    elements.foreach(println)
    println("--- INSERT --------------")
    stmts.forEach(stmt => println(stmt))

    Collections.unmodifiableList(stmts)
  }

  @tailrec
  final override protected def resolve(
    elements: Seq[Element],
    resolvers: List[Product => Unit],
    n: Int = 0
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case atom: Atom =>
          val a = kw(atom.ns, atom.attr)
          atom match {
            case atom: AtomOneMan => resolve(tail, resolvers :+ resolveAtomOneMan(atom, n, a), n + 1)
            case atom: AtomOneOpt => resolve(tail, resolvers :+ resolveAtomOneOpt(atom, n, a), n + 1)
          }

        case Bond(ns, refAttr, refNs, one) => resolvers
        case other                         =>
          throw MoleculeException("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }

  import clojure.lang.Keyword

  private def kw(ns: String, attr: String) = Keyword.intern(ns, attr)
  private lazy val add     = kw("db", "add")
  private lazy val retract = kw("db", "retract")

  private lazy val bigInt2java = (v: Any) => v.asInstanceOf[BigInt].bigInteger
  private lazy val bigDec2java = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal
  private lazy val char2java   = (v: Any) => v.toString
  private lazy val byte2java   = (v: Any) => v.asInstanceOf[Byte].toInt
  private lazy val short2java  = (v: Any) => v.asInstanceOf[Short].toInt

  private def resolveAtomOneMan(atom: AtomOneMan, n: Int, a: Keyword): Product => Unit = {
    atom match {
      case _: AtomOneManString     => addV(a, n, identity)
      case _: AtomOneManInt        => addV(a, n, identity)
      case _: AtomOneManLong       => addV(a, n, identity)
      case _: AtomOneManFloat      => addV(a, n, identity)
      case _: AtomOneManDouble     => addV(a, n, identity)
      case _: AtomOneManBoolean    => addV(a, n, identity)
      case _: AtomOneManBigInt     => addV(a, n, bigInt2java)
      case _: AtomOneManBigDecimal => addV(a, n, bigDec2java)
      case _: AtomOneManDate       => addV(a, n, identity)
      case _: AtomOneManUUID       => addV(a, n, identity)
      case _: AtomOneManURI        => addV(a, n, identity)
      case _: AtomOneManChar       => addV(a, n, char2java)
      case _: AtomOneManByte       => addV(a, n, byte2java)
      case _: AtomOneManShort      => addV(a, n, short2java)
      case other                   => throw MoleculeException("Unexpected element: " + other)
    }
  }

  // todo
  private def resolveAtomOneOpt(atom: AtomOneOpt, n: Int, a: Keyword): Product => Unit = {
    atom match {
      case _: AtomOneOptString     => addOptV(a, n, identity)
      case _: AtomOneOptInt        => addOptV(a, n, identity)
      case _: AtomOneOptLong       => addOptV(a, n, identity)
      case _: AtomOneOptFloat      => addOptV(a, n, identity)
      case _: AtomOneOptDouble     => addOptV(a, n, identity)
      case _: AtomOneOptBoolean    => addOptV(a, n, identity)
      case _: AtomOneOptBigInt     => addOptV(a, n, bigInt2java)
      case _: AtomOneOptBigDecimal => addOptV(a, n, bigDec2java)
      case _: AtomOneOptDate       => addOptV(a, n, identity)
      case _: AtomOneOptUUID       => addOptV(a, n, identity)
      case _: AtomOneOptURI        => addOptV(a, n, identity)
      case _: AtomOneOptChar       => addOptV(a, n, char2java)
      case _: AtomOneOptByte       => addOptV(a, n, byte2java)
      case _: AtomOneOptShort      => addOptV(a, n, short2java)
      case other                   => throw MoleculeException("Unexpected element: " + other)
    }
  }

  @tailrec
  private def getNs(elements: Seq[Element]): String = elements.head match {
    case a: Atom       => a.ns
    case b: Bond       => b.ns
    case Composite(es) => getNs(es)
    case other         =>
      throw MoleculeException("StmtsBuilder: Unexpected head element: " + other)
  }

  private val nsFull: String        = getNs(elements)
  private val part  : String        = fns.partNs(nsFull).head
  private var tempId: Int           = tempIdInit
  private var lowest: Int           = tempIdInit
  private var e     : String        = ""
  private var stmt  : jList[AnyRef] = null
  private def stmtList = new java.util.ArrayList[AnyRef](4)


  private def newId: String = {
    tempId = lowest - 1
    lowest = tempId
    "#db/id[" + part + " " + tempId + "]"
  }
  private def prevId: String = {
    tempId += 1
    "#db/id[" + part + " " + tempId + "]"
  }


  private def addV(a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(value(tpl.productElement(n)).asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }
  private def addOptV(a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(n) match {
        case Some(v) =>
          stmt = stmtList
          stmt.add(add)
          stmt.add(e)
          stmt.add(a)
          stmt.add(value(v).asInstanceOf[AnyRef])
          stmts.add(stmt)
        case None    => // no statement to insert
      }
    }
  }



  //  private def oneValue(tpe: String): Any => AnyRef = tpe match {
  //    case "Int"        => (v: Any) => v.toString.toLong.asInstanceOf[AnyRef]
  //    case "BigInt"     => (v: Any) => new java.math.BigInteger(v.toString)
  //    case "BigDecimal" => (v: Any) => new java.math.BigDecimal(v.toString)
  //    case _            => (v: Any) => v.asInstanceOf[AnyRef]
  //  }

  private def addSet(a: Keyword, value: Any => AnyRef): Iterable[Any] => Unit = {
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

  private def addBond(refAttr: Keyword): () => Unit = {
    () => bond(refAttr)
  }

  private def addNested(a: Keyword): String => Unit = {
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

  private def vs[T](a: Keyword, array: Array[T]): Unit = {
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

  private def cvs[T](a: Keyword, vs: Seq[T]): Unit = {
    vs.foreach { v =>
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(v.asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }

  private def ccs[T](a: Keyword, sets: Seq[Set[T]]): Unit = {
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

  private def bond(refAttr: Keyword): Unit = {
    stmt = stmtList
    stmt.add(add)
    stmt.add(e)
    stmt.add(refAttr)
    e = newId
    stmt.add(e.asInstanceOf[AnyRef])
    stmts.add(stmt)
  }
}