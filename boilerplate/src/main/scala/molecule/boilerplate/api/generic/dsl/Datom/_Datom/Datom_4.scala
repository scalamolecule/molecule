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


trait Datom_4[A, B, C, D, 
  Ns_5_OneM[_, _, _, _, _, _, _],
  Ns_4_OneT[_, _, _, _, _, _]]
  extends Molecule_04[A, B, C, D] {

  final lazy val e          : Ns_5_OneM[e         , Long   , A, B, C, D, Long   ] = ???
  final lazy val a          : Ns_5_OneM[a         , String , A, B, C, D, String ] = ???
  final lazy val v          : Ns_5_OneM[v         , Any    , A, B, C, D, Any    ] = ???
  final lazy val t          : Ns_5_OneM[t         , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx         : Ns_5_OneM[tx        , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant  : Ns_5_OneM[txInstant , Date   , A, B, C, D, Date   ] = ???
  final lazy val op         : Ns_5_OneM[op        , Boolean, A, B, C, D, Boolean] = ???
  
  final lazy val e_         : Ns_4_OneT[e_        , Long   , A, B, C, D] = ???
  final lazy val a_         : Ns_4_OneT[a_        , String , A, B, C, D] = ???
  final lazy val v_         : Ns_4_OneT[v_        , Any    , A, B, C, D] = ???
  final lazy val t_         : Ns_4_OneT[t_        , Long   , A, B, C, D] = ???
  final lazy val tx_        : Ns_4_OneT[tx_       , Long   , A, B, C, D] = ???
  final lazy val txInstant_ : Ns_4_OneT[txInstant_, Date   , A, B, C, D] = ???
  final lazy val op_        : Ns_4_OneT[op_       , Boolean, A, B, C, D] = ???
}

