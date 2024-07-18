package molecule.sql.core.transaction

import java.sql.{PreparedStatement => PS}
import boopickle.Default._
import molecule.base.ast._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.transaction.ResolveSave
import molecule.core.transaction.ops.SaveOps
import molecule.core.util.SerializationUtils
import molecule.sql.core.transaction.strategy.save.{SaveAction, SaveNs}

trait SqlSave
  extends SqlBase_JVM
    with SaveOps with SqlBaseOps
    with MoleculeLogging
    with SerializationUtils { self: ResolveSave =>

  protected var save: SaveAction = null

  def getSaveAction(elements: List[Element]): SaveAction = {
    save = SaveNs(sqlConn, getInitialNs(elements))
    resolve(elements)
    save.initialAction
  }

  // Resolve after all back refs have been resolved and namespaces grouped
  private var postResolvers = List.empty[Unit => Unit]

  def getSaveData(elements: List[Element]): Data = {
    initialNs = getInitialNs(elements)
    curRefPath = List(initialNs)
    resolve(elements)
    postResolvers.foreach(_(()))
    addRowSetterToTables()
    (getTables, Nil)
  }

  private def addRowSetterToTables(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table      = refPath.last
        val insertStmt = insertEmptyRowStmt(table, cols)
        val colSetters = colSettersMap(refPath)
        debug(s"--- save -------------------  ${colSetters.length}  $refPath")
        debug(insertStmt)

        tableDatas(refPath) = Table(refPath, insertStmt)
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

  protected def insertEmptyRowStmt(
    table: String, cols: List[(String, String)]
  ): String = {
    val columns           = cols.map(_._1).mkString(",\n  ")
    val inputPlaceholders = cols.map(_ => "?").mkString(", ")
    s"""INSERT INTO $table (
       |  $columns
       |) VALUES ($inputPlaceholders)""".stripMargin
  }

  private def getTables: List[Table] = {
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

  protected def getParamIndex(
    attr: String, add: Boolean = true, castExt: String = ""
  ): (List[String], Int) = {
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
    optValue: Option[T],
    transformValue: T => Any,
    exts: List[String] = Nil
  ): Unit = {
    val paramIndex = save.paramIndex(attr, "?", exts(2))
//    val stableSave = save
    optValue.fold {
      save.add((ps: PS) => ps.setNull(paramIndex, 0))
    } { value =>
      val setter = transformValue(value).asInstanceOf[(PS, Int) => Unit]
      save.add((ps: PS) => setter(ps, paramIndex))
    }
  }

  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String] = Nil,
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(ns, attr, optRefNs, optSet, exts(1), set2array)
  }

  override protected def addSeq[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSeq: Option[Seq[T]],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    addIterable(ns, attr, optRefNs, optSeq, exts(1), seq2array)
  }

  override protected def addByteArray(
    ns: String,
    attr: String,
    optArray: Option[Array[Byte]],
  ): Unit = {
    val paramIndex = save.paramIndex(attr)
    if (optArray.nonEmpty && optArray.get.nonEmpty) {
      save.add((ps: PS) => ps.setBytes(paramIndex, optArray.get))
    } else {
      save.add((ps: PS) => ps.setNull(paramIndex, 0))
    }
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val paramIndex = save.paramIndex(attr)
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        save.add((ps: PS) =>
          ps.setBytes(paramIndex, map2jsonByteArray(map, value2json)))
      case _                                    =>
        save.add((ps: PS) => ps.setNull(paramIndex, 0))
    }
  }

  override protected def addRef(
    ns: String, refAttr: String, refNs: String, card: Card
  ): Unit = {
    save = card match {
      case CardOne => save.refOne(ns, refAttr, refNs)
      case _       => save.refMany(ns, refAttr, refNs)
    }
  }

  override protected def addBackRef(backRefNs: String): Unit = {
    save = save.backRef
  }

  override protected def handleRefNs(refNs: String): Unit = ()


  // Helpers -------------------------------------------------------------------

  private def addIterable[T, M[_] <: Iterable[_]](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optIterable: Option[M[T]],
    sqlTpe: String,
    iterable2array: M[T] => Array[AnyRef],
  ): Unit = {
    optRefNs.fold {
      val paramIndex = save.paramIndex(attr)
      if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val iterable = optIterable.get
        save.add((ps: PS) => {
          val conn  = ps.getConnection
          val array = conn.createArrayOf(sqlTpe, iterable2array(iterable))
          ps.setArray(paramIndex, array)
        })
      } else {
        save.add((ps: PS) => ps.setNull(paramIndex, 0))
      }
    } { refNs =>
      optIterable.foreach(refIds =>
        save.addCardManyRefAttr(
          ns, attr, refNs, refIds.asInstanceOf[Set[Long]]
        )
      )
    }
  }
  protected def join[T, M[_] <: Iterable[_]](
    ns: String,
    refAttr: String,
    refNs: String,
    optIterable: Option[M[T]]
  ): Unit = {
    val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
    if (optIterable.isEmpty || optIterable.get.isEmpty) {
      addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
    } else {
      val joinTable = ss(ns, refAttr, refNs)
      val joinPath  = curPath :+ joinTable

      // join table with single row
      // (treated as normal insert since there's only 1 join per row)
      val (id1, id2) = if (ns == refNs)
        (ss(ns, "1_id"), ss(refNs, "2_id"))
      else
        (ss(ns, "id"), ss(refNs, "id"))
      // When insertion order is reversed, this join table
      // will be set after left and right has been inserted
      inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts

      if (paramIndexes.isEmpty) {
        // If current namespace has no attributes, then add an empty row with
        // default null values (only to be referenced as the left side of the join table)
        val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
        addColSetter(curPath, emptyRowSetter)
        inserts = inserts :+ (curRefPath -> List())
      }

      // Join table setter
      val refIds2            = optIterable.get.iterator.asInstanceOf[Iterator[Long]]
      val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
        val refId1 = idsMap(curPath)(rowIndex)
        while (refIds2.hasNext) {
          val refId2 = refIds2.next()
          ps.setLong(1, refId1)
          ps.setLong(2, refId2)
          if (refIds2.hasNext)
            ps.addBatch()
        }
      }
      addColSetter(joinPath, joinSetter)
    }
  }
}