package molecule.db.h2.query

import molecule.core.error.ModelError
import molecule.db.common.javaSql.ResultSetImpl
import molecule.db.common.query.SqlQueryBase
import molecule.db.common.query.casting.*
import molecule.db.h2.javaSql.ResultSetImpl_h2
import org.h2.value.ValueRow


trait SqlQueryBase_h2 { self: SqlQueryBase =>

  // Subqueries can be returned as H2 ROW objects
  override def h2Row2resultSet(rowCasts: List[Cast]): (RS, ParamIndex) => RS = {
    (row: RS, paramIndex: Int) =>
      // Get the raw ROW object from H2
      val rowObject = row.getObject(paramIndex)

      // Convert H2's ROW to a ResultSet that we can apply casts to
      rowObject match {
        case null                            =>
          throw ModelError("Null ROW from subquery - subquery returned no results")
        case rs: java.sql.ResultSet          =>
          // H2 returns ROW as a ResultSet - position cursor to first row
          rs.next()
          // Wrap it in our ResultSetImpl
          new ResultSetImpl(rs)
        case valueRow: org.h2.value.ValueRow =>
          // If we get a ValueRow directly, use synthetic ResultSet
          new ResultSetImpl_h2(valueRow)
        case other                           =>
          throw ModelError(
            s"Unsupported H2 ROW type: ${other.getClass.getName}. " +
              s"Aggregating on related entities in subqueries is not currently supported."
          )
      }
  }
}
