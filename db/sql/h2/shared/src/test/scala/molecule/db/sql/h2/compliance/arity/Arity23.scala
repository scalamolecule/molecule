package molecule.db.sql.h2.compliance.arity

import molecule.core.setup.MUnit
import molecule.db.compliance.test.arity.Arity23
import molecule.db.compliance.test.bind.*
import molecule.db.compliance.test.bind.types.*
import molecule.db.sql.h2.setup.Api_h2_async

class Arity23Test extends MUnit {
  Arity23(this, Api_h2_async)
}
