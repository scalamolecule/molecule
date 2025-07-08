package molecule.db.h2.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.ref.*
import molecule.db.h2.setup.Api_h2_async

class AggrRef_countTest extends MUnit {
  AggrRef_count(this, Api_h2_async)
}
class AggrRef_distinctTest extends MUnit {
  AggrRef_distinct(this, Api_h2_async)
}
class AggrRef_min_maxTest extends MUnit {
  AggrRef_min_max(this, Api_h2_async)
}
class AggrRef_min_max_nTest extends MUnit {
  AggrRef_min_max_n(this, Api_h2_async)
}
class AggrRef_sampleTest extends MUnit {
  AggrRef_sample(this, Api_h2_async)
}
class AggrRef_sample_nTest extends MUnit {
  AggrRef_sample_n(this, Api_h2_async)
}
