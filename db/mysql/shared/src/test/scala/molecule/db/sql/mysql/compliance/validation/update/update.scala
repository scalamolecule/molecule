package molecule.db.sql.mysql.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.mysql.setup.Api_mysql_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_mysql_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_mysql_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_mysql_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_mysql_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_mysql_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_mysql_async)
}
