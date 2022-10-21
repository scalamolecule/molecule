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


trait Datom_5[A, B, C, D, E, 
  Ns_6_OneM[_, _, _, _, _, _, _, _],
  Ns_5_OneT[_, _, _, _, _, _, _]]
  extends Molecule_05[A, B, C, D, E] {

  final lazy val e          : Ns_6_OneM[e         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a          : Ns_6_OneM[a         , String , A, B, C, D, E, String ] = ???
  final lazy val v          : Ns_6_OneM[v         , Any    , A, B, C, D, E, Any    ] = ???
  final lazy val t          : Ns_6_OneM[t         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx         : Ns_6_OneM[tx        , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant  : Ns_6_OneM[txInstant , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val op         : Ns_6_OneM[op        , Boolean, A, B, C, D, E, Boolean] = ???
  
  final lazy val e_         : Ns_5_OneT[e_        , Long   , A, B, C, D, E] = ???
  final lazy val a_         : Ns_5_OneT[a_        , String , A, B, C, D, E] = ???
  final lazy val v_         : Ns_5_OneT[v_        , Any    , A, B, C, D, E] = ???
  final lazy val t_         : Ns_5_OneT[t_        , Long   , A, B, C, D, E] = ???
  final lazy val tx_        : Ns_5_OneT[tx_       , Long   , A, B, C, D, E] = ???
  final lazy val txInstant_ : Ns_5_OneT[txInstant_, Date   , A, B, C, D, E] = ???
  final lazy val op_        : Ns_5_OneT[op_       , Boolean, A, B, C, D, E] = ???
}

