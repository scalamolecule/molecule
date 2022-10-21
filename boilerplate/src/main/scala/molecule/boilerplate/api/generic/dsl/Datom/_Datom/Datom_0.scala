/*
* AUTO-GENERATED Molecule DSL for namespace `Datom`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Datom
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Datom

import java.util.Date
import molecule.boilerplate.api.generic.dsl.Datom.Datom_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait Datom_0[
  Ns_1_OneM[_, _, _],
  Ns_0_OneT[_, _]]
  extends Molecule_00 {

  final lazy val e          : Ns_1_OneM[e         , Long   , Long   ] = ???
  final lazy val a          : Ns_1_OneM[a         , String , String ] = ???
  final lazy val v          : Ns_1_OneM[v         , Any    , Any    ] = ???
  final lazy val t          : Ns_1_OneM[t         , Long   , Long   ] = ???
  final lazy val tx         : Ns_1_OneM[tx        , Long   , Long   ] = ???
  final lazy val txInstant  : Ns_1_OneM[txInstant , Date   , Date   ] = ???
  final lazy val op         : Ns_1_OneM[op        , Boolean, Boolean] = ???
  
  final lazy val e_         : Ns_0_OneT[e_        , Long   ] = ???
  final lazy val a_         : Ns_0_OneT[a_        , String ] = ???
  final lazy val v_         : Ns_0_OneT[v_        , Any    ] = ???
  final lazy val t_         : Ns_0_OneT[t_        , Long   ] = ???
  final lazy val tx_        : Ns_0_OneT[tx_       , Long   ] = ???
  final lazy val txInstant_ : Ns_0_OneT[txInstant_, Date   ] = ???
  final lazy val op_        : Ns_0_OneT[op_       , Boolean] = ???
}

