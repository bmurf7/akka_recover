import java.util.concurrent.Executors

import akka.actor.ActorSystem
import com.foo.actors.DatabaseServiceActor
import com.foo.repo.EcsRepo
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

object DemoActorApplication extends App {

  // Execution context limited to 1000 threads.
  implicit val executionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(1000))

  // Initialize the actor system
  implicit val system = ActorSystem("test-actor-system")

  // Initialize the repository
  val repo = new EcsRepo()

  // Initialize an actor to schedule operations against a database repo
  system.actorOf(DatabaseServiceActor.props(repo), "database-service")

}

