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


trait Schema_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Molecule_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] {
  final lazy val t            : Schema_18_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Long   ] = ???
  final lazy val tx           : Schema_18_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Long   ] = ???
  final lazy val txInstant    : Schema_18_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Date   ] = ???
  final lazy val attrId       : Schema_18_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Long   ] = ???
  final lazy val a            : Schema_18_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val part         : Schema_18_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val nsFull       : Schema_18_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val ns           : Schema_18_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val attr         : Schema_18_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val ident        : Schema_18_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val valueType    : Schema_18_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val cardinality  : Schema_18_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val doc          : Schema_18_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val unique       : Schema_18_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, String ] = ???
  final lazy val isComponent  : Schema_18_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Boolean] = ???
  final lazy val noHistory    : Schema_18_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Boolean] = ???
  final lazy val index        : Schema_18_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Boolean] = ???
  final lazy val fulltext     : Schema_18_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Boolean] = ???
  
  final lazy val ident$       : Schema_18_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[String ]] = ???
  final lazy val valueType$   : Schema_18_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[String ]] = ???
  final lazy val cardinality$ : Schema_18_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[String ]] = ???
  final lazy val doc$         : Schema_18_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[String ]] = ???
  final lazy val unique$      : Schema_18_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[String ]] = ???
  final lazy val isComponent$ : Schema_18_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_18_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[Boolean]] = ???
  final lazy val index$       : Schema_18_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_18_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_17_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val tx_          : Schema_17_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val txInstant_   : Schema_17_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val attrId_      : Schema_17_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val a_           : Schema_17_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val part_        : Schema_17_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val nsFull_      : Schema_17_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val ns_          : Schema_17_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val attr_        : Schema_17_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val ident_       : Schema_17_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val valueType_   : Schema_17_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val cardinality_ : Schema_17_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val doc_         : Schema_17_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val unique_      : Schema_17_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val isComponent_ : Schema_17_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val noHistory_   : Schema_17_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val index_       : Schema_17_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
  final lazy val fulltext_    : Schema_17_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] = ???
}

trait Schema_17_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Schema_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with ExprOneM_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Schema_17_OneM]
trait Schema_17_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Schema_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with ExprOneO_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Schema_17_OneO]
trait Schema_17_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] extends Schema_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q] with ExprOneT_17[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Schema_17_OneT]

