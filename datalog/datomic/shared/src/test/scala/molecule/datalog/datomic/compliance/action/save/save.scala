package molecule.datalog.datomic.compliance.action.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save._
import molecule.datalog.datomic.setup.Api_datomic_async

class SaveCardOne extends Test {
  SaveCardOne(this, Api_datomic_async)
}
class SaveCardSeq extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_datomic_async)
}
class SaveCardSet extends Test {
  SaveCardSet(this, Api_datomic_async)
}
class SaveCardMap extends Test {
  SaveCardMap(this, Api_datomic_async)
}
class SaveRefs extends Test {
  SaveRefs(this, Api_datomic_async)
}
class SaveSemantics extends Test {
  SaveSemantics(this, Api_datomic_async)
}
