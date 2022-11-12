package molecule.db.datomic.transaction

import java.util.{Collections, List => jList, Set => jSet}
import clojure.lang.Keyword
import molecule.base.ast.SchemaAST._
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.MoleculeModel._
import scala.annotation.tailrec


class InsertStmts(elements: Seq[Element], tpls: Seq[Product]) extends InsertResolvers_(elements) {
  lazy val notImpl = MoleculeException("Not implemented yet...")


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
          val ns = attr.ns
          val a  = kw(attr.ns, attr.attr)
          attr match {
            case attr: AttrOne =>
              attr match {
                case attr: AttrOneMan => resolve(tail, resolvers :+ resolveAttrOneMan(attr, n, ns, a), n + 1)
                case attr: AttrOneOpt => resolve(tail, resolvers :+ resolveAttrOneOpt(attr, n, ns, a), n + 1)
              }
            case attr: AttrSet =>
              attr match {
                case attr: AttrSetMan => resolve(tail, resolvers :+ resolveAttrSetMan(attr, n, ns, a), n + 1)
                case attr: AttrSetOpt => resolve(tail, resolvers :+ resolveAttrSetOpt(attr, n, ns, a), n + 1)
              }
          }

        case Ref(ns, refAttr, _, _) => resolve(tail, resolvers :+ addRef(ns, kw(ns, refAttr)), n)
        case BackRef(backRefNs)     => resolve(tail, resolvers :+ addBackRef(backRefNs), n)

        case other => unexpected(other)
      }
      case Nil             => resolvers
    }
  }

  private def resolveAttrOneMan(attr: AttrOneMan, n: Int, ns: String, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrOneManString     => addV(ns, a, n, identity)
      case _: AttrOneManInt        => addV(ns, a, n, identity)
      case _: AttrOneManLong       => addV(ns, a, n, identity)
      case _: AttrOneManFloat      => addV(ns, a, n, identity)
      case _: AttrOneManDouble     => addV(ns, a, n, identity)
      case _: AttrOneManBoolean    => addV(ns, a, n, boolean2java)
      case _: AttrOneManBigInt     => addV(ns, a, n, bigInt2java)
      case _: AttrOneManBigDecimal => addV(ns, a, n, bigDec2java)
      case _: AttrOneManDate       => addV(ns, a, n, identity)
      case _: AttrOneManUUID       => addV(ns, a, n, identity)
      case _: AttrOneManURI        => addV(ns, a, n, identity)
      case _: AttrOneManByte       => addV(ns, a, n, byte2java)
      case _: AttrOneManShort      => addV(ns, a, n, short2java)
      case _: AttrOneManChar       => addV(ns, a, n, char2java)
    }
  }

  private def resolveAttrOneOpt(attr: AttrOneOpt, n: Int, ns: String, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrOneOptString     => addOptV(ns, a, n, identity)
      case _: AttrOneOptInt        => addOptV(ns, a, n, identity)
      case _: AttrOneOptLong       => addOptV(ns, a, n, identity)
      case _: AttrOneOptFloat      => addOptV(ns, a, n, identity)
      case _: AttrOneOptDouble     => addOptV(ns, a, n, identity)
      case _: AttrOneOptBoolean    => addOptV(ns, a, n, boolean2java)
      case _: AttrOneOptBigInt     => addOptV(ns, a, n, bigInt2java)
      case _: AttrOneOptBigDecimal => addOptV(ns, a, n, bigDec2java)
      case _: AttrOneOptDate       => addOptV(ns, a, n, identity)
      case _: AttrOneOptUUID       => addOptV(ns, a, n, identity)
      case _: AttrOneOptURI        => addOptV(ns, a, n, identity)
      case _: AttrOneOptByte       => addOptV(ns, a, n, byte2java)
      case _: AttrOneOptShort      => addOptV(ns, a, n, short2java)
      case _: AttrOneOptChar       => addOptV(ns, a, n, char2java)
    }
  }

  private def resolveAttrSetMan(attr: AttrSetMan, n: Int, ns: String, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrSetManString     => addSet(ns, a, n, identity)
      case _: AttrSetManInt        => addSet(ns, a, n, identity)
      case _: AttrSetManLong       => addSet(ns, a, n, identity)
      case _: AttrSetManFloat      => addSet(ns, a, n, identity)
      case _: AttrSetManDouble     => addSet(ns, a, n, identity)
      case _: AttrSetManBoolean    => addSet(ns, a, n, boolean2java)
      case _: AttrSetManBigInt     => addSet(ns, a, n, bigInt2java)
      case _: AttrSetManBigDecimal => addSet(ns, a, n, bigDec2java)
      case _: AttrSetManDate       => addSet(ns, a, n, identity)
      case _: AttrSetManUUID       => addSet(ns, a, n, identity)
      case _: AttrSetManURI        => addSet(ns, a, n, identity)
      case _: AttrSetManByte       => addSet(ns, a, n, byte2java)
      case _: AttrSetManShort      => addSet(ns, a, n, short2java)
      case _: AttrSetManChar       => addSet(ns, a, n, char2java)
    }
  }

  private def resolveAttrSetOpt(attr: AttrSetOpt, n: Int, ns: String, a: Keyword): Product => Unit = {
    attr match {
      case _: AttrSetOptString     => addOptSet(ns, a, n, identity)
      case _: AttrSetOptInt        => addOptSet(ns, a, n, identity)
      case _: AttrSetOptLong       => addOptSet(ns, a, n, identity)
      case _: AttrSetOptFloat      => addOptSet(ns, a, n, identity)
      case _: AttrSetOptDouble     => addOptSet(ns, a, n, identity)
      case _: AttrSetOptBoolean    => addOptSet(ns, a, n, boolean2java)
      case _: AttrSetOptBigInt     => addOptSet(ns, a, n, bigInt2java)
      case _: AttrSetOptBigDecimal => addOptSet(ns, a, n, bigDec2java)
      case _: AttrSetOptDate       => addOptSet(ns, a, n, identity)
      case _: AttrSetOptUUID       => addOptSet(ns, a, n, identity)
      case _: AttrSetOptURI        => addOptSet(ns, a, n, identity)
      case _: AttrSetOptByte       => addOptSet(ns, a, n, byte2java)
      case _: AttrSetOptShort      => addOptSet(ns, a, n, short2java)
      case _: AttrSetOptChar       => addOptSet(ns, a, n, char2java)
    }
  }


  private def addV(ns: String, a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      stmt.add(value(tpl.productElement(n)).asInstanceOf[AnyRef])
      stmts.add(stmt)
    }
  }
  private def addOptV(ns: String, a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
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

  private def addSet(ns: String, a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
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
  private def addOptSet(ns: String, a: Keyword, n: Int, value: Any => Any): Product => Unit = {
    (tpl: Product) => {
      backRefs = backRefs + (ns -> e)
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
        case None              => // no statement to insert
      }
    }
  }

  private def addRef(ns: String, refAttr: Keyword): Product => Unit = {
    (_: Product) =>
      backRefs = backRefs + (ns -> e)
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(refAttr)
      e = newId
      stmt.add(e.asInstanceOf[AnyRef])
      stmts.add(stmt)
  }

  private def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) => e = backRefs(backRefNs)
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


  //  // Tx meta data is already extracted in the query preparation process.
  //
  //  private def vs[T](a: Keyword, array: Array[T]): Unit = {
  //    if (array.length != 1) {
  //      throw MoleculeException(
  //        "Only a single value can be applied to a tx meta attribute when inserting."
  //      )
  //    }
  //    stmt = stmtList
  //    stmt.add(add)
  //    stmt.add(e)
  //    stmt.add(a)
  //    stmt.add(array.head.asInstanceOf[AnyRef])
  //    stmts.add(stmt)
  //  }
  //
  //  private def cvs[T](a: Keyword, vs: Seq[T]): Unit = {
  //    vs.foreach { v =>
  //      stmt = stmtList
  //      stmt.add(add)
  //      stmt.add(e)
  //      stmt.add(a)
  //      stmt.add(v.asInstanceOf[AnyRef])
  //      stmts.add(stmt)
  //    }
  //  }
  //
  //  private def ccs[T](a: Keyword, sets: Seq[Set[T]]): Unit = {
  //    if (sets.length != 1) {
  //      throw MoleculeException(
  //        "Only a single set of values can be applied to a tx meta attribute when inserting."
  //      )
  //    }
  //    sets.head.foreach { v =>
  //      stmt = stmtList
  //      stmt.add(add)
  //      stmt.add(e)
  //      stmt.add(a)
  //      stmt.add(v.asInstanceOf[AnyRef])
  //      stmts.add(stmt)
  //    }
  //  }

  //  private def ref(refAttr: Keyword): Unit = {
  //    stmt = stmtList
  //    stmt.add(add)
  //    stmt.add(e)
  //    stmt.add(refAttr)
  //    e = newId
  //    stmt.add(e.asInstanceOf[AnyRef])
  //    stmts.add(stmt)
  //  }
}