package molecule.db.sql.h2.query

import molecule.core.dataModel.Element
import molecule.db.sql.core.query.*

class Model2SqlQuery_h2(elements0: List[Element])
  extends Model2SqlQuery(elements0)
    with QueryExprOne_h2
    with QueryExprSet_h2
    with QueryExprSeq_h2
    with QueryExprMap_h2
    with QueryExprSetRefAttr_h2
    with SqlQueryBase