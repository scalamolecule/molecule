package molecule.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.ref._
import molecule.datalog.datomic.setup.Api_datomic_async

class AggrRef_count extends Test {
  AggrRef_count(this, Api_datomic_async)
}
class AggrRef_distinct extends Test {
  AggrRef_distinct(this, Api_datomic_async)
}
class AggrRef_min_max extends Test {
  AggrRef_min_max(this, Api_datomic_async)
}
class AggrRef_min_max_n extends Test {
  AggrRef_min_max_n(this, Api_datomic_async)
}
class AggrRef_sample extends Test {
  AggrRef_sample(this, Api_datomic_async)
}
class AggrRef_sample_n extends Test {
  AggrRef_sample_n(this, Api_datomic_async)
}
