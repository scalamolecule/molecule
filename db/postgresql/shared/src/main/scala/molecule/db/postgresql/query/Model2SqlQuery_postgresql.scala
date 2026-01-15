package molecule.db.postgresql.query

import molecule.core.dataModel.*
import molecule.db.common.query.*

class Model2SqlQuery_postgresql(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_postgresql
    with QueryExprSet_postgresql
    with QueryExprSeq_postgresql
    with QueryExprMap_postgresql
    with QueryExprSetRefAttr_postgresql
    with QueryExprSubQuery_postgresql
    with SqlQueryBase
