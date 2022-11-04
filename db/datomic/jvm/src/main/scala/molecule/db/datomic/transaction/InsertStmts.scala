package molecule.db.datomic.transaction

import java.util.{Collections, List => jList, Set => jSet}
import clojure.lang.Keyword
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import scala.annotation.tailrec


class InsertStmts(elements: Seq[Element], tpls: Seq[Product]) extends InsertResolvers_(elements) {


  def getStmts: jList[jList[_]] = {
    val tpl2stmts = getResolver
    tpls.foreach { tpl =>
      e = newId
      tpl2stmts(tpl)
    }

    println("\n--- INSERT --------------------------------------------------------")
    elements.foreach(println)
    println("---")
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
        case attr: Attr =>
          val a = kw(attr.ns, attr.attr)
          attr match {
            case attr: AttrOne =>
              attr match {
                case attr: AttrOneMan => resolve(tail, resolvers :+ resolveAttrOneMan(attr, n, a), n + 1)
                case attr: AttrOneOpt => resolve(tail, resolvers :+ resolveAttrOneOpt(attr, n, a), n + 1)
              }
            case attr: AttrSet =>
              attr match {
                case attr: AttrSetMan => resolve(tail, resolvers :+ resolveAttrSetMan(attr, n, a), n + 1)
                case attr: AttrSetOpt => resolve(tail, resolvers :+ resolveAttrSetOpt(attr, n, a), n + 1)
              }
          }

        case Ref(ns, refAttr, refNs, one) => resolvers
        case other                        =>
          throw MoleculeException("Unexpected element: " + other)
      }
      case Nil             => resolvers
    }
  }

  private def resolveAttrOneMan(attr: AttrOneMan, n: Int, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrOneManString     => addV(a, n, identity)
      case _: AttrOneManInt        => addV(a, n, identity)
      case _: AttrOneManLong       => addV(a, n, identity)
      case _: AttrOneManFloat      => addV(a, n, identity)
      case _: AttrOneManDouble     => addV(a, n, identity)
      case _: AttrOneManBoolean    => addV(a, n, identity)
      case _: AttrOneManBigInt     => addV(a, n, bigInt2java)
      case _: AttrOneManBigDecimal => addV(a, n, bigDec2java)
      case _: AttrOneManDate       => addV(a, n, identity)
      case _: AttrOneManUUID       => addV(a, n, identity)
      case _: AttrOneManURI        => addV(a, n, identity)
      case _: AttrOneManByte       => addV(a, n, byte2java)
      case _: AttrOneManShort      => addV(a, n, short2java)
      case _: AttrOneManChar       => addV(a, n, char2java)
    }
  }

  private def resolveAttrOneOpt(attr: AttrOneOpt, n: Int, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrOneOptString     => addOptV(a, n, identity)
      case _: AttrOneOptInt        => addOptV(a, n, identity)
      case _: AttrOneOptLong       => addOptV(a, n, identity)
      case _: AttrOneOptFloat      => addOptV(a, n, identity)
      case _: AttrOneOptDouble     => addOptV(a, n, identity)
      case _: AttrOneOptBoolean    => addOptV(a, n, identity)
      case _: AttrOneOptBigInt     => addOptV(a, n, bigInt2java)
      case _: AttrOneOptBigDecimal => addOptV(a, n, bigDec2java)
      case _: AttrOneOptDate       => addOptV(a, n, identity)
      case _: AttrOneOptUUID       => addOptV(a, n, identity)
      case _: AttrOneOptURI        => addOptV(a, n, identity)
      case _: AttrOneOptByte       => addOptV(a, n, byte2java)
      case _: AttrOneOptShort      => addOptV(a, n, short2java)
      case _: AttrOneOptChar       => addOptV(a, n, char2java)
    }
  }

  private def resolveAttrSetMan(attr: AttrSetMan, n: Int, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrSetManString     => addSet(a, n, identity)
      case _: AttrSetManInt        => addSet(a, n, identity)
      case _: AttrSetManLong       => addSet(a, n, identity)
      case _: AttrSetManFloat      => addSet(a, n, identity)
      case _: AttrSetManDouble     => addSet(a, n, identity)
      case _: AttrSetManBoolean    => addSet(a, n, identity)
      case _: AttrSetManBigInt     => addSet(a, n, bigInt2java)
      case _: AttrSetManBigDecimal => addSet(a, n, bigDec2java)
      case _: AttrSetManDate       => addSet(a, n, identity)
      case _: AttrSetManUUID       => addSet(a, n, identity)
      case _: AttrSetManURI        => addSet(a, n, identity)
      case _: AttrSetManByte       => addSet(a, n, byte2java)
      case _: AttrSetManShort      => addSet(a, n, short2java)
      case _: AttrSetManChar       => addSet(a, n, char2java)
    }
  }

  private def resolveAttrSetOpt(attr: AttrSetOpt, n: Int, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrSetOptString     => addOptSet(a, n, identity)
      case _: AttrSetOptInt        => addOptSet(a, n, identity)
      case _: AttrSetOptLong       => addOptSet(a, n, identity)
      case _: AttrSetOptFloat      => addOptSet(a, n, identity)
      case _: AttrSetOptDouble     => addOptSet(a, n, identity)
      case _: AttrSetOptBoolean    => addOptSet(a, n, identity)
      case _: AttrSetOptBigInt     => addOptSet(a, n, bigInt2java)
      case _: AttrSetOptBigDecimal => addOptSet(a, n, bigDec2java)
      case _: AttrSetOptDate       => addOptSet(a, n, identity)
      case _: AttrSetOptUUID       => addOptSet(a, n, identity)
      case _: AttrSetOptURI        => addOptSet(a, n, identity)
      case _: AttrSetOptByte       => addOptSet(a, n, byte2java)
      case _: AttrSetOptShort      => addOptSet(a, n, short2java)
      case _: AttrSetOptChar       => addOptSet(a, n, char2java)
    }
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

  private def addSet(a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(n).asInstanceOf[Set[Any]].foreach { v =>
        stmt = stmtList
        stmt.add(add)
        stmt.add(e)
        stmt.add(a)
        stmt.add(value(v).asInstanceOf[AnyRef])
        stmts.add(stmt)
      }
    }
  }
  private def addOptSet(a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      tpl.productElement(n) match {
        case Some(set: Set[_]) =>
          set.foreach { v =>
            stmt = stmtList
            stmt.add(add)
            stmt.add(e)
            stmt.add(a)
            stmt.add(value(v).asInstanceOf[AnyRef])
            stmts.add(stmt)
          }
        case None    => // no statement to insert
      }
    }
  }

  private def addRef(refAttr: Keyword): () => Unit = {
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