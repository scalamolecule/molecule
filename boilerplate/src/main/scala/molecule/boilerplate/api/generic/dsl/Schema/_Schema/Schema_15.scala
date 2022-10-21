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


trait Schema_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Molecule_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] {
  final lazy val t            : Schema_16_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Long   ] = ???
  final lazy val tx           : Schema_16_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Long   ] = ???
  final lazy val txInstant    : Schema_16_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Date   ] = ???
  final lazy val attrId       : Schema_16_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Long   ] = ???
  final lazy val a            : Schema_16_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val part         : Schema_16_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val nsFull       : Schema_16_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val ns           : Schema_16_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val attr         : Schema_16_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val ident        : Schema_16_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val valueType    : Schema_16_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val cardinality  : Schema_16_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val doc          : Schema_16_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val unique       : Schema_16_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, String ] = ???
  final lazy val isComponent  : Schema_16_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Boolean] = ???
  final lazy val noHistory    : Schema_16_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Boolean] = ???
  final lazy val index        : Schema_16_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Boolean] = ???
  final lazy val fulltext     : Schema_16_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Boolean] = ???
  
  final lazy val ident$       : Schema_16_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[String ]] = ???
  final lazy val valueType$   : Schema_16_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[String ]] = ???
  final lazy val cardinality$ : Schema_16_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[String ]] = ???
  final lazy val doc$         : Schema_16_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[String ]] = ???
  final lazy val unique$      : Schema_16_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[String ]] = ???
  final lazy val isComponent$ : Schema_16_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_16_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[Boolean]] = ???
  final lazy val index$       : Schema_16_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_16_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_15_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val tx_          : Schema_15_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val txInstant_   : Schema_15_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val attrId_      : Schema_15_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val a_           : Schema_15_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val part_        : Schema_15_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val nsFull_      : Schema_15_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val ns_          : Schema_15_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val attr_        : Schema_15_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val ident_       : Schema_15_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val valueType_   : Schema_15_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val cardinality_ : Schema_15_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val doc_         : Schema_15_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val unique_      : Schema_15_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val isComponent_ : Schema_15_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val noHistory_   : Schema_15_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val index_       : Schema_15_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
  final lazy val fulltext_    : Schema_15_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] = ???
}

trait Schema_15_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Schema_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with ExprOneM_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Schema_15_OneM]
trait Schema_15_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Schema_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with ExprOneO_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Schema_15_OneO]
trait Schema_15_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] extends Schema_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O] with ExprOneT_15[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Schema_15_OneT]

