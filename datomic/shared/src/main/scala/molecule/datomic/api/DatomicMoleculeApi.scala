package molecule.datomic.api

import molecule.boilerplate.markers.namespaceMarkers.Molecule_02
import molecule.core.api.Connection
import molecule.core.api.MoleculeApi.MoleculeApi_2
import molecule.datomic.api.DatomicInsert.DatomicInsert_2

object DatomicMoleculeApi {

  class DatomicMoleculeApi_2[A, B](molecule: Molecule_02[A, B]) extends MoleculeApi_2[A, B] {
    override def insert: DatomicInsert_2[A, B] = new DatomicInsert_2(molecule)
    override def query: DatomicQuery[(A, B)] = new DatomicQuery[(A, B)](molecule.elements)
  }
}
