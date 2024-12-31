package molecule.datalog.datomic.compliance.transaction.save

import molecule.coreTests.setup.{MUnitSuite, MUnitSuiteWithArrays}
import molecule.coreTests.spi.action.save._
import molecule.datalog.datomic.setup.Api_datomic_async

class SaveCardOne extends MUnitSuite {
  SaveCardOne(this, Api_datomic_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_datomic_async)
}
class SaveCardSet extends MUnitSuite {
  SaveCardSet(this, Api_datomic_async)
}
class SaveCardMap extends MUnitSuite {
  SaveCardMap(this, Api_datomic_async)
}
class SaveRefs extends MUnitSuite {
  SaveRefs(this, Api_datomic_async)
}
class SaveSemantics extends MUnitSuite {
  SaveSemantics(this, Api_datomic_async)
}
