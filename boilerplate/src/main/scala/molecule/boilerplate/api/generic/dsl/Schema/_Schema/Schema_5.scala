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


trait Schema_5[A, B, C, D, E] extends Molecule_05[A, B, C, D, E] {
  final lazy val t            : Schema_6_OneM[t           , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx           : Schema_6_OneM[tx          , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant    : Schema_6_OneM[txInstant   , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val attrId       : Schema_6_OneM[attrId      , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a            : Schema_6_OneM[a           , String , A, B, C, D, E, String ] = ???
  final lazy val part         : Schema_6_OneM[part        , String , A, B, C, D, E, String ] = ???
  final lazy val nsFull       : Schema_6_OneM[nsFull      , String , A, B, C, D, E, String ] = ???
  final lazy val ns           : Schema_6_OneM[ns          , String , A, B, C, D, E, String ] = ???
  final lazy val attr         : Schema_6_OneM[attr        , String , A, B, C, D, E, String ] = ???
  final lazy val ident        : Schema_6_OneM[ident       , String , A, B, C, D, E, String ] = ???
  final lazy val valueType    : Schema_6_OneM[valueType   , String , A, B, C, D, E, String ] = ???
  final lazy val cardinality  : Schema_6_OneM[cardinality , String , A, B, C, D, E, String ] = ???
  final lazy val doc          : Schema_6_OneM[doc         , String , A, B, C, D, E, String ] = ???
  final lazy val unique       : Schema_6_OneM[unique      , String , A, B, C, D, E, String ] = ???
  final lazy val isComponent  : Schema_6_OneM[isComponent , Boolean, A, B, C, D, E, Boolean] = ???
  final lazy val noHistory    : Schema_6_OneM[noHistory   , Boolean, A, B, C, D, E, Boolean] = ???
  final lazy val index        : Schema_6_OneM[index       , Boolean, A, B, C, D, E, Boolean] = ???
  final lazy val fulltext     : Schema_6_OneM[fulltext    , Boolean, A, B, C, D, E, Boolean] = ???
  
  final lazy val ident$       : Schema_6_OneO[ident$      , String , A, B, C, D, E, Option[String ]] = ???
  final lazy val valueType$   : Schema_6_OneO[valueType$  , String , A, B, C, D, E, Option[String ]] = ???
  final lazy val cardinality$ : Schema_6_OneO[cardinality$, String , A, B, C, D, E, Option[String ]] = ???
  final lazy val doc$         : Schema_6_OneO[doc$        , String , A, B, C, D, E, Option[String ]] = ???
  final lazy val unique$      : Schema_6_OneO[unique$     , String , A, B, C, D, E, Option[String ]] = ???
  final lazy val isComponent$ : Schema_6_OneO[isComponent$, Boolean, A, B, C, D, E, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_6_OneO[noHistory$  , Boolean, A, B, C, D, E, Option[Boolean]] = ???
  final lazy val index$       : Schema_6_OneO[index$      , Boolean, A, B, C, D, E, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_6_OneO[fulltext$   , Boolean, A, B, C, D, E, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_5_OneT[t_          , Long   , A, B, C, D, E] = ???
  final lazy val tx_          : Schema_5_OneT[tx_         , Long   , A, B, C, D, E] = ???
  final lazy val txInstant_   : Schema_5_OneT[txInstant_  , Date   , A, B, C, D, E] = ???
  final lazy val attrId_      : Schema_5_OneT[attrId_     , Long   , A, B, C, D, E] = ???
  final lazy val a_           : Schema_5_OneT[a_          , String , A, B, C, D, E] = ???
  final lazy val part_        : Schema_5_OneT[part_       , String , A, B, C, D, E] = ???
  final lazy val nsFull_      : Schema_5_OneT[nsFull_     , String , A, B, C, D, E] = ???
  final lazy val ns_          : Schema_5_OneT[ns_         , String , A, B, C, D, E] = ???
  final lazy val attr_        : Schema_5_OneT[attr_       , String , A, B, C, D, E] = ???
  final lazy val ident_       : Schema_5_OneT[ident_      , String , A, B, C, D, E] = ???
  final lazy val valueType_   : Schema_5_OneT[valueType_  , String , A, B, C, D, E] = ???
  final lazy val cardinality_ : Schema_5_OneT[cardinality_, String , A, B, C, D, E] = ???
  final lazy val doc_         : Schema_5_OneT[doc_        , String , A, B, C, D, E] = ???
  final lazy val unique_      : Schema_5_OneT[unique_     , String , A, B, C, D, E] = ???
  final lazy val isComponent_ : Schema_5_OneT[isComponent_, Boolean, A, B, C, D, E] = ???
  final lazy val noHistory_   : Schema_5_OneT[noHistory_  , Boolean, A, B, C, D, E] = ???
  final lazy val index_       : Schema_5_OneT[index_      , Boolean, A, B, C, D, E] = ???
  final lazy val fulltext_    : Schema_5_OneT[fulltext_   , Boolean, A, B, C, D, E] = ???
}

trait Schema_5_OneM[Attr, t, A, B, C, D, E] extends Schema_5[A, B, C, D, E] with ExprOneM_5[Attr, t, A, B, C, D, E, Schema_5_OneM]
trait Schema_5_OneO[Attr, t, A, B, C, D, E] extends Schema_5[A, B, C, D, E] with ExprOneO_5[Attr, t, A, B, C, D, E, Schema_5_OneO]
trait Schema_5_OneT[Attr, t, A, B, C, D, E] extends Schema_5[A, B, C, D, E] with ExprOneT_5[Attr, t, A, B, C, D, E, Schema_5_OneT]

