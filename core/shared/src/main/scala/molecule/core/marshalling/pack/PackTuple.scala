package molecule.core.marshalling.pack

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions.MoleculeException
import molecule.boilerplate.ast.Model._
import scala.annotation.tailrec


trait PackTuple { self: Tpls2DTO =>

  @tailrec
  final protected def resolvePackers(
    elements: Seq[Element],
    resolvers: List[Product => Unit],
    n: Int = 0
  ): List[Product => Unit] = {
    elements match {
      case element :: tail => element match {
        case a: Attr =>
          a match {
            case a: AttrOne =>
              a match {
                case a: AttrOneMan => resolvePackers(tail, resolvers :+ resolveAttrOneMan(a, n), n + 1)
                case a: AttrOneOpt => resolvePackers(tail, resolvers :+ resolveAttrOneOpt(a, n), n + 1)
              }
            case a: AttrSet =>
              a match {
                case a: AttrSetMan => resolvePackers(tail, resolvers :+ resolveAttrSetMan(a, n), n + 1)
                case a: AttrSetOpt => resolvePackers(tail, resolvers :+ resolveAttrSetOpt(a, n), n + 1)
              }
          }


        //        case Ref(ns, refAttr, _, _) =>
        //          prevRefs += refAttr
        //          resolve(tail, resolvers :+ addRef(ns, refAttr), n)
        //
        //        case BackRef(backRefNs) =>
        //          tail.head match {
        //            case Ref(_, refAttr, _, _) if prevRefs.contains(refAttr) => throw MoleculeException(
        //              s"Can't re-use previous namespace ${refAttr.capitalize} after backref _$backRefNs."
        //            )
        //            case _                                                   => // ok
        //          }
        //          resolve(tail, resolvers :+ addBackRef(backRefNs), n)
        //
        //        case Nested(Ref(ns, refAttr, _, _), elements) =>
        //          prevRefs.clear()
        //          resolve(tail, resolvers :+ addNested(n, ns, refAttr, elements), n)
        //
        //        case NestedOpt(Ref(ns, refAttr, _, _), elements) =>
        //          prevRefs.clear()
        //          resolve(tail, resolvers :+ addNested(n, ns, refAttr, elements), n)
        //
        //        case Composite(compositeElements) =>
        //          resolve(tail, resolvers :+ addComposite(n, compositeElements), n + 1)

        case other => unexpected(other)
      }
      case Nil             => resolvers
    }
  }

  protected def unexpected(element: Element) =
    throw MoleculeException("Unexpected element: " + element)

  private def resolveAttrOneMan(a: AttrOneMan, n: Int): Product => Unit = {
    a match {
      case _: AttrOneManString     => (tpl: Product) => oneString.addOne(tpl.productElement(n).asInstanceOf[String])
      case _: AttrOneManInt        => (tpl: Product) => oneInt.addOne(tpl.productElement(n).asInstanceOf[Int])
      case _: AttrOneManLong       => (tpl: Product) => oneLong.addOne(tpl.productElement(n).asInstanceOf[Long])
      case _: AttrOneManDouble     => (tpl: Product) => oneDouble.addOne(tpl.productElement(n).asInstanceOf[Double])
      case _: AttrOneManBoolean    => (tpl: Product) => oneBoolean.addOne(tpl.productElement(n).asInstanceOf[Boolean])
      case _: AttrOneManBigInt     => (tpl: Product) => oneBigInt.addOne(tpl.productElement(n).asInstanceOf[BigInt])
      case _: AttrOneManBigDecimal => (tpl: Product) => oneBigDecimal.addOne(tpl.productElement(n).asInstanceOf[BigDecimal])
      case _: AttrOneManDate       => (tpl: Product) => oneDate.addOne(tpl.productElement(n).asInstanceOf[Date])
      case _: AttrOneManUUID       => (tpl: Product) => oneUUID.addOne(tpl.productElement(n).asInstanceOf[UUID])
      case _: AttrOneManURI        => (tpl: Product) => oneURI.addOne(tpl.productElement(n).asInstanceOf[URI])
      case _: AttrOneManByte       => (tpl: Product) => oneByte.addOne(tpl.productElement(n).asInstanceOf[Byte])
      case _: AttrOneManShort      => (tpl: Product) => oneShort.addOne(tpl.productElement(n).asInstanceOf[Short])
      case _: AttrOneManFloat      => (tpl: Product) => oneFloat.addOne(tpl.productElement(n).asInstanceOf[Float])
      case _: AttrOneManChar       => (tpl: Product) => oneChar.addOne(tpl.productElement(n).asInstanceOf[Char])
    }
  }

  private def resolveAttrOneOpt(a: AttrOneOpt, n: Int): Product => Unit = {
    a match {
      case _: AttrOneOptString     => (tpl: Product) => oneOptString.addOne(tpl.productElement(n).asInstanceOf[Option[String]])
      case _: AttrOneOptInt        => (tpl: Product) => oneOptInt.addOne(tpl.productElement(n).asInstanceOf[Option[Int]])
      case _: AttrOneOptLong       => (tpl: Product) => oneOptLong.addOne(tpl.productElement(n).asInstanceOf[Option[Long]])
      case _: AttrOneOptFloat      => (tpl: Product) => oneOptDouble.addOne(tpl.productElement(n).asInstanceOf[Option[Double]])
      case _: AttrOneOptDouble     => (tpl: Product) => oneOptBoolean.addOne(tpl.productElement(n).asInstanceOf[Option[Boolean]])
      case _: AttrOneOptBoolean    => (tpl: Product) => oneOptBigInt.addOne(tpl.productElement(n).asInstanceOf[Option[BigInt]])
      case _: AttrOneOptBigInt     => (tpl: Product) => oneOptBigDecimal.addOne(tpl.productElement(n).asInstanceOf[Option[BigDecimal]])
      case _: AttrOneOptBigDecimal => (tpl: Product) => oneOptDate.addOne(tpl.productElement(n).asInstanceOf[Option[Date]])
      case _: AttrOneOptDate       => (tpl: Product) => oneOptUUID.addOne(tpl.productElement(n).asInstanceOf[Option[UUID]])
      case _: AttrOneOptUUID       => (tpl: Product) => oneOptURI.addOne(tpl.productElement(n).asInstanceOf[Option[URI]])
      case _: AttrOneOptURI        => (tpl: Product) => oneOptByte.addOne(tpl.productElement(n).asInstanceOf[Option[Byte]])
      case _: AttrOneOptByte       => (tpl: Product) => oneOptShort.addOne(tpl.productElement(n).asInstanceOf[Option[Short]])
      case _: AttrOneOptShort      => (tpl: Product) => oneOptFloat.addOne(tpl.productElement(n).asInstanceOf[Option[Float]])
      case _: AttrOneOptChar       => (tpl: Product) => oneOptChar.addOne(tpl.productElement(n).asInstanceOf[Option[Char]])
    }
  }

  private def resolveAttrSetMan(a: AttrSetMan, n: Int): Product => Unit = {
    a match {
      case _: AttrSetManString     => (tpl: Product) => setString.addOne(tpl.productElement(n).asInstanceOf[Set[String]])
      case _: AttrSetManInt        => (tpl: Product) => setInt.addOne(tpl.productElement(n).asInstanceOf[Set[Int]])
      case _: AttrSetManLong       => (tpl: Product) => setLong.addOne(tpl.productElement(n).asInstanceOf[Set[Long]])
      case _: AttrSetManFloat      => (tpl: Product) => setDouble.addOne(tpl.productElement(n).asInstanceOf[Set[Double]])
      case _: AttrSetManDouble     => (tpl: Product) => setBoolean.addOne(tpl.productElement(n).asInstanceOf[Set[Boolean]])
      case _: AttrSetManBoolean    => (tpl: Product) => setBigInt.addOne(tpl.productElement(n).asInstanceOf[Set[BigInt]])
      case _: AttrSetManBigInt     => (tpl: Product) => setBigDecimal.addOne(tpl.productElement(n).asInstanceOf[Set[BigDecimal]])
      case _: AttrSetManBigDecimal => (tpl: Product) => setDate.addOne(tpl.productElement(n).asInstanceOf[Set[Date]])
      case _: AttrSetManDate       => (tpl: Product) => setUUID.addOne(tpl.productElement(n).asInstanceOf[Set[UUID]])
      case _: AttrSetManUUID       => (tpl: Product) => setURI.addOne(tpl.productElement(n).asInstanceOf[Set[URI]])
      case _: AttrSetManURI        => (tpl: Product) => setByte.addOne(tpl.productElement(n).asInstanceOf[Set[Byte]])
      case _: AttrSetManByte       => (tpl: Product) => setShort.addOne(tpl.productElement(n).asInstanceOf[Set[Short]])
      case _: AttrSetManShort      => (tpl: Product) => setFloat.addOne(tpl.productElement(n).asInstanceOf[Set[Float]])
      case _: AttrSetManChar       => (tpl: Product) => setChar.addOne(tpl.productElement(n).asInstanceOf[Set[Char]])
    }
  }

  private def resolveAttrSetOpt(a: AttrSetOpt, n: Int): Product => Unit = {
    a match {
      case _: AttrSetOptString     => (tpl: Product) => setOptString.addOne(tpl.productElement(n).asInstanceOf[Option[Set[String]]])
      case _: AttrSetOptInt        => (tpl: Product) => setOptInt.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Int]]])
      case _: AttrSetOptLong       => (tpl: Product) => setOptLong.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Long]]])
      case _: AttrSetOptFloat      => (tpl: Product) => setOptDouble.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Double]]])
      case _: AttrSetOptDouble     => (tpl: Product) => setOptBoolean.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Boolean]]])
      case _: AttrSetOptBoolean    => (tpl: Product) => setOptBigInt.addOne(tpl.productElement(n).asInstanceOf[Option[Set[BigInt]]])
      case _: AttrSetOptBigInt     => (tpl: Product) => setOptBigDecimal.addOne(tpl.productElement(n).asInstanceOf[Option[Set[BigDecimal]]])
      case _: AttrSetOptBigDecimal => (tpl: Product) => setOptDate.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Date]]])
      case _: AttrSetOptDate       => (tpl: Product) => setOptUUID.addOne(tpl.productElement(n).asInstanceOf[Option[Set[UUID]]])
      case _: AttrSetOptUUID       => (tpl: Product) => setOptURI.addOne(tpl.productElement(n).asInstanceOf[Option[Set[URI]]])
      case _: AttrSetOptURI        => (tpl: Product) => setOptByte.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Byte]]])
      case _: AttrSetOptByte       => (tpl: Product) => setOptShort.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Short]]])
      case _: AttrSetOptShort      => (tpl: Product) => setOptFloat.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Float]]])
      case _: AttrSetOptChar       => (tpl: Product) => setOptChar.addOne(tpl.productElement(n).asInstanceOf[Option[Set[Char]]])
    }
  }


  //  override protected def addComposite(
  //    n: Int,
  //    elements: Seq[Element]
  //  ): Product => Unit = {
  //    hasComposites = true
  //    val composite2stmts = getResolver(elements)
  //    // Start from initial entity id for each composite sub group
  //    elements.length match {
  //      case 1 => (tpl: Product) =>
  //        e = e0
  //        composite2stmts(Tuple1(tpl.productElement(n)))
  //      case _ => (tpl: Product) =>
  //        e = e0
  //        composite2stmts(tpl.productElement(n).asInstanceOf[Product])
  //    }
  //  }
  //
  //  override protected def addNested(
  //    n: Int,
  //    ns: String,
  //    refAttr: String,
  //    elements: Seq[Element]
  //  ): Product => Unit = {
  //    val nested2stmts = getResolver(elements)
  //    val nestedArity  = elements.count {
  //      case _: Mandatory@unchecked => true
  //      case _: Optional@unchecked  => true
  //      case _                      => false
  //    }
  //    nestedArity match {
  //      case 1 =>
  //        (tpl: Product) => {
  //          val nested       = tpl.productElement(n).asInstanceOf[Seq[Any]]
  //          val nestedBaseId = e
  //          nested.foreach { value =>
  //            e = nestedBaseId
  //            val tpl = Tuple1(value)
  //            addRef(ns, refAttr)(tpl)
  //            e0 = e
  //            nested2stmts(tpl)
  //          }
  //        }
  //      case _ =>
  //        (tpl: Product) => {
  //          val nested       = tpl.productElement(n).asInstanceOf[Seq[Product]]
  //          val nestedBaseId = e
  //          nested.foreach { tpl =>
  //            e = nestedBaseId
  //            addRef(ns, refAttr)(tpl)
  //            e0 = e
  //            nested2stmts(tpl)
  //          }
  //        }
  //    }
  //  }

  //  override protected def addV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
  //    (tpl: Product) => tpl.productElement(n)
  //
  //  }
  //  override protected def addOptV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
  //    val a = kw(ns, attr)
  //    (tpl: Product) => {
  //      backRefs = backRefs + (ns -> e)
  //      tpl.productElement(n) match {
  //        case Some(v) =>
  //          appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
  //        case None    => // no statement to insert
  //      }
  //    }
  //  }
  //  override protected def addTxV(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
  //    val a = kw(ns, attr)
  //    (tpl: Product) => {
  //      e = datomicTx
  //      backRefs = backRefs + (ns -> e)
  //      appendStmt(add, e, a, value(tpl.productElement(n)).asInstanceOf[AnyRef])
  //    }
  //  }
  //
  //  override protected def addSet(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
  //    val a = kw(ns, attr)
  //    (tpl: Product) => {
  //      backRefs = backRefs + (ns -> e)
  //      tpl.productElement(n).asInstanceOf[Set[Any]].foreach { v =>
  //        appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
  //      }
  //    }
  //  }
  //  override protected def addOptSet(ns: String, attr: String, n: Int, value: Any => Any): Product => Unit = {
  //    val a = kw(ns, attr)
  //    (tpl: Product) => {
  //      backRefs = backRefs + (ns -> e)
  //      tpl.productElement(n) match {
  //        case Some(set: Set[_]) =>
  //          set.foreach { v =>
  //            appendStmt(add, e, a, value(v).asInstanceOf[AnyRef])
  //          }
  //        case None              => // no statement to insert
  //      }
  //    }
  //  }
  //
  //  override protected def addRef(ns: String, refAttr: String): Product => Unit = {
  //    val a = kw(ns, refAttr)
  //    (_: Product) =>
  //      backRefs = backRefs + (ns -> e)
  //      stmt = stmtList
  //      stmt.add(add)
  //      stmt.add(e)
  //      stmt.add(a)
  //      e = newId
  //      stmt.add(e)
  //      stmts.add(stmt)
  //  }
  //
  //  override protected def addBackRef(backRefNs: String): Product => Unit = {
  //    (_: Product) => e = backRefs(backRefNs)
  //  }
  //
  //  override protected lazy val valueString     = identity
  //  override protected lazy val valueInt        = identity
  //  override protected lazy val valueLong       = identity
  //  override protected lazy val valueFloat      = identity
  //  override protected lazy val valueDouble     = identity
  //  override protected lazy val valueBoolean    = boolean2java
  //  override protected lazy val valueBigInt     = bigInt2java
  //  override protected lazy val valueBigDecimal = bigDec2java
  //  override protected lazy val valueDate       = identity
  //  override protected lazy val valueUUID       = identity
  //  override protected lazy val valueURI        = identity
  //  override protected lazy val valueByte       = byte2java
  //  override protected lazy val valueShort      = short2java
  //  override protected lazy val valueChar       = char2java
}
