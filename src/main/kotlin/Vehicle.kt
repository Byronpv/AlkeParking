import java.util.*
import kotlin.concurrent.thread

data class Vehicle(
    val plate: String,
    val type: VehicleType,
    val checkInTime: Calendar,
    val discountCard: String?
) {

    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int = this.plate.hashCode()

}


