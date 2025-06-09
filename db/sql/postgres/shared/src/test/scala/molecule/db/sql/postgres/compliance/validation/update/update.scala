package molecule.db.sql.postgres.compliance.validation.update

import molecule.core.setup.MUnit
import molecule.db.compliance.test.validation.update.*
import molecule.db.sql.postgres.setup.Api_postgres_async

class TypesOneTest extends MUnit {
  TypesOne(this, Api_postgres_async)
}
class TypesOneOptTest extends MUnit {
  TypesOneOpt(this, Api_postgres_async)
}
class TypesSeqTest extends MUnit {
  TypesSeq(this, Api_postgres_async)
}
class TypesSeqOptTest extends MUnit {
  TypesSeqOpt(this, Api_postgres_async)
}
class TypesSetTest extends MUnit {
  TypesSet(this, Api_postgres_async)
}
class TypesSetOptTest extends MUnit {
  TypesSetOpt(this, Api_postgres_async)
}
