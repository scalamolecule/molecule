package molecule.sql.sqlite.transaction

import java.sql.{PreparedStatement => PS}
import java.util.Date
import molecule.core.transaction.ResolveSave
import molecule.sql.core.transaction.SqlSave

trait Save_sqlite extends SqlSave with TxBase_sqlite { self: ResolveSave =>

  doPrint = false
//  doPrint = true

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
    addIterable(ns, attr, optRefNs, optSet, value2json)
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
    addIterable(ns, attr, optRefNs, optSeq, value2json)
  }

  override protected def addMap[T](
    ns: String,
    attr: String,
    optMap: Option[Map[String, T]],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    val paramIndex1 = save.paramIndex
    optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        save.add(attr, (ps: PS) =>
          ps.setString(paramIndex1, map2json(map, value2json)))
      case _                                    =>
        save.add(attr, (ps: PS) => ps.setNull(paramIndex1, 0))
    }





    val (curPath, paramIndex) = getParamIndex(attr)
    val colSetter: Setter     = optMap match {
      case Some(map: Map[_, _]) if map.nonEmpty =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setString(paramIndex, map2json(map, value2json))
      case _                                    =>
        (ps: PS, _: IdsMap, _: RowIndex) =>
          ps.setNull(paramIndex, 0)
    }
    addColSetter(curPath, colSetter)
  }

  // Save Floats as Doubles (REAL PRECISION) in SQlite
  override protected lazy val transformFloat =
    (v: Float) => (ps: PS, n: Int) => ps.setDouble(n, v.toString.toDouble)

  override protected lazy val transformDate =
    (v: Date) => (ps: PS, n: Int) => ps.setLong(n, v.getTime)


  // Helpers -------------------------------------------------------------------

  override protected def insertEmptyRowStmt(
    table: String, cols: List[(String, String)]
  ): String = {
    val columns           = cols.map(_._1).mkString(",\n  ")
    val inputPlaceholders = cols.map {
      case (_, castExt) => s"?$castExt"
    }.mkString(", ")
    if (cols.nonEmpty) {
      s"""INSERT INTO $table (
         |  $columns
         |) VALUES ($inputPlaceholders)""".stripMargin
    } else {
      s"INSERT INTO $table DEFAULT VALUES"
    }
  }

  private def addIterable[T](
    ns: String,
    attr: String,
    optRefNs: Option[String],
    optIterable: Option[Iterable[T]],
    value2json: (StringBuffer, T) => StringBuffer
  ): Unit = {
    optRefNs.fold {
      val paramIndex1 = save.paramIndex
      if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val json = iterable2json(optIterable.get, value2json)
        save.add(attr, (ps: PS) => ps.setString(paramIndex1, json))
      } else {
        save.add(attr, (ps: PS) => ps.setNull(paramIndex1, 0))
      }




      val (curPath, paramIndex) = getParamIndex(attr)
      val colSetter: Setter     = if (optIterable.nonEmpty && optIterable.get.nonEmpty) {
        val json = iterable2json(optIterable.get, value2json)
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setString(paramIndex, json)
      } else {
        (ps: PS, _: IdsMap, _: RowIndex) => ps.setNull(paramIndex, 0)
      }
      addColSetter(curPath, colSetter)


    } { refNs =>
      optIterable.foreach(refIds =>
        save.addCardManyRefAttr(
          ns, attr, refNs, refIds.asInstanceOf[Set[Long]], defaultValues
        )
      )


      join(ns, attr, refNs, optIterable)
    }
  }
}