package molecule.db.postgresql.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.refNum.*
import molecule.db.postgresql.setup.Api_postgresql_async

class AggrRefNum_sumTest extends MUnit {
  AggrRefNum_sum(this, Api_postgresql_async)
}
class AggrRefNum_medianTest extends MUnit {
  AggrRefNum_median(this, Api_postgresql_async)
}
class AggrRefNum_avgTest extends MUnit {
  AggrRefNum_avg(this, Api_postgresql_async)
}
class AggrRefNum_varianceTest extends MUnit {
  AggrRefNum_variance(this, Api_postgresql_async)
}
class AggrRefNum_stddevTest extends MUnit {
  AggrRefNum_stddev(this, Api_postgresql_async)
}
