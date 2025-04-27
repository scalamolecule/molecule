package molecule.db.sql.postgres.query

import molecule.core.ast.DataModel.Element
import molecule.db.sql.core.query.*

class Model2SqlQuery_postgres(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_postgres
    with QueryExprSet_postgres
    with QueryExprSeq_postgres
    with QueryExprMap_postgres
    with QueryExprSetRefAttr_postgres
    with SqlQueryBase