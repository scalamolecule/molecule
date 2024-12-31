package molecule.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.aggregation.ref._
import molecule.datalog.datomic.setup.Api_datomic_async

class AggrRef_count extends MUnitSuite {
  AggrRef_count(this, Api_datomic_async)
}
class AggrRef_distinct extends MUnitSuite {
  AggrRef_distinct(this, Api_datomic_async)
}
class AggrRef_min_max extends MUnitSuite {
  AggrRef_min_max(this, Api_datomic_async)
}
class AggrRef_min_max_n extends MUnitSuite {
  AggrRef_min_max_n(this, Api_datomic_async)
}
class AggrRef_sample extends MUnitSuite {
  AggrRef_sample(this, Api_datomic_async)
}
class AggrRef_sample_n extends MUnitSuite {
  AggrRef_sample_n(this, Api_datomic_async)
}
