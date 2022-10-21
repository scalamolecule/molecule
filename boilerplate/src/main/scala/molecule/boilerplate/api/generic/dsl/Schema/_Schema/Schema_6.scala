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


trait Schema_6[A, B, C, D, E, F] extends Molecule_06[A, B, C, D, E, F] {
  final lazy val t            : Schema_7_OneM[t           , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val tx           : Schema_7_OneM[tx          , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val txInstant    : Schema_7_OneM[txInstant   , Date   , A, B, C, D, E, F, Date   ] = ???
  final lazy val attrId       : Schema_7_OneM[attrId      , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val a            : Schema_7_OneM[a           , String , A, B, C, D, E, F, String ] = ???
  final lazy val part         : Schema_7_OneM[part        , String , A, B, C, D, E, F, String ] = ???
  final lazy val nsFull       : Schema_7_OneM[nsFull      , String , A, B, C, D, E, F, String ] = ???
  final lazy val ns           : Schema_7_OneM[ns          , String , A, B, C, D, E, F, String ] = ???
  final lazy val attr         : Schema_7_OneM[attr        , String , A, B, C, D, E, F, String ] = ???
  final lazy val ident        : Schema_7_OneM[ident       , String , A, B, C, D, E, F, String ] = ???
  final lazy val valueType    : Schema_7_OneM[valueType   , String , A, B, C, D, E, F, String ] = ???
  final lazy val cardinality  : Schema_7_OneM[cardinality , String , A, B, C, D, E, F, String ] = ???
  final lazy val doc          : Schema_7_OneM[doc         , String , A, B, C, D, E, F, String ] = ???
  final lazy val unique       : Schema_7_OneM[unique      , String , A, B, C, D, E, F, String ] = ???
  final lazy val isComponent  : Schema_7_OneM[isComponent , Boolean, A, B, C, D, E, F, Boolean] = ???
  final lazy val noHistory    : Schema_7_OneM[noHistory   , Boolean, A, B, C, D, E, F, Boolean] = ???
  final lazy val index        : Schema_7_OneM[index       , Boolean, A, B, C, D, E, F, Boolean] = ???
  final lazy val fulltext     : Schema_7_OneM[fulltext    , Boolean, A, B, C, D, E, F, Boolean] = ???
  
  final lazy val ident$       : Schema_7_OneO[ident$      , String , A, B, C, D, E, F, Option[String ]] = ???
  final lazy val valueType$   : Schema_7_OneO[valueType$  , String , A, B, C, D, E, F, Option[String ]] = ???
  final lazy val cardinality$ : Schema_7_OneO[cardinality$, String , A, B, C, D, E, F, Option[String ]] = ???
  final lazy val doc$         : Schema_7_OneO[doc$        , String , A, B, C, D, E, F, Option[String ]] = ???
  final lazy val unique$      : Schema_7_OneO[unique$     , String , A, B, C, D, E, F, Option[String ]] = ???
  final lazy val isComponent$ : Schema_7_OneO[isComponent$, Boolean, A, B, C, D, E, F, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_7_OneO[noHistory$  , Boolean, A, B, C, D, E, F, Option[Boolean]] = ???
  final lazy val index$       : Schema_7_OneO[index$      , Boolean, A, B, C, D, E, F, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_7_OneO[fulltext$   , Boolean, A, B, C, D, E, F, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_6_OneT[t_          , Long   , A, B, C, D, E, F] = ???
  final lazy val tx_          : Schema_6_OneT[tx_         , Long   , A, B, C, D, E, F] = ???
  final lazy val txInstant_   : Schema_6_OneT[txInstant_  , Date   , A, B, C, D, E, F] = ???
  final lazy val attrId_      : Schema_6_OneT[attrId_     , Long   , A, B, C, D, E, F] = ???
  final lazy val a_           : Schema_6_OneT[a_          , String , A, B, C, D, E, F] = ???
  final lazy val part_        : Schema_6_OneT[part_       , String , A, B, C, D, E, F] = ???
  final lazy val nsFull_      : Schema_6_OneT[nsFull_     , String , A, B, C, D, E, F] = ???
  final lazy val ns_          : Schema_6_OneT[ns_         , String , A, B, C, D, E, F] = ???
  final lazy val attr_        : Schema_6_OneT[attr_       , String , A, B, C, D, E, F] = ???
  final lazy val ident_       : Schema_6_OneT[ident_      , String , A, B, C, D, E, F] = ???
  final lazy val valueType_   : Schema_6_OneT[valueType_  , String , A, B, C, D, E, F] = ???
  final lazy val cardinality_ : Schema_6_OneT[cardinality_, String , A, B, C, D, E, F] = ???
  final lazy val doc_         : Schema_6_OneT[doc_        , String , A, B, C, D, E, F] = ???
  final lazy val unique_      : Schema_6_OneT[unique_     , String , A, B, C, D, E, F] = ???
  final lazy val isComponent_ : Schema_6_OneT[isComponent_, Boolean, A, B, C, D, E, F] = ???
  final lazy val noHistory_   : Schema_6_OneT[noHistory_  , Boolean, A, B, C, D, E, F] = ???
  final lazy val index_       : Schema_6_OneT[index_      , Boolean, A, B, C, D, E, F] = ???
  final lazy val fulltext_    : Schema_6_OneT[fulltext_   , Boolean, A, B, C, D, E, F] = ???
}

trait Schema_6_OneM[Attr, t, A, B, C, D, E, F] extends Schema_6[A, B, C, D, E, F] with ExprOneM_6[Attr, t, A, B, C, D, E, F, Schema_6_OneM]
trait Schema_6_OneO[Attr, t, A, B, C, D, E, F] extends Schema_6[A, B, C, D, E, F] with ExprOneO_6[Attr, t, A, B, C, D, E, F, Schema_6_OneO]
trait Schema_6_OneT[Attr, t, A, B, C, D, E, F] extends Schema_6[A, B, C, D, E, F] with ExprOneT_6[Attr, t, A, B, C, D, E, F, Schema_6_OneT]

