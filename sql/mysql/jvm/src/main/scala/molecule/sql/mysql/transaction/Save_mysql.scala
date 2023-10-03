package molecule.sql.mysql.transaction

import java.sql.{PreparedStatement => PS}
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave

trait Save_mysql extends SqlSave { self: ResolveSave =>

  doPrint = false

  override protected def addSet[T](
    ns: String,
    attr: String,
    optSet: Option[Set[Any]],
    transformValue: T => Any,
    set2array: Set[Any] => Array[AnyRef],
    refNs: Option[String],
    exts: List[String] = Nil,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    refNs.fold {
      val (curPath, paramIndex) = getParamIndex(attr)
      val colSetter: Setter     = optSet.fold {
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setNull(paramIndex, 0)
      } { set =>
        if (set.nonEmpty) {
          val json = set2json(set.asInstanceOf[Set[T]], value2json)
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setString(paramIndex, json)
        } else {
          (ps: PS, _: IdsMap, _: RowIndex) =>
            ps.setNull(paramIndex, 0)
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
        val refIds2            = set.iterator.asInstanceOf[Iterator[Long]]
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
}