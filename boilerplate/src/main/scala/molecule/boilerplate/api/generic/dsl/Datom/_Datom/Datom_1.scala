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


trait Datom_1[A, 
  Ns_2_OneM[_, _, _, _],
  Ns_1_OneT[_, _, _]]
  extends Molecule_01[A] {

  final lazy val e          : Ns_2_OneM[e         , Long   , A, Long   ] = ???
  final lazy val a          : Ns_2_OneM[a         , String , A, String ] = ???
  final lazy val v          : Ns_2_OneM[v         , Any    , A, Any    ] = ???
  final lazy val t          : Ns_2_OneM[t         , Long   , A, Long   ] = ???
  final lazy val tx         : Ns_2_OneM[tx        , Long   , A, Long   ] = ???
  final lazy val txInstant  : Ns_2_OneM[txInstant , Date   , A, Date   ] = ???
  final lazy val op         : Ns_2_OneM[op        , Boolean, A, Boolean] = ???
  
  final lazy val e_         : Ns_1_OneT[e_        , Long   , A] = ???
  final lazy val a_         : Ns_1_OneT[a_        , String , A] = ???
  final lazy val v_         : Ns_1_OneT[v_        , Any    , A] = ???
  final lazy val t_         : Ns_1_OneT[t_        , Long   , A] = ???
  final lazy val tx_        : Ns_1_OneT[tx_       , Long   , A] = ???
  final lazy val txInstant_ : Ns_1_OneT[txInstant_, Date   , A] = ???
  final lazy val op_        : Ns_1_OneT[op_       , Boolean, A] = ???
}

