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


trait Datom_3[A, B, C, 
  Ns_4_OneM[_, _, _, _, _, _],
  Ns_3_OneT[_, _, _, _, _]]
  extends Molecule_03[A, B, C] {

  final lazy val e          : Ns_4_OneM[e         , Long   , A, B, C, Long   ] = ???
  final lazy val a          : Ns_4_OneM[a         , String , A, B, C, String ] = ???
  final lazy val v          : Ns_4_OneM[v         , Any    , A, B, C, Any    ] = ???
  final lazy val t          : Ns_4_OneM[t         , Long   , A, B, C, Long   ] = ???
  final lazy val tx         : Ns_4_OneM[tx        , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant  : Ns_4_OneM[txInstant , Date   , A, B, C, Date   ] = ???
  final lazy val op         : Ns_4_OneM[op        , Boolean, A, B, C, Boolean] = ???
  
  final lazy val e_         : Ns_3_OneT[e_        , Long   , A, B, C] = ???
  final lazy val a_         : Ns_3_OneT[a_        , String , A, B, C] = ???
  final lazy val v_         : Ns_3_OneT[v_        , Any    , A, B, C] = ???
  final lazy val t_         : Ns_3_OneT[t_        , Long   , A, B, C] = ???
  final lazy val tx_        : Ns_3_OneT[tx_       , Long   , A, B, C] = ???
  final lazy val txInstant_ : Ns_3_OneT[txInstant_, Date   , A, B, C] = ???
  final lazy val op_        : Ns_3_OneT[op_       , Boolean, A, B, C] = ???
}

