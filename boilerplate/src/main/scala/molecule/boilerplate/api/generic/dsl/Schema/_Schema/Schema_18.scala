/*
* AUTO-GENERATED Molecule DSL for namespace `Schema`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Schema
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Schema

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.Schema.Schema_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait Schema_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Molecule_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] {
  final lazy val t            : Schema_19_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Long   ] = ???
  final lazy val tx           : Schema_19_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Long   ] = ???
  final lazy val txInstant    : Schema_19_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Date   ] = ???
  final lazy val attrId       : Schema_19_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Long   ] = ???
  final lazy val a            : Schema_19_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val part         : Schema_19_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val nsFull       : Schema_19_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val ns           : Schema_19_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val attr         : Schema_19_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val ident        : Schema_19_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val valueType    : Schema_19_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val cardinality  : Schema_19_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val doc          : Schema_19_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val unique       : Schema_19_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, String ] = ???
  final lazy val isComponent  : Schema_19_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Boolean] = ???
  final lazy val noHistory    : Schema_19_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Boolean] = ???
  final lazy val index        : Schema_19_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Boolean] = ???
  final lazy val fulltext     : Schema_19_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Boolean] = ???
  
  final lazy val ident$       : Schema_19_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[String ]] = ???
  final lazy val valueType$   : Schema_19_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[String ]] = ???
  final lazy val cardinality$ : Schema_19_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[String ]] = ???
  final lazy val doc$         : Schema_19_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[String ]] = ???
  final lazy val unique$      : Schema_19_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[String ]] = ???
  final lazy val isComponent$ : Schema_19_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_19_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[Boolean]] = ???
  final lazy val index$       : Schema_19_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_19_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_18_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val tx_          : Schema_18_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val txInstant_   : Schema_18_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val attrId_      : Schema_18_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val a_           : Schema_18_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val part_        : Schema_18_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val nsFull_      : Schema_18_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val ns_          : Schema_18_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val attr_        : Schema_18_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val ident_       : Schema_18_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val valueType_   : Schema_18_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val cardinality_ : Schema_18_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val doc_         : Schema_18_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val unique_      : Schema_18_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val isComponent_ : Schema_18_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val noHistory_   : Schema_18_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val index_       : Schema_18_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
  final lazy val fulltext_    : Schema_18_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] = ???
}

trait Schema_18_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Schema_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with ExprOneM_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Schema_18_OneM]
trait Schema_18_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Schema_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with ExprOneO_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Schema_18_OneO]
trait Schema_18_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] extends Schema_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R] with ExprOneT_18[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Schema_18_OneT]

