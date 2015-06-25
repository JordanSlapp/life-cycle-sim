package actors

import akka.actor._
import scala.concurrent.duration._
import scala.util.Random


object Warren {

  case object CreateRabbit

  case object rabbitsBreed

  def props(population: Int): Props = {
    Props(new Warren(population, breedRate = 1, deathRate = 1))

  }
}

class Warren(population: Int, breedRate: Double, deathRate: Double) extends Actor with ActorLogging {

  import context.dispatcher

  println("Warren Created")

 // val scheduler = context.system.scheduler.schedule(1 second, 500 millisecond, self, Warren.rabbitsBreed)

  private val rabbits: List[ActorRef] = List.empty[ActorRef]
  addRabbits(population)

  override def receive: Receive = {
    case rabbitsBreed =>
      rabbitsMate(population)

    case Terminated =>

  }

  protected def addRabbits(rabbitsPop: Int): Unit = {
    for (rab <- 1 to rabbitsPop) {
      rabbits :+ context.actorOf(Rabbit.props)
    }
  }

  protected def rabbitsMate(rabbits: Int): Unit = {
    val amountOfRabbitsMated: Int = rabbits % 2
    for (rab <- 1 to amountOfRabbitsMated) {
      addRabbits(Random.nextInt(5))
    }
  }
}