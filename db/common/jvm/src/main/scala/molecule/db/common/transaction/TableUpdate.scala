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

case class TableUpdate(
  refPath: List[String],
  columns: List[String] = Nil,
  foreignKeys: List[(String, List[String])] = Nil,
  inputPlaceHolders: List[String] = Nil,
  colSetters: List[(PS, Product) => Unit] = Nil,
) {
  def add(a: Attr, colSetter: (PS, Product) => Unit) = copy(
    columns = columns :+ a.attr,
    inputPlaceHolders = inputPlaceHolders :+ "?",
    colSetters = colSetters :+ colSetter
  )

  val defaultValues = "(id) VALUES (DEFAULT)"

  def sql = {
    val table  = refPath.last
//    val cols   = (columns ++ foreignKeys.map(_._1)).mkString(",\n  ")
//    val inputs = inputPlaceHolders.mkString(", ")
//    if (cols.nonEmpty) {
//      s"""INSERT INTO $table (
//         |  $cols
//         |) VALUES ($inputs)""".stripMargin
//    } else {
//      s"INSERT INTO $table $defaultValues"
//    }

    val cols = columns.mkString(",\n  ")
//    val updateClauses = clauses.mkString(" AND\n  ")
    val updateClauses = "xxx"
    s"""UPDATE $table
       |SET
       |  $cols
       |WHERE
       |  $updateClauses""".stripMargin
  }

  override def toString =
    s"""--------------------------------------------------
       |TableUpdate(
       |  refPath           = $refPath,
       |  columns           = $columns,
       |  foreignKeys       = $foreignKeys,
       |  inputPlaceHolders = $inputPlaceHolders,
       |  colSetters        = <${colSetters.length} colSetters>
       |)
       |
       |$sql
       |--------------------------------------------------""".stripMargin
}
