import java.util.*
import kotlin.math.ceil

const val TWO_HOURS = 720000L
const val FIFTEEN_MINUTES = 90000L

data class ParkingSpace(var vehicle: Vehicle) {
    var vehicleList = mutableSetOf<Vehicle>()

    companion object {
        var countRemoveVehicles = 0
        var addFee = 0
    }


    fun setListVehicles(vehicles: MutableSet<Vehicle>) {
        vehicleList = vehicles
    }


    fun checkOutVehicle( vehicleType: VehicleType?) {
        val fee = calculateFee(vehicle, vehicleType)
        onSuccess(fee, vehicle)

    }


    private fun calculateFee(vehicle: Vehicle, vehicleType: VehicleType?): Int {

        val parkedTime = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis)
        val hasDiscountCard = vehicle.discountCard?.isNotBlank() ?: false
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
                val discountCard = ceil(totalFee * 0.15).toInt()
                println(discountCard)
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
        Parking.totalEarnings = Pair(countRemoveVehicles, (fee + addFee))
        addFee += fee
    }

    private fun onError() = println("*** Sorry, the check-out failed ***")


}




