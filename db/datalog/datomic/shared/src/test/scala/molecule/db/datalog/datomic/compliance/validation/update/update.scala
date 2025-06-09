package molecule.db.datalog.datomic.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.update.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_datomic_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_datomic_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_datomic_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_datomic_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_datomic_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_datomic_async)
}
