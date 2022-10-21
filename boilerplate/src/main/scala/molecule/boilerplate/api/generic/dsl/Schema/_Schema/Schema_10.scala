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


trait Schema_10[A, B, C, D, E, F, G, H, I, J] extends Molecule_10[A, B, C, D, E, F, G, H, I, J] {
  final lazy val t            : Schema_11_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, Long   ] = ???
  final lazy val tx           : Schema_11_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, Long   ] = ???
  final lazy val txInstant    : Schema_11_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, Date   ] = ???
  final lazy val attrId       : Schema_11_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, Long   ] = ???
  final lazy val a            : Schema_11_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val part         : Schema_11_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val nsFull       : Schema_11_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val ns           : Schema_11_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val attr         : Schema_11_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val ident        : Schema_11_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val valueType    : Schema_11_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val cardinality  : Schema_11_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val doc          : Schema_11_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val unique       : Schema_11_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, String ] = ???
  final lazy val isComponent  : Schema_11_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, Boolean] = ???
  final lazy val noHistory    : Schema_11_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, Boolean] = ???
  final lazy val index        : Schema_11_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, Boolean] = ???
  final lazy val fulltext     : Schema_11_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, Boolean] = ???
  
  final lazy val ident$       : Schema_11_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, Option[String ]] = ???
  final lazy val valueType$   : Schema_11_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, Option[String ]] = ???
  final lazy val cardinality$ : Schema_11_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, Option[String ]] = ???
  final lazy val doc$         : Schema_11_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, Option[String ]] = ???
  final lazy val unique$      : Schema_11_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, Option[String ]] = ???
  final lazy val isComponent$ : Schema_11_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_11_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, Option[Boolean]] = ???
  final lazy val index$       : Schema_11_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_11_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_10_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val tx_          : Schema_10_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val txInstant_   : Schema_10_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val attrId_      : Schema_10_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val a_           : Schema_10_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val part_        : Schema_10_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val nsFull_      : Schema_10_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val ns_          : Schema_10_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val attr_        : Schema_10_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val ident_       : Schema_10_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val valueType_   : Schema_10_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val cardinality_ : Schema_10_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val doc_         : Schema_10_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val unique_      : Schema_10_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val isComponent_ : Schema_10_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val noHistory_   : Schema_10_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val index_       : Schema_10_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J] = ???
  final lazy val fulltext_    : Schema_10_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J] = ???
}

trait Schema_10_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J] extends Schema_10[A, B, C, D, E, F, G, H, I, J] with ExprOneM_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Schema_10_OneM]
trait Schema_10_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J] extends Schema_10[A, B, C, D, E, F, G, H, I, J] with ExprOneO_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Schema_10_OneO]
trait Schema_10_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J] extends Schema_10[A, B, C, D, E, F, G, H, I, J] with ExprOneT_10[Attr, t, A, B, C, D, E, F, G, H, I, J, Schema_10_OneT]

