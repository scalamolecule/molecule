package molecule.document
import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.document.mongodb.spi._

package object mongodb {
  object async extends MoleculeImplicits_ with ApiAsync with SpiAsync_mongodb
  object sync extends MoleculeImplicits_ with ApiSync with SpiSync_mongodb
  object zio extends MoleculeImplicits_ with ApiZio with SpiZio_mongodb
}