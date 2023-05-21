package molecule

import molecule.core.MoleculeImplicits_

package object coreTests {

//  object async extends MoleculeImplicits_ with CoreTestsApiAsync
  object async extends MoleculeImplicits_ //with ApiAsyncDummy with ApiAsync
//  object sync extends MoleculeImplicits_ with ApiSyncDummy
  //  object zio extends MoleculeImplicits_ with JdbcApiZio
}
