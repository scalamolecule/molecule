package molecule.db.sql.h2.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.refNum.*
import molecule.db.sql.h2.setup.Api_h2_async

class AggrRefNum_sumTest extends Test {
  AggrRefNum_sum(this, Api_h2_async)
}
class AggrRefNum_medianTest extends Test {
  AggrRefNum_median(this, Api_h2_async)
}
class AggrRefNum_avgTest extends Test {
  AggrRefNum_avg(this, Api_h2_async)
}
class AggrRefNum_varianceTest extends Test {
  AggrRefNum_variance(this, Api_h2_async)
}
class AggrRefNum_stddevTest extends Test {
  AggrRefNum_stddev(this, Api_h2_async)
}
