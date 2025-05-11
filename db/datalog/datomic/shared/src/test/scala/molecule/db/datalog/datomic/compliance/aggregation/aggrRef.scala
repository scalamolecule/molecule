package molecule.db.datalog.datomic.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.ref.{AggrRef_count, AggrRef_distinct, AggrRef_min_max, AggrRef_min_max_n, AggrRef_sample, AggrRef_sample_n}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AggrRef_countTest extends Test {
  AggrRef_count(this, Api_datomic_async)
}
class AggrRef_distinctTest extends Test {
  AggrRef_distinct(this, Api_datomic_async)
}
class AggrRef_min_maxTest extends Test {
  AggrRef_min_max(this, Api_datomic_async)
}
class AggrRef_min_max_nTest extends Test {
  AggrRef_min_max_n(this, Api_datomic_async)
}
class AggrRef_sampleTest extends Test {
  AggrRef_sample(this, Api_datomic_async)
}
class AggrRef_sample_nTest extends Test {
  AggrRef_sample_n(this, Api_datomic_async)
}
