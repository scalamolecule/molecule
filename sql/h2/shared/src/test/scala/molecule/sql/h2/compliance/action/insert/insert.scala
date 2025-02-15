package molecule.sql.h2.compliance.action.insert

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.insert._
import molecule.sql.h2.setup.Api_h2_async

class InsertCardOne extends Test {
  InsertCardOne(this, Api_h2_async)
}
class InsertCardSet extends Test {
  InsertCardSet(this, Api_h2_async)
}
class InsertCardMap extends Test {
  InsertCardMap(this, Api_h2_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_h2_async)
}
class InsertRefs extends Test {
  InsertRefs(this, Api_h2_async)
}
class InsertSemantics extends Test {
  InsertSemantics(this, Api_h2_async)
}
