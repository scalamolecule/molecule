package molecule.sql.h2.compliance.sorting

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.sorting._
import molecule.sql.h2.setup.Api_h2_async

class SortAggr extends Test {
  SortAggr(this, Api_h2_async)
}
class SortBasics extends Test {
  SortBasics(this, Api_h2_async)
}
class SortDynamic extends Test {
  SortDynamic(this, Api_h2_async)
}
class SortNested extends Test {
  SortNested(this, Api_h2_async)
}

