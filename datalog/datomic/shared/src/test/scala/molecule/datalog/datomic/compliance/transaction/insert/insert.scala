package molecule.datalog.datomic.compliance.transaction.insert

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.insert._
import molecule.datalog.datomic.setup.Api_datomic_async

class InsertCardOne extends MUnitSuite {
  InsertCardOne(this, Api_datomic_async)
}
class InsertCardSeq extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_datomic_async)
}
class InsertCardSet extends MUnitSuite {
  InsertCardSet(this, Api_datomic_async)
}
class InsertCardMap extends MUnitSuite {
  InsertCardMap(this, Api_datomic_async)
}
class InsertRefs extends MUnitSuite {
  InsertRefs(this, Api_datomic_async)
}
class InsertSemantics extends MUnitSuite {
  InsertSemantics(this, Api_datomic_async)
}
