package molecule.db.core.api

import molecule.base.error.ModelError
import molecule.core.dataModel.DataModel
import molecule.db.core.action.*

// Missing tuple type ops in scala 3.3.6 (exist in 3.7)

// (demands NonEmptyTuple in scala 3.3.x)
type Head[X <: Tuple] = X match {
  case x *: _ => x
}

// (demands NonEmptyTuple in scala 3.3.x)
type Tail[X <: Tuple] <: Tuple = X match {
  case _ *: xs => xs
}

/** Type of the reversed tuple */
type Reverse[X <: Tuple] = ReverseOnto[X, EmptyTuple]

/** Prepends all elements of a tuple in reverse order onto the other tuple */
type ReverseOnto[From <: Tuple, +To <: Tuple] <: Tuple = From match
  case x *: xs => ReverseOnto[xs, x *: To]
  case EmptyTuple => To


// Distinguish non-tuple type from tuple type

//type IsTuple[T] = T match
//  case EmptyTuple         => true
//  case h *: t             => true
//  case _                  => false
//
//sealed trait NotTuple[T]
//object NotTuple:
//  given scalar[T](using ev: IsTuple[T] =:= false): NotTuple[T] = new NotTuple[T] {}



//sealed trait NotTuple[T]
//object NotTuple:
//  given scalar[T](using ev: T match
//    case EmptyTuple => false
//    case _ *: _ => false
//    case _ => true
//      =:= true): NotTuple[T] = new NotTuple[T] {}



sealed trait NotTuple[T]
object NotTuple:
  given scalar[T](using ev: T <:< Tuple =:= false): NotTuple[T] = new NotTuple[T] {}