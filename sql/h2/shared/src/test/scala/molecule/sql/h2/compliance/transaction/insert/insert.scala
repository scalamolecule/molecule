package molecule.sql.h2.compliance.transaction.insert

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.transaction.insert._
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
