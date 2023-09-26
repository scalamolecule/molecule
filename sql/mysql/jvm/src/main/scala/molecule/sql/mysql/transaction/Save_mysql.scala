package molecule.sql.mysql.transaction

import java.sql.{Statement, PreparedStatement => PS}
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.{SqlSave, Table}

trait Save_mysql extends SqlSave { self: ResolveSave =>

  doPrint = false

  override protected def addRowSetterToTables(): Unit = {
    inserts.foreach {
      case (refPath, cols) =>
        val table             = refPath.last
        val columns           = cols.map(_._1).mkString(",\n  ")
        val inputPlaceholders = cols.map { case (_, castExt) => s"?$castExt" }.mkString(", ")
        val stmt = if (cols.nonEmpty) {
          s"""INSERT INTO $table (
             |  $columns
             |) VALUES ($inputPlaceholders)""".stripMargin
        } else {
          s"INSERT INTO $table() VALUES()"
        }

        val colSetters = colSettersMap(refPath)
        debug(s"--- save -------------------  ${colSetters.length}  $refPath")
        debug(stmt)

        val ps = sqlConn.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS)
        tableDatas(refPath) = Table(refPath, stmt, ps)


        colSettersMap(refPath) = Nil
        val rowSetter = (ps: PS, idsMap: IdsMap, _: RowIndex) => {
          // Set all column values for this row in this insert/batch
          colSetters.foreach(colSetter =>
            colSetter(ps, idsMap, 0)
          )
          // Complete row
          ps.addBatch()
        }
        rowSettersMap(refPath) = List(rowSetter)
    }
  }


  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[T]],
    handleValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String] = Nil
  ): Unit = {
    refNs.fold {
      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns + "_" + attr)
      //      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)

      if (optSet.isEmpty || optSet.get.isEmpty) {
        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      } else {
        val set       = optSet.get
        val joinTable = s"${ns}_${attr}_"
        val joinPath  = curPath :+ joinTable

        // join table with single row (treated as normal insert since there's only 1 join per row)
        val (id1, id2) = (s"${ns}_id", s"${ns}_${attr}_id")
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
        val refIds             = set.asInstanceOf[Set[Int]]
        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
          val refId1 = idsMap(curPath)(rowIndex)
          refIds.foreach { refId2 =>
            ps.setLong(1, refId1)
            ps.setInt(2, refId2)
            ps.addBatch()
          }
        }
        addColSetter(joinPath, joinSetter)
      }


    } { refNs =>
      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
      if (optSet.isEmpty || optSet.get.isEmpty) {
        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
      } else {
        val set       = optSet.get
        val refAttr   = attr
        val joinTable = s"${ns}_${refAttr}_$refNs"
        val joinPath  = curPath :+ joinTable

        // join table with single row (treated as normal insert since there's only 1 join per row)
        val (id1, id2) = if (ns == refNs) (s"${ns}_1_id", s"${refNs}_2_id") else (s"${ns}_id", s"${refNs}_id")
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
        val refIds             = set.asInstanceOf[Set[Long]]
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

  override protected lazy val extsString     = List("", "LONGTEXT")
  override protected lazy val extsInt        = List("", "INT")
  override protected lazy val extsLong       = List("", "BIGINT")
  override protected lazy val extsFloat      = List("", "FLOAT")
  override protected lazy val extsDouble     = List("", "DOUBLE")
  override protected lazy val extsBoolean    = List("", "TINYINT(1)")
  override protected lazy val extsBigInt     = List("", "DECIMAL")
  override protected lazy val extsBigDecimal = List("", "DECIMAL")
  override protected lazy val extsDate       = List("", "DATETIME")
  override protected lazy val extsUUID       = List("", "TINYTEXT")
  override protected lazy val extsURI        = List("", "TEXT")
  override protected lazy val extsByte       = List("", "TINYINT")
  override protected lazy val extsShort      = List("", "SMALLINT")
  override protected lazy val extsChar       = List("", "CHAR")
}