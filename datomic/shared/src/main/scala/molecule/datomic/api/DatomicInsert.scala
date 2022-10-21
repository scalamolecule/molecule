package molecule.datomic.api

import molecule.boilerplate.markers.namespaceMarkers.Molecule_02
import molecule.core.api.Insert.Insert_2
;

object DatomicInsert {
  class DatomicInsert_2[A, B](molecule: Molecule_02[A, B])
    extends DatomicInsertP(molecule.elements) // JS/JVM platform implementations
      with Insert_2[A, B]
}
