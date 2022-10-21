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


trait AVET_1[A] extends Molecule_01[A] {
  final lazy val e          : AVET_2_OneM[e         , Long   , A, Long   ] = ???
  final lazy val a          : AVET_2_OneM[a         , String , A, String ] = ???
  final lazy val v          : AVET_2_OneM[v         , Any    , A, Any    ] = ???
  final lazy val t          : AVET_2_OneM[t         , Long   , A, Long   ] = ???
  final lazy val tx         : AVET_2_OneM[tx        , Long   , A, Long   ] = ???
  final lazy val txInstant  : AVET_2_OneM[txInstant , Date   , A, Date   ] = ???
  final lazy val op         : AVET_2_OneM[op        , Boolean, A, Boolean] = ???
}

trait AVET_1_OneM[Attr, t, A] extends AVET_1[A] with ExprOneM_1[Attr, t, A, AVET_1_OneM]

