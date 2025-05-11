package molecule.db.sql.mysql.query

import molecule.db.core.ast.Element
import molecule.db.sql.core.query.*
import scala.collection.mutable.ListBuffer

class Model2SqlQuery_mysql(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_mysql
    with QueryExprSet_mysql
    with QueryExprSeq_mysql
    with QueryExprMap_mysql
    with QueryExprSetRefAttr_mysql
    with SqlQueryBase {


  override def getWhereClauses: ListBuffer[String] = {
    resolveElements(elements0)
    val clauses    = where.map {
      case (col, expr) => s"$col $expr"
    }
    val joinsExist = Nil

    // This would be a false positive only working for `Entity`...
    //    val joinsExist = if (joins.isEmpty) Nil else
    //      List(
    //        s"""Entity.id IN (
    //           |  SELECT Entity.id FROM (
    //           |    SELECT Entity.id FROM Entity
    //           |      ${mkJoins(2).trim}
    //           |  ) AS t
    //           |)""".stripMargin)

    clauses ++ joinsExist
  }

  override def pagination(
    optLimit: Option[Int],
    optOffset: Option[Int],
    isBackwards: Boolean
  ): String = {
    if (isManNested || isOptNested) {
      ""
    } else if (hardLimit == 0) {
      if (!isBackwards) {
        (optOffset, optLimit) match {
          case (None, None)                => ""
          case (None, Some(limit))         => s"\nLIMIT 0, $limit"
          case (Some(offset), None)        => s"\nLIMIT $offset, 18446744073709551615"
          case (Some(offset), Some(limit)) =>
            s"\nLIMIT $offset, $limit"
        }
      } else {
        (optOffset, optLimit) match {
          case (None, None)                => ""
          case (None, Some(limit))         => s"\nLIMIT 0, ${-limit}"
          case (Some(offset), None)        => s"\nLIMIT ${-offset}, 18446744073709551615"
          case (Some(offset), Some(limit)) => s"\nLIMIT ${-offset}, ${-limit}"
        }
      }
    } else {
      optOffset match {
        case None                        => s"\nLIMIT 0, $hardLimit"
        case Some(offset) if isBackwards => s"\nLIMIT ${-offset}, $hardLimit"
        case Some(offset)                => s"\nLIMIT $offset, $hardLimit"
      }
    }
  }
}