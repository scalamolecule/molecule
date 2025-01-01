package molecule.datalog.datomic.compliance.action.insert

import molecule.coreTests.setup.{Test, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.datalog.datomic.setup.Api_datomic_async

class InsertCardOne extends Test {
  InsertCardOne(this, Api_datomic_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_datomic_async)
}
class InsertCardSet extends Test {
  InsertCardSet(this, Api_datomic_async)
}
class InsertCardMap extends Test {
  InsertCardMap(this, Api_datomic_async)
}
class InsertRefs extends Test {
  InsertRefs(this, Api_datomic_async)
}
class InsertSemantics extends Test {
  InsertSemantics(this, Api_datomic_async)
}
