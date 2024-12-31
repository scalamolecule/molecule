package molecule.sql.h2.compliance.transaction.insert

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.sql.h2.setup.Api_h2_async

class InsertCardOne extends MUnitSuite {
  InsertCardOne(this, Api_h2_async)
}
class InsertCardSet extends MUnitSuite {
  InsertCardSet(this, Api_h2_async)
}
class InsertCardMap extends MUnitSuite {
  InsertCardMap(this, Api_h2_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_h2_async)
}
class InsertRefs extends MUnitSuite {
  InsertRefs(this, Api_h2_async)
}
class InsertSemantics extends MUnitSuite {
  InsertSemantics(this, Api_h2_async)
}
