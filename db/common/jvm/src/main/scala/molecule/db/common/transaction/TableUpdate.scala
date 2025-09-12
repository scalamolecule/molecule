package molecule.db.common.transaction

import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.validation.insert.InsertValidators_
import java.sql.PreparedStatement as PS
import molecule.db.common.query.{Model2SqlQuery, SqlQueryBase}
import scala.collection.mutable.ListBuffer


case class TableUpdate(
  refPath: List[String],
  colInputs: List[String] = Nil,
  colSetters: List[PS => Unit] = Nil,
) {
  def add(inputAndSetter: (String, PS => Unit)) = copy(
    colInputs = colInputs :+ inputAndSetter._1,
    colSetters = colSetters :+ inputAndSetter._2
  )

  def sql: ListBuffer[Long] => String = {
    val table = refPath.last
    val cols  = colInputs.mkString(",\n  ")
    (ids: ListBuffer[Long]) =>
      s"""UPDATE $table SET
         |  $cols
         |WHERE id IN (${ids.mkString(", ")})""".stripMargin
  }


  //  override def toString =
  //    s"""--------------------------------------------------
  //       |TableUpdate(
  //       |  refPath           = $refPath,
  //       |  columns           = $columns,
  //       |  foreignKeys       = $foreignKeys,
  //       |  inputPlaceHolders = $inputPlaceHolders,
  //       |  colSetters        = <${colSetters.length} colSetters>
  //       |)
  //       |
  //       |$sql
  //       |--------------------------------------------------""".stripMargin
}
