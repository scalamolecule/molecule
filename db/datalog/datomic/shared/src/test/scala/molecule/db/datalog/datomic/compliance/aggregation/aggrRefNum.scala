package molecule.db.datalog.datomic.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.refNum.{AggrRefNum_avg, AggrRefNum_median, AggrRefNum_stddev, AggrRefNum_sum, AggrRefNum_variance}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AggrRefNum_sumTest extends Test {
  AggrRefNum_sum(this, Api_datomic_async)
}
class AggrRefNum_medianTest extends Test {
  AggrRefNum_median(this, Api_datomic_async)
}
class AggrRefNum_avgTest extends Test {
  AggrRefNum_avg(this, Api_datomic_async)
}
class AggrRefNum_varianceTest extends Test {
  AggrRefNum_variance(this, Api_datomic_async)
}
class AggrRefNum_stddevTest extends Test {
  AggrRefNum_stddev(this, Api_datomic_async)
}
