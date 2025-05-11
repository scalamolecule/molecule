package molecule.db.sql.h2.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.ref.{AggrRef_count, AggrRef_distinct, AggrRef_min_max, AggrRef_min_max_n, AggrRef_sample, AggrRef_sample_n}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async

class AggrRef_countTest extends Test {
  AggrRef_count(this, Api_h2_async)
}
class AggrRef_distinctTest extends Test {
  AggrRef_distinct(this, Api_h2_async)
}
class AggrRef_min_maxTest extends Test {
  AggrRef_min_max(this, Api_h2_async)
}
class AggrRef_min_max_nTest extends Test {
  AggrRef_min_max_n(this, Api_h2_async)
}
class AggrRef_sampleTest extends Test {
  AggrRef_sample(this, Api_h2_async)
}
class AggrRef_sample_nTest extends Test {
  AggrRef_sample_n(this, Api_h2_async)
}
