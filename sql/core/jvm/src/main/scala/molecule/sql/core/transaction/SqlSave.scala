package molecule.sql.core.transaction

import java.sql.{Statement, PreparedStatement => PS}
import boopickle.Default._
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.SerializationUtils

trait SqlSave
  extends SqlBase_JVM
    with SaveOps with SqlBaseOps
    with MoleculeLogging
    with SerializationUtils { self: ResolveSave =>

  // Resolve after all back refs have been resolved and namespaces grouped
  protected var postResolvers = List.empty[Unit => Unit]

  doPrint = false

  def getData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    curRefPath = List(initialNs)
    resolve(elements)
    postResolvers.foreach(_(()))
    addRowSetterToTables()
    (getTables, Nil)
  }

  protected def addRowSetterToTables(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.map(_._1).mkString(",\n  ")
        val inputPlaceholders = cols.map(_ => "?").mkString(", ")
        val stmt              = {
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin
        }
        val colSetters        = colSettersMap(refPath)
        debug(s"--- save -------------------  ${colSetters.length}  $refPath")
        debug(stmt)

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)
        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach { colSetter =>
            colSetter(ps, idsMap, 0)
          }
          // Complete row
          ps.addBatch()
        }
        rowSettersMap(refPath) = List(rowSetter)
    }
  }

  protected def getTables: List[Table] = {
    // Add insert resolver to each table insert
    inserts.map { case (refPath, _) =>
      val rowSetters = rowSettersMap(refPath)
      val populatePS = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        // Set all column values for this row in this insert/batch
        rowSetters.foreach(rowSetter =>
          rowSetter(ps, idsMap, rowIndex)
        )
      }
      tableDatas(refPath).copy(populatePS = populatePS)
    }
  }

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
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    val (curPath, paramIndex) = getParamIndex(attr, castExt = exts.head)
    val colSetter: Setter     = optValue.fold {
      (ps: PS, _: IdsMap, _: RowIndex) => {
        ps.setNull(paramIndex, 0)
      }
    } { value =>
      (ps: PS, _: IdsMap, _: RowIndex) => {
        transformValue(value).asInstanceOf[(PS, Int) => Unit](ps, paramIndex)
      }
    }
    addColSetter(curPath, colSetter)
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      val colSetter: Setter     = optSet.fold {
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(paramIndex, 0)
        }
      } { set =>
        (ps: PS, _: IdsMap, _: RowIndex) => {
          if (set.isEmpty) {
            ps.setNull(paramIndex, 0)
          } else {
            val conn  = ps.getConnection
            val array = conn.createArrayOf(exts(1), set2array(set))
            ps.setArray(paramIndex, array)
          }
        }
      }
      addColSetter(curPath, colSetter)
    } { refNs =>
      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
      if (optSet.isEmpty || optSet.get.isEmpty) {
        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      } else {
        val set       = optSet.get
        val refAttr   = attr
        val joinTable = ss(ns, refAttr, refNs)
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

        // Join table setter
        val refIds2            = set.iterator.asInstanceOf[Iterator[String]]
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          while (refIds2.hasNext) {
            val refId2 = refIds2.next()
            ps.setLong(1, refId1)
            ps.setLong(2, refId2.toLong)
            if (refIds2.hasNext)
              ps.addBatch()
          }
        }
        addColSetter(joinPath, joinSetter)
      }
    }
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    refNs: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      val colSetter: Setter     = optSeq.fold {
        (ps: PS, _: IdsMap, _: RowIndex) => {
          ps.setNull(paramIndex, 0)
        }
      } { seq =>
        (ps: PS, _: IdsMap, _: RowIndex) => {
          if (seq.isEmpty) {
            ps.setNull(paramIndex, 0)
          } else {
            val conn  = ps.getConnection
            val array = conn.createArrayOf(exts(1), seq2array(seq))
            ps.setArray(paramIndex, array)
          }
        }
      }
      addColSetter(curPath, colSetter)
    } { refNs =>
      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
      if (optSeq.isEmpty || optSeq.get.isEmpty) {
        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      } else {
        val set       = optSeq.get
        val refAttr   = attr
        val joinTable = ss(ns, refAttr, refNs)
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

        // Join table setter
        val refIds2            = set.iterator.asInstanceOf[Iterator[String]]
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          while (refIds2.hasNext) {
            val refId2 = refIds2.next()
            ps.setLong(1, refId1)
            ps.setLong(2, refId2.toLong)
            if (refIds2.hasNext)
              ps.addBatch()
          }
        }
        addColSetter(joinPath, joinSetter)
      }
    }
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    val colSetter: Setter     = optArray match {
      case Some(byteArray) if !byteArray.isEmpty =>
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setBytes(paramIndex, byteArray)
      case _                                     =>
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setNull(paramIndex, 0)
    }
    addColSetter(curPath, colSetter)
  }


  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    val colSetter: Setter     = optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      case _                                    =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setNull(paramIndex, 0)
    }
    addColSetter(curPath, colSetter)
  }

  override protected def addRef(
    ns: String, refAttr: String, refNs: String, card: Card, owner: Boolean
  ): Unit = {
    postResolvers = postResolvers :+ getRefResolver[Unit](ns, refAttr, refNs, card)
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    curRefPath = curRefPath.dropRight(2)
  }

  override protected def handleRefNs(refNs: String): Unit = ()
}