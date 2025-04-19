//package molecule.core.util
//
//import cats.effect.{Bracket, ExitCase}
//import cats.syntax.all.*
//import monix.reactive.Observable
//
//object CatsInstances {
//  implicit def observableBracket: Bracket[Observable, Throwable] =
//    new Bracket[Observable, Throwable] {
//      def pure[A](a: A): Observable[A] =
//        Observable.pure(a)
//
//      def raiseError[A](e: Throwable): Observable[A] =
//        Observable.raiseError(e)
//
//      def bracketCase[A, B](
//        acquire: Observable[A])(
//        use: A => Observable[B])(
//        release: (A, ExitCase[Throwable]) => Observable[Unit]
//      ): Observable[B] = {
//        acquire.bracket(
//          use = use,
//          release = (a, exitCase) => release(a, exitCase)
//        )
//      }
//
//      def flatMap[A, B](fa: Observable[A])(f: A => Observable[B]): Observable[B] =
//        fa.flatMap(f)
//
//      def tailRecM[A, B](a: A)(f: A => Observable[Either[A, B]]): Observable[B] =
//        Observable.tailRecM(a)(f)
//    }
//
//    implicit val observableSync: Sync[Observable] = new Sync[Observable] {
//      def pure[A](a: A): Observable[A] = Observable.pure(a)
//      def raiseError[A](e: Throwable): Observable[A] = Observable.raiseError(e)
//      def handleErrorWith[A](fa: Observable[A])(f: Throwable => Observable[A]): Observable[A] =
//        fa.onErrorRecoverWith { case e => f(e) }
//      def flatMap[A, B](fa: Observable[A])(f: A => Observable[B]): Observable[B] =
//        fa.flatMap(f)
//      def tailRecM[A, B](a: A)(f: A => Observable[Either[A, B]]): Observable[B] =
//        Observable.tailRecM(a)(f)
//      def suspend[A](thunk: => Observable[A]): Observable[A] =
//        Observable.defer(thunk)
//      def bracketCase[A, B](acquire: Observable[A])(use: A => Observable[B])
//                           (release: (A, ExitCase[Throwable]) => Observable[Unit]): Observable[B] =
//        Resource.make(acquire)(a => release(a, ExitCase.Completed)).use(use)
//    }
//
//}
//
