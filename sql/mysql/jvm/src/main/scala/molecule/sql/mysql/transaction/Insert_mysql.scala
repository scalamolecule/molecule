package molecule.sql.mysql.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_mysql extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

  doPrint = false


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
        val set       = tpl.productElement(tplIndex).asInstanceOf[Set[T]]
        val colSetter = if (set.nonEmpty) {
          val json = set2json(set, value2json)
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, json)
        } else {
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, java.sql.Types.NULL)
        }
        addColSetter(curPath, colSetter)

    } { refNs =>
      val refAttr   = attr
      val joinTable = s"${ns}_${refAttr}_$refNs"
      val curPath   = if (paramIndexes.nonEmpty) curRefPath else List("0", ns)
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
            val json = set2json(set.asInstanceOf[Set[T]], value2json)
            (ps: PS, _: IdsMap, _: RowIndex) =>
              ps.setString(paramIndex, json)
          case None =>
            (ps: PS, _: IdsMap, _: RowIndex) =>
              ps.setNull(paramIndex, java.sql.Types.NULL)
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

  override protected lazy val extsString     = List("", "VARCHAR")
  override protected lazy val extsInt        = List("", "INTEGER")
  override protected lazy val extsLong       = List("", "BIGINT")
  override protected lazy val extsFloat      = List("", "DECIMAL")
  override protected lazy val extsDouble     = List("", "DOUBLE")
  override protected lazy val extsBoolean    = List("", "BOOLEAN")
  override protected lazy val extsBigInt     = List("", "DECIMAL")
  override protected lazy val extsBigDecimal = List("", "DECIMAL")
  override protected lazy val extsDate       = List("", "DATE")
  override protected lazy val extsUUID       = List("", "UUID")
  override protected lazy val extsURI        = List("", "VARCHAR")
  override protected lazy val extsByte       = List("", "SMALLINT")
  override protected lazy val extsShort      = List("", "SMALLINT")
  override protected lazy val extsChar       = List("", "CHAR")
}