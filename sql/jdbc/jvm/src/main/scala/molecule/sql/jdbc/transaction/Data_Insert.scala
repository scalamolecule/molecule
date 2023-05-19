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
import scala.collection.mutable

trait Data_Insert
  extends JdbcTxBase_JVM
    with InsertOps
    with JdbcDataType_JVM
    with ModelUtils

    with MoleculeLogging { self: InsertExtraction with InsertResolvers_ =>

  private var level    = 0
  private var firstRow = true

  // Current path of (ns, ns/ns_refAttr) pairs
  private var selfRefs = List.empty[(String, String)]

  // Ordered tables to have data inserted
  var tableCols = List.empty[(String, String, List[String])]

  // PreparedStatement param indexes for each (table, col) coordinate
  var paramIndexes = Map.empty[(String, String, String), Int]

  val colSetterMap = mutable.Map.empty[(String, String), List[Setter]]
  val resMap       = mutable.Map.empty[(String, String), List[Setter]]
  val resolverMap  = mutable.Map.empty[(String, String), Resolver]


  def getData(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    tpls: Seq[Product],
  ): Data = {
    //    elements.foreach(println)

    val (mainElements, _) = separateTxElements(elements)
    val tpl2data          = getResolver(nsMap, mainElements)

    // Loop rows of tuples
    tpls.zipWithIndex.foreach { case (tpl, rowIndex) =>
      tpl2data(tpl)
      addRowSetters(rowIndex)
      firstRow = false
    }
    getInsertResolvers
  }

  private def getInsertResolvers: List[Resolver] = {
    //
    tableCols.map { case (ns, selfRef, _) =>
      val colSetters    = resMap((ns, selfRef))
      val resolveInsert = (ps: PS, refMap: Map[(Int, String, String), Array[Long]], rowIndex: Int) => {
        // Set all column values for this row in this insert/batch
        colSetters.foreach(colSetter =>
          colSetter(ps, refMap, rowIndex)
        )
      }
      resolverMap((ns, selfRef)).copy(resolve = resolveInsert)
    }
  }

  private def addRowSetters(rowIndex: Int): Unit = {
    if (firstRow) {
      tableCols.foreach {
        case (ns, selfRef, cols) =>
          val stmt =
            s"""INSERT INTO $ns (
               |  ${cols.mkString(",\n  ")}
               |) VALUES (${cols.map(_ => "?").mkString(", ")})""".stripMargin
          val ps   = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)

//          println(".......................\n" + stmt)
          println(stmt)

          resolverMap((ns, selfRef)) = Resolver(level, ns, selfRef, stmt, ps)
          val colSetters = colSetterMap((ns, selfRef))

          println(s"----------------------  $ns${padS(6, ns)}  $selfRef${padS(12, selfRef)}  ${colSetters.length}")

          colSetterMap((ns, selfRef)) = Nil
          val resolveRow = (ps: PS, refMap: Map[(Int, String, String), Array[Long]], _: Int) => {
            // Set all column values for this row in this insert/batch
            colSetters.foreach(colSetter =>
              colSetter(ps, refMap, rowIndex)
            )
            // Complete row
            ps.addBatch()
          }
          resMap((ns, selfRef)) = List(resolveRow)
      }
    } else {
      tableCols.foreach {
        case (ns, selfRef, _) =>
          val colSetters = colSetterMap((ns, selfRef))
          colSetterMap((ns, selfRef)) = Nil
          val resolveRow = (ps: PS, refMap: Map[(Int, String, String), Array[Long]], _: Int) => {
            // Set all column values for this insert/batch
            colSetters.foreach(colSetter =>
              colSetter(ps, refMap, rowIndex)
            )
            // next row
            ps.addBatch()
          }
          resMap((ns, selfRef)) = resMap((ns, selfRef)) :+ resolveRow
      }
    }
  }

  override protected def addV[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    if (selfRefs.isEmpty)
      selfRefs = List(ns -> "")
    val selfRef = selfRefs.last._2

    if (firstRow) {
      if (tableCols.exists { case (ns1, selfRef1, _) => ns1 == ns && selfRef1 == selfRef }) {
        tableCols = tableCols.map {
          case (ns1, selfRef1, cols) if ns1 == ns && selfRef1 == selfRef =>
            paramIndexes = paramIndexes + ((ns, selfRef, attr) -> (cols.length + 1))
            (ns, selfRef, cols :+ attr)
          case other                                                     => other
        }
      } else {
        paramIndexes = paramIndexes + ((ns, selfRef, attr) -> 1)
        tableCols = tableCols :+ (ns, selfRef, List(attr))
      }
    }

    val paramIndex = paramIndexes((ns, selfRef, attr))
    (tpl: Product) => {
      val scalaValue        = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter       = handleScalaValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      val colSetter: Setter = (ps: PS, refMap: Map[(Int, String, String), Array[Long]], rowIndex: Int) => {
        valueSetter(ps, paramIndex)
        printValue(rowIndex, ns, selfRef, attr, tplIndex, paramIndex, scalaValue)
      }

      addColSetter(ns, selfRef, colSetter)
    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Product => Unit = {
    val selfRef = selfRefs.last._2
    if (firstRow) {
      if (tableCols.exists { case (ns1, selfRef1, _) => ns1 == ns && selfRef1 == selfRef }) {
        // Add ref attribute to current namespace
        tableCols = tableCols.map {
          case (ns1, selfRef1, cols) if card == CardOne && ns1 == ns && selfRef1 == selfRef =>
            paramIndexes = paramIndexes + ((ns, selfRef, refAttr) -> (cols.length + 1))
            (ns, selfRef, cols :+ refAttr)

          case other =>
            other
        }
      } else if (card == CardOne) {
        // Add ref attribute to new namespace
        paramIndexes = paramIndexes + ((ns, selfRef, refAttr) -> 1)
        tableCols = tableCols :+ (ns, selfRef, List(refAttr))
      }

      // Start new ref table
      val selfRef1 = if (ns == refNs) refAttr else ""
      tableCols = tableCols :+ (refNs, selfRef1, Nil)
    }

    // Prepare next table
    val selfRef1 = if (ns == refNs) {
      selfRefs = selfRefs :+ (refNs, refAttr)
      refAttr
    } else {
      selfRefs = selfRefs :+ (refNs, "")
      ""
    }

    // Return current setter
    if (card == CardOne) {
      val paramIndex = paramIndexes((ns, selfRef, refAttr))
      (_: Product) => {
        val colSetter: Setter = (ps: PS, refMap: Map[(Int, String, String), Array[Long]], rowIndex: Int) => {
          val refId = refMap((level, refNs, selfRef1))(rowIndex)
          printValue(rowIndex, ns, selfRef1, refAttr, -1, paramIndex, refId)
          ps.setLong(paramIndex, refId)
        }
        addColSetter(ns, selfRef, colSetter)
      }

    } else {
      val joinTable  = s"${ns}_${refAttr}_$refNs"
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      tableCols = (joinTable, "", List(id1, id2)) +: tableCols

      // No values set here, only in join table
      (_: Product) => {
        val joinSetter: Setter = (ps: PS, refMap: Map[(Int, String, String), Array[Long]], rowIndex: Int) => {

          //          refMap.foreach {
          //            case ((a, b, c), ids) => println(s"$a  $b  $c  " + ids.toList)
          //          }
          //          println(s"------------ $ns,  $selfRef, $selfRef1,  $refNs")

          val refId1 = refMap((level, ns, ""))(rowIndex)
          val refId2 = refMap((level, refNs, selfRef1))(rowIndex)
          println("id1: " + refId1)
          println("id2: " + refId2)
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          ps.addBatch()
        }
        addColSetter(joinTable, "", joinSetter)
      }
    }
  }

  private def addColSetter(ns: String, selfRef: String, colSetter: Setter) = {
    // Cache colSetter for this table
    colSetterMap.get((ns, selfRef)).fold[Unit](
      colSetterMap.addOne((ns, selfRef) -> List(colSetter))
    )(map =>
      colSetterMap((ns, selfRef)) = map :+ colSetter
    )
  }

  private def printValue(
    rowIndex: Int,
    ns: String,
    selfRef: String,
    attr: String,
    tplIndex0: Int,
    paramIndex: Int,
    value: Any
  ): Unit = {
    val fullAttr = if (selfRef.isEmpty) s"$ns.$attr" else s"${ns}_$selfRef.$attr"
    val pad      = padS(18, fullAttr)
    val tplIndex = if (tplIndex0 == -1) "-" else tplIndex0
    println(s"$rowIndex  $fullAttr$pad tplIndex: $tplIndex   paramIndex: $paramIndex   value: " + value)
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    selfRefs = selfRefs.init
    (_: Product) => ()
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

  override protected def addComposite(
    nsMap: Map[String, MetaNs],
    outerTpl: Int,
    tplIndex: Int,
    compositeElements: List[Element]
  ): Product => Unit = {
    hasComposites = true
    val composite2stmts = getResolver(nsMap, compositeElements, outerTpl)
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
            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
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
            addRef(ns, refAttr, refNs, CardOne)(nestedTpl)
            e0 = e
            nested2stmts(nestedTpl)
          }
        }
    }
  }


  override protected def addOptV[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(value) => appendStmt(add, e, a,
          handleScalaValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
        case _           => () // no statement to insert
      }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex).asInstanceOf[Set[_]].foreach { value =>
        appendStmt(add, e, a, handleScalaValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
      }
  }

  override protected def addOptSet[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    val a = kw(ns, attr)
    backRefs = backRefs + (ns -> e)
    (tpl: Product) =>
      tpl.productElement(tplIndex) match {
        case Some(set: Set[_]) =>
          set.foreach(value =>
            appendStmt(add, e, a, handleScalaValue(value.asInstanceOf[T]).asInstanceOf[AnyRef])
          )
        case None              => () // no statement to insert
      }
  }
}