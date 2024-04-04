package molecule.sql.mysql.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.base.util.BaseHelpers
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave

trait Save_mysql extends SqlSave with BaseHelpers { self: ResolveSave =>

//  override protected def addSet[T](
//    ns: String,
//    attr: String,
//    optRefNs: Option[String],
//    optSet: Option[Set[T]],
//    transformValue: T => Any,
//    exts: List[String] = Nil,
//    set2array: Set[T] => Array[AnyRef],
//    value2json: (StringBuffer, T) => StringBuffer
//  ): Unit = {
//    refNs.fold {
//      val (curPath, paramIndex) = getParamIndex(attr)
//      val colSetter: Setter     = optSet.fold {
//        (ps: PS, _: IdsMap, _: RowIndex) =>
//          ps.setNull(paramIndex, 0)
//      } { set =>
//        if (set.nonEmpty) {
//          val json = iterable2json(set, value2json)
//          (ps: PS, _: IdsMap, _: RowIndex) =>
//            ps.setString(paramIndex, json)
//        } else {
//          (ps: PS, _: IdsMap, _: RowIndex) =>
//            ps.setNull(paramIndex, 0)
//        }
//      }
//      addColSetter(curPath, colSetter)
//    } { refNs =>
//      val curPath = if (paramIndexes.nonEmpty) curRefPath else List(ns)
//      if (optSet.isEmpty || optSet.get.isEmpty) {
//        addColSetter(curPath, (_: PS, _: IdsMap, _: RowIndex) => ())
//      } else {
//        val set       = optSet.get
//        val refAttr   = attr
//        val joinTable = ss(ns, refAttr, refNs)
//        val joinPath  = curPath :+ joinTable
//
//        // join table with single row (treated as normal insert since there's only 1 join per row)
//        val (id1, id2) = if (ns == refNs) {
//          (ss(ns, "1_id"), ss(refNs, "2_id"))
//        } else
//          (ss(ns, "id"), ss(refNs, "id"))
//
//        // When insertion order is reversed, this join table will be set after left and right has been inserted
//        inserts = (joinPath, List((id1, ""), (id2, ""))) +: inserts
//
//        if (paramIndexes.isEmpty) {
//          // If current namespace has no attributes, then add an empty row with
//          // default null values (only to be referenced as the left side of the join table)
//          val emptyRowSetter: Setter = (ps: PS, _: IdsMap, _: RowIndex) => ps.addBatch()
//          addColSetter(curPath, emptyRowSetter)
//          inserts = inserts :+ (curRefPath, List())
//        }
//
//        // Join table setter
//        val refIds2            = set.iterator.asInstanceOf[Iterator[String]]
//        val joinSetter: Setter = (ps: PS, idsMap: IdsMap, rowIndex: RowIndex) => {
//          val refId1 = idsMap(curPath)(rowIndex)
//          while (refIds2.hasNext) {
//            val refId2 = refIds2.next()
//            ps.setLong(1, refId1)
//            ps.setLong(2, refId2.toLong)
//            if (refIds2.hasNext)
//              ps.addBatch()
//          }
//        }
//        addColSetter(joinPath, joinSetter)
//      }
//    }
//  }
//
//  override protected lazy val transformDate =
//    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)


  override protected def addSet[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optSet: Option[Set[T]],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRefNs.fold {
      addIterable(attr, optSet, value2json)
    } { refNs =>
      join(ns, attr, refNs, optSet)
    }
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
    optRefNs.fold {
      addIterable(attr, optSeq, value2json)
    } { refNs =>
      join(ns, attr, refNs, optSeq)
    }
  }

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)


  // Helpers -------------------------------------------------------------------

  private def addIterable[T](
    attr: String,
    optIterable: Option[Iterable[T]],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val (curPath, paramIndex) = getParamIndex(attr)
    val colSetter: Setter     = if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
      val json = iterable2json(optIterable.get, value2json)
      (ps: PS, _: IdsMap, _: RowIndex) => ps.setString(paramIndex, json)
    } else {
      (ps: PS, _: IdsMap, _: RowIndex) => ps.setNull(paramIndex, 0)
    }
    addColSetter(curPath, colSetter)
  }
}