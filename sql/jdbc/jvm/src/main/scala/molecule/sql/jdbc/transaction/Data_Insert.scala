package molecule.sql.jdbc.transaction

import java.net.URI
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertExtraction, InsertResolvers_, SaveExtraction}
import molecule.core.util.ModelUtils
import java.sql.{SQLException, Statement, PreparedStatement => PS}
import java.util.{Date, UUID}

trait Data_Insert
  extends JdbcTxBase_JVM
    with InsertOps
    with JdbcDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: InsertExtraction with InsertResolvers_ =>

  var colIndex = 0

  def getData(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    tpls: Seq[Product],
    eidIndex: Int = 0,
    debug: Boolean = true
  ): Data = {
    table = getInitialNs(elements)
    initTxBase(elements, eidIndex)
    val (mainElements, txMetaElements) = separateTxElements(elements)
    val tpl2data                       = getResolver(nsMap, mainElements)
    tpls.foreach { tpl =>
      colIndex = 0
      tpl2data(tpl)
    }
    //    if (txMetaElements.nonEmpty) {
    //      val txMetaStmts = (new SaveExtraction(true) with Data_Save)
    //        .getRawStmts(txMetaElements, datomicTx, false)
    //      stmts.addAll(txMetaStmts)
    //    }
    //    if (debug) {
    //      val insertStrs = "INSERT:" +: elements :+ "" :+ stmts.toArray().mkString("\n")
    //      logger.debug(insertStrs.mkString("\n").trim)
    //    }
    //    stmts

    insertNs()
    (stmts, setters)
  }


  override protected def addComposite(
    nsMap: Map[String, MetaNs],
    tpl: Int,
    tplIndex: Int,
    compositeElements: List[Element]
  ): Product => Unit = {
    hasComposites = true
    val composite2stmts = getResolver(nsMap, compositeElements, tpl)
    // Start from initial entity id for each composite sub group
    countValueAttrs(compositeElements) match {
      case 1 => (tpl: Product) =>
        e = e0
        composite2stmts(Tuple1(tpl.productElement(tplIndex)))
      case _ => (tpl: Product) =>
        e = e0
        composite2stmts(tpl.productElement(tplIndex).asInstanceOf[Product])
    }
  }

  override protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    // Recursively resolve nested data
    val nested2stmts = getResolver(nsMap, nestedElements)
    countValueAttrs(nestedElements) match {
      case 1 => // Nested arity-1 values
        (tpl: Product) => {
          val values       = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val nestedBaseId = e
          values.foreach { value =>
            e = nestedBaseId
            val nestedTpl = Tuple1(value)
            addRef(ns, refAttr, refNs)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls   = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val nestedBaseId = e
          nestedTpls.foreach { nestedTpl =>
            e = nestedBaseId
            addRef(ns, refAttr, refNs)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
    }
  }


  override protected def addOptV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(value) => appendStmt(add, e, a,
          scala2dbTpe(value.asInstanceOf[T]).asInstanceOf[AnyRef])
        case _           => () // no statement to insert
      }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex).asInstanceOf[Set[_]].foreach { value =>
        appendStmt(add, e, a, scala2dbTpe(value.asInstanceOf[T]).asInstanceOf[AnyRef])
      }
  }

  override protected def addOptSet[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) =>
          set.foreach(value =>
            appendStmt(add, e, a, scala2dbTpe(value.asInstanceOf[T]).asInstanceOf[AnyRef])
          )
        case None              => () // no statement to insert
      }
  }

  protected def addRefOLD(ns: String, refAttr: String): Product => Unit = {
    val a = kw(ns, refAttr)
    (_: Product) =>
      backRefs = backRefs + (ns -> e)
      stmt = stmtList
      stmt.add(add)
      stmt.add(e)
      stmt.add(a)
      e = newId
      stmt.add(e)
      stmtsOLD.add(stmt)
  }
  override protected def addRef(ns: String, refAttr: String, refNs: String): Product => Unit = {
    (_: Product) => {
      columns += refAttr
      val j = colIndex + 1
      colSetters = colSetters :+ ((ps: PS, refIds: List[Long]) => {
        refIds.size match {
          case 0 => ()
          case 1 => ps.setLong(j, refIds.head)
          case n => throw new Exception(s"todo - save $n refs in join table")
        }
      })
      insertNs()
      table = refNs
    }
  }

  override protected def addV[T](
    ns: String,
    attr: String,
    outerTpl: Int,
    tplIndex: Int,
    scala2dbTpe: T => Any,
  ): Product => Unit = {
    columns += attr
    (tpl: Product) => {
      val tplArity = tpl.productArity
      colIndex += 1
      //      println("tplArity: " + tplArity + "  -  " + colIndex)
      val j = colIndex
      colSetters = colSetters :+ ((ps: PS, _: List[Long]) => {
        val value  = tpl.productElement(tplIndex).asInstanceOf[T]
        val setter = scala2dbTpe(value).asInstanceOf[(PS, Int) => Unit]
        setter(ps, j)
        //        println("value: " + value)
        if (j == tplArity) {
          //          println("***")
          ps.addBatch()
        }
      })
    }
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    (_: Product) =>
      e = backRefs(backRefNs)
  }

  def insertNs(): Unit = {
    val cols = columns.toList
    val stmt =
      s"""insert into $table(
         |  ${cols.mkString(",\n  ")}
         |) values (${cols.map(_ => "?").mkString(", ")})""".stripMargin

    println("---- stmt -----------\n" + stmt)
    stmts = stmt :: stmts
    val colSetters1    = colSetters
    val setter: Setter = (ps: PS, refIds: List[Long]) =>
      colSetters1.foreach { colSetter =>
        colSetter.apply(ps, refIds)
      }
    setters = setter :: setters
    colIndex = 0
    columns.clear()
  }

  override protected lazy val valueString     = (v: String) => (ps: PS, n: Int) => ps.setString(n, v)
  override protected lazy val valueInt        = (v: Int) => (ps: PS, n: Int) => ps.setInt(n, v)
  override protected lazy val valueLong       = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val valueFloat      = (v: Float) => (ps: PS, n: Int) => ps.setFloat(n, v)
  override protected lazy val valueDouble     = (v: Double) => (ps: PS, n: Int) => ps.setDouble(n, v)
  override protected lazy val valueBoolean    = (v: Boolean) => (ps: PS, n: Int) => ps.setBoolean(n, v)
  override protected lazy val valueBigInt     = (v: BigInt) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v).bigDecimal)
  override protected lazy val valueBigDecimal = (v: BigDecimal) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.bigDecimal)
  override protected lazy val valueDate       = (v: Date) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.getTime))
  override protected lazy val valueUUID       = (v: UUID) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueURI        = (v: URI) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val valueByte       = (v: Byte) => (ps: PS, n: Int) => ps.setByte(n, v)
  override protected lazy val valueShort      = (v: Short) => (ps: PS, n: Int) => ps.setShort(n, v)
  override protected lazy val valueChar       = (v: Char) => (ps: PS, n: Int) => ps.setString(n, v.toString)
}