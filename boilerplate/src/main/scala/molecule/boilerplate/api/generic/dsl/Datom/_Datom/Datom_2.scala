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


trait Datom_2[A, B, 
  Ns_3_OneM[_, _, _, _, _],
  Ns_2_OneT[_, _, _, _]]
  extends Molecule_02[A, B] {

  final lazy val e          : Ns_3_OneM[e         , Long   , A, B, Long   ] = ???
  final lazy val a          : Ns_3_OneM[a         , String , A, B, String ] = ???
  final lazy val v          : Ns_3_OneM[v         , Any    , A, B, Any    ] = ???
  final lazy val t          : Ns_3_OneM[t         , Long   , A, B, Long   ] = ???
  final lazy val tx         : Ns_3_OneM[tx        , Long   , A, B, Long   ] = ???
  final lazy val txInstant  : Ns_3_OneM[txInstant , Date   , A, B, Date   ] = ???
  final lazy val op         : Ns_3_OneM[op        , Boolean, A, B, Boolean] = ???
  
  final lazy val e_         : Ns_2_OneT[e_        , Long   , A, B] = ???
  final lazy val a_         : Ns_2_OneT[a_        , String , A, B] = ???
  final lazy val v_         : Ns_2_OneT[v_        , Any    , A, B] = ???
  final lazy val t_         : Ns_2_OneT[t_        , Long   , A, B] = ???
  final lazy val tx_        : Ns_2_OneT[tx_       , Long   , A, B] = ???
  final lazy val txInstant_ : Ns_2_OneT[txInstant_, Date   , A, B] = ???
  final lazy val op_        : Ns_2_OneT[op_       , Boolean, A, B] = ???
}

