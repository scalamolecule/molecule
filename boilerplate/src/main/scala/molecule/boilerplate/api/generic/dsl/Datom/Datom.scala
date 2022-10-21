/*
* AUTO-GENERATED Molecule DSL for namespace `Datom`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Datom
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Datom

import java.util.Date
import molecule.boilerplate.markers.attrMarkers._


object Datom_ {
  trait e          extends OneLong     with GenericAttr
  trait a          extends OneString   with GenericAttr
  trait v          extends OneAny      with GenericAttr
  trait t          extends OneLong     with GenericAttr
  trait tx         extends OneLong     with GenericAttr
  trait txInstant  extends OneDate     with GenericAttr
  trait op         extends OneBoolean  with GenericAttr
  
  trait e_         extends OneLong_    with GenericAttr
  trait a_         extends OneString_  with GenericAttr
  trait v_         extends OneAny_     with GenericAttr
  trait t_         extends OneLong_    with GenericAttr
  trait tx_        extends OneLong_    with GenericAttr
  trait txInstant_ extends OneDate_    with GenericAttr
  trait op_        extends OneBoolean_ with GenericAttr
}

