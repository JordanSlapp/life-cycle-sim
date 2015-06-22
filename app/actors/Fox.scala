package actors

import akka.actor.{Props, ActorLogging, Actor}

object Fox {


  def props: Props = {

    Props(new Fox)

  }

}

class Fox extends Actor with ActorLogging{

  println("Fox Created")

  import Fox._

  override def receive: Receive = {
    case _ => println("TODO")
  }

}