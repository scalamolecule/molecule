package molecule.sql.mariadb.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.core.transaction.{InsertResolvers_, ResolveInsert}
import molecule.sql.core.transaction.SqlInsert

trait Insert_mariadb extends SqlInsert { self: ResolveInsert with InsertResolvers_ =>

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
            if (set.nonEmpty) {
              val json = set2json(set.asInstanceOf[Set[T]], value2json)
              (ps: PS, _: IdsMap, _: RowIndex) =>
                ps.setString(paramIndex, json)
            } else {
              (ps: PS, _: IdsMap, _: RowIndex) =>
                ps.setNull(paramIndex, java.sql.Types.NULL)
            }
          case None              =>
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

          case None =>
            (_: PS, _: IdsMap, _: RowIndex) => ()
        }
        addColSetter(joinPath, colSetter)
      }
    }
  }


  override protected lazy val handleDate =
    (v: Any) => (ps: PS, n: Int) => ps.setLong(n, v.asInstanceOf[Date].getTime)
}