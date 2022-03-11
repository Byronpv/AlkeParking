import java.util.*
import kotlin.math.floor

const val TWO_HOURS = 5000L
const val FIFTEEN_MINUTES = 2000L

data class ParkingSpace(var vehicle: Vehicle) {
    private var vehicleList = mutableSetOf<Vehicle>()

    companion object {
        var countRemoveVehicles = 0
        var addFee = 0
    }


    fun getListVehicles(vehicles: MutableSet<Vehicle>) {
        vehicleList = vehicles
    }


    fun checkOutVehicle(plate: String?, indexVehicle: Int, vehicleType: VehicleType?) {
        when (plate?.let { vehicleList.elementAt(indexVehicle).plate.contains(it) }) {
            true -> {
                val fee = calculateFee(vehicleList.elementAt(indexVehicle), vehicleType)
                onSuccess(fee, vehicleList.elementAt(indexVehicle))
            }
            else -> onError()
        }


    }

    private fun calculateFee(vehicle: Vehicle, vehicleType: VehicleType?): Int {

        val parkedTime = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis)
        val hasDiscountCard = vehicle.discountCard?.isNotBlank() == true
        var totalFee = 0
        var timeOut = 0

        when (parkedTime <= TWO_HOURS) {
            true -> totalFee = vehicleType?.rate ?: 0
            else -> {
                for (i in 0..parkedTime) {
                    if (parkedTime > TWO_HOURS) {
                        val parkedTimeOut = parkedTime - TWO_HOURS
                        for (i in 0..parkedTimeOut step FIFTEEN_MINUTES) {
                            timeOut++
                        }
                        totalFee = vehicleType?.rate?.plus(timeOut * 5) ?: 0
                        break
                    }

                }

            }
        }
        when (hasDiscountCard) {
            true -> {
                val discountCard = floor(totalFee * 0.15).toInt()
                totalFee -= discountCard
            }
            false -> println("----There is no discount----")
        }
        return totalFee
    }


    private fun onSuccess(fee: Int, vehicle: Vehicle) {

        println("|Your fee is $fee. Come back soon.|")
        vehicleList.remove(vehicle)
        countRemoveVehicles += 1
        Parking.totalEarnings = Pair(countRemoveVehicles, fee + addFee)
        addFee += fee
    }

    private fun onError() = println("*** Sorry, the check-out failed ***")


}




