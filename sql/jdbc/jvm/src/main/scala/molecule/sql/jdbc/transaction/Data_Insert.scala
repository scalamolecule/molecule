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

trait Data_Insert
  extends JdbcBase_JVM
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
    //    println("################################################################################################")

    curRefPath = List(s"$level", getInitialNs(elements))
    val (mainElements, _)           = separateTxElements(elements)
    val resolveTpl: Product => Unit = getResolver(nsMap, mainElements)
    initInserts()

    //    println(inserts.mkString("--- inserts\n", "\n", ""))
    //    println(joins.mkString("--- joins\n", "\n", ""))

    // Loop rows of tuples
    var rowIndex = 0
    tpls.foreach { tpl =>
      //      println(s"### $rowIndex ##################################### " + tpl)
      resolveTpl(tpl)
      addRowSetterToTableInserts(rowIndex)
      rowIndex += 1
    }
    (getTableInserts, getJoinTableInserts)
  }


  private def initInserts(): Unit = {
    //    println(inserts.mkString("--- inserts\n", "\n", ""))
    inserts.foreach {
      case (refPath, cols) =>
        val table = refPath.last
        val stmt  =
          s"""INSERT INTO $table (
             |  ${cols.mkString(",\n  ")}
             |) VALUES (${cols.map(_ => "?").mkString(", ")})""".stripMargin
        val ps    = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableInserts(refPath) = TableInsert(refPath, stmt, ps)
        rowSettersMap(refPath) = Nil
    }

    joins.foreach {
      case (joinRefPath, id1, id2, leftPath, rightPath) =>
        val joinTable = joinRefPath.last
        val stmt      = s"INSERT INTO $joinTable ($id1, $id2) VALUES (?, ?)"
        val ps        = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        joinTableInserts = joinTableInserts :+ JoinTableInsert(joinRefPath, stmt, ps, leftPath, rightPath)
    }
  }

  private def addRowSetterToTableInserts(rowIndex: Int): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        //        println(s"---------------------- $refPath")
        //        colSettersMap.foreach(println)
        val colSetters = colSettersMap(refPath)
        //        println(s"----------------------  ${colSetters.length}  $refPath")
        //          println(stmt)
        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          var i        = 0
          val colCount = cols.length
          ps.toString

          // Set all column values for this each row of this insert
          colSetters.foreach { colSetter =>
            colSetter(ps, idsMap, rowIndex)
            i += 1

            if (i == colCount) {
              // Add row for this insert
              ps.addBatch()
              i = 0
            }
          }
        }
        rowSettersMap(refPath) = rowSettersMap(refPath) :+ rowSetter
    }
  }


  private def getTableInserts: List[TableInsert] = {
    // Add insert resolver to each insert
    inserts.map { case (refPath, _) =>
      val rowSetters = rowSettersMap(refPath)
      val populatePS = (ps: PS, idsMap: Map[List[String], List[Long]], _: Int) => {
        // Set all column values for this row in this insert/batch
        var rowIndex = 0
        rowSetters.foreach { rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
          rowIndex += 1
        }
      }
      tableInserts(refPath).copy(populatePS = populatePS)
    }
  }
  private def getJoinTableInserts: List[JoinTableInsert] = {
    joins.zip(joinTableInserts).map {
      case ((joinRefPath, id1, id2, leftPath, rightPath), joinTableInsert) =>
        joinTableInsert.copy(rightCounts = rightCountsMap(joinRefPath))
    }
  }


  // Pre-process ----------------------------------------------------------------------------------------

  private def updateInsert(attr: String): (List[String], Int) = {
    if (inserts.exists(_._1 == curRefPath)) {
      inserts = inserts.map {
        case (path, cols) if path == curRefPath =>
          paramIndexes += (curRefPath, attr) -> (cols.length + 1)
          (path, cols :+ attr)

        case other => other
      }
    } else {
      paramIndexes += (curRefPath, attr) -> 1
      inserts = inserts :+ (curRefPath, List(attr))
    }
    (curRefPath, paramIndexes(curRefPath, attr))
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    val (curPath, paramIndex) = updateInsert(attr)
    val curLevel              = level
    (tpl: Product) => {
      val scalaValue  = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter = handleScalaValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      val colSetter   = (ps: PS, _: IdsMap, _: RowIndex) => {
        valueSetter(ps, paramIndex)
        //        printValue(curLevel, ns, attr, tplIndex, paramIndex, scalaValue)
      }
      addColSetter(curPath, colSetter)
    }
  }


  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    val (curPath, paramIndex) = updateInsert(attr)
    val curLevel              = level
    (tpl: Product) => {
      tpl.productElement(tplIndex) match {
        case Some(scalaValue) =>
          val valueSetter = handleScalaValue(scalaValue.asInstanceOf[T]).asInstanceOf[(PS, Int) => Unit]
          val colSetter   = (ps: PS, _: IdsMap, _: RowIndex) => {
            valueSetter(ps, paramIndex)
            //            printValue(curLevel, ns, attr, tplIndex, paramIndex, scalaValue)
          }
          addColSetter(curPath, colSetter)

        case None =>
          val valueSetter = (ps: PS, n: Int) => ps.setNull(n, java.sql.Types.NULL)
          val colSetter   = (ps: PS, _: IdsMap, _: RowIndex) => {
            valueSetter(ps, paramIndex)
            //            printValue(curLevel, ns, attr, tplIndex, paramIndex, null)
          }
          addColSetter(curPath, colSetter)
      }
    }
  }


  override protected def addSet[T](
    ns: String,
    attr: String,
    baseTpe: String,
    refNsOpt: Option[String],
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    refNsOpt.fold {
      val (curPath, paramIndex) = updateInsert(attr)
      (tpl: Product) =>
        val array     = getArray(baseTpe, tpl.productElement(tplIndex).asInstanceOf[Set[AnyRef]])
        val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
          val conn = ps.getConnection
          val arr  = conn.createArrayOf("AnyRef", array)
          ps.setArray(paramIndex, arr)
          //        printValue(curLevel, ns, attr, tplIndex, paramIndex, scalaValue)
        }
        addColSetter(curPath, colSetter)

    } { refNs =>
      val refAttr   = attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val curPath   = curRefPath
      val joinPath  = curPath :+ joinTable

      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List(id1, id2)) +: inserts

      (tpl: Product) => {
        val refIds = tpl.productElement(tplIndex).asInstanceOf[Set[Long]]

        // Empty row if no attributes in namespace in order to have an id for join table
        if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
          addColSetter(curPath, emptyRowSetter)
        }

        // Join table setter
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          refIds.foreach { refId2 =>
            ps.setLong(1, refId1)
            ps.setLong(2, refId2)
            ps.addBatch()
          }
        }
        addColSetter(joinPath, joinSetter)
      }
    }
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    baseTpe: String,
    refNsOpt: Option[String],
    tplIndex: Int,
    handleScalaValue: T => Any,
  ): Product => Unit = {
    refNsOpt.fold {
      val (curPath, paramIndex) = updateInsert(attr)
      (tpl: Product) =>
        tpl.productElement(tplIndex) match {
          case Some(set: Set[_]) =>
            val array     = getArray(baseTpe, set.asInstanceOf[Set[AnyRef]])
            val colSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
              val conn = ps.getConnection
              val arr  = conn.createArrayOf("AnyRef", array)
              ps.setArray(paramIndex, arr)
              //        printValue(curLevel, ns, attr, tplIndex, paramIndex, scalaValue)
            }
            addColSetter(curPath, colSetter)

          case None =>
            val valueSetter = (ps: PS, n: Int) => ps.setNull(n, java.sql.Types.NULL)
            val colSetter   = (ps: PS, _: IdsMap, _: RowIndex) => {
              valueSetter(ps, paramIndex)
              //            printValue(curLevel, ns, attr, tplIndex, paramIndex, null)
            }
            addColSetter(curPath, colSetter)
        }
    } { refNs =>
      val refAttr   = attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val curPath   = curRefPath
      val joinPath  = curPath :+ joinTable

      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List(id1, id2)) +: inserts

      (tpl: Product) => {
        tpl.productElement(tplIndex) match {
          case Some(set: Set[_]) =>
            val refIds = set.asInstanceOf[Set[Long]]

            // Empty row if no attributes in namespace in order to have an id for join table
            if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
              // If current namespace has no attributes, then add an empty row with
              // default null values (only to be referenced as the left side of the join table)
              val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
              addColSetter(curPath, emptyRowSetter)
            }

            // Join table setter
            val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
              val refId1 = idsMap(curPath)(rowIndex)
              refIds.foreach { refId2 =>
                ps.setLong(1, refId1)
                ps.setLong(2, refId2)
                ps.addBatch()
              }
            }
            addColSetter(joinPath, joinSetter)

          case None =>
            val dummySetter: Setter = (_: PS, _: IdsMap, _: RowIndex) => ()
            addColSetter(joinPath, dummySetter)
        }
      }
    }
  }

  private def getArray(baseTpe: String, set: Set[AnyRef]): Array[AnyRef] = {
    val array = set.toArray
    baseTpe match {
      case "Float"      => array.map(_.toString.toDouble.asInstanceOf[AnyRef])
      case "BigInt"     => array.map(v => BigDecimal(v.asInstanceOf[BigInt]).bigDecimal.asInstanceOf[AnyRef])
      case "BigDecimal" => array.map(v => v.asInstanceOf[BigDecimal].bigDecimal.asInstanceOf[AnyRef])
      case "URI"        => array.map(_.toString.asInstanceOf[AnyRef])
      case _            => array

    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Product => Unit = {
    val joinTable = s"${ns}_${refAttr}_$refNs"
    val curPath   = curRefPath

    if (inserts.exists(_._1 == curPath)) {
      // Add ref attribute to current namespace
      inserts = inserts.map {
        case (refPath1, cols) if card == CardOne && refPath1 == curPath =>
          paramIndexes += (curPath, refAttr) -> (cols.length + 1)
          (curPath, cols :+ refAttr)

        case other => other
      }

    } else if (card == CardOne) {
      // Make card-one ref from current empty namespace
      paramIndexes += (curPath, refAttr) -> 1
      inserts = inserts :+ (curPath, List(refAttr))

    } else if (card == CardSet) {
      // ref to join table
      // Make card-many ref from current empty namespace
      inserts = inserts :+ (curPath, Nil)
    }

    lazy val joinPath = curPath :+ joinTable
    if (card == CardSet) {
      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List(id1, id2)) +: inserts
    }

    // Start new ref table
    val refPath = curPath ++ List(refAttr, refNs)
    curRefPath = refPath
    inserts = inserts :+ (refPath, Nil)

    if (card == CardOne) {
      // Card-one ref setter
      val paramIndex = paramIndexes(curPath, refAttr)
      (_: Product) => {
        val colSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId = idsMap(refPath)(rowIndex)
          //          printValue(level, ns, refAttr, -1, paramIndex, refId)
          ps.setLong(paramIndex, refId)
        }
        addColSetter(curPath, colSetter)
      }

    } else {
      (_: Product) => {
        // Empty row if no attributes in namespace in order to have an id for join table
        if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => {
            ps.addBatch()
          }
          addColSetter(curPath, emptyRowSetter)
        }

        // Join table setter
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          val refId2 = idsMap(refPath)(rowIndex)
          //          println("-----------")
          //          println("id1: " + refId1)
          //          println("id2: " + refId2)
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          ps.addBatch()
        }
        addColSetter(joinPath, joinSetter)
      }
    }
  }


  override protected def addBackRef(backRefNs: String): Product => Unit = {
    curRefPath = curRefPath.dropRight(2) // drop refAttr, refNs
    (_: Product) => ()
  }


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
        composite2stmts(Tuple1(tpl.productElement(tplIndex)))
      case _ => (tpl: Product) =>
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
    val joinTable  = s"${ns}_${refAttr}_$refNs"
    val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
    val nextLevel  = level + 1
    val joinPath   = curRefPath :+ joinTable
    val leftPath   = curRefPath
    val rightPath  = List(s"$nextLevel", refNs)
    joins = joins :+ (joinPath, id1, id2, leftPath, rightPath)
    rightCountsMap(joinPath) = List.empty[Int]

    // Initiate new level
    level = nextLevel
    curRefPath = List(s"$level", refNs)
    colSettersMap += curRefPath -> Nil

    // Recursively resolve nested data
    val nestedResolvers = getResolver(nsMap, nestedElements)

    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          val nestedSingleValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val length             = nestedSingleValues.length
          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
          nestedSingleValues.foreach { nestedSingleValue =>
            nestedResolvers(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val length     = nestedTpls.length
          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
          nestedTpls.foreach { nestedTpl =>
            nestedResolvers(nestedTpl)
          }
        }
    }
  }

  override protected lazy val valueString     = (v: String) => (ps: PS, n: Int) => ps.setString(n, v)
  override protected lazy val valueInt        = (v: Int) => (ps: PS, n: Int) => ps.setInt(n, v)
  override protected lazy val valueLong       = (v: Long) => (ps: PS, n: Int) => ps.setLong(n, v)
  override protected lazy val valueFloat      = (v: Float) => (ps: PS, n: Int) => ps.setDouble(n, v.toString.toDouble)
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