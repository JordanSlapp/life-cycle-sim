package actors

import akka.actor.{ActorRef, Props, ActorLogging, Actor}

object SimManager {
  
  case object CreateWarren
//  case object CreateDen
  case object Multiply
  

  def props: Props = {

    Props(new SimManager)

  }
}

class SimManager extends Actor with ActorLogging {

  import SimManager._

  private val den = createDen()
  
  override def receive: Receive = {
    case CreateWarren =>
      createWarren()
//    case CreateDen =>
//      createDen()
    case Multiply =>
      multiply()
  }

  protected def createWarren(): ActorRef =
    context.actorOf(Warren.props(3), "Warren")
    
  protected def createDen(): ActorRef =
    context.actorOf(Den.props, "Den")
  
    protected def multiply(): Unit = { 
      den ! Den.Breed

      den ! Den.ChangeBreedRate(2.0)
      den ! Den.Breed

}