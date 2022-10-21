/*
* AUTO-GENERATED Molecule DSL for namespace `AVET`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.AVET
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.AVET

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.AVET.AVET_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait AVET_5[A, B, C, D, E] extends Molecule_05[A, B, C, D, E] {
  final lazy val e          : AVET_6_OneM[e         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a          : AVET_6_OneM[a         , String , A, B, C, D, E, String ] = ???
  final lazy val v          : AVET_6_OneM[v         , Any    , A, B, C, D, E, Any    ] = ???
  final lazy val t          : AVET_6_OneM[t         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx         : AVET_6_OneM[tx        , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant  : AVET_6_OneM[txInstant , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val op         : AVET_6_OneM[op        , Boolean, A, B, C, D, E, Boolean] = ???
}

trait AVET_5_OneM[Attr, t, A, B, C, D, E] extends AVET_5[A, B, C, D, E] with ExprOneM_5[Attr, t, A, B, C, D, E, AVET_5_OneM]

