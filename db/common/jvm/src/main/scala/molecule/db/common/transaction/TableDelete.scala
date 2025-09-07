package molecule.db.common.transaction

import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.validation.insert.InsertValidators_
import java.sql.PreparedStatement as PS

//case class ForeignKey(
//  refAttr: String,
//  refPath: List[String],
//  tplIndex: Int,
//)

case class TableDelete(
  table: String,
  filterElements: List[Element],
  joinTable: Option[String],
  joinClause: Option[String],
) {
  def add(a: Element) = copy(
    filterElements = filterElements :+ a,
  )

//  val defaultValues = "(id) VALUES (DEFAULT)"

//  def getSql(m2q: Model2Query): String = {
//    val table  = refPath.last
////    val cols   = (columns ++ foreignKeys.map(_._1)).mkString(",\n  ")
////    val inputs = inputPlaceHolders.mkString(", ")
////    if (cols.nonEmpty) {
////      s"""INSERT INTO $table (
////         |  $cols
////         |) VALUES ($inputs)""".stripMargin
////    } else {
////      s"INSERT INTO $table $defaultValues"
////    }
//
////    val cols = columns.mkString(",\n  ")
////    val updateClauses = clauses.mkString(" AND\n  ")
////    s"""UPDATE $table
////       |SET
////       |  $cols
////       |WHERE
////       |  $updateClauses""".stripMargin
//
////    val deleteClauses = clauses.mkString(" AND\n  ")
//    val deleteClauses = "zzz"
//    s"""DELETE FROM $table
//       |WHERE
//       |  $deleteClauses""".stripMargin
//  }

//  override def toString =
//    s"""--------------------------------------------------
//       |TableDelete(
//       |  refPath           = $refPath,
//       |  columns           = $columns,
//       |  foreignKeys       = $foreignKeys,
//       |)
//       |
//       |$sql
//       |--------------------------------------------------""".stripMargin
}
