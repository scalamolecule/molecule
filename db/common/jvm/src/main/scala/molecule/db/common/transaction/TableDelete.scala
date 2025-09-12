package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import scala.annotation.tailrec
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.query.Model2SqlQuery
import molecule.db.common.validation.insert.InsertValidators_

case class TableDelete(
  table: String,
  filterElements: List[Element],
  joinTable: Option[String],
  joinClause: Option[String],
) {
  def add(a: Element) = copy(
    filterElements = filterElements :+ a,
  )

  def getSql(m2q: Model2SqlQuery): String = {
    joinTable.fold {
      m2q.resolve(filterElements)
      val joins = m2q.mkJoins(1)
      val where = m2q.mkWhere
      s"DELETE FROM $table$joins$where"
    } { joinTable =>
      m2q.resolve(filterElements)
      val joins     = m2q.mkJoins(1)
      val where     = m2q.mkWhere
      val joinWhere = joinClause.get
      s"""DELETE FROM $table$joins
         |WHERE EXISTS (
         |  SELECT 1 FROM $joinTable$joins$where AND
         |  $joinWhere
         |)""".stripMargin
    }
  }

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
