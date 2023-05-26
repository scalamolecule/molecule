package molecule.sql.jdbc.transaction

import java.net.URI
import java.sql.{Statement, PreparedStatement => PS}
import java.util.{Date, UUID}
import molecule.base.ast.SchemaAST._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertExtraction, InsertResolvers_}
import molecule.core.util.ModelUtils
import scala.collection.mutable

trait Data_Insert
  extends JdbcTxBase_JVM
    with InsertOps
    with JdbcDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: InsertExtraction with InsertResolvers_ =>

  def getData(
    nsMap: Map[String, MetaNs],
    elements: List[Element],
    tpls: Seq[Product],
  ): Data = {
    //    elements.foreach(println)

    curRefPath = List(getInitialNs(elements))
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
    // Add insert resolver to each table insert
    tableCols.map { case (refPath, ns, _) =>
      val rowSetters    = rowSettersMap(refPath, ns)
      val resolveInsert = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, insertIds, rowIndex)
        )
      }
      insertResolvers((refPath, ns)).copy(populatePS = resolveInsert)
    }
  }

  private def addRowSetters(rowIndex: Int): Unit = {
    if (firstRow) {
      tableCols.foreach {
        case (refPath, ns, cols) =>
          val stmt =
            s"""INSERT INTO $ns (
               |  ${cols.mkString(",\n  ")}
               |) VALUES (${cols.map(_ => "?").mkString(", ")})""".stripMargin
          val ps   = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)

          insertResolvers((refPath, ns)) = Resolver(level, refPath, ns, stmt, ps)

          val colSetters = colSettersMap(refPath, ns)
          //          println(s"----------------------  ${colSetters.length}  $refPath  $ns")
          //          println(stmt)
          colSettersMap((refPath, ns)) = Nil
          val resolveRow = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], _: Int) => {
            // Set all column values for this row in this insert/batch
            colSetters.foreach(colSetter =>
              colSetter(ps, insertIds, rowIndex)
            )
            // Complete row
            ps.addBatch()
          }
          rowSettersMap((refPath, ns)) = List(resolveRow)
      }
    } else {
      tableCols.foreach {
        case (refPath, ns, _) =>
          val colSetters = colSettersMap(refPath, ns)
          colSettersMap((refPath, ns)) = Nil
          val resolveRow = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], _: Int) => {
            // Set all column values for this insert/batch
            colSetters.foreach(colSetter =>
              colSetter(ps, insertIds, rowIndex)
            )
            // next row
            ps.addBatch()
          }
          rowSettersMap((refPath, ns)) = rowSettersMap((refPath, ns)) :+ resolveRow
      }
    }
  }

  override protected def addV[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    val refPath = curRefPath

    if (firstRow) {
      if (tableCols.exists(_._1 == refPath)) {
        tableCols = tableCols.map {
          case (refPath1, ns1, cols) if refPath1 == refPath && ns1 == ns =>
            paramIndexes = paramIndexes + ((refPath, ns, attr) -> (cols.length + 1))
            (refPath1, ns, cols :+ attr)
          case other                                                     =>
            other
        }
      } else {
        paramIndexes = paramIndexes + ((refPath, ns, attr) -> 1)
        tableCols = tableCols :+ (refPath, ns, List(attr))
      }
    }

    val paramIndex = paramIndexes((refPath, ns, attr))
    (tpl: Product) => {
      val scalaValue        = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter       = handleScalaValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      val colSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
        valueSetter(ps, paramIndex)
        //        printValue(rowIndex, ns, attr, tplIndex, paramIndex, scalaValue)
      }
      addColSetter(refPath, ns, colSetter)
    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Product => Unit = {
    val joinTable = s"${ns}_${refAttr}_$refNs"
    val refPath   = curRefPath
    curRefPath = curRefPath ++ List(refAttr, refNs)
    val refPath1 = curRefPath

    if (firstRow) {
      if (tableCols.exists(_._1 == refPath)) {
        // Add ref attribute to current namespace
        tableCols = tableCols.map {
          case (refPath1, ns1, cols) if card == CardOne && refPath1 == refPath && ns1 == ns =>
            paramIndexes = paramIndexes + ((refPath, ns, refAttr) -> (cols.length + 1))
            (refPath, ns, cols :+ refAttr)

          case other => other
        }

      } else if (card == CardOne) {
        // Make card-one ref from current empty namespace
        paramIndexes = paramIndexes + ((refPath, ns, refAttr) -> 1)
        tableCols = tableCols :+ (refPath, ns, List(refAttr))

      } else if (card == CardSet) {
        // ref to join table
        // Make card-many ref from current empty namespace
        tableCols = tableCols :+ (refPath, ns, Nil)
      }

      if (card == CardSet) {
        // join table
        val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
        tableCols = (refPath, joinTable, List(id1, id2)) +: tableCols
      }

      // Start new ref table
      tableCols = tableCols :+ (refPath1, refNs, Nil)
    }

    if (card == CardOne) {
      // Card-one ref setter
      val paramIndex = paramIndexes((refPath, ns, refAttr))
      (_: Product) => {
        val colSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
          //          println("insertIds.length 0: " + insertIds.size)
          //          insertIds.foreach {
          //            case ((a, b, c, d), ids) => println(s"$a  $b  $c  $d  " + ids.toList)
          //          }
          val refId = insertIds((level, refPath1, refNs))(rowIndex)
          //          printValue(rowIndex, ns, refAttr, -1, paramIndex, refId)
          ps.setLong(paramIndex, refId)
        }
        addColSetter(refPath, ns, colSetter)
      }

    } else {
      (_: Product) => {
        // Empty row if no attributes in namespace in order to have an ide for join table
        if (!paramIndexes.exists { case ((refPath2, ns1, _), _) => refPath2 == refPath && ns1 == ns }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], _: Int) => {
            //            println("insertIds.length 1: " + insertIds.size)
            ps.addBatch()
          }
          addColSetter(refPath, ns, emptyRowSetter)
        }

        // Join table setter
        val joinSetter: Setter = (ps: PS, insertIds: Map[(Int, List[String], String), Array[Long]], rowIndex: Int) => {
          //          println("insertIds.length 2: " + insertIds.size)
          //          insertIds.foreach {
          //            case ((a, b, c, d), ids) => println(s"$a  $b  $c  $d  " + ids.toList)
          //          }
          //          println(s"------------ $ns,  $refNs")
          //          println("  " + refPath)
          //          println("  " + refPath1)
          val refId1 = insertIds((level, refPath, ns))(rowIndex)
          val refId2 = insertIds((level, refPath1, refNs))(rowIndex)
          //          println("id1: " + refId1)
          //          println("id2: " + refId2)
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          ps.addBatch()
        }
        addColSetter(refPath, joinTable, joinSetter)
      }
    }
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    curRefPath = curRefPath.dropRight(2)
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


  private def printValue(
    rowIndex: Int,
    ns: String,
    attr: String,
    tplIndex0: Int,
    paramIndex: Int,
    value: Any
  ): Unit = {
    val fullAttr = s"$ns.$attr"
    val pad      = padS(18, fullAttr)
    val tplIndex = if (tplIndex0 == -1) "-" else tplIndex0
    println(s"$rowIndex  $fullAttr$pad tplIndex: $tplIndex   paramIndex: $paramIndex   value: " + value)
  }
}