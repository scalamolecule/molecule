package molecule.db.datalog.core.spi

import java.util.stream.Stream as jStream
import java.util.{ArrayList as jArrayList, Iterator as jIterator, List as jList}
import cats.effect.IO
import fs2.Stream
import molecule.base.error.{ExecutionError, MoleculeError}
import molecule.core.action.*
import molecule.core.spi.*
import zio.*
import zio.stream.*

trait StreamingDatomic {

  def fs2streamDatomic[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getStream: (Query[Tpl], Conn) => (jStream[jList[AnyRef]], jList[AnyRef] => Any)
  )(implicit conn: Conn): fs2.Stream[IO, Tpl] = {
    val inspectIO: IO[Unit] =
      if (q.doInspect) IO.blocking(inspect(q, conn))
      else IO.unit

    def chunkify(it: jIterator[jList[AnyRef]], row2tpl: jList[AnyRef] => Any): IO[Option[(List[Tpl], Unit)]] =
      IO.blocking {
        val buffer = List.newBuilder[Tpl]
        var count  = 0
        while (count < chunkSize && it.hasNext) {
          buffer += row2tpl(it.next()).asInstanceOf[Tpl]
          count += 1
        }
        val result = buffer.result()
        if (result.isEmpty) None else Some((result, ()))
      }

    for {
      _ <- fs2.Stream.eval(inspectIO)
      chunk <- fs2.Stream.bracket(IO.blocking(getStream(q, conn))) {
        case (javaStream, _) => IO.blocking(javaStream.close())
      }.flatMap { case (javaStream, row2tpl) =>
        val it = javaStream.iterator()
        fs2.Stream.unfoldEval(())(_ => chunkify(it, row2tpl))
          .flatMap(chunk => fs2.Stream.emits(chunk))
      }
    } yield chunk
  }


  def zioStreamDatomic[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getStream: (Query[Tpl], Conn) => (jStream[jList[AnyRef]], jList[AnyRef] => Any)
  ): ZStream[Conn, MoleculeError, Tpl] = {

    def attemptBlockingMolecule[A](block: => A): ZIO[Any, MoleculeError, A] =
      ZIO.attemptBlocking(block).mapError {
        case e: MoleculeError => e
        case other            => ExecutionError(other.getMessage)
      }

    def inspectZIO(conn: Conn): ZIO[Any, MoleculeError, Unit] =
      if (q.doInspect)
        attemptBlockingMolecule(inspect(q, conn))
      else
        ZIO.unit

    def acquire(conn: Conn): ZIO[Any, MoleculeError, (jStream[jList[AnyRef]], jList[AnyRef] => Any)] =
      attemptBlockingMolecule(getStream(q, conn))

    def release(stream: jStream[_]): UIO[Unit] =
      ZIO.attemptBlocking(stream.close()).orDie

    def chunkify(
      it: jIterator[jList[AnyRef]],
      row2tpl: jList[AnyRef] => Any
    ): ZIO[Any, MoleculeError, Option[(List[Tpl], Unit)]] =
      attemptBlockingMolecule {
        val buffer = List.newBuilder[Tpl]
        var count  = 0
        while (count < chunkSize && it.hasNext) {
          buffer += row2tpl(it.next()).asInstanceOf[Tpl]
          count += 1
        }
        val chunk = buffer.result()
        if (chunk.isEmpty) None else Some((chunk, ()))
      }

    for {
      conn <- ZStream.fromZIO(ZIO.service[Conn])
      _ <- ZStream.fromZIO(inspectZIO(conn))
      (javaStream, row2tpl) <- ZStream.acquireReleaseWith(acquire(conn)) {
        case (stream, _) => release(stream)
      }
      it = javaStream.iterator()
      chunk <- ZStream.unfoldZIO(())(_ => chunkify(it, row2tpl))
      item <- ZStream.fromIterable(chunk)
    } yield item
  }
}