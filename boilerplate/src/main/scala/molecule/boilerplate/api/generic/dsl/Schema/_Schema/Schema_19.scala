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


trait Schema_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Molecule_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] {
  final lazy val t            : Schema_20_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Long   ] = ???
  final lazy val tx           : Schema_20_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Long   ] = ???
  final lazy val txInstant    : Schema_20_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Date   ] = ???
  final lazy val attrId       : Schema_20_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Long   ] = ???
  final lazy val a            : Schema_20_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val part         : Schema_20_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val nsFull       : Schema_20_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val ns           : Schema_20_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val attr         : Schema_20_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val ident        : Schema_20_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val valueType    : Schema_20_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val cardinality  : Schema_20_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val doc          : Schema_20_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val unique       : Schema_20_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, String ] = ???
  final lazy val isComponent  : Schema_20_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Boolean] = ???
  final lazy val noHistory    : Schema_20_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Boolean] = ???
  final lazy val index        : Schema_20_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Boolean] = ???
  final lazy val fulltext     : Schema_20_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Boolean] = ???
  
  final lazy val ident$       : Schema_20_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[String ]] = ???
  final lazy val valueType$   : Schema_20_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[String ]] = ???
  final lazy val cardinality$ : Schema_20_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[String ]] = ???
  final lazy val doc$         : Schema_20_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[String ]] = ???
  final lazy val unique$      : Schema_20_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[String ]] = ???
  final lazy val isComponent$ : Schema_20_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_20_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[Boolean]] = ???
  final lazy val index$       : Schema_20_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_20_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_19_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val tx_          : Schema_19_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val txInstant_   : Schema_19_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val attrId_      : Schema_19_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val a_           : Schema_19_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val part_        : Schema_19_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val nsFull_      : Schema_19_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val ns_          : Schema_19_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val attr_        : Schema_19_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val ident_       : Schema_19_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val valueType_   : Schema_19_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val cardinality_ : Schema_19_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val doc_         : Schema_19_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val unique_      : Schema_19_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val isComponent_ : Schema_19_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val noHistory_   : Schema_19_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val index_       : Schema_19_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
  final lazy val fulltext_    : Schema_19_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] = ???
}

trait Schema_19_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Schema_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with ExprOneM_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Schema_19_OneM]
trait Schema_19_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Schema_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with ExprOneO_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Schema_19_OneO]
trait Schema_19_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] extends Schema_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S] with ExprOneT_19[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Schema_19_OneT]

