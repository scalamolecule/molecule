package molecule.db.datalog.datomic.query

import molecule.core.dataModel.DataModel
import molecule.core.util.MoleculeLogging
import molecule.db.core.marshalling.dbView.DbView
import molecule.db.core.util.FutureUtils
import molecule.db.datalog.core.query.{DatomicQueryBase, Model2DatomicQuery}
import molecule.db.datalog.datomic.facade.DatomicConn_JVM
import scala.collection.mutable.ListBuffer

/**
 * @param elements  Molecule model
 * @param optLimit  When going forward from start, use a positive number.
 *                  And vice versa from end with a negative number. Can't be zero.
 * @param optOffset Positive offset from start when going forwards,
 *                  negative offset from end when going backwards
 * @param dbView    Database with a time perspective (Datomic)
 */
case class DatomicQueryResolveOffset[Tpl](
  dataModel: DataModel,
  optLimit: Option[Int],
  optOffset: Option[Int],
  dbView: Option[DbView],
  m2q: Model2DatomicQuery[Tpl] & DatomicQueryBase
) extends DatomicQueryResolve[Tpl](dataModel, dbView, m2q)
  with FutureUtils
  with MoleculeLogging {


  // Datomic querying is synchronous
  def getListFromOffset_sync(implicit conn: DatomicConn_JVM): (List[Tpl], Int, Boolean) = {
    offsetLimitCheck(optLimit, optOffset)
    val rows       = getRawData(conn)
    val totalCount = rows.size
    val sortedRows = sortRows(rows)
    if (m2q.isNested) {
      val nestedRows    = m2q.rows2nested(sortedRows)
      val topLevelCount = nestedRows.length
      val fromUntil     = getFromUntil(topLevelCount, optLimit, optOffset)
      val hasMore       = fromUntil.fold(totalCount > 0)(_._3)
      (offsetList(nestedRows, fromUntil), topLevelCount, hasMore)

    } else {
      val fromUntil = getFromUntil(totalCount, optLimit, optOffset)
      val hasMore   = fromUntil.fold(totalCount > 0)(_._3)
      val tuples    = ListBuffer.empty[Tpl]

      if (m2q.isOptNested) {
        postAdjustPullCasts()
        val row2tpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
        offsetRaw(sortedRows, fromUntil).forEach { row =>
          tuples += row2tpl(row).asInstanceOf[Tpl]
        }
        (tuples.toList.filterNot(_ == Nil), totalCount, hasMore)

      } else if (m2q.nestedOptRef) {
        val row2tpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
        offsetRaw(sortedRows, fromUntil).forEach { row =>
          //          println("\n================= ROW: " + row)
          tuples += row2tpl(row).asInstanceOf[Tpl]
        }
        (tuples.toList.filterNot(_ == Nil), totalCount, hasMore)

      } else {
        val row2tpl = m2q.castRow2AnyTpl(m2q.castss.head, 0)
        offsetRaw(sortedRows, fromUntil).forEach { row =>
          tuples += row2tpl(row).asInstanceOf[Tpl]
        }
        (tuples.toList, totalCount, hasMore)
      }
    }
  }

//  def subscribe(
//    conn: DatomicConn_JVM,
//    callback: List[Tpl] => Unit
//  ): Unit = {
//    val involvedAttrs        = getAttrNames(dataModel.elements)
//    val involvedDeleteEntity = getInitialEntity(dataModel.elements)
//    val maybeCallback        = (mutationAttrs: Set[String], isDelete: Boolean) => {
//      if (
//        mutationAttrs.exists(involvedAttrs.contains) ||
//          isDelete && mutationAttrs.head.startsWith(involvedDeleteEntity)
//      ) {
//        Future {
//          val m2q = new Model2DatomicQuery[Tpl](dataModel)
//          callback(
//            DatomicQueryResolveOffset(dataModel, optLimit, None, None, m2q).getListFromOffset_sync(conn)._1
//          )
//        }
//      } else Future.unit
//    }
//    conn.addCallback(dataModel -> maybeCallback)
//    ???
//  }
}
