package molecule.coreTests.setup

import molecule.core.MoleculeImplicits_

trait CoreTest extends TestData with MoleculeImplicits_ {
  val isJsPlatform: Boolean
  val database    : String
  val platform    : String
}
