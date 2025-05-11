package molecule.db.datalog.datomic.compliance.action.insert

import molecule.db.compliance.setup.{MUnitSuiteWithArrays, Test}
import molecule.db.compliance.test.action.insert.{InsertCardMap, InsertCardOne, InsertCardSeq, InsertCardSet, InsertRefs, InsertSemantics}
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_async

class InsertCardOneTest extends Test {
  InsertCardOne(this, Api_datomic_async)
}
class InsertCardSeqTest extends MUnitSuiteWithArrays {
  InsertCardSeq(this, Api_datomic_async)
}
class InsertCardSetTest extends Test {
  InsertCardSet(this, Api_datomic_async)
}
class InsertCardMapTest extends Test {
  InsertCardMap(this, Api_datomic_async)
}
class InsertRefsTest extends Test {
  InsertRefs(this, Api_datomic_async)
}
class InsertSemanticsTest extends Test {
  InsertSemantics(this, Api_datomic_async)
}
