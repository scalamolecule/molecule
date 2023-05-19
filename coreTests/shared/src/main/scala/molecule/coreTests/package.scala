package molecule

import molecule.core.MoleculeImplicits_
import molecule.coreTests.api.CoreTestsApiAsync

package object coreTests {

  object async extends MoleculeImplicits_ //with CoreTestsApiAsync
//  object sync extends MoleculeImplicits_ with ApiSync
  //  object zio extends MoleculeImplicits_ with JdbcApiZio
}
