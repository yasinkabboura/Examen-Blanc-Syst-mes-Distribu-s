package coreapi

import java.lang.RuntimeException

abstract class VehicleOverSpeedingTicketException(message : String)
    : RuntimeException(message);
class VehicleOwnerAlreadyAffctedException(message : String) : VehicleOverSpeedingTicketException(message);
data class NegativeVitesseException(
        override var message : String
):RuntimeException(message)
