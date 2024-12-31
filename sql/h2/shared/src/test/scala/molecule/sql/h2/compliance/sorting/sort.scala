package molecule.sql.h2.compliance.sorting

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.sorting._
import molecule.sql.h2.setup.Api_h2_async

class SortAggr extends MUnitSuite {
  SortAggr(this, Api_h2_async)
}
class SortBasics extends MUnitSuite {
  SortBasics(this, Api_h2_async)
}
class SortDynamic extends MUnitSuite {
  SortDynamic(this, Api_h2_async)
}
class SortNested extends MUnitSuite {
  SortNested(this, Api_h2_async)
}

