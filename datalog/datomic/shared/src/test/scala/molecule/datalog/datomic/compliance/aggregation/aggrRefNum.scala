package molecule.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.refNum._
import molecule.datalog.datomic.setup.Api_datomic_async

class AggrRefNum_sum extends MUnitSuite {
  AggrRefNum_sum(this, Api_datomic_async)
}
class AggrRefNum_median extends MUnitSuite {
  AggrRefNum_median(this, Api_datomic_async)
}
class AggrRefNum_avg extends MUnitSuite {
  AggrRefNum_avg(this, Api_datomic_async)
}
class AggrRefNum_variance extends MUnitSuite {
  AggrRefNum_variance(this, Api_datomic_async)
}
class AggrRefNum_stddev extends MUnitSuite {
  AggrRefNum_stddev(this, Api_datomic_async)
}
