package molecule

import molecule.core.MoleculeImplicits_

package object coreTests {

  object async extends MoleculeImplicits_
  object sync extends MoleculeImplicits_
  object zio extends MoleculeImplicits_
}
