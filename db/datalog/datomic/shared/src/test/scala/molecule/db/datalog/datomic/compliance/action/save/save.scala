package molecule.db.datalog.datomic.compliance.action.save

import molecule.coreTests.setup.{MUnitSuiteWithArrays, Test}
import molecule.coreTests.spi.action.save.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class SaveCardOneTest extends Test {
  SaveCardOne(this, Api_datomic_async)
}
class SaveCardSeqTest extends MUnitSuiteWithArrays {
  SaveCardSeq(this, Api_datomic_async)
}
class SaveCardSetTest extends Test {
  SaveCardSet(this, Api_datomic_async)
}
class SaveCardMapTest extends Test {
  SaveCardMap(this, Api_datomic_async)
}
class SaveRefsTest extends Test {
  SaveRefs(this, Api_datomic_async)
}
class SaveSemanticsTest extends Test {
  SaveSemantics(this, Api_datomic_async)
}
