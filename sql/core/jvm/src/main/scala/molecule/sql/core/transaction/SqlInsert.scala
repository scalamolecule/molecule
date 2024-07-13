package molecule.sql.core.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ops.InsertOps
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.core.util.ModelUtils

trait SqlInsert
  extends SqlBase_JVM
    with InsertOps
    with SqlBaseOps
    with SqlDataType_JVM
    with ModelUtils
    with MoleculeLogging { self: ResolveInsert with InsertResolvers_ =>

  // (set doPrint in db implementations to print debug data)

  def getInsertData(nsMap: Map[String, MetaNs], elements: List[Element], tpls: Seq[Product]): Data = {
    elements.foreach(debug)
    debug("\n\n### A #############################################################################################")
    if (tpls.isEmpty) {
      debug("Tpls data empty, so no insert...")
      // No need to handle inserts if no data
      (Nil, Nil)
    } else {
      initialNs = getInitialNs(elements)
      curRefPath = List(s"$level", initialNs)
      val resolveTpl: Product => Unit = getResolver(nsMap, elements)

      debug(inserts.mkString("--- inserts\n  ", "\n  ", ""))
      debug("### B #############################################################################################")
      initInserts()
      debug("### C ############################################################################################# " + tpls)

      // Loop rows of tuples
      var rowIndex = 0
      tpls.foreach { tpl =>
        debug(s"###### $rowIndex ##################################### " + tpl)
        resolveTpl(tpl)
        addRowSetterToTableInserts()
        rowIndex += 1
      }
      debug("### D #############################################################################################")
      (getTableInserts, getJoinTableInserts)
    }
  }

  protected def initInserts(): Unit = {
    inserts.foreach {
      case (refPath, Nil) =>
        // Add entity without attributes having only ref to next namespace
        val table = refPath.last
        val stmt  = s"INSERT INTO $table DEFAULT VALUES"
        debug(s"B -------------------- refPath: $refPath")
        debug(stmt)
        tableDatas(refPath) = Table(refPath, stmt)
        val rowSetter = (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.addBatch()
        }
        colSettersMap(refPath) = Nil
        rowSettersMap(refPath) = List(rowSetter)

      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.map(_._1).mkString(",\n  ")
        val inputPlaceholders = cols.map { case (_, castExt) => s"?$castExt" }.mkString(", ")

        val stmt =
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin
        debug(s"B -------------------- refPath: $refPath")
        debug(stmt)
        tableDatas(refPath) = Table(refPath, stmt)
        rowSettersMap(refPath) = Nil
    }

    joins.foreach {
      case (joinRefPath, id1, id2, leftPath, rightPath) =>
        val joinTable = joinRefPath.last
        val stmt      = s"INSERT INTO $joinTable ($id1, $id2) VALUES (?, ?)"
        joinTableDatas = joinTableDatas :+ JoinTable(stmt, leftPath, rightPath)
    }
  }

  private def addRowSetterToTableInserts(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        debug(s"C ---------------------- $refPath")
        if (doPrint) {
          colSettersMap.foreach(x =>
            println(s"C ${x._2.size} colSetters: " + x._1)
          )
        }
        colSettersMap.get(refPath).foreach { colSetters =>
          //        debug(s"C ---------------------- ${colSetters.length}  $refPath")
          colSettersMap(refPath) = Nil
          val rowSetter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
            var i        = 0
            val colCount = cols.length

            // Set all column values for this row of this insert
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
  }

  private def getTableInserts: List[Table] = {
    // Add insert resolver to each table insert
    inserts.map { case (refPath, _) =>
      val rowSetters = rowSettersMap(refPath)
      val populatePS = (ps: PS, idsMap: IdsMap, _: Int) => {
        // Set all column values for this row in this insert/batch
        var rowIndex = 0
        rowSetters.foreach { rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
          rowIndex += 1
        }
      }
      tableDatas(refPath).copy(populatePS = populatePS)
    }
  }

  private def getJoinTableInserts: List[JoinTable] = {
    joins.zip(joinTableDatas).map {
      case ((joinRefPath, _, _, _, _), joinTableInsert) =>
        joinTableInsert.copy(rightCounts = rightCountsMap(joinRefPath))
    }
  }


  // Pre-process ----------------------------------------------------------------------------------------

  protected def getParamIndex(attr: String, add: Boolean = true, castExt: String = ""): (List[String], Int) = {
    if (inserts.exists(_._1 == curRefPath)) {
      inserts = inserts.map {
        case (path, cols) if path == curRefPath =>
          paramIndexes += (curRefPath, attr) -> (cols.length + 1)
          (path, if (add) cols :+ (attr -> castExt) else cols)

        case other => other
      }
    } else {
      paramIndexes += (curRefPath, attr) -> 1
      inserts = inserts :+ (curRefPath -> List(attr -> castExt))
    }
    (curRefPath, paramIndexes(curRefPath -> attr))
  }

  override protected def addOne[T](
    ns: String,
    attr: String,
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = exts(2))
    (tpl: Product) => {
      val scalaValue  = tpl.productElement(tplIndex).asInstanceOf[T]
      val valueSetter = transformValue(scalaValue).asInstanceOf[(PS, Int) => Unit]
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
    exts: List[String] = Nil
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = exts(2))
    (tpl: Product) => {
      val colSetter = tpl.productElement(tplIndex) match {
        case Some(scalaValue) =>
          val valueSetter = transformValue(scalaValue.asInstanceOf[T]).asInstanceOf[(PS, Int) => Unit]
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
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, exts(1), tplIndex, set2array)
  }

  override protected def addSetOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, exts(1), tplIndex, set2array)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addIterable(ns, attr, optRefNs, exts(1), tplIndex, seq2array)
  }

  override protected def addSeqOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    exts: List[String] = Nil,
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    addOptIterable(ns, attr, optRefNs, exts(1), tplIndex, seq2array)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    tplIndex: Int,
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    (tpl: Product) => {
      val colSetter = tpl.productElement(tplIndex) match {
        case byteArray: Array[_] if byteArray.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]])

        case Some(byteArray: Array[_]) if !byteArray.isEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setBytes(paramIndex, byteArray.asInstanceOf[Array[Byte]])

        case _ => (ps: PS, _: IdsMap, _: RowIndex) => ps.setNull(paramIndex, 0)
      }
      addColSetter(curPath, colSetter)
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    (tpl: Product) =>
      val colSetter = tpl.productElement(tplIndex).asInstanceOf[Map[String, _]] match {
        case map if map.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setBytes(paramIndex, map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json))

        case _ =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
      }
      addColSetter(curPath, colSetter)
  }

  override protected def addMapOpt[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    tplIndex: Int,
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Product => Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    (tpl: Product) =>
      val colSetter = tpl.productElement(tplIndex) match {
        case Some(map: Map[_, _]) if map.nonEmpty =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setBytes(paramIndex, map2jsonByteArray(map.asInstanceOf[Map[String, T]], value2json))

        case _ =>
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
      }
      addColSetter(curPath, colSetter)
  }

  override protected def addRef(
    ns: String,
    refAttr: String,
    refNs: String,
    card: Card,
  ): Product => Unit = {
    getRefResolver[Product](ns, refAttr, refNs, card)
  }

  override protected def addBackRef(backRefNs: String): Product => Unit = {
    curRefPath = curRefPath.dropRight(2) // drop refAttr, refNs
    (_: Product) => ()
  }

  override protected def addOptRef(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    if (inserts.isEmpty) {
      inserts = inserts :+ (curRefPath -> Nil)
    }

    val curPath       = curRefPath

//    // Make card-one ref from current empty namespace
//    paramIndexes += (curPath, refAttr) -> 1
//    inserts = inserts :+ (curPath -> List(refAttr -> ""))
//
//
//    // Start new ref table
//    val refPath = curPath ++ List(refAttr, refNs)
//    curRefPath = refPath
//    inserts = inserts :+ (refPath -> Nil)



//    val joinTable  = ss(ns, refAttr, refNs)
//    val (id1, id2) = if (ns == refNs)
//      (ss(ns, "1_id"), ss(refNs, "2_id"))
//    else
//      (ss(ns, "id"), ss(refNs, "id"))
    val nextLevel  = level + 1
//    val joinPath   = curRefPath :+ joinTable
//    val leftPath   = curRefPath
//    val rightPath  = List(s"$nextLevel", refNs)
//    joins = joins :+ ((joinPath, id1, id2, leftPath, rightPath))

    // Initiate new level
    level = nextLevel
//    curRefPath = List(s"$level", refNs)
//    colSettersMap += curRefPath -> Nil


    // Start new ref table
    val refPath = curPath ++ List(refAttr, refNs)

    rightCountsMap(refPath) = List.empty[Int]

    paramIndexes += (curPath, refAttr) -> 1

    curRefPath = refPath
    inserts = inserts :+ (refPath -> List(refAttr -> ""))
    inserts = inserts :+ (refPath -> Nil)

    // Recursively resolve nested data
    val resolveNested = getResolver(nsMap, nestedElements)


    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          println("tpl 1: " + tpl)

          tpl.productElement(tplIndex) match {
            case Some(value) =>
            //                val array = iterable2array(iterable.asInstanceOf[M[T]])
            //                (ps: PS, _: IdsMap, _: RowIndex) => {
            //                  val conn = ps.getConnection
            //                  val arr  = conn.createArrayOf(sqlTpe, array)
            //                  ps.setArray(paramIndex, arr)
            //                }

            println("+++++++++++++++++++++++ " + value)

            case None =>
            println("+++++++++++++++++++++++ NONE" )
            //                (ps: PS, _: IdsMap, _: RowIndex) =>
            //                  ps.setNull(paramIndex, java.sql.Types.NULL)
          }


          val optionalSingleValue = tpl.productElement(tplIndex).asInstanceOf[Option[Any]].filter {
            case set: Set[_] if set.isEmpty => false
            case _                          => true
          }
          val length              = optionalSingleValue.fold(0)(_ => 1)
          rightCountsMap(refPath) = rightCountsMap(refPath) :+ length
          optionalSingleValue.foreach { nestedSingleValue =>
            println("   ######  " + nestedSingleValue)
            resolveNested(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val optionalTpl = tpl.productElement(tplIndex).asInstanceOf[Option[Product]]
          val length      = optionalTpl.fold(0)(_ => 1)
          rightCountsMap(refPath) = rightCountsMap(refPath) :+ length
          var rowIndex = 0
          optionalTpl.foreach { nestedTpl =>
            debug(s"------ $rowIndex ##################################### " + nestedTpl)
            rowIndex += 1
            resolveNested(nestedTpl)
          }
        }
    }

//    (tpl: Product) => {
//      println("tpl 1: " + tpl)
//
//      val colSetter = tpl.productElement(tplIndex) match {
//        case Some(iterable: Iterable[_]) =>
//          val array = iterable2array(iterable.asInstanceOf[M[T]])
//          (ps: PS, _: IdsMap, _: RowIndex) => {
//            val conn = ps.getConnection
//            val arr  = conn.createArrayOf(sqlTpe, array)
//            ps.setArray(paramIndex, arr)
//          }
//
//        case None =>
//          (ps: PS, _: IdsMap, _: RowIndex) =>
//            ps.setNull(paramIndex, java.sql.Types.NULL)
//      }
//      addColSetter(curPath, colSetter)
//    }
  }

  override protected def addNested(
    nsMap: Map[String, MetaNs],
    tplIndex: Int,
    ns: String,
    refAttr: String,
    refNs: String,
    nestedElements: List[Element]
  ): Product => Unit = {
    if (inserts.isEmpty) {
      inserts = inserts :+ (curRefPath -> Nil)
    }

    val joinTable  = ss(ns, refAttr, refNs)
    val (id1, id2) = if (ns == refNs)
      (ss(ns, "1_id"), ss(refNs, "2_id"))
    else
      (ss(ns, "id"), ss(refNs, "id"))
    val nextLevel  = level + 1
    val joinPath   = curRefPath :+ joinTable
    val leftPath   = curRefPath
    val rightPath  = List(s"$nextLevel", refNs)
    joins = joins :+ ((joinPath, id1, id2, leftPath, rightPath))
    rightCountsMap(joinPath) = List.empty[Int]

    // Initiate new level
    level = nextLevel
    curRefPath = List(s"$level", refNs)
    colSettersMap += curRefPath -> Nil

    // Recursively resolve nested data
    val resolveNested = getResolver(nsMap, nestedElements)

    countValueAttrs(nestedElements) match {
      case 1 =>
        (tpl: Product) => {
          println("tpl 1: " + tpl)

          val nestedSingleValues = tpl.productElement(tplIndex).asInstanceOf[Seq[Any]].filter {
            case set: Set[_] if set.isEmpty => false
            case _                          => true
          }
          val length             = nestedSingleValues.length
          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
          nestedSingleValues.foreach { nestedSingleValue =>
            resolveNested(Tuple1(nestedSingleValue))
          }
        }
      case _ =>
        (tpl: Product) => {
          val nestedTpls = tpl.productElement(tplIndex).asInstanceOf[Seq[Product]]
          val length     = nestedTpls.length
          rightCountsMap(joinPath) = rightCountsMap(joinPath) :+ length
          var rowIndex = 0
          nestedTpls.foreach { nestedTpl =>
            debug(s"------ $rowIndex ##################################### " + nestedTpl)
            rowIndex += 1
            resolveNested(nestedTpl)
          }
        }
    }
  }


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    optRefNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      (tpl: Product) => {
        val array     = iterable2array(tpl.productElement(tplIndex).asInstanceOf[M[T]])
        val colSetter = if (array.nonEmpty) {
          (ps: PS, _: IdsMap, _: RowIndex) => {
            val conn = ps.getConnection
            val arr  = conn.createArrayOf(sqlTpe, array)
            ps.setArray(paramIndex, arr)
          }
        } else {
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)
      }
    } { refNs =>
      join(ns, attr, refNs, tplIndex)
    }
  }

  private def addOptIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    sqlTpe: String,
    tplIndex: Int,
    iterable2array: M[T] => Array[AnyRef],
  ): Product => Unit = {
    optRefNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      (tpl: Product) => {
        val colSetter = tpl.productElement(tplIndex) match {
          case Some(iterable: Iterable[_]) =>
            val array = iterable2array(iterable.asInstanceOf[M[T]])
            (ps: PS, _: IdsMap, _: RowIndex) => {
              val conn = ps.getConnection
              val arr  = conn.createArrayOf(sqlTpe, array)
              ps.setArray(paramIndex, arr)
            }

          case None =>
            (ps: PS, _: IdsMap, _: RowIndex) =>
              ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)
      }
    } { refNs =>
      optJoin(ns, attr, refNs, tplIndex)
    }
  }

  protected def join(
    ns: String,
    refAttr: String,
    refNs: String,
    tplIndex: Int
  ): Product => Unit = {
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
      inserts = inserts :+ (curRefPath -> List())
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
      val refIds             = tpl.productElement(tplIndex).asInstanceOf[Iterable[Long]]
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

  protected def optJoin(
    ns: String,
    refAttr: String,
    refNs: String,
    tplIndex: Int
  ): Product => Unit = {
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
        case Some(set: Iterable[_]) =>
          if (set.nonEmpty) {
            // Empty row if no attributes in namespace in order to have an id for join table
            if (!paramIndexes.exists { case ((path, _), _) => path == curPath }) {
              // If current namespace has no attributes, then add an empty row with
              // default null values (only to be referenced as the left side of the join table)
              val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
              addColSetter(curPath, emptyRowSetter)
            }

            // Join table setter
            val refIds = set.asInstanceOf[Iterable[Long]]
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