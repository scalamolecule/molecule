package molecule.document.mongodb.transaction

import java.sql.{Statement, PreparedStatement => PS}
import java.time._
import java.util.Date
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils

trait SqlInsert
  extends SqlBase_JVM
    with InsertOps
    with SqlDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false

  def getData(nsMap: Map[String, MetaNs], elements: List[Element], tpls: Seq[Product]): Data = {
    elements.foreach(debug)
    //    debug("### A #############################################################################################")
    //    if (tpls.isEmpty) {
    //      debug("Tpls data empty, so no insert...")
    //      // No need to handle inserts if no data
    //      (Nil, Nil)
    //    } else {
    //      initialNs = getInitialNs(elements)
    //      curRefPath = List(s"$level", initialNs)
    //      val resolveTpl: Product => Unit = getResolver(nsMap, elements)
    //
    //      debug(inserts.mkString("--- inserts\n  ", "\n  ", ""))
    //      //    debug(joins.mkString("--- joins\n  ", "\n  ", ""))
    //      debug("### B #############################################################################################")
    //      initInserts()
    //      debug("### C ############################################################################################# " + tpls)
    //
    //      // Loop rows of tuples
    //      var rowIndex = 0
    //      tpls.foreach { tpl =>
    //        debug(s"###### $rowIndex ##################################### " + tpl)
    //        resolveTpl(tpl)
    //        addRowSetterToTableInserts(rowIndex)
    //        rowIndex += 1
    //      }
    //      debug("### D #############################################################################################")
    //      (getTableInserts, getJoinTableInserts)
    //    }

    ???
  }

  //  protected def initInserts(): Unit = {
  //    inserts.foreach {
  //      case (refPath, cols) =>
  //        val table             = refPath.last
  //        val columns           = cols.map(_._1).mkString(",\n  ")
  //        val inputPlaceholders = cols.map(_ => "?").mkString(", ")
  //        val stmt              =
  //          s"""INSERT INTO $table (
  //             |  $columns
  //             |) VALUES ($inputPlaceholders)""".stripMargin
  //
  //        debug(s"B -------------------- refPath: $refPath")
  //        debug(stmt)
  //        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  //        tableDatas(refPath) = Table(refPath, stmt, ps)
  //        rowSettersMap(refPath) = Nil
  //    }
  //
  //    joins.foreach {
  //      case (joinRefPath, id1, id2, leftPath, rightPath) =>
  //        val joinTable = joinRefPath.last
  //        val stmt      = s"INSERT INTO $joinTable ($id1, $id2) VALUES (?, ?)"
  //        val ps        = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
  //        joinTableDatas = joinTableDatas :+ JoinTable(stmt, ps, leftPath, rightPath)
  //    }
  //  }
  //
  //  protected def addRowSetterToTableInserts(rowIndex: Int): Unit = {
  //    inserts.foreach {
  //      case (refPath, cols) =>
  //        debug(s"C ---------------------- $refPath")
  //        colSettersMap.foreach(x => debug(s"C ${x._2.size} colSetters: " + x._1))
  //        val colSetters = colSettersMap(refPath)
  //        debug(s"C ---------------------- ${colSetters.length}  $refPath")
  //        colSettersMap(refPath) = Nil
  //        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
  //          var i        = 0
  //          val colCount = cols.length
  //          ps.toString
  //
  //          // Set all column values for this each row of this insert
  //          colSetters.foreach { colSetter =>
  //            colSetter(ps, idsMap, rowIndex)
  //            i += 1
  //
  //            if (i == colCount) {
  //              // Add row for this insert
  //              ps.addBatch()
  //              i = 0
  //            }
  //          }
  //        }
  //        rowSettersMap(refPath) = rowSettersMap(refPath) :+ rowSetter
  //    }
  //  }
  //
  //  protected def getTableInserts: List[Table] = {
  //    // Add insert resolver to each insert
  //    inserts.map { case (refPath, _) =>
  //      val rowSetters = rowSettersMap(refPath)
  //      val populatePS = (ps: PS, idsMap: Map[List[String], List[Long]], _: Int) => {
  //        // Set all column values for this row in this insert/batch
  //        var rowIndex = 0
  //        rowSetters.foreach { rowSetter =>
  //          rowSetter(ps, idsMap, rowIndex)
  //          rowIndex += 1
  //        }
  //      }
  //      tableDatas(refPath).copy(populatePS = populatePS)
  //    }
  //  }
  //
  //  protected def getJoinTableInserts: List[JoinTable] = {
  //    joins.zip(joinTableDatas).map {
  //      case ((joinRefPath, _, _, _, _), joinTableInsert) =>
  //        joinTableInsert.copy(rightCounts = rightCountsMap(joinRefPath))
  //    }
  //  }


  // Pre-process ----------------------------------------------------------------------------------------

  protected def getParamIndex(attr: String, add: Boolean = true, castExt: String = ""): (List[String], Int) = {
    if (inserts.exists(_._1 == curRefPath)) {
      inserts = inserts.map {
        case (path, cols) if path == curRefPath =>
          paramIndexes += (curRefPath, attr) -> (cols.length + 1)
          (path, if (add) cols :+ (attr, castExt) else cols)

        case other => other
      }
    } else {
      paramIndexes += (curRefPath, attr) -> 1
      inserts = inserts :+ (curRefPath, List((attr, castExt)))
    }
    (curRefPath, paramIndexes(curRefPath, attr))
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = exts.head)
    (tpl: Product) => {
      val scalaValue  = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter = handleValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
      val colSetter   = (ps: PS, _: IdsMap, _: RowIndex) => {
        valueSetter(ps, paramIndex)
      }
      addColSetter(curPath, colSetter)
    }
  }

  override protected def addOneOpt[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    handleValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = exts.head)
    (tpl: Product) => {
      val colSetter = tpl.productElement(tplIndex) match {
        case Some(scalaValue) =>
          val valueSetter = handleValue(scalaValue.asInstanceOf[T]).asInstanceOf[(PS, Int) => Unit]
          (ps: PS, _: IdsMap, _: RowIndex) =>
            valueSetter(ps, paramIndex)

        case None =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
      }
      addColSetter(curPath, colSetter)
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    refNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      (tpl: Product) =>
        val array     = set2array(tpl.productElement(tplIndex).asInstanceOf[Set[Any]])
        val colSetter = if (array.nonEmpty) {
          (ps: PS, _: IdsMap, _: RowIndex) => {
            val conn = ps.getConnection
            val arr  = conn.createArrayOf(exts(1), array)
            ps.setArray(paramIndex, arr)
          }
        } else {
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)

    } { refNs =>
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val curPath   = if (paramIndexes.nonEmpty) curRefPath else List("0", ns)
      val joinPath  = curPath :+ joinTable

      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs)
        (ss(ns, "1_id"), ss(refNs, "2_id"))
      else
        (ss(ns, "id"), ss(refNs, "id"))
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts

      if (paramIndexes.isEmpty) {
        // If current namespace has no attributes, then add an empty row with
        // default null values (only to be referenced as the left side of the join table)
        val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
        addColSetter(curPath, emptyRowSetter)
        inserts = inserts :+ (curRefPath, List())
      }

      (tpl: Product) => {
        // Empty row if no attributes in namespace in order to have an id for join table left side
        if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
          // If current namespace has no attributes, then add an empty row with
          // default null values (only to be referenced as the left side of the join table)
          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
          addColSetter(curPath, emptyRowSetter)
        }

        // Join table setter
        val refIds             = tpl.productElement(tplIndex).asInstanceOf[Set[Long]]
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val id = idsMap(curPath)(rowIndex)
          refIds.foreach { refId =>
            ps.setLong(1, id)
            ps.setLong(2, refId)
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
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    refNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      (tpl: Product) => {
        val colSetter = tpl.productElement(tplIndex) match {
          case Some(set: Set[_]) =>
            val array = set2array(set.asInstanceOf[Set[Any]])
            (ps: PS, _: IdsMap, _: RowIndex) => {
              val conn = ps.getConnection
              val arr  = conn.createArrayOf(exts(1), array)
              ps.setArray(paramIndex, arr)
            }

          case None =>
            (ps: PS, _: IdsMap, _: RowIndex) =>
              ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)
      }
    } { refNs =>
      val refAttr   = attr
      val joinTable = ss(ns, refAttr, refNs)
      val curPath   = curRefPath
      val joinPath  = curPath :+ joinTable

      // join table with single row (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs)
        (ss(ns, "1_id"), ss(refNs, "2_id"))
      else
        (ss(ns, "id"), ss(refNs, "id"))
      // When insertion order is reversed, this join table will be set after left and right has been inserted
      inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts

      (tpl: Product) => {
        val colSetter = tpl.productElement(tplIndex) match {
          case Some(set: Set[_]) =>
            if (set.nonEmpty) {
              // Empty row if no attributes in namespace in order to have an id for join table
              if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
                // If current namespace has no attributes, then add an empty row with
                // default null values (only to be referenced as the left side of the join table)
                val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
                addColSetter(curPath, emptyRowSetter)
              }

              // Join table setter
              val refIds = set.asInstanceOf[Set[Long]]
              (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
                val id = idsMap(curPath)(rowIndex)
                refIds.foreach { refId =>
                  ps.setLong(1, id)
                  ps.setLong(2, refId)
                  ps.addBatch()
                }
              }
            } else {
              (_: PS, _: IdsMap, _: RowIndex) => ()
            }

          case None => (_: PS, _: IdsMap, _: RowIndex) => ()
        }
        addColSetter(joinPath, colSetter)
      }
    }
  }

  override protected def addRef(ns: String, refAttr: String, refNs: String, card: Card): Product => Unit = {
    getRefResolver[Product](ns, refAttr, refNs, card)
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    curRefPath = curRefPath.dropRight(2) // drop refAttr, refNs
    (_: Product) => ()
  }

  override protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
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
    val nestedResolver = getResolver(nsMap, nestedElements)

    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          val nestedSingleValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]]
          val length             = nestedSingleValues.length
          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
          nestedSingleValues.foreach { nestedSingleValue =>
            nestedResolver(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val length     = nestedTpls.length
          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
          nestedTpls.foreach { nestedTpl =>
            nestedResolver(nestedTpl)
          }
        }
    }
  }

  override protected lazy val handleString         = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[String])
  override protected lazy val handleInt            = (v: Any) => (ps: PS, n: Int) => ps.setInt(n, v.asInstanceOf[Int])
  override protected lazy val handleLong           = (v: Any) => (ps: PS, n: Int) => ps.setLong(n, v.asInstanceOf[Long])
  override protected lazy val handleFloat          = (v: Any) => (ps: PS, n: Int) => ps.setFloat(n, v.asInstanceOf[Float])
  override protected lazy val handleDouble         = (v: Any) => (ps: PS, n: Int) => ps.setDouble(n, v.asInstanceOf[Double])
  override protected lazy val handleBoolean        = (v: Any) => (ps: PS, n: Int) => ps.setBoolean(n, v.asInstanceOf[Boolean])
  override protected lazy val handleBigInt         = (v: Any) => (ps: PS, n: Int) => ps.setBigDecimal(n, BigDecimal(v.asInstanceOf[BigInt]).bigDecimal)
  override protected lazy val handleBigDecimal     = (v: Any) => (ps: PS, n: Int) => ps.setBigDecimal(n, v.asInstanceOf[BigDecimal].bigDecimal)
  override protected lazy val handleDate           = (v: Any) => (ps: PS, n: Int) => ps.setDate(n, new java.sql.Date(v.asInstanceOf[Date].getTime))
  override protected lazy val handleDuration       = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[Duration].toString)
  override protected lazy val handleInstant        = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[Instant].toString)
  override protected lazy val handleLocalDate      = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[LocalDate].toString)
  override protected lazy val handleLocalTime      = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[LocalTime].toString)
  override protected lazy val handleLocalDateTime  = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[LocalDateTime].toString)
  override protected lazy val handleOffsetTime     = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[OffsetTime].toString)
  override protected lazy val handleOffsetDateTime = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[OffsetDateTime].toString)
  override protected lazy val handleZonedDateTime  = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.asInstanceOf[ZonedDateTime].toString)
  override protected lazy val handleUUID           = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val handleURI            = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
  override protected lazy val handleByte           = (v: Any) => (ps: PS, n: Int) => ps.setByte(n, v.asInstanceOf[Byte])
  override protected lazy val handleShort          = (v: Any) => (ps: PS, n: Int) => ps.setShort(n, v.asInstanceOf[Short])
  override protected lazy val handleChar           = (v: Any) => (ps: PS, n: Int) => ps.setString(n, v.toString)
}